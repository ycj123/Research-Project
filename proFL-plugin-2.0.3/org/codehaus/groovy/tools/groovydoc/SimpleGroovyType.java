// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import org.codehaus.groovy.groovydoc.GroovyType;

public class SimpleGroovyType implements GroovyType
{
    private String typeName;
    
    public SimpleGroovyType(final String typeName) {
        this.typeName = typeName;
    }
    
    public String typeName() {
        return this.typeName;
    }
    
    public boolean isPrimitive() {
        return false;
    }
    
    public String qualifiedTypeName() {
        return this.typeName.startsWith("DefaultPackage.") ? this.typeName.substring("DefaultPackage.".length()) : this.typeName;
    }
    
    public String simpleTypeName() {
        final int lastDot = this.typeName.lastIndexOf(46);
        if (lastDot < 0) {
            return this.typeName;
        }
        return this.typeName.substring(lastDot + 1);
    }
}
