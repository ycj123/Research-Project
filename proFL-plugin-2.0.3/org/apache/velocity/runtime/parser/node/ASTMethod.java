// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import java.lang.reflect.InvocationTargetException;
import org.apache.velocity.app.event.EventHandlerUtil;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.util.introspection.IntrospectionCacheData;
import org.apache.velocity.util.introspection.VelMethod;
import org.mudebug.prapr.reloc.commons.lang.ArrayUtils;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;
import org.apache.velocity.util.introspection.Info;

public class ASTMethod extends SimpleNode
{
    private String methodName;
    private int paramCount;
    protected Info uberInfo;
    
    public ASTMethod(final int id) {
        super(id);
        this.methodName = "";
        this.paramCount = 0;
    }
    
    public ASTMethod(final Parser p, final int id) {
        super(p, id);
        this.methodName = "";
        this.paramCount = 0;
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object init(final InternalContextAdapter context, final Object data) throws TemplateInitException {
        super.init(context, data);
        this.uberInfo = new Info(context.getCurrentTemplateName(), this.getLine(), this.getColumn());
        this.methodName = this.getFirstToken().image;
        this.paramCount = this.jjtGetNumChildren() - 1;
        return data;
    }
    
    public Object execute(final Object o, final InternalContextAdapter context) throws MethodInvocationException {
        VelMethod method = null;
        final Object[] params = new Object[this.paramCount];
        try {
            final Class[] paramClasses = (this.paramCount > 0) ? new Class[this.paramCount] : ArrayUtils.EMPTY_CLASS_ARRAY;
            for (int j = 0; j < this.paramCount; ++j) {
                params[j] = this.jjtGetChild(j + 1).value(context);
                if (params[j] != null) {
                    paramClasses[j] = params[j].getClass();
                }
            }
            final MethodCacheKey mck = new MethodCacheKey(this.methodName, paramClasses);
            IntrospectionCacheData icd = context.icacheGet(mck);
            if (icd != null && o != null && icd.contextData == o.getClass()) {
                method = (VelMethod)icd.thingy;
            }
            else {
                method = this.rsvc.getUberspect().getMethod(o, this.methodName, params, new Info(context.getCurrentTemplateName(), this.getLine(), this.getColumn()));
                if (method != null && o != null) {
                    icd = new IntrospectionCacheData();
                    icd.contextData = o.getClass();
                    icd.thingy = method;
                    context.icachePut(mck, icd);
                }
            }
            if (method == null) {
                return null;
            }
        }
        catch (MethodInvocationException mie) {
            throw mie;
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            this.log.error("ASTMethod.execute() : exception from introspection", e2);
            return null;
        }
        try {
            final Object obj = method.invoke(o, params);
            if (obj == null && method.getReturnType() == Void.TYPE) {
                return "";
            }
            return obj;
        }
        catch (InvocationTargetException ite) {
            final Throwable t = ite.getTargetException();
            if (t instanceof Exception) {
                try {
                    return EventHandlerUtil.methodException(this.rsvc, context, o.getClass(), this.methodName, (Exception)t);
                }
                catch (Exception e3) {
                    throw new MethodInvocationException("Invocation of method '" + this.methodName + "' in  " + o.getClass() + " threw exception " + e3.toString(), e3, this.methodName, context.getCurrentTemplateName(), this.getLine(), this.getColumn());
                }
            }
            throw new MethodInvocationException("Invocation of method '" + this.methodName + "' in  " + o.getClass() + " threw exception " + ite.getTargetException().toString(), ite.getTargetException(), this.methodName, context.getCurrentTemplateName(), this.getLine(), this.getColumn());
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            this.log.error("ASTMethod.execute() : exception invoking method '" + this.methodName + "' in " + o.getClass(), e2);
            return null;
        }
    }
    
    public String getMethodName() {
        return this.methodName;
    }
    
    public static class MethodCacheKey
    {
        private final String methodName;
        private final Class[] params;
        
        public MethodCacheKey(final String methodName, final Class[] params) {
            this.methodName = ((methodName != null) ? methodName : "");
            this.params = ((params != null) ? params : ArrayUtils.EMPTY_CLASS_ARRAY);
        }
        
        public boolean equals(final Object o) {
            if (o instanceof MethodCacheKey) {
                final MethodCacheKey other = (MethodCacheKey)o;
                if (this.params.length == other.params.length && this.methodName.equals(other.methodName)) {
                    for (int i = 0; i < this.params.length; ++i) {
                        if (this.params[i] == null) {
                            if (this.params[i] != other.params[i]) {
                                return false;
                            }
                        }
                        else if (!this.params[i].equals(other.params[i])) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
        
        public int hashCode() {
            int result = 17;
            for (int i = 0; i < this.params.length; ++i) {
                final Class param = this.params[i];
                if (param != null) {
                    result = result * 37 + param.hashCode();
                }
            }
            result = result * 37 + this.methodName.hashCode();
            return result;
        }
    }
}
