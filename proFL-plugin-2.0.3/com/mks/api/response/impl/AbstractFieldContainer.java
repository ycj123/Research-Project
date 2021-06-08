// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import com.mks.api.response.Field;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import com.mks.api.response.modifiable.ModifiableFieldContainer;

public abstract class AbstractFieldContainer implements ModifiableFieldContainer
{
    private List fields;
    private Set fieldIds;
    
    AbstractFieldContainer() {
        this.fields = new ArrayList();
        this.fieldIds = new HashSet();
    }
    
    public void add(final Field field) {
        if (field != null) {
            this.fields.add(field);
            this.fieldIds.add(field.getName().toLowerCase());
        }
    }
    
    public Field getField(final String name) {
        for (final Field f : this.fields) {
            if (f.getName().equalsIgnoreCase(name)) {
                return f;
            }
        }
        throw new NoSuchElementException(name);
    }
    
    public Iterator getFields() {
        return Collections.unmodifiableList((List<?>)this.fields).iterator();
    }
    
    public int getFieldListSize() {
        return this.fields.size();
    }
    
    public boolean contains(final String id) {
        return this.fieldIds.contains(id.toLowerCase());
    }
}
