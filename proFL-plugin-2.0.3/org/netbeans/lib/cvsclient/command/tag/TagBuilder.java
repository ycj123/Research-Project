// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.tag;

import java.io.File;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.DefaultFileInfoContainer;
import org.netbeans.lib.cvsclient.command.Builder;

public class TagBuilder implements Builder
{
    public static final String STATES = "T D ? ";
    public static final String CVS_SERVER = "server: ";
    public static final String EXAM_DIR = "server: ";
    private DefaultFileInfoContainer fileInfoContainer;
    private EventManager eventManager;
    private String localPath;
    
    public TagBuilder(final EventManager eventManager, final String localPath) {
        this.eventManager = eventManager;
        this.localPath = localPath;
    }
    
    public void outputDone() {
        if (this.fileInfoContainer != null) {
            this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.fileInfoContainer));
            this.fileInfoContainer = null;
        }
    }
    
    public void parseLine(final String s, final boolean b) {
        if (b) {
            return;
        }
        if (s.indexOf("server: ") < 0) {
            if (s.length() < 3) {
                return;
            }
            if ("T D ? ".indexOf(s.substring(0, 2)) >= 0) {
                this.processFile(s);
            }
        }
    }
    
    private void processFile(final String s) {
        if (this.fileInfoContainer == null) {
            this.fileInfoContainer = new DefaultFileInfoContainer();
        }
        this.fileInfoContainer.setType(s.substring(0, 1));
        String s2 = s.substring(2).trim();
        if (s2.startsWith("no file")) {
            s2 = s2.substring(8);
        }
        this.fileInfoContainer.setFile(this.createFile(s2));
        this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.fileInfoContainer));
        this.fileInfoContainer = null;
    }
    
    private File createFile(final String child) {
        return new File(this.localPath, child);
    }
    
    public void parseEnhancedMessage(final String s, final Object o) {
    }
}
