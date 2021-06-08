// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import java.io.File;
import com.mks.api.response.Response;
import com.mks.api.commands.ide.WorkingFileList;
import com.mks.api.commands.ide.WorkingFileFactory;
import java.util.List;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

public class WFCommands extends IDECommands
{
    public WFCommands(final CmdRunnerCreator session, final boolean isInteractive) throws APIException {
        super(session, isInteractive);
    }
    
    public List getStatus(final List fileNames) {
        final WorkingFileList workingFiles = WorkingFileFactory.getWorkingFiles(this, fileNames);
        return workingFiles.getList();
    }
    
    public Response resync(final String[] fileNames) throws APIException {
        final SIResyncCommand siResync = new SIResyncCommand(this.getCmdRunnerCreator());
        final GenericWFCommandRunner runner = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siResync);
        return runner.execute(fileNames, this.isInteractive);
    }
    
    public Response resync(final List fileNames, final boolean overwriteChanged, final boolean overwriteDeferred, final boolean overwritePending, final boolean merge, final String mergeType, final String mergeConflict, final boolean downgradeOnConflict) throws APIException {
        final SIResyncCommand siResync = new SIResyncCommand(this.getCmdRunnerCreator());
        siResync.setOverwrite(overwriteChanged);
        siResync.setOverwriteDeferred(overwriteDeferred);
        siResync.setOverwritePending(overwritePending);
        siResync.setMerge(merge);
        siResync.setMergeConflict("launchtool");
        siResync.setMergeType("automatic");
        siResync.setDowngradeOnLockConflict(downgradeOnConflict);
        final GenericWFCommandRunner runner = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siResync);
        return runner.execute(WorkingFileFactory.getWorkingFiles(this.getCmdRunnerCreator(), fileNames), this.isInteractive);
    }
    
    public Response checkOut(final List fileNames, final boolean overwriteChanged, final boolean overwriteDeferred, final boolean lock, final boolean merge, final String mergeType, final String mergeConflict) throws APIException {
        final SICheckOutCommand siCO = new SICheckOutCommand(this.getCmdRunnerCreator());
        siCO.setOverwrite(overwriteChanged);
        siCO.setOverwriteDeferred(overwriteDeferred);
        siCO.setMerge(merge);
        siCO.setMergeConflict("launchtool");
        siCO.setMergeType("automatic");
        siCO.setLock(lock);
        final GenericWFCommandRunner runner = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siCO);
        return runner.execute(WorkingFileFactory.getWorkingFiles(this.getCmdRunnerCreator(), fileNames), this.isInteractive);
    }
    
    public Response revert(final String[] fileNames) throws APIException {
        final SIRevertCommand siRevert = new SIRevertCommand(this.getCmdRunnerCreator());
        final GenericWFCommandRunner runner = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siRevert);
        return runner.execute(fileNames, this.isInteractive);
    }
    
    public Response revert(final List fileNames, final boolean overwriteDeferred, final boolean overwriteChanged) throws APIException {
        final SIRevertCommand siRevert = new SIRevertCommand(this.getCmdRunnerCreator());
        siRevert.setOverwriteIfChanged(overwriteChanged);
        siRevert.setOverwriteIfDeferred(overwriteDeferred);
        final GenericWFCommandRunner runner = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siRevert);
        return runner.execute(WorkingFileFactory.getWorkingFiles(this.getCmdRunnerCreator(), fileNames), this.isInteractive);
    }
    
    public Response lockFile(final String workingFilePath, final String cpid, final boolean isUpgrade) throws APIException {
        final SILockCommand siLock = new SILockCommand(this.getCmdRunnerCreator());
        siLock.setCpid(cpid);
        if (isUpgrade) {
            siLock.setAction("upgrade");
        }
        siLock.setAllowPrompting(false);
        final GenericWFCommandRunner runner = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siLock);
        return runner.execute(new String[] { workingFilePath }, this.isInteractive);
    }
    
    public Response newFile(final String workingFilePath, final String cpid, final boolean binary, final boolean allowCreateSubs) throws APIException {
        final WFTrackCommandBase trackNewFile = new WFTrackNewFile(this.getCmdRunnerCreator(), binary);
        trackNewFile.setCpid(cpid);
        trackNewFile.setAllowCreateSubs(allowCreateSubs);
        return trackNewFile.execute(new String[] { workingFilePath }, this.isInteractive);
    }
    
    public Response newFiles(final String[] workingFiles, final String cpid, final File sandboxRoot) throws APIException {
        final WFTrackCommandBase trackNewFile = new WFTrackNewFile(this.getCmdRunnerCreator());
        trackNewFile.setCpid(cpid);
        trackNewFile.setPreferredRoot(sandboxRoot);
        return trackNewFile.execute(workingFiles, this.isInteractive);
    }
    
    public Response deletedFile(final String workingFilePath, final String cpid) throws APIException {
        return this.deletedFiles(new String[] { workingFilePath }, cpid);
    }
    
    public Response deletedFiles(final String[] workingFiles, final String cpid) throws APIException {
        final WFTrackDeletedFile trackDeletedFile = new WFTrackDeletedFile(this.getCmdRunnerCreator());
        trackDeletedFile.setCpid(cpid);
        return trackDeletedFile.execute(workingFiles, this.isInteractive);
    }
    
    public Response movedFile(final String from, final String to, final String cpid, final boolean allowCreateSubs) throws APIException {
        final String[] from2 = { from };
        final WFTrackMovedFile trackMovedFile = new WFTrackMovedFile(this.getCmdRunnerCreator());
        trackMovedFile.setCpid(cpid);
        trackMovedFile.setMapping(from2, new String[] { to });
        trackMovedFile.setAllowCreateSubs(allowCreateSubs);
        return trackMovedFile.execute(from2, this.isInteractive);
    }
    
    public Response movedFile(final String from, final String to, final String cpid, final File sandboxRoot) throws APIException {
        final String[] from2 = { from };
        final WFTrackMovedFile trackMovedFile = new WFTrackMovedFile(this.getCmdRunnerCreator());
        trackMovedFile.setCpid(cpid);
        trackMovedFile.setPreferredRoot(sandboxRoot);
        trackMovedFile.setMapping(from2, new String[] { to });
        trackMovedFile.setAllowCreateSubs(true);
        return trackMovedFile.execute(from2, this.isInteractive);
    }
    
    public Response addToCP(final String workingFilePath, final String destinationCpid) throws APIException {
        return this.addToCP(new String[] { workingFilePath }, destinationCpid, null);
    }
    
    public Response addToCP(final String[] workingFiles, final String destinationCpid, final File sandboxRoot) throws APIException {
        final WFTrackAddToCP trackAddToCP = new WFTrackAddToCP(this.getCmdRunnerCreator());
        trackAddToCP.setCpid(destinationCpid);
        trackAddToCP.setPreferredRoot(sandboxRoot);
        return trackAddToCP.execute(workingFiles, this.isInteractive);
    }
    
    public Response submitChanges(final String[] workingFile, final String cpid) throws APIException {
        final WFSubmitTrackedChanges wfSubmit = new WFSubmitTrackedChanges(this.getCmdRunnerCreator());
        wfSubmit.setCpid(cpid);
        return wfSubmit.execute(workingFile, this.isInteractive);
    }
}
