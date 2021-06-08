// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.history;

import org.netbeans.lib.cvsclient.event.TerminationEvent;
import org.netbeans.lib.cvsclient.request.ArgumentRequest;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import org.netbeans.lib.cvsclient.request.UseUnchangedRequest;
import org.netbeans.lib.cvsclient.request.RootRequest;
import org.netbeans.lib.cvsclient.ClientServices;
import org.netbeans.lib.cvsclient.command.Builder;
import java.util.LinkedList;
import org.netbeans.lib.cvsclient.event.EventManager;
import java.util.List;
import org.netbeans.lib.cvsclient.command.Command;

public class HistoryCommand extends Command
{
    private final List requests;
    private EventManager eventManager;
    private boolean forAllUsers;
    private String showBackToRecordContaining;
    private boolean reportCommits;
    private String sinceDate;
    private boolean reportEverything;
    private boolean lastEventOfProject;
    private boolean reportCheckouts;
    private String sinceRevision;
    private boolean reportTags;
    private String sinceTag;
    private boolean forWorkingDirectory;
    private String reportEventType;
    private String timeZone;
    private String[] lastEventForFile;
    private String[] reportOnModule;
    private String[] reportLastEventForModule;
    private String[] forUsers;
    
    public HistoryCommand() {
        this.requests = new LinkedList();
    }
    
    public Builder createBuilder(final EventManager eventManager) {
        return null;
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        clientServices.ensureConnection();
        this.eventManager = eventManager;
        this.requests.clear();
        super.execute(clientServices, eventManager);
        try {
            if (clientServices.isFirstCommand()) {
                this.requests.add(new RootRequest(clientServices.getRepository()));
                this.requests.add(new UseUnchangedRequest());
            }
            this.addBooleanArgument(this.requests, this.isForAllUsers(), "-a");
            this.addBooleanArgument(this.requests, this.isForWorkingDirectory(), "-w");
            this.addBooleanArgument(this.requests, this.isLastEventOfProject(), "-l");
            this.addBooleanArgument(this.requests, this.isReportCheckouts(), "-o");
            this.addBooleanArgument(this.requests, this.isReportCommits(), "-c");
            this.addBooleanArgument(this.requests, this.isReportEverything(), "-e");
            this.addBooleanArgument(this.requests, this.isReportTags(), "-T");
            this.addStringArgument(this.requests, this.getReportEventType(), "-x");
            this.addStringArgument(this.requests, this.getShowBackToRecordContaining(), "-b");
            this.addStringArgument(this.requests, this.getSinceDate(), "-D");
            this.addStringArgument(this.requests, this.getSinceRevision(), "-r");
            this.addStringArgument(this.requests, this.getSinceTag(), "-t");
            this.addStringArrayArgument(this.requests, this.getForUsers(), "-u");
            this.addStringArrayArgument(this.requests, this.getReportLastEventForModule(), "-n");
            this.addStringArrayArgument(this.requests, this.getReportOnModule(), "-m");
            this.addStringArrayArgument(this.requests, this.getLastEventForFile(), "-f");
            if (!this.isReportCheckouts() && !this.isReportCommits() && !this.isReportTags() && !this.isReportEverything() && this.getReportEventType() == null && this.getReportOnModule() == null) {
                this.addBooleanArgument(this.requests, true, "-c");
            }
            if (this.getTimeZone() != null) {
                this.addStringArgument(this.requests, this.getTimeZone(), "-z");
            }
            else {
                this.addStringArgument(this.requests, "+0000", "-z");
            }
            this.requests.add(CommandRequest.HISTORY);
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
    
    private void addStringArgument(final List list, final String s, final String s2) {
        if (s != null) {
            list.add(new ArgumentRequest(s2));
            list.add(new ArgumentRequest(s));
        }
    }
    
    private void addStringArrayArgument(final List list, final String[] array, final String s) {
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                list.add(new ArgumentRequest(s));
                list.add(new ArgumentRequest(array[i]));
            }
        }
    }
    
    private void addBooleanArgument(final List list, final boolean b, final String s) {
        if (b) {
            list.add(new ArgumentRequest(s));
        }
    }
    
    public void commandTerminated(final TerminationEvent terminationEvent) {
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("history ");
        sb.append(this.getCVSArguments());
        return sb.toString();
    }
    
