// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io;

import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.lang.reflect.Method;

public class Java7Support
{
    private static final boolean isJava7;
    private static Method isSymbolicLink;
    private static Method toPath;
    
    public static boolean isSymLink(final File file) {
        try {
            final Object path = Java7Support.toPath.invoke(file, new Object[0]);
            return (boolean)Java7Support.isSymbolicLink.invoke(null, path);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        catch (InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }
    
    public static boolean isJava7() {
        return Java7Support.isJava7;
    }
    
    static {
        boolean isJava7x = true;
        try {
            final Class<?> files = Thread.currentThread().getContextClassLoader().loadClass("java.nio.file.Files");
            final Class<?> path = Thread.currentThread().getContextClassLoader().loadClass("java.nio.file.Path");
            Java7Support.isSymbolicLink = files.getMethod("isSymbolicLink", path);
            Java7Support.toPath = File.class.getMethod("toPath", (Class<?>[])new Class[0]);
        }
        catch (ClassNotFoundException e) {
            isJava7x = false;
        }
        catch (NoSuchMethodException e2) {
            isJava7x = false;
        }
        isJava7 = isJava7x;
    }
}
