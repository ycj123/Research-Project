// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.v1.builder;

import java.util.Iterator;

public interface XmlDoctype extends XmlContainer
{
    String getSystemIdentifier();
    
    String getPublicIdentifier();
    
    Iterator children();
    
    XmlDocument getParent();
    
    XmlProcessingInstruction addProcessingInstruction(final String p0, final String p1);
    
    void removeAllProcessingInstructions();
}
