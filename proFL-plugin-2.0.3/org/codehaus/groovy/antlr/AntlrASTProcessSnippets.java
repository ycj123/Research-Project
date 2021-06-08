// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import groovyjarjarantlr.collections.AST;

public class AntlrASTProcessSnippets implements AntlrASTProcessor
{
    public AST process(final AST t) {
        final List l = new ArrayList();
        this.traverse((GroovySourceAST)t, l, null);
        final Iterator itr = l.iterator();
        if (itr.hasNext()) {
            itr.next();
        }
        this.traverse((GroovySourceAST)t, null, itr);
        return t;
    }
    
    private void traverse(GroovySourceAST t, final List l, final Iterator itr) {
        while (t != null) {
            if (l != null) {
                l.add(new LineColumn(t.getLine(), t.getColumn()));
            }
            if (itr != null && itr.hasNext()) {
                final LineColumn lc = itr.next();
                if (t.getLineLast() == 0) {
                    int nextLine = lc.getLine();
                    int nextColumn = lc.getColumn();
                    if (nextLine < t.getLine() || (nextLine == t.getLine() && nextColumn < t.getColumn())) {
                        nextLine = t.getLine();
                        nextColumn = t.getColumn();
                    }
                    t.setLineLast(nextLine);
                    t.setColumnLast(nextColumn);
                }
            }
            final GroovySourceAST child = (GroovySourceAST)t.getFirstChild();
            if (child != null) {
                this.traverse(child, l, itr);
            }
            t = (GroovySourceAST)t.getNextSibling();
        }
    }
}
