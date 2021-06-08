// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven.report.generator;

import java.util.Iterator;
import org.pitest.util.PitError;
import java.util.Arrays;
import java.util.List;
import org.pitest.maven.report.ReportSourceLocator;

public class ReportGenerationManager
{
    private final ReportSourceLocator reportLocator;
    private final List<ReportGenerationStrategy> reportGenerationStrategyList;
    
    public ReportGenerationManager() {
        this(new ReportSourceLocator(), Arrays.asList(new XMLReportGenerator(), new HTMLReportGenerator()));
    }
    
    public ReportGenerationManager(final ReportSourceLocator reportLocator, final List<ReportGenerationStrategy> reportGenerationStrategyList) {
        this.reportLocator = reportLocator;
        this.reportGenerationStrategyList = reportGenerationStrategyList;
    }
    
    public void generateSiteReport(final ReportGenerationContext context) {
        boolean successfulExecution = false;
        context.setReportsDataDirectory(this.reportLocator.locate(context.getReportsDataDirectory(), context.getLogger()));
        context.getLogger().debug("starting execution of report generators");
        context.getLogger().debug("using report generation context: " + context);
        for (final String dataFormat : context.getSourceDataFormats()) {
            context.getLogger().debug("starting report generator for source data format [" + dataFormat + "]");
            final ReportGenerationResultEnum result = this.locateReportGenerationStrategy(dataFormat).generate(context);
            context.getLogger().debug("result of report generator for source data format [" + dataFormat + "] was [" + result.toString() + "]");
            if (result == ReportGenerationResultEnum.SUCCESS) {
                successfulExecution = true;
                break;
            }
        }
        if (!successfulExecution) {
            throw new PitError("no report generators executed successfully");
        }
        context.getLogger().debug("finished execution of report generators");
    }
    
    private ReportGenerationStrategy locateReportGenerationStrategy(final String sourceDataFormat) {
        for (final ReportGenerationStrategy strategy : this.reportGenerationStrategyList) {
            if (sourceDataFormat.equalsIgnoreCase(strategy.getGeneratorDataFormat())) {
                return strategy;
            }
        }
        throw new PitError("Could not locate report generator for data source [" + sourceDataFormat + "]");
    }
}
