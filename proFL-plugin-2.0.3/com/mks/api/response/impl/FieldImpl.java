// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.Item;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import com.mks.api.response.APIError;
import com.mks.api.common.XMLResponseDef;
import com.mks.api.response.modifiable.ModifiableField;

public class FieldImpl implements ModifiableField
{
    private String name;
    private String displayName;
    private Object value;
    private String displayValue;
    private String dataType;
    private String modelType;
    private static final String INVALID_DATATYPE = "Resultant field must have a \"com.mks.api.response.Item\" datatype.";
    
    FieldImpl(final String name) {
        this(name, name);
    }
    
    FieldImpl(final String name, final String displayName) {
        this.name = name;
        this.displayName = displayName;
        this.displayValue = null;
    }
    
    public String getModelType() {
        return this.modelType;
    }
    
    public void setModelType(final String modelType) {
        this.modelType = modelType;
    }
    
    public void setDataType(final String dataType) {
        if (this.name.equalsIgnoreCase(XMLResponseDef.XML_RESULT_FIELD) && !dataType.equals("com.mks.api.response.Item")) {
            throw new APIError("Resultant field must have a \"com.mks.api.response.Item\" datatype.");
        }
        this.dataType = dataType;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public void setValue(final Object value) {
        this.value = value;
    }
    
    public String getDataType() {
        return this.dataType;
    }
    
    public Boolean getBoolean() {
        if (this.dataType == null) {
            return null;
        }
        if (this.dataType.equals("java.lang.Boolean")) {
            return (Boolean)this.value;
        }
        throw new UnsupportedOperationException();
    }
    
    public Date getDateTime() {
        if (this.dataType == null) {
            return null;
        }
        if (this.dataType.equals("java.util.Date")) {
            return (Date)this.value;
        }
        throw new UnsupportedOperationException();
    }
    
    public Double getDouble() {
        if (this.dataType == null) {
            return null;
        }
        if (this.dataType.equals("java.lang.Double")) {
            return (Double)this.value;
        }
        throw new UnsupportedOperationException();
    }
    
    public Float getFloat() {
        if (this.dataType == null) {
            return null;
        }
        if (this.dataType.equals("java.lang.Float")) {
            return (Float)this.value;
        }
        if (this.dataType.equals("java.lang.Double")) {
            return new Float(((Double)this.value).floatValue());
        }
        throw new UnsupportedOperationException();
    }
    
    public Integer getInteger() {
        if (this.dataType == null) {
            return null;
        }
        if (this.dataType.equals("java.lang.Integer")) {
            return (Integer)this.value;
        }
        throw new UnsupportedOperationException();
    }
    
    public Long getLong() {
        if (this.dataType == null) {
            return null;
        }
        if (this.dataType.equals("java.lang.Long")) {
            return (Long)this.value;
        }
        throw new UnsupportedOperationException();
    }
    
    public String getString() {
        if (this.dataType == null) {
            return null;
        }
        if (this.dataType.equals("java.lang.String")) {
            return (String)this.value;
        }
        throw new UnsupportedOperationException();
    }
    
    public String getValueAsString() {
        if (this.displayValue != null) {
            return this.displayValue;
        }
        if (this.value == null) {
            return null;
        }
        if (this.value instanceof List) {
            final StringBuffer sb = new StringBuffer();
            final Iterator it = ((List)this.value).iterator();
            while (it.hasNext()) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(it.next().toString());
            }
            return sb.toString();
        }
        return this.value.toString();
    }
    
    public List getList() {
        if (this.dataType == null) {
            return null;
        }
        if (this.dataType.equals("com.mks.api.response.ItemList") || this.dataType.equals("com.mks.api.response.ValueList")) {
            return (List)this.value;
        }
        throw new UnsupportedOperationException();
    }
    
    public Item getItem() {
        if (this.dataType == null) {
            return null;
        }
        if (this.dataType.equals("com.mks.api.response.Item")) {
            return (Item)this.value;
        }
        throw new UnsupportedOperationException();
    }
    
    public void setDisplayValue(final String value) {
        this.displayValue = value;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
}
