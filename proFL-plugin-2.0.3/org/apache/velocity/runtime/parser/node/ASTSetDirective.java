// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.MethodInvocationException;
import java.io.IOException;
import org.apache.velocity.app.event.EventHandlerUtil;
import java.io.Writer;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;
import org.apache.velocity.util.introspection.Info;

public class ASTSetDirective extends SimpleNode
{
    private String leftReference;
    private Node right;
    private ASTReference left;
    boolean logOnNull;
    protected Info uberInfo;
    
    public ASTSetDirective(final int id) {
        super(id);
        this.leftReference = "";
        this.right = null;
        this.left = null;
        this.logOnNull = false;
    }
    
    public ASTSetDirective(final Parser p, final int id) {
        super(p, id);
        this.leftReference = "";
        this.right = null;
        this.left = null;
        this.logOnNull = false;
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object init(final InternalContextAdapter context, final Object data) throws TemplateInitException {
        super.init(context, data);
        this.uberInfo = new Info(context.getCurrentTemplateName(), this.getLine(), this.getColumn());
        this.right = this.getRightHandSide();
        this.left = this.getLeftHandSide();
        this.logOnNull = this.rsvc.getBoolean("runtime.log.invalid.references", true);
        this.leftReference = this.left.getFirstToken().image.substring(1);
        return data;
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer) throws IOException, MethodInvocationException {
        final Object value = this.right.value(context);
        if (!this.rsvc.getBoolean("directive.set.null.allowed", false) && value == null) {
            if (this.logOnNull) {
                final boolean doit = EventHandlerUtil.shouldLogOnNullSet(this.rsvc, context, this.left.literal(), this.right.literal());
                if (doit && this.log.isInfoEnabled()) {
                    this.log.info("RHS of #set statement is null. Context will not be modified. " + context.getCurrentTemplateName() + " [line " + this.getLine() + ", column " + this.getColumn() + "]");
                }
            }
            String rightReference = null;
            if (this.right instanceof ASTExpression) {
                rightReference = ((ASTExpression)this.right).getLastToken().image;
            }
            EventHandlerUtil.invalidSetMethod(this.rsvc, context, this.leftReference, rightReference, this.uberInfo);
            return false;
        }
        if (value == null) {
            String rightReference = null;
            if (this.right instanceof ASTExpression) {
                rightReference = ((ASTExpression)this.right).getLastToken().image;
            }
            EventHandlerUtil.invalidSetMethod(this.rsvc, context, this.leftReference, rightReference, this.uberInfo);
            context.remove(this.leftReference);
            return false;
        }
        if (this.left.jjtGetNumChildren() == 0) {
            context.put(this.leftReference, value);
        }
        else {
            this.left.setValue(context, value);
        }
        return true;
    }
    
    private ASTReference getLeftHandSide() {
        return (ASTReference)this.jjtGetChild(0);
    }
    
    private Node getRightHandSide() {
        return this.jjtGetChild(1);
    }
}
