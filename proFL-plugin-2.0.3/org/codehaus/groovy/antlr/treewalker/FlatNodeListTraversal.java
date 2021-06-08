// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr.treewalker;

import java.util.Iterator;
import java.util.List;
import org.codehaus.groovy.antlr.AntlrASTProcessor;
import org.codehaus.groovy.antlr.GroovySourceAST;
import groovyjarjarantlr.collections.AST;

public class FlatNodeListTraversal extends TraversalHelper
{
    public FlatNodeListTraversal(final Visitor visitor) {
        super(visitor);
    }
    
    @Override
    public AST process(final AST t) {
        final GroovySourceAST node = (GroovySourceAST)t;
        final NodeCollector collector = new NodeCollector();
        final AntlrASTProcessor internalTraversal = new PreOrderTraversal(collector);
        internalTraversal.process(t);
        final List listOfAllNodesInThisAST = collector.getNodes();
        this.setUp(node);
        for (final GroovySourceAST currentNode : listOfAllNodesInThisAST) {
            this.accept(currentNode);
        }
        this.tearDown(node);
        return null;
    }
    
    @Override
    protected void accept(final GroovySourceAST currentNode) {
        this.openingVisit(currentNode);
        this.closingVisit(currentNode);
    }
}
