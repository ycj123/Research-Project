// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.entry.report.log;

import org.pitest.mutationtest.DetectionStatus;
import java.io.FileOutputStream;
import java.util.Arrays;
import org.pitest.mutationtest.ClassMutationResults;
import java.util.Date;
import org.pitest.functional.FCollection;
import org.pitest.functional.F;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.mudebug.prapr.entry.report.Commons;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import org.pitest.util.ResultOutputStrategy;
import org.pitest.mutationtest.engine.Mutater;
import java.util.Collection;
import org.mudebug.prapr.core.SuspStrategy;
import java.util.Set;
import org.pitest.mutationtest.MutationResult;
import java.util.List;
import java.io.Writer;
import java.io.File;
import org.pitest.mutationtest.engine.MutationDetails;
import java.util.Map;
import org.pitest.mutationtest.MutationResultListener;

public class LOGReportListener implements MutationResultListener
{
    private final Map<MutationDetails, Integer> allRanks;
    private final Map<MutationDetails, Integer> plRanks;
    private final Map<MutationDetails, File> dumpFiles;
    private final Writer out;
    private final Map<String, Double> mutatorScore;
    private final List<MutationResult> killedMutations;
    private final List<MutationResult> survivedMutations;
    private final Set<MutationResultWrapper> allMutations;
    private final SuspStrategy suspStrategy;
    private final Collection<String> failingTests;
    private final int allTestsCount;
    private final Mutater mutater;
    private final File poolDirectory;
    private final boolean shouldDumpMutations;
    
    public LOGReportListener(final ResultOutputStrategy outStrategy, final File poolDirectory, final SuspStrategy suspStrategy, final Collection<String> failingTests, final int allTestsCount, final Mutater mutater, final boolean shouldDumpMutations) {
        this.poolDirectory = poolDirectory;
        this.out = outStrategy.createWriterForFile("fix-report.log");
        this.mutatorScore = new HashMap<String, Double>();
        this.killedMutations = new ArrayList<MutationResult>();
        this.survivedMutations = new ArrayList<MutationResult>();
        this.allMutations = new HashSet<MutationResultWrapper>();
        this.suspStrategy = suspStrategy;
        this.failingTests = failingTests;
        this.allTestsCount = allTestsCount;
        this.allRanks = new HashMap<MutationDetails, Integer>();
        this.plRanks = new HashMap<MutationDetails, Integer>();
        this.dumpFiles = new HashMap<MutationDetails, File>();
        this.mutater = mutater;
        this.shouldDumpMutations = shouldDumpMutations;
    }
    
