// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.tag;

import org.netbeans.lib.cvsclient.event.TerminationEvent;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import java.io.EOFException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.request.Request;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import org.netbeans.lib.cvsclient.request.ArgumentRequest;
import org.netbeans.lib.cvsclient.ClientServices;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.RepositoryCommand;

public class RtagCommand extends RepositoryCommand
{
    private EventManager eventManager;
    private boolean clearFromRemoved;
    private boolean deleteTag;
    private boolean makeBranchTag;
    private boolean overrideExistingTag;
    private boolean matchHeadIfRevisionNotFound;
    private boolean noExecTagProgram;
    private String tag;
    private String tagByDate;
    private String tagByRevision;
    
    public Builder createBuilder(final EventManager eventManager) {
        return new TagBuilder(eventManager, this.getLocalDirectory());
    }
    
    public boolean isClearFromRemoved() {
        return this.clearFromRemoved;
    }
    
    public void setClearFromRemoved(final boolean clearFromRemoved) {
        this.clearFromRemoved = clearFromRemoved;
    }
    
    public boolean isDeleteTag() {
        return this.deleteTag;
    }
    
    public void setDeleteTag(final boolean deleteTag) {
        this.deleteTag = deleteTag;
    }
    
    public boolean isMakeBranchTag() {
        return this.makeBranchTag;
    }
    
    public void setMakeBranchTag(final boolean makeBranchTag) {
        this.makeBranchTag = makeBranchTag;
    }
    
    public boolean isOverrideExistingTag() {
        return this.overrideExistingTag;
    }
    
    public void setOverrideExistingTag(final boolean overrideExistingTag) {
        this.overrideExistingTag = overrideExistingTag;
    }
    
    public boolean isMatchHeadIfRevisionNotFound() {
        return this.matchHeadIfRevisionNotFound;
    }
    
    public void setMatchHeadIfRevisionNotFound(final boolean matchHeadIfRevisionNotFound) {
        this.matchHeadIfRevisionNotFound = matchHeadIfRevisionNotFound;
    }
    
    public boolean isNoExecTagProgram() {
        return this.noExecTagProgram;
    }
    
    public void setNoExecTagProgram(final boolean noExecTagProgram) {
        this.noExecTagProgram = noExecTagProgram;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public void setTag(final String tag) {
        this.tag = tag;
    }
    
    public String getTagByDate() {
        return this.tagByDate;
    }
    
    public void setTagByDate(final String tagByDate) {
        this.tagByDate = tagByDate;
    }
    
    public String getTagByRevision() {
        return this.tagByRevision;
    }
    
    public void setTagByRevision(final String tagByRevision) {
        this.tagByRevision = tagByRevision;
    }
    
    protected void postExpansionExecute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        clientServices.ensureConnection();
        this.eventManager = eventManager;
        try {
            if (this.clearFromRemoved) {
                this.requests.add(new ArgumentRequest("-a"));
            }
            if (this.overrideExistingTag) {
                this.requests.add(new ArgumentRequest("-F"));
            }
            if (this.matchHeadIfRevisionNotFound) {
                this.requests.add(new ArgumentRequest("-f"));
            }
            if (this.makeBranchTag) {
                this.requests.add(new ArgumentRequest("-b"));
            }
            if (this.deleteTag) {
                this.requests.add(new ArgumentRequest("-d"));
            }
            if (this.noExecTagProgram) {
                this.requests.add(new ArgumentRequest("-n "));
            }
            if (this.tagByDate != null && this.tagByDate.length() > 0) {
                this.requests.add(new ArgumentRequest("-D"));
                this.requests.add(new ArgumentRequest(this.getTagByDate()));
            }
            if (this.tagByRevision != null && this.tagByRevision.length() > 0) {
                this.requests.add(new ArgumentRequest("-r"));
                this.requests.add(new ArgumentRequest(this.getTagByRevision()));
            }
            this.requests.add(new ArgumentRequest(this.getTag()));
            this.addArgumentRequests();
            this.addRequest(CommandRequest.RTAG);
            clientServices.processRequests(this.requests);
        }
        catch (CommandException ex) {
            throw ex;
        }
        catch (EOFException ex2) {
            throw new CommandException(ex2, CommandException.getLocalMessage("CommandException.EndOfFile", null));
        }
        catch (Exception ex3) {
            throw new CommandException(ex3, ex3.getLocalizedMessage());
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
        final StringBuffer sb = new StringBuffer("rtag ");
        sb.append(this.getCVSArguments());
        if (this.getTag() != null) {
            sb.append(this.getTag());
            sb.append(" ");
        }
        this.appendModuleArguments(sb);
        return sb.toString();
    }
    
    public boolean setCVSCommand(final char c, final String s) {
        if (c == 'R') {
            this.setRecursive(true);
        }
        else if (c == 'l') {
            this.setRecursive(false);
        }
        else if (c == 'a') {
            this.setClearFromRemoved(true);
        }
        else if (c == 'd') {
            this.setDeleteTag(true);
        }
        else if (c == 'F') {
            this.setOverrideExistingTag(true);
        }
        else if (c == 'f') {
            this.setMatchHeadIfRevisionNotFound(true);
        }
        else if (c == 'b') {
            this.setMakeBranchTag(true);
        }
        else if (c == 'n') {
            this.setNoExecTagProgram(true);
        }
        else if (c == 'D') {
            this.setTagByDate(s.trim());
        }
        else {
            if (c != 'r') {
                return false;
            }
            this.setTagByRevision(s.trim());
        }
        return true;
    }
    
    public String getOptString() {
        return "RlaFfbdnD:r:";
    }
    
    public void resetCVSCommand() {
        this.setRecursive(true);
        this.setClearFromRemoved(false);
        this.setDeleteTag(false);
        this.setMakeBranchTag(false);
        this.setOverrideExistingTag(false);
        this.setMatchHeadIfRevisionNotFound(false);
        this.setNoExecTagProgram(false);
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer();
        if (!this.isRecursive()) {
            sb.append("-l ");
        }
        if (this.isClearFromRemoved()) {
            sb.append("-a ");
        }
        if (this.isOverrideExistingTag()) {
            sb.append("-F ");
        }
        if (this.isMatchHeadIfRevisionNotFound()) {
            sb.append("-f ");
        }
        if (this.isMakeBranchTag()) {
            sb.append("-b ");
        }
        if (this.isDeleteTag()) {
            sb.append("-d ");
        }
        if (this.isNoExecTagProgram()) {
            sb.append("-n ");
        }
        if (this.getTagByRevision() != null && this.getTagByRevision().length() > 0) {
            sb.append("-r ");
            sb.append(this.getTagByRevision());
            sb.append(" ");
        }
        if (this.getTagByDate() != null && this.getTagByDate().length() > 0) {
            sb.append("-D ");
            sb.append(this.getTagByDate());
            sb.append(" ");
        }
        return sb.toString();
    }
}
