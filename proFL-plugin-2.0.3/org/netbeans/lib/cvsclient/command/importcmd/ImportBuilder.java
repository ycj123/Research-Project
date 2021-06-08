// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.importcmd;

import java.io.File;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import org.netbeans.lib.cvsclient.command.DefaultFileInfoContainer;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.Builder;

public class ImportBuilder implements Builder
{
    private static final String NO_CONFLICTS = "No conflicts created by this import";
    private static final String FILE_INFOS = "NUCIL?";
    private final EventManager eventManager;
    private final String localPath;
    private final String module;
    private DefaultFileInfoContainer fileInfoContainer;
    
    public ImportBuilder(final EventManager eventManager, final ImportCommand importCommand) {
        this.eventManager = eventManager;
        this.localPath = importCommand.getLocalDirectory();
        this.module = importCommand.getModule();
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
            if ("NUCIL?".indexOf(substring) >= 0) {
                this.processFile(substring, s.substring(2).trim());
            }
            else {
                this.error(s);
            }
        }
        else if (s.startsWith("No conflicts created by this import")) {
            this.outputDone();
        }
    }
    
    public void parseEnhancedMessage(final String s, final Object o) {
    }
    
    private void error(final String str) {
        System.err.println("Don't know anything about: " + str);
    }
    
    private void processFile(final String type, String substring) {
        this.outputDone();
        substring = substring.substring(this.module.length());
        final File file = new File(this.localPath, substring);
        (this.fileInfoContainer = new DefaultFileInfoContainer()).setType(type);
        this.fileInfoContainer.setFile(file);
        this.outputDone();
    }
}
