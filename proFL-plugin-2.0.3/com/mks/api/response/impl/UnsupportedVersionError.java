// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

public class UnsupportedVersionError extends Error
{
    public UnsupportedVersionError() {
    }
    
    public UnsupportedVersionError(final String msg) {
        super(msg);
    }
    
    public UnsupportedVersionError(final Throwable t) {
        super(t);
    }
}
