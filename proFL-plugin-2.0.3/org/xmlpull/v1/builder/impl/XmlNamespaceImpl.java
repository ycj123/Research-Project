// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.v1.builder.impl;

import org.xmlpull.v1.builder.XmlBuilderException;
import org.xmlpull.v1.builder.XmlNamespace;

public class XmlNamespaceImpl implements XmlNamespace
{
    private String namespaceName;
    private String prefix;
    
    XmlNamespaceImpl(final String prefix, final String namespaceName) {
        this.prefix = prefix;
        if (namespaceName == null) {
            throw new XmlBuilderException("namespace name can not be null");
        }
        this.namespaceName = namespaceName;
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public String getNamespaceName() {
        return this.namespaceName;
    }
}
