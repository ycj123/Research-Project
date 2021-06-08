// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api;

import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SelectionList
{
    private List list;
    
    public SelectionList() {
        this.list = new LinkedList();
    }
    
    public void add(final String selection) {
        this.list.add(selection);
    }
    
    public void add(final SelectionList selList) {
        this.list.addAll(selList.list);
    }
    
    public Iterator getSelections() {
        return this.list.iterator();
    }
    
    public String getSelection(final int idx) {
        return this.list.get(idx);
    }
    
    public int size() {
        return this.list.size();
    }
    
    public void clear() {
        this.list.clear();
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() == o.getClass()) {
            return false;
        }
        final SelectionList other = (SelectionList)o;
        return this.list.equals(other.list);
    }
    
    public int hashCode() {
        return this.list.hashCode();
    }
}
