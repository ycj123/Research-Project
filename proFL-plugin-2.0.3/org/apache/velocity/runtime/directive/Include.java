// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.directive;

import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.app.event.EventHandlerUtil;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.MethodInvocationException;
import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.RuntimeServices;

public class Include extends InputBase
{
    private String outputMsgStart;
    private String outputMsgEnd;
    
    public Include() {
        this.outputMsgStart = "";
        this.outputMsgEnd = "";
    }
    
    public String getName() {
        return "include";
    }
    
    public int getType() {
        return 2;
    }
    
    public void init(final RuntimeServices rs, final InternalContextAdapter context, final Node node) throws TemplateInitException {
        super.init(rs, context, node);
        this.outputMsgStart = this.rsvc.getString("directive.include.output.errormsg.start");
        this.outputMsgStart += " ";
        this.outputMsgEnd = this.rsvc.getString("directive.include.output.errormsg.end");
        this.outputMsgEnd = " " + this.outputMsgEnd;
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer, final Node node) throws IOException, MethodInvocationException, ResourceNotFoundException {
        for (int argCount = node.jjtGetNumChildren(), i = 0; i < argCount; ++i) {
            final Node n = node.jjtGetChild(i);
            if (n.getType() == 7 || n.getType() == 16) {
                if (!this.renderOutput(n, context, writer)) {
                    this.outputErrorToStream(writer, "error with arg " + i + " please see log.");
                }
            }
            else {
                this.rsvc.getLog().error("#include() invalid argument type: " + n.toString());
                this.outputErrorToStream(writer, "error with arg " + i + " please see log.");
            }
        }
        return true;
    }
    
    private boolean renderOutput(final Node node, final InternalContextAdapter context, final Writer writer) throws IOException, MethodInvocationException, ResourceNotFoundException {
        if (node == null) {
            this.rsvc.getLog().error("#include() null argument");
            return false;
        }
        final Object value = node.value(context);
        if (value == null) {
            this.rsvc.getLog().error("#include()  null argument");
            return false;
        }
        final String sourcearg = value.toString();
        final String arg = EventHandlerUtil.includeEvent(this.rsvc, context, sourcearg, context.getCurrentTemplateName(), this.getName());
        boolean blockinput = false;
        if (arg == null) {
            blockinput = true;
        }
        Resource resource = null;
        try {
            if (!blockinput) {
                resource = this.rsvc.getContent(arg, this.getInputEncoding(context));
            }
        }
        catch (ResourceNotFoundException rnfe) {
            this.rsvc.getLog().error("#include(): cannot find resource '" + arg + "', called from template " + context.getCurrentTemplateName() + " at (" + this.getLine() + ", " + this.getColumn() + ")");
            throw rnfe;
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            this.rsvc.getLog().error("#include(): arg = '" + arg + "', called from template " + context.getCurrentTemplateName() + " at (" + this.getLine() + ", " + this.getColumn() + ')', e2);
        }
        if (blockinput) {
            return true;
        }
        if (resource == null) {
            return false;
        }
        writer.write((String)resource.getData());
        return true;
    }
    
    private void outputErrorToStream(final Writer writer, final String msg) throws IOException {
        if (this.outputMsgStart != null && this.outputMsgEnd != null) {
            writer.write(this.outputMsgStart);
            writer.write(msg);
            writer.write(this.outputMsgEnd);
        }
    }
}
