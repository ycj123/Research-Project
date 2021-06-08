// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import java.util.List;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import groovyjarjarasm.asm.Opcodes;

public class MethodNode extends AnnotatedNode implements Opcodes
{
    private final String name;
    private int modifiers;
    private ClassNode returnType;
    private Parameter[] parameters;
    private boolean hasDefaultValue;
    private Statement code;
    private boolean dynamicReturnType;
    private VariableScope variableScope;
    private final ClassNode[] exceptions;
    private final boolean staticConstructor;
    private GenericsType[] genericsTypes;
    private boolean hasDefault;
    String typeDescriptor;
    
    public MethodNode(final String name, final int modifiers, final ClassNode returnType, final Parameter[] parameters, final ClassNode[] exceptions, final Statement code) {
        this.hasDefaultValue = false;
        this.genericsTypes = null;
        this.name = name;
        this.modifiers = modifiers;
        this.code = code;
        this.setReturnType(returnType);
        final VariableScope scope = new VariableScope();
        this.setVariableScope(scope);
        this.setParameters(parameters);
        this.hasDefault = false;
        this.exceptions = exceptions;
        this.staticConstructor = (name != null && name.equals("<clinit>"));
    }
    
    public String getTypeDescriptor() {
        if (this.typeDescriptor == null) {
            final StringBuffer buf = new StringBuffer(this.name.length() + this.parameters.length * 10);
            buf.append(this.returnType.getName());
            buf.append(' ');
            buf.append(this.name);
            buf.append('(');
            for (int i = 0; i < this.parameters.length; ++i) {
                if (i > 0) {
                    buf.append(", ");
                }
                final Parameter param = this.parameters[i];
                buf.append(param.getType().getName());
            }
            buf.append(')');
            this.typeDescriptor = buf.toString();
        }
        return this.typeDescriptor;
    }
    
    private void invalidateCachedData() {
        this.typeDescriptor = null;
    }
    
    public boolean isVoidMethod() {
        return this.returnType == ClassHelper.VOID_TYPE;
    }
    
    public Statement getCode() {
        return this.code;
    }
    
    public void setCode(final Statement code) {
        this.code = code;
    }
    
    public int getModifiers() {
        return this.modifiers;
    }
    
    public void setModifiers(final int modifiers) {
        this.invalidateCachedData();
        this.modifiers = modifiers;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Parameter[] getParameters() {
        return this.parameters;
    }
    
    public void setParameters(final Parameter[] parameters) {
        this.invalidateCachedData();
        final VariableScope scope = new VariableScope();
        this.parameters = parameters;
        if (parameters != null && parameters.length > 0) {
            for (final Parameter para : parameters) {
                if (para.hasInitialExpression()) {
                    this.hasDefaultValue = true;
                }
                para.setInStaticContext(this.isStatic());
                scope.putDeclaredVariable(para);
            }
        }
        this.setVariableScope(scope);
    }
    
    public ClassNode getReturnType() {
        return this.returnType;
    }
    
    public VariableScope getVariableScope() {
        return this.variableScope;
    }
    
    public void setVariableScope(final VariableScope variableScope) {
        (this.variableScope = variableScope).setInStaticContext(this.isStatic());
    }
    
    public boolean isDynamicReturnType() {
        return this.dynamicReturnType;
    }
    
    public boolean isAbstract() {
        return (this.modifiers & 0x400) != 0x0;
    }
    
    public boolean isStatic() {
        return (this.modifiers & 0x8) != 0x0;
    }
    
    public boolean isPublic() {
        return (this.modifiers & 0x1) != 0x0;
    }
    
    public boolean isPrivate() {
        return (this.modifiers & 0x2) != 0x0;
    }
    
    public boolean isProtected() {
        return (this.modifiers & 0x4) != 0x0;
    }
    
    public boolean hasDefaultValue() {
        return this.hasDefaultValue;
    }
    
    @Override
    public String toString() {
        return "MethodNode@" + this.hashCode() + "[" + this.getTypeDescriptor() + "]";
    }
    
    public void setReturnType(final ClassNode returnType) {
        this.invalidateCachedData();
        this.dynamicReturnType |= (ClassHelper.DYNAMIC_TYPE == returnType);
        this.returnType = returnType;
        if (returnType == null) {
            this.returnType = ClassHelper.OBJECT_TYPE;
        }
    }
    
    public ClassNode[] getExceptions() {
        return this.exceptions;
    }
    
    public Statement getFirstStatement() {
        if (this.code == null) {
            return null;
        }
        Statement first = this.code;
        while (first instanceof BlockStatement) {
            final List<Statement> list = ((BlockStatement)first).getStatements();
            if (list.isEmpty()) {
                first = null;
            }
            else {
                first = list.get(0);
            }
        }
        return first;
    }
    
    public GenericsType[] getGenericsTypes() {
        return this.genericsTypes;
    }
    
    public void setGenericsTypes(final GenericsType[] genericsTypes) {
        this.invalidateCachedData();
        this.genericsTypes = genericsTypes;
    }
    
    public void setAnnotationDefault(final boolean b) {
        this.hasDefault = b;
    }
    
    public boolean hasAnnotationDefault() {
        return this.hasDefault;
    }
    
    public boolean isStaticConstructor() {
        return this.staticConstructor;
    }
}
