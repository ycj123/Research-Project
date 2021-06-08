// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.runorder;

import java.util.ArrayList;
import java.util.List;

public class ThreadedExecutionScheduler
{
    private final int numThreads;
    private final int[] runTime;
    private final List[] lists;
    
    public ThreadedExecutionScheduler(final int numThreads) {
        this.numThreads = numThreads;
        this.runTime = new int[numThreads];
        this.lists = new List[numThreads];
        for (int i = 0; i < numThreads; ++i) {
            this.lists[i] = new ArrayList();
        }
    }
    
    public void addTest(final PrioritizedTest prioritizedTest) {
        final int leastBusySlot = this.findLeastBusySlot();
        final int[] runTime = this.runTime;
        final int n = leastBusySlot;
        runTime[n] += prioritizedTest.getTotalRuntime();
        this.lists[leastBusySlot].add(prioritizedTest.getClazz());
    }
    
    public List<Class> getResult() {
        final List<Class> result = new ArrayList<Class>();
        int index = 0;
        boolean added;
        do {
            added = false;
            for (int i = 0; i < this.numThreads; ++i) {
                if (this.lists[i].size() > index) {
                    result.add(this.lists[i].get(index));
                    added = true;
                }
            }
            ++index;
        } while (added);
        return result;
    }
    
    private int findLeastBusySlot() {
        int leastBusy = 0;
        int minRuntime = this.runTime[0];
        for (int i = 1; i < this.numThreads; ++i) {
            if (this.runTime[i] < minRuntime) {
                leastBusy = i;
                minRuntime = this.runTime[i];
            }
        }
        return leastBusy;
    }
}
