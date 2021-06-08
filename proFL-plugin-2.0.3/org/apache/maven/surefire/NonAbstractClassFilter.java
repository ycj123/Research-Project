// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire;

import java.lang.reflect.Modifier;
import org.apache.maven.surefire.util.ScannerFilter;

public class NonAbstractClassFilter implements ScannerFilter
{
    public boolean accept(final Class testClass) {
        return !Modifier.isAbstract(testClass.getModifiers());
    }
}
