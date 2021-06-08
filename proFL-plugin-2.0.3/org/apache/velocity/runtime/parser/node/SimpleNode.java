// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.mudebug.prapr.reloc.commons.lang.builder.ToStringBuilder;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.ParseErrorException;
import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Token;
import org.apache.velocity.runtime.parser.Parser;
import org.apache.velocity.runtime.log.Log;
import org.apache.velocity.runtime.RuntimeServices;

public class SimpleNode implements Node
{
    protected RuntimeServices rsvc;
    protected Log log;
    protected Node parent;
    protected Node[] children;
    protected int id;
    protected Parser parser;
    protected int info;
    public boolean state;
    protected boolean invalid;
    protected Token first;
    protected Token last;
    
    public SimpleNode(final int i) {
        this.rsvc = null;
        this.log = null;
        this.invalid = false;
        this.id = i;
    }
    
    public SimpleNode(final Parser p, final int i) {
        this(i);
        this.parser = p;
    }
    
    public void jjtOpen() {
        this.first = this.parser.getToken(1);
    }
    
    public void jjtClose() {
        this.last = this.parser.getToken(0);
    }
    
    public void setFirstToken(final Token t) {
        this.first = t;
    }
    
    public Token getFirstToken() {
        return this.first;
    }
    
    public Token getLastToken() {
        return this.last;
    }
    
    public void jjtSetParent(final Node n) {
        this.parent = n;
    }
    
    public Node jjtGetParent() {
        return this.parent;
    }
    
    public void jjtAddChild(final Node n, final int i) {
        if (this.children == null) {
            this.children = new Node[i + 1];
        }
        else if (i >= this.children.length) {
            final Node[] c = new Node[i + 1];
            System.arraycopy(this.children, 0, c, 0, this.children.length);
            this.children = c;
        }
        this.children[i] = n;
    }
    
    public Node jjtGetChild(final int i) {
        return this.children[i];
    }
    
    public int jjtGetNumChildren() {
        return (this.children == null) ? 0 : this.children.length;
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object childrenAccept(final ParserVisitor visitor, final Object data) {
        if (this.children != null) {
            for (int i = 0; i < this.children.length; ++i) {
                this.children[i].jjtAccept(visitor, data);
            }
        }
        return data;
    }
    
    public String toString(final String prefix) {
        return prefix + this.toString();
    }
    
    public void dump(final String prefix) {
        System.out.println(this.toString(prefix));
        if (this.children != null) {
            for (int i = 0; i < this.children.length; ++i) {
                final SimpleNode n = (SimpleNode)this.children[i];
                if (n != null) {
                    n.dump(prefix + " ");
                }
            }
        }
    }
    
    public String literal() {
        Token t = this.first;
        final StringBuffer sb = new StringBuffer(t.image);
        while (t != this.last) {
            t = t.next;
            sb.append(t.image);
        }
        return sb.toString();
    }
    
    public Object init(final InternalContextAdapter context, final Object data) throws TemplateInitException {
        this.rsvc = (RuntimeServices)data;
        this.log = this.rsvc.getLog();
        for (int k = this.jjtGetNumChildren(), i = 0; i < k; ++i) {
            this.jjtGetChild(i).init(context, data);
        }
        return data;
    }
    
    public boolean evaluate(final InternalContextAdapter context) throws MethodInvocationException {
        return false;
    }
    
    public Object value(final InternalContextAdapter context) throws MethodInvocationException {
        return null;
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer) throws IOException, MethodInvocationException, ParseErrorException, ResourceNotFoundException {
        for (int k = this.jjtGetNumChildren(), i = 0; i < k; ++i) {
            this.jjtGetChild(i).render(context, writer);
        }
        return true;
    }
    
    public Object execute(final Object o, final InternalContextAdapter context) throws MethodInvocationException {
        return null;
    }
    
    public int getType() {
        return this.id;
    }
    
    public void setInfo(final int info) {
        this.info = info;
    }
    
    public int getInfo() {
        return this.info;
    }
    
    public void setInvalid() {
        this.invalid = true;
    }
    
    public boolean isInvalid() {
        return this.invalid;
    }
    
    public int getLine() {
        return this.first.beginLine;
    }
    
    public int getColumn() {
        return this.first.beginColumn;
    }
    
    public String toString() {
        final StringBuffer tokens = new StringBuffer();
        for (Token t = this.getFirstToken(); t != null; t = t.next) {
            tokens.append("[").append(t.image).append("]");
            if (t.next != null) {
                if (t.equals(this.getLastToken())) {
                    break;
                }
                tokens.append(", ");
            }
        }
        return new ToStringBuilder(this).append("id", this.getType()).append("info", this.getInfo()).append("invalid", this.isInvalid()).append("children", this.jjtGetNumChildren()).append("tokens", tokens).toString();
    }
}
