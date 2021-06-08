// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

import org.codehaus.groovy.control.messages.ExceptionMessage;
import org.codehaus.groovy.GroovyBugError;
import java.util.Collection;
import org.codehaus.groovy.control.io.ReaderSource;
import org.codehaus.groovy.control.io.InputStreamReaderSource;
import java.io.InputStream;
import java.net.URL;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.transform.ASTTransformationVisitor;
import org.codehaus.groovy.classgen.InnerClassVisitor;
import org.codehaus.groovy.classgen.EnumVisitor;
import java.util.HashMap;
import java.util.ArrayList;
import org.codehaus.groovy.classgen.ClassGenerator;
import groovyjarjarasm.asm.ClassVisitor;
import org.codehaus.groovy.ast.ASTNode;
import groovyjarjarasm.asm.ClassWriter;
import org.codehaus.groovy.classgen.AsmClassGenerator;
import org.codehaus.groovy.classgen.ExtendedVerifier;
import org.codehaus.groovy.classgen.ClassCompletionVerifier;
import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.control.messages.SimpleMessage;
import java.io.IOException;
import org.codehaus.groovy.control.messages.Message;
import java.io.FileOutputStream;
import java.io.File;
import org.codehaus.groovy.classgen.GeneratorContext;
import java.util.Iterator;
import org.codehaus.groovy.classgen.VariableScopeVisitor;
import org.codehaus.groovy.ast.ClassNode;
import java.security.CodeSource;
import org.codehaus.groovy.classgen.Verifier;
import org.codehaus.groovy.tools.GroovyClass;
import org.codehaus.groovy.ast.CompileUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import groovy.lang.GroovyClassLoader;

public class CompilationUnit extends ProcessingUnit
{
    private GroovyClassLoader transformLoader;
    protected Map<String, SourceUnit> sources;
    protected Map summariesBySourceName;
    protected Map summariesByPublicClassName;
    protected Map classSourcesByPublicClassName;
    protected List<String> names;
    protected LinkedList<SourceUnit> queuedSources;
    protected CompileUnit ast;
    protected List<GroovyClass> generatedClasses;
    protected Verifier verifier;
    protected boolean debug;
    protected boolean configured;
    protected ClassgenCallback classgenCallback;
    protected ProgressCallback progressCallback;
    protected ResolveVisitor resolveVisitor;
    protected StaticImportVisitor staticImportVisitor;
    protected OptimizerVisitor optimizer;
    LinkedList[] phaseOperations;
    LinkedList[] newPhaseOperations;
    private final SourceUnitOperation resolve;
    private PrimaryClassNodeOperation staticImport;
    private SourceUnitOperation convert;
    private GroovyClassOperation output;
    private SourceUnitOperation compileCompleteCheck;
    private PrimaryClassNodeOperation classgen;
    private SourceUnitOperation mark;
    
    public CompilationUnit() {
        this(null, null, (GroovyClassLoader)null);
    }
    
    public CompilationUnit(final GroovyClassLoader loader) {
        this(null, null, loader);
    }
    
    public CompilationUnit(final CompilerConfiguration configuration) {
        this(configuration, null, (GroovyClassLoader)null);
    }
    
    public CompilationUnit(final CompilerConfiguration configuration, final CodeSource security, final GroovyClassLoader loader) {
        this(configuration, security, loader, null);
    }
    
