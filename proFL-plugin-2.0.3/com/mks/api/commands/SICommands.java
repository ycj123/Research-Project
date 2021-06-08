// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import com.mks.api.response.WorkItemIterator;
import com.mks.api.response.Result;
import com.mks.api.response.WorkItem;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;
import com.mks.api.FileOption;
import java.io.IOException;
import java.util.Iterator;
import com.mks.api.response.Item;
import com.mks.api.response.Response;
import com.mks.api.OptionList;
import com.mks.api.response.InvalidCommandOptionException;
import java.io.File;
import com.mks.api.response.InvalidCommandSelectionException;
import com.mks.api.Option;
import com.mks.api.Command;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

public class SICommands extends MKSCommands
{
    protected boolean isInteractive;
    
    public SICommands(final CmdRunnerCreator session, final boolean isInteractive) throws APIException {
        super(session);
        this.isInteractive = isInteractive;
    }
    
    public void siClientAboutView() throws APIException {
        final Command cmd = new Command("si", "about");
        cmd.addOption(new Option("g"));
        this.runAPICommand(cmd);
    }
    
    public void launchMKSGUI() throws APIException {
        final Command cmd = new Command("si", "gui");
        this.runAPICommand(cmd);
    }
    
    public void siPreferencesView() throws APIException {
        final Command cmd = new Command("si", "viewprefs");
        cmd.addOption(new Option("g"));
        this.runAPICommand(cmd);
    }
    
    private boolean resolveLocalMemberAccess(final Command cmd, final String cwd, final String project, String member) throws APIException {
        boolean sandboxAssumed = true;
        if (member == null || member.length() == 0) {
            throw new InvalidCommandSelectionException("SICommands: parameter 'member' cannot be null or empty.");
        }
        if (project == null) {
            final File memberFile = new File(cwd, member);
            if (!memberFile.isAbsolute()) {
                throw new InvalidCommandOptionException("SICommands: parameter 'cwd' cannot be null or empty.");
            }
            member = memberFile.getAbsolutePath();
        }
        if (project != null) {
            cmd.addOption(new Option("P", project));
            sandboxAssumed = false;
        }
        cmd.addSelection(member);
        return sandboxAssumed;
    }
    
    public void siSandboxMemberHistoryView(final String cwd, final String member) throws APIException {
        this.siMemberHistoryView(cwd, null, member);
    }
    
    public void siMemberHistoryView(final String project, final String member) throws APIException {
        this.siMemberHistoryView(null, project, member);
    }
    
    private void siMemberHistoryView(final String cwd, final String project, final String member) throws APIException {
        final Command cmd = new Command("si", "viewhistory");
        cmd.addOption(new Option("g"));
        final boolean useSandbox = this.resolveLocalMemberAccess(cmd, cwd, project, member);
        this.runAPICommand(cmd);
    }
    
    public void siSandboxMemberInformationView(final String cwd, final String member) throws APIException {
        final Command cmd = new Command("si", "memberinfo");
        cmd.addOption(new Option("g"));
        this.resolveLocalMemberAccess(cmd, cwd, null, member);
        this.runAPICommand(cmd);
    }
    
    public void siSandboxMemberDifferences(final String cwd, final String member) throws APIException {
        this.siMemberDifferences(cwd, null, member, null, null);
    }
    
    public void siMemberDifferences(final String project, final String member, final String revision1, final String revision2) throws APIException {
        this.siMemberDifferences(null, project, member, revision1, revision2);
    }
    
    private void siMemberDifferences(final String cwd, final String project, final String member, final String revision1, final String revision2) throws APIException {
        final Command cmd = new Command("si", "diff");
        cmd.addOption(new Option("g"));
        if (revision1 != null) {
            cmd.addOption(new Option("r", revision1));
        }
        if (revision2 != null) {
            cmd.addOption(new Option("r", revision2));
        }
        final boolean useSandbox = this.resolveLocalMemberAccess(cmd, cwd, project, member);
        this.runAPICommand(cmd);
    }
    
    public boolean isSandboxMember(final String cwd, final String filename) {
        try {
            final String[] members = { filename };
            final OptionList options = new OptionList();
            options.add(new Option("fields", "name"));
            final Response response = this.getSandboxMemberStatus(cwd, members, options);
            final Item item = response.getWorkItems().next();
            return item != null && item.getModelType().equalsIgnoreCase("si.Member");
        }
        catch (APIException ignored) {
            return false;
        }
    }
    
