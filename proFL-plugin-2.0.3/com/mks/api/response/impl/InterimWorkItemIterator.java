// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.APIException;
import com.mks.api.response.WorkItem;
import java.util.List;
import com.mks.api.response.WorkItemIterator;

public class InterimWorkItemIterator extends AbstractInterimIterator implements WorkItemIterator
{
    InterimWorkItemIterator(final XMLResponseHandler xrh, final List wil) {
        super(xrh, wil);
    }
    
    public synchronized WorkItem next() throws APIException {
        return (WorkItem)super.getNext();
    }
    
    public WorkItem getLast() {
        final WorkItem wio = (WorkItem)this.lastReadObj;
        return wio;
    }
    
    protected Object nextObject() {
        if (this.xrh.readResponseWorkItemsTagAttributes()) {
            return this.xrh.getWorkItem();
        }
        return null;
    }
}
