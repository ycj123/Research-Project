// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.v1.wrapper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import org.xmlpull.v1.XmlSerializer;

public interface XmlSerializerWrapper extends XmlSerializer
{
    public static final String NO_NAMESPACE = "";
    public static final String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";
    
    String getCurrentNamespaceForElements();
    
    String setCurrentNamespaceForElements(final String p0);
    
    XmlSerializerWrapper attribute(final String p0, final String p1) throws IOException, IllegalArgumentException, IllegalStateException;
    
    XmlSerializerWrapper startTag(final String p0) throws IOException, IllegalArgumentException, IllegalStateException;
    
    XmlSerializerWrapper endTag(final String p0) throws IOException, IllegalArgumentException, IllegalStateException;
    
    XmlSerializerWrapper element(final String p0, final String p1, final String p2) throws IOException, XmlPullParserException;
    
    XmlSerializerWrapper element(final String p0, final String p1) throws IOException, XmlPullParserException;
    
    void fragment(final String p0) throws IOException, IllegalArgumentException, IllegalStateException, XmlPullParserException;
    
    void event(final XmlPullParser p0) throws IOException, IllegalArgumentException, IllegalStateException, XmlPullParserException;
    
    String escapeText(final String p0);
    
    String escapeAttributeValue(final String p0);
}
