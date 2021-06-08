// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.xhtml;

import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.doxia.sink.StructureSink;
import org.apache.maven.doxia.util.HtmlTools;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.html.HTML;
import java.util.Map;
import java.io.Writer;
import org.apache.maven.doxia.module.xhtml.decoration.render.RenderingContext;
import java.io.PrintWriter;
import org.apache.maven.doxia.sink.AbstractXmlSink;

public class XhtmlSink extends AbstractXmlSink implements XhtmlMarkup
{
    private StringBuffer buffer;
    private boolean headFlag;
    private boolean itemFlag;
    private boolean verbatimFlag;
    private int cellCount;
    private PrintWriter writer;
    private RenderingContext renderingContext;
    private int[] cellJustif;
    private int rowMarker;
    
    public XhtmlSink(final Writer writer) {
        this(writer, null);
    }
    
    public XhtmlSink(final Writer writer, final RenderingContext renderingContext) {
        this.buffer = new StringBuffer();
        this.rowMarker = 0;
        this.writer = new PrintWriter(writer);
        this.renderingContext = renderingContext;
    }
    
    public XhtmlSink(final Writer writer, final RenderingContext renderingContext, final Map directives) {
        this.buffer = new StringBuffer();
        this.rowMarker = 0;
        this.writer = new PrintWriter(writer);
        this.renderingContext = renderingContext;
    }
    
    protected StringBuffer getBuffer() {
        return this.buffer;
    }
    
    protected void setHeadFlag(final boolean headFlag) {
        this.headFlag = headFlag;
    }
    
    protected void resetState() {
        this.headFlag = false;
        this.resetBuffer();
        this.itemFlag = false;
        this.verbatimFlag = false;
        this.cellCount = 0;
    }
    
    protected void resetBuffer() {
        this.buffer = new StringBuffer();
    }
    
    public void head() {
        this.resetState();
        this.headFlag = true;
        this.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        this.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        this.writeStartTag(HTML.Tag.HEAD);
    }
    
    public void head_() {
        this.headFlag = false;
        this.writeEndTag(HTML.Tag.HEAD);
    }
    
    public void title() {
        this.writeStartTag(HTML.Tag.TITLE);
    }
    
    public void title_() {
        this.write(this.buffer.toString());
        this.writeEndTag(HTML.Tag.TITLE);
        this.resetBuffer();
    }
    
    public void author_() {
        if (this.buffer.length() > 0) {
            final MutableAttributeSet att = new SimpleAttributeSet();
            att.addAttribute(HTML.Attribute.NAME, "author");
            att.addAttribute(HTML.Attribute.CONTENT, this.buffer.toString());
            this.writeSimpleTag(HTML.Tag.META, att);
            this.resetBuffer();
        }
    }
    
    public void date_() {
        if (this.buffer.length() > 0) {
            final MutableAttributeSet att = new SimpleAttributeSet();
            att.addAttribute(HTML.Attribute.NAME, "date");
            att.addAttribute(HTML.Attribute.CONTENT, this.buffer.toString());
            this.writeSimpleTag(HTML.Tag.META, att);
            this.resetBuffer();
        }
    }
    
    public void body() {
        this.writeStartTag(HTML.Tag.BODY);
    }
    
    public void body_() {
        this.writeEndTag(HTML.Tag.BODY);
        this.writeEndTag(HTML.Tag.HTML);
    }
    
    public void section1() {
        final MutableAttributeSet att = new SimpleAttributeSet();
        att.addAttribute(HTML.Attribute.CLASS, "section");
        this.writeStartTag(HTML.Tag.DIV, att);
    }
    
    public void section2() {
        final MutableAttributeSet att = new SimpleAttributeSet();
        att.addAttribute(HTML.Attribute.CLASS, "section");
        this.writeStartTag(HTML.Tag.DIV, att);
    }
    
    public void section3() {
        final MutableAttributeSet att = new SimpleAttributeSet();
        att.addAttribute(HTML.Attribute.CLASS, "section");
        this.writeStartTag(HTML.Tag.DIV, att);
    }
    
