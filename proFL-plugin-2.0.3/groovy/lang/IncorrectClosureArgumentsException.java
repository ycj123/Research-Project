// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.runtime.InvokerHelper;

public class IncorrectClosureArgumentsException extends GroovyRuntimeException
{
    private final Closure closure;
    private final Object arguments;
    private final Class[] expected;
    
    public IncorrectClosureArgumentsException(final Closure closure, final Object arguments, final Class[] expected) {
        super("Incorrect arguments to closure: " + closure + ". Expected: " + InvokerHelper.toString(expected) + ", actual: " + InvokerHelper.toString(arguments));
        this.closure = closure;
        this.arguments = arguments;
        this.expected = expected;
    }
    
    public Object getArguments() {
        return this.arguments;
    }
    
    public Closure getClosure() {
        return this.closure;
    }
    
    public Class[] getExpected() {
        return this.expected;
    }
}
