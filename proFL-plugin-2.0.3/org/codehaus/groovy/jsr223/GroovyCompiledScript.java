// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.jsr223;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.ScriptContext;
import javax.script.CompiledScript;

public class GroovyCompiledScript extends CompiledScript
{
    private final GroovyScriptEngineImpl engine;
    private final Class clasz;
    
    public GroovyCompiledScript(final GroovyScriptEngineImpl engine, final Class clasz) {
        this.engine = engine;
        this.clasz = clasz;
    }
    
    @Override
    public Object eval(final ScriptContext context) throws ScriptException {
        return this.engine.eval(this.clasz, context);
    }
    
    @Override
    public ScriptEngine getEngine() {
        return this.engine;
    }
}