    public void section4() {
        final MutableAttributeSet att = new SimpleAttributeSet();
        att.addAttribute(HTML.Attribute.CLASS, "section");
        this.writeStartTag(HTML.Tag.DIV, att);
    }
    
    public void section5() {
        final MutableAttributeSet att = new SimpleAttributeSet();
        att.addAttribute(HTML.Attribute.CLASS, "section");
        this.writeStartTag(HTML.Tag.DIV, att);
    }
    
    public void section1_() {
        this.writeEndTag(HTML.Tag.DIV);
    }
    
    public void section2_() {
        this.writeEndTag(HTML.Tag.DIV);
    }
    
    public void section3_() {
        this.writeEndTag(HTML.Tag.DIV);
    }
    
    public void section4_() {
        this.writeEndTag(HTML.Tag.DIV);
    }
    
    public void section5_() {
        this.writeEndTag(HTML.Tag.DIV);
    }
    
    public void sectionTitle1() {
        this.writeStartTag(HTML.Tag.H2);
    }
    
    public void sectionTitle1_() {
        this.writeEndTag(HTML.Tag.H2);
    }
    
    public void sectionTitle2() {
        this.writeStartTag(HTML.Tag.H3);
    }
    
    public void sectionTitle2_() {
        this.writeEndTag(HTML.Tag.H3);
    }
    
    public void sectionTitle3() {
        this.writeStartTag(HTML.Tag.H4);
    }
    
    public void sectionTitle3_() {
        this.writeEndTag(HTML.Tag.H4);
    }
    
    public void sectionTitle4() {
        this.writeStartTag(HTML.Tag.H5);
    }
    
    public void sectionTitle4_() {
        this.writeEndTag(HTML.Tag.H5);
    }
    
    public void sectionTitle5() {
        this.writeStartTag(HTML.Tag.H6);
    }
    
    public void sectionTitle5_() {
        this.writeEndTag(HTML.Tag.H6);
    }
    
    public void list() {
        this.writeStartTag(HTML.Tag.UL);
    }
    
    public void list_() {
        this.writeEndTag(HTML.Tag.UL);
        this.itemFlag = false;
    }
    
    public void listItem() {
        this.writeStartTag(HTML.Tag.LI);
        this.itemFlag = true;
    }
    
    public void listItem_() {
        this.writeEndTag(HTML.Tag.LI);
    }
    
    public void numberedList(final int numbering) {
        String type = null;
        switch (numbering) {
            case 1: {
                type = "a";
                break;
            }
            case 2: {
                type = "A";
                break;
            }
            case 3: {
                type = "i";
                break;
            }
            case 4: {
                type = "I";
                break;
            }
            default: {
                type = "1";
                break;
            }
        }
        final MutableAttributeSet att = new SimpleAttributeSet();
        att.addAttribute(HTML.Attribute.TYPE, type);
        this.writeStartTag(HTML.Tag.OL, att);
    }
    
    public void numberedList_() {
        this.writeEndTag(HTML.Tag.OL);
        this.itemFlag = false;
    }
    
    public void numberedListItem() {
        this.writeStartTag(HTML.Tag.LI);
        this.itemFlag = true;
    }
    
    public void numberedListItem_() {
        this.writeEndTag(HTML.Tag.LI);
    }
    
    public void definitionList() {
        this.writeStartTag(HTML.Tag.DL);
    }
    
    public void definitionList_() {
        this.writeEndTag(HTML.Tag.DL);
        this.itemFlag = false;
    }
    
    public void definedTerm() {
        this.writeStartTag(HTML.Tag.DT);
    }
    
    public void definedTerm_() {
        this.writeEndTag(HTML.Tag.DT);
    }
    
    public void definition() {
        this.writeStartTag(HTML.Tag.DD);
        this.itemFlag = true;
    }
    
    public void definition_() {
        this.writeEndTag(HTML.Tag.DD);
    }
    
    public void paragraph() {
        if (!this.itemFlag) {
            this.writeStartTag(HTML.Tag.P);
        }
    }
    
