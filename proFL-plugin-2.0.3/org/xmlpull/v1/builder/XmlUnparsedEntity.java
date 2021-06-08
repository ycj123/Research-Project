// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.v1.builder;

public interface XmlUnparsedEntity extends XmlContainer
{
    String getName();
    
    String getSystemIdentifier();
    
    String getPublicIdentifier();
    
    String getDeclarationBaseUri();
    
    String getNotationName();
    
    XmlNotation getNotation();
}
