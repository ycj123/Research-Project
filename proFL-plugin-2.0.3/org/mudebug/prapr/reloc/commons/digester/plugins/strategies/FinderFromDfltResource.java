// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins.strategies;

import org.mudebug.prapr.reloc.commons.digester.plugins.PluginException;
import java.io.InputStream;
import org.mudebug.prapr.reloc.commons.digester.plugins.RuleLoader;
import java.util.Properties;
import org.mudebug.prapr.reloc.commons.digester.Digester;
import org.mudebug.prapr.reloc.commons.digester.plugins.RuleFinder;

public class FinderFromDfltResource extends RuleFinder
{
    public static String DFLT_RESOURCE_SUFFIX;
    private String resourceSuffix;
    
    public FinderFromDfltResource() {
        this(FinderFromDfltResource.DFLT_RESOURCE_SUFFIX);
    }
    
    public FinderFromDfltResource(final String resourceSuffix) {
        this.resourceSuffix = resourceSuffix;
    }
    
    public RuleLoader findLoader(final Digester d, final Class pluginClass, final Properties p) throws PluginException {
        final String resourceName = pluginClass.getName().replace('.', '/') + this.resourceSuffix;
        final InputStream is = pluginClass.getClassLoader().getResourceAsStream(resourceName);
        if (is == null) {
            return null;
        }
        return FinderFromResource.loadRules(d, pluginClass, is, resourceName);
    }
    
    static {
        FinderFromDfltResource.DFLT_RESOURCE_SUFFIX = "RuleInfo.xml";
    }
}
