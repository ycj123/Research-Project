// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

public class JmxBuilderException extends RuntimeException
{
    public JmxBuilderException() {
    }
    
    public JmxBuilderException(final String ex) {
        super(ex);
    }
    
    public JmxBuilderException(final Throwable ex) {
        super(ex);
    }
    
    public JmxBuilderException(final String msg, final Throwable ex) {
        super(msg, ex);
    }
}
