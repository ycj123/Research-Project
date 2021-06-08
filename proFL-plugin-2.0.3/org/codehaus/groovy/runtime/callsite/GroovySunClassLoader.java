// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.io.IOException;
import java.io.InputStream;
import groovyjarjarasm.asm.ClassVisitor;
import groovyjarjarasm.asm.ClassWriter;
import groovyjarjarasm.asm.ClassReader;
import org.codehaus.groovy.reflection.SunClassLoader;

public class GroovySunClassLoader extends SunClassLoader
{
    public static final SunClassLoader sunVM;
    
    protected GroovySunClassLoader() throws Throwable {
        this.loadAbstract();
        this.loadFromRes("org.codehaus.groovy.runtime.callsite.MetaClassSite");
        this.loadFromRes("org.codehaus.groovy.runtime.callsite.MetaMethodSite");
        this.loadFromRes("org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite");
        this.loadFromRes("org.codehaus.groovy.runtime.callsite.PojoMetaMethodSite");
        this.loadFromRes("org.codehaus.groovy.runtime.callsite.StaticMetaMethodSite");
    }
    
    private void loadAbstract() throws IOException {
        final InputStream asStream = GroovySunClassLoader.class.getClass().getClassLoader().getResourceAsStream(SunClassLoader.resName("org.codehaus.groovy.runtime.callsite.AbstractCallSite"));
        final ClassReader reader = new ClassReader(asStream);
        final ClassWriter cw = new ClassWriter(1) {
            @Override
            public void visit(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces) {
                super.visit(version, access, name, signature, "sun/reflect/GroovyMagic", interfaces);
            }
        };
        reader.accept(cw, 1);
        asStream.close();
        this.define(cw.toByteArray(), "org.codehaus.groovy.runtime.callsite.AbstractCallSite");
    }
    
    static {
        sunVM = AccessController.doPrivileged((PrivilegedAction<SunClassLoader>)new PrivilegedAction<SunClassLoader>() {
            public SunClassLoader run() {
                try {
                    if (GroovySunClassLoader.sunVM != null) {
                        return new GroovySunClassLoader();
                    }
                }
                catch (Throwable t) {}
                return null;
            }
        });
    }
}
