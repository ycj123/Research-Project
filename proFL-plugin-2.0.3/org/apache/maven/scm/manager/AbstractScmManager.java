// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.manager;

import org.apache.maven.scm.command.blame.BlameScmRequest;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.command.unedit.UnEditScmResult;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.command.mkdir.MkdirScmResult;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.command.export.ExportScmResult;
import org.apache.maven.scm.command.edit.EditScmResult;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.command.changelog.ChangeLogScmRequest;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmBranch;
import java.util.Date;
import org.apache.maven.scm.ScmBranchParameters;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.ScmFileSet;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.scm.repository.UnknownRepositoryStructure;
import java.io.File;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.repository.ScmRepository;
import java.util.Iterator;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.apache.maven.scm.provider.ScmUrlUtils;
import java.util.HashMap;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.ScmProvider;
import java.util.Map;

public abstract class AbstractScmManager implements ScmManager
{
    private Map<String, ScmProvider> scmProviders;
    private ScmLogger logger;
    private Map<String, String> userProviderTypes;
    
    public AbstractScmManager() {
        this.scmProviders = new HashMap<String, ScmProvider>();
        this.userProviderTypes = new HashMap<String, String>();
    }
    
    protected void setScmProviders(final Map<String, ScmProvider> providers) {
        this.scmProviders = providers;
    }
    
    @Deprecated
    protected void addScmProvider(final String providerType, final ScmProvider provider) {
        this.setScmProvider(providerType, provider);
    }
    
    public void setScmProvider(final String providerType, final ScmProvider provider) {
        this.scmProviders.put(providerType, provider);
    }
    
    protected abstract ScmLogger getScmLogger();
    
    public ScmProvider getProviderByUrl(final String scmUrl) throws ScmRepositoryException, NoSuchScmProviderException {
        if (scmUrl == null) {
            throw new NullPointerException("The scm url cannot be null.");
        }
        final String providerType = ScmUrlUtils.getProvider(scmUrl);
        return this.getProviderByType(providerType);
    }
    
    public void setScmProviderImplementation(final String providerType, final String providerImplementation) {
        this.userProviderTypes.put(providerType, providerImplementation);
    }
    
    public ScmProvider getProviderByType(final String providerType) throws NoSuchScmProviderException {
        if (this.logger == null) {
            this.logger = this.getScmLogger();
            for (final Map.Entry<String, ScmProvider> entry : this.scmProviders.entrySet()) {
                final ScmProvider p = entry.getValue();
                p.addListener(this.logger);
            }
        }
        String usedProviderType = System.getProperty("maven.scm.provider." + providerType + ".implementation");
        if (usedProviderType == null) {
            if (this.userProviderTypes.containsKey(providerType)) {
                usedProviderType = this.userProviderTypes.get(providerType);
            }
            else {
                usedProviderType = providerType;
            }
        }
        final ScmProvider scmProvider = this.scmProviders.get(usedProviderType);
        if (scmProvider == null) {
            throw new NoSuchScmProviderException(usedProviderType);
        }
        return scmProvider;
    }
    
    public ScmProvider getProviderByRepository(final ScmRepository repository) throws NoSuchScmProviderException {
        return this.getProviderByType(repository.getProvider());
    }
    
    public ScmRepository makeScmRepository(final String scmUrl) throws ScmRepositoryException, NoSuchScmProviderException {
        if (scmUrl == null) {
            throw new NullPointerException("The scm url cannot be null.");
        }
        final char delimiter = ScmUrlUtils.getDelimiter(scmUrl).charAt(0);
        final String providerType = ScmUrlUtils.getProvider(scmUrl);
        final ScmProvider provider = this.getProviderByType(providerType);
        final String scmSpecificUrl = this.cleanScmUrl(scmUrl.substring(providerType.length() + 5));
        final ScmProviderRepository providerRepository = provider.makeProviderScmRepository(scmSpecificUrl, delimiter);
        return new ScmRepository(providerType, providerRepository);
    }
    
    protected String cleanScmUrl(String scmUrl) {
        if (scmUrl == null) {
            throw new NullPointerException("The scm url cannot be null.");
        }
        String pathSeparator = "";
        int indexOfDoubleDot = -1;
        if (scmUrl.indexOf("../") > 1) {
            pathSeparator = "/";
            indexOfDoubleDot = scmUrl.indexOf("../");
        }
        if (scmUrl.indexOf("..\\") > 1) {
            pathSeparator = "\\";
            indexOfDoubleDot = scmUrl.indexOf("..\\");
        }
        if (indexOfDoubleDot > 1) {
            final int startOfTextToRemove = scmUrl.substring(0, indexOfDoubleDot - 1).lastIndexOf(pathSeparator);
            String beginUrl = "";
            if (startOfTextToRemove >= 0) {
                beginUrl = scmUrl.substring(0, startOfTextToRemove);
            }
            final String endUrl = scmUrl.substring(indexOfDoubleDot + 3);
            scmUrl = beginUrl + pathSeparator + endUrl;
            if (scmUrl.indexOf("../") > 1 || scmUrl.indexOf("..\\") > 1) {
                scmUrl = this.cleanScmUrl(scmUrl);
            }
        }
        return scmUrl;
    }
    
