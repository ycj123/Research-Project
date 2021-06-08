// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import com.mks.api.response.Response;
import com.mks.api.commands.ide.WorkingFileList;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

class GenericWFCommandRunner extends WorkingFileCommandBase
{
    private IWorkingFileCompatibleCommand cmd;
    
    GenericWFCommandRunner(final CmdRunnerCreator session, final IWorkingFileCompatibleCommand cmd) throws APIException {
        super(session);
        this.cmd = cmd;
    }
    
    protected Response execute(final WorkingFileList workingFiles) throws APIException {
        final Response[] response = this.runApiCommand(this.cmd, workingFiles, this.interactive);
        for (int i = 0; i < response.length; ++i) {
            if (response[i] != null && response[i].getAPIException() != null) {
                return response[i];
            }
        }
        return null;
    }
}
