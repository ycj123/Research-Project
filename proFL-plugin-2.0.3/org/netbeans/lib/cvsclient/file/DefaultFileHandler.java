// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.file;

import org.netbeans.lib.cvsclient.util.BugLog;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;
import java.io.BufferedInputStream;
import org.netbeans.lib.cvsclient.util.LoggedDataOutputStream;
import org.netbeans.lib.cvsclient.request.Request;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;
import java.io.File;
import org.netbeans.lib.cvsclient.command.GlobalOptions;
import java.util.Date;

public class DefaultFileHandler implements FileHandler
{
    private static final boolean DEBUG = false;
    private static final int CHUNK_SIZE = 32768;
    private Date modifiedDate;
    private TransmitTextFilePreprocessor transmitTextFilePreprocessor;
    private WriteTextFilePreprocessor writeTextFilePreprocessor;
    private WriteTextFilePreprocessor writeRcsDiffFilePreprocessor;
    private GlobalOptions globalOptions;
    
    public DefaultFileHandler() {
        this.setTransmitTextFilePreprocessor(new DefaultTransmitTextFilePreprocessor());
        this.setWriteTextFilePreprocessor(new DefaultWriteTextFilePreprocessor());
        this.setWriteRcsDiffFilePreprocessor(new WriteRcsDiffFilePreprocessor());
    }
    
    public TransmitTextFilePreprocessor getTransmitTextFilePreprocessor() {
        return this.transmitTextFilePreprocessor;
    }
    
    public void setTransmitTextFilePreprocessor(final TransmitTextFilePreprocessor transmitTextFilePreprocessor) {
        this.transmitTextFilePreprocessor = transmitTextFilePreprocessor;
    }
    
    public WriteTextFilePreprocessor getWriteTextFilePreprocessor() {
        return this.writeTextFilePreprocessor;
    }
    
    public void setWriteTextFilePreprocessor(final WriteTextFilePreprocessor writeTextFilePreprocessor) {
        this.writeTextFilePreprocessor = writeTextFilePreprocessor;
    }
    
    public WriteTextFilePreprocessor getWriteRcsDiffFilePreprocessor() {
        return this.writeRcsDiffFilePreprocessor;
    }
    
    public void setWriteRcsDiffFilePreprocessor(final WriteTextFilePreprocessor writeRcsDiffFilePreprocessor) {
        this.writeRcsDiffFilePreprocessor = writeRcsDiffFilePreprocessor;
    }
    
    protected String getLengthString(final long l) {
        return String.valueOf(l) + "\n";
    }
    
    protected Reader getProcessedReader(final File file) throws IOException {
        return new FileReader(file);
    }
    
    protected InputStream getProcessedInputStream(final File file) throws IOException {
        return new FileInputStream(file);
    }
    
    public Request[] getInitialisationRequests() {
        return null;
    }
    
