// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.util;

public interface DirectoryScanner
{
    TestsToRun locateTestClasses(final ClassLoader p0, final ScannerFilter p1);
}
