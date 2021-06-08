// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.aggregate;

import org.pitest.functional.Option;
import java.util.HashMap;
import org.pitest.coverage.TestInfo;
import org.pitest.coverage.BlockLocation;
import java.util.Map;
import org.pitest.coverage.LineMap;
import org.pitest.coverage.analysis.LineMapper;
import org.pitest.coverage.CoverageData;
import org.pitest.coverage.CoverageDatabase;
import org.pitest.classpath.CodeSource;
import org.pitest.mutationtest.report.html.MutationHtmlReportListener;
import org.pitest.mutationtest.SourceLocator;
import org.pitest.functional.F;
import org.pitest.functional.FCollection;
import org.pitest.mutationtest.tooling.SmartSourceLocator;
import java.util.Iterator;
import org.pitest.mutationtest.MutationResultListener;
import org.pitest.mutationtest.ClassMutationResults;
import java.util.List;
import org.pitest.mutationtest.MutationMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.util.Collection;
import org.pitest.mutationtest.MutationResult;
import org.pitest.coverage.BlockCoverage;
import org.pitest.util.ResultOutputStrategy;

public final class ReportAggregator
{
    private final ResultOutputStrategy resultOutputStrategy;
    private final DataLoader<BlockCoverage> blockCoverageLoader;
    private final DataLoader<MutationResult> mutationLoader;
    private final Collection<File> sourceCodeDirectories;
    private final CodeSourceAggregator codeSourceAggregator;
    
    private ReportAggregator(final ResultOutputStrategy resultOutputStrategy, final Set<File> lineCoverageFiles, final Set<File> mutationFiles, final Set<File> sourceCodeDirs, final Set<File> compiledCodeDirs) {
        this.resultOutputStrategy = resultOutputStrategy;
        this.blockCoverageLoader = new BlockCoverageDataLoader(lineCoverageFiles);
        this.mutationLoader = new MutationResultDataLoader(mutationFiles);
        this.sourceCodeDirectories = Collections.unmodifiableCollection((Collection<? extends File>)new HashSet<File>(sourceCodeDirs));
        this.codeSourceAggregator = new CodeSourceAggregator(new HashSet<File>(compiledCodeDirs));
    }
    
    public void aggregateReport() throws ReportAggregationException {
        final MutationMetaData mutationMetaData = new MutationMetaData(new ArrayList<MutationResult>(this.mutationLoader.loadData()));
        final MutationResultListener mutationResultListener = this.createResultListener(mutationMetaData);
        mutationResultListener.runStart();
        for (final ClassMutationResults mutationResults : mutationMetaData.toClassResults()) {
            mutationResultListener.handleMutationResult(mutationResults);
        }
        mutationResultListener.runEnd();
    }
    
    private MutationResultListener createResultListener(final MutationMetaData mutationMetaData) throws ReportAggregationException {
        final SourceLocator sourceLocator = new SmartSourceLocator(this.sourceCodeDirectories);
        final CodeSource codeSource = this.codeSourceAggregator.createCodeSource();
        final CoverageDatabase coverageDatabase = this.calculateCoverage(codeSource, mutationMetaData);
        final Collection<String> mutatorNames = new HashSet<String>((Collection<? extends String>)FCollection.flatMap((Iterable<? extends MutationResult>)mutationMetaData.getMutations(), (F<MutationResult, ? extends Iterable<Object>>)resultToMutatorName()));
        return new MutationHtmlReportListener(coverageDatabase, this.resultOutputStrategy, mutatorNames, new SourceLocator[] { sourceLocator });
    }
    
    private static F<MutationResult, List<String>> resultToMutatorName() {
        return new F<MutationResult, List<String>>() {
            @Override
            public List<String> apply(final MutationResult a) {
                try {
                    final String mutatorName = MutatorUtil.loadMutator(a.getDetails().getMutator()).getName();
                    return Collections.singletonList(mutatorName);
                }
                catch (Exception e) {
                    throw new RuntimeException("Cannot convert to mutator: " + a.getDetails().getMutator(), e);
                }
            }
        };
    }
    
    private CoverageData calculateCoverage(final CodeSource codeSource, final MutationMetaData metadata) throws ReportAggregationException {
        final Collection<BlockCoverage> coverageData = this.blockCoverageLoader.loadData();
        try {
            final Map<BlockLocation, Set<TestInfo>> blockCoverageMap = this.blocksToMap(coverageData);
            return new CoverageData(codeSource, new LineMapper(codeSource), blockCoverageMap);
        }
        catch (Exception e) {
            throw new ReportAggregationException(e.getMessage(), e);
        }
    }
    
    private Map<BlockLocation, Set<TestInfo>> blocksToMap(final Collection<BlockCoverage> coverageData) {
        final Map<BlockLocation, Set<TestInfo>> blockCoverageMap = new HashMap<BlockLocation, Set<TestInfo>>();
        for (final BlockCoverage blockData : coverageData) {
            blockCoverageMap.put(blockData.getBlock(), new HashSet<TestInfo>(FCollection.map(blockData.getTests(), this.toTestInfo(blockData))));
        }
        return blockCoverageMap;
    }
    
