// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.anakia;

import java.util.WeakHashMap;
import com.werken.xpath.XPath;
import java.util.Map;

class XPathCache
{
    private static final Map XPATH_CACHE;
    
    private XPathCache() {
    }
    
    static XPath getXPath(final String xpathString) {
        XPath xpath = null;
        synchronized (XPathCache.XPATH_CACHE) {
            xpath = XPathCache.XPATH_CACHE.get(xpathString);
            if (xpath == null) {
                xpath = new XPath(xpathString);
                XPathCache.XPATH_CACHE.put(xpathString, xpath);
            }
        }
        return xpath;
    }
    
    static {
        XPATH_CACHE = new WeakHashMap();
    }
}
