// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import com.mks.api.Command;
import com.mks.api.response.InvalidCommandSelectionException;
import com.mks.api.response.Response;
import com.mks.api.SelectionList;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

class SISetMemberWritableCommand extends TrackingCommandBase
{
    SISetMemberWritableCommand(final CmdRunnerCreator session) throws APIException {
        super(session);
    }
    
    protected Response execute(final SelectionList selection) throws APIException {
        if (selection == null || selection.size() == 0) {
            throw new InvalidCommandSelectionException("The SISetMemberWritable command requires a selection.");
        }
        final Command myCommand = new Command("si", "si.HooksSetAlwaysWritable");
        myCommand.setSelectionList(selection);
        return this.runAPICommand(myCommand);
    }
}
