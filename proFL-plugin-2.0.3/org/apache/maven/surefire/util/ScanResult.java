// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.util;

import java.util.Properties;
import java.util.List;

public interface ScanResult
{
    int size();
    
    String getClassName(final int p0);
    
    TestsToRun applyFilter(final ScannerFilter p0, final ClassLoader p1);
    
    List getClassesSkippedByValidation(final ScannerFilter p0, final ClassLoader p1);
    
    void writeTo(final Properties p0);
}
