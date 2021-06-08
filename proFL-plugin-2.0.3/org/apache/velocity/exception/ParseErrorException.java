// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.exception;

import org.apache.velocity.util.introspection.Info;
import org.apache.velocity.runtime.parser.ParseException;

public class ParseErrorException extends VelocityException
{
    private static final long serialVersionUID = -6665197935086306472L;
    private int columnNumber;
    private int lineNumber;
    private String templateName;
    private String invalidSyntax;
    
    public ParseErrorException(final String exceptionMessage) {
        super(exceptionMessage);
        this.columnNumber = -1;
        this.lineNumber = -1;
        this.templateName = "*unset*";
    }
    
    public ParseErrorException(final ParseException pex) {
        super(pex.getMessage());
        this.columnNumber = -1;
        this.lineNumber = -1;
        this.templateName = "*unset*";
        if (pex instanceof ExtendedParseException) {
            final ExtendedParseException xpex = (ExtendedParseException)pex;
            this.columnNumber = xpex.getColumnNumber();
            this.lineNumber = xpex.getLineNumber();
            this.templateName = xpex.getTemplateName();
        }
        else if (pex.currentToken != null && pex.currentToken.next != null) {
            this.columnNumber = pex.currentToken.next.beginColumn;
            this.lineNumber = pex.currentToken.next.beginLine;
        }
    }
    
    public ParseErrorException(final VelocityException pex) {
        super(pex.getMessage());
        this.columnNumber = -1;
        this.lineNumber = -1;
        this.templateName = "*unset*";
        if (pex instanceof ExtendedParseException) {
            final ExtendedParseException xpex = (ExtendedParseException)pex;
            this.columnNumber = xpex.getColumnNumber();
            this.lineNumber = xpex.getLineNumber();
            this.templateName = xpex.getTemplateName();
        }
        else if (pex.getWrappedThrowable() instanceof ParseException) {
            final ParseException pex2 = (ParseException)pex.getWrappedThrowable();
            if (pex2.currentToken != null && pex2.currentToken.next != null) {
                this.columnNumber = pex2.currentToken.next.beginColumn;
                this.lineNumber = pex2.currentToken.next.beginLine;
            }
        }
    }
    
    public ParseErrorException(final String exceptionMessage, final Info info) {
        super(exceptionMessage);
        this.columnNumber = -1;
        this.lineNumber = -1;
        this.templateName = "*unset*";
        this.columnNumber = info.getColumn();
        this.lineNumber = info.getLine();
        this.templateName = info.getTemplateName();
    }
    
    public ParseErrorException(final String exceptionMessage, final Info info, final String invalidSyntax) {
        super(exceptionMessage);
        this.columnNumber = -1;
        this.lineNumber = -1;
        this.templateName = "*unset*";
        this.columnNumber = info.getColumn();
        this.lineNumber = info.getLine();
        this.templateName = info.getTemplateName();
        this.invalidSyntax = invalidSyntax;
    }
    
    public int getColumnNumber() {
        return this.columnNumber;
    }
    
    public int getLineNumber() {
        return this.lineNumber;
    }
    
    public String getTemplateName() {
        return this.templateName;
    }
    
    public String getInvalidSyntax() {
        return this.invalidSyntax;
    }
}
