// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.io.input;

import java.io.ObjectStreamClass;
import java.io.StreamCorruptedException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ClassLoaderObjectInputStream extends ObjectInputStream
{
    private final ClassLoader classLoader;
    
    public ClassLoaderObjectInputStream(final ClassLoader classLoader, final InputStream inputStream) throws IOException, StreamCorruptedException {
        super(inputStream);
        this.classLoader = classLoader;
    }
    
    @Override
    protected Class<?> resolveClass(final ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
        final Class<?> clazz = Class.forName(objectStreamClass.getName(), false, this.classLoader);
        if (clazz != null) {
            return clazz;
        }
        return super.resolveClass(objectStreamClass);
    }
}
