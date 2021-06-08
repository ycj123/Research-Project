// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.util.ArrayList;
import groovy.lang.MetaClass;
import java.util.List;
import groovy.lang.Closure;

public class IteratorClosureAdapter<T> extends Closure
{
    private final List<T> list;
    private MetaClass metaClass;
    
    public IteratorClosureAdapter(final Object delegate) {
        super(delegate);
        this.list = new ArrayList<T>();
        this.metaClass = InvokerHelper.getMetaClass(this.getClass());
    }
    
    @Override
    public MetaClass getMetaClass() {
        return this.metaClass;
    }
    
    @Override
    public void setMetaClass(final MetaClass metaClass) {
        this.metaClass = metaClass;
    }
    
    public List<T> asList() {
        return this.list;
    }
    
    protected Object doCall(final T argument) {
        this.list.add(argument);
        return null;
    }
}
