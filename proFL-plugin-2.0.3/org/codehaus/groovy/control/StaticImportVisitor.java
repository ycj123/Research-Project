// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

import org.codehaus.groovy.ast.expr.EmptyExpression;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.runtime.MetaClassHelper;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import java.util.List;
import org.codehaus.groovy.ast.expr.NamedArgumentListExpression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.DynamicVariable;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.ast.ImportNode;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.expr.AnnotationConstantExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.ExpressionTransformer;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ClassCodeExpressionTransformer;

public class StaticImportVisitor extends ClassCodeExpressionTransformer
{
    private ClassNode currentClass;
    private MethodNode currentMethod;
    private SourceUnit source;
    private CompilationUnit compilationUnit;
    private boolean stillResolving;
    private boolean inSpecialConstructorCall;
    private boolean inClosure;
    private boolean inPropertyExpression;
    private Expression foundConstant;
    private Expression foundArgs;
    private boolean inAnnotation;
    private boolean inLeftExpression;
    
    public StaticImportVisitor(final CompilationUnit cu) {
        this.compilationUnit = cu;
    }
    
    public void visitClass(final ClassNode node, final SourceUnit source) {
        this.currentClass = node;
        this.source = source;
        super.visitClass(node);
    }
    
    @Override
    protected void visitConstructorOrMethod(final MethodNode node, final boolean isConstructor) {
        super.visitConstructorOrMethod(this.currentMethod = node, isConstructor);
        this.currentMethod = null;
    }
    
    @Override
    public void visitAnnotations(final AnnotatedNode node) {
        final boolean oldInAnnotation = this.inAnnotation;
        this.inAnnotation = true;
        super.visitAnnotations(node);
        this.inAnnotation = oldInAnnotation;
    }
    
    @Override
    public Expression transform(final Expression exp) {
        if (exp == null) {
            return null;
        }
        if (exp.getClass() == VariableExpression.class) {
            return this.transformVariableExpression((VariableExpression)exp);
        }
        if (exp.getClass() == BinaryExpression.class) {
            return this.transformBinaryExpression((BinaryExpression)exp);
        }
        if (exp.getClass() == PropertyExpression.class) {
            return this.transformPropertyExpression((PropertyExpression)exp);
        }
        if (exp.getClass() == MethodCallExpression.class) {
            return this.transformMethodCallExpression((MethodCallExpression)exp);
        }
        if (exp.getClass() == ClosureExpression.class) {
            return this.transformClosureExpression((ClosureExpression)exp);
        }
        if (exp.getClass() == ConstructorCallExpression.class) {
            return this.transformConstructorCallExpression((ConstructorCallExpression)exp);
        }
        if (exp.getClass() == ArgumentListExpression.class) {
            final Expression result = exp.transformExpression(this);
            if (this.inPropertyExpression) {
                this.foundArgs = result;
            }
            return result;
        }
        if (exp instanceof ConstantExpression) {
            final Expression result = exp.transformExpression(this);
            if (this.inPropertyExpression) {
                this.foundConstant = result;
            }
            if (this.inAnnotation && exp instanceof AnnotationConstantExpression) {
                final ConstantExpression ce = (ConstantExpression)result;
                if (ce.getValue() instanceof AnnotationNode) {
                    final AnnotationNode an = (AnnotationNode)ce.getValue();
                    final Map<String, Expression> attributes = an.getMembers();
                    for (final Map.Entry entry : attributes.entrySet()) {
                        final Expression attrExpr = this.transform(entry.getValue());
                        entry.setValue(attrExpr);
                    }
                }
            }
            return result;
        }
        return exp.transformExpression(this);
    }
    
    private Expression transformMapEntryExpression(final MapEntryExpression me, final ClassNode constructorCallType) {
        final Expression key = me.getKeyExpression();
        final Expression value = me.getValueExpression();
        final ModuleNode module = this.currentClass.getModule();
        if (module != null && key instanceof ConstantExpression) {
            final Map<String, ImportNode> importNodes = module.getStaticImports();
            if (importNodes.containsKey(key.getText())) {
                final ImportNode importNode = importNodes.get(key.getText());
                if (importNode.getType().equals(constructorCallType)) {
                    final String newKey = importNode.getFieldName();
                    return new MapEntryExpression(new ConstantExpression(newKey), value.transformExpression(this));
                }
            }
        }
        return me;
    }
    
