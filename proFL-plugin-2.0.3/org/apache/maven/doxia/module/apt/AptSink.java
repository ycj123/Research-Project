// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.apt;

import org.apache.maven.doxia.util.HtmlTools;
import org.codehaus.plexus.util.StringUtils;
import java.io.Writer;
import java.util.Stack;
import java.io.PrintWriter;
import org.apache.maven.doxia.sink.AbstractTextSink;

public class AptSink extends AbstractTextSink implements AptMarkup
{
    private StringBuffer buffer;
    private StringBuffer tableCaptionBuffer;
    private String author;
    private String title;
    private String date;
    private boolean tableCaptionFlag;
    private boolean headerFlag;
    private boolean bufferFlag;
    private boolean itemFlag;
    private boolean verbatimFlag;
    private boolean boxed;
    private boolean gridFlag;
    private int cellCount;
    private PrintWriter writer;
    private int[] cellJustif;
    private String rowLine;
    private String listNestingIndent;
    private Stack listStyles;
    
    public AptSink(final Writer writer) {
        this.buffer = new StringBuffer();
        this.tableCaptionBuffer = new StringBuffer();
        this.writer = new PrintWriter(writer);
        this.listNestingIndent = "";
        this.listStyles = new Stack();
    }
    
    protected StringBuffer getBuffer() {
        return this.buffer;
    }
    
    protected void setHeadFlag(final boolean headFlag) {
        this.headerFlag = headFlag;
    }
    
    protected void resetState() {
        this.headerFlag = false;
        this.resetBuffer();
        this.itemFlag = false;
        this.verbatimFlag = false;
        this.cellCount = 0;
    }
    
    protected void resetBuffer() {
        this.buffer = new StringBuffer();
    }
    
    protected void resetTableCaptionBuffer() {
        this.tableCaptionBuffer = new StringBuffer();
    }
    
    public void head() {
        this.resetState();
        this.headerFlag = true;
    }
    
    public void head_() {
        this.headerFlag = false;
        this.write(AptSink.HEADER_START_MARKUP + AptSink.EOL);
        if (this.title != null) {
            this.write(" " + this.title + AptSink.EOL);
        }
        this.write(AptSink.HEADER_START_MARKUP + AptSink.EOL);
        if (this.author != null) {
            this.write(" " + this.author + AptSink.EOL);
        }
        this.write(AptSink.HEADER_START_MARKUP + AptSink.EOL);
        if (this.date != null) {
            this.write(" " + this.date + AptSink.EOL);
        }
        this.write(AptSink.HEADER_START_MARKUP + AptSink.EOL);
    }
    
    public void title_() {
        if (this.buffer.length() > 0) {
            this.title = this.buffer.toString();
            this.resetBuffer();
        }
    }
    
    public void author_() {
        if (this.buffer.length() > 0) {
            this.author = this.buffer.toString();
            this.resetBuffer();
        }
    }
    
    public void date_() {
        if (this.buffer.length() > 0) {
            this.date = this.buffer.toString();
            this.resetBuffer();
        }
    }
    
    public void section1_() {
        this.write(AptSink.EOL);
    }
    
    public void section2_() {
        this.write(AptSink.EOL);
    }
    
    public void section3_() {
        this.write(AptSink.EOL);
    }
    
    public void section4_() {
        this.write(AptSink.EOL);
    }
    
    public void section5_() {
        this.write(AptSink.EOL);
    }
    
    public void sectionTitle1() {
        this.write(AptSink.EOL);
    }
    
    public void sectionTitle1_() {
        this.write(AptSink.EOL + AptSink.EOL);
    }
    
    public void sectionTitle2() {
        this.write(AptSink.EOL + AptSink.SECTION_TITLE_START_MARKUP);
    }
    
    public void sectionTitle2_() {
        this.write(AptSink.EOL + AptSink.EOL);
    }
    
    public void sectionTitle3() {
        this.write(AptSink.EOL + StringUtils.repeat(AptSink.SECTION_TITLE_START_MARKUP, 2));
    }
    
