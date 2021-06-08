// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.javac;

import java.util.Arrays;
import org.codehaus.groovy.control.ResolveVisitor;
import org.codehaus.groovy.ast.ImportNode;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.tools.Utilities;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.ClassHelper;
import java.util.Collection;
import java.util.Iterator;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.classgen.Verifier;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import org.codehaus.groovy.ast.InnerClassNode;
import org.codehaus.groovy.ast.ClassNode;
import java.util.HashMap;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.ast.ConstructorNode;
import java.util.Map;
import org.codehaus.groovy.ast.MethodNode;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class JavaStubGenerator
{
    private boolean java5;
    private boolean requireSuperResolved;
    private File outputPath;
    private List<String> toCompile;
    private ArrayList<MethodNode> propertyMethods;
    private Map<String, MethodNode> propertyMethodsWithSigs;
    private ArrayList<ConstructorNode> constructors;
    private ModuleNode currentModule;
    
    public JavaStubGenerator(final File outputPath, final boolean requireSuperResolved, final boolean java5) {
        this.java5 = false;
        this.requireSuperResolved = false;
        this.toCompile = new ArrayList<String>();
        this.propertyMethods = new ArrayList<MethodNode>();
        this.propertyMethodsWithSigs = new HashMap<String, MethodNode>();
        this.constructors = new ArrayList<ConstructorNode>();
        this.outputPath = outputPath;
        this.requireSuperResolved = requireSuperResolved;
        this.java5 = java5;
        outputPath.mkdirs();
    }
    
    public JavaStubGenerator(final File outputPath) {
        this(outputPath, false, false);
    }
    
    private void mkdirs(final File parent, final String relativeFile) {
        final int index = relativeFile.lastIndexOf(47);
        if (index == -1) {
            return;
        }
        final File dir = new File(parent, relativeFile.substring(0, index));
        dir.mkdirs();
    }
    
    public void generateClass(final ClassNode classNode) throws FileNotFoundException {
        if (this.requireSuperResolved && !classNode.getSuperClass().isResolved()) {
            return;
        }
        if (classNode instanceof InnerClassNode) {
            return;
        }
        if ((classNode.getModifiers() & 0x2) != 0x0) {
            return;
        }
        final String fileName = classNode.getName().replace('.', '/');
        this.mkdirs(this.outputPath, fileName);
        this.toCompile.add(fileName);
        final File file = new File(this.outputPath, fileName + ".java");
        final FileOutputStream fos = new FileOutputStream(file);
        final PrintWriter out = new PrintWriter(fos);
        try {
            final String packageName = classNode.getPackageName();
            if (packageName != null) {
                out.println("package " + packageName + ";\n");
            }
            this.genImports(classNode, out);
            this.genClassInner(classNode, out);
        }
        finally {
            try {
                out.close();
            }
            catch (Exception ex) {}
            try {
                fos.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    private void genClassInner(final ClassNode classNode, final PrintWriter out) throws FileNotFoundException {
        if (classNode instanceof InnerClassNode && ((InnerClassNode)classNode).isAnonymous()) {
            return;
        }
        try {
            final Verifier verifier = new Verifier() {
                public void addCovariantMethods(final ClassNode cn) {
                }
                
                @Override
                protected void addTimeStamp(final ClassNode node) {
                }
                
                @Override
                protected void addInitialization(final ClassNode node) {
                }
                
                @Override
                protected void addPropertyMethod(final MethodNode method) {
                    this.doAddMethod(method);
                }
                
                @Override
                protected void addReturnIfNeeded(final MethodNode node) {
                }
                
                @Override
                protected void addMethod(final ClassNode node, final boolean shouldBeSynthetic, final String name, final int modifiers, final ClassNode returnType, final Parameter[] parameters, final ClassNode[] exceptions, final Statement code) {
                    this.doAddMethod(new MethodNode(name, modifiers, returnType, parameters, exceptions, code));
                }
                
                @Override
                protected void addConstructor(final Parameter[] newParams, final ConstructorNode ctor, Statement code, final ClassNode node) {
                    if (code instanceof ExpressionStatement) {
                        final Statement temp = code;
                        code = new BlockStatement();
                        ((BlockStatement)code).addStatement(temp);
                    }
                    final ConstructorNode ctrNode = new ConstructorNode(ctor.getModifiers(), newParams, ctor.getExceptions(), code);
                    ctrNode.setDeclaringClass(node);
                    JavaStubGenerator.this.constructors.add(ctrNode);
                }
                
                @Override
                protected void addDefaultParameters(final DefaultArgsAction action, final MethodNode method) {
                    final Parameter[] parameters = method.getParameters();
                    final Expression[] saved = new Expression[parameters.length];
                    for (int i = 0; i < parameters.length; ++i) {
                        if (parameters[i].hasInitialExpression()) {
                            saved[i] = parameters[i].getInitialExpression();
                        }
                    }
                    super.addDefaultParameters(action, method);
                    for (int i = 0; i < parameters.length; ++i) {
                        if (saved[i] != null) {
                            parameters[i].setInitialExpression(saved[i]);
                        }
                    }
                }
                
                private void doAddMethod(final MethodNode method) {
                    final String sig = method.getTypeDescriptor();
                    if (JavaStubGenerator.this.propertyMethodsWithSigs.containsKey(sig)) {
                        return;
                    }
                    JavaStubGenerator.this.propertyMethods.add(method);
                    JavaStubGenerator.this.propertyMethodsWithSigs.put(sig, method);
                }
            };
            verifier.visitClass(classNode);
            this.currentModule = classNode.getModule();
            final boolean isInterface = classNode.isInterface();
            final boolean isEnum = (classNode.getModifiers() & 0x4000) != 0x0;
            final boolean isAnnotationDefinition = classNode.isAnnotationDefinition();
            this.printAnnotations(out, classNode);
            this.printModifiers(out, classNode.getModifiers() & ~(isInterface ? 1024 : 0));
            if (isInterface) {
                if (isAnnotationDefinition) {
                    out.print("@");
                }
                out.print("interface ");
            }
            else if (isEnum) {
                out.print("enum ");
            }
            else {
                out.print("class ");
            }
            String className = classNode.getNameWithoutPackage();
            if (classNode instanceof InnerClassNode) {
                className = className.substring(className.lastIndexOf("$") + 1);
            }
            out.println(className);
            this.writeGenericsBounds(out, classNode, true);
            final ClassNode superClass = classNode.getUnresolvedSuperClass(false);
            if (!isInterface && !isEnum) {
                out.print("  extends ");
                this.printType(superClass, out);
            }
            final ClassNode[] interfaces = classNode.getInterfaces();
            if (interfaces != null && interfaces.length > 0 && !isAnnotationDefinition) {
                if (isInterface) {
                    out.println("  extends");
                }
                else {
                    out.println("  implements");
                }
                for (int i = 0; i < interfaces.length - 1; ++i) {
                    out.print("    ");
                    this.printType(interfaces[i], out);
                    out.print(",");
                }
                out.print("    ");
                this.printType(interfaces[interfaces.length - 1], out);
            }
            out.println(" {");
            this.genFields(classNode, out);
            this.genMethods(classNode, out, isEnum);
            final Iterator<InnerClassNode> inner = classNode.getInnerClasses();
            while (inner.hasNext()) {
                this.propertyMethods.clear();
                this.propertyMethodsWithSigs.clear();
                this.constructors.clear();
                this.genClassInner(inner.next(), out);
            }
            out.println("}");
        }
        finally {
            this.propertyMethods.clear();
            this.propertyMethodsWithSigs.clear();
            this.constructors.clear();
            this.currentModule = null;
        }
    }
    
    private void genMethods(final ClassNode classNode, final PrintWriter out, final boolean isEnum) {
        if (!isEnum) {
            this.getConstructors(classNode, out);
        }
        final List<MethodNode> methods = (List<MethodNode>)this.propertyMethods.clone();
        methods.addAll(classNode.getMethods());
        for (final MethodNode method : methods) {
            if (isEnum && method.isSynthetic()) {
                final String name = method.getName();
                final Parameter[] params = method.getParameters();
                if (name.equals("values") && params.length == 0) {
                    continue;
                }
                if (name.equals("valueOf") && params.length == 1 && params[0].getType().equals(ClassHelper.STRING_TYPE)) {
                    continue;
                }
            }
            this.genMethod(classNode, method, out);
        }
    }
    
    private void getConstructors(final ClassNode classNode, final PrintWriter out) {
        final List<ConstructorNode> constrs = (List<ConstructorNode>)this.constructors.clone();
        constrs.addAll(classNode.getDeclaredConstructors());
        if (constrs != null) {
            for (final ConstructorNode constr : constrs) {
                this.genConstructor(classNode, constr, out);
            }
        }
    }
    
    private void genFields(final ClassNode classNode, final PrintWriter out) {
        final boolean isInterface = classNode.isInterface();
        final List<FieldNode> fields = classNode.getFields();
        if (fields == null) {
            return;
        }
        final List<FieldNode> enumFields = new ArrayList<FieldNode>(fields.size());
        final List<FieldNode> normalFields = new ArrayList<FieldNode>(fields.size());
        for (final FieldNode field : fields) {
            final boolean isSynthetic = (field.getModifiers() & 0x1000) != 0x0;
            if (field.isEnum()) {
                enumFields.add(field);
            }
            else {
                if (isSynthetic) {
                    continue;
                }
                normalFields.add(field);
            }
        }
        this.genEnumFields(enumFields, out);
        for (final FieldNode normalField : normalFields) {
            this.genField(normalField, out, isInterface);
        }
    }
    
    private void genEnumFields(final List<FieldNode> fields, final PrintWriter out) {
        if (fields.size() == 0) {
            return;
        }
        boolean first = true;
        for (final FieldNode field : fields) {
            if (!first) {
                out.print(", ");
            }
            else {
                first = false;
            }
            out.print(field.getName());
        }
        out.println(";");
    }
    
    private void genField(final FieldNode fieldNode, final PrintWriter out, final boolean isInterface) {
        if ((fieldNode.getModifiers() & 0x2) != 0x0) {
            return;
        }
        this.printAnnotations(out, fieldNode);
        if (!isInterface) {
            this.printModifiers(out, fieldNode.getModifiers());
        }
        final ClassNode type = fieldNode.getType();
        this.printType(type, out);
        out.print(" ");
        out.print(fieldNode.getName());
        if (isInterface) {
            out.print(" = ");
            if (ClassHelper.isPrimitiveType(type)) {
                final String val = (type == ClassHelper.boolean_TYPE) ? "false" : "0";
                out.print("new " + ClassHelper.getWrapper(type) + "((" + type + ")" + val + ")");
            }
            else {
                out.print("null");
            }
        }
        out.println(";");
    }
    
    private ConstructorCallExpression getConstructorCallExpression(final ConstructorNode constructorNode) {
        final Statement code = constructorNode.getCode();
        if (!(code instanceof BlockStatement)) {
            return null;
        }
        final BlockStatement block = (BlockStatement)code;
        final List stats = block.getStatements();
        if (stats == null || stats.size() == 0) {
            return null;
        }
        final Statement stat = stats.get(0);
        if (!(stat instanceof ExpressionStatement)) {
            return null;
        }
        final Expression expr = ((ExpressionStatement)stat).getExpression();
        if (!(expr instanceof ConstructorCallExpression)) {
            return null;
        }
        return (ConstructorCallExpression)expr;
    }
    
    private void genConstructor(final ClassNode clazz, final ConstructorNode constructorNode, final PrintWriter out) {
        this.printAnnotations(out, constructorNode);
        out.print("public ");
        String className = clazz.getNameWithoutPackage();
        if (clazz instanceof InnerClassNode) {
            className = className.substring(className.lastIndexOf("$") + 1);
        }
        out.println(className);
        this.printParams(constructorNode, out);
        final ConstructorCallExpression constrCall = this.getConstructorCallExpression(constructorNode);
        if (constrCall == null || !constrCall.isSpecialCall()) {
            out.println(" {}");
        }
        else {
            out.println(" {");
            this.genSpecialConstructorArgs(out, constructorNode, constrCall);
            out.println("}");
        }
    }
    
    private Parameter[] selectAccessibleConstructorFromSuper(final ConstructorNode node) {
        final ClassNode type = node.getDeclaringClass();
        final ClassNode superType = type.getSuperClass();
        for (final ConstructorNode c : superType.getDeclaredConstructors()) {
            if (c.isPublic() || c.isProtected()) {
                return c.getParameters();
            }
        }
        if (superType.isPrimaryClassNode()) {
            return Parameter.EMPTY_ARRAY;
        }
        return null;
    }
    
    private void genSpecialConstructorArgs(final PrintWriter out, final ConstructorNode node, final ConstructorCallExpression constrCall) {
        final Parameter[] params = this.selectAccessibleConstructorFromSuper(node);
        if (params != null) {
            out.print("super (");
            for (int i = 0; i < params.length; ++i) {
                this.printDefaultValue(out, params[i].getType());
                if (i + 1 < params.length) {
                    out.print(", ");
                }
            }
            out.println(");");
            return;
        }
        final Expression arguments = constrCall.getArguments();
        if (constrCall.isSuperCall()) {
            out.print("super(");
        }
        else {
            out.print("this(");
        }
        if (arguments instanceof ArgumentListExpression) {
            final ArgumentListExpression argumentListExpression = (ArgumentListExpression)arguments;
            final List<Expression> args = argumentListExpression.getExpressions();
            for (final Expression arg : args) {
                if (arg instanceof ConstantExpression) {
                    final ConstantExpression expression = (ConstantExpression)arg;
                    final Object o = expression.getValue();
                    if (o instanceof String) {
                        out.print("(String)null");
                    }
                    else {
                        out.print(expression.getText());
                    }
                }
                else {
                    final ClassNode type = this.getConstructorArgumentType(arg, node);
                    this.printDefaultValue(out, type);
                }
                if (arg != args.get(args.size() - 1)) {
                    out.print(", ");
                }
            }
        }
        out.println(");");
    }
    
    private ClassNode getConstructorArgumentType(final Expression arg, final ConstructorNode node) {
        if (!(arg instanceof VariableExpression)) {
            return arg.getType();
        }
        final VariableExpression vexp = (VariableExpression)arg;
        final String name = vexp.getName();
        for (final Parameter param : node.getParameters()) {
            if (param.getName().equals(name)) {
                return param.getType();
            }
        }
        return vexp.getType();
    }
    
    private void genMethod(final ClassNode clazz, final MethodNode methodNode, final PrintWriter out) {
        if (methodNode.getName().equals("<clinit>")) {
            return;
        }
        if (methodNode.isPrivate() || !Utilities.isJavaIdentifier(methodNode.getName())) {
            return;
        }
        if (methodNode.isSynthetic() && methodNode.getName().equals("$getStaticMetaClass")) {
            return;
        }
        this.printAnnotations(out, methodNode);
        if (!clazz.isInterface()) {
            this.printModifiers(out, methodNode.getModifiers());
        }
        this.writeGenericsBounds(out, methodNode.getGenericsTypes());
        out.print(" ");
        this.printType(methodNode.getReturnType(), out);
        out.print(" ");
        out.print(methodNode.getName());
        this.printParams(methodNode, out);
        final ClassNode[] exceptions = methodNode.getExceptions();
        for (int i = 0; i < exceptions.length; ++i) {
            final ClassNode exception = exceptions[i];
            if (i == 0) {
                out.print("throws ");
            }
            else {
                out.print(", ");
            }
            this.printType(exception, out);
        }
        if ((methodNode.getModifiers() & 0x400) != 0x0) {
            out.println(";");
        }
        else {
            out.print(" { ");
            final ClassNode retType = methodNode.getReturnType();
            this.printReturn(out, retType);
            out.println("}");
        }
    }
    
    private void printReturn(final PrintWriter out, final ClassNode retType) {
        final String retName = retType.getName();
        if (!retName.equals("void")) {
            out.print("return ");
            this.printDefaultValue(out, retType);
            out.print(";");
        }
    }
    
    private void printDefaultValue(final PrintWriter out, final ClassNode type) {
        if (type.redirect() != ClassHelper.OBJECT_TYPE) {
            out.print("(");
            this.printType(type, out);
            out.print(")");
        }
        if (ClassHelper.isPrimitiveType(type)) {
            if (type == ClassHelper.boolean_TYPE) {
                out.print("false");
            }
            else {
                out.print("0");
            }
        }
        else {
            out.print("null");
        }
    }
    
    private void printType(final ClassNode type, final PrintWriter out) {
        if (type.isArray()) {
            this.printType(type.getComponentType(), out);
            out.print("[]");
        }
        else if (this.java5 && type.isGenericsPlaceHolder()) {
            out.print(type.getGenericsTypes()[0].getName());
        }
        else {
            this.writeGenericsBounds(out, type, false);
        }
    }
    
    private void printTypeName(final ClassNode type, final PrintWriter out) {
        if (ClassHelper.isPrimitiveType(type)) {
            if (type == ClassHelper.boolean_TYPE) {
                out.print("boolean");
            }
            else if (type == ClassHelper.char_TYPE) {
                out.print("char");
            }
            else if (type == ClassHelper.int_TYPE) {
                out.print("int");
            }
            else if (type == ClassHelper.short_TYPE) {
                out.print("short");
            }
            else if (type == ClassHelper.long_TYPE) {
                out.print("long");
            }
            else if (type == ClassHelper.float_TYPE) {
                out.print("float");
            }
            else if (type == ClassHelper.double_TYPE) {
                out.print("double");
            }
            else if (type == ClassHelper.byte_TYPE) {
                out.print("byte");
            }
            else {
                out.print("void");
            }
        }
        else {
            String name = type.getName();
            final ClassNode alias = this.currentModule.getImportType(name);
            if (alias != null) {
                name = alias.getName();
            }
            out.print(name.replace('$', '.'));
        }
    }
    
    private void writeGenericsBounds(final PrintWriter out, final ClassNode type, final boolean skipName) {
        if (!skipName) {
            this.printTypeName(type, out);
        }
        if (!this.java5) {
            return;
        }
        if (!ClassHelper.isCachedType(type)) {
            this.writeGenericsBounds(out, type.getGenericsTypes());
        }
    }
    
    private void writeGenericsBounds(final PrintWriter out, final GenericsType[] genericsTypes) {
        if (genericsTypes == null || genericsTypes.length == 0) {
            return;
        }
        out.print('<');
        for (int i = 0; i < genericsTypes.length; ++i) {
            if (i != 0) {
                out.print(", ");
            }
            this.writeGenericsBounds(out, genericsTypes[i]);
        }
        out.print('>');
    }
    
    private void writeGenericsBounds(final PrintWriter out, final GenericsType genericsType) {
        if (genericsType.isPlaceholder()) {
            out.print(genericsType.getName());
        }
        else {
            this.printType(genericsType.getType(), out);
        }
        final ClassNode[] upperBounds = genericsType.getUpperBounds();
        final ClassNode lowerBound = genericsType.getLowerBound();
        if (upperBounds != null) {
            out.print(" extends ");
            for (int i = 0; i < upperBounds.length; ++i) {
                this.printType(upperBounds[i], out);
                if (i + 1 < upperBounds.length) {
                    out.print(" & ");
                }
            }
        }
        else if (lowerBound != null) {
            out.print(" super ");
            this.printType(lowerBound, out);
        }
    }
    
    private void printParams(final MethodNode methodNode, final PrintWriter out) {
        out.print("(");
        final Parameter[] parameters = methodNode.getParameters();
        if (parameters != null && parameters.length != 0) {
            for (int i = 0; i != parameters.length; ++i) {
                this.printType(parameters[i].getType(), out);
                out.print(" ");
                out.print(parameters[i].getName());
                if (i + 1 < parameters.length) {
                    out.print(", ");
                }
            }
        }
        out.print(")");
    }
    
    private void printAnnotations(final PrintWriter out, final AnnotatedNode annotated) {
        if (!this.java5) {
            return;
        }
        for (final AnnotationNode annotation : annotated.getAnnotations()) {
            out.print("@" + annotation.getClassNode().getName() + "(");
            boolean first = true;
            final Map<String, Expression> members = annotation.getMembers();
            for (final String key : members.keySet()) {
                if (first) {
                    first = false;
                }
                else {
                    out.print(", ");
                }
                out.print(key + "=" + this.getAnnotationValue(members.get(key)));
            }
            out.print(") ");
        }
    }
    
    private String getAnnotationValue(final Object memberValue) {
        String val = "null";
        if (memberValue instanceof ListExpression) {
            final StringBuilder sb = new StringBuilder("{");
            boolean first = true;
            final ListExpression le = (ListExpression)memberValue;
            for (final Expression e : le.getExpressions()) {
                if (first) {
                    first = false;
                }
                else {
                    sb.append(",");
                }
                sb.append(this.getAnnotationValue(e));
            }
            sb.append("}");
            val = sb.toString();
        }
        else if (memberValue instanceof ConstantExpression) {
            final ConstantExpression ce = (ConstantExpression)memberValue;
            final Object constValue = ce.getValue();
            if (constValue instanceof Number || constValue instanceof Boolean) {
                val = constValue.toString();
            }
            else {
                val = "\"" + constValue + "\"";
            }
        }
        else if (memberValue instanceof PropertyExpression || memberValue instanceof VariableExpression) {
            val = ((Expression)memberValue).getText();
        }
        else if (memberValue instanceof ClassExpression) {
            val = ((Expression)memberValue).getText() + ".class";
        }
        return val;
    }
    
    private void printModifiers(final PrintWriter out, final int modifiers) {
        if ((modifiers & 0x1) != 0x0) {
            out.print("public ");
        }
        if ((modifiers & 0x4) != 0x0) {
            out.print("protected ");
        }
        if ((modifiers & 0x2) != 0x0) {
            out.print("private ");
        }
        if ((modifiers & 0x8) != 0x0) {
            out.print("static ");
        }
        if ((modifiers & 0x20) != 0x0) {
            out.print("synchronized ");
        }
        if ((modifiers & 0x400) != 0x0) {
            out.print("abstract ");
        }
    }
    
    private void genImports(final ClassNode classNode, final PrintWriter out) {
        final List<String> imports = new ArrayList<String>();
        final ModuleNode moduleNode = classNode.getModule();
        for (final ImportNode importNode : moduleNode.getStarImports()) {
            imports.add(importNode.getPackageName());
        }
        for (final ImportNode imp : moduleNode.getImports()) {
            if (imp.getAlias() == null) {
                imports.add(imp.getType().getName());
            }
        }
        imports.addAll(Arrays.asList(ResolveVisitor.DEFAULT_IMPORTS));
        for (final String imp2 : imports) {
            final String s = ("import " + imp2 + ((imp2.charAt(imp2.length() - 1) == '.') ? "*;" : ";")).replace('$', '.');
            out.println(s);
        }
        out.println();
    }
    
    public void clean() {
        for (final String path : this.toCompile) {
            new File(this.outputPath, path + ".java").delete();
        }
    }
}