    private void writeln(final String s) {
        try {
            this.out.write(s + "\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void computeMutatorScores() {
        final Map<String, Integer> totalCount = new HashMap<String, Integer>();
        final Map<String, Integer> plausibleCount = new HashMap<String, Integer>();
        for (final MutationResult mr : this.killedMutations) {
            final String mutator = Commons.sanitizeMutatorName(mr.getDetails().getMutator());
            this.inc(totalCount, mutator);
        }
        for (final MutationResult mr : this.survivedMutations) {
            final String mutator = Commons.sanitizeMutatorName(mr.getDetails().getMutator());
            this.inc(plausibleCount, mutator);
            this.inc(totalCount, mutator);
        }
        for (final Map.Entry<String, Integer> totalEnt : totalCount.entrySet()) {
            final String mutator = totalEnt.getKey();
            Integer num = plausibleCount.get(mutator);
            if (num == null) {
                num = 0;
            }
            final Integer denum = totalEnt.getValue();
            final Double score = num / (double)denum;
            this.mutatorScore.put(mutator, score);
        }
    }
    
    private List<List<MutationDetails>> groupAndSortBySusp(final List<List<MutationResult>> mutationsSuperList) {
        Map<Double, List<MutationDetails>> mutationsGroupedMap = new HashMap<Double, List<MutationDetails>>();
        for (final List<MutationResult> mrl : mutationsSuperList) {
            for (final MutationResult mr : mrl) {
                final MutationDetails md = mr.getDetails();
                final Double susp = Commons.calculateSusp(this.suspStrategy, md, this.failingTests, this.allTestsCount);
                List<MutationDetails> group = mutationsGroupedMap.get(susp);
                if (group == null) {
                    group = new ArrayList<MutationDetails>();
                    mutationsGroupedMap.put(susp, group);
                }
                group.add(md);
            }
        }
        final List list = new ArrayList(mutationsGroupedMap.entrySet());
        mutationsGroupedMap = null;
        Collections.sort((List<Object>)list, new Comparator() {
            @Override
            public int compare(final Object o1, final Object o2) {
                final Map.Entry<Double, ?> e1 = (Map.Entry<Double, ?>)o1;
                final Map.Entry<Double, ?> e2 = (Map.Entry<Double, ?>)o2;
                return Double.compare(e2.getKey(), e1.getKey());
            }
        });
        for (int i = 0; i < list.size(); ++i) {
            list.set(i, list.get(i).getValue());
        }
        return (List<List<MutationDetails>>)list;
    }
    
    private List<List<MutationDetails>> groupAndSortByMutScore(final List<MutationDetails> chunk) {
        Map<Double, Collection<MutationDetails>> mutationsGroupedMap = FCollection.bucket(chunk, (F<MutationDetails, Double>)new F<MutationDetails, Double>() {
            @Override
            public Double apply(final MutationDetails md) {
                return LOGReportListener.this.mutatorScore.get(Commons.sanitizeMutatorName(md.getMutator()));
            }
        });
        final List list = new ArrayList(mutationsGroupedMap.entrySet());
        mutationsGroupedMap = null;
        Collections.sort((List<Object>)list, new Comparator() {
            @Override
            public int compare(final Object o1, final Object o2) {
                final Map.Entry<Double, ?> e1 = (Map.Entry<Double, ?>)o1;
                final Map.Entry<Double, ?> e2 = (Map.Entry<Double, ?>)o2;
                return Double.compare(e1.getKey(), e2.getKey());
            }
        });
        for (int i = 0; i < list.size(); ++i) {
            list.set(i, list.get(i).getValue());
        }
        return (List<List<MutationDetails>>)list;
    }
    
    private void doRanking(final SetRankFunction setRankFunction, final List<List<MutationResult>> mutationsSuperList) {
        final List list = this.groupAndSortBySusp(mutationsSuperList);
        for (int i = 0; i < list.size(); ++i) {
            list.set(i, this.groupAndSortByMutScore(list.get(i)));
        }
        final List<List<MutationDetails>> finalSorted = (List<List<MutationDetails>>)(List)FCollection.flatten((Iterable<? extends Iterable<?>>)list);
        int rank = 0;
        for (final List<MutationDetails> mdl : finalSorted) {
            rank += mdl.size();
            for (final MutationDetails md : mdl) {
                setRankFunction.setRank(md, rank);
            }
        }
    }
    
    private void inc(final Map<String, Integer> countMap, final String mutator) {
        Integer count = countMap.get(mutator);
        if (count == null) {
            count = 0;
        }
        countMap.put(mutator, count + 1);
    }
    
    private void thickLine() {
        this.writeln("================================================");
    }
    
    private void thinLine() {
        this.writeln("------------------------------------------------");
    }
    
    @Override
    public void runStart() {
        this.writeln("PraPR 2 (JDK 1.7) Fix Report - " + new Date().toString());
    }
    
    @Override
    public void handleMutationResult(final ClassMutationResults results) {
        for (final MutationResult mr : results.getMutations()) {
            this.allMutations.add(MutationResultWrapper.wrap(mr));
        }
    }
    
    private void printRankedList() {
        final int plausiblesSize = this.survivedMutations.size();
        final int totalSize = this.killedMutations.size() + plausiblesSize;
        this.writeln("Number of Plausible Fixes: " + plausiblesSize);
        this.writeln("Total Number of Patches: " + totalSize);
        this.thickLine();
        if (plausiblesSize == 0) {
            this.writeln("No fix found!");
            return;
        }
        this.computeMutatorScores();
        this.doRanking(new SetRankFunction() {
            @Override
            public void setRank(final MutationDetails md, final int rank) {
                LOGReportListener.this.plRanks.put(md, rank);
            }
        }, Arrays.asList(this.survivedMutations));
        this.doRanking(new SetRankFunction() {
            @Override
            public void setRank(final MutationDetails md, final int rank) {
                LOGReportListener.this.allRanks.put(md, rank);
            }
        }, Arrays.asList(this.survivedMutations, this.killedMutations));
        Collections.sort(this.survivedMutations, new Comparator<MutationResult>() {
            @Override
            public int compare(final MutationResult mr1, final MutationResult mr2) {
                final MutationDetails md1 = mr1.getDetails();
                final MutationDetails md2 = mr2.getDetails();
                final int rank1 = LOGReportListener.this.plRanks.get(md1);
                final int rank2 = LOGReportListener.this.plRanks.get(md2);
                return Integer.compare(rank1, rank2);
            }
        });
        for (int i = 0; i < this.survivedMutations.size(); ++i) {
            final MutationDetails md = this.survivedMutations.get(i).getDetails();
            this.printMutationDetails(1 + i, md);
            this.thinLine();
        }
    }
    
    private void printMutationDetails(final int row, final MutationDetails md) {
        this.writeln(String.format("%d.", row));
        this.writeln(String.format("\tMutator: %s", Commons.sanitizeMutatorName(md.getMutator())));
        this.writeln(String.format("\tDescription: %s", md.getDescription()));
        final String mutatedClass = md.getClassName().asInternalName();
        final int lastSlash = mutatedClass.lastIndexOf(47);
        String filePath;
        if (lastSlash >= 0) {
            filePath = mutatedClass.substring(0, 1 + lastSlash);
        }
        else {
            filePath = "";
        }
        this.writeln(String.format("\tFile Name: %s%s", filePath, md.getFilename()));
        this.writeln(String.format("\tLine Number: %d", md.getLineNumber()));
        this.writeln(String.format("\tRank: %d", this.plRanks.get(md)));
        this.writeln(String.format("\tTotal Rank: %d", this.allRanks.get(md)));
        final File dumpFile = this.dumpFiles.get(md);
        if (dumpFile != null) {
            this.writeln(String.format("\tDump: %s", dumpFile.getName()));
            if (this.shouldDumpMutations) {
                try (final FileOutputStream fos = new FileOutputStream(dumpFile)) {
                    final byte[] bytes = this.mutater.getMutation(md.getId()).getBytes();
                    fos.write(bytes);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private File getDumpFile() {
        final int id = this.survivedMutations.size();
        return new File(this.poolDirectory, "mutant-" + id + ".class");
    }
    
    private void multiplexMutations() {
        final Iterator<MutationResultWrapper> mit = this.allMutations.iterator();
        while (mit.hasNext()) {
            final MutationResult mr = mit.next().getMutationResult();
            if (mr.getStatus() == DetectionStatus.SURVIVED) {
                this.survivedMutations.add(mr);
                if (this.shouldDumpMutations) {
                    this.dumpFiles.put(mr.getDetails(), this.getDumpFile());
                }
            }
            else {
                this.killedMutations.add(mr);
            }
            mit.remove();
        }
    }
    
    @Override
    public void runEnd() {
        try {
            this.multiplexMutations();
            if (this.shouldDumpMutations) {
                this.poolDirectory.mkdirs();
            }
            this.printRankedList();
            this.out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
