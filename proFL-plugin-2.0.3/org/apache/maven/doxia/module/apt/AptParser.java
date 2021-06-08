// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.apt;

import java.util.Map;
import org.apache.maven.doxia.macro.manager.MacroNotFoundException;
import org.apache.maven.doxia.macro.MacroExecutionException;
import org.apache.maven.doxia.macro.MacroRequest;
import java.util.HashMap;
import org.codehaus.plexus.util.StringUtils;
import java.util.StringTokenizer;
import org.apache.maven.doxia.markup.Markup;
import java.io.File;
import org.apache.maven.doxia.sink.SinkAdapter;
import java.io.StringReader;
import java.io.IOException;
import java.io.Writer;
import org.codehaus.plexus.util.IOUtil;
import java.io.StringWriter;
import java.io.Reader;
import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.doxia.parser.AbstractTextParser;

public class AptParser extends AbstractTextParser implements AptMarkup
{
    private static final int TITLE = 0;
    private static final int SECTION1 = 1;
    private static final int SECTION2 = 2;
    private static final int SECTION3 = 3;
    private static final int SECTION4 = 4;
    private static final int SECTION5 = 5;
    private static final int PARAGRAPH = 6;
    private static final int VERBATIM = 7;
    private static final int FIGURE = 8;
    private static final int TABLE = 9;
    private static final int LIST_ITEM = 10;
    private static final int NUMBERED_LIST_ITEM = 11;
    private static final int DEFINITION_LIST_ITEM = 12;
    private static final int HORIZONTAL_RULE = 13;
    private static final int PAGE_BREAK = 14;
    private static final int LIST_BREAK = 15;
    private static final int MACRO = 16;
    private static final String[] TYPE_NAMES;
    private static final char[] SPACES;
    public static final int TAB_WIDTH = 8;
    private String sourceContent;
    private AptSource source;
    private Sink sink;
    private String line;
    private Block block;
    private String blockFileName;
    private int blockLineNumber;
    
    public void parse(final Reader source, final Sink sink) throws AptParseException {
        try {
            try {
                final StringWriter contentWriter = new StringWriter();
                IOUtil.copy(source, contentWriter);
                this.sourceContent = contentWriter.toString();
            }
            catch (IOException e) {
                throw new AptParseException("IOException: " + e.getMessage(), e);
            }
            this.source = new AptReaderSource(new StringReader(this.sourceContent));
            this.sink = sink;
            this.blockFileName = null;
            this.blockLineNumber = -1;
            this.nextLine();
            this.nextBlock(true);
            this.traverseHead();
            this.traverseBody();
            this.source = null;
            this.sink = null;
        }
        catch (AptParseException ape) {
            throw new AptParseException(ape.getMessage(), this.getSourceName(), this.getSourceLineNumber(), ape);
        }
    }
    
    public String getSourceName() {
        return this.blockFileName;
    }
    
    public int getSourceLineNumber() {
        return this.blockLineNumber;
    }
    
    private void traverseHead() throws AptParseException {
        this.sink.head();
        if (this.block != null && this.block.getType() == 0) {
            this.block.traverse();
            this.nextBlock();
        }
        this.sink.head_();
    }
    
    private void traverseBody() throws AptParseException {
        this.sink.body();
        if (this.block != null) {
            this.traverseSectionBlocks();
        }
        while (this.block != null) {
            this.traverseSection(0);
        }
        this.sink.body_();
    }
    
    private void traverseSection(final int level) throws AptParseException {
        if (this.block == null) {
            return;
        }
        final int type = 1 + level;
        this.expectedBlock(type);
        switch (level) {
            case 0: {
                this.sink.section1();
                break;
            }
            case 1: {
                this.sink.section2();
                break;
            }
            case 2: {
                this.sink.section3();
                break;
            }
            case 3: {
                this.sink.section4();
                break;
            }
            case 4: {
                this.sink.section5();
                break;
            }
        }
        this.block.traverse();
        this.nextBlock();
        this.traverseSectionBlocks();
        while (this.block != null && this.block.getType() > type) {
            this.traverseSection(level + 1);
        }
        switch (level) {
            case 0: {
                this.sink.section1_();
                break;
            }
            case 1: {
                this.sink.section2_();
                break;
            }
            case 2: {
                this.sink.section3_();
                break;
            }
            case 3: {
                this.sink.section4_();
                break;
            }
            case 4: {
                this.sink.section5_();
                break;
            }
        }
    }
    
    private void traverseSectionBlocks() throws AptParseException {
    Label_0117:
        while (this.block != null) {
            switch (this.block.getType()) {
                case 6:
                case 7:
                case 8:
                case 9:
                case 13:
                case 14:
                case 16: {
                    this.block.traverse();
                    this.nextBlock();
                    continue;
                }
                case 10: {
                    this.traverseList();
                    continue;
                }
                case 11: {
                    this.traverseNumberedList();
                    continue;
                }
                case 12: {
                    this.traverseDefinitionList();
                    continue;
                }
                case 15: {
                    this.nextBlock();
                    continue;
                }
                default: {
                    break Label_0117;
                }
            }
        }
    }
    
