// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

public interface AdaptingMetaClass extends MetaClass
{
    MetaClass getAdaptee();
    
    void setAdaptee(final MetaClass p0);
}
