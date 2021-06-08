// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.analysis;

import org.pitest.reloc.asm.Type;
import org.pitest.reloc.asm.MethodVisitor;
import java.util.ArrayList;
import java.util.Enumeration;
import org.pitest.util.Log;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.io.File;
import java.util.List;
import java.util.Iterator;
import org.pitest.reloc.asm.ClassVisitor;
import org.pitest.reloc.asm.ClassReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public final class GlobalInfo implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final Map<String, String[]> classHierarchy;
    private final Map<String, FactoryMethod[]> factoryMethods;
    
    private GlobalInfo() {
        this.classHierarchy = new HashMap<String, String[]>();
        this.factoryMethods = new HashMap<String, FactoryMethod[]>();
    }
    
    public String[] subclassesOf(final String superclass) {
        return this.classHierarchy.get(superclass);
    }
    
    public FactoryMethod[] factoryMethodsFor(final String className) {
        return this.factoryMethods.get(className);
    }
    
    private String[] addClassName(final String[] names, final String name) {
        final String[] namesExt = new String[names.length + 1];
        System.arraycopy(names, 0, namesExt, 0, names.length);
        namesExt[names.length] = name;
        return namesExt;
    }
    
    private void addFactoryMethod(final String className, final FactoryMethod method) {
        FactoryMethod[] methods = this.factoryMethods.get(className);
        if (methods == null) {
            methods = new FactoryMethod[0];
        }
        final FactoryMethod[] methodsExt = new FactoryMethod[methods.length + 1];
        System.arraycopy(methods, 0, methodsExt, 0, methods.length);
        methodsExt[methods.length] = method;
        this.factoryMethods.put(className, methodsExt);
    }
    
    private void addSubclass(final String superclass, final String subclass) {
        String[] names = this.classHierarchy.get(superclass);
        if (names == null) {
            names = new String[0];
        }
        names = this.addClassName(names, subclass);
        this.classHierarchy.put(superclass, names);
    }
    
    private static void populate(final GlobalInfo ch, final InputStream is) throws Exception {
        final BasicClassVisitor classVisitor = new BasicClassVisitor();
        final ClassReader reader = new ClassReader(is);
        reader.accept(classVisitor, 8);
        final String subclass = classVisitor.name;
        for (final String superclass : classVisitor.supers) {
            ch.addSubclass(superclass, subclass);
        }
        for (final FactoryMethod fm : classVisitor.factoryMethods) {
            ch.addFactoryMethod(fm.getReturnType().getInternalName(), fm);
        }
    }
    
    public static GlobalInfo construct(final List<File> files) {
        final GlobalInfo ch = new GlobalInfo();
        for (final File file : files) {
            try {
                if (file.getName().endsWith(".jar")) {
                    final JarFile jar = new JarFile(file);
                    final Enumeration<JarEntry> enums = jar.entries();
                    while (enums.hasMoreElements()) {
                        final JarEntry entry = enums.nextElement();
                        if (entry.getName().endsWith(".class")) {
                            final InputStream is = jar.getInputStream(entry);
                            populate(ch, is);
                            is.close();
                        }
                    }
                    jar.close();
                }
                else {
                    final InputStream is2 = new FileInputStream(file);
                    populate(ch, is2);
                    is2.close();
                }
            }
            catch (Exception e) {
                Log.getLogger().info("OOPS! Something went wrong while reading the file " + file.getAbsolutePath());
                Log.getLogger().info("\t" + e.getMessage());
            }
        }
        return ch;
    }
    
    private static class BasicClassVisitor extends ClassVisitor
    {
        final List<FactoryMethod> factoryMethods;
        final List<String> supers;
        private boolean isInterface;
        String name;
        
        BasicClassVisitor() {
            super(393216);
            this.supers = new ArrayList<String>();
            this.factoryMethods = new ArrayList<FactoryMethod>();
            this.isInterface = false;
        }
        
        @Override
        public void visit(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces) {
            this.name = name;
            this.supers.add(superName);
            if (interfaces != null) {
                for (final String si : interfaces) {
                    this.supers.add(si);
                }
            }
            this.isInterface = ((access & 0x200) != 0x0);
            super.visit(version, access, name, signature, superName, interfaces);
        }
        
        @Override
        public MethodVisitor visitMethod(final int access, final String methName, final String desc, final String signature, final String[] exceptions) {
            if ((access & 0x8) != 0x0) {
                final int returnTypeSort = Type.getReturnType(desc).getSort();
                if (returnTypeSort == 10) {
                    this.factoryMethods.add(new FactoryMethod(this.name, methName, desc, this.isInterface));
                }
            }
            return super.visitMethod(access, methName, desc, signature, exceptions);
        }
    }
}
