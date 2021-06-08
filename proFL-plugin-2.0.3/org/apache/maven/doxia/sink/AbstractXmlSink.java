// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.sink;

import java.util.Enumeration;
import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import org.apache.maven.doxia.markup.XmlMarkup;

public abstract class AbstractXmlSink extends SinkAdapter implements XmlMarkup
{
    protected void writeStartTag(final HTML.Tag t) {
        this.writeStartTag(t, null);
    }
    
    protected void writeStartTag(final HTML.Tag t, final MutableAttributeSet att) {
        this.writeStartTag(t, att, false);
    }
    
    protected void writeStartTag(final HTML.Tag t, final MutableAttributeSet att, final boolean isSimpleTag) {
        if (t == null) {
            throw new IllegalArgumentException("A tag is required");
        }
        final StringBuffer sb = new StringBuffer();
        sb.append('<');
        sb.append(t.toString());
        if (att != null) {
            final Enumeration names = att.getAttributeNames();
            while (names.hasMoreElements()) {
                final Object key = names.nextElement();
                final Object value = att.getAttribute(key);
                if (value instanceof AttributeSet) {
                    continue;
                }
                sb.append(' ').append(key.toString()).append('=').append('\"').append(value.toString()).append('\"');
            }
        }
        if (isSimpleTag) {
            sb.append(' ').append('/');
        }
        sb.append('>');
        if (isSimpleTag) {
            sb.append(AbstractXmlSink.EOL);
        }
        this.write(sb.toString());
    }
    
    protected void writeEndTag(final HTML.Tag t) {
        this.writeEndTagWithoutEOL(t);
        this.write(AbstractXmlSink.EOL);
    }
    
    protected void writeEndTagWithoutEOL(final HTML.Tag t) {
        final StringBuffer sb = new StringBuffer();
        sb.append('<');
        sb.append('/');
        sb.append(t.toString());
        sb.append('>');
        this.write(sb.toString());
    }
    
    protected void writeSimpleTag(final HTML.Tag t) {
        this.writeSimpleTag(t, null);
    }
    
    protected void writeSimpleTag(final HTML.Tag t, final MutableAttributeSet att) {
        this.writeStartTag(t, att, true);
    }
    
    protected abstract void write(final String p0);
}
