// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.v1.builder;

public interface XmlAttribute
{
    XmlElement getOwner();
    
    String getNamespaceName();
    
    XmlNamespace getNamespace();
    
    String getName();
    
    String getValue();
    
    String getType();
    
    boolean isSpecified();
}
