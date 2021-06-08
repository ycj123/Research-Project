// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli;

import java.security.AccessControlException;

public class ShutdownHookUtils
{
    public static void addShutDownHook(final Thread hook) {
        try {
            Runtime.getRuntime().addShutdownHook(hook);
        }
        catch (IllegalStateException ignore) {}
        catch (AccessControlException ex) {}
    }
    
    public static void removeShutdownHook(final Thread hook) {
        try {
            Runtime.getRuntime().removeShutdownHook(hook);
        }
        catch (IllegalStateException ignore) {}
        catch (AccessControlException ex) {}
    }
}
