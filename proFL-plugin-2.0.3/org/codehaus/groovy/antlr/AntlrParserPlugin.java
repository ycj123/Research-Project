// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr;

import org.codehaus.groovy.ast.expr.ExpressionTransformer;
import org.codehaus.groovy.ast.expr.UnaryPlusExpression;
import org.codehaus.groovy.ast.expr.UnaryMinusExpression;
import org.codehaus.groovy.syntax.Numbers;
import java.util.Set;
import java.util.HashSet;
import org.codehaus.groovy.ast.expr.NamedArgumentListExpression;
import org.codehaus.groovy.ast.expr.ArrayExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.PostfixExpression;
import org.codehaus.groovy.ast.expr.PrefixExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.GStringExpression;
import org.codehaus.groovy.ast.expr.AttributeExpression;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.syntax.Types;
import org.codehaus.groovy.ast.expr.CastExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.ast.expr.MethodPointerExpression;
import org.codehaus.groovy.ast.expr.SpreadMapExpression;
import org.codehaus.groovy.ast.expr.SpreadExpression;
import org.codehaus.groovy.ast.expr.RangeExpression;
import org.codehaus.groovy.ast.expr.TernaryExpression;
import org.codehaus.groovy.ast.expr.ElvisOperatorExpression;
import org.codehaus.groovy.ast.expr.EmptyExpression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.expr.AnnotationConstantExpression;
import org.codehaus.groovy.ast.expr.BitwiseNegationExpression;
import org.codehaus.groovy.ast.expr.NotExpression;
import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.ast.stmt.TryCatchStatement;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.ast.stmt.ThrowStatement;
import org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import java.util.Iterator;
import org.codehaus.groovy.ast.stmt.CaseStatement;
import org.codehaus.groovy.ast.stmt.SwitchStatement;
import java.util.Collection;
import java.util.LinkedList;
import org.codehaus.groovy.syntax.Token;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.expr.ClosureListExpression;
import org.codehaus.groovy.ast.stmt.EmptyStatement;
import org.codehaus.groovy.ast.stmt.ForStatement;
import org.codehaus.groovy.ast.stmt.ContinueStatement;
import org.codehaus.groovy.ast.stmt.BreakStatement;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.stmt.AssertStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.ConstructorNode;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.EnumConstantClassNode;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.InnerClassNode;
import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.ast.MixinNode;
import org.codehaus.groovy.GroovyBugError;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.PackageNode;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.syntax.ParserException;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.ModuleNode;
import java.io.Writer;
import java.io.FileWriter;
import com.thoughtworks.xstream.XStream;
import org.codehaus.groovy.antlr.treewalker.Visitor;
import java.util.List;
import org.codehaus.groovy.antlr.treewalker.CompositeVisitor;
import org.codehaus.groovy.antlr.treewalker.NodeAsHTMLPrinter;
import org.codehaus.groovy.antlr.treewalker.VisitorAdapter;
import java.util.ArrayList;
import org.codehaus.groovy.antlr.treewalker.PreOrderTraversal;
import org.codehaus.groovy.antlr.treewalker.MindMapPrinter;
import java.io.FileNotFoundException;
import org.codehaus.groovy.antlr.treewalker.SourceCodeTraversal;
import org.codehaus.groovy.antlr.treewalker.SourcePrinter;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import groovyjarjarantlr.TokenStreamException;
import groovyjarjarantlr.RecognitionException;
import groovyjarjarantlr.TokenStreamRecognitionException;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.antlr.parser.GroovyRecognizer;
import groovyjarjarantlr.CharScanner;
import org.codehaus.groovy.antlr.parser.GroovyLexer;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.syntax.Reduction;
import java.io.Reader;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ClassNode;
import groovyjarjarantlr.collections.AST;
import org.codehaus.groovy.antlr.parser.GroovyTokenTypes;
import org.codehaus.groovy.control.ParserPlugin;
import org.codehaus.groovy.syntax.ASTHelper;

public class AntlrParserPlugin extends ASTHelper implements ParserPlugin, GroovyTokenTypes
{
    protected AST ast;
    private ClassNode classNode;
    private String[] tokenNames;
    private int innerClassCounter;
    private boolean enumConstantBeingDef;
    private boolean forStatementBeingDef;
    private boolean firstParamIsVarArg;
    private boolean firstParam;
    
    public AntlrParserPlugin() {
        this.innerClassCounter = 1;
        this.enumConstantBeingDef = false;
        this.forStatementBeingDef = false;
        this.firstParamIsVarArg = false;
        this.firstParam = false;
    }
    
    public Reduction parseCST(final SourceUnit sourceUnit, final Reader reader) throws CompilationFailedException {
        final SourceBuffer sourceBuffer = new SourceBuffer();
        this.transformCSTIntoAST(sourceUnit, reader, sourceBuffer);
        this.processAST();
        return this.outputAST(sourceUnit, sourceBuffer);
    }
    
    protected void transformCSTIntoAST(final SourceUnit sourceUnit, final Reader reader, final SourceBuffer sourceBuffer) throws CompilationFailedException {
        this.ast = null;
        this.setController(sourceUnit);
        final UnicodeEscapingReader unicodeReader = new UnicodeEscapingReader(reader, sourceBuffer);
        final GroovyLexer lexer = new GroovyLexer(unicodeReader);
        unicodeReader.setLexer(lexer);
        final GroovyRecognizer parser = GroovyRecognizer.make(lexer);
        parser.setSourceBuffer(sourceBuffer);
        this.tokenNames = parser.getTokenNames();
        parser.setFilename(sourceUnit.getName());
        try {
            parser.compilationUnit();
        }
        catch (TokenStreamRecognitionException tsre) {
            final RecognitionException e = tsre.recog;
            final SyntaxException se = new SyntaxException(e.getMessage(), e, e.getLine(), e.getColumn());
            se.setFatal(true);
            sourceUnit.addError(se);
        }
        catch (RecognitionException e2) {
            final SyntaxException se2 = new SyntaxException(e2.getMessage(), e2, e2.getLine(), e2.getColumn());
            se2.setFatal(true);
            sourceUnit.addError(se2);
        }
        catch (TokenStreamException e3) {
            sourceUnit.addException(e3);
        }
        this.ast = parser.getAST();
    }
    
    protected void processAST() {
        final AntlrASTProcessor snippets = new AntlrASTProcessSnippets();
        this.ast = snippets.process(this.ast);
    }
    
