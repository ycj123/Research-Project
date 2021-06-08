// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.runtime.MetaClassHelper;

public abstract class MetaProperty
{
    protected final String name;
    protected Class type;
    public static final String PROPERTY_SET_PREFIX = "set";
    
    public MetaProperty(final String name, final Class type) {
        this.name = name;
        this.type = type;
    }
    
    public abstract Object getProperty(final Object p0);
    
    public abstract void setProperty(final Object p0, final Object p1);
    
    public String getName() {
        return this.name;
    }
    
    public Class getType() {
        return this.type;
    }
    
    public int getModifiers() {
        return 1;
    }
    
    public static String getGetterName(final String propertyName, final Class type) {
        final String prefix = (type == Boolean.TYPE || type == Boolean.class) ? "is" : "get";
        return prefix + MetaClassHelper.capitalize(propertyName);
    }
    
    public static String getSetterName(final String propertyName) {
        return "set" + MetaClassHelper.capitalize(propertyName);
    }
}
