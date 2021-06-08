// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.toolchain.java;

import org.apache.maven.toolchain.RequirementMatcherFactory;
import java.io.File;
import hidden.org.codehaus.plexus.util.FileUtils;
import org.apache.maven.toolchain.MisconfiguredToolchainException;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.apache.maven.toolchain.ToolchainPrivate;
import org.apache.maven.toolchain.model.ToolchainModel;
import org.codehaus.plexus.logging.Logger;
import org.codehaus.plexus.logging.LogEnabled;
import org.apache.maven.toolchain.ToolchainFactory;

public class DefaultJavaToolchainFactory implements ToolchainFactory, LogEnabled
{
    private Logger logger;
    
    public ToolchainPrivate createToolchain(final ToolchainModel model) throws MisconfiguredToolchainException {
        if (model == null) {
            return null;
        }
        final DefaultJavaToolChain jtc = new DefaultJavaToolChain(model, this.logger);
        Xpp3Dom dom = (Xpp3Dom)model.getConfiguration();
        final Xpp3Dom javahome = dom.getChild("jdkHome");
        if (javahome == null) {
            throw new MisconfiguredToolchainException("Java toolchain without the jdkHome configuration element.");
        }
        final File normal = new File(FileUtils.normalize(javahome.getValue()));
        if (normal.exists()) {
            jtc.setJavaHome(FileUtils.normalize(javahome.getValue()));
            dom = (Xpp3Dom)model.getProvides();
            final Xpp3Dom[] provides = dom.getChildren();
            for (int i = 0; i < provides.length; ++i) {
                final String key = provides[i].getName();
                final String value = provides[i].getValue();
                if (value == null) {
                    throw new MisconfiguredToolchainException("Provides token '" + key + "' doesn't have any value configured.");
                }
                if ("version".equals(key)) {
                    jtc.addProvideToken(key, RequirementMatcherFactory.createVersionMatcher(value));
                }
                else {
                    jtc.addProvideToken(key, RequirementMatcherFactory.createExactMatcher(value));
                }
            }
            return jtc;
        }
        throw new MisconfiguredToolchainException("Non-existing JDK home configuration at " + normal.getAbsolutePath());
    }
    
    public ToolchainPrivate createDefaultToolchain() {
        return null;
    }
    
    protected Logger getLogger() {
        return this.logger;
    }
    
    public void enableLogging(final Logger logger) {
        this.logger = logger;
    }
}
