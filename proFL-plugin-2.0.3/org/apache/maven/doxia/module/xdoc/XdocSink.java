// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.xdoc;

import org.apache.maven.doxia.util.HtmlTools;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.html.HTML;
import java.io.Writer;
import org.apache.maven.doxia.util.LineBreaker;
import org.apache.maven.doxia.sink.AbstractXmlSink;

public class XdocSink extends AbstractXmlSink implements XdocMarkup
{
    protected LineBreaker out;
    protected StringBuffer buffer;
    protected boolean headFlag;
    protected boolean titleFlag;
    private boolean boxedFlag;
    private boolean verbatimFlag;
    private int[] cellJustif;
    private int cellCount;
    
    public XdocSink(final Writer writer) {
        this.buffer = new StringBuffer();
        this.out = new LineBreaker(writer);
    }
    
    protected void resetState() {
        this.headFlag = false;
        this.buffer = new StringBuffer();
        this.boxedFlag = false;
        this.verbatimFlag = false;
        this.cellJustif = null;
        this.cellCount = 0;
    }
    
    public void head() {
        this.resetState();
        this.headFlag = true;
        this.markup("<?xml version=\"1.0\" ?>" + XdocSink.EOL);
        this.writeStartTag(XdocSink.DOCUMENT_TAG);
        this.writeStartTag(XdocSink.PROPERTIES_TAG);
    }
    
    public void head_() {
        this.headFlag = false;
        this.writeEndTag(XdocSink.PROPERTIES_TAG);
    }
    
    public void title_() {
        if (this.buffer.length() > 0) {
            this.writeStartTag(HTML.Tag.TITLE);
            this.content(this.buffer.toString());
            this.writeEndTag(HTML.Tag.TITLE);
            this.buffer = new StringBuffer();
        }
    }
    
    public void author_() {
        if (this.buffer.length() > 0) {
            this.writeStartTag(XdocSink.AUTHOR_TAG);
            this.content(this.buffer.toString());
            this.writeEndTag(XdocSink.AUTHOR_TAG);
            this.buffer = new StringBuffer();
        }
    }
    
    public void date_() {
        if (this.buffer.length() > 0) {
            this.writeStartTag(XdocSink.DATE_TAG);
            this.content(this.buffer.toString());
            this.writeEndTag(XdocSink.DATE_TAG);
            this.buffer = new StringBuffer();
        }
    }
    
    public void body() {
        this.writeStartTag(HTML.Tag.BODY);
    }
    
    public void body_() {
        this.writeEndTag(HTML.Tag.BODY);
        this.writeEndTag(XdocSink.DOCUMENT_TAG);
        this.out.flush();
        this.resetState();
    }
    
    public void section1() {
        this.onSection(1);
    }
    
    public void sectionTitle1() {
        this.onSectionTitle(1);
    }
    
    public void sectionTitle1_() {
        this.onSectionTitle_(1);
    }
    
    public void section1_() {
        this.onSection_(1);
    }
    
    public void section2() {
        this.onSection(2);
    }
    
    public void sectionTitle2() {
        this.onSectionTitle(2);
    }
    
    public void sectionTitle2_() {
        this.onSectionTitle_(2);
    }
    
    public void section2_() {
        this.onSection_(2);
    }
    
    public void section3() {
        this.onSection(3);
    }
    
    public void sectionTitle3() {
        this.onSectionTitle(3);
    }
    
    public void sectionTitle3_() {
        this.onSectionTitle_(3);
    }
    
    public void section3_() {
        this.onSection_(3);
    }
    
    public void section4() {
        this.onSection(4);
    }
    
    public void sectionTitle4() {
        this.onSectionTitle(4);
    }
    
    public void sectionTitle4_() {
        this.onSectionTitle_(4);
    }
    
    public void section4_() {
        this.onSection_(4);
    }
    
    public void section5() {
        this.onSection(5);
    }
    
    public void sectionTitle5() {
        this.onSectionTitle(5);
    }
    
    public void sectionTitle5_() {
        this.onSectionTitle_(5);
    }
    
    public void section5_() {
        this.onSection_(5);
    }
    
    private void onSection(final int depth) {
        if (depth == 1) {
            this.markup(String.valueOf('<') + XdocSink.SECTION_TAG.toString() + String.valueOf(' ') + HTML.Attribute.NAME + String.valueOf('=') + String.valueOf('\"'));
        }
        else if (depth == 2) {
            this.markup(String.valueOf('<') + XdocSink.SUBSECTION_TAG.toString() + String.valueOf(' ') + HTML.Attribute.NAME + String.valueOf('=') + String.valueOf('\"'));
        }
    }
    
    private void onSectionTitle(final int depth) {
        if (depth == 3) {
            this.writeStartTag(HTML.Tag.H4);
        }
        else if (depth == 4) {
            this.writeStartTag(HTML.Tag.H5);
        }
        else if (depth == 5) {
            this.writeStartTag(HTML.Tag.H6);
        }
        this.titleFlag = true;
    }
    
