// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

public class ClosureException extends RuntimeException
{
    private final Closure closure;
    
    public ClosureException(final Closure closure, final Throwable cause) {
        super("Exception thrown by call to closure: " + closure + " reaason: " + cause, cause);
        this.closure = closure;
    }
    
    public Closure getClosure() {
        return this.closure;
    }
}
