// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.testset;

import java.io.File;
import org.apache.maven.surefire.util.RunOrder;

public class RunOrderParameters
{
    private final RunOrder[] runOrder;
    private File runStatisticsFile;
    
    public RunOrderParameters(final RunOrder[] runOrder, final File runStatisticsFile) {
        this.runOrder = runOrder;
        this.runStatisticsFile = runStatisticsFile;
    }
    
    public RunOrderParameters(final String runOrder, final String runStatisticsFile) {
        this.runOrder = ((runOrder == null) ? RunOrder.DEFAULT : RunOrder.valueOfMulti(runOrder));
        this.runStatisticsFile = ((runStatisticsFile != null) ? new File(runStatisticsFile) : null);
    }
    
    public static RunOrderParameters ALPHABETICAL() {
        return new RunOrderParameters(new RunOrder[] { RunOrder.ALPHABETICAL }, null);
    }
    
    public RunOrder[] getRunOrder() {
        return this.runOrder;
    }
    
    public File getRunStatisticsFile() {
        return this.runStatisticsFile;
    }
}