    public ScmRepository makeProviderScmRepository(final String providerType, final File path) throws ScmRepositoryException, UnknownRepositoryStructure, NoSuchScmProviderException {
        if (providerType == null) {
            throw new NullPointerException("The provider type cannot be null.");
        }
        final ScmProvider provider = this.getProviderByType(providerType);
        final ScmProviderRepository providerRepository = provider.makeProviderScmRepository(path);
        return new ScmRepository(providerType, providerRepository);
    }
    
    public List<String> validateScmRepository(final String scmUrl) {
        final List<String> messages = new ArrayList<String>();
        messages.addAll(ScmUrlUtils.validate(scmUrl));
        final String providerType = ScmUrlUtils.getProvider(scmUrl);
        ScmProvider provider;
        try {
            provider = this.getProviderByType(providerType);
        }
        catch (NoSuchScmProviderException e) {
            messages.add("No such provider installed '" + providerType + "'.");
            return messages;
        }
        final String scmSpecificUrl = this.cleanScmUrl(scmUrl.substring(providerType.length() + 5));
        final List<String> providerMessages = provider.validateScmUrl(scmSpecificUrl, ScmUrlUtils.getDelimiter(scmUrl).charAt(0));
        if (providerMessages == null) {
            throw new RuntimeException("The SCM provider cannot return null from validateScmUrl().");
        }
        messages.addAll(providerMessages);
        return messages;
    }
    
    public AddScmResult add(final ScmRepository repository, final ScmFileSet fileSet) throws ScmException {
        return this.getProviderByRepository(repository).add(repository, fileSet);
    }
    
    public AddScmResult add(final ScmRepository repository, final ScmFileSet fileSet, final String message) throws ScmException {
        return this.getProviderByRepository(repository).add(repository, fileSet, message);
    }
    
    public BranchScmResult branch(final ScmRepository repository, final ScmFileSet fileSet, final String branchName) throws ScmException {
        final ScmBranchParameters scmBranchParameters = new ScmBranchParameters("");
        return this.getProviderByRepository(repository).branch(repository, fileSet, branchName, scmBranchParameters);
    }
    
    public BranchScmResult branch(final ScmRepository repository, final ScmFileSet fileSet, final String branchName, final String message) throws ScmException {
        final ScmBranchParameters scmBranchParameters = new ScmBranchParameters(message);
        return this.getProviderByRepository(repository).branch(repository, fileSet, branchName, scmBranchParameters);
    }
    
    public ChangeLogScmResult changeLog(final ScmRepository repository, final ScmFileSet fileSet, final Date startDate, final Date endDate, final int numDays, final ScmBranch branch) throws ScmException {
        return this.getProviderByRepository(repository).changeLog(repository, fileSet, startDate, endDate, numDays, branch);
    }
    
    public ChangeLogScmResult changeLog(final ScmRepository repository, final ScmFileSet fileSet, final Date startDate, final Date endDate, final int numDays, final ScmBranch branch, final String datePattern) throws ScmException {
        return this.getProviderByRepository(repository).changeLog(repository, fileSet, startDate, endDate, numDays, branch, datePattern);
    }
    
    public ChangeLogScmResult changeLog(final ChangeLogScmRequest scmRequest) throws ScmException {
        return this.getProviderByRepository(scmRequest.getScmRepository()).changeLog(scmRequest);
    }
    
    public ChangeLogScmResult changeLog(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion startVersion, final ScmVersion endVersion) throws ScmException {
        return this.getProviderByRepository(repository).changeLog(repository, fileSet, startVersion, endVersion);
    }
    
    public ChangeLogScmResult changeLog(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion startRevision, final ScmVersion endRevision, final String datePattern) throws ScmException {
        return this.getProviderByRepository(repository).changeLog(repository, fileSet, startRevision, endRevision, datePattern);
    }
    
    public CheckInScmResult checkIn(final ScmRepository repository, final ScmFileSet fileSet, final String message) throws ScmException {
        return this.getProviderByRepository(repository).checkIn(repository, fileSet, message);
    }
    
    public CheckInScmResult checkIn(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion revision, final String message) throws ScmException {
        return this.getProviderByRepository(repository).checkIn(repository, fileSet, revision, message);
    }
    
    public CheckOutScmResult checkOut(final ScmRepository repository, final ScmFileSet fileSet) throws ScmException {
        return this.getProviderByRepository(repository).checkOut(repository, fileSet);
    }
    
    public CheckOutScmResult checkOut(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion version) throws ScmException {
        return this.getProviderByRepository(repository).checkOut(repository, fileSet, version);
    }
    
    public CheckOutScmResult checkOut(final ScmRepository repository, final ScmFileSet fileSet, final boolean recursive) throws ScmException {
        return this.getProviderByRepository(repository).checkOut(repository, fileSet, recursive);
    }
    