    private void onSectionTitle_(final int depth) {
        if (depth == 1 || depth == 2) {
            this.markup(String.valueOf('\"') + String.valueOf('>'));
        }
        else if (depth == 3) {
            this.writeEndTag(HTML.Tag.H4);
        }
        else if (depth == 4) {
            this.writeEndTag(HTML.Tag.H5);
        }
        else if (depth == 5) {
            this.writeEndTag(HTML.Tag.H6);
        }
        this.titleFlag = false;
    }
    
    private void onSection_(final int depth) {
        if (depth == 1) {
            this.writeEndTag(XdocSink.SECTION_TAG);
        }
        else if (depth == 2) {
            this.writeEndTag(XdocSink.SUBSECTION_TAG);
        }
    }
    
    public void list() {
        this.writeStartTag(HTML.Tag.UL);
    }
    
    public void list_() {
        this.writeEndTag(HTML.Tag.UL);
    }
    
    public void listItem() {
        this.writeStartTag(HTML.Tag.LI);
    }
    
    public void listItem_() {
        this.writeEndTag(HTML.Tag.LI);
    }
    
    public void numberedList(final int numbering) {
        String style = null;
        switch (numbering) {
            case 2: {
                style = "upper-alpha";
                break;
            }
            case 1: {
                style = "lower-alpha";
                break;
            }
            case 4: {
                style = "upper-roman";
                break;
            }
            case 3: {
                style = "lower-roman";
                break;
            }
            default: {
                style = "decimal";
                break;
            }
        }
        final MutableAttributeSet att = new SimpleAttributeSet();
        att.addAttribute(HTML.Attribute.STYLE, "list-style-type: " + style);
        this.writeStartTag(HTML.Tag.OL, att);
    }
    
    public void numberedList_() {
        this.writeEndTag(HTML.Tag.OL);
    }
    
    public void numberedListItem() {
        this.writeStartTag(HTML.Tag.LI);
    }
    
    public void numberedListItem_() {
        this.writeEndTag(HTML.Tag.LI);
    }
    
    public void definitionList() {
        this.writeStartTag(HTML.Tag.DL);
    }
    
    public void definitionList_() {
        this.writeEndTag(HTML.Tag.DL);
    }
    
    public void definedTerm() {
        this.writeStartTag(HTML.Tag.DT);
    }
    
    public void definedTerm_() {
        this.writeEndTag(HTML.Tag.DT);
    }
    
    public void definition() {
        this.writeStartTag(HTML.Tag.DD);
    }
    
    public void definition_() {
        this.writeEndTag(HTML.Tag.DD);
    }
    
    public void figure() {
        this.markup(String.valueOf('<') + HTML.Tag.IMG);
    }
    
    public void figure_() {
        this.markup(String.valueOf(' ') + String.valueOf('/') + String.valueOf('>'));
    }
    
    public void figureGraphics(final String s) {
        this.markup(String.valueOf(' ') + HTML.Attribute.SRC + String.valueOf('=') + String.valueOf('\"') + s + String.valueOf('\"'));
    }
    
    public void figureCaption() {
        this.markup(String.valueOf(' ') + HTML.Attribute.ALT + String.valueOf('=') + String.valueOf('\"'));
    }
    
    public void figureCaption_() {
        this.markup(String.valueOf('\"'));
    }
    
    public void paragraph() {
        this.writeStartTag(HTML.Tag.P);
    }
    
    public void paragraph_() {
        this.writeEndTag(HTML.Tag.P);
    }
    
    public void verbatim(final boolean boxed) {
        this.verbatimFlag = true;
        this.boxedFlag = boxed;
        if (boxed) {
            this.writeStartTag(XdocSink.SOURCE_TAG);
        }
        else {
            this.writeStartTag(HTML.Tag.PRE);
        }
    }
    
    public void verbatim_() {
        if (this.boxedFlag) {
            this.writeEndTag(XdocSink.SOURCE_TAG);
        }
        else {
            this.writeEndTag(HTML.Tag.PRE);
        }
        this.verbatimFlag = false;
        this.boxedFlag = false;
    }
    
    public void horizontalRule() {
        this.writeSimpleTag(HTML.Tag.HR);
    }
    
    public void table() {
        final MutableAttributeSet att = new SimpleAttributeSet();
        att.addAttribute(HTML.Attribute.ALIGN, "center");
        this.writeStartTag(HTML.Tag.TABLE, att);
    }
    
    public void table_() {
        this.writeEndTag(HTML.Tag.TABLE);
    }
    
    public void tableRows(final int[] justification, final boolean grid) {
        final MutableAttributeSet att = new SimpleAttributeSet();
        att.addAttribute(HTML.Attribute.ALIGN, "center");
        att.addAttribute(HTML.Attribute.BORDER, grid ? "1" : "0");
        this.writeStartTag(HTML.Tag.TABLE, att);
    }
    
    public void tableRows_() {
        this.writeEndTag(HTML.Tag.TABLE);
    }
    
