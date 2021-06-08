// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib;

import java.util.Iterator;
import java.util.ArrayList;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.command.mkdir.MkdirScmResult;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.command.login.LoginScmResult;
import org.apache.maven.scm.command.export.ExportScmResult;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import java.util.List;
import org.apache.maven.scm.repository.UnknownRepositoryStructure;
import java.io.IOException;
import org.codehaus.plexus.util.FileUtils;
import java.io.File;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.AbstractScmProvider;

public abstract class AbstractCvsScmProvider extends AbstractScmProvider
{
    public static final String TRANSPORT_EXT = "ext";
    public static final String TRANSPORT_LOCAL = "local";
    public static final String TRANSPORT_LSERVER = "lserver";
    public static final String TRANSPORT_PSERVER = "pserver";
    public static final String TRANSPORT_SSPI = "sspi";
    
    @Override
    public String getScmSpecificFilename() {
        return "CVS";
    }
    
    @Override
    public String sanitizeTagName(final String arg0) {
        if (this.validateTagName(arg0)) {
            return arg0;
        }
        if (arg0.equals("HEAD") || arg0.equals("BASE") || !arg0.matches("[A-Za-z].*")) {
            throw new RuntimeException("Unable to sanitize tag " + arg0 + ": must begin with a letter" + "and not be HEAD or BASE");
        }
        return arg0.replaceAll("[^A-Za-z0-9_-]", "_");
    }
    
    @Override
    public boolean validateTagName(final String arg0) {
        return arg0.matches("[A-Za-z][A-Za-z0-9_-]*") && !arg0.equals("HEAD") && !arg0.equals("BASE");
    }
    
    public ScmProviderRepository makeProviderScmRepository(final String scmSpecificUrl, final char delimiter) throws ScmRepositoryException {
        final ScmUrlParserResult result = this.parseScmUrl(scmSpecificUrl, delimiter);
        if (result.getMessages().size() > 0) {
            throw new ScmRepositoryException("The scm url is invalid.", result.getMessages());
        }
        return result.getRepository();
    }
    
    @Override
    public ScmProviderRepository makeProviderScmRepository(final File path) throws ScmRepositoryException, UnknownRepositoryStructure {
        if (path == null || !path.isDirectory()) {
            throw new ScmRepositoryException(path.getAbsolutePath() + " isn't a valid directory.");
        }
        final File cvsDirectory = new File(path, "CVS");
        if (!cvsDirectory.exists()) {
            throw new ScmRepositoryException(path.getAbsolutePath() + " isn't a cvs checkout directory.");
        }
        final File cvsRootFile = new File(cvsDirectory, "Root");
        final File moduleFile = new File(cvsDirectory, "Repository");
        String cvsRoot;
        try {
            cvsRoot = FileUtils.fileRead(cvsRootFile).trim().substring(1);
        }
        catch (IOException e) {
            throw new ScmRepositoryException("Can't read " + cvsRootFile.getAbsolutePath());
        }
        String module;
        try {
            module = FileUtils.fileRead(moduleFile).trim();
        }
        catch (IOException e) {
            throw new ScmRepositoryException("Can't read " + moduleFile.getAbsolutePath());
        }
        return this.makeProviderScmRepository(cvsRoot + ":" + module, ':');
    }
    
    @Override
    public List<String> validateScmUrl(final String scmSpecificUrl, final char delimiter) {
        final ScmUrlParserResult result = this.parseScmUrl(scmSpecificUrl, delimiter);
        return result.getMessages();
    }
    
    public String getScmType() {
        return "cvs";
    }
    
    @Override
    public AddScmResult add(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (AddScmResult)this.executeCommand(this.getAddCommand(), repository, fileSet, parameters);
    }
    
    public BranchScmResult branch(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (BranchScmResult)this.executeCommand(this.getBranchCommand(), repository, fileSet, parameters);
    }
    
    @Override
    protected BlameScmResult blame(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (BlameScmResult)this.executeCommand(this.getBlameCommand(), repository, fileSet, parameters);
    }
    
    public ChangeLogScmResult changelog(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (ChangeLogScmResult)this.executeCommand(this.getChangeLogCommand(), repository, fileSet, parameters);
    }
    
    public CheckInScmResult checkin(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (CheckInScmResult)this.executeCommand(this.getCheckInCommand(), repository, fileSet, parameters);
    }
    
    public CheckOutScmResult checkout(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (CheckOutScmResult)this.executeCommand(this.getCheckOutCommand(), repository, fileSet, parameters);
    }
    
    public DiffScmResult diff(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (DiffScmResult)this.executeCommand(this.getDiffCommand(), repository, fileSet, parameters);
    }
    
    @Override
    protected ExportScmResult export(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (ExportScmResult)this.executeCommand(this.getExportCommand(), repository, fileSet, parameters);
    }
    
    public LoginScmResult login(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (LoginScmResult)this.executeCommand(this.getLoginCommand(), repository, fileSet, parameters);
    }
    
    public RemoveScmResult remove(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (RemoveScmResult)this.executeCommand(this.getRemoveCommand(), repository, fileSet, parameters);
    }
    
