// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.response.APIError;

public class UnsupportedApplicationError extends APIError
{
    public UnsupportedApplicationError() {
    }
    
    public UnsupportedApplicationError(final String msg) {
        super(msg);
    }
    
    public UnsupportedApplicationError(final Throwable t) {
        super(t);
    }
}
