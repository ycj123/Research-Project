// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins.strategies;

import org.mudebug.prapr.reloc.commons.digester.plugins.PluginException;
import java.lang.reflect.Method;
import org.mudebug.prapr.reloc.commons.digester.plugins.RuleLoader;
import java.util.Properties;
import org.mudebug.prapr.reloc.commons.digester.Digester;
import org.mudebug.prapr.reloc.commons.digester.plugins.RuleFinder;

public class FinderFromDfltMethod extends RuleFinder
{
    public static String DFLT_METHOD_NAME;
    private String methodName;
    
    public FinderFromDfltMethod() {
        this(FinderFromDfltMethod.DFLT_METHOD_NAME);
    }
    
    public FinderFromDfltMethod(final String methodName) {
        this.methodName = methodName;
    }
    
    public RuleLoader findLoader(final Digester d, final Class pluginClass, final Properties p) throws PluginException {
        final Method rulesMethod = LoaderFromClass.locateMethod(pluginClass, this.methodName);
        if (rulesMethod == null) {
            return null;
        }
        return new LoaderFromClass(pluginClass, rulesMethod);
    }
    
    static {
        FinderFromDfltMethod.DFLT_METHOD_NAME = "addRules";
    }
}
