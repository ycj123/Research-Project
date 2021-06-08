// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.lang.Script;
import groovy.lang.Reference;

public class ScriptReference extends Reference
{
    private Script script;
    private String variable;
    
    public ScriptReference(final Script script, final String variable) {
        this.script = script;
        this.variable = variable;
    }
    
    @Override
    public Object get() {
        return this.script.getBinding().getVariable(this.variable);
    }
    
    @Override
    public void set(final Object value) {
        this.script.getBinding().setVariable(this.variable, value);
    }
}
