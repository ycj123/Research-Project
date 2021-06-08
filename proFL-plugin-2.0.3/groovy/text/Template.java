// 
// Decompiled by Procyon v0.5.36
// 

package groovy.text;

import java.util.Map;
import groovy.lang.Writable;

public interface Template
{
    Writable make();
    
    Writable make(final Map p0);
}
