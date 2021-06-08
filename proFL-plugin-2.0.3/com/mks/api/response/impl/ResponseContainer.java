// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.SubRoutineIterator;
import com.mks.api.response.SubRoutine;
import com.mks.api.response.InterruptedException;
import com.mks.api.response.WorkItemIterator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import com.mks.api.response.WorkItem;
import java.util.ArrayList;
import java.util.List;
import com.mks.api.response.APIException;
import com.mks.api.response.Result;
import com.mks.api.response.modifiable.ModifiableResultContainer;
import com.mks.api.response.modifiable.ModifiableAPIExceptionContainer;
import com.mks.api.response.modifiable.ModifiableWorkItemContainer;
import com.mks.api.response.modifiable.ModifiableSubRoutineContainer;

class ResponseContainer implements ModifiableSubRoutineContainer, ModifiableWorkItemContainer, ModifiableAPIExceptionContainer, ModifiableResultContainer
{
    protected Result result;
    protected APIException apiException;
    protected int exitCode;
    protected List workItemList;
    protected List subRoutineList;
    protected String wiSelectionType;
    protected boolean interimResults;
    protected boolean cacheResults;
    private String hostname;
    private int port;
    private String username;
    
    protected ResponseContainer() {
        this.port = -1;
        this.workItemList = new ArrayList();
        this.subRoutineList = new ArrayList();
        this.exitCode = Integer.MIN_VALUE;
    }
    
    public void setWorkItemSelectionType(final String workItemSelectionType) {
        this.wiSelectionType = workItemSelectionType;
    }
    
    public String getWorkItemSelectionType() {
        return this.wiSelectionType;
    }
    
    public boolean getUseInterim() {
        return this.interimResults;
    }
    
    public void setUseInterim(final boolean flag) {
        this.interimResults = flag;
    }
    
    public boolean getCacheContents() {
        return this.cacheResults;
    }
    
    public void setCacheContents(final boolean flag) {
        this.cacheResults = flag;
    }
    
    public void add(final WorkItem wi) {
        this.workItemList.add(wi);
        if (this.wiSelectionType == null) {
            this.setWorkItemSelectionType(wi.getModelType());
        }
    }
    
    public WorkItem getWorkItem(final String id) {
        for (final WorkItem wi : this.workItemList) {
            if (wi.getId().equals(id)) {
                return wi;
            }
        }
        throw new NoSuchElementException(id);
    }
    
    public WorkItem getWorkItem(final String id, final String context) {
        for (final WorkItem wi : this.workItemList) {
            if (wi.getId().equals(id) && wi.getContext().equals(context)) {
                return wi;
            }
        }
        throw new NoSuchElementException(id);
    }
    
    public boolean containsWorkItem(final String id) {
        try {
            return this.getWorkItem(id) != null;
        }
        catch (NoSuchElementException nsee) {
            return false;
        }
    }
    
    public boolean containsWorkItem(final String id, final String context) {
        try {
            return this.getWorkItem(id, context) != null;
        }
        catch (NoSuchElementException nsee) {
            return false;
        }
    }
    
    public int getWorkItemListSize() {
        return this.workItemList.size();
    }
    
    public WorkItemIterator getWorkItems() {
        return new WorkItemIteratorImpl(this.workItemList.iterator());
    }
    
    public void setAPIException(final APIException ae) {
        this.apiException = ae;
    }
    
    public APIException getAPIException() throws InterruptedException {
        return this.apiException;
    }
    
    public void add(final SubRoutine sr) {
        this.subRoutineList.add(sr);
    }
    
    public boolean containsSubRoutine(final String name) {
        try {
            return this.getSubRoutine(name) != null;
        }
        catch (NoSuchElementException nsee) {
            return false;
        }
    }
    
    public SubRoutine getSubRoutine(final String name) {
        for (final SubRoutine sr : this.subRoutineList) {
            if (name.equals(sr.getRoutine())) {
                return sr;
            }
        }
        throw new NoSuchElementException(name);
    }
    
    public int getSubRoutineListSize() {
        return this.subRoutineList.size();
    }
    
    public SubRoutineIterator getSubRoutines() {
        return new SubRoutineIteratorImpl(this.subRoutineList.iterator());
    }
    
    public Result getResult() throws InterruptedException {
        return this.result;
    }
    
    public void setResult(final Result res) {
        this.result = res;
    }
    
    public int getExitCode() throws InterruptedException {
        return this.exitCode;
    }
    
    public void setExitCode(final int exitCode) {
        this.exitCode = exitCode;
    }
    
    public void setConnectionHostname(final String hostname) {
        this.hostname = hostname;
    }
    
    public String getConnectionHostname() {
        return this.hostname;
    }
    
    public void setConnectionPort(final int port) {
        this.port = port;
    }
    
    public int getConnectionPort() {
        return this.port;
    }
    
    public void setConnectionUsername(final String username) {
        this.username = username;
    }
    
    public String getConnectionUsername() {
        return this.username;
    }
}
