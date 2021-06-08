// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.xml;

import java.io.PrintWriter;
import java.io.Writer;

public class Xpp3DomWriter
{
    public static void write(final Writer writer, final Xpp3Dom dom) {
        write(new PrettyPrintXMLWriter(writer), dom);
    }
    
    public static void write(final PrintWriter writer, final Xpp3Dom dom) {
        write(new PrettyPrintXMLWriter(writer), dom);
    }
    
    public static void write(final XMLWriter xmlWriter, final Xpp3Dom dom) {
        write(xmlWriter, dom, true);
    }
    
    public static void write(final XMLWriter xmlWriter, final Xpp3Dom dom, final boolean escape) {
        xmlWriter.startElement(dom.getName());
        final String[] arr$;
        final String[] attributeNames = arr$ = dom.getAttributeNames();
        for (final String attributeName : arr$) {
            xmlWriter.addAttribute(attributeName, dom.getAttribute(attributeName));
        }
        final Xpp3Dom[] arr$2;
        final Xpp3Dom[] children = arr$2 = dom.getChildren();
        for (final Xpp3Dom aChildren : arr$2) {
            write(xmlWriter, aChildren, escape);
        }
        final String value = dom.getValue();
        if (value != null) {
            if (escape) {
                xmlWriter.writeText(value);
            }
            else {
                xmlWriter.writeMarkup(value);
            }
        }
        xmlWriter.endElement();
    }
}
