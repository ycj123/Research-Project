// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import com.mks.api.response.Item;
import com.mks.api.response.modifiable.ModifiableItemList;
import java.util.ArrayList;

public class ItemListImpl extends ArrayList implements ModifiableItemList
{
    ItemListImpl() {
    }
    
    public void add(final Item item) {
        super.add(item);
    }
    
    public Item getItem(final String id) {
        for (final Item item : this) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        throw new NoSuchElementException(id);
    }
    
    public Iterator getItems() {
        return super.iterator();
    }
    
    public int getItemListSize() {
        return super.size();
    }
    
    public boolean contains(final String id) {
        for (final Item i : this) {
            if (i.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean contains(final String id, final String context, final String modelType) {
        return this.contains(new ItemImpl(id, context, modelType));
    }
    
    public boolean contains(final Object o) {
        if (o instanceof String) {
            return this.contains((String)o);
        }
        return super.contains(o);
    }
}
