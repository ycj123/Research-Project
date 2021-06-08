// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform.powerassert;

import org.codehaus.groovy.GroovyBugError;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.transform.GroovyASTTransformation;
import org.codehaus.groovy.transform.ASTTransformation;

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class AssertionTransformation implements ASTTransformation
{
    public void visit(final ASTNode[] nodes, final SourceUnit sourceUnit) {
        if (!(nodes[0] instanceof ModuleNode)) {
            throw new GroovyBugError("tried to apply AssertionTransformation to " + nodes[0]);
        }
    }
}
