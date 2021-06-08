// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.v1.builder;

import java.util.Iterator;

public interface XmlDocument extends XmlContainer
{
    Iterator children();
    
    XmlElement getDocumentElement();
    
    Iterator notations();
    
    Iterator unparsedEntities();
    
    String getBaseUri();
    
    String getCharacterEncodingScheme();
    
    void setCharacterEncodingScheme(final String p0);
    
    Boolean isStandalone();
    
    String getVersion();
    
    boolean isAllDeclarationsProcessed();
    
    void setDocumentElement(final XmlElement p0);
    
    void addChild(final Object p0);
    
    void insertChild(final int p0, final Object p1);
    
    void removeAllChildren();
    
    XmlComment newComment(final String p0);
    
    XmlComment addComment(final String p0);
    
    XmlDoctype newDoctype(final String p0, final String p1);
    
    XmlDoctype addDoctype(final String p0, final String p1);
    
    XmlElement addDocumentElement(final String p0);
    
    XmlElement addDocumentElement(final XmlNamespace p0, final String p1);
    
    XmlProcessingInstruction newProcessingInstruction(final String p0, final String p1);
    
    XmlProcessingInstruction addProcessingInstruction(final String p0, final String p1);
    
    void remocveAllUnparsedEntities();
    
    XmlNotation addNotation(final String p0, final String p1, final String p2, final String p3);
    
    void removeAllNotations();
}
