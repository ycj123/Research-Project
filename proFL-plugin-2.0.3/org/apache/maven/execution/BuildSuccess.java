// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.execution;

import org.apache.maven.project.MavenProject;

public class BuildSuccess
{
    private final MavenProject project;
    private final long time;
    
    public BuildSuccess(final MavenProject project, final long time) {
        this.project = project;
        this.time = time;
    }
    
    public MavenProject getProject() {
        return this.project;
    }
    
    public long getTime() {
        return this.time;
    }
}
