// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.util.EventListener;

public interface MetaClassRegistryChangeEventListener extends EventListener
{
    void updateConstantMetaClass(final MetaClassRegistryChangeEvent p0);
}
