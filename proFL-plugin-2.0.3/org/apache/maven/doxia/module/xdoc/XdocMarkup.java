// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.xdoc;

import javax.swing.text.html.HTML;
import org.apache.maven.doxia.markup.XmlMarkup;

public interface XdocMarkup extends XmlMarkup
{
    public static final HTML.Tag AUTHOR_TAG = new HTML.Tag() {
        public String toString() {
            return "author";
        }
    };
    public static final HTML.Tag DATE_TAG = new HTML.Tag() {
        public String toString() {
            return "date";
        }
    };
    public static final HTML.Tag DOCUMENT_TAG = new HTML.Tag() {
        public String toString() {
            return "document";
        }
    };
    public static final HTML.Tag MACRO_TAG = new HTML.Tag() {
        public String toString() {
            return "macro";
        }
    };
    public static final HTML.Tag PROPERTIES_TAG = new HTML.Tag() {
        public String toString() {
            return "properties";
        }
    };
    public static final HTML.Tag SECTION_TAG = new HTML.Tag() {
        public String toString() {
            return "section";
        }
    };
    public static final HTML.Tag SOURCE_TAG = new HTML.Tag() {
        public String toString() {
            return "source";
        }
    };
    public static final HTML.Tag SUBSECTION_TAG = new HTML.Tag() {
        public String toString() {
            return "subsection";
        }
    };
}
