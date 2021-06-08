// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.GroovyCodeVisitor;

public class EmptyStatement extends Statement
{
    public static final EmptyStatement INSTANCE;
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
    }
    
    @Override
    public boolean isEmpty() {
        return true;
    }
    
    static {
        INSTANCE = new EmptyStatement();
    }
}
