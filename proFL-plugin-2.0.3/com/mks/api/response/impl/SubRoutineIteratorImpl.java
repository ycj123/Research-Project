// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.APIException;
import com.mks.api.response.SubRoutine;
import java.util.Iterator;
import com.mks.api.response.SubRoutineIterator;

public class SubRoutineIteratorImpl implements SubRoutineIterator
{
    private Iterator it;
    private SubRoutine subRoutine;
    
    SubRoutineIteratorImpl(final Iterator it) {
        this.it = it;
    }
    
    public synchronized SubRoutine next() throws APIException {
        this.subRoutine = this.it.next();
        if (this.subRoutine.getAPIException() != null) {
            throw this.subRoutine.getAPIException();
        }
        final SubRoutine sro = this.subRoutine;
        this.subRoutine = null;
        return sro;
    }
    
    public synchronized boolean hasNext() {
        return this.it.hasNext();
    }
    
    public SubRoutine getLast() {
        return this.subRoutine;
    }
}
