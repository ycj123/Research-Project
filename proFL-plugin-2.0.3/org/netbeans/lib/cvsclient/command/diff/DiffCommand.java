// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.diff;

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

public class DiffCommand extends BasicCommand
{
    protected EventManager eventManager;
    private String beforeDate1;
    private String revision1;
    private String revision2;
    private String beforeDate2;
    private String keywordSubst;
    private boolean ignoreAllWhitespace;
    private boolean ignoreBlankLines;
    private boolean ignoreCase;
    private boolean ignoreSpaceChange;
    private boolean contextDiff;
    private boolean unifiedDiff;
    
    public Builder createBuilder(final EventManager eventManager) {
        if (this.isContextDiff() || this.isUnifiedDiff()) {
            return null;
        }
        return new SimpleDiffBuilder(eventManager, this);
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        clientServices.ensureConnection();
        super.execute(clientServices, this.eventManager = eventManager);
        try {
            this.addRDSwitches();
            if (this.getKeywordSubst() != null && !this.getKeywordSubst().equals("")) {
                this.requests.add(new ArgumentRequest("-k" + this.getKeywordSubst()));
            }
            this.addArgumentRequest(this.isIgnoreAllWhitespace(), "-w");
            this.addArgumentRequest(this.isIgnoreBlankLines(), "-B");
            this.addArgumentRequest(this.isIgnoreSpaceChange(), "-b");
            this.addArgumentRequest(this.isIgnoreCase(), "-i");
            this.addArgumentRequest(this.isContextDiff(), "-c");
            this.addArgumentRequest(this.isUnifiedDiff(), "-u");
            this.addRequestForWorkingDirectory(clientServices);
            this.addArgumentRequests();
            this.addRequest(CommandRequest.DIFF);
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
    
    private void addRDSwitches() {
        if (this.getRevision2() != null) {
            this.requests.add(1, new ArgumentRequest("-r"));
            this.requests.add(2, new ArgumentRequest(this.getRevision2()));
        }
        else if (this.getBeforeDate2() != null) {
            this.requests.add(1, new ArgumentRequest("-D " + this.getBeforeDate2()));
        }
        if (this.getRevision1() != null) {
            this.requests.add(1, new ArgumentRequest("-r"));
            this.requests.add(2, new ArgumentRequest(this.getRevision1()));
        }
        else {
            if (this.getBeforeDate1() == null) {
                return;
            }
            this.requests.add(1, new ArgumentRequest("-D " + this.getBeforeDate1()));
        }
    }
    
    public void commandTerminated(final TerminationEvent terminationEvent) {
        if (this.builder != null) {
            this.builder.outputDone();
        }
    }
    
    public String getBeforeDate1() {
        return this.beforeDate1;
    }
    
    public void setBeforeDate1(final String beforeDate1) {
        this.beforeDate1 = beforeDate1;
    }
    
    public String getRevision1() {
        return this.revision1;
    }
    
    public void setRevision1(final String revision1) {
        this.revision1 = revision1;
    }
    
    public String getRevision2() {
        return this.revision2;
    }
    
    public void setRevision2(final String revision2) {
        this.revision2 = revision2;
    }
    
    public String getBeforeDate2() {
        return this.beforeDate2;
    }
    
    public void setBeforeDate2(final String beforeDate2) {
        this.beforeDate2 = beforeDate2;
    }
    
    public String getKeywordSubst() {
        return this.keywordSubst;
    }
    
    public void setKeywordSubst(final String keywordSubst) {
        this.keywordSubst = keywordSubst;
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("diff ");
        sb.append(this.getCVSArguments());
        final File[] files = this.getFiles();
        if (files != null) {
            for (int i = 0; i < files.length; ++i) {
                sb.append(files[i].getName() + " ");
            }
        }
        return sb.toString();
    }
    
    public boolean setCVSCommand(final char c, final String keywordSubst) {
        if (c == 'R') {
            this.setRecursive(true);
        }
        else if (c == 'l') {
            this.setRecursive(false);
        }
        else if (c == 'r') {
            if (this.getRevision1() == null) {
                this.setRevision1(keywordSubst);
            }
            else {
                this.setRevision2(keywordSubst);
            }
        }
        else if (c == 'D') {
            if (this.getBeforeDate1() == null) {
                this.setBeforeDate1(keywordSubst);
            }
            else {
                this.setBeforeDate2(keywordSubst);
            }
        }
        else if (c == 'k') {
            this.setKeywordSubst(keywordSubst);
        }
        else if (c == 'w') {
            this.setIgnoreAllWhitespace(true);
        }
        else if (c == 'b') {
            this.setIgnoreSpaceChange(true);
        }
        else if (c == 'B') {
            this.setIgnoreBlankLines(true);
        }
        else if (c == 'i') {
            this.setIgnoreCase(true);
        }
        else if (c == 'c') {
            this.setContextDiff(true);
        }
        else {
            if (c != 'u') {
                return false;
            }
            this.setUnifiedDiff(true);
        }
        return true;
    }
    
    public String getOptString() {
        return "Rlr:D:k:wBbicu";
    }
    
    public void resetCVSCommand() {
        this.setRecursive(true);
        this.setRevision1(null);
        this.setRevision2(null);
        this.setBeforeDate1(null);
        this.setBeforeDate2(null);
        this.setKeywordSubst(null);
        this.setIgnoreAllWhitespace(false);
        this.setIgnoreBlankLines(false);
        this.setIgnoreCase(false);
        this.setIgnoreSpaceChange(false);
        this.setContextDiff(false);
        this.setUnifiedDiff(false);
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer("");
        if (this.getKeywordSubst() != null && this.getKeywordSubst().length() > 0) {
            sb.append("-k" + this.getKeywordSubst() + " ");
        }
        if (!this.isRecursive()) {
            sb.append("-l ");
        }
        if (this.getRevision1() != null) {
            sb.append("-r " + this.getRevision1() + " ");
        }
        if (this.getBeforeDate1() != null) {
            sb.append("-D " + this.getBeforeDate1() + " ");
        }
        if (this.getRevision2() != null) {
            sb.append("-r " + this.getRevision2() + " ");
        }
        if (this.getBeforeDate2() != null) {
            sb.append("-D " + this.getBeforeDate2() + " ");
        }
        if (this.isIgnoreAllWhitespace()) {
            sb.append("-w ");
        }
        if (this.isIgnoreBlankLines()) {
            sb.append("-B ");
        }
        if (this.isIgnoreCase()) {
            sb.append("-i ");
        }
        if (this.isIgnoreSpaceChange()) {
            sb.append("-b ");
        }
        if (this.isContextDiff()) {
            sb.append("-c ");
        }
        if (this.isUnifiedDiff()) {
            sb.append("-u ");
        }
        return sb.toString();
    }
    
    public boolean isIgnoreAllWhitespace() {
        return this.ignoreAllWhitespace;
    }
    
    public void setIgnoreAllWhitespace(final boolean ignoreAllWhitespace) {
        this.ignoreAllWhitespace = ignoreAllWhitespace;
    }
    
    public boolean isIgnoreBlankLines() {
        return this.ignoreBlankLines;
    }
    
    public void setIgnoreBlankLines(final boolean ignoreBlankLines) {
        this.ignoreBlankLines = ignoreBlankLines;
    }
    
    public boolean isIgnoreCase() {
        return this.ignoreCase;
    }
    
    public void setIgnoreCase(final boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }
    
    public boolean isIgnoreSpaceChange() {
        return this.ignoreSpaceChange;
    }
    
    public void setIgnoreSpaceChange(final boolean ignoreSpaceChange) {
        this.ignoreSpaceChange = ignoreSpaceChange;
    }
    
    public boolean isContextDiff() {
        return this.contextDiff;
    }
    
    public void setContextDiff(final boolean contextDiff) {
        this.contextDiff = contextDiff;
    }
    
    public boolean isUnifiedDiff() {
        return this.unifiedDiff;
    }
    
    public void setUnifiedDiff(final boolean unifiedDiff) {
        this.unifiedDiff = unifiedDiff;
    }
}
