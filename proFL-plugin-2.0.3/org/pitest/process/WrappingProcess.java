// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.process;

import org.pitest.functional.FunctionalList;
import java.lang.management.RuntimeMXBean;
import org.pitest.functional.F;
import org.pitest.functional.FCollection;
import org.pitest.functional.prelude.Prelude;
import org.pitest.functional.predicate.Predicate;
import java.lang.management.ManagementFactory;
import org.pitest.functional.Option;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class WrappingProcess
{
    private final int port;
    private final ProcessArgs processArgs;
    private final Class<?> minionClass;
    private JavaProcess process;
    
    public WrappingProcess(final int port, final ProcessArgs args, final Class<?> minionClass) {
        this.port = port;
        this.processArgs = args;
        this.minionClass = minionClass;
    }
    
    public void start() throws IOException {
        final String[] args = { "" + this.port };
        final ProcessBuilder processBuilder = createProcessBuilder(this.processArgs.getJavaExecutable(), this.processArgs.getJvmArgs(), this.minionClass, Arrays.asList(args), this.processArgs.getJavaAgentFinder());
        this.configureProcessBuilder(processBuilder, this.processArgs.getWorkingDir(), this.processArgs.getLaunchClassPath(), this.processArgs.getEnvironmentVariables());
        final Process process = processBuilder.start();
        this.process = new JavaProcess(process, this.processArgs.getStdout(), this.processArgs.getStdErr());
    }
    
    private void configureProcessBuilder(final ProcessBuilder processBuilder, final File workingDirectory, final String initialClassPath, final Map<String, String> environmentVariables) {
        processBuilder.directory(workingDirectory);
        final Map<String, String> environment = processBuilder.environment();
        environment.put("CLASSPATH", initialClassPath);
        for (final Map.Entry<String, String> entry : environmentVariables.entrySet()) {
            environment.put(entry.getKey(), entry.getValue());
        }
    }
    
    public void destroy() {
        this.process.destroy();
    }
    
    private static ProcessBuilder createProcessBuilder(final String javaProc, final List<String> args, final Class<?> mainClass, final List<String> programArgs, final JavaAgent javaAgent) {
        final List<String> cmd = createLaunchArgs(javaProc, javaAgent, args, mainClass, programArgs);
        removeClassPathProperties(cmd);
        return new ProcessBuilder(cmd);
    }
    
    private static void removeClassPathProperties(final List<String> cmd) {
        for (int i = cmd.size() - 1; i >= 0; --i) {
            if (cmd.get(i).startsWith("-Djava.class.path")) {
                cmd.remove(i);
            }
        }
    }
    
    private static List<String> createLaunchArgs(final String javaProcess, final JavaAgent agentJarLocator, final List<String> args, final Class<?> mainClass, final List<String> programArgs) {
        final List<String> cmd = new ArrayList<String>();
        cmd.add(javaProcess);
        cmd.addAll(args);
        addPITJavaAgent(agentJarLocator, cmd);
        addLaunchJavaAgents(cmd);
        cmd.add(mainClass.getName());
        cmd.addAll(programArgs);
        return cmd;
    }
    
    private static void addPITJavaAgent(final JavaAgent agentJarLocator, final List<String> cmd) {
        final Option<String> jarLocation = agentJarLocator.getJarLocation();
        for (final String each : jarLocation) {
            cmd.add("-javaagent:" + each);
        }
    }
    
    private static void addLaunchJavaAgents(final List<String> cmd) {
        final RuntimeMXBean rt = ManagementFactory.getRuntimeMXBean();
        final FunctionalList<String> agents = FCollection.filter(rt.getInputArguments(), (F<String, Boolean>)Prelude.or(isJavaAgentParam(), isEnvironmentSetting()));
        cmd.addAll(agents);
    }
    
    private static Predicate<String> isEnvironmentSetting() {
        return new Predicate<String>() {
            @Override
            public Boolean apply(final String a) {
                return a.startsWith("-D");
            }
        };
    }
    
    private static Predicate<String> isJavaAgentParam() {
        return new Predicate<String>() {
            @Override
            public Boolean apply(final String a) {
                return a.toLowerCase().startsWith("-javaagent");
            }
        };
    }
    
    public JavaProcess getProcess() {
        return this.process;
    }
}
