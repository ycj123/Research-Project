// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.Result;
import com.mks.api.response.InterruptedException;
import com.mks.api.response.APIException;
import com.mks.api.response.WorkItemIterator;
import com.mks.api.response.WorkItem;
import com.mks.api.response.SubRoutine;
import com.mks.api.response.SubRoutineIterator;
import com.mks.api.response.modifiable.ModifiableResultContainer;
import com.mks.api.response.modifiable.ModifiableAPIExceptionContainer;
import com.mks.api.response.modifiable.ModifiableWorkItemContainer;
import com.mks.api.response.modifiable.ModifiableSubRoutineContainer;

public abstract class DelegatedResponseContainer implements ModifiableSubRoutineContainer, ModifiableWorkItemContainer, ModifiableAPIExceptionContainer, ModifiableResultContainer
{
    protected ResponseContainer rc;
    
    protected DelegatedResponseContainer(final ResponseContainer rc) {
        this.rc = rc;
    }
    
    public boolean getUseInterim() {
        return this.rc.getUseInterim();
    }
    
    public void setUseInterim(final boolean flag) {
        this.rc.setUseInterim(flag);
    }
    
    public void setCacheContents(final boolean flag) {
        this.rc.setCacheContents(flag);
    }
    
    public boolean getCacheContents() {
        return this.rc.getCacheContents();
    }
    
    public String getWorkItemSelectionType() {
        return this.rc.getWorkItemSelectionType();
    }
    
    public void setWorkItemSelectionType(final String type) {
        this.rc.setWorkItemSelectionType(type);
    }
    
    public SubRoutineIterator getSubRoutines() {
        return this.rc.getSubRoutines();
    }
    
    public boolean containsSubRoutine(final String name) {
        return this.rc.containsSubRoutine(name);
    }
    
    public SubRoutine getSubRoutine(final String name) {
        return this.rc.getSubRoutine(name);
    }
    
    public int getSubRoutineListSize() {
        return this.rc.getSubRoutineListSize();
    }
    
    public void add(final SubRoutine sr) {
        this.rc.add(sr);
    }
    
    public void setConnectionHostname(final String hostname) {
        this.rc.setConnectionHostname(hostname);
    }
    
    public String getConnectionHostname() {
        return this.rc.getConnectionHostname();
    }
    
    public void setConnectionPort(final int port) {
        this.rc.setConnectionPort(port);
    }
    
    public int getConnectionPort() {
        return this.rc.getConnectionPort();
    }
    
    public void setConnectionUsername(final String username) {
        this.rc.setConnectionUsername(username);
    }
    
    public String getConnectionUsername() {
        return this.rc.getConnectionUsername();
    }
    
    public WorkItem getWorkItem(final String id) {
        return this.rc.getWorkItem(id);
    }
    
    public WorkItem getWorkItem(final String id, final String context) {
        return this.rc.getWorkItem(id, context);
    }
    
    public boolean containsWorkItem(final String id) {
        return this.rc.containsWorkItem(id);
    }
    
    public boolean containsWorkItem(final String id, final String context) {
        return this.rc.containsWorkItem(id, context);
    }
    
    public int getWorkItemListSize() {
        return this.rc.getWorkItemListSize();
    }
    
    public WorkItemIterator getWorkItems() {
        return this.rc.getWorkItems();
    }
    
    public void add(final WorkItem wi) {
        this.rc.add(wi);
    }
    
    public void setAPIException(final APIException ae) {
        this.rc.setAPIException(ae);
    }
    
    public APIException getAPIException() throws InterruptedException {
        return this.rc.getAPIException();
    }
    
    public void setResult(final Result res) {
        this.rc.setResult(res);
    }
    
    public int getExitCode() throws InterruptedException {
        return this.rc.getExitCode();
    }
    
    public void setExitCode(final int exitCode) {
        this.rc.setExitCode(exitCode);
    }
}
