// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform;

import java.util.HashSet;
import java.util.Enumeration;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.messages.WarningMessage;
import org.codehaus.groovy.syntax.CSTNode;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.classgen.GeneratorContext;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.AnnotatedNode;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import org.codehaus.groovy.control.messages.Message;
import org.codehaus.groovy.control.ProcessingUnit;
import org.codehaus.groovy.control.messages.SimpleMessage;
import java.util.HashMap;
import org.codehaus.groovy.ast.ClassNode;
import java.util.Set;
import org.codehaus.groovy.control.CompilationUnit;
import java.util.Map;
import org.codehaus.groovy.ast.ASTNode;
import java.util.List;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;

public final class ASTTransformationVisitor extends ClassCodeVisitorSupport
{
    private CompilePhase phase;
    private SourceUnit source;
    private List<ASTNode[]> targetNodes;
    private Map<ASTNode, List<ASTTransformation>> transforms;
    private Map<Class<? extends ASTTransformation>, ASTTransformation> transformInstances;
    private static CompilationUnit compUnit;
    private static Set<String> globalTransformNames;
    
    private ASTTransformationVisitor(final CompilePhase phase) {
        this.phase = phase;
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        return this.source;
    }
    
    @Override
    public void visitClass(final ClassNode classNode) {
        final Map<Class<? extends ASTTransformation>, Set<ASTNode>> baseTransforms = classNode.getTransforms(this.phase);
        if (!baseTransforms.isEmpty()) {
            this.transformInstances = new HashMap<Class<? extends ASTTransformation>, ASTTransformation>();
            for (final Class<? extends ASTTransformation> transformClass : baseTransforms.keySet()) {
                try {
                    this.transformInstances.put(transformClass, (ASTTransformation)transformClass.newInstance());
                }
                catch (InstantiationException e) {
                    this.source.getErrorCollector().addError(new SimpleMessage("Could not instantiate Transformation Processor " + transformClass, this.source));
                }
                catch (IllegalAccessException e2) {
                    this.source.getErrorCollector().addError(new SimpleMessage("Could not instantiate Transformation Processor " + transformClass, this.source));
                }
            }
            this.transforms = new HashMap<ASTNode, List<ASTTransformation>>();
            for (final Map.Entry<Class<? extends ASTTransformation>, Set<ASTNode>> entry : baseTransforms.entrySet()) {
                for (final ASTNode node : entry.getValue()) {
                    List<ASTTransformation> list = this.transforms.get(node);
                    if (list == null) {
                        list = new ArrayList<ASTTransformation>();
                        this.transforms.put(node, list);
                    }
                    list.add(this.transformInstances.get(entry.getKey()));
                }
            }
            this.targetNodes = new LinkedList<ASTNode[]>();
            super.visitClass(classNode);
            for (final ASTNode[] node2 : this.targetNodes) {
                for (final ASTTransformation snt : this.transforms.get(node2[0])) {
                    snt.visit(node2, this.source);
                }
            }
        }
    }
    
    @Override
    public void visitAnnotations(final AnnotatedNode node) {
        super.visitAnnotations(node);
        for (final AnnotationNode annotation : node.getAnnotations()) {
            if (this.transforms.containsKey(annotation)) {
                this.targetNodes.add(new ASTNode[] { annotation, node });
            }
        }
    }
    
    public static void addPhaseOperations(final CompilationUnit compilationUnit) {
        addGlobalTransforms(compilationUnit);
        compilationUnit.addPhaseOperation(new CompilationUnit.PrimaryClassNodeOperation() {
            @Override
            public void call(final SourceUnit source, final GeneratorContext context, final ClassNode classNode) throws CompilationFailedException {
                final ASTTransformationCollectorCodeVisitor collector = new ASTTransformationCollectorCodeVisitor(source, compilationUnit.getTransformLoader());
                collector.visitClass(classNode);
            }
        }, 4);
        for (final CompilePhase phase : CompilePhase.values()) {
            final ASTTransformationVisitor visitor = new ASTTransformationVisitor(phase);
            switch (phase) {
                case INITIALIZATION:
                case PARSING:
                case CONVERSION: {
                    break;
                }
                default: {
                    compilationUnit.addPhaseOperation(new CompilationUnit.PrimaryClassNodeOperation() {
                        @Override
                        public void call(final SourceUnit source, final GeneratorContext context, final ClassNode classNode) throws CompilationFailedException {
                            visitor.source = source;
                            visitor.visitClass(classNode);
                        }
                    }, phase.getPhaseNumber());
                    break;
                }
            }
        }
    }
    
    public static void addGlobalTransformsAfterGrab() {
        doAddGlobalTransforms(ASTTransformationVisitor.compUnit, false);
    }
    
    public static void addGlobalTransforms(final CompilationUnit compilationUnit) {
        doAddGlobalTransforms(ASTTransformationVisitor.compUnit = compilationUnit, true);
    }
    