    public void paragraph_() {
        if (this.itemFlag) {
            this.itemFlag = false;
        }
        else {
            this.writeEndTag(HTML.Tag.P);
        }
    }
    
    public void verbatim(final boolean boxed) {
        this.verbatimFlag = true;
        final MutableAttributeSet att = new SimpleAttributeSet();
        att.addAttribute(HTML.Attribute.CLASS, "source");
        this.writeStartTag(HTML.Tag.DIV, att);
        this.writeStartTag(HTML.Tag.PRE);
    }
    
    public void verbatim_() {
        this.writeEndTag(HTML.Tag.PRE);
        this.writeEndTag(HTML.Tag.DIV);
        this.verbatimFlag = false;
    }
    
    public void horizontalRule() {
        this.writeSimpleTag(HTML.Tag.HR);
    }
    
    public void table() {
        final MutableAttributeSet att = new SimpleAttributeSet();
        att.addAttribute(HTML.Attribute.CLASS, "bodyTable");
        this.writeStartTag(HTML.Tag.TABLE, att);
    }
    
    public void table_() {
        this.writeEndTag(HTML.Tag.TABLE);
    }
    
    public void tableRows(final int[] justification, final boolean grid) {
        this.writeStartTag(XhtmlSink.TBODY_TAG);
        this.cellJustif = justification;
    }
    
    public void tableRows_() {
        this.writeEndTag(XhtmlSink.TBODY_TAG);
        this.cellJustif = null;
    }
    
