// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import org.codehaus.groovy.groovydoc.GroovyClassDoc;
import org.codehaus.groovy.groovydoc.GroovyConstructorDoc;

public class SimpleGroovyConstructorDoc extends SimpleGroovyExecutableMemberDoc implements GroovyConstructorDoc
{
    public SimpleGroovyConstructorDoc(final String name, final GroovyClassDoc belongsToClass) {
        super(name, belongsToClass);
    }
}
