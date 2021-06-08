// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SCPClient
{
    Connection conn;
    
    public SCPClient(final Connection conn) {
        if (conn == null) {
            throw new IllegalArgumentException("Cannot accept null argument!");
        }
        this.conn = conn;
    }
    
    private void readResponse(final InputStream is) throws IOException {
        final int c = is.read();
        if (c == 0) {
            return;
        }
        if (c == -1) {
            throw new IOException("Remote scp terminated unexpectedly.");
        }
        if (c != 1 && c != 2) {
            throw new IOException("Remote scp sent illegal error code.");
        }
        if (c == 2) {
            throw new IOException("Remote scp terminated with error.");
        }
        final String err = this.receiveLine(is);
        throw new IOException("Remote scp terminated with error (" + err + ").");
    }
    
    private String receiveLine(final InputStream is) throws IOException {
        final StringBuffer sb = new StringBuffer(30);
        while (sb.length() <= 8192) {
            final int c = is.read();
            if (c < 0) {
                throw new IOException("Remote scp terminated unexpectedly.");
            }
            if (c == 10) {
                return sb.toString();
            }
            sb.append((char)c);
        }
        throw new IOException("Remote scp sent a too long line");
    }
    
    private LenNamePair parseCLine(final String line) throws IOException {
        if (line.length() < 8) {
            throw new IOException("Malformed C line sent by remote SCP binary, line too short.");
        }
        if (line.charAt(4) != ' ' || line.charAt(5) == ' ') {
            throw new IOException("Malformed C line sent by remote SCP binary.");
        }
        final int length_name_sep = line.indexOf(32, 5);
        if (length_name_sep == -1) {
            throw new IOException("Malformed C line sent by remote SCP binary.");
        }
        final String length_substring = line.substring(5, length_name_sep);
        final String name_substring = line.substring(length_name_sep + 1);
        if (length_substring.length() <= 0 || name_substring.length() <= 0) {
            throw new IOException("Malformed C line sent by remote SCP binary.");
        }
        if (6 + length_substring.length() + name_substring.length() != line.length()) {
            throw new IOException("Malformed C line sent by remote SCP binary.");
        }
        long len;
        try {
            len = Long.parseLong(length_substring);
        }
        catch (NumberFormatException e) {
            throw new IOException("Malformed C line sent by remote SCP binary, cannot parse file length.");
        }
        if (len < 0L) {
            throw new IOException("Malformed C line sent by remote SCP binary, illegal file length.");
        }
        final LenNamePair lnp = new LenNamePair();
        lnp.length = len;
        lnp.filename = name_substring;
        return lnp;
    }
    
    private void sendBytes(final Session sess, final byte[] data, final String fileName, final String mode) throws IOException {
        final OutputStream os = sess.getStdin();
        final InputStream is = new BufferedInputStream(sess.getStdout(), 512);
        this.readResponse(is);
        final String cline = "C" + mode + " " + data.length + " " + fileName + "\n";
        os.write(cline.getBytes());
        os.flush();
        this.readResponse(is);
        os.write(data, 0, data.length);
        os.write(0);
        os.flush();
        this.readResponse(is);
        os.write("E\n".getBytes());
        os.flush();
    }
    
    private void sendFiles(final Session sess, final String[] files, final String[] remoteFiles, final String mode) throws IOException {
        final byte[] buffer = new byte[8192];
        final OutputStream os = new BufferedOutputStream(sess.getStdin(), 40000);
        final InputStream is = new BufferedInputStream(sess.getStdout(), 512);
        this.readResponse(is);
        for (int i = 0; i < files.length; ++i) {
            final File f = new File(files[i]);
            long remain = f.length();
            String remoteName;
            if (remoteFiles != null && remoteFiles.length > i && remoteFiles[i] != null) {
                remoteName = remoteFiles[i];
            }
            else {
                remoteName = f.getName();
            }
            final String cline = "C" + mode + " " + remain + " " + remoteName + "\n";
            os.write(cline.getBytes());
            os.flush();
            this.readResponse(is);
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(f);
                while (remain > 0L) {
                    int trans;
                    if (remain > buffer.length) {
                        trans = buffer.length;
                    }
                    else {
                        trans = (int)remain;
                    }
                    if (fis.read(buffer, 0, trans) != trans) {
                        throw new IOException("Cannot read enough from local file " + files[i]);
                    }
                    os.write(buffer, 0, trans);
                    remain -= trans;
                }
            }
            finally {
                if (fis != null) {
                    fis.close();
                }
            }
            if (fis != null) {
                fis.close();
            }
            os.write(0);
            os.flush();
            this.readResponse(is);
        }
        os.write("E\n".getBytes());
        os.flush();
    }
    
    private void receiveFiles(final Session sess, final OutputStream[] targets) throws IOException {
        final byte[] buffer = new byte[8192];
        final OutputStream os = new BufferedOutputStream(sess.getStdin(), 512);
        final InputStream is = new BufferedInputStream(sess.getStdout(), 40000);
        os.write(0);
        os.flush();
        for (int i = 0; i < targets.length; ++i) {
            LenNamePair lnp = null;
            while (true) {
                final int c = is.read();
                if (c < 0) {
                    throw new IOException("Remote scp terminated unexpectedly.");
                }
                final String line = this.receiveLine(is);
                if (c == 84) {
                    continue;
                }
                if (c == 1 || c == 2) {
                    throw new IOException("Remote SCP error: " + line);
                }
                if (c == 67) {
                    lnp = this.parseCLine(line);
                    os.write(0);
                    os.flush();
                    int this_time_received;
                    for (long remain = lnp.length; remain > 0L; remain -= this_time_received) {
                        int trans;
                        if (remain > buffer.length) {
                            trans = buffer.length;
                        }
                        else {
                            trans = (int)remain;
                        }
                        this_time_received = is.read(buffer, 0, trans);
                        if (this_time_received < 0) {
                            throw new IOException("Remote scp terminated connection unexpectedly");
                        }
                        targets[i].write(buffer, 0, this_time_received);
                    }
                    this.readResponse(is);
                    os.write(0);
                    os.flush();
                    break;
                }
                throw new IOException("Remote SCP error: " + (char)c + line);
            }
        }
    }
    
    private void receiveFiles(final Session sess, final String[] files, final String target) throws IOException {
        final byte[] buffer = new byte[8192];
        final OutputStream os = new BufferedOutputStream(sess.getStdin(), 512);
        final InputStream is = new BufferedInputStream(sess.getStdout(), 40000);
        os.write(0);
        os.flush();
        for (int i = 0; i < files.length; ++i) {
            LenNamePair lnp = null;
            while (true) {
                final int c = is.read();
                if (c < 0) {
                    throw new IOException("Remote scp terminated unexpectedly.");
                }
                final String line = this.receiveLine(is);
                if (c == 84) {
                    continue;
                }
                if (c == 1 || c == 2) {
                    throw new IOException("Remote SCP error: " + line);
                }
                if (c == 67) {
                    lnp = this.parseCLine(line);
                    os.write(0);
                    os.flush();
                    final File f = new File(String.valueOf(target) + File.separatorChar + lnp.filename);
                    FileOutputStream fop = null;
                    try {
                        fop = new FileOutputStream(f);
                        int this_time_received;
                        for (long remain = lnp.length; remain > 0L; remain -= this_time_received) {
                            int trans;
                            if (remain > buffer.length) {
                                trans = buffer.length;
                            }
                            else {
                                trans = (int)remain;
                            }
                            this_time_received = is.read(buffer, 0, trans);
                            if (this_time_received < 0) {
                                throw new IOException("Remote scp terminated connection unexpectedly");
                            }
                            fop.write(buffer, 0, this_time_received);
                        }
                    }
                    finally {
                        if (fop != null) {
                            fop.close();
                        }
                    }
                    if (fop != null) {
                        fop.close();
                    }
                    this.readResponse(is);
                    os.write(0);
                    os.flush();
                    break;
                }
                throw new IOException("Remote SCP error: " + (char)c + line);
            }
        }
    }
    
    public void put(final String localFile, final String remoteTargetDirectory) throws IOException {
        this.put(new String[] { localFile }, remoteTargetDirectory, "0600");
    }
    
    public void put(final String[] localFiles, final String remoteTargetDirectory) throws IOException {
        this.put(localFiles, remoteTargetDirectory, "0600");
    }
    
    public void put(final String localFile, final String remoteTargetDirectory, final String mode) throws IOException {
        this.put(new String[] { localFile }, remoteTargetDirectory, mode);
    }
    
    public void put(final String localFile, final String remoteFileName, final String remoteTargetDirectory, final String mode) throws IOException {
        this.put(new String[] { localFile }, new String[] { remoteFileName }, remoteTargetDirectory, mode);
    }
    
    public void put(final byte[] data, final String remoteFileName, final String remoteTargetDirectory) throws IOException {
        this.put(data, remoteFileName, remoteTargetDirectory, "0600");
    }
    
    public void put(final byte[] data, final String remoteFileName, String remoteTargetDirectory, final String mode) throws IOException {
        Session sess = null;
        if (remoteFileName == null || remoteTargetDirectory == null || mode == null) {
            throw new IllegalArgumentException("Null argument.");
        }
        if (mode.length() != 4) {
            throw new IllegalArgumentException("Invalid mode.");
        }
        for (int i = 0; i < mode.length(); ++i) {
            if (!Character.isDigit(mode.charAt(i))) {
                throw new IllegalArgumentException("Invalid mode.");
            }
        }
        remoteTargetDirectory = remoteTargetDirectory.trim();
        remoteTargetDirectory = ((remoteTargetDirectory.length() > 0) ? remoteTargetDirectory : ".");
        final String cmd = "scp -t -d " + remoteTargetDirectory;
        try {
            sess = this.conn.openSession();
            sess.execCommand(cmd);
            this.sendBytes(sess, data, remoteFileName, mode);
        }
        catch (IOException e) {
            throw (IOException)new IOException("Error during SCP transfer.").initCause(e);
        }
        finally {
            if (sess != null) {
                sess.close();
            }
        }
        if (sess != null) {
            sess.close();
        }
    }
    
    public void put(final String[] localFiles, final String remoteTargetDirectory, final String mode) throws IOException {
        this.put(localFiles, null, remoteTargetDirectory, mode);
    }
    
    public void put(final String[] localFiles, final String[] remoteFiles, String remoteTargetDirectory, final String mode) throws IOException {
        Session sess = null;
        if (localFiles == null || remoteTargetDirectory == null || mode == null) {
            throw new IllegalArgumentException("Null argument.");
        }
        if (mode.length() != 4) {
            throw new IllegalArgumentException("Invalid mode.");
        }
        for (int i = 0; i < mode.length(); ++i) {
            if (!Character.isDigit(mode.charAt(i))) {
                throw new IllegalArgumentException("Invalid mode.");
            }
        }
        if (localFiles.length == 0) {
            return;
        }
        remoteTargetDirectory = remoteTargetDirectory.trim();
        remoteTargetDirectory = ((remoteTargetDirectory.length() > 0) ? remoteTargetDirectory : ".");
        final String cmd = "scp -t -d " + remoteTargetDirectory;
        for (int j = 0; j < localFiles.length; ++j) {
            if (localFiles[j] == null) {
                throw new IllegalArgumentException("Cannot accept null filename.");
            }
        }
        try {
            sess = this.conn.openSession();
            sess.execCommand(cmd);
            this.sendFiles(sess, localFiles, remoteFiles, mode);
        }
        catch (IOException e) {
            throw (IOException)new IOException("Error during SCP transfer.").initCause(e);
        }
        finally {
            if (sess != null) {
                sess.close();
            }
        }
        if (sess != null) {
            sess.close();
        }
    }
    
    public void get(final String remoteFile, final String localTargetDirectory) throws IOException {
        this.get(new String[] { remoteFile }, localTargetDirectory);
    }
    
    public void get(final String remoteFile, final OutputStream target) throws IOException {
        this.get(new String[] { remoteFile }, new OutputStream[] { target });
    }
    
    private void get(final String[] remoteFiles, final OutputStream[] targets) throws IOException {
        Session sess = null;
        if (remoteFiles == null || targets == null) {
            throw new IllegalArgumentException("Null argument.");
        }
        if (remoteFiles.length != targets.length) {
            throw new IllegalArgumentException("Length of arguments does not match.");
        }
        if (remoteFiles.length == 0) {
            return;
        }
        String cmd = "scp -f";
        for (int i = 0; i < remoteFiles.length; ++i) {
            if (remoteFiles[i] == null) {
                throw new IllegalArgumentException("Cannot accept null filename.");
            }
            final String tmp = remoteFiles[i].trim();
            if (tmp.length() == 0) {
                throw new IllegalArgumentException("Cannot accept empty filename.");
            }
            cmd = String.valueOf(cmd) + " " + tmp;
        }
        try {
            sess = this.conn.openSession();
            sess.execCommand(cmd);
            this.receiveFiles(sess, targets);
        }
        catch (IOException e) {
            throw (IOException)new IOException("Error during SCP transfer.").initCause(e);
        }
        finally {
            if (sess != null) {
                sess.close();
            }
        }
        if (sess != null) {
            sess.close();
        }
    }
    
    public void get(final String[] remoteFiles, final String localTargetDirectory) throws IOException {
        Session sess = null;
        if (remoteFiles == null || localTargetDirectory == null) {
            throw new IllegalArgumentException("Null argument.");
        }
        if (remoteFiles.length == 0) {
            return;
        }
        String cmd = "scp -f";
        for (int i = 0; i < remoteFiles.length; ++i) {
            if (remoteFiles[i] == null) {
                throw new IllegalArgumentException("Cannot accept null filename.");
            }
            final String tmp = remoteFiles[i].trim();
            if (tmp.length() == 0) {
                throw new IllegalArgumentException("Cannot accept empty filename.");
            }
            cmd = String.valueOf(cmd) + " " + tmp;
        }
        try {
            sess = this.conn.openSession();
            sess.execCommand(cmd);
            this.receiveFiles(sess, remoteFiles, localTargetDirectory);
        }
        catch (IOException e) {
            throw (IOException)new IOException("Error during SCP transfer.").initCause(e);
        }
        finally {
            if (sess != null) {
                sess.close();
            }
        }
        if (sess != null) {
            sess.close();
        }
    }
    
    class LenNamePair
    {
        long length;
        String filename;
    }
}
