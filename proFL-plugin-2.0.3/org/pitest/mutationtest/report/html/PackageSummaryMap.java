// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.report.html;

import java.util.Collection;
import java.util.TreeMap;
import java.util.Map;

public class PackageSummaryMap
{
    private final Map<String, PackageSummaryData> packageSummaryData;
    
    public PackageSummaryMap() {
        this.packageSummaryData = new TreeMap<String, PackageSummaryData>();
    }
    
    private PackageSummaryData getPackageSummaryData(final String packageName) {
        PackageSummaryData psData;
        if (this.packageSummaryData.containsKey(packageName)) {
            psData = this.packageSummaryData.get(packageName);
        }
        else {
            psData = new PackageSummaryData(packageName);
            this.packageSummaryData.put(packageName, psData);
        }
        return psData;
    }
    
    public PackageSummaryData update(final String packageName, final MutationTestSummaryData data) {
        final PackageSummaryData psd = this.getPackageSummaryData(packageName);
        psd.addSummaryData(data);
        return psd;
    }
    
    public Collection<PackageSummaryData> values() {
        return this.packageSummaryData.values();
    }
}
