// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.util;

import java.security.Permission;

public class NoExitSecurityManager extends SecurityManager
{
    private final SecurityManager parent;
    
    public NoExitSecurityManager(final SecurityManager parent) {
        assert parent != null;
        this.parent = parent;
    }
    
    public NoExitSecurityManager() {
        this(System.getSecurityManager());
    }
    
    @Override
    public void checkPermission(final Permission perm) {
        if (this.parent != null) {
            this.parent.checkPermission(perm);
        }
    }
    
    @Override
    public void checkExit(final int code) {
        throw new SecurityException("Use of System.exit() is forbidden!");
    }
}
