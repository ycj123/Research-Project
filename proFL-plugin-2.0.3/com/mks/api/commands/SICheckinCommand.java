// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import com.mks.api.OptionList;
import com.mks.api.Option;
import com.mks.api.Command;
import com.mks.api.response.Response;
import com.mks.api.SelectionList;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

class SICheckinCommand extends TrackingCommandBase
{
    SICheckinCommand(final CmdRunnerCreator session) throws APIException {
        super(session);
    }
    
    protected Response execute(final SelectionList selection) throws APIException {
        final Command cmd = new Command("ii", "checkin");
        final OptionList options = this.getBaseOptions();
        options.add(this.getTrackableCommandOptions());
        if (this.interactive) {
            options.add(new Option("g"));
        }
        if (selection != null && selection.size() > 0) {
            options.add(new Option("recurse"));
        }
        cmd.setOptionList(options);
        cmd.setSelectionList(selection);
        return this.runAPICommand(cmd);
    }
}
