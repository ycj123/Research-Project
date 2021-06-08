// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.report.html;

import java.util.Comparator;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class PackageSummaryData implements Comparable<PackageSummaryData>
{
    private final String packageName;
    private final Map<String, MutationTestSummaryData> fileNameToSummaryData;
    
    public PackageSummaryData(final String packageName) {
        this.fileNameToSummaryData = new HashMap<String, MutationTestSummaryData>();
        this.packageName = packageName;
    }
    
    public void addSummaryData(final MutationTestSummaryData data) {
        final MutationTestSummaryData existing = this.fileNameToSummaryData.get(data.getFileName());
        if (existing == null) {
            this.fileNameToSummaryData.put(data.getFileName(), data);
        }
        else {
            existing.add(data);
        }
    }
    
    public MutationTestSummaryData getForSourceFile(final String filename) {
        return this.fileNameToSummaryData.get(filename);
    }
    
    public MutationTotals getTotals() {
        final MutationTotals mt = new MutationTotals();
        for (final MutationTestSummaryData each : this.fileNameToSummaryData.values()) {
            mt.add(each.getTotals());
        }
        return mt;
    }
    
    public String getPackageName() {
        return this.packageName;
    }
    
    public String getPackageDirectory() {
        return this.packageName;
    }
    
    public List<MutationTestSummaryData> getSummaryData() {
        final ArrayList<MutationTestSummaryData> values = new ArrayList<MutationTestSummaryData>(this.fileNameToSummaryData.values());
        Collections.sort(values, new MutationTestSummaryDataFileNameComparator());
        return values;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + ((this.packageName == null) ? 0 : this.packageName.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final PackageSummaryData other = (PackageSummaryData)obj;
        if (this.packageName == null) {
            if (other.packageName != null) {
                return false;
            }
        }
        else if (!this.packageName.equals(other.packageName)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(final PackageSummaryData arg0) {
        return this.packageName.compareTo(arg0.packageName);
    }
}
