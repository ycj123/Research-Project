// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli.shell;

import java.io.File;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.StringUtils;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Shell implements Cloneable
{
    private static final char[] DEFAULT_QUOTING_TRIGGER_CHARS;
    private String shellCommand;
    private final List<String> shellArgs;
    private boolean quotedArgumentsEnabled;
    private String executable;
    private String workingDir;
    private boolean quotedExecutableEnabled;
    private boolean singleQuotedArgumentEscaped;
    private boolean singleQuotedExecutableEscaped;
    private char argQuoteDelimiter;
    private char exeQuoteDelimiter;
    
    public Shell() {
        this.shellArgs = new ArrayList<String>();
        this.quotedArgumentsEnabled = true;
        this.quotedExecutableEnabled = true;
        this.singleQuotedArgumentEscaped = false;
        this.singleQuotedExecutableEscaped = false;
        this.argQuoteDelimiter = '\"';
        this.exeQuoteDelimiter = '\"';
    }
    
    void setShellCommand(final String shellCommand) {
        this.shellCommand = shellCommand;
    }
    
    String getShellCommand() {
        return this.shellCommand;
    }
    
    void setShellArgs(final String[] shellArgs) {
        this.shellArgs.clear();
        this.shellArgs.addAll(Arrays.asList(shellArgs));
    }
    
    String[] getShellArgs() {
        if (this.shellArgs == null || this.shellArgs.isEmpty()) {
            return null;
        }
        return this.shellArgs.toArray(new String[this.shellArgs.size()]);
    }
    
    List<String> getCommandLine(final String executable, final String... arguments) {
        return this.getRawCommandLine(executable, arguments);
    }
    
    List<String> getRawCommandLine(final String executable, final String... arguments) {
        final List<String> commandLine = new ArrayList<String>();
        final StringBuilder sb = new StringBuilder();
        if (executable != null) {
            final String preamble = this.getExecutionPreamble();
            if (preamble != null) {
                sb.append(preamble);
            }
            if (this.isQuotedExecutableEnabled()) {
                final char[] escapeChars = this.getEscapeChars(this.isSingleQuotedExecutableEscaped(), this.isDoubleQuotedExecutableEscaped());
                sb.append(StringUtils.quoteAndEscape(this.getExecutable(), this.getExecutableQuoteDelimiter(), escapeChars, this.getQuotingTriggerChars(), '\\', false));
            }
            else {
                sb.append(this.getExecutable());
            }
        }
        for (final String argument : arguments) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            if (this.isQuotedArgumentsEnabled()) {
                final char[] escapeChars2 = this.getEscapeChars(this.isSingleQuotedArgumentEscaped(), this.isDoubleQuotedArgumentEscaped());
                sb.append(StringUtils.quoteAndEscape(argument, this.getArgumentQuoteDelimiter(), escapeChars2, this.getQuotingTriggerChars(), '\\', false));
            }
            else {
                sb.append(argument);
            }
        }
        commandLine.add(sb.toString());
        return commandLine;
    }
    
    char[] getQuotingTriggerChars() {
        return Shell.DEFAULT_QUOTING_TRIGGER_CHARS;
    }
    
    String getExecutionPreamble() {
        return null;
    }
    
    char[] getEscapeChars(final boolean includeSingleQuote, final boolean includeDoubleQuote) {
        final StringBuilder buf = new StringBuilder(2);
        if (includeSingleQuote) {
            buf.append('\'');
        }
        if (includeDoubleQuote) {
            buf.append('\"');
        }
        final char[] result = new char[buf.length()];
        buf.getChars(0, buf.length(), result, 0);
        return result;
    }
    
    protected boolean isDoubleQuotedArgumentEscaped() {
        return false;
    }
    
    protected boolean isSingleQuotedArgumentEscaped() {
        return this.singleQuotedArgumentEscaped;
    }
    
    boolean isDoubleQuotedExecutableEscaped() {
        return false;
    }
    
    boolean isSingleQuotedExecutableEscaped() {
        return this.singleQuotedExecutableEscaped;
    }
    
    void setArgumentQuoteDelimiter(final char argQuoteDelimiter) {
        this.argQuoteDelimiter = argQuoteDelimiter;
    }
    
    char getArgumentQuoteDelimiter() {
        return this.argQuoteDelimiter;
    }
    
    void setExecutableQuoteDelimiter(final char exeQuoteDelimiter) {
        this.exeQuoteDelimiter = exeQuoteDelimiter;
    }
    
    char getExecutableQuoteDelimiter() {
        return this.exeQuoteDelimiter;
    }
    
    public List<String> getShellCommandLine(final String... arguments) {
        final List<String> commandLine = new ArrayList<String>();
        if (this.getShellCommand() != null) {
            commandLine.add(this.getShellCommand());
        }
        if (this.getShellArgs() != null) {
            commandLine.addAll(this.getShellArgsList());
        }
        commandLine.addAll(this.getCommandLine(this.getExecutable(), arguments));
        return commandLine;
    }
    
    List<String> getShellArgsList() {
        return this.shellArgs;
    }
    
    public void setQuotedArgumentsEnabled(final boolean quotedArgumentsEnabled) {
        this.quotedArgumentsEnabled = quotedArgumentsEnabled;
    }
    
    boolean isQuotedArgumentsEnabled() {
        return this.quotedArgumentsEnabled;
    }
    
    void setQuotedExecutableEnabled(final boolean quotedExecutableEnabled) {
        this.quotedExecutableEnabled = quotedExecutableEnabled;
    }
    
    boolean isQuotedExecutableEnabled() {
        return this.quotedExecutableEnabled;
    }
    
    public void setExecutable(final String executable) {
        if (executable == null || executable.length() == 0) {
            return;
        }
        this.executable = executable.replace('/', File.separatorChar).replace('\\', File.separatorChar);
    }
    
    public String getExecutable() {
        return this.executable;
    }
    
    public void setWorkingDirectory(final String path) {
        if (path != null) {
            this.workingDir = path;
        }
    }
    
    public void setWorkingDirectory(final File workingDir) {
        if (workingDir != null) {
            this.workingDir = workingDir.getAbsolutePath();
        }
    }
    
    public File getWorkingDirectory() {
        return (this.workingDir == null) ? null : new File(this.workingDir);
    }
    
    String getWorkingDirectoryAsString() {
        return this.workingDir;
    }
    
    public Object clone() {
        throw new RuntimeException("Do we ever clone this?");
    }
    
    void setSingleQuotedArgumentEscaped(final boolean singleQuotedArgumentEscaped) {
        this.singleQuotedArgumentEscaped = singleQuotedArgumentEscaped;
    }
    
    void setSingleQuotedExecutableEscaped(final boolean singleQuotedExecutableEscaped) {
        this.singleQuotedExecutableEscaped = singleQuotedExecutableEscaped;
    }
    
    static {
        DEFAULT_QUOTING_TRIGGER_CHARS = new char[] { ' ' };
    }
}