    public CompilationUnit(final CompilerConfiguration configuration, final CodeSource security, final GroovyClassLoader loader, final GroovyClassLoader transformLoader) {
        super(configuration, loader, null);
        this.resolve = new SourceUnitOperation() {
            @Override
            public void call(final SourceUnit source) throws CompilationFailedException {
                final List<ClassNode> classes = source.ast.getClasses();
                for (final ClassNode node : classes) {
                    final VariableScopeVisitor scopeVisitor = new VariableScopeVisitor(source);
                    scopeVisitor.visitClass(node);
                    CompilationUnit.this.resolveVisitor.startResolving(node, source);
                }
            }
        };
        this.staticImport = new PrimaryClassNodeOperation() {
            @Override
            public void call(final SourceUnit source, final GeneratorContext context, final ClassNode classNode) throws CompilationFailedException {
                CompilationUnit.this.staticImportVisitor.visitClass(classNode, source);
            }
        };
        this.convert = new SourceUnitOperation() {
            @Override
            public void call(final SourceUnit source) throws CompilationFailedException {
                source.convert();
                CompilationUnit.this.ast.addModule(source.getAST());
                if (CompilationUnit.this.progressCallback != null) {
                    CompilationUnit.this.progressCallback.call(source, CompilationUnit.this.phase);
                }
            }
        };
        this.output = new GroovyClassOperation() {
            @Override
            public void call(final GroovyClass gclass) throws CompilationFailedException {
                boolean failures = false;
                final String name = gclass.getName().replace('.', File.separatorChar) + ".class";
                final File path = new File(CompilationUnit.this.configuration.getTargetDirectory(), name);
                final File directory = path.getParentFile();
                if (directory != null && !directory.exists()) {
                    directory.mkdirs();
                }
                final byte[] bytes = gclass.getBytes();
                FileOutputStream stream = null;
                try {
                    stream = new FileOutputStream(path);
                    stream.write(bytes, 0, bytes.length);
                }
                catch (IOException e) {
                    CompilationUnit.this.getErrorCollector().addError(Message.create(e.getMessage(), CompilationUnit.this));
                    failures = true;
                }
                finally {
                    if (stream != null) {
                        try {
                            stream.close();
                        }
                        catch (Exception ex) {}
                    }
                }
            }
        };
        this.compileCompleteCheck = new SourceUnitOperation() {
            @Override
            public void call(final SourceUnit source) throws CompilationFailedException {
                final List<ClassNode> classes = source.ast.getClasses();
                for (final ClassNode node : classes) {
                    final CompileUnit cu = node.getCompileUnit();
                    final Iterator iter = cu.iterateClassNodeToCompile();
                    while (iter.hasNext()) {
                        final String name = iter.next();
                        final SourceUnit su = CompilationUnit.this.ast.getScriptSourceLocation(name);
                        final List<ClassNode> classesInSourceUnit = su.ast.getClasses();
                        final StringBuffer message = new StringBuffer();
                        message.append("Compilation incomplete: expected to find the class ").append(name).append(" in ").append(su.getName());
                        if (classesInSourceUnit.isEmpty()) {
                            message.append(", but the file seems not to contain any classes");
                        }
                        else {
                            message.append(", but the file contains the classes: ");
                            boolean first = true;
                            for (final ClassNode cn : classesInSourceUnit) {
                                if (!first) {
                                    message.append(", ");
                                }
                                else {
                                    first = false;
                                }
                                message.append(cn.getName());
                            }
                        }
                        CompilationUnit.this.getErrorCollector().addErrorAndContinue(new SimpleMessage(message.toString(), CompilationUnit.this));
                        iter.remove();
                    }
                }
            }
        };
        this.classgen = new PrimaryClassNodeOperation() {
            @Override
            public boolean needSortedInput() {
                return true;
            }
            
            @Override
            public void call(final SourceUnit source, final GeneratorContext context, final ClassNode classNode) throws CompilationFailedException {
                CompilationUnit.this.optimizer.visitClass(classNode, source);
                if (!classNode.isSynthetic()) {
                    final GenericsVisitor genericsVisitor = new GenericsVisitor(source);
                    genericsVisitor.visitClass(classNode);
                }
                try {
                    CompilationUnit.this.verifier.visitClass(classNode);
                }
                catch (GroovyRuntimeException rpe) {
                    final ASTNode node = rpe.getNode();
                    CompilationUnit.this.getErrorCollector().addError(new SyntaxException(rpe.getMessage(), null, node.getLineNumber(), node.getColumnNumber()), source);
                }
                final LabelVerifier lv = new LabelVerifier(source);
                lv.visitClass(classNode);
                final ClassCompletionVerifier completionVerifier = new ClassCompletionVerifier(source);
                completionVerifier.visitClass(classNode);
                final ExtendedVerifier xverifier = new ExtendedVerifier(source);
                xverifier.visitClass(classNode);
                CompilationUnit.this.getErrorCollector().failIfErrors();
                final ClassVisitor visitor = CompilationUnit.this.createClassVisitor();
                String sourceName = (source == null) ? classNode.getModule().getDescription() : source.getName();
                if (sourceName != null) {
                    sourceName = sourceName.substring(Math.max(sourceName.lastIndexOf(92), sourceName.lastIndexOf(47)) + 1);
                }
                final ClassGenerator generator = new AsmClassGenerator(source, context, visitor, CompilationUnit.this.classLoader, sourceName);
                generator.visitClass(classNode);
                final byte[] bytes = ((ClassWriter)visitor).toByteArray();
                CompilationUnit.this.generatedClasses.add(new GroovyClass(classNode.getName(), bytes));
                if (CompilationUnit.this.classgenCallback != null) {
                    CompilationUnit.this.classgenCallback.call(visitor, classNode);
                }
                final LinkedList innerClasses = generator.getInnerClasses();
                while (!innerClasses.isEmpty()) {
                    CompilationUnit.this.classgen.call(source, context, innerClasses.removeFirst());
                }
            }
        };
        this.mark = new SourceUnitOperation() {
            @Override
            public void call(final SourceUnit source) throws CompilationFailedException {
                if (source.phase < CompilationUnit.this.phase) {
                    source.gotoPhase(CompilationUnit.this.phase);
                }
                if (source.phase == CompilationUnit.this.phase && CompilationUnit.this.phaseComplete && !source.phaseComplete) {
                    source.completePhase();
                }
            }
        };
        this.transformLoader = transformLoader;
        this.names = new ArrayList<String>();
        this.queuedSources = new LinkedList<SourceUnit>();
        this.sources = new HashMap<String, SourceUnit>();
        this.summariesBySourceName = new HashMap();
        this.summariesByPublicClassName = new HashMap();
        this.classSourcesByPublicClassName = new HashMap();
        this.ast = new CompileUnit(this.classLoader, security, this.configuration);
        this.generatedClasses = new ArrayList<GroovyClass>();
        this.verifier = new Verifier();
        this.resolveVisitor = new ResolveVisitor(this);
        this.staticImportVisitor = new StaticImportVisitor(this);
        this.optimizer = new OptimizerVisitor(this);
        this.phaseOperations = new LinkedList[10];
        this.newPhaseOperations = new LinkedList[10];
        for (int i = 0; i < this.phaseOperations.length; ++i) {
            this.phaseOperations[i] = new LinkedList();
            this.newPhaseOperations[i] = new LinkedList();
        }
        this.addPhaseOperation(new SourceUnitOperation() {
            @Override
            public void call(final SourceUnit source) throws CompilationFailedException {
                source.parse();
            }
        }, 2);
        this.addPhaseOperation(this.convert, 3);
        this.addPhaseOperation(new PrimaryClassNodeOperation() {
            @Override
            public void call(final SourceUnit source, final GeneratorContext context, final ClassNode classNode) throws CompilationFailedException {
                final EnumVisitor ev = new EnumVisitor(CompilationUnit.this, source);
                ev.visitClass(classNode);
            }
        }, 3);
        this.addPhaseOperation(this.resolve, 4);
        this.addPhaseOperation(this.staticImport, 4);
        this.addPhaseOperation(new PrimaryClassNodeOperation() {
            @Override
            public void call(final SourceUnit source, final GeneratorContext context, final ClassNode classNode) throws CompilationFailedException {
                final InnerClassVisitor iv = new InnerClassVisitor(CompilationUnit.this, source);
                iv.visitClass(classNode);
            }
        }, 4);
        this.addPhaseOperation(this.compileCompleteCheck, 5);
        this.addPhaseOperation(this.classgen, 7);
        this.addPhaseOperation(this.output);
        ASTTransformationVisitor.addPhaseOperations(this);
        this.classgenCallback = null;
    }
    
