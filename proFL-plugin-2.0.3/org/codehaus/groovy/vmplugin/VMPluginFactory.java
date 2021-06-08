// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.vmplugin;

import org.codehaus.groovy.vmplugin.v4.Java4;

public class VMPluginFactory
{
    private static final String JDK5_CLASSNAME_CHECK = "java.lang.annotation.Annotation";
    private static final String JDK5_PLUGIN_NAME = "org.codehaus.groovy.vmplugin.v5.Java5";
    private static final String JDK6_CLASSNAME_CHECK = "javax.script.ScriptEngine";
    private static final String JDK6_PLUGIN_NAME = "org.codehaus.groovy.vmplugin.v6.Java6";
    private static VMPlugin plugin;
    
    public static VMPlugin getPlugin() {
        return VMPluginFactory.plugin;
    }
    
    static {
        try {
            final ClassLoader loader = VMPluginFactory.class.getClassLoader();
            loader.loadClass("javax.script.ScriptEngine");
            VMPluginFactory.plugin = (VMPlugin)loader.loadClass("org.codehaus.groovy.vmplugin.v6.Java6").newInstance();
        }
        catch (Exception ex2) {}
        if (VMPluginFactory.plugin == null) {
            try {
                final ClassLoader loader = VMPluginFactory.class.getClassLoader();
                loader.loadClass("java.lang.annotation.Annotation");
                VMPluginFactory.plugin = (VMPlugin)loader.loadClass("org.codehaus.groovy.vmplugin.v5.Java5").newInstance();
            }
            catch (Exception ex) {
                VMPluginFactory.plugin = new Java4();
            }
        }
    }
}
