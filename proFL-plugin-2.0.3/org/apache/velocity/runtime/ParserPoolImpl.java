// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime;

import org.apache.velocity.runtime.parser.Parser;
import org.apache.velocity.util.SimplePool;

public class ParserPoolImpl implements ParserPool
{
    RuntimeServices rsvc;
    SimplePool pool;
    int max;
    
    public ParserPoolImpl() {
        this.rsvc = null;
        this.pool = null;
        this.max = 20;
    }
    
    public void initialize(final RuntimeServices rsvc) {
        this.rsvc = rsvc;
        this.max = rsvc.getInt("parser.pool.size", 20);
        this.pool = new SimplePool(this.max);
        for (int i = 0; i < this.max; ++i) {
            this.pool.put(rsvc.createNewParser());
        }
        if (rsvc.getLog().isDebugEnabled()) {
            rsvc.getLog().debug("Created '" + this.max + "' parsers.");
        }
    }
    
    public Parser get() {
        Parser parser = (Parser)this.pool.get();
        if (parser == null) {
            this.rsvc.getLog().debug("Created new parser (pool exhausted).  Consider increasing pool size.");
            parser = this.rsvc.createNewParser();
        }
        return parser;
    }
    
    public void put(final Parser parser) {
        this.pool.put(parser);
    }
}