    private void traverseList() throws AptParseException {
        if (this.block == null) {
            return;
        }
        this.expectedBlock(10);
        final int listIndent = this.block.getIndent();
        this.sink.list();
        this.sink.listItem();
        this.block.traverse();
        this.nextBlock();
    Label_0247:
        while (this.block != null) {
            final int blockIndent = this.block.getIndent();
            Label_0241: {
                switch (this.block.getType()) {
                    case 6: {
                        if (blockIndent < listIndent) {
                            break Label_0247;
                        }
                    }
                    case 7:
                    case 8:
                    case 9:
                    case 13:
                    case 14: {
                        this.block.traverse();
                        this.nextBlock();
                        continue;
                    }
                    case 10: {
                        if (blockIndent < listIndent) {
                            break Label_0247;
                        }
                        if (blockIndent > listIndent) {
                            this.traverseList();
                            continue;
                        }
                        this.sink.listItem_();
                        this.sink.listItem();
                        this.block.traverse();
                        this.nextBlock();
                        continue;
                    }
                    case 11: {
                        if (blockIndent < listIndent) {
                            break Label_0247;
                        }
                        this.traverseNumberedList();
                        continue;
                    }
                    case 12: {
                        if (blockIndent < listIndent) {
                            break Label_0247;
                        }
                        this.traverseDefinitionList();
                        continue;
                    }
                    case 15: {
                        if (blockIndent >= listIndent) {
                            this.nextBlock();
                            break Label_0241;
                        }
                        break Label_0241;
                    }
                }
                continue;
            }
            break;
        }
        this.sink.listItem_();
        this.sink.list_();
    }
    
    private void traverseNumberedList() throws AptParseException {
        if (this.block == null) {
            return;
        }
        this.expectedBlock(11);
        final int listIndent = this.block.getIndent();
        this.sink.numberedList(((NumberedListItem)this.block).getNumbering());
        this.sink.numberedListItem();
        this.block.traverse();
        this.nextBlock();
    Label_0255:
        while (this.block != null) {
            final int blockIndent = this.block.getIndent();
            Label_0249: {
                switch (this.block.getType()) {
                    case 6: {
                        if (blockIndent < listIndent) {
                            break Label_0255;
                        }
                    }
                    case 7:
                    case 8:
                    case 9:
                    case 13:
                    case 14: {
                        this.block.traverse();
                        this.nextBlock();
                        continue;
                    }
                    case 10: {
                        if (blockIndent < listIndent) {
                            break Label_0255;
                        }
                        this.traverseList();
                        continue;
                    }
                    case 11: {
                        if (blockIndent < listIndent) {
                            break Label_0255;
                        }
                        if (blockIndent > listIndent) {
                            this.traverseNumberedList();
                            continue;
                        }
                        this.sink.numberedListItem_();
                        this.sink.numberedListItem();
                        this.block.traverse();
                        this.nextBlock();
                        continue;
                    }
                    case 12: {
                        if (blockIndent < listIndent) {
                            break Label_0255;
                        }
                        this.traverseDefinitionList();
                        continue;
                    }
                    case 15: {
                        if (blockIndent >= listIndent) {
                            this.nextBlock();
                            break Label_0249;
                        }
                        break Label_0249;
                    }
                }
                continue;
            }
            break;
        }
        this.sink.numberedListItem_();
        this.sink.numberedList_();
    }
    
    private void traverseDefinitionList() throws AptParseException {
        if (this.block == null) {
            return;
        }
        this.expectedBlock(12);
        final int listIndent = this.block.getIndent();
        this.sink.definitionList();
        this.sink.definitionListItem();
        this.block.traverse();
        this.nextBlock();
    Label_0256:
        while (this.block != null) {
            final int blockIndent = this.block.getIndent();
            Label_0250: {
                switch (this.block.getType()) {
                    case 6: {
                        if (blockIndent < listIndent) {
                            break Label_0256;
                        }
                    }
                    case 7:
                    case 8:
                    case 9:
                    case 13:
                    case 14: {
                        this.block.traverse();
                        this.nextBlock();
                        continue;
                    }
                    case 10: {
                        if (blockIndent < listIndent) {
                            break Label_0256;
                        }
                        this.traverseList();
                        continue;
                    }
                    case 11: {
                        if (blockIndent < listIndent) {
                            break Label_0256;
                        }
                        this.traverseNumberedList();
                        continue;
                    }
                    case 12: {
                        if (blockIndent < listIndent) {
                            break Label_0256;
                        }
                        if (blockIndent > listIndent) {
                            this.traverseDefinitionList();
                            continue;
                        }
                        this.sink.definition_();
                        this.sink.definitionListItem_();
                        this.sink.definitionListItem();
                        this.block.traverse();
                        this.nextBlock();
                        continue;
                    }
                    case 15: {
                        if (blockIndent >= listIndent) {
                            this.nextBlock();
                            break Label_0250;
                        }
                        break Label_0250;
                    }
                }
                continue;
            }
            break;
        }
        this.sink.definition_();
        this.sink.definitionListItem_();
        this.sink.definitionList_();
    }
    
    private void nextLine() throws AptParseException {
        this.line = this.source.getNextLine();
    }
    
    private void nextBlock() throws AptParseException {
        this.nextBlock(false);
    }
    
