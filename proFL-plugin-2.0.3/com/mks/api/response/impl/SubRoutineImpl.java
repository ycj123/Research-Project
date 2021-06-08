// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.APIException;
import com.mks.api.response.InterruptedException;
import com.mks.api.IntegrationPointFactory;
import com.mks.api.response.Result;
import com.mks.api.response.modifiable.ModifiableSubRoutine;

public class SubRoutineImpl extends DelegatedResponseContainer implements ModifiableSubRoutine
{
    private String routine;
    
    protected SubRoutineImpl(final ResponseContainer rc, final String routine) {
        super(rc);
        this.routine = routine;
    }
    
    public String getRoutine() {
        return this.routine;
    }
    
    public Result getResult() {
        try {
            return this.rc.getResult();
        }
        catch (InterruptedException ex) {
            IntegrationPointFactory.getLogger().exception(this, "API", 0, ex);
            return null;
        }
    }
    
    public APIException getAPIException() {
        try {
            return this.rc.getAPIException();
        }
        catch (InterruptedException ex) {
            IntegrationPointFactory.getLogger().exception(this, "API", 0, ex);
            return null;
        }
    }
}