    public boolean setCVSCommand(final char c, final String s) {
        if (c == 'a') {
            this.setForAllUsers(true);
        }
        else if (c == 'b') {
            this.setShowBackToRecordContaining(s);
        }
        else if (c == 'c') {
            this.setReportCommits(true);
        }
        else if (c == 'D') {
            this.setSinceDate(s);
        }
        else if (c == 'e') {
            this.setReportEverything(true);
        }
        else if (c == 'l') {
            this.setLastEventOfProject(true);
        }
        else if (c == 'o') {
            this.setReportCheckouts(true);
        }
        else if (c == 'r') {
            this.setSinceRevision(s);
        }
        else if (c == 'T') {
            this.setReportTags(true);
        }
        else if (c == 't') {
            this.setSinceTag(s);
        }
        else if (c == 'w') {
            this.setForWorkingDirectory(true);
        }
        else if (c == 'x') {
            this.setReportEventType(s);
        }
        else if (c == 'z') {
            this.setTimeZone(s);
        }
        else if (c == 'f') {
            this.addLastEventForFile(s);
        }
        else if (c == 'm') {
            this.addReportOnModule(s);
        }
        else if (c == 'n') {
            this.addReportLastEventForModule(s);
        }
        else {
            if (c != 'u') {
                return false;
            }
            this.addForUsers(s);
        }
        return true;
    }
    
    public String getOptString() {
        return "ab:cD:ef:lm:n:or:Tt:u:wx:z:";
    }
    
    public void resetCVSCommand() {
        this.setForAllUsers(false);
        this.setForUsers(null);
        this.setForWorkingDirectory(false);
        this.setLastEventForFile(null);
        this.setLastEventOfProject(false);
        this.setReportCheckouts(false);
        this.setReportCommits(false);
        this.setReportEventType(null);
        this.setReportEverything(false);
        this.setReportLastEventForModule(null);
        this.setReportOnModule(null);
        this.setReportTags(false);
        this.setShowBackToRecordContaining(null);
        this.setSinceDate(null);
        this.setSinceRevision(null);
        this.setSinceTag(null);
        this.setTimeZone(null);
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer("");
        if (this.isForAllUsers()) {
            sb.append("-a ");
        }
        if (this.isForWorkingDirectory()) {
            sb.append("-w ");
        }
        if (this.isLastEventOfProject()) {
            sb.append("-l ");
        }
        if (this.isReportCheckouts()) {
            sb.append("-o ");
        }
        if (this.isReportCommits()) {
            sb.append("-c ");
        }
        if (this.isReportEverything()) {
            sb.append("-e ");
        }
        if (this.isReportTags()) {
            sb.append("-T ");
        }
        if (this.getForUsers() != null) {
            this.appendArrayToSwitches(sb, this.getForUsers(), "-u ");
        }
        if (this.getLastEventForFile() != null) {
            this.appendArrayToSwitches(sb, this.getLastEventForFile(), "-f ");
        }
        if (this.getReportEventType() != null) {
            sb.append("-x ");
            sb.append(this.getReportEventType());
            sb.append(" ");
        }
        if (this.getReportLastEventForModule() != null) {
            this.appendArrayToSwitches(sb, this.getReportLastEventForModule(), "-n ");
        }
        if (this.getReportOnModule() != null) {
            this.appendArrayToSwitches(sb, this.getReportOnModule(), "-m ");
        }
        if (this.getShowBackToRecordContaining() != null) {
            sb.append("-b ");
            sb.append(this.getShowBackToRecordContaining());
            sb.append(" ");
        }
        if (this.getSinceDate() != null) {
            sb.append("-D ");
            sb.append(this.getSinceDate());
            sb.append(" ");
        }
        if (this.getSinceRevision() != null) {
            sb.append("-r ");
            sb.append(this.getSinceRevision());
            sb.append(" ");
        }
        if (this.getSinceTag() != null) {
            sb.append("-t ");
            sb.append(this.getSinceTag());
            sb.append(" ");
        }
        if (this.getTimeZone() != null) {
            sb.append("-z ");
            sb.append(this.getTimeZone());
            sb.append(" ");
        }
        return sb.toString();
    }
    
