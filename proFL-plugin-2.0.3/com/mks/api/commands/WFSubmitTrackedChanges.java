// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import java.util.Iterator;
import java.util.Collection;
import com.mks.api.commands.ide.WorkingFileFactory;
import com.mks.api.commands.ide.WorkingFile;
import java.util.HashSet;
import com.mks.api.response.Response;
import com.mks.api.commands.ide.WorkingFileList;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

class WFSubmitTrackedChanges extends WFTrackCommandBase
{
    WFSubmitTrackedChanges(final CmdRunnerCreator session) throws APIException {
        super(session);
    }
    
    protected Response execute(final WorkingFileList workingFiles) throws APIException {
        final WorkingFileList toBeReverted = new WorkingFileList();
        final WorkingFileList toBeAdded = new WorkingFileList();
        final WorkingFileList toBeDropped = new WorkingFileList();
        final WorkingFileList toBeLocked = new WorkingFileList();
        final WorkingFileList toBeCheckedIn = new WorkingFileList();
        final WorkingFileList toBeSubmitted = new WorkingFileList();
        final WorkingFileList toBeCheckedInDeferred = new WorkingFileList();
        final HashSet emptySandboxCandidates = new HashSet();
        final HashSet knownNonemptySandboxes = new HashSet();
        for (WorkingFile wf : workingFiles) {
            if (wf.isAdded() || (!wf.isControlled() && wf.getFile().exists())) {
                toBeAdded.add(wf);
            }
            if (!wf.getFile().exists() && wf.isMember()) {
                toBeDropped.add(wf);
            }
            if (wf.isDeferred() && (!wf.isLockedByMe() || wf.isMoved()) && !toBeAdded.getList().contains(wf) && !toBeDropped.getList().contains(wf)) {
                toBeSubmitted.add(wf);
            }
            if (wf.isMoved() || wf.isDropped()) {
                emptySandboxCandidates.add(wf.getSandbox());
            }
            else {
                knownNonemptySandboxes.add(wf.getSandbox());
            }
            if (wf.isModified() && !wf.isLockedByMe() && !wf.isDropped() && wf.getFile().exists()) {
                toBeLocked.add(wf);
                toBeCheckedIn.add(wf);
            }
            if (wf.isLockedByMe() && !this.isOutOfDateMove(wf)) {
                if (wf.hasWorkingDelta() && !wf.isModified()) {
                    wf = WorkingFileFactory.refreshWorkingFile(this.getCmdRunnerCreator(), wf.getName());
                }
                if (wf.isModified() || wf.isMoved()) {
                    toBeCheckedIn.add(wf);
                }
                else {
                    toBeReverted.add(wf);
                }
            }
            if (this.isOutOfDateMove(wf) && wf.isLockedByMe()) {
                toBeCheckedInDeferred.add(wf);
            }
        }
        Response r = this.revertFiles(toBeReverted);
        if (r != null && r.getAPIException() != null) {
            return r;
        }
        r = this.addFiles(toBeAdded);
        if (r != null && r.getAPIException() != null) {
            return r;
        }
        r = this.setWritable(toBeAdded);
        if (r != null && r.getAPIException() != null) {
            return r;
        }
        r = this.dropFiles(toBeDropped);
        if (r != null && r.getAPIException() != null) {
            return r;
        }
        r = this.lockFiles(toBeLocked);
        if (r != null && r.getAPIException() != null) {
            return r;
        }
        r = this.checkInFiles(toBeCheckedIn);
        if (r != null && r.getAPIException() != null) {
            return r;
        }
        r = this.checkInFilesDeferred(toBeCheckedInDeferred);
        if (r != null && r.getAPIException() != null) {
            return r;
        }
        r = this.submitFiles(toBeSubmitted);
        if (r != null && r.getAPIException() != null) {
            return r;
        }
        emptySandboxCandidates.removeAll(knownNonemptySandboxes);
        return null;
    }
    