    public Response siCheckOut(final String cwd, final String[] members) throws APIException {
        return this.siCheckOut(cwd, members, null);
    }
    
    public Response siCheckOut(final String cwd, final String[] members, final OptionList options) throws APIException {
        if (members == null || members.length == 0) {
            throw new InvalidCommandSelectionException("SICommands.siCheckOut: parameter 'members' cannot be null or empty.");
        }
        final Command cmd = new Command("ii", "checkout");
        if (this.isInteractive) {
            cmd.addOption(new Option("g"));
        }
        if (cwd != null) {
            cmd.addOption(new Option("cwd", cwd));
        }
        if (members != null && members.length > 0) {
            for (int i = 0; i < members.length; ++i) {
                cmd.addSelection(members[i]);
            }
            cmd.addOption(new Option("recurse"));
        }
        if (options != null) {
            final Iterator it = options.getOptions();
            while (it.hasNext()) {
                cmd.addOption(it.next());
            }
        }
        return this.runAPICommand(cmd);
    }
    
    public Response siCheckIn(final String cwd, final String[] members) throws APIException {
        return this.siCheckIn(cwd, members, null);
    }
    
    public Response siCheckIn(final String cwd, final String[] members, final OptionList options) throws APIException {
        final SICheckinCommand siCheckin = new SICheckinCommand(this.getCmdRunnerCreator());
        siCheckin.setCwd(cwd);
        siCheckin.addOptionList(options);
        return siCheckin.execute(members, this.isInteractive);
    }
    
    public Response siSubmitMembers(final String cwd, final String[] members) throws APIException {
        return this.siSubmitMembers(cwd, members, null);
    }
    
    public Response siSubmitMembers(final String cwd, final String[] members, final OptionList options) throws APIException {
        if (members == null || members.length == 0) {
            throw new InvalidCommandSelectionException("SICommands.siCheckIn: parameter 'members' cannot be null or empty.");
        }
        final Command cmd = new Command("si", "submit");
        if (this.isInteractive) {
            cmd.addOption(new Option("g"));
        }
        if (cwd != null) {
            cmd.addOption(new Option("cwd", cwd));
        }
        if (members != null && members.length > 0) {
            for (int i = 0; i < members.length; ++i) {
                cmd.addSelection(members[i]);
            }
            cmd.addOption(new Option("recurse"));
        }
        if (options != null) {
            final Iterator it = options.getOptions();
            while (it.hasNext()) {
                cmd.addOption(it.next());
            }
        }
        return this.runAPICommand(cmd);
    }
    
    public Response siAddMembers(final String cwd, final String[] nonMembers) throws APIException {
        return this.siAddMembers(cwd, nonMembers, null);
    }
    
    public Response siAddMembers(final String cwd, final String[] nonMembers, final OptionList options) throws APIException {
        final SIAddCommand siAdd = new SIAddCommand(this.getCmdRunnerCreator());
        siAdd.setCwd(cwd);
        siAdd.addOptionList(options);
        return siAdd.execute(nonMembers, this.isInteractive);
    }
    
    public Response siAddSharedMember(final String cwd, final String member, final String archive) throws APIException {
        if (archive == null || archive.length() == 0) {
            throw new InvalidCommandOptionException("SICommands.siAddMembers: parameter 'archive' cannot be null or empty.");
        }
        final Command cmd = new Command("si", "add");
        if (this.isInteractive) {
            cmd.addOption(new Option("g"));
        }
        if (cwd != null) {
            cmd.addOption(new Option("cwd", cwd));
        }
        cmd.addOption(new Option("archive", archive));
        cmd.addOption(new Option("revision", ":head"));
        this.resolveLocalMemberAccess(cmd, cwd, null, member);
        return this.runAPICommand(cmd);
    }
    
    public String getSandboxMemberArchive(final String cwd, final String member) throws APIException {
        final Response response = this.getSandboxMemberArchiveInfo(cwd, member);
        return response.getWorkItems().next().getField("archiveName").getValueAsString();
    }
    
