// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.xdoc;

import org.apache.maven.doxia.macro.manager.MacroNotFoundException;
import org.apache.maven.doxia.macro.MacroRequest;
import org.apache.maven.doxia.macro.MacroExecutionException;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.doxia.util.HtmlTools;
import javax.swing.text.html.HTML;
import org.codehaus.plexus.util.xml.pull.XmlPullParser;
import java.io.StringReader;
import java.io.IOException;
import org.apache.maven.doxia.parser.ParseException;
import java.io.Writer;
import org.codehaus.plexus.util.IOUtil;
import java.io.StringWriter;
import org.apache.maven.doxia.sink.Sink;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import org.apache.maven.doxia.parser.AbstractXmlParser;

public class XdocParser extends AbstractXmlParser implements XdocMarkup
{
    private String sourceContent;
    private boolean isLink;
    private boolean isAnchor;
    private boolean isEmptyElement;
    private int orderedListDepth;
    private String macroName;
    private Map macroParameters;
    
    public XdocParser() {
        this.orderedListDepth = 0;
        this.macroParameters = new HashMap();
    }
    
    public void parse(final Reader source, final Sink sink) throws ParseException {
        try {
            final StringWriter contentWriter = new StringWriter();
            IOUtil.copy(source, contentWriter);
            this.sourceContent = contentWriter.toString();
        }
        catch (IOException ex) {
            throw new ParseException("Error reading the input source: " + ex.getMessage(), ex);
        }
        finally {
            IOUtil.close(source);
        }
        final Reader tmp = new StringReader(this.sourceContent);
        super.parse(tmp, sink);
    }
    