    public Reduction outputAST(final SourceUnit sourceUnit, final SourceBuffer sourceBuffer) {
        AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
            public Object run() {
                AntlrParserPlugin.this.outputASTInVariousFormsIfNeeded(sourceUnit, sourceBuffer);
                return null;
            }
        });
        return null;
    }
    
    private void outputASTInVariousFormsIfNeeded(final SourceUnit sourceUnit, final SourceBuffer sourceBuffer) {
        if ("xml".equals(System.getProperty("groovyjarjarantlr.ast"))) {
            this.saveAsXML(sourceUnit.getName(), this.ast);
        }
        if ("groovy".equals(System.getProperty("groovyjarjarantlr.ast"))) {
            try {
                final PrintStream out = new PrintStream(new FileOutputStream(sourceUnit.getName() + ".pretty.groovy"));
                final Visitor visitor = new SourcePrinter(out, this.tokenNames);
                final AntlrASTProcessor treewalker = new SourceCodeTraversal(visitor);
                treewalker.process(this.ast);
            }
            catch (FileNotFoundException e) {
                System.out.println("Cannot create " + sourceUnit.getName() + ".pretty.groovy");
            }
        }
        if ("mindmap".equals(System.getProperty("groovyjarjarantlr.ast"))) {
            try {
                final PrintStream out = new PrintStream(new FileOutputStream(sourceUnit.getName() + ".mm"));
                final Visitor visitor = new MindMapPrinter(out, this.tokenNames);
                final AntlrASTProcessor treewalker = new PreOrderTraversal(visitor);
                treewalker.process(this.ast);
            }
            catch (FileNotFoundException e) {
                System.out.println("Cannot create " + sourceUnit.getName() + ".mm");
            }
        }
        if ("extendedMindmap".equals(System.getProperty("groovyjarjarantlr.ast"))) {
            try {
                final PrintStream out = new PrintStream(new FileOutputStream(sourceUnit.getName() + ".mm"));
                final Visitor visitor = new MindMapPrinter(out, this.tokenNames, sourceBuffer);
                final AntlrASTProcessor treewalker = new PreOrderTraversal(visitor);
                treewalker.process(this.ast);
            }
            catch (FileNotFoundException e) {
                System.out.println("Cannot create " + sourceUnit.getName() + ".mm");
            }
        }
        if ("html".equals(System.getProperty("groovyjarjarantlr.ast"))) {
            try {
                final PrintStream out = new PrintStream(new FileOutputStream(sourceUnit.getName() + ".html"));
                final List<VisitorAdapter> v = new ArrayList<VisitorAdapter>();
                v.add(new NodeAsHTMLPrinter(out, this.tokenNames));
                v.add(new SourcePrinter(out, this.tokenNames));
                final Visitor visitors = new CompositeVisitor(v);
                final AntlrASTProcessor treewalker2 = new SourceCodeTraversal(visitors);
                treewalker2.process(this.ast);
            }
            catch (FileNotFoundException e) {
                System.out.println("Cannot create " + sourceUnit.getName() + ".html");
            }
        }
    }
    
    private void saveAsXML(final String name, final AST ast) {
        final XStream xstream = new XStream();
        try {
            xstream.toXML((Object)ast, (Writer)new FileWriter(name + ".antlr.xml"));
            System.out.println("Written AST to " + name + ".antlr.xml");
        }
        catch (Exception e) {
            System.out.println("Couldn't write to " + name + ".antlr.xml");
            e.printStackTrace();
        }
    }
    
    public ModuleNode buildAST(final SourceUnit sourceUnit, final ClassLoader classLoader, final Reduction cst) throws ParserException {
        this.setClassLoader(classLoader);
        this.makeModule();
        try {
            this.convertGroovy(this.ast);
            if (this.output.getStatementBlock().isEmpty() && this.output.getMethods().isEmpty() && this.output.getClasses().isEmpty()) {
                this.output.addStatement(ReturnStatement.RETURN_NULL_OR_VOID);
            }
        }
        catch (ASTRuntimeException e) {
            throw new ASTParserException(e.getMessage() + ". File: " + sourceUnit.getName(), e);
        }
        return this.output;
    }
    
    protected void convertGroovy(AST node) {
        while (node != null) {
            final int type = node.getType();
            switch (type) {
                case 15: {
                    this.packageDef(node);
                    break;
                }
                case 28:
                case 59: {
                    this.importDef(node);
                    break;
                }
                case 13: {
                    this.classDef(node);
                    break;
                }
                case 14: {
                    this.interfaceDef(node);
                    break;
                }
                case 8: {
                    this.methodDef(node);
                    break;
                }
                case 60: {
                    this.enumDef(node);
                    break;
                }
                case 63: {
                    this.annotationDef(node);
                    break;
                }
                default: {
                    final Statement statement = this.statement(node);
                    this.output.addStatement(statement);
                    break;
                }
            }
            node = node.getNextSibling();
        }
    }
    
    protected void packageDef(final AST packageDef) {
        final List<AnnotationNode> annotations = new ArrayList<AnnotationNode>();
        AST node = packageDef.getFirstChild();
        if (isType(64, node)) {
            this.processAnnotations(annotations, node);
            node = node.getNextSibling();
        }
        final String name = qualifiedName(node);
        final PackageNode packageNode = this.setPackage(name, annotations);
        this.configureAST(packageNode, packageDef);
    }
    
    protected void importDef(final AST importNode) {
        final boolean isStatic = importNode.getType() == 59;
        final List<AnnotationNode> annotations = new ArrayList<AnnotationNode>();
        AST node = importNode.getFirstChild();
        if (isType(64, node)) {
            this.processAnnotations(annotations, node);
            node = node.getNextSibling();
        }
        String alias = null;
        if (isType(110, node)) {
            node = node.getFirstChild();
            final AST aliasNode = node.getNextSibling();
            alias = this.identifier(aliasNode);
        }
        if (node.getNumberOfChildren() == 0) {
            final String name = this.identifier(node);
            final ClassNode type = ClassHelper.make(name);
            this.configureAST(type, importNode);
            this.addImport(type, name, alias, annotations);
            return;
        }
        final AST packageNode = node.getFirstChild();
        final String packageName = qualifiedName(packageNode);
        final AST nameNode = packageNode.getNextSibling();
        if (isType(109, nameNode)) {
            if (isStatic) {
                final ClassNode type2 = ClassHelper.make(packageName);
                this.configureAST(type2, importNode);
                this.addStaticStarImport(type2, packageName, annotations);
            }
            else {
                this.addStarImport(packageName, annotations);
            }
            if (alias != null) {
                throw new GroovyBugError("imports like 'import foo.* as Bar' are not supported and should be caught by the grammar");
            }
        }
        else {
            final String name2 = this.identifier(nameNode);
            if (isStatic) {
                final ClassNode type3 = ClassHelper.make(packageName);
                this.configureAST(type3, importNode);
                this.addStaticImport(type3, name2, alias, annotations);
            }
            else {
                final ClassNode type3 = ClassHelper.make(packageName + "." + name2);
                this.configureAST(type3, importNode);
                this.addImport(type3, name2, alias, annotations);
            }
        }
    }
    
    private void processAnnotations(final List<AnnotationNode> annotations, final AST node) {
        for (AST child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (isType(65, child)) {
                annotations.add(this.annotation(child));
            }
        }
    }
    
    protected void annotationDef(final AST classDef) {
        final List<AnnotationNode> annotations = new ArrayList<AnnotationNode>();
        AST node = classDef.getFirstChild();
        int modifiers = 1;
        if (isType(5, node)) {
            modifiers = this.modifiers(node, annotations, modifiers);
            this.checkNoInvalidModifier(classDef, "Annotation Definition", modifiers, 32, "synchronized");
            node = node.getNextSibling();
        }
        modifiers |= 0x2600;
        final String name = this.identifier(node);
        node = node.getNextSibling();
        final ClassNode superClass = ClassHelper.OBJECT_TYPE;
        GenericsType[] genericsType = null;
        if (isType(71, node)) {
            genericsType = this.makeGenericsType(node);
            node = node.getNextSibling();
        }
        ClassNode[] interfaces = ClassNode.EMPTY_ARRAY;
        if (isType(17, node)) {
            interfaces = this.interfaces(node);
            node = node.getNextSibling();
        }
        (this.classNode = new ClassNode(ASTHelper.dot(this.getPackageName(), name), modifiers, superClass, interfaces, null)).addAnnotations(annotations);
        this.classNode.setGenericsTypes(genericsType);
        this.classNode.addInterface(ClassHelper.Annotation_TYPE);
        this.configureAST(this.classNode, classDef);
        this.assertNodeType(6, node);
        this.objectBlock(node);
        this.output.addClass(this.classNode);
        this.classNode = null;
    }
    
    protected void interfaceDef(final AST classDef) {
        final int oldInnerClassCounter = this.innerClassCounter;
        this.innerInterfaceDef(classDef);
        this.classNode = null;
        this.innerClassCounter = oldInnerClassCounter;
    }
    
    protected void innerInterfaceDef(final AST classDef) {
        final List<AnnotationNode> annotations = new ArrayList<AnnotationNode>();
        AST node = classDef.getFirstChild();
        int modifiers = 1;
        if (isType(5, node)) {
            modifiers = this.modifiers(node, annotations, modifiers);
            this.checkNoInvalidModifier(classDef, "Interface", modifiers, 32, "synchronized");
            node = node.getNextSibling();
        }
        modifiers |= 0x600;
        String name = this.identifier(node);
        node = node.getNextSibling();
        final ClassNode superClass = ClassHelper.OBJECT_TYPE;
        GenericsType[] genericsType = null;
        if (isType(71, node)) {
            genericsType = this.makeGenericsType(node);
            node = node.getNextSibling();
        }
        ClassNode[] interfaces = ClassNode.EMPTY_ARRAY;
        if (isType(17, node)) {
            interfaces = this.interfaces(node);
            node = node.getNextSibling();
        }
        final ClassNode outerClass = this.classNode;
        if (this.classNode != null) {
            name = this.classNode.getNameWithoutPackage() + "$" + name;
            final String fullName = ASTHelper.dot(this.classNode.getPackageName(), name);
            this.classNode = new InnerClassNode(this.classNode, fullName, modifiers, superClass, interfaces, null);
        }
        else {
            this.classNode = new ClassNode(ASTHelper.dot(this.getPackageName(), name), modifiers, superClass, interfaces, null);
        }
        this.classNode.addAnnotations(annotations);
        this.classNode.setGenericsTypes(genericsType);
        this.configureAST(this.classNode, classDef);
        final int oldClassCount = this.innerClassCounter;
        this.assertNodeType(6, node);
        this.objectBlock(node);
        this.output.addClass(this.classNode);
        this.classNode = outerClass;
        this.innerClassCounter = oldClassCount;
    }
    
    protected void classDef(final AST classDef) {
        final int oldInnerClassCounter = this.innerClassCounter;
        this.innerClassDef(classDef);
        this.classNode = null;
        this.innerClassCounter = oldInnerClassCounter;
    }
    
    private ClassNode getClassOrScript(final ClassNode node) {
        if (node != null) {
            return node;
        }
        return this.output.getScriptClassDummy();
    }
    
    protected Expression anonymousInnerClassDef(final AST node) {
        final ClassNode oldNode = this.classNode;
        final ClassNode outerClass = this.getClassOrScript(oldNode);
        final String fullName = outerClass.getName() + '$' + this.innerClassCounter;
        ++this.innerClassCounter;
        if (this.enumConstantBeingDef) {
            this.classNode = new EnumConstantClassNode(outerClass, fullName, 1, ClassHelper.OBJECT_TYPE);
        }
        else {
            this.classNode = new InnerClassNode(outerClass, fullName, 1, ClassHelper.OBJECT_TYPE);
        }
        ((InnerClassNode)this.classNode).setAnonymous(true);
        this.assertNodeType(6, node);
        this.objectBlock(node);
        this.output.addClass(this.classNode);
        final AnonymousInnerClassCarrier ret = new AnonymousInnerClassCarrier();
        ret.innerClass = this.classNode;
        this.classNode = oldNode;
        return ret;
    }
    
    protected void innerClassDef(final AST classDef) {
        final List<AnnotationNode> annotations = new ArrayList<AnnotationNode>();
        AST node = classDef.getFirstChild();
        int modifiers = 1;
        if (isType(5, node)) {
            modifiers = this.modifiers(node, annotations, modifiers);
            this.checkNoInvalidModifier(classDef, "Class", modifiers, 32, "synchronized");
            node = node.getNextSibling();
        }
        String name = this.identifier(node);
        node = node.getNextSibling();
        GenericsType[] genericsType = null;
        if (isType(71, node)) {
            genericsType = this.makeGenericsType(node);
            node = node.getNextSibling();
        }
        ClassNode superClass = null;
        if (isType(17, node)) {
            superClass = this.makeTypeWithArguments(node);
            node = node.getNextSibling();
        }
        ClassNode[] interfaces = ClassNode.EMPTY_ARRAY;
        if (isType(18, node)) {
            interfaces = this.interfaces(node);
            node = node.getNextSibling();
        }
        final MixinNode[] mixins = new MixinNode[0];
        final ClassNode outerClass = this.classNode;
        if (this.classNode != null) {
            name = this.classNode.getNameWithoutPackage() + "$" + name;
            final String fullName = ASTHelper.dot(this.classNode.getPackageName(), name);
            this.classNode = new InnerClassNode(this.classNode, fullName, modifiers, superClass, interfaces, mixins);
        }
        else {
            this.classNode = new ClassNode(ASTHelper.dot(this.getPackageName(), name), modifiers, superClass, interfaces, mixins);
        }
        this.classNode.addAnnotations(annotations);
        this.classNode.setGenericsTypes(genericsType);
        this.configureAST(this.classNode, classDef);
        this.output.addClass(this.classNode);
        final int oldClassCount = this.innerClassCounter;
        this.assertNodeType(6, node);
        this.objectBlock(node);
        this.classNode = outerClass;
        this.innerClassCounter = oldClassCount;
    }
    
    protected void objectBlock(final AST objectBlock) {
        for (AST node = objectBlock.getFirstChild(); node != null; node = node.getNextSibling()) {
            final int type = node.getType();
            switch (type) {
                case 6: {
                    this.objectBlock(node);
                    break;
                }
                case 8:
                case 67: {
                    this.methodDef(node);
                    break;
                }
                case 45: {
                    this.constructorDef(node);
                    break;
                }
                case 9: {
                    this.fieldDef(node);
                    break;
                }
                case 11: {
                    this.staticInit(node);
                    break;
                }
                case 10: {
                    this.objectInit(node);
                    break;
                }
                case 60: {
                    this.enumDef(node);
                    break;
                }
                case 61: {
                    this.enumConstantDef(node);
                    break;
                }
                case 13: {
                    this.innerClassDef(node);
                    break;
                }
                case 14: {
                    this.innerInterfaceDef(node);
                    break;
                }
                default: {
                    this.unknownAST(node);
                    break;
                }
            }
        }
    }
    
    protected void enumDef(final AST enumNode) {
        this.assertNodeType(60, enumNode);
        final List<AnnotationNode> annotations = new ArrayList<AnnotationNode>();
        AST node = enumNode.getFirstChild();
        int modifiers = 1;
        if (isType(5, node)) {
            modifiers = this.modifiers(node, annotations, modifiers);
            node = node.getNextSibling();
        }
        final String name = this.identifier(node);
        node = node.getNextSibling();
        final ClassNode[] interfaces = this.interfaces(node);
        node = node.getNextSibling();
        final String enumName = (this.classNode != null) ? name : ASTHelper.dot(this.getPackageName(), name);
        final ClassNode enumClass = EnumHelper.makeEnumNode(enumName, modifiers, interfaces, this.classNode);
        final ClassNode oldNode = this.classNode;
        this.classNode = enumClass;
        this.assertNodeType(6, node);
        this.objectBlock(node);
        this.classNode = oldNode;
        this.output.addClass(enumClass);
    }
    
    protected void enumConstantDef(final AST node) {
        this.enumConstantBeingDef = true;
        this.assertNodeType(61, node);
        AST element = node.getFirstChild();
        if (isType(64, element)) {
            element = element.getNextSibling();
        }
        final String identifier = this.identifier(element);
        Expression init = null;
        element = element.getNextSibling();
        if (element != null) {
            init = this.expression(element);
            final ClassNode innerClass = this.getAnonymousInnerClassNode(init);
            if (innerClass != null) {
                innerClass.setSuperClass(this.classNode);
                innerClass.setModifiers(this.classNode.getModifiers() | 0x10);
                init = new ClassExpression(innerClass);
                this.classNode.setModifiers(this.classNode.getModifiers() & 0xFFFFFFEF);
            }
            else if (isType(32, element) && init instanceof ListExpression && !((ListExpression)init).isWrapped()) {
                final ListExpression le = new ListExpression();
                le.addExpression(init);
                init = le;
            }
        }
        EnumHelper.addEnumConstant(this.classNode, identifier, init);
        this.enumConstantBeingDef = false;
    }
    
    protected void throwsList(final AST node, final List list) {
        String name;
        if (isType(87, node)) {
            name = qualifiedName(node);
        }
        else {
            name = this.identifier(node);
        }
        final ClassNode exception = ClassHelper.make(name);
        this.configureAST(exception, node);
        list.add(exception);
        final AST next = node.getNextSibling();
        if (next != null) {
            this.throwsList(next, list);
        }
    }
    
    protected void methodDef(final AST methodDef) {
        final List<AnnotationNode> annotations = new ArrayList<AnnotationNode>();
        AST node = methodDef.getFirstChild();
        GenericsType[] generics = null;
        if (isType(71, node)) {
            generics = this.makeGenericsType(node);
            node = node.getNextSibling();
        }
        int modifiers = 1;
        if (isType(5, node)) {
            modifiers = this.modifiers(node, annotations, modifiers);
            this.checkNoInvalidModifier(methodDef, "Method", modifiers, 64, "volatile");
            node = node.getNextSibling();
        }
        if (this.isAnInterface()) {
            modifiers |= 0x400;
        }
        ClassNode returnType = null;
        if (isType(12, node)) {
            returnType = this.makeTypeWithArguments(node);
            node = node.getNextSibling();
        }
        final String name = this.identifier(node);
        if (this.classNode == null || this.classNode.isAnnotationDefinition() || !this.classNode.getNameWithoutPackage().equals(name)) {
            node = node.getNextSibling();
            Parameter[] parameters = Parameter.EMPTY_ARRAY;
            ClassNode[] exceptions = ClassNode.EMPTY_ARRAY;
            if (this.classNode == null || !this.classNode.isAnnotationDefinition()) {
                this.assertNodeType(19, node);
                parameters = this.parameters(node);
                if (parameters == null) {
                    parameters = Parameter.EMPTY_ARRAY;
                }
                node = node.getNextSibling();
                if (isType(126, node)) {
                    final AST throwsNode = node.getFirstChild();
                    final List exceptionList = new ArrayList();
                    this.throwsList(throwsNode, exceptionList);
                    exceptions = exceptionList.toArray(exceptions);
                    node = node.getNextSibling();
                }
            }
            boolean hasAnnotationDefault = false;
            Statement code = null;
            if ((modifiers & 0x400) == 0x0) {
                if (node == null) {
                    throw new ASTRuntimeException(methodDef, "You defined a method without body. Try adding a body, or declare it abstract.");
                }
                this.assertNodeType(7, node);
                code = this.statementList(node);
            }
            else if (node != null && this.classNode.isAnnotationDefinition()) {
                code = this.statement(node);
                hasAnnotationDefault = true;
            }
            else if ((modifiers & 0x400) > 0 && node != null) {
                throw new ASTRuntimeException(methodDef, "Abstract methods do not define a body.");
            }
            final MethodNode methodNode = new MethodNode(name, modifiers, returnType, parameters, exceptions, code);
            methodNode.addAnnotations(annotations);
            methodNode.setGenericsTypes(generics);
            methodNode.setAnnotationDefault(hasAnnotationDefault);
            this.configureAST(methodNode, methodDef);
            if (this.classNode != null) {
                this.classNode.addMethod(methodNode);
            }
            else {
                this.output.addMethod(methodNode);
            }
            return;
        }
        if (this.isAnInterface()) {
            throw new ASTRuntimeException(methodDef, "Constructor not permitted within an interface.");
        }
        throw new ASTRuntimeException(methodDef, "Invalid constructor format. Remove '" + returnType.getName() + "' as the return type if you want a constructor, or use a different name if you want a method.");
    }
    
    private void checkNoInvalidModifier(final AST node, final String nodeType, final int modifiers, final int modifier, final String modifierText) {
        if ((modifiers & modifier) != 0x0) {
            throw new ASTRuntimeException(node, nodeType + " has an incorrect modifier '" + modifierText + "'.");
        }
    }
    
    private boolean isAnInterface() {
        return this.classNode != null && (this.classNode.getModifiers() & 0x200) > 0;
    }
    
    protected void staticInit(final AST staticInit) {
        final BlockStatement code = (BlockStatement)this.statementList(staticInit);
        this.classNode.addStaticInitializerStatements(code.getStatements(), false);
    }
    
    protected void objectInit(final AST init) {
        final BlockStatement code = (BlockStatement)this.statementList(init);
        this.classNode.addObjectInitializerStatements(code);
    }
    
    protected void constructorDef(final AST constructorDef) {
        final List<AnnotationNode> annotations = new ArrayList<AnnotationNode>();
        AST node = constructorDef.getFirstChild();
        int modifiers = 1;
        if (isType(5, node)) {
            modifiers = this.modifiers(node, annotations, modifiers);
            node = node.getNextSibling();
        }
        this.assertNodeType(19, node);
        Parameter[] parameters = this.parameters(node);
        if (parameters == null) {
            parameters = Parameter.EMPTY_ARRAY;
        }
        node = node.getNextSibling();
        ClassNode[] exceptions = ClassNode.EMPTY_ARRAY;
        if (isType(126, node)) {
            final AST throwsNode = node.getFirstChild();
            final List exceptionList = new ArrayList();
            this.throwsList(throwsNode, exceptionList);
            exceptions = exceptionList.toArray(exceptions);
            node = node.getNextSibling();
        }
        this.assertNodeType(7, node);
        final Statement code = this.statementList(node);
        final ConstructorNode constructorNode = this.classNode.addConstructor(modifiers, parameters, exceptions, code);
        constructorNode.addAnnotations(annotations);
        this.configureAST(constructorNode, constructorDef);
    }
    
    protected void fieldDef(final AST fieldDef) {
        final List<AnnotationNode> annotations = new ArrayList<AnnotationNode>();
        AST node = fieldDef.getFirstChild();
        int modifiers = 0;
        if (isType(5, node)) {
            modifiers = this.modifiers(node, annotations, modifiers);
            node = node.getNextSibling();
        }
        if (this.classNode.isInterface()) {
            modifiers |= 0x18;
            if ((modifiers & 0x6) == 0x0) {
                modifiers |= 0x1;
            }
        }
        ClassNode type = null;
        if (isType(12, node)) {
            type = this.makeTypeWithArguments(node);
            node = node.getNextSibling();
        }
        final String name = this.identifier(node);
        node = node.getNextSibling();
        Expression initialValue = null;
        if (node != null) {
            this.assertNodeType(120, node);
            initialValue = this.expression(node.getFirstChild());
        }
        if (this.classNode.isInterface() && initialValue == null && type != null) {
            if (type == ClassHelper.int_TYPE) {
                initialValue = new ConstantExpression(0);
            }
            else if (type == ClassHelper.long_TYPE) {
                initialValue = new ConstantExpression(0L);
            }
            else if (type == ClassHelper.double_TYPE) {
                initialValue = new ConstantExpression(0.0);
            }
            else if (type == ClassHelper.float_TYPE) {
                initialValue = new ConstantExpression(0.0f);
            }
            else if (type == ClassHelper.boolean_TYPE) {
                initialValue = ConstantExpression.FALSE;
            }
            else if (type == ClassHelper.short_TYPE) {
                initialValue = new ConstantExpression(0);
            }
            else if (type == ClassHelper.byte_TYPE) {
                initialValue = new ConstantExpression(0);
            }
            else if (type == ClassHelper.char_TYPE) {
                initialValue = new ConstantExpression('\0');
            }
        }
        FieldNode fieldNode = new FieldNode(name, modifiers, type, this.classNode, initialValue);
        fieldNode.addAnnotations(annotations);
        this.configureAST(fieldNode, fieldDef);
        if (!this.hasVisibility(modifiers)) {
            int fieldModifiers = 0;
            final int flags = 216;
            if (!this.hasVisibility(modifiers)) {
                modifiers |= 0x1;
                fieldModifiers |= 0x2;
            }
            fieldModifiers |= (modifiers & flags);
            fieldNode.setModifiers(fieldModifiers);
            fieldNode.setSynthetic(true);
            final FieldNode storedNode = this.classNode.getDeclaredField(fieldNode.getName());
            if (storedNode != null && !this.classNode.hasProperty(name)) {
                fieldNode = storedNode;
                this.classNode.getFields().remove(storedNode);
            }
            final PropertyNode propertyNode = new PropertyNode(fieldNode, modifiers, null, null);
            this.configureAST(propertyNode, fieldDef);
            this.classNode.addProperty(propertyNode);
        }
        else {
            fieldNode.setModifiers(modifiers);
            final PropertyNode pn = this.classNode.getProperty(name);
            if (pn != null && pn.getField().isSynthetic()) {
                this.classNode.getFields().remove(pn.getField());
                pn.setField(fieldNode);
            }
            this.classNode.addField(fieldNode);
        }
    }
    
    protected ClassNode[] interfaces(final AST node) {
        final List<ClassNode> interfaceList = new ArrayList<ClassNode>();
        for (AST implementNode = node.getFirstChild(); implementNode != null; implementNode = implementNode.getNextSibling()) {
            interfaceList.add(this.makeTypeWithArguments(implementNode));
        }
        ClassNode[] interfaces = ClassNode.EMPTY_ARRAY;
        if (!interfaceList.isEmpty()) {
            interfaces = new ClassNode[interfaceList.size()];
            interfaceList.toArray(interfaces);
        }
        return interfaces;
    }
    
    protected Parameter[] parameters(final AST parametersNode) {
        AST node = parametersNode.getFirstChild();
        this.firstParam = false;
        this.firstParamIsVarArg = false;
        if (node != null) {
            final List<Parameter> parameters = new ArrayList<Parameter>();
            AST firstParameterNode = null;
            do {
                this.firstParam = (firstParameterNode == null);
                if (firstParameterNode == null) {
                    firstParameterNode = node;
                }
                parameters.add(this.parameter(node));
                node = node.getNextSibling();
            } while (node != null);
            this.verifyParameters(parameters, firstParameterNode);
            final Parameter[] answer = new Parameter[parameters.size()];
            parameters.toArray(answer);
            return answer;
        }
        if (isType(50, parametersNode)) {
            return Parameter.EMPTY_ARRAY;
        }
        return null;
    }
    
    private void verifyParameters(final List<Parameter> parameters, final AST firstParameterNode) {
        if (parameters.size() <= 1) {
            return;
        }
        final Parameter first = parameters.get(0);
        if (this.firstParamIsVarArg) {
            throw new ASTRuntimeException(firstParameterNode, "The var-arg parameter " + first.getName() + " must be the last parameter.");
        }
    }
    
    protected Parameter parameter(final AST paramNode) {
        final List<AnnotationNode> annotations = new ArrayList<AnnotationNode>();
        final boolean variableParameterDef = isType(46, paramNode);
        AST node = paramNode.getFirstChild();
        int modifiers = 0;
        if (isType(5, node)) {
            modifiers = this.modifiers(node, annotations, modifiers);
            node = node.getNextSibling();
        }
        ClassNode type = ClassHelper.DYNAMIC_TYPE;
        if (isType(12, node)) {
            type = this.makeTypeWithArguments(node);
            if (variableParameterDef) {
                type = type.makeArray();
            }
            node = node.getNextSibling();
        }
        final String name = this.identifier(node);
        node = node.getNextSibling();
        final VariableExpression leftExpression = new VariableExpression(name, type);
        this.configureAST(leftExpression, paramNode);
        Parameter parameter = null;
        if (node != null) {
            this.assertNodeType(120, node);
            final Expression rightExpression = this.expression(node.getFirstChild());
            if (this.isAnInterface()) {
                throw new ASTRuntimeException(node, "Cannot specify default value for method parameter '" + name + " = " + rightExpression.getText() + "' inside an interface");
            }
            parameter = new Parameter(type, name, rightExpression);
        }
        else {
            parameter = new Parameter(type, name);
        }
        if (this.firstParam) {
            this.firstParamIsVarArg = variableParameterDef;
        }
        this.configureAST(parameter, paramNode);
        parameter.addAnnotations(annotations);
        return parameter;
    }
    
    protected int modifiers(final AST modifierNode, final List<AnnotationNode> annotations, final int defaultModifiers) {
        this.assertNodeType(5, modifierNode);
        boolean access = false;
        int answer = 0;
        for (AST node = modifierNode.getFirstChild(); node != null; node = node.getNextSibling()) {
            final int type = node.getType();
            switch (type) {
                case 59: {
                    break;
                }
                case 65: {
                    annotations.add(this.annotation(node));
                    break;
                }
                case 111: {
                    answer = this.setModifierBit(node, answer, 2);
                    access = this.setAccessTrue(node, access);
                    break;
                }
                case 113: {
                    answer = this.setModifierBit(node, answer, 4);
                    access = this.setAccessTrue(node, access);
                    break;
                }
                case 112: {
                    answer = this.setModifierBit(node, answer, 1);
                    access = this.setAccessTrue(node, access);
                    break;
                }
                case 38: {
                    answer = this.setModifierBit(node, answer, 1024);
                    break;
                }
                case 37: {
                    answer = this.setModifierBit(node, answer, 16);
                    break;
                }
                case 115: {
                    answer = this.setModifierBit(node, answer, 256);
                    break;
                }
                case 80: {
                    answer = this.setModifierBit(node, answer, 8);
                    break;
                }
                case 42: {
                    answer = this.setModifierBit(node, answer, 2048);
                    break;
                }
                case 117: {
                    answer = this.setModifierBit(node, answer, 32);
                    break;
                }
                case 114: {
                    answer = this.setModifierBit(node, answer, 128);
                    break;
                }
                case 118: {
                    answer = this.setModifierBit(node, answer, 64);
                    break;
                }
                default: {
                    this.unknownAST(node);
                    break;
                }
            }
        }
        if (!access) {
            answer |= defaultModifiers;
        }
        return answer;
    }
    
    protected boolean setAccessTrue(final AST node, final boolean access) {
        if (!access) {
            return true;
        }
        throw new ASTRuntimeException(node, "Cannot specify modifier: " + node.getText() + " when access scope has already been defined");
    }
    
    protected int setModifierBit(final AST node, final int answer, final int bit) {
        if ((answer & bit) != 0x0) {
            throw new ASTRuntimeException(node, "Cannot repeat modifier: " + node.getText());
        }
        return answer | bit;
    }
    
    protected AnnotationNode annotation(final AST annotationNode) {
        AST node = annotationNode.getFirstChild();
        final String name = qualifiedName(node);
        final AnnotationNode annotatedNode = new AnnotationNode(ClassHelper.make(name));
        this.configureAST(annotatedNode, annotationNode);
        while (true) {
            node = node.getNextSibling();
            if (!isType(66, node)) {
                return annotatedNode;
            }
            final AST memberNode = node.getFirstChild();
            final String param = this.identifier(memberNode);
            final Expression expression = this.expression(memberNode.getNextSibling());
            if (annotatedNode.getMember(param) != null) {
                throw new ASTRuntimeException(memberNode, "Annotation member '" + param + "' has already been associated with a value");
            }
            annotatedNode.setMember(param, expression);
        }
    }
    
    protected Statement statement(final AST node) {
        Statement statement = null;
        final int type = node.getType();
        switch (type) {
            case 7:
            case 147: {
                statement = this.statementList(node);
                break;
            }
            case 26: {
                statement = this.methodCall(node);
                break;
            }
            case 9: {
                statement = this.variableDef(node);
                break;
            }
            case 21: {
                statement = this.labelledStatement(node);
                break;
            }
            case 142: {
                statement = this.assertStatement(node);
                break;
            }
            case 139: {
                statement = this.breakStatement(node);
                break;
            }
            case 140: {
                statement = this.continueStatement(node);
                break;
            }
            case 132: {
                statement = this.ifStatement(node);
                break;
            }
            case 136: {
                statement = this.forStatement(node);
                break;
            }
            case 138: {
                statement = this.returnStatement(node);
                break;
            }
            case 117: {
                statement = this.synchronizedStatement(node);
                break;
            }
            case 135: {
                statement = this.switchStatement(node);
                break;
            }
            case 146: {
                statement = this.tryStatement(node);
                break;
            }
            case 141: {
                statement = this.throwStatement(node);
                break;
            }
            case 134: {
                statement = this.whileStatement(node);
                break;
            }
            default: {
                statement = new ExpressionStatement(this.expression(node));
                break;
            }
        }
        if (statement != null) {
            this.configureAST(statement, node);
        }
        return statement;
    }
    
    protected Statement statementList(final AST code) {
        return this.statementListNoChild(code.getFirstChild(), code);
    }
    
    protected Statement statementListNoChild(AST node, final AST alternativeConfigureNode) {
        final BlockStatement block = new BlockStatement();
        if (node != null) {
            this.configureAST(block, node);
        }
        else {
            this.configureAST(block, alternativeConfigureNode);
        }
        while (node != null) {
            block.addStatement(this.statement(node));
            node = node.getNextSibling();
        }
        return block;
    }
    
    protected Statement assertStatement(final AST assertNode) {
        AST node = assertNode.getFirstChild();
        final BooleanExpression booleanExpression = this.booleanExpression(node);
        Expression messageExpression = null;
        node = node.getNextSibling();
        if (node != null) {
            messageExpression = this.expression(node);
        }
        else {
            messageExpression = ConstantExpression.NULL;
        }
        final AssertStatement assertStatement = new AssertStatement(booleanExpression, messageExpression);
        this.configureAST(assertStatement, assertNode);
        return assertStatement;
    }
    
    protected Statement breakStatement(final AST node) {
        final BreakStatement breakStatement = new BreakStatement(this.label(node));
        this.configureAST(breakStatement, node);
        return breakStatement;
    }
    
    protected Statement continueStatement(final AST node) {
        final ContinueStatement continueStatement = new ContinueStatement(this.label(node));
        this.configureAST(continueStatement, node);
        return continueStatement;
    }
    
    protected Statement forStatement(final AST forNode) {
        final AST inNode = forNode.getFirstChild();
        Expression collectionExpression;
        Parameter forParameter;
        if (isType(76, inNode)) {
            this.forStatementBeingDef = true;
            final ClosureListExpression clist = this.closureListExpression(inNode);
            this.forStatementBeingDef = false;
            final int size = clist.getExpressions().size();
            if (size != 3) {
                throw new ASTRuntimeException(inNode, "3 expressions are required for the classic for loop, you gave " + size);
            }
            collectionExpression = clist;
            forParameter = ForStatement.FOR_LOOP_DUMMY;
        }
        else {
            AST variableNode = inNode.getFirstChild();
            final AST collectionNode = variableNode.getNextSibling();
            ClassNode type = ClassHelper.OBJECT_TYPE;
            if (isType(9, variableNode)) {
                AST node = variableNode.getFirstChild();
                if (isType(5, node)) {
                    final int modifiersMask = this.modifiers(node, new ArrayList<AnnotationNode>(), 0);
                    if ((modifiersMask & 0xFFFFFFEF) != 0x0) {
                        throw new ASTRuntimeException(node, "Only the 'final' modifier is allowed in front of the for loop variable.");
                    }
                    node = node.getNextSibling();
                }
                type = this.makeTypeWithArguments(node);
                variableNode = node.getNextSibling();
            }
            final String variable = this.identifier(variableNode);
            collectionExpression = this.expression(collectionNode);
            forParameter = new Parameter(type, variable);
            this.configureAST(forParameter, variableNode);
        }
        final AST node2 = inNode.getNextSibling();
        Statement block;
        if (isType(124, node2)) {
            block = EmptyStatement.INSTANCE;
        }
        else {
            block = this.statement(node2);
        }
        final ForStatement forStatement = new ForStatement(forParameter, collectionExpression, block);
        this.configureAST(forStatement, forNode);
        return forStatement;
    }
    
    protected Statement ifStatement(final AST ifNode) {
        AST node = ifNode.getFirstChild();
        this.assertNodeType(27, node);
        final BooleanExpression booleanExpression = this.booleanExpression(node);
        node = node.getNextSibling();
        final Statement ifBlock = this.statement(node);
        Statement elseBlock = EmptyStatement.INSTANCE;
        node = node.getNextSibling();
        if (node != null) {
            elseBlock = this.statement(node);
        }
        final IfStatement ifStatement = new IfStatement(booleanExpression, ifBlock, elseBlock);
        this.configureAST(ifStatement, ifNode);
        return ifStatement;
    }
    
    protected Statement labelledStatement(final AST labelNode) {
        final AST node = labelNode.getFirstChild();
        final String label = this.identifier(node);
        final Statement statement = this.statement(node.getNextSibling());
        if (statement.getStatementLabel() == null) {
            statement.setStatementLabel(label);
        }
        return statement;
    }
    
    protected Statement methodCall(final AST code) {
        final Expression expression = this.methodCallExpression(code);
        final ExpressionStatement expressionStatement = new ExpressionStatement(expression);
        this.configureAST(expressionStatement, code);
        return expressionStatement;
    }
    
    protected Expression declarationExpression(final AST variableDef) {
        AST node = variableDef.getFirstChild();
        ClassNode type = null;
        final List<AnnotationNode> annotations = new ArrayList<AnnotationNode>();
        boolean staticVariable = false;
        AST modifierNode = null;
        if (isType(5, node)) {
            final int modifiers = this.modifiers(node, annotations, 0);
            if ((modifiers & 0x8) != 0x0) {
                modifierNode = node;
                staticVariable = true;
            }
            node = node.getNextSibling();
        }
        if (isType(12, node)) {
            type = this.makeTypeWithArguments(node);
            node = node.getNextSibling();
        }
        Expression rightExpression = ConstantExpression.NULL;
        Expression leftExpression;
        if (isType(120, node)) {
            node = node.getFirstChild();
            final AST left = node.getFirstChild();
            final ArgumentListExpression alist = new ArgumentListExpression();
            for (AST varDef = left; varDef != null; varDef = varDef.getNextSibling()) {
                this.assertNodeType(9, varDef);
                final DeclarationExpression de = (DeclarationExpression)this.declarationExpression(varDef);
                alist.addExpression(de.getVariableExpression());
            }
            leftExpression = alist;
            final AST right = node.getNextSibling();
            if (right != null) {
                rightExpression = this.expression(right);
            }
        }
        else {
            if (staticVariable) {
                throw new ASTRuntimeException(modifierNode, "Variable definition has an incorrect modifier 'static'.");
            }
            final String name = this.identifier(node);
            final VariableExpression ve = new VariableExpression(name, type);
            ve.addAnnotations(annotations);
            leftExpression = ve;
            final AST right = node.getNextSibling();
            if (right != null) {
                this.assertNodeType(120, right);
                rightExpression = this.expression(right.getFirstChild());
            }
        }
        this.configureAST(leftExpression, node);
        final Token token = makeToken(100, variableDef);
        final DeclarationExpression expression = new DeclarationExpression(leftExpression, token, rightExpression);
        this.configureAST(expression, variableDef);
        final ExpressionStatement expressionStatement = new ExpressionStatement(expression);
        this.configureAST(expressionStatement, variableDef);
        return expression;
    }
    
    protected Statement variableDef(final AST variableDef) {
        final ExpressionStatement expressionStatement = new ExpressionStatement(this.declarationExpression(variableDef));
        this.configureAST(expressionStatement, variableDef);
        return expressionStatement;
    }
    
    protected Statement returnStatement(final AST node) {
        final AST exprNode = node.getFirstChild();
        final Expression expression = (exprNode == null) ? ConstantExpression.NULL : this.expression(exprNode);
        final ReturnStatement returnStatement = new ReturnStatement(expression);
        this.configureAST(returnStatement, node);
        return returnStatement;
    }
    
    protected Statement switchStatement(final AST switchNode) {
        AST node = switchNode.getFirstChild();
        final Expression expression = this.expression(node);
        Statement defaultStatement = EmptyStatement.INSTANCE;
        final List list = new ArrayList();
        for (node = node.getNextSibling(); isType(31, node); node = node.getNextSibling()) {
            final AST child = node.getFirstChild();
            Statement tmpDefaultStatement;
            if (isType(145, child)) {
                final List cases = new LinkedList();
                tmpDefaultStatement = this.caseStatements(child, cases);
                list.addAll(cases);
            }
            else {
                tmpDefaultStatement = this.statement(child.getNextSibling());
            }
            if (tmpDefaultStatement != EmptyStatement.INSTANCE) {
                if (defaultStatement != EmptyStatement.INSTANCE) {
                    throw new ASTRuntimeException(switchNode, "The default case is already defined.");
                }
                defaultStatement = tmpDefaultStatement;
            }
        }
        if (node != null) {
            this.unknownAST(node);
        }
        final SwitchStatement switchStatement = new SwitchStatement(expression, list, defaultStatement);
        this.configureAST(switchStatement, switchNode);
        return switchStatement;
    }
    
    protected Statement caseStatements(final AST node, final List cases) {
        final List<Expression> expressions = new LinkedList<Expression>();
        Statement statement = EmptyStatement.INSTANCE;
        Statement defaultStatement = EmptyStatement.INSTANCE;
        AST nextSibling = node;
        do {
            final Expression expression = this.expression(nextSibling.getFirstChild());
            expressions.add(expression);
            nextSibling = nextSibling.getNextSibling();
        } while (isType(145, nextSibling));
        if (nextSibling != null) {
            if (isType(125, nextSibling)) {
                defaultStatement = this.statement(nextSibling.getNextSibling());
                statement = EmptyStatement.INSTANCE;
            }
            else {
                statement = this.statement(nextSibling);
            }
        }
        final Iterator iterator = expressions.iterator();
        while (iterator.hasNext()) {
            final Expression expr = iterator.next();
            Statement stmt;
            if (iterator.hasNext()) {
                stmt = new CaseStatement(expr, EmptyStatement.INSTANCE);
            }
            else {
                stmt = new CaseStatement(expr, statement);
            }
            this.configureAST(stmt, node);
            cases.add(stmt);
        }
        return defaultStatement;
    }
    
    protected Statement synchronizedStatement(final AST syncNode) {
        final AST node = syncNode.getFirstChild();
        final Expression expression = this.expression(node);
        final Statement code = this.statement(node.getNextSibling());
        final SynchronizedStatement synchronizedStatement = new SynchronizedStatement(expression, code);
        this.configureAST(synchronizedStatement, syncNode);
        return synchronizedStatement;
    }
    
    protected Statement throwStatement(final AST node) {
        AST expressionNode = node.getFirstChild();
        if (expressionNode == null) {
            expressionNode = node.getNextSibling();
        }
        if (expressionNode == null) {
            throw new ASTRuntimeException(node, "No expression available");
        }
        final ThrowStatement throwStatement = new ThrowStatement(this.expression(expressionNode));
        this.configureAST(throwStatement, node);
        return throwStatement;
    }
    
    protected Statement tryStatement(final AST tryStatementNode) {
        final AST tryNode = tryStatementNode.getFirstChild();
        final Statement tryStatement = this.statement(tryNode);
        Statement finallyStatement = EmptyStatement.INSTANCE;
        AST node = tryNode.getNextSibling();
        final List<CatchStatement> catches = new ArrayList<CatchStatement>();
        while (node != null && isType(148, node)) {
            catches.add(this.catchStatement(node));
            node = node.getNextSibling();
        }
        if (isType(147, node)) {
            finallyStatement = this.statement(node);
            node = node.getNextSibling();
        }
        if (finallyStatement instanceof EmptyStatement && catches.size() == 0) {
            throw new ASTRuntimeException(tryStatementNode, "A try statement must have at least one catch or finally block.");
        }
        final TryCatchStatement tryCatchStatement = new TryCatchStatement(tryStatement, finallyStatement);
        this.configureAST(tryCatchStatement, tryStatementNode);
        for (final CatchStatement statement : catches) {
            tryCatchStatement.addCatch(statement);
        }
        return tryCatchStatement;
    }
    
    protected CatchStatement catchStatement(final AST catchNode) {
        AST node = catchNode.getFirstChild();
        final Parameter parameter = this.parameter(node);
        final ClassNode exceptionType = parameter.getType();
        final String variable = parameter.getName();
        node = node.getNextSibling();
        final Statement code = this.statement(node);
        final Parameter catchParameter = new Parameter(exceptionType, variable);
        final CatchStatement answer = new CatchStatement(catchParameter, code);
        this.configureAST(answer, catchNode);
        return answer;
    }
    
    protected Statement whileStatement(final AST whileNode) {
        AST node = whileNode.getFirstChild();
        this.assertNodeType(27, node);
        if (isType(9, node.getFirstChild())) {
            throw new ASTRuntimeException(whileNode, "While loop condition contains a declaration; this is currently unsupported.");
        }
        final BooleanExpression booleanExpression = this.booleanExpression(node);
        node = node.getNextSibling();
        Statement block;
        if (isType(124, node)) {
            block = EmptyStatement.INSTANCE;
        }
        else {
            block = this.statement(node);
        }
        final WhileStatement whileStatement = new WhileStatement(booleanExpression, block);
        this.configureAST(whileStatement, whileNode);
        return whileStatement;
    }
    
    protected Expression expression(final AST node) {
        return this.expression(node, false);
    }
    
    protected Expression expression(final AST node, final boolean convertToConstant) {
        Expression expression = this.expressionSwitch(node);
        if (convertToConstant && expression instanceof VariableExpression) {
            final VariableExpression ve = (VariableExpression)expression;
            if (!ve.isThisExpression() && !ve.isSuperExpression()) {
                expression = new ConstantExpression(ve.getName());
            }
        }
        this.configureAST(expression, node);
        return expression;
    }
    
    protected Expression expressionSwitch(final AST node) {
        final int type = node.getType();
        switch (type) {
            case 27: {
                return this.expression(node.getFirstChild());
            }
            case 32: {
                return this.expressionList(node);
            }
            case 7: {
                return this.blockExpression(node);
            }
            case 49: {
                return this.closureExpression(node);
            }
            case 43: {
                return this.specialConstructorCallExpression(node, ClassNode.SUPER);
            }
            case 26: {
                return this.methodCallExpression(node);
            }
            case 154: {
                return this.constructorCallExpression(node);
            }
            case 44: {
                return this.specialConstructorCallExpression(node, ClassNode.THIS);
            }
            case 93:
            case 169: {
                return this.ternaryExpression(node);
            }
            case 87:
            case 149:
            case 150: {
                return this.dotExpression(node);
            }
            case 84:
            case 95:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 128: {
                return this.variableExpression(node);
            }
            case 56: {
                return this.listExpression(node);
            }
            case 57: {
                return this.mapExpression(node);
            }
            case 53: {
                return this.mapEntryExpression(node);
            }
            case 54: {
                return this.spreadExpression(node);
            }
            case 55: {
                return this.spreadMapExpression(node);
            }
            case 151: {
                return this.methodPointerExpression(node);
            }
            case 23: {
                return this.indexExpression(node);
            }
            case 153: {
                return this.instanceofExpression(node);
            }
            case 110: {
                return this.asExpression(node);
            }
            case 22: {
                return this.castExpression(node);
            }
            case 156: {
                return this.literalExpression(node, Boolean.TRUE);
            }
            case 152: {
                return this.literalExpression(node, Boolean.FALSE);
            }
            case 155: {
                return this.literalExpression(node, null);
            }
            case 85: {
                return this.literalExpression(node, node.getText());
            }
            case 47: {
                return this.gstring(node);
            }
            case 196:
            case 198:
            case 200: {
                return this.decimalExpression(node);
            }
            case 195:
            case 197:
            case 199: {
                return this.integerExpression(node);
            }
            case 192: {
                final NotExpression notExpression = new NotExpression(this.expression(node.getFirstChild()));
                this.configureAST(notExpression, node);
                return notExpression;
            }
            case 29: {
                return this.unaryMinusExpression(node);
            }
            case 191: {
                final BitwiseNegationExpression bitwiseNegationExpression = new BitwiseNegationExpression(this.expression(node.getFirstChild()));
                this.configureAST(bitwiseNegationExpression, node);
                return bitwiseNegationExpression;
            }
            case 30: {
                return this.unaryPlusExpression(node);
            }
            case 186: {
                return this.prefixExpression(node, 250);
            }
            case 189: {
                return this.prefixExpression(node, 260);
            }
            case 24: {
                return this.postfixExpression(node, 250);
            }
            case 25: {
                return this.postfixExpression(node, 260);
            }
            case 120: {
                return this.binaryExpression(100, node);
            }
            case 177: {
                return this.binaryExpression(123, node);
            }
            case 178: {
                return this.binaryExpression(121, node);
            }
            case 176: {
                return this.binaryExpression(120, node);
            }
            case 179: {
                return this.binaryExpression(122, node);
            }
            case 180: {
                return this.binaryExpression(128, node);
            }
            case 181: {
                return this.binaryExpression(125, node);
            }
            case 86: {
                return this.binaryExpression(124, node);
            }
            case 97: {
                return this.binaryExpression(126, node);
            }
            case 182: {
                return this.binaryExpression(127, node);
            }
            case 171: {
                return this.binaryExpression(164, node);
            }
            case 170: {
                return this.binaryExpression(162, node);
            }
            case 121: {
                return this.binaryExpression(341, node);
            }
            case 165: {
                return this.binaryExpression(351, node);
            }
            case 172: {
                return this.binaryExpression(340, node);
            }
            case 167: {
                return this.binaryExpression(350, node);
            }
            case 173: {
                return this.binaryExpression(342, node);
            }
            case 166: {
                return this.binaryExpression(352, node);
            }
            case 143: {
                return this.binaryExpression(200, node);
            }
            case 157: {
                return this.binaryExpression(210, node);
            }
            case 144: {
                return this.binaryExpression(201, node);
            }
            case 158: {
                return this.binaryExpression(211, node);
            }
            case 109: {
                return this.binaryExpression(202, node);
            }
            case 159: {
                return this.binaryExpression(212, node);
            }
            case 190: {
                return this.binaryExpression(206, node);
            }
            case 168: {
                return this.binaryExpression(216, node);
            }
            case 187: {
                return this.binaryExpression(203, node);
            }
            case 160: {
                return this.binaryExpression(213, node);
            }
            case 188: {
                return this.binaryExpression(205, node);
            }
            case 161: {
                return this.binaryExpression(215, node);
            }
            case 183: {
                return this.binaryExpression(280, node);
            }
            case 164: {
                return this.binaryExpression(285, node);
            }
            case 98: {
                return this.binaryExpression(281, node);
            }
            case 162: {
                return this.binaryExpression(286, node);
            }
            case 99: {
                return this.binaryExpression(282, node);
            }
            case 163: {
                return this.binaryExpression(287, node);
            }
            case 9: {
                return this.declarationExpression(node);
            }
            case 174: {
                return this.binaryExpression(90, node);
            }
            case 175: {
                return this.binaryExpression(94, node);
            }
            case 184: {
                return this.rangeExpression(node, true);
            }
            case 185: {
                return this.rangeExpression(node, false);
            }
            case 52: {
                return this.dynamicMemberExpression(node);
            }
            case 137: {
                return this.binaryExpression(573, node);
            }
            case 65: {
                return new AnnotationConstantExpression(this.annotation(node));
            }
            case 76: {
                return this.closureListExpression(node);
            }
            case 82:
            case 88: {
                return this.tupleExpression(node);
            }
            case 6: {
                return this.anonymousInnerClassDef(node);
            }
            default: {
                this.unknownAST(node);
                return null;
            }
        }
    }
    
    private TupleExpression tupleExpression(AST node) {
        final TupleExpression exp = new TupleExpression();
        this.configureAST(exp, node);
        AST nameNode;
        VariableExpression varExp;
        for (node = node.getFirstChild(); node != null; node = node.getNextSibling()) {
            this.assertNodeType(9, node);
            nameNode = node.getFirstChild().getNextSibling();
            varExp = new VariableExpression(nameNode.getText());
            this.configureAST(varExp, nameNode);
            exp.addExpression(varExp);
        }
        return exp;
    }
    
    private ClosureListExpression closureListExpression(final AST node) {
        this.isClosureListExpressionAllowedHere(node);
        AST exprNode = node.getFirstChild();
        final List<Expression> list = new LinkedList<Expression>();
        while (exprNode != null) {
            if (isType(27, exprNode)) {
                final Expression expr = this.expression(exprNode);
                this.configureAST(expr, exprNode);
                list.add(expr);
            }
            else {
                this.assertNodeType(36, exprNode);
                list.add(EmptyExpression.INSTANCE);
            }
            exprNode = exprNode.getNextSibling();
        }
        final ClosureListExpression cle = new ClosureListExpression(list);
        this.configureAST(cle, node);
        return cle;
    }
    
    private void isClosureListExpressionAllowedHere(final AST node) {
        if (!this.forStatementBeingDef) {
            throw new ASTRuntimeException(node, "Expression list of the form (a; b; c) is not supported in this context.");
        }
    }
    
    protected Expression dynamicMemberExpression(final AST dynamicMemberNode) {
        final AST node = dynamicMemberNode.getFirstChild();
        return this.expression(node);
    }
    
    protected Expression ternaryExpression(final AST ternaryNode) {
        AST node = ternaryNode.getFirstChild();
        final Expression base = this.expression(node);
        node = node.getNextSibling();
        final Expression left = this.expression(node);
        node = node.getNextSibling();
        Expression ret;
        if (node == null) {
            ret = new ElvisOperatorExpression(base, left);
        }
        else {
            final Expression right = this.expression(node);
            final BooleanExpression booleanExpression = new BooleanExpression(base);
            booleanExpression.setSourcePosition(base);
            ret = new TernaryExpression(booleanExpression, left, right);
        }
        this.configureAST(ret, ternaryNode);
        return ret;
    }
    
    protected Expression variableExpression(final AST node) {
        final String text = node.getText();
        final VariableExpression variableExpression = new VariableExpression(text);
        this.configureAST(variableExpression, node);
        return variableExpression;
    }
    
    protected Expression literalExpression(final AST node, final Object value) {
        final ConstantExpression constantExpression = new ConstantExpression(value);
        this.configureAST(constantExpression, node);
        return constantExpression;
    }
    
    protected Expression rangeExpression(final AST rangeNode, final boolean inclusive) {
        final AST node = rangeNode.getFirstChild();
        final Expression left = this.expression(node);
        final Expression right = this.expression(node.getNextSibling());
        final RangeExpression rangeExpression = new RangeExpression(left, right, inclusive);
        this.configureAST(rangeExpression, rangeNode);
        return rangeExpression;
    }
    
    protected Expression spreadExpression(final AST node) {
        final AST exprNode = node.getFirstChild();
        final AST listNode = exprNode.getFirstChild();
        final Expression right = this.expression(listNode);
        final SpreadExpression spreadExpression = new SpreadExpression(right);
        this.configureAST(spreadExpression, node);
        return spreadExpression;
    }
    
    protected Expression spreadMapExpression(final AST node) {
        final AST exprNode = node.getFirstChild();
        final Expression expr = this.expression(exprNode);
        final SpreadMapExpression spreadMapExpression = new SpreadMapExpression(expr);
        this.configureAST(spreadMapExpression, node);
        return spreadMapExpression;
    }
    
    protected Expression methodPointerExpression(final AST node) {
        final AST exprNode = node.getFirstChild();
        final Expression objectExpression = this.expression(exprNode);
        final AST mNode = exprNode.getNextSibling();
        Expression methodName;
        if (isType(52, mNode)) {
            methodName = this.expression(mNode);
        }
        else {
            methodName = new ConstantExpression(this.identifier(mNode));
        }
        this.configureAST(methodName, mNode);
        final MethodPointerExpression methodPointerExpression = new MethodPointerExpression(objectExpression, methodName);
        this.configureAST(methodPointerExpression, node);
        return methodPointerExpression;
    }
    
    protected Expression listExpression(final AST listNode) {
        final List<Expression> expressions = new ArrayList<Expression>();
        final AST elist = listNode.getFirstChild();
        this.assertNodeType(32, elist);
        for (AST node = elist.getFirstChild(); node != null; node = node.getNextSibling()) {
            switch (node.getType()) {
                case 53: {
                    this.assertNodeType(96, node);
                    break;
                }
                case 55: {
                    this.assertNodeType(54, node);
                    break;
                }
            }
            expressions.add(this.expression(node));
        }
        final ListExpression listExpression = new ListExpression(expressions);
        this.configureAST(listExpression, listNode);
        return listExpression;
    }
    
    protected Expression mapExpression(final AST mapNode) {
        final List expressions = new ArrayList();
        final AST elist = mapNode.getFirstChild();
        if (elist != null) {
            this.assertNodeType(32, elist);
            for (AST node = elist.getFirstChild(); node != null; node = node.getNextSibling()) {
                switch (node.getType()) {
                    case 53:
                    case 55: {
                        break;
                    }
                    case 54: {
                        this.assertNodeType(55, node);
                        break;
                    }
                    default: {
                        this.assertNodeType(53, node);
                        break;
                    }
                }
                expressions.add(this.mapEntryExpression(node));
            }
        }
        final MapExpression mapExpression = new MapExpression(expressions);
        this.configureAST(mapExpression, mapNode);
        return mapExpression;
    }
    
    protected MapEntryExpression mapEntryExpression(final AST node) {
        if (node.getType() == 55) {
            final AST rightNode = node.getFirstChild();
            final Expression keyExpression = this.spreadMapExpression(node);
            final Expression rightExpression = this.expression(rightNode);
            final MapEntryExpression mapEntryExpression = new MapEntryExpression(keyExpression, rightExpression);
            this.configureAST(mapEntryExpression, node);
            return mapEntryExpression;
        }
        final AST keyNode = node.getFirstChild();
        final Expression keyExpression = this.expression(keyNode);
        final AST valueNode = keyNode.getNextSibling();
        final Expression valueExpression = this.expression(valueNode);
        final MapEntryExpression mapEntryExpression2 = new MapEntryExpression(keyExpression, valueExpression);
        this.configureAST(mapEntryExpression2, node);
        return mapEntryExpression2;
    }
    
    protected Expression instanceofExpression(final AST node) {
        final AST leftNode = node.getFirstChild();
        final Expression leftExpression = this.expression(leftNode);
        final AST rightNode = leftNode.getNextSibling();
        final ClassNode type = this.buildName(rightNode);
        this.assertTypeNotNull(type, rightNode);
        final Expression rightExpression = new ClassExpression(type);
        this.configureAST(rightExpression, rightNode);
        final BinaryExpression binaryExpression = new BinaryExpression(leftExpression, makeToken(544, node), rightExpression);
        this.configureAST(binaryExpression, node);
        return binaryExpression;
    }
    
    protected void assertTypeNotNull(final ClassNode type, final AST rightNode) {
        if (type == null) {
            throw new ASTRuntimeException(rightNode, "No type available for: " + qualifiedName(rightNode));
        }
    }
    
    protected Expression asExpression(final AST node) {
        final AST leftNode = node.getFirstChild();
        final Expression leftExpression = this.expression(leftNode);
        final AST rightNode = leftNode.getNextSibling();
        final ClassNode type = this.makeTypeWithArguments(rightNode);
        return CastExpression.asExpression(type, leftExpression);
    }
    
    protected Expression castExpression(final AST castNode) {
        final AST node = castNode.getFirstChild();
        final ClassNode type = this.makeTypeWithArguments(node);
        this.assertTypeNotNull(type, node);
        final AST expressionNode = node.getNextSibling();
        final Expression expression = this.expression(expressionNode);
        final CastExpression castExpression = new CastExpression(type, expression);
        this.configureAST(castExpression, castNode);
        return castExpression;
    }
    
    protected Expression indexExpression(final AST indexNode) {
        final AST bracket = indexNode.getFirstChild();
        final AST leftNode = bracket.getNextSibling();
        final Expression leftExpression = this.expression(leftNode);
        final AST rightNode = leftNode.getNextSibling();
        final Expression rightExpression = this.expression(rightNode);
        final BinaryExpression binaryExpression = new BinaryExpression(leftExpression, makeToken(30, bracket), rightExpression);
        this.configureAST(binaryExpression, indexNode);
        return binaryExpression;
    }
    
    protected Expression binaryExpression(final int type, final AST node) {
        final Token token = makeToken(type, node);
        final AST leftNode = node.getFirstChild();
        final Expression leftExpression = this.expression(leftNode);
        final AST rightNode = leftNode.getNextSibling();
        if (rightNode == null) {
            return leftExpression;
        }
        if (Types.ofType(type, 1100) && !(leftExpression instanceof VariableExpression) && leftExpression.getClass() != PropertyExpression.class && !(leftExpression instanceof FieldExpression) && !(leftExpression instanceof AttributeExpression) && !(leftExpression instanceof DeclarationExpression)) {
            if (!(leftExpression instanceof TupleExpression)) {
                if (leftExpression instanceof ConstantExpression) {
                    throw new ASTRuntimeException(node, "\n[" + ((ConstantExpression)leftExpression).getValue() + "] is a constant expression, but it should be a variable expression");
                }
                if (leftExpression instanceof BinaryExpression) {
                    final Expression leftexp = ((BinaryExpression)leftExpression).getLeftExpression();
                    final int lefttype = ((BinaryExpression)leftExpression).getOperation().getType();
                    if (!Types.ofType(lefttype, 1100) && lefttype != 30) {
                        throw new ASTRuntimeException(node, "\n" + ((BinaryExpression)leftExpression).getText() + " is a binary expression, but it should be a variable expression");
                    }
                }
                else {
                    if (leftExpression instanceof GStringExpression) {
                        throw new ASTRuntimeException(node, "\n\"" + ((GStringExpression)leftExpression).getText() + "\" is a GString expression, but it should be a variable expression");
                    }
                    if (leftExpression instanceof MethodCallExpression) {
                        throw new ASTRuntimeException(node, "\n\"" + ((MethodCallExpression)leftExpression).getText() + "\" is a method call expression, but it should be a variable expression");
                    }
                    if (leftExpression instanceof MapExpression) {
                        throw new ASTRuntimeException(node, "\n'" + ((MapExpression)leftExpression).getText() + "' is a map expression, but it should be a variable expression");
                    }
                    throw new ASTRuntimeException(node, "\n" + leftExpression.getClass() + ", with its value '" + leftExpression.getText() + "', is a bad expression as the left hand side of an assignment operator");
                }
            }
        }
        final Expression rightExpression = this.expression(rightNode);
        final BinaryExpression binaryExpression = new BinaryExpression(leftExpression, token, rightExpression);
        this.configureAST(binaryExpression, node);
        return binaryExpression;
    }
    
    protected Expression prefixExpression(final AST node, final int token) {
        final Expression expression = this.expression(node.getFirstChild());
        final PrefixExpression prefixExpression = new PrefixExpression(makeToken(token, node), expression);
        this.configureAST(prefixExpression, node);
        return prefixExpression;
    }
    
    protected Expression postfixExpression(final AST node, final int token) {
        final Expression expression = this.expression(node.getFirstChild());
        final PostfixExpression postfixExpression = new PostfixExpression(expression, makeToken(token, node));
        this.configureAST(postfixExpression, node);
        return postfixExpression;
    }
    
    protected BooleanExpression booleanExpression(final AST node) {
        final BooleanExpression booleanExpression = new BooleanExpression(this.expression(node));
        this.configureAST(booleanExpression, node);
        return booleanExpression;
    }
    
    protected Expression dotExpression(final AST node) {
        final AST leftNode = node.getFirstChild();
        if (leftNode != null) {
            final AST identifierNode = leftNode.getNextSibling();
            if (identifierNode != null) {
                final Expression leftExpression = this.expression(leftNode);
                if (isType(51, identifierNode)) {
                    final Expression field = this.expression(identifierNode.getFirstChild(), true);
                    final AttributeExpression attributeExpression = new AttributeExpression(leftExpression, field, node.getType() != 87);
                    if (node.getType() == 149) {
                        attributeExpression.setSpreadSafe(true);
                    }
                    this.configureAST(attributeExpression, node);
                    return attributeExpression;
                }
                if (isType(7, identifierNode)) {
                    final Statement code = this.statementList(identifierNode);
                    final ClosureExpression closureExpression = new ClosureExpression(Parameter.EMPTY_ARRAY, code);
                    this.configureAST(closureExpression, identifierNode);
                    final PropertyExpression propertyExpression = new PropertyExpression(leftExpression, closureExpression);
                    if (node.getType() == 149) {
                        propertyExpression.setSpreadSafe(true);
                    }
                    this.configureAST(propertyExpression, node);
                    return propertyExpression;
                }
                Expression property = this.expression(identifierNode, true);
                if (property instanceof VariableExpression) {
                    final VariableExpression ve = (VariableExpression)property;
                    property = new ConstantExpression(ve.getName());
                }
                final PropertyExpression propertyExpression2 = new PropertyExpression(leftExpression, property, node.getType() != 87);
                if (node.getType() == 149) {
                    propertyExpression2.setSpreadSafe(true);
                }
                this.configureAST(propertyExpression2, node);
                return propertyExpression2;
            }
        }
        return this.methodCallExpression(node);
    }
    
    protected Expression specialConstructorCallExpression(final AST methodCallNode, final ClassNode special) {
        final AST node = methodCallNode.getFirstChild();
        final Expression arguments = this.arguments(node);
        final ConstructorCallExpression expression = new ConstructorCallExpression(special, arguments);
        this.configureAST(expression, methodCallNode);
        return expression;
    }
    
    private int getTypeInParenthesis(AST node) {
        if (!isType(27, node)) {
            node = node.getFirstChild();
        }
        while (node != null && isType(27, node) && node.getNextSibling() == null) {
            node = node.getFirstChild();
        }
        if (node == null) {
            return -1;
        }
        return node.getType();
    }
    
    protected Expression methodCallExpression(final AST methodCallNode) {
        final AST node = methodCallNode.getFirstChild();
        final AST elist = node.getNextSibling();
        List<GenericsType> typeArgumentList = null;
        boolean implicitThis = false;
        final boolean safe = isType(150, node);
        final boolean spreadSafe = isType(149, node);
        Expression objectExpression;
        AST selector;
        if (isType(87, node) || safe || spreadSafe) {
            final AST objectNode = node.getFirstChild();
            objectExpression = this.expression(objectNode);
            selector = objectNode.getNextSibling();
        }
        else {
            implicitThis = true;
            objectExpression = VariableExpression.THIS_EXPRESSION;
            selector = node;
        }
        if (isType(69, selector)) {
            typeArgumentList = this.getTypeArgumentsList(selector);
            selector = selector.getNextSibling();
        }
        Expression name = null;
        if (isType(95, selector)) {
            implicitThis = true;
            name = new ConstantExpression("super");
            if (objectExpression instanceof VariableExpression && ((VariableExpression)objectExpression).isThisExpression()) {
                objectExpression = VariableExpression.SUPER_EXPRESSION;
            }
        }
        else {
            if (this.isPrimitiveTypeLiteral(selector)) {
                throw new ASTRuntimeException(selector, "Primitive type literal: " + selector.getText() + " cannot be used as a method name");
            }
            if (isType(51, selector)) {
                final Expression field = this.expression(selector.getFirstChild(), true);
                final AttributeExpression attributeExpression = new AttributeExpression(objectExpression, field, node.getType() != 87);
                this.configureAST(attributeExpression, node);
                final Expression arguments = this.arguments(elist);
                final MethodCallExpression expression = new MethodCallExpression(attributeExpression, "call", arguments);
                this.setTypeArgumentsOnMethodCallExpression(expression, typeArgumentList);
                this.configureAST(expression, methodCallNode);
                return expression;
            }
            if (isType(52, selector) || isType(84, selector) || isType(47, selector) || isType(85, selector)) {
                name = this.expression(selector, true);
            }
            else {
                implicitThis = false;
                name = new ConstantExpression("call");
                objectExpression = this.expression(selector, true);
            }
        }
        if (selector.getText().equals("this") || selector.getText().equals("super")) {
            throw new ASTRuntimeException(elist, "Constructor call must be the first statement in a constructor.");
        }
        final Expression arguments2 = this.arguments(elist);
        final MethodCallExpression expression2 = new MethodCallExpression(objectExpression, name, arguments2);
        expression2.setSafe(safe);
        expression2.setSpreadSafe(spreadSafe);
        expression2.setImplicitThis(implicitThis);
        this.setTypeArgumentsOnMethodCallExpression(expression2, typeArgumentList);
        Expression ret = expression2;
        if (implicitThis && "this".equals(expression2.getMethodAsString())) {
            ret = new ConstructorCallExpression(this.classNode, arguments2);
        }
        this.configureAST(ret, methodCallNode);
        return ret;
    }
    
    private void setTypeArgumentsOnMethodCallExpression(final MethodCallExpression expression, final List<GenericsType> typeArgumentList) {
        if (typeArgumentList != null && typeArgumentList.size() > 0) {
            expression.setGenericsTypes(typeArgumentList.toArray(new GenericsType[typeArgumentList.size()]));
        }
    }
    
    protected Expression constructorCallExpression(AST node) {
        final AST constructorCallNode = node;
        ClassNode type = this.makeTypeWithArguments(constructorCallNode);
        if (isType(44, node) || isType(154, node)) {
            node = node.getFirstChild();
        }
        AST elist = node.getNextSibling();
        if (elist == null && isType(32, node)) {
            elist = node;
            if ("(".equals(type.getName())) {
                type = this.classNode;
            }
        }
        if (!isType(16, elist)) {
            final Expression arguments = this.arguments(elist);
            final ClassNode innerClass = this.getAnonymousInnerClassNode(arguments);
            final ConstructorCallExpression ret = new ConstructorCallExpression(type, arguments);
            if (innerClass != null) {
                ret.setType(innerClass);
                ret.setUsingAnonymousInnerClass(true);
                innerClass.setUnresolvedSuperClass(type);
            }
            this.configureAST(ret, constructorCallNode);
            return ret;
        }
        final AST expressionNode = elist.getFirstChild();
        if (expressionNode == null) {
            throw new ASTRuntimeException(elist, "No expression for the array constructor call");
        }
        final List size = this.arraySizeExpression(expressionNode);
        final ArrayExpression arrayExpression = new ArrayExpression(type, null, size);
        this.configureAST(arrayExpression, constructorCallNode);
        return arrayExpression;
    }
    
    private ClassNode getAnonymousInnerClassNode(final Expression arguments) {
        if (arguments instanceof TupleExpression) {
            final TupleExpression te = (TupleExpression)arguments;
            final List<Expression> expressions = te.getExpressions();
            if (expressions.size() == 0) {
                return null;
            }
            final Expression last = expressions.remove(expressions.size() - 1);
            if (last instanceof AnonymousInnerClassCarrier) {
                final AnonymousInnerClassCarrier carrier = (AnonymousInnerClassCarrier)last;
                return carrier.innerClass;
            }
            expressions.add(last);
        }
        else if (arguments instanceof AnonymousInnerClassCarrier) {
            final AnonymousInnerClassCarrier carrier2 = (AnonymousInnerClassCarrier)arguments;
            return carrier2.innerClass;
        }
        return null;
    }
    
    protected List arraySizeExpression(final AST node) {
        Expression size = null;
        List list;
        if (isType(16, node)) {
            final AST right = node.getNextSibling();
            if (right != null) {
                size = this.expression(right);
            }
            else {
                size = ConstantExpression.EMPTY_EXPRESSION;
            }
            list = this.arraySizeExpression(node.getFirstChild());
        }
        else {
            size = this.expression(node);
            list = new ArrayList();
        }
        list.add(size);
        return list;
    }
    
    protected Expression arguments(final AST elist) {
        final List expressionList = new ArrayList();
        boolean namedArguments = false;
        for (AST node = elist; node != null; node = node.getNextSibling()) {
            if (isType(32, node)) {
                for (AST child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
                    namedArguments |= this.addArgumentExpression(child, expressionList);
                }
            }
            else {
                namedArguments |= this.addArgumentExpression(node, expressionList);
            }
        }
        if (namedArguments) {
            if (!expressionList.isEmpty()) {
                final List argumentList = new ArrayList();
                for (final Expression expression : expressionList) {
                    if (!(expression instanceof MapEntryExpression)) {
                        argumentList.add(expression);
                    }
                }
                if (!argumentList.isEmpty()) {
                    expressionList.removeAll(argumentList);
                    this.checkDuplicateNamedParams(elist, expressionList);
                    final MapExpression mapExpression = new MapExpression(expressionList);
                    this.configureAST(mapExpression, elist);
                    argumentList.add(0, mapExpression);
                    final ArgumentListExpression argumentListExpression = new ArgumentListExpression(argumentList);
                    this.configureAST(argumentListExpression, elist);
                    return argumentListExpression;
                }
            }
            this.checkDuplicateNamedParams(elist, expressionList);
            final NamedArgumentListExpression namedArgumentListExpression = new NamedArgumentListExpression(expressionList);
            this.configureAST(namedArgumentListExpression, elist);
            return namedArgumentListExpression;
        }
        final ArgumentListExpression argumentListExpression2 = new ArgumentListExpression(expressionList);
        this.configureAST(argumentListExpression2, elist);
        return argumentListExpression2;
    }
    
    private void checkDuplicateNamedParams(final AST elist, final List expressionList) {
        if (expressionList.isEmpty()) {
            return;
        }
        final Set<String> namedArgumentNames = new HashSet<String>();
        for (final MapEntryExpression meExp : expressionList) {
            if (meExp.getKeyExpression() instanceof ConstantExpression) {
                final String argName = ((ConstantExpression)meExp.getKeyExpression()).getText();
                if (namedArgumentNames.contains(argName)) {
                    throw new ASTRuntimeException(elist, "Duplicate named parameter '" + argName + "' found.");
                }
                namedArgumentNames.add(argName);
            }
        }
    }
    
    protected boolean addArgumentExpression(final AST node, final List expressionList) {
        if (node.getType() == 55) {
            final AST rightNode = node.getFirstChild();
            final Expression keyExpression = this.spreadMapExpression(node);
            final Expression rightExpression = this.expression(rightNode);
            final MapEntryExpression mapEntryExpression = new MapEntryExpression(keyExpression, rightExpression);
            expressionList.add(mapEntryExpression);
            return true;
        }
        final Expression expression = this.expression(node);
        expressionList.add(expression);
        return expression instanceof MapEntryExpression;
    }
    
    protected Expression expressionList(final AST node) {
        final List expressionList = new ArrayList();
        for (AST child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            expressionList.add(this.expression(child));
        }
        if (expressionList.size() == 1) {
            return expressionList.get(0);
        }
        final ListExpression listExpression = new ListExpression(expressionList);
        listExpression.setWrapped(true);
        this.configureAST(listExpression, node);
        return listExpression;
    }
    
    protected ClosureExpression closureExpression(final AST node) {
        final AST paramNode = node.getFirstChild();
        Parameter[] parameters = null;
        AST codeNode = paramNode;
        if (isType(19, paramNode) || isType(50, paramNode)) {
            parameters = this.parameters(paramNode);
            codeNode = paramNode.getNextSibling();
        }
        final Statement code = this.statementListNoChild(codeNode, node);
        final ClosureExpression closureExpression = new ClosureExpression(parameters, code);
        this.configureAST(closureExpression, node);
        return closureExpression;
    }
    
    protected Expression blockExpression(final AST node) {
        final AST codeNode = node.getFirstChild();
        if (codeNode == null) {
            return ConstantExpression.NULL;
        }
        if (codeNode.getType() == 27 && codeNode.getNextSibling() == null) {
            return this.expression(codeNode);
        }
        final Parameter[] parameters = Parameter.EMPTY_ARRAY;
        final Statement code = this.statementListNoChild(codeNode, node);
        final ClosureExpression closureExpression = new ClosureExpression(parameters, code);
        this.configureAST(closureExpression, node);
        final String callName = "call";
        final Expression noArguments = new ArgumentListExpression();
        final MethodCallExpression call = new MethodCallExpression(closureExpression, callName, noArguments);
        this.configureAST(call, node);
        return call;
    }
    
    protected Expression unaryMinusExpression(final AST unaryMinusExpr) {
        final AST node = unaryMinusExpr.getFirstChild();
        final String text = node.getText();
        switch (node.getType()) {
            case 196:
            case 198:
            case 200: {
                final ConstantExpression constantExpression = new ConstantExpression(Numbers.parseDecimal("-" + text));
                this.configureAST(constantExpression, unaryMinusExpr);
                return constantExpression;
            }
            case 195:
            case 197:
            case 199: {
                final ConstantExpression constantLongExpression = new ConstantExpression(Numbers.parseInteger("-" + text));
                this.configureAST(constantLongExpression, unaryMinusExpr);
                return constantLongExpression;
            }
            default: {
                final UnaryMinusExpression unaryMinusExpression = new UnaryMinusExpression(this.expression(node));
                this.configureAST(unaryMinusExpression, unaryMinusExpr);
                return unaryMinusExpression;
            }
        }
    }
    
    protected Expression unaryPlusExpression(final AST unaryPlusExpr) {
        final AST node = unaryPlusExpr.getFirstChild();
        switch (node.getType()) {
            case 195:
            case 196:
            case 197:
            case 198:
            case 199:
            case 200: {
                return this.expression(node);
            }
            default: {
                final UnaryPlusExpression unaryPlusExpression = new UnaryPlusExpression(this.expression(node));
                this.configureAST(unaryPlusExpression, unaryPlusExpr);
                return unaryPlusExpression;
            }
        }
    }
    
    protected ConstantExpression decimalExpression(final AST node) {
        final String text = node.getText();
        final ConstantExpression constantExpression = new ConstantExpression(Numbers.parseDecimal(text));
        this.configureAST(constantExpression, node);
        return constantExpression;
    }
    
    protected ConstantExpression integerExpression(final AST node) {
        final String text = node.getText();
        final ConstantExpression constantExpression = new ConstantExpression(Numbers.parseInteger(text));
        this.configureAST(constantExpression, node);
        return constantExpression;
    }
    
    protected Expression gstring(final AST gstringNode) {
        final List strings = new ArrayList();
        final List values = new ArrayList();
        final StringBuffer buffer = new StringBuffer();
        boolean isPrevString = false;
        for (AST node = gstringNode.getFirstChild(); node != null; node = node.getNextSibling()) {
            final int type = node.getType();
            String text = null;
            switch (type) {
                case 85: {
                    if (isPrevString) {
                        this.assertNodeType(84, node);
                    }
                    isPrevString = true;
                    text = node.getText();
                    final ConstantExpression constantExpression = new ConstantExpression(text);
                    this.configureAST(constantExpression, node);
                    strings.add(constantExpression);
                    buffer.append(text);
                    break;
                }
                default: {
                    if (!isPrevString) {
                        this.assertNodeType(84, node);
                    }
                    isPrevString = false;
                    final Expression expression = this.expression(node);
                    values.add(expression);
                    buffer.append("$");
                    buffer.append(expression.getText());
                    break;
                }
            }
        }
        final GStringExpression gStringExpression = new GStringExpression(buffer.toString(), strings, values);
        this.configureAST(gStringExpression, gstringNode);
        return gStringExpression;
    }
    
    protected ClassNode type(final AST typeNode) {
        return this.buildName(typeNode.getFirstChild());
    }
    
    public static String qualifiedName(final AST qualifiedNameNode) {
        if (isType(84, qualifiedNameNode)) {
            return qualifiedNameNode.getText();
        }
        if (isType(87, qualifiedNameNode)) {
            AST node = qualifiedNameNode.getFirstChild();
            final StringBuffer buffer = new StringBuffer();
            boolean first = true;
            while (node != null && !isType(69, node)) {
                if (first) {
                    first = false;
                }
                else {
                    buffer.append(".");
                }
                buffer.append(qualifiedName(node));
                node = node.getNextSibling();
            }
            return buffer.toString();
        }
        return qualifiedNameNode.getText();
    }
    
    private static AST getTypeArgumentsNode(AST root) {
        while (root != null && !isType(69, root)) {
            root = root.getNextSibling();
        }
        return root;
    }
    
    private int getBoundType(final AST node) {
        if (node == null) {
            return -1;
        }
        if (isType(74, node)) {
            return 74;
        }
        if (isType(75, node)) {
            return 75;
        }
        throw new ASTRuntimeException(node, "Unexpected node type: " + this.getTokenName(node) + " found when expecting type: " + this.getTokenName(74) + " or type: " + this.getTokenName(75));
    }
    
    private GenericsType makeGenericsArgumentType(final AST typeArgument) {
        final AST rootNode = typeArgument.getFirstChild();
        GenericsType gt;
        if (isType(73, rootNode)) {
            final ClassNode base = ClassHelper.makeWithoutCaching("?");
            if (rootNode.getNextSibling() != null) {
                final int boundType = this.getBoundType(rootNode.getNextSibling());
                final ClassNode[] gts = this.makeGenericsBounds(rootNode, boundType);
                if (boundType == 74) {
                    gt = new GenericsType(base, gts, null);
                }
                else {
                    gt = new GenericsType(base, null, gts[0]);
                }
            }
            else {
                gt = new GenericsType(base, null, null);
            }
            gt.setName("?");
            gt.setWildcard(true);
        }
        else {
            final ClassNode argument = this.makeTypeWithArguments(rootNode);
            gt = new GenericsType(argument);
        }
        this.configureAST(gt, typeArgument);
        return gt;
    }
    
    protected ClassNode makeTypeWithArguments(final AST rootNode) {
        final ClassNode basicType = this.makeType(rootNode);
        AST node = rootNode.getFirstChild();
        if (node == null || isType(23, node) || isType(16, node)) {
            return basicType;
        }
        if (isType(87, node)) {
            for (node = node.getFirstChild(); node != null && !isType(69, node); node = node.getNextSibling()) {}
            return (node == null) ? basicType : this.addTypeArguments(basicType, node);
        }
        node = node.getFirstChild();
        if (node == null) {
            return basicType;
        }
        return this.addTypeArguments(basicType, node);
    }
    
    private ClassNode addTypeArguments(final ClassNode basicType, final AST node) {
        final List<GenericsType> typeArgumentList = this.getTypeArgumentsList(node);
        if (typeArgumentList.size() > 0) {
            basicType.setGenericsTypes(typeArgumentList.toArray(new GenericsType[typeArgumentList.size()]));
        }
        return basicType;
    }
    
    private List<GenericsType> getTypeArgumentsList(final AST node) {
        this.assertNodeType(69, node);
        final List<GenericsType> typeArgumentList = new LinkedList<GenericsType>();
        for (AST typeArgument = node.getFirstChild(); typeArgument != null; typeArgument = typeArgument.getNextSibling()) {
            this.assertNodeType(70, typeArgument);
            final GenericsType gt = this.makeGenericsArgumentType(typeArgument);
            typeArgumentList.add(gt);
        }
        return typeArgumentList;
    }
    
    private ClassNode[] makeGenericsBounds(final AST rn, final int boundType) {
        final AST boundsRoot = rn.getNextSibling();
        if (boundsRoot == null) {
            return null;
        }
        this.assertNodeType(boundType, boundsRoot);
        final LinkedList bounds = new LinkedList();
        for (AST boundsNode = boundsRoot.getFirstChild(); boundsNode != null; boundsNode = boundsNode.getNextSibling()) {
            ClassNode bound = null;
            bound = this.makeTypeWithArguments(boundsNode);
            this.configureAST(bound, boundsNode);
            bounds.add(bound);
        }
        if (bounds.size() == 0) {
            return null;
        }
        return bounds.toArray(new ClassNode[bounds.size()]);
    }
    
    protected GenericsType[] makeGenericsType(final AST rootNode) {
        AST typeParameter = rootNode.getFirstChild();
        final LinkedList ret = new LinkedList();
        this.assertNodeType(72, typeParameter);
        while (isType(72, typeParameter)) {
            final AST typeNode = typeParameter.getFirstChild();
            final ClassNode type = this.makeType(typeParameter);
            final GenericsType gt = new GenericsType(type, this.makeGenericsBounds(typeNode, 74), null);
            this.configureAST(gt, typeParameter);
            ret.add(gt);
            typeParameter = typeParameter.getNextSibling();
        }
        return ret.toArray(new GenericsType[0]);
    }
    
    protected ClassNode makeType(final AST typeNode) {
        ClassNode answer = ClassHelper.DYNAMIC_TYPE;
        final AST node = typeNode.getFirstChild();
        if (node != null) {
            if (isType(23, node) || isType(16, node)) {
                answer = this.makeType(node).makeArray();
            }
            else {
                answer = ClassHelper.make(qualifiedName(node));
                if (answer.isUsingGenerics()) {
                    final ClassNode newAnswer = ClassHelper.makeWithoutCaching(answer.getName());
                    newAnswer.setRedirect(answer);
                    answer = newAnswer;
                }
            }
            this.configureAST(answer, node);
        }
        return answer;
    }
    
    protected ClassNode buildName(AST node) {
        if (isType(12, node)) {
            node = node.getFirstChild();
        }
        ClassNode answer = null;
        if (isType(87, node) || isType(150, node)) {
            answer = ClassHelper.make(qualifiedName(node));
        }
        else if (this.isPrimitiveTypeLiteral(node)) {
            answer = ClassHelper.make(node.getText());
        }
        else {
            if (isType(23, node) || isType(16, node)) {
                final AST child = node.getFirstChild();
                answer = this.buildName(child).makeArray();
                this.configureAST(answer, node);
                return answer;
            }
            final String identifier = node.getText();
            answer = ClassHelper.make(identifier);
        }
        final AST nextSibling = node.getNextSibling();
        if (isType(23, nextSibling) || isType(16, node)) {
            answer = answer.makeArray();
            this.configureAST(answer, node);
            return answer;
        }
        this.configureAST(answer, node);
        return answer;
    }
    
    protected boolean isPrimitiveTypeLiteral(final AST node) {
        final int type = node.getType();
        switch (type) {
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected String identifier(final AST node) {
        this.assertNodeType(84, node);
        return node.getText();
    }
    
    protected String label(final AST labelNode) {
        final AST node = labelNode.getFirstChild();
        if (node == null) {
            return null;
        }
        return this.identifier(node);
    }
    
    protected boolean hasVisibility(final int modifiers) {
        return (modifiers & 0x7) != 0x0;
    }
    
    protected void configureAST(final ASTNode node, final AST ast) {
        if (ast == null) {
            throw new ASTRuntimeException(ast, "PARSER BUG: Tried to configure " + node.getClass().getName() + " with null Node");
        }
        node.setColumnNumber(ast.getColumn());
        node.setLineNumber(ast.getLine());
        if (ast instanceof GroovySourceAST) {
            node.setLastColumnNumber(((GroovySourceAST)ast).getColumnLast());
            node.setLastLineNumber(((GroovySourceAST)ast).getLineLast());
        }
    }
    
    protected static Token makeToken(final int typeCode, final AST node) {
        return Token.newSymbol(typeCode, node.getLine(), node.getColumn());
    }
    
    protected String getFirstChildText(final AST node) {
        final AST child = node.getFirstChild();
        return (child != null) ? child.getText() : null;
    }
    
    public static boolean isType(final int typeCode, final AST node) {
        return node != null && node.getType() == typeCode;
    }
    
    private String getTokenName(final int token) {
        if (this.tokenNames == null) {
            return "" + token;
        }
        return this.tokenNames[token];
    }
    
    private String getTokenName(final AST node) {
        if (node == null) {
            return "null";
        }
        return this.getTokenName(node.getType());
    }
    
    protected void assertNodeType(final int type, final AST node) {
        if (node == null) {
            throw new ASTRuntimeException(node, "No child node available in AST when expecting type: " + this.getTokenName(type));
        }
        if (node.getType() != type) {
            throw new ASTRuntimeException(node, "Unexpected node type: " + this.getTokenName(node) + " found when expecting type: " + this.getTokenName(type));
        }
    }
    
    protected void notImplementedYet(final AST node) {
        throw new ASTRuntimeException(node, "AST node not implemented yet for type: " + this.getTokenName(node));
    }
    
    protected void unknownAST(final AST node) {
        if (node.getType() == 13) {
            throw new ASTRuntimeException(node, "Class definition not expected here. Perhaps try using a closure instead.");
        }
        throw new ASTRuntimeException(node, "Unknown type: " + this.getTokenName(node));
    }
    
    protected void dumpTree(final AST ast) {
        for (AST node = ast.getFirstChild(); node != null; node = node.getNextSibling()) {
            this.dump(node);
        }
    }
    
    protected void dump(final AST node) {
        System.out.println("Type: " + this.getTokenName(node) + " text: " + node.getText());
    }
    
    private static class AnonymousInnerClassCarrier extends Expression
    {
        ClassNode innerClass;
        
        @Override
        public Expression transformExpression(final ExpressionTransformer transformer) {
            return null;
        }
    }
}
