// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import groovy.lang.MetaMethod;
import groovy.lang.MetaClassImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.codehaus.groovy.runtime.callsite.GroovySunClassLoader;
import java.util.HashSet;
import java.util.Set;
import java.lang.ref.SoftReference;

public class ClassLoaderForClassArtifacts extends ClassLoader
{
    public final SoftReference<Class> klazz;
    private final Set<String> allocatedNames;
    
    public ClassLoaderForClassArtifacts(final Class klazz) {
        super(klazz.getClassLoader());
        this.allocatedNames = new HashSet<String>();
        this.klazz = new SoftReference<Class>(klazz);
    }
    
    public Class define(final String name, final byte[] bytes) {
        final Class cls = this.defineClass(name, bytes, 0, bytes.length, this.klazz.get().getProtectionDomain());
        this.resolveClass(cls);
        return cls;
    }
    
    @Override
    public Class loadClass(final String name) throws ClassNotFoundException {
        Class cls = this.findLoadedClass(name);
        if (cls != null) {
            return cls;
        }
        if (GroovySunClassLoader.sunVM != null) {
            cls = GroovySunClassLoader.sunVM.doesKnow(name);
            if (cls != null) {
                return cls;
            }
        }
        return super.loadClass(name);
    }
    
    public synchronized String createClassName(final Method method) {
        final String clsName = this.klazz.get().getName();
        String name;
        if (clsName.startsWith("java.")) {
            name = clsName.replace('.', '_') + "$" + method.getName();
        }
        else {
            name = clsName + "$" + method.getName();
        }
        if (!this.allocatedNames.contains(name)) {
            this.allocatedNames.add(name);
            return name;
        }
        int i = 0;
        String newName;
        while (true) {
            newName = name + "$" + i;
            if (!this.allocatedNames.contains(newName)) {
                break;
            }
            ++i;
        }
        this.allocatedNames.add(newName);
        return newName;
    }
    
    public Constructor defineClassAndGetConstructor(final String name, final byte[] bytes) {
        final Class cls = AccessController.doPrivileged((PrivilegedAction<Class>)new PrivilegedAction<Class>() {
            public Class run() {
                return ClassLoaderForClassArtifacts.this.define(name, bytes);
            }
        });
        if (cls != null) {
            try {
                return cls.getConstructor(CallSite.class, MetaClassImpl.class, MetaMethod.class, Class[].class);
            }
            catch (NoSuchMethodException ex) {}
        }
        return null;
    }
}
