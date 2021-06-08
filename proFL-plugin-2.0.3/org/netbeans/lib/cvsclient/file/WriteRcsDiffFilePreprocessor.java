// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.file;

import org.netbeans.lib.cvsclient.util.ByteArray;
import java.io.PushbackInputStream;
import java.io.IOException;
import org.netbeans.lib.cvsclient.util.BugLog;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

public class WriteRcsDiffFilePreprocessor implements WriteTextFilePreprocessor
{
    private static final int CHUNK_SIZE = 32768;
    private static final int READ_REMAINING = -2;
    private String lineEnding;
    
    public WriteRcsDiffFilePreprocessor() {
        this.lineEnding = System.getProperty("line.separator");
    }
    
    public String getLineEnding() {
        return this.lineEnding;
    }
    
    public void setLineEnding(final String lineEnding) {
        this.lineEnding = lineEnding;
    }
    
    public void copyTextFileToLocation(final InputStream in, final File file, final OutputStreamProvider outputStreamProvider) throws IOException {
        ReadInfo readInfo = null;
        OutputStream outputStream = null;
        ReadInfo readInfo2 = null;
        try {
            readInfo2 = new ReadInfo(new BufferedInputStream(in));
            readInfo = new ReadInfo(new BufferedInputStream(new FileInputStream(file)));
            final File tempFile = File.createTempFile(".#merg", "cvs", new File(file.getParent(), "CVS"));
            outputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
            for (byte[] array = readInfo2.readLine(); array != null && array.length > 0; array = readInfo2.readLine()) {
                if (array[0] == 100) {
                    final int start = getStart(array);
                    final int length = getLength(array);
                    if (start < 0 || length <= 0) {
                        BugLog.getInstance().bug("wrong parsing.." + new String(array));
                        throw new IOException();
                    }
                    this.readToLine(start - 1, readInfo, outputStream);
                    this.readToLine(start - 1 + length, readInfo, null);
                }
                else if (array[0] == 97) {
                    final int start2 = getStart(array);
                    final int length2 = getLength(array);
                    if (start2 < 0 || length2 <= 0) {
                        BugLog.getInstance().bug("wrong parsing.." + new String(array));
                        throw new IOException();
                    }
                    this.readToLine(start2, readInfo, outputStream);
                    readInfo2.setLineNumber(0);
                    this.readToLine(length2, readInfo2, outputStream);
                }
            }
            this.readToLine(-2, readInfo, outputStream);
            if (tempFile != null) {
                readInfo.close();
                outputStream.close();
                InputStream inputStream = null;
                final OutputStream outputStream2 = outputStreamProvider.createOutputStream();
                try {
                    inputStream = new BufferedInputStream(new FileInputStream(tempFile));
                    while (true) {
                        final int read = inputStream.read();
                        if (read == -1) {
                            break;
                        }
                        outputStream2.write(read);
                    }
                }
                finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        }
                        catch (IOException ex2) {}
                    }
                    try {
                        outputStream2.close();
                    }
                    catch (IOException ex3) {}
                }
            }
        }
        catch (Exception ex) {
            BugLog.getInstance().showException(ex);
        }
        finally {
            if (readInfo2 != null) {
                try {
                    readInfo2.close();
                }
                catch (IOException ex4) {}
            }
            if (readInfo != null) {
                try {
                    readInfo.close();
                }
                catch (IOException ex5) {}
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (IOException ex6) {}
            }
        }
    }
    
    private void readToLine(final int n, final ReadInfo readInfo, final OutputStream outputStream) throws IOException {
        while (readInfo.getLineNumber() < n || n == -2) {
            final byte[] line = readInfo.readLine();
            if (line == null) {
                return;
            }
            if (outputStream == null) {
                continue;
            }
            outputStream.write(line);
            outputStream.write(this.getLineEnding().getBytes());
        }
    }
    
    private static int indexOf(final byte[] array, final byte b) {
        return indexOf(array, b, 0);
    }
    
    private static int indexOf(final byte[] array, final byte b, final int n) {
        int n2 = -1;
        for (int i = n; i < array.length; ++i) {
            if (array[i] == b) {
                n2 = i;
                break;
            }
        }
        return n2;
    }
    
    private static int getStart(final byte[] bytes) {
        final int index = indexOf(bytes, (byte)32);
        if (index > 0) {
            final String s = new String(bytes, 1, index - 1);
            try {
                return Integer.parseInt(s);
            }
            catch (NumberFormatException ex) {
                return -1;
            }
        }
        return -1;
    }
    
    private static int getLength(final byte[] bytes) {
        final int index = indexOf(bytes, (byte)32);
        if (index > 0) {
            int n = indexOf(bytes, (byte)32, index + 1);
            if (n < 0) {
                n = bytes.length;
            }
            final String s = new String(bytes, index + 1, n - index - 1);
            try {
                return Integer.parseInt(s);
            }
            catch (NumberFormatException ex) {
                return -1;
            }
        }
        return -1;
    }
    
    private static class ReadInfo
    {
        private static final boolean crLines;
        private PushbackInputStream in;
        private int readLength;
        private int startIndex;
        private int lineNumber;
        private ByteArray line;
        
        public ReadInfo(final InputStream in) {
            this.in = new PushbackInputStream(in, 1);
            this.readLength = -1;
            this.startIndex = 0;
            this.lineNumber = 0;
            this.line = new ByteArray();
        }
        
        public int getLineNumber() {
            return this.lineNumber;
        }
        
        public void setLineNumber(final int lineNumber) {
            this.lineNumber = lineNumber;
        }
        
        public byte[] readLine() throws IOException {
            this.line.reset();
            boolean b = false;
            while (true) {
                final int read = this.in.read();
                if (read == -1) {
                    b = true;
                    break;
                }
                if (read == 10) {
                    ++this.lineNumber;
                    break;
                }
                if (read == 13) {
                    final int read2 = this.in.read();
                    if (read2 == 10) {
                        ++this.lineNumber;
                        break;
                    }
                    this.in.unread(read2);
                    if (ReadInfo.crLines) {
                        ++this.lineNumber;
                        break;
                    }
                }
                this.line.add((byte)read);
            }
            byte[] bytes = this.line.getBytes();
            if (b && bytes.length == 0) {
                bytes = null;
            }
            return bytes;
        }
        
        public void close() throws IOException {
            if (this.in != null) {
                this.in.close();
            }
        }
        
        static {
            crLines = "\r".equals(System.getProperty("line.separator"));
        }
    }
}
