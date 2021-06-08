// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins.strategies;

import org.mudebug.prapr.reloc.commons.digester.plugins.PluginException;
import org.mudebug.prapr.reloc.commons.digester.plugins.RuleLoader;
import java.util.Properties;
import org.mudebug.prapr.reloc.commons.digester.Digester;
import org.mudebug.prapr.reloc.commons.digester.plugins.RuleFinder;

public class FinderFromDfltClass extends RuleFinder
{
    public static String DFLT_RULECLASS_SUFFIX;
    public static String DFLT_METHOD_NAME;
    private String rulesClassSuffix;
    private String methodName;
    
    public FinderFromDfltClass() {
        this(FinderFromDfltClass.DFLT_RULECLASS_SUFFIX, FinderFromDfltClass.DFLT_METHOD_NAME);
    }
    
    public FinderFromDfltClass(final String rulesClassSuffix, final String methodName) {
        this.rulesClassSuffix = rulesClassSuffix;
        this.methodName = methodName;
    }
    
    public RuleLoader findLoader(final Digester digester, final Class pluginClass, final Properties p) throws PluginException {
        final String rulesClassName = pluginClass.getName() + this.rulesClassSuffix;
        Class rulesClass = null;
        try {
            rulesClass = digester.getClassLoader().loadClass(rulesClassName);
        }
        catch (ClassNotFoundException cnfe) {}
        if (rulesClass == null) {
            return null;
        }
        if (this.methodName == null) {
            this.methodName = FinderFromDfltClass.DFLT_METHOD_NAME;
        }
        return new LoaderFromClass(rulesClass, this.methodName);
    }
    
    static {
        FinderFromDfltClass.DFLT_RULECLASS_SUFFIX = "RuleInfo";
        FinderFromDfltClass.DFLT_METHOD_NAME = "addRules";
    }
}
