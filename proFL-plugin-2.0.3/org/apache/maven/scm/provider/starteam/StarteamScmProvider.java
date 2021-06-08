// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam;

import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import org.apache.maven.scm.provider.starteam.command.remove.StarteamRemoveCommand;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.provider.starteam.command.unedit.StarteamUnEditCommand;
import org.apache.maven.scm.command.unedit.UnEditScmResult;
import org.apache.maven.scm.provider.starteam.command.edit.StarteamEditCommand;
import org.apache.maven.scm.command.edit.EditScmResult;
import org.apache.maven.scm.provider.starteam.command.update.StarteamUpdateCommand;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.provider.starteam.command.tag.StarteamTagCommand;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.provider.starteam.command.status.StarteamStatusCommand;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.provider.starteam.command.diff.StarteamDiffCommand;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.provider.starteam.command.checkout.StarteamCheckOutCommand;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.provider.starteam.command.checkin.StarteamCheckInCommand;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.provider.starteam.command.changelog.StarteamChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.starteam.command.add.StarteamAddCommand;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.starteam.repository.StarteamScmProviderRepository;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.AbstractScmProvider;

public class StarteamScmProvider extends AbstractScmProvider
{
    public static final String STARTEAM_URL_FORMAT = "[username[:password]@]hostname:port:/projectName/[viewName/][folderHiearchy/]";
    
    public ScmProviderRepository makeProviderScmRepository(final String scmSpecificUrl, final char delimiter) throws ScmRepositoryException {
        String user = null;
        String password = null;
        int index = scmSpecificUrl.indexOf(64);
        String rest = scmSpecificUrl;
        if (index != -1) {
            final String userAndPassword = scmSpecificUrl.substring(0, index);
            rest = scmSpecificUrl.substring(index + 1);
            index = userAndPassword.indexOf(58);
            if (index != -1) {
                user = userAndPassword.substring(0, index);
                password = userAndPassword.substring(index + 1);
            }
            else {
                user = userAndPassword;
            }
        }
        final String[] tokens = StringUtils.split(rest, Character.toString(delimiter));
        String host;
        int port;
        String path;
        if (tokens.length == 3) {
            host = tokens[0];
            port = Integer.valueOf(tokens[1]);
            path = tokens[2];
        }
        else {
            if (tokens.length != 2) {
                throw new ScmRepositoryException("Invalid SCM URL: The url has to be on the form: [username[:password]@]hostname:port:/projectName/[viewName/][folderHiearchy/]");
            }
            if (this.getLogger().isWarnEnabled()) {
                this.getLogger().warn("Your scm URL use a deprecated format. The new format is :[username[:password]@]hostname:port:/projectName/[viewName/][folderHiearchy/]");
            }
            host = tokens[0];
            if (tokens[1].indexOf(47) == -1) {
                throw new ScmRepositoryException("Invalid SCM URL: The url has to be on the form: [username[:password]@]hostname:port:/projectName/[viewName/][folderHiearchy/]");
            }
            final int at = tokens[1].indexOf(47);
            port = new Integer(tokens[1].substring(0, at));
            path = tokens[1].substring(at);
        }
        try {
            return new StarteamScmProviderRepository(user, password, host, port, path);
        }
        catch (Exception e) {
            throw new ScmRepositoryException("Invalid SCM URL: The url has to be on the form: [username[:password]@]hostname:port:/projectName/[viewName/][folderHiearchy/]");
        }
    }
    
    public String getScmType() {
        return "starteam";
    }
    
