// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.anakia;

import java.util.List;
import org.jdom.Namespace;
import org.jdom.output.XMLOutputter;
import org.jdom.Element;

public class AnakiaElement extends Element
{
    private static final long serialVersionUID = 8429597252274491314L;
    private static final XMLOutputter DEFAULT_OUTPUTTER;
    
    public AnakiaElement(final String name, final Namespace namespace) {
        super(name, namespace);
    }
    
    public AnakiaElement(final String name) {
        super(name);
    }
    
    public AnakiaElement(final String name, final String uri) {
        super(name, uri);
    }
    
    public AnakiaElement(final String name, final String prefix, final String uri) {
        super(name, prefix, uri);
    }
    
    public NodeList selectNodes(final String xpathExpression) {
        return new NodeList(XPathCache.getXPath(xpathExpression).applyTo((Element)this), false);
    }
    
    public String toString() {
        return AnakiaElement.DEFAULT_OUTPUTTER.outputString((Element)this);
    }
    
    public List getContent() {
        return new NodeList(super.getContent(), false);
    }
    
    public List getChildren() {
        return new NodeList(super.getChildren(), false);
    }
    
    public List getChildren(final String name) {
        return new NodeList(super.getChildren(name));
    }
    
    public List getChildren(final String name, final Namespace ns) {
        return new NodeList(super.getChildren(name, ns));
    }
    
    public List getAttributes() {
        return new NodeList(super.getAttributes());
    }
    
    static {
        DEFAULT_OUTPUTTER = new XMLOutputter();
        AnakiaElement.DEFAULT_OUTPUTTER.getFormat().setLineSeparator(System.getProperty("line.separator"));
    }
}
