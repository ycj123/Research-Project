// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.cli;

import org.mudebug.prapr.reloc.commons.cli.HelpFormatter;
import java.util.List;
import java.util.ArrayList;
import org.mudebug.prapr.reloc.commons.cli.CommandLineParser;
import org.mudebug.prapr.reloc.commons.cli.GnuParser;
import org.mudebug.prapr.reloc.commons.cli.OptionBuilder;
import org.mudebug.prapr.reloc.commons.cli.Options;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import java.io.InputStream;
import org.codehaus.plexus.util.Os;
import org.apache.maven.artifact.repository.DefaultArtifactRepository;
import org.apache.maven.artifact.repository.ArtifactRepositoryFactory;
import org.apache.maven.artifact.repository.layout.ArtifactRepositoryLayout;
import org.apache.maven.wagon.events.TransferListener;
import org.apache.maven.artifact.manager.WagonManager;
import org.codehaus.plexus.logging.Logger;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.monitor.event.EventMonitor;
import org.apache.maven.monitor.event.DefaultEventMonitor;
import org.apache.maven.plugin.Mojo;
import org.apache.maven.execution.DefaultMavenExecutionRequest;
import org.apache.maven.settings.RuntimeInfo;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.IOException;
import java.io.File;
import org.apache.maven.settings.MavenSettingsBuilder;
import org.apache.maven.profiles.ProfileManager;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.Maven;
import org.apache.maven.settings.Settings;
import org.apache.maven.monitor.event.EventDispatcher;
import org.mudebug.prapr.reloc.commons.cli.CommandLine;
import org.apache.maven.reactor.MavenExecutionException;
import org.codehaus.plexus.component.repository.exception.ComponentLifecycleException;
import java.util.StringTokenizer;
import org.apache.maven.profiles.DefaultProfileManager;
import org.codehaus.plexus.logging.LoggerManager;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.apache.maven.SettingsConfigurationException;
import java.util.Properties;
import org.codehaus.plexus.PlexusContainerException;
import org.apache.maven.monitor.event.DefaultEventDispatcher;
import org.mudebug.prapr.reloc.commons.cli.ParseException;
import org.codehaus.classworlds.ClassWorld;
import org.codehaus.plexus.embed.Embedder;

public class MavenCli
{
    public static final String OS_NAME;
    public static final String OS_ARCH;
    public static final String OS_VERSION;
    private static Embedder embedder;
    
