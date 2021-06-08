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

class SIRevertCommand extends CommandBase implements IWorkingFileCompatibleCommand
{
    private String sandbox;
    private String cwd;
    private Boolean overwriteChanged;
    private Boolean overwriteDeferred;
    
    SIRevertCommand(final CmdRunnerCreator session) throws APIException {
        super(session);
        this.sandbox = null;
        this.cwd = null;
        this.overwriteChanged = null;
        this.overwriteDeferred = null;
    }
    
    protected Response execute(final SelectionList selection) throws APIException {
        if (selection == null || selection.size() == 0) {
            throw new InvalidCommandSelectionException("SICommands.siRevertMembers: parameter 'members' cannot be null or empty.");
        }
        final Command myCommand = new Command("si", "scc-revert");
        final OptionList options = this.getBaseOptions();
        if (this.overwriteChanged != null) {
            options.add(this.createBinaryOption("overwriteChanged", this.overwriteChanged));
        }
        if (this.overwriteDeferred != null) {
            options.add(this.createBinaryOption("overwriteDeferred", this.overwriteDeferred));
        }
        if (this.interactive) {
            options.add(new Option("g"));
        }
        if (this.sandbox != null) {
            options.add("sandbox", this.sandbox);
        }
        if (this.cwd != null) {
            options.add("cwd", this.cwd);
        }
        options.add(new Option("recurse"));
        myCommand.setOptionList(options);
        myCommand.setSelectionList(selection);
        return this.runAPICommand(myCommand);
    }
    
    public void setSandbox(final String sandbox) {
        this.sandbox = sandbox;
    }
    
    public void setCwd(final String cwd) {
        this.cwd = cwd;
    }
    
    public void setOverwriteIfChanged(final boolean overwrite) {
        this.overwriteChanged = new Boolean(overwrite);
    }
    
    public void setOverwriteIfDeferred(final boolean overwrite) {
        this.overwriteDeferred = new Boolean(overwrite);
    }
}
