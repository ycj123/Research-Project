// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugins.annotations;

@Deprecated
public enum InstanciationStrategy
{
    PER_LOOKUP("per-lookup"), 
    SINGLETON("singleton"), 
    KEEP_ALIVE("keep-alive"), 
    POOLABLE("poolable");
    
    private final String id;
    
    private InstanciationStrategy(final String id) {
        this.id = id;
    }
    
    public String id() {
        return this.id;
    }
}