    private F<String, TestInfo> toTestInfo(final BlockCoverage blockData) {
        return new F<String, TestInfo>() {
            @Override
            public TestInfo apply(final String a) {
                return new TestInfo(null, a, 0, Option.some(blockData.getBlock().getLocation().getClassName()), blockData.getBlock().getBlock());
            }
        };
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder
    {
        private ResultOutputStrategy resultOutputStrategy;
        private final Set<File> lineCoverageFiles;
        private final Set<File> mutationResultsFiles;
        private final Set<File> sourceCodeDirectories;
        private final Set<File> compiledCodeDirectories;
        
        public Builder() {
            this.lineCoverageFiles = new HashSet<File>();
            this.mutationResultsFiles = new HashSet<File>();
            this.sourceCodeDirectories = new HashSet<File>();
            this.compiledCodeDirectories = new HashSet<File>();
        }
        
        public Builder resultOutputStrategy(final ResultOutputStrategy resultOutputStrategy) {
            this.resultOutputStrategy = resultOutputStrategy;
            return this;
        }
        
        public Builder lineCoverageFiles(final List<File> lineCoverageFiles) {
            this.lineCoverageFiles.clear();
            for (final File file : lineCoverageFiles) {
                this.addLineCoverageFile(file);
            }
            return this;
        }
        
        public Builder addLineCoverageFile(final File lineCoverageFile) {
            this.validateFile(lineCoverageFile);
            this.lineCoverageFiles.add(lineCoverageFile);
            return this;
        }
        
        public Builder mutationResultsFiles(final List<File> mutationResultsFiles) {
            this.mutationResultsFiles.clear();
            for (final File file : mutationResultsFiles) {
                this.addMutationResultsFile(file);
            }
            return this;
        }
        
        public Builder addMutationResultsFile(final File mutationResultsFile) {
            this.validateFile(mutationResultsFile);
            this.mutationResultsFiles.add(mutationResultsFile);
            return this;
        }
        
        public Builder sourceCodeDirectories(final List<File> sourceCodeDirectories) {
            this.sourceCodeDirectories.clear();
            for (final File file : sourceCodeDirectories) {
                this.addSourceCodeDirectory(file);
            }
            return this;
        }
        
        public Builder addSourceCodeDirectory(final File sourceCodeDirectory) {
            this.validateDirectory(sourceCodeDirectory);
            this.sourceCodeDirectories.add(sourceCodeDirectory);
            return this;
        }
        
        public Builder compiledCodeDirectories(final List<File> compiledCodeDirectories) {
            this.compiledCodeDirectories.clear();
            for (final File file : compiledCodeDirectories) {
                this.addCompiledCodeDirectory(file);
            }
            return this;
        }
        
        public Builder addCompiledCodeDirectory(final File compiledCodeDirectory) {
            this.validateDirectory(compiledCodeDirectory);
            this.compiledCodeDirectories.add(compiledCodeDirectory);
            return this;
        }
        
        public Set<File> getCompiledCodeDirectories() {
            return this.compiledCodeDirectories;
        }
        
        public Set<File> getLineCoverageFiles() {
            return this.lineCoverageFiles;
        }
        
        public Set<File> getMutationResultsFiles() {
            return this.mutationResultsFiles;
        }
        
        public Set<File> getSourceCodeDirectories() {
            return this.sourceCodeDirectories;
        }
        
        public ReportAggregator build() {
            this.validateState();
            return new ReportAggregator(this.resultOutputStrategy, this.lineCoverageFiles, this.mutationResultsFiles, this.sourceCodeDirectories, this.compiledCodeDirectories, null);
        }
        
        private void validateState() {
            if (this.resultOutputStrategy == null) {
                throw new IllegalStateException("Failed to build: the resultOutputStrategy has not been set");
            }
            if (this.lineCoverageFiles.isEmpty()) {
                throw new IllegalStateException("Failed to build: no lineCoverageFiles have been set");
            }
            if (this.mutationResultsFiles.isEmpty()) {
                throw new IllegalStateException("Failed to build: no mutationResultsFiles have been set");
            }
            if (this.sourceCodeDirectories.isEmpty()) {
                throw new IllegalStateException("Failed to build: no sourceCodeDirectories have been set");
            }
            if (this.compiledCodeDirectories.isEmpty()) {
                throw new IllegalStateException("Failed to build: no compiledCodeDirectories have been set");
            }
        }
        
        private void validateFile(final File file) {
            if (file == null) {
                throw new IllegalArgumentException("file is null");
            }
            if (!file.exists() || !file.isFile()) {
                throw new IllegalArgumentException(file.getAbsolutePath() + " does not exist or is not a file");
            }
        }
        
        private void validateDirectory(final File directory) {
            if (directory == null) {
                throw new IllegalArgumentException("directory is null");
            }
            if (!directory.exists() || !directory.isDirectory()) {
                throw new IllegalArgumentException(directory.getAbsolutePath() + " does not exist or is not a directory");
            }
        }
    }
}
