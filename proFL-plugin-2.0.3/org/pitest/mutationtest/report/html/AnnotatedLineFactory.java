// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.report.html;

import org.pitest.coverage.ClassLine;
import org.pitest.functional.FCollection;
import java.util.List;
import org.pitest.util.StringUtil;
import org.pitest.functional.F;
import java.io.IOException;
import org.pitest.functional.FunctionalList;
import java.io.Reader;
import org.pitest.classinfo.ClassInfo;
import java.util.Collection;
import org.pitest.coverage.CoverageDatabase;
import org.pitest.mutationtest.MutationResult;
import org.pitest.functional.FunctionalIterable;

public class AnnotatedLineFactory
{
    private final FunctionalIterable<MutationResult> mutations;
    private final CoverageDatabase statistics;
    private final Collection<ClassInfo> classesInFile;
    
    public AnnotatedLineFactory(final FunctionalIterable<MutationResult> mutations, final CoverageDatabase statistics, final Collection<ClassInfo> classes) {
        this.mutations = mutations;
        this.statistics = statistics;
        this.classesInFile = classes;
    }
    
    public FunctionalList<Line> convert(final Reader source) throws IOException {
        try {
            final InputStreamLineIterable lines = new InputStreamLineIterable(source);
            return lines.map(this.stringToAnnotatedLine());
        }
        finally {
            source.close();
        }
    }
    
    private F<String, Line> stringToAnnotatedLine() {
        return new F<String, Line>() {
            private int lineNumber = 1;
            
            @Override
            public Line apply(final String a) {
                final Line l = new Line(this.lineNumber, StringUtil.escapeBasicHtmlChars(a), AnnotatedLineFactory.this.lineCovered(this.lineNumber), AnnotatedLineFactory.this.getMutationsForLine(this.lineNumber));
                ++this.lineNumber;
                return l;
            }
        };
    }
    
    private List<MutationResult> getMutationsForLine(final int lineNumber) {
        return this.mutations.filter(this.isAtLineNumber(lineNumber));
    }
    
    private F<MutationResult, Boolean> isAtLineNumber(final int lineNumber) {
        return new F<MutationResult, Boolean>() {
            @Override
            public Boolean apply(final MutationResult result) {
                return result.getDetails().getLineNumber() == lineNumber;
            }
        };
    }
    
    private LineStatus lineCovered(final int line) {
        if (!this.isCodeLine(line)) {
            return LineStatus.NotApplicable;
        }
        if (this.isLineCovered(line)) {
            return LineStatus.Covered;
        }
        return LineStatus.NotCovered;
    }
    
    private boolean isCodeLine(final int line) {
        final F<ClassInfo, Boolean> predicate = new F<ClassInfo, Boolean>() {
            @Override
            public Boolean apply(final ClassInfo a) {
                return a.isCodeLine(line);
            }
        };
        return FCollection.contains(this.classesInFile, predicate);
    }
    
    private boolean isLineCovered(final int line) {
        final F<ClassInfo, Boolean> predicate = new F<ClassInfo, Boolean>() {
            @Override
            public Boolean apply(final ClassInfo a) {
                return !AnnotatedLineFactory.this.statistics.getTestsForClassLine(new ClassLine(a.getName().asInternalName(), line)).isEmpty();
            }
        };
        return FCollection.contains(this.classesInFile, predicate);
    }
}
