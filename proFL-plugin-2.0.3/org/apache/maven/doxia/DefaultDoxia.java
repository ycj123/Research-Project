// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia;

import org.apache.maven.doxia.parser.ParseException;
import org.apache.maven.doxia.parser.manager.ParserNotFoundException;
import org.apache.maven.doxia.parser.Parser;
import org.apache.maven.doxia.sink.Sink;
import java.io.Reader;
import org.apache.maven.doxia.parser.manager.ParserManager;

public class DefaultDoxia implements Doxia
{
    private ParserManager parserManager;
    
    public void parse(final Reader source, final String parserId, final Sink sink) throws ParserNotFoundException, ParseException {
        final Parser parser = this.parserManager.getParser(parserId);
        parser.parse(source, sink);
    }
    
    public Parser getParser(final String parserId) throws ParserNotFoundException {
        return this.parserManager.getParser(parserId);
    }
}
