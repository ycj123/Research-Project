// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public interface SubRoutineIterator
{
    SubRoutine next() throws APIException;
    
    boolean hasNext();
    
    SubRoutine getLast();
}