    protected void handleStartTag(final XmlPullParser parser, final Sink sink) throws XmlPullParserException, MacroExecutionException {
        this.isEmptyElement = parser.isEmptyElementTag();
        if (parser.getName().equals(XdocParser.DOCUMENT_TAG.toString())) {
            return;
        }
        if (parser.getName().equals(HTML.Tag.TITLE.toString())) {
            sink.title();
        }
        else if (parser.getName().equals(XdocParser.AUTHOR_TAG.toString())) {
            sink.author();
        }
        else if (parser.getName().equals(HTML.Tag.BODY.toString())) {
            sink.body();
        }
        else if (parser.getName().equals(XdocParser.SECTION_TAG.toString())) {
            sink.section1();
            sink.sectionTitle1();
            sink.anchor(HtmlTools.encodeId(parser.getAttributeValue(null, HTML.Attribute.NAME.toString())));
            sink.anchor_();
            sink.text(parser.getAttributeValue(null, HTML.Attribute.NAME.toString()));
            sink.sectionTitle1_();
        }
        else if (parser.getName().equals(XdocParser.SUBSECTION_TAG.toString())) {
            sink.section2();
            sink.sectionTitle2();
            sink.anchor(HtmlTools.encodeId(parser.getAttributeValue(null, HTML.Attribute.NAME.toString())));
            sink.anchor_();
            sink.text(parser.getAttributeValue(null, HTML.Attribute.NAME.toString()));
            sink.sectionTitle2_();
        }
        else if (parser.getName().equals(HTML.Tag.H4.toString())) {
            sink.sectionTitle3();
        }
        else if (parser.getName().equals(HTML.Tag.H5.toString())) {
            sink.sectionTitle4();
        }
        else if (parser.getName().equals(HTML.Tag.H6.toString())) {
            sink.sectionTitle5();
        }
        else if (parser.getName().equals(HTML.Tag.P.toString())) {
            sink.paragraph();
        }
        else if (parser.getName().equals(XdocParser.SOURCE_TAG.toString())) {
            sink.verbatim(true);
        }
        else if (parser.getName().equals(HTML.Tag.UL.toString())) {
            sink.list();
        }
        else if (parser.getName().equals(HTML.Tag.OL.toString())) {
            sink.numberedList(0);
            ++this.orderedListDepth;
        }
        else if (parser.getName().equals(HTML.Tag.LI.toString())) {
            if (this.orderedListDepth == 0) {
                sink.listItem();
            }
            else {
                sink.numberedListItem();
            }
        }
        else if (parser.getName().equals(HTML.Tag.DL.toString())) {
            sink.definitionList();
        }
        else if (parser.getName().equals(HTML.Tag.DT.toString())) {
            sink.definitionListItem();
            sink.definedTerm();
        }
        else if (parser.getName().equals(HTML.Tag.DD.toString())) {
            sink.definition();
        }
        else if (parser.getName().equals(XdocParser.PROPERTIES_TAG.toString())) {
            sink.head();
        }
        else if (parser.getName().equals(HTML.Tag.B.toString())) {
            sink.bold();
        }
        else if (parser.getName().equals(HTML.Tag.I.toString())) {
            sink.italic();
        }
        else if (parser.getName().equals(HTML.Tag.TT.toString())) {
            sink.monospaced();
        }
        else if (parser.getName().equals(HTML.Tag.A.toString())) {
            final String href = parser.getAttributeValue(null, HTML.Attribute.HREF.toString());
            if (href != null) {
                sink.link(href);
                this.isLink = true;
            }
            else {
                final String name = parser.getAttributeValue(null, HTML.Attribute.NAME.toString());
                if (name != null) {
                    sink.anchor(name);
                    this.isAnchor = true;
                }
                else {
                    this.handleRawText(sink, parser);
                }
            }
        }
        else if (parser.getName().equals(XdocParser.MACRO_TAG.toString())) {
            if (!this.secondParsing) {
                this.macroName = parser.getAttributeValue(null, HTML.Attribute.NAME.toString());
                if (StringUtils.isEmpty(this.macroName)) {
                    throw new IllegalArgumentException("The '" + HTML.Attribute.NAME.toString() + "' attribute for the '" + XdocParser.MACRO_TAG.toString() + "' tag is required.");
                }
            }
        }
        else if (parser.getName().equals(HTML.Tag.PARAM.toString())) {
            if (!this.secondParsing && StringUtils.isNotEmpty(this.macroName)) {
                if (this.macroParameters == null) {
                    this.macroParameters = new HashMap();
                }
                final String paramName = parser.getAttributeValue(null, HTML.Attribute.NAME.toString());
                final String paramValue = parser.getAttributeValue(null, HTML.Attribute.VALUE.toString());
                if (StringUtils.isEmpty(paramName) || StringUtils.isEmpty(paramValue)) {
                    throw new IllegalArgumentException("'" + HTML.Attribute.NAME.toString() + "' and '" + HTML.Attribute.VALUE.toString() + "' attributes for the '" + HTML.Tag.PARAM.toString() + "' tag are required inside the '" + XdocParser.MACRO_TAG.toString() + "' tag.");
                }
                this.macroParameters.put(paramName, paramValue);
            }
        }
        else if (parser.getName().equals(HTML.Tag.TABLE.toString())) {
            sink.table();
        }
        else if (parser.getName().equals(HTML.Tag.TR.toString())) {
            sink.tableRow();
        }
        else if (parser.getName().equals(HTML.Tag.TH.toString())) {
            final String width = parser.getAttributeValue(null, HTML.Attribute.WIDTH.toString());
            if (width == null) {
                sink.tableHeaderCell();
            }
            else {
                sink.tableHeaderCell(width);
            }
        }
        else if (parser.getName().equals(HTML.Tag.TD.toString())) {
            final String width = parser.getAttributeValue(null, HTML.Attribute.WIDTH.toString());
            if (width == null) {
                sink.tableCell();
            }
            else {
                sink.tableCell(width);
            }
        }
        else if (parser.getName().equals(HTML.Tag.BR.toString())) {
            sink.lineBreak();
        }
        else if (parser.getName().equals(HTML.Tag.HR.toString())) {
            sink.horizontalRule();
        }
        else if (parser.getName().equals(HTML.Tag.IMG.toString())) {
            final String src = parser.getAttributeValue(null, HTML.Attribute.SRC.toString());
            final String alt = parser.getAttributeValue(null, HTML.Attribute.ALT.toString());
            sink.figure();
            sink.figureGraphics(src);
            if (alt != null) {
                sink.figureCaption();
                sink.text(alt);
                sink.figureCaption_();
            }
            sink.figure_();
        }
        else {
            this.handleRawText(sink, parser);
        }
    }
    
