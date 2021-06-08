// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime;

import org.apache.velocity.runtime.parser.Parser;

public interface ParserPool
{
    void initialize(final RuntimeServices p0);
    
    Parser get();
    
    void put(final Parser p0);
}
