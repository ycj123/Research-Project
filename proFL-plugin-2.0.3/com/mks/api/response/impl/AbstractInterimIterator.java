// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.APIException;
import java.util.NoSuchElementException;
import com.mks.api.response.APIExceptionContainer;
import java.util.List;

public abstract class AbstractInterimIterator
{
    protected XMLResponseHandler xrh;
    protected Object lastReadObj;
    private Object nextObj;
    private List list;
    private int listIndex;
    
    protected AbstractInterimIterator(final XMLResponseHandler xrh, final List l) {
        this.xrh = xrh;
        this.list = l;
        this.listIndex = 0;
    }
    
    protected Object getNext() throws APIException {
        APIExceptionContainer obj = null;
        if (this.list != null && this.listIndex < this.list.size()) {
            obj = this.list.get(this.listIndex++);
        }
        else if (this.nextObj != null) {
            if (this.list != null) {
                this.list.add(this.nextObj);
                ++this.listIndex;
            }
            obj = (APIExceptionContainer)this.nextObj;
            this.nextObj = null;
        }
        else if ((obj = (APIExceptionContainer)this.nextObject()) != null && this.list != null) {
            this.list.add(obj);
            ++this.listIndex;
        }
        this.lastReadObj = null;
        if (obj == null) {
            throw new NoSuchElementException();
        }
        final APIException ex = obj.getAPIException();
        if (ex != null) {
            this.lastReadObj = obj;
            throw ex;
        }
        return obj;
    }
    
    public synchronized boolean hasNext() {
        if (this.nextObj != null || (this.list != null && this.listIndex < this.list.size())) {
            return true;
        }
        this.nextObj = this.nextObject();
        return this.nextObj != null;
    }
    
    protected abstract Object nextObject();
}
