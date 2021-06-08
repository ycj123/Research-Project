// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import java.util.ArrayList;
import org.codehaus.groovy.groovydoc.GroovyAnnotationRef;
import java.util.List;
import org.codehaus.groovy.groovydoc.GroovyType;
import org.codehaus.groovy.groovydoc.GroovyParameter;

public class SimpleGroovyParameter implements GroovyParameter
{
    private String name;
    private String typeName;
    private String defaultValue;
    private GroovyType type;
    private final List<GroovyAnnotationRef> annotationRefs;
    
    public SimpleGroovyParameter(final String name) {
        this.name = name;
        this.annotationRefs = new ArrayList<GroovyAnnotationRef>();
    }
    
    public String defaultValue() {
        return this.defaultValue;
    }
    
    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }
    
    public String name() {
        return this.name;
    }
    
    public String typeName() {
        if (this.type == null) {
            return this.typeName;
        }
        return this.type.simpleTypeName();
    }
    
    public void setTypeName(final String typeName) {
        this.typeName = typeName;
    }
    
    public GroovyAnnotationRef[] annotations() {
        return this.annotationRefs.toArray(new GroovyAnnotationRef[this.annotationRefs.size()]);
    }
    
    public void addAnnotationRef(final GroovyAnnotationRef ref) {
        this.annotationRefs.add(ref);
    }
    
    public GroovyType type() {
        return this.type;
    }
    
    public void setType(final GroovyType type) {
        this.type = type;
    }
    
    public boolean isTypeAvailable() {
        return this.type != null;
    }
}
