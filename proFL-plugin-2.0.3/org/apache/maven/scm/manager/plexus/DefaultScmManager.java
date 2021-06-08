// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.manager.plexus;

import org.apache.maven.scm.log.ScmLogger;
import java.util.HashMap;
import org.codehaus.plexus.logging.Logger;
import org.apache.maven.scm.provider.ScmProvider;
import java.util.Map;
import org.codehaus.plexus.logging.LogEnabled;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.apache.maven.scm.manager.AbstractScmManager;

public class DefaultScmManager extends AbstractScmManager implements Initializable, LogEnabled
{
    private Map<String, ScmProvider> scmProviders;
    private Logger logger;
    
    public void enableLogging(final Logger logger) {
        this.logger = logger;
    }
    
    protected Logger getLogger() {
        return this.logger;
    }
    
    protected void setupLogger(final Object component) {
        this.setupLogger(component, this.logger);
    }
    
    protected void setupLogger(final Object component, final String subCategory) {
        if (subCategory == null) {
            throw new IllegalStateException("Logging category must be defined.");
        }
        final Logger logger = this.logger.getChildLogger(subCategory);
        this.setupLogger(component, logger);
    }
    
    protected void setupLogger(final Object component, final Logger logger) {
        if (component instanceof LogEnabled) {
            ((LogEnabled)component).enableLogging(logger);
        }
    }
    
    public void initialize() {
        if (this.scmProviders == null) {
            this.scmProviders = new HashMap<String, ScmProvider>(0);
        }
        if (this.getLogger().isWarnEnabled() && this.scmProviders.size() == 0) {
            this.getLogger().warn("No SCM providers configured.");
        }
        this.setScmProviders(this.scmProviders);
    }
    
    @Override
    protected ScmLogger getScmLogger() {
        return new PlexusLogger(this.getLogger());
    }
}
