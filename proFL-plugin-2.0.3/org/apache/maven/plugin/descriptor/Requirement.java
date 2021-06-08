// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.descriptor;

public class Requirement
{
    private final String role;
    private final String roleHint;
    
    public Requirement(final String role) {
        this.role = role;
        this.roleHint = null;
    }
    
    public Requirement(final String role, final String roleHint) {
        this.role = role;
        this.roleHint = roleHint;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public String getRoleHint() {
        return this.roleHint;
    }
}
