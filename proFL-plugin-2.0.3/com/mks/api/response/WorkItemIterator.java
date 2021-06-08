// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public interface WorkItemIterator
{
    WorkItem next() throws APIException;
    
    boolean hasNext();
    
    WorkItem getLast();
}
