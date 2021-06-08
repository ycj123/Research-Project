// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr.treewalker;

import org.codehaus.groovy.antlr.GroovySourceAST;

public class PreOrderTraversal extends TraversalHelper
{
    public PreOrderTraversal(final Visitor visitor) {
        super(visitor);
    }
    
    public void accept(final GroovySourceAST currentNode) {
        this.push(currentNode);
        this.openingVisit(currentNode);
        this.acceptChildren(currentNode);
        this.closingVisit(currentNode);
        this.pop();
    }
}
