// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli;

import java.util.Iterator;
import java.util.Locale;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.StringUtils;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Map;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.Os;
import java.util.Properties;
import java.io.InputStream;

public abstract class CommandLineUtils
{
    public static int executeCommandLine(final Commandline cl, final StreamConsumer systemOut, final StreamConsumer systemErr) throws CommandLineException {
        return executeCommandLine(cl, null, systemOut, systemErr, 0);
    }
    
    public static int executeCommandLine(final Commandline cl, final StreamConsumer systemOut, final StreamConsumer systemErr, final int timeoutInSeconds) throws CommandLineException {
        return executeCommandLine(cl, null, systemOut, systemErr, timeoutInSeconds);
    }
    
    public static int executeCommandLine(final Commandline cl, final InputStream systemIn, final StreamConsumer systemOut, final StreamConsumer systemErr) throws CommandLineException {
        return executeCommandLine(cl, systemIn, systemOut, systemErr, 0);
    }
    
    public static int executeCommandLine(final Commandline cl, final InputStream systemIn, final StreamConsumer systemOut, final StreamConsumer systemErr, final int timeoutInSeconds) throws CommandLineException {
        return executeCommandLine(cl, systemIn, systemOut, systemErr, timeoutInSeconds, null);
    }
    
    public static int executeCommandLine(final Commandline cl, final InputStream systemIn, final StreamConsumer systemOut, final StreamConsumer systemErr, final int timeoutInSeconds, final Runnable runAfterProcessTermination) throws CommandLineException {
        final CommandLineCallable future = executeCommandLineAsCallable(cl, systemIn, systemOut, systemErr, timeoutInSeconds, runAfterProcessTermination);
        return future.call();
    }
    
    private static CommandLineCallable executeCommandLineAsCallable(final Commandline cl, final InputStream systemIn, final StreamConsumer systemOut, final StreamConsumer systemErr, final int timeoutInSeconds, final Runnable runAfterProcessTermination) throws CommandLineException {
        if (cl == null) {
            throw new IllegalArgumentException("cl cannot be null.");
        }
        final Process p = cl.execute();
        final StreamFeeder inputFeeder = (systemIn != null) ? new StreamFeeder(systemIn, p.getOutputStream()) : null;
        final StreamPumper outputPumper = new StreamPumper(p.getInputStream(), systemOut);
        final StreamPumper errorPumper = new StreamPumper(p.getErrorStream(), systemErr);
        if (inputFeeder != null) {
            inputFeeder.start();
        }
        outputPumper.start();
        errorPumper.start();
        final ProcessHook processHook = new ProcessHook(p);
        ShutdownHookUtils.addShutDownHook(processHook);
        return new CommandLineCallable() {
            public Integer call() throws CommandLineException {
                try {
                    int returnValue;
                    if (timeoutInSeconds <= 0) {
                        returnValue = p.waitFor();
                    }
                    else {
                        final long now = System.currentTimeMillis();
                        final long timeoutInMillis = 1000L * timeoutInSeconds;
                        final long finish = now + timeoutInMillis;
                        while (isAlive(p) && System.currentTimeMillis() < finish) {
                            Thread.sleep(10L);
                        }
                        if (isAlive(p)) {
                            throw new InterruptedException("Process timeout out after " + timeoutInSeconds + " seconds");
                        }
                        returnValue = p.exitValue();
                    }
                    if (runAfterProcessTermination != null) {
                        runAfterProcessTermination.run();
                    }
                    waitForAllPumpers(inputFeeder, outputPumper, errorPumper);
                    if (outputPumper.getException() != null) {
                        throw new CommandLineException("Error inside systemOut parser", outputPumper.getException());
                    }
                    if (errorPumper.getException() != null) {
                        throw new CommandLineException("Error inside systemErr parser", errorPumper.getException());
                    }
                    return returnValue;
                }
                catch (InterruptedException ex) {
                    if (inputFeeder != null) {
                        inputFeeder.disable();
                    }
                    outputPumper.disable();
                    errorPumper.disable();
                    throw new CommandLineTimeOutException("Error while executing external command, process killed.", ex);
                }
                finally {
                    ShutdownHookUtils.removeShutdownHook(processHook);
                    processHook.run();
                    if (inputFeeder != null) {
                        inputFeeder.close();
                    }
                    outputPumper.close();
                    errorPumper.close();
                }
            }
        };
    }
    
