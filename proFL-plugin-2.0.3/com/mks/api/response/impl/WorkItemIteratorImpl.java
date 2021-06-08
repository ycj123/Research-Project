// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.APIException;
import com.mks.api.response.WorkItem;
import java.util.Iterator;
import com.mks.api.response.WorkItemIterator;

public class WorkItemIteratorImpl implements WorkItemIterator
{
    private Iterator it;
    private WorkItem workItem;
    
    WorkItemIteratorImpl(final Iterator it) {
        this.it = it;
    }
    
    public synchronized WorkItem next() throws APIException {
        this.workItem = this.it.next();
        if (this.workItem.getAPIException() != null) {
            throw this.workItem.getAPIException();
        }
        final WorkItem wio = this.workItem;
        this.workItem = null;
        return wio;
    }
    
    public synchronized boolean hasNext() {
        return this.it.hasNext();
    }
    
    public WorkItem getLast() {
        return this.workItem;
    }
}
