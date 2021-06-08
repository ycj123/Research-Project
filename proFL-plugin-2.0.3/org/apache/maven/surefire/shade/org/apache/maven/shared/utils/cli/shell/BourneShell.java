// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli.shell;

import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.StringUtils;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.Os;

public class BourneShell extends Shell
{
    private static final char[] BASH_QUOTING_TRIGGER_CHARS;
    
    public BourneShell() {
        this.setShellCommand("/bin/sh");
        this.setArgumentQuoteDelimiter('\'');
        this.setExecutableQuoteDelimiter('\"');
        this.setSingleQuotedArgumentEscaped(true);
        this.setSingleQuotedExecutableEscaped(false);
        this.setQuotedExecutableEnabled(true);
    }
    
    @Override
    public String getExecutable() {
        if (Os.isFamily("windows")) {
            return super.getExecutable();
        }
        return unifyQuotes(super.getExecutable());
    }
    
    public List<String> getShellArgsList() {
        final List<String> shellArgs = new ArrayList<String>();
        final List<String> existingShellArgs = super.getShellArgsList();
        if (existingShellArgs != null && !existingShellArgs.isEmpty()) {
            shellArgs.addAll(existingShellArgs);
        }
        shellArgs.add("-c");
        return shellArgs;
    }
    
    public String[] getShellArgs() {
        String[] shellArgs = super.getShellArgs();
        if (shellArgs == null) {
            shellArgs = new String[0];
        }
        if (shellArgs.length > 0 && !shellArgs[shellArgs.length - 1].equals("-c")) {
            final String[] newArgs = new String[shellArgs.length + 1];
            System.arraycopy(shellArgs, 0, newArgs, 0, shellArgs.length);
            newArgs[shellArgs.length] = "-c";
            shellArgs = newArgs;
        }
        return shellArgs;
    }
    
    protected String getExecutionPreamble() {
        if (this.getWorkingDirectoryAsString() == null) {
            return null;
        }
        final String dir = this.getWorkingDirectoryAsString();
        final StringBuilder sb = new StringBuilder();
        sb.append("cd ");
        sb.append(unifyQuotes(dir));
        sb.append(" && ");
        return sb.toString();
    }
    
    protected char[] getQuotingTriggerChars() {
        return BourneShell.BASH_QUOTING_TRIGGER_CHARS;
    }
    
    private static String unifyQuotes(final String path) {
        if (path == null) {
            return null;
        }
        if (path.indexOf(32) == -1 && path.indexOf(39) != -1 && path.indexOf(34) == -1) {
            return StringUtils.escape(path);
        }
        return StringUtils.quoteAndEscape(path, '\"', BourneShell.BASH_QUOTING_TRIGGER_CHARS);
    }
    
    static {
        BASH_QUOTING_TRIGGER_CHARS = new char[] { ' ', '$', ';', '&', '|', '<', '>', '*', '?', '(', ')', '[', ']', '{', '}', '`' };
    }
}
