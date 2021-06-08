// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.entry.report.compressedxml;

import java.lang.reflect.Field;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPOutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.Writer;
import org.pitest.util.ResultOutputStrategy;

public class CompressedDirectoryResultOutputStrategy implements ResultOutputStrategy
{
    private final ResultOutputStrategy core;
    
    private CompressedDirectoryResultOutputStrategy(final ResultOutputStrategy core) {
        this.core = core;
    }
    
    @Override
    public Writer createWriterForFile(final String sourceFile) {
        final Writer coreWriter = this.core.createWriterForFile(sourceFile);
        final OutputStream os = exposeOutputStreamUnsafe((BufferedWriter)coreWriter);
        if (os == null) {
            throw new IllegalStateException();
        }
        final OutputStream bos = new BufferedOutputStream(os);
        try {
            final OutputStream gzos = new GZIPOutputStream(bos);
            return new OutputStreamWriter(gzos);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
    
    public static CompressedDirectoryResultOutputStrategy forResultOutputStrategy(final ResultOutputStrategy dros) {
        return new CompressedDirectoryResultOutputStrategy(dros);
    }
    
    private static OutputStream exposeOutputStreamUnsafe(final BufferedWriter writer) {
        try {
            final Field fieldOut = BufferedWriter.class.getDeclaredField("out");
            fieldOut.setAccessible(true);
            final FileWriter fileWriter = (FileWriter)fieldOut.get(writer);
            final Field fieldSE = OutputStreamWriter.class.getDeclaredField("se");
            fieldSE.setAccessible(true);
            final Object se = fieldSE.get(fileWriter);
            final Field fieldOS = se.getClass().getDeclaredField("out");
            fieldOS.setAccessible(true);
            return (OutputStream)fieldOS.get(se);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