    public void transmitTextFile(final File file, final LoggedDataOutputStream loggedDataOutputStream) throws IOException {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is either null or does not exist. Cannot transmit.");
        }
        File preprocessedTextFile = file;
        final TransmitTextFilePreprocessor transmitTextFilePreprocessor = this.getTransmitTextFilePreprocessor();
        if (transmitTextFilePreprocessor != null) {
            preprocessedTextFile = transmitTextFilePreprocessor.getPreprocessedTextFile(file);
        }
        BufferedInputStream bufferedInputStream = null;
        try {
            long length = preprocessedTextFile.length();
            loggedDataOutputStream.writeBytes(this.getLengthString(length), "US-ASCII");
            bufferedInputStream = new BufferedInputStream(new FileInputStream(preprocessedTextFile));
            final byte[] b = new byte[32768];
            while (length > 0L) {
                final int read = bufferedInputStream.read(b, 0, (length >= 32768L) ? 32768 : ((int)length));
                if (read == -1) {
                    throw new IOException("Unexpected end of stream from " + preprocessedTextFile + ".");
                }
                length -= read;
                loggedDataOutputStream.write(b, 0, read);
            }
            loggedDataOutputStream.flush();
        }
        finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                }
                catch (IOException ex) {}
            }
            if (transmitTextFilePreprocessor != null) {
                transmitTextFilePreprocessor.cleanup(preprocessedTextFile);
            }
        }
    }
    
    public void transmitBinaryFile(final File file, final LoggedDataOutputStream loggedDataOutputStream) throws IOException {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is either null or does not exist. Cannot transmit.");
        }
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            long length = file.length();
            loggedDataOutputStream.writeBytes(this.getLengthString(length), "US-ASCII");
            final byte[] b = new byte[32768];
            while (length > 0L) {
                final int read = bufferedInputStream.read(b, 0, (length >= 32768L) ? 32768 : ((int)length));
                if (read == -1) {
                    throw new IOException("Unexpected end of stream from " + file + ".");
                }
                length -= read;
                loggedDataOutputStream.write(b, 0, read);
            }
            loggedDataOutputStream.flush();
        }
        finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void writeTextFile(final String s, final String s2, final LoggedDataInputStream loggedDataInputStream, final int n) throws IOException {
        this.writeAndPostProcessTextFile(s, s2, loggedDataInputStream, n, this.getWriteTextFilePreprocessor());
    }
    
    public void writeRcsDiffFile(final String s, final String s2, final LoggedDataInputStream loggedDataInputStream, final int n) throws IOException {
        this.writeAndPostProcessTextFile(s, s2, loggedDataInputStream, n, this.getWriteRcsDiffFilePreprocessor());
    }
    
    private void writeAndPostProcessTextFile(final String s, final String s2, final LoggedDataInputStream loggedDataInputStream, int i, final WriteTextFilePreprocessor writeTextFilePreprocessor) throws IOException {
        final File file = new File(s);
        final boolean resetReadOnly = this.resetReadOnly(file);
        this.createNewFile(file);
        final File tempFile = File.createTempFile("cvsCRLF", "tmp");
        try {
            OutputStream outputStream = null;
            try {
                outputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
                final byte[] b = new byte[32768];
                while (i > 0) {
                    final int read = loggedDataInputStream.read(b, 0, (i >= 32768) ? 32768 : i);
                    if (read == -1) {
                        throw new IOException("Unexpected end of stream: " + s + "\nMissing " + i + " bytes. Probably network communication failure.\nPlease try again.");
                    }
                    i -= read;
                    outputStream.write(b, 0, read);
                }
            }
            finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    }
                    catch (IOException ex) {}
                }
            }
            final InputStream processedInputStream = this.getProcessedInputStream(tempFile);
            try {
                writeTextFilePreprocessor.copyTextFileToLocation(processedInputStream, file, new StreamProvider(file));
            }
            finally {
                processedInputStream.close();
            }
            if (this.modifiedDate != null) {
                file.setLastModified(this.modifiedDate.getTime());
                this.modifiedDate = null;
            }
        }
        finally {
            tempFile.delete();
        }
        if (resetReadOnly) {
            FileUtils.setFileReadOnly(file, true);
        }
    }
    
    public void writeBinaryFile(final String s, final String s2, final LoggedDataInputStream loggedDataInputStream, int i) throws IOException {
        final File file = new File(s);
        final boolean resetReadOnly = this.resetReadOnly(file);
        this.createNewFile(file);
        final File directory = new File(file.getParentFile(), "CVS");
        directory.mkdir();
        final File tempFile = File.createTempFile("cvsPostConversion", "tmp", directory);
        try {
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
            final byte[] array = new byte[32768];
            try {
                while (i > 0) {
                    final int read = loggedDataInputStream.read(array, 0, (i >= 32768) ? 32768 : i);
                    if (read == -1) {
                        throw new IOException("Unexpected end of stream: " + s + "\nMissing " + i + " bytes. Probably network communication failure.\nPlease try again.");
                    }
                    if (read < 0) {
                        break;
                    }
                    i -= read;
                    bufferedOutputStream.write(array, 0, read);
                }
            }
            finally {
                bufferedOutputStream.close();
            }
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(this.getProcessedInputStream(tempFile));
            final BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(this.createOutputStream(file));
            try {
                for (int j = bufferedInputStream.read(array, 0, 32768); j > 0; j = bufferedInputStream.read(array, 0, 32768)) {
                    bufferedOutputStream2.write(array, 0, j);
                }
            }
            finally {
                bufferedOutputStream2.close();
                bufferedInputStream.close();
            }
            if (this.modifiedDate != null) {
                file.setLastModified(this.modifiedDate.getTime());
                this.modifiedDate = null;
            }
        }
        finally {
            tempFile.delete();
        }
        if (resetReadOnly) {
            FileUtils.setFileReadOnly(file, true);
        }
    }
    
    protected boolean createNewFile(final File file) throws IOException {
        file.getParentFile().mkdirs();
        return file.createNewFile();
    }
    
    protected OutputStream createOutputStream(final File file) throws IOException {
        return new FileOutputStream(file);
    }
    
    private boolean resetReadOnly(final File file) throws IOException {
        boolean b = this.globalOptions != null && this.globalOptions.isCheckedOutFilesReadOnly();
        if (file.exists() && b) {
            b = !file.canWrite();
            if (b) {
                FileUtils.setFileReadOnly(file, false);
            }
        }
        return b;
    }
    
    public void removeLocalFile(final String pathname) throws IOException {
        final File file = new File(pathname);
        if (file.exists() && !file.delete()) {
            System.err.println("Could not delete file " + file.getAbsolutePath());
        }
    }
    
    public void renameLocalFile(final String pathname, final String child) throws IOException {
        final File file = new File(pathname);
        file.renameTo(new File(file.getParentFile(), child));
    }
    
    public void setNextFileDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    public void setGlobalOptions(final GlobalOptions globalOptions) {
        BugLog.getInstance().assertNotNull(globalOptions);
        this.globalOptions = globalOptions;
        this.transmitTextFilePreprocessor.setTempDir(globalOptions.getTempDir());
    }
    
    private class StreamProvider implements OutputStreamProvider
    {
        private final File file;
        
        public StreamProvider(final File file) {
            this.file = file;
        }
        
        public OutputStream createOutputStream() throws IOException {
            return DefaultFileHandler.this.createOutputStream(this.file);
        }
    }
}
