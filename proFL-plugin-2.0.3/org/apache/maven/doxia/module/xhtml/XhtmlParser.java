// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.xhtml;

import org.apache.maven.doxia.macro.MacroExecutionException;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import javax.swing.text.html.HTML;
import org.apache.maven.doxia.sink.Sink;
import org.codehaus.plexus.util.xml.pull.XmlPullParser;
import java.util.Stack;
import org.apache.maven.doxia.parser.AbstractXmlParser;

public class XhtmlParser extends AbstractXmlParser implements XhtmlMarkup
{
    private Stack linktypes;
    private Stack sections;
    private static final String LINK = "link";
    private static final String ANCHOR = "anchor";
    
    public XhtmlParser() {
        this.linktypes = new Stack();
        this.sections = new Stack();
    }
    
    protected void handleStartTag(final XmlPullParser parser, final Sink sink) throws XmlPullParserException, MacroExecutionException {
        if (parser.getName().equals(HTML.Tag.TITLE.toString())) {
            sink.title();
        }
        else if (parser.getName().equals(HTML.Tag.ADDRESS.toString())) {
            sink.author();
        }
        else if (parser.getName().equals(HTML.Tag.BODY.toString())) {
            sink.body();
        }
        else if (parser.getName().equals(HTML.Tag.H1.toString()) || parser.getName().equals(HTML.Tag.H2.toString()) || parser.getName().equals(HTML.Tag.H3.toString()) || parser.getName().equals(HTML.Tag.H4.toString()) || parser.getName().equals(HTML.Tag.H5.toString())) {
            this.closeSubordinatedSections(parser.getName(), sink);
            this.startSection(this.sections.size(), sink);
            this.startSectionTitle(this.sections.size(), sink);
            this.sections.push(parser.getName());
        }
        else if (parser.getName().equals(HTML.Tag.P.toString())) {
            sink.paragraph();
        }
        else if (parser.getName().equals(HTML.Tag.PRE.toString())) {
            sink.verbatim(true);
        }
        else if (parser.getName().equals(HTML.Tag.CODE.toString()) || parser.getName().equals(HTML.Tag.SAMP.toString()) || parser.getName().equals(HTML.Tag.TT.toString())) {
            sink.monospaced();
        }
        else if (parser.getName().equals(HTML.Tag.UL.toString())) {
            sink.list();
        }
        else if (parser.getName().equals(HTML.Tag.OL.toString())) {
            sink.numberedList(0);
        }
        else if (parser.getName().equals(HTML.Tag.LI.toString())) {
            sink.listItem();
        }
        else if (parser.getName().equals(HTML.Tag.HEAD.toString())) {
            sink.head();
        }
        else if (parser.getName().equals(HTML.Tag.B.toString()) || parser.getName().equals(HTML.Tag.STRONG.toString())) {
            sink.bold();
        }
        else if (parser.getName().equals(HTML.Tag.I.toString()) || parser.getName().equals(HTML.Tag.EM.toString())) {
            sink.italic();
        }
        else if (parser.getName().equals(HTML.Tag.A.toString())) {
            final String href = parser.getAttributeValue(null, HTML.Attribute.HREF.toString());
            final String name = parser.getAttributeValue(null, HTML.Attribute.NAME.toString());
            final String id = parser.getAttributeValue(null, HTML.Attribute.ID.toString());
            if (href != null) {
                sink.link(href);
                this.linktypes.push("link");
            }
            else if (name != null) {
                sink.anchor(name);
                this.linktypes.push("anchor");
            }
            else if (id != null) {
                sink.anchor(id);
                this.linktypes.push("anchor");
            }
        }
        else if (parser.getName().equals(HTML.Tag.BR.toString())) {
            sink.lineBreak();
        }
        else if (parser.getName().equals(HTML.Tag.HR.toString())) {
            sink.horizontalRule();
        }
        else if (parser.getName().equals(HTML.Tag.IMG.toString())) {
            sink.figure();
            final String src = parser.getAttributeValue(null, HTML.Attribute.SRC.toString());
            final String title = parser.getAttributeValue(null, HTML.Attribute.TITLE.toString());
            final String alt = parser.getAttributeValue(null, HTML.Attribute.ALT.toString());
            if (src != null) {
                sink.figureGraphics(src);
            }
            if (title != null) {
                sink.figureCaption();
                text(sink, title);
                sink.figureCaption_();
            }
            else if (alt != null) {
                sink.figureCaption();
                text(sink, alt);
                sink.figureCaption_();
            }
            sink.figure_();
        }
        else if (parser.getName().equals(HTML.Tag.TABLE.toString())) {
            sink.table();
        }
        else if (parser.getName().equals(HTML.Tag.TR.toString())) {
            sink.tableRow();
        }
        else if (parser.getName().equals(HTML.Tag.TH.toString())) {
            sink.tableCell();
        }
        else if (parser.getName().equals(HTML.Tag.TD.toString())) {
            sink.tableCell();
        }
    }
    
