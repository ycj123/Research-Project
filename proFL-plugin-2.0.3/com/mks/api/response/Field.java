// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

import java.util.List;
import java.util.Date;

public interface Field
{
    public static final String BOOLEAN_TYPE = "java.lang.Boolean";
    public static final String DATE_TYPE = "java.util.Date";
    public static final String DOUBLE_TYPE = "java.lang.Double";
    public static final String FLOAT_TYPE = "java.lang.Float";
    public static final String INTEGER_TYPE = "java.lang.Integer";
    public static final String LONG_TYPE = "java.lang.Long";
    public static final String STRING_TYPE = "java.lang.String";
    public static final String ITEM_TYPE = "com.mks.api.response.Item";
    public static final String VALUE_LIST_TYPE = "com.mks.api.response.ValueList";
    public static final String ITEM_LIST_TYPE = "com.mks.api.response.ItemList";
    
    String getName();
    
    Object getValue();
    
    String getModelType();
    
    String getDataType();
    
    Boolean getBoolean();
    
    Date getDateTime();
    
    Double getDouble();
    
    Float getFloat();
    
    Integer getInteger();
    
    Long getLong();
    
    String getString();
    
    List getList();
    
    Item getItem();
    
    String getValueAsString();
    
    String getDisplayName();
}
