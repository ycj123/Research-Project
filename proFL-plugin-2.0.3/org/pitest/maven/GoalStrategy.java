// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.pitest.mutationtest.tooling.CombinedStatistics;
import java.util.Map;
import org.pitest.mutationtest.config.PluginServices;
import org.pitest.mutationtest.config.ReportOptions;
import java.io.File;

public interface GoalStrategy
{
    CombinedStatistics execute(final File p0, final ReportOptions p1, final PluginServices p2, final Map<String, String> p3) throws MojoExecutionException;
}