    private void nextBlock(final boolean firstBlock) throws AptParseException {
        while (this.line != null) {
            final int length = this.line.length();
            int indent = 0;
            int i = 0;
        Label_0120:
            while (i < length) {
                Label_0133: {
                    switch (this.line.charAt(i)) {
                        case ' ': {
                            ++indent;
                            break;
                        }
                        case '\t': {
                            indent += 8;
                            break;
                        }
                        case '~': {
                            if (charAt(this.line, length, i + 1) == '~') {
                                i = length;
                                break Label_0120;
                            }
                            break Label_0133;
                        }
                        default: {
                            break Label_0133;
                        }
                    }
                    ++i;
                    continue;
                }
                this.blockFileName = this.source.getName();
                this.blockLineNumber = this.source.getLineNumber();
                this.block = null;
                switch (this.line.charAt(i)) {
                    case '*': {
                        if (indent != 0) {
                            this.block = new ListItem(indent, this.line);
                            break;
                        }
                        if (charAt(this.line, length, i + 1) == '-' && charAt(this.line, length, i + 2) == '-') {
                            this.block = new Table(indent, this.line);
                            break;
                        }
                        if (charAt(this.line, length, i + 1) != '*') {
                            this.block = new Section2(indent, this.line);
                            break;
                        }
                        if (charAt(this.line, length, i + 2) != '*') {
                            this.block = new Section3(indent, this.line);
                            break;
                        }
                        if (charAt(this.line, length, i + 3) == '*') {
                            this.block = new Section5(indent, this.line);
                            break;
                        }
                        this.block = new Section4(indent, this.line);
                        break;
                    }
                    case '[': {
                        if (charAt(this.line, length, i + 1) == ']') {
                            this.block = new ListBreak(indent, this.line);
                            break;
                        }
                        if (indent == 0) {
                            this.block = new Figure(indent, this.line);
                            break;
                        }
                        if (charAt(this.line, length, i + 1) == '[') {
                            int numbering = 0;
                            switch (charAt(this.line, length, i + 2)) {
                                case 'a': {
                                    numbering = 1;
                                    break;
                                }
                                case 'A': {
                                    numbering = 2;
                                    break;
                                }
                                case 'i': {
                                    numbering = 3;
                                    break;
                                }
                                case 'I': {
                                    numbering = 4;
                                    break;
                                }
                                default: {
                                    numbering = 0;
                                    break;
                                }
                            }
                            this.block = new NumberedListItem(indent, this.line, numbering);
                            break;
                        }
                        this.block = new DefinitionListItem(indent, this.line);
                        break;
                    }
                    case '-': {
                        if (charAt(this.line, length, i + 1) != '-' || charAt(this.line, length, i + 2) != '-') {
                            break;
                        }
                        if (indent == 0) {
                            this.block = new Verbatim(indent, this.line);
                            break;
                        }
                        if (firstBlock) {
                            this.block = new Title(indent, this.line);
                            break;
                        }
                        break;
                    }
                    case '+': {
                        if (indent == 0 && charAt(this.line, length, i + 1) == '-' && charAt(this.line, length, i + 2) == '-') {
                            this.block = new Verbatim(indent, this.line);
                            break;
                        }
                        break;
                    }
                    case '=': {
                        if (indent == 0 && charAt(this.line, length, i + 1) == '=' && charAt(this.line, length, i + 2) == '=') {
                            this.block = new HorizontalRule(indent, this.line);
                            break;
                        }
                        break;
                    }
                    case '\u000e': {
                        if (indent == 0) {
                            this.block = new PageBreak(indent, this.line);
                            break;
                        }
                        break;
                    }
                    case '%': {
                        if (indent == 0 && charAt(this.line, length, i + 1) == '{') {
                            this.block = new MacroBlock(indent, this.line);
                            break;
                        }
                        break;
                    }
                }
                if (this.block == null) {
                    if (indent == 0) {
                        this.block = new Section1(indent, this.line);
                    }
                    else {
                        this.block = new Paragraph(indent, this.line);
                    }
                }
                return;
            }
            if (i == length) {
                this.nextLine();
            }
        }
        this.block = null;
    }
    
    private void expectedBlock(final int type) throws AptParseException {
        final int blockType = this.block.getType();
        if (blockType != type) {
            throw new AptParseException("expected " + AptParser.TYPE_NAMES[type] + ", found " + AptParser.TYPE_NAMES[blockType]);
        }
    }
    
    private static boolean isOctalChar(final char c) {
        return c >= '0' && c <= '7';
    }
    
    private static boolean isHexChar(final char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }
    
    private static char charAt(final String string, final int length, final int i) {
        return (i < length) ? string.charAt(i) : '\0';
    }
    
    private static int skipSpace(final String string, final int length, int i) {
    Label_0048:
        while (i < length) {
            switch (string.charAt(i)) {
                case '\t':
                case ' ': {
                    ++i;
                    continue;
                }
                default: {
                    break Label_0048;
                }
            }
        }
        return i;
    }
    
