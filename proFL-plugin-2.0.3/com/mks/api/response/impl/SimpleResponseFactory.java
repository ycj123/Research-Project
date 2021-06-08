// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.modifiable.ModifiableField;
import com.mks.api.response.modifiable.ModifiableValueList;
import com.mks.api.response.modifiable.ModifiableItemList;
import com.mks.api.response.modifiable.ModifiableResult;
import com.mks.api.response.modifiable.ModifiableItem;
import com.mks.api.response.modifiable.ModifiableSubRoutine;
import java.util.Enumeration;
import java.util.Properties;
import com.mks.api.response.modifiable.ModifiableWorkItem;
import com.mks.api.response.modifiable.ModifiableResponse;
import com.mks.api.response.modifiable.ResponseFactory;

public final class SimpleResponseFactory implements ResponseFactory
{
    private static SimpleResponseFactory singleton;
    
    public static synchronized SimpleResponseFactory getResponseFactory() {
        if (SimpleResponseFactory.singleton == null) {
            SimpleResponseFactory.singleton = new SimpleResponseFactory();
        }
        return SimpleResponseFactory.singleton;
    }
    
    private SimpleResponseFactory() {
    }
    
    public ModifiableResponse createResponse() {
        return this.createResponse(null, null);
    }
    
    public ModifiableResponse createResponse(final String appName, final String commandName) {
        return new ResponseImpl(new ResponseContainer(), appName, commandName);
    }
    
    public ModifiableWorkItem createWorkItem(final String id, final String context, final String modelType) {
        return this.createWorkItem(id, context, modelType, null);
    }
    
    public ModifiableWorkItem createWorkItem(final String id, final String context, final String modelType, final Properties properties) {
        final WorkItemImpl wi = new WorkItemImpl(id, context, modelType);
        if (properties != null) {
            final Enumeration e = properties.propertyNames();
            while (e.hasMoreElements()) {
                final String key = e.nextElement();
                wi.addContext(key, properties.getProperty(key));
            }
        }
        return wi;
    }
    
    public ModifiableSubRoutine createSubRoutine(final String name) {
        return new SubRoutineImpl(new ResponseContainer(), name);
    }
    
    public ModifiableItem createItem(final String name, final String context, final String modelType) {
        return this.createItem(name, context, modelType, null);
    }
    
    public ModifiableItem createItem(final String name, final String context, final String modelType, final Properties properties) {
        final ItemImpl item = new ItemImpl(name, context, modelType);
        if (properties != null) {
            final Enumeration e = properties.propertyNames();
            while (e.hasMoreElements()) {
                final String key = e.nextElement();
                item.addContext(key, properties.getProperty(key));
            }
        }
        return item;
    }
    
    public ModifiableResult createResult() {
        return new ResultImpl();
    }
    
    public ModifiableItemList createItemList() {
        return new ItemListImpl();
    }
    
    public ModifiableValueList createValueList() {
        return new ValueListImpl();
    }
    
    public ModifiableField createField(final String name) {
        return new FieldImpl(name);
    }
    
    public ModifiableField createField(final String name, final String displayName) {
        return new FieldImpl(name, displayName);
    }
    
    static {
        SimpleResponseFactory.singleton = null;
    }
}
