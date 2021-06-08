// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.watchers;

import java.io.File;
import org.netbeans.lib.cvsclient.event.TerminationEvent;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.request.Request;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import org.netbeans.lib.cvsclient.ClientServices;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.BasicCommand;

public class WatchersCommand extends BasicCommand
{
    public WatchersCommand() {
        this.resetCVSCommand();
    }
    
    public Builder createBuilder(final EventManager eventManager) {
        return new WatchersBuilder(eventManager, this.getLocalDirectory());
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        clientServices.ensureConnection();
        super.execute(clientServices, eventManager);
        try {
            this.addRequestForWorkingDirectory(clientServices);
            this.addArgumentRequests();
            this.addRequest(CommandRequest.WATCHERS);
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
    
    public void commandTerminated(final TerminationEvent terminationEvent) {
        if (this.builder != null) {
            this.builder.outputDone();
        }
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("watchers ");
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
        else {
            if (c != 'l') {
                return false;
            }
            this.setRecursive(false);
        }
        return true;
    }
    
    public String getOptString() {
        return "Rl";
    }
    
    public void resetCVSCommand() {
        this.setRecursive(true);
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer();
        if (!this.isRecursive()) {
            sb.append("-l ");
        }
        return sb.toString();
    }
}
