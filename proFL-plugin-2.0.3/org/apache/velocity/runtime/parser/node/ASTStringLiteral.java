// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import java.io.IOException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import java.io.Writer;
import java.io.StringWriter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.exception.TemplateInitException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.Parser;

public class ASTStringLiteral extends SimpleNode
{
    private boolean interpolate;
    private SimpleNode nodeTree;
    private String image;
    private String interpolateimage;
    private boolean containsLineComment;
    
    public ASTStringLiteral(final int id) {
        super(id);
        this.interpolate = true;
        this.nodeTree = null;
        this.image = "";
        this.interpolateimage = "";
    }
    
    public ASTStringLiteral(final Parser p, final int id) {
        super(p, id);
        this.interpolate = true;
        this.nodeTree = null;
        this.image = "";
        this.interpolateimage = "";
    }
    
    public Object init(final InternalContextAdapter context, final Object data) throws TemplateInitException {
        super.init(context, data);
        this.interpolate = (this.rsvc.getBoolean("runtime.interpolate.string.literals", true) && this.getFirstToken().image.startsWith("\"") && (this.getFirstToken().image.indexOf(36) != -1 || this.getFirstToken().image.indexOf(35) != -1));
        this.image = this.getFirstToken().image.substring(1, this.getFirstToken().image.length() - 1);
        if (!(this.containsLineComment = (this.image.indexOf("##") != -1))) {
            this.interpolateimage = this.image + " ";
        }
        else {
            this.interpolateimage = this.image;
        }
        if (this.interpolate) {
            final BufferedReader br = new BufferedReader(new StringReader(this.interpolateimage));
            try {
                this.nodeTree = this.rsvc.parse(br, (context != null) ? context.getCurrentTemplateName() : "StringLiteral", false);
            }
            catch (ParseException e) {
                throw new TemplateInitException("Problem parsing String literal.", e, (context != null) ? context.getCurrentTemplateName() : "StringLiteral", this.getColumn(), this.getLine());
            }
            this.nodeTree.init(context, this.rsvc);
        }
        return data;
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object value(final InternalContextAdapter context) {
        if (this.interpolate) {
            try {
                final StringWriter writer = new StringWriter();
                this.nodeTree.render(context, writer);
                final String ret = writer.toString();
                if (!this.containsLineComment && ret.length() > 0) {
                    return ret.substring(0, ret.length() - 1);
                }
                return ret;
            }
            catch (ParseErrorException e) {
                this.log.error("Error in interpolating string literal", e);
            }
            catch (MethodInvocationException e2) {
                this.log.error("Error in interpolating string literal", e2);
            }
            catch (ResourceNotFoundException e3) {
                this.log.error("Error in interpolating string literal", e3);
            }
            catch (RuntimeException e4) {
                throw e4;
            }
            catch (IOException e5) {
                this.log.error("Error in interpolating string literal", e5);
            }
        }
        return this.image;
    }
}
