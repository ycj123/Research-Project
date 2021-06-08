// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.file;

import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

public class FileUtils
{
    private static FileReadOnlyHandler fileReadOnlyHandler;
    
    public static FileReadOnlyHandler getFileReadOnlyHandler() {
        return FileUtils.fileReadOnlyHandler;
    }
    
    public static void setFileReadOnlyHandler(final FileReadOnlyHandler fileReadOnlyHandler) {
        FileUtils.fileReadOnlyHandler = fileReadOnlyHandler;
    }
    
    public static void setFileReadOnly(final File file, final boolean b) throws IOException {
        if (getFileReadOnlyHandler() == null) {
            return;
        }
        getFileReadOnlyHandler().setFileReadOnly(file, b);
    }
    
    public static void copyFile(final File file, final File file2) throws IOException {
        if (file == null || file2 == null) {
            throw new NullPointerException("sourceFile and targetFile must not be null");
        }
        final File parentFile = file2.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            throw new IOException("Could not create directory '" + parentFile + "'");
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            outputStream = new BufferedOutputStream(new FileOutputStream(file2));
            try {
                final byte[] b = new byte[32768];
                for (int i = inputStream.read(b); i > 0; i = inputStream.read(b)) {
                    outputStream.write(b, 0, i);
                }
            }
            catch (IOException ex) {
                file2.delete();
                throw ex;
            }
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException ex2) {}
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (IOException ex3) {}
            }
        }
    }
    
    public static void renameFile(final File file, final File dest) throws IOException {
        int exists = dest.exists() ? 1 : 0;
        if (exists != 0) {
            for (int i = 0; i < 3; ++i) {
                if (dest.delete()) {
                    exists = 0;
                    break;
                }
                try {
                    Thread.sleep(71L);
                }
                catch (InterruptedException ex) {}
            }
        }
        if (exists == 0) {
            for (int j = 0; j < 3; ++j) {
                if (file.renameTo(dest)) {
                    return;
                }
                try {
                    Thread.sleep(71L);
                }
                catch (InterruptedException ex2) {}
            }
        }
        copyFile(file, dest);
        for (int k = 0; k < 3; ++k) {
            if (file.delete()) {
                return;
            }
            try {
                Thread.sleep(71L);
            }
            catch (InterruptedException ex3) {}
        }
        throw new IOException("Can not delete: " + file.getAbsolutePath());
    }
    
    private FileUtils() {
    }
}
