// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.maven.scm.provider.perforce.command.PerforceWhereCommand;
import org.apache.maven.scm.provider.perforce.command.PerforceInfoCommand;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.IOException;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.StringUtils;
import org.codehaus.plexus.util.cli.Commandline;
import java.io.File;
import org.apache.maven.scm.provider.perforce.command.blame.PerforceBlameCommand;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.provider.perforce.command.update.PerforceUpdateCommand;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.provider.perforce.command.unedit.PerforceUnEditCommand;
import org.apache.maven.scm.command.unedit.UnEditScmResult;
import org.apache.maven.scm.provider.perforce.command.tag.PerforceTagCommand;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.provider.perforce.command.status.PerforceStatusCommand;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.provider.perforce.command.login.PerforceLoginCommand;
import org.apache.maven.scm.command.login.LoginScmResult;
import org.apache.maven.scm.provider.perforce.command.edit.PerforceEditCommand;
import org.apache.maven.scm.command.edit.EditScmResult;
import org.apache.maven.scm.provider.perforce.command.diff.PerforceDiffCommand;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.provider.perforce.command.checkout.PerforceCheckOutCommand;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.provider.perforce.command.checkin.PerforceCheckInCommand;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.provider.perforce.command.remove.PerforceRemoveCommand;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.provider.perforce.command.add.PerforceAddCommand;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.perforce.command.changelog.PerforceChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.apache.maven.scm.repository.ScmRepositoryException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.AbstractScmProvider;

public class PerforceScmProvider extends AbstractScmProvider
{
    private static final String[] PROTOCOLS;
    private static final String NEWLINE = "\r\n";
    public static final String DEFAULT_CLIENTSPEC_PROPERTY = "maven.scm.perforce.clientspec.name";
    private static Boolean live;
    
    @Override
    public boolean requiresEditMode() {
        return true;
    }
    
    public ScmProviderRepository makeProviderScmRepository(String scmSpecificUrl, final char delimiter) throws ScmRepositoryException {
        String protocol = null;
        int port = 0;
        String host = null;
        final int i0 = scmSpecificUrl.indexOf(delimiter);
        if (i0 > 0) {
            protocol = scmSpecificUrl.substring(0, i0);
            final HashSet<String> protocols = new HashSet<String>(Arrays.asList(PerforceScmProvider.PROTOCOLS));
            if (protocols.contains(protocol)) {
                scmSpecificUrl = scmSpecificUrl.substring(i0 + 1);
            }
            else {
                protocol = null;
            }
        }
        final int i2 = scmSpecificUrl.indexOf(delimiter);
        final int i3 = scmSpecificUrl.indexOf(delimiter, i2 + 1);
        String path;
        if (i2 > 0) {
            final int lastDelimiter = scmSpecificUrl.lastIndexOf(delimiter);
            path = scmSpecificUrl.substring(lastDelimiter + 1);
            host = scmSpecificUrl.substring(0, i2);
            if (i3 >= 0) {
                try {
                    final String tmp = scmSpecificUrl.substring(i2 + 1, lastDelimiter);
                    port = Integer.parseInt(tmp);
                }
                catch (NumberFormatException ex) {
                    throw new ScmRepositoryException("The port has to be a number.");
                }
            }
        }
        else {
            path = scmSpecificUrl;
        }
        String user = null;
        final String password = null;
        if (host != null && host.indexOf(64) > 1) {
            user = host.substring(0, host.indexOf(64));
            host = host.substring(host.indexOf(64) + 1);
        }
        if (path.indexOf(64) > 1) {
            if (host != null && this.getLogger().isWarnEnabled()) {
                this.getLogger().warn("Username as part of path is deprecated, the new format is scm:perforce:[username@]host:port:path_to_repository");
            }
            user = path.substring(0, path.indexOf(64));
            path = path.substring(path.indexOf(64) + 1);
        }
        return new PerforceScmProviderRepository(protocol, host, port, path, user, password);
    }
    
    public String getScmType() {
        return "perforce";
    }
    
