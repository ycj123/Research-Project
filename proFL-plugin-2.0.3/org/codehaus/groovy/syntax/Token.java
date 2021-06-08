// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.syntax;

import org.codehaus.groovy.GroovyBugError;

public class Token extends CSTNode
{
    public static final Token NULL;
    public static final Token EOF;
    private int type;
    private int meaning;
    private String text;
    private int startLine;
    private int startColumn;
    
    public Token(final int type, final String text, final int startLine, final int startColumn) {
        this.type = 0;
        this.meaning = 0;
        this.text = "";
        this.startLine = -1;
        this.startColumn = -1;
        this.type = type;
        this.meaning = type;
        this.text = text;
        this.startLine = startLine;
        this.startColumn = startColumn;
    }
    
    private Token() {
        this.type = 0;
        this.meaning = 0;
        this.text = "";
        this.startLine = -1;
        this.startColumn = -1;
    }
    
    public Token dup() {
        final Token token = new Token(this.type, this.text, this.startLine, this.startColumn);
        token.setMeaning(this.meaning);
        return token;
    }
    
    @Override
    public int getMeaning() {
        return this.meaning;
    }
    
    @Override
    public CSTNode setMeaning(final int meaning) {
        this.meaning = meaning;
        return this;
    }
    
    @Override
    public int getType() {
        return this.type;
    }
    
    @Override
    public int size() {
        return 1;
    }
    
    @Override
    public CSTNode get(final int index) {
        if (index > 0) {
            throw new GroovyBugError("attempt to access Token element other than root");
        }
        return this;
    }
    
    @Override
    public Token getRoot() {
        return this;
    }
    
    @Override
    public String getRootText() {
        return this.text;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    @Override
    public int getStartLine() {
        return this.startLine;
    }
    
    @Override
    public int getStartColumn() {
        return this.startColumn;
    }
    
    @Override
    public Reduction asReduction() {
        return new Reduction(this);
    }
    
    public Reduction asReduction(final CSTNode second) {
        final Reduction created = this.asReduction();
        created.add(second);
        return created;
    }
    
    public Reduction asReduction(final CSTNode second, final CSTNode third) {
        final Reduction created = this.asReduction(second);
        created.add(third);
        return created;
    }
    
    public Reduction asReduction(final CSTNode second, final CSTNode third, final CSTNode fourth) {
        final Reduction created = this.asReduction(second, third);
        created.add(fourth);
        return created;
    }
    
    public static Token newKeyword(final String text, final int startLine, final int startColumn) {
        final int type = Types.lookupKeyword(text);
        if (type != 0) {
            return new Token(type, text, startLine, startColumn);
        }
        return null;
    }
    
    public static Token newString(final String text, final int startLine, final int startColumn) {
        return new Token(400, text, startLine, startColumn);
    }
    
    public static Token newIdentifier(final String text, final int startLine, final int startColumn) {
        return new Token(440, text, startLine, startColumn);
    }
    
    public static Token newInteger(final String text, final int startLine, final int startColumn) {
        return new Token(450, text, startLine, startColumn);
    }
    
    public static Token newDecimal(final String text, final int startLine, final int startColumn) {
        return new Token(451, text, startLine, startColumn);
    }
    
    public static Token newSymbol(final int type, final int startLine, final int startColumn) {
        return new Token(type, Types.getText(type), startLine, startColumn);
    }
    
    public static Token newSymbol(final String type, final int startLine, final int startColumn) {
        return new Token(Types.lookupSymbol(type), type, startLine, startColumn);
    }
    
    public static Token newPlaceholder(final int type) {
        final Token token = new Token(0, "", -1, -1);
        token.setMeaning(type);
        return token;
    }
    
    static {
        NULL = new Token();
        EOF = new Token(-1, "", -1, -1);
    }
}
