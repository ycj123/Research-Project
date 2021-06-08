// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.SubRoutine;
import java.util.Iterator;
import com.mks.api.response.SubRoutineIterator;
import java.util.ArrayList;
import java.util.List;
import com.mks.api.response.APIException;
import com.mks.api.response.Result;
import com.mks.api.response.modifiable.ModifiableWorkItem;

public class WorkItemImpl extends ItemImpl implements ModifiableWorkItem
{
    private Result result;
    private APIException apiException;
    private List subRoutineList;
    
    protected WorkItemImpl(final String id, final String context, final String modelType) {
        super(id, context, modelType);
        this.subRoutineList = new ArrayList();
    }
    
    public APIException getAPIException() {
        return this.apiException;
    }
    
    public void setAPIException(final APIException ex) {
        this.apiException = ex;
    }
    
    public Result getResult() {
        return this.result;
    }
    
    public void setResult(final Result result) {
        this.result = result;
    }
    
    public SubRoutineIterator getSubRoutines() {
        return new SubRoutineIteratorImpl(this.subRoutineList.iterator());
    }
    
    public List getSubRoutineList() {
        return this.subRoutineList;
    }
    
    public int getSubRoutineListSize() {
        return this.subRoutineList.size();
    }
    
    public SubRoutine getSubRoutine(final String name) {
        for (final SubRoutine sr : this.subRoutineList) {
            if (sr.getRoutine().equals(name)) {
                return sr;
            }
        }
        return null;
    }
    
    public boolean containsSubRoutine(final String name) {
        for (final SubRoutine sr : this.subRoutineList) {
            if (sr.getRoutine().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public void add(final SubRoutine subRoutine) {
        this.subRoutineList.add(subRoutine);
    }
}
