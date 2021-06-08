// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.sink;

import org.apache.maven.doxia.logging.LogEnabled;

public interface Sink extends LogEnabled
{
    public static final String ROLE = ((Sink$1.class$org$apache$maven$doxia$sink$Sink == null) ? (Sink$1.class$org$apache$maven$doxia$sink$Sink = Sink$1.class$("org.apache.maven.doxia.sink.Sink")) : Sink$1.class$org$apache$maven$doxia$sink$Sink).getName();
    public static final int NUMBERING_DECIMAL = 0;
    public static final int NUMBERING_LOWER_ALPHA = 1;
    public static final int NUMBERING_UPPER_ALPHA = 2;
    public static final int NUMBERING_LOWER_ROMAN = 3;
    public static final int NUMBERING_UPPER_ROMAN = 4;
    public static final int SECTION_LEVEL_1 = 1;
    public static final int SECTION_LEVEL_2 = 2;
    public static final int SECTION_LEVEL_3 = 3;
    public static final int SECTION_LEVEL_4 = 4;
    public static final int SECTION_LEVEL_5 = 5;
    public static final int JUSTIFY_CENTER = 0;
    public static final int JUSTIFY_LEFT = 1;
    public static final int JUSTIFY_RIGHT = 2;
    
    void head();
    
    void head(final SinkEventAttributes p0);
    
    void head_();
    
    void title();
    
    void title(final SinkEventAttributes p0);
    
    void title_();
    
    void author();
    
    void author(final SinkEventAttributes p0);
    
    void author_();
    
    void date();
    
    void date(final SinkEventAttributes p0);
    
    void date_();
    
    void body();
    
    void body(final SinkEventAttributes p0);
    
    void body_();
    
    void sectionTitle();
    
    void sectionTitle_();
    
    void section1();
    
    void section1_();
    
    void sectionTitle1();
    
    void sectionTitle1_();
    
    void section2();
    
    void section2_();
    
    void sectionTitle2();
    
    void sectionTitle2_();
    
    void section3();
    
    void section3_();
    
    void sectionTitle3();
    
    void sectionTitle3_();
    
    void section4();
    
    void section4_();
    
    void sectionTitle4();
    
    void sectionTitle4_();
    
    void section5();
    
    void section5_();
    
    void sectionTitle5();
    
    void sectionTitle5_();
    
    void section(final int p0, final SinkEventAttributes p1);
    
    void section_(final int p0);
    
    void sectionTitle(final int p0, final SinkEventAttributes p1);
    
    void sectionTitle_(final int p0);
    
    void list();
    
    void list(final SinkEventAttributes p0);
    
    void list_();
    
    void listItem();
    
    void listItem(final SinkEventAttributes p0);
    
    void listItem_();
    
    void numberedList(final int p0);
    
    void numberedList(final int p0, final SinkEventAttributes p1);
    
    void numberedList_();
    
    void numberedListItem();
    
    void numberedListItem(final SinkEventAttributes p0);
    
    void numberedListItem_();
    
    void definitionList();
    
    void definitionList(final SinkEventAttributes p0);
    
    void definitionList_();
    
    void definitionListItem();
    
    void definitionListItem(final SinkEventAttributes p0);
    
    void definitionListItem_();
    
    void definition();
    
    void definition(final SinkEventAttributes p0);
    
    void definition_();
    
    void definedTerm();
    
    void definedTerm(final SinkEventAttributes p0);
    
    void definedTerm_();
    
    void figure();
    
    void figure(final SinkEventAttributes p0);
    
    void figure_();
    
    void figureCaption();
    
    void figureCaption(final SinkEventAttributes p0);
    
    void figureCaption_();
    
    void figureGraphics(final String p0);
    
    void figureGraphics(final String p0, final SinkEventAttributes p1);
    
    void table();
    
    void table(final SinkEventAttributes p0);
    
    void table_();
    
    void tableRows(final int[] p0, final boolean p1);
    
    void tableRows_();
    
    void tableRow();
    
    void tableRow(final SinkEventAttributes p0);
    
    void tableRow_();
    
    void tableCell();
    
    void tableCell(final String p0);
    
    void tableCell(final SinkEventAttributes p0);
    
    void tableCell_();
    
    void tableHeaderCell();
    
    void tableHeaderCell(final String p0);
    
    void tableHeaderCell(final SinkEventAttributes p0);
    
    void tableHeaderCell_();
    
    void tableCaption();
    
    void tableCaption(final SinkEventAttributes p0);
    
    void tableCaption_();
    
    void paragraph();
    
    void paragraph(final SinkEventAttributes p0);
    
    void paragraph_();
    
    void verbatim(final boolean p0);
    
    void verbatim(final SinkEventAttributes p0);
    
    void verbatim_();
    
    void horizontalRule();
    
    void horizontalRule(final SinkEventAttributes p0);
    
    void pageBreak();
    
    void anchor(final String p0);
    
    void anchor(final String p0, final SinkEventAttributes p1);
    
    void anchor_();
    
    void link(final String p0);
    
    void link(final String p0, final SinkEventAttributes p1);
    
    void link_();
    
    void italic();
    
    void italic_();
    
    void bold();
    
    void bold_();
    
    void monospaced();
    
    void monospaced_();
    
    void lineBreak();
    
    void lineBreak(final SinkEventAttributes p0);
    
    void nonBreakingSpace();
    
    void text(final String p0);
    
    void text(final String p0, final SinkEventAttributes p1);
    
    void rawText(final String p0);
    
    void comment(final String p0);
    
    void unknown(final String p0, final Object[] p1, final SinkEventAttributes p2);
    
    void flush();
    
    void close();
}
