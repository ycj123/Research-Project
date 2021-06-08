// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.util.Map;

public class MetaExpandoProperty extends MetaProperty
{
    Object value;
    
    public MetaExpandoProperty(final Map.Entry entry) {
        super(entry.getKey(), Object.class);
        this.value = null;
        this.value = entry.getValue();
    }
    
    @Override
    public Object getProperty(final Object object) {
        return this.value;
    }
    
    @Override
    public void setProperty(final Object object, final Object newValue) {
        this.value = newValue;
    }
}