    public static int main(final String[] args, final ClassWorld classWorld) {
        final CLIManager cliManager = new CLIManager();
        CommandLine commandLine;
        try {
            commandLine = cliManager.parse(args);
        }
        catch (ParseException e) {
            System.err.println("Unable to parse command line options: " + e.getMessage());
            cliManager.displayHelp();
            return 1;
        }
        if ("1.4".compareTo(System.getProperty("java.specification.version")) > 0) {
            System.err.println("Sorry, but JDK 1.4 or above is required to execute Maven. You appear to be using Java:");
            System.err.println("java version \"" + System.getProperty("java.version", "<unknown java version>") + "\"");
            System.err.println(System.getProperty("java.runtime.name", "<unknown runtime name>") + " (build " + System.getProperty("java.runtime.version", "<unknown runtime version>") + ")");
            System.err.println(System.getProperty("java.vm.name", "<unknown vm name>") + " (build " + System.getProperty("java.vm.version", "<unknown vm version>") + ", " + System.getProperty("java.vm.info", "<unknown vm info>") + ")");
            return 1;
        }
        final boolean debug = commandLine.hasOption('X');
        final boolean showErrors = debug || commandLine.hasOption('e');
        if (showErrors) {
            System.out.println("+ Error stacktraces are turned on.");
        }
        if (commandLine.hasOption('h')) {
            cliManager.displayHelp();
            return 0;
        }
        if (commandLine.hasOption('v')) {
            showVersion();
            return 0;
        }
        if (debug) {
            showVersion();
        }
        final EventDispatcher eventDispatcher = new DefaultEventDispatcher();
        MavenCli.embedder = new Embedder();
        try {
            MavenCli.embedder.start(classWorld);
        }
        catch (PlexusContainerException e2) {
            showFatalError("Unable to start the embedded plexus container", e2, showErrors);
            return 1;
        }
        final Properties executionProperties = new Properties();
        final Properties userProperties = new Properties();
        populateProperties(commandLine, executionProperties, userProperties);
        Settings settings;
        try {
            settings = buildSettings(commandLine);
        }
        catch (SettingsConfigurationException e3) {
            showError("Error reading settings.xml: " + e3.getMessage(), e3, showErrors);
            return 1;
        }
        catch (ComponentLookupException e4) {
            showFatalError("Unable to read settings.xml", e4, showErrors);
            return 1;
        }
        Maven maven = null;
        MavenExecutionRequest request = null;
        LoggerManager loggerManager = null;
        try {
            loggerManager = (LoggerManager)MavenCli.embedder.lookup(LoggerManager.ROLE);
            if (debug) {
                loggerManager.setThreshold(0);
            }
            else if (commandLine.hasOption('q')) {
                loggerManager.setThreshold(3);
            }
            final ProfileManager profileManager = new DefaultProfileManager(MavenCli.embedder.getContainer(), executionProperties);
            if (commandLine.hasOption('P')) {
                final String profilesLine = commandLine.getOptionValue('P');
                final StringTokenizer profileTokens = new StringTokenizer(profilesLine, ",");
                while (profileTokens.hasMoreTokens()) {
                    final String profileAction = profileTokens.nextToken().trim();
                    if (profileAction.startsWith("-")) {
                        profileManager.explicitlyDeactivate(profileAction.substring(1));
                    }
                    else if (profileAction.startsWith("+")) {
                        profileManager.explicitlyActivate(profileAction.substring(1));
                    }
                    else {
                        profileManager.explicitlyActivate(profileAction);
                    }
                }
            }
            request = createRequest(commandLine, settings, eventDispatcher, loggerManager, profileManager, executionProperties, userProperties, showErrors);
            setProjectFileOptions(commandLine, request);
            maven = createMavenInstance(settings.isInteractiveMode());
        }
        catch (ComponentLookupException e5) {
            showFatalError("Unable to configure the Maven application", e5, showErrors);
            return 1;
        }
        finally {
            if (loggerManager != null) {
                try {
                    MavenCli.embedder.release(loggerManager);
                }
                catch (ComponentLifecycleException e6) {
                    showFatalError("Error releasing logging manager", e6, showErrors);
                }
            }
        }
        try {
            maven.execute(request);
        }
        catch (MavenExecutionException e7) {
            return 1;
        }
        return 0;
    }
    
    private static Settings buildSettings(final CommandLine commandLine) throws ComponentLookupException, SettingsConfigurationException {
        String userSettingsPath = null;
        if (commandLine.hasOption('s')) {
            userSettingsPath = commandLine.getOptionValue('s');
        }
        Settings settings = null;
        final MavenSettingsBuilder settingsBuilder = (MavenSettingsBuilder)MavenCli.embedder.lookup(MavenSettingsBuilder.ROLE);
        try {
            if (userSettingsPath != null) {
                final File userSettingsFile = new File(userSettingsPath);
                if (userSettingsFile.exists() && !userSettingsFile.isDirectory()) {
                    settings = settingsBuilder.buildSettings(userSettingsFile);
                }
                else {
                    System.out.println("WARNING: Alternate user settings file: " + userSettingsPath + " is invalid. Using default path.");
                }
            }
            if (settings == null) {
                settings = settingsBuilder.buildSettings();
            }
        }
        catch (IOException e) {
            throw new SettingsConfigurationException("Error reading settings file", e);
        }
        catch (XmlPullParserException e2) {
            throw new SettingsConfigurationException(e2.getMessage(), e2.getDetail(), e2.getLineNumber(), e2.getColumnNumber());
        }
        if (commandLine.hasOption('B')) {
            settings.setInteractiveMode(false);
        }
        if (commandLine.hasOption("npr")) {
            settings.setUsePluginRegistry(false);
        }
        settings.setRuntimeInfo(createRuntimeInfo(commandLine, settings));
        return settings;
    }
    
