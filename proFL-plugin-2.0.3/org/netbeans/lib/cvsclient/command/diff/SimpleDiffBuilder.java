// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.diff;

import java.io.File;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.Builder;

public class SimpleDiffBuilder implements Builder
{
    protected EventManager eventManager;
    protected DiffCommand diffCommand;
    protected DiffInformation diffInformation;
    protected String fileDirectory;
    protected boolean readingDiffs;
    private static final String UNKNOWN = ": I know nothing about";
    private static final String CANNOT_FIND = ": cannot find";
    private static final String UNKNOWN_TAG = ": tag";
    private static final String EXAM_DIR = ": Diffing";
    private static final String FILE = "Index: ";
    private static final String RCS_FILE = "RCS file: ";
    private static final String REVISION = "retrieving revision ";
    private static final String PARAMETERS = "diff ";
    private DiffInformation.DiffChange currentChange;
    
    public SimpleDiffBuilder(final EventManager eventManager, final DiffCommand diffCommand) {
        this.readingDiffs = false;
        this.eventManager = eventManager;
        this.diffCommand = diffCommand;
    }
    
    public void outputDone() {
        if (this.diffInformation != null) {
            if (this.currentChange != null) {
                this.diffInformation.addChange(this.currentChange);
                this.currentChange = null;
            }
            this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.diffInformation));
            this.diffInformation = null;
            this.readingDiffs = false;
        }
    }
    
    public void parseLine(final String s, final boolean b) {
        if (this.readingDiffs) {
            if (!s.startsWith("Index: ")) {
                this.processDifferences(s);
                return;
            }
            this.outputDone();
        }
        if (s.indexOf(": I know nothing about") >= 0) {
            this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.diffInformation));
            this.diffInformation = null;
            return;
        }
        if (s.indexOf(": Diffing") >= 0) {
            this.fileDirectory = s.substring(s.indexOf(": Diffing") + ": Diffing".length()).trim();
            return;
        }
        if (s.startsWith("Index: ")) {
            this.processFile(s.substring("Index: ".length()));
            return;
        }
        if (s.startsWith("RCS file: ")) {
            this.processRCSfile(s.substring("RCS file: ".length()));
            return;
        }
        if (s.startsWith("retrieving revision ")) {
            this.processRevision(s.substring("retrieving revision ".length()));
            return;
        }
        if (s.startsWith("diff ")) {
            this.processParameters(s.substring("diff ".length()));
            this.readingDiffs = true;
        }
    }
    
    protected void processFile(final String s) {
        this.outputDone();
        this.diffInformation = this.createDiffInformation();
        String child = s.trim();
        if (child.startsWith("no file")) {
            child = child.substring(8);
        }
        this.diffInformation.setFile(new File(this.diffCommand.getLocalDirectory(), child));
    }
    
    protected void processRCSfile(final String s) {
        if (this.diffInformation == null) {
            return;
        }
        this.diffInformation.setRepositoryFileName(s.trim());
    }
    
    protected void processRevision(String trim) {
        if (this.diffInformation == null) {
            return;
        }
        trim = trim.trim();
        if (this.diffInformation.getLeftRevision() != null) {
            this.diffInformation.setRightRevision(trim);
        }
        else {
            this.diffInformation.setLeftRevision(trim);
        }
    }
    
    protected void processParameters(final String s) {
        if (this.diffInformation == null) {
            return;
        }
        this.diffInformation.setParameters(s.trim());
    }
    
    public DiffInformation createDiffInformation() {
        return new DiffInformation();
    }
    
    protected void assignType(final DiffInformation.DiffChange diffChange, final String s) {
        int endIndex = 0;
        final int index = s.indexOf(99);
        if (index > 0) {
            diffChange.setType(2);
            endIndex = index;
        }
        else {
            final int index2 = s.indexOf(97);
            if (index2 > 0) {
                diffChange.setType(0);
                endIndex = index2;
            }
            else {
                final int index3 = s.indexOf(100);
                if (index3 > 0) {
                    diffChange.setType(1);
                    endIndex = index3;
                }
            }
        }
        final String substring = s.substring(0, endIndex);
        diffChange.setLeftRange(this.getMin(substring), this.getMax(substring));
        final String substring2 = s.substring(endIndex + 1);
        diffChange.setRightRange(this.getMin(substring2), this.getMax(substring2));
    }
    
    private int getMin(final String s) {
        String substring = s;
        final int index = substring.indexOf(44);
        if (index > 0) {
            substring = substring.substring(0, index);
        }
        int int1;
        try {
            int1 = Integer.parseInt(substring);
        }
        catch (NumberFormatException ex) {
            int1 = 0;
        }
        return int1;
    }
    
    private int getMax(final String s) {
        String substring = s;
        final int index = substring.indexOf(44);
        if (index > 0) {
            substring = substring.substring(index + 1);
        }
        int int1;
        try {
            int1 = Integer.parseInt(substring);
        }
        catch (NumberFormatException ex) {
            int1 = 0;
        }
        return int1;
    }
    
    protected void processDifferences(final String s) {
        final char char1 = s.charAt(0);
        if (char1 >= '0' && char1 <= '9') {
            if (this.currentChange != null) {
                this.diffInformation.addChange(this.currentChange);
            }
            this.assignType(this.currentChange = this.diffInformation.createDiffChange(), s);
        }
        if (char1 == '<') {
            this.currentChange.appendLeftLine(s.substring(2));
        }
        if (char1 == '>') {
            this.currentChange.appendRightLine(s.substring(2));
        }
    }
    
    public void parseEnhancedMessage(final String s, final Object o) {
    }
}