    private static void doTraverseText(final String text, final int begin, final int end, final Sink sink) throws AptParseException {
        boolean anchor = false;
        boolean link = false;
        boolean italic = false;
        boolean bold = false;
        boolean monospaced = false;
        final StringBuffer buffer = new StringBuffer(end - begin);
        for (int i = begin; i < end; ++i) {
            final char c = text.charAt(i);
            switch (c) {
                case '\\': {
                    if (i + 1 < end) {
                        final char escaped = text.charAt(i + 1);
                        switch (escaped) {
                            case ' ': {
                                ++i;
                                flushTraversed(buffer, sink);
                                sink.nonBreakingSpace();
                                break;
                            }
                            case '\n':
                            case '\r': {
                                ++i;
                                while (i + 1 < end && Character.isWhitespace(text.charAt(i + 1))) {
                                    ++i;
                                }
                                flushTraversed(buffer, sink);
                                sink.lineBreak();
                                break;
                            }
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
                            case '|':
                            case '}':
                            case '~': {
                                ++i;
                                buffer.append(escaped);
                                break;
                            }
                            case 'x': {
                                if (i + 3 < end && isHexChar(text.charAt(i + 2)) && isHexChar(text.charAt(i + 3))) {
                                    int value = 63;
                                    try {
                                        value = Integer.parseInt(text.substring(i + 2, i + 4), 16);
                                    }
                                    catch (NumberFormatException ex) {}
                                    i += 3;
                                    buffer.append((char)value);
                                    break;
                                }
                                buffer.append('\\');
                                break;
                            }
                            case 'u': {
                                if (i + 5 < end && isHexChar(text.charAt(i + 2)) && isHexChar(text.charAt(i + 3)) && isHexChar(text.charAt(i + 4)) && isHexChar(text.charAt(i + 5))) {
                                    int value = 63;
                                    try {
                                        value = Integer.parseInt(text.substring(i + 2, i + 6), 16);
                                    }
                                    catch (NumberFormatException ex2) {}
                                    i += 5;
                                    buffer.append((char)value);
                                    break;
                                }
                                buffer.append('\\');
                                break;
                            }
                            default: {
                                if (isOctalChar(escaped)) {
                                    int octalChars = 1;
                                    if (isOctalChar(charAt(text, end, i + 2))) {
                                        ++octalChars;
                                        if (isOctalChar(charAt(text, end, i + 3))) {
                                            ++octalChars;
                                        }
                                    }
                                    int value2 = 63;
                                    try {
                                        value2 = Integer.parseInt(text.substring(i + 1, i + 1 + octalChars), 8);
                                    }
                                    catch (NumberFormatException ex3) {}
                                    i += octalChars;
                                    buffer.append((char)value2);
                                    break;
                                }
                                buffer.append('\\');
                                break;
                            }
                        }
                        break;
                    }
                    buffer.append('\\');
                    break;
                }
                case '{': {
                    if (anchor || link) {
                        buffer.append(c);
                        break;
                    }
                    if (i + 1 < end && text.charAt(i + 1) == '{') {
                        ++i;
                        link = true;
                        flushTraversed(buffer, sink);
                        String linkAnchor = null;
                        if (i + 1 < end && text.charAt(i + 1) == '{') {
                            ++i;
                            final StringBuffer buf = new StringBuffer();
                            i = skipTraversedLinkAnchor(text, i + 1, end, buf);
                            linkAnchor = buf.toString();
                        }
                        if (linkAnchor == null) {
                            linkAnchor = getTraversedLink(text, i + 1, end);
                        }
                        sink.link(linkAnchor);
                        break;
                    }
                    anchor = true;
                    flushTraversed(buffer, sink);
                    sink.anchor(getTraversedAnchor(text, i + 1, end));
                    break;
                }
                case '}': {
                    if (link && i + 1 < end && text.charAt(i + 1) == '}') {
                        ++i;
                        link = false;
                        flushTraversed(buffer, sink);
                        sink.link_();
                        break;
                    }
                    if (anchor) {
                        anchor = false;
                        flushTraversed(buffer, sink);
                        sink.anchor_();
                        break;
                    }
                    buffer.append(c);
                    break;
                }
                case '<': {
                    if (italic || bold || monospaced) {
                        buffer.append(c);
                        break;
                    }
                    if (i + 1 >= end || text.charAt(i + 1) != '<') {
                        italic = true;
                        flushTraversed(buffer, sink);
                        sink.italic();
                        break;
                    }
                    if (i + 2 < end && text.charAt(i + 2) == '<') {
                        i += 2;
                        monospaced = true;
                        flushTraversed(buffer, sink);
                        sink.monospaced();
                        break;
                    }
                    ++i;
                    bold = true;
                    flushTraversed(buffer, sink);
                    sink.bold();
                    break;
                }
                case '>': {
                    if (monospaced && i + 2 < end && text.charAt(i + 1) == '>' && text.charAt(i + 2) == '>') {
                        i += 2;
                        monospaced = false;
                        flushTraversed(buffer, sink);
                        sink.monospaced_();
                        break;
                    }
                    if (bold && i + 1 < end && text.charAt(i + 1) == '>') {
                        ++i;
                        bold = false;
                        flushTraversed(buffer, sink);
                        sink.bold_();
                        break;
                    }
                    if (italic) {
                        italic = false;
                        flushTraversed(buffer, sink);
                        sink.italic_();
                        break;
                    }
                    buffer.append(c);
                    break;
                }
                default: {
                    if (Character.isWhitespace(c)) {
                        buffer.append(' ');
                        while (i + 1 < end && Character.isWhitespace(text.charAt(i + 1))) {
                            ++i;
                        }
                        break;
                    }
                    buffer.append(c);
                    break;
                }
            }
        }
        if (monospaced) {
            throw new AptParseException("missing '" + AptParser.MONOSPACED_END_MARKUP + "'");
        }
        if (bold) {
            throw new AptParseException("missing '" + AptParser.BOLD_END_MARKUP + "'");
        }
        if (italic) {
            throw new AptParseException("missing '" + AptParser.ITALIC_END_MARKUP + "'");
        }
        if (link) {
            throw new AptParseException("missing '" + AptParser.LINK_END_MARKUP + "'");
        }
        if (anchor) {
            throw new AptParseException("missing '" + AptParser.ANCHOR_END_MARKUP + "'");
        }
        flushTraversed(buffer, sink);
    }
    
    private static void flushTraversed(final StringBuffer buffer, final Sink sink) {
        if (buffer.length() > 0) {
            sink.text(buffer.toString());
            buffer.setLength(0);
        }
    }
    
