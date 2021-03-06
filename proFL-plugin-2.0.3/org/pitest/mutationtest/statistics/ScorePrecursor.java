// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.statistics;

import java.util.LinkedHashMap;
import org.pitest.functional.F2;
import org.pitest.functional.F;
import org.pitest.functional.FCollection;
import org.pitest.mutationtest.DetectionStatus;
import java.util.Map;

class ScorePrecursor
{
    private final String mutatorName;
    private final Map<DetectionStatus, StatusCount> counts;
    
    ScorePrecursor(final String name) {
        this.mutatorName = name;
        this.counts = createMap();
    }
    
    void registerResult(final DetectionStatus result) {
        final StatusCount total = this.counts.get(result);
        total.increment();
    }
    
    Iterable<StatusCount> getCounts() {
        return this.counts.values();
    }
    
    private long getTotalMutations() {
        return FCollection.fold(this.addTotals(), 0L, this.counts.values());
    }
    
    private long getTotalDetectedMutations() {
        return FCollection.fold(this.addTotals(), 0L, FCollection.filter(this.counts.values(), isDetected()));
    }
    
    private static F<StatusCount, Boolean> isDetected() {
        return new F<StatusCount, Boolean>() {
            @Override
            public Boolean apply(final StatusCount a) {
                return a.getStatus().isDetected();
            }
        };
    }
    
    private F2<Long, StatusCount, Long> addTotals() {
        return new F2<Long, StatusCount, Long>() {
            @Override
            public Long apply(final Long a, final StatusCount b) {
                return a + b.getCount();
            }
        };
    }
    
    private static Map<DetectionStatus, StatusCount> createMap() {
        final Map<DetectionStatus, StatusCount> map = new LinkedHashMap<DetectionStatus, StatusCount>();
        for (final DetectionStatus each : DetectionStatus.values()) {
            map.put(each, new StatusCount(each, 0L));
        }
        return map;
    }
    
    Score toScore() {
        return new Score(this.mutatorName, this.getCounts(), this.getTotalMutations(), this.getTotalDetectedMutations());
    }
}
