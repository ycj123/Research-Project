// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.anakia;

import java.io.IOException;
import java.io.Writer;
import java.io.StringWriter;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class OutputWrapper extends XMLOutputter
{
    public OutputWrapper() {
    }
    
    public OutputWrapper(final Format f) {
        super(f);
    }
    
    public String outputString(final Element element, final boolean strip) {
        final StringWriter buff = new StringWriter();
        try {
            this.outputElementContent(element, (Writer)buff);
        }
        catch (IOException ex) {}
        return buff.toString();
    }
}
