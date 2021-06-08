// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.log;

import java.io.File;
import java.util.StringTokenizer;
import org.netbeans.lib.cvsclient.command.CommandUtils;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.lib.cvsclient.command.BasicCommand;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.Builder;

public class LogBuilder implements Builder
{
    private static final String LOGGING_DIR = ": Logging ";
    private static final String RCS_FILE = "RCS file: ";
    private static final String WORK_FILE = "Working file: ";
    private static final String REV_HEAD = "head: ";
    private static final String BRANCH = "branch: ";
    private static final String LOCKS = "locks: ";
    private static final String ACCESS_LIST = "access list: ";
    private static final String SYM_NAME = "symbolic names:";
    private static final String KEYWORD_SUBST = "keyword substitution: ";
    private static final String TOTAL_REV = "total revisions: ";
    private static final String SEL_REV = ";\tselected revisions: ";
    private static final String DESCRIPTION = "description:";
    private static final String REVISION = "revision ";
    private static final String DATE = "date: ";
    private static final String BRANCHES = "branches: ";
    private static final String AUTHOR = "author: ";
    private static final String STATE = "state: ";
    private static final String LINES = "lines: ";
    private static final String COMMITID = "commitid: ";
    private static final String SPLITTER = "----------------------------";
    private static final String FINAL_SPLIT = "=============================================================================";
    private static final String ERROR = ": nothing known about ";
    private static final String NO_FILE = "no file";
    protected EventManager eventManager;
    protected BasicCommand logCommand;
    protected LogInformation logInfo;
    protected LogInformation.Revision revision;
    protected String fileDirectory;
    private boolean addingSymNames;
    private boolean addingDescription;
    private boolean addingLogMessage;
    private StringBuffer tempBuffer;
    private List messageList;
    
    public LogBuilder(final EventManager eventManager, final BasicCommand logCommand) {
        this.tempBuffer = null;
        this.logCommand = logCommand;
        this.eventManager = eventManager;
        this.addingSymNames = false;
        this.addingDescription = false;
        this.addingLogMessage = false;
        this.logInfo = null;
        this.revision = null;
        this.messageList = new ArrayList(500);
    }
    