    private static int skipTraversedLinkAnchor(final String text, final int begin, final int end, final StringBuffer linkAnchor) throws AptParseException {
        int i = 0;
    Label_0095:
        for (i = begin; i < end; ++i) {
            final char c = text.charAt(i);
            switch (c) {
                case '}': {
                    break Label_0095;
                }
                case '\\': {
                    if (i + 1 < end) {
                        ++i;
                        linkAnchor.append(text.charAt(i));
                        break;
                    }
                    linkAnchor.append('\\');
                    break;
                }
                default: {
                    linkAnchor.append(c);
                    break;
                }
            }
        }
        if (i == end) {
            throw new AptParseException("missing '}'");
        }
        return i;
    }
    
    private static String getTraversedLink(final String text, final int begin, final int end) throws AptParseException {
        char previous2 = '{';
        char previous3 = '{';
        int i;
        for (i = begin; i < end; ++i) {
            final char c = text.charAt(i);
            if (c == '}' && previous3 == '}' && previous2 != '\\') {
                break;
            }
            previous2 = previous3;
            previous3 = c;
        }
        if (i == end) {
            throw new AptParseException("missing '{{'");
        }
        return doGetTraversedLink(text, begin, i - 1);
    }
    
    private static String getTraversedAnchor(final String text, final int begin, final int end) throws AptParseException {
        char previous = '{';
        int i;
        for (i = begin; i < end; ++i) {
            final char c = text.charAt(i);
            if (c == '}' && previous != '\\') {
                break;
            }
            previous = c;
        }
        if (i == end) {
            throw new AptParseException("missing '}'");
        }
        return doGetTraversedLink(text, begin, i);
    }
    
    private static String doGetTraversedLink(final String text, final int begin, final int end) throws AptParseException {
        final StringBuffer buffer = new StringBuffer(end - begin);
        final Sink sink = new SinkAdapter() {
            public void lineBreak() {
                buffer.append(' ');
            }
            
            public void nonBreakingSpace() {
                buffer.append(' ');
            }
            
            public void text(final String text) {
                buffer.append(text);
            }
        };
        doTraverseText(text, begin, end, sink);
        return buffer.toString().trim();
    }
    
    private static String replaceAll(final String string, final String oldSub, final String newSub) {
        final StringBuffer replaced = new StringBuffer();
        int oldSubLength;
        int begin;
        int end;
        for (oldSubLength = oldSub.length(), begin = 0; (end = string.indexOf(oldSub, begin)) >= 0; begin = end + oldSubLength) {
            if (end > begin) {
                replaced.append(string.substring(begin, end));
            }
            replaced.append(newSub);
        }
        if (begin < string.length()) {
            replaced.append(string.substring(begin));
        }
        return replaced.toString();
    }
    
    static {
        TYPE_NAMES = new String[] { "TITLE", "SECTION1", "SECTION2", "SECTION3", "SECTION4", "SECTION5", "PARAGRAPH", "VERBATIM", "FIGURE", "TABLE", "LIST_ITEM", "NUMBERED_LIST_ITEM", "DEFINITION_LIST_ITEM", "HORIZONTAL_RULE", "PAGE_BREAK", "LIST_BREAK", "MACRO" };
        SPACES = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
    }
    
    private abstract class Block
    {
        protected int type;
        protected int indent;
        protected String text;
        protected int textLength;
        
        public Block(final AptParser this$0, final int type, final int indent) throws AptParseException {
            this(this$0, type, indent, null);
        }
        
        public Block(final int type, final int indent, final String firstLine) throws AptParseException {
            this.type = type;
            this.indent = indent;
            AptParser.this.nextLine();
            if (firstLine == null) {
                this.text = null;
                this.textLength = 0;
            }
            else {
                final StringBuffer buffer = new StringBuffer(firstLine);
                while (AptParser.this.line != null) {
                    final String l = AptParser.this.line;
                    final int length = l.length();
                    int i = 0;
                    i = skipSpace(l, length, i);
                    if (i == length || (charAt(l, length, i) == '~' && charAt(l, length, i + 1) == '~')) {
                        AptParser.this.nextLine();
                        break;
                    }
                    buffer.append(Markup.EOL);
                    buffer.append(l);
                    AptParser.this.nextLine();
                }
                this.text = buffer.toString();
                this.textLength = this.text.length();
            }
        }
        
        public final int getType() {
            return this.type;
        }
        
        public final int getIndent() {
            return this.indent;
        }
        
        public abstract void traverse() throws AptParseException;
        
        protected void traverseText(final int begin) throws AptParseException {
            this.traverseText(begin, this.text.length());
        }
        
        protected void traverseText(final int begin, final int end) throws AptParseException {
            doTraverseText(this.text, begin, end, AptParser.this.sink);
        }
        
        protected int skipLeadingBullets() {
            int i;
            for (i = this.skipSpaceFrom(0); i < this.textLength && this.text.charAt(i) == '*'; ++i) {}
            return this.skipSpaceFrom(i);
        }
        
        protected int skipFromLeftToRightBracket(int i) throws AptParseException {
            char previous = '[';
            ++i;
            while (i < this.textLength) {
                final char c = this.text.charAt(i);
                if (c == ']' && previous != '\\') {
                    break;
                }
                previous = c;
                ++i;
            }
            if (i == this.textLength) {
                throw new AptParseException("missing ']'");
            }
            return i;
        }
        
