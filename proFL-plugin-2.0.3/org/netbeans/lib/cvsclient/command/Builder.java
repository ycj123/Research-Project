// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command;

public interface Builder
{
    void parseLine(final String p0, final boolean p1);
    
    void parseEnhancedMessage(final String p0, final Object p1);
    
    void outputDone();
}
