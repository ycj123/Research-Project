// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.xml.pull;

import org.xml.sax.SAXException;
import java.io.IOException;

public class XmlPullParserException extends RuntimeException
{
    public XmlPullParserException(final IOException e) {
        super(e);
    }
    
    public XmlPullParserException(final SAXException e) {
        super(e);
    }
    
    public XmlPullParserException(final String message) {
        super(message);
    }
}
