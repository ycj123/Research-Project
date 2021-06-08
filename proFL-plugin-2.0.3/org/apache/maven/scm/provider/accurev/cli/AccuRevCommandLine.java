// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.cli;

import org.apache.maven.scm.provider.accurev.Stream;
import java.util.HashMap;
import org.apache.maven.scm.provider.accurev.WorkSpace;
import java.util.Map;
import org.apache.maven.scm.command.blame.BlameLine;
import java.io.ByteArrayInputStream;
import org.codehaus.plexus.util.Os;
import org.apache.maven.scm.provider.accurev.FileDifference;
import org.apache.maven.scm.provider.accurev.Transaction;
import org.apache.maven.scm.provider.accurev.CategorisedElements;
import org.apache.maven.scm.provider.accurev.AccuRevVersion;
import org.apache.maven.scm.provider.accurev.AccuRevStat;
import java.util.Collection;
import org.apache.maven.scm.provider.accurev.AccuRevInfo;
import java.util.Collections;
import org.codehaus.plexus.util.StringUtils;
import java.util.ArrayList;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineException;
import java.util.Iterator;
import java.io.InputStream;
import org.apache.maven.scm.provider.accurev.AccuRevException;
import java.util.List;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.log.ScmLogger;
import java.io.File;
import org.apache.maven.scm.provider.accurev.AccuRev;

public class AccuRevCommandLine implements AccuRev
{
    private static final String[] EMPTY_STRING_ARRAY;
    private static final File CURRENT_DIR;
    private ScmLogger logger;
    private Commandline cl;
    private StringBuilder commandLines;
    private StringBuilder errorOutput;
    private StreamConsumer systemErr;
    private String[] hostArgs;
    private String[] authArgs;
    private String executable;
    private long executableModTime;
    private String clientVersion;
    
    public AccuRevCommandLine() {
        this.cl = new Commandline();
        this.commandLines = new StringBuilder();
        this.errorOutput = new StringBuilder();
        this.hostArgs = AccuRevCommandLine.EMPTY_STRING_ARRAY;
        this.authArgs = AccuRevCommandLine.EMPTY_STRING_ARRAY;
        this.executable = "accurev";
        this.reset();
    }
    
    public AccuRevCommandLine(final String host, final int port) {
        this();
        this.setServer(host, port);
    }
    
    public void setServer(final String host, final int port) {
        if (host != null) {
            this.hostArgs = new String[] { "-H", host + ":" + port };
        }
        else {
            this.hostArgs = AccuRevCommandLine.EMPTY_STRING_ARRAY;
        }
    }
    
    public void setExecutable(final String accuRevExe) {
        this.executable = accuRevExe;
        this.reset();
    }
    
    private boolean executeCommandLine(final File basedir, final String[] args, final Iterable<File> elements, final Pattern matchPattern, final List<File> matchedFiles) throws AccuRevException {
        final FileConsumer stdoutConsumer = new FileConsumer(matchedFiles, matchPattern);
        return this.executeCommandLine(basedir, args, elements, stdoutConsumer);
    }
    
    private boolean executeCommandLine(final File basedir, final String[] args, final Iterable<File> elements, final StreamConsumer stdoutConsumer) throws AccuRevException {
        this.setWorkingDirectory(basedir);
        this.setCommandLineArgs(args);
        if (elements != null) {
            for (final File file : elements) {
                String path = file.getPath();
                if ("\\.".equals(path)) {
                    path = "\\.\\";
                }
                this.cl.createArg().setValue(path);
            }
        }
        return this.executeCommandLine(null, stdoutConsumer) == 0;
    }
    
    private void setCommandLineArgs(final String[] args) {
        this.cl.clearArgs();
        if (args.length > 0) {
            this.cl.createArg().setValue(args[0]);
            this.cl.addArguments(this.hostArgs);
            this.cl.addArguments(this.authArgs);
        }
        for (int i = 1; i < args.length; ++i) {
            this.cl.createArg().setValue(args[i]);
        }
    }
    
