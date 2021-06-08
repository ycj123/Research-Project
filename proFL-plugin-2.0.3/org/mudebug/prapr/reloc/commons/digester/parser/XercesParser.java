// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.parser;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import java.util.Properties;
import java.lang.reflect.Method;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class XercesParser
{
    protected static Log log;
    private static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    protected static String JAXP_SCHEMA_LANGUAGE;
    protected static String XERCES_DYNAMIC;
    protected static String XERCES_SCHEMA;
    protected static float version;
    protected static String versionNumber;
    
    private static String getXercesVersion() {
        String versionNumber = "1.0";
        try {
            final Class versionClass = Class.forName("org.apache.xerces.impl.Version");
            final Method method = versionClass.getMethod("getVersion", (Class[])null);
            final String version = (String)method.invoke(null, (Object[])null);
            versionNumber = version.substring("Xerces-J".length(), version.lastIndexOf("."));
        }
        catch (Exception ex) {}
        return versionNumber;
    }
    
    public static SAXParser newSAXParser(final Properties properties) throws ParserConfigurationException, SAXException, SAXNotSupportedException {
        final SAXParserFactory factory = (SAXParserFactory)properties.get("SAXParserFactory");
        if (XercesParser.versionNumber == null) {
            XercesParser.versionNumber = getXercesVersion();
            XercesParser.version = new Float(XercesParser.versionNumber);
        }
        if (XercesParser.version > 2.1) {
            configureXerces(factory);
            return factory.newSAXParser();
        }
        final SAXParser parser = factory.newSAXParser();
        configureOldXerces(parser, properties);
        return parser;
    }
    
    private static void configureOldXerces(final SAXParser parser, final Properties properties) throws ParserConfigurationException, SAXNotSupportedException {
        final String schemaLocation = (String)properties.get("schemaLocation");
        final String schemaLanguage = (String)properties.get("schemaLanguage");
        try {
            if (schemaLocation != null) {
                parser.setProperty(XercesParser.JAXP_SCHEMA_LANGUAGE, schemaLanguage);
                parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", schemaLocation);
            }
        }
        catch (SAXNotRecognizedException e) {
            XercesParser.log.info(parser.getClass().getName() + ": " + e.getMessage() + " not supported.");
        }
    }
    
    private static void configureXerces(final SAXParserFactory factory) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        factory.setFeature(XercesParser.XERCES_DYNAMIC, true);
        factory.setFeature(XercesParser.XERCES_SCHEMA, true);
    }
    
    static {
        XercesParser.log = LogFactory.getLog("org.mudebug.prapr.reloc.commons.digester.Digester.sax");
        XercesParser.JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
        XercesParser.XERCES_DYNAMIC = "http://apache.org/xml/features/validation/dynamic";
        XercesParser.XERCES_SCHEMA = "http://apache.org/xml/features/validation/schema";
        XercesParser.versionNumber = null;
    }
}
