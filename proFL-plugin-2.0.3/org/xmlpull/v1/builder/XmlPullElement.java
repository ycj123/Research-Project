// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.v1.builder;

import org.xmlpull.v1.XmlPullParser;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import java.util.Iterator;

public interface XmlPullElement extends XmlElement
{
    Iterator children();
    
    boolean fullyConstructed();
    
    XmlPullElement readNextChild() throws XmlPullParserException, IOException;
    
    boolean skipNextChild() throws XmlPullParserException, IOException;
    
    XmlPullParser nextChildAsPullParser() throws IOException, XmlPullParserException;
}
