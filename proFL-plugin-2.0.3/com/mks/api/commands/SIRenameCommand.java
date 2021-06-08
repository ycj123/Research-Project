// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import com.mks.api.OptionList;
import com.mks.api.Command;
import com.mks.api.Option;
import com.mks.api.response.InvalidCommandSelectionException;
import com.mks.api.response.Response;
import com.mks.api.SelectionList;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

class SIRenameCommand extends TrackingCommandBase
{
    private String newName;
    private Boolean renameFile;
    
    SIRenameCommand(final CmdRunnerCreator session) throws APIException {
        super(session);
    }
    
    protected Response execute(final SelectionList selection) throws APIException {
        if (selection == null || selection.size() != 1) {
            throw new InvalidCommandSelectionException("The SI Rename command requires a selection of exactly one member.");
        }
        final OptionList options = this.getBaseOptions();
        options.add(this.getTrackableCommandOptions());
        if (this.interactive) {
            options.add(new Option("g"));
        }
        options.add("newName", this.newName);
        if (this.renameFile != null) {
            options.add(this.createBinaryOption("renameWorkingFile", this.renameFile));
        }
        final Command cmd = new Command("si", "rename");
        cmd.setOptionList(options);
        cmd.setSelectionList(selection);
        return this.runAPICommand(cmd);
    }
    
    public void setNewName(final String newName) {
        this.newName = newName;
    }
    
    public void setRenameFile(final boolean rename) {
        this.renameFile = new Boolean(rename);
    }
}
