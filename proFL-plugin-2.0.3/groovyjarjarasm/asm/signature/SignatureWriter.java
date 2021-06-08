// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarasm.asm.signature;

public class SignatureWriter implements SignatureVisitor
{
    private final StringBuffer a;
    private boolean b;
    private boolean c;
    private int d;
    
    public SignatureWriter() {
        this.a = new StringBuffer();
    }
    
    public void visitFormalTypeParameter(final String str) {
        if (!this.b) {
            this.b = true;
            this.a.append('<');
        }
        this.a.append(str);
        this.a.append(':');
    }
    
    public SignatureVisitor visitClassBound() {
        return this;
    }
    
    public SignatureVisitor visitInterfaceBound() {
        this.a.append(':');
        return this;
    }
    
    public SignatureVisitor visitSuperclass() {
        this.a();
        return this;
    }
    
    public SignatureVisitor visitInterface() {
        return this;
    }
    
    public SignatureVisitor visitParameterType() {
        this.a();
        if (!this.c) {
            this.c = true;
            this.a.append('(');
        }
        return this;
    }
    
    public SignatureVisitor visitReturnType() {
        this.a();
        if (!this.c) {
            this.a.append('(');
        }
        this.a.append(')');
        return this;
    }
    
    public SignatureVisitor visitExceptionType() {
        this.a.append('^');
        return this;
    }
    
    public void visitBaseType(final char c) {
        this.a.append(c);
    }
    
    public void visitTypeVariable(final String str) {
        this.a.append('T');
        this.a.append(str);
        this.a.append(';');
    }
    
    public SignatureVisitor visitArrayType() {
        this.a.append('[');
        return this;
    }
    
    public void visitClassType(final String str) {
        this.a.append('L');
        this.a.append(str);
        this.d *= 2;
    }
    
    public void visitInnerClassType(final String str) {
        this.b();
        this.a.append('.');
        this.a.append(str);
        this.d *= 2;
    }
    
    public void visitTypeArgument() {
        if (this.d % 2 == 0) {
            ++this.d;
            this.a.append('<');
        }
        this.a.append('*');
    }
    
    public SignatureVisitor visitTypeArgument(final char c) {
        if (this.d % 2 == 0) {
            ++this.d;
            this.a.append('<');
        }
        if (c != '=') {
            this.a.append(c);
        }
        return this;
    }
    
    public void visitEnd() {
        this.b();
        this.a.append(';');
    }
    
    public String toString() {
        return this.a.toString();
    }
    
    private void a() {
        if (this.b) {
            this.b = false;
            this.a.append('>');
        }
    }
    
    private void b() {
        if (this.d % 2 != 0) {
            this.a.append('>');
        }
        this.d /= 2;
    }
}
