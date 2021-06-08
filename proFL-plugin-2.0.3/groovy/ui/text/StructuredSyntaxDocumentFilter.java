// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.text;

import javax.swing.text.Position;
import java.util.Iterator;
import java.util.regex.Pattern;
import javax.swing.text.StyleContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.Map;
import javax.swing.text.Style;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.SortedSet;
import java.nio.CharBuffer;
import javax.swing.text.Segment;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.DocumentFilter;

public class StructuredSyntaxDocumentFilter extends DocumentFilter
{
    public static final String TAB_REPLACEMENT = "    ";
    private static final MLComparator ML_COMPARATOR;
    protected LexerNode lexer;
    protected DefaultStyledDocument styledDocument;
    private Segment segment;
    private CharBuffer buffer;
    protected SortedSet mlTextRunSet;
    
    private static void checkRegexp(final String regexp) {
        final String checking = regexp.replaceAll("\\\\\\(", "X").replaceAll("\\(\\?", "X");
        final int checked = checking.indexOf(40);
        if (checked > -1) {
            String msg = "Only non-capturing groups allowed:\r\n" + regexp + "\r\n";
            for (int i = 0; i < checked; ++i) {
                msg += " ";
            }
            msg += "^";
            throw new IllegalArgumentException(msg);
        }
    }
    
    public StructuredSyntaxDocumentFilter(final DefaultStyledDocument document) {
        this.lexer = new LexerNode(true);
        this.segment = new Segment();
        this.mlTextRunSet = new TreeSet(StructuredSyntaxDocumentFilter.ML_COMPARATOR);
        this.styledDocument = document;
    }
    
    private int calcBeginParse(int offset) {
        MultiLineRun mlr = this.getMultiLineRun(offset);
        if (mlr != null) {
            offset = mlr.start();
        }
        else {
            offset = this.styledDocument.getParagraphElement(offset).getStartOffset();
            mlr = this.getMultiLineRun(offset);
            offset = ((mlr == null) ? offset : (mlr.end() + 1));
        }
        return offset;
    }
    
    private int calcEndParse(int offset) {
        MultiLineRun mlr = this.getMultiLineRun(offset);
        if (mlr != null) {
            offset = mlr.end();
        }
        else {
            offset = this.styledDocument.getParagraphElement(offset).getEndOffset();
            mlr = this.getMultiLineRun(offset);
            offset = ((mlr == null) ? offset : mlr.end());
        }
        return offset;
    }
    
    public LexerNode createLexerNode() {
        return new LexerNode(false);
    }
    
    private MultiLineRun getMultiLineRun(final int offset) {
        MultiLineRun ml = null;
        if (offset > 0) {
            final Integer os = offset;
            final SortedSet set = this.mlTextRunSet.headSet(os);
            if (!set.isEmpty()) {
                ml = set.last();
                ml = ((ml.end() >= offset) ? ml : null);
            }
        }
        return ml;
    }
    
    public LexerNode getRootNode() {
        return this.lexer;
    }
    
    @Override
    public void insertString(final FilterBypass fb, final int offset, String text, final AttributeSet attrs) throws BadLocationException {
        text = this.replaceMetaCharacters(text);
        fb.insertString(offset, text, attrs);
        this.parseDocument(offset, text.length());
    }
    
    protected void parseDocument(int offset, int length) throws BadLocationException {
        this.styledDocument.getText(0, this.styledDocument.getLength(), this.segment);
        this.buffer = CharBuffer.wrap(this.segment.array).asReadOnlyBuffer();
        if (!this.lexer.isInitialized()) {
            this.lexer.initialize();
            offset = 0;
            length = this.styledDocument.getLength();
        }
        else {
            final int end = offset + length;
            offset = this.calcBeginParse(offset);
            length = this.calcEndParse(end) - offset;
            final SortedSet set = this.mlTextRunSet.subSet(offset, offset + length);
            if (set != null) {
                set.clear();
            }
        }
        this.lexer.parse(this.buffer, offset, length);
    }
    
    @Override
    public void remove(final FilterBypass fb, final int offset, final int length) throws BadLocationException {
        if (offset == 0 && length != fb.getDocument().getLength()) {
            fb.replace(0, length, "\n", this.lexer.defaultStyle);
            this.parseDocument(offset, 2);
            fb.remove(offset, 1);
        }
        else {
            fb.remove(offset, length);
            if (offset + 1 < fb.getDocument().getLength()) {
                this.parseDocument(offset, 1);
            }
            else if (offset - 1 > 0) {
                this.parseDocument(offset - 1, 1);
            }
            else {
                this.mlTextRunSet.clear();
            }
        }
    }
    
    @Override
    public void replace(final FilterBypass fb, final int offset, final int length, String text, final AttributeSet attrs) throws BadLocationException {
        text = this.replaceMetaCharacters(text);
        fb.replace(offset, length, text, attrs);
        this.parseDocument(offset, text.length());
    }
    
    private String replaceMetaCharacters(String string) {
        string = string.replaceAll("\\t", "    ");
        return string;
    }
    
    static {
        ML_COMPARATOR = new MLComparator();
    }
    
    public final class LexerNode
    {
        private Style defaultStyle;
        private Map styleMap;
        private Map children;
        private Matcher matcher;
        private List groupList;
        private boolean initialized;
        private CharBuffer lastBuffer;
        
        LexerNode(final boolean isParent) {
            this.styleMap = new LinkedHashMap();
            this.children = new HashMap();
            this.groupList = new ArrayList();
            final StyleContext sc = StyleContext.getDefaultStyleContext();
            this.defaultStyle = sc.getStyle("default");
        }
        
