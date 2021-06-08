// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.file;

public class FileStatus
{
    public static final FileStatus ADDED;
    public static final FileStatus REMOVED;
    public static final FileStatus MODIFIED;
    public static final FileStatus UP_TO_DATE;
    public static final FileStatus NEEDS_CHECKOUT;
    public static final FileStatus NEEDS_PATCH;
    public static final FileStatus NEEDS_MERGE;
    public static final FileStatus HAS_CONFLICTS;
    public static final FileStatus UNRESOLVED_CONFLICT;
    public static final FileStatus UNKNOWN;
    public static final FileStatus INVALID;
    private final String statusString;
    
    public static FileStatus getStatusForString(final String s) {
        if (s == null) {
            return null;
        }
        if (s.equals(FileStatus.ADDED.toString())) {
            return FileStatus.ADDED;
        }
        if (s.equals(FileStatus.REMOVED.toString())) {
            return FileStatus.REMOVED;
        }
        if (s.equals(FileStatus.MODIFIED.toString())) {
            return FileStatus.MODIFIED;
        }
        if (s.equals(FileStatus.UP_TO_DATE.toString())) {
            return FileStatus.UP_TO_DATE;
        }
        if (s.equals(FileStatus.NEEDS_CHECKOUT.toString())) {
            return FileStatus.NEEDS_CHECKOUT;
        }
        if (s.equals(FileStatus.NEEDS_MERGE.toString())) {
            return FileStatus.NEEDS_MERGE;
        }
        if (s.equals(FileStatus.NEEDS_PATCH.toString())) {
            return FileStatus.NEEDS_PATCH;
        }
        if (s.equals(FileStatus.HAS_CONFLICTS.toString())) {
            return FileStatus.HAS_CONFLICTS;
        }
        if (s.equals(FileStatus.UNRESOLVED_CONFLICT.toString())) {
            return FileStatus.UNRESOLVED_CONFLICT;
        }
        if (s.equals(FileStatus.UNKNOWN.toString())) {
            return FileStatus.UNKNOWN;
        }
        if (s.equals(FileStatus.INVALID.toString())) {
            return FileStatus.INVALID;
        }
        return null;
    }
    
    private FileStatus(final String statusString) {
        this.statusString = statusString;
    }
    
    public String toString() {
        return this.statusString;
    }
    
    static {
        ADDED = new FileStatus("Locally Added");
        REMOVED = new FileStatus("Locally Removed");
        MODIFIED = new FileStatus("Locally Modified");
        UP_TO_DATE = new FileStatus("Up-to-date");
        NEEDS_CHECKOUT = new FileStatus("Needs Checkout");
        NEEDS_PATCH = new FileStatus("Needs Patch");
        NEEDS_MERGE = new FileStatus("Needs Merge");
        HAS_CONFLICTS = new FileStatus("File had conflicts on merge");
        UNRESOLVED_CONFLICT = new FileStatus("Unresolved Conflict");
        UNKNOWN = new FileStatus("Unknown");
        INVALID = new FileStatus("Entry Invalid");
    }
}
