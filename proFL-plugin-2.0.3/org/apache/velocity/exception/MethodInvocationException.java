// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.exception;

import org.mudebug.prapr.reloc.commons.lang.StringUtils;

public class MethodInvocationException extends VelocityException implements ExtendedParseException
{
    private static final long serialVersionUID = 7305685093478106342L;
    private String referenceName;
    private final String methodName;
    private final int lineNumber;
    private final int columnNumber;
    private final String templateName;
    
    public MethodInvocationException(final String message, final Throwable e, final String methodName, final String templateName, final int lineNumber, final int columnNumber) {
        super(message, e);
        this.referenceName = "";
        this.methodName = methodName;
        this.templateName = templateName;
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }
    
    public String getMethodName() {
        return this.methodName;
    }
    
    public void setReferenceName(final String ref) {
        this.referenceName = ref;
    }
    
    public String getReferenceName() {
        return this.referenceName;
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
    
    public String getMessage() {
        final StringBuffer message = new StringBuffer();
        message.append(super.getMessage());
        message.append(" @ ");
        message.append(StringUtils.isNotEmpty(this.templateName) ? this.templateName : "<unknown template>");
        message.append("[").append(this.lineNumber).append(",").append(this.columnNumber).append("]");
        return message.toString();
    }
}
