// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.lang.reflect.Array;

public class MetaArrayLengthProperty extends MetaProperty
{
    public MetaArrayLengthProperty() {
        super("length", Integer.TYPE);
    }
    
    @Override
    public Object getProperty(final Object object) {
        return Array.getLength(object);
    }
    
    @Override
    public void setProperty(final Object object, final Object newValue) {
        throw new ReadOnlyPropertyException("length", object.getClass());
    }
}