    private static RuntimeInfo createRuntimeInfo(final CommandLine commandLine, final Settings settings) {
        final RuntimeInfo runtimeInfo = new RuntimeInfo(settings);
        if (commandLine.hasOption("cpu") || commandLine.hasOption("up")) {
            runtimeInfo.setPluginUpdateOverride(Boolean.TRUE);
        }
        else if (commandLine.hasOption("npu")) {
            runtimeInfo.setPluginUpdateOverride(Boolean.FALSE);
        }
        return runtimeInfo;
    }
    
    private static void showFatalError(final String message, final Exception e, final boolean show) {
        System.err.println("FATAL ERROR: " + message);
        if (show) {
            System.err.println("Error stacktrace:");
            e.printStackTrace();
        }
        else {
            System.err.println("For more information, run with the -e flag");
        }
    }
    
    private static void showError(final String message, final Exception e, final boolean show) {
        System.err.println(message);
        if (show) {
            System.err.println("Error stacktrace:");
            e.printStackTrace();
        }
    }
    
    private static MavenExecutionRequest createRequest(final CommandLine commandLine, final Settings settings, final EventDispatcher eventDispatcher, final LoggerManager loggerManager, final ProfileManager profileManager, final Properties executionProperties, final Properties userProperties, final boolean showErrors) throws ComponentLookupException {
        final ArtifactRepository localRepository = createLocalRepository(MavenCli.embedder, settings, commandLine);
        final File userDir = new File(System.getProperty("user.dir"));
        final MavenExecutionRequest request = new DefaultMavenExecutionRequest(localRepository, settings, eventDispatcher, commandLine.getArgList(), userDir.getPath(), profileManager, executionProperties, userProperties, showErrors);
        final Logger logger = loggerManager.getLoggerForComponent(Mojo.ROLE);
        if (logger != null) {
            request.addEventMonitor(new DefaultEventMonitor(logger));
        }
        if (commandLine.hasOption('N')) {
            request.setRecursive(false);
        }
        if (commandLine.hasOption("ff")) {
            request.setFailureBehavior("fail-fast");
        }
        else if (commandLine.hasOption("fae")) {
            request.setFailureBehavior("fail-at-end");
        }
        else if (commandLine.hasOption("fn")) {
            request.setFailureBehavior("fail-never");
        }
        return request;
    }
    
    private static void setProjectFileOptions(final CommandLine commandLine, final MavenExecutionRequest request) {
        if (commandLine.hasOption('r')) {
            request.setReactorActive(true);
        }
        else if (commandLine.hasOption('f')) {
            request.setPomFile(commandLine.getOptionValue('f'));
        }
    }
    
    private static Maven createMavenInstance(final boolean interactive) throws ComponentLookupException {
        final WagonManager wagonManager = (WagonManager)MavenCli.embedder.lookup(WagonManager.ROLE);
        if (interactive) {
            wagonManager.setDownloadMonitor(new ConsoleDownloadMonitor());
        }
        else {
            wagonManager.setDownloadMonitor(new BatchModeDownloadMonitor());
        }
        wagonManager.setInteractive(interactive);
        return (Maven)MavenCli.embedder.lookup(Maven.ROLE);
    }
    
