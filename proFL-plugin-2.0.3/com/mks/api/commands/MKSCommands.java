// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import com.mks.api.response.Response;
import com.mks.api.Command;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

abstract class MKSCommands
{
    private CmdRunnerCreator cmdRunnerCreator;
    
    protected MKSCommands(final CmdRunnerCreator session) throws APIException {
        CommandBase.validateConnection(this.cmdRunnerCreator = session);
    }
    
    public final CmdRunnerCreator getCmdRunnerCreator() {
        return this.cmdRunnerCreator;
    }
    
    protected final Response runAPICommand(final Command command) throws APIException {
        return this.runAPICommand(command, false);
    }
    
    protected final Response runAPICommand(final Command command, final boolean generatedStreamedResponse) throws APIException {
        return CommandBase.runAPICommand(this.getCmdRunnerCreator(), command, generatedStreamedResponse);
    }
}
