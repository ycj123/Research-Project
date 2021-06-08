// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.event.implement;

import org.mudebug.prapr.reloc.commons.lang.StringEscapeUtils;

public class EscapeXmlReference extends EscapeReference
{
    protected String escape(final Object text) {
        return StringEscapeUtils.escapeXml(text.toString());
    }
    
    protected String getMatchAttribute() {
        return "eventhandler.escape.xml.match";
    }
}
