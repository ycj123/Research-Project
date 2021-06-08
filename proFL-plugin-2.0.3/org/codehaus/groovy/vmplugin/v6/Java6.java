// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.vmplugin.v6;

import org.codehaus.groovy.vmplugin.v5.Java5;

public class Java6 extends Java5
{
    private static final Class[] PLUGIN_DGM;
    
    @Override
    public Class[] getPluginDefaultGroovyMethods() {
        return Java6.PLUGIN_DGM;
    }
    
    static {
        PLUGIN_DGM = new Class[] { PluginDefaultGroovyMethods.class, org.codehaus.groovy.vmplugin.v5.PluginDefaultGroovyMethods.class };
    }
}