    private void appendArrayToSwitches(final StringBuffer sb, final String[] array, final String str) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            sb.append(str);
            sb.append(array[i]);
            sb.append(" ");
        }
    }
    
    public boolean isForAllUsers() {
        return this.forAllUsers;
    }
    
    public void setForAllUsers(final boolean forAllUsers) {
        this.forAllUsers = forAllUsers;
    }
    
    public String getShowBackToRecordContaining() {
        return this.showBackToRecordContaining;
    }
    
    public void setShowBackToRecordContaining(final String showBackToRecordContaining) {
        this.showBackToRecordContaining = showBackToRecordContaining;
    }
    
    public boolean isReportCommits() {
        return this.reportCommits;
    }
    
    public void setReportCommits(final boolean reportCommits) {
        this.reportCommits = reportCommits;
    }
    
    public String getSinceDate() {
        return this.sinceDate;
    }
    
    public void setSinceDate(final String sinceDate) {
        this.sinceDate = sinceDate;
    }
    
    public boolean isReportEverything() {
        return this.reportEverything;
    }
    
    public void setReportEverything(final boolean reportEverything) {
        this.reportEverything = reportEverything;
    }
    
    public boolean isLastEventOfProject() {
        return this.lastEventOfProject;
    }
    
    public void setLastEventOfProject(final boolean lastEventOfProject) {
        this.lastEventOfProject = lastEventOfProject;
    }
    
    public boolean isReportCheckouts() {
        return this.reportCheckouts;
    }
    
    public void setReportCheckouts(final boolean reportCheckouts) {
        this.reportCheckouts = reportCheckouts;
    }
    
    public String getSinceRevision() {
        return this.sinceRevision;
    }
    
    public void setSinceRevision(final String sinceRevision) {
        this.sinceRevision = sinceRevision;
    }
    
    public boolean isReportTags() {
        return this.reportTags;
    }
    
    public void setReportTags(final boolean reportTags) {
        this.reportTags = reportTags;
    }
    
    public String getSinceTag() {
        return this.sinceTag;
    }
    
    public void setSinceTag(final String sinceTag) {
        this.sinceTag = sinceTag;
    }
    
    public boolean isForWorkingDirectory() {
        return this.forWorkingDirectory;
    }
    
    public void setForWorkingDirectory(final boolean forWorkingDirectory) {
        this.forWorkingDirectory = forWorkingDirectory;
    }
    
    public String getReportEventType() {
        return this.reportEventType;
    }
    
    public void setReportEventType(final String reportEventType) {
        this.reportEventType = reportEventType;
    }
    
    public String getTimeZone() {
        return this.timeZone;
    }
    
    public void setTimeZone(final String timeZone) {
        this.timeZone = timeZone;
    }
    
    public String[] getLastEventForFile() {
        return this.lastEventForFile;
    }
    
    public void setLastEventForFile(final String[] lastEventForFile) {
        this.lastEventForFile = lastEventForFile;
    }
    
    public void addLastEventForFile(final String s) {
        this.lastEventForFile = this.addNewValue(this.lastEventForFile, s);
    }
    
    public String[] getReportOnModule() {
        return this.reportOnModule;
    }
    
    public void setReportOnModule(final String[] reportOnModule) {
        this.reportOnModule = reportOnModule;
    }
    
    public void addReportOnModule(final String s) {
        this.reportOnModule = this.addNewValue(this.reportOnModule, s);
    }
    
    public String[] getReportLastEventForModule() {
        return this.reportLastEventForModule;
    }
    
    public void setReportLastEventForModule(final String[] reportLastEventForModule) {
        this.reportLastEventForModule = reportLastEventForModule;
    }
    
    public void addReportLastEventForModule(final String s) {
        this.reportLastEventForModule = this.addNewValue(this.reportLastEventForModule, s);
    }
    
    public String[] getForUsers() {
        return this.forUsers;
    }
    
    public void setForUsers(final String[] forUsers) {
        this.forUsers = forUsers;
    }
    
    public void addForUsers(final String s) {
        this.forUsers = this.addNewValue(this.forUsers, s);
    }
    
    private String[] addNewValue(String[] array, final String s) {
        if (array == null) {
            array = new String[] { s };
            return array;
        }
        final String[] array2 = new String[array.length + 1];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i];
        }
        array2[array2.length] = s;
        return array2;
    }
}
