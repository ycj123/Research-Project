// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import groovy.lang.GroovyObjectSupport;

@Deprecated
public class GroovyLog extends GroovyObjectSupport
{
    String prefix;
    
    public static GroovyLog newInstance(final Class aClass) {
        return new GroovyLog(aClass);
    }
    
    public GroovyLog() {
        this("");
    }
    
    public GroovyLog(final Class type) {
        this(type.getName());
    }
    
    public GroovyLog(final Object obj) {
        this(obj.getClass());
    }
    
    public GroovyLog(final String prefix) {
        this.prefix = ((prefix != null && prefix.length() > 0) ? ("[" + prefix + ":") : "[");
    }
    
    @Override
    public Object invokeMethod(final String name, Object args) {
        if (args != null && args.getClass().isArray()) {
            args = DefaultGroovyMethods.join((Object[])args, ",");
        }
        System.out.println(this.prefix + name + "] " + args);
        return null;
    }
}
