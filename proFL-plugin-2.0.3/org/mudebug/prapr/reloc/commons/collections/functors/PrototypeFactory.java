// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.functors;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import org.mudebug.prapr.reloc.commons.collections.FunctorException;
import java.lang.reflect.Method;
import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.Factory;

public class PrototypeFactory
{
    public static Factory getInstance(final Object prototype) {
        if (prototype == null) {
            return ConstantFactory.NULL_INSTANCE;
        }
        try {
            final Method method = prototype.getClass().getMethod("clone", (Class<?>[])null);
            return new PrototypeCloneFactory(prototype, method);
        }
        catch (NoSuchMethodException ex) {
            try {
                prototype.getClass().getConstructor(prototype.getClass());
                return new InstantiateFactory(prototype.getClass(), new Class[] { prototype.getClass() }, new Object[] { prototype });
            }
            catch (NoSuchMethodException ex2) {
                if (prototype instanceof Serializable) {
                    return new PrototypeSerializationFactory((Serializable)prototype);
                }
                throw new IllegalArgumentException("The prototype must be cloneable via a public clone method");
            }
        }
    }
    
    private PrototypeFactory() {
    }
    
    static class PrototypeCloneFactory implements Factory, Serializable
    {
        private static final long serialVersionUID = 5604271422565175555L;
        private final Object iPrototype;
        private transient Method iCloneMethod;
        
        private PrototypeCloneFactory(final Object prototype, final Method method) {
            this.iPrototype = prototype;
            this.iCloneMethod = method;
        }
        
        private void findCloneMethod() {
            try {
                this.iCloneMethod = this.iPrototype.getClass().getMethod("clone", (Class<?>[])null);
            }
            catch (NoSuchMethodException ex) {
                throw new IllegalArgumentException("PrototypeCloneFactory: The clone method must exist and be public ");
            }
        }
        
        public Object create() {
            if (this.iCloneMethod == null) {
                this.findCloneMethod();
            }
            try {
                return this.iCloneMethod.invoke(this.iPrototype, (Object[])null);
            }
            catch (IllegalAccessException ex) {
                throw new FunctorException("PrototypeCloneFactory: Clone method must be public", ex);
            }
            catch (InvocationTargetException ex2) {
                throw new FunctorException("PrototypeCloneFactory: Clone method threw an exception", ex2);
            }
        }
    }
    
    static class PrototypeSerializationFactory implements Factory, Serializable
    {
        private static final long serialVersionUID = -8704966966139178833L;
        private final Serializable iPrototype;
        
        private PrototypeSerializationFactory(final Serializable prototype) {
            this.iPrototype = prototype;
        }
        
        public Object create() {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
            ByteArrayInputStream bais = null;
            try {
                final ObjectOutputStream out = new ObjectOutputStream(baos);
                out.writeObject(this.iPrototype);
                bais = new ByteArrayInputStream(baos.toByteArray());
                final ObjectInputStream in = new ObjectInputStream(bais);
                return in.readObject();
            }
            catch (ClassNotFoundException ex) {
                throw new FunctorException(ex);
            }
            catch (IOException ex2) {
                throw new FunctorException(ex2);
            }
            finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                }
                catch (IOException ex3) {}
                try {
                    if (baos != null) {
                        baos.close();
                    }
                }
                catch (IOException ex4) {}
            }
        }
    }
}
