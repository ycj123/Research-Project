// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.surefire.suite.RunResult;

public final class SurefireHelper
{
    private SurefireHelper() {
        throw new IllegalAccessError("Utility class");
    }
    
    public static void reportExecution(final SurefireReportParameters reportParameters, final RunResult result, final Log log) throws MojoFailureException, MojoExecutionException {
        final boolean timeoutOrOtherFailure = result.isFailureOrTimeout();
        if (!timeoutOrOtherFailure) {
            if (result.getCompletedCount() == 0) {
                if (reportParameters.getFailIfNoTests() == null || !reportParameters.getFailIfNoTests()) {
                    return;
                }
                throw new MojoFailureException("No tests were executed!  (Set -DfailIfNoTests=false to ignore this error.)");
            }
            else if (result.isErrorFree()) {
                return;
            }
        }
        final String msg = timeoutOrOtherFailure ? "There was a timeout or other error in the fork" : ("There are test failures.\n\nPlease refer to " + reportParameters.getReportsDirectory() + " for the individual test results.");
        if (reportParameters.isTestFailureIgnore()) {
            log.error(msg);
            return;
        }
        if (result.isFailure()) {
            throw new MojoExecutionException(msg);
        }
        throw new MojoFailureException(msg);
    }
}
