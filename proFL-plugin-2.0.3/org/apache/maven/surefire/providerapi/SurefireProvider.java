// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.providerapi;

import java.lang.reflect.InvocationTargetException;
import org.apache.maven.surefire.report.ReporterException;
import org.apache.maven.surefire.testset.TestSetFailedException;
import org.apache.maven.surefire.suite.RunResult;
import java.util.Iterator;

public interface SurefireProvider
{
    Iterator getSuites();
    
    RunResult invoke(final Object p0) throws TestSetFailedException, ReporterException, InvocationTargetException;
    
    void cancel();
}
