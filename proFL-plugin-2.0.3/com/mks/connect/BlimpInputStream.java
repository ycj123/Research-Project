// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.connect;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.EOFException;
import java.util.ArrayList;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.SequenceInputStream;
import java.io.FileInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.MessageFormat;
import java.io.IOException;
import com.mks.api.IntegrationPointFactory;
import java.util.Locale;
import com.mks.api.util.MKSLogger;
import java.io.InputStream;

public abstract class BlimpInputStream extends InputStream
{
    private static final String UNKNOWN_COMMAND = "Unknown Blimp command request: command code={0}";
    private static final String UNEXPECTED_COMMAND = "Unsupported Blimp command request: {0}";
    protected static final String UNEXPECTED_HTTP_STATUS = "Unexpected HTTP status: {0}";
    protected static final String INVALID_APP = "Invalid App Name: {0}";
    private AbstractCmdRunner cmdRunner;
    private InputStream input;
    private String inputLocale;
    private int numLeftForCmdStdout;
    private boolean finished;
    protected boolean generateSubRtns;
    private String[] args;
    private String appName;
    private MKSLogger apiLogger;
    
    protected BlimpInputStream(final AbstractCmdRunner cmdRunner, final String[] cmd) {
        this.inputLocale = Locale.getDefault().toString();
        this.finished = false;
        this.cmdRunner = cmdRunner;
        this.args = cmd;
        this.appName = cmd[0];
        this.apiLogger = IntegrationPointFactory.getLogger();
    }
    
    protected void setCodePage(final String inputLocale) {
        this.inputLocale = inputLocale;
    }
    
    protected void setGenerateSubRoutines(final boolean generateSubRtns) {
        this.generateSubRtns = generateSubRtns;
    }
    
    protected abstract void blimpInterrupt(final String p0) throws IOException;
    
    private void interrupt() throws IOException {
        this.blimpInterrupt(this.appName);
        this.cmdRunner.setInterrupted();
        try {
            while (!this.isFinished() && this.read() != -1 && this.cmdRunner.isInterrupted()) {}
        }
        catch (IOException ee) {}
        finally {
            this.cmdRunner.resetInterrupt();
        }
    }
    
    public void close() throws IOException {
        try {
            if (!this.isFinished()) {
                this.interrupt();
            }
        }
        finally {
            this.cleanup();
            this.numLeftForCmdStdout = -1;
        }
    }
    
    public boolean isFinished() {
        return this.finished;
    }
    
    public int read() throws IOException {
        final byte[] buffer = { 0 };
        final int numRead = this.read(buffer, 0, 1);
        if (numRead == -1) {
            return -1;
        }
        return buffer[0];
    }
    
    public int read(final byte[] buf) throws IOException {
        return this.read(buf, 0, buf.length);
    }
    
    public int read(final byte[] buffer, final int offset, final int length) throws IOException {
        int numRead;
        for (numRead = this.readFromCmdStdout(buffer, offset, length); numRead == 0; numRead = this.readFromCmdStdout(buffer, offset, length)) {
            this.handleNextBlimpCommand();
        }
        return numRead;
    }
    
    private int readFromCmdStdout(final byte[] buffer, final int offset, final int length) throws IOException {
        if (this.numLeftForCmdStdout <= 0) {
            return this.numLeftForCmdStdout;
        }
        final int numToRead = Math.min(length, this.numLeftForCmdStdout);
        final int numRead = this.readNoEof(buffer, offset, numToRead);
        this.numLeftForCmdStdout -= numRead;
        return numRead;
    }
    
    private InputStream getInputStream() throws IOException {
        if (this.input == null) {
            this.input = this.blimpInitiate(this.appName);
        }
        return this.input;
    }
    
    protected abstract InputStream blimpInitiate(final String p0) throws IOException;
    
    protected abstract void blimpTerminate(final InputStream p0) throws IOException;
    
    protected abstract InputStream blimpResponse(final InputStream p0) throws IOException;
    
