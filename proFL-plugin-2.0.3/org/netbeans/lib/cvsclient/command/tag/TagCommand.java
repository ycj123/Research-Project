// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.tag;

import java.io.File;
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
import org.netbeans.lib.cvsclient.command.BasicCommand;

public class TagCommand extends BasicCommand
{
    private EventManager eventManager;
    private boolean checkThatUnmodified;
    private boolean deleteTag;
    private boolean makeBranchTag;
    private boolean overrideExistingTag;
    private boolean matchHeadIfRevisionNotFound;
    private String tag;
    private String tagByDate;
    private String tagByRevision;
    
    public Builder createBuilder(final EventManager eventManager) {
        return new TagBuilder(eventManager, this.getLocalDirectory());
    }
    
    public boolean doesCheckThatUnmodified() {
        return this.checkThatUnmodified;
    }
    
    public boolean isCheckThatUnmodified() {
        return this.checkThatUnmodified;
    }
    
    public void setCheckThatUnmodified(final boolean checkThatUnmodified) {
        this.checkThatUnmodified = checkThatUnmodified;
    }
    
    public boolean doesDeleteTag() {
        return this.deleteTag;
    }
    
    public boolean isDeleteTag() {
        return this.deleteTag;
    }
    
    public void setDeleteTag(final boolean deleteTag) {
        this.deleteTag = deleteTag;
    }
    
    public boolean doesMakeBranchTag() {
        return this.makeBranchTag;
    }
    
    public boolean isMakeBranchTag() {
        return this.makeBranchTag;
    }
    
    public void setMakeBranchTag(final boolean makeBranchTag) {
        this.makeBranchTag = makeBranchTag;
    }
    
    public boolean doesOverrideExistingTag() {
        return this.overrideExistingTag;
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
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        clientServices.ensureConnection();
        super.execute(clientServices, this.eventManager = eventManager);
        try {
            this.requests.add(1, new ArgumentRequest(this.getTag()));
            if (this.checkThatUnmodified) {
                this.requests.add(1, new ArgumentRequest("-c"));
            }
            if (this.overrideExistingTag) {
                this.requests.add(1, new ArgumentRequest("-F"));
            }
            if (this.matchHeadIfRevisionNotFound) {
                this.requests.add(1, new ArgumentRequest("-f"));
            }
            if (this.makeBranchTag) {
                this.requests.add(1, new ArgumentRequest("-b"));
            }
            if (this.deleteTag) {
                this.requests.add(1, new ArgumentRequest("-d"));
            }
            if (this.tagByDate != null && this.tagByDate.length() > 0) {
                this.requests.add(1, new ArgumentRequest("-D"));
                this.requests.add(2, new ArgumentRequest(this.getTagByDate()));
            }
            if (this.tagByRevision != null && this.tagByRevision.length() > 0) {
                this.requests.add(1, new ArgumentRequest("-r"));
                this.requests.add(2, new ArgumentRequest(this.getTagByRevision()));
            }
            this.addRequestForWorkingDirectory(clientServices);
            this.addArgumentRequests();
            this.addRequest(CommandRequest.TAG);
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
        final StringBuffer sb = new StringBuffer("tag ");
        sb.append(this.getCVSArguments());
        if (this.getTag() != null) {
            sb.append(this.getTag());
            sb.append(" ");
        }
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
        else if (c == 'c') {
            this.setCheckThatUnmodified(true);
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
        return "RlcFfbdD:r:";
    }
    
    public void resetCVSCommand() {
        this.setRecursive(true);
        this.setCheckThatUnmodified(false);
        this.setDeleteTag(false);
        this.setMakeBranchTag(false);
        this.setOverrideExistingTag(false);
        this.setMatchHeadIfRevisionNotFound(false);
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer();
        if (!this.isRecursive()) {
            sb.append("-l ");
        }
        if (this.isCheckThatUnmodified()) {
            sb.append("-c ");
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
