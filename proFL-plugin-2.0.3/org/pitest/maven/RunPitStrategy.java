// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import org.pitest.mutationtest.tooling.AnalysisResult;
import org.apache.maven.plugin.MojoExecutionException;
import org.pitest.mutationtest.tooling.EntryPoint;
import org.pitest.mutationtest.tooling.CombinedStatistics;
import java.util.Map;
import org.pitest.mutationtest.config.PluginServices;
import org.pitest.mutationtest.config.ReportOptions;
import java.io.File;

public class RunPitStrategy implements GoalStrategy
{
    @Override
    public CombinedStatistics execute(final File baseDir, final ReportOptions data, final PluginServices plugins, final Map<String, String> environmentVariables) throws MojoExecutionException {
        final EntryPoint e = new EntryPoint();
        final AnalysisResult result = e.execute(baseDir, data, plugins, environmentVariables);
        if (result.getError().hasSome()) {
            throw new MojoExecutionException("fail", result.getError().value());
        }
        return result.getStatistics().value();
    }
}
