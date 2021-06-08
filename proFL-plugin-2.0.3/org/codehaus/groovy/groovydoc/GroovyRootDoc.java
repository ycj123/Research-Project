// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.groovydoc;

import java.util.Map;
import java.util.List;

public interface GroovyRootDoc extends GroovyDoc, GroovyDocErrorReporter
{
    GroovyClassDoc classNamed(final String p0);
    
    GroovyClassDoc[] classes();
    
    String[][] options();
    
    GroovyPackageDoc packageNamed(final String p0);
    
    GroovyClassDoc[] specifiedClasses();
    
    GroovyPackageDoc[] specifiedPackages();
    
    Map<String, GroovyClassDoc> getVisibleClasses(final List p0);
}