    public void sectionTitle3_() {
        this.write(AptSink.EOL + AptSink.EOL);
    }
    
    public void sectionTitle4() {
        this.write(AptSink.EOL + StringUtils.repeat(AptSink.SECTION_TITLE_START_MARKUP, 3));
    }
    
    public void sectionTitle4_() {
        this.write(AptSink.EOL + AptSink.EOL);
    }
    
    public void sectionTitle5() {
        this.write(AptSink.EOL + StringUtils.repeat(AptSink.SECTION_TITLE_START_MARKUP, 4));
    }
    
    public void sectionTitle5_() {
        this.write(AptSink.EOL + AptSink.EOL);
    }
    
    public void list() {
        this.listNestingIndent += " ";
        this.listStyles.push(AptSink.LIST_START_MARKUP);
        this.write(AptSink.EOL);
    }
    
    public void list_() {
        if (this.listNestingIndent.length() <= 1) {
            this.write(AptSink.EOL + this.listNestingIndent + AptSink.LIST_END_MARKUP + AptSink.EOL);
        }
        else {
            this.write(AptSink.EOL);
        }
        this.listNestingIndent = StringUtils.chomp(this.listNestingIndent, " ");
        this.listStyles.pop();
        this.itemFlag = false;
    }
    
    public void listItem() {
        this.numberedListItem();
        this.itemFlag = true;
    }
    
    public void listItem_() {
        this.write(AptSink.EOL);
    }
    
    public void numberedList(final int numbering) {
        this.listNestingIndent += " ";
        this.write(AptSink.EOL);
        String style = null;
        switch (numbering) {
            case 2: {
                style = String.valueOf('A');
                break;
            }
            case 1: {
                style = String.valueOf('a');
                break;
            }
            case 4: {
                style = String.valueOf('I');
                break;
            }
            case 3: {
                style = String.valueOf('i');
                break;
            }
            default: {
                style = String.valueOf('1');
                break;
            }
        }
        this.listStyles.push(style);
    }
    
    public void numberedList_() {
        if (this.listNestingIndent.length() <= 1) {
            this.write(AptSink.EOL + this.listNestingIndent + AptSink.LIST_END_MARKUP + AptSink.EOL);
        }
        else {
            this.write(AptSink.EOL);
        }
        this.listNestingIndent = StringUtils.chomp(this.listNestingIndent, " ");
        this.listStyles.pop();
        this.itemFlag = false;
    }
    
    public void numberedListItem() {
        final String style = this.listStyles.peek();
        if (style.equals(String.valueOf('*'))) {
            this.write(AptSink.EOL + this.listNestingIndent + String.valueOf('*') + String.valueOf(' '));
        }
        else {
            this.write(AptSink.EOL + this.listNestingIndent + String.valueOf('[') + String.valueOf('[') + style + String.valueOf(']') + String.valueOf(']') + String.valueOf(' '));
        }
        this.itemFlag = true;
    }
    
    public void numberedListItem_() {
        this.write(AptSink.EOL);
    }
    
    public void definitionList() {
        this.write(AptSink.EOL);
    }
    
    public void definitionList_() {
        this.write(AptSink.EOL);
        this.itemFlag = false;
    }
    
    public void definedTerm() {
        this.write(AptSink.EOL + " [");
    }
    
    public void definedTerm_() {
        this.write("]");
    }
    
    public void definition() {
        this.itemFlag = true;
    }
    
    public void definition_() {
        this.write(AptSink.EOL);
    }
    
    public void pageBreak() {
        this.write(AptSink.EOL + '\f' + AptSink.EOL);
    }
    
    public void paragraph() {
        if (!this.itemFlag) {
            this.write(AptSink.EOL + " ");
        }
    }
    
    public void paragraph_() {
        if (this.itemFlag) {
            this.itemFlag = false;
        }
        else {
            this.write(AptSink.EOL + AptSink.EOL);
        }
    }
    
