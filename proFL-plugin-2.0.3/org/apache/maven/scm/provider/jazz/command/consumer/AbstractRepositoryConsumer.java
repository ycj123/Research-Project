// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.consumer;

import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.util.AbstractConsumer;

public abstract class AbstractRepositoryConsumer extends AbstractConsumer
{
    private ScmProviderRepository repository;
    protected boolean fed;
    
    public AbstractRepositoryConsumer(final ScmProviderRepository repository, final ScmLogger logger) {
        super(logger);
        this.repository = null;
        this.fed = false;
        this.setRepository(repository);
    }
    
    public ScmProviderRepository getRepository() {
        return this.repository;
    }
    
    public void setRepository(final ScmProviderRepository repository) {
        this.repository = repository;
    }
    
    public boolean isFed() {
        return this.fed;
    }
    
    public void setFed(final boolean fed) {
        this.fed = fed;
    }
    
    public void consumeLine(final String line) {
        this.getLogger().debug("Consumed line :" + line);
        this.fed = true;
    }
}