    public void tableRow() {
        if (this.rowMarker == 0) {
            final MutableAttributeSet att = new SimpleAttributeSet();
            att.addAttribute(HTML.Attribute.CLASS, "a");
            this.writeStartTag(HTML.Tag.TR, att);
            this.rowMarker = 1;
        }
        else {
            final MutableAttributeSet att = new SimpleAttributeSet();
            att.addAttribute(HTML.Attribute.CLASS, "b");
            this.writeStartTag(HTML.Tag.TR, att);
            this.rowMarker = 0;
        }
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
                case 0: {
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
    
    public void tableCell(final String width) {
        this.tableCell(false, width);
    }
    
    public void tableHeaderCell(final String width) {
        this.tableCell(true, width);
    }
    
    public void tableCell(final boolean headerRow, final String width) {
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
                case 0: {
                    justif = "center";
                    break;
                }
            }
        }
        final HTML.Tag t = headerRow ? HTML.Tag.TH : HTML.Tag.TD;
        final MutableAttributeSet att = new SimpleAttributeSet();
        if (width != null) {
            att.addAttribute(HTML.Attribute.WIDTH, width);
        }
        if (justif != null) {
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
        this.writeStartTag(HTML.Tag.CAPTION);
    }
    
    public void tableCaption_() {
        this.writeEndTag(HTML.Tag.CAPTION);
    }
    
    public void figure() {
        this.write(String.valueOf('<') + HTML.Tag.IMG);
    }
    
    public void figure_() {
        this.write(String.valueOf(' ') + String.valueOf('/') + String.valueOf('>'));
    }
    
    public void figureCaption() {
        this.write(String.valueOf(' ') + HTML.Attribute.ALT + String.valueOf('=') + String.valueOf('\"'));
    }
    
    public void figureCaption_() {
        this.write(String.valueOf('\"'));
    }
    
    public void figureGraphics(final String name) {
        this.write(String.valueOf(' ') + HTML.Attribute.SRC + String.valueOf('=') + String.valueOf('\"') + name + String.valueOf('\"'));
    }
    
    public void anchor(final String name) {
        if (!this.headFlag) {
            final String id = HtmlTools.encodeId(name);
            final MutableAttributeSet att = new SimpleAttributeSet();
            if (id != null) {
                att.addAttribute(HTML.Attribute.NAME, id);
            }
            this.writeStartTag(HTML.Tag.A, att);
        }
    }
    
    public void anchor_() {
        if (!this.headFlag) {
            this.writeEndTagWithoutEOL(HTML.Tag.A);
        }
    }
    
    public void link(final String name) {
        this.link(name, (String)null);
    }
    
    public void link(final String name, final String target) {
        if (this.headFlag) {
            return;
        }
        final MutableAttributeSet att = new SimpleAttributeSet();
        if (target != null) {
            att.addAttribute(HTML.Attribute.TARGET, target);
        }
        if (StructureSink.isExternalLink(name) || this.isExternalHtml(name)) {
            if (this.isExternalLink(name)) {
                att.addAttribute(HTML.Attribute.CLASS, "externalLink");
            }
            att.addAttribute(HTML.Attribute.HREF, HtmlTools.escapeHTML(name));
            this.writeStartTag(HTML.Tag.A, att);
        }
        else {
            att.addAttribute(HTML.Attribute.HREF, "#" + HtmlTools.escapeHTML(name));
            this.writeStartTag(HTML.Tag.A, att);
        }
    }
    
    private boolean isExternalLink(final String href) {
        final String text = href.toLowerCase();
        return text.indexOf("http:/") == 0 || text.indexOf("https:/") == 0 || text.indexOf("ftp:/") == 0 || text.indexOf("mailto:") == 0 || text.indexOf("file:/") == 0;
    }
    
    private boolean isExternalHtml(final String href) {
        final String text = href.toLowerCase();
        return text.indexOf(".html#") != -1 || text.indexOf(".htm#") != -1 || text.endsWith(".htm") || text.endsWith(".html") || !HtmlTools.isId(text);
    }
    
    public void link_() {
        if (!this.headFlag) {
            this.writeEndTagWithoutEOL(HTML.Tag.A);
        }
    }
    
    public void italic() {
        if (!this.headFlag) {
            this.writeStartTag(HTML.Tag.I);
        }
    }
    
    public void italic_() {
        if (!this.headFlag) {
            this.writeEndTagWithoutEOL(HTML.Tag.I);
        }
    }
    
    public void bold() {
        if (!this.headFlag) {
            this.writeStartTag(HTML.Tag.B);
        }
    }
    
    public void bold_() {
        if (!this.headFlag) {
            this.writeEndTagWithoutEOL(HTML.Tag.B);
        }
    }
    
    public void monospaced() {
        if (!this.headFlag) {
            this.writeStartTag(HTML.Tag.TT);
        }
    }
    
    public void monospaced_() {
        if (!this.headFlag) {
            this.writeEndTagWithoutEOL(HTML.Tag.TT);
        }
    }
    
    public void lineBreak() {
        if (this.headFlag) {
            this.buffer.append(XhtmlSink.EOL);
        }
        else {
            this.writeSimpleTag(HTML.Tag.BR);
        }
    }
    
    public void nonBreakingSpace() {
        if (this.headFlag) {
            this.buffer.append(' ');
        }
        else {
            this.write("&#160;");
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
    
    public void rawText(final String text) {
        this.write(text);
    }
    
    public void flush() {
        this.writer.flush();
    }
    
    public void close() {
        this.writer.close();
    }
    
    protected void write(String text) {
        if (this.renderingContext != null) {
            final String relativePathToBasedir = this.renderingContext.getRelativePath();
            if (relativePathToBasedir == null) {
                text = StringUtils.replace(text, "$relativePath", ".");
            }
            else {
                text = StringUtils.replace(text, "$relativePath", relativePathToBasedir);
            }
        }
        this.writer.write(text);
    }
    
    protected void content(final String text) {
        this.write(escapeHTML(text));
    }
    
    protected void verbatimContent(final String text) {
        this.write(escapeHTML(text));
    }
    
    public static String escapeHTML(final String text) {
        return HtmlTools.escapeHTML(text);
    }
    
    public static String encodeFragment(final String text) {
        return encodeURL(StructureSink.linkToKey(text));
    }
    
    public static String encodeURL(final String text) {
        return HtmlTools.encodeURL(text);
    }
    
    public RenderingContext getRenderingContext() {
        return this.renderingContext;
    }
}
