// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.parser;

import java.io.IOException;
import org.codehaus.plexus.util.xml.pull.XmlPullParser;
import org.apache.maven.doxia.macro.MacroExecutionException;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.codehaus.plexus.util.xml.pull.MXParser;
import org.apache.maven.doxia.sink.Sink;
import java.io.Reader;
import org.apache.maven.doxia.markup.XmlMarkup;

public abstract class AbstractXmlParser extends AbstractParser implements XmlMarkup
{
    public void parse(final Reader source, final Sink sink) throws ParseException {
        try {
            final XmlPullParser parser = new MXParser();
            parser.setInput(source);
            this.parseXml(parser, sink);
        }
        catch (XmlPullParserException ex) {
            throw new ParseException("Error parsing the model: " + ex.getMessage(), ex);
        }
        catch (MacroExecutionException ex2) {
            throw new ParseException("Macro execution failed: " + ex2.getMessage(), ex2);
        }
    }
    
    public final int getType() {
        return 2;
    }
    
    private void parseXml(final XmlPullParser parser, final Sink sink) throws XmlPullParserException, MacroExecutionException {
        int eventType = parser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                this.handleStartTag(parser, sink);
            }
            else if (eventType == 3) {
                this.handleEndTag(parser, sink);
            }
            else if (eventType == 4) {
                this.handleText(parser, sink);
            }
            else if (eventType != 5) {
                if (eventType != 9) {
                    if (eventType == 6) {}
                }
            }
            try {
                eventType = parser.next();
                continue;
            }
            catch (IOException io) {
                throw new XmlPullParserException("IOException: " + io.getMessage(), parser, io);
            }
            break;
        }
    }
    
    protected abstract void handleStartTag(final XmlPullParser p0, final Sink p1) throws XmlPullParserException, MacroExecutionException;
    
    protected abstract void handleEndTag(final XmlPullParser p0, final Sink p1) throws XmlPullParserException, MacroExecutionException;
    
    protected abstract void handleText(final XmlPullParser p0, final Sink p1) throws XmlPullParserException;
}
