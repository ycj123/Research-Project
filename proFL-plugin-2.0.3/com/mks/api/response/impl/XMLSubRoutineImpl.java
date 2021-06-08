// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

public final class XMLSubRoutineImpl extends SubRoutineImpl implements ModifiableXMLSubRoutine
{
    private XMLResponseContainer xrc;
    
    XMLSubRoutineImpl(final XMLResponseContainer xrc, final String routine) {
        super(xrc, routine);
        this.xrc = xrc;
    }
    
    public void setXMLResponseHandler(final XMLResponseHandler xrh) {
        this.xrc.setXMLResponseHandler(xrh);
    }
    
    public String getWorkItemSelectionType() {
        return this.xrc.getWorkItemSelectionType();
    }
}
