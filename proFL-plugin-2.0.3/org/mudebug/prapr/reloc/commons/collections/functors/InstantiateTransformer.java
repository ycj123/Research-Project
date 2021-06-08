// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.functors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.mudebug.prapr.reloc.commons.collections.FunctorException;
import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.Transformer;

public class InstantiateTransformer implements Transformer, Serializable
{
    private static final long serialVersionUID = 3786388740793356347L;
    public static final Transformer NO_ARG_INSTANCE;
    private final Class[] iParamTypes;
    private final Object[] iArgs;
    
    public static Transformer getInstance(Class[] paramTypes, Object[] args) {
        if ((paramTypes == null && args != null) || (paramTypes != null && args == null) || (paramTypes != null && args != null && paramTypes.length != args.length)) {
            throw new IllegalArgumentException("Parameter types must match the arguments");
        }
        if (paramTypes == null || paramTypes.length == 0) {
            return InstantiateTransformer.NO_ARG_INSTANCE;
        }
        paramTypes = paramTypes.clone();
        args = args.clone();
        return new InstantiateTransformer(paramTypes, args);
    }
    
    private InstantiateTransformer() {
        this.iParamTypes = null;
        this.iArgs = null;
    }
    
    public InstantiateTransformer(final Class[] paramTypes, final Object[] args) {
        this.iParamTypes = paramTypes;
        this.iArgs = args;
    }
    
    public Object transform(final Object input) {
        try {
            if (!(input instanceof Class)) {
                throw new FunctorException("InstantiateTransformer: Input object was not an instanceof Class, it was a " + ((input == null) ? "null object" : input.getClass().getName()));
            }
            final Constructor con = ((Class)input).getConstructor((Class[])this.iParamTypes);
            return con.newInstance(this.iArgs);
        }
        catch (NoSuchMethodException ex4) {
            throw new FunctorException("InstantiateTransformer: The constructor must exist and be public ");
        }
        catch (InstantiationException ex) {
            throw new FunctorException("InstantiateTransformer: InstantiationException", ex);
        }
        catch (IllegalAccessException ex2) {
            throw new FunctorException("InstantiateTransformer: Constructor must be public", ex2);
        }
        catch (InvocationTargetException ex3) {
            throw new FunctorException("InstantiateTransformer: Constructor threw an exception", ex3);
        }
    }
    
    static {
        NO_ARG_INSTANCE = new InstantiateTransformer();
    }
}
