// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import org.codehaus.groovy.groovydoc.GroovyProgramElementDoc;

public class SimpleGroovyAbstractableElementDoc extends SimpleGroovyProgramElementDoc implements GroovyProgramElementDoc
{
    private boolean abstractElement;
    
    public SimpleGroovyAbstractableElementDoc(final String name) {
        super(name);
    }
    
    public void setAbstract(final boolean b) {
        this.abstractElement = b;
    }
    
    public boolean isAbstract() {
        return this.abstractElement;
    }
}
