// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import com.mks.api.Option;
import com.mks.api.Command;
import com.mks.api.response.Response;
import com.mks.api.SelectionList;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

class SILockCommand extends CommandBase implements IWorkingFileCompatibleCommand, IHasChangePackage
{
    private String sandbox;
    private Boolean closeCP;
    private String cpid;
    private String cwd;
    private Boolean allowPrompting;
    private String action;
    private String lockType;
    
    SILockCommand(final CmdRunnerCreator session) throws APIException {
        super(session);
        this.sandbox = null;
        this.closeCP = null;
        this.cpid = null;
        this.cwd = null;
        this.allowPrompting = null;
        this.action = null;
        this.lockType = null;
    }
    
    protected Response execute(final SelectionList selection) throws APIException {
        final Command cmd = new Command("si", "lock");
        if (this.interactive) {
            if (this.allowPrompting != null && this.allowPrompting) {
                cmd.addOption(new Option("settingsUI", "gui"));
            }
            else {
                cmd.addOption(new Option("status", "gui"));
            }
        }
        if (this.sandbox != null) {
            cmd.addOption(new Option("sandbox", this.sandbox));
        }
        if (this.cwd != null) {
            cmd.addOption(new Option("cwd", this.cwd));
        }
        if (this.cpid != null) {
            cmd.addOption(new Option("cpid", this.cpid));
        }
        cmd.addOption(new Option("recurse"));
        if (this.action != null) {
            cmd.addOption(new Option("action", this.action));
        }
        if (this.lockType != null) {
            cmd.addOption(new Option("lockType", this.lockType));
        }
        cmd.setSelectionList(selection);
        return this.runAPICommand(cmd);
    }
    
    public void setAllowPrompting(final boolean allowPrompting) {
        this.allowPrompting = new Boolean(allowPrompting);
    }
    
    public void setAction(final String action) {
        this.action = action;
    }
    
    public void setLockType(final String lockType) {
        this.lockType = lockType;
    }
    
    public void setSandbox(final String sandbox) {
        this.sandbox = sandbox;
    }
    
    public void setCwd(final String cwd) {
        this.cwd = cwd;
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
}