    @Override
    public AddScmResult add(final ScmProviderRepository repository, ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        fileSet = fixUpScmFileSetAbsoluteFilePath(fileSet);
        final StarteamAddCommand command = new StarteamAddCommand();
        command.setLogger(this.getLogger());
        return (AddScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public ChangeLogScmResult changelog(final ScmProviderRepository repository, ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        fileSet = fixUpScmFileSetAbsoluteFilePath(fileSet);
        final StarteamChangeLogCommand command = new StarteamChangeLogCommand();
        command.setLogger(this.getLogger());
        return (ChangeLogScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public CheckInScmResult checkin(final ScmProviderRepository repository, ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        fileSet = fixUpScmFileSetAbsoluteFilePath(fileSet);
        final StarteamCheckInCommand command = new StarteamCheckInCommand();
        command.setLogger(this.getLogger());
        return (CheckInScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public CheckOutScmResult checkout(final ScmProviderRepository repository, ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        fileSet = fixUpScmFileSetAbsoluteFilePath(fileSet);
        final StarteamCheckOutCommand command = new StarteamCheckOutCommand();
        command.setLogger(this.getLogger());
        return (CheckOutScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public DiffScmResult diff(final ScmProviderRepository repository, ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        fileSet = fixUpScmFileSetAbsoluteFilePath(fileSet);
        final StarteamDiffCommand command = new StarteamDiffCommand();
        command.setLogger(this.getLogger());
        return (DiffScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public StatusScmResult status(final ScmProviderRepository repository, ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        fileSet = fixUpScmFileSetAbsoluteFilePath(fileSet);
        final StarteamStatusCommand command = new StarteamStatusCommand();
        command.setLogger(this.getLogger());
        return (StatusScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public TagScmResult tag(final ScmProviderRepository repository, ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        fileSet = fixUpScmFileSetAbsoluteFilePath(fileSet);
        final StarteamTagCommand command = new StarteamTagCommand();
        command.setLogger(this.getLogger());
        return (TagScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public UpdateScmResult update(final ScmProviderRepository repository, ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        fileSet = fixUpScmFileSetAbsoluteFilePath(fileSet);
        final StarteamUpdateCommand command = new StarteamUpdateCommand();
        command.setLogger(this.getLogger());
        return (UpdateScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected EditScmResult edit(final ScmProviderRepository repository, ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        fileSet = fixUpScmFileSetAbsoluteFilePath(fileSet);
        final StarteamEditCommand command = new StarteamEditCommand();
        command.setLogger(this.getLogger());
        return (EditScmResult)command.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected UnEditScmResult unedit(final ScmProviderRepository repository, ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        fileSet = fixUpScmFileSetAbsoluteFilePath(fileSet);
        final StarteamUnEditCommand command = new StarteamUnEditCommand();
        command.setLogger(this.getLogger());
        return (UnEditScmResult)command.execute(repository, fileSet, parameters);
    }
    
    public RemoveScmResult remove(final ScmProviderRepository repository, ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        fileSet = fixUpScmFileSetAbsoluteFilePath(fileSet);
        final StarteamRemoveCommand command = new StarteamRemoveCommand();
        command.setLogger(this.getLogger());
        return (RemoveScmResult)command.execute(repository, fileSet, parameters);
    }
    
    private static ScmFileSet fixUpScmFileSetAbsoluteFilePath(final ScmFileSet currentFileSet) throws ScmException {
        ScmFileSet newFileSet = null;
        try {
            final File basedir = getAbsoluteFilePath(currentFileSet.getBasedir());
            final List<File> files = currentFileSet.getFileList();
            final List<File> relPathFiles = new ArrayList<File>(files.size());
            for (File file : files) {
                if (file.isAbsolute()) {
                    file = new File(getRelativePath(basedir, file));
                }
                relPathFiles.add(file);
            }
            newFileSet = new ScmFileSet(basedir, relPathFiles);
        }
        catch (IOException e) {
            throw new ScmException("Invalid file set.", e);
        }
        return newFileSet;
    }
    
    public static String getRelativePath(final File basedir, final File f) throws ScmException, IOException {
        final File fileOrDir = getAbsoluteFilePath(f);
        if (!fileOrDir.getCanonicalPath().startsWith(basedir.getCanonicalPath())) {
            throw new ScmException(fileOrDir.getPath() + " was not contained in " + basedir.getPath());
        }
        if (basedir.getCanonicalFile().equals(basedir.getAbsoluteFile())) {
            return fileOrDir.getPath().substring(basedir.getPath().length() + 1, fileOrDir.getPath().length());
        }
        return fileOrDir.getPath().substring(basedir.getCanonicalPath().length() + 1, fileOrDir.getPath().length());
    }
    
    private static File getAbsoluteFilePath(final File fileOrDir) throws IOException {
        String javaPathString = fileOrDir.getCanonicalPath().replace('\\', '/');
        if (javaPathString.endsWith("/")) {
            javaPathString = javaPathString.substring(0, javaPathString.length() - 1);
        }
        return new File(javaPathString);
    }
}
