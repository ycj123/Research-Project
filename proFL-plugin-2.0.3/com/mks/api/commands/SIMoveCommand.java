// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import com.mks.api.OptionList;
import java.io.File;
import com.mks.api.Option;
import com.mks.api.Command;
import com.mks.api.response.InvalidCommandSelectionException;
import com.mks.api.response.Response;
import com.mks.api.SelectionList;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

class SIMoveCommand extends TrackingCommandBase
{
    private String targetDir;
    private String targetSandbox;
    private Boolean moveFile;
    
    SIMoveCommand(final CmdRunnerCreator session) throws APIException {
        super(session);
    }
    
    protected Response execute(final SelectionList selection) throws APIException {
        if (selection == null || selection.size() == 0) {
            throw new InvalidCommandSelectionException("The SI Move command requires a selection.");
        }
        final Command cmd = new Command("si", "move");
        final OptionList options = this.getBaseOptions();
        options.add(this.getTrackableCommandOptions());
        if (this.interactive) {
            options.add(new Option("g"));
        }
        final File targetDirFile = new File(this.targetDir);
        final File sandboxDirFile = new File(this.targetSandbox).getParentFile();
        if (targetDirFile.equals(sandboxDirFile)) {
            this.targetDir = null;
        }
        if (this.targetDir != null) {
            options.add("targetDir", this.targetDir);
        }
        if (this.targetSandbox != null) {
            options.add("targetSandbox", this.targetSandbox);
        }
        if (this.moveFile != null) {
            options.add(this.createBinaryOption("moveWorkingFile", this.moveFile));
        }
        cmd.setOptionList(options);
        cmd.setSelectionList(selection);
        return this.runAPICommand(cmd);
    }
    
    public void setTargetDir(final String targetDir) {
        this.targetDir = targetDir;
    }
    
    public void setTargetSandbox(final String targetSandbox) {
        this.targetSandbox = targetSandbox;
    }
    
    public void setMoveWorking(final boolean moveFile) {
        this.moveFile = new Boolean(moveFile);
    }
}