    public GroovyClassLoader getTransformLoader() {
        return (this.transformLoader == null) ? this.getClassLoader() : this.transformLoader;
    }
    
    public void addPhaseOperation(final SourceUnitOperation op, final int phase) {
        if (phase < 0 || phase > 9) {
            throw new IllegalArgumentException("phase " + phase + " is unknown");
        }
        this.phaseOperations[phase].add(op);
    }
    
    public void addPhaseOperation(final PrimaryClassNodeOperation op, final int phase) {
        if (phase < 0 || phase > 9) {
            throw new IllegalArgumentException("phase " + phase + " is unknown");
        }
        this.phaseOperations[phase].add(op);
    }
    
    public void addPhaseOperation(final GroovyClassOperation op) {
        this.phaseOperations[8].addFirst(op);
    }
    
    public void addNewPhaseOperation(final SourceUnitOperation op, final int phase) {
        if (phase < 0 || phase > 9) {
            throw new IllegalArgumentException("phase " + phase + " is unknown");
        }
        this.newPhaseOperations[phase].add(op);
    }
    
    @Override
    public void configure(final CompilerConfiguration configuration) {
        super.configure(configuration);
        this.debug = configuration.getDebug();
        if (!this.configured && this.classLoader instanceof GroovyClassLoader) {
            this.appendCompilerConfigurationClasspathToClassLoader(configuration, this.classLoader);
        }
        this.configured = true;
    }
    
