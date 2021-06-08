// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.v1.wrapper.classic;

import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import org.xmlpull.v1.util.XmlPullUtil;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.wrapper.XmlPullParserWrapper;

public class StaticXmlPullParserWrapper extends XmlPullParserDelegate implements XmlPullParserWrapper
{
    public StaticXmlPullParserWrapper(final XmlPullParser pp) {
        super(pp);
    }
    
    public String getAttributeValue(final String name) {
        return XmlPullUtil.getAttributeValue(this.pp, name);
    }
    
    public String getRequiredAttributeValue(final String name) throws IOException, XmlPullParserException {
        return XmlPullUtil.getRequiredAttributeValue(this.pp, null, name);
    }
    
    public String getRequiredAttributeValue(final String namespace, final String name) throws IOException, XmlPullParserException {
        return XmlPullUtil.getRequiredAttributeValue(this.pp, namespace, name);
    }
    
    public String getRequiredElementText(final String namespace, final String name) throws IOException, XmlPullParserException {
        if (name == null) {
            throw new XmlPullParserException("name for element can not be null");
        }
        String text = null;
        this.nextStartTag(namespace, name);
        if (this.isNil()) {
            this.nextEndTag(namespace, name);
        }
        else {
            text = this.pp.nextText();
        }
        this.pp.require(3, namespace, name);
        return text;
    }
    
    public boolean isNil() throws IOException, XmlPullParserException {
        boolean result = false;
        final String value = this.pp.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
        if ("true".equals(value)) {
            result = true;
        }
        return result;
    }
    
    public String getPITarget() throws IllegalStateException {
        return XmlPullUtil.getPITarget(this.pp);
    }
    
    public String getPIData() throws IllegalStateException {
        return XmlPullUtil.getPIData(this.pp);
    }
    
    public boolean matches(final int type, final String namespace, final String name) throws XmlPullParserException {
        return XmlPullUtil.matches(this.pp, type, namespace, name);
    }
    
    public void nextStartTag() throws XmlPullParserException, IOException {
        if (this.pp.nextTag() != 2) {
            throw new XmlPullParserException("expected START_TAG and not " + this.pp.getPositionDescription());
        }
    }
    
    public void nextStartTag(final String name) throws XmlPullParserException, IOException {
        this.pp.nextTag();
        this.pp.require(2, null, name);
    }
    
    public void nextStartTag(final String namespace, final String name) throws XmlPullParserException, IOException {
        this.pp.nextTag();
        this.pp.require(2, namespace, name);
    }
    
    public void nextEndTag() throws XmlPullParserException, IOException {
        XmlPullUtil.nextEndTag(this.pp);
    }
    
    public void nextEndTag(final String name) throws XmlPullParserException, IOException {
        XmlPullUtil.nextEndTag(this.pp, null, name);
    }
    
    public void nextEndTag(final String namespace, final String name) throws XmlPullParserException, IOException {
        XmlPullUtil.nextEndTag(this.pp, namespace, name);
    }
    
    public String nextText(final String namespace, final String name) throws IOException, XmlPullParserException {
        return XmlPullUtil.nextText(this.pp, namespace, name);
    }
    
    public void skipSubTree() throws XmlPullParserException, IOException {
        XmlPullUtil.skipSubTree(this.pp);
    }
}
