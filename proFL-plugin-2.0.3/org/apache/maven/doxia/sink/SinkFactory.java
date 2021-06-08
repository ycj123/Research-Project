// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.sink;

import java.io.OutputStream;
import java.io.IOException;
import java.io.File;

public interface SinkFactory
{
    public static final String ROLE = ((SinkFactory$1.class$org$apache$maven$doxia$sink$SinkFactory == null) ? (SinkFactory$1.class$org$apache$maven$doxia$sink$SinkFactory = SinkFactory$1.class$("org.apache.maven.doxia.sink.SinkFactory")) : SinkFactory$1.class$org$apache$maven$doxia$sink$SinkFactory).getName();
    
    Sink createSink(final File p0, final String p1) throws IOException;
    
    Sink createSink(final File p0, final String p1, final String p2) throws IOException;
    
    Sink createSink(final OutputStream p0) throws IOException;
    
    Sink createSink(final OutputStream p0, final String p1) throws IOException;
}
