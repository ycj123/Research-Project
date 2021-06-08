// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.APIError;

public class ApplicationConnectionError extends APIError
{
    public ApplicationConnectionError() {
    }
    
    public ApplicationConnectionError(final String msg) {
        super(msg);
    }
    
    public ApplicationConnectionError(final Throwable t) {
        super(t);
    }
}
