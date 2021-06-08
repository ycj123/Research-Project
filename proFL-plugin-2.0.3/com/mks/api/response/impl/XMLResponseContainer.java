// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.APIError;
import com.mks.api.response.Result;
import com.mks.api.response.InterruptedException;
import com.mks.api.response.Response;
import com.mks.api.response.APIException;
import com.mks.api.response.APIInternalError;
import com.mks.api.IntegrationPointFactory;
import com.mks.api.response.WorkItem;
import com.mks.api.response.WorkItemIterator;
import java.util.List;
import com.mks.api.response.APIExceptionFactory;
import java.text.MessageFormat;
import com.mks.api.response.SubRoutineIterator;
import com.mks.api.response.SubRoutine;

class XMLResponseContainer extends ResponseContainer implements ModifiableXMLResponseContainer
{
    private boolean haveResult;
    private boolean haveException;
    private boolean haveExitCode;
    private boolean subRoutinesPrimed;
    private boolean workItemsPrimed;
    private String wiSelectionType;
    private XMLResponseHandler xrh;
    private static final String INVALID_STATE_MSG = "Unable to retrieve {0} data at this point.";
    private static final String BAD_EXIT_CODE_ERROR_MSG = "Exit code not properly read from the stream.";
    
    public void setXMLResponseHandler(final XMLResponseHandler xrh) {
        this.xrh = xrh;
    }
    
    public String getWorkItemSelectionType() {
        return this.wiSelectionType;
    }
    
    public boolean containsSubRoutine(final String name) {
        if (this.getUseInterim()) {
            if (!this.getCacheContents()) {
                throw new UnsupportedOperationException();
            }
            this.primeSubRoutineList();
        }
        return super.containsSubRoutine(name);
    }
    
    public int getSubRoutineListSize() {
        if (this.getUseInterim()) {
            if (!this.getCacheContents()) {
                throw new UnsupportedOperationException();
            }
            this.primeSubRoutineList();
        }
        return super.getSubRoutineListSize();
    }
    
    public SubRoutine getSubRoutine(final String name) {
        if (this.getUseInterim()) {
            if (!this.getCacheContents()) {
                throw new UnsupportedOperationException();
            }
            this.primeSubRoutineList();
        }
        return super.getSubRoutine(name);
    }
    
    public SubRoutineIterator getSubRoutines() {
        if (!this.getUseInterim()) {
            return super.getSubRoutines();
        }
        if (this.getCacheContents()) {
            return new InterimSubRoutineIterator(this.xrh, this.subRoutineList);
        }
        if (this.xrh.getReadPreSubRoutines() && (!this.xrh.getReadWorkItems() || this.xrh.getReadPostSubRoutines())) {
            final String msg = MessageFormat.format("Unable to retrieve {0} data at this point.", "SubRoutine");
            APIExceptionFactory.createAPIException("APIError", msg);
        }
        return new InterimSubRoutineIterator(this.xrh, null);
    }
    
    public String getConnectionHostname() {
        if (this.getUseInterim()) {
            this.primeConnectionAttributes();
        }
        return super.getConnectionHostname();
    }
    
    public int getConnectionPort() {
        if (this.getUseInterim()) {
            this.primeConnectionAttributes();
        }
        return super.getConnectionPort();
    }
    
    public String getConnectionUsername() {
        if (this.getUseInterim()) {
            this.primeConnectionAttributes();
        }
        return super.getConnectionUsername();
    }
    
    public WorkItemIterator getWorkItems() {
        if (!this.getUseInterim()) {
            return super.getWorkItems();
        }
        this.primeConnectionAttributes();
        if (this.getCacheContents()) {
            return new InterimWorkItemIterator(this.xrh, this.workItemList);
        }
        if (this.xrh.getReadWorkItems()) {
            final String msg = MessageFormat.format("Unable to retrieve {0} data at this point.", "WorkItem");
            APIExceptionFactory.createAPIException("APIError", msg);
        }
        return new InterimWorkItemIterator(this.xrh, null);
    }
    
    public int getWorkItemListSize() {
        if (this.getUseInterim()) {
            if (!this.getCacheContents()) {
                throw new UnsupportedOperationException();
            }
            this.primeWorkItemList();
        }
        return super.getWorkItemListSize();
    }
    
    public WorkItem getWorkItem(final String id, final String context) {
        if (this.getUseInterim()) {
            if (!this.getCacheContents()) {
                throw new UnsupportedOperationException();
            }
            this.primeWorkItemList();
        }
        return super.getWorkItem(id, context);
    }
    
