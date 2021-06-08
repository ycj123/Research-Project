// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.annotate;

import java.io.File;
import org.netbeans.lib.cvsclient.event.TerminationEvent;
import java.util.Iterator;
import org.netbeans.lib.cvsclient.request.EntryRequest;
import java.util.List;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.request.Request;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import org.netbeans.lib.cvsclient.request.ArgumentRequest;
import org.netbeans.lib.cvsclient.ClientServices;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.BasicCommand;

public class AnnotateCommand extends BasicCommand
{
    protected EventManager eventManager;
    private boolean useHeadIfNotFound;
    private String annotateByDate;
    private String annotateByRevision;
    
    public Builder createBuilder(final EventManager eventManager) {
        return new AnnotateBuilder(eventManager, this);
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        this.eventManager = eventManager;
        clientServices.ensureConnection();
        super.execute(clientServices, eventManager);
        this.excludeBinaryFiles(this.requests);
        try {
            if (this.useHeadIfNotFound) {
                this.requests.add(1, new ArgumentRequest("-f"));
            }
            if (this.annotateByDate != null && this.annotateByDate.length() > 0) {
                this.requests.add(1, new ArgumentRequest("-D"));
                this.requests.add(2, new ArgumentRequest(this.getAnnotateByDate()));
            }
            if (this.annotateByRevision != null && this.annotateByRevision.length() > 0) {
                this.requests.add(1, new ArgumentRequest("-r"));
                this.requests.add(2, new ArgumentRequest(this.getAnnotateByRevision()));
            }
            this.addRequestForWorkingDirectory(clientServices);
            this.addArgumentRequests();
            this.addRequest(CommandRequest.ANNOTATE);
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
    
    private void excludeBinaryFiles(final List list) {
        final Iterator<EntryRequest> iterator = list.iterator();
        while (iterator.hasNext()) {
            final EntryRequest next = iterator.next();
            if (next instanceof EntryRequest && next.getEntry().isBinary()) {
                iterator.remove();
                if (!iterator.hasNext()) {
                    continue;
                }
                iterator.next();
                iterator.remove();
            }
        }
    }
    
    public void commandTerminated(final TerminationEvent terminationEvent) {
        if (this.builder != null) {
            this.builder.outputDone();
        }
    }
    
    public boolean isUseHeadIfNotFound() {
        return this.useHeadIfNotFound;
    }
    
    public void setUseHeadIfNotFound(final boolean useHeadIfNotFound) {
        this.useHeadIfNotFound = useHeadIfNotFound;
    }
    
    public String getAnnotateByDate() {
        return this.annotateByDate;
    }
    
    public void setAnnotateByDate(final String annotateByDate) {
        this.annotateByDate = annotateByDate;
    }
    
    public String getAnnotateByRevision() {
        return this.annotateByRevision;
    }
    
    public void setAnnotateByRevision(final String annotateByRevision) {
        this.annotateByRevision = annotateByRevision;
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("annotate ");
        sb.append(this.getCVSArguments());
        final File[] files = this.getFiles();
        if (files != null) {
            for (int i = 0; i < files.length; ++i) {
                sb.append(files[i].getName() + " ");
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
        else if (c == 'r') {
            this.setAnnotateByRevision(s);
        }
        else if (c == 'D') {
            this.setAnnotateByDate(s);
        }
        else {
            if (c != 'f') {
                return false;
            }
            this.setUseHeadIfNotFound(true);
        }
        return true;
    }
    
    public String getOptString() {
        return "Rlr:D:f";
    }
    
    public void resetCVSCommand() {
        this.setRecursive(true);
        this.setAnnotateByDate(null);
        this.setAnnotateByRevision(null);
        this.setUseHeadIfNotFound(false);
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer("");
        if (!this.isRecursive()) {
            sb.append("-l ");
        }
        if (this.getAnnotateByRevision() != null) {
            sb.append("-r ");
            sb.append(this.getAnnotateByRevision());
            sb.append(" ");
        }
        if (this.getAnnotateByDate() != null) {
            sb.append("-D ");
            sb.append(this.getAnnotateByDate());
            sb.append(" ");
        }
        if (this.isUseHeadIfNotFound()) {
            sb.append("-f ");
        }
        return sb.toString();
    }
}
