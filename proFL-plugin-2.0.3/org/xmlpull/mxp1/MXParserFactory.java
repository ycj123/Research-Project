// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.mxp1;

import org.xmlpull.mxp1_serializer.MXSerializer;
import org.xmlpull.v1.XmlSerializer;
import org.xmlpull.v1.XmlPullParserException;
import java.util.Enumeration;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class MXParserFactory extends XmlPullParserFactory
{
    protected static boolean stringCachedParserAvailable;
    
    public XmlPullParser newPullParser() throws XmlPullParserException {
        XmlPullParser pp = null;
        if (MXParserFactory.stringCachedParserAvailable) {
            try {
                pp = new MXParserCachingStrings();
            }
            catch (Exception ex) {
                MXParserFactory.stringCachedParserAvailable = false;
            }
        }
        if (pp == null) {
            pp = new MXParser();
        }
        final Enumeration e = this.features.keys();
        while (e.hasMoreElements()) {
            final String key = e.nextElement();
            final Boolean value = this.features.get(key);
            if (value != null && value) {
                pp.setFeature(key, true);
            }
        }
        return pp;
    }
    
    public XmlSerializer newSerializer() throws XmlPullParserException {
        return new MXSerializer();
    }
    
    static {
        MXParserFactory.stringCachedParserAvailable = true;
    }
}
