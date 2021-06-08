// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev;

import org.apache.maven.scm.provider.accurev.command.blame.AccuRevBlameCommand;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.provider.accurev.command.remove.AccuRevRemoveCommand;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.provider.accurev.command.changelog.AccuRevChangeLogCommand;
import org.apache.maven.scm.provider.accurev.command.export.AccuRevExportCommand;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import java.util.Collections;
import org.apache.maven.scm.ChangeSet;
import java.util.Date;
import org.apache.maven.scm.ScmFile;
import org.apache.maven.scm.ChangeFile;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmRevision;
import org.apache.maven.scm.provider.accurev.command.update.AccuRevUpdateScmResult;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.provider.accurev.command.update.AccuRevUpdateCommand;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.provider.accurev.command.status.AccuRevStatusCommand;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.provider.accurev.command.tag.AccuRevTagCommand;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.provider.accurev.command.add.AccuRevAddCommand;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.repository.UnknownRepositoryStructure;
import java.io.File;
import org.apache.maven.scm.provider.accurev.command.checkin.AccuRevCheckInCommand;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.command.export.ExportScmResult;
import org.apache.maven.scm.provider.accurev.command.checkout.AccuRevCheckOutCommand;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.accurev.command.login.AccuRevLoginCommand;
import org.apache.maven.scm.command.login.LoginScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.maven.scm.repository.ScmRepositoryException;
import java.util.List;
import org.apache.maven.scm.provider.accurev.cli.AccuRevCommandLine;
import java.util.Map;
import org.apache.maven.scm.provider.accurev.util.QuotedPropertyParser;
import java.util.HashMap;
import org.codehaus.plexus.util.StringUtils;
import java.util.ArrayList;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.AbstractScmProvider;

public class AccuRevScmProvider extends AbstractScmProvider
{
    public static final String ACCUREV_EXECUTABLE_PROPERTY = "accurevExe";
    public static final String TAG_FORMAT_PROPERTY = "tagFormat";
    public static final String SYSTEM_PROPERTY_PREFIX = "maven.scm.accurev.";
    
    public String getScmType() {
        return "accurev";
    }
    
    public ScmProviderRepository makeProviderScmRepository(final String scmSpecificUrl, final char delimiter) throws ScmRepositoryException {
        final List<String> validationMessages = new ArrayList<String>();
        final String[] tokens = StringUtils.split(scmSpecificUrl, Character.toString(delimiter));
        String basisStream = null;
        String projectPath = null;
        int port = 5050;
        String host = null;
        String user = null;
        String password = null;
        final Map<String, String> properties = new HashMap<String, String>();
        properties.put("tagFormat", "%s");
        properties.put("accurevExe", "accurev");
        this.fillSystemProperties(properties);
        int i;
        for (i = 0; i < tokens.length; ++i) {
            final int at = tokens[i].indexOf(64);
            int slash = tokens[i].indexOf(47);
            slash = ((slash < 0) ? tokens[i].indexOf(92) : slash);
            final int qMark = tokens[i].indexOf(63);
            if (qMark == 0) {
                QuotedPropertyParser.parse(tokens[i].substring(1), properties);
            }
            else {
                if (slash == 0) {
                    projectPath = tokens[i].substring(1);
                    break;
                }
                if ((slash > 0 || at >= 0) && host == null && user == null) {
                    final int len = tokens[i].length();
                    if (at >= 0 && len > at) {
                        host = tokens[i].substring(at + 1);
                    }
                    if (slash > 0) {
                        user = tokens[i].substring(0, slash);
                        password = tokens[i].substring(slash + 1, (at < 0) ? len : at);
                    }
                    else {
                        user = tokens[i].substring(0, (at < 0) ? len : at);
                    }
                }
                else if (host != null && tokens[i].matches("^[0-9]+$")) {
                    port = Integer.parseInt(tokens[i]);
                }
                else {
                    basisStream = tokens[i];
                }
            }
        }
        if (i < tokens.length) {
            validationMessages.add("Unknown tokens in URL " + scmSpecificUrl);
        }
        final AccuRevScmProviderRepository repo = new AccuRevScmProviderRepository();
        repo.setLogger(this.getLogger());
        if (!StringUtils.isEmpty(user)) {
            repo.setUser(user);
        }
        if (!StringUtils.isEmpty(password)) {
            repo.setPassword(password);
        }
        if (!StringUtils.isEmpty(basisStream)) {
            repo.setStreamName(basisStream);
        }
        if (!StringUtils.isEmpty(projectPath)) {
            repo.setProjectPath(projectPath);
        }
        if (!StringUtils.isEmpty(host)) {
            repo.setHost(host);
        }
        repo.setPort(port);
        repo.setTagFormat(properties.get("tagFormat"));
        final AccuRevCommandLine accuRev = new AccuRevCommandLine(host, port);
        accuRev.setLogger(this.getLogger());
        accuRev.setExecutable(properties.get("accurevExe"));
        repo.setAccuRev(accuRev);
        return repo;
    }
    
    private void fillSystemProperties(final Map<String, String> properties) {
        final Set<String> propertyKeys = properties.keySet();
        for (final String key : propertyKeys) {
            final String systemPropertyKey = "maven.scm.accurev." + key;
            final String systemProperty = System.getProperty(systemPropertyKey);
            if (systemProperty != null) {
                properties.put(key, systemProperty);
            }
        }
    }
    
