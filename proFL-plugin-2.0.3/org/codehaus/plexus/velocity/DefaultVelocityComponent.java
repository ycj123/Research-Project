// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.plexus.velocity;

import java.util.Enumeration;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;
import org.apache.velocity.runtime.RuntimeServices;
import java.util.Properties;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.log.LogSystem;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.codehaus.plexus.logging.AbstractLogEnabled;

public class DefaultVelocityComponent extends AbstractLogEnabled implements VelocityComponent, Initializable, LogSystem
{
    private VelocityEngine engine;
    private Properties properties;
    private RuntimeServices runtimeServices;
    
    public void initialize() throws InitializationException {
        (this.engine = new VelocityEngine()).setProperty("velocimacro.library", "");
        this.engine.setProperty("runtime.log.logsystem", this);
        if (this.properties != null) {
            final Enumeration e = this.properties.propertyNames();
            while (e.hasMoreElements()) {
                final String key = e.nextElement().toString();
                final String value = this.properties.getProperty(key);
                this.engine.setProperty(key, value);
                this.getLogger().debug("Setting property: " + key + " => '" + value + "'.");
            }
        }
        try {
            this.engine.init();
        }
        catch (Exception e2) {
            throw new InitializationException("Cannot start the velocity engine: ", e2);
        }
    }
    
    public VelocityEngine getEngine() {
        return this.engine;
    }
    
    public void init(final RuntimeServices runtimeServices) {
        this.runtimeServices = runtimeServices;
    }
    
    public void logVelocityMessage(final int level, final String message) {
        switch (level) {
            case 2: {
                this.getLogger().warn(message);
                break;
            }
            case 1: {
                this.getLogger().debug(message);
                break;
            }
            case 0: {
                this.getLogger().debug(message);
                break;
            }
            case 3: {
                this.getLogger().error(message);
                break;
            }
            default: {
                this.getLogger().debug(message);
                break;
            }
        }
    }
}
