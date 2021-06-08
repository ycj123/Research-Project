// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev;

import java.io.File;

public class FileDifference
{
    private String oldVersionSpec;
    private File oldFile;
    private String newVersionSpec;
    private File newFile;
    private long elementId;
    
    public FileDifference(final long elementId, final String newPath, final String newVersion, final String oldPath, final String oldVersion) {
        this.oldVersionSpec = null;
        this.oldFile = null;
        this.newVersionSpec = null;
        this.newFile = null;
        this.elementId = -1L;
        this.setElementId(elementId);
        this.setNewVersion(newPath, newVersion);
        this.setOldVersion(oldPath, oldVersion);
    }
    
    public FileDifference() {
        this.oldVersionSpec = null;
        this.oldFile = null;
        this.newVersionSpec = null;
        this.newFile = null;
        this.elementId = -1L;
    }
    
    public String getOldVersionSpec() {
        return this.oldVersionSpec;
    }
    
    public File getOldFile() {
        return this.oldFile;
    }
    
    public String getNewVersionSpec() {
        return this.newVersionSpec;
    }
    
    public File getNewFile() {
        return this.newFile;
    }
    
    public long getElementId() {
        return this.elementId;
    }
    
    public void setElementId(final long elementId) {
        this.elementId = elementId;
    }
    
    public void setNewVersion(final String path, final String version) {
        this.newFile = ((this.oldFile != null && this.oldFile.getPath().equals(path)) ? this.oldFile : ((path == null) ? null : new File(path)));
        this.newVersionSpec = version;
    }
    
    public void setOldVersion(final String path, final String version) {
        this.oldFile = ((this.newFile != null && this.newFile.getPath().equals(path)) ? this.newFile : ((path == null) ? null : new File(path)));
        this.oldVersionSpec = version;
    }
    
    @Override
    public String toString() {
        return "FileDifference [elementId=" + this.elementId + ", newFile=" + this.newFile + ", newVersionSpec=" + this.newVersionSpec + ", oldFile=" + this.oldFile + ", oldVersionSpec=" + this.oldVersionSpec + "]";
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + (int)(this.elementId ^ this.elementId >>> 32);
        result = 31 * result + ((this.newFile == null) ? 0 : this.newFile.hashCode());
        result = 31 * result + ((this.newVersionSpec == null) ? 0 : this.newVersionSpec.hashCode());
        result = 31 * result + ((this.oldFile == null) ? 0 : this.oldFile.hashCode());
        result = 31 * result + ((this.oldVersionSpec == null) ? 0 : this.oldVersionSpec.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final FileDifference other = (FileDifference)obj;
        if (this.elementId != other.elementId) {
            return false;
        }
        if (this.newFile == null) {
            if (other.newFile != null) {
                return false;
            }
        }
        else if (!this.newFile.equals(other.newFile)) {
            return false;
        }
        if (this.newVersionSpec == null) {
            if (other.newVersionSpec != null) {
                return false;
            }
        }
        else if (!this.newVersionSpec.equals(other.newVersionSpec)) {
            return false;
        }
        if (this.oldFile == null) {
            if (other.oldFile != null) {
                return false;
            }
        }
        else if (!this.oldFile.equals(other.oldFile)) {
            return false;
        }
        if (this.oldVersionSpec == null) {
            if (other.oldVersionSpec != null) {
                return false;
            }
        }
        else if (!this.oldVersionSpec.equals(other.oldVersionSpec)) {
            return false;
        }
        return true;
    }
}
