// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.status;

import org.netbeans.lib.cvsclient.file.FileStatus;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import java.io.File;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.Builder;

public class StatusBuilder implements Builder
{
    private static final String UNKNOWN = ": nothing known about";
    private static final String EXAM_DIR = ": Examining";
    private static final String NOT_IN_REPOSITORY = "No revision control file";
    private static final String FILE = "File: ";
    private static final String STATUS = "Status:";
    private static final String NO_FILE_FILENAME = "no file";
    private static final String WORK_REV = "   Working revision:";
    private static final String REP_REV = "   Repository revision:";
    private static final String TAG = "   Sticky Tag:";
    private static final String DATE = "   Sticky Date:";
    private static final String OPTIONS = "   Sticky Options:";
    private static final String EXISTING_TAGS = "   Existing Tags:";
    private static final String EMPTY_BEFORE_TAGS = "   ";
    private static final String NO_TAGS = "   No Tags Exist";
    private static final String UNKNOWN_FILE = "? ";
    private StatusInformation statusInformation;
    private EventManager eventManager;
    private final StatusCommand statusCommand;
    private String relativeDirectory;
    private final String localPath;
    private boolean beginning;
    private boolean readingTags;
    private final File[] fileArray;
    
    public StatusBuilder(final EventManager eventManager, final StatusCommand statusCommand) {
        this.eventManager = eventManager;
        this.statusCommand = statusCommand;
        final File[] files = statusCommand.getFiles();
        if (files != null) {
            System.arraycopy(files, 0, this.fileArray = new File[files.length], 0, files.length);
        }
        else {
            this.fileArray = null;
        }
        this.localPath = statusCommand.getLocalDirectory();
        this.beginning = true;
    }
    
    public void outputDone() {
        if (this.statusInformation != null) {
            this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.statusInformation));
            this.statusInformation = null;
            this.readingTags = false;
        }
    }
    
    public void parseLine(final String s, final boolean b) {
        if (this.readingTags) {
            if (s.startsWith("   No Tags Exist")) {
                this.outputDone();
                return;
            }
            final int index = s.indexOf("\t(");
            if (index <= 0) {
                this.outputDone();
                return;
            }
            final String trim = s.substring(0, index).trim();
            final String substring = s.substring(index + 2, s.length() - 1);
            if (this.statusInformation == null) {
                this.statusInformation = new StatusInformation();
            }
            this.statusInformation.addExistingTag(trim, substring);
        }
        if (s.startsWith("? ") && this.beginning) {
            (this.statusInformation = new StatusInformation()).setFile(new File(this.localPath, s.substring("? ".length())));
            this.statusInformation.setStatusString(FileStatus.UNKNOWN.toString());
            this.outputDone();
        }
        if (s.startsWith(": nothing known about")) {
            this.outputDone();
            this.beginning = false;
        }
        else if (s.indexOf(": Examining") >= 0) {
            this.relativeDirectory = s.substring(s.indexOf(": Examining") + ": Examining".length()).trim();
            this.beginning = false;
        }
        else if (s.startsWith("File: ")) {
            this.outputDone();
            this.statusInformation = new StatusInformation();
            this.processFileAndStatusLine(s.substring("File: ".length()));
            this.beginning = false;
        }
        else if (s.startsWith("   Working revision:")) {
            this.processWorkRev(s.substring("   Working revision:".length()));
        }
        else if (s.startsWith("   Repository revision:")) {
            this.processRepRev(s.substring("   Repository revision:".length()));
        }
        else if (s.startsWith("   Sticky Tag:")) {
            this.processTag(s.substring("   Sticky Tag:".length()));
        }
        else if (s.startsWith("   Sticky Date:")) {
            this.processDate(s.substring("   Sticky Date:".length()));
        }
        else if (s.startsWith("   Sticky Options:")) {
            this.processOptions(s.substring("   Sticky Options:".length()));
            if (!this.statusCommand.isIncludeTags()) {
                this.outputDone();
            }
        }
        else if (s.startsWith("   Existing Tags:")) {
            this.readingTags = true;
        }
    }
    
    private File createFile(final String s) {
        File file = null;
        if (this.relativeDirectory != null) {
            if (this.relativeDirectory.trim().equals(".")) {
                file = new File(this.localPath, s);
            }
            else {
                file = new File(this.localPath, this.relativeDirectory + '/' + s);
            }
        }
        else if (this.fileArray != null) {
            for (int i = 0; i < this.fileArray.length; ++i) {
                final File file2 = this.fileArray[i];
                if (file2 != null) {
                    if (!file2.isDirectory()) {
                        if (s.equals(file2.getName())) {
                            this.fileArray[i] = null;
                            file = file2;
                            break;
                        }
                    }
                }
            }
        }
        if (file == null) {
            System.err.println("JAVACVS ERROR!! wrong algorithm for assigning path to single files(1)!!");
        }
        return file;
    }
    
    private void processFileAndStatusLine(final String s) {
        final int lastIndex = s.lastIndexOf("Status:");
        String s2 = s.substring(0, lastIndex).trim();
        if (s2.startsWith("no file")) {
            s2 = s2.substring(8);
        }
        this.statusInformation.setFile(this.createFile(s2));
        this.statusInformation.setStatusString(new String(s.substring(lastIndex + 8).trim()));
    }
    
    private boolean assertNotNull() {
        if (this.statusInformation == null) {
            System.err.println("Bug: statusInformation must not be null!");
            return false;
        }
        return true;
    }
    
    private void processWorkRev(final String s) {
        if (!this.assertNotNull()) {
            return;
        }
        this.statusInformation.setWorkingRevision(s.trim().intern());
    }
    
    private void processRepRev(String trim) {
        if (!this.assertNotNull()) {
            return;
        }
        trim = trim.trim();
        if (trim.startsWith("No revision control file")) {
            this.statusInformation.setRepositoryRevision(trim.trim().intern());
            return;
        }
        final int index = trim.indexOf(9);
        if (index > 0) {
            this.statusInformation.setRepositoryRevision(trim.substring(0, index).trim().intern());
            this.statusInformation.setRepositoryFileName(new String(trim.substring(index).trim()));
        }
        else {
            this.statusInformation.setRepositoryRevision("");
            this.statusInformation.setRepositoryFileName("");
        }
    }
    
    private void processTag(final String s) {
        if (!this.assertNotNull()) {
            return;
        }
        this.statusInformation.setStickyTag(s.trim().intern());
    }
    
    private void processDate(final String s) {
        if (!this.assertNotNull()) {
            return;
        }
        this.statusInformation.setStickyDate(s.trim().intern());
    }
    
    private void processOptions(final String s) {
        if (!this.assertNotNull()) {
            return;
        }
        this.statusInformation.setStickyOptions(s.trim().intern());
    }
    
    public void parseEnhancedMessage(final String s, final Object o) {
    }
}
