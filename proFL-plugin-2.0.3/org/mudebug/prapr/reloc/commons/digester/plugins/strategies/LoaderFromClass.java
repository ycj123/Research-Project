// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins.strategies;

import org.mudebug.prapr.reloc.commons.beanutils.MethodUtils;
import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.digester.Digester;
import org.mudebug.prapr.reloc.commons.digester.plugins.PluginException;
import java.lang.reflect.Method;
import org.mudebug.prapr.reloc.commons.digester.plugins.RuleLoader;

public class LoaderFromClass extends RuleLoader
{
    private Class rulesClass;
    private Method rulesMethod;
    
    public LoaderFromClass(final Class rulesClass, final Method rulesMethod) {
        this.rulesClass = rulesClass;
        this.rulesMethod = rulesMethod;
    }
    
    public LoaderFromClass(final Class rulesClass, final String methodName) throws PluginException {
        final Method method = locateMethod(rulesClass, methodName);
        if (method == null) {
            throw new PluginException("rule class " + rulesClass.getName() + " does not have method " + methodName + " or that method has an invalid signature.");
        }
        this.rulesClass = rulesClass;
        this.rulesMethod = method;
    }
    
    public void addRules(final Digester d, final String path) throws PluginException {
        final Log log = d.getLogger();
        final boolean debug = log.isDebugEnabled();
        if (debug) {
            log.debug("LoaderFromClass loading rules for plugin at path [" + path + "]");
        }
        try {
            final Object[] params = { d, path };
            final Object none = this.rulesMethod.invoke(null, params);
        }
        catch (Exception e) {
            throw new PluginException("Unable to invoke rules method " + this.rulesMethod + " on rules class " + this.rulesClass, e);
        }
    }
    
    public static Method locateMethod(final Class rulesClass, final String methodName) throws PluginException {
        final Class[] paramSpec = { Digester.class, String.class };
        final Method rulesMethod = MethodUtils.getAccessibleMethod(rulesClass, methodName, paramSpec);
        return rulesMethod;
    }
}