    private static ArtifactRepository createLocalRepository(final Embedder embedder, final Settings settings, final CommandLine commandLine) throws ComponentLookupException {
        final ArtifactRepositoryLayout repositoryLayout = (ArtifactRepositoryLayout)embedder.lookup(ArtifactRepositoryLayout.ROLE, "default");
        final ArtifactRepositoryFactory artifactRepositoryFactory = (ArtifactRepositoryFactory)embedder.lookup(ArtifactRepositoryFactory.ROLE);
        String url = settings.getLocalRepository();
        if (!url.startsWith("file:")) {
            url = "file://" + url;
        }
        final ArtifactRepository localRepository = new DefaultArtifactRepository("local", url, repositoryLayout);
        boolean snapshotPolicySet = false;
        if (commandLine.hasOption('o')) {
            settings.setOffline(true);
            snapshotPolicySet = true;
        }
        if (!snapshotPolicySet && commandLine.hasOption('U')) {
            artifactRepositoryFactory.setGlobalUpdatePolicy("always");
        }
        if (commandLine.hasOption('C')) {
            System.out.println("+ Enabling strict checksum verification on all artifact downloads.");
            artifactRepositoryFactory.setGlobalChecksumPolicy("fail");
        }
        else if (commandLine.hasOption('c')) {
            System.out.println("+ Disabling strict checksum verification on all artifact downloads.");
            artifactRepositoryFactory.setGlobalChecksumPolicy("warn");
        }
        return localRepository;
    }
    
