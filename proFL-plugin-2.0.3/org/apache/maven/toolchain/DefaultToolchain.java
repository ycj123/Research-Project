// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.toolchain;

import java.util.Iterator;
import java.util.HashMap;
import org.codehaus.plexus.logging.Logger;
import org.apache.maven.toolchain.model.ToolchainModel;
import java.util.Map;

public abstract class DefaultToolchain implements Toolchain, ToolchainPrivate
{
    private String type;
    private Map provides;
    public static final String KEY_TYPE = "type";
    private ToolchainModel model;
    private Logger logger;
    
    protected DefaultToolchain(final ToolchainModel model, final Logger logger) {
        this.provides = new HashMap();
        this.model = model;
        this.logger = logger;
    }
    
    protected DefaultToolchain(final ToolchainModel model, final String type, final Logger logger) {
        this(model, logger);
        this.type = type;
    }
    
    public final String getType() {
        return (this.type != null) ? this.type : this.model.getType();
    }
    
    public final ToolchainModel getModel() {
        return this.model;
    }
    
    public final void addProvideToken(final String type, final RequirementMatcher matcher) {
        this.provides.put(type, matcher);
    }
    
    public boolean matchesRequirements(final Map requirements) {
        for (final String key : requirements.keySet()) {
            final RequirementMatcher matcher = this.provides.get(key);
            if (matcher == null) {
                this.getLog().debug("Toolchain " + this + " is missing required property: " + key);
                return false;
            }
            if (!matcher.matches(requirements.get(key))) {
                this.getLog().debug("Toolchain " + this + " doesn't match required property: " + key);
                return false;
            }
        }
        return true;
    }
    
    protected Logger getLog() {
        return this.logger;
    }
}
