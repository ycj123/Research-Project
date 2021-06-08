// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.event;

import java.util.EventObject;

public abstract class CVSEvent extends EventObject
{
    public CVSEvent(final Object source) {
        super(source);
    }
    
    protected abstract void fireEvent(final CVSListener p0);
}
