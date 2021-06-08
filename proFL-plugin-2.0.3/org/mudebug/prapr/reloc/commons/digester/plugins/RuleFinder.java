// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins;

import java.util.Properties;
import org.mudebug.prapr.reloc.commons.digester.Digester;

public abstract class RuleFinder
{
    public abstract RuleLoader findLoader(final Digester p0, final Class p1, final Properties p2) throws PluginException;
}
