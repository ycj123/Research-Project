// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr.java;

import org.codehaus.groovy.antlr.GroovySourceAST;
import org.codehaus.groovy.antlr.parser.GroovyTokenTypes;
import org.codehaus.groovy.antlr.treewalker.VisitorAdapter;

public class Groovifier extends VisitorAdapter implements GroovyTokenTypes
{
    private String[] tokenNames;
    private String currentClassName;
    private boolean cleanRedundantPublic;
    
    public Groovifier(final String[] tokenNames) {
        this(tokenNames, true);
    }
    
    public Groovifier(final String[] tokenNames, final boolean cleanRedundantPublic) {
        this.currentClassName = "";
        this.tokenNames = tokenNames;
        this.cleanRedundantPublic = cleanRedundantPublic;
    }
    
    @Override
    public void visitClassDef(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            this.currentClassName = t.childOfType(84).getText();
        }
    }
    
    @Override
    public void visitDefault(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            if (t.getType() == 112 && this.cleanRedundantPublic) {
                t.setType(27);
            }
            if (t.getType() == 8) {
                final String methodName = t.childOfType(84).getText();
                if (methodName != null && methodName.length() > 0 && methodName.equals(this.currentClassName)) {
                    t.setType(45);
                }
            }
        }
    }
}
