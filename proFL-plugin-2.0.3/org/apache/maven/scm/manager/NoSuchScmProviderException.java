// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.manager;

import org.apache.maven.scm.ScmException;

public class NoSuchScmProviderException extends ScmException
{
    static final long serialVersionUID = 4770645185214496323L;
    private String providerName;
    
    public NoSuchScmProviderException(final String providerName) {
        super("No such provider: '" + providerName + "'.");
        this.providerName = providerName;
    }
    
    public String getProviderName() {
        return this.providerName;
    }
}
