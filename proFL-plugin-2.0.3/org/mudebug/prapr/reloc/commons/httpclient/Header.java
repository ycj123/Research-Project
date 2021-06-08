// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

public class Header extends NameValuePair
{
    public Header() {
        this(null, null);
    }
    
    public Header(final String name, final String value) {
        super(name, value);
    }
    
    public String toExternalForm() {
        return ((null == this.getName()) ? "" : this.getName()) + ": " + ((null == this.getValue()) ? "" : this.getValue()) + "\r\n";
    }
    
    public String toString() {
        return this.toExternalForm();
    }
    
    public HeaderElement[] getValues() throws HttpException {
        return HeaderElement.parse(this.getValue());
    }
}
