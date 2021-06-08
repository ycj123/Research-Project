// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

public interface SubRoutineContainer
{
    int getSubRoutineListSize();
    
    SubRoutineIterator getSubRoutines();
    
    SubRoutine getSubRoutine(final String p0);
    
    boolean containsSubRoutine(final String p0);
}
