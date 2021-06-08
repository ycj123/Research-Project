// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.xml;

public interface XMLWriter
{
    void setEncoding(final String p0);
    
    void setDocType(final String p0);
    
    void startElement(final String p0);
    
    void addAttribute(final String p0, final String p1);
    
    void writeText(final String p0);
    
    void writeMarkup(final String p0);
    
    void endElement();
}
