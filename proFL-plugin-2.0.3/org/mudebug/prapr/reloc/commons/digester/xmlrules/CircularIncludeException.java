// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.xmlrules;

public class CircularIncludeException extends XmlLoadException
{
    public CircularIncludeException(final String fileName) {
        super("Circular file inclusion detected for file: " + fileName);
    }
}
