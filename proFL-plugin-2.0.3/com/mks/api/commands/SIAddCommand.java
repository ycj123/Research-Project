// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import com.mks.api.OptionList;
import com.mks.api.Option;
import com.mks.api.Command;
import com.mks.api.response.InvalidCommandSelectionException;
import com.mks.api.response.Response;
import com.mks.api.SelectionList;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

class SIAddCommand extends TrackingCommandBase
{
    private boolean binary;
    
    SIAddCommand(final CmdRunnerCreator session) throws APIException {
        super(session);
        this.binary = false;
    }
    
    protected Response execute(final SelectionList selection) throws APIException {
        final OptionList options = this.getBaseOptions();
        if (selection == null || selection.size() == 0) {
            throw new InvalidCommandSelectionException("The SI Add command requires a selection.");
        }
        final Command myCommand = new Command("si", "add");
        if (this.interactive) {
            options.add(new Option("g"));
        }
        if (this.binary) {
            options.add(new Option("--binaryFormat"));
        }
        options.add(this.getTrackableCommandOptions());
        myCommand.setOptionList(options);
        myCommand.setSelectionList(selection);
        return this.runAPICommand(myCommand);
    }
    
    public void setBinary(final boolean binary) {
        this.binary = binary;
    }
}
