// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven.report.generator;

public interface ReportGenerationStrategy
{
    ReportGenerationResultEnum generate(final ReportGenerationContext p0);
    
    String getGeneratorName();
    
    String getGeneratorDataFormat();
}
