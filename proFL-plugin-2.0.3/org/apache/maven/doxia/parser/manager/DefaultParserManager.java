// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.parser.manager;

import org.apache.maven.doxia.parser.Parser;
import java.util.Map;

public class DefaultParserManager implements ParserManager
{
    private Map parsers;
    
    public Parser getParser(final String id) throws ParserNotFoundException {
        final Parser parser = this.parsers.get(id);
        if (parser == null) {
            throw new ParserNotFoundException("Cannot find parser with id = " + id);
        }
        return parser;
    }
}
