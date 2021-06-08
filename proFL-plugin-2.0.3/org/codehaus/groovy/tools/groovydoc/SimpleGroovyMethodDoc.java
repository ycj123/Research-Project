// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import org.codehaus.groovy.groovydoc.GroovyClassDoc;
import org.codehaus.groovy.groovydoc.GroovyType;
import org.codehaus.groovy.groovydoc.GroovyMethodDoc;

public class SimpleGroovyMethodDoc extends SimpleGroovyExecutableMemberDoc implements GroovyMethodDoc
{
    private GroovyType returnType;
    
    public SimpleGroovyMethodDoc(final String name, final GroovyClassDoc belongsToClass) {
        super(name, belongsToClass);
    }
    
    public GroovyType returnType() {
        return this.returnType;
    }
    
    public void setReturnType(final GroovyType returnType) {
        this.returnType = returnType;
    }
    
    @Override
    public boolean isAbstract() {
        return false;
    }
    
    public GroovyClassDoc overriddenClass() {
        return null;
    }
    
    public GroovyMethodDoc overriddenMethod() {
        return null;
    }
    
    public GroovyType overriddenType() {
        return null;
    }
    
    public boolean overrides(final GroovyMethodDoc arg0) {
        return false;
    }
}