    protected Expression transformBinaryExpression(final BinaryExpression be) {
        final int type = be.getOperation().getType();
        final Expression right = this.transform(be.getRightExpression());
        be.setRightExpression(right);
        Expression left;
        if (type == 100) {
            final boolean oldInLeftExpression = this.inLeftExpression;
            this.inLeftExpression = true;
            left = this.transform(be.getLeftExpression());
            this.inLeftExpression = oldInLeftExpression;
            if (left instanceof StaticMethodCallExpression) {
                final StaticMethodCallExpression smce = (StaticMethodCallExpression)left;
                final StaticMethodCallExpression result = new StaticMethodCallExpression(smce.getOwnerType(), smce.getMethod(), right);
                this.setSourcePosition(result, be);
                return result;
            }
        }
        else {
            left = this.transform(be.getLeftExpression());
        }
        be.setLeftExpression(left);
        return be;
    }
    
    protected Expression transformVariableExpression(final VariableExpression ve) {
        final Variable v = ve.getAccessedVariable();
        if (v != null && v instanceof DynamicVariable) {
            Expression result = this.findStaticFieldOrPropAccessorImportFromModule(v.getName());
            if (result != null) {
                this.setSourcePosition(result, ve);
                if (this.inAnnotation) {
                    result = this.transformInlineConstants(result);
                }
                return result;
            }
            if (!this.inPropertyExpression || this.inSpecialConstructorCall) {
                this.addStaticVariableError(ve);
            }
        }
        return ve;
    }
    
    private void setSourcePosition(final Expression toSet, final Expression origNode) {
        toSet.setSourcePosition(origNode);
        if (toSet instanceof PropertyExpression) {
            ((PropertyExpression)toSet).getProperty().setSourcePosition(origNode);
        }
    }
    
    private Expression transformInlineConstants(final Expression exp) {
        if (exp instanceof PropertyExpression) {
            final PropertyExpression pe = (PropertyExpression)exp;
            if (pe.getObjectExpression() instanceof ClassExpression) {
                final ClassExpression ce = (ClassExpression)pe.getObjectExpression();
                final ClassNode type = ce.getType();
                if (type.isEnum()) {
                    return exp;
                }
                final Expression constant = this.findConstant(type.getField(pe.getPropertyAsString()));
                if (constant != null) {
                    return constant;
                }
            }
        }
        else if (exp instanceof ListExpression) {
            final ListExpression le = (ListExpression)exp;
            final ListExpression result = new ListExpression();
            for (final Expression e : le.getExpressions()) {
                result.addExpression(this.transformInlineConstants(e));
            }
            return result;
        }
        return exp;
    }
    
    private Expression findConstant(final FieldNode fn) {
        if (fn != null && !fn.isEnum() && fn.isStatic() && fn.isFinal() && fn.getInitialValueExpression() instanceof ConstantExpression) {
            return fn.getInitialValueExpression();
        }
        return null;
    }
    
    protected Expression transformMethodCallExpression(final MethodCallExpression mce) {
        final Expression args = this.transform(mce.getArguments());
        final Expression method = this.transform(mce.getMethod());
        final Expression object = this.transform(mce.getObjectExpression());
        boolean isExplicitThisOrSuper = false;
        if (object instanceof VariableExpression) {
            final VariableExpression ve = (VariableExpression)object;
            isExplicitThisOrSuper = (!mce.isImplicitThis() && (ve.getName().equals("this") || ve.getName().equals("super")));
            final boolean isExplicitSuper = ve.getName().equals("super");
            if (isExplicitSuper && this.currentMethod != null && this.currentMethod.isStatic()) {
                this.addError("'super' cannot be used in a static context, use the explicit class instead.", mce);
                return mce;
            }
        }
        if (mce.isImplicitThis() || isExplicitThisOrSuper) {
            if (mce.isImplicitThis()) {
                Expression ret = this.findStaticMethodImportFromModule(method, args);
                if (ret != null) {
                    this.setSourcePosition(ret, mce);
                    return ret;
                }
                if (method instanceof ConstantExpression && !this.inLeftExpression) {
                    final String methodName = (String)((ConstantExpression)method).getValue();
                    ret = this.findStaticFieldOrPropAccessorImportFromModule(methodName);
                    if (ret != null) {
                        ret = new MethodCallExpression(ret, "call", args);
                        this.setSourcePosition(ret, mce);
                        return ret;
                    }
                }
            }
            if (method instanceof ConstantExpression) {
                final ConstantExpression ce = (ConstantExpression)method;
                final Object value = ce.getValue();
                if (value instanceof String) {
                    final String methodName2 = (String)value;
                    boolean lookForPossibleStaticMethod = !methodName2.equals("call");
                    if (this.currentMethod != null && !this.currentMethod.isStatic() && this.currentClass.hasPossibleMethod(methodName2, args)) {
                        lookForPossibleStaticMethod = false;
                    }
                    if (this.inSpecialConstructorCall || (lookForPossibleStaticMethod && this.currentClass.hasPossibleStaticMethod(methodName2, args))) {
                        final StaticMethodCallExpression smce = new StaticMethodCallExpression(this.currentClass, methodName2, args);
                        this.setSourcePosition(smce, mce);
                        return smce;
                    }
                }
            }
        }
        final MethodCallExpression result = new MethodCallExpression(object, method, args);
        result.setSafe(mce.isSafe());
        result.setImplicitThis(mce.isImplicitThis());
        result.setSpreadSafe(mce.isSpreadSafe());
        this.setSourcePosition(result, mce);
        return result;
    }
    