    private void handleNextBlimpCommand() throws IOException {
        final int cmd = this.readNoEof();
        final int dataLen = this.readDataLength();
        this.apiLogger.message("DEBUG", 10, this + ": got cmd: " + cmd + ", length: " + dataLen);
        switch (cmd) {
            case 0: {
                this.cmdGetStatus();
                break;
            }
            case 1: {
                this.cmdGetEnv();
                break;
            }
            case 2: {
                this.cmdGetArgs();
                break;
            }
            case 3: {
                this.cmdGetCwd();
                break;
            }
            case 4: {
                this.cmdWriteStdout(dataLen, false);
                break;
            }
            case 11: {
                this.cmdWriteStdout(dataLen, true);
                break;
            }
            case 5: {
                this.cmdWriteStderr(dataLen, false);
                break;
            }
            case 12: {
                this.cmdWriteStderr(dataLen, true);
                break;
            }
            case 6: {
                this.cmdReadLine(dataLen);
                break;
            }
            case 7: {
                this.cmdReadMultiLine(dataLen);
                break;
            }
            case 8: {
                this.cmdReadSecret(dataLen);
                break;
            }
            case 10: {
                this.cmdResetIntr();
                break;
            }
            case 14: {
                this.cmdReadFile(dataLen);
                break;
            }
            case 15: {
                this.cmdWriteFile(dataLen);
                break;
            }
            case 16: {
                this.cmdGetCwdAndArgs();
                break;
            }
            case 9: {
                this.cmdExit();
                break;
            }
            case 13: {
                this.cmdExec(dataLen);
                break;
            }
            default: {
                throw new BlimpException(MessageFormat.format("Unknown Blimp command request: command code={0}", String.valueOf(cmd)));
            }
        }
    }
    
    private void cmdGetStatus() throws IOException {
        this.writeResponse();
    }
    
    private void cmdGetEnv() throws IOException {
        this.interrupt();
        this.writeResponse();
        throw new BlimpException(MessageFormat.format("Unsupported Blimp command request: {0}", "cmdGetEnv"));
    }
    
    private void cmdGetArgs() throws IOException {
        this.writeResponse(this.args);
    }
    
    private void cmdGetCwd() throws IOException {
        final String directory = System.getProperty("user.dir");
        this.writeResponse(directory);
    }
    
    private void cmdGetCwdAndArgs() throws IOException {
        final String cwd = System.getProperty("user.dir");
        final String[] cwdAndArgs = new String[this.args.length + 1];
        System.arraycopy(this.args, 0, cwdAndArgs, 1, this.args.length);
        cwdAndArgs[0] = cwd;
        this.writeResponse(cwdAndArgs);
    }
    
    private void cmdWriteStdout(final int dataLength, final boolean addNewLine) throws IOException {
        this.numLeftForCmdStdout = dataLength;
    }
    
    private void cmdWriteStderr(final int dataLength, final boolean addNewLine) throws IOException {
        if (dataLength == 0) {
            return;
        }
        final int bufferSize = Math.min(dataLength, 1024);
        final byte[] buffer = new byte[bufferSize];
        for (int numRead = 0, totalNumRead = 0; totalNumRead < dataLength; totalNumRead += numRead) {
            final int numToRead = Math.min(dataLength - totalNumRead, bufferSize);
            numRead = this.readNoEof(buffer, 0, numToRead);
            this.apiLogger.message(new String(buffer, 0, numRead));
        }
    }
    
    private void cmdReadLine(final int dataLength) throws IOException {
        this.interrupt();
        this.writeResponse();
        throw new BlimpException(MessageFormat.format("Unsupported Blimp command request: {0}", "cmdReadLine"));
    }
    
    private void cmdReadMultiLine(final int dataLength) throws IOException {
        this.interrupt();
        this.writeResponse();
        throw new BlimpException(MessageFormat.format("Unsupported Blimp command request: {0}", "cmdReadMultiLine"));
    }
    
    private void cmdReadSecret(final int dataLength) throws IOException {
        this.interrupt();
        this.writeResponse();
        throw new BlimpException(MessageFormat.format("Unsupported Blimp command request: {0}", "cmdReadSecret"));
    }
    
