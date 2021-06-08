// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.runtime.parser.Token;
import org.apache.velocity.util.introspection.VelPropertySet;
import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.app.event.EventHandlerUtil;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.Parser;
import org.apache.velocity.util.introspection.Info;

public class ASTReference extends SimpleNode
{
    private static final int NORMAL_REFERENCE = 1;
    private static final int FORMAL_REFERENCE = 2;
    private static final int QUIET_REFERENCE = 3;
    private static final int RUNT = 4;
    private int referenceType;
    private String nullString;
    private String rootString;
    private boolean escaped;
    private boolean computableReference;
    private boolean logOnNull;
    private String escPrefix;
    private String morePrefix;
    private String identifier;
    private String literal;
    private int numChildren;
    protected Info uberInfo;
    
    public ASTReference(final int id) {
        super(id);
        this.escaped = false;
        this.computableReference = true;
        this.logOnNull = true;
        this.escPrefix = "";
        this.morePrefix = "";
        this.identifier = "";
        this.literal = null;
        this.numChildren = 0;
    }
    
    public ASTReference(final Parser p, final int id) {
        super(p, id);
        this.escaped = false;
        this.computableReference = true;
        this.logOnNull = true;
        this.escPrefix = "";
        this.morePrefix = "";
        this.identifier = "";
        this.literal = null;
        this.numChildren = 0;
    }
    
    public Object jjtAccept(final ParserVisitor visitor, final Object data) {
        return visitor.visit(this, data);
    }
    
    public Object init(final InternalContextAdapter context, final Object data) throws TemplateInitException {
        super.init(context, data);
        this.rootString = this.getRoot();
        this.numChildren = this.jjtGetNumChildren();
        if (this.numChildren > 0) {
            this.identifier = this.jjtGetChild(this.numChildren - 1).getFirstToken().image;
        }
        this.uberInfo = new Info(context.getCurrentTemplateName(), this.getLine(), this.getColumn());
        this.logOnNull = this.rsvc.getBoolean("runtime.log.invalid.references", true);
        return data;
    }
    
    public String getRootString() {
        return this.rootString;
    }
    
    public Object execute(final Object o, final InternalContextAdapter context) throws MethodInvocationException {
        if (this.referenceType == 4) {
            return null;
        }
        Object result = this.getVariableValue(context, this.rootString);
        if (result == null) {
            return EventHandlerUtil.invalidGetMethod(this.rsvc, context, "$" + this.rootString, null, null, this.uberInfo);
        }
        try {
            Object previousResult = result;
            int failedChild = -1;
            for (int i = 0; i < this.numChildren; ++i) {
                previousResult = result;
                result = this.jjtGetChild(i).execute(result, context);
                if (result == null) {
                    failedChild = i;
                    break;
                }
            }
            if (result == null) {
                if (failedChild == -1) {
                    result = EventHandlerUtil.invalidGetMethod(this.rsvc, context, "$" + this.rootString, previousResult, null, this.uberInfo);
                }
                else {
                    final StringBuffer name = new StringBuffer("$").append(this.rootString);
                    for (int j = 0; j <= failedChild; ++j) {
                        final Node node = this.jjtGetChild(j);
                        if (node instanceof ASTMethod) {
                            name.append(".").append(((ASTMethod)node).getMethodName()).append("()");
                        }
                        else {
                            name.append(".").append(node.getFirstToken().image);
                        }
                    }
                    if (this.jjtGetChild(failedChild) instanceof ASTMethod) {
                        final String methodName = ((ASTMethod)this.jjtGetChild(failedChild)).getMethodName();
                        result = EventHandlerUtil.invalidMethod(this.rsvc, context, name.toString(), previousResult, methodName, this.uberInfo);
                    }
                    else {
                        final String property = this.jjtGetChild(failedChild).getFirstToken().image;
                        result = EventHandlerUtil.invalidGetMethod(this.rsvc, context, name.toString(), previousResult, property, this.uberInfo);
                    }
                }
            }
            return result;
        }
        catch (MethodInvocationException mie) {
            this.log.error("Method " + mie.getMethodName() + " threw exception for reference $" + this.rootString + " in template " + context.getCurrentTemplateName() + " at " + " [" + this.getLine() + "," + this.getColumn() + "]");
            mie.setReferenceName(this.rootString);
            throw mie;
        }
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer) throws IOException, MethodInvocationException {
        if (this.referenceType == 4) {
            if (context.getAllowRendering()) {
                writer.write(this.rootString);
            }
            return true;
        }
        Object value = this.execute(null, context);
        if (this.escaped) {
            if (value == null) {
                if (context.getAllowRendering()) {
                    writer.write(this.escPrefix);
                    writer.write("\\");
                    writer.write(this.nullString);
                }
            }
            else if (context.getAllowRendering()) {
                writer.write(this.escPrefix);
                writer.write(this.nullString);
            }
            return true;
        }
        value = EventHandlerUtil.referenceInsert(this.rsvc, context, this.literal(), value);
        String toString = null;
        if (value != null) {
            toString = value.toString();
        }
        if (value == null || toString == null) {
            if (context.getAllowRendering()) {
                writer.write(this.escPrefix);
                writer.write(this.escPrefix);
                writer.write(this.morePrefix);
                writer.write(this.nullString);
            }
            if (this.logOnNull && this.referenceType != 3 && this.log.isInfoEnabled()) {
                this.log.info("Null reference [template '" + context.getCurrentTemplateName() + "', line " + this.getLine() + ", column " + this.getColumn() + "] : " + this.literal() + " cannot be resolved.");
            }
            return true;
        }
        if (context.getAllowRendering()) {
            writer.write(this.escPrefix);
            writer.write(this.morePrefix);
            writer.write(toString);
        }
        return true;
    }
    