    public void outputDone() {
        if (this.logInfo != null) {
            this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.logInfo));
            this.logInfo = null;
            this.messageList = null;
        }
    }
    
    public void parseLine(final String s, final boolean b) {
        if (s.equals("=============================================================================")) {
            if (this.addingDescription) {
                this.addingDescription = false;
                this.logInfo.setDescription(this.tempBuffer.toString());
            }
            if (this.addingLogMessage) {
                this.addingLogMessage = false;
                this.revision.setMessage(CommandUtils.findUniqueString(this.tempBuffer.toString(), this.messageList));
            }
            if (this.revision != null) {
                this.logInfo.addRevision(this.revision);
                this.revision = null;
            }
            if (this.logInfo != null) {
                this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.logInfo));
                this.logInfo = null;
                this.tempBuffer = null;
            }
            return;
        }
        if (this.addingLogMessage) {
            if (!s.startsWith("branches: ")) {
                this.processLogMessage(s);
                return;
            }
            this.processBranches(s.substring("branches: ".length()));
        }
        if (this.addingSymNames) {
            this.processSymbolicNames(s);
        }
        if (this.addingDescription) {
            this.processDescription(s);
        }
        if (s.startsWith("revision ")) {
            this.processRevisionStart(s);
        }
        if (s.startsWith("date: ")) {
            this.processRevisionDate(s);
        }
        if (s.startsWith("keyword substitution: ")) {
            this.logInfo.setKeywordSubstitution(s.substring("keyword substitution: ".length()).trim().intern());
            this.addingSymNames = false;
            return;
        }
        if (s.startsWith("description:")) {
            this.tempBuffer = new StringBuffer(s.substring("description:".length()));
            this.addingDescription = true;
        }
        if (s.indexOf(": Logging ") >= 0) {
            this.fileDirectory = s.substring(s.indexOf(": Logging ") + ": Logging ".length()).trim();
            return;
        }
        if (s.startsWith("RCS file: ")) {
            this.processRcsFile(s.substring("RCS file: ".length()));
            return;
        }
        if (s.startsWith("Working file: ")) {
            this.processWorkingFile(s.substring("Working file: ".length()));
            return;
        }
        if (s.startsWith("head: ")) {
            this.logInfo.setHeadRevision(s.substring("head: ".length()).trim().intern());
            return;
        }
        if (s.startsWith("branch: ")) {
            this.logInfo.setBranch(s.substring("branch: ".length()).trim().intern());
        }
        if (s.startsWith("locks: ")) {
            this.logInfo.setLocks(s.substring("locks: ".length()).trim().intern());
        }
        if (s.startsWith("access list: ")) {
            this.logInfo.setAccessList(s.substring("access list: ".length()).trim().intern());
        }
        if (s.startsWith("symbolic names:")) {
            this.addingSymNames = true;
        }
        if (s.startsWith("total revisions: ")) {
            final int index = s.indexOf(59);
            if (index < 0) {
                this.logInfo.setTotalRevisions(s.substring("total revisions: ".length()).trim().intern());
                this.logInfo.setSelectedRevisions("0");
            }
            else {
                final String substring = s.substring(0, index);
                final String substring2 = s.substring(index, s.length());
                this.logInfo.setTotalRevisions(substring.substring("total revisions: ".length()).trim().intern());
                this.logInfo.setSelectedRevisions(substring2.substring(";\tselected revisions: ".length()).trim().intern());
            }
        }
    }
    
    private String findUniqueString(final String s, final List list) {
        if (s == null) {
            return null;
        }
        final int index = list.indexOf(s);
        if (index >= 0) {
            return list.get(index);
        }
        list.add(s);
        return s;
    }
    
    private void processRcsFile(final String s) {
        if (this.logInfo != null) {}
        (this.logInfo = new LogInformation()).setRepositoryFilename(s.trim());
    }
    
    private void processWorkingFile(final String s) {
        final String trim = s.trim();
        if (trim.startsWith("no file")) {
            trim.substring(8);
        }
        this.logInfo.setFile(this.createFile(s));
    }
    
    private void processBranches(String substring) {
        final int lastIndex = substring.lastIndexOf(59);
        if (lastIndex > 0) {
            substring = substring.substring(0, lastIndex);
        }
        this.revision.setBranches(substring.trim());
    }
    
    private void processLogMessage(final String str) {
        if (str.startsWith("----------------------------")) {
            this.addingLogMessage = false;
            this.revision.setMessage(this.findUniqueString(this.tempBuffer.toString(), this.messageList));
            return;
        }
        this.tempBuffer.append(str + "\n");
    }
    
    private void processSymbolicNames(String trim) {
        if (!trim.startsWith("keyword substitution: ")) {
            trim = trim.trim();
            final int index = trim.indexOf(58);
            if (index > 0) {
                this.logInfo.addSymbolicName(trim.substring(0, index).trim().intern(), trim.substring(index + 1, trim.length()).trim().intern());
            }
        }
    }
    
    private void processDescription(final String str) {
        if (str.startsWith("----------------------------")) {
            this.addingDescription = false;
            this.logInfo.setDescription(this.tempBuffer.toString());
            return;
        }
        this.tempBuffer.append(str);
    }
    
    private void processRevisionStart(final String s) {
        if (this.revision != null) {
            this.logInfo.addRevision(this.revision);
        }
        this.revision = this.logInfo.createNewRevision(s.substring("revision ".length()).intern());
    }
    
    private void processRevisionDate(final String str) {
        final StringTokenizer stringTokenizer = new StringTokenizer(str, ";", false);
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            if (trim.startsWith("date: ")) {
                this.revision.setDateString(trim.substring("date: ".length()));
            }
            else if (trim.startsWith("author: ")) {
                this.revision.setAuthor(trim.substring("author: ".length()));
            }
            else if (trim.startsWith("state: ")) {
                this.revision.setState(trim.substring("state: ".length()));
            }
            else if (trim.startsWith("lines: ")) {
                this.revision.setLines(trim.substring("lines: ".length()));
            }
            else {
                if (!trim.startsWith("commitid: ")) {
                    continue;
                }
                this.revision.setCommitID(trim.substring("commitid: ".length()));
            }
        }
        this.addingLogMessage = true;
        this.tempBuffer = new StringBuffer();
    }
    
    protected File createFile(final String s) {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.logCommand.getLocalDirectory());
        sb.append(File.separator);
        sb.append(s.replace('/', File.separatorChar));
        return new File(sb.toString());
    }
    
    public void parseEnhancedMessage(final String s, final Object o) {
    }
}
