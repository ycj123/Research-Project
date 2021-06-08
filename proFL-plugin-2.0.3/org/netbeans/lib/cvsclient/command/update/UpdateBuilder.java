// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.update;

import java.io.File;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.DefaultFileInfoContainer;
import org.netbeans.lib.cvsclient.command.Builder;

public class UpdateBuilder implements Builder
{
    public static final String UNKNOWN = ": nothing known about";
    public static final String EXAM_DIR = ": Updating";
    public static final String TO_ADD = ": use `cvs add' to create an entry for";
    public static final String STATES = "U P A R M C ? ";
    public static final String WARNING = ": warning: ";
    public static final String SERVER = "server: ";
    public static final String PERTINENT = "is not (any longer) pertinent";
    public static final String CONFLICTS = "rcsmerge: warning: conflicts during merge";
    public static final String NOT_IN_REPOSITORY = "is no longer in the repository";
    private static final String MERGE_SAME = " already contains the differences between";
    private DefaultFileInfoContainer fileInfoContainer;
    private EventManager eventManager;
    private final String localPath;
    private String diagnostics;
    
    public UpdateBuilder(final EventManager eventManager, final String localPath) {
        this.eventManager = eventManager;
        this.localPath = localPath;
    }
    
    public void outputDone() {
        if (this.fileInfoContainer != null) {
            if (this.fileInfoContainer.getFile() == null) {
                System.err.println("#65387 CVS: firing invalid event while processing: " + this.diagnostics);
            }
            this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.fileInfoContainer));
            this.fileInfoContainer = null;
        }
    }
    
    public void parseLine(final String diagnostics, final boolean b) {
        this.diagnostics = diagnostics;
        if (diagnostics.indexOf(": nothing known about") >= 0) {
            this.processUnknownFile(diagnostics, diagnostics.indexOf(": nothing known about") + ": nothing known about".length());
        }
        else if (diagnostics.indexOf(": use `cvs add' to create an entry for") >= 0) {
            this.processUnknownFile(diagnostics, diagnostics.indexOf(": use `cvs add' to create an entry for") + ": use `cvs add' to create an entry for".length());
        }
        else {
            if (diagnostics.indexOf(": Updating") >= 0) {
                return;
            }
            if (diagnostics.startsWith("rcsmerge: warning: conflicts during merge")) {
                if (this.fileInfoContainer != null) {
                    this.fileInfoContainer.setType("C");
                }
            }
            else {
                if (diagnostics.indexOf(": warning: ") >= 0) {
                    if (diagnostics.indexOf("is not (any longer) pertinent") > 0) {
                        this.processNotPertinent(diagnostics.substring(diagnostics.indexOf(": warning: ") + ": warning: ".length(), diagnostics.indexOf("is not (any longer) pertinent")).trim());
                    }
                    return;
                }
                if (diagnostics.indexOf(" already contains the differences between") >= 0) {
                    this.ensureExistingFileInfoContainer();
                    this.fileInfoContainer.setType("G");
                    this.fileInfoContainer.setFile(this.createFile(diagnostics.substring(0, diagnostics.indexOf(" already contains the differences between"))));
                    this.outputDone();
                }
                else {
                    if (diagnostics.indexOf("is no longer in the repository") > 0) {
                        this.processNotPertinent(diagnostics.substring(diagnostics.indexOf("server: ") + "server: ".length(), diagnostics.indexOf("is no longer in the repository")).trim());
                        return;
                    }
                    if (diagnostics.length() > 2 && "U P A R M C ? ".indexOf(diagnostics.substring(0, 2)) >= 0) {
                        this.processFile(diagnostics);
                    }
                }
            }
        }
    }
    
    private File createFile(final String child) {
        return new File(this.localPath, child);
    }
    
    private void ensureExistingFileInfoContainer() {
        if (this.fileInfoContainer != null) {
            return;
        }
        this.fileInfoContainer = new DefaultFileInfoContainer();
    }
    
    private void processUnknownFile(final String s, final int beginIndex) {
        this.outputDone();
        (this.fileInfoContainer = new DefaultFileInfoContainer()).setType("?");
        this.fileInfoContainer.setFile(this.createFile(s.substring(beginIndex).trim()));
    }
    
    private void processFile(final String s) {
        String s2 = s.substring(2).trim();
        if (s2.startsWith("no file")) {
            s2 = s2.substring(8);
        }
        if (s2.startsWith("./")) {
            s2 = s2.substring(2);
        }
        final File file = this.createFile(s2);
        if (this.fileInfoContainer != null) {
            if (this.fileInfoContainer.getFile() == null) {
                this.fileInfoContainer.setFile(file);
            }
            if (file.equals(this.fileInfoContainer.getFile())) {
                if (!this.fileInfoContainer.getType().equals("?")) {
                    this.outputDone();
                    return;
                }
                this.fileInfoContainer = null;
            }
        }
        this.outputDone();
        this.ensureExistingFileInfoContainer();
        this.fileInfoContainer.setType(s.substring(0, 1));
        this.fileInfoContainer.setFile(file);
    }
    
    private void processLog(final String s) {
        this.ensureExistingFileInfoContainer();
    }
    
    private void processNotPertinent(final String s) {
        this.outputDone();
        final File file = this.createFile(s);
        this.ensureExistingFileInfoContainer();
        this.fileInfoContainer.setType("Y");
        this.fileInfoContainer.setFile(file);
    }
    
    public void parseEnhancedMessage(final String s, final Object o) {
        if (s.equals("Merged_Response_File_Path")) {
            this.ensureExistingFileInfoContainer();
            final File file = new File(o.toString());
            if (!file.equals(this.fileInfoContainer.getFile())) {
                this.fileInfoContainer.setFile(file);
                this.fileInfoContainer.setType("G");
            }
            this.outputDone();
        }
    }
}