    public StatusScmResult status(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (StatusScmResult)this.executeCommand(this.getStatusCommand(), repository, fileSet, parameters);
    }
    
    public TagScmResult tag(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (TagScmResult)this.executeCommand(this.getTagCommand(), repository, fileSet, parameters);
    }
    
    protected TagScmResult tag(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters, final ScmTagParameters scmParameters) throws ScmException {
        return (TagScmResult)this.getTagCommand().execute(repository, fileSet, parameters);
    }
    
    public UpdateScmResult update(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (UpdateScmResult)this.executeCommand(this.getUpdateCommand(), repository, fileSet, parameters);
    }
    
    @Override
    protected ListScmResult list(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (ListScmResult)this.executeCommand(this.getListCommand(), repository, fileSet, parameters);
    }
    
    @Override
    protected MkdirScmResult mkdir(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (MkdirScmResult)this.executeCommand(this.getMkdirCommand(), repository, fileSet, parameters);
    }
    
    public static String getRelativePath(final File basedir, final File f) throws ScmException, IOException {
        final File fileOrDir = getAbsoluteFilePath(f);
        if (!fileOrDir.getPath().startsWith(basedir.getPath())) {
            throw new ScmException(fileOrDir.getPath() + " was not contained in " + basedir.getPath());
        }
        return fileOrDir.getPath().substring(basedir.getPath().length() + 1, fileOrDir.getPath().length());
    }
    
    protected ScmUrlParserResult parseScmUrl(final String scmSpecificUrl, final char delimiter) {
        final ScmUrlParserResult result = new ScmUrlParserResult();
        final String[] tokens = StringUtils.split(scmSpecificUrl, Character.toString(delimiter));
        if (tokens.length < 3) {
            result.getMessages().add("The connection string contains too few tokens.");
            return result;
        }
        final String transport = tokens[0];
        String cvsroot;
        if (transport.equalsIgnoreCase("local")) {
            cvsroot = tokens[1];
        }
        else {
            if (!transport.equalsIgnoreCase("pserver") && !transport.equalsIgnoreCase("lserver") && !transport.equalsIgnoreCase("ext") && !transport.equalsIgnoreCase("sspi")) {
                result.getMessages().add("Unknown transport: " + transport);
                return result;
            }
            if (tokens.length != 4 && transport.equalsIgnoreCase("ext")) {
                result.getMessages().add("The connection string contains too few tokens.");
                return result;
            }
            if ((tokens.length < 4 || tokens.length > 6) && transport.equalsIgnoreCase("pserver")) {
                result.getMessages().add("The connection string contains too few tokens.");
                return result;
            }
            if (tokens.length < 4 || (tokens.length > 5 && !transport.equalsIgnoreCase("pserver"))) {
                result.getMessages().add("The connection string contains too few tokens.");
                return result;
            }
            if (tokens.length < 4 || (tokens.length > 5 && transport.equalsIgnoreCase("sspi"))) {
                result.getMessages().add("The connection string contains too few tokens.");
                return result;
            }
            if (transport.equalsIgnoreCase("lserver")) {
                cvsroot = tokens[1] + ":" + tokens[2];
            }
            else if (tokens.length == 4) {
                cvsroot = ":" + transport + ":" + tokens[1] + ":" + tokens[2];
            }
            else {
                cvsroot = ":" + transport + ":" + tokens[1] + ":" + tokens[2] + ":" + tokens[3];
            }
        }
        String user = null;
        String password = null;
        String host = null;
        String path = null;
        String module = null;
        int port = -1;
        if (transport.equalsIgnoreCase("pserver")) {
            port = 2401;
            if (tokens.length == 4) {
                final String userhost = tokens[1];
                final int index = userhost.indexOf(64);
                if (index == -1) {
                    host = userhost;
                }
                else {
                    user = userhost.substring(0, index);
                    host = userhost.substring(index + 1);
                }
                path = tokens[2];
                module = tokens[3];
            }
            else if (tokens.length == 6) {
                user = tokens[1];
                final String passhost = tokens[2];
                final int index = passhost.indexOf(64);
                if (index == -1) {
                    result.getMessages().add("The user_password_host part must be on the form: <username>:<password>@<hostname>.");
                    return result;
                }
                password = passhost.substring(0, index);
                host = passhost.substring(index + 1);
                port = Integer.valueOf(tokens[3]);
                path = tokens[4];
                module = tokens[5];
            }
            else {
                if (tokens[1].indexOf(64) > 0) {
                    final String userhost = tokens[1];
                    final int index = userhost.indexOf(64);
                    user = userhost.substring(0, index);
                    host = userhost.substring(index + 1);
                    port = new Integer(tokens[2]);
                }
                else if (tokens[2].indexOf(64) >= 0) {
                    user = tokens[1];
                    final String passhost = tokens[2];
                    final int index = passhost.indexOf(64);
                    password = passhost.substring(0, index);
                    host = passhost.substring(index + 1);
                }
                else {
                    try {
                        port = new Integer(tokens[2]);
                    }
                    catch (Exception e) {
                        result.getMessages().add("Your scm url is invalid.");
                        return result;
                    }
                    host = tokens[1];
                }
                path = tokens[3];
                module = tokens[4];
            }
            String userHost = host;
            if (user != null) {
                userHost = user + "@" + host;
            }
            cvsroot = ":" + transport + ":" + userHost + ":";
            if (port != -1) {
                cvsroot += port;
            }
            cvsroot += path;
        }
        else if (transport.equalsIgnoreCase("sspi")) {
            final String userhost = tokens[1];
            final int index = userhost.indexOf(64);
            if (index == -1) {
                user = "";
                host = userhost;
            }
            else {
                user = userhost.substring(0, index);
                host = userhost.substring(index + 1);
            }
            if (tokens.length == 4) {
                path = tokens[2];
                module = tokens[3];
            }
            else {
                try {
                    port = new Integer(tokens[2]);
                    path = tokens[3];
                    module = tokens[4];
                }
                catch (Exception e2) {
                    result.getMessages().add("Your scm url is invalid, could not get port value.");
                    return result;
                }
            }
            cvsroot = ":" + transport + ":" + host + ":";
            if (port != -1) {
                cvsroot += port;
            }
            cvsroot += path;
        }
        else {
            if (!transport.equalsIgnoreCase("local")) {
                final String userhost = tokens[1];
                final int index = userhost.indexOf(64);
                if (index == -1) {
                    host = userhost;
                }
                else {
                    user = userhost.substring(0, index);
                    host = userhost.substring(index + 1);
                }
            }
            if (transport.equals("local")) {
                path = tokens[1];
                module = tokens[2];
                if (module != null && module.startsWith("/")) {
                    module = module.substring(1);
                }
            }
            else if (tokens.length == 4) {
                path = tokens[2];
                module = tokens[3];
            }
            else {
                port = new Integer(tokens[2]);
                path = tokens[3];
                module = tokens[4];
            }
        }
        if (port == -1) {
            result.setRepository(new CvsScmProviderRepository(cvsroot, transport, user, password, host, path, module));
        }
        else {
            result.setRepository(new CvsScmProviderRepository(cvsroot, transport, user, password, host, port, path, module));
        }
        return result;
    }
    
