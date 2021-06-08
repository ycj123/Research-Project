// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.exception;

import org.apache.velocity.runtime.parser.ParseException;

public class TemplateInitException extends VelocityException implements ExtendedParseException
{
    private final String templateName;
    private final int col;
    private final int line;
    private static final long serialVersionUID = -4985224672336070621L;
    
    public TemplateInitException(final String msg, final String templateName, final int col, final int line) {
        super(msg);
        this.templateName = templateName;
        this.col = col;
        this.line = line;
    }
    
    public TemplateInitException(final String msg, final ParseException parseException, final String templateName, final int col, final int line) {
        super(msg, parseException);
        this.templateName = templateName;
        this.col = col;
        this.line = line;
    }
    
    public String getTemplateName() {
        return this.templateName;
    }
    
    public int getLineNumber() {
        return this.line;
    }
    
    public int getColumnNumber() {
        return this.col;
    }
}
