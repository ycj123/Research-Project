// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import java.lang.reflect.InvocationTargetException;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.app.event.EventHandlerUtil;
import org.apache.velocity.util.introspection.IntrospectionCacheData;
import org.apache.velocity.util.introspection.VelPropertyGet;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;
import org.apache.velocity.util.introspection.Info;

public class ASTIdentifier extends SimpleNode
{
    private String identifier;
    protected Info uberInfo;
    
    public ASTIdentifier(final int id) {
        super(id);
        this.identifier = "";
    }
    
    public ASTIdentifier(final Parser p, final int id) {
        super(p, id);
        this.identifier = "";
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object init(final InternalContextAdapter context, final Object data) throws TemplateInitException {
        super.init(context, data);
        this.identifier = this.getFirstToken().image;
        this.uberInfo = new Info(context.getCurrentTemplateName(), this.getLine(), this.getColumn());
        return data;
    }
    
    public Object execute(final Object o, final InternalContextAdapter context) throws MethodInvocationException {
        VelPropertyGet vg = null;
        try {
            IntrospectionCacheData icd = context.icacheGet(this);
            if (icd != null && o != null && icd.contextData == o.getClass()) {
                vg = (VelPropertyGet)icd.thingy;
            }
            else {
                vg = this.rsvc.getUberspect().getPropertyGet(o, this.identifier, this.uberInfo);
                if (vg != null && vg.isCacheable() && o != null) {
                    icd = new IntrospectionCacheData();
                    icd.contextData = o.getClass();
                    icd.thingy = vg;
                    context.icachePut(this, icd);
                }
            }
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            this.log.error("ASTIdentifier.execute() : identifier = " + this.identifier, e2);
        }
        if (vg == null) {
            return null;
        }
        try {
            return vg.invoke(o);
        }
        catch (InvocationTargetException ite) {
            final Throwable t = ite.getTargetException();
            if (t instanceof Exception) {
                try {
                    return EventHandlerUtil.methodException(this.rsvc, context, o.getClass(), vg.getMethodName(), (Exception)t);
                }
                catch (Exception e3) {
                    throw new MethodInvocationException("Invocation of method '" + vg.getMethodName() + "'" + " in  " + o.getClass() + " threw exception " + ite.getTargetException().toString(), ite.getTargetException(), vg.getMethodName(), context.getCurrentTemplateName(), this.getLine(), this.getColumn());
                }
            }
            throw new MethodInvocationException("Invocation of method '" + vg.getMethodName() + "'" + " in  " + o.getClass() + " threw exception " + ite.getTargetException().toString(), ite.getTargetException(), vg.getMethodName(), context.getCurrentTemplateName(), this.getLine(), this.getColumn());
        }
        catch (IllegalArgumentException iae) {
            return null;
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            this.log.error("ASTIdentifier() : exception invoking method for identifier '" + this.identifier + "' in " + o.getClass() + " : " + e2);
            return null;
        }
    }
}
