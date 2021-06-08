// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.sink;

import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.lang.reflect.Method;
import java.util.List;
import java.lang.reflect.InvocationHandler;

public class PipelineSink implements InvocationHandler
{
    private List pipeline;
    
    public PipelineSink(final List pipeline) {
        this.pipeline = pipeline;
    }
    
    public void addSink(final Sink sink) {
        this.pipeline.add(sink);
    }
    
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        for (final Sink sink : this.pipeline) {
            method.invoke(sink, args);
        }
        return null;
    }
    
    public static Sink newInstance(final List pipeline) {
        return (Sink)Proxy.newProxyInstance(PipelineSink.class.getClassLoader(), new Class[] { Sink.class }, new PipelineSink(pipeline));
    }
}
