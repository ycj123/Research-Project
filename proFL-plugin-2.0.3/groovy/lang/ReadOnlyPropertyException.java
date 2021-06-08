// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

public class ReadOnlyPropertyException extends MissingPropertyException
{
    public ReadOnlyPropertyException(final String property, final Class type) {
        super("Cannot set readonly property: " + property + " for class: " + type.getName(), property, type);
    }
    
    public ReadOnlyPropertyException(final String property, final String classname) {
        super("Cannot set readonly property: " + property + " for class: " + classname);
    }
}
