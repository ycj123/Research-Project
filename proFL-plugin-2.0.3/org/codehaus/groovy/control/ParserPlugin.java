// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

import org.codehaus.groovy.syntax.ParserException;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.syntax.Reduction;
import java.io.Reader;

public interface ParserPlugin
{
    Reduction parseCST(final SourceUnit p0, final Reader p1) throws CompilationFailedException;
    
    ModuleNode buildAST(final SourceUnit p0, final ClassLoader p1, final Reduction p2) throws ParserException;
}
