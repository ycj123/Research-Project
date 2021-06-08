// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.bag;

import java.util.Set;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.Predicate;
import org.mudebug.prapr.reloc.commons.collections.Bag;
import org.mudebug.prapr.reloc.commons.collections.collection.PredicatedCollection;

public class PredicatedBag extends PredicatedCollection implements Bag
{
    private static final long serialVersionUID = -2575833140344736876L;
    
    public static Bag decorate(final Bag bag, final Predicate predicate) {
        return new PredicatedBag(bag, predicate);
    }
    
    protected PredicatedBag(final Bag bag, final Predicate predicate) {
        super(bag, predicate);
    }
    
    protected Bag getBag() {
        return (Bag)this.getCollection();
    }
    
    public boolean add(final Object object, final int count) {
        this.validate(object);
        return this.getBag().add(object, count);
    }
    
    public boolean remove(final Object object, final int count) {
        return this.getBag().remove(object, count);
    }
    
    public Set uniqueSet() {
        return this.getBag().uniqueSet();
    }
    
    public int getCount(final Object object) {
        return this.getBag().getCount(object);
    }
}