    protected void handleEndTag(final XmlPullParser parser, final Sink sink) throws XmlPullParserException, MacroExecutionException {
        if (parser.getName().equals(XdocParser.DOCUMENT_TAG.toString())) {
            return;
        }
        if (parser.getName().equals(HTML.Tag.TITLE.toString())) {
            sink.title_();
        }
        else if (parser.getName().equals(XdocParser.AUTHOR_TAG.toString())) {
            sink.author_();
        }
        else if (parser.getName().equals(HTML.Tag.BODY.toString())) {
            sink.body_();
        }
        else if (parser.getName().equals(HTML.Tag.P.toString())) {
            sink.paragraph_();
        }
        else if (parser.getName().equals(XdocParser.SOURCE_TAG.toString())) {
            sink.verbatim_();
        }
        else if (parser.getName().equals(HTML.Tag.UL.toString())) {
            sink.list_();
        }
        else if (parser.getName().equals(HTML.Tag.OL.toString())) {
            sink.numberedList_();
            --this.orderedListDepth;
        }
        else if (parser.getName().equals(HTML.Tag.LI.toString())) {
            if (this.orderedListDepth == 0) {
                sink.listItem_();
            }
            else {
                sink.numberedListItem_();
            }
        }
        else if (parser.getName().equals(HTML.Tag.DL.toString())) {
            sink.definitionList_();
        }
        else if (parser.getName().equals(HTML.Tag.DT.toString())) {
            sink.definedTerm_();
        }
        else if (parser.getName().equals(HTML.Tag.DD.toString())) {
            sink.definition_();
            sink.definitionListItem_();
        }
        else if (parser.getName().equals(XdocParser.PROPERTIES_TAG.toString())) {
            sink.head_();
        }
        else if (parser.getName().equals(HTML.Tag.B.toString())) {
            sink.bold_();
        }
        else if (parser.getName().equals(HTML.Tag.I.toString())) {
            sink.italic_();
        }
        else if (parser.getName().equals(HTML.Tag.TT.toString())) {
            sink.monospaced_();
        }
        else if (parser.getName().equals(HTML.Tag.A.toString())) {
            if (this.isLink) {
                sink.link_();
                this.isLink = false;
            }
            else if (this.isAnchor) {
                sink.anchor_();
                this.isAnchor = false;
            }
        }
        else if (parser.getName().equals(XdocParser.MACRO_TAG.toString())) {
            if (!this.secondParsing && StringUtils.isNotEmpty(this.macroName)) {
                this.macroParameters.put("sourceContent", this.sourceContent);
                final XdocParser xdocParser = new XdocParser();
                xdocParser.setSecondParsing(true);
                this.macroParameters.put("parser", xdocParser);
                final MacroRequest request = new MacroRequest(this.macroParameters, this.getBasedir());
                try {
                    this.executeMacro(this.macroName, request, sink);
                }
                catch (MacroNotFoundException me) {
                    throw new MacroExecutionException("Macro not found: " + this.macroName, me);
                }
            }
            this.macroName = null;
            this.macroParameters = null;
        }
        else if (!parser.getName().equals(HTML.Tag.PARAM.toString())) {
            if (parser.getName().equals(HTML.Tag.TABLE.toString())) {
                sink.table_();
            }
            else if (parser.getName().equals(HTML.Tag.TR.toString())) {
                sink.tableRow_();
            }
            else if (parser.getName().equals(HTML.Tag.TH.toString())) {
                sink.tableHeaderCell_();
            }
            else if (parser.getName().equals(HTML.Tag.TD.toString())) {
                sink.tableCell_();
            }
            else if (parser.getName().equals(XdocParser.SECTION_TAG.toString())) {
                sink.section1_();
            }
            else if (parser.getName().equals(XdocParser.SUBSECTION_TAG.toString())) {
                sink.section2_();
            }
            else if (parser.getName().equals(HTML.Tag.H4.toString())) {
                sink.sectionTitle3_();
            }
            else if (parser.getName().equals(HTML.Tag.H5.toString())) {
                sink.sectionTitle4_();
            }
            else if (parser.getName().equals(HTML.Tag.H6.toString())) {
                sink.sectionTitle5_();
            }
            else if (!this.isEmptyElement) {
                sink.rawText(String.valueOf('<') + String.valueOf('/'));
                sink.rawText(parser.getName());
                sink.rawText(String.valueOf('>'));
            }
            else {
                this.isEmptyElement = false;
            }
        }
    }
    
    protected void handleText(final XmlPullParser parser, final Sink sink) throws XmlPullParserException {
        final String text = parser.getText();
        if (!"".equals(text.trim())) {
            sink.text(text);
        }
    }
    
    private void handleRawText(final Sink sink, final XmlPullParser parser) {
        sink.rawText(String.valueOf('<'));
        sink.rawText(parser.getName());
        for (int count = parser.getAttributeCount(), i = 0; i < count; ++i) {
            sink.rawText(String.valueOf(' '));
            sink.rawText(parser.getAttributeName(i));
            sink.rawText(String.valueOf('='));
            sink.rawText(String.valueOf('\"'));
            sink.rawText(parser.getAttributeValue(i));
            sink.rawText(String.valueOf('\"'));
        }
        sink.rawText(String.valueOf('>'));
    }
}
