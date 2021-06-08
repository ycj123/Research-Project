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

class WFTrackNewFile extends WFTrackCommandBase
{
    private boolean binary;
    
    WFTrackNewFile(final CmdRunnerCreator session, final boolean binary) throws APIException {
        super(session);
        this.binary = binary;
    }
    
    WFTrackNewFile(final CmdRunnerCreator session) throws APIException {
        this(session, false);
    }
    
    protected Response execute(final WorkingFileList workingFiles) throws APIException {
        final WorkingFileList toBeAdded = new WorkingFileList();
        final WorkingFileList toBeReverted = new WorkingFileList();
        for (final WorkingFile wf : workingFiles) {
            if (!wf.isInSandboxDir()) {
                continue;
            }
            if (wf.isControlled() && !wf.isFormerMember() && wf.isDropped()) {
                toBeReverted.add(wf);
            }
            else {
                if (wf.isMember()) {
                    continue;
                }
                toBeAdded.add(wf);
            }
        }
        if (!toBeReverted.isEmpty()) {
            final Response r = this.revertDeferred(toBeReverted, true);
            if (r != null && r.getAPIException() != null) {
                return r;
            }
        }
        if (!toBeAdded.isEmpty()) {
            final SIAddCommand siAdd = new SIAddCommand(this.getCmdRunnerCreator());
            siAdd.setDeferred(true);
            siAdd.setCpid(this.cpid);
            siAdd.setBinary(this.binary);
            siAdd.setCloseCP(false);
            siAdd.setAllowCreateSubs(this.allowCreateSubs);
            Response[] response = null;
            response = this.runApiCommand(siAdd, toBeAdded, this.interactive);
            for (int j = 0; j < response.length; ++j) {
                if (response[j] != null && response[j].getAPIException() != null) {
                    return response[j];
                }
            }
        }
        return null;
    }
}
