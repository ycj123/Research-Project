// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr.treewalker;

import org.codehaus.groovy.antlr.GroovySourceAST;
import java.io.PrintStream;

public class NodePrinter extends VisitorAdapter
{
    private String[] tokenNames;
    private PrintStream out;
    
    public NodePrinter(final PrintStream out, final String[] tokenNames) {
        this.tokenNames = tokenNames;
        this.out = out;
    }
    
    @Override
    public void visitDefault(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            this.out.print("<" + this.tokenNames[t.getType()] + ">");
        }
        else {
            this.out.print("</" + this.tokenNames[t.getType()] + ">");
        }
    }
}
