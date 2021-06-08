// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform.powerassert;

import java.io.Writer;
import java.io.IOException;
import java.io.FileWriter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public abstract class AssertionVerifier
{
    public static final String VERIFY_METHOD_NAME = "verify";
    private static final String LOG_FILE;
    
    public static void verify(final Object truthValue, final String text, final ValueRecorder recorder) {
        if (AssertionVerifier.LOG_FILE != null) {
            log(text, recorder);
        }
        if (!DefaultTypeTransformation.castToBoolean(truthValue)) {
            final String msg = AssertionRenderer.render(text, recorder);
            final Error error = new PowerAssertionError(msg);
            filterStackTrace(error);
            throw error;
        }
    }
    
    private static void log(final String text, final ValueRecorder recorder) {
        Writer out = null;
        try {
            out = new FileWriter(AssertionVerifier.LOG_FILE, true);
            out.write(AssertionRenderer.render(text, recorder));
            out.write("\n\n");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
            }
            catch (IOException ex) {}
        }
    }
    
    private static void filterStackTrace(final Throwable error) {
        final StackTraceElement[] stackTrace = error.getStackTrace();
        for (int i = 0; i < stackTrace.length; ++i) {
            final StackTraceElement elem = stackTrace[i];
            if (!elem.getClassName().startsWith("org.codehaus.groovy.transform.powerassert") && !elem.getClassName().startsWith("org.codehaus.groovy.runtime.callsite")) {
                final StackTraceElement[] newStackTrace = new StackTraceElement[stackTrace.length - i];
                System.arraycopy(stackTrace, i, newStackTrace, 0, newStackTrace.length);
                error.setStackTrace(newStackTrace);
                return;
            }
        }
    }
    
    static {
        LOG_FILE = null;
    }
}
