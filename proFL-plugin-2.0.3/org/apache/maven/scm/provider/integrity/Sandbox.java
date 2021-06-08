// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity;

import com.mks.api.response.Field;
import java.util.Collection;
import org.apache.maven.scm.ChangeFile;
import org.apache.maven.scm.ChangeSet;
import java.util.Hashtable;
import org.apache.maven.scm.command.changelog.ChangeLogSet;
import java.util.Date;
import com.mks.api.MultiValue;
import java.util.Iterator;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.io.File;
import com.mks.api.response.Item;
import com.mks.api.response.WorkItem;
import com.mks.api.response.WorkItemIterator;
import com.mks.api.response.Response;
import com.mks.api.response.APIException;
import com.mks.api.Option;
import com.mks.api.Command;
import org.codehaus.plexus.util.StringUtils;
import java.text.SimpleDateFormat;

public class Sandbox
{
    public static final SimpleDateFormat RLOG_DATEFORMAT;
    private String fs;
    private APISession api;
    private Project siProject;
    private String sandboxDir;
    private String cpid;
    private boolean addSuccess;
    private boolean ciSuccess;
    
    public static String formatFilePatterns(final String pattern) {
        final StringBuilder sb = new StringBuilder();
        if (null != pattern && pattern.length() > 0) {
            final String[] tokens = StringUtils.split(pattern, ",");
            for (int i = 0; i < tokens.length; ++i) {
                final String tkn = tokens[i].trim();
                if (tkn.indexOf("file:") != 0 && tkn.indexOf("dir:") != 0) {
                    sb.append((tkn.indexOf(46) > 0) ? StringUtils.replaceOnce(tkn, "**/", "file:") : StringUtils.replaceOnce(tkn, "**/", "dir:"));
                }
                else {
                    sb.append(tkn);
                }
                sb.append((i < tokens.length) ? "," : "");
            }
        }
        return sb.toString();
    }
    
    public Sandbox(final APISession api, final Project cmProject, final String dir) {
        this.fs = System.getProperty("file.separator");
        this.siProject = cmProject;
        this.sandboxDir = dir;
        this.api = api;
        this.cpid = System.getProperty("maven.scm.integrity.cpid");
        this.cpid = ((null == this.cpid || this.cpid.length() == 0) ? ":none" : this.cpid);
        this.addSuccess = true;
        this.ciSuccess = true;
    }
    
    private boolean isValidSandbox(final String sandbox) throws APIException {
        final Command cmd = new Command("si", "sandboxinfo");
        cmd.addOption(new Option("sandbox", sandbox));
        this.api.getLogger().debug("Validating existing sandbox: " + sandbox);
        final Response res = this.api.runCommand(cmd);
        final WorkItemIterator wit = res.getWorkItems();
        try {
            final WorkItem wi = wit.next();
            return wi.getField("fullConfigSyntax").getValueAsString().equalsIgnoreCase(this.siProject.getConfigurationPath());
        }
        catch (APIException aex) {
            final ExceptionHandler eh = new ExceptionHandler(aex);
            this.api.getLogger().error("MKS API Exception: " + eh.getMessage());
            this.api.getLogger().debug(eh.getCommand() + " completed with exit code " + eh.getExitCode());
            return false;
        }
    }
    
    private boolean isDelta(final Item wfdelta) {
        return wfdelta.getField("isDelta").getBoolean();
    }
    
    private Response add(final File memberFile, final String message) throws APIException {
        this.api.getLogger().info("Adding member: " + memberFile.getAbsolutePath());
        final Command siAdd = new Command("si", "add");
        siAdd.addOption(new Option("onExistingArchive", "sharearchive"));
        siAdd.addOption(new Option("cpid", this.cpid));
        if (null != message && message.length() > 0) {
            siAdd.addOption(new Option("description", message));
        }
        siAdd.addOption(new Option("cwd", memberFile.getParentFile().getAbsolutePath()));
        siAdd.addSelection(memberFile.getName());
        return this.api.runCommand(siAdd);
    }
    
