// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.status;

import java.io.File;
import org.netbeans.lib.cvsclient.event.TerminationEvent;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.request.Request;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import org.netbeans.lib.cvsclient.request.ArgumentRequest;
import org.netbeans.lib.cvsclient.ClientServices;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.BasicCommand;

public class StatusCommand extends BasicCommand
{
    private EventManager eventManager;
    private boolean includeTags;
    
    public Builder createBuilder(final EventManager eventManager) {
        return new StatusBuilder(eventManager, this);
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        clientServices.ensureConnection();
        super.execute(clientServices, this.eventManager = eventManager);
        try {
            if (this.includeTags) {
                this.requests.add(1, new ArgumentRequest("-v"));
            }
            this.addRequestForWorkingDirectory(clientServices);
            this.addArgumentRequests();
            this.addRequest(CommandRequest.STATUS);
            clientServices.processRequests(this.requests);
        }
        catch (CommandException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new CommandException(ex2, ex2.getLocalizedMessage());
        }
        finally {
            this.requests.clear();
        }
    }
    
    public boolean isIncludeTags() {
        return this.includeTags;
    }
    
    public void setIncludeTags(final boolean includeTags) {
        this.includeTags = includeTags;
    }
    
    public void commandTerminated(final TerminationEvent terminationEvent) {
        if (this.builder != null) {
            this.builder.outputDone();
        }
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("status ");
        sb.append(this.getCVSArguments());
        final File[] files = this.getFiles();
        if (files != null) {
            for (int i = 0; i < files.length; ++i) {
                sb.append(files[i].getName());
                sb.append(' ');
            }
        }
        return sb.toString();
    }
    
    public boolean setCVSCommand(final char c, final String s) {
        if (c == 'R') {
            this.setRecursive(true);
        }
        else if (c == 'l') {
            this.setRecursive(false);
        }
        else {
            if (c != 'v') {
                return false;
            }
            this.setIncludeTags(true);
        }
        return true;
    }
    
    public String getOptString() {
        return "Rlv";
    }
    
    public void resetCVSCommand() {
        this.setRecursive(true);
        this.setIncludeTags(false);
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer("");
        if (this.isIncludeTags()) {
            sb.append("-v ");
        }
        if (!this.isRecursive()) {
            sb.append("-l ");
        }
        return sb.toString();
    }
}