    private static void showVersion() {
        try {
            final Properties properties = new Properties();
            final InputStream resourceAsStream = MavenCli.class.getClassLoader().getResourceAsStream("META-INF/maven/org.apache.maven/maven-core/pom.properties");
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
                if (properties.getProperty("builtOn") != null) {
                    System.out.println("Maven version: " + properties.getProperty("version", "unknown") + " built on " + properties.getProperty("builtOn"));
                }
                else {
                    System.out.println("Maven version: " + properties.getProperty("version", "unknown"));
                }
            }
            else {
                System.out.println("Maven version: unknown");
            }
            System.out.println("Java version: " + System.getProperty("java.version", "<unknown java version>"));
            System.out.println("OS name: \"" + Os.OS_NAME + "\" version: \"" + Os.OS_VERSION + "\" arch: \"" + Os.OS_ARCH + "\" Family: \"" + Os.OS_FAMILY + "\"");
        }
        catch (IOException e) {
            System.err.println("Unable determine version from JAR file: " + e.getMessage());
        }
    }
    
    static void populateProperties(final CommandLine commandLine, final Properties executionProperties, final Properties userProperties) {
        try {
            final Properties envVars = CommandLineUtils.getSystemEnvVars();
            for (final Map.Entry e : envVars.entrySet()) {
                executionProperties.setProperty("env." + e.getKey().toString(), e.getValue().toString());
            }
        }
        catch (IOException e2) {
            System.err.println("Error getting environment vars for profile activation: " + e2);
        }
        if (commandLine.hasOption('D')) {
            final String[] defStrs = commandLine.getOptionValues('D');
            if (defStrs != null) {
                for (int j = 0; j < defStrs.length; ++j) {
                    setCliProperty(defStrs[j], userProperties);
                }
            }
            executionProperties.putAll(userProperties);
        }
        executionProperties.putAll(System.getProperties());
    }
    
    private static void setCliProperty(final String property, final Properties requestProperties) {
        final int i = property.indexOf("=");
        String name;
        String value;
        if (i <= 0) {
            name = property.trim();
            value = "true";
        }
        else {
            name = property.substring(0, i).trim();
            value = property.substring(i + 1).trim();
        }
        requestProperties.setProperty(name, value);
        System.setProperty(name, value);
    }
    
    static {
        OS_NAME = Os.OS_NAME;
        OS_ARCH = Os.OS_ARCH;
        OS_VERSION = Os.OS_VERSION;
    }
    
    static class CLIManager
    {
        public static final char ALTERNATE_POM_FILE = 'f';
        public static final char BATCH_MODE = 'B';
        public static final char SET_SYSTEM_PROPERTY = 'D';
        public static final char OFFLINE = 'o';
        public static final char REACTOR = 'r';
        public static final char QUIET = 'q';
        public static final char DEBUG = 'X';
        public static final char ERRORS = 'e';
        public static final char HELP = 'h';
        public static final char VERSION = 'v';
        private Options options;
        public static final char NON_RECURSIVE = 'N';
        public static final char UPDATE_SNAPSHOTS = 'U';
        public static final char ACTIVATE_PROFILES = 'P';
        public static final String FORCE_PLUGIN_UPDATES = "cpu";
        public static final String FORCE_PLUGIN_UPDATES2 = "up";
        public static final String SUPPRESS_PLUGIN_UPDATES = "npu";
        public static final String SUPPRESS_PLUGIN_REGISTRY = "npr";
        public static final char CHECKSUM_FAILURE_POLICY = 'C';
        public static final char CHECKSUM_WARNING_POLICY = 'c';
        private static final char ALTERNATE_USER_SETTINGS = 's';
        private static final String FAIL_FAST = "ff";
        private static final String FAIL_AT_END = "fae";
        private static final String FAIL_NEVER = "fn";
        
        public CLIManager() {
            this.options = new Options();
            final Options options = this.options;
            OptionBuilder.withLongOpt("file");
            OptionBuilder.hasArg();
            OptionBuilder.withDescription("Force the use of an alternate POM file.");
            options.addOption(OptionBuilder.create('f'));
            final Options options2 = this.options;
            OptionBuilder.withLongOpt("define");
            OptionBuilder.hasArg();
            OptionBuilder.withDescription("Define a system property");
            options2.addOption(OptionBuilder.create('D'));
            final Options options3 = this.options;
            OptionBuilder.withLongOpt("offline");
            OptionBuilder.withDescription("Work offline");
            options3.addOption(OptionBuilder.create('o'));
            final Options options4 = this.options;
            OptionBuilder.withLongOpt("help");
            OptionBuilder.withDescription("Display help information");
            options4.addOption(OptionBuilder.create('h'));
            final Options options5 = this.options;
            OptionBuilder.withLongOpt("version");
            OptionBuilder.withDescription("Display version information");
            options5.addOption(OptionBuilder.create('v'));
            final Options options6 = this.options;
            OptionBuilder.withLongOpt("quiet");
            OptionBuilder.withDescription("Quiet output - only show errors");
            options6.addOption(OptionBuilder.create('q'));
            final Options options7 = this.options;
            OptionBuilder.withLongOpt("debug");
            OptionBuilder.withDescription("Produce execution debug output");
            options7.addOption(OptionBuilder.create('X'));
            final Options options8 = this.options;
            OptionBuilder.withLongOpt("errors");
            OptionBuilder.withDescription("Produce execution error messages");
            options8.addOption(OptionBuilder.create('e'));
            final Options options9 = this.options;
            OptionBuilder.withLongOpt("reactor");
            OptionBuilder.withDescription("Execute goals for project found in the reactor");
            options9.addOption(OptionBuilder.create('r'));
            final Options options10 = this.options;
            OptionBuilder.withLongOpt("non-recursive");
            OptionBuilder.withDescription("Do not recurse into sub-projects");
            options10.addOption(OptionBuilder.create('N'));
            final Options options11 = this.options;
            OptionBuilder.withLongOpt("update-snapshots");
            OptionBuilder.withDescription("Forces a check for updated releases and snapshots on remote repositories");
            options11.addOption(OptionBuilder.create('U'));
            final Options options12 = this.options;
            OptionBuilder.withLongOpt("activate-profiles");
            OptionBuilder.withDescription("Comma-delimited list of profiles to activate");
            OptionBuilder.hasArg();
            options12.addOption(OptionBuilder.create('P'));
            final Options options13 = this.options;
            OptionBuilder.withLongOpt("batch-mode");
            OptionBuilder.withDescription("Run in non-interactive (batch) mode");
            options13.addOption(OptionBuilder.create('B'));
            final Options options14 = this.options;
            OptionBuilder.withLongOpt("check-plugin-updates");
            OptionBuilder.withDescription("Force upToDate check for any relevant registered plugins");
            options14.addOption(OptionBuilder.create("cpu"));
            final Options options15 = this.options;
            OptionBuilder.withLongOpt("update-plugins");
            OptionBuilder.withDescription("Synonym for cpu");
            options15.addOption(OptionBuilder.create("up"));
            final Options options16 = this.options;
            OptionBuilder.withLongOpt("no-plugin-updates");
            OptionBuilder.withDescription("Suppress upToDate check for any relevant registered plugins");
            options16.addOption(OptionBuilder.create("npu"));
            final Options options17 = this.options;
            OptionBuilder.withLongOpt("no-plugin-registry");
            OptionBuilder.withDescription("Don't use ~/.m2/plugin-registry.xml for plugin versions");
            options17.addOption(OptionBuilder.create("npr"));
            final Options options18 = this.options;
            OptionBuilder.withLongOpt("strict-checksums");
            OptionBuilder.withDescription("Fail the build if checksums don't match");
            options18.addOption(OptionBuilder.create('C'));
            final Options options19 = this.options;
            OptionBuilder.withLongOpt("lax-checksums");
            OptionBuilder.withDescription("Warn if checksums don't match");
            options19.addOption(OptionBuilder.create('c'));
            final Options options20 = this.options;
            OptionBuilder.withLongOpt("settings");
            OptionBuilder.withDescription("Alternate path for the user settings file");
            OptionBuilder.hasArg();
            options20.addOption(OptionBuilder.create('s'));
            final Options options21 = this.options;
            OptionBuilder.withLongOpt("fail-fast");
            OptionBuilder.withDescription("Stop at first failure in reactorized builds");
            options21.addOption(OptionBuilder.create("ff"));
            final Options options22 = this.options;
            OptionBuilder.withLongOpt("fail-at-end");
            OptionBuilder.withDescription("Only fail the build afterwards; allow all non-impacted builds to continue");
            options22.addOption(OptionBuilder.create("fae"));
            final Options options23 = this.options;
            OptionBuilder.withLongOpt("fail-never");
            OptionBuilder.withDescription("NEVER fail the build, regardless of project result");
            options23.addOption(OptionBuilder.create("fn"));
        }
        
        public CommandLine parse(final String[] args) throws ParseException {
            final String[] cleanArgs = this.cleanArgs(args);
            final CommandLineParser parser = (CommandLineParser)new GnuParser();
            return parser.parse(this.options, cleanArgs);
        }
        
        private String[] cleanArgs(final String[] args) {
            final List cleaned = new ArrayList();
            StringBuffer currentArg = null;
            for (int i = 0; i < args.length; ++i) {
                final String arg = args[i];
                boolean addedToBuffer = false;
                if (arg.startsWith("\"")) {
                    if (currentArg != null) {
                        cleaned.add(currentArg.toString());
                    }
                    currentArg = new StringBuffer(arg.substring(1));
                    addedToBuffer = true;
                }
                if (arg.endsWith("\"")) {
                    final String cleanArgPart = arg.substring(0, arg.length() - 1);
                    if (currentArg != null) {
                        if (addedToBuffer) {
                            currentArg.setLength(currentArg.length() - 1);
                        }
                        else {
                            currentArg.append(' ').append(cleanArgPart);
                        }
                        cleaned.add(currentArg.toString());
                    }
                    else {
                        cleaned.add(cleanArgPart);
                    }
                    currentArg = null;
                }
                else if (!addedToBuffer) {
                    if (currentArg != null) {
                        currentArg.append(' ').append(arg);
                    }
                    else {
                        cleaned.add(arg);
                    }
                }
            }
            if (currentArg != null) {
                cleaned.add(currentArg.toString());
            }
            final int cleanedSz = cleaned.size();
            String[] cleanArgs = null;
            if (cleanedSz == 0) {
                cleanArgs = args;
            }
            else {
                cleanArgs = cleaned.toArray(new String[cleanedSz]);
            }
            return cleanArgs;
        }
        
        public void displayHelp() {
            System.out.println();
            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("mvn [options] [<goal(s)>] [<phase(s)>]", "\nOptions:", this.options, "\n");
        }
    }
}
