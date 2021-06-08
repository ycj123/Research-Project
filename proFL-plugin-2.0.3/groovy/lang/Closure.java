// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.io.StringWriter;
import java.io.IOException;
import java.io.Writer;
import org.codehaus.groovy.runtime.CurriedClosure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.reflection.stdclasses.CachedClosureClass;
import java.io.Serializable;

public abstract class Closure extends GroovyObjectSupport implements Cloneable, Runnable, Serializable
{
    public static final int OWNER_FIRST = 0;
    public static final int DELEGATE_FIRST = 1;
    public static final int OWNER_ONLY = 2;
    public static final int DELEGATE_ONLY = 3;
    public static final int TO_SELF = 4;
    public static final int DONE = 1;
    public static final int SKIP = 2;
    private static final Object[] EMPTY_OBJECT_ARRAY;
    private Object delegate;
    private Object owner;
    private Object thisObject;
    private int resolveStrategy;
    private int directive;
    protected Class[] parameterTypes;
    protected int maximumNumberOfParameters;
    private static final long serialVersionUID = 4368710879820278874L;
    
    public Closure(final Object owner, final Object thisObject) {
        this.resolveStrategy = 0;
        this.owner = owner;
        this.delegate = owner;
        this.thisObject = thisObject;
        final CachedClosureClass cachedClass = (CachedClosureClass)ReflectionCache.getCachedClass(this.getClass());
        this.parameterTypes = cachedClass.getParameterTypes();
        this.maximumNumberOfParameters = cachedClass.getMaximumNumberOfParameters();
    }
    
    public Closure(final Object owner) {
        this(owner, null);
    }
    
    public void setResolveStrategy(final int resolveStrategy) {
        this.resolveStrategy = resolveStrategy;
    }
    
    public int getResolveStrategy() {
        return this.resolveStrategy;
    }
    
    public Object getThisObject() {
        return this.thisObject;
    }
    
    @Override
    public Object getProperty(final String property) {
        if ("delegate".equals(property)) {
            return this.getDelegate();
        }
        if ("owner".equals(property)) {
            return this.getOwner();
        }
        if ("maximumNumberOfParameters".equals(property)) {
            return this.getMaximumNumberOfParameters();
        }
        if ("parameterTypes".equals(property)) {
            return this.getParameterTypes();
        }
        if ("metaClass".equals(property)) {
            return this.getMetaClass();
        }
        if ("class".equals(property)) {
            return this.getClass();
        }
        if ("directive".equals(property)) {
            return this.getDirective();
        }
        switch (this.resolveStrategy) {
            case 1: {
                return this.getPropertyDelegateFirst(property);
            }
            case 3: {
                return InvokerHelper.getProperty(this.delegate, property);
            }
            case 2: {
                return InvokerHelper.getProperty(this.owner, property);
            }
            case 4: {
                return super.getProperty(property);
            }
            default: {
                return this.getPropertyOwnerFirst(property);
            }
        }
    }
    
    private Object getPropertyDelegateFirst(final String property) {
        if (this.delegate == null) {
            return this.getPropertyOwnerFirst(property);
        }
        return this.getPropertyTryThese(property, this.delegate, this.owner);
    }
    
    private Object getPropertyOwnerFirst(final String property) {
        return this.getPropertyTryThese(property, this.owner, this.delegate);
    }
    
    private Object getPropertyTryThese(final String property, final Object firstTry, final Object secondTry) {
        try {
            return InvokerHelper.getProperty(firstTry, property);
        }
        catch (MissingPropertyException e1) {
            if (secondTry != null && firstTry != this && firstTry != secondTry) {
                try {
                    return InvokerHelper.getProperty(secondTry, property);
                }
                catch (GroovyRuntimeException ex) {}
            }
            throw e1;
        }
    }
    
    @Override
    public void setProperty(final String property, final Object newValue) {
        if ("delegate".equals(property)) {
            this.setDelegate(newValue);
        }
        else if ("metaClass".equals(property)) {
            this.setMetaClass((MetaClass)newValue);
        }
        else if ("resolveStrategy".equals(property)) {
            this.setResolveStrategy(((Number)newValue).intValue());
        }
        else {
            switch (this.resolveStrategy) {
                case 1: {
                    this.setPropertyDelegateFirst(property, newValue);
                    break;
                }
                case 3: {
                    InvokerHelper.setProperty(this.delegate, property, newValue);
                    break;
                }
                case 2: {
                    InvokerHelper.setProperty(this.owner, property, newValue);
                    break;
                }
                case 4: {
                    super.setProperty(property, newValue);
                    break;
                }
                default: {
                    this.setPropertyOwnerFirst(property, newValue);
                    break;
                }
            }
        }
    }
    
    private void setPropertyDelegateFirst(final String property, final Object newValue) {
        if (this.delegate == null) {
            this.setPropertyOwnerFirst(property, newValue);
        }
        else {
            this.setPropertyTryThese(property, newValue, this.delegate, this.owner);
        }
    }
    
    private void setPropertyOwnerFirst(final String property, final Object newValue) {
        this.setPropertyTryThese(property, newValue, this.owner, this.delegate);
    }
    
