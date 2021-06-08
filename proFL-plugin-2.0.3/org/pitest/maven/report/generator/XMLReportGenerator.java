// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven.report.generator;

public class XMLReportGenerator implements ReportGenerationStrategy
{
    @Override
    public ReportGenerationResultEnum generate(final ReportGenerationContext context) {
        context.getLogger().debug("XMLReportGenerator not yet implemented");
        return ReportGenerationResultEnum.NOT_EXECUTED;
    }
    
    @Override
    public String getGeneratorName() {
        return "XMLReportGenerator";
    }
    
    @Override
    public String getGeneratorDataFormat() {
        return "XML";
    }
}
