// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.parser;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXNotRecognizedException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import java.util.Properties;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class GenericParser
{
    protected static Log log;
    private static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    protected static String JAXP_SCHEMA_LANGUAGE;
    
    public static SAXParser newSAXParser(final Properties properties) throws ParserConfigurationException, SAXException, SAXNotRecognizedException {
        final SAXParserFactory factory = (SAXParserFactory)properties.get("SAXParserFactory");
        final SAXParser parser = factory.newSAXParser();
        final String schemaLocation = (String)properties.get("schemaLocation");
        final String schemaLanguage = (String)properties.get("schemaLanguage");
        try {
            if (schemaLocation != null) {
                parser.setProperty(GenericParser.JAXP_SCHEMA_LANGUAGE, schemaLanguage);
                parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", schemaLocation);
            }
        }
        catch (SAXNotRecognizedException e) {
            GenericParser.log.info(parser.getClass().getName() + ": " + e.getMessage() + " not supported.");
        }
        return parser;
    }
    
    static {
        GenericParser.log = LogFactory.getLog("org.mudebug.prapr.reloc.commons.digester.Digester.sax");
        GenericParser.JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    }
}
