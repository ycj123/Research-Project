// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins.strategies;

import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.digester.Digester;
import org.mudebug.prapr.reloc.commons.digester.plugins.RuleLoader;

public class LoaderSetProperties extends RuleLoader
{
    public void addRules(final Digester digester, final String path) {
        final Log log = digester.getLogger();
        final boolean debug = log.isDebugEnabled();
        if (debug) {
            log.debug("LoaderSetProperties loading rules for plugin at path [" + path + "]");
        }
        digester.addSetProperties(path);
    }
}