    private Response checkin(final File memberFile, final String relativeName, final String message) throws APIException {
        this.api.getLogger().info("Checking in member:  " + memberFile.getAbsolutePath());
        final Command sici = new Command("si", "ci");
        sici.addOption(new Option("cpid", this.cpid));
        if (null != message && message.length() > 0) {
            sici.addOption(new Option("description", message));
        }
        sici.addOption(new Option("cwd", memberFile.getParentFile().getAbsolutePath()));
        sici.addSelection(relativeName);
        return this.api.runCommand(sici);
    }
    
    private Response dropMember(final File memberFile, final String relativeName) throws APIException {
        this.api.getLogger().info("Dropping member " + memberFile.getAbsolutePath());
        final Command siDrop = new Command("si", "drop");
        siDrop.addOption(new Option("cwd", memberFile.getParentFile().getAbsolutePath()));
        siDrop.addOption(new Option("noconfirm"));
        siDrop.addOption(new Option("cpid", this.cpid));
        siDrop.addOption(new Option("delete"));
        siDrop.addSelection(relativeName);
        return this.api.runCommand(siDrop);
    }
    
    private boolean hasMemberChanged(final File memberFile, final String relativeName) {
        final Command siDiff = new Command("si", "diff");
        siDiff.addOption(new Option("cwd", memberFile.getParentFile().getAbsolutePath()));
        siDiff.addSelection(relativeName);
        try {
            final Response res = this.api.runCommand(siDiff);
            try {
                return res.getWorkItems().next().getResult().getField("resultant").getItem().getField("different").getBoolean();
            }
            catch (NullPointerException npe) {
                this.api.getLogger().warn("Couldn't figure out differences for file: " + memberFile.getAbsolutePath());
                this.api.getLogger().warn("Null value found along response object for WorkItem/Result/Field/Item/Field.getBoolean()");
                this.api.getLogger().warn("Proceeding with the assumption that the file has changed!");
            }
        }
        catch (APIException aex) {
            final ExceptionHandler eh = new ExceptionHandler(aex);
            this.api.getLogger().warn("Couldn't figure out differences for file: " + memberFile.getAbsolutePath());
            this.api.getLogger().warn(eh.getMessage());
            this.api.getLogger().warn("Proceeding with the assumption that the file has changed!");
            this.api.getLogger().debug(eh.getCommand() + " completed with exit Code " + eh.getExitCode());
        }
        return true;
    }
    
    public String getSandboxDir() {
        return this.sandboxDir;
    }
    
    public Response lock(final File memberFile, final String relativeName) throws APIException {
        this.api.getLogger().debug("Locking member: " + memberFile.getAbsolutePath());
        final Command siLock = new Command("si", "lock");
        siLock.addOption(new Option("revision", ":member"));
        siLock.addOption(new Option("cpid", this.cpid));
        siLock.addOption(new Option("cwd", memberFile.getParentFile().getAbsolutePath()));
        siLock.addSelection(relativeName);
        return this.api.runCommand(siLock);
    }
    
    public Response unlock(final File memberFile, final String relativeName) throws APIException {
        this.api.getLogger().debug("Unlocking member: " + memberFile.getAbsolutePath());
        final Command siUnlock = new Command("si", "unlock");
        siUnlock.addOption(new Option("revision", ":member"));
        siUnlock.addOption(new Option("action", "remove"));
        siUnlock.addOption(new Option("cwd", memberFile.getParentFile().getAbsolutePath()));
        siUnlock.addSelection(relativeName);
        return this.api.runCommand(siUnlock);
    }
    
    public Response drop() throws APIException {
        final File project = new File(this.siProject.getProjectName());
        final File sandboxpj = new File(this.sandboxDir + this.fs + project.getName());
        this.api.getLogger().debug("Sandbox Project File: " + sandboxpj.getAbsolutePath());
        final Command cmd = new Command("si", "dropsandbox");
        cmd.addOption(new Option("delete", "members"));
        cmd.addOption(new Option("sandbox", sandboxpj.getAbsolutePath()));
        cmd.addOption(new Option("cwd", this.sandboxDir));
        return this.api.runCommand(cmd);
    }
    