    public void verbatim(final boolean boxed) {
        this.verbatimFlag = true;
        this.boxed = boxed;
        if (boxed) {
            this.write(AptSink.EOL + AptSink.BOXED_VERBATIM_START_MARKUP + AptSink.EOL);
        }
        else {
            this.write(AptSink.EOL + AptSink.NON_BOXED_VERBATIM_START_MARKUP + AptSink.EOL);
        }
    }
    
    public void verbatim_() {
        if (this.boxed) {
            this.write(AptSink.EOL + AptSink.BOXED_VERBATIM_END_MARKUP + AptSink.EOL);
        }
        else {
            this.write(AptSink.EOL + AptSink.NON_BOXED_VERBATIM_END_MARKUP + AptSink.EOL);
        }
        this.boxed = false;
        this.verbatimFlag = false;
    }
    
    public void horizontalRule() {
        this.write(AptSink.EOL + AptSink.HORIZONTAL_RULE_MARKUP + AptSink.EOL);
    }
    
    public void table() {
        this.write(AptSink.EOL);
    }
    
    public void table_() {
        if (this.rowLine != null) {
            this.write(this.rowLine);
        }
        this.rowLine = null;
        if (this.tableCaptionBuffer.length() > 0) {
            this.text(this.tableCaptionBuffer.toString() + AptSink.EOL);
        }
        this.resetTableCaptionBuffer();
    }
    
    public void tableRows(final int[] justification, final boolean grid) {
        this.cellJustif = justification;
        this.gridFlag = grid;
    }
    
    public void tableRows_() {
        this.cellJustif = null;
        this.gridFlag = false;
    }
    
    public void tableRow() {
        this.bufferFlag = true;
        this.cellCount = 0;
    }
    
    public void tableRow_() {
        this.bufferFlag = false;
        this.buildRowLine();
        this.write(this.rowLine);
        if (this.gridFlag) {
            this.write(AptSink.TABLE_ROW_SEPARATOR_MARKUP);
        }
        this.write(this.buffer.toString());
        this.resetBuffer();
        this.write(AptSink.EOL);
        this.cellCount = 0;
    }
    
    private void buildRowLine() {
        final StringBuffer rowLine = new StringBuffer();
        rowLine.append(AptSink.TABLE_ROW_START_MARKUP);
        for (int i = 0; i < this.cellCount; ++i) {
            if (this.cellJustif != null) {
                switch (this.cellJustif[i]) {
                    case 1: {
                        rowLine.append(AptSink.TABLE_COL_LEFT_ALIGNED_MARKUP);
                        break;
                    }
                    case 2: {
                        rowLine.append(AptSink.TABLE_COL_RIGHT_ALIGNED_MARKUP);
                        break;
                    }
                    default: {
                        rowLine.append(AptSink.TABLE_COL_CENTERED_ALIGNED_MARKUP);
                        break;
                    }
                }
            }
            else {
                rowLine.append(AptSink.TABLE_COL_CENTERED_ALIGNED_MARKUP);
            }
        }
        rowLine.append(AptSink.EOL);
        this.rowLine = rowLine.toString();
    }
    
    public void tableCell() {
        this.tableCell(false);
    }
    
    public void tableHeaderCell() {
        this.tableCell(true);
    }
    
    public void tableCell(final boolean headerRow) {
        if (headerRow) {
            this.buffer.append(AptSink.TABLE_CELL_SEPARATOR_MARKUP);
        }
    }
    
    public void tableCell_() {
        this.tableCell_(false);
    }
    
    public void tableHeaderCell_() {
        this.tableCell_(true);
    }
    
    private void tableCell_(final boolean headerRow) {
        this.buffer.append(AptSink.TABLE_CELL_SEPARATOR_MARKUP);
        ++this.cellCount;
    }
    
    public void tableCaption() {
        this.tableCaptionFlag = true;
    }
    
    public void tableCaption_() {
        this.tableCaptionFlag = false;
    }
    
    public void figureCaption_() {
        this.write(AptSink.EOL);
    }
    