    public boolean evaluate(final InternalContextAdapter context) throws MethodInvocationException {
        final Object value = this.execute(null, context);
        return value != null && (!(value instanceof Boolean) || (boolean)value);
    }
    
    public Object value(final InternalContextAdapter context) throws MethodInvocationException {
        return this.computableReference ? this.execute(null, context) : null;
    }
    
    public boolean setValue(final InternalContextAdapter context, final Object value) throws MethodInvocationException {
        if (this.jjtGetNumChildren() == 0) {
            context.put(this.rootString, value);
            return true;
        }
        Object result = this.getVariableValue(context, this.rootString);
        if (result == null) {
            final String msg = "reference set : template = " + context.getCurrentTemplateName() + " [line " + this.getLine() + ",column " + this.getColumn() + "] : " + this.literal() + " is not a valid reference.";
            this.log.error(msg);
            return false;
        }
        for (int i = 0; i < this.numChildren - 1; ++i) {
            result = this.jjtGetChild(i).execute(result, context);
            if (result == null) {
                final String msg2 = "reference set : template = " + context.getCurrentTemplateName() + " [line " + this.getLine() + ",column " + this.getColumn() + "] : " + this.literal() + " is not a valid reference.";
                this.log.error(msg2);
                return false;
            }
        }
        try {
            final VelPropertySet vs = this.rsvc.getUberspect().getPropertySet(result, this.identifier, value, this.uberInfo);
            if (vs == null) {
                return false;
            }
            vs.invoke(result, value);
        }
        catch (InvocationTargetException ite) {
            throw new MethodInvocationException("ASTReference : Invocation of method '" + this.identifier + "' in  " + result.getClass() + " threw exception " + ite.getTargetException().toString(), ite.getTargetException(), this.identifier, context.getCurrentTemplateName(), this.getLine(), this.getColumn());
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            this.log.error("ASTReference setValue() : exception : " + e2 + " template = " + context.getCurrentTemplateName() + " [" + this.getLine() + "," + this.getColumn() + "]");
            return false;
        }
        return true;
    }
    
    private String getRoot() {
        final Token t = this.getFirstToken();
        final int slashbang = t.image.indexOf("\\!");
        if (slashbang != -1) {
            int i = 0;
            final int len = t.image.length();
            i = t.image.indexOf(36);
            if (i == -1) {
                this.log.error("ASTReference.getRoot() : internal error : no $ found for slashbang.");
                this.computableReference = false;
                return this.nullString = t.image;
            }
            while (i < len && t.image.charAt(i) != '\\') {
                ++i;
            }
            final int start = i;
            int count = 0;
            while (i < len && t.image.charAt(i++) == '\\') {
                ++count;
            }
            this.nullString = t.image.substring(0, start);
            this.nullString += t.image.substring(start, start + count - 1);
            this.nullString += t.image.substring(start + count);
            this.computableReference = false;
            return this.nullString;
        }
        else {
            this.escaped = false;
            if (t.image.startsWith("\\")) {
                int i = 0;
                for (int len = t.image.length(); i < len && t.image.charAt(i) == '\\'; ++i) {}
                if (i % 2 != 0) {
                    this.escaped = true;
                }
                if (i > 0) {
                    this.escPrefix = t.image.substring(0, i / 2);
                }
                t.image = t.image.substring(i);
            }
            final int loc1 = t.image.lastIndexOf(36);
            if (loc1 > 0) {
                this.morePrefix += t.image.substring(0, loc1);
                t.image = t.image.substring(loc1);
            }
            this.nullString = this.literal();
            if (t.image.startsWith("$!")) {
                this.referenceType = 3;
                if (!this.escaped) {
                    this.nullString = "";
                }
                if (t.image.startsWith("$!{")) {
                    return t.next.image;
                }
                return t.image.substring(2);
            }
            else {
                if (t.image.equals("${")) {
                    this.referenceType = 2;
                    return t.next.image;
                }
                if (t.image.startsWith("$")) {
                    this.referenceType = 1;
                    return t.image.substring(1);
                }
                this.referenceType = 4;
                return t.image;
            }
        }
    }
    
    public Object getVariableValue(final Context context, final String variable) throws MethodInvocationException {
        return context.get(variable);
    }
    
    public void setLiteral(final String literal) {
        if (this.literal == null) {
            this.literal = literal;
        }
    }
    
    public String literal() {
        if (this.literal != null) {
            return this.literal;
        }
        return super.literal();
    }
}
