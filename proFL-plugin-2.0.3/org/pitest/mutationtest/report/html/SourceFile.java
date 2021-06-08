// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.report.html;

import java.util.List;

public class SourceFile
{
    private final String fileName;
    private final List<Line> lines;
    private final List<MutationGrouping> groups;
    
    public SourceFile(final String fileName, final List<Line> lines, final List<MutationGrouping> groups) {
        this.fileName = fileName;
        this.lines = lines;
        this.groups = groups;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public List<Line> getLines() {
        return this.lines;
    }
    
    public List<MutationGrouping> getGroups() {
        return this.groups;
    }
}
