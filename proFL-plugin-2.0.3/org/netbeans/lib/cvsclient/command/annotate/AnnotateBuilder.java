// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.annotate;

import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import java.io.IOException;
import org.netbeans.lib.cvsclient.command.BasicCommand;
import java.io.File;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.Builder;

public class AnnotateBuilder implements Builder
{
    private static final String UNKNOWN = ": nothing known about";
    private static final String ANNOTATING = "Annotations for ";
    private static final String STARS = "***************";
    private AnnotateInformation annotateInformation;
    private final EventManager eventManager;
    private final String localPath;
    private String relativeDirectory;
    private int lineNum;
    private File tempDir;
    
    public AnnotateBuilder(final EventManager eventManager, final BasicCommand basicCommand) {
        this.eventManager = eventManager;
        this.localPath = basicCommand.getLocalDirectory();
        this.tempDir = basicCommand.getGlobalOptions().getTempDir();
    }
    
    public void outputDone() {
        if (this.annotateInformation == null) {
            return;
        }
        try {
            this.annotateInformation.closeTempFile();
        }
        catch (IOException ex) {}
        this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.annotateInformation));
        this.annotateInformation = null;
    }
    
    public void parseLine(final String s, final boolean b) {
        if (b && s.startsWith("Annotations for ")) {
            this.outputDone();
            (this.annotateInformation = new AnnotateInformation(this.tempDir)).setFile(this.createFile(s.substring("Annotations for ".length())));
            this.lineNum = 0;
            return;
        }
        if (b && s.startsWith("***************")) {
            return;
        }
        if (!b) {
            this.processLines(s);
        }
    }
    
    private File createFile(final String child) {
        return new File(this.localPath, child);
    }
    
    public void parseEnhancedMessage(final String s, final Object o) {
    }
    
    private void processLines(final String s) {
        if (this.annotateInformation != null) {
            try {
                this.annotateInformation.addToTempFile(s);
            }
            catch (IOException ex) {}
        }
    }
    
    public static AnnotateLine processLine(final String s) {
        final int index = s.indexOf(40);
        final int index2 = s.indexOf(41);
        AnnotateLine annotateLine = null;
        if (index > 0 && index2 > index) {
            final String trim = s.substring(0, index).trim();
            final String substring = s.substring(index + 1, index2);
            final String substring2 = s.substring(index2 + 3);
            final int lastIndex = substring.lastIndexOf(32);
            String trim2 = substring;
            String trim3 = substring;
            if (lastIndex > 0) {
                trim2 = substring.substring(0, lastIndex).trim();
                trim3 = substring.substring(lastIndex).trim();
            }
            annotateLine = new AnnotateLine();
            annotateLine.setContent(substring2);
            annotateLine.setAuthor(trim2);
            annotateLine.setDateString(trim3);
            annotateLine.setRevision(trim);
        }
        return annotateLine;
    }
}