    public WorkItem getWorkItem(final String id) {
        if (this.getUseInterim()) {
            if (!this.getCacheContents()) {
                throw new UnsupportedOperationException();
            }
            this.primeWorkItemList();
        }
        return super.getWorkItem(id);
    }
    
    public boolean containsWorkItem(final String id) {
        if (this.getUseInterim()) {
            if (!this.getCacheContents()) {
                throw new UnsupportedOperationException();
            }
            this.primeWorkItemList();
        }
        try {
            return super.containsWorkItem(id);
        }
        catch (APIInternalError err) {
            IntegrationPointFactory.getLogger().exception(this, "API", 0, err);
            return false;
        }
    }
    
    public boolean containsWorkItem(final String id, final String context) {
        if (this.getUseInterim()) {
            if (this.getCacheContents()) {
                this.primeWorkItemList();
            }
            throw new UnsupportedOperationException();
        }
        try {
            return super.containsWorkItem(id, context);
        }
        catch (APIInternalError err) {
            IntegrationPointFactory.getLogger().exception(this, "API", 0, err);
            return false;
        }
    }
    
    public APIException getAPIException() throws InterruptedException {
        if (this.getUseInterim() && !this.haveException) {
            if (this.xrh.isInterrupted()) {
                final APIException ex = APIExceptionFactory.createAPIException("InterruptedException", (Response)null);
                throw (InterruptedException)ex;
            }
            this.primeLists();
            this.getResult();
            this.setAPIException(this.xrh.getException());
            this.haveException = true;
        }
        return super.getAPIException();
    }
    
    public Result getResult() throws InterruptedException {
        if (this.getUseInterim() && !this.haveResult) {
            if (this.xrh.isInterrupted()) {
                final APIException ex = APIExceptionFactory.createAPIException("InterruptedException", (Response)null);
                throw (InterruptedException)ex;
            }
            this.primeLists();
            this.setResult(this.xrh.getResult(true));
            this.haveResult = true;
        }
        return super.getResult();
    }
    
    public int getExitCode() throws InterruptedException {
        if (this.getUseInterim() && !this.haveExitCode) {
            if (this.xrh.isInterrupted()) {
                final APIException ex = APIExceptionFactory.createAPIException("InterruptedException", (Response)null);
                throw (InterruptedException)ex;
            }
            this.primeLists();
            this.getResult();
            this.getAPIException();
            this.setExitCode(this.xrh.getExitCode());
            this.haveExitCode = true;
        }
        final int exitCode = super.getExitCode();
        if (exitCode == Integer.MIN_VALUE) {
            final String msg = "Exit code not properly read from the stream.";
            APIExceptionFactory.createAPIException("APIInternalError", msg);
        }
        return exitCode;
    }
    
    public void interrupt() {
        this.xrh.interrupt();
    }
    
    private void primeSubRoutineList() {
        if (!this.subRoutinesPrimed) {
            final SubRoutineIterator it = this.getSubRoutines();
            while (it.hasNext()) {
                try {
                    it.next();
                }
                catch (APIException ex) {
                    IntegrationPointFactory.getLogger().exception(this, "API", 0, ex);
                }
            }
            this.subRoutinesPrimed = true;
        }
    }
    
    private void primeConnectionAttributes() {
        if (!this.subRoutinesPrimed && !this.xrh.getReadPreSubRoutines()) {
            this.primeSubRoutineList();
        }
        this.xrh.readResponseConnectionAttributes();
    }
    
    private void primeWorkItemList() {
        if (!this.workItemsPrimed) {
            final WorkItemIterator it = this.getWorkItems();
            while (it.hasNext()) {
                try {
                    it.next();
                }
                catch (APIException ex) {
                    IntegrationPointFactory.getLogger().exception(this, "API", 0, ex);
                }
            }
            this.workItemsPrimed = true;
        }
    }
    
    private void primeLists() {
        try {
            this.primeSubRoutineList();
        }
        catch (APIError err) {
            if (this.getCacheContents()) {
                throw err;
            }
        }
        try {
            this.primeWorkItemList();
        }
        catch (APIError err) {
            if (this.getCacheContents()) {
                throw err;
            }
        }
        try {
            this.primeSubRoutineList();
        }
        catch (APIError err) {
            if (this.getCacheContents()) {
                throw err;
            }
        }
    }
}
