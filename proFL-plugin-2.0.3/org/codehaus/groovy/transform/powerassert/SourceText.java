// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform.powerassert;

import org.codehaus.groovy.ast.ASTNode;
import java.util.ArrayList;
import org.codehaus.groovy.control.Janitor;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.stmt.AssertStatement;
import java.util.List;

public class SourceText
{
    private final int firstLine;
    private String normalizedText;
    private final List<Integer> lineOffsets;
    private final List<Integer> textOffsets;
    
    public SourceText(final AssertStatement stat, final SourceUnit sourceUnit, final Janitor janitor) {
        this.lineOffsets = new ArrayList<Integer>();
        this.textOffsets = new ArrayList<Integer>();
        if (!this.hasPlausibleSourcePosition(stat)) {
            throw new SourceTextNotAvailableException(stat, sourceUnit, "Invalid source position");
        }
        this.firstLine = stat.getLineNumber();
        this.textOffsets.add(0);
        this.normalizedText = "";
        for (int line = stat.getLineNumber(); line <= stat.getLastLineNumber(); ++line) {
            String lineText = sourceUnit.getSample(line, 0, janitor);
            if (lineText == null) {
                throw new SourceTextNotAvailableException(stat, sourceUnit, "SourceUnit.getSample() returned null");
            }
            if (line == stat.getLastLineNumber()) {
                lineText = lineText.substring(0, stat.getLastColumnNumber() - 1);
            }
            if (line == stat.getLineNumber()) {
                lineText = lineText.substring(stat.getColumnNumber() - 1);
                this.lineOffsets.add(stat.getColumnNumber() - 1);
            }
            else {
                this.lineOffsets.add(this.countLeadingWhitespace(lineText));
            }
            lineText = lineText.trim();
            if (line != stat.getLastLineNumber() && lineText.length() > 0) {
                lineText += ' ';
            }
            this.normalizedText += lineText;
            this.textOffsets.add(this.normalizedText.length());
        }
    }
    
    public String getNormalizedText() {
        return this.normalizedText;
    }
    
    public int getNormalizedColumn(final int line, final int column) {
        final int deltaLine = line - this.firstLine;
        if (deltaLine < 0) {
            return -1;
        }
        final int deltaColumn = column - this.lineOffsets.get(deltaLine);
        if (deltaColumn < 0) {
            return -1;
        }
        return this.textOffsets.get(deltaLine) + deltaColumn;
    }
    
    public int getNormalizedColumn(final String str, final int line, final int column) {
        return this.getNormalizedText().lastIndexOf(str, this.getNormalizedColumn(line, column) - 1 - str.length()) + 1;
    }
    
    private boolean hasPlausibleSourcePosition(final ASTNode node) {
        return node.getLineNumber() > 0 && node.getColumnNumber() > 0 && node.getLastLineNumber() >= node.getLineNumber() && node.getLastColumnNumber() > ((node.getLineNumber() == node.getLastLineNumber()) ? node.getColumnNumber() : 0);
    }
    
    private int countLeadingWhitespace(final String lineText) {
        int result;
        for (result = 0; result < lineText.length() && Character.isWhitespace(lineText.charAt(result)); ++result) {}
        return result;
    }
}
