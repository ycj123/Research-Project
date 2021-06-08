// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.runorder;

import java.util.regex.Matcher;
import org.apache.maven.surefire.report.ReportEntry;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;
import java.util.Collection;
import java.util.ArrayList;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.FileReader;
import java.io.File;
import java.util.HashMap;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.Map;

public class RunEntryStatisticsMap
{
    private final Map<String, RunEntryStatistics> runEntryStatistics;
    private static final Pattern PARENS;
    
    public RunEntryStatisticsMap(final Map<String, RunEntryStatistics> runEntryStatistics) {
        this.runEntryStatistics = Collections.synchronizedMap(runEntryStatistics);
    }
    
    public RunEntryStatisticsMap() {
        this(new HashMap<String, RunEntryStatistics>());
    }
    
    public static RunEntryStatisticsMap fromFile(final File file) {
        if (file.exists()) {
            try {
                final FileReader fileReader = new FileReader(file);
                return fromReader(fileReader);
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
        return new RunEntryStatisticsMap();
    }
    
    static RunEntryStatisticsMap fromReader(final Reader fileReader) throws IOException {
        final Map<String, RunEntryStatistics> result = new HashMap<String, RunEntryStatistics>();
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            if (!line.startsWith("#")) {
                final RunEntryStatistics stats = RunEntryStatistics.fromString(line);
                result.put(stats.getTestName(), stats);
            }
        }
        return new RunEntryStatisticsMap(result);
    }
    
    public void serialize(final File file) throws FileNotFoundException {
        final FileOutputStream fos = new FileOutputStream(file);
        final PrintWriter printWriter = new PrintWriter(fos);
        final List<RunEntryStatistics> items = new ArrayList<RunEntryStatistics>(this.runEntryStatistics.values());
        Collections.sort(items, new RunCountComparator());
        for (final RunEntryStatistics item2 : items) {
            final RunEntryStatistics item1 = item2;
            printWriter.println(item2.getAsString());
        }
        printWriter.close();
    }
    
    public RunEntryStatistics findOrCreate(final ReportEntry reportEntry) {
        final RunEntryStatistics item = this.runEntryStatistics.get(reportEntry.getName());
        return (item != null) ? item : RunEntryStatistics.fromReportEntry(reportEntry);
    }
    
    public RunEntryStatistics createNextGeneration(final ReportEntry reportEntry) {
        final RunEntryStatistics newItem = this.findOrCreate(reportEntry);
        final Integer elapsed = reportEntry.getElapsed();
        return newItem.nextGeneration((elapsed != null) ? elapsed : 0);
    }
    
    public RunEntryStatistics createNextGenerationFailure(final ReportEntry reportEntry) {
        final RunEntryStatistics newItem = this.findOrCreate(reportEntry);
        final Integer elapsed = reportEntry.getElapsed();
        return newItem.nextGenerationFailure((elapsed != null) ? elapsed : 0);
    }
    
    public void add(final RunEntryStatistics item) {
        this.runEntryStatistics.put(item.getTestName(), item);
    }
    
    public List<Class> getPrioritizedTestsClassRunTime(final List testsToRun, final int threadCount) {
        final List<PrioritizedTest> prioritizedTests = this.getPrioritizedTests(testsToRun, new TestRuntimeComparator());
        final ThreadedExecutionScheduler threadedExecutionScheduler = new ThreadedExecutionScheduler(threadCount);
        for (final Object prioritizedTest1 : prioritizedTests) {
            threadedExecutionScheduler.addTest((PrioritizedTest)prioritizedTest1);
        }
        return threadedExecutionScheduler.getResult();
    }
    
    public List<Class> getPrioritizedTestsByFailureFirst(final List testsToRun) {
        final List prioritizedTests = this.getPrioritizedTests(testsToRun, new LeastFailureComparator());
        return this.transformToClasses(prioritizedTests);
    }
    
    private List<PrioritizedTest> getPrioritizedTests(final List testsToRun, final Comparator<Priority> priorityComparator) {
        final Map classPriorities = this.getPriorities(priorityComparator);
        final List<PrioritizedTest> tests = new ArrayList<PrioritizedTest>();
        for (final Object aTestsToRun : testsToRun) {
            final Class clazz = (Class)aTestsToRun;
            Priority pri = classPriorities.get(clazz.getName());
            if (pri == null) {
                pri = Priority.newTestClassPriority(clazz.getName());
            }
            final PrioritizedTest prioritizedTest = new PrioritizedTest(clazz, pri);
            tests.add(prioritizedTest);
        }
        Collections.sort(tests, new PrioritizedTestComparator());
        return tests;
    }
    
    private List<Class> transformToClasses(final List tests) {
        final List<Class> result = new ArrayList<Class>();
        for (final Object test : tests) {
            result.add(((PrioritizedTest)test).getClazz());
        }
        return result;
    }
    
    public Map getPriorities(final Comparator<Priority> priorityComparator) {
        final Map<String, Priority> priorities = new HashMap<String, Priority>();
        for (final Object o : this.runEntryStatistics.keySet()) {
            final String testNames = (String)o;
            final String clazzName = this.extractClassName(testNames);
            Priority priority = priorities.get(clazzName);
            if (priority == null) {
                priority = new Priority(clazzName);
                priorities.put(clazzName, priority);
            }
            final RunEntryStatistics itemStat = this.runEntryStatistics.get(testNames);
            priority.addItem(itemStat);
        }
        final List<Priority> items = new ArrayList<Priority>(priorities.values());
        Collections.sort(items, priorityComparator);
        final Map<String, Priority> result = new HashMap<String, Priority>();
        int i = 0;
        for (final Priority pri : items) {
            pri.setPriority(i++);
            result.put(pri.getClassName(), pri);
        }
        return result;
    }
    
    String extractClassName(final String displayName) {
        final Matcher m = RunEntryStatisticsMap.PARENS.matcher(displayName);
        if (!m.find()) {
            return displayName;
        }
        return m.group(1);
    }
    
    static {
        PARENS = Pattern.compile("^[^\\(\\)]+\\(([^\\\\(\\\\)]+)\\)$");
    }
    
    class RunCountComparator implements Comparator<RunEntryStatistics>
    {
        public int compare(final RunEntryStatistics o, final RunEntryStatistics o1) {
            final int runtime = o.getSuccessfulBuilds() - o1.getSuccessfulBuilds();
            if (runtime == 0) {
                return o.getRunTime() - o1.getRunTime();
            }
            return runtime;
        }
    }
    
    class PrioritizedTestComparator implements Comparator<PrioritizedTest>
    {
        public int compare(final PrioritizedTest o, final PrioritizedTest o1) {
            return o.getPriority() - o1.getPriority();
        }
    }
    
    class TestRuntimeComparator implements Comparator<Priority>
    {
        public int compare(final Priority o, final Priority o1) {
            return o1.getTotalRuntime() - o.getTotalRuntime();
        }
    }
    
    class LeastFailureComparator implements Comparator<Priority>
    {
        public int compare(final Priority o, final Priority o1) {
            return o.getMinSuccessRate() - o1.getMinSuccessRate();
        }
    }
}
