// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven.report.generator;

import org.mudebug.prapr.reloc.commons.io.filefilter.IOFileFilter;
import org.mudebug.prapr.reloc.commons.io.filefilter.NotFileFilter;
import org.mudebug.prapr.reloc.commons.io.filefilter.RegexFileFilter;
import java.io.IOException;
import org.mudebug.prapr.reloc.commons.io.FileUtils;
import java.io.FileFilter;

public class HTMLReportGenerator implements ReportGenerationStrategy
{
    protected static final FileFilter EXCLUDE_TIMESTAMPED_REPORTS_DIRECTORIES;
    
    @Override
    public ReportGenerationResultEnum generate(final ReportGenerationContext context) {
        try {
            context.getLogger().debug("HTMLReportGenerator using directory [" + context.getReportsDataDirectory() + "] as directory containing the html report");
            context.getLogger().debug("HTMLReportGenerator using directory [" + context.getSiteDirectory() + "] as directory that is the destination of the site report");
            FileUtils.copyDirectory(context.getReportsDataDirectory(), context.getSiteDirectory(), HTMLReportGenerator.EXCLUDE_TIMESTAMPED_REPORTS_DIRECTORIES);
        }
        catch (IOException e) {
            context.getLogger().warn(e);
            return ReportGenerationResultEnum.FAILURE;
        }
        return ReportGenerationResultEnum.SUCCESS;
    }
    
    @Override
    public String getGeneratorName() {
        return "HTMLReportGenerator";
    }
    
    @Override
    public String getGeneratorDataFormat() {
        return "HTML";
    }
    
    static {
        EXCLUDE_TIMESTAMPED_REPORTS_DIRECTORIES = new NotFileFilter(new RegexFileFilter("^\\d+$"));
    }
}
