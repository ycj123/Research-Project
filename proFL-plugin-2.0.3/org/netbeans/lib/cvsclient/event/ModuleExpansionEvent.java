// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.event;

public class ModuleExpansionEvent extends CVSEvent
{
    private String module;
    
    public ModuleExpansionEvent(final Object o, final String module) {
        super(o);
        this.module = module;
    }
    
    public String getModule() {
        return this.module;
    }
    
    protected void fireEvent(final CVSListener cvsListener) {
        cvsListener.moduleExpanded(this);
    }
}
