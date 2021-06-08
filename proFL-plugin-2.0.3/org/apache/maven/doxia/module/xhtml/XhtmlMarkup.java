// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.xhtml;

import javax.swing.text.html.HTML;
import org.apache.maven.doxia.markup.XmlMarkup;

public interface XhtmlMarkup extends XmlMarkup
{
    public static final HTML.Tag TBODY_TAG = new HTML.Tag() {
        public String toString() {
            return "tbody";
        }
    };
}
