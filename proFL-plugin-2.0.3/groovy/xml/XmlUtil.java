// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerFactory;
import java.io.UnsupportedEncodingException;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.util.XmlNodePrinter;
import java.io.PrintWriter;
import groovy.lang.Writable;
import groovy.util.slurpersupport.GPathResult;
import java.io.OutputStream;
import java.io.Writer;
import javax.xml.transform.Source;
import org.w3c.dom.Node;
import javax.xml.transform.dom.DOMSource;
import java.io.StringWriter;
import org.w3c.dom.Element;

public class XmlUtil
{
    public static String serialize(final Element element) {
        final StringWriter sw = new StringWriter();
        serialize(new DOMSource(element), sw);
        return sw.toString();
    }
    
    public static void serialize(final Element element, final OutputStream os) {
        final Source source = new DOMSource(element);
        serialize(source, os);
    }
    
    public static void serialize(final Element element, final Writer w) {
        final Source source = new DOMSource(element);
        serialize(source, w);
    }
    
    public static String serialize(final groovy.util.Node node) {
        return serialize(asString(node));
    }
    
    public static void serialize(final groovy.util.Node node, final OutputStream os) {
        serialize(asString(node), os);
    }
    
    public static void serialize(final groovy.util.Node node, final Writer w) {
        serialize(asString(node), w);
    }
    
    public static String serialize(final GPathResult node) {
        return serialize(asString(node));
    }
    
    public static void serialize(final GPathResult node, final OutputStream os) {
        serialize(asString(node), os);
    }
    
    public static void serialize(final GPathResult node, final Writer w) {
        serialize(asString(node), w);
    }
    
    public static String serialize(final Writable writable) {
        return serialize(asString(writable));
    }
    
    public static void serialize(final Writable writable, final OutputStream os) {
        serialize(asString(writable), os);
    }
    
    public static void serialize(final Writable writable, final Writer w) {
        serialize(asString(writable), w);
    }
    
    public static String serialize(final String xmlString) {
        final StringWriter sw = new StringWriter();
        serialize(asStreamSource(xmlString), sw);
        return sw.toString();
    }
    
    public static void serialize(final String xmlString, final OutputStream os) {
        serialize(asStreamSource(xmlString), os);
    }
    
    public static void serialize(final String xmlString, final Writer w) {
        serialize(asStreamSource(xmlString), w);
    }
    
    private static String asString(final groovy.util.Node node) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        final XmlNodePrinter nodePrinter = new XmlNodePrinter(pw);
        nodePrinter.setPreserveWhitespace(true);
        nodePrinter.print(node);
        return sw.toString();
    }
    
    private static String asString(final GPathResult node) {
        try {
            final Object builder = Class.forName("groovy.xml.StreamingMarkupBuilder").newInstance();
            final Writable w = (Writable)InvokerHelper.invokeMethod(builder, "bindNode", node);
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + w.toString();
        }
        catch (Exception e) {
            return "Couldn't convert node to string because: " + e.getMessage();
        }
    }
    
    private static String asString(final Writable writable) {
        if (writable instanceof GPathResult) {
            return asString((GPathResult)writable);
        }
        final Writer sw = new StringWriter();
        try {
            writable.writeTo(sw);
        }
        catch (IOException ex) {}
        return sw.toString();
    }
    
    private static StreamSource asStreamSource(final String xmlString) {
        return new StreamSource(new StringReader(xmlString));
    }
    
    private static void serialize(final Source source, final OutputStream os) {
        try {
            serialize(source, new StreamResult(new OutputStreamWriter(os, "UTF-8")));
        }
        catch (UnsupportedEncodingException ex) {}
    }
    
    private static void serialize(final Source source, final Writer w) {
        serialize(source, new StreamResult(w));
    }
    
    private static void serialize(final Source source, final StreamResult target) {
        final TransformerFactory factory = TransformerFactory.newInstance();
        setIndent(factory, 2);
        try {
            final Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("method", "xml");
            transformer.setOutputProperty("media-type", "text/xml");
            transformer.transform(source, target);
        }
        catch (TransformerException ex) {}
    }
    
    private static void setIndent(final TransformerFactory factory, final int indent) {
        try {
            factory.setAttribute("indent-number", indent);
        }
        catch (IllegalArgumentException ex) {}
    }
}
