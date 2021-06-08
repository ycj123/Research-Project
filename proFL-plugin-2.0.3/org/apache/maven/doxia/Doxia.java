// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia;

import org.apache.maven.doxia.parser.Parser;
import org.apache.maven.doxia.parser.ParseException;
import org.apache.maven.doxia.parser.manager.ParserNotFoundException;
import org.apache.maven.doxia.sink.Sink;
import java.io.Reader;

public interface Doxia
{
    public static final String ROLE = ((Doxia$1.class$org$apache$maven$doxia$Doxia == null) ? (Doxia$1.class$org$apache$maven$doxia$Doxia = Doxia$1.class$("org.apache.maven.doxia.Doxia")) : Doxia$1.class$org$apache$maven$doxia$Doxia).getName();
    
    void parse(final Reader p0, final String p1, final Sink p2) throws ParserNotFoundException, ParseException;
    
    Parser getParser(final String p0) throws ParserNotFoundException;
}
