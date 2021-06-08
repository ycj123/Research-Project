// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.mudebug.prapr.reloc.commons.lang.builder.ToStringBuilder;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.MethodInvocationException;
import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.util.ExceptionUtils;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;
import org.apache.velocity.runtime.directive.Directive;

public class ASTDirective extends SimpleNode
{
    private Directive directive;
    private String directiveName;
    private boolean isDirective;
    
    public ASTDirective(final int id) {
        super(id);
        this.directive = null;
        this.directiveName = "";
    }
    
    public ASTDirective(final Parser p, final int id) {
        super(p, id);
        this.directive = null;
        this.directiveName = "";
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object init(final InternalContextAdapter context, final Object data) throws TemplateInitException {
        super.init(context, data);
        if (this.parser.isDirective(this.directiveName)) {
            this.isDirective = true;
            try {
                this.directive = (Directive)this.parser.getDirective(this.directiveName).getClass().newInstance();
            }
            catch (InstantiationException e) {
                throw ExceptionUtils.createRuntimeException("Couldn't initialize directive of class " + this.parser.getDirective(this.directiveName).getClass().getName(), e);
            }
            catch (IllegalAccessException e2) {
                throw ExceptionUtils.createRuntimeException("Couldn't initialize directive of class " + this.parser.getDirective(this.directiveName).getClass().getName(), e2);
            }
            this.directive.init(this.rsvc, context, this);
            this.directive.setLocation(this.getLine(), this.getColumn());
        }
        else if (this.rsvc.isVelocimacro(this.directiveName, context.getCurrentTemplateName())) {
            this.isDirective = true;
            this.directive = this.rsvc.getVelocimacro(this.directiveName, context.getCurrentTemplateName());
            try {
                this.directive.init(this.rsvc, context, this);
            }
            catch (TemplateInitException die) {
                throw new TemplateInitException(die.getMessage(), (ParseException)die.getWrappedThrowable(), die.getTemplateName(), die.getColumnNumber() + this.getColumn(), die.getLineNumber() + this.getLine());
            }
            this.directive.setLocation(this.getLine(), this.getColumn());
        }
        else {
            this.isDirective = false;
        }
        return data;
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer) throws IOException, MethodInvocationException, ResourceNotFoundException, ParseErrorException {
        if (this.isDirective) {
            this.directive.render(context, writer, this);
        }
        else if (context.getAllowRendering()) {
            writer.write("#");
            writer.write(this.directiveName);
        }
        return true;
    }
    
    public void setDirectiveName(final String str) {
        this.directiveName = str;
    }
    
    public String getDirectiveName() {
        return this.directiveName;
    }
    
    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString()).append("directiveName", this.getDirectiveName()).toString();
    }
}