    public Response getSandboxMemberArchiveInfo(final String cwd, final String member) throws APIException {
        final Command cmd = new Command("si", "archiveinfo");
        this.resolveLocalMemberAccess(cmd, cwd, null, member);
        return this.runAPICommand(cmd);
    }
    
    public void siDropSandboxMembers(final String cwd, final String[] members) throws APIException {
        this.siDropSandboxMembers(cwd, members, null);
    }
    
    public void siDropSandboxMembers(final String cwd, final String[] members, final OptionList options) throws APIException {
        final SIDropCommand siDrop = new SIDropCommand(this.getCmdRunnerCreator());
        siDrop.setCwd(cwd);
        siDrop.addOptionList(options);
        siDrop.execute(members, this.isInteractive);
    }
    
    public void siMoveSandboxMembers(final String cwd, final String[] members, final OptionList options) throws APIException {
        final SIMoveCommand siMove = new SIMoveCommand(this.getCmdRunnerCreator());
        siMove.setCwd(cwd);
        siMove.addOptionList(options);
        siMove.execute(members, this.isInteractive);
    }
    
    public Response siRenameSandboxMember(final String cwd, final String member, final String newName) throws APIException {
        return this.siRenameMember(cwd, null, member, newName, new OptionList());
    }
    
    public Response siRenameMember(final String project, final String member, final String newName) throws APIException {
        return this.siRenameMember(null, project, member, newName, new OptionList());
    }
    
    public Response siRenameMember(final String cwd, final String member, final String newName, final OptionList options) throws APIException {
        return this.siRenameMember(cwd, null, member, newName, options);
    }
    
    private Response siRenameMember(String cwd, final String project, final String member, final String newName, final OptionList options) throws APIException {
        final Command cmd = new Command("si", "rename");
        if (this.isInteractive) {
            cmd.addOption(new Option("g"));
        }
        cmd.addOption(new Option("newName", newName));
        if (options != null) {
            final Iterator it = options.getOptions();
            while (it.hasNext()) {
                cmd.addOption(it.next());
            }
        }
        cwd = this.validateSandboxCWD(cwd, new String[] { member });
        final String memberName = new File(member).getName();
        final boolean useSandbox = this.resolveLocalMemberAccess(cmd, cwd, project, memberName);
        return this.runAPICommand(cmd);
    }
    
    public Response siResync(final String cwd, final String[] members, final boolean recurse) throws APIException {
        final SIResyncCommand cmd = new SIResyncCommand(this.getCmdRunnerCreator());
        cmd.setCwd(cwd);
        cmd.setRecurse(recurse);
        return cmd.execute(members, true);
    }
    
    public Response siResync(final String cwd, final String[] members) throws APIException {
        return this.siResync(cwd, members, false);
    }
    
    public Response siRevertMembers(final String cwd, final String[] members) throws APIException {
        final SIRevertCommand siRevert = new SIRevertCommand(this.getCmdRunnerCreator());
        siRevert.setCwd(cwd);
        return siRevert.execute(members, this.isInteractive);
    }
    
    private void siRetrieveMember(final String cwd, final String project, final String member, final String revision, final String targetFile) throws APIException {
        if (targetFile == null || targetFile.length() == 0) {
            throw new InvalidCommandOptionException("SICommands.siProjectCheckout: parameter 'targetFile' cannot be null or empty.");
        }
        if (revision == null || revision.length() == 0) {
            throw new InvalidCommandOptionException("SICommands.siProjectCheckout: parameter 'revision' cannot be null or empty.");
        }
        final Command cmd = new Command("si", "projectco");
        cmd.addOption(new Option("nolock"));
        if (revision != null) {
            cmd.addOption(new Option("revision", revision));
        }
        cmd.addOption(new Option("overwriteExisting"));
        final File target = new File(targetFile);
        if (!target.exists()) {
            try {
                target.createNewFile();
            }
            catch (IOException ex) {}
        }
        cmd.addOption(new FileOption("targetFile", targetFile));
        final boolean useSandbox = this.resolveLocalMemberAccess(cmd, cwd, project, member);
        this.runAPICommand(cmd);
    }
    
    public void siRetrieveMember(final String project, final String member, final String revision, final String targetFile) throws APIException {
        this.siRetrieveMember(null, project, member, revision, targetFile);
    }
    
