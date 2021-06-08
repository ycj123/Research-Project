// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.util;

import java.util.Calendar;
import java.util.Collection;
import org.apache.maven.plugin.surefire.runorder.RunEntryStatisticsMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import org.apache.maven.surefire.testset.RunOrderParameters;
import java.util.Comparator;

public class DefaultRunOrderCalculator implements RunOrderCalculator
{
    private final Comparator<Class> sortOrder;
    private final RunOrder[] runOrder;
    private final RunOrderParameters runOrderParameters;
    private final int threadCount;
    
    public DefaultRunOrderCalculator(final RunOrderParameters runOrderParameters, final int threadCount) {
        this.runOrderParameters = runOrderParameters;
        this.threadCount = threadCount;
        this.runOrder = runOrderParameters.getRunOrder();
        this.sortOrder = ((this.runOrder.length > 0) ? this.getSortOrderComparator(this.runOrder[0]) : null);
    }
    
    public TestsToRun orderTestClasses(final TestsToRun scannedClasses) {
        final List<Class> result = new ArrayList<Class>(500);
        for (final Class scannedClass : scannedClasses) {
            result.add(scannedClass);
        }
        this.orderTestClasses(result, (this.runOrder.length != 0) ? this.runOrder[0] : null);
        return new TestsToRun(result);
    }
    
    private void orderTestClasses(final List<Class> testClasses, final RunOrder runOrder) {
        if (RunOrder.RANDOM.equals(runOrder)) {
            Collections.shuffle(testClasses);
        }
        else if (RunOrder.FAILEDFIRST.equals(runOrder)) {
            final RunEntryStatisticsMap runEntryStatisticsMap = RunEntryStatisticsMap.fromFile(this.runOrderParameters.getRunStatisticsFile());
            final List<Class> prioritized = runEntryStatisticsMap.getPrioritizedTestsByFailureFirst(testClasses);
            testClasses.clear();
            testClasses.addAll(prioritized);
        }
        else if (RunOrder.BALANCED.equals(runOrder)) {
            final RunEntryStatisticsMap runEntryStatisticsMap = RunEntryStatisticsMap.fromFile(this.runOrderParameters.getRunStatisticsFile());
            final List<Class> prioritized = runEntryStatisticsMap.getPrioritizedTestsClassRunTime(testClasses, this.threadCount);
            testClasses.clear();
            testClasses.addAll(prioritized);
        }
        else if (this.sortOrder != null) {
            Collections.sort(testClasses, this.sortOrder);
        }
    }
    
    private Comparator<Class> getSortOrderComparator(final RunOrder runOrder) {
        if (RunOrder.ALPHABETICAL.equals(runOrder)) {
            return this.getAlphabeticalComparator();
        }
        if (RunOrder.REVERSE_ALPHABETICAL.equals(runOrder)) {
            return this.getReverseAlphabeticalComparator();
        }
        if (RunOrder.HOURLY.equals(runOrder)) {
            final int hour = Calendar.getInstance().get(11);
            return (hour % 2 == 0) ? this.getAlphabeticalComparator() : this.getReverseAlphabeticalComparator();
        }
        return null;
    }
    
    private Comparator<Class> getReverseAlphabeticalComparator() {
        return new Comparator<Class>() {
            public int compare(final Class o1, final Class o2) {
                return o2.getName().compareTo(o1.getName());
            }
        };
    }
    
    private Comparator<Class> getAlphabeticalComparator() {
        return new Comparator<Class>() {
            public int compare(final Class o1, final Class o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
    }
}
