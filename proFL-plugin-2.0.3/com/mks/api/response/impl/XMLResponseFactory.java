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
import com.mks.api.response.modifiable.ModifiableWorkItem;
import com.mks.api.CmdRunner;
import com.mks.api.response.modifiable.ModifiableResponse;
import com.mks.api.response.modifiable.ResponseFactory;

class XMLResponseFactory implements ResponseFactory
{
    private static XMLResponseFactory instance;
    
    static XMLResponseFactory getInstance() {
        return XMLResponseFactory.instance;
    }
    
    private XMLResponseFactory() {
    }
    
    public ModifiableResponse createResponse() {
        return this.createResponse(null, null);
    }
    
    public ModifiableXMLResponse createXMLResponse(final CmdRunner cmdRunner, final String appName, final String commandName) {
        return new XMLResponseImpl(cmdRunner, new XMLResponseContainer(), appName, commandName);
    }
    
    public ModifiableResponse createResponse(final String appName, final String commandName) {
        return this.createXMLResponse(null, appName, commandName);
    }
    
    public ModifiableWorkItem createWorkItem(final String id, final String context, final String modelType) {
        return new WorkItemImpl(id, context, modelType);
    }
    
    public ModifiableSubRoutine createSubRoutine(final String name) {
        return this.createXMLSubRoutine(name);
    }
    
    public ModifiableXMLSubRoutine createXMLSubRoutine(final String name) {
        return new XMLSubRoutineImpl(new XMLResponseContainer(), name);
    }
    
    public ModifiableItem createItem(final String name, final String context, final String modelType) {
        return new ItemImpl(name, context, modelType);
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
        XMLResponseFactory.instance = new XMLResponseFactory();
    }
}
