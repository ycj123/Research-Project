// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.io.IOException;
import java.io.InputStream;
import groovyjarjarasm.asm.ClassVisitor;
import groovyjarjarasm.asm.ClassReader;
import groovyjarjarasm.asm.MethodVisitor;
import groovyjarjarasm.asm.ClassWriter;
import java.util.HashMap;
import java.util.Map;
import groovyjarjarasm.asm.Opcodes;

public class SunClassLoader extends ClassLoader implements Opcodes
{
    protected final Map<String, Class> knownClasses;
    protected static final SunClassLoader sunVM;
    
    protected SunClassLoader() throws Throwable {
        super(SunClassLoader.class.getClassLoader());
        this.knownClasses = new HashMap<String, Class>();
        final Class magic = ClassLoader.getSystemClassLoader().loadClass("sun.reflect.MagicAccessorImpl");
        this.knownClasses.put("sun.reflect.MagicAccessorImpl", magic);
        this.loadMagic();
    }
    
    private void loadMagic() {
        final ClassWriter cw = new ClassWriter(1);
        cw.visit(48, 1, "sun/reflect/GroovyMagic", null, "sun/reflect/MagicAccessorImpl", null);
        final MethodVisitor mv = cw.visitMethod(1, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(25, 0);
        mv.visitMethodInsn(183, "sun/reflect/MagicAccessorImpl", "<init>", "()V");
        mv.visitInsn(177);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        cw.visitEnd();
        this.define(cw.toByteArray(), "sun.reflect.GroovyMagic");
    }
    
    protected void loadFromRes(final String name) throws IOException {
        final InputStream asStream = SunClassLoader.class.getClassLoader().getResourceAsStream(resName(name));
        final ClassReader reader = new ClassReader(asStream);
        final ClassWriter cw = new ClassWriter(1);
        reader.accept(cw, 1);
        asStream.close();
        this.define(cw.toByteArray(), name);
    }
    
    protected static String resName(final String s) {
        return s.replace('.', '/') + ".class";
    }
    
    protected void define(final byte[] bytes, final String name) {
        this.knownClasses.put(name, this.defineClass(name, bytes, 0, bytes.length));
    }
    
    @Override
    protected synchronized Class loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
        final Class aClass = this.knownClasses.get(name);
        if (aClass != null) {
            return aClass;
        }
        try {
            return super.loadClass(name, resolve);
        }
        catch (ClassNotFoundException e) {
            return this.getClass().getClassLoader().loadClass(name);
        }
    }
    
    public Class doesKnow(final String name) {
        return this.knownClasses.get(name);
    }
    
    static {
        SunClassLoader res;
        try {
            res = AccessController.doPrivileged((PrivilegedAction<SunClassLoader>)new PrivilegedAction<SunClassLoader>() {
                public SunClassLoader run() {
                    try {
                        return new SunClassLoader();
                    }
                    catch (Throwable e) {
                        return null;
                    }
                }
            });
        }
        catch (Throwable e) {
            res = null;
        }
        sunVM = res;
    }
}
