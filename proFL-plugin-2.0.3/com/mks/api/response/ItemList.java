// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

import java.util.Iterator;
import java.util.List;

public interface ItemList extends List
{
    Item getItem(final String p0);
    
    Iterator getItems();
    
    int getItemListSize();
    
    boolean contains(final String p0);
    
    boolean contains(final String p0, final String p1, final String p2);
}
