// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.directive;

import java.io.IOException;
import org.apache.velocity.Template;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.app.event.EventHandlerUtil;
import org.apache.velocity.runtime.parser.node.Node;
import java.io.Writer;
import org.apache.velocity.context.InternalContextAdapter;

public class Parse extends InputBase
{
    public String getName() {
        return "parse";
    }
    
    public int getType() {
        return 2;
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer, final Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        if (!context.getAllowRendering()) {
            return true;
        }
        if (node.jjtGetChild(0) == null) {
            this.rsvc.getLog().error("#parse() null argument");
            return false;
        }
        final Object value = node.jjtGetChild(0).value(context);
        if (value == null) {
            this.rsvc.getLog().error("#parse() null argument");
            return false;
        }
        final String sourcearg = value.toString();
        final String arg = EventHandlerUtil.includeEvent(this.rsvc, context, sourcearg, context.getCurrentTemplateName(), this.getName());
        boolean blockinput = false;
        if (arg == null) {
            blockinput = true;
        }
        final Object[] templateStack = context.getTemplateNameStack();
        if (templateStack.length >= this.rsvc.getInt("directive.parse.max.depth", 20)) {
            final StringBuffer path = new StringBuffer();
            for (int i = 0; i < templateStack.length; ++i) {
                path.append(" > " + templateStack[i]);
            }
            this.rsvc.getLog().error("Max recursion depth reached (" + templateStack.length + ')' + " File stack:" + (Object)path);
            return false;
        }
        Template t = null;
        try {
            if (!blockinput) {
                t = this.rsvc.getTemplate(arg, this.getInputEncoding(context));
            }
        }
        catch (ResourceNotFoundException rnfe) {
            this.rsvc.getLog().error("#parse(): cannot find template '" + arg + "', called from template " + context.getCurrentTemplateName() + " at (" + this.getLine() + ", " + this.getColumn() + ")");
            throw rnfe;
        }
        catch (ParseErrorException pee) {
            this.rsvc.getLog().error("#parse(): syntax error in #parse()-ed template '" + arg + "', called from template " + context.getCurrentTemplateName() + " at (" + this.getLine() + ", " + this.getColumn() + ")");
            throw pee;
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            this.rsvc.getLog().error("#parse() : arg = " + arg + '.', e2);
            return false;
        }
        try {
            if (!blockinput) {
                context.pushCurrentTemplateName(arg);
                ((SimpleNode)t.getData()).render(context, writer);
            }
        }
        catch (MethodInvocationException e3) {
            throw e3;
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            this.rsvc.getLog().error("Exception rendering #parse(" + arg + ')', e2);
            return false;
        }
        finally {
            if (!blockinput) {
                context.popCurrentTemplateName();
            }
        }
        return true;
    }
}
