// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import com.mks.api.Option;
import com.mks.api.OptionList;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;

abstract class TrackingCommandBase extends CommandBase implements IHasChangePackage, IWorkingFileCompatibleCommand
{
    private String sandbox;
    private String cwd;
    private String cpid;
    private Boolean closeCP;
    private Boolean deferred;
    private boolean allowCreateSubs;
    
    protected TrackingCommandBase(final CmdRunnerCreator session) throws APIException {
        super(session);
        this.sandbox = null;
        this.cwd = null;
        this.cpid = null;
        this.closeCP = null;
        this.deferred = null;
        this.allowCreateSubs = true;
    }
    
    public void setCloseCP(final boolean closeCP) {
        this.closeCP = new Boolean(closeCP);
    }
    
    public boolean isCloseCPOverridden() {
        return this.closeCP != null;
    }
    
    public void resetCloseCP() {
        this.closeCP = null;
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
    
    public void setAllowCreateSubs(final boolean allowCreateSubs) {
        this.allowCreateSubs = allowCreateSubs;
    }
    
    public void setDeferred(final boolean deferred) {
        this.deferred = new Boolean(deferred);
    }
    
    protected OptionList getTrackableCommandOptions() {
        final OptionList options = new OptionList();
        if (this.sandbox != null) {
            options.add("sandbox", this.sandbox);
        }
        if (this.cwd != null) {
            options.add("cwd", this.cwd);
        }
        if (this.cpid != null) {
            options.add("cpid", this.cpid);
        }
        if (this.deferred != null && this.deferred) {
            options.add(new Option("defer"));
        }
        if (this.deferred != null && !this.deferred) {
            options.add(new Option("nodefer"));
        }
        if (!this.allowCreateSubs) {
            options.add(new Option("nocreateSubprojects"));
        }
        if (this.closeCP != null) {
            options.add(this.createBinaryOption("closecp", this.closeCP));
        }
        return options;
    }
}