    protected void handleEndTag(final XmlPullParser parser, final Sink sink) throws XmlPullParserException, MacroExecutionException {
        if (parser.getName().equals(HTML.Tag.TITLE.toString())) {
            sink.title_();
        }
        else if (parser.getName().equals(HTML.Tag.ADDRESS.toString())) {
            sink.author_();
        }
        else if (parser.getName().equals(HTML.Tag.BODY.toString())) {
            this.closeSubordinatedSections("h0", sink);
            sink.body_();
        }
        else if (parser.getName().equals(HTML.Tag.H1.toString()) || parser.getName().equals(HTML.Tag.H2.toString()) || parser.getName().equals(HTML.Tag.H3.toString()) || parser.getName().equals(HTML.Tag.H4.toString()) || parser.getName().equals(HTML.Tag.H5.toString())) {
            this.closeSectionTitle(this.sections.size() - 1, sink);
        }
        else if (parser.getName().equals(HTML.Tag.P.toString())) {
            sink.paragraph_();
        }
        else if (parser.getName().equals(HTML.Tag.PRE.toString())) {
            sink.verbatim_();
        }
        else if (parser.getName().equals(HTML.Tag.CODE.toString()) || parser.getName().equals(HTML.Tag.SAMP.toString()) || parser.getName().equals(HTML.Tag.TT.toString())) {
            sink.monospaced_();
        }
        else if (parser.getName().equals(HTML.Tag.UL.toString())) {
            sink.list_();
        }
        else if (parser.getName().equals(HTML.Tag.OL.toString())) {
            sink.numberedList_();
        }
        else if (parser.getName().equals(HTML.Tag.LI.toString())) {
            sink.listItem_();
        }
        else if (parser.getName().equals(HTML.Tag.HEAD.toString())) {
            sink.head_();
        }
        else if (parser.getName().equals(HTML.Tag.B.toString()) || parser.getName().equals(HTML.Tag.STRONG.toString())) {
            sink.bold_();
        }
        else if (parser.getName().equals(HTML.Tag.I.toString()) || parser.getName().equals(HTML.Tag.EM.toString())) {
            sink.italic_();
        }
        else if (parser.getName().equals(HTML.Tag.A.toString())) {
            final String linktype = this.linktypes.pop();
            if (linktype == "link") {
                sink.link_();
            }
            else {
                sink.anchor_();
            }
        }
        else if (parser.getName().equals(HTML.Tag.TABLE.toString())) {
            sink.table_();
        }
        else if (parser.getName().equals(HTML.Tag.TR.toString())) {
            sink.tableRow_();
        }
        else if (parser.getName().equals(HTML.Tag.TH.toString())) {
            sink.tableCell_();
        }
        else if (parser.getName().equals(HTML.Tag.TD.toString())) {
            sink.tableCell_();
        }
    }
    
    protected void handleText(final XmlPullParser parser, final Sink sink) throws XmlPullParserException {
        text(sink, parser.getText());
    }
    
    private static void text(final Sink sink, final String text) {
        if (text.startsWith("&nbsp;")) {
            sink.nonBreakingSpace();
        }
        final String[] s = text.split("&nbsp;");
        for (int i = 0; i < s.length; ++i) {
            sink.text(s[i]);
            if (i + 1 < s.length) {
                sink.nonBreakingSpace();
            }
        }
        if (text.endsWith("&nbsp;")) {
            sink.nonBreakingSpace();
        }
    }
    
    private void closeSubordinatedSections(final String level, final Sink sink) {
        if (this.sections.size() > 0) {
            final String heading = this.sections.peek();
            final int otherlevel = Integer.parseInt(heading.substring(1));
            final int mylevel = Integer.parseInt(level.substring(1));
            if (otherlevel >= mylevel) {
                this.closeSection(this.sections.size(), sink);
                this.closeSubordinatedSections(level, sink);
            }
        }
    }
    
    private void closeSection(final int level, final Sink sink) {
        this.sections.pop();
        switch (level) {
            case 1: {
                sink.section1_();
                break;
            }
            case 2: {
                sink.section2_();
                break;
            }
            case 3: {
                sink.section3_();
                break;
            }
            case 4: {
                sink.section4_();
                break;
            }
            case 5: {
                sink.section5_();
                break;
            }
        }
    }
    
    private void startSection(final int level, final Sink sink) {
        switch (level) {
            case 0: {
                sink.section1();
                break;
            }
            case 1: {
                sink.section2();
                break;
            }
            case 2: {
                sink.section3();
                break;
            }
            case 3: {
                sink.section4();
                break;
            }
            case 4: {
                sink.section5();
                break;
            }
        }
    }
    
    private void closeSectionTitle(final int level, final Sink sink) {
        switch (level) {
            case 0: {
                sink.sectionTitle1_();
                break;
            }
            case 1: {
                sink.sectionTitle2_();
                break;
            }
            case 2: {
                sink.sectionTitle3_();
                break;
            }
            case 3: {
                sink.sectionTitle4_();
                break;
            }
            case 4: {
                sink.sectionTitle5_();
                break;
            }
        }
    }
    
    private void startSectionTitle(final int level, final Sink sink) {
        switch (level) {
            case 0: {
                sink.sectionTitle1();
                break;
            }
            case 1: {
                sink.sectionTitle2();
                break;
            }
            case 2: {
                sink.sectionTitle3();
                break;
            }
            case 3: {
                sink.sectionTitle4();
                break;
            }
            case 4: {
                sink.sectionTitle5();
                break;
            }
        }
    }
}