    public void siRetrieveSandboxMember(final String cwd, final String member, final String revision, final String targetFile) throws APIException {
        this.siRetrieveMember(cwd, null, member, revision, targetFile);
    }
    
    public Response getSandboxMemberStatus(final String cwd, final String[] members) throws APIException {
        final List fields = new ArrayList();
        fields.add("lockrecord");
        fields.add("memberarchive");
        fields.add("memberrev");
        fields.add("name");
        fields.add("newrevdelta");
        fields.add("revsyncdelta");
        fields.add("type");
        fields.add("wfdelta");
        fields.add("workingrev");
        fields.add("merge");
        fields.add("frozen");
        fields.add("archiveshared");
        fields.add("workingcpid");
        fields.add("context");
        return this.getSandboxMemberStatus(cwd, members, fields);
    }
    
    public Response getSandboxMemberStatus(final String cwd, final String[] members, final List statusFields) throws APIException {
        final OptionList options = new OptionList();
        StringBuffer sb = null;
        final Iterator it = statusFields.iterator();
        while (it.hasNext()) {
            if (sb == null) {
                sb = new StringBuffer();
            }
            else {
                sb.append(',');
            }
            sb.append(it.next());
        }
        if (sb != null) {
            options.add(new Option("fields", sb.toString()));
        }
        return this.getSandboxMemberStatus(cwd, members, options);
    }
    
    public Response getSandboxMemberStatus(final String cwd, final String[] members, final OptionList options) throws APIException {
        return this.getSandboxMemberStatus(cwd, members, options, false);
    }
    
    public Response getSandboxMemberStatus(final String cwd, final String[] members, final OptionList options, final boolean generateStreamedResponse) throws APIException {
        final Command cmd = new Command("si", "viewsandbox");
        if (this.isInteractive) {
            cmd.addOption(new Option("settingsUI", "gui"));
        }
        cmd.addOption(new Option("cwd", this.validateSandboxCWD(cwd, members)));
        cmd.addOption(new Option("norecurse"));
        if (members != null && members.length > 0) {
            for (int i = 0; i < members.length; ++i) {
                cmd.addSelection(members[i]);
            }
        }
        if (options != null) {
            final Iterator it = options.getOptions();
            while (it.hasNext()) {
                cmd.addOption(it.next());
            }
        }
        return this.runAPICommand(cmd, generateStreamedResponse);
    }
    
    public Response getSandboxMembers(final File sandbox, final OptionList options, final boolean generateStreamedResponse) throws APIException {
        final Command cmd = new Command("si", "viewsandbox");
        cmd.addOption(new Option("sandbox", sandbox.toString()));
        if (options != null) {
            final Iterator it = options.getOptions();
            while (it.hasNext()) {
                cmd.addOption(it.next());
            }
        }
        return this.runAPICommand(cmd, generateStreamedResponse);
    }
    
    public Response getSandboxMemberDifferenceStatus(final String cwd, final String[] members, final OptionList options) throws APIException {
        final Command cmd = new Command("si", "diff");
        if (this.isInteractive) {
            cmd.addOption(new Option("settingsUI", "gui"));
        }
        cmd.addOption(new Option("cwd", this.validateSandboxCWD(cwd, members)));
        if (members != null && members.length > 0) {
            for (int i = 0; i < members.length; ++i) {
                cmd.addSelection(members[i]);
            }
        }
        if (options != null) {
            final Iterator it = options.getOptions();
            while (it.hasNext()) {
                cmd.addOption(it.next());
            }
        }
        return this.runAPICommand(cmd);
    }
    
    public void siLabelSandboxMember(final String cwd, final String[] members, final String label, final String revision) throws APIException {
        final Command cmd = new Command("si", "addlabel");
        if (this.isInteractive) {
            cmd.addOption(new Option("gui"));
        }
        if (cwd != null) {
            cmd.addOption(new Option("cwd", cwd));
        }
        if (label != null) {
            cmd.addOption(new Option("label", label));
            cmd.addOption(new Option("moveLabel"));
        }
        if (revision != null) {
            cmd.addOption(new Option("revision", revision));
        }
        cmd.addOption(new Option("recurse"));
        for (int i = 0; i < members.length; ++i) {
            cmd.addSelection(members[i]);
        }
        this.runAPICommand(cmd);
    }
    
