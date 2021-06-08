// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.toolchain;

import hidden.org.codehaus.plexus.util.IOUtil;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import org.apache.maven.toolchain.model.io.xpp3.MavenToolchainsXpp3Reader;
import java.io.File;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import java.util.HashMap;
import java.lang.reflect.Method;
import org.apache.maven.project.MavenProject;
import org.apache.maven.execution.MavenSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.maven.toolchain.model.PersistedToolchains;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.apache.maven.toolchain.model.ToolchainModel;
import java.util.ArrayList;
import org.codehaus.plexus.context.ContextException;
import org.codehaus.plexus.context.Context;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Contextualizable;
import org.codehaus.plexus.logging.AbstractLogEnabled;

public class DefaultToolchainManager extends AbstractLogEnabled implements ToolchainManager, ToolchainManagerPrivate, Contextualizable
{
    private PlexusContainer container;
    
    public void contextualize(final Context context) throws ContextException {
        this.container = (PlexusContainer)context.get("plexus");
    }
    
    public ToolchainPrivate[] getToolchainsForType(final String type) throws MisconfiguredToolchainException {
        try {
            final PersistedToolchains pers = this.readToolchainSettings();
            final Map factories = this.container.lookupMap(ToolchainFactory.ROLE);
            final List toRet = new ArrayList();
            if (pers != null) {
                final List lst = pers.getToolchains();
                if (lst != null) {
                    for (final ToolchainModel toolchainModel : lst) {
                        final ToolchainFactory fact = factories.get(toolchainModel.getType());
                        if (fact != null) {
                            toRet.add(fact.createToolchain(toolchainModel));
                        }
                        else {
                            this.getLogger().error("Missing toolchain factory for type:" + toolchainModel.getType() + ". Possibly caused by misconfigured project.");
                        }
                    }
                }
            }
            for (final ToolchainFactory fact2 : factories.values()) {
                final ToolchainPrivate tool = fact2.createDefaultToolchain();
                if (tool != null) {
                    toRet.add(tool);
                }
            }
            final ToolchainPrivate[] tc = new ToolchainPrivate[toRet.size()];
            return toRet.toArray(tc);
        }
        catch (ComponentLookupException ex) {
            this.getLogger().fatalError("Error in component lookup", ex);
            return new ToolchainPrivate[0];
        }
    }
    
    public Toolchain getToolchainFromBuildContext(String type, final MavenSession session) {
        final Map context = this.retrieveContext(session);
        if ("javac".equals(type)) {
            type = "jdk";
        }
        final Object obj = context.get(getStorageKey(type));
        final ToolchainModel model = (ToolchainModel)obj;
        if (model != null) {
            try {
                final ToolchainFactory fact = (ToolchainFactory)this.container.lookup(ToolchainFactory.ROLE, type);
                return fact.createToolchain(model);
            }
            catch (ComponentLookupException ex) {
                this.getLogger().fatalError("Error in component lookup", ex);
            }
            catch (MisconfiguredToolchainException ex2) {
                this.getLogger().error("Misconfigured toolchain.", ex2);
            }
        }
        return null;
    }
    
    private MavenProject getCurrentProject(final MavenSession session) {
        try {
            final Method meth = session.getClass().getMethod("getCurrentProject", (Class<?>[])new Class[0]);
            return (MavenProject)meth.invoke(session, (Object[])null);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private Map retrieveContext(final MavenSession session) {
        if (session == null) {
            return new HashMap();
        }
        final PluginDescriptor desc = new PluginDescriptor();
        desc.setGroupId(PluginDescriptor.getDefaultPluginGroupId());
        desc.setArtifactId(PluginDescriptor.getDefaultPluginArtifactId("toolchains"));
        final MavenProject current = this.getCurrentProject(session);
        if (current != null) {
            return session.getPluginContext(desc, current);
        }
        return new HashMap();
    }
    
    public void storeToolchainToBuildContext(final ToolchainPrivate toolchain, final MavenSession session) {
        final Map context = this.retrieveContext(session);
        context.put(getStorageKey(toolchain.getType()), toolchain.getModel());
    }
    
    public static final String getStorageKey(final String type) {
        return "toolchain-" + type;
    }
    
    private PersistedToolchains readToolchainSettings() throws MisconfiguredToolchainException {
        final File tch = new File(System.getProperty("user.home"), ".m2/toolchains.xml");
        if (tch.exists()) {
            final MavenToolchainsXpp3Reader reader = new MavenToolchainsXpp3Reader();
            InputStreamReader in = null;
            try {
                in = new InputStreamReader(new BufferedInputStream(new FileInputStream(tch)));
                return reader.read(in);
            }
            catch (Exception ex) {
                throw new MisconfiguredToolchainException("Cannot read toolchains file at " + tch.getAbsolutePath(), ex);
            }
            finally {
                IOUtil.close((Reader)in);
            }
        }
        return null;
    }
}
