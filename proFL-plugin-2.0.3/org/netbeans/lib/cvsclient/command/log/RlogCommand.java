// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.log;

import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import java.util.Iterator;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import org.netbeans.lib.cvsclient.request.ArgumentRequest;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.ClientServices;
import java.util.LinkedList;
import java.util.List;
import org.netbeans.lib.cvsclient.command.BasicCommand;

public class RlogCommand extends BasicCommand
{
    private final List modules;
    private boolean defaultBranch;
    private String dateFilter;
    private boolean headerOnly;
    private boolean suppressHeader;
    private boolean noTags;
    private String revisionFilter;
    private String stateFilter;
    private String userFilter;
    private boolean headerAndDescOnly;
    
    public RlogCommand() {
        this.modules = new LinkedList();
        this.resetCVSCommand();
    }
    
    public void setModule(final String s) {
        this.modules.add(s);
    }
    
    public void clearModules() {
        this.modules.clear();
    }
    
    public void setModules(final String[] array) {
        this.clearModules();
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            this.modules.add(array[i]);
        }
    }
    
    public String[] getModules() {
        return this.modules.toArray(new String[this.modules.size()]);
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
    
    public boolean isSuppressHeader() {
        return this.suppressHeader;
    }
    
    public void setSuppressHeader(final boolean suppressHeader) {
        this.suppressHeader = suppressHeader;
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
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        clientServices.ensureConnection();
        super.execute(clientServices, eventManager);
        if (!this.isRecursive()) {
            this.requests.add(new ArgumentRequest("-l"));
        }
        if (this.defaultBranch) {
            this.requests.add(new ArgumentRequest("-b"));
        }
        if (this.headerAndDescOnly) {
            this.requests.add(new ArgumentRequest("-t"));
        }
        if (this.headerOnly) {
            this.requests.add(new ArgumentRequest("-h"));
        }
        if (this.suppressHeader) {
            this.requests.add(new ArgumentRequest("-S"));
        }
        if (this.noTags) {
            this.requests.add(new ArgumentRequest("-N"));
        }
        if (this.userFilter != null) {
            this.requests.add(new ArgumentRequest("-w" + this.userFilter));
        }
        if (this.revisionFilter != null) {
            this.requests.add(new ArgumentRequest("-r" + this.revisionFilter));
        }
        if (this.stateFilter != null) {
            this.requests.add(new ArgumentRequest("-s" + this.stateFilter));
        }
        if (this.dateFilter != null) {
            this.requests.add(new ArgumentRequest("-d" + this.dateFilter));
        }
        final Iterator<String> iterator = this.modules.iterator();
        while (iterator.hasNext()) {
            this.requests.add(new ArgumentRequest(iterator.next()));
        }
        this.requests.add(CommandRequest.RLOG);
        try {
            clientServices.processRequests(this.requests);
            this.requests.clear();
        }
        catch (CommandException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new CommandException(ex2, ex2.getLocalizedMessage());
        }
    }
    
    protected boolean assumeLocalPathWhenUnspecified() {
        return false;
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("rlog ");
        sb.append(this.getCVSArguments());
        if (this.modules != null && this.modules.size() > 0) {
            final Iterator<String> iterator = (Iterator<String>)this.modules.iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
                sb.append(' ');
            }
        }
        else {
            final String localMessage = CommandException.getLocalMessage("ExportCommand.moduleEmpty.text");
            sb.append(" ");
            sb.append(localMessage);
        }
        return sb.toString();
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
        if (this.isSuppressHeader()) {
            sb.append("-S ");
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
        else if (c == 'S') {
            this.setSuppressHeader(true);
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
        this.setSuppressHeader(false);
        this.setNoTags(false);
        this.setDateFilter(null);
        this.setRevisionFilter(null);
        this.setStateFilter(null);
        this.setUserFilter(null);
    }
    
    public String getOptString() {
        return "RlbhStNd:r:s:w:";
    }
    
    public Builder createBuilder(final EventManager eventManager) {
        return new LogBuilder(eventManager, this);
    }
}