    private void cmdResetIntr() throws IOException {
        this.cmdRunner.resetInterrupt();
        this.writeResponse();
    }
    
    private File cmdFile(final int datalen) throws IOException {
        final byte[] bytes = new byte[datalen];
        for (int i = 0; i < datalen; ++i) {
            final int c = this.readNoEof();
            if (c == 0) {
                break;
            }
            bytes[i] = (byte)c;
        }
        final String filename = new String(bytes, this.inputLocale);
        File f = new File(filename);
        if (!f.isAbsolute()) {
            f = new File(new File(System.getProperty("user.dir")), filename);
        }
        return f;
    }
    
    private void cmdReadFile(final int datalen) throws IOException {
        final File f = this.cmdFile(datalen);
        if (!f.exists()) {
            this.writeResponse(2);
            return;
        }
        if (!f.canRead()) {
            this.writeResponse(3);
            return;
        }
        final long size = f.length();
        final long mtime = f.lastModified() / 1000L;
        final byte[] data = { (byte)(size >> 56), (byte)(size >> 48), (byte)(size >> 40), (byte)(size >> 32), (byte)(size >> 24), (byte)(size >> 16), (byte)(size >> 8), (byte)size, (byte)(mtime >> 24), (byte)(mtime >> 16), (byte)(mtime >> 8), (byte)mtime };
        final InputStream response = new SequenceInputStream(new ByteArrayInputStream(data), new FileInputStream(f));
        this.writeResponse(response, data.length);
    }
    
    private void cmdWriteFile(final int datalen) throws IOException {
        final File f = this.cmdFile(datalen);
        final File parent = f.getParentFile();
        if (parent != null && !parent.exists()) {
            this.writeResponse(2);
            return;
        }
        if ((f.exists() && !f.canWrite()) || (parent != null && !parent.canWrite())) {
            this.writeResponse(3);
            return;
        }
        OutputStream os = null;
        try {
            final byte[] data = new byte[12];
            int num;
            for (int pos = 0, length = data.length; (num = this.readNoEof(data, pos, length)) > 0; pos += num, length -= num) {}
            final byte[] buf = new byte[1024];
            long fileLength = (data[0] & 0xFF) << 56 | (data[1] & 0xFF) << 48 | (data[2] & 0xFF) << 40 | (data[3] & 0xFF) << 32 | (data[4] & 0xFF) << 24 | (data[5] & 0xFF) << 16 | (data[6] & 0xFF) << 8 | (data[7] & 0xFF);
            long mtime = (data[8] & 0xFF) << 24 | (data[9] & 0xFF) << 16 | (data[10] & 0xFF) << 8 | (data[11] & 0xFF);
            mtime *= 1000L;
            os = new FileOutputStream(f);
            while (fileLength > 0L && (num = this.readNoEof(buf, 0, Math.min(buf.length, (int)fileLength))) > 0) {
                os.write(buf, 0, num);
                fileLength -= num;
            }
            f.setLastModified(mtime);
        }
        finally {
            try {
                this.writeResponse();
            }
            finally {
                if (os != null) {
                    os.close();
                }
            }
        }
    }
    
    private void cmdExit() throws IOException {
        this.readNoEof();
        this.numLeftForCmdStdout = -1;
        this.cleanup();
    }
    
