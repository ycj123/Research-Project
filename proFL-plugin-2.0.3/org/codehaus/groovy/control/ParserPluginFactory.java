// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

import org.codehaus.groovy.antlr.AntlrParserPluginFactory;

public abstract class ParserPluginFactory
{
    public static ParserPluginFactory newInstance(final boolean useNewParser) {
        if (useNewParser) {
            Class type = null;
            final String name = "org.codehaus.groovy.antlr.AntlrParserPluginFactory";
            Label_0058: {
                try {
                    type = Class.forName(name);
                }
                catch (ClassNotFoundException e2) {
                    try {
                        type = ParserPluginFactory.class.getClassLoader().loadClass(name);
                    }
                    catch (ClassNotFoundException e3) {
                        final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                        if (contextClassLoader == null) {
                            break Label_0058;
                        }
                        try {
                            type = contextClassLoader.loadClass(name);
                        }
                        catch (ClassNotFoundException ex) {}
                    }
                }
            }
            if (type != null) {
                try {
                    return type.newInstance();
                }
                catch (Exception e) {
                    throw new RuntimeException("Could not create AntlrParserPluginFactory: " + e, e);
                }
            }
        }
        return new AntlrParserPluginFactory();
    }
    
    public abstract ParserPlugin createParserPlugin();
}
