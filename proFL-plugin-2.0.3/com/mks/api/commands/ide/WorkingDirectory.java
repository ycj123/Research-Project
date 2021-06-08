// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands.ide;

import java.util.Collections;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.io.File;

public final class WorkingDirectory
{
    private File directory;
    private Set sandboxes;
    
    WorkingDirectory(final File directory) {
        this.directory = null;
        this.sandboxes = new HashSet();
        this.directory = directory;
    }
    
    public File getDirectory() {
        return this.directory;
    }
    
    public synchronized File getSandboxLocation() {
        ISandboxInfo example = null;
        if (!this.sandboxes.isEmpty()) {
            example = this.sandboxes.iterator().next();
        }
        return (example == null) ? null : example.getSandboxLocation();
    }
    
    public synchronized Collection getSandboxes() {
        return Collections.unmodifiableCollection((Collection<?>)this.sandboxes);
    }
    
    public boolean isSandboxDirectory() {
        return this.directory.equals(this.getSandboxLocation());
    }
    
    public synchronized boolean isSandboxAware() {
        return !this.sandboxes.isEmpty();
    }
    
    public synchronized boolean isAmbiguous() {
        return this.sandboxes.size() > 1;
    }
    
    synchronized void addSandbox(final ISandboxInfo sandbox) {
        final File sandboxLocation = this.getSandboxLocation();
        if (sandboxLocation != null) {
            final String location = sandbox.getSandboxLocation().getAbsolutePath();
            final String current = sandboxLocation.getAbsolutePath();
            if (location.length() > current.length()) {
                this.sandboxes.clear();
            }
        }
        this.sandboxes.add(sandbox);
    }
}