    public void figureGraphics(final String name) {
        this.write(AptSink.EOL + "[" + name + "] ");
    }
    
    public void anchor(final String name) {
        this.write(AptSink.ANCHOR_START_MARKUP);
    }
    
    public void anchor_() {
        this.write(AptSink.ANCHOR_END_MARKUP);
    }
    
    public void link(final String name) {
        if (!this.headerFlag) {
            this.write(AptSink.LINK_START_1_MARKUP);
            this.text(name);
            this.write(AptSink.LINK_START_2_MARKUP);
        }
    }
    
    public void link_() {
        if (!this.headerFlag) {
            this.write(AptSink.LINK_END_MARKUP);
        }
    }
    
    public void link(final String name, final String target) {
        if (!this.headerFlag) {
            this.write(AptSink.LINK_START_1_MARKUP);
            this.text(target);
            this.write(AptSink.LINK_START_2_MARKUP);
            this.text(name);
        }
    }
    
    public void italic() {
        if (!this.headerFlag) {
            this.write(AptSink.ITALIC_START_MARKUP);
        }
    }
    
    public void italic_() {
        if (!this.headerFlag) {
            this.write(AptSink.ITALIC_END_MARKUP);
        }
    }
    
    public void bold() {
        if (!this.headerFlag) {
            this.write(AptSink.BOLD_START_MARKUP);
        }
    }
    
    public void bold_() {
        if (!this.headerFlag) {
            this.write(AptSink.BOLD_END_MARKUP);
        }
    }
    
    public void monospaced() {
        if (!this.headerFlag) {
            this.write(AptSink.MONOSPACED_START_MARKUP);
        }
    }
    
    public void monospaced_() {
        if (!this.headerFlag) {
            this.write(AptSink.MONOSPACED_END_MARKUP);
        }
    }
    
    public void lineBreak() {
        if (this.headerFlag || this.bufferFlag) {
            this.buffer.append(AptSink.EOL);
        }
        else {
            this.write("\\" + AptSink.EOL);
        }
    }
    
    public void nonBreakingSpace() {
        if (this.headerFlag || this.bufferFlag) {
            this.buffer.append(AptSink.NON_BREAKING_SPACE_MARKUP);
        }
        else {
            this.write(AptSink.NON_BREAKING_SPACE_MARKUP);
        }
    }
    
    public void text(final String text) {
        if (this.tableCaptionFlag) {
            this.tableCaptionBuffer.append(text);
        }
        else if (this.headerFlag || this.bufferFlag) {
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
    
    protected void write(final String text) {
        this.writer.write(text);
    }
    
    protected void content(final String text) {
        this.write(escapeAPT(text));
    }
    
    protected void verbatimContent(final String text) {
        this.write(escapeAPT(text));
    }
    
    public static String encodeFragment(final String text) {
        return HtmlTools.encodeFragment(text);
    }
    
    public static String encodeURL(final String text) {
        return HtmlTools.encodeURL(text);
    }
    
    public void flush() {
        this.writer.flush();
    }
    
    public void close() {
        this.writer.close();
    }
    
    private static String escapeAPT(final String text) {
        if (text == null) {
            return "";
        }
        final int length = text.length();
        final StringBuffer buffer = new StringBuffer(length);
        for (int i = 0; i < length; ++i) {
            final char c = text.charAt(i);
            switch (c) {
                case '*':
                case '+':
                case '-':
                case '<':
                case '=':
                case '>':
                case '[':
                case '\\':
                case ']':
                case '{':
                case '}':
                case '~': {
                    buffer.append('\\');
                    buffer.append(c);
                    break;
                }
                default: {
                    if (c > '\u007f') {
                        buffer.append("\\u");
                        final String hex = Integer.toHexString(c);
                        if (hex.length() == 2) {
                            buffer.append("00");
                        }
                        else if (hex.length() == 3) {
                            buffer.append("0");
                        }
                        buffer.append(hex);
                        break;
                    }
                    buffer.append(c);
                    break;
                }
            }
        }
        return buffer.toString();
    }
}
