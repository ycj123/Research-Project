// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins;

import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.digester.Digester;
import java.util.Properties;
import org.xml.sax.Attributes;
import org.mudebug.prapr.reloc.commons.digester.Rule;

public class PluginDeclarationRule extends Rule
{
    public void begin(final String namespace, final String name, final Attributes attributes) throws Exception {
        final int nAttrs = attributes.getLength();
        final Properties props = new Properties();
        for (int i = 0; i < nAttrs; ++i) {
            String key = attributes.getLocalName(i);
            if (key == null || key.length() == 0) {
                key = attributes.getQName(i);
            }
            final String value = attributes.getValue(i);
            props.setProperty(key, value);
        }
        try {
            declarePlugin(this.digester, props);
        }
        catch (PluginInvalidInputException ex) {
            throw new PluginInvalidInputException("Error on element [" + this.digester.getMatch() + "]: " + ex.getMessage());
        }
    }
    
    public static void declarePlugin(final Digester digester, final Properties props) throws PluginException {
        final Log log = digester.getLogger();
        final boolean debug = log.isDebugEnabled();
        final String id = props.getProperty("id");
        final String pluginClassName = props.getProperty("class");
        if (id == null) {
            throw new PluginInvalidInputException("mandatory attribute id not present on plugin declaration");
        }
        if (pluginClassName == null) {
            throw new PluginInvalidInputException("mandatory attribute class not present on plugin declaration");
        }
        final Declaration newDecl = new Declaration(pluginClassName);
        newDecl.setId(id);
        newDecl.setProperties(props);
        final PluginRules rc = (PluginRules)digester.getRules();
        final PluginManager pm = rc.getPluginManager();
        newDecl.init(digester, pm);
        pm.addDeclaration(newDecl);
    }
}
