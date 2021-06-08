// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform;

import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ASTNode;

public interface ASTTransformation
{
    void visit(final ASTNode[] p0, final SourceUnit p1);
}