    protected Expression transformConstructorCallExpression(final ConstructorCallExpression cce) {
        this.inSpecialConstructorCall = cce.isSpecialCall();
        Expression expression = cce.getArguments();
        if (expression instanceof TupleExpression) {
            final TupleExpression tuple = (TupleExpression)expression;
            if (tuple.getExpressions().size() == 1) {
                expression = tuple.getExpression(0);
                if (expression instanceof NamedArgumentListExpression) {
                    final NamedArgumentListExpression namedArgs = (NamedArgumentListExpression)expression;
                    final List<MapEntryExpression> entryExpressions = namedArgs.getMapEntryExpressions();
                    for (int i = 0; i < entryExpressions.size(); ++i) {
                        entryExpressions.set(i, (MapEntryExpression)this.transformMapEntryExpression(entryExpressions.get(i), cce.getType()));
                    }
                }
            }
        }
        final Expression ret = cce.transformExpression(this);
        this.inSpecialConstructorCall = false;
        return ret;
    }
    
    protected Expression transformClosureExpression(final ClosureExpression ce) {
        final boolean oldInClosure = this.inClosure;
        this.inClosure = true;
        final Statement code = ce.getCode();
        if (code != null) {
            code.visit(this);
        }
        this.inClosure = oldInClosure;
        return ce;
    }
    
    protected Expression transformPropertyExpression(final PropertyExpression pe) {
        final boolean oldInPropertyExpression = this.inPropertyExpression;
        final Expression oldFoundArgs = this.foundArgs;
        final Expression oldFoundConstant = this.foundConstant;
        this.inPropertyExpression = true;
        this.foundArgs = null;
        this.foundConstant = null;
        Expression objectExpression = this.transform(pe.getObjectExpression());
        if (objectExpression instanceof VariableExpression) {
            final VariableExpression ve = (VariableExpression)objectExpression;
            final boolean isExplicitSuper = ve.getName().equals("super");
            if (isExplicitSuper && this.currentMethod != null && this.currentMethod.isStatic()) {
                this.addError("'super' cannot be used in a static context, use the explicit class instead.", pe);
                return null;
            }
        }
        if (this.foundArgs != null && this.foundConstant != null) {
            final Expression result = this.findStaticMethodImportFromModule(this.foundConstant, this.foundArgs);
            if (result != null) {
                objectExpression = result;
            }
        }
        this.inPropertyExpression = oldInPropertyExpression;
        this.foundArgs = oldFoundArgs;
        this.foundConstant = oldFoundConstant;
        pe.setObjectExpression(objectExpression);
        if (!this.inSpecialConstructorCall) {
            this.checkStaticScope(pe);
        }
        return pe;
    }
    
    private void checkStaticScope(final PropertyExpression pe) {
        if (this.inClosure) {
            return;
        }
        for (Expression it = pe; it != null; it = ((PropertyExpression)it).getObjectExpression()) {
            if (!(it instanceof PropertyExpression)) {
                if (it instanceof VariableExpression) {
                    this.addStaticVariableError((VariableExpression)it);
                }
                return;
            }
        }
    }
    