    public boolean create() throws APIException {
        final File project = new File(this.siProject.getProjectName());
        final File sandboxpj = new File(this.sandboxDir + this.fs + project.getName());
        this.api.getLogger().debug("Sandbox Project File: " + sandboxpj.getAbsolutePath());
        if (!sandboxpj.isFile()) {
            this.api.getLogger().debug("Creating Sandbox in " + this.sandboxDir + " for project " + this.siProject.getConfigurationPath());
            try {
                final Command cmd = new Command("si", "createsandbox");
                cmd.addOption(new Option("recurse"));
                cmd.addOption(new Option("nopopulate"));
                cmd.addOption(new Option("project", this.siProject.getConfigurationPath()));
                cmd.addOption(new Option("cwd", this.sandboxDir));
                this.api.runCommand(cmd);
            }
            catch (APIException aex) {
                final ExceptionHandler eh = new ExceptionHandler(aex);
                if (eh.getMessage().indexOf("There is already a registered entry") > 0) {
                    return this.create();
                }
                throw aex;
            }
            return true;
        }
        if (this.isValidSandbox(sandboxpj.getAbsolutePath())) {
            this.api.getLogger().debug("Reusing existing Sandbox in " + this.sandboxDir + " for project " + this.siProject.getConfigurationPath());
            return true;
        }
        this.api.getLogger().error("An invalid Sandbox exists in " + this.sandboxDir + ". Please provide a different location!");
        return false;
    }
    
    public Response resync() throws APIException {
        this.api.getLogger().debug("Resynchronizing Sandbox in " + this.sandboxDir + " for project " + this.siProject.getConfigurationPath());
        final Command cmd = new Command("si", "resync");
        cmd.addOption(new Option("recurse"));
        cmd.addOption(new Option("populate"));
        cmd.addOption(new Option("cwd", this.sandboxDir));
        return this.api.runCommand(cmd);
    }
    
    public Response makeWriteable() throws APIException {
        this.api.getLogger().debug("Setting files to writeable in " + this.sandboxDir + " for project " + this.siProject.getConfigurationPath());
        final Command cmd = new Command("si", "makewritable");
        cmd.addOption(new Option("recurse"));
        cmd.addOption(new Option("cwd", this.sandboxDir));
        return this.api.runCommand(cmd);
    }
    
    public Response revertMembers() throws APIException {
        this.api.getLogger().debug("Reverting changes in sandbox " + this.sandboxDir + " for project " + this.siProject.getConfigurationPath());
        final Command cmd = new Command("si", "revert");
        cmd.addOption(new Option("recurse"));
        cmd.addOption(new Option("cwd", this.sandboxDir));
        return this.api.runCommand(cmd);
    }
    
    public List<ScmFile> getNewMembers(final String exclude, final String include) throws APIException {
        final List<ScmFile> filesAdded = new ArrayList<ScmFile>();
        final Command siViewNonMem = new Command("si", "viewnonmembers");
        siViewNonMem.addOption(new Option("recurse"));
        if (null != exclude && exclude.length() > 0) {
            siViewNonMem.addOption(new Option("exclude", exclude));
        }
        if (null != include && include.length() > 0) {
            siViewNonMem.addOption(new Option("include", include));
        }
        siViewNonMem.addOption(new Option("noincludeFormers"));
        siViewNonMem.addOption(new Option("cwd", this.sandboxDir));
        final Response response = this.api.runCommand(siViewNonMem);
        final WorkItemIterator wit = response.getWorkItems();
        while (wit.hasNext()) {
            filesAdded.add(new ScmFile(wit.next().getField("absolutepath").getValueAsString(), ScmFileStatus.ADDED));
        }
        return filesAdded;
    }
    