    private static void doAddGlobalTransforms(final CompilationUnit compilationUnit, final boolean isFirstScan) {
        final GroovyClassLoader transformLoader = compilationUnit.getTransformLoader();
        final Map<String, URL> transformNames = new LinkedHashMap<String, URL>();
        try {
            final Enumeration<URL> globalServices = transformLoader.getResources("META-INF/services/org.codehaus.groovy.transform.ASTTransformation");
            while (globalServices.hasMoreElements()) {
                final URL service = globalServices.nextElement();
                final BufferedReader svcIn = new BufferedReader(new InputStreamReader(service.openStream()));
                String className;
                try {
                    className = svcIn.readLine();
                }
                catch (IOException ioe) {
                    compilationUnit.getErrorCollector().addError(new SimpleMessage("IOException reading the service definition at " + service.toExternalForm() + " because of exception " + ioe.toString(), null));
                    continue;
                }
                while (className != null) {
                    if (!className.startsWith("#") && className.length() > 0) {
                        if (transformNames.containsKey(className)) {
                            if (!service.equals(transformNames.get(className))) {
                                compilationUnit.getErrorCollector().addWarning(2, "The global transform for class " + className + " is defined in both " + transformNames.get(className).toExternalForm() + " and " + service.toExternalForm() + " - the former definition will be used and the latter ignored.", null, null);
                            }
                        }
                        else {
                            transformNames.put(className, service);
                        }
                    }
                    try {
                        className = svcIn.readLine();
                    }
                    catch (IOException ioe) {
                        compilationUnit.getErrorCollector().addError(new SimpleMessage("IOException reading the service definition at " + service.toExternalForm() + " because of exception " + ioe.toString(), null));
                    }
                }
            }
        }
        catch (IOException e) {
            compilationUnit.getErrorCollector().addError(new SimpleMessage("IO Exception attempting to load global transforms:" + e.getMessage(), null));
        }
        try {
            Class.forName("java.lang.annotation.Annotation");
        }
        catch (Exception e2) {
            final StringBuffer sb = new StringBuffer();
            sb.append("Global ASTTransformations are not enabled in retro builds of groovy.\n");
            sb.append("The following transformations will be ignored:");
            for (final Map.Entry<String, URL> entry : transformNames.entrySet()) {
                sb.append('\t');
                sb.append(entry.getKey());
                sb.append('\n');
            }
            compilationUnit.getErrorCollector().addWarning(new WarningMessage(2, sb.toString(), null, null));
            return;
        }
        if (isFirstScan) {
            for (final Map.Entry<String, URL> entry2 : transformNames.entrySet()) {
                ASTTransformationVisitor.globalTransformNames.add(entry2.getKey());
            }
            addPhaseOperationsForGlobalTransforms(compilationUnit, transformNames, isFirstScan);
        }
        else {
            final Iterator<Map.Entry<String, URL>> it = transformNames.entrySet().iterator();
            while (it.hasNext()) {
                final Map.Entry<String, URL> entry2 = it.next();
                if (!ASTTransformationVisitor.globalTransformNames.add(entry2.getKey())) {
                    it.remove();
                }
            }
            addPhaseOperationsForGlobalTransforms(compilationUnit, transformNames, isFirstScan);
        }
    }
    
    private static void addPhaseOperationsForGlobalTransforms(final CompilationUnit compilationUnit, final Map<String, URL> transformNames, final boolean isFirstScan) {
        final GroovyClassLoader transformLoader = compilationUnit.getTransformLoader();
        for (final Map.Entry<String, URL> entry : transformNames.entrySet()) {
            try {
                final Class gTransClass = transformLoader.loadClass(entry.getKey(), false, true, false);
                final GroovyASTTransformation transformAnnotation = gTransClass.getAnnotation(GroovyASTTransformation.class);
                if (transformAnnotation == null) {
                    compilationUnit.getErrorCollector().addWarning(new WarningMessage(2, "Transform Class " + entry.getKey() + " is specified as a global transform in " + entry.getValue().toExternalForm() + " but it is not annotated by " + GroovyASTTransformation.class.getName() + " the global tranform associated with it may fail and cause the compilation to fail.", null, null));
                }
                else if (ASTTransformation.class.isAssignableFrom(gTransClass)) {
                    final ASTTransformation instance = gTransClass.newInstance();
                    final CompilationUnit.SourceUnitOperation suOp = new CompilationUnit.SourceUnitOperation() {
                        @Override
                        public void call(final SourceUnit source) throws CompilationFailedException {
                            instance.visit(new ASTNode[] { source.getAST() }, source);
                        }
                    };
                    if (isFirstScan) {
                        compilationUnit.addPhaseOperation(suOp, transformAnnotation.phase().getPhaseNumber());
                    }
                    else {
                        compilationUnit.addNewPhaseOperation(suOp, transformAnnotation.phase().getPhaseNumber());
                    }
                }
                else {
                    compilationUnit.getErrorCollector().addError(new SimpleMessage("Transform Class " + entry.getKey() + " specified at " + entry.getValue().toExternalForm() + " is not an ASTTransformation.", null));
                }
            }
            catch (Exception e) {
                compilationUnit.getErrorCollector().addError(new SimpleMessage("Could not instantiate global transform class " + entry.getKey() + " specified at " + entry.getValue().toExternalForm() + "  because of exception " + e.toString(), null));
            }
        }
    }
    
    static {
        ASTTransformationVisitor.globalTransformNames = new HashSet<String>();
    }
}
