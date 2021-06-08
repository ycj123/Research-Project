// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm;

import java.io.Serializable;

public final class ScmFileStatus implements Serializable
{
    private static final long serialVersionUID = -7840223279162817915L;
    public static final ScmFileStatus ADDED;
    public static final ScmFileStatus DELETED;
    public static final ScmFileStatus MODIFIED;
    public static final ScmFileStatus RENAMED;
    public static final ScmFileStatus COPIED;
    public static final ScmFileStatus MISSING;
    public static final ScmFileStatus CHECKED_IN;
    public static final ScmFileStatus CHECKED_OUT;
    public static final ScmFileStatus CONFLICT;
    public static final ScmFileStatus PATCHED;
    public static final ScmFileStatus UPDATED;
    public static final ScmFileStatus TAGGED;
    public static final ScmFileStatus LOCKED;
    public static final ScmFileStatus UNKNOWN;
    public static final ScmFileStatus EDITED;
    private final String name;
    
    private ScmFileStatus(final String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public boolean isStatus() {
        return this == ScmFileStatus.UNKNOWN || this.isDiff();
    }
    
    public boolean isDiff() {
        return this == ScmFileStatus.ADDED || this == ScmFileStatus.DELETED || this == ScmFileStatus.MODIFIED;
    }
    
    public boolean isTransaction() {
        return this == ScmFileStatus.CHECKED_IN || this == ScmFileStatus.CHECKED_OUT || this == ScmFileStatus.LOCKED || this == ScmFileStatus.TAGGED || this.isUpdate();
    }
    
    public boolean isUpdate() {
        return this == ScmFileStatus.CONFLICT || this == ScmFileStatus.UPDATED || this == ScmFileStatus.PATCHED;
    }
    
    static {
        ADDED = new ScmFileStatus("added");
        DELETED = new ScmFileStatus("deleted");
        MODIFIED = new ScmFileStatus("modified");
        RENAMED = new ScmFileStatus("renamed");
        COPIED = new ScmFileStatus("copied");
        MISSING = new ScmFileStatus("missing");
        CHECKED_IN = new ScmFileStatus("checked-in");
        CHECKED_OUT = new ScmFileStatus("checked-out");
        CONFLICT = new ScmFileStatus("conflict");
        PATCHED = new ScmFileStatus("patched");
        UPDATED = new ScmFileStatus("updated");
        TAGGED = new ScmFileStatus("tagged");
        LOCKED = new ScmFileStatus("locked");
        UNKNOWN = new ScmFileStatus("unknown");
        EDITED = new ScmFileStatus("edit");
    }
}
