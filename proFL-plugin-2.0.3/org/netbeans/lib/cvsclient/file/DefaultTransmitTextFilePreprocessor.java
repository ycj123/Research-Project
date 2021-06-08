// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.file;

import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;

public class DefaultTransmitTextFilePreprocessor implements TransmitTextFilePreprocessor
{
    private static final int CHUNK_SIZE = 32768;
    private File tempDir;
    
    public void setTempDir(final File tempDir) {
        this.tempDir = tempDir;
    }
    
    public File getPreprocessedTextFile(final File file) throws IOException {
        final File tempFile = File.createTempFile("cvs", null, this.tempDir);
        final byte[] bytes = System.getProperty("line.separator").getBytes();
        final boolean b = bytes.length != 1 || bytes[0] != 10;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            outputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
            final byte[] b2 = new byte[32768];
            final byte[] b3 = new byte[32768];
            for (int i = inputStream.read(b2); i > 0; i = inputStream.read(b2)) {
                if (b) {
                    int len = 0;
                    int j = 0;
                    while (j < i) {
                        final int index = findIndexOf(b2, bytes, j);
                        if (index >= j && index < i) {
                            System.arraycopy(b2, j, b3, len, index - j);
                            len += index - j;
                            j = index + bytes.length;
                            b3[len++] = 10;
                        }
                        else {
                            System.arraycopy(b2, j, b3, len, i - j);
                            len += i - j;
                            j = i;
                        }
                    }
                    outputStream.write(b3, 0, len);
                }
                else {
                    outputStream.write(b2, 0, i);
                }
            }
            return tempFile;
        }
        catch (IOException ex) {
            if (tempFile != null) {
                this.cleanup(tempFile);
            }
            throw ex;
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
    
    private static int findIndexOf(final byte[] array, final byte[] array2, final int n) {
        int n2 = 0;
        for (int i = n; i < array.length; ++i) {
            if (array[i] == array2[n2]) {
                if (++n2 == array2.length) {
                    return i - n2 + 1;
                }
            }
            else {
                n2 = 0;
            }
        }
        return -1;
    }
    
    public void cleanup(final File file) {
        if (file != null) {
            file.delete();
        }
    }
}