    private Response revertFiles(final WorkingFileList toBeReverted) throws APIException {
        final SIRevertCommand siRevert = new SIRevertCommand(this.getCmdRunnerCreator());
        siRevert.setOverwriteIfChanged(false);
        final GenericWFCommandRunner runner = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siRevert);
        return runner.execute(toBeReverted, this.interactive);
    }
    
    private Response addFiles(final WorkingFileList toBeAdded) throws APIException {
        if (toBeAdded.isEmpty()) {
            return null;
        }
        final SIAddCommand siAdd = new SIAddCommand(this.getCmdRunnerCreator());
        siAdd.setCpid(this.cpid);
        siAdd.setDeferred(false);
        siAdd.setCloseCP(false);
        final GenericWFCommandRunner wfAdd = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siAdd);
        return wfAdd.execute(toBeAdded, this.interactive);
    }
    
    private Response setWritable(final WorkingFileList added) throws APIException {
        if (added.isEmpty()) {
            return null;
        }
        final WorkingFileList toSetWritable = this.getWFListToSetWritable(added);
        if (toSetWritable.isEmpty()) {
            return null;
        }
        final SISetMemberWritableCommand siSetMemberWritable = new SISetMemberWritableCommand(this.getCmdRunnerCreator());
        final GenericWFCommandRunner wfSetMemberWritable = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siSetMemberWritable);
        return wfSetMemberWritable.execute(toSetWritable, this.interactive);
    }
    
    private WorkingFileList getWFListToSetWritable(final WorkingFileList unfiltered) {
        final WorkingFileList filtered = new WorkingFileList();
        String suffixProperties = System.getProperties().getProperty("mks.api.setwritableonsubmit", "exe,bat,dll");
        suffixProperties = suffixProperties.toLowerCase();
        final String[] suffixArray = suffixProperties.split(",");
        for (final WorkingFile wf : unfiltered) {
            for (int i = 0; i < suffixArray.length; ++i) {
                final String suffix = suffixArray[i].trim();
                if (wf.getName().toLowerCase().endsWith("." + suffix)) {
                    filtered.add(wf);
                }
            }
        }
        return filtered;
    }
    
    private Response dropFiles(final WorkingFileList toBeDropped) throws APIException {
        if (toBeDropped.isEmpty()) {
            return null;
        }
        final SIDropCommand siDrop = new SIDropCommand(this.getCmdRunnerCreator());
        siDrop.setCpid(this.cpid);
        siDrop.setDeferred(false);
        siDrop.setCloseCP(false);
        final GenericWFCommandRunner wfDrop = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siDrop);
        return wfDrop.execute(toBeDropped, this.interactive);
    }
    
    private Response lockFiles(final WorkingFileList toBeLocked) throws APIException {
        if (toBeLocked.isEmpty()) {
            return null;
        }
        final SILockCommand siLock = new SILockCommand(this.getCmdRunnerCreator());
        siLock.setCpid(this.cpid);
        siLock.setAllowPrompting(false);
        final GenericWFCommandRunner wfLock = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siLock);
        return wfLock.execute(toBeLocked, this.interactive);
    }
    
    private Response checkInFiles(final WorkingFileList toBeCheckedIn) throws APIException {
        if (toBeCheckedIn.isEmpty()) {
            return null;
        }
        final SICheckinCommand siCheckin = new SICheckinCommand(this.getCmdRunnerCreator());
        siCheckin.setCpid(this.cpid);
        siCheckin.setCloseCP(false);
        siCheckin.setDeferred(false);
        final GenericWFCommandRunner wfCheckin = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siCheckin);
        return wfCheckin.execute(toBeCheckedIn, this.interactive);
    }
    
    private Response checkInFilesDeferred(final WorkingFileList toBeCheckedIn) throws APIException {
        if (toBeCheckedIn.isEmpty()) {
            return null;
        }
        final SICheckinCommand siCheckin = new SICheckinCommand(this.getCmdRunnerCreator());
        siCheckin.setCpid(this.cpid);
        siCheckin.setCloseCP(false);
        siCheckin.setDeferred(true);
        final GenericWFCommandRunner wfCheckin = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siCheckin);
        return wfCheckin.execute(toBeCheckedIn, this.interactive);
    }
    
    private Response submitFiles(final WorkingFileList toBeSubmitted) throws APIException {
        if (toBeSubmitted.isEmpty()) {
            return null;
        }
        final SISubmitCommand siSubmit = new SISubmitCommand(this.getCmdRunnerCreator());
        siSubmit.setCpid(this.cpid);
        siSubmit.setGuiStatus(true);
        siSubmit.setCloseCP(false);
        final GenericWFCommandRunner wfSubmit = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siSubmit);
        return wfSubmit.execute(toBeSubmitted, false);
    }
    
    private boolean isOutOfDateMove(final WorkingFile wf) {
        return wf.isMoved() && wf.getWorkingRev() != null && !wf.getWorkingRev().equals(wf.getMemberRev());
    }
}
