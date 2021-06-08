// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.ParseErrorException;
import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.Token;
import org.apache.velocity.runtime.parser.ParserVisitor;

public interface Node
{
    void jjtOpen();
    
    void jjtClose();
    
    void jjtSetParent(final Node p0);
    
    Node jjtGetParent();
    
    void jjtAddChild(final Node p0, final int p1);
    
    Node jjtGetChild(final int p0);
    
    int jjtGetNumChildren();
    
    Object jjtAccept(final ParserVisitor p0, final Object p1);
    
    Object childrenAccept(final ParserVisitor p0, final Object p1);
    
    Token getFirstToken();
    
    Token getLastToken();
    
    int getType();
    
    Object init(final InternalContextAdapter p0, final Object p1) throws TemplateInitException;
    
    boolean evaluate(final InternalContextAdapter p0) throws MethodInvocationException;
    
    Object value(final InternalContextAdapter p0) throws MethodInvocationException;
    
    boolean render(final InternalContextAdapter p0, final Writer p1) throws IOException, MethodInvocationException, ParseErrorException, ResourceNotFoundException;
    
    Object execute(final Object p0, final InternalContextAdapter p1) throws MethodInvocationException;
    
    void setInfo(final int p0);
    
    int getInfo();
    
    String literal();
    
    void setInvalid();
    
    boolean isInvalid();
    
    int getLine();
    
    int getColumn();
}