    @Override
    protected LoginScmResult login(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug(repository.toString());
        }
        final AccuRevLoginCommand command = new AccuRevLoginCommand(this.getLogger());
        return command.login(repository, fileSet, parameters);
    }
    
    @Override
    protected CheckOutScmResult checkout(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final AccuRevScmProviderRepository accuRevRepo = (AccuRevScmProviderRepository)repository;
        if (repository.isPersistCheckout() || !accuRevRepo.shouldUseExportForNonPersistentCheckout()) {
            final AccuRevCheckOutCommand command = new AccuRevCheckOutCommand(this.getLogger());
            return command.checkout(repository, fileSet, parameters);
        }
        final ExportScmResult result = this.export(repository, fileSet, parameters);
        if (result.isSuccess()) {
            return new CheckOutScmResult(result.getCommandLine(), result.getExportedFiles(), accuRevRepo.getExportRelativePath());
        }
        return new CheckOutScmResult(result.getCommandLine(), result.getProviderMessage(), result.getCommandOutput(), false);
    }
    
    @Override
    protected CheckInScmResult checkin(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final AccuRevCheckInCommand command = new AccuRevCheckInCommand(this.getLogger());
        return command.checkIn(repository, fileSet, parameters);
    }
    
    @Override
    public ScmProviderRepository makeProviderScmRepository(final File path) throws ScmRepositoryException, UnknownRepositoryStructure {
        return super.makeProviderScmRepository(path);
    }
    
    @Override
    public AddScmResult add(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final AccuRevAddCommand command = new AccuRevAddCommand(this.getLogger());
        return command.add(repository, fileSet, parameters);
    }
    
    @Override
    protected TagScmResult tag(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final AccuRevTagCommand command = new AccuRevTagCommand(this.getLogger());
        return command.tag(repository, fileSet, parameters);
    }
    
    @Override
    protected StatusScmResult status(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final AccuRevStatusCommand command = new AccuRevStatusCommand(this.getLogger());
        return command.status(repository, fileSet, parameters);
    }
    
    @Override
    protected UpdateScmResult update(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final AccuRevScmProviderRepository accurevRepo = (AccuRevScmProviderRepository)repository;
        final AccuRevUpdateCommand command = new AccuRevUpdateCommand(this.getLogger());
        final UpdateScmResult result = command.update(repository, fileSet, parameters);
        if (result.isSuccess() && parameters.getBoolean(CommandParameter.RUN_CHANGELOG_WITH_UPDATE)) {
            final AccuRevUpdateScmResult accuRevResult = (AccuRevUpdateScmResult)result;
            final ScmRevision fromRevision = new ScmRevision(accuRevResult.getFromRevision());
            final ScmRevision toRevision = new ScmRevision(accuRevResult.getToRevision());
            parameters.setScmVersion(CommandParameter.START_SCM_VERSION, fromRevision);
            parameters.setScmVersion(CommandParameter.END_SCM_VERSION, toRevision);
            final AccuRevVersion startVersion = accurevRepo.getAccuRevVersion(fromRevision);
            final AccuRevVersion endVersion = accurevRepo.getAccuRevVersion(toRevision);
            if (startVersion.getBasisStream().equals(endVersion.getBasisStream())) {
                final ChangeLogScmResult changeLogResult = this.changelog(repository, fileSet, parameters);
                if (changeLogResult.isSuccess()) {
                    result.setChanges(changeLogResult.getChangeLog().getChangeSets());
                }
                else {
                    this.getLogger().warn("Changelog from " + fromRevision + " to " + toRevision + " failed");
                }
            }
            else {
                final String comment = "Cross stream update result from " + startVersion + " to " + endVersion;
                final String author = "";
                final List<ScmFile> files = result.getUpdatedFiles();
                final List<ChangeFile> changeFiles = new ArrayList<ChangeFile>(files.size());
                for (final ScmFile scmFile : files) {
                    changeFiles.add(new ChangeFile(scmFile.getPath()));
                }
                final ChangeSet dummyChangeSet = new ChangeSet(new Date(), comment, author, changeFiles);
                final List<ChangeSet> changeSets = Collections.singletonList(dummyChangeSet);
                result.setChanges(changeSets);
            }
        }
        return result;
    }
    
    @Override
    protected ExportScmResult export(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final AccuRevExportCommand command = new AccuRevExportCommand(this.getLogger());
        return command.export(repository, fileSet, parameters);
    }
    
    @Override
    protected ChangeLogScmResult changelog(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final AccuRevChangeLogCommand command = new AccuRevChangeLogCommand(this.getLogger());
        return command.changelog(repository, fileSet, parameters);
    }
    
    @Override
    protected RemoveScmResult remove(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final AccuRevRemoveCommand command = new AccuRevRemoveCommand(this.getLogger());
        return command.remove(repository, fileSet, parameters);
    }
    
    @Override
    protected BlameScmResult blame(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final AccuRevBlameCommand blameCommand = new AccuRevBlameCommand(this.getLogger());
        return blameCommand.blame(repository, fileSet, parameters);
    }
}
