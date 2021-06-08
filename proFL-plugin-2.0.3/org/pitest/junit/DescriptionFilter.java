// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.junit;

import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;

public class DescriptionFilter extends Filter
{
    private final String desc;
    
    public DescriptionFilter(final String description) {
        this.desc = description;
    }
    
    public boolean shouldRun(final Description description) {
        return description.toString().equals(this.desc);
    }
    
    public String describe() {
        return this.desc;
    }
    
    public String toString() {
        return this.describe();
    }
}