    private void appendCompilerConfigurationClasspathToClassLoader(final CompilerConfiguration configuration, final GroovyClassLoader classLoader) {
    }
    
    public CompileUnit getAST() {
        return this.ast;
    }
    
    public Map getSummariesBySourceName() {
        return this.summariesBySourceName;
    }
    
    public Map getSummariesByPublicClassName() {
        return this.summariesByPublicClassName;
    }
    
    public Map getClassSourcesByPublicClassName() {
        return this.classSourcesByPublicClassName;
    }
    
    public boolean isPublicClass(final String className) {
        return this.summariesByPublicClassName.containsKey(className);
    }
    
    public List getClasses() {
        return this.generatedClasses;
    }
    
    public ClassNode getFirstClassNode() {
        return this.ast.getModules().get(0).getClasses().get(0);
    }
    
    public ClassNode getClassNode(final String name) {
        final ClassNode[] result = { null };
        final PrimaryClassNodeOperation handler = new PrimaryClassNodeOperation() {
            @Override
            public void call(final SourceUnit source, final GeneratorContext context, final ClassNode classNode) {
                if (classNode.getName().equals(name)) {
                    result[0] = classNode;
                }
            }
        };
        try {
            this.applyToPrimaryClassNodes(handler);
        }
        catch (CompilationFailedException e) {
            if (this.debug) {
                e.printStackTrace();
            }
        }
        return result[0];
    }
    
    public void addSources(final String[] paths) {
        for (final String path : paths) {
            this.addSource(new File(path));
        }
    }
    
    public void addSources(final File[] files) {
        for (final File file : files) {
            this.addSource(file);
        }
    }
    
    public SourceUnit addSource(final File file) {
        return this.addSource(new SourceUnit(file, this.configuration, this.classLoader, this.getErrorCollector()));
    }
    
    public SourceUnit addSource(final URL url) {
        return this.addSource(new SourceUnit(url, this.configuration, this.classLoader, this.getErrorCollector()));
    }
    
    public SourceUnit addSource(final String name, final InputStream stream) {
        final ReaderSource source = new InputStreamReaderSource(stream, this.configuration);
        return this.addSource(new SourceUnit(name, source, this.configuration, this.classLoader, this.getErrorCollector()));
    }
    
    public SourceUnit addSource(final String name, final String scriptText) {
        return this.addSource(new SourceUnit(name, scriptText, this.configuration, this.classLoader, this.getErrorCollector()));
    }
    
    public SourceUnit addSource(final SourceUnit source) {
        final String name = source.getName();
        source.setClassLoader(this.classLoader);
        for (final SourceUnit su : this.queuedSources) {
            if (name.equals(su.getName())) {
                return su;
            }
        }
        this.queuedSources.add(source);
        return source;
    }
    