    private boolean executeCommandLine(final String[] args) throws AccuRevException {
        return this.executeCommandLine(args, null, null) == 0;
    }
    
    private int executeCommandLine(final String[] args, final InputStream stdin, final StreamConsumer stdout) throws AccuRevException {
        this.setCommandLineArgs(args);
        return this.executeCommandLine(stdin, stdout);
    }
    
    private int executeCommandLine(final InputStream stdin, final StreamConsumer stdout) throws AccuRevException {
        this.commandLines.append(this.cl.toString());
        this.commandLines.append(';');
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug(this.cl.toString());
        }
        try {
            final int result = this.executeCommandLine(this.cl, stdin, new CommandOutputConsumer(this.getLogger(), stdout), this.systemErr);
            if (result != 0) {
                this.getLogger().debug("Non zero result - " + result);
            }
            return result;
        }
        catch (CommandLineException ex) {
            throw new AccuRevException("Error executing command " + this.cl.toString(), ex);
        }
    }
    
    protected int executeCommandLine(final Commandline cl, final InputStream stdin, final CommandOutputConsumer stdout, final StreamConsumer stderr) throws CommandLineException {
        final int result = CommandLineUtils.executeCommandLine(cl, stdin, stdout, stderr);
        stdout.waitComplete();
        return result;
    }
    
    protected Commandline getCommandline() {
        return this.cl;
    }
    
    public void reset() {
        this.cl = new Commandline();
        this.commandLines = new StringBuilder();
        this.errorOutput = new StringBuilder();
        this.systemErr = new ErrorConsumer(this.getLogger(), this.errorOutput);
        this.cl.getShell().setQuotedArgumentsEnabled(true);
        this.cl.setExecutable(this.executable);
        try {
            this.cl.addSystemEnvironment();
        }
        catch (Exception e) {
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Unable to obtain system environment", e);
            }
            else {
                this.getLogger().warn("Unable to obtain system environment");
            }
        }
    }
    
    public boolean mkws(final String basisStream, final String workspaceName, final File basedir) throws AccuRevException {
        this.setWorkingDirectory(basedir);
        final String[] mkws = { "mkws", "-b", basisStream, "-w", workspaceName, "-l", basedir.getAbsolutePath() };
        return this.executeCommandLine(mkws);
    }
    
    public List<File> update(final File baseDir, String transactionId) throws AccuRevException {
        if (transactionId == null) {
            transactionId = "highest";
        }
        final String[] update = { "update", "-t", transactionId };
        this.setWorkingDirectory(baseDir);
        final List<File> updatedFiles = new ArrayList<File>();
        final int ret = this.executeCommandLine(update, null, new FileConsumer(updatedFiles, FileConsumer.UPDATE_PATTERN));
        return (ret == 0) ? updatedFiles : null;
    }
    
    public List<File> add(final File basedir, List<File> elements, String message) throws AccuRevException {
        if (StringUtils.isBlank(message)) {
            message = "initial version (maven-scm)";
        }
        boolean recursive = false;
        if (elements == null || elements.isEmpty()) {
            elements = Collections.singletonList(AccuRevCommandLine.CURRENT_DIR);
            recursive = true;
        }
        else if (elements.size() == 1 && elements.toArray()[0].equals(AccuRevCommandLine.CURRENT_DIR)) {
            recursive = true;
        }
        final List<File> addedFiles = new ArrayList<File>();
        return this.executeCommandLine(basedir, new String[] { "add", "-c", message, recursive ? "-R" : null }, elements, FileConsumer.ADD_PATTERN, addedFiles) ? addedFiles : null;
    }
    
    public List<File> defunct(final File basedir, List<File> files, String message) throws AccuRevException {
        if (StringUtils.isBlank(message)) {
            message = "removed (maven-scm)";
        }
        if (files == null || files.isEmpty()) {
            files = Collections.singletonList(AccuRevCommandLine.CURRENT_DIR);
        }
        final ArrayList<File> defunctFiles = new ArrayList<File>();
        return this.executeCommandLine(basedir, new String[] { "defunct", "-c", message }, files, FileConsumer.DEFUNCT_PATTERN, defunctFiles) ? defunctFiles : null;
    }
    
    public List<File> promote(final File basedir, final List<File> files, String message) throws AccuRevException {
        if (StringUtils.isBlank(message)) {
            message = "promote (maven-scm)";
        }
        final List<File> promotedFiles = new ArrayList<File>();
        return this.executeCommandLine(basedir, new String[] { "promote", "-K", "-c", message }, files, FileConsumer.PROMOTE_PATTERN, promotedFiles) ? promotedFiles : null;
    }
    
    public String getCommandLines() {
        return this.commandLines.toString();
    }
    
    public String getErrorOutput() {
        return this.errorOutput.toString();
    }
    
    public void setLogger(final ScmLogger logger) {
        this.logger = logger;
    }
    
    public ScmLogger getLogger() {
        return this.logger;
    }
    
    public boolean mkdepot(final String depotName) throws AccuRevException {
        final String[] mkdepot = { "mkdepot", "-p", depotName };
        return this.executeCommandLine(mkdepot);
    }
    
    public boolean mkstream(final String backingStream, final String newStreamName) throws AccuRevException {
        final String[] mkstream = { "mkstream", "-b", backingStream, "-s", newStreamName };
        return this.executeCommandLine(mkstream);
    }
    
    public boolean promoteStream(final String subStream, final String commitMessage, final List<File> promotedFiles) throws AccuRevException {
        final String[] promote = { "promote", "-s", subStream, "-d" };
        return this.executeCommandLine(promote);
    }
    
    public List<File> promoteAll(final File baseDir, final String commitMessage) throws AccuRevException {
        this.setWorkingDirectory(baseDir);
        final String[] promote = { "promote", "-p", "-K", "-c", commitMessage };
        final List<File> promotedFiles = new ArrayList<File>();
        final int ret = this.executeCommandLine(promote, null, new FileConsumer(promotedFiles, FileConsumer.PROMOTE_PATTERN));
        return (ret == 0) ? promotedFiles : null;
    }
    
    public AccuRevInfo info(final File basedir) throws AccuRevException {
        this.setWorkingDirectory(basedir);
        final String[] info = { "info" };
        final AccuRevInfo result = new AccuRevInfo(basedir);
        this.executeCommandLine(info, null, new InfoConsumer(result));
        return result;
    }
    
    private void setWorkingDirectory(final File basedir) {
        if (basedir == null) {
            this.cl.setWorkingDirectory(".");
        }
        this.cl.setWorkingDirectory(basedir);
    }
    
    public boolean reactivate(final String workSpaceName) throws AccuRevException {
        final String[] reactivate = { "reactivate", "wspace", workSpaceName };
        return this.executeCommandLine(reactivate, null, new CommandOutputConsumer(this.getLogger(), null)) == 0;
    }
    
    public boolean rmws(final String workSpaceName) throws AccuRevException {
        final String[] rmws = { "rmws", "-s", workSpaceName };
        return this.executeCommandLine(rmws);
    }
    
    public String stat(final File element) throws AccuRevException {
        final String[] stat = { "stat", "-fx", element.getAbsolutePath() };
        final StatConsumer statConsumer = new StatConsumer(this.getLogger());
        this.executeCommandLine(stat, null, statConsumer);
        return statConsumer.getStatus();
    }
    
    public boolean chws(final File basedir, final String workSpaceName, final String newBasisStream) throws AccuRevException {
        this.setWorkingDirectory(basedir);
        return this.executeCommandLine(new String[] { "chws", "-s", workSpaceName, "-b", newBasisStream, "-l", "." });
    }
    
    public boolean mksnap(final String snapShotName, final String basisStream) throws AccuRevException {
        return this.executeCommandLine(new String[] { "mksnap", "-s", snapShotName, "-b", basisStream, "-t", "now" });
    }
    
    public List<File> statTag(final String streamName) throws AccuRevException {
        final List<File> taggedFiles = new ArrayList<File>();
        final String[] stat = { "stat", "-a", "-ffl", "-s", streamName };
        return this.executeCommandLine(null, stat, null, FileConsumer.STAT_PATTERN, taggedFiles) ? taggedFiles : null;
    }
    
    public List<File> stat(final File basedir, Collection<File> elements, final AccuRevStat statType) throws AccuRevException {
        boolean recursive = false;
        if (elements == null || elements.isEmpty()) {
            elements = Collections.singletonList(AccuRevCommandLine.CURRENT_DIR);
            recursive = true;
        }
        else if (elements.size() == 1 && elements.toArray()[0].equals(AccuRevCommandLine.CURRENT_DIR)) {
            recursive = true;
        }
        final String[] args = { "stat", "-ffr", statType.getStatArg(), recursive ? "-R" : null };
        final List<File> matchingElements = new ArrayList<File>();
        final boolean ret = this.executeCommandLine(basedir, args, elements, statType.getMatchPattern(), matchingElements);
        return ret ? matchingElements : null;
    }
    
    public List<File> pop(final File basedir, Collection<File> elements) throws AccuRevException {
        if (elements == null || elements.isEmpty()) {
            elements = Collections.singletonList(AccuRevCommandLine.CURRENT_DIR);
        }
        final String[] popws = { "pop", "-R" };
        final List<File> poppedFiles = new ArrayList<File>();
        final boolean ret = this.executeCommandLine(basedir, popws, elements, FileConsumer.POPULATE_PATTERN, poppedFiles);
        return ret ? poppedFiles : null;
    }
    
    public List<File> popExternal(final File basedir, final String versionSpec, String tranSpec, Collection<File> elements) throws AccuRevException {
        if (elements == null || elements.isEmpty()) {
            elements = Collections.singletonList(new File("/./"));
        }
        if (StringUtils.isBlank(tranSpec)) {
            tranSpec = "now";
        }
        String[] popArgs;
        if (AccuRevVersion.isNow(tranSpec)) {
            popArgs = new String[] { "pop", "-v", versionSpec, "-L", basedir.getAbsolutePath(), "-R" };
        }
        else {
            popArgs = new String[] { "pop", "-v", versionSpec, "-L", basedir.getAbsolutePath(), "-t", tranSpec, "-R" };
        }
        final List<File> poppedFiles = new ArrayList<File>();
        final boolean ret = this.executeCommandLine(basedir, popArgs, elements, FileConsumer.POPULATE_PATTERN, poppedFiles);
        return ret ? poppedFiles : null;
    }
    
    public CategorisedElements statBackingStream(final File basedir, final Collection<File> elements) throws AccuRevException {
        final CategorisedElements catElems = new CategorisedElements();
        if (elements.isEmpty()) {
            return catElems;
        }
        final String[] args = { "stat", "-b", "-ffr" };
        final boolean ret = this.executeCommandLine(basedir, args, elements, new StatBackingConsumer(catElems.getMemberElements(), catElems.getNonMemberElements()));
        return ret ? catElems : null;
    }
    
    public List<Transaction> history(final String baseStream, final String fromTimeSpec, final String toTimeSpec, final int count, final boolean depotHistory, final boolean transactionsOnly) throws AccuRevException {
        String timeSpec = fromTimeSpec;
        if (toTimeSpec != null) {
            timeSpec = timeSpec + "-" + toTimeSpec;
        }
        if (count > 0) {
            timeSpec = timeSpec + "." + count;
        }
        final String[] hist = { "hist", transactionsOnly ? "-ftx" : "-fx", depotHistory ? "-p" : "-s", baseStream, "-t", timeSpec };
        final ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        final HistoryConsumer stdout = new HistoryConsumer(this.getLogger(), transactions);
        return (this.executeCommandLine(hist, null, stdout) == 0) ? transactions : null;
    }
    
    public List<FileDifference> diff(final String baseStream, final String fromTimeSpec, final String toTimeSpec) throws AccuRevException {
        final String timeSpec = fromTimeSpec + "-" + toTimeSpec;
        final String[] diff = { "diff", "-fx", "-a", "-i", "-v", baseStream, "-V", baseStream, "-t", timeSpec };
        final List<FileDifference> results = new ArrayList<FileDifference>();
        final DiffConsumer stdout = new DiffConsumer(this.getLogger(), results);
        return (this.executeCommandLine(diff, null, stdout) < 2) ? results : null;
    }
    
    public boolean login(final String user, String password) throws AccuRevException {
        this.cl.setWorkingDirectory(".");
        this.authArgs = AccuRevCommandLine.EMPTY_STRING_ARRAY;
        final AuthTokenConsumer stdout = new AuthTokenConsumer();
        boolean result;
        if (Os.isFamily("windows")) {
            if (StringUtils.isBlank(password)) {
                password = "\"\"";
            }
            final String[] login = { "login", "-A", user, password };
            result = (this.executeCommandLine(login, null, stdout) == 0);
        }
        else {
            final String[] login = { "login", "-A", user };
            password = StringUtils.clean(password) + "\n";
            final byte[] bytes = password.getBytes();
            final ByteArrayInputStream stdin = new ByteArrayInputStream(bytes);
            result = (this.executeCommandLine(login, stdin, stdout) == 0);
        }
        this.authArgs = new String[] { "-A", stdout.getAuthToken() };
        return result;
    }
    
    public boolean logout() throws AccuRevException {
        final String[] logout = { "logout" };
        return this.executeCommandLine(logout);
    }
    
    public List<BlameLine> annotate(final File basedir, final File file) throws AccuRevException {
        final String[] annotate = { "annotate", "-ftud" };
        final List<BlameLine> lines = new ArrayList<BlameLine>();
        final AnnotateConsumer stdout = new AnnotateConsumer(lines, this.getLogger());
        return this.executeCommandLine(basedir, annotate, Collections.singletonList(file), stdout) ? lines : null;
    }
    
    public Map<String, WorkSpace> showRefTrees() throws AccuRevException {
        final String[] show = { "show", "-fx", "refs" };
        final Map<String, WorkSpace> refTrees = new HashMap<String, WorkSpace>();
        final WorkSpaceConsumer stdout = new WorkSpaceConsumer(this.getLogger(), refTrees);
        return (this.executeCommandLine(show, null, stdout) == 0) ? refTrees : null;
    }
    
    public Map<String, WorkSpace> showWorkSpaces() throws AccuRevException {
        final String[] show = { "show", "-a", "-fx", "wspaces" };
        final Map<String, WorkSpace> workSpaces = new HashMap<String, WorkSpace>();
        final WorkSpaceConsumer stdout = new WorkSpaceConsumer(this.getLogger(), workSpaces);
        return (this.executeCommandLine(show, null, stdout) == 0) ? workSpaces : null;
    }
    
    public Stream showStream(final String stream) throws AccuRevException {
        final String[] show = { "show", "-s", stream, "-fx", "streams" };
        final List<Stream> streams = new ArrayList<Stream>();
        final StreamsConsumer stdout = new StreamsConsumer(this.getLogger(), streams);
        return (this.executeCommandLine(show, null, stdout) == 0 && streams.size() == 1) ? streams.get(0) : null;
    }
    
    public String getExecutable() {
        return this.executable;
    }
    
    public String getClientVersion() throws AccuRevException {
        final long lastModified = new File(this.getExecutable()).lastModified();
        if (this.clientVersion == null || this.executableModTime != lastModified) {
            this.executableModTime = lastModified;
            final ClientVersionConsumer stdout = new ClientVersionConsumer();
            this.executeCommandLine(new String[0], null, stdout);
            this.clientVersion = stdout.getClientVersion();
        }
        return this.clientVersion;
    }
    
    public boolean syncReplica() throws AccuRevException {
        return this.executeCommandLine(new String[] { "replica", "sync" });
    }
    
    static {
        EMPTY_STRING_ARRAY = new String[0];
        CURRENT_DIR = new File(".");
    }
}
