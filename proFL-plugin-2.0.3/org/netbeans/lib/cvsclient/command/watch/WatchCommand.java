// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.watch;

import org.netbeans.lib.cvsclient.event.TerminationEvent;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.request.Request;
import org.netbeans.lib.cvsclient.request.ArgumentRequest;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.ClientServices;
import org.netbeans.lib.cvsclient.command.Watch;
import org.netbeans.lib.cvsclient.command.BasicCommand;

public class WatchCommand extends BasicCommand
{
    private WatchMode watchMode;
    private Watch watch;
    
    public WatchCommand() {
        this.resetCVSCommand();
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        this.checkState();
        clientServices.ensureConnection();
        try {
            super.execute(clientServices, eventManager);
            if (this.getWatchMode().isWatchOptionAllowed()) {
                final String[] arguments = this.getWatchNotNull().getArguments();
                for (int i = 0; i < arguments.length; ++i) {
                    this.addRequest(new ArgumentRequest("-a"));
                    this.addRequest(new ArgumentRequest(arguments[i]));
                }
            }
            this.addRequestForWorkingDirectory(clientServices);
            this.addArgumentRequests();
            this.addRequest(this.getWatchMode().getCommand());
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
        this.setWatch(null);
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("watch ");
        sb.append(this.getCVSArguments());
        this.appendFileArguments(sb);
        return sb.toString();
    }
    
    public String getCVSArguments() {
        this.checkState();
        final StringBuffer sb = new StringBuffer();
        sb.append(this.getWatchMode().toString());
        sb.append(' ');
        if (!this.isRecursive()) {
            sb.append("-l ");
        }
        if (this.getWatchMode().isWatchOptionAllowed()) {
            sb.append("-a ");
            sb.append(this.getWatchNotNull().toString());
        }
        return sb.toString();
    }
    
    public WatchMode getWatchMode() {
        return this.watchMode;
    }
    
    public void setWatchMode(final WatchMode watchMode) {
        this.watchMode = watchMode;
    }
    
    public Watch getWatch() {
        return this.watch;
    }
    
    private Watch getWatchNotNull() {
        if (this.watch == null) {
            return Watch.ALL;
        }
        return this.watch;
    }
    
    public void setWatch(final Watch watch) {
        this.watch = watch;
    }
    
    private void checkState() {
        if (this.getWatchMode() == null) {
            throw new IllegalStateException("Watch mode expected!");
        }
    }
}
