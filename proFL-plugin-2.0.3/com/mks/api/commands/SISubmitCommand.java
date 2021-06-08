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

class SISubmitCommand extends CommandBase implements IHasChangePackage, IWorkingFileCompatibleCommand
{
    String sandbox;
    String cwd;
    Boolean closeCP;
    String cpid;
    boolean guiStatus;
    
    SISubmitCommand(final CmdRunnerCreator session) throws APIException {
        super(session);
        this.sandbox = null;
        this.cwd = null;
        this.closeCP = null;
        this.cpid = null;
        this.guiStatus = false;
    }
    
    protected Response execute(final SelectionList selection) throws APIException {
        final Command cmd = new Command("si", "submit");
        final OptionList options = this.getBaseOptions();
        if (this.interactive) {
            options.add(new Option("gui"));
        }
        else {
            options.add(new Option("yes"));
        }
        if (this.sandbox != null) {
            options.add("sandbox", this.sandbox);
        }
        if (this.cwd != null) {
            options.add("cwd", this.cwd);
        }
        if (this.closeCP != null) {
            options.add(this.createBinaryOption("closeCP", this.closeCP));
        }
        if (this.cpid != null) {
            options.add("cpid", this.cpid);
        }
        if (this.guiStatus) {
            options.add("status", "gui");
        }
        if (selection != null && selection.size() > 0) {
            options.add(new Option("recurse"));
        }
        cmd.setOptionList(options);
        cmd.setSelectionList(selection);
        return this.runAPICommand(cmd);
    }
    
    public boolean isCloseCPOverridden() {
        return this.closeCP != null;
    }
    
    public void resetCloseCP() {
        this.closeCP = null;
    }
    
    public void setCloseCP(final boolean closeCP) {
        this.closeCP = new Boolean(closeCP);
    }
    
    public void setCpid(final String cpid) {
        this.cpid = cpid;
    }
    
    public void setSandbox(final String sandbox) {
        this.sandbox = sandbox;
    }
    
    public void setCwd(final String cwd) {
        this.cwd = cwd;
    }
    
    public void setGuiStatus(final boolean guiStatus) {
        this.guiStatus = guiStatus;
    }
}
