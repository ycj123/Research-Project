// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.list;

import java.util.List;
import org.mudebug.prapr.reloc.commons.collections.Factory;

public class LazyList extends AbstractSerializableListDecorator
{
    private static final long serialVersionUID = -1708388017160694542L;
    protected final Factory factory;
    
    public static List decorate(final List list, final Factory factory) {
        return new LazyList(list, factory);
    }
    
    protected LazyList(final List list, final Factory factory) {
        super(list);
        if (factory == null) {
            throw new IllegalArgumentException("Factory must not be null");
        }
        this.factory = factory;
    }
    
    public Object get(final int index) {
        final int size = this.getList().size();
        if (index >= size) {
            for (int i = size; i < index; ++i) {
                this.getList().add(null);
            }
            final Object object = this.factory.create();
            this.getList().add(object);
            return object;
        }
        Object object2 = this.getList().get(index);
        if (object2 == null) {
            object2 = this.factory.create();
            this.getList().set(index, object2);
            return object2;
        }
        return object2;
    }
    
    public List subList(final int fromIndex, final int toIndex) {
        final List sub = this.getList().subList(fromIndex, toIndex);
        return new LazyList(sub, this.factory);
    }
}
