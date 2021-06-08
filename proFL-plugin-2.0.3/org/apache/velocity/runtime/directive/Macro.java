// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.directive;

import org.apache.velocity.runtime.parser.node.NodeUtils;
import java.util.ArrayList;
import org.apache.velocity.runtime.parser.ParseException;
import java.util.List;
import org.apache.velocity.runtime.parser.ParserTreeConstants;
import org.apache.velocity.runtime.parser.Token;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.runtime.RuntimeServices;
import java.io.IOException;
import org.apache.velocity.runtime.parser.node.Node;
import java.io.Writer;
import org.apache.velocity.context.InternalContextAdapter;

public class Macro extends Directive
{
    private static boolean debugMode;
    
    public String getName() {
        return "macro";
    }
    
    public int getType() {
        return 1;
    }
    
    public boolean render(final InternalContextAdapter context, final Writer writer, final Node node) throws IOException {
        return true;
    }
    
    public void init(final RuntimeServices rs, final InternalContextAdapter context, final Node node) throws TemplateInitException {
        super.init(rs, context, node);
    }
    
    public static void processAndRegister(final RuntimeServices rs, final Token t, final Node node, final String sourceTemplate) throws IOException, ParseException {
        final int numArgs = node.jjtGetNumChildren();
        if (numArgs < 2) {
            rs.getLog().error("#macro error : Velocimacro must have name as 1st argument to #macro(). #args = " + numArgs);
            throw new MacroParseException("First argument to #macro() must be  macro name.", sourceTemplate, t);
        }
        final int firstType = node.jjtGetChild(0).getType();
        if (firstType != 9) {
            throw new MacroParseException("First argument to #macro() must be a token without surrounding ' or \", which specifies the macro name.  Currently it is a " + ParserTreeConstants.jjtNodeName[firstType], sourceTemplate, t);
        }
        final String[] argArray = getArgArray(node, rs);
        final List macroArray = getASTAsStringArray(node.jjtGetChild(numArgs - 1));
        final StringBuffer macroBody = new StringBuffer();
        for (int i = 0; i < macroArray.size(); ++i) {
            macroBody.append(macroArray.get(i));
        }
        final boolean macroAdded = rs.addVelocimacro(argArray[0], macroBody.toString(), argArray, sourceTemplate);
        if (!macroAdded && rs.getLog().isWarnEnabled()) {
            final StringBuffer msg = new StringBuffer("Failed to add macro: ");
            macroToString(msg, argArray);
            msg.append(" : source = ").append(sourceTemplate);
            rs.getLog().warn(msg);
        }
    }
    
    private static String[] getArgArray(final Node node, final RuntimeServices rsvc) {
        int numArgs = node.jjtGetNumChildren();
        final String[] argArray = new String[--numArgs];
        for (int i = 0; i < numArgs; ++i) {
            argArray[i] = node.jjtGetChild(i).getFirstToken().image;
            if (i > 0 && argArray[i].startsWith("$")) {
                argArray[i] = argArray[i].substring(1, argArray[i].length());
            }
        }
        if (Macro.debugMode) {
            final StringBuffer msg = new StringBuffer("Macro.getArgArray() : nbrArgs=");
            msg.append(numArgs).append(" : ");
            macroToString(msg, argArray);
            rsvc.getLog().debug(msg);
        }
        return argArray;
    }
    
    private static List getASTAsStringArray(final Node rootNode) {
        Token t = rootNode.getFirstToken();
        final Token tLast = rootNode.getLastToken();
        final List list = new ArrayList();
        while (t != tLast) {
            list.add(NodeUtils.tokenLiteral(t));
            t = t.next;
        }
        list.add(NodeUtils.tokenLiteral(t));
        return list;
    }
    
    public static final StringBuffer macroToString(final StringBuffer buf, final String[] argArray) {
        final StringBuffer ret = (buf == null) ? new StringBuffer() : buf;
        ret.append('#').append(argArray[0]).append("( ");
        for (int i = 1; i < argArray.length; ++i) {
            ret.append(' ').append(argArray[i]);
        }
        ret.append(" )");
        return ret;
    }
    
    static {
        Macro.debugMode = false;
    }
}
