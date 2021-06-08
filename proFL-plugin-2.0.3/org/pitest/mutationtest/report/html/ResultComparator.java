// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.report.html;

import org.pitest.mutationtest.DetectionStatus;
import java.util.EnumMap;
import java.io.Serializable;
import org.pitest.mutationtest.MutationResult;
import java.util.Comparator;

class ResultComparator implements Comparator<MutationResult>, Serializable
{
    private static final long serialVersionUID = 1L;
    private static final EnumMap<DetectionStatus, Integer> RANK;
    
    @Override
    public int compare(final MutationResult o1, final MutationResult o2) {
        return this.getRanking(o1.getStatus()) - this.getRanking(o2.getStatus());
    }
    
    private int getRanking(final DetectionStatus status) {
        return ResultComparator.RANK.get(status);
    }
    
    static {
        (RANK = new EnumMap<DetectionStatus, Integer>(DetectionStatus.class)).put(DetectionStatus.KILLED, 4);
        ResultComparator.RANK.put(DetectionStatus.SURVIVED, 0);
        ResultComparator.RANK.put(DetectionStatus.TIMED_OUT, 2);
        ResultComparator.RANK.put(DetectionStatus.NON_VIABLE, 3);
        ResultComparator.RANK.put(DetectionStatus.MEMORY_ERROR, 1);
        ResultComparator.RANK.put(DetectionStatus.NOT_STARTED, 1);
        ResultComparator.RANK.put(DetectionStatus.STARTED, 1);
        ResultComparator.RANK.put(DetectionStatus.RUN_ERROR, 0);
        ResultComparator.RANK.put(DetectionStatus.NO_COVERAGE, 0);
    }
}
