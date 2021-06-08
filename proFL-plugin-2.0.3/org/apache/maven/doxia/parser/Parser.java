// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.parser;

import org.apache.maven.doxia.sink.Sink;
import java.io.Reader;

public interface Parser
{
    public static final String ROLE = ((Parser$1.class$org$apache$maven$doxia$parser$Parser == null) ? (Parser$1.class$org$apache$maven$doxia$parser$Parser = Parser$1.class$("org.apache.maven.doxia.parser.Parser")) : Parser$1.class$org$apache$maven$doxia$parser$Parser).getName();
    public static final int UNKNOWN_TYPE = 0;
    public static final int TXT_TYPE = 1;
    public static final int XML_TYPE = 2;
    public static final int JUSTIFY_CENTER = 0;
    public static final int JUSTIFY_LEFT = 1;
    public static final int JUSTIFY_RIGHT = 2;
    
    void parse(final Reader p0, final Sink p1) throws ParseException;
    
    int getType();
}
