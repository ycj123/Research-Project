// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Relocator
{
    @Nullable
    private final String relocation;
    private static final String relocationBase = "org.apache.maven.surefire.";
    
    public Relocator(@Nullable final String relocation) {
        this.relocation = relocation;
    }
    
    public Relocator() {
        this.relocation = "shadefire";
    }
    
    @Nullable
    private String getRelocation() {
        return this.relocation;
    }
    
    @Nonnull
    public String relocate(@Nonnull final String className) {
        if (this.relocation == null) {
            return className;
        }
        if (className.contains(this.relocation)) {
            return className;
        }
        final String rest = className.substring("org.apache.maven.surefire.".length());
        final String s = "org.apache.maven.surefire." + this.getRelocation() + ".";
        return s + rest;
    }
}
