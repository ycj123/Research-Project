// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public interface WorkItemContainer
{
    int getWorkItemListSize();
    
    WorkItemIterator getWorkItems();
    
    WorkItem getWorkItem(final String p0);
    
    WorkItem getWorkItem(final String p0, final String p1);
    
    boolean containsWorkItem(final String p0);
    
    boolean containsWorkItem(final String p0, final String p1);
}
