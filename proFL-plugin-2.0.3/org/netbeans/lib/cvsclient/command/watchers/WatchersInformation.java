// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.watchers;

import org.netbeans.lib.cvsclient.util.BugLog;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;

public class WatchersInformation extends FileInfoContainer
{
    public static final String WATCH_EDIT = "edit";
    public static final String WATCH_UNEDIT = "unedit";
    public static final String WATCH_COMMIT = "commit";
    public static final String WATCH_TEMP_EDIT = "tedit";
    public static final String WATCH_TEMP_UNEDIT = "tunedit";
    public static final String WATCH_TEMP_COMMIT = "tcommit";
    private final File file;
    private final List userList;
    
    public WatchersInformation(final File file) {
        this.userList = new LinkedList();
        this.file = file;
    }
    
    public File getFile() {
        return this.file;
    }
    
    void addWatcher(final String s) {
        final String replace = s.trim().replace('\t', ' ');
        final int index = replace.indexOf(32);
        if (index >= 0) {
            this.userList.add(new Watcher(replace.substring(0, index), replace.substring(index + 1)));
        }
    }
    
    public Iterator getWatchersIterator() {
        return this.userList.iterator();
    }
    
    public static class Watcher
    {
        private final String userName;
        private final String watches;
        private boolean watchingEdit;
        private boolean watchingUnedit;
        private boolean watchingCommit;
        private boolean temporaryEdit;
        private boolean temporaryUnedit;
        private boolean temporaryCommit;
        
        Watcher(final String userName, final String s) {
            this.userName = userName;
            this.watches = s;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, " ", false);
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if ("edit".equals(nextToken)) {
                    this.watchingEdit = true;
                }
                else if ("unedit".equals(nextToken)) {
                    this.watchingUnedit = true;
                }
                else if ("commit".equals(nextToken)) {
                    this.watchingCommit = true;
                }
                else if ("tcommit".equals(nextToken)) {
                    this.temporaryCommit = true;
                }
                else if ("tedit".equals(nextToken)) {
                    this.temporaryEdit = true;
                }
                else if ("tunedit".equals(nextToken)) {
                    this.temporaryUnedit = true;
                }
                else {
                    BugLog.getInstance().bug("unknown = " + nextToken);
                }
            }
        }
        
        public String getUserName() {
            return this.userName;
        }
        
        public String getWatches() {
            return this.watches;
        }
        
        public boolean isWatchingCommit() {
            return this.watchingCommit;
        }
        
        public boolean isWatchingEdit() {
            return this.watchingEdit;
        }
        
        public boolean isWatchingUnedit() {
            return this.watchingUnedit;
        }
        
        public boolean isTempWatchingCommit() {
            return this.temporaryCommit;
        }
        
        public boolean isTempWatchingEdit() {
            return this.temporaryEdit;
        }
        
        public boolean isTempWatchingUnedit() {
            return this.temporaryUnedit;
        }
    }
}
