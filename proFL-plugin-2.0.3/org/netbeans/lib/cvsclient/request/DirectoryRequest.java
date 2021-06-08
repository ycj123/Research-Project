// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

public final class DirectoryRequest extends Request
{
    private final String localDirectory;
    private final String repository;
    
    public DirectoryRequest(final String localDirectory, final String repository) {
        if (localDirectory == null || repository == null) {
            throw new IllegalArgumentException("Both, directory and repository, must not be null!");
        }
        this.localDirectory = localDirectory;
        this.repository = repository;
    }
    
    public String getLocalDirectory() {
        return this.localDirectory;
    }
    
    public String getRepository() {
        return this.repository;
    }
    
    public String getRequestString() {
        return "Directory " + this.localDirectory + "\n" + this.repository + "\n";
    }
    
    public boolean isResponseExpected() {
        return false;
    }
}
