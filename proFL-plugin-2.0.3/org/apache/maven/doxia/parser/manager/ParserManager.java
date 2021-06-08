// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.parser.manager;

import org.apache.maven.doxia.parser.Parser;

public interface ParserManager
{
    public static final String ROLE = ((ParserManager$1.class$org$apache$maven$doxia$parser$manager$ParserManager == null) ? (ParserManager$1.class$org$apache$maven$doxia$parser$manager$ParserManager = ParserManager$1.class$("org.apache.maven.doxia.parser.manager.ParserManager")) : ParserManager$1.class$org$apache$maven$doxia$parser$manager$ParserManager).getName();
    
    Parser getParser(final String p0) throws ParserNotFoundException;
}