    @Override
    protected ChangeLogScmResult changelog(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final PerforceChangeLogCommand command = new PerforceChangeLogCommand();
        command.setLogger(this.getLogger());
        return (ChangeLogScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    public AddScmResult add(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final PerforceAddCommand command = new PerforceAddCommand();
        command.setLogger(this.getLogger());
        return (AddScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected RemoveScmResult remove(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final PerforceRemoveCommand command = new PerforceRemoveCommand();
        command.setLogger(this.getLogger());
        return (RemoveScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected CheckInScmResult checkin(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final PerforceCheckInCommand command = new PerforceCheckInCommand();
        command.setLogger(this.getLogger());
        return (CheckInScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected CheckOutScmResult checkout(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final PerforceCheckOutCommand command = new PerforceCheckOutCommand();
        command.setLogger(this.getLogger());
        return (CheckOutScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected DiffScmResult diff(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final PerforceDiffCommand command = new PerforceDiffCommand();
        command.setLogger(this.getLogger());
        return (DiffScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected EditScmResult edit(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final PerforceEditCommand command = new PerforceEditCommand();
        command.setLogger(this.getLogger());
        return (EditScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected LoginScmResult login(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final PerforceLoginCommand command = new PerforceLoginCommand();
        command.setLogger(this.getLogger());
        return (LoginScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected StatusScmResult status(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final PerforceStatusCommand command = new PerforceStatusCommand();
        command.setLogger(this.getLogger());
        return (StatusScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected TagScmResult tag(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final PerforceTagCommand command = new PerforceTagCommand();
        command.setLogger(this.getLogger());
        return (TagScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected UnEditScmResult unedit(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final PerforceUnEditCommand command = new PerforceUnEditCommand();
        command.setLogger(this.getLogger());
        return (UnEditScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected UpdateScmResult update(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final PerforceUpdateCommand command = new PerforceUpdateCommand();
        command.setLogger(this.getLogger());
        return (UpdateScmResult)command.execute(repository, fileSet, params);
    }
    
    @Override
    protected BlameScmResult blame(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        final PerforceBlameCommand command = new PerforceBlameCommand();
        command.setLogger(this.getLogger());
        return (BlameScmResult)command.execute(repository, fileSet, params);
    }
    
    public static Commandline createP4Command(final PerforceScmProviderRepository repo, final File workingDir) {
        final Commandline command = new Commandline();
        command.setExecutable("p4");
        if (workingDir != null) {
            command.createArg().setValue("-d");
            command.createArg().setValue(workingDir.getAbsolutePath());
        }
        if (repo.getHost() != null) {
            command.createArg().setValue("-p");
            String value = "";
            if (!StringUtils.isBlank(repo.getProtocol())) {
                value = value + repo.getProtocol() + ":";
            }
            value += repo.getHost();
            if (repo.getPort() != 0) {
                value = value + ":" + Integer.toString(repo.getPort());
            }
            command.createArg().setValue(value);
        }
        if (StringUtils.isNotEmpty(repo.getUser())) {
            command.createArg().setValue("-u");
            command.createArg().setValue(repo.getUser());
        }
        if (StringUtils.isNotEmpty(repo.getPassword())) {
            command.createArg().setValue("-P");
            command.createArg().setValue(repo.getPassword());
        }
        return command;
    }
    
    public static String clean(final String string) {
        if (string.indexOf(" -P ") == -1) {
            return string;
        }
        final int idx = string.indexOf(" -P ") + 4;
        final int end = string.indexOf(32, idx);
        return string.substring(0, idx) + StringUtils.repeat("*", end - idx) + string.substring(end);
    }
    
    public static String getCanonicalRepoPath(final String repoPath) {
        if (repoPath.endsWith("/...")) {
            return repoPath;
        }
        if (repoPath.endsWith("/")) {
            return repoPath + "...";
        }
        return repoPath + "/...";
    }
    
    public static String createClientspec(final ScmLogger logger, final PerforceScmProviderRepository repo, final File workDir, final String repoPath) {
        final String clientspecName = getClientspecName(logger, repo, workDir);
        final String userName = getUsername(logger, repo);
        String rootDir;
        try {
            rootDir = workDir.getCanonicalPath();
        }
        catch (IOException ex) {
            rootDir = workDir.getAbsolutePath();
        }
        final StringBuilder buf = new StringBuilder();
        buf.append("Client: ").append(clientspecName).append("\r\n");
        buf.append("Root: ").append(rootDir).append("\r\n");
        buf.append("Owner: ").append(userName).append("\r\n");
        buf.append("View:").append("\r\n");
        buf.append("\t").append(getCanonicalRepoPath(repoPath));
        buf.append(" //").append(clientspecName).append("/...").append("\r\n");
        buf.append("Description:").append("\r\n");
        buf.append("\t").append("Created by maven-scm-provider-perforce").append("\r\n");
        return buf.toString();
    }
    
    public static String getClientspecName(final ScmLogger logger, final PerforceScmProviderRepository repo, final File workDir) {
        final String def = generateDefaultClientspecName(logger, repo, workDir);
        final String l = System.getProperty("maven.scm.perforce.clientspec.name", def);
        if (l == null || "".equals(l.trim())) {
            return def;
        }
        return l;
    }
    
    private static String generateDefaultClientspecName(final ScmLogger logger, final PerforceScmProviderRepository repo, final File workDir) {
        final String username = getUsername(logger, repo);
        String hostname;
        String path;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
            path = workDir.getCanonicalPath().replaceAll("[/ ~]", "-");
        }
        catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e2) {
            throw new RuntimeException(e2);
        }
        return username + "-" + hostname + "-MavenSCM-" + path;
    }
    
    private static String getUsername(final ScmLogger logger, final PerforceScmProviderRepository repo) {
        String username = PerforceInfoCommand.getInfo(logger, repo).getEntry("User name");
        if (username == null) {
            username = repo.getUser();
            if (username == null) {
                username = System.getProperty("user.name", "nouser");
            }
        }
        return username;
    }
    
    public static String getRepoPath(final ScmLogger log, final PerforceScmProviderRepository repo, File basedir) {
        final PerforceWhereCommand where = new PerforceWhereCommand(log, repo);
        if (basedir.toString().replace('\\', '/').endsWith("/target/checkout")) {
            final String dir = basedir.toString();
            basedir = new File(dir.substring(0, dir.length() - "/target/checkout".length()));
            log.debug("Fixing checkout URL: " + basedir);
        }
        final File pom = new File(basedir, "pom.xml");
        String loc = repo.getPath();
        log.debug("SCM path in pom: " + loc);
        if (pom.exists()) {
            loc = where.getDepotLocation(pom);
            if (loc == null) {
                loc = repo.getPath();
                log.debug("cannot find depot => using " + loc);
            }
            else if (loc.endsWith("/pom.xml")) {
                loc = loc.substring(0, loc.length() - "/pom.xml".length());
                log.debug("Actual POM location: " + loc);
                if (!repo.getPath().equals(loc)) {
                    log.info("The SCM location in your pom.xml (" + repo.getPath() + ") is not equal to the depot location (" + loc + ").  This happens frequently with branches.  " + "Ignoring the SCM location.");
                }
            }
        }
        return loc;
    }
    
    public static boolean isLive() {
        if (PerforceScmProvider.live == null) {
            if (!Boolean.getBoolean("maven.scm.testing")) {
                PerforceScmProvider.live = Boolean.TRUE;
            }
            else {
                try {
                    final Commandline command = new Commandline();
                    command.setExecutable("p4");
                    final Process proc = command.execute();
                    final BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                    String line;
                    while ((line = br.readLine()) != null) {}
                    final int rc = proc.exitValue();
                    PerforceScmProvider.live = ((rc == 0) ? Boolean.TRUE : Boolean.FALSE);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    PerforceScmProvider.live = Boolean.FALSE;
                }
            }
        }
        return PerforceScmProvider.live;
    }
    
    static {
        PROTOCOLS = new String[] { "tcp", "tcp4", "tcp6", "tcp46", "tcp64", "ssl", "ssl4", "ssl6", "ssl46", "ssl64" };
        PerforceScmProvider.live = null;
    }
}
