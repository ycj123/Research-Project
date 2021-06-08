// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.util.LinkedHashMap;
import java.util.Map;

public class Binding extends GroovyObjectSupport
{
    private Map variables;
    
    public Binding() {
    }
    
    public Binding(final Map variables) {
        this.variables = variables;
    }
    
    public Binding(final String[] args) {
        this();
        this.setVariable("args", args);
    }
    
    public Object getVariable(final String name) {
        if (this.variables == null) {
            throw new MissingPropertyException(name, this.getClass());
        }
        final Object result = this.variables.get(name);
        if (result == null && !this.variables.containsKey(name)) {
            throw new MissingPropertyException(name, this.getClass());
        }
        return result;
    }
    
    public void setVariable(final String name, final Object value) {
        if (this.variables == null) {
            this.variables = new LinkedHashMap();
        }
        this.variables.put(name, value);
    }
    
    public Map getVariables() {
        if (this.variables == null) {
            this.variables = new LinkedHashMap();
        }
        return this.variables;
    }
    
    @Override
    public Object getProperty(final String property) {
        try {
            return super.getProperty(property);
        }
        catch (MissingPropertyException e) {
            return this.getVariable(property);
        }
    }
    
    @Override
    public void setProperty(final String property, final Object newValue) {
        try {
            super.setProperty(property, newValue);
        }
        catch (MissingPropertyException e) {
            this.setVariable(property, newValue);
        }
    }
}
