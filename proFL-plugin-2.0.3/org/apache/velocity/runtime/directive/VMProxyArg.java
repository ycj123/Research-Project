// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.directive;

import org.apache.velocity.runtime.parser.ParserTreeConstants;
import org.apache.velocity.context.Context;
import org.apache.velocity.context.InternalContextAdapterImpl;
import org.apache.velocity.VelocityContext;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.Writer;
import java.io.StringWriter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.node.ASTReference;
import org.apache.velocity.runtime.log.Log;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.parser.node.SimpleNode;

public class VMProxyArg
{
    private static final int GENERALSTATIC = -1;
    private int type;
    private SimpleNode nodeTree;
    private Object staticObject;
    private int numTreeChildren;
    private String contextReference;
    private String callerReference;
    private String singleLevelRef;
    private boolean constant;
    private RuntimeServices rsvc;
    private Log log;
    
    public VMProxyArg(final RuntimeServices rs, final String contextRef, final String callerRef, final int t) {
        this.type = 0;
        this.nodeTree = null;
        this.staticObject = null;
        this.numTreeChildren = 0;
        this.contextReference = null;
        this.callerReference = null;
        this.singleLevelRef = null;
        this.constant = false;
        this.rsvc = null;
        this.log = null;
        this.rsvc = rs;
        this.log = this.rsvc.getLog();
        this.contextReference = contextRef;
        this.callerReference = callerRef;
        this.type = t;
        this.setup();
        if (this.nodeTree != null) {
            this.numTreeChildren = this.nodeTree.jjtGetNumChildren();
        }
        if (this.type == 16 && this.numTreeChildren == 0) {
            this.singleLevelRef = ((ASTReference)this.nodeTree).getRootString();
        }
    }
    
    public boolean isConstant() {
        return this.constant;
    }
    
    public Object setObject(final InternalContextAdapter context, final Object o) {
        if (this.type == 16) {
            if (this.numTreeChildren > 0) {
                try {
                    ((ASTReference)this.nodeTree).setValue(context, o);
                }
                catch (MethodInvocationException mie) {
                    this.log.error("VMProxyArg.getObject() : method invocation error setting value", mie);
                }
            }
            else {
                context.put(this.singleLevelRef, o);
            }
        }
        else {
            this.type = -1;
            this.staticObject = o;
            this.log.error("VMProxyArg.setObject() : Programmer error : I am a constant!  No setting! : " + this.contextReference + " / " + this.callerReference);
        }
        return null;
    }
    
    public Object getObject(final InternalContextAdapter context) throws MethodInvocationException {
        try {
            Object retObject = null;
            if (this.type == 16) {
                if (this.numTreeChildren == 0) {
                    retObject = context.get(this.singleLevelRef);
                }
                else {
                    retObject = this.nodeTree.execute(null, context);
                }
            }
            else if (this.type == 12) {
                retObject = this.nodeTree.value(context);
            }
            else if (this.type == 13) {
                retObject = this.nodeTree.value(context);
            }
            else if (this.type == 14) {
                retObject = this.nodeTree.value(context);
            }
            else if (this.type == 17) {
                retObject = this.staticObject;
            }
            else if (this.type == 18) {
                retObject = this.staticObject;
            }
            else if (this.type == 7) {
                retObject = this.nodeTree.value(context);
            }
            else if (this.type == 6) {
                retObject = this.staticObject;
            }
            else if (this.type == 5) {
                retObject = this.staticObject;
            }
            else {
                if (this.type == 19) {
                    try {
                        final StringWriter writer = new StringWriter();
                        this.nodeTree.render(context, writer);
                        retObject = writer;
                        return retObject;
                    }
                    catch (RuntimeException e) {
                        throw e;
                    }
                    catch (Exception e2) {
                        this.log.error("VMProxyArg.getObject() : error rendering reference", e2);
                        return retObject;
                    }
                }
                if (this.type == -1) {
                    retObject = this.staticObject;
                }
                else {
                    this.log.error("Unsupported VM arg type : VM arg = " + this.callerReference + " type = " + this.type + "( VMProxyArg.getObject() )");
                }
            }
            return retObject;
        }
        catch (MethodInvocationException mie) {
            this.log.error("VMProxyArg.getObject() : method invocation error getting value", mie);
            throw mie;
        }
    }
    
    private void setup() {
        switch (this.type) {
            case 7:
            case 12:
            case 13:
            case 14:
            case 16:
            case 19: {
                this.constant = false;
                try {
                    final String buff = "#include(" + this.callerReference + " ) ";
                    final BufferedReader br = new BufferedReader(new StringReader(buff));
                    this.nodeTree = this.rsvc.parse(br, "VMProxyArg:" + this.callerReference, true);
                    this.nodeTree = (SimpleNode)this.nodeTree.jjtGetChild(0).jjtGetChild(0);
                    if (this.nodeTree != null) {
                        if (this.nodeTree.getType() != this.type) {
                            this.log.error("VMProxyArg.setup() : programmer error : type doesn't match node type.");
                        }
                        final InternalContextAdapter ica = new InternalContextAdapterImpl(new VelocityContext());
                        ica.pushCurrentTemplateName("VMProxyArg : " + ParserTreeConstants.jjtNodeName[this.type]);
                        this.nodeTree.init(ica, this.rsvc);
                    }
                    break;
                }
                catch (RuntimeException e) {
                    throw e;
                }
                catch (Exception e2) {
                    this.log.error("VMProxyArg.setup() : exception " + this.callerReference, e2);
                    break;
                }
            }
            case 17: {
                this.constant = true;
                this.staticObject = Boolean.TRUE;
                break;
            }
            case 18: {
                this.constant = true;
                this.staticObject = Boolean.FALSE;
                break;
            }
            case 6: {
                this.constant = true;
                this.staticObject = new Integer(this.callerReference);
                break;
            }
            case 5: {
                this.constant = true;
                this.staticObject = new Double(this.callerReference);
                break;
            }
            case 9: {
                this.log.error("Unsupported arg type : " + this.callerReference + " You most likely intended to call a VM with a string literal, so enclose with ' or \" characters. (VMProxyArg.setup())");
                this.constant = true;
                this.staticObject = this.callerReference;
                break;
            }
            default: {
                this.log.error("VMProxyArg.setup() : unsupported type : " + this.callerReference);
                break;
            }
        }
    }
    
    public VMProxyArg(final VMProxyArg model, final InternalContextAdapter c) {
        this.type = 0;
        this.nodeTree = null;
        this.staticObject = null;
        this.numTreeChildren = 0;
        this.contextReference = null;
        this.callerReference = null;
        this.singleLevelRef = null;
        this.constant = false;
        this.rsvc = null;
        this.log = null;
        this.contextReference = model.getContextReference();
        this.callerReference = model.getCallerReference();
        this.nodeTree = model.getNodeTree();
        this.staticObject = model.getStaticObject();
        this.type = model.getType();
        if (this.nodeTree != null) {
            this.numTreeChildren = this.nodeTree.jjtGetNumChildren();
        }
        if (this.type == 16 && this.numTreeChildren == 0) {
            this.singleLevelRef = ((ASTReference)this.nodeTree).getRootString();
        }
    }
    
    public String getCallerReference() {
        return this.callerReference;
    }
    
    public String getContextReference() {
        return this.contextReference;
    }
    
    public SimpleNode getNodeTree() {
        return this.nodeTree;
    }
    
    public Object getStaticObject() {
        return this.staticObject;
    }
    
    public int getType() {
        return this.type;
    }
}
