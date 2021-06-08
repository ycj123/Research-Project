// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.AbstractMojo;
import org.pitest.mutationtest.config.ReportOptions;
import org.mudebug.prapr.core.SuspCheckerType;
import org.pitest.mutationtest.tooling.CombinedStatistics;
import org.pitest.functional.Option;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@org.apache.maven.plugins.annotations.Mojo(name = "proflM", defaultPhase = LifecyclePhase.VERIFY, requiresDependencyResolution = ResolutionScope.TEST, threadSafe = true)
@Execute(phase = LifecyclePhase.TEST_COMPILE)
public class ProFLMultiMojo extends PraPRMultiMojo
{
    @Override
    protected Option<CombinedStatistics> analyse() throws MojoExecutionException {
        final PraPRReportOptions data = this.preanalyse();
        data.setFailWhenNoMutations(false);
        data.setMutateSuspStmt(SuspCheckerType.WEAK);
        if (!data.getOutputFormats().contains("COMPRESSED-XML")) {
            data.getOutputFormats().add("COMPRESSED-XML");
        }
        this.modifyPraPRReportOptions(data);
        final Option<CombinedStatistics> statistics = Option.some(this.goalStrategy.execute(this.detectBaseDir(), data, this.plugins, this.getEnvironmentVariables()));
        ProFLMojo.proflInvocation(this, data);
        return statistics;
    }
}
