// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.impl;

import com.mks.api.CmdRunner;

public final class XMLResponseImpl extends ResponseImpl implements ModifiableXMLResponse
{
    private XMLResponseContainer xrc;
    
    XMLResponseImpl(final CmdRunner cmdRunner, final XMLResponseContainer xrc, final String app, final String cmdName) {
        super(cmdRunner, xrc, app, cmdName);
        this.xrc = xrc;
    }
    
    public void interrupt() {
        this.xrc.interrupt();
    }
    
    public void setXMLResponseHandler(final XMLResponseHandler xrh) {
        this.xrc.setXMLResponseHandler(xrh);
    }
}
