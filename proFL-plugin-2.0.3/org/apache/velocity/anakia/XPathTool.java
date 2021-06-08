// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.anakia;

import java.util.List;
import org.jdom.Element;
import org.jdom.Document;

public class XPathTool
{
    public NodeList applyTo(final String xpathSpec, final Document doc) {
        return new NodeList(XPathCache.getXPath(xpathSpec).applyTo(doc), false);
    }
    
    public NodeList applyTo(final String xpathSpec, final Element elem) {
        return new NodeList(XPathCache.getXPath(xpathSpec).applyTo(elem), false);
    }
    
    public NodeList applyTo(final String xpathSpec, final List nodeSet) {
        return new NodeList(XPathCache.getXPath(xpathSpec).applyTo(nodeSet), false);
    }
}