    public Response siMakeSandboxMemberWritable(final String cwd, final String[] members) throws APIException {
        final Command cmd = new Command("si", "makewritable");
        if (cwd != null) {
            cmd.addOption(new Option("cwd", cwd));
        }
        cmd.addOption(new Option("recurse"));
        for (int i = 0; i < members.length; ++i) {
            cmd.addSelection(members[i]);
        }
        return this.runAPICommand(cmd);
    }
    
    public Response siLockSandboxMembers(final String cwd, final String[] members, final String cpid, final boolean allowPrompting, final boolean isUpgrade) throws APIException {
        final SILockCommand siLock = new SILockCommand(this.getCmdRunnerCreator());
        siLock.setCwd(cwd);
        if (cpid != null) {
            siLock.setCpid(cpid);
        }
        siLock.setAllowPrompting(allowPrompting);
        siLock.setLockType("auto");
        if (isUpgrade) {
            siLock.setAction("upgrade");
        }
        return siLock.execute(members, this.isInteractive);
    }
    
    public Response siDowngradeLockMembers(final String cwd, final String[] members) throws APIException {
        return this.siUnlockMembers(cwd, members, true);
    }
    
    public Response siUnlockMembers(final String cwd, final String[] members, final boolean downgrade) throws APIException {
        final SIUnlockCommand siUnlock = new SIUnlockCommand(this.getCmdRunnerCreator());
        siUnlock.setCwd(cwd);
        if (downgrade) {
            siUnlock.setAction("downgrade");
        }
        else {
            siUnlock.setAction("unlock");
        }
        return siUnlock.execute(members, this.isInteractive);
    }
    
    public Response siCreateProject(final String project) throws APIException {
        final Command cmd = new Command("si", "createproject");
        if (this.isInteractive) {
            cmd.addOption(new Option("g"));
        }
        if (project != null) {
            cmd.addSelection(project);
        }
        return this.runAPICommand(cmd);
    }
    
    public Response siCreateSubproject(final String project, final String subproject) throws APIException {
        final Command cmd = new Command("si", "createsubproject");
        if (this.isInteractive) {
            cmd.addOption(new Option("g"));
        }
        if (project != null && project.length() > 0) {
            cmd.addOption(new Option("project", project));
        }
        if (subproject != null) {
            cmd.addSelection(subproject);
        }
        return this.runAPICommand(cmd);
    }
    
    public Response runCreateProjectWizard() throws APIException {
        final Command cmd = new Command("ii", "createprojectwizard");
        cmd.addOption(new Option("g"));
        return this.runAPICommand(cmd);
    }
    
    public Response getProjectInfo(final String project) throws APIException {
        return this.getProjectInfo(project, false);
    }
    
    public Response getProjectInfo(final String project, final boolean showDevPaths) throws APIException {
        if (project == null || project.length() == 0) {
            throw new InvalidCommandOptionException("SICommands: parameter 'project' cannot be null or empty.");
        }
        final Command cmd = new Command("si", "projectinfo");
        cmd.addOption(new Option("noassociatedIssues"));
        cmd.addOption(new Option("noacl"));
        cmd.addOption(new Option("noshowCheckpointDescription"));
        cmd.addOption(new Option("noattributes"));
        if (showDevPaths) {
            cmd.addOption(new Option("devpaths"));
        }
        else {
            cmd.addOption(new Option("nodevpaths"));
        }
        cmd.addOption(new Option("project", project));
        return this.runAPICommand(cmd);
    }
    