    public List<ScmFile> addNonMembers(final String exclude, final String include, final String message) {
        this.addSuccess = true;
        final List<ScmFile> filesAdded = new ArrayList<ScmFile>();
        this.api.getLogger().debug("Looking for new members in sandbox dir: " + this.sandboxDir);
        try {
            final List<ScmFile> newFileList = this.getNewMembers(exclude, include);
            final Iterator<ScmFile> sit = newFileList.iterator();
            while (sit.hasNext()) {
                try {
                    final ScmFile localFile = sit.next();
                    this.add(new File(localFile.getPath()), message);
                    filesAdded.add(localFile);
                }
                catch (APIException aex) {
                    this.addSuccess = false;
                    final ExceptionHandler eh = new ExceptionHandler(aex);
                    this.api.getLogger().error("MKS API Exception: " + eh.getMessage());
                    this.api.getLogger().debug(eh.getCommand() + " completed with exit Code " + eh.getExitCode());
                }
            }
        }
        catch (APIException aex2) {
            this.addSuccess = false;
            final ExceptionHandler eh2 = new ExceptionHandler(aex2);
            this.api.getLogger().error("MKS API Exception: " + eh2.getMessage());
            this.api.getLogger().debug(eh2.getCommand() + " completed with exit Code " + eh2.getExitCode());
        }
        return filesAdded;
    }
    
    public boolean getOverallAddSuccess() {
        return this.addSuccess;
    }
    
    public boolean hasWorkingFile(final Item wfdelta) {
        return !wfdelta.getField("noWorkingFile").getBoolean();
    }
    
    public List<WorkItem> getChangeList() throws APIException {
        final List<WorkItem> changedFiles = new ArrayList<WorkItem>();
        final Command siViewSandbox = new Command("si", "viewsandbox");
        final MultiValue mv = new MultiValue(",");
        mv.add("name");
        mv.add("context");
        mv.add("wfdelta");
        mv.add("memberarchive");
        siViewSandbox.addOption(new Option("fields", mv));
        siViewSandbox.addOption(new Option("recurse"));
        siViewSandbox.addOption(new Option("noincludeDropped"));
        siViewSandbox.addOption(new Option("filterSubs"));
        siViewSandbox.addOption(new Option("cwd", this.sandboxDir));
        final Response r = this.api.runCommand(siViewSandbox);
        final WorkItemIterator wit = r.getWorkItems();
        while (wit.hasNext()) {
            final WorkItem wi = wit.next();
            this.api.getLogger().debug("Inspecting file: " + wi.getField("name").getValueAsString());
            if (wi.getModelType().equals("si.Member")) {
                final Item wfdeltaItem = (Item)wi.getField("wfdelta").getValue();
                if (!this.isDelta(wfdeltaItem)) {
                    continue;
                }
                final File memberFile = new File(wi.getField("name").getValueAsString());
                if (this.hasWorkingFile(wfdeltaItem)) {
                    if (!this.hasMemberChanged(memberFile, wi.getId())) {
                        continue;
                    }
                    changedFiles.add(wi);
                }
                else {
                    changedFiles.add(wi);
                }
            }
        }
        return changedFiles;
    }
    
    public List<ScmFile> checkInUpdates(final String message) {
        this.ciSuccess = true;
        final List<ScmFile> changedFiles = new ArrayList<ScmFile>();
        this.api.getLogger().debug("Looking for changed and dropped members in sandbox dir: " + this.sandboxDir);
        try {
            final List<WorkItem> changeList = this.getChangeList();
            final Iterator<WorkItem> wit = changeList.iterator();
            while (wit.hasNext()) {
                try {
                    final WorkItem wi = wit.next();
                    final File memberFile = new File(wi.getField("name").getValueAsString());
                    if (this.hasWorkingFile((Item)wi.getField("wfdelta").getValue())) {
                        this.lock(memberFile, wi.getId());
                        this.checkin(memberFile, wi.getId(), message);
                        changedFiles.add(new ScmFile(memberFile.getAbsolutePath(), ScmFileStatus.CHECKED_IN));
                    }
                    else {
                        this.dropMember(memberFile, wi.getId());
                        changedFiles.add(new ScmFile(memberFile.getAbsolutePath(), ScmFileStatus.DELETED));
                    }
                }
                catch (APIException aex) {
                    this.ciSuccess = false;
                    final ExceptionHandler eh = new ExceptionHandler(aex);
                    this.api.getLogger().error("MKS API Exception: " + eh.getMessage());
                    this.api.getLogger().debug(eh.getCommand() + " completed with exit Code " + eh.getExitCode());
                }
            }
        }
        catch (APIException aex2) {
            this.ciSuccess = false;
            final ExceptionHandler eh2 = new ExceptionHandler(aex2);
            this.api.getLogger().error("MKS API Exception: " + eh2.getMessage());
            this.api.getLogger().debug(eh2.getCommand() + " completed with exit Code " + eh2.getExitCode());
        }
        return changedFiles;
    }
    
