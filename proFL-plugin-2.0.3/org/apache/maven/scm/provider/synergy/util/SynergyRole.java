// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.util;

public class SynergyRole
{
    public static final SynergyRole BUILD_MGR;
    public static final SynergyRole CCM_ADMIN;
    private String value;
    
    private SynergyRole(final String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.value;
    }
    
    static {
        BUILD_MGR = new SynergyRole("build_mgr");
        CCM_ADMIN = new SynergyRole("ccm_admin");
    }
}