    static String getMemberListCWD(final String[] memberlist) throws APIException {
        String cwd = null;
        if (memberlist != null) {
            for (int i = 0; i < memberlist.length; ++i) {
                final File memberFile = new File(memberlist[i]);
                if (memberFile.isAbsolute()) {
                    try {
                        File dir;
                        if (!memberFile.isDirectory()) {
                            dir = memberFile.getParentFile();
                        }
                        else {
                            dir = memberFile;
                        }
                        if (dir != null) {
                            if (cwd == null) {
                                cwd = dir.getCanonicalPath();
                            }
                            else {
                                final String dirpath = dir.getCanonicalPath();
                                if (cwd.startsWith(dirpath)) {
                                    cwd = dirpath;
                                }
                                else {
                                    while (!dirpath.startsWith(cwd)) {
                                        cwd = new File(cwd).getParent();
                                        if (cwd == null) {
                                            throw new InvalidCommandSelectionException("SICommands: selection of member cannot span devices");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    catch (IOException ex) {
                        throw new InvalidCommandSelectionException(ex);
                    }
                }
            }
        }
        return cwd;
    }
    
    protected String validateSandboxCWD(String cwd, final String[] memberlist) throws APIException {
        final String rootdir = getMemberListCWD(memberlist);
        if (rootdir != null) {
            if (cwd == null) {
                cwd = rootdir;
            }
            else {
                String canonicalCWD = null;
                try {
                    canonicalCWD = new File(cwd).getCanonicalPath();
                }
                catch (IOException ex) {
                    throw new InvalidCommandOptionException(ex);
                }
                if (!rootdir.startsWith(canonicalCWD)) {
                    throw new InvalidCommandOptionException("SICommands: invalid 'cwd' in relation to its member list");
                }
            }
        }
        if (cwd == null || cwd.length() == 0) {
            throw new InvalidCommandOptionException("SICommands: parameter 'cwd' cannot be null or empty.");
        }
        return cwd;
    }
    
    public Response getSandboxInfo(final String cwd) throws APIException {
        if (cwd == null || cwd.length() == 0) {
            throw new InvalidCommandOptionException("SICommands: parameter 'cwd' cannot be null or empty.");
        }
        final Command cmd = new Command("si", "sandboxinfo");
        cmd.addOption(new Option("noassociatedIssues"));
        cmd.addOption(new Option("noattributes"));
        cmd.addOption(new Option("noshowCheckpointDescription"));
        cmd.addOption(new Option("cwd", cwd));
        return this.runAPICommand(cmd);
    }
    
    public Response getSandboxInfoFromSandbox(final String sandboxFile) throws APIException {
        if (sandboxFile == null || sandboxFile.length() == 0) {
            throw new InvalidCommandOptionException("SICommands: parameter 'sandboxFile' cannot be null or empty.");
        }
        final Command cmd = new Command("si", "sandboxinfo");
        cmd.addOption(new Option("noassociatedIssues"));
        cmd.addOption(new Option("noattributes"));
        cmd.addOption(new Option("noshowCheckpointDescription"));
        cmd.addOption(new Option("sandbox", sandboxFile));
        return this.runAPICommand(cmd);
    }
    
    public String getSandboxName(final String cwd) throws APIException {
        try {
            final Response response = this.getSandboxInfo(cwd);
            final WorkItem sandbox = response.getWorkItems().next();
            return sandbox.getField("sandboxName").getValueAsString();
        }
        catch (NoSuchElementException ex) {
            throw new APIException(ex);
        }
    }
    
    public String siCreateSandbox(final String project, final String sandboxDir) throws APIException {
        return this.siCreateSandbox(project, null, null, sandboxDir);
    }
    
    public String siCreateVariantSandbox(final String project, final String devPath, final String sandboxDir) throws APIException {
        return this.siCreateSandbox(project, devPath, null, sandboxDir);
    }
    
    public String siCreateBuildSandbox(final String project, final String projectRevision, final String sandboxDir) throws APIException {
        return this.siCreateSandbox(project, null, projectRevision, sandboxDir);
    }
    
    private String siCreateSandbox(final String project, final String devPath, final String projectRevision, final String sandboxDir) throws APIException {
        final Command cmd = new Command("si", "createsandbox");
        if (this.isInteractive) {
            cmd.addOption(new Option("g"));
        }
        cmd.addOption(new Option("noopenView"));
        cmd.addOption(new Option("R"));
        if (project != null && project.length() > 0) {
            cmd.addOption(new Option("project", project));
            if (devPath != null && devPath.length() > 0) {
                cmd.addOption(new Option("devpath", devPath));
            }
            if (projectRevision != null && projectRevision.length() > 0) {
                cmd.addOption(new Option("projectRevision", projectRevision));
            }
            if (sandboxDir != null && sandboxDir.length() > 0) {
                cmd.addSelection(sandboxDir);
            }
            final Response response = this.runAPICommand(cmd);
            return response.getResult().getPrimaryValue().getId();
        }
        throw new InvalidCommandOptionException("SICommands.siCreateSandbox: parameter 'project' cannot be null or empty.");
    }
    
    public void siSandboxView(final String cwd) throws APIException {
        this.siSandboxView(cwd, null);
    }
    
    public void siSandboxView(final String cwd, final String[] members) throws APIException {
        final Command cmd = new Command("si", "viewsandbox");
        cmd.addOption(new Option("cwd", this.validateSandboxCWD(cwd, members)));
        cmd.addOption(new Option("g"));
        if (members != null && members.length > 0) {
            for (int i = 0; i < members.length; ++i) {
                cmd.addSelection(members[i]);
            }
        }
        this.runAPICommand(cmd);
    }
    
    public void siDeleteSandbox(final String sandboxFile) throws APIException {
        if (sandboxFile != null) {
            final Command cmd = new Command("si", "dropsandbox");
            cmd.addOption(new Option("delete", "all"));
            cmd.addSelection(sandboxFile);
            this.runAPICommand(cmd);
        }
    }
    
    public Response getSandboxes(final boolean includeSubs) throws APIException {
        final Command cmd = new Command("si", "sandboxes");
        if (includeSubs) {
            cmd.addOption(new Option("displaySubs"));
        }
        return this.runAPICommand(cmd);
    }
    
    public String siCreateChangePackage() throws APIException {
        return this.siCreateChangePackage(null, null);
    }
    
    public String siCreateChangePackage(final String summary) throws APIException {
        return this.siCreateChangePackage(null, summary);
    }
    
    public String siCreateChangePackage(final int issueId) throws APIException {
        return this.siCreateChangePackage(new Integer(issueId), null);
    }
    
    private String siCreateChangePackage(final Integer issueId, final String summary) throws APIException {
        final Command cmd = new Command("si", "createcp");
        if (this.isInteractive) {
            cmd.addOption(new Option("g"));
        }
        if (issueId != null) {
            cmd.addOption(new Option("issueID", String.valueOf(issueId)));
        }
        if (summary != null) {
            cmd.addOption(new Option("summary", summary));
        }
        final Response response = this.runAPICommand(cmd);
        final Result result = response.getResult();
        final String cpid = result.getPrimaryValue().getId();
        this.setActiveChangePackage(cpid);
        return cpid;
    }
    
    public void siSubmitChangePackage(final String cpID) throws APIException {
        final Command cmd = new Command("si", "submitcp");
        if (this.isInteractive) {
            cmd.addOption(new Option("g"));
        }
        if (cpID != null && cpID.length() > 0) {
            cmd.addSelection(cpID);
            this.runAPICommand(cmd);
            if (this.getActiveChangePackage().equalsIgnoreCase(cpID)) {
                this.setActiveChangePackage(":none");
            }
            return;
        }
        throw new InvalidCommandSelectionException("SICommands.siSubmitCP: parameter 'cpId' cannot be null or empty.");
    }
    
    public void siCloseChangePackage(final String cpID) throws APIException {
        final Command cmd = new Command("si", "closecp");
        if (this.isInteractive) {
            cmd.addOption(new Option("g"));
        }
        if (cpID != null && cpID.length() > 0) {
            cmd.addSelection(cpID);
            this.runAPICommand(cmd);
            if (this.getActiveChangePackage().equalsIgnoreCase(cpID)) {
                this.setActiveChangePackage(":none");
            }
            return;
        }
        throw new InvalidCommandSelectionException("SICommands.siCloseCP: parameter 'cpId' cannot be null or empty.");
    }
    
    public void siDiscardChangePackage(final String cpid) throws APIException {
        this.siDiscardChangePackage(cpid, null);
    }
    
    public void siDiscardChangePackage(final String cpid, final OptionList options) throws APIException {
        final Command cmd = new Command("si", "discardcp");
        if (this.isInteractive) {
            cmd.addOption(new Option("g"));
        }
        else {
            cmd.addOption(new Option("noconfirm"));
        }
        if (cpid != null && cpid.length() > 0) {
            cmd.addSelection(cpid);
            this.runAPICommand(cmd);
            if (this.getActiveChangePackage().equalsIgnoreCase(cpid)) {
                this.setActiveChangePackage(":none");
            }
            return;
        }
        throw new InvalidCommandSelectionException("SICommands.siCloseCP: parameter 'cpid' cannot be null or empty.");
    }
    
    public void siChangePackageView(final String cpID) throws APIException {
        final Command cmd = new Command("si", "viewcp");
        cmd.addOption(new Option("g"));
        if (cpID != null && cpID.length() > 0) {
            cmd.addSelection(cpID);
            this.runAPICommand(cmd);
            return;
        }
        throw new InvalidCommandSelectionException("SICommands.siViewCP: parameter 'cpId' cannot be null or empty.");
    }
    
    public String getChangePackageSummary(final String cpID) throws APIException {
        final Command cmd = new Command("si", "viewcps");
        if (this.isInteractive) {
            cmd.addOption(new Option("settingsUI", "gui"));
        }
        cmd.addOption(new Option("fields=summary"));
        if (cpID != null && cpID.length() > 0) {
            cmd.addSelection(cpID);
            final Response response = this.runAPICommand(cmd);
            return response.getWorkItems().next().getField("summary").getValueAsString();
        }
        throw new InvalidCommandSelectionException("SICommands.siSubmitCP: parameter 'cpId' cannot be null or empty.");
    }
    
    public void setActiveChangePackage(final String cpID) throws APIException {
        if (cpID == null || cpID.length() == 0) {
            throw new InvalidCommandSelectionException("SICommands.siSetActiveCP: parameter 'cpId' cannot be null or empty.");
        }
        final Command cmd = new Command("si", "setactivecp");
        if (this.isInteractive) {
            cmd.addOption(new Option("settingsUI", "gui"));
        }
        cmd.addSelection(cpID);
        this.runAPICommand(cmd);
    }
    
    public String getActiveChangePackage() throws APIException {
        final Command cmd = new Command("si", "viewactivecp");
        if (this.isInteractive) {
            cmd.addOption(new Option("settingsUI", "gui"));
        }
        final Response response = this.runAPICommand(cmd);
        return response.getWorkItems().next().getId();
    }
    
    public Item[] getChangePackages(final int issueID) throws APIException {
        WorkItem[] cps = new WorkItem[0];
        final Command cmd = new Command("si", "viewcp");
        cmd.addOption(new Option("showCommitted"));
        cmd.addOption(new Option("showPending"));
        cmd.addOption(new Option("showUncommitted"));
        cmd.addOption(new Option("fields=id,summary,state,archive,isclosed,member,project,revision,sandbox,server,siserver,type,user,variant"));
        cmd.addSelection(String.valueOf(issueID));
        final Response response = this.runAPICommand(cmd);
        cps = new WorkItem[response.getWorkItemListSize()];
        int index = 0;
        final WorkItemIterator items = response.getWorkItems();
        while (items.hasNext()) {
            cps[index] = items.next();
            ++index;
        }
        return cps;
    }
    
    public Item getChangePackage(final String cpID) throws APIException {
        final Command cmd = new Command("si", "viewcp");
        cmd.addOption(new Option("showCommitted"));
        cmd.addOption(new Option("showPending"));
        cmd.addOption(new Option("showUncommitted"));
        cmd.addOption(new Option("fields=id,summary,state,archive,isclosed,member,project,revision,sandbox,server,siserver,type,user,variant"));
        cmd.addSelection(cpID);
        final Response response = this.runAPICommand(cmd);
        return response.getWorkItems().next();
    }
    
    public Item[] getMyOpenChangePackages(final String fields) throws APIException {
        WorkItem[] cps = new WorkItem[0];
        final Command cmd = new Command("si", "viewcps");
        if (this.isInteractive) {
            cmd.addOption(new Option("settingsUI", "gui"));
        }
        if (fields != null) {
            cmd.addOption(new Option("fields=" + fields));
        }
        final Response response = this.runAPICommand(cmd);
        cps = new WorkItem[response.getWorkItemListSize()];
        int index = 0;
        final WorkItemIterator items = response.getWorkItems();
        while (items.hasNext()) {
            cps[index] = items.next();
            ++index;
        }
        return cps;
    }
}
