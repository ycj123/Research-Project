// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import java.util.Iterator;
import java.util.List;
import com.mks.api.commands.ide.WorkingFileFactory;
import com.mks.api.commands.ide.WorkingFile;
import java.util.ArrayList;
import com.mks.api.response.ItemNotFoundException;
import com.mks.api.response.Response;
import com.mks.api.commands.ide.WorkingFileList;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

abstract class WFTrackCommandBase extends WorkingFileCommandBase
{
    protected String cpid;
    protected boolean allowCreateSubs;
    
    WFTrackCommandBase(final CmdRunnerCreator session) throws APIException {
        super(session);
        this.cpid = null;
        this.allowCreateSubs = true;
    }
    
    protected Response revertDeferred(final WorkingFileList toBeReverted, final boolean overwriteIfDeferred) throws APIException {
        if (toBeReverted.size() == 0) {
            return null;
        }
        final SIRevertCommand siRevert = new SIRevertCommand(this.getCmdRunnerCreator());
        siRevert.setOverwriteIfDeferred(overwriteIfDeferred);
        siRevert.setOverwriteIfChanged(false);
        try {
            final Response[] response = this.runApiCommand(siRevert, toBeReverted, this.interactive);
            for (int j = 0; j < response.length; ++j) {
                if (response[j] != null && response[j].getAPIException() != null) {
                    return response[j];
                }
            }
        }
        catch (ItemNotFoundException infx) {
            return null;
        }
        return null;
    }
    
    public void setCpid(final String cpid) {
        this.cpid = cpid;
    }
    
    public void setAllowCreateSubs(final boolean allowCreateSubs) {
        this.allowCreateSubs = allowCreateSubs;
    }
    
    protected WorkingFileList update(final WorkingFileList outdated) throws APIException {
        final ArrayList fileNames = new ArrayList();
        for (final WorkingFile wf : outdated) {
            fileNames.add(wf.getFile().getAbsolutePath());
        }
        return WorkingFileFactory.getWorkingFiles(this.getCmdRunnerCreator(), fileNames);
    }
}