    private void setPropertyTryThese(final String property, final Object newValue, final Object firstTry, final Object secondTry) {
        try {
            InvokerHelper.setProperty(firstTry, property, newValue);
        }
        catch (GroovyRuntimeException e1) {
            if (firstTry != null && firstTry != this && firstTry != secondTry) {
                try {
                    InvokerHelper.setProperty(secondTry, property, newValue);
                    return;
                }
                catch (GroovyRuntimeException ex) {}
            }
            throw e1;
        }
    }
    
    public boolean isCase(final Object candidate) {
        return DefaultTypeTransformation.castToBoolean(this.call(candidate));
    }
    
    public Object call() {
        final Object[] NOARGS = Closure.EMPTY_OBJECT_ARRAY;
        return this.call(NOARGS);
    }
    
    public Object call(final Object[] args) {
        try {
            return this.getMetaClass().invokeMethod(this, "doCall", args);
        }
        catch (Exception e) {
            return throwRuntimeException(e);
        }
    }
    
    public Object call(final Object arguments) {
        return this.call(new Object[] { arguments });
    }
    
    protected static Object throwRuntimeException(final Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException)throwable;
        }
        throw new GroovyRuntimeException(throwable.getMessage(), throwable);
    }
    
    public Object getOwner() {
        return this.owner;
    }
    
    public Object getDelegate() {
        return this.delegate;
    }
    
    public void setDelegate(final Object delegate) {
        this.delegate = delegate;
    }
    
    public Class[] getParameterTypes() {
        return this.parameterTypes;
    }
    
    public int getMaximumNumberOfParameters() {
        return this.maximumNumberOfParameters;
    }
    
    public Closure asWritable() {
        return new WritableClosure();
    }
    
    public void run() {
        this.call();
    }
    
    public Closure curry(final Object[] arguments) {
        return new CurriedClosure(this, arguments);
    }
    
    public Closure rcurry(final Object[] arguments) {
        return new CurriedClosure(-arguments.length, this, arguments);
    }
    
    public Closure ncurry(final int n, final Object[] arguments) {
        return new CurriedClosure(n, this, arguments);
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }
    
    public int getDirective() {
        return this.directive;
    }
    
    public void setDirective(final int directive) {
        this.directive = directive;
    }
    
    static {
        EMPTY_OBJECT_ARRAY = new Object[0];
    }
    
    private class WritableClosure extends Closure implements Writable
    {
        public WritableClosure() {
            super(Closure.this);
        }
        
        public Writer writeTo(final Writer out) throws IOException {
            Closure.this.call(new Object[] { out });
            return out;
        }
        
        @Override
        public Object invokeMethod(final String method, final Object arguments) {
            if ("clone".equals(method)) {
                return this.clone();
            }
            if ("curry".equals(method)) {
                return this.curry((Object[])arguments);
            }
            if ("asWritable".equals(method)) {
                return this.asWritable();
            }
            return Closure.this.invokeMethod(method, arguments);
        }
        
        @Override
        public Object getProperty(final String property) {
            return Closure.this.getProperty(property);
        }
        
        @Override
        public void setProperty(final String property, final Object newValue) {
            Closure.this.setProperty(property, newValue);
        }
        
        @Override
        public Object call() {
            return ((Closure)this.getOwner()).call();
        }
        
        @Override
        public Object call(final Object arguments) {
            return ((Closure)this.getOwner()).call(arguments);
        }
        
        @Override
        public Object call(final Object[] args) {
            return ((Closure)this.getOwner()).call(args);
        }
        
        public Object doCall(final Object[] args) {
            return this.call(args);
        }
        
        @Override
        public Object getDelegate() {
            return Closure.this.getDelegate();
        }
        
        @Override
        public void setDelegate(final Object delegate) {
            Closure.this.setDelegate(delegate);
        }
        
        @Override
        public Class[] getParameterTypes() {
            return Closure.this.getParameterTypes();
        }
        
        @Override
        public int getMaximumNumberOfParameters() {
            return Closure.this.getMaximumNumberOfParameters();
        }
        
        @Override
        public Closure asWritable() {
            return this;
        }
        
        @Override
        public void run() {
            Closure.this.run();
        }
        
        @Override
        public Object clone() {
            return ((Closure)Closure.this.clone()).asWritable();
        }
        
        @Override
        public int hashCode() {
            return Closure.this.hashCode();
        }
        
        @Override
        public boolean equals(final Object arg0) {
            return Closure.this.equals(arg0);
        }
        
        @Override
        public String toString() {
            final StringWriter writer = new StringWriter();
            try {
                this.writeTo(writer);
            }
            catch (IOException e) {
                return null;
            }
            return writer.toString();
        }
        
        @Override
        public Closure curry(final Object[] arguments) {
            return new CurriedClosure(this, arguments).asWritable();
        }
        
        @Override
        public void setResolveStrategy(final int resolveStrategy) {
            Closure.this.setResolveStrategy(resolveStrategy);
        }
        
        @Override
        public int getResolveStrategy() {
            return Closure.this.getResolveStrategy();
        }
    }
}
