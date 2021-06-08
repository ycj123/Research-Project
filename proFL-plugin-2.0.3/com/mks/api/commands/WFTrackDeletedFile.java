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

class WFTrackDeletedFile extends WorkingFileCommandBase
{
    private String cpid;
    
    WFTrackDeletedFile(final CmdRunnerCreator session) throws APIException {
        super(session);
        this.cpid = null;
    }
    
    protected Response execute(final WorkingFileList workingFiles) throws APIException {
        final WorkingFileList toBeDropped = new WorkingFileList();
        final WorkingFileList toBeReverted = new WorkingFileList();
        for (final WorkingFile wf : workingFiles) {
            if (wf.isDeferred() || wf.isLockedByMe()) {
                toBeReverted.add(wf);
            }
            if (wf.isMember()) {
                toBeDropped.add(wf);
            }
        }
        Response[] response = null;
        if (toBeReverted.size() > 0) {
            final SIRevertCommand siRevert = new SIRevertCommand(this.getCmdRunnerCreator());
            response = this.runApiCommand(siRevert, toBeReverted, this.interactive);
            for (int j = 0; j < response.length; ++j) {
                if (response[j] != null && response[j].getAPIException() != null) {
                    return response[j];
                }
            }
        }
        if (toBeDropped.size() > 0) {
            final SIDropCommand siDrop = new SIDropCommand(this.getCmdRunnerCreator());
            siDrop.setDeferred(true);
            siDrop.setCloseCP(false);
            siDrop.setCpid(this.cpid);
            response = this.runApiCommand(siDrop, toBeDropped, this.interactive);
            for (int j = 0; j < response.length; ++j) {
                if (response[j] != null && response[j].getAPIException() != null) {
                    return response[j];
                }
            }
        }
        return null;
    }
    
    public void setCpid(final String cpid) {
        this.cpid = cpid;
    }
}
