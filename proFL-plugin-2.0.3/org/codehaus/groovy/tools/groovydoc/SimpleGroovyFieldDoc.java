// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import org.codehaus.groovy.groovydoc.GroovyClassDoc;
import org.codehaus.groovy.groovydoc.GroovyType;
import org.codehaus.groovy.groovydoc.GroovyFieldDoc;

public class SimpleGroovyFieldDoc extends SimpleGroovyMemberDoc implements GroovyFieldDoc
{
    private GroovyType type;
    private String constantValueExpression;
    
    public SimpleGroovyFieldDoc(final String name, final GroovyClassDoc belongsToClass) {
        super(name, belongsToClass);
    }
    
    public Object constantValue() {
        return null;
    }
    
    public void setConstantValueExpression(final String constantValueExpression) {
        this.constantValueExpression = constantValueExpression;
    }
    
    public String constantValueExpression() {
        return this.constantValueExpression;
    }
    
    public boolean isTransient() {
        return false;
    }
    
    public boolean isVolatile() {
        return false;
    }
    
    public GroovyType type() {
        return this.type;
    }
    
    public void setType(final GroovyType type) {
        this.type = type;
    }
}