    private void addStaticVariableError(final VariableExpression ve) {
        if (!this.inSpecialConstructorCall && (this.inClosure || !ve.isInStaticContext())) {
            return;
        }
        if (this.stillResolving) {
            return;
        }
        if (ve.isThisExpression() || ve.isSuperExpression()) {
            return;
        }
        if (this.currentMethod != null && this.currentMethod.isStatic()) {
            final FieldNode fieldNode = this.currentMethod.getDeclaringClass().getField(ve.getName());
            if (fieldNode != null && fieldNode.isStatic()) {
                return;
            }
        }
        final Variable v = ve.getAccessedVariable();
        if (v != null && !(v instanceof DynamicVariable) && v.isInStaticContext()) {
            return;
        }
        this.addError("Apparent variable '" + ve.getName() + "' was found in a static scope but doesn't refer" + " to a local variable, static field or class. Possible causes:\n" + "You attempted to reference a variable in the binding or an instance variable from a static context.\n" + "You misspelled a classname or statically imported field. Please check the spelling.\n" + "You attempted to use a method '" + ve.getName() + "' but left out brackets in a place not allowed by the grammar.", ve);
    }
    
    private Expression findStaticFieldOrPropAccessorImportFromModule(final String name) {
        final ModuleNode module = this.currentClass.getModule();
        if (module == null) {
            return null;
        }
        final Map<String, ImportNode> importNodes = module.getStaticImports();
        this.stillResolving = false;
        String accessorName = this.getAccessorName(name);
        if (importNodes.containsKey(accessorName)) {
            final ImportNode importNode = importNodes.get(accessorName);
            Expression expression = this.findStaticPropertyAccessorByFullName(importNode.getType(), importNode.getFieldName());
            if (expression != null) {
                return expression;
            }
            expression = this.findStaticPropertyAccessor(importNode.getType(), this.getPropNameForAccessor(importNode.getFieldName()));
            if (expression != null) {
                return expression;
            }
        }
        if (accessorName.startsWith("get")) {
            accessorName = "is" + accessorName.substring(3);
            if (importNodes.containsKey(accessorName)) {
                final ImportNode importNode = importNodes.get(accessorName);
                Expression expression = this.findStaticPropertyAccessorByFullName(importNode.getType(), importNode.getFieldName());
                if (expression != null) {
                    return expression;
                }
                expression = this.findStaticPropertyAccessor(importNode.getType(), this.getPropNameForAccessor(importNode.getFieldName()));
                if (expression != null) {
                    return expression;
                }
            }
        }
        if (importNodes.containsKey(name)) {
            final ImportNode importNode = importNodes.get(name);
            Expression expression = this.findStaticPropertyAccessor(importNode.getType(), importNode.getFieldName());
            if (expression != null) {
                return expression;
            }
            expression = this.findStaticField(importNode.getType(), importNode.getFieldName());
            if (expression != null) {
                return expression;
            }
        }
        for (final ImportNode importNode2 : module.getStaticStarImports().values()) {
            final ClassNode node = importNode2.getType();
            Expression expression = this.findStaticPropertyAccessor(node, name);
            if (expression != null) {
                return expression;
            }
            expression = this.findStaticField(node, name);
            if (expression != null) {
                return expression;
            }
        }
        return null;
    }
    
    private Expression findStaticMethodImportFromModule(final Expression method, final Expression args) {
        final ModuleNode module = this.currentClass.getModule();
        if (module == null || !(method instanceof ConstantExpression)) {
            return null;
        }
        final Map<String, ImportNode> importNodes = module.getStaticImports();
        final ConstantExpression ce = (ConstantExpression)method;
        final Object value = ce.getValue();
        if (!(value instanceof String)) {
            return null;
        }
        final String name = (String)value;
        if (importNodes.containsKey(name)) {
            final ImportNode importNode = importNodes.get(name);
            Expression expression = this.findStaticMethod(importNode.getType(), importNode.getFieldName(), args);
            if (expression != null) {
                return expression;
            }
            expression = this.findStaticPropertyAccessorGivenArgs(importNode.getType(), this.getPropNameForAccessor(importNode.getFieldName()), args);
            if (expression != null) {
                return new StaticMethodCallExpression(importNode.getType(), importNode.getFieldName(), args);
            }
        }
        if (this.validPropName(name)) {
            final String propName = this.getPropNameForAccessor(name);
            if (importNodes.containsKey(propName)) {
                final ImportNode importNode2 = importNodes.get(propName);
                Expression expression = this.findStaticMethod(importNode2.getType(), this.prefix(name) + MetaClassHelper.capitalize(importNode2.getFieldName()), args);
                if (expression != null) {
                    return expression;
                }
                expression = this.findStaticPropertyAccessorGivenArgs(importNode2.getType(), importNode2.getFieldName(), args);
                if (expression != null) {
                    return new StaticMethodCallExpression(importNode2.getType(), this.prefix(name) + MetaClassHelper.capitalize(importNode2.getFieldName()), args);
                }
            }
        }
        final Map<String, ImportNode> starImports = module.getStaticStarImports();
        if (this.isEnum(this.currentClass) && starImports.containsKey(this.currentClass.getName())) {
            final ImportNode importNode3 = starImports.get(this.currentClass.getName());
            final ClassNode starImportType = (importNode3 == null) ? null : importNode3.getType();
            final Expression expression = this.findStaticMethod(starImportType, name, args);
            if (expression != null) {
                return expression;
            }
        }
        else {
            for (final ImportNode importNode4 : starImports.values()) {
                final ClassNode starImportType = (importNode4 == null) ? null : importNode4.getType();
                Expression expression = this.findStaticMethod(starImportType, name, args);
                if (expression != null) {
                    return expression;
                }
                expression = this.findStaticPropertyAccessorGivenArgs(starImportType, this.getPropNameForAccessor(name), args);
                if (expression != null) {
                    return new StaticMethodCallExpression(starImportType, name, args);
                }
            }
        }
        return null;
    }
    
