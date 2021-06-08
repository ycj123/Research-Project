// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli;

import java.io.IOException;
import java.io.File;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.StringUtils;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli.shell.BourneShell;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli.shell.CmdShell;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli.shell.CommandShell;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.Os;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Vector;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli.shell.Shell;
import java.util.Map;
import java.util.List;

public class Commandline implements Cloneable
{
    private final List<Arg> arguments;
    private final Map<String, String> envVars;
    private Shell shell;
    
    public Commandline(final Shell shell) {
        this.arguments = new Vector<Arg>();
        this.envVars = Collections.synchronizedMap(new LinkedHashMap<String, String>());
        this.shell = shell;
    }
    
    public Commandline(final String toProcess) {
        this.arguments = new Vector<Arg>();
        this.envVars = Collections.synchronizedMap(new LinkedHashMap<String, String>());
        this.setDefaultShell();
        String[] tmp = new String[0];
        try {
            tmp = CommandLineUtils.translateCommandline(toProcess);
        }
        catch (Exception e) {
            System.err.println("Error translating Commandline.");
        }
        if (tmp != null && tmp.length > 0) {
            this.setExecutable(tmp[0]);
            for (int i = 1; i < tmp.length; ++i) {
                this.createArg().setValue(tmp[i]);
            }
        }
    }
    
    public Commandline() {
        this.arguments = new Vector<Arg>();
        this.envVars = Collections.synchronizedMap(new LinkedHashMap<String, String>());
        this.setDefaultShell();
    }
    
    private void setDefaultShell() {
        if (Os.isFamily("windows")) {
            if (Os.isFamily("win9x")) {
                this.setShell(new CommandShell());
            }
            else {
                this.setShell(new CmdShell());
            }
        }
        else {
            this.setShell(new BourneShell());
        }
    }
    
    public Arg createArg() {
        return this.createArg(false);
    }
    
    public Arg createArg(final boolean insertAtStart) {
        final Arg argument = new Argument();
        if (insertAtStart) {
            this.arguments.add(0, argument);
        }
        else {
            this.arguments.add(argument);
        }
        return argument;
    }
    
    public void setExecutable(final String executable) {
        this.shell.setExecutable(executable);
    }
    
    public String getExecutable() {
        return this.shell.getExecutable();
    }
    
    public void addArguments(final String... line) {
        for (final String aLine : line) {
            this.createArg().setValue(aLine);
        }
    }
    
    public void addEnvironment(final String name, final String value) {
        this.envVars.put(name, value);
    }
    
    public void addSystemEnvironment() {
        final Properties systemEnvVars = CommandLineUtils.getSystemEnvVars();
        for (final Object o : systemEnvVars.keySet()) {
            final String key = (String)o;
            if (!this.envVars.containsKey(key)) {
                this.addEnvironment(key, systemEnvVars.getProperty(key));
            }
        }
    }
    
    public String[] getEnvironmentVariables() {
        this.addSystemEnvironment();
        final String[] environmentVars = new String[this.envVars.size()];
        int i = 0;
        for (final String name : this.envVars.keySet()) {
            final String value = this.envVars.get(name);
            environmentVars[i] = name + "=" + value;
            ++i;
        }
        return environmentVars;
    }
    
    public String[] getCommandline() {
        final String[] args = this.getArguments();
        final String executable = this.getExecutable();
        if (executable == null) {
            return args;
        }
        final String[] result = new String[args.length + 1];
        result[0] = executable;
        System.arraycopy(args, 0, result, 1, args.length);
        return result;
    }
    
    private String[] getShellCommandline() {
        final List<String> shellCommandLine = this.getShell().getShellCommandLine(this.getArguments());
        return shellCommandLine.toArray(new String[shellCommandLine.size()]);
    }
    
    public String[] getArguments() {
        final List<String> result = new ArrayList<String>(this.arguments.size() * 2);
        for (final Arg argument : this.arguments) {
            final Argument arg = (Argument)argument;
            final String[] s = arg.getParts();
            if (s != null) {
                Collections.addAll(result, s);
            }
        }
        return result.toArray(new String[result.size()]);
    }
    
    @Override
    public String toString() {
        return StringUtils.join(this.getShellCommandline(), " ");
    }
    
    public Object clone() {
        throw new RuntimeException("Do we ever clone a commandline?");
    }
    
    public void setWorkingDirectory(final String path) {
        this.shell.setWorkingDirectory(path);
    }
    
    public void setWorkingDirectory(final File workingDirectory) {
        this.shell.setWorkingDirectory(workingDirectory);
    }
    
    public File getWorkingDirectory() {
        return this.shell.getWorkingDirectory();
    }
    
    public void clearArgs() {
        this.arguments.clear();
    }
    
    public Process execute() throws CommandLineException {
        final String[] environment = this.getEnvironmentVariables();
        final File workingDir = this.shell.getWorkingDirectory();
        Process process;
        try {
            if (workingDir == null) {
                process = Runtime.getRuntime().exec(this.getShellCommandline(), environment);
            }
            else {
                if (!workingDir.exists()) {
                    throw new CommandLineException("Working directory \"" + workingDir.getPath() + "\" does not exist!");
                }
                if (!workingDir.isDirectory()) {
                    throw new CommandLineException("Path \"" + workingDir.getPath() + "\" does not specify a directory.");
                }
                process = Runtime.getRuntime().exec(this.getShellCommandline(), environment, workingDir);
            }
        }
        catch (IOException ex) {
            throw new CommandLineException("Error while executing process.", ex);
        }
        return process;
    }
    
    void setShell(final Shell shell) {
        this.shell = shell;
    }
    
    public Shell getShell() {
        return this.shell;
    }
    
    public static class Argument implements Arg
    {
        private String[] parts;
        
        public void setValue(final String value) {
            if (value != null) {
                this.parts = new String[] { value };
            }
        }
        
        public void setLine(final String line) {
            if (line == null) {
                return;
            }
            try {
                this.parts = CommandLineUtils.translateCommandline(line);
            }
            catch (Exception e) {
                System.err.println("Error translating Commandline.");
            }
        }
        
        public void setFile(final File value) {
            this.parts = new String[] { value.getAbsolutePath() };
        }
        
        private String[] getParts() {
            return this.parts;
        }
    }
}
