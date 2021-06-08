// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin;

import org.codehaus.plexus.logging.Logger;
import org.codehaus.plexus.component.configurator.ConfigurationListener;

public class DebugConfigurationListener implements ConfigurationListener
{
    private Logger logger;
    
    public DebugConfigurationListener(final Logger logger) {
        this.logger = logger;
    }
    
    public void notifyFieldChangeUsingSetter(final String fieldName, final Object value, final Object target) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("  (s) " + fieldName + " = " + value);
        }
    }
    
    public void notifyFieldChangeUsingReflection(final String fieldName, final Object value, final Object target) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("  (f) " + fieldName + " = " + value);
        }
    }
}
