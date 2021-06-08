// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command;

public class Watch
{
    public static final Watch EDIT;
    public static final Watch UNEDIT;
    public static final Watch COMMIT;
    public static final Watch ALL;
    public static final Watch NONE;
    private final String name;
    private final String value;
    private final String[] arguments;
    
    public static String getWatchString(final Watch watch) {
        if (watch == null) {
            return Watch.NONE.getValue();
        }
        return watch.getValue();
    }
    
    private Watch(final String name, final String value, final String[] arguments) {
        this.name = name;
        this.value = value;
        this.arguments = arguments;
    }
    
    public String[] getArguments() {
        return this.arguments;
    }
    
    public String toString() {
        return this.name;
    }
    
    private String getValue() {
        return this.value;
    }
    
    static {
        EDIT = new Watch("edit", "E", new String[] { "edit" });
        UNEDIT = new Watch("unedit", "U", new String[] { "unedit" });
        COMMIT = new Watch("commit", "C", new String[] { "commit" });
        ALL = new Watch("all", "EUC", new String[] { "edit", "unedit", "commit" });
        NONE = new Watch("none", "", new String[0]);
    }
}
