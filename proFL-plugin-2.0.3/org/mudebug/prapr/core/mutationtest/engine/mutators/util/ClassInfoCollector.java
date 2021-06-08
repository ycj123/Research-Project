// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators.util;

import org.pitest.functional.Option;
import org.pitest.util.Log;
import org.pitest.classinfo.ClassByteArraySource;
import org.pitest.reloc.asm.ClassVisitor;
import org.pitest.reloc.asm.ClassReader;

public class ClassInfoCollector
{
    public static CollectedClassInfo collect(final byte[] bytes) {
        final ClassReader reader = new ClassReader(bytes);
        final CollectorClassVisitor ccv = new CollectorClassVisitor();
        reader.accept(ccv, 8);
        return ccv.getCollectedClassInfo();
    }
    
    public static CollectedClassInfo collect(final ClassByteArraySource cache, final String className) {
        if (className.startsWith("[")) {
            return new CollectedClassInfo();
        }
        final Option<byte[]> bytes = cache.getBytes(className);
        if (bytes.hasSome()) {
            return collect(bytes.value());
        }
        final String javaName = className.replace('/', '.');
        Log.getLogger().warning("OOPS! Something went wrong in reading/parsing the class " + javaName);
        return new CollectedClassInfo();
    }
}
