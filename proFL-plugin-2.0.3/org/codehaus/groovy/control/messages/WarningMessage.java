// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control.messages;

import org.codehaus.groovy.control.Janitor;
import java.io.PrintWriter;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.syntax.CSTNode;

public class WarningMessage extends LocatedMessage
{
    public static final int NONE = 0;
    public static final int LIKELY_ERRORS = 1;
    public static final int POSSIBLE_ERRORS = 2;
    public static final int PARANOIA = 3;
    private int importance;
    
    public static boolean isRelevant(final int actual, final int limit) {
        return actual <= limit;
    }
    
    public boolean isRelevant(final int importance) {
        return isRelevant(this.importance, importance);
    }
    
    public WarningMessage(final int importance, final String message, final CSTNode context, final SourceUnit owner) {
        super(message, context, owner);
        this.importance = importance;
    }
    
    public WarningMessage(final int importance, final String message, final Object data, final CSTNode context, final SourceUnit owner) {
        super(message, data, context, owner);
        this.importance = importance;
    }
    
    @Override
    public void write(final PrintWriter writer, final Janitor janitor) {
        writer.print("warning: ");
        super.write(writer, janitor);
    }
}
