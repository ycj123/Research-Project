// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.export;

import java.io.File;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import org.netbeans.lib.cvsclient.command.DefaultFileInfoContainer;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.Builder;

public class ExportBuilder implements Builder
{
    private static final String FILE_INFOS = "MUARC?";
    private final EventManager eventManager;
    private final String localPath;
    private DefaultFileInfoContainer fileInfoContainer;
    
    public ExportBuilder(final EventManager eventManager, final ExportCommand exportCommand) {
        this.eventManager = eventManager;
        this.localPath = exportCommand.getLocalDirectory();
    }
    
    public void outputDone() {
        if (this.fileInfoContainer == null) {
            return;
        }
        this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.fileInfoContainer));
        this.fileInfoContainer = null;
    }
    
    public void parseLine(final String s, final boolean b) {
        if (s.length() > 2 && s.charAt(1) == ' ') {
            final String substring = s.substring(0, 1);
            if ("MUARC?".indexOf(substring) >= 0) {
                this.processFile(substring, s.substring(2).trim());
            }
            else {
                this.error(s);
            }
        }
    }
    
    public void parseEnhancedMessage(final String s, final Object o) {
    }
    
    private void error(final String str) {
        System.err.println("Don't know anything about: " + str);
    }
    
    private void processFile(final String type, final String child) {
        this.outputDone();
        final File file = new File(this.localPath, child);
        (this.fileInfoContainer = new DefaultFileInfoContainer()).setType(type);
        this.fileInfoContainer.setFile(file);
        this.outputDone();
    }
}
