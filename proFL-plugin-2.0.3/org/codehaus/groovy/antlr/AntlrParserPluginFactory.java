// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr;

import org.codehaus.groovy.control.ParserPlugin;
import org.codehaus.groovy.control.ParserPluginFactory;

public class AntlrParserPluginFactory extends ParserPluginFactory
{
    @Override
    public ParserPlugin createParserPlugin() {
        return new AntlrParserPlugin();
    }
}
