// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import java.util.Enumeration;
import com.mks.api.common.XMLResponseDef;
import java.util.Properties;
import com.mks.api.response.modifiable.ModifiableItem;

public class ItemImpl extends AbstractFieldContainer implements ModifiableItem
{
    protected Properties contextProperties;
    
    ItemImpl(final String id, final String context, final String modelType) {
        this.contextProperties = new Properties();
        this.addContext(XMLResponseDef.XML_ID_ATTR, id);
        this.addContext(XMLResponseDef.XML_MODELTYPE_ATTR, modelType);
        this.addContext(XMLResponseDef.XML_CONTEXT_ATTR, context);
    }
    
    public String getId() {
        return this.getContext(XMLResponseDef.XML_ID_ATTR);
    }
    
    public String getContext() {
        return this.getContext(XMLResponseDef.XML_CONTEXT_ATTR);
    }
    
    public String getModelType() {
        return this.getContext(XMLResponseDef.XML_MODELTYPE_ATTR);
    }
    
    public String getDisplayId() {
        String displayId = this.getContext(XMLResponseDef.XML_DISPLAYID_ATTR);
        if (displayId == null) {
            displayId = this.getId();
        }
        return displayId;
    }
    
    public String getContext(final String key) {
        if (key == null) {
            return null;
        }
        return this.contextProperties.getProperty(key);
    }
    
    public String toString() {
        return this.getDisplayId();
    }
    
    void addContext(final String key, final String context) {
        if (key == null || context == null) {
            return;
        }
        this.contextProperties.setProperty(key, context);
    }
    
    public Enumeration getContextKeys() {
        return this.contextProperties.propertyNames();
    }
}
