// 
// Decompiled by Procyon v0.5.36
// 

package groovy.security;

import java.security.BasicPermission;

public final class GroovyCodeSourcePermission extends BasicPermission
{
    public GroovyCodeSourcePermission(final String name) {
        super(name);
    }
    
    public GroovyCodeSourcePermission(final String name, final String actions) {
        super(name, actions);
    }
}