    protected abstract Command getAddCommand();
    
    protected abstract Command getBranchCommand();
    
    protected abstract Command getBlameCommand();
    
    protected abstract Command getChangeLogCommand();
    
    protected abstract Command getCheckInCommand();
    
    protected abstract Command getCheckOutCommand();
    
    protected abstract Command getDiffCommand();
    
    protected abstract Command getExportCommand();
    
    protected abstract Command getListCommand();
    
    protected abstract Command getLoginCommand();
    
    protected abstract Command getRemoveCommand();
    
    protected abstract Command getStatusCommand();
    
    protected abstract Command getTagCommand();
    
    protected abstract Command getUpdateCommand();
    
    protected abstract Command getMkdirCommand();
    
    private ScmResult executeCommand(final Command command, final ScmProviderRepository repository, ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        fileSet = fixUpScmFileSetAbsoluteFilePath(fileSet);
        command.setLogger(this.getLogger());
        return command.execute(repository, fileSet, parameters);
    }
    
    private static ScmFileSet fixUpScmFileSetAbsoluteFilePath(final ScmFileSet currentFileSet) throws ScmException {
        ScmFileSet newFileSet = null;
        try {
            final File basedir = getAbsoluteFilePath(currentFileSet.getBasedir());
            final List<File> fixedFiles = new ArrayList<File>(currentFileSet.getFileList().size());
            for (final File file : currentFileSet.getFileList()) {
                if (file.isAbsolute()) {
                    fixedFiles.add(new File(getRelativePath(basedir, file)));
                }
                else {
                    fixedFiles.add(file);
                }
            }
            newFileSet = new ScmFileSet(basedir, fixedFiles);
        }
        catch (IOException e) {
            throw new ScmException("Invalid file set.", e);
        }
        return newFileSet;
    }
    
    private static File getAbsoluteFilePath(final File fileOrDir) throws IOException {
        String javaPathString = fileOrDir.getCanonicalPath().replace('\\', '/');
        if (javaPathString.endsWith("/")) {
            javaPathString = javaPathString.substring(0, javaPathString.length() - 1);
        }
        return new File(javaPathString);
    }
    
    public static class ScmUrlParserResult
    {
        private List<String> messages;
        private ScmProviderRepository repository;
        
        public ScmUrlParserResult() {
            this.messages = new ArrayList<String>();
        }
        
        public List<String> getMessages() {
            return this.messages;
        }
        
        public void setMessages(final List<String> messages) {
            this.messages = messages;
        }
        
        public ScmProviderRepository getRepository() {
            return this.repository;
        }
        
        public void setRepository(final ScmProviderRepository repository) {
            this.repository = repository;
        }
        
        public void resetMessages() {
            this.messages = new ArrayList<String>();
        }
    }
}
