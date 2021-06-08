// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform.powerassert;

public class PowerAssertionError extends AssertionError
{
    public PowerAssertionError(final String msg) {
        super((Object)msg);
    }
    
    @Override
    public String toString() {
        return String.format("Assertion failed: \n\n%s\n", this.getMessage());
    }
}
