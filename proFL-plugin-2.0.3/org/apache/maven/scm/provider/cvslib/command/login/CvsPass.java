// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.login;

import java.io.IOException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.log.ScmLogger;
import java.io.File;

public class CvsPass
{
    private String cvsRoot;
    private File passFile;
    private String password;
    private ScmLogger logger;
    private final char[] shifts;
    
    public CvsPass(final ScmLogger logger) {
        this.cvsRoot = null;
        this.passFile = null;
        this.password = null;
        this.shifts = new char[] { '\0', '\u0001', '\u0002', '\u0003', '\u0004', '\u0005', '\u0006', '\u0007', '\b', '\t', '\n', '\u000b', '\f', '\r', '\u000e', '\u000f', '\u0010', '\u0011', '\u0012', '\u0013', '\u0014', '\u0015', '\u0016', '\u0017', '\u0018', '\u0019', '\u001a', '\u001b', '\u001c', '\u001d', '\u001e', '\u001f', 'r', 'x', '5', 'O', '`', 'm', 'H', 'l', 'F', '@', 'L', 'C', 't', 'J', 'D', 'W', 'o', '4', 'K', 'w', '1', '\"', 'R', 'Q', '_', 'A', 'p', 'V', 'v', 'n', 'z', 'i', ')', '9', 'S', '+', '.', 'f', '(', 'Y', '&', 'g', '-', '2', '*', '{', '[', '#', '}', '7', '6', 'B', '|', '~', ';', '/', '\\', 'G', 's', 'N', 'X', 'k', 'j', '8', '$', 'y', 'u', 'h', 'e', 'd', 'E', 'I', 'c', '?', '^', ']', '\'', '%', '=', '0', ':', 'q', ' ', 'Z', ',', 'b', '<', '3', '!', 'a', '>', 'M', 'T', 'P', 'U', '\u00df', '\u00e1', '\u00d8', '»', '¦', '\u00e5', '½', '\u00de', '¼', '\u008d', '\u00f9', '\u0094', '\u00c8', '¸', '\u0088', '\u00f8', '¾', '\u00c7', 'ª', 'µ', '\u00cc', '\u008a', '\u00e8', '\u00da', '·', '\u00ff', '\u00ea', '\u00dc', '\u00f7', '\u00d5', '\u00cb', '\u00e2', '\u00c1', '®', '¬', '\u00e4', '\u00fc', '\u00d9', '\u00c9', '\u0083', '\u00e6', '\u00c5', '\u00d3', '\u0091', '\u00ee', '¡', '³', ' ', '\u00d4', '\u00cf', '\u00dd', '\u00fe', '\u00ad', '\u00ca', '\u0092', '\u00e0', '\u0097', '\u008c', '\u00c4', '\u00cd', '\u0082', '\u0087', '\u0085', '\u008f', '\u00f6', '\u00c0', '\u009f', '\u00f4', '\u00ef', '¹', '¨', '\u00d7', '\u0090', '\u008b', '¥', '´', '\u009d', '\u0093', 'º', '\u00d6', '°', '\u00e3', '\u00e7', '\u00db', '©', '¯', '\u009c', '\u00ce', '\u00c6', '\u0081', '¤', '\u0096', '\u00d2', '\u009a', '±', '\u0086', '\u007f', '¶', '\u0080', '\u009e', '\u00d0', '¢', '\u0084', '§', '\u00d1', '\u0095', '\u00f1', '\u0099', '\u00fb', '\u00ed', '\u00ec', '«', '\u00c3', '\u00f3', '\u00e9', '\u00fd', '\u00f0', '\u00c2', '\u00fa', '¿', '\u009b', '\u008e', '\u0089', '\u00f5', '\u00eb', '£', '\u00f2', '²', '\u0098' };
        this.passFile = new File(System.getProperty("cygwin.user.home", System.getProperty("user.home")) + File.separatorChar + ".cvspass");
        this.logger = logger;
    }
    
    public final void execute() throws ScmException, IOException {
        if (this.cvsRoot == null) {
            throw new ScmException("cvsroot is required");
        }
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("cvsRoot: " + this.cvsRoot);
            this.logger.debug("passFile: " + this.passFile);
        }
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            final StringBuilder buf = new StringBuilder();
            if (this.passFile.exists()) {
                reader = new BufferedReader(new FileReader(this.passFile));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(this.cvsRoot) || line.startsWith("/1 " + this.cvsRoot)) {
                        if (this.logger.isDebugEnabled()) {
                            this.logger.debug("cvsroot " + this.cvsRoot + " already exist in " + this.passFile.getAbsolutePath() + ". SKIPPED.");
                        }
                        return;
                    }
                    buf.append(line).append("\n");
                }
            }
            else {
                this.passFile.getParentFile().mkdirs();
            }
            if (this.password == null) {
                throw new ScmException("password is required. You must run a 'cvs -d " + this.cvsRoot + " login' first or provide it in the connection url.");
            }
            final String pwdfile = buf.toString() + "/1 " + this.cvsRoot + " A" + this.mangle(this.password);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Writing -> " + pwdfile + " in " + this.passFile.getAbsolutePath());
            }
            writer = new PrintWriter(new FileWriter(this.passFile));
            writer.println(pwdfile);
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException ex) {}
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
    
    private String mangle(final String password) {
        final StringBuilder buf = new StringBuilder();
        for (int i = 0; i < password.length(); ++i) {
            buf.append(this.shifts[password.charAt(i)]);
        }
        return buf.toString();
    }
    
    public void setCvsroot(final String cvsRoot) {
        this.cvsRoot = cvsRoot;
    }
    
    public void setPassfile(final File passFile) {
        this.passFile = passFile;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
}
