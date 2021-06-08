// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.repository;

import org.apache.maven.scm.provider.ScmProviderRepository;

public class ScmRepository
{
    private String provider;
    private ScmProviderRepository providerRepository;
    
    public ScmRepository(final String provider, final ScmProviderRepository providerRepository) {
        this.provider = provider;
        this.providerRepository = providerRepository;
    }
    
    public String getProvider() {
        return this.provider;
    }
    
    public ScmProviderRepository getProviderRepository() {
        return this.providerRepository;
    }
    
    @Override
    public String toString() {
        return this.provider.toString() + ":" + this.providerRepository.toString();
    }
}
