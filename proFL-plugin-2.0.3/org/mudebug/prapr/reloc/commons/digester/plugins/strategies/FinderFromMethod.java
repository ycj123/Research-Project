// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins.strategies;

import org.mudebug.prapr.reloc.commons.digester.plugins.PluginException;
import org.mudebug.prapr.reloc.commons.digester.plugins.RuleLoader;
import java.util.Properties;
import org.mudebug.prapr.reloc.commons.digester.Digester;
import org.mudebug.prapr.reloc.commons.digester.plugins.RuleFinder;

public class FinderFromMethod extends RuleFinder
{
    public static String DFLT_METHOD_ATTR;
    private String methodAttr;
    
    public FinderFromMethod() {
        this(FinderFromMethod.DFLT_METHOD_ATTR);
    }
    
    public FinderFromMethod(final String methodAttr) {
        this.methodAttr = methodAttr;
    }
    
    public RuleLoader findLoader(final Digester d, final Class pluginClass, final Properties p) throws PluginException {
        final String methodName = p.getProperty(this.methodAttr);
        if (methodName == null) {
            return null;
        }
        return new LoaderFromClass(pluginClass, methodName);
    }
    
    static {
        FinderFromMethod.DFLT_METHOD_ATTR = "method";
    }
}
