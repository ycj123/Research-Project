// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.APIException;
import com.mks.api.response.SubRoutine;
import java.util.List;
import com.mks.api.response.SubRoutineIterator;

public class InterimSubRoutineIterator extends AbstractInterimIterator implements SubRoutineIterator
{
    InterimSubRoutineIterator(final XMLResponseHandler xrh, final List srl) {
        super(xrh, srl);
    }
    
    public synchronized SubRoutine next() throws APIException {
        return (SubRoutine)super.getNext();
    }
    
    public SubRoutine getLast() {
        final SubRoutine wio = (SubRoutine)this.lastReadObj;
        return wio;
    }
    
    protected Object nextObject() {
        return this.xrh.getSubRoutine();
    }
}
