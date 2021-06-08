// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser;

import org.apache.velocity.exception.ExtendedParseException;

public class TemplateParseException extends ParseException implements ExtendedParseException
{
    private static final long serialVersionUID = -3146323135623083918L;
    private final String templateName;
    
    public TemplateParseException(final Token currentTokenVal, final int[][] expectedTokenSequencesVal, final String[] tokenImageVal, final String templateNameVal) {
        super(currentTokenVal, expectedTokenSequencesVal, tokenImageVal);
        this.templateName = templateNameVal;
    }
    
    public TemplateParseException(final Token currentTokenVal, final int[][] expectedTokenSequencesVal, final String[] tokenImageVal) {
        super(currentTokenVal, expectedTokenSequencesVal, tokenImageVal);
        this.templateName = "*unset*";
    }
    
    public TemplateParseException() {
        this.templateName = "*unset*";
    }
    
    public TemplateParseException(final String message) {
        super(message);
        this.templateName = "*unset*";
    }
    
    public String getTemplateName() {
        return this.templateName;
    }
    
    public int getLineNumber() {
        if (this.currentToken != null && this.currentToken.next != null) {
            return this.currentToken.next.beginLine;
        }
        return -1;
    }
    
    public int getColumnNumber() {
        if (this.currentToken != null && this.currentToken.next != null) {
            return this.currentToken.next.beginColumn;
        }
        return -1;
    }
    
    public String getMessage() {
        if (!this.specialConstructor) {
            final StringBuffer sb = new StringBuffer(super.getMessage());
            this.appendTemplateInfo(sb);
            return sb.toString();
        }
        int maxSize = 0;
        final StringBuffer expected = new StringBuffer();
        for (int i = 0; i < this.expectedTokenSequences.length; ++i) {
            if (maxSize < this.expectedTokenSequences[i].length) {
                maxSize = this.expectedTokenSequences[i].length;
            }
            for (int j = 0; j < this.expectedTokenSequences[i].length; ++j) {
                expected.append(this.tokenImage[this.expectedTokenSequences[i][j]]).append(" ");
            }
            if (this.expectedTokenSequences[i][this.expectedTokenSequences[i].length - 1] != 0) {
                expected.append("...");
            }
            expected.append(this.eol).append("    ");
        }
        final StringBuffer retval = new StringBuffer("Encountered \"");
        Token tok = this.currentToken.next;
        for (int k = 0; k < maxSize; ++k) {
            if (k != 0) {
                retval.append(" ");
            }
            if (tok.kind == 0) {
                retval.append(this.tokenImage[0]);
                break;
            }
            retval.append(this.add_escapes(tok.image));
            tok = tok.next;
        }
        retval.append("\"");
        this.appendTemplateInfo(retval);
        if (this.expectedTokenSequences.length == 1) {
            retval.append("Was expecting:").append(this.eol).append("    ");
        }
        else {
            retval.append("Was expecting one of:").append(this.eol).append("    ");
        }
        retval.append(expected.toString());
        return retval.toString();
    }
    
    protected void appendTemplateInfo(final StringBuffer sb) {
        sb.append(" at line ").append(this.getLineNumber()).append(", column ").append(this.getColumnNumber());
        if (this.getTemplateName() != null) {
            sb.append(" of ").append(this.getTemplateName());
        }
        else {
            sb.append(".");
        }
        sb.append(this.eol);
    }
}