    private static void waitForAllPumpers(final StreamFeeder inputFeeder, final StreamPumper outputPumper, final StreamPumper errorPumper) throws InterruptedException {
        if (inputFeeder != null) {
            inputFeeder.waitUntilDone();
        }
        outputPumper.waitUntilDone();
        errorPumper.waitUntilDone();
    }
    
    public static Properties getSystemEnvVars() {
        return getSystemEnvVars(!Os.isFamily("windows"));
    }
    
    public static Properties getSystemEnvVars(final boolean caseSensitive) {
        final Map<String, String> envs = System.getenv();
        return ensureCaseSensitivity(envs, caseSensitive);
    }
    
    private static boolean isAlive(final Process p) {
        if (p == null) {
            return false;
        }
        try {
            p.exitValue();
            return false;
        }
        catch (IllegalThreadStateException e) {
            return true;
        }
    }
    
    public static String[] translateCommandline(final String toProcess) throws Exception {
        if (toProcess == null || toProcess.length() == 0) {
            return new String[0];
        }
        final int normal = 0;
        final int inQuote = 1;
        final int inDoubleQuote = 2;
        int state = 0;
        final StringTokenizer tok = new StringTokenizer(toProcess, "\"' ", true);
        final List<String> tokens = new ArrayList<String>();
        final StringBuilder current = new StringBuilder();
        while (tok.hasMoreTokens()) {
            final String nextTok = tok.nextToken();
            switch (state) {
                case 1: {
                    if ("'".equals(nextTok)) {
                        state = 0;
                        continue;
                    }
                    current.append(nextTok);
                    continue;
                }
                case 2: {
                    if ("\"".equals(nextTok)) {
                        state = 0;
                        continue;
                    }
                    current.append(nextTok);
                    continue;
                }
                default: {
                    if ("'".equals(nextTok)) {
                        state = 1;
                        continue;
                    }
                    if ("\"".equals(nextTok)) {
                        state = 2;
                        continue;
                    }
                    if (!" ".equals(nextTok)) {
                        current.append(nextTok);
                        continue;
                    }
                    if (current.length() != 0) {
                        tokens.add(current.toString());
                        current.setLength(0);
                        continue;
                    }
                    continue;
                }
            }
        }
        if (current.length() != 0) {
            tokens.add(current.toString());
        }
        if (state == 1 || state == 2) {
            throw new CommandLineException("unbalanced quotes in " + toProcess);
        }
        return tokens.toArray(new String[tokens.size()]);
    }
    
    public static String toString(final String... line) {
        if (line == null || line.length == 0) {
            return "";
        }
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < line.length; ++i) {
            if (i > 0) {
                result.append(' ');
            }
            try {
                result.append(StringUtils.quoteAndEscape(line[i], '\"'));
            }
            catch (Exception e) {
                System.err.println("Error quoting argument: " + e.getMessage());
            }
        }
        return result.toString();
    }
    
    static Properties ensureCaseSensitivity(final Map<String, String> envs, final boolean preserveKeyCase) {
        final Properties envVars = new Properties();
        for (final Map.Entry<String, String> entry : envs.entrySet()) {
            envVars.put(preserveKeyCase ? entry.getKey() : entry.getKey().toUpperCase(Locale.ENGLISH), entry.getValue());
        }
        return envVars;
    }
    
    public static class StringStreamConsumer implements StreamConsumer
    {
        private final StringBuffer string;
        private static final String LS;
        
        public StringStreamConsumer() {
            this.string = new StringBuffer();
        }
        
        public void consumeLine(final String line) {
            this.string.append(line).append(StringStreamConsumer.LS);
        }
        
        public String getOutput() {
            return this.string.toString();
        }
        
        static {
            LS = System.getProperty("line.separator");
        }
    }
    
    private static class ProcessHook extends Thread
    {
        private final Process process;
        
        private ProcessHook(final Process process) {
            super("CommandlineUtils process shutdown hook");
            this.process = process;
            this.setContextClassLoader(null);
        }
        
        @Override
        public void run() {
            this.process.destroy();
        }
    }
}
