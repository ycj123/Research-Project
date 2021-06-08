// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.artifact.filter;

import org.codehaus.plexus.logging.Logger;

public interface StatisticsReportingArtifactFilter
{
    void reportMissedCriteria(final Logger p0);
    
    void reportFilteredArtifacts(final Logger p0);
    
    boolean hasMissedCriteria();
}
