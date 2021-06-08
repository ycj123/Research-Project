// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import java.util.HashMap;
import java.security.ProtectionDomain;
import org.codehaus.groovy.runtime.Reflector;
import java.util.Map;

public class ReflectorLoader extends ClassLoader
{
    private boolean inDefine;
    private final Map loadedClasses;
    private final ClassLoader delegatationLoader;
    private static final String REFLECTOR;
    
    @Override
    protected Class findClass(final String name) throws ClassNotFoundException {
        if (this.delegatationLoader == null) {
            return super.findClass(name);
        }
        return this.delegatationLoader.loadClass(name);
    }
    
    @Override
    protected synchronized Class loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
        if (this.inDefine && name.equals(ReflectorLoader.REFLECTOR)) {
            return Reflector.class;
        }
        return super.loadClass(name, resolve);
    }
    
    public synchronized Class defineClass(final String name, final byte[] bytecode, final ProtectionDomain domain) {
        this.inDefine = true;
        final Class c = this.defineClass(name, bytecode, 0, bytecode.length, domain);
        this.loadedClasses.put(name, c);
        this.resolveClass(c);
        this.inDefine = false;
        return c;
    }
    
    public ReflectorLoader(final ClassLoader parent) {
        super(parent);
        this.inDefine = false;
        this.loadedClasses = new HashMap();
        this.delegatationLoader = this.getClass().getClassLoader();
    }
    
    public synchronized Class getLoadedClass(final String name) {
        return this.loadedClasses.get(name);
    }
    
    static String getReflectorName(final Class theClass) {
        final String className = theClass.getName();
        if (className.startsWith("java.")) {
            final String packagePrefix = "gjdk.";
            String name = packagePrefix + className + "_GroovyReflector";
            if (theClass.isArray()) {
                Class clazz = theClass;
                name = packagePrefix;
                int level;
                for (level = 0; clazz.isArray(); clazz = clazz.getComponentType(), ++level) {}
                final String componentName = clazz.getName();
                name = packagePrefix + componentName + "_GroovyReflectorArray";
                if (level > 1) {
                    name += level;
                }
            }
            return name;
        }
        String name2 = className.replace('$', '_') + "_GroovyReflector";
        if (theClass.isArray()) {
            Class clazz2;
            int level2;
            for (clazz2 = theClass, level2 = 0; clazz2.isArray(); clazz2 = clazz2.getComponentType(), ++level2) {}
            final String componentName2 = clazz2.getName();
            name2 = componentName2.replace('$', '_') + "_GroovyReflectorArray";
            if (level2 > 1) {
                name2 += level2;
            }
        }
        return name2;
    }
    
    static {
        REFLECTOR = Reflector.class.getName();
    }
}
