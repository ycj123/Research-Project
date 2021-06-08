// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml.dom;

import java.io.OutputStream;
import groovy.xml.XmlUtil;
import org.w3c.dom.Element;

public class DOMUtil
{
    @Deprecated
    public static String serialize(final Element element) {
        return XmlUtil.serialize(element);
    }
    
    @Deprecated
    public static void serialize(final Element element, final OutputStream os) {
        XmlUtil.serialize(element, os);
    }
}
