// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.report.html;

import org.pitest.mutationtest.DetectionStatus;

public class LineStyle
{
    private final Line line;
    
    public LineStyle(final Line line) {
        this.line = line;
    }
    
    public String getLineCoverage() {
        switch (this.line.getLineCovered()) {
            case Covered: {
                return "covered";
            }
            case NotCovered: {
                return "uncovered";
            }
            default: {
                return "na";
            }
        }
    }
    
    public String getCode() {
        switch (this.line.getLineCovered()) {
            case Covered: {
                return "covered";
            }
            case NotCovered: {
                return "uncovered";
            }
            default: {
                return "";
            }
        }
    }
    
    public String getMutation() {
        if (this.line.detectionStatus().hasNone()) {
            return "";
        }
        final DetectionStatus status = this.line.detectionStatus().value();
        if (!status.isDetected()) {
            return "survived";
        }
        if (ConfidenceMap.hasHighConfidence(status)) {
            return "killed";
        }
        return "uncertain";
    }
    
    public String getText() {
        return this.getLineCoverage();
    }
}
