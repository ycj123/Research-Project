// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import java.util.Iterator;
import com.mks.api.commands.ide.WorkingFile;
import com.mks.api.response.Response;
import com.mks.api.commands.ide.WorkingFileList;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

class WFTrackAddToCP extends WFTrackCommandBase
{
    WFTrackAddToCP(final CmdRunnerCreator session) throws APIException {
        super(session);
    }
    
    protected Response execute(final WorkingFileList workingFiles) throws APIException {
        final WorkingFileList toBeReverted = new WorkingFileList();
        final WorkingFileList toBeAdded = new WorkingFileList();
        final WorkingFileList toBeDropped = new WorkingFileList();
        final WorkingFileList toBeMoved = new WorkingFileList();
        final WorkingFileList toBeUnlocked = new WorkingFileList();
        final WorkingFileList toBeLocked = new WorkingFileList();
        for (final WorkingFile wf : workingFiles) {
            if (this.cpid == null) {
                if (wf.getWorkingCpid() == null || wf.getWorkingCpid().length() == 0) {
                    continue;
                }
                toBeReverted.add(wf);
            }
            else {
                if (this.cpid.equals(wf.getWorkingCpid())) {
                    continue;
                }
                boolean revert = false;
                if (!wf.isControlled() && wf.getFile().exists()) {
                    toBeAdded.add(wf);
                }
                if (!wf.getFile().exists()) {
                    toBeDropped.add(wf);
                }
                if (wf.isAdded()) {
                    toBeAdded.add(wf);
                    revert = true;
                }
                if (wf.isDropped()) {
                    toBeDropped.add(wf);
                    revert = true;
                }
                if (wf.isMoved()) {
                    throw new APIException("INVALID_MOVE_TO_CP_OPERATION");
                }
                if (revert) {
                    toBeReverted.add(wf);
                }
                if (wf.isLockedByMe()) {
                    toBeUnlocked.add(wf);
                }
                if (wf.isDropped() || (!wf.isModified() && !wf.isLockedByMe())) {
                    continue;
                }
                toBeLocked.add(wf);
            }
        }
        Response r = this.revertDeferred(toBeReverted, false);
        if (r != null && r.getAPIException() != null) {
            return r;
        }
        r = this.unlockFiles(toBeUnlocked);
        if (r != null && r.getAPIException() != null) {
            return r;
        }
        r = this.dropFiles(toBeDropped);
        if (r != null && r.getAPIException() != null) {
            return r;
        }
        r = this.addFiles(toBeAdded);
        if (r != null && r.getAPIException() != null) {
            return r;
        }
        r = this.moveFiles(toBeMoved);
        if (r != null && r.getAPIException() != null) {
            return r;
        }
        r = this.lockFiles(toBeLocked);
        if (r != null && r.getAPIException() != null) {
            return r;
        }
        return null;
    }
    
    private Response dropFiles(final WorkingFileList toBeDropped) throws APIException {
        if (toBeDropped.isEmpty()) {
            return null;
        }
        final WFTrackDeletedFile wfDeleted = new WFTrackDeletedFile(this.getCmdRunnerCreator());
        wfDeleted.setCpid(this.cpid);
        return wfDeleted.execute(this.update(toBeDropped), this.interactive);
    }
    
    private Response addFiles(final WorkingFileList toBeAdded) throws APIException {
        if (toBeAdded.isEmpty()) {
            return null;
        }
        final WFTrackNewFile wfNew = new WFTrackNewFile(this.getCmdRunnerCreator());
        wfNew.setCpid(this.cpid);
        wfNew.setPreferredRoot(this.getPreferredRoot());
        return wfNew.execute(this.update(toBeAdded), this.interactive);
    }
    
    private Response moveFiles(final WorkingFileList toBeMoved) throws APIException {
        if (toBeMoved.isEmpty()) {
            return null;
        }
        final WFTrackMovedFile wfMoved = new WFTrackMovedFile(this.getCmdRunnerCreator());
        wfMoved.setCpid(this.cpid);
        wfMoved.setPreferredRoot(this.getPreferredRoot());
        final String[] fromNames = new String[toBeMoved.size()];
        final String[] toNames = new String[toBeMoved.size()];
        int index = 0;
        for (final WorkingFile wf : toBeMoved) {
            fromNames[index] = wf.getMemberName().getAbsolutePath();
            toNames[index] = wf.getFile().getAbsolutePath();
            ++index;
        }
        wfMoved.setMapping(fromNames, toNames);
        return wfMoved.execute(fromNames, this.interactive);
    }
    
    private Response unlockFiles(final WorkingFileList toBeUnlocked) throws APIException {
        if (toBeUnlocked.isEmpty()) {
            return null;
        }
        final SIUnlockCommand siUnlock = new SIUnlockCommand(this.getCmdRunnerCreator());
        siUnlock.setAction("remove");
        final GenericWFCommandRunner wfUnlock = new GenericWFCommandRunner(this.getCmdRunnerCreator(), siUnlock);
        return wfUnlock.execute(toBeUnlocked, this.interactive);
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
}
