// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

public interface JmxEventEmitterMBean
{
    String getEvent();
    
    void setEvent(final String p0);
    
    long send(final Object p0);
}
