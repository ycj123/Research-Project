// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import org.codehaus.groovy.groovydoc.GroovyType;
import org.codehaus.groovy.groovydoc.GroovyParameter;
import java.util.ArrayList;
import org.codehaus.groovy.groovydoc.GroovyClassDoc;
import java.util.List;
import org.codehaus.groovy.groovydoc.GroovyExecutableMemberDoc;

public class SimpleGroovyExecutableMemberDoc extends SimpleGroovyMemberDoc implements GroovyExecutableMemberDoc
{
    List parameters;
    
    public SimpleGroovyExecutableMemberDoc(final String name, final GroovyClassDoc belongsToClass) {
        super(name, belongsToClass);
        this.parameters = new ArrayList();
    }
    
    public GroovyParameter[] parameters() {
        return this.parameters.toArray(new GroovyParameter[this.parameters.size()]);
    }
    
    public void add(final GroovyParameter parameter) {
        this.parameters.add(parameter);
    }
    
    public String flatSignature() {
        return null;
    }
    
    public boolean isNative() {
        return false;
    }
    
    public boolean isSynchronized() {
        return false;
    }
    
    public boolean isVarArgs() {
        return false;
    }
    
    public String signature() {
        return null;
    }
    
    public GroovyClassDoc[] thrownExceptions() {
        return null;
    }
    
    public GroovyType[] thrownExceptionTypes() {
        return null;
    }
}