        protected final int skipSpaceFrom(final int i) {
            return skipSpace(this.text, this.textLength, i);
        }
    }
    
    private class ListBreak extends Block
    {
        public ListBreak(final int indent, final String firstLine) throws AptParseException {
            super(15, indent);
        }
        
        public void traverse() throws AptParseException {
            throw new AptParseException("internal error: traversing list break");
        }
    }
    
    private class Title extends Block
    {
        public Title(final int indent, final String firstLine) throws AptParseException {
            super(0, indent, firstLine);
        }
        
        public void traverse() throws AptParseException {
            final StringTokenizer lines = new StringTokenizer(this.text, Markup.EOL);
            int separator = -1;
            boolean firstLine = true;
            boolean title = false;
            boolean author = false;
            boolean date = false;
        Label_0299:
            while (lines.hasMoreTokens()) {
                final String line = lines.nextToken().trim();
                final int lineLength = line.length();
                if (charAt(line, lineLength, 0) == '-' && charAt(line, lineLength, 1) == '-' && charAt(line, lineLength, 2) == '-') {
                    switch (separator) {
                        case 0: {
                            if (title) {
                                AptParser.this.sink.title_();
                                break;
                            }
                            throw new AptParseException("missing title");
                        }
                        case 1: {
                            if (author) {
                                AptParser.this.sink.author_();
                                break;
                            }
                            break;
                        }
                        case 2: {
                            break Label_0299;
                        }
                    }
                    ++separator;
                    firstLine = true;
                }
                else {
                    if (firstLine) {
                        firstLine = false;
                        switch (separator) {
                            case 0: {
                                title = true;
                                AptParser.this.sink.title();
                                break;
                            }
                            case 1: {
                                author = true;
                                AptParser.this.sink.author();
                                break;
                            }
                            case 2: {
                                date = true;
                                AptParser.this.sink.date();
                                break;
                            }
                        }
                    }
                    else {
                        AptParser.this.sink.lineBreak();
                    }
                    doTraverseText(line, 0, lineLength, AptParser.this.sink);
                }
            }
            switch (separator) {
                case 0: {
                    if (title) {
                        AptParser.this.sink.title_();
                        break;
                    }
                    throw new AptParseException("missing title");
                }
                case 1: {
                    if (author) {
                        AptParser.this.sink.author_();
                        break;
                    }
                    break;
                }
                case 2: {
                    if (date) {
                        AptParser.this.sink.date_();
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    private class Section extends Block
    {
        public Section(final int type, final int indent, final String firstLine) throws AptParseException {
            super(type, indent, firstLine);
        }
        
        public void traverse() throws AptParseException {
            this.Title();
            this.traverseText(this.skipLeadingBullets());
            this.Title_();
        }
        
        public void Title() {
            AptParser.this.sink.sectionTitle();
        }
        
        public void Title_() {
            AptParser.this.sink.sectionTitle_();
        }
    }
    
    private class Section1 extends Section
    {
        public Section1(final int indent, final String firstLine) throws AptParseException {
            super(1, indent, firstLine);
        }
        
        public void Title() {
            AptParser.this.sink.sectionTitle1();
        }
        
        public void Title_() {
            AptParser.this.sink.sectionTitle1_();
        }
    }
    
    private class Section2 extends Section
    {
        public Section2(final int indent, final String firstLine) throws AptParseException {
            super(2, indent, firstLine);
        }
        
        public void Title() {
            AptParser.this.sink.sectionTitle2();
        }
        
        public void Title_() {
            AptParser.this.sink.sectionTitle2_();
        }
    }
    
    private class Section3 extends Section
    {
        public Section3(final int indent, final String firstLine) throws AptParseException {
            super(3, indent, firstLine);
        }
        
        public void Title() {
            AptParser.this.sink.sectionTitle3();
        }
        
        public void Title_() {
            AptParser.this.sink.sectionTitle3_();
        }
    }
    
    private class Section4 extends Section
    {
        public Section4(final int indent, final String firstLine) throws AptParseException {
            super(4, indent, firstLine);
        }
        
        public void Title() {
            AptParser.this.sink.sectionTitle4();
        }
        
        public void Title_() {
            AptParser.this.sink.sectionTitle4_();
        }
    }
    
    private class Section5 extends Section
    {
        public Section5(final int indent, final String firstLine) throws AptParseException {
            super(5, indent, firstLine);
        }
        
        public void Title() {
            AptParser.this.sink.sectionTitle5();
        }
        
        public void Title_() {
            AptParser.this.sink.sectionTitle5_();
        }
    }
    
    private class Paragraph extends Block
    {
        public Paragraph(final int indent, final String firstLine) throws AptParseException {
            super(6, indent, firstLine);
        }
        
        public void traverse() throws AptParseException {
            AptParser.this.sink.paragraph();
            this.traverseText(this.skipSpaceFrom(0));
            AptParser.this.sink.paragraph_();
        }
    }
    
    private class Verbatim extends Block
    {
        private boolean boxed;
        
        public Verbatim(final int indent, final String firstLine) throws AptParseException {
            super(7, indent, null);
            final StringBuffer buffer = new StringBuffer();
            final char firstChar = firstLine.charAt(0);
            this.boxed = (firstChar == '+');
            while (AptParser.this.line != null) {
                final String l = AptParser.this.line;
                final int length = l.length();
                if (charAt(l, length, 0) == firstChar && charAt(l, length, 1) == '-' && charAt(l, length, 2) == '-') {
                    AptParser.this.nextLine();
                    break;
                }
                int column = 0;
                for (int i = 0; i < length; ++i) {
                    final char c = l.charAt(i);
                    if (c == '\t') {
                        final int prevColumn = column;
                        column = (column + 1 + 8 - 1) / 8 * 8;
                        buffer.append(AptParser.SPACES, 0, column - prevColumn);
                    }
                    else {
                        ++column;
                        buffer.append(c);
                    }
                }
                buffer.append(Markup.EOL);
                AptParser.this.nextLine();
            }
            this.textLength = buffer.length();
            if (this.textLength > 0) {
                buffer.setLength(--this.textLength);
            }
            this.text = buffer.toString();
        }
        
        public void traverse() throws AptParseException {
            AptParser.this.sink.verbatim(this.boxed);
            AptParser.this.sink.text(this.text);
            AptParser.this.sink.verbatim_();
        }
    }
    
    private class Figure extends Block
    {
        public Figure(final int indent, final String firstLine) throws AptParseException {
            super(8, indent, firstLine);
        }
        
        public void traverse() throws AptParseException {
            AptParser.this.sink.figure();
            int i = this.skipFromLeftToRightBracket(0);
            AptParser.this.sink.figureGraphics(this.text.substring(1, i));
            i = this.skipSpaceFrom(i + 1);
            if (i < this.textLength) {
                AptParser.this.sink.figureCaption();
                this.traverseText(i);
                AptParser.this.sink.figureCaption_();
            }
            AptParser.this.sink.figure_();
        }
    }
    
    private class Table extends Block
    {
        public Table(final int indent, final String firstLine) throws AptParseException {
            super(9, indent, firstLine);
        }
        
        public void traverse() throws AptParseException {
            int captionIndex = -1;
            int nextLineIndex = 0;
            int init = 2;
            int[] justification = null;
            int rows = 0;
            int columns = 0;
            StringBuffer[] cells = null;
            boolean[] headers = null;
            AptParser.this.sink.table();
            while (nextLineIndex < this.textLength) {
                int i = this.text.indexOf("*--", nextLineIndex);
                if (i < 0) {
                    captionIndex = nextLineIndex;
                    break;
                }
                i = this.text.indexOf(10, nextLineIndex);
                String line;
                if (i < 0) {
                    line = this.text.substring(nextLineIndex);
                    nextLineIndex = this.textLength;
                }
                else {
                    line = this.text.substring(nextLineIndex, i);
                    nextLineIndex = i + 1;
                }
                final int lineLength = line.length();
                if (line.indexOf("*--") == 0) {
                    if (init == 2) {
                        init = 1;
                        justification = this.parseJustification(line, lineLength);
                        columns = justification.length;
                        cells = new StringBuffer[columns];
                        headers = new boolean[columns];
                        for (i = 0; i < columns; ++i) {
                            cells[i] = new StringBuffer();
                            headers[i] = false;
                        }
                    }
                    else {
                        if (!this.traverseRow(cells, headers)) {
                            continue;
                        }
                        ++rows;
                    }
                }
                else {
                    if (init == 1) {
                        init = 0;
                        final boolean grid = charAt(line, lineLength, 0) == '|';
                        AptParser.this.sink.tableRows(justification, grid);
                    }
                    line = replaceAll(line, "\\|", "\\174");
                    final StringTokenizer cellLines = new StringTokenizer(line, "|", true);
                    i = 0;
                    boolean processedGrid = false;
                    while (cellLines.hasMoreTokens()) {
                        String cellLine = cellLines.nextToken();
                        if ("|".equals(cellLine)) {
                            if (processedGrid) {
                                headers[i] = true;
                            }
                            else {
                                processedGrid = true;
                                headers[i] = false;
                            }
                        }
                        else {
                            processedGrid = false;
                            cellLine = replaceAll(cellLine, "\\ ", "\\240");
                            cellLine = cellLine.trim();
                            final StringBuffer cell = cells[i];
                            if (cellLine.length() > 0) {
                                if (cell.length() > 0) {
                                    cell.append("\\\n");
                                }
                                cell.append(cellLine);
                            }
                            if (++i == columns) {
                                break;
                            }
                            continue;
                        }
                    }
                }
            }
            if (rows == 0) {
                throw new AptParseException("no table rows");
            }
            AptParser.this.sink.tableRows_();
            if (captionIndex >= 0) {
                AptParser.this.sink.tableCaption();
                doTraverseText(this.text, captionIndex, this.textLength, AptParser.this.sink);
                AptParser.this.sink.tableCaption_();
            }
            AptParser.this.sink.table_();
        }
        
        private int[] parseJustification(final String jline, final int lineLength) throws AptParseException {
            int columns = 0;
            for (int i = 2; i < lineLength; ++i) {
                switch (jline.charAt(i)) {
                    case '*':
                    case '+':
                    case ':': {
                        ++columns;
                        break;
                    }
                }
            }
            if (columns == 0) {
                throw new AptParseException("no columns specified");
            }
            final int[] justification = new int[columns];
            columns = 0;
            for (int j = 2; j < lineLength; ++j) {
                switch (jline.charAt(j)) {
                    case '*': {
                        justification[columns++] = 0;
                        break;
                    }
                    case '+': {
                        justification[columns++] = 1;
                        break;
                    }
                    case ':': {
                        justification[columns++] = 2;
                        break;
                    }
                }
            }
            return justification;
        }
        
        private boolean traverseRow(final StringBuffer[] cells, final boolean[] headers) throws AptParseException {
            boolean traversed = false;
            for (int i = 0; i < cells.length; ++i) {
                if (cells[i].length() > 0) {
                    traversed = true;
                    break;
                }
            }
            if (traversed) {
                AptParser.this.sink.tableRow();
                for (int i = 0; i < cells.length; ++i) {
                    final StringBuffer cell = cells[i];
                    if (headers[i]) {
                        AptParser.this.sink.tableHeaderCell();
                    }
                    else {
                        AptParser.this.sink.tableCell();
                    }
                    if (cell.length() > 0) {
                        doTraverseText(cell.toString(), 0, cell.length(), AptParser.this.sink);
                        cell.setLength(0);
                    }
                    if (headers[i]) {
                        AptParser.this.sink.tableHeaderCell_();
                    }
                    else {
                        AptParser.this.sink.tableCell_();
                    }
                }
                AptParser.this.sink.tableRow_();
            }
            return traversed;
        }
    }
    
    private class ListItem extends Block
    {
        public ListItem(final int indent, final String firstLine) throws AptParseException {
            super(10, indent, firstLine);
        }
        
        public void traverse() throws AptParseException {
            AptParser.this.sink.paragraph();
            this.traverseText(this.skipLeadingBullets());
            AptParser.this.sink.paragraph_();
        }
    }
    
    private class NumberedListItem extends Block
    {
        private int numbering;
        
        public NumberedListItem(final int indent, final String firstLine, final int number) throws AptParseException {
            super(11, indent, firstLine);
            this.numbering = number;
        }
        
        public int getNumbering() {
            return this.numbering;
        }
        
        public void traverse() throws AptParseException {
            AptParser.this.sink.paragraph();
            this.traverseText(this.skipItemNumber());
            AptParser.this.sink.paragraph_();
        }
        
        private int skipItemNumber() throws AptParseException {
            int i = this.skipSpaceFrom(0);
            char prevChar = ' ';
            while (i < this.textLength) {
                final char c = this.text.charAt(i);
                if (c == ']' && prevChar == ']') {
                    break;
                }
                prevChar = c;
                ++i;
            }
            if (i == this.textLength) {
                throw new AptParseException("missing ']]'");
            }
            return this.skipSpaceFrom(i + 1);
        }
    }
    
    private class DefinitionListItem extends Block
    {
        public DefinitionListItem(final int indent, final String firstLine) throws AptParseException {
            super(12, indent, firstLine);
        }
        
        public void traverse() throws AptParseException {
            final int i = this.skipSpaceFrom(0);
            int j = this.skipFromLeftToRightBracket(i);
            AptParser.this.sink.definedTerm();
            this.traverseText(i + 1, j);
            AptParser.this.sink.definedTerm_();
            j = this.skipSpaceFrom(j + 1);
            if (j == this.textLength) {
                throw new AptParseException("no definition");
            }
            AptParser.this.sink.definition();
            AptParser.this.sink.paragraph();
            this.traverseText(j);
            AptParser.this.sink.paragraph_();
        }
    }
    
    private class HorizontalRule extends Block
    {
        public HorizontalRule(final int indent, final String firstLine) throws AptParseException {
            super(13, indent);
        }
        
        public void traverse() throws AptParseException {
            AptParser.this.sink.horizontalRule();
        }
    }
    
    private class PageBreak extends Block
    {
        public PageBreak(final int indent, final String firstLine) throws AptParseException {
            super(14, indent);
        }
        
        public void traverse() throws AptParseException {
            AptParser.this.sink.pageBreak();
        }
    }
    
    private class MacroBlock extends Block
    {
        public MacroBlock(final int indent, final String firstLine) throws AptParseException {
            super(16, indent);
            this.text = firstLine;
        }
        
        public void traverse() throws AptParseException {
            if (AptParser.this.secondParsing) {
                return;
            }
            String s = this.text;
            s = s.substring(2, s.length() - 1);
            s = this.escapeForMacro(s);
            final String[] params = StringUtils.split(s, "|");
            final String macroId = params[0];
            final Map parameters = new HashMap();
            for (int i = 1; i < params.length; ++i) {
                final String[] param = StringUtils.split(params[i], "=");
                final String key = this.unescapeForMacro(param[0]);
                final String value = this.unescapeForMacro(param[1]);
                parameters.put(key, value);
            }
            parameters.put("sourceContent", AptParser.this.sourceContent);
            final AptParser aptParser = new AptParser();
            aptParser.setSecondParsing(true);
            parameters.put("parser", aptParser);
            final MacroRequest request = new MacroRequest(parameters, AbstractParser.this.getBasedir());
            try {
                AptParser.this.executeMacro(macroId, request, AptParser.this.sink);
            }
            catch (MacroExecutionException e) {
                throw new AptParseException("Unable to execute macro in the APT document", e);
            }
            catch (MacroNotFoundException e2) {
                throw new AptParseException("Unable to find macro used in the APT document", e2);
            }
        }
        
        private String escapeForMacro(final String s) {
            if (s == null || s.length() < 1) {
                return s;
            }
            String result = s;
            result = StringUtils.replace(result, "\\=", "\u0011");
            result = StringUtils.replace(result, "\\|", "\u0012");
            return result;
        }
        
        private String unescapeForMacro(final String s) {
            if (s == null || s.length() < 1) {
                return s;
            }
            String result = s;
            result = StringUtils.replace(result, "\u0011", "=");
            result = StringUtils.replace(result, "\u0012", "|");
            return result;
        }
    }
}
