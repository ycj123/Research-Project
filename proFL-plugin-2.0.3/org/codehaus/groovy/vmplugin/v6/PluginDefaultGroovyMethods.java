// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.vmplugin.v6;

import java.util.Iterator;
import java.util.Set;
import java.util.Map;
import java.io.Reader;
import javax.script.ScriptException;
import groovy.lang.Binding;
import javax.script.ScriptEngine;
import org.codehaus.groovy.runtime.DefaultGroovyMethodsSupport;

public class PluginDefaultGroovyMethods extends DefaultGroovyMethodsSupport
{
    public static Object eval(final ScriptEngine self, final String script, final Binding binding) throws ScriptException {
        storeBindingVars(self, binding);
        final Object result = self.eval(script);
        retrieveBindingVars(self, binding);
        return result;
    }
    
    public static Object eval(final ScriptEngine self, final Reader reader, final Binding binding) throws ScriptException {
        storeBindingVars(self, binding);
        final Object result = self.eval(reader);
        retrieveBindingVars(self, binding);
        return result;
    }
    
    private static void retrieveBindingVars(final ScriptEngine self, final Binding binding) {
        final Set<Map.Entry<String, Object>> returnVars = self.getBindings(100).entrySet();
        for (final Map.Entry<String, Object> me : returnVars) {
            binding.setVariable(me.getKey(), me.getValue());
        }
    }
    
    private static void storeBindingVars(final ScriptEngine self, final Binding binding) {
        final Set<Map.Entry> vars = (Set<Map.Entry>)binding.getVariables().entrySet();
        for (final Map.Entry me : vars) {
            self.put(me.getKey().toString(), me.getValue());
        }
    }
}
