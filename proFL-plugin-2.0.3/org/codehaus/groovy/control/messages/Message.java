// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control.messages;

import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.control.ProcessingUnit;
import org.codehaus.groovy.control.Janitor;
import java.io.PrintWriter;

public abstract class Message
{
    public abstract void write(final PrintWriter p0, final Janitor p1);
    
    public final void write(final PrintWriter writer) {
        this.write(writer, null);
    }
    
    public static Message create(final String text, final ProcessingUnit owner) {
        return new SimpleMessage(text, owner);
    }
    
    public static Message create(final String text, final Object data, final ProcessingUnit owner) {
        return new SimpleMessage(text, data, owner);
    }
    
    public static Message create(final SyntaxException error, final SourceUnit owner) {
        return new SyntaxErrorMessage(error, owner);
    }
}
