// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

public class PropertyValue
{
    private Object bean;
    private MetaProperty mp;
    
    public PropertyValue(final Object bean, final MetaProperty mp) {
        this.bean = bean;
        this.mp = mp;
    }
    
    public String getName() {
        return this.mp.getName();
    }
    
    public Class getType() {
        return this.mp.getType();
    }
    
    public Object getValue() {
        return this.mp.getProperty(this.bean);
    }
    
    public void setValue(final Object value) {
        this.mp.setProperty(this.bean, value);
    }
}
