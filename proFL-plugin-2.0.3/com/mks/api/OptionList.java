// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api;

import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class OptionList
{
    private List list;
    
    public OptionList() {
        this.list = new LinkedList();
    }
    
    public void add(final Option option) {
        this.list.add(option);
    }
    
    public void add(final OptionList list) {
        this.list.addAll(list.list);
    }
    
    public void add(final String name, final String value) {
        this.add(new Option(name, value));
    }
    
    public void clear() {
        this.list.clear();
    }
    
    public Iterator getOptions() {
        return this.list.iterator();
    }
    
    public int size() {
        return this.list.size();
    }
    
    public Option getOption(final String name) {
        Option option = null;
        final Iterator it = this.getOptions();
        while (it.hasNext()) {
            final Option test = it.next();
            if (test.getName().equalsIgnoreCase(name)) {
                option = test;
            }
        }
        return option;
    }
    
    public boolean hasOption(final String name) {
        return this.getOption(name) != null;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() == o.getClass()) {
            return false;
        }
        final OptionList other = (OptionList)o;
        return this.list.containsAll(other.list) && other.list.containsAll(this.list);
    }
    
    public int hashCode() {
        return this.list.hashCode();
    }
}
