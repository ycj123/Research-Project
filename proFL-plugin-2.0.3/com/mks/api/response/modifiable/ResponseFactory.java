// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.modifiable;

public interface ResponseFactory
{
    ModifiableResponse createResponse();
    
    ModifiableWorkItem createWorkItem(final String p0, final String p1, final String p2);
    
    ModifiableSubRoutine createSubRoutine(final String p0);
    
    ModifiableItem createItem(final String p0, final String p1, final String p2);
    
    ModifiableResult createResult();
    
    ModifiableItemList createItemList();
    
    ModifiableValueList createValueList();
    
    ModifiableField createField(final String p0);
    
    ModifiableField createField(final String p0, final String p1);
}