        private String buildRegexp(final String[] regexps) {
            String regexp = "";
            for (int i = 0; i < regexps.length; ++i) {
                regexp = regexp + "|" + regexps[i];
            }
            return regexp.substring(1);
        }
        
        public Style getDefaultStyle() {
            return this.defaultStyle;
        }
        
        private void initialize() {
            this.matcher = null;
            this.groupList.clear();
            this.groupList.add(null);
            Iterator iter = this.styleMap.keySet().iterator();
            String regexp = "";
            while (iter.hasNext()) {
                final String nextRegexp = iter.next();
                regexp = regexp + "|(" + nextRegexp + ")";
                this.groupList.add(Pattern.compile(nextRegexp).pattern());
            }
            if (!regexp.equals("")) {
                this.matcher = Pattern.compile(regexp.substring(1)).matcher("");
                iter = this.children.values().iterator();
                while (iter.hasNext()) {
                    iter.next().initialize();
                }
            }
            this.initialized = true;
        }
        
        public boolean isInitialized() {
            return this.initialized;
        }
        
        public void parse(final CharBuffer buffer, int offset, final int length) throws BadLocationException {
            final int checkPoint = offset + length;
            if (this.lastBuffer != buffer) {
                this.matcher.reset(buffer);
                this.lastBuffer = buffer;
            }
            int matchEnd = offset;
            Style style = null;
            while (matchEnd < checkPoint && this.matcher.find(offset)) {
                int groupNum = 0;
                while ((offset = this.matcher.start(++groupNum)) == -1) {}
                if (offset != matchEnd) {
                    offset = ((offset > checkPoint) ? checkPoint : offset);
                    StructuredSyntaxDocumentFilter.this.styledDocument.setCharacterAttributes(matchEnd, offset - matchEnd, this.defaultStyle, true);
                    if (offset >= checkPoint) {
                        return;
                    }
                }
                matchEnd = this.matcher.end(groupNum);
                style = this.styleMap.get(this.groupList.get(groupNum));
                StructuredSyntaxDocumentFilter.this.styledDocument.setCharacterAttributes(offset, matchEnd - offset, style, true);
                if (StructuredSyntaxDocumentFilter.this.styledDocument.getParagraphElement(offset).getStartOffset() != StructuredSyntaxDocumentFilter.this.styledDocument.getParagraphElement(matchEnd).getStartOffset()) {
                    final MultiLineRun mlr = new MultiLineRun(offset, matchEnd);
                    StructuredSyntaxDocumentFilter.this.mlTextRunSet.add(mlr);
                }
                final LexerNode node = this.children.get(this.groupList.get(groupNum));
                if (node != null) {
                    node.parse(buffer, offset, matchEnd - offset);
                }
                offset = matchEnd;
            }
            if (matchEnd < checkPoint) {
                StructuredSyntaxDocumentFilter.this.styledDocument.setCharacterAttributes(matchEnd, checkPoint - matchEnd, this.defaultStyle, true);
            }
        }
        
        public void putChild(final String regexp, final LexerNode node) {
            node.defaultStyle = this.styleMap.get(regexp);
            this.children.put(Pattern.compile(regexp).pattern(), node);
            this.initialized = false;
        }
        
        public void putChild(final String[] regexps, final LexerNode node) {
            this.putChild(this.buildRegexp(regexps), node);
        }
        
        public void putStyle(final String regexp, final Style style) {
            checkRegexp(regexp);
            this.styleMap.put(regexp, style);
            this.initialized = false;
        }
        
        public void putStyle(final String[] regexps, final Style style) {
            this.putStyle(this.buildRegexp(regexps), style);
        }
        
        public void removeChild(final String regexp) {
            this.children.remove(regexp);
        }
        
        public void removeStyle(final String regexp) {
            this.styleMap.remove(regexp);
            this.children.remove(regexp);
        }
        
        public void removeStyle(final String[] regexps) {
            this.removeStyle(this.buildRegexp(regexps));
        }
        
        public void setDefaultStyle(final Style style) {
            this.defaultStyle = style;
        }
    }
    
    protected class MultiLineRun
    {
        private Position start;
        private Position end;
        private int delimeterSize;
        
        public MultiLineRun(final StructuredSyntaxDocumentFilter structuredSyntaxDocumentFilter, final int start, final int end) throws BadLocationException {
            this(structuredSyntaxDocumentFilter, start, end, 2);
        }
        
        public MultiLineRun(final int start, final int end, final int delimeterSize) throws BadLocationException {
            if (start > end) {
                final String msg = "Start offset is after end: ";
                throw new BadLocationException(msg, start);
            }
            if (delimeterSize < 1) {
                final String msg = "Delimiters be at least size 1: " + delimeterSize;
                throw new IllegalArgumentException(msg);
            }
            this.start = StructuredSyntaxDocumentFilter.this.styledDocument.createPosition(start);
            this.end = StructuredSyntaxDocumentFilter.this.styledDocument.createPosition(end);
            this.delimeterSize = delimeterSize;
        }
        
        public int getDelimeterSize() {
            return this.delimeterSize;
        }
        
        public int end() {
            return this.end.getOffset();
        }
        
        public int length() {
            return this.end.getOffset() - this.start.getOffset();
        }
        
        public int start() {
            return this.start.getOffset();
        }
        
        @Override
        public String toString() {
            return this.start.toString() + " " + this.end.toString();
        }
    }
    
    private static class MLComparator implements Comparator
    {
        public int compare(final Object obj, final Object obj1) {
            return this.valueOf(obj) - this.valueOf(obj1);
        }
        
        private int valueOf(final Object obj) {
            return (int)((obj instanceof Integer) ? obj : ((obj instanceof MultiLineRun) ? ((MultiLineRun)obj).start() : ((Position)obj).getOffset()));
        }
    }
}
