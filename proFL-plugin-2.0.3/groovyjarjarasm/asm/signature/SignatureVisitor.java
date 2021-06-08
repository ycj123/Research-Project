// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarasm.asm.signature;

public interface SignatureVisitor
{
    public static final char EXTENDS = '+';
    public static final char SUPER = '-';
    public static final char INSTANCEOF = '=';
    
    void visitFormalTypeParameter(final String p0);
    
    SignatureVisitor visitClassBound();
    
    SignatureVisitor visitInterfaceBound();
    
    SignatureVisitor visitSuperclass();
    
    SignatureVisitor visitInterface();
    
    SignatureVisitor visitParameterType();
    
    SignatureVisitor visitReturnType();
    
    SignatureVisitor visitExceptionType();
    
    void visitBaseType(final char p0);
    
    void visitTypeVariable(final String p0);
    
    SignatureVisitor visitArrayType();
    
    void visitClassType(final String p0);
    
    void visitInnerClassType(final String p0);
    
    void visitTypeArgument();
    
    SignatureVisitor visitTypeArgument(final char p0);
    
    void visitEnd();
}
