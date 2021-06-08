// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.directive;

import org.apache.velocity.runtime.parser.Token;
import org.apache.velocity.runtime.parser.ParserVisitor;
import java.util.Map;
import org.apache.velocity.runtime.visitor.VMReferenceMungeVisitor;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import org.apache.velocity.exception.TemplateInitException;
import org.mudebug.prapr.reloc.commons.lang.StringUtils;
import org.apache.velocity.runtime.parser.node.ASTDirective;
import org.apache.velocity.runtime.RuntimeServices;
import java.io.IOException;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.context.VMContext;
import org.apache.velocity.runtime.parser.node.Node;
import java.io.Writer;
import org.apache.velocity.context.InternalContextAdapter;
import java.util.HashMap;
import org.apache.velocity.runtime.parser.node.SimpleNode;

public class VelocimacroProxy extends Directive
{
    private String macroName;
    private String macroBody;
    private String[] argArray;
    private SimpleNode nodeTree;
    private int numMacroArgs;
    private String namespace;
    private boolean init;
    private String[] callingArgs;
    private int[] callingArgTypes;
    private HashMap proxyArgHash;
    private boolean strictArguments;
    
    public VelocimacroProxy() {
        this.macroName = "";
        this.macroBody = "";
        this.argArray = null;
        this.nodeTree = null;
        this.numMacroArgs = 0;
        this.namespace = "";
        this.init = false;
        this.proxyArgHash = new HashMap();
    }
    
    public String getName() {
        return this.macroName;
    }
    
    public int getType() {
        return 2;
    }
    
    public void setName(final String name) {
        this.macroName = name;
    }
    
    public void setArgArray(final String[] arr) {
        this.argArray = arr;
        this.numMacroArgs = this.argArray.length - 1;
    }
    
    public void setNodeTree(final SimpleNode tree) {
        this.nodeTree = tree;
    }
    
    public int getNumArgs() {
        return this.numMacroArgs;
    }
    
    public void setMacrobody(final String mb) {
        this.macroBody = mb;
    }
    
    public void setNamespace(final String ns) {
        this.namespace = ns;
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer, final Node node) throws IOException, MethodInvocationException {
        try {
            if (this.nodeTree != null) {
                if (!this.init) {
                    this.nodeTree.init(context, this.rsvc);
                    this.init = true;
                }
                final VMContext vmc = new VMContext(context, this.rsvc);
                for (int i = 1; i < this.argArray.length; ++i) {
                    final VMProxyArg arg = this.proxyArgHash.get(this.argArray[i]);
                    vmc.addVMProxyArg(arg);
                }
                this.nodeTree.render(vmc, writer);
            }
            else {
                this.rsvc.getLog().error("VM error " + this.macroName + ". Null AST");
            }
        }
        catch (MethodInvocationException e) {
            throw e;
        }
        catch (RuntimeException e2) {
            throw e2;
        }
        catch (Exception e3) {
            this.rsvc.getLog().error("VelocimacroProxy.render() : exception VM = #" + this.macroName + "()", e3);
        }
        return true;
    }
    
    public void init(final RuntimeServices rs, final InternalContextAdapter context, final Node node) throws TemplateInitException {
        super.init(rs, context, node);
        this.strictArguments = rs.getConfiguration().getBoolean("velocimacro.arguments.strict", false);
        final int i = node.jjtGetNumChildren();
        if (this.getNumArgs() == i) {
            this.setupMacro(this.callingArgs = this.getArgArray(node), this.callingArgTypes);
            return;
        }
        for (Node parent = node.jjtGetParent(); parent != null; parent = parent.jjtGetParent()) {
            if (parent instanceof ASTDirective && StringUtils.equals(((ASTDirective)parent).getDirectiveName(), "macro")) {
                return;
            }
        }
        final String errormsg = "VM #" + this.macroName + ": error : too " + ((this.getNumArgs() > i) ? "few" : "many") + " arguments to macro. Wanted " + this.getNumArgs() + " got " + i;
        if (this.strictArguments) {
            throw new TemplateInitException(errormsg, context.getCurrentTemplateName(), 0, 0);
        }
        this.rsvc.getLog().error(errormsg);
    }
    
    public boolean setupMacro(final String[] callArgs, final int[] callArgTypes) {
        this.setupProxyArgs(callArgs, callArgTypes);
        this.parseTree(callArgs);
        return true;
    }
    
    private void parseTree(final String[] callArgs) {
        try {
            final BufferedReader br = new BufferedReader(new StringReader(this.macroBody));
            this.nodeTree = this.rsvc.parse(br, this.namespace, false);
            final HashMap hm = new HashMap();
            for (int i = 1; i < this.argArray.length; ++i) {
                final String arg = callArgs[i - 1];
                if (arg.charAt(0) == '$') {
                    hm.put(this.argArray[i], arg);
                }
            }
            final VMReferenceMungeVisitor v = new VMReferenceMungeVisitor(hm);
            this.nodeTree.jjtAccept(v, null);
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            this.rsvc.getLog().error("VelocimacroManager.parseTree() : exception " + this.macroName, e2);
        }
    }
    
    private void setupProxyArgs(final String[] callArgs, final int[] callArgTypes) {
        for (int i = 1; i < this.argArray.length; ++i) {
            final VMProxyArg arg = new VMProxyArg(this.rsvc, this.argArray[i], callArgs[i - 1], callArgTypes[i - 1]);
            this.proxyArgHash.put(this.argArray[i], arg);
        }
    }
    
    private String[] getArgArray(final Node node) {
        final int numArgs = node.jjtGetNumChildren();
        final String[] args = new String[numArgs];
        this.callingArgTypes = new int[numArgs];
        int i = 0;
        Token t = null;
        Token tLast = null;
        while (i < numArgs) {
            args[i] = "";
            this.callingArgTypes[i] = node.jjtGetChild(i).getType();
            for (t = node.jjtGetChild(i).getFirstToken(), tLast = node.jjtGetChild(i).getLastToken(); t != tLast; t = t.next) {
                final StringBuffer sb = new StringBuffer();
                final String[] array = args;
                final int n = i;
                array[n] = sb.append(array[n]).append(t.image).toString();
            }
            final StringBuffer sb2 = new StringBuffer();
            final String[] array2 = args;
            final int n2 = i;
            array2[n2] = sb2.append(array2[n2]).append(t.image).toString();
            ++i;
        }
        return args;
    }
}