    private void cmdExec(final int datalen) throws IOException {
        final ArrayList ar = new ArrayList();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < datalen; ++i) {
            final int ch = this.readNoEof();
            if (ch == 0) {
                ar.add(sb.toString());
                sb = new StringBuffer();
            }
            else {
                sb.append((char)ch);
            }
        }
        final String[] args = new String[ar.size()];
        for (int j = 0; j < ar.size(); ++j) {
            args[j] = ar.get(j);
        }
        this.apiLogger.message("DEBUG", 10, "Execing: " + ar);
        try {
            final Process p = Runtime.getRuntime().exec(args);
            new InputStreamGobbler(p.getErrorStream(), "ERROR");
            new InputStreamGobbler(p.getInputStream(), "OUTPUT");
            final byte[] resp = { (byte)p.waitFor() };
            this.writeResponse(resp);
        }
        catch (InterruptedException ie) {
            this.writeResponse(new byte[] { -128 });
        }
    }
    
    protected void cleanup() throws IOException {
        this.finished = true;
        try {
            if (this.input != null) {
                try {
                    this.blimpTerminate(this.input);
                }
                finally {
                    this.input = null;
                }
            }
        }
        finally {
            super.close();
        }
    }
    
    private int readNoEof() throws IOException {
        final int b = this.getInputStream().read();
        if (b == -1) {
            throw new EOFException();
        }
        return b;
    }
    
    private int readNoEof(final byte[] buffer, final int offset, final int length) throws IOException {
        if (length == 0) {
            return 0;
        }
        final int numRead = this.getInputStream().read(buffer, offset, length);
        if (numRead == -1) {
            throw new EOFException();
        }
        return numRead;
    }
    
    private int readDataLength() throws IOException {
        int length = 0;
        for (int i = 2; i >= 0; --i) {
            final int c = this.readNoEof();
            length += c << i * 8;
        }
        return length;
    }
    
    private void writeResponse() throws IOException {
        this.writeResponse(new byte[0]);
    }
    
    private void writeResponse(final String[] responseArgs) throws IOException {
        String response = "";
        for (int i = 0; i < responseArgs.length; ++i) {
            response = response + responseArgs[i] + '\0';
        }
        this.writeResponse(response.getBytes(this.inputLocale));
    }
    
    private void writeResponse(final int errorCode) throws IOException {
        this.writeResponse(new byte[0], errorCode);
    }
    
    private void writeResponse(final String message) throws IOException {
        final String response = message + '\0';
        this.writeResponse(response.getBytes(this.inputLocale));
    }
    
    private void writeResponse(final byte[] response) throws IOException {
        this.writeResponse(response, 0);
    }
    
    private void writeResponse(final byte[] response, final int errorCode) throws IOException {
        this.writeResponse(new ByteArrayInputStream(response), response.length, errorCode);
    }
    
    private void writeResponse(final InputStream response, final int length) throws IOException {
        this.writeResponse(response, length, 0);
    }
    
    private void writeResponse(final InputStream response, final int dataLen, final int errorCode) throws IOException {
        if (this.input != null) {
            try {
                this.blimpTerminate(this.input);
            }
            finally {
                this.input = null;
            }
        }
        final byte[] fullResponse = new byte[4];
        if (this.cmdRunner.isInterrupted()) {
            fullResponse[0] = 1;
        }
        else {
            fullResponse[0] = (byte)errorCode;
        }
        fullResponse[1] = (byte)(dataLen >> 16);
        fullResponse[2] = (byte)(dataLen >> 8);
        fullResponse[3] = (byte)dataLen;
        final InputStream resp = new SequenceInputStream(new ByteArrayInputStream(fullResponse), response);
        this.input = this.blimpResponse(resp);
    }
    
    private class InputStreamGobbler extends Thread
    {
        private BufferedReader br;
        private String prefix;
        
        public InputStreamGobbler(final InputStream is, final String prefix) {
            this.br = new BufferedReader(new InputStreamReader(is));
            this.prefix = prefix;
            this.start();
        }
        
        public void run() {
            while (true) {
                try {
                    while (true) {
                        final String str = this.br.readLine();
                        if (str == null) {
                            break;
                        }
                        BlimpInputStream.this.apiLogger.message("DEBUG", 10, this.prefix + ": " + str);
                    }
                }
                catch (EOFException ee) {}
                catch (IOException ie) {
                    BlimpInputStream.this.apiLogger.exception("DEBUG", ie);
                    continue;
                }
                catch (Throwable th) {
                    BlimpInputStream.this.apiLogger.exception("DEBUG", th);
                    continue;
                }
                break;
            }
        }
    }
}
