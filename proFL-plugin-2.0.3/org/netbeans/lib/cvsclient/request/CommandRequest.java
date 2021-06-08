// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

public class CommandRequest extends Request
{
    public static final CommandRequest ADD;
    public static final CommandRequest ANNOTATE;
    public static final CommandRequest CHECKOUT;
    public static final CommandRequest COMMIT;
    public static final CommandRequest DIFF;
    public static final CommandRequest EDITORS;
    public static final CommandRequest EXPORT;
    public static final CommandRequest HISTORY;
    public static final CommandRequest IMPORT;
    public static final CommandRequest LOG;
    public static final CommandRequest NOOP;
    public static final CommandRequest RANNOTATE;
    public static final CommandRequest REMOVE;
    public static final CommandRequest RLOG;
    public static final CommandRequest RTAG;
    public static final CommandRequest STATUS;
    public static final CommandRequest TAG;
    public static final CommandRequest UPDATE;
    public static final CommandRequest WATCH_ADD;
    public static final CommandRequest WATCH_ON;
    public static final CommandRequest WATCH_OFF;
    public static final CommandRequest WATCH_REMOVE;
    public static final CommandRequest WATCHERS;
    private final String request;
    
    private CommandRequest(final String request) {
        this.request = request;
    }
    
    public String getRequestString() {
        return this.request;
    }
    
    public boolean isResponseExpected() {
        return true;
    }
    
    static {
        ADD = new CommandRequest("add\n");
        ANNOTATE = new CommandRequest("annotate\n");
        CHECKOUT = new CommandRequest("co\n");
        COMMIT = new CommandRequest("ci\n");
        DIFF = new CommandRequest("diff\n");
        EDITORS = new CommandRequest("editors\n");
        EXPORT = new CommandRequest("export\n");
        HISTORY = new CommandRequest("history\n");
        IMPORT = new CommandRequest("import\n");
        LOG = new CommandRequest("log\n");
        NOOP = new CommandRequest("noop\n");
        RANNOTATE = new CommandRequest("rannotate\n");
        REMOVE = new CommandRequest("remove\n");
        RLOG = new CommandRequest("rlog\n");
        RTAG = new CommandRequest("rtag\n");
        STATUS = new CommandRequest("status\n");
        TAG = new CommandRequest("tag\n");
        UPDATE = new CommandRequest("update\n");
        WATCH_ADD = new CommandRequest("watch-add\n");
        WATCH_ON = new CommandRequest("watch-on\n");
        WATCH_OFF = new CommandRequest("watch-off\n");
        WATCH_REMOVE = new CommandRequest("watch-remove\n");
        WATCHERS = new CommandRequest("watchers\n");
    }
}
