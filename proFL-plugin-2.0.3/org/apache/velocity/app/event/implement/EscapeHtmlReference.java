// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.event.implement;

import org.mudebug.prapr.reloc.commons.lang.StringEscapeUtils;

public class EscapeHtmlReference extends EscapeReference
{
    protected String escape(final Object text) {
        return StringEscapeUtils.escapeHtml(text.toString());
    }
    
    protected String getMatchAttribute() {
        return "eventhandler.escape.html.match";
    }
}
