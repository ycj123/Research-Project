// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.usability.diagnostics;

public final class DiagnosisUtils
{
    private DiagnosisUtils() {
    }
    
    public static boolean containsInCausality(final Throwable error, final Class test) {
        for (Throwable cause = error; cause != null; cause = cause.getCause()) {
            if (test.isInstance(cause)) {
                return true;
            }
        }
        return false;
    }
    
    public static Throwable getRootCause(final Throwable error) {
        Throwable cause = error;
        while (true) {
            final Throwable nextCause = cause.getCause();
            if (nextCause == null) {
                break;
            }
            cause = nextCause;
        }
        return cause;
    }
    
    public static Throwable getFromCausality(final Throwable error, final Class targetClass) {
        for (Throwable cause = error; cause != null; cause = cause.getCause()) {
            if (targetClass.isInstance(cause)) {
                return cause;
            }
        }
        return null;
    }
    
    public static void appendRootCauseIfPresentAndUnique(final Throwable error, final StringBuffer message, final boolean includeTypeInfo) {
        if (error == null) {
            return;
        }
        final Throwable root = getRootCause(error);
        if (root != null && !root.equals(error)) {
            final String rootMsg = root.getMessage();
            if (rootMsg != null && (error.getMessage() == null || error.getMessage().indexOf(rootMsg) < 0)) {
                message.append("\n").append(rootMsg);
                if (includeTypeInfo) {
                    message.append("\nRoot error type: ").append(root.getClass().getName());
                }
            }
        }
    }
}
