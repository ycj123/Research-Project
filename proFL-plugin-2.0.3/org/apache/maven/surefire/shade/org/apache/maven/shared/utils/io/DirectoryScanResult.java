// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io;

public class DirectoryScanResult
{
    private final String[] filesAdded;
    private final String[] filesRemoved;
    
    public DirectoryScanResult(final String[] filesAdded, final String[] filesRemoved) {
        this.filesAdded = filesAdded;
        this.filesRemoved = filesRemoved;
    }
    
    public String[] getFilesAdded() {
        return this.filesAdded;
    }
    
    public String[] getFilesRemoved() {
        return this.filesRemoved;
    }
}