    public CheckOutScmResult checkOut(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion version, final boolean recursive) throws ScmException {
        return this.getProviderByRepository(repository).checkOut(repository, fileSet, version, recursive);
    }
    
    public DiffScmResult diff(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion startVersion, final ScmVersion endVersion) throws ScmException {
        return this.getProviderByRepository(repository).diff(repository, fileSet, startVersion, endVersion);
    }
    
    public EditScmResult edit(final ScmRepository repository, final ScmFileSet fileSet) throws ScmException {
        return this.getProviderByRepository(repository).edit(repository, fileSet);
    }
    
    public ExportScmResult export(final ScmRepository repository, final ScmFileSet fileSet) throws ScmException {
        return this.getProviderByRepository(repository).export(repository, fileSet);
    }
    
    public ExportScmResult export(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion version) throws ScmException {
        return this.getProviderByRepository(repository).export(repository, fileSet, version);
    }
    
    public ExportScmResult export(final ScmRepository repository, final ScmFileSet fileSet, final String outputDirectory) throws ScmException {
        return this.getProviderByRepository(repository).export(repository, fileSet, (ScmVersion)null, outputDirectory);
    }
    
    public ExportScmResult export(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion version, final String outputDirectory) throws ScmException {
        return this.getProviderByRepository(repository).export(repository, fileSet, version, outputDirectory);
    }
    
    public ListScmResult list(final ScmRepository repository, final ScmFileSet fileSet, final boolean recursive, final ScmVersion version) throws ScmException {
        return this.getProviderByRepository(repository).list(repository, fileSet, recursive, version);
    }
    
    public MkdirScmResult mkdir(final ScmRepository repository, final ScmFileSet fileSet, final String message, final boolean createInLocal) throws ScmException {
        return this.getProviderByRepository(repository).mkdir(repository, fileSet, message, createInLocal);
    }
    
    public RemoveScmResult remove(final ScmRepository repository, final ScmFileSet fileSet, final String message) throws ScmException {
        return this.getProviderByRepository(repository).remove(repository, fileSet, message);
    }
    
    public StatusScmResult status(final ScmRepository repository, final ScmFileSet fileSet) throws ScmException {
        return this.getProviderByRepository(repository).status(repository, fileSet);
    }
    
    public TagScmResult tag(final ScmRepository repository, final ScmFileSet fileSet, final String tagName) throws ScmException {
        return this.tag(repository, fileSet, tagName, "");
    }
    
    public TagScmResult tag(final ScmRepository repository, final ScmFileSet fileSet, final String tagName, final String message) throws ScmException {
        final ScmTagParameters scmTagParameters = new ScmTagParameters(message);
        return this.getProviderByRepository(repository).tag(repository, fileSet, tagName, scmTagParameters);
    }
    
    public UnEditScmResult unedit(final ScmRepository repository, final ScmFileSet fileSet) throws ScmException {
        return this.getProviderByRepository(repository).unedit(repository, fileSet);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet) throws ScmException {
        return this.getProviderByRepository(repository).update(repository, fileSet);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion version) throws ScmException {
        return this.getProviderByRepository(repository).update(repository, fileSet, version);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final boolean runChangelog) throws ScmException {
        return this.getProviderByRepository(repository).update(repository, fileSet, runChangelog);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion version, final boolean runChangelog) throws ScmException {
        return this.getProviderByRepository(repository).update(repository, fileSet, version, runChangelog);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final String datePattern) throws ScmException {
        return this.getProviderByRepository(repository).update(repository, fileSet, (ScmVersion)null, datePattern);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion version, final String datePattern) throws ScmException {
        return this.getProviderByRepository(repository).update(repository, fileSet, version, datePattern);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final Date lastUpdate) throws ScmException {
        return this.getProviderByRepository(repository).update(repository, fileSet, (ScmVersion)null, lastUpdate);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion version, final Date lastUpdate) throws ScmException {
        return this.getProviderByRepository(repository).update(repository, fileSet, version, lastUpdate);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final Date lastUpdate, final String datePattern) throws ScmException {
        return this.getProviderByRepository(repository).update(repository, fileSet, (ScmVersion)null, lastUpdate, datePattern);
    }
    
    public UpdateScmResult update(final ScmRepository repository, final ScmFileSet fileSet, final ScmVersion version, final Date lastUpdate, final String datePattern) throws ScmException {
        return this.getProviderByRepository(repository).update(repository, fileSet, version, lastUpdate, datePattern);
    }
    
    public BlameScmResult blame(final ScmRepository repository, final ScmFileSet fileSet, final String filename) throws ScmException {
        return this.getProviderByRepository(repository).blame(repository, fileSet, filename);
    }
    
    public BlameScmResult blame(final BlameScmRequest blameScmRequest) throws ScmException {
        return this.getProviderByRepository(blameScmRequest.getScmRepository()).blame(blameScmRequest);
    }
}
