// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.remove;

import java.io.File;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.Builder;

public class RemoveBuilder implements Builder
{
    private static final String UNKNOWN = ": nothing known about";
    private static final String WARNING = ": warning: ";
    private static final String SCHEDULING = ": scheduling `";
    private static final String USE_COMMIT = ": use 'cvs commit' ";
    private static final String DIRECTORY = ": Removing ";
    private static final String STILL_IN_WORKING = ": file `";
    private static final String REMOVE_FIRST = "first";
    private static final String UNKNOWN_FILE = "?";
    private RemoveInformation removeInformation;
    private String fileDirectory;
    private final EventManager eventManager;
    private final RemoveCommand removeCommand;
    
    public RemoveBuilder(final EventManager eventManager, final RemoveCommand removeCommand) {
        this.eventManager = eventManager;
        this.removeCommand = removeCommand;
    }
    
    public void outputDone() {
        if (this.removeInformation != null) {
            this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.removeInformation));
            this.removeInformation = null;
        }
    }
    
    public void parseLine(final String s, final boolean b) {
        if (s.indexOf(": scheduling `") >= 0) {
            this.addFile(s.substring(s.indexOf(": scheduling `") + ": scheduling `".length(), s.indexOf(39)).trim());
            this.removeInformation.setRemoved(true);
            this.outputDone();
        }
        if (s.startsWith("?")) {
            this.addFile(s.substring("?".length()));
            this.removeInformation.setRemoved(false);
            this.outputDone();
        }
        if (s.indexOf(": file `") >= 0) {
            this.addFile(s.substring(s.indexOf(": file `") + ": file `".length(), s.indexOf(39)).trim());
            this.removeInformation.setRemoved(false);
            this.outputDone();
        }
    }
    
    protected File createFile(final String s) {
        StringBuffer sb = new StringBuffer();
        sb.append(this.removeCommand.getLocalDirectory());
        sb.append(File.separator);
        if (this.fileDirectory == null) {
            final File fileEndingWith = this.removeCommand.getFileEndingWith(s);
            if (fileEndingWith == null) {
                sb.append(s);
            }
            else {
                sb = new StringBuffer(fileEndingWith.getAbsolutePath());
            }
        }
        else {
            sb.append(s);
        }
        sb.toString().replace('/', File.separatorChar);
        return new File(sb.toString());
    }
    
    protected void addFile(final String s) {
        (this.removeInformation = new RemoveInformation()).setFile(this.createFile(s));
    }
    
    public void parseEnhancedMessage(final String s, final Object o) {
    }
}