    private String prefix(final String name) {
        return name.startsWith("is") ? "is" : name.substring(0, 3);
    }
    
    private String getPropNameForAccessor(final String fieldName) {
        final int prefixLength = fieldName.startsWith("is") ? 2 : 3;
        if (fieldName.length() < prefixLength + 1) {
            return fieldName;
        }
        if (!this.validPropName(fieldName)) {
            return fieldName;
        }
        return String.valueOf(fieldName.charAt(prefixLength)).toLowerCase() + fieldName.substring(prefixLength + 1);
    }
    
    private boolean validPropName(final String propName) {
        return propName.startsWith("get") || propName.startsWith("is") || propName.startsWith("set");
    }
    
    private String getAccessorName(final String name) {
        return (this.inLeftExpression ? "set" : "get") + MetaClassHelper.capitalize(name);
    }
    
    private Expression findStaticPropertyAccessorGivenArgs(final ClassNode staticImportType, final String propName, final Expression args) {
        return this.findStaticPropertyAccessor(staticImportType, propName);
    }
    
    private Expression findStaticPropertyAccessor(final ClassNode staticImportType, final String propName) {
        final String accessorName = this.getAccessorName(propName);
        Expression accessor = this.findStaticPropertyAccessorByFullName(staticImportType, accessorName);
        if (accessor == null && accessorName.startsWith("get")) {
            accessor = this.findStaticPropertyAccessorByFullName(staticImportType, "is" + accessorName.substring(3));
        }
        if (accessor == null && this.hasStaticProperty(staticImportType, propName)) {
            if (this.inLeftExpression) {
                accessor = new StaticMethodCallExpression(staticImportType, accessorName, ArgumentListExpression.EMPTY_ARGUMENTS);
            }
            else {
                accessor = new PropertyExpression(new ClassExpression(staticImportType), propName);
            }
        }
        return accessor;
    }
    
    private boolean hasStaticProperty(final ClassNode staticImportType, final String propName) {
        for (ClassNode classNode = staticImportType; classNode != null; classNode = classNode.getSuperClass()) {
            for (final PropertyNode pn : classNode.getProperties()) {
                if (pn.getName().equals(propName) && pn.isStatic()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private Expression findStaticPropertyAccessorByFullName(final ClassNode staticImportType, final String accessorMethodName) {
        final ArgumentListExpression dummyArgs = new ArgumentListExpression();
        dummyArgs.addExpression(new EmptyExpression());
        return this.findStaticMethod(staticImportType, accessorMethodName, this.inLeftExpression ? dummyArgs : ArgumentListExpression.EMPTY_ARGUMENTS);
    }
    
    private Expression findStaticField(final ClassNode staticImportType, final String fieldName) {
        if (staticImportType.isPrimaryClassNode() || staticImportType.isResolved()) {
            staticImportType.getFields();
            final FieldNode field = staticImportType.getField(fieldName);
            if (field != null && field.isStatic()) {
                return new PropertyExpression(new ClassExpression(staticImportType), fieldName);
            }
        }
        else {
            this.stillResolving = true;
        }
        return null;
    }
    
    private Expression findStaticMethod(final ClassNode staticImportType, final String methodName, final Expression args) {
        if ((staticImportType.isPrimaryClassNode() || staticImportType.isResolved()) && staticImportType.hasPossibleStaticMethod(methodName, args)) {
            return new StaticMethodCallExpression(staticImportType, methodName, args);
        }
        return null;
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        return this.source;
    }
    
    private boolean isEnum(final ClassNode node) {
        return (node.getModifiers() & 0x4000) != 0x0;
    }
}
