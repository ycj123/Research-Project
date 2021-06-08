// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.javac;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.ResolveVisitor;

public class JavaAwareResolveVisitor extends ResolveVisitor
{
    public JavaAwareResolveVisitor(final CompilationUnit cu) {
        super(cu);
    }
    
    @Override
    protected void visitClassCodeContainer(final Statement code) {
    }
    
    @Override
    protected void addError(final String msg, final ASTNode expr) {
    }
}
