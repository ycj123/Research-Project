// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.collection;

import java.util.Iterator;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.Predicate;

public class PredicatedCollection extends AbstractSerializableCollectionDecorator
{
    private static final long serialVersionUID = -5259182142076705162L;
    protected final Predicate predicate;
    
    public static Collection decorate(final Collection coll, final Predicate predicate) {
        return new PredicatedCollection(coll, predicate);
    }
    
    protected PredicatedCollection(final Collection coll, final Predicate predicate) {
        super(coll);
        if (predicate == null) {
            throw new IllegalArgumentException("Predicate must not be null");
        }
        this.predicate = predicate;
        final Iterator it = coll.iterator();
        while (it.hasNext()) {
            this.validate(it.next());
        }
    }
    
    protected void validate(final Object object) {
        if (!this.predicate.evaluate(object)) {
            throw new IllegalArgumentException("Cannot add Object '" + object + "' - Predicate rejected it");
        }
    }
    
    public boolean add(final Object object) {
        this.validate(object);
        return this.getCollection().add(object);
    }
    
    public boolean addAll(final Collection coll) {
        final Iterator it = coll.iterator();
        while (it.hasNext()) {
            this.validate(it.next());
        }
        return this.getCollection().addAll(coll);
    }
}
