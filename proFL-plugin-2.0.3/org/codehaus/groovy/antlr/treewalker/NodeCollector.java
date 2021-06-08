// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr.treewalker;

import org.codehaus.groovy.antlr.GroovySourceAST;
import java.util.ArrayList;
import java.util.List;

public class NodeCollector extends VisitorAdapter
{
    private List nodes;
    
    public NodeCollector() {
        this.nodes = new ArrayList();
    }
    
    public List getNodes() {
        return this.nodes;
    }
    
    @Override
    public void visitDefault(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            this.nodes.add(t);
        }
    }
}
