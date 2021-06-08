// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins.strategies;

import org.mudebug.prapr.reloc.commons.digester.plugins.PluginException;
import org.mudebug.prapr.reloc.commons.digester.plugins.RuleLoader;
import java.util.Properties;
import org.mudebug.prapr.reloc.commons.digester.Digester;
import org.mudebug.prapr.reloc.commons.digester.plugins.RuleFinder;

public class FinderFromClass extends RuleFinder
{
    public static String DFLT_RULECLASS_ATTR;
    public static String DFLT_METHOD_ATTR;
    public static String DFLT_METHOD_NAME;
    private String ruleClassAttr;
    private String methodAttr;
    private String dfltMethodName;
    
    public FinderFromClass() {
        this(FinderFromClass.DFLT_RULECLASS_ATTR, FinderFromClass.DFLT_METHOD_ATTR, FinderFromClass.DFLT_METHOD_NAME);
    }
    
    public FinderFromClass(final String ruleClassAttr, final String methodAttr, final String dfltMethodName) {
        this.ruleClassAttr = ruleClassAttr;
        this.methodAttr = methodAttr;
        this.dfltMethodName = dfltMethodName;
    }
    
    public RuleLoader findLoader(final Digester digester, final Class pluginClass, final Properties p) throws PluginException {
        final String ruleClassName = p.getProperty(this.ruleClassAttr);
        if (ruleClassName == null) {
            return null;
        }
        String methodName = null;
        if (this.methodAttr != null) {
            methodName = p.getProperty(this.methodAttr);
        }
        if (methodName == null) {
            methodName = this.dfltMethodName;
        }
        if (methodName == null) {
            methodName = FinderFromClass.DFLT_METHOD_NAME;
        }
        Class ruleClass;
        try {
            ruleClass = digester.getClassLoader().loadClass(ruleClassName);
        }
        catch (ClassNotFoundException cnfe) {
            throw new PluginException("Unable to load class " + ruleClassName, cnfe);
        }
        return new LoaderFromClass(ruleClass, methodName);
    }
    
    static {
        FinderFromClass.DFLT_RULECLASS_ATTR = "ruleclass";
        FinderFromClass.DFLT_METHOD_ATTR = "method";
        FinderFromClass.DFLT_METHOD_NAME = "addRules";
    }
}
