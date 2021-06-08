// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins.strategies;

import org.mudebug.prapr.reloc.commons.digester.plugins.PluginException;
import org.mudebug.prapr.reloc.commons.logging.Log;
import org.xml.sax.InputSource;
import java.io.ByteArrayInputStream;
import org.mudebug.prapr.reloc.commons.digester.Digester;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.mudebug.prapr.reloc.commons.digester.xmlrules.FromXmlRuleSet;
import org.mudebug.prapr.reloc.commons.digester.plugins.RuleLoader;

public class LoaderFromStream extends RuleLoader
{
    private byte[] input;
    private FromXmlRuleSet ruleSet;
    
    public LoaderFromStream(final InputStream s) throws Exception {
        this.load(s);
    }
    
    private void load(final InputStream s) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final byte[] buf = new byte[256];
        while (true) {
            final int i = s.read(buf);
            if (i == -1) {
                break;
            }
            baos.write(buf, 0, i);
        }
        this.input = baos.toByteArray();
    }
    
    public void addRules(final Digester d, final String path) throws PluginException {
        final Log log = d.getLogger();
        final boolean debug = log.isDebugEnabled();
        if (debug) {
            log.debug("LoaderFromStream: loading rules for plugin at path [" + path + "]");
        }
        final InputSource source = new InputSource(new ByteArrayInputStream(this.input));
        final FromXmlRuleSet ruleSet = new FromXmlRuleSet(source);
        ruleSet.addRuleInstances(d, path);
    }
}
