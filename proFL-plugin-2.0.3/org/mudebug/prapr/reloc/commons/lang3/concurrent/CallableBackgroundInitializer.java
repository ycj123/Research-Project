// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.lang3.concurrent;

import org.mudebug.prapr.reloc.commons.lang3.Validate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;

public class CallableBackgroundInitializer<T> extends BackgroundInitializer<T>
{
    private final Callable<T> callable;
    
    public CallableBackgroundInitializer(final Callable<T> call) {
        this.checkCallable(call);
        this.callable = call;
    }
    
    public CallableBackgroundInitializer(final Callable<T> call, final ExecutorService exec) {
        super(exec);
        this.checkCallable(call);
        this.callable = call;
    }
    
    @Override
    protected T initialize() throws Exception {
        return this.callable.call();
    }
    
    private void checkCallable(final Callable<T> call) {
        Validate.isTrue(call != null, "Callable must not be null!", new Object[0]);
    }
}
