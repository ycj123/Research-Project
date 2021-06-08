// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.lang.reflect.Method;

public interface MutableMetaClass extends MetaClass
{
    boolean isModified();
    
    void addNewInstanceMethod(final Method p0);
    
    void addNewStaticMethod(final Method p0);
    
    void addMetaMethod(final MetaMethod p0);
    
    void addMetaBeanProperty(final MetaBeanProperty p0);
}
