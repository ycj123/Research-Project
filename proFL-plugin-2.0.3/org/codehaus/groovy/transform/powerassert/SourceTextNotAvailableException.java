// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform.powerassert;

import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.stmt.AssertStatement;

public class SourceTextNotAvailableException extends RuntimeException
{
    public SourceTextNotAvailableException(final AssertStatement stat, final SourceUnit unit, final String msg) {
        super(String.format("%s for %s at (%d,%d)-(%d,%d) in %s", msg, stat.getBooleanExpression().getText(), stat.getLineNumber(), stat.getColumnNumber(), stat.getLastLineNumber(), stat.getLastColumnNumber(), unit.getName()));
    }
}
