// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.booterclient;

import org.apache.maven.surefire.util.UrlUtils;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.jar.JarEntry;
import java.io.OutputStream;
import java.util.jar.JarOutputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import org.apache.maven.plugin.surefire.util.Relocator;
import java.util.Iterator;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.StringUtils;
import java.io.IOException;
import org.apache.maven.surefire.booter.SurefireBooterForkException;
import org.apache.maven.surefire.booter.ForkedBooter;
import org.apache.maven.plugin.surefire.booterclient.lazytestprovider.OutputStreamFlushableCommandline;
import org.apache.maven.surefire.booter.StartupConfiguration;
import java.util.List;
import java.io.File;
import java.util.Map;
import java.util.Properties;
import org.apache.maven.surefire.booter.Classpath;

public class ForkConfiguration
{
    public static final String FORK_ONCE = "once";
    public static final String FORK_ALWAYS = "always";
    public static final String FORK_NEVER = "never";
    public static final String FORK_PERTHREAD = "perthread";
    private final int forkCount;
    private final boolean reuseForks;
    private final Classpath bootClasspathConfiguration;
    private final String jvmExecutable;
    private Properties modelProperties;
    private final String argLine;
    private final Map<String, String> environmentVariables;
    private final File workingDirectory;
    private final File tempDirectory;
    private final boolean debug;
    private final String debugLine;
    
    public ForkConfiguration(final Classpath bootClasspathConfiguration, final File tmpDir, final String debugLine, final String jvmExecutable, final File workingDirectory, final Properties modelProperties, final String argLine, final Map<String, String> environmentVariables, final boolean debugEnabled, final int forkCount, final boolean reuseForks) {
        this.bootClasspathConfiguration = bootClasspathConfiguration;
        this.tempDirectory = tmpDir;
        this.debugLine = debugLine;
        this.jvmExecutable = jvmExecutable;
        this.workingDirectory = workingDirectory;
        this.modelProperties = modelProperties;
        this.argLine = argLine;
        this.environmentVariables = environmentVariables;
        this.debug = debugEnabled;
        this.forkCount = forkCount;
        this.reuseForks = reuseForks;
    }
    
    public Classpath getBootClasspath() {
        return this.bootClasspathConfiguration;
    }
    
    public static String getEffectiveForkMode(final String forkMode) {
        if ("pertest".equalsIgnoreCase(forkMode)) {
            return "always";
        }
        if ("none".equalsIgnoreCase(forkMode)) {
            return "never";
        }
        if (forkMode.equals("never") || forkMode.equals("once") || forkMode.equals("always") || forkMode.equals("perthread")) {
            return forkMode;
        }
        throw new IllegalArgumentException("Fork mode " + forkMode + " is not a legal value");
    }
    
    public OutputStreamFlushableCommandline createCommandLine(final List<String> classPath, final StartupConfiguration startupConfiguration, final int threadNumber) throws SurefireBooterForkException {
        return this.createCommandLine(classPath, startupConfiguration.getClassLoaderConfiguration().isManifestOnlyJarRequestedAndUsable(), startupConfiguration.isShadefire(), startupConfiguration.isProviderMainClass() ? startupConfiguration.getActualClassName() : ForkedBooter.class.getName(), threadNumber);
    }
    
    OutputStreamFlushableCommandline createCommandLine(final List<String> classPath, final boolean useJar, final boolean shadefire, final String providerThatHasMainMethod, final int threadNumber) throws SurefireBooterForkException {
        final OutputStreamFlushableCommandline cli = new OutputStreamFlushableCommandline();
        cli.setExecutable(this.jvmExecutable);
        if (this.argLine != null) {
            cli.createArg().setLine(this.replaceThreadNumberPlaceholder(this.stripNewLines(this.replacePropertyExpressions(this.argLine)), threadNumber));
        }
        if (this.environmentVariables != null) {
            for (final String key : this.environmentVariables.keySet()) {
                final String value = this.environmentVariables.get(key);
                cli.addEnvironment(key, value);
            }
        }
        if (this.getDebugLine() != null && !"".equals(this.getDebugLine())) {
            cli.createArg().setLine(this.getDebugLine());
        }
        if (useJar) {
            File jarFile;
            try {
                jarFile = this.createJar(classPath, providerThatHasMainMethod);
            }
            catch (IOException e) {
                throw new SurefireBooterForkException("Error creating archive file", e);
            }
            cli.createArg().setValue("-jar");
            cli.createArg().setValue(jarFile.getAbsolutePath());
        }
        else {
            cli.addEnvironment("CLASSPATH", StringUtils.join(classPath.iterator(), File.pathSeparator));
            final String forkedBooter = (providerThatHasMainMethod != null) ? providerThatHasMainMethod : ForkedBooter.class.getName();
            cli.createArg().setValue(shadefire ? new Relocator().relocate(forkedBooter) : forkedBooter);
        }
        cli.setWorkingDirectory(this.workingDirectory.getAbsolutePath());
        return cli;
    }
    
    private String replaceThreadNumberPlaceholder(final String argLine, final int threadNumber) {
        return argLine.replace("${surefire.threadNumber}", String.valueOf(threadNumber)).replace("${surefire.forkNumber}", String.valueOf(threadNumber));
    }
    
    private String replacePropertyExpressions(String argLine) {
        if (argLine == null) {
            return null;
        }
        final Enumeration<?> e = this.modelProperties.propertyNames();
        while (e.hasMoreElements()) {
            final String key = e.nextElement().toString();
            final String field = "@{" + key + "}";
            if (argLine.contains(field)) {
                argLine = argLine.replace(field, this.modelProperties.getProperty(key, ""));
            }
        }
        return argLine;
    }
    
    private File createJar(final List<String> classPath, final String startClassName) throws IOException {
        final File file = File.createTempFile("surefirebooter", ".jar", this.tempDirectory);
        if (!this.debug) {
            file.deleteOnExit();
        }
        final FileOutputStream fos = new FileOutputStream(file);
        final JarOutputStream jos = new JarOutputStream(fos);
        jos.setLevel(0);
        final JarEntry je = new JarEntry("META-INF/MANIFEST.MF");
        jos.putNextEntry(je);
        final Manifest man = new Manifest();
        String cp = "";
        for (final String el : classPath) {
            cp = cp + UrlUtils.getURL(new File(el)).toExternalForm() + " ";
        }
        man.getMainAttributes().putValue("Manifest-Version", "1.0");
        man.getMainAttributes().putValue("Class-Path", cp.trim());
        man.getMainAttributes().putValue("Main-Class", startClassName);
        man.write(jos);
        jos.close();
        return file;
    }
    
    public boolean isDebug() {
        return this.debug;
    }
    
    public String stripNewLines(final String argline) {
        return argline.replace("\n", " ").replace("\r", " ");
    }
    
    public String getDebugLine() {
        return this.debugLine;
    }
    
    public File getTempDirectory() {
        return this.tempDirectory;
    }
    
    public int getForkCount() {
        return this.forkCount;
    }
    
    public boolean isReuseForks() {
        return this.reuseForks;
    }
}
