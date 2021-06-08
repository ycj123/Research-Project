// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.report.html;

import org.pitest.mutationtest.DetectionStatus;
import java.util.EnumSet;

class ConfidenceMap
{
    private static final EnumSet<DetectionStatus> HIGH;
    
    public static boolean hasHighConfidence(final DetectionStatus status) {
        return ConfidenceMap.HIGH.contains(status);
    }
    
    static {
        HIGH = EnumSet.of(DetectionStatus.KILLED, DetectionStatus.SURVIVED, DetectionStatus.NO_COVERAGE, DetectionStatus.NON_VIABLE);
    }
}
