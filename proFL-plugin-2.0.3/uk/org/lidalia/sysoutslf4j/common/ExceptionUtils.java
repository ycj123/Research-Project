// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.common;

import java.util.concurrent.Callable;
import java.lang.reflect.InvocationTargetException;
import java.io.InterruptedIOException;

public final class ExceptionUtils
{
    public static RuntimeException asRuntimeException(final Throwable throwable) {
        if (throwable == null) {
            throw new IllegalArgumentException("Throwable argument cannot be null");
        }
        if (throwable instanceof Error) {
            throw (Error)throwable;
        }
        RuntimeException result;
        if (throwable instanceof RuntimeException) {
            result = (RuntimeException)throwable;
        }
        else {
            if (throwable instanceof InterruptedException || throwable instanceof InterruptedIOException) {
                throw new IllegalArgumentException("An interrupted exception needs to be handled to end the thread, or the interrupted status needs to be restored, or the exception needs to be propagated explicitly - it should not be used as an argument to this method", throwable);
            }
            if (throwable instanceof InvocationTargetException) {
                result = asRuntimeException(throwable.getCause());
            }
            else {
                result = new WrappedCheckedException(throwable);
            }
        }
        return result;
    }
    
    public static <ResultType> ResultType doUnchecked(final Callable<ResultType> work) {
        try {
            return work.call();
        }
        catch (Exception e) {
            throw asRuntimeException(e);
        }
    }
    
    private ExceptionUtils() {
        throw new UnsupportedOperationException("Not instantiable");
    }
}
