// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import org.codehaus.groovy.groovydoc.GroovyClassDoc;
import org.codehaus.groovy.groovydoc.GroovyAnnotationRef;

public class SimpleGroovyAnnotationRef implements GroovyAnnotationRef
{
    private GroovyClassDoc type;
    private final String desc;
    private String name;
    
    public SimpleGroovyAnnotationRef(final String name, final String desc) {
        this.desc = desc;
        this.name = name;
    }
    
    public void setType(final GroovyClassDoc type) {
        this.type = type;
    }
    
    public GroovyClassDoc type() {
        return this.type;
    }
    
    public String name() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String description() {
        return this.desc;
    }
}
