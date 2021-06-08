// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.log;

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

public class LogCommand extends BasicCommand
{
    protected EventManager eventManager;
    private boolean defaultBranch;
    private String dateFilter;
    private boolean headerOnly;
    private boolean noTags;
    private String revisionFilter;
    private String stateFilter;
    private String userFilter;
    private boolean headerAndDescOnly;
    
    public LogCommand() {
        this.resetCVSCommand();
    }
    
    public Builder createBuilder(final EventManager eventManager) {
        return new LogBuilder(eventManager, this);
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        clientServices.ensureConnection();
        super.execute(clientServices, this.eventManager = eventManager);
        try {
            if (this.defaultBranch) {
                this.requests.add(1, new ArgumentRequest("-b"));
            }
            if (this.headerAndDescOnly) {
                this.requests.add(1, new ArgumentRequest("-t"));
            }
            if (this.headerOnly) {
                this.requests.add(1, new ArgumentRequest("-h"));
            }
            if (this.noTags) {
                this.requests.add(1, new ArgumentRequest("-N"));
            }
            if (this.userFilter != null) {
                this.requests.add(1, new ArgumentRequest("-w" + this.userFilter));
            }
            if (this.revisionFilter != null) {
                this.requests.add(1, new ArgumentRequest("-r" + this.revisionFilter));
            }
            if (this.stateFilter != null) {
                this.requests.add(1, new ArgumentRequest("-s" + this.stateFilter));
            }
            if (this.dateFilter != null) {
                this.requests.add(1, new ArgumentRequest("-d" + this.dateFilter));
            }
            this.addRequestForWorkingDirectory(clientServices);
            this.addArgumentRequests();
            this.addRequest(CommandRequest.LOG);
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
            if (!this.isBuilderSet()) {
                this.builder = null;
            }
        }
    }
    
    public void commandTerminated(final TerminationEvent terminationEvent) {
        if (this.builder != null) {
            this.builder.outputDone();
        }
    }
    
    public boolean isDefaultBranch() {
        return this.defaultBranch;
    }
    
    public void setDefaultBranch(final boolean defaultBranch) {
        this.defaultBranch = defaultBranch;
    }
    
    public String getDateFilter() {
        return this.dateFilter;
    }
    
    public void setDateFilter(final String dateFilter) {
        this.dateFilter = dateFilter;
    }
    
    public boolean isHeaderOnly() {
        return this.headerOnly;
    }
    
    public void setHeaderOnly(final boolean headerOnly) {
        this.headerOnly = headerOnly;
    }
    
    public boolean isNoTags() {
        return this.noTags;
    }
    
    public void setNoTags(final boolean noTags) {
        this.noTags = noTags;
    }
    
    public String getRevisionFilter() {
        return this.revisionFilter;
    }
    
    public void setRevisionFilter(final String revisionFilter) {
        this.revisionFilter = revisionFilter;
    }
    
    public String getStateFilter() {
        return this.stateFilter;
    }
    
    public void setStateFilter(final String stateFilter) {
        this.stateFilter = stateFilter;
    }
    
    public String getUserFilter() {
        return this.userFilter;
    }
    
    public void setUserFilter(final String userFilter) {
        this.userFilter = userFilter;
    }
    
    public boolean isHeaderAndDescOnly() {
        return this.headerAndDescOnly;
    }
    
    public void setHeaderAndDescOnly(final boolean headerAndDescOnly) {
        this.headerAndDescOnly = headerAndDescOnly;
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("log ");
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
        else if (c == 'b') {
            this.setDefaultBranch(true);
        }
        else if (c == 'h') {
            this.setHeaderOnly(true);
        }
        else if (c == 't') {
            this.setHeaderAndDescOnly(true);
        }
        else if (c == 'N') {
            this.setNoTags(true);
        }
        else if (c == 'd') {
            this.setDateFilter(s);
        }
        else if (c == 'r') {
            this.setRevisionFilter((s == null) ? "" : s);
        }
        else if (c == 's') {
            this.setStateFilter(s);
        }
        else {
            if (c != 'w') {
                return false;
            }
            this.setUserFilter((s == null) ? "" : s);
        }
        return true;
    }
    
    public void resetCVSCommand() {
        this.setRecursive(true);
        this.setDefaultBranch(false);
        this.setHeaderOnly(false);
        this.setHeaderAndDescOnly(false);
        this.setNoTags(false);
        this.setDateFilter(null);
        this.setRevisionFilter(null);
        this.setStateFilter(null);
        this.setUserFilter(null);
    }
    
    public String getOptString() {
        return "RlbhtNd:r:s:w:";
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer("");
        if (this.isDefaultBranch()) {
            sb.append("-b ");
        }
        if (this.isHeaderAndDescOnly()) {
            sb.append("-t ");
        }
        if (this.isHeaderOnly()) {
            sb.append("-h ");
        }
        if (this.isNoTags()) {
            sb.append("-N ");
        }
        if (!this.isRecursive()) {
            sb.append("-l ");
        }
        if (this.userFilter != null) {
            sb.append("-w");
            sb.append(this.userFilter);
            sb.append(' ');
        }
        if (this.revisionFilter != null) {
            sb.append("-r");
            sb.append(this.revisionFilter);
            sb.append(' ');
        }
        if (this.stateFilter != null) {
            sb.append("-s");
            sb.append(this.stateFilter);
            sb.append(' ');
        }
        if (this.dateFilter != null) {
            sb.append("-d");
            sb.append(this.dateFilter);
            sb.append(' ');
        }
        return sb.toString();
    }
}
