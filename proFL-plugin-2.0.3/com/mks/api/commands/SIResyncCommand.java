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

class SIResyncCommand extends CommandBase implements IWorkingFileCompatibleCommand
{
    private String sandbox;
    private String cwd;
    private Boolean recurse;
    private Boolean overwriteChanged;
    private Boolean merge;
    private String mergeType;
    private String mergeConflict;
    private Boolean overwriteDeferred;
    private Boolean overwritePending;
    private Boolean downgradeLockConflict;
    
    SIResyncCommand(final CmdRunnerCreator session) throws APIException {
        super(session);
        this.sandbox = null;
        this.cwd = null;
    }
    
    protected Response execute(final SelectionList selection) throws APIException {
        final OptionList options = this.getBaseOptions();
        final Command myCommand = new Command("si", "resync");
        if (this.sandbox != null) {
            options.add("sandbox", this.sandbox);
        }
        if (this.cwd != null) {
            options.add("cwd", this.cwd);
        }
        if (this.interactive) {
            options.add(new Option("gui"));
        }
        if (this.recurse != null) {
            options.add(this.createBinaryOption("recurse", this.recurse));
        }
        if (this.overwriteChanged != null) {
            options.add(this.createBinaryOption("overwriteChanged", this.overwriteChanged));
        }
        if (this.overwriteDeferred != null) {
            options.add(this.createBinaryOption("overwriteDeferred", this.overwriteDeferred));
        }
        if (this.overwritePending != null) {
            options.add(this.createBinaryOption("overwriteIfPending", this.overwritePending));
        }
        if (this.merge != null) {
            options.add(this.createBinaryOption("merge", this.merge));
        }
        if (this.mergeType != null) {
            options.add(new Option("mergeType", this.mergeType));
        }
        if (this.mergeConflict != null) {
            options.add(new Option("onMergeConflict", this.mergeConflict));
        }
        if (this.downgradeLockConflict != null) {
            options.add(this.createBinaryOption("downgradeOnLockConflict", this.downgradeLockConflict));
        }
        if (selection != null && selection.size() > 0 && this.recurse == null) {
            options.add(new Option("recurse"));
        }
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
    
    public void setRecurse(final boolean recurse) {
        this.recurse = new Boolean(recurse);
    }
    
    public void setOverwrite(final boolean overwrite) {
        this.overwriteChanged = new Boolean(overwrite);
    }
    
    public void setMerge(final boolean merge) {
        this.merge = new Boolean(merge);
    }
    
    public void setMergeType(final String mergeType) {
        this.mergeType = mergeType;
    }
    
    public void setMergeConflict(final String mergeConflict) {
        this.mergeConflict = mergeConflict;
    }
    
    public void setOverwriteDeferred(final boolean overwrite) {
        this.overwriteDeferred = new Boolean(overwrite);
    }
    
    public void setOverwritePending(final boolean overwrite) {
        this.overwritePending = new Boolean(overwrite);
    }
    
    public void setDowngradeOnLockConflict(final boolean downgrade) {
        this.downgradeLockConflict = new Boolean(downgrade);
    }
}
