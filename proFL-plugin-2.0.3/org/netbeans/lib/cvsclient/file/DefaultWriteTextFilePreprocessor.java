// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.file;

import java.io.OutputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

public class DefaultWriteTextFilePreprocessor implements WriteTextFilePreprocessor
{
    private static final int CHUNK_SIZE = 32768;
    
    public void copyTextFileToLocation(final InputStream in, final File file, final OutputStreamProvider outputStreamProvider) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        final byte[] bytes = System.getProperty("line.separator").getBytes();
        try {
            inputStream = new BufferedInputStream(in);
            outputStream = new BufferedOutputStream(outputStreamProvider.createOutputStream());
            final byte[] array = new byte[32768];
            for (int i = inputStream.read(array); i > 0; i = inputStream.read(array)) {
                for (int j = 0; j < i; ++j) {
                    if (array[j] == 10) {
                        outputStream.write(bytes);
                    }
                    else {
                        outputStream.write(array[j]);
                    }
                }
            }
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException ex) {}
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (IOException ex2) {}
            }
        }
    }
}
