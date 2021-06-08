// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import java.lang.reflect.Field;
import org.pitest.mutationtest.config.DirectoryResultOutputStrategy;
import org.pitest.util.ResultOutputStrategy;
import java.util.Collection;
import org.apache.maven.plugin.logging.Log;
import org.mudebug.profl.core.ProFL;
import java.io.File;
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

@org.apache.maven.plugins.annotations.Mojo(name = "profl", defaultPhase = LifecyclePhase.VERIFY, requiresDependencyResolution = ResolutionScope.TEST, threadSafe = true)
@Execute(phase = LifecyclePhase.TEST_COMPILE)
public class ProFLMojo extends PraPRMojo
{
    @Override
    protected Option<CombinedStatistics> analyse() throws MojoExecutionException {
        final PraPRReportOptions data = this.preanalyse();
        data.setFailWhenNoMutations(false);
        data.setMutateSuspStmt(SuspCheckerType.WEAK);
        if (!data.getOutputFormats().contains("COMPRESSED-XML")) {
            data.getOutputFormats().add("COMPRESSED-XML");
        }
        final Option<CombinedStatistics> statistics = Option.some(this.goalStrategy.execute(this.detectBaseDir(), data, this.plugins, this.getEnvironmentVariables()));
        proflInvocation(this, data);
        return statistics;
    }
    
    public static void proflInvocation(final AbstractMojo mojo, final PraPRReportOptions data) {
        final Log log = mojo.getLog();
        final String path = decodeOutputDir(data.getOutStrategy());
        final String mutationFile = path + File.separator + "mutations.xml.gz";
        final String proflOutput = path + File.separator + "profl.log";
        final Collection<String> failingTests = data.getFailingTests();
        final File f = new File(mutationFile);
        if (!f.exists()) {
            log.info("ProFL skipped since no detailed repair xml is produced!");
        }
        else if (failingTests.isEmpty()) {
            log.info("PraPR detailed repair results dumped to: " + mutationFile);
            log.info("ProFL skipped since there is no failing test!");
        }
        else {
            ProFL.compute(mutationFile, failingTests, path);
            log.info("PraPR detailed repair results dumped to: " + mutationFile);
            log.info("ProFL fault localization results dumped to: " + proflOutput);
        }
    }
    
    public static String decodeOutputDir(final ResultOutputStrategy strategy) {
        try {
            final Field privateField = DirectoryResultOutputStrategy.class.getDeclaredField("reportDir");
            privateField.setAccessible(true);
            final File outputDir = (File)privateField.get(strategy);
            return outputDir.getAbsolutePath();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (SecurityException e2) {
            e2.printStackTrace();
        }
        catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        }
        catch (IllegalAccessException e4) {
            e4.printStackTrace();
        }
        return null;
    }
}
