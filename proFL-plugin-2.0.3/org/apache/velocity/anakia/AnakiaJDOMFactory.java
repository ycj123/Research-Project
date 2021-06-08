// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.anakia;

import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.DefaultJDOMFactory;

public class AnakiaJDOMFactory extends DefaultJDOMFactory
{
    public Element element(final String name, final Namespace namespace) {
        return new AnakiaElement(name, namespace);
    }
    
    public Element element(final String name) {
        return new AnakiaElement(name);
    }
    
    public Element element(final String name, final String uri) {
        return new AnakiaElement(name, uri);
    }
    
    public Element element(final String name, final String prefix, final String uri) {
        return new AnakiaElement(name, prefix, uri);
    }
}