    public Iterator<SourceUnit> iterator() {
        return new Iterator<SourceUnit>() {
            Iterator<String> nameIterator = CompilationUnit.this.names.iterator();
            
            public boolean hasNext() {
                return this.nameIterator.hasNext();
            }
            
            public SourceUnit next() {
                final String name = this.nameIterator.next();
                return CompilationUnit.this.sources.get(name);
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    public void addClassNode(final ClassNode node) {
        final ModuleNode module = new ModuleNode(this.ast);
        this.ast.addModule(module);
        module.addClass(node);
    }
    
    public void setClassgenCallback(final ClassgenCallback visitor) {
        this.classgenCallback = visitor;
    }
    
    public void setProgressCallback(final ProgressCallback callback) {
        this.progressCallback = callback;
    }
    
    public void compile() throws CompilationFailedException {
        this.compile(9);
    }
    
    public void compile(int throughPhase) throws CompilationFailedException {
        this.gotoPhase(1);
        throughPhase = Math.min(throughPhase, 9);
        while (throughPhase >= this.phase && this.phase <= 9) {
            if (this.phase == 4) {
                this.doPhaseOperation(this.resolve);
                if (this.dequeued()) {
                    continue;
                }
            }
            this.processPhaseOperations(this.phase);
            this.processNewPhaseOperations(this.phase);
            if (this.progressCallback != null) {
                this.progressCallback.call(this, this.phase);
            }
            this.completePhase();
            this.applyToSourceUnits(this.mark);
            if (this.dequeued()) {
                continue;
            }
            this.gotoPhase(this.phase + 1);
            if (this.phase != 7) {
                continue;
            }
            this.sortClasses();
        }
        this.errorCollector.failIfErrors();
    }
    
    private void processPhaseOperations(final int ph) {
        final LinkedList ops = this.phaseOperations[ph];
        for (final Object next : ops) {
            this.doPhaseOperation(next);
        }
    }
    
    private void processNewPhaseOperations(final int currPhase) {
        this.recordPhaseOpsInAllOtherPhases(currPhase);
        for (LinkedList currentPhaseNewOps = this.newPhaseOperations[currPhase]; !currentPhaseNewOps.isEmpty(); currentPhaseNewOps = this.newPhaseOperations[currPhase]) {
            final Object operation = currentPhaseNewOps.removeFirst();
            this.phaseOperations[currPhase].add(operation);
            this.doPhaseOperation(operation);
            this.recordPhaseOpsInAllOtherPhases(currPhase);
        }
    }
    
    private void doPhaseOperation(final Object operation) {
        if (operation instanceof PrimaryClassNodeOperation) {
            this.applyToPrimaryClassNodes((PrimaryClassNodeOperation)operation);
        }
        else if (operation instanceof SourceUnitOperation) {
            this.applyToSourceUnits((SourceUnitOperation)operation);
        }
        else {
            this.applyToGeneratedGroovyClasses((GroovyClassOperation)operation);
        }
    }
    
    private void recordPhaseOpsInAllOtherPhases(final int currPhase) {
        for (int ph = 1; ph <= 9; ++ph) {
            if (ph != currPhase && !this.newPhaseOperations[ph].isEmpty()) {
                this.phaseOperations[ph].addAll(this.newPhaseOperations[ph]);
                this.newPhaseOperations[ph].clear();
            }
        }
    }
    
    private void sortClasses() throws CompilationFailedException {
        for (final ModuleNode module : this.ast.getModules()) {
            module.sortClasses();
        }
    }
    
    protected boolean dequeued() throws CompilationFailedException {
        final boolean dequeue = !this.queuedSources.isEmpty();
        while (!this.queuedSources.isEmpty()) {
            final SourceUnit su = this.queuedSources.removeFirst();
            final String name = su.getName();
            this.names.add(name);
            this.sources.put(name, su);
        }
        if (dequeue) {
            this.gotoPhase(1);
        }
        return dequeue;
    }
    
    protected ClassVisitor createClassVisitor() {
        return new ClassWriter(1);
    }
    
    protected void mark() throws CompilationFailedException {
        this.applyToSourceUnits(this.mark);
    }
    
    public void applyToSourceUnits(final SourceUnitOperation body) throws CompilationFailedException {
        for (final String name : this.names) {
            final SourceUnit source = this.sources.get(name);
            if (source.phase >= this.phase) {
                if (source.phase != this.phase || source.phaseComplete) {
                    continue;
                }
            }
            try {
                body.call(source);
            }
            catch (CompilationFailedException e) {
                throw e;
            }
            catch (Exception e2) {
                final GroovyBugError gbe = new GroovyBugError(e2);
                this.changeBugText(gbe, source);
                throw gbe;
            }
            catch (GroovyBugError e3) {
                this.changeBugText(e3, source);
                throw e3;
            }
        }
        this.getErrorCollector().failIfErrors();
    }
    
    private int getSuperClassCount(ClassNode element) {
        int count = 0;
        while (element != null) {
            ++count;
            element = element.getSuperClass();
        }
        return count;
    }
    
    private int getSuperInterfaceCount(final ClassNode element) {
        int count = 1;
        final ClassNode[] arr$;
        final ClassNode[] interfaces = arr$ = element.getInterfaces();
        for (final ClassNode anInterface : arr$) {
            count = Math.max(count, this.getSuperInterfaceCount(anInterface) + 1);
        }
        return count;
    }
    
    private List getPrimaryClassNodes(final boolean sort) {
        final List<ClassNode> unsorted = new ArrayList<ClassNode>();
        for (final ModuleNode module : this.ast.getModules()) {
            for (final ClassNode classNode : module.getClasses()) {
                unsorted.add(classNode);
            }
        }
        if (!sort) {
            return unsorted;
        }
        final int[] indexClass = new int[unsorted.size()];
        final int[] indexInterface = new int[unsorted.size()];
        int i = 0;
        for (final ClassNode element : unsorted) {
            if (element.isInterface()) {
                indexInterface[i] = this.getSuperInterfaceCount(element);
                indexClass[i] = -1;
            }
            else {
                indexClass[i] = this.getSuperClassCount(element);
                indexInterface[i] = -1;
            }
            ++i;
        }
        final List<ClassNode> sorted = this.getSorted(indexInterface, unsorted);
        sorted.addAll(this.getSorted(indexClass, unsorted));
        return sorted;
    }
    
    private List<ClassNode> getSorted(final int[] index, final List<ClassNode> unsorted) {
        final List<ClassNode> sorted = new ArrayList<ClassNode>(unsorted.size());
        for (int i = 0; i < unsorted.size(); ++i) {
            int min = -1;
            for (int j = 0; j < unsorted.size(); ++j) {
                if (index[j] != -1) {
                    if (min == -1) {
                        min = j;
                    }
                    else if (index[j] < index[min]) {
                        min = j;
                    }
                }
            }
            if (min == -1) {
                break;
            }
            sorted.add(unsorted.get(min));
            index[min] = -1;
        }
        return sorted;
    }
    
    public void applyToPrimaryClassNodes(final PrimaryClassNodeOperation body) throws CompilationFailedException {
        final Iterator classNodes = this.getPrimaryClassNodes(body.needSortedInput()).iterator();
        while (classNodes.hasNext()) {
            SourceUnit context = null;
            try {
                final ClassNode classNode = classNodes.next();
                context = classNode.getModule().getContext();
                if (context != null && context.phase >= this.phase && (context.phase != this.phase || context.phaseComplete)) {
                    continue;
                }
                body.call(context, new GeneratorContext(this.ast), classNode);
            }
            catch (CompilationFailedException e3) {}
            catch (NullPointerException npe) {
                throw npe;
            }
            catch (GroovyBugError e) {
                this.changeBugText(e, context);
                throw e;
            }
            catch (Exception e2) {
                ErrorCollector nestedCollector = null;
                for (Throwable next = e2.getCause(); next != e2 && next != null; next = next.getCause()) {
                    if (next instanceof MultipleCompilationErrorsException) {
                        final MultipleCompilationErrorsException mcee = (MultipleCompilationErrorsException)next;
                        nestedCollector = mcee.collector;
                        break;
                    }
                }
                if (nestedCollector != null) {
                    this.getErrorCollector().addCollectorContents(nestedCollector);
                }
                else {
                    this.getErrorCollector().addError(new ExceptionMessage(e2, this.configuration.getDebug(), this));
                }
            }
        }
        this.getErrorCollector().failIfErrors();
    }
    
    public void applyToGeneratedGroovyClasses(final GroovyClassOperation body) throws CompilationFailedException {
        if (this.phase != 8 && (this.phase != 7 || !this.phaseComplete)) {
            throw new GroovyBugError("CompilationUnit not ready for output(). Current phase=" + this.getPhaseDescription());
        }
        for (final GroovyClass gclass : this.generatedClasses) {
            try {
                body.call(gclass);
            }
            catch (CompilationFailedException e3) {}
            catch (NullPointerException npe) {
                throw npe;
            }
            catch (GroovyBugError e) {
                this.changeBugText(e, null);
                throw e;
            }
            catch (Exception e2) {
                throw new GroovyBugError(e2);
            }
        }
        this.getErrorCollector().failIfErrors();
    }
    
    private void changeBugText(final GroovyBugError e, final SourceUnit context) {
        e.setBugText("exception in phase '" + this.getPhaseDescription() + "' in source unit '" + ((context != null) ? context.getName() : "?") + "' " + e.getBugText());
    }
    
    public abstract static class ClassgenCallback
    {
        public abstract void call(final ClassVisitor p0, final ClassNode p1) throws CompilationFailedException;
    }
    
    public abstract static class ProgressCallback
    {
        public abstract void call(final ProcessingUnit p0, final int p1) throws CompilationFailedException;
    }
    
    public abstract static class SourceUnitOperation
    {
        public abstract void call(final SourceUnit p0) throws CompilationFailedException;
    }
    
    public abstract static class PrimaryClassNodeOperation
    {
        public abstract void call(final SourceUnit p0, final GeneratorContext p1, final ClassNode p2) throws CompilationFailedException;
        
        public boolean needSortedInput() {
            return false;
        }
    }
    
    public abstract static class GroovyClassOperation
    {
        public abstract void call(final GroovyClass p0) throws CompilationFailedException;
    }
}
