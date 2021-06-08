// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.mxp1;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MXParserNonValidating extends MXParserCachingStrings
{
    private boolean processDocDecl;
    
    public void setFeature(final String name, final boolean state) throws XmlPullParserException {
        if ("http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(name)) {
            if (this.eventType != 0) {
                throw new XmlPullParserException("process DOCDECL feature can only be changed before parsing", this, null);
            }
            if (!(this.processDocDecl = state)) {}
        }
        else {
            super.setFeature(name, state);
        }
    }
    
    public boolean getFeature(final String name) {
        if ("http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(name)) {
            return this.processDocDecl;
        }
        return super.getFeature(name);
    }
    
    protected char more() throws IOException, XmlPullParserException {
        return super.more();
    }
    
    protected char[] lookuEntityReplacement(final int entitNameLen) throws XmlPullParserException, IOException {
        if (!this.allStringsInterned) {
            final int hash = MXParser.fastHash(this.buf, this.posStart, this.posEnd - this.posStart);
        Label_0129:
            for (int i = this.entityEnd - 1; i >= 0; --i) {
                if (hash == this.entityNameHash[i] && entitNameLen == this.entityNameBuf[i].length) {
                    final char[] entityBuf = this.entityNameBuf[i];
                    for (int j = 0; j < entitNameLen; ++j) {
                        if (this.buf[this.posStart + j] != entityBuf[j]) {
                            continue Label_0129;
                        }
                    }
                    if (this.tokenize) {
                        this.text = this.entityReplacement[i];
                    }
                    return this.entityReplacementBuf[i];
                }
            }
        }
        else {
            this.entityRefName = this.newString(this.buf, this.posStart, this.posEnd - this.posStart);
            for (int k = this.entityEnd - 1; k >= 0; --k) {
                if (this.entityRefName == this.entityName[k]) {
                    if (this.tokenize) {
                        this.text = this.entityReplacement[k];
                    }
                    return this.entityReplacementBuf[k];
                }
            }
        }
        return null;
    }
    
    protected void parseDocdecl() throws XmlPullParserException, IOException {
        final boolean oldTokenize = this.tokenize;
        try {
            char ch = this.more();
            if (ch != 'O') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, null);
            }
            ch = this.more();
            if (ch != 'C') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, null);
            }
            ch = this.more();
            if (ch != 'T') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, null);
            }
            ch = this.more();
            if (ch != 'Y') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, null);
            }
            ch = this.more();
            if (ch != 'P') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, null);
            }
            ch = this.more();
            if (ch != 'E') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, null);
            }
            this.posStart = this.pos;
            ch = this.requireNextS();
            final int nameStart = this.pos;
            ch = this.readName(ch);
            final int nameEnd = this.pos;
            ch = this.skipS(ch);
            if (ch == 'S' || ch == 'P') {
                ch = this.processExternalId(ch);
                ch = this.skipS(ch);
            }
            if (ch == '[') {
                this.processInternalSubset();
            }
            ch = this.skipS(ch);
            if (ch != '>') {
                throw new XmlPullParserException("expected > to finish <[DOCTYPE but got " + this.printable(ch), this, null);
            }
            this.posEnd = this.pos - 1;
        }
        finally {
            this.tokenize = oldTokenize;
        }
    }
    
    protected char processExternalId(final char ch) throws XmlPullParserException, IOException {
        return ch;
    }
    
    protected void processInternalSubset() throws XmlPullParserException, IOException {
        while (true) {
            char ch = this.more();
            if (ch == ']') {
                break;
            }
            if (ch == '%') {
                this.processPEReference();
            }
            else if (this.isS(ch)) {
                ch = this.skipS(ch);
            }
            else {
                this.processMarkupDecl(ch);
            }
        }
    }
    
    protected void processPEReference() throws XmlPullParserException, IOException {
    }
    
    protected void processMarkupDecl(char ch) throws XmlPullParserException, IOException {
        if (ch != '<') {
            throw new XmlPullParserException("expected < for markupdecl in DTD not " + this.printable(ch), this, null);
        }
        ch = this.more();
        if (ch == '?') {
            this.parsePI();
        }
        else {
            if (ch != '!') {
                throw new XmlPullParserException("expected markupdecl in DTD not " + this.printable(ch), this, null);
            }
            ch = this.more();
            if (ch == '-') {
                this.parseComment();
            }
            else {
                ch = this.more();
                if (ch == 'A') {
                    this.processAttlistDecl(ch);
                }
                else if (ch == 'E') {
                    ch = this.more();
                    if (ch == 'L') {
                        this.processElementDecl(ch);
                    }
                    else {
                        if (ch != 'N') {
                            throw new XmlPullParserException("expected ELEMENT or ENTITY after <! in DTD not " + this.printable(ch), this, null);
                        }
                        this.processEntityDecl(ch);
                    }
                }
                else {
                    if (ch != 'N') {
                        throw new XmlPullParserException("expected markupdecl after <! in DTD not " + this.printable(ch), this, null);
                    }
                    this.processNotationDecl(ch);
                }
            }
        }
    }
    
    protected void processElementDecl(char ch) throws XmlPullParserException, IOException {
        ch = this.requireNextS();
        this.readName(ch);
        ch = this.requireNextS();
    }
    
    protected void processAttlistDecl(final char ch) throws XmlPullParserException, IOException {
    }
    
    protected void processEntityDecl(final char ch) throws XmlPullParserException, IOException {
    }
    
    protected void processNotationDecl(final char ch) throws XmlPullParserException, IOException {
    }
    
    protected char readName(char ch) throws XmlPullParserException, IOException {
        if (this.isNameStartChar(ch)) {
            throw new XmlPullParserException("XML name must start with name start character not " + this.printable(ch), this, null);
        }
        while (this.isNameChar(ch)) {
            ch = this.more();
        }
        return ch;
    }
}
