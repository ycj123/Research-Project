// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.DocumentBuilderFactory;
import java.security.PrivilegedActionException;
import javax.xml.parsers.ParserConfigurationException;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

public class FactorySupport
{
    static Object createFactory(final PrivilegedExceptionAction action) throws ParserConfigurationException {
        Object factory;
        try {
            factory = AccessController.doPrivileged((PrivilegedExceptionAction<Object>)action);
        }
        catch (PrivilegedActionException pae) {
            final Exception e = pae.getException();
            if (e instanceof ParserConfigurationException) {
                throw (ParserConfigurationException)e;
            }
            throw new RuntimeException(e);
        }
        return factory;
    }
    
    public static DocumentBuilderFactory createDocumentBuilderFactory() throws ParserConfigurationException {
        return (DocumentBuilderFactory)createFactory(new PrivilegedExceptionAction() {
            public Object run() throws ParserConfigurationException {
                return DocumentBuilderFactory.newInstance();
            }
        });
    }
    
    public static SAXParserFactory createSaxParserFactory() throws ParserConfigurationException {
        return (SAXParserFactory)createFactory(new PrivilegedExceptionAction() {
            public Object run() throws ParserConfigurationException {
                return SAXParserFactory.newInstance();
            }
        });
    }
}
