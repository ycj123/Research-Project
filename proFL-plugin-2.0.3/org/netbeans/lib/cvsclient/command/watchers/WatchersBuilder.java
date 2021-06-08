// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.watchers;

import org.netbeans.lib.cvsclient.util.BugLog;
import java.io.File;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.Builder;

public class WatchersBuilder implements Builder
{
    private static final String UNKNOWN_FILE = "? ";
    private WatchersInformation watchersInfo;
    private final EventManager eventManager;
    private final String localPath;
    
    public WatchersBuilder(final EventManager eventManager, final String localPath) {
        this.eventManager = eventManager;
        this.localPath = localPath;
    }
    
    public void outputDone() {
        if (this.watchersInfo != null) {
            this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.watchersInfo));
            this.watchersInfo = null;
        }
    }
    
    public void parseLine(final String str, final boolean b) {
        if (str.startsWith("? ")) {
            this.watchersInfo = new WatchersInformation(new File(this.localPath, str.substring("? ".length())));
            this.outputDone();
            return;
        }
        if (b) {
            return;
        }
        if (str.startsWith(" ") || str.startsWith("\t")) {
            BugLog.getInstance().assertNotNull(this.watchersInfo);
            this.watchersInfo.addWatcher(str);
            return;
        }
        this.outputDone();
        final String replace = str.trim().replace('\t', ' ');
        final int index = replace.indexOf(32);
        BugLog.getInstance().assertTrue(index > 0, "Wrong line = " + str);
        (this.watchersInfo = new WatchersInformation(new File(this.localPath, replace.substring(0, index)))).addWatcher(replace.substring(index + 1));
    }
    
    public void parseEnhancedMessage(final String s, final Object o) {
    }
}
