// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.util.EventObject;

public class MetaClassRegistryChangeEvent extends EventObject
{
    private Class clazz;
    private MetaClass metaClass;
    
    public MetaClassRegistryChangeEvent(final Object source, final Class clazz, final MetaClass metaClass) {
        super(source);
        this.clazz = clazz;
        this.metaClass = metaClass;
    }
    
    public Class getClassToUpdate() {
        return this.clazz;
    }
    
    public MetaClass getNewMetaClass() {
        return this.metaClass;
    }
}
