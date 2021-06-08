// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.runtime.MetaClassHelper;
import org.codehaus.groovy.classgen.BytecodeHelper;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.ParameterTypes;

public abstract class MetaMethod extends ParameterTypes implements Cloneable
{
    private String signature;
    private String mopName;
    
    public MetaMethod() {
    }
    
    public MetaMethod(final Class[] pt) {
        super(pt);
    }
    
    public abstract int getModifiers();
    
    public abstract String getName();
    
    public abstract Class getReturnType();
    
    public abstract CachedClass getDeclaringClass();
    
    public abstract Object invoke(final Object p0, final Object[] p1);
    
    public void checkParameters(final Class[] arguments) {
        if (!this.isValidMethod(arguments)) {
            throw new IllegalArgumentException("Parameters to method: " + this.getName() + " do not match types: " + InvokerHelper.toString(this.getParameterTypes()) + " for arguments: " + InvokerHelper.toString(arguments));
        }
    }
    
    public boolean isMethod(final MetaMethod method) {
        return this.getName().equals(method.getName()) && this.getModifiers() == method.getModifiers() && this.getReturnType().equals(method.getReturnType()) && equal(this.getParameterTypes(), method.getParameterTypes());
    }
    
    protected static boolean equal(final CachedClass[] a, final Class[] b) {
        if (a.length == b.length) {
            for (int i = 0, size = a.length; i < size; ++i) {
                if (!a[i].getTheClass().equals(b[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    protected static boolean equal(final CachedClass[] a, final CachedClass[] b) {
        if (a.length == b.length) {
            for (int i = 0, size = a.length; i < size; ++i) {
                if (a[i] != b[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return super.toString() + "[name: " + this.getName() + " params: " + InvokerHelper.toString(this.getParameterTypes()) + " returns: " + this.getReturnType() + " owner: " + this.getDeclaringClass() + "]";
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new GroovyRuntimeException("This should never happen", e);
        }
    }
    
    public boolean isStatic() {
        return (this.getModifiers() & 0x8) != 0x0;
    }
    
    public boolean isAbstract() {
        return (this.getModifiers() & 0x400) != 0x0;
    }
    
    public final boolean isPrivate() {
        return (this.getModifiers() & 0x2) != 0x0;
    }
    
    public final boolean isProtected() {
        return (this.getModifiers() & 0x4) != 0x0;
    }
    
    public final boolean isPublic() {
        return (this.getModifiers() & 0x1) != 0x0;
    }
    
    public final boolean isSame(final MetaMethod method) {
        return this.getName().equals(method.getName()) && compatibleModifiers(this.getModifiers(), method.getModifiers()) && this.getReturnType().equals(method.getReturnType()) && equal(this.getParameterTypes(), method.getParameterTypes());
    }
    
    private static boolean compatibleModifiers(final int modifiersA, final int modifiersB) {
        final int mask = 15;
        return (modifiersA & mask) == (modifiersB & mask);
    }
    
    public boolean isCacheable() {
        return true;
    }
    
    public String getDescriptor() {
        return BytecodeHelper.getMethodDescriptor(this.getReturnType(), this.getNativeParameterTypes());
    }
    
    public synchronized String getSignature() {
        if (this.signature == null) {
            final CachedClass[] parameters = this.getParameterTypes();
            final String name = this.getName();
            final StringBuffer buf = new StringBuffer(name.length() + parameters.length * 10);
            buf.append(this.getReturnType().getName());
            buf.append(' ');
            buf.append(name);
            buf.append('(');
            for (int i = 0; i < parameters.length; ++i) {
                if (i > 0) {
                    buf.append(", ");
                }
                buf.append(parameters[i].getName());
            }
            buf.append(')');
            this.signature = buf.toString();
        }
        return this.signature;
    }
    
    public String getMopName() {
        if (this.mopName == null) {
            final String name = this.getName();
            final CachedClass declaringClass = this.getDeclaringClass();
            if ((this.getModifiers() & 0x5) == 0x0) {
                this.mopName = new StringBuffer().append("this$").append(declaringClass.getSuperClassDistance()).append("$").append(name).toString();
            }
            else {
                this.mopName = new StringBuffer().append("super$").append(declaringClass.getSuperClassDistance()).append("$").append(name).toString();
            }
        }
        return this.mopName;
    }
    
    public final RuntimeException processDoMethodInvokeException(final Exception e, final Object object, final Object[] argumentArray) {
        if (e instanceof RuntimeException) {
            return (RuntimeException)e;
        }
        return MetaClassHelper.createExceptionText("failed to invoke method: ", this, object, argumentArray, e, true);
    }
    
    public Object doMethodInvoke(final Object object, Object[] argumentArray) {
        argumentArray = this.coerceArgumentsToClasses(argumentArray);
        try {
            return this.invoke(object, argumentArray);
        }
        catch (Exception e) {
            throw this.processDoMethodInvokeException(e, object, argumentArray);
        }
    }
}
