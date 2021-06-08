// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven;

import org.apache.maven.reactor.MavenExecutionException;
import org.apache.maven.execution.MavenExecutionRequest;

public interface Maven
{
    public static final String ROLE = ((Maven$1.class$org$apache$maven$Maven == null) ? (Maven$1.class$org$apache$maven$Maven = Maven$1.class$("org.apache.maven.Maven")) : Maven$1.class$org$apache$maven$Maven).getName();
    public static final String POMv4 = "pom.xml";
    public static final String RELEASE_POMv4 = "release-pom.xml";
    
    void execute(final MavenExecutionRequest p0) throws MavenExecutionException;
}
