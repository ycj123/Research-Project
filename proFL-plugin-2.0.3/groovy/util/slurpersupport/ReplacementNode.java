// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util.slurpersupport;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import groovy.lang.GroovyObject;
import groovy.lang.Writable;
import groovy.lang.Buildable;

public abstract class ReplacementNode implements Buildable, Writable
{
    public abstract void build(final GroovyObject p0, final Map p1, final Map<String, String> p2);
    
    public void build(final GroovyObject builder) {
        this.build(builder, null, null);
    }
    
    public Writer writeTo(final Writer out) throws IOException {
        return out;
    }
}
