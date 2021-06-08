// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import java.text.BreakIterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.List;
import java.util.ArrayList;
import org.codehaus.groovy.groovydoc.GroovyTag;
import java.util.regex.Pattern;
import org.codehaus.groovy.antlr.parser.GroovyTokenTypes;
import org.codehaus.groovy.groovydoc.GroovyDoc;

public class SimpleGroovyDoc implements GroovyDoc, GroovyTokenTypes
{
    private static final Pattern TAG2_PATTERN;
    private static final Pattern TAG3_PATTERN;
    private String name;
    private String commentText;
    private String rawCommentText;
    private String firstSentenceCommentText;
    private int definitionType;
    private boolean deprecated;
    private boolean isScript;
    private GroovyTag[] tags;
    
    public SimpleGroovyDoc(final String name) {
        this.commentText = null;
        this.rawCommentText = "";
        this.firstSentenceCommentText = null;
        this.name = name;
        this.definitionType = 13;
    }
    
    public String name() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return "" + this.getClass() + "(" + this.name + ")";
    }
    
    protected void setCommentText(final String commentText) {
        this.commentText = commentText;
    }
    
    protected void setFirstSentenceCommentText(final String firstSentenceCommentText) {
        this.firstSentenceCommentText = firstSentenceCommentText;
    }
    
    public String commentText() {
        return this.commentText;
    }
    
    public String firstSentenceCommentText() {
        return this.firstSentenceCommentText;
    }
    
    public String getRawCommentText() {
        return this.rawCommentText;
    }
    
    public void setRawCommentText(final String rawCommentText) {
        this.calculateTags(this.rawCommentText = rawCommentText);
    }
    
    public void setScript(final boolean script) {
        this.isScript = script;
    }
    
    private void calculateTags(final String rawCommentText) {
        final String trimmed = rawCommentText.replaceFirst("(?s).*?\\*\\s*@", "@");
        if (trimmed.equals(rawCommentText)) {
            return;
        }
        final String cleaned = trimmed.replaceAll("(?m)^\\s*\\*\\s*([^*]*)$", "$1").trim();
        final String[] split = cleaned.split("(?m)^@");
        final List<GroovyTag> result = new ArrayList<GroovyTag>();
        for (final String s : split) {
            String tagname = null;
            if (s.startsWith("param") || s.startsWith("throws")) {
                final Matcher m = SimpleGroovyDoc.TAG3_PATTERN.matcher(s);
                if (m.find()) {
                    tagname = m.group(1);
                    result.add(new SimpleGroovyTag(tagname, m.group(2), m.group(3)));
                }
            }
            else {
                final Matcher m = SimpleGroovyDoc.TAG2_PATTERN.matcher(s);
                if (m.find()) {
                    tagname = m.group(1);
                    result.add(new SimpleGroovyTag(tagname, null, m.group(2)));
                }
            }
            if ("deprecated".equals(tagname)) {
                this.setDeprecated(true);
            }
        }
        this.tags = result.toArray(new GroovyTag[result.size()]);
    }
    
    public static String calculateFirstSentence(final String raw) {
        String text = raw.replaceAll("(?m)^\\s*\\*", "").trim();
        text = text.replaceFirst("(?ms)\\n\\s*\\n.*", "").trim();
        text = text.replaceFirst("(?ms)\\n\\s*@(see|param|throws|return|author|since|exception|version|deprecated|todo)\\s.*", "").trim();
        final BreakIterator boundary = BreakIterator.getSentenceInstance(Locale.getDefault());
        boundary.setText(text);
        final int start = boundary.first();
        final int end = boundary.next();
        if (start > -1 && end > -1) {
            text = text.substring(start, end);
        }
        return text;
    }
    
    public boolean isClass() {
        return this.definitionType == 13 && !this.isScript;
    }
    
    public boolean isScript() {
        return this.definitionType == 13 && this.isScript;
    }
    
    public boolean isInterface() {
        return this.definitionType == 14;
    }
    
    public boolean isAnnotationType() {
        return this.definitionType == 63;
    }
    
    public boolean isEnum() {
        return this.definitionType == 60;
    }
    
    public String getTypeDescription() {
        if (this.isInterface()) {
            return "Interface";
        }
        if (this.isAnnotationType()) {
            return "Annotation Type";
        }
        if (this.isEnum()) {
            return "Enum";
        }
        return "Class";
    }
    
    public String getTypeSourceDescription() {
        if (this.isInterface()) {
            return "interface";
        }
        if (this.isAnnotationType()) {
            return "@interface";
        }
        if (this.isEnum()) {
            return "enum";
        }
        return "class";
    }
    
    public void setTokenType(final int t) {
        this.definitionType = t;
    }
    
    public int tokenType() {
        return this.definitionType;
    }
    
    public int compareTo(final Object that) {
        if (that instanceof GroovyDoc) {
            return this.name.compareTo(((GroovyDoc)that).name());
        }
        throw new ClassCastException(String.format("Cannot compare object of type %s.", that.getClass()));
    }
    
    public boolean isAnnotationTypeElement() {
        return false;
    }
    
    public boolean isConstructor() {
        return false;
    }
    
    public boolean isEnumConstant() {
        return false;
    }
    
    public boolean isDeprecated() {
        return this.deprecated;
    }
    
    public boolean isError() {
        return false;
    }
    
    public boolean isException() {
        return false;
    }
    
    public boolean isField() {
        return false;
    }
    
    public boolean isIncluded() {
        return false;
    }
    
    public boolean isMethod() {
        return false;
    }
    
    public boolean isOrdinaryClass() {
        return false;
    }
    
    public GroovyTag[] tags() {
        return this.tags;
    }
    
    public void setDeprecated(final boolean deprecated) {
        this.deprecated = deprecated;
    }
    
    static {
        TAG2_PATTERN = Pattern.compile("(?s)([a-z]+)\\s+(.*)");
        TAG3_PATTERN = Pattern.compile("(?s)([a-z]+)\\s+(\\S*)\\s+(.*)");
    }
}