    public boolean getOverallCheckInSuccess() {
        return this.ciSuccess;
    }
    
    public Response createSubproject(final String dirPath) throws APIException {
        this.api.getLogger().debug("Creating subprojects for: " + dirPath + "/project.pj");
        final Command siCreateSubproject = new Command("si", "createsubproject");
        siCreateSubproject.addOption(new Option("cpid", this.cpid));
        siCreateSubproject.addOption(new Option("createSubprojects"));
        siCreateSubproject.addOption(new Option("cwd", this.sandboxDir));
        siCreateSubproject.addSelection(dirPath + "/project.pj");
        return this.api.runCommand(siCreateSubproject);
    }
    
    public ChangeLogSet getChangeLog(final Date startDate, final Date endDate) throws APIException {
        final ChangeLogSet changeLog = new ChangeLogSet(startDate, endDate);
        final Hashtable<String, ChangeSet> changeSetHash = new Hashtable<String, ChangeSet>();
        final Command siRlog = new Command("si", "rlog");
        siRlog.addOption(new Option("recurse"));
        final MultiValue rFilter = new MultiValue(":");
        rFilter.add("daterange");
        rFilter.add("'" + Sandbox.RLOG_DATEFORMAT.format(startDate) + "'-'" + Sandbox.RLOG_DATEFORMAT.format(endDate) + "'");
        siRlog.addOption(new Option("rfilter", rFilter));
        siRlog.addOption(new Option("cwd", this.sandboxDir));
        final Response response = this.api.runCommand(siRlog);
        final WorkItemIterator wit = response.getWorkItems();
        while (wit.hasNext()) {
            final WorkItem wi = wit.next();
            String memberName = wi.getContext();
            memberName = memberName.substring(0, memberName.lastIndexOf(47));
            memberName = memberName + '/' + wi.getId();
            memberName = memberName.replace('\\', '/');
            final Field revisionsFld = wi.getField("revisions");
            if (null != revisionsFld && revisionsFld.getDataType().equals("com.mks.api.response.ItemList") && null != revisionsFld.getList()) {
                final List<Item> revList = (List<Item>)revisionsFld.getList();
                for (final Item revisionItem : revList) {
                    final String revision = revisionItem.getId();
                    String author = revisionItem.getField("author").getItem().getId();
                    try {
                        author = revisionItem.getField("author").getItem().getField("fullname").getValueAsString();
                    }
                    catch (NullPointerException ex) {}
                    String cpid = ":none";
                    try {
                        cpid = revisionItem.getField("cpid").getItem().getId();
                    }
                    catch (NullPointerException ex2) {}
                    final String comment = cpid + ": " + revisionItem.getField("cpsummary").getValueAsString();
                    final Date date = revisionItem.getField("date").getDateTime();
                    final ChangeFile changeFile = new ChangeFile(memberName, revision);
                    ChangeSet changeSet = changeSetHash.get(cpid);
                    if (null != changeSet) {
                        if (changeSet.getDate().after(date)) {
                            changeSet.setDate(date);
                        }
                        changeSet.addFile(changeFile);
                        changeSetHash.put(cpid, changeSet);
                    }
                    else {
                        final List<ChangeFile> changeFileList = new ArrayList<ChangeFile>();
                        changeFileList.add(changeFile);
                        changeSet = new ChangeSet(date, comment, author, changeFileList);
                        changeSetHash.put(cpid, changeSet);
                    }
                }
            }
        }
        final List<ChangeSet> changeSetList = new ArrayList<ChangeSet>();
        changeSetList.addAll(changeSetHash.values());
        changeLog.setChangeSets(changeSetList);
        return changeLog;
    }
    
    static {
        RLOG_DATEFORMAT = new SimpleDateFormat("MMMMM d, yyyy - h:mm:ss a");
    }
}