    public void tableRow() {
        final MutableAttributeSet att = new SimpleAttributeSet();
        att.addAttribute(HTML.Attribute.VALIGN, "top");
        this.writeStartTag(HTML.Tag.TR, att);
        this.cellCount = 0;
    }
    
    public void tableRow_() {
        this.writeEndTag(HTML.Tag.TR);
        this.cellCount = 0;
    }
    
    public void tableCell() {
        this.tableCell(false);
    }
    
    public void tableHeaderCell() {
        this.tableCell(true);
    }
    
    public void tableCell(final boolean headerRow) {
        String justif = null;
        if (this.cellJustif != null) {
            switch (this.cellJustif[this.cellCount]) {
                case 1: {
                    justif = "left";
                    break;
                }
                case 2: {
                    justif = "right";
                    break;
                }
                default: {
                    justif = "center";
                    break;
                }
            }
        }
        final HTML.Tag t = headerRow ? HTML.Tag.TH : HTML.Tag.TD;
        MutableAttributeSet att = null;
        if (justif != null) {
            att = new SimpleAttributeSet();
            att.addAttribute(HTML.Attribute.ALIGN, justif);
        }
        this.writeStartTag(t, att);
    }
    
    public void tableCell_() {
        this.tableCell_(false);
    }
    
    public void tableHeaderCell_() {
        this.tableCell_(true);
    }
    
    public void tableCell_(final boolean headerRow) {
        final HTML.Tag t = headerRow ? HTML.Tag.TH : HTML.Tag.TD;
        this.writeEndTag(t);
        ++this.cellCount;
    }
    
    public void tableCaption() {
        this.writeStartTag(HTML.Tag.P);
        this.writeStartTag(HTML.Tag.I);
    }
    
    public void tableCaption_() {
        this.writeEndTag(HTML.Tag.I);
        this.writeEndTag(HTML.Tag.P);
    }
    
    public void anchor(final String name) {
        if (!this.headFlag && !this.titleFlag) {
            final String id = HtmlTools.encodeId(name);
            final MutableAttributeSet att = new SimpleAttributeSet();
            att.addAttribute(HTML.Attribute.ID, id);
            att.addAttribute(HTML.Attribute.NAME, id);
            this.writeStartTag(HTML.Tag.A, att);
        }
    }
    
    public void anchor_() {
        if (!this.headFlag && !this.titleFlag) {
            this.writeEndTagWithoutEOL(HTML.Tag.A);
        }
    }
    
    public void link(final String name) {
        if (!this.headFlag && !this.titleFlag) {
            final MutableAttributeSet att = new SimpleAttributeSet();
            att.addAttribute(HTML.Attribute.HREF, name);
            this.writeStartTag(HTML.Tag.A, att);
        }
    }
    
    public void link_() {
        if (!this.headFlag && !this.titleFlag) {
            this.writeEndTagWithoutEOL(HTML.Tag.A);
        }
    }
    
    public void italic() {
        if (!this.headFlag && !this.titleFlag) {
            this.writeStartTag(HTML.Tag.I);
        }
    }
    
    public void italic_() {
        if (!this.headFlag && !this.titleFlag) {
            this.writeEndTagWithoutEOL(HTML.Tag.I);
        }
    }
    
    public void bold() {
        if (!this.headFlag && !this.titleFlag) {
            this.writeStartTag(HTML.Tag.B);
        }
    }
    
    public void bold_() {
        if (!this.headFlag && !this.titleFlag) {
            this.writeEndTagWithoutEOL(HTML.Tag.B);
        }
    }
    
    public void monospaced() {
        if (!this.headFlag && !this.titleFlag) {
            this.writeStartTag(HTML.Tag.TT);
        }
    }
    
    public void monospaced_() {
        if (!this.headFlag && !this.titleFlag) {
            this.writeEndTagWithoutEOL(HTML.Tag.TT);
        }
    }
    
    public void lineBreak() {
        if (this.headFlag || this.titleFlag) {
            this.buffer.append(XdocSink.EOL);
        }
        else {
            this.writeSimpleTag(HTML.Tag.BR);
        }
    }
    
    public void nonBreakingSpace() {
        if (this.headFlag || this.titleFlag) {
            this.buffer.append(' ');
        }
        else {
            this.markup("&#160;");
        }
    }
    
    public void text(final String text) {
        if (this.headFlag) {
            this.buffer.append(text);
        }
        else if (this.verbatimFlag) {
            this.verbatimContent(text);
        }
        else {
            this.content(text);
        }
    }
    
    protected void markup(final String text) {
        this.out.write(text, true);
    }
    
    protected void content(final String text) {
        this.out.write(escapeHTML(text), false);
    }
    
    protected void verbatimContent(final String text) {
        this.out.write(escapeHTML(text), true);
    }
    
    public static String escapeHTML(final String text) {
        return HtmlTools.escapeHTML(text);
    }
    
    public static String encodeURL(final String text) {
        return HtmlTools.encodeURL(text);
    }
    
    public void flush() {
        this.out.flush();
    }
    
    public void close() {
        this.out.close();
    }
    
    protected void write(final String text) {
        this.markup(text);
    }
}
