// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import java.util.jar.Attributes;
import java.util.Enumeration;
import java.io.IOException;
import org.pitest.util.Log;
import org.pitest.util.StringUtil;
import java.util.jar.Manifest;
import java.net.URL;
import org.pitest.util.PitError;
import org.pitest.functional.F;
import org.pitest.plugin.ClientClasspathPlugin;
import java.util.Collection;
import org.pitest.functional.FCollection;
import java.util.HashSet;
import org.pitest.mutationtest.config.PluginServices;
import java.util.Set;
import org.apache.maven.artifact.Artifact;
import org.pitest.functional.predicate.Predicate;

public class DependencyFilter implements Predicate<Artifact>
{
    private final Set<GroupIdPair> groups;
    
    public DependencyFilter(final PluginServices plugins) {
        this.groups = new HashSet<GroupIdPair>();
        final Iterable<? extends ClientClasspathPlugin> runtimePlugins = plugins.findClientClasspathPlugins();
        FCollection.mapTo(runtimePlugins, artifactToPair(), this.groups);
        this.findVendorIdForGroups();
    }
    
    private static F<ClientClasspathPlugin, GroupIdPair> artifactToPair() {
        return new F<ClientClasspathPlugin, GroupIdPair>() {
            @Override
            public GroupIdPair apply(final ClientClasspathPlugin a) {
                final Package p = a.getClass().getPackage();
                final GroupIdPair g = new GroupIdPair(p.getImplementationVendor(), p.getImplementationTitle());
                if (g.id == null) {
                    this.reportBadPlugin("title", a);
                }
                if (g.group == null) {
                    this.reportBadPlugin("vendor", a);
                }
                return g;
            }
            
            private void reportBadPlugin(final String missingProperty, final ClientClasspathPlugin a) {
                final Class<?> clss = a.getClass();
                throw new PitError("No implementation " + missingProperty + " in manifest of plugin jar for " + clss + " in " + clss.getProtectionDomain().getCodeSource().getLocation());
            }
        };
    }
    
    private void findVendorIdForGroups() {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            final Enumeration<URL> urls = loader.getResources("META-INF/MANIFEST.MF");
            while (urls.hasMoreElements()) {
                final URL url = urls.nextElement();
                final Manifest manifest = new Manifest(url.openStream());
                final Attributes attributes = manifest.getMainAttributes();
                final String vendor = attributes.getValue("Implementation-Vendor");
                final String vendorId = attributes.getValue("Implementation-Vendor-Id");
                final String id = attributes.getValue("Implementation-Title");
                if (!StringUtil.isNullOrEmpty(vendor) && !StringUtil.isNullOrEmpty(vendorId)) {
                    if (StringUtil.isNullOrEmpty(id)) {
                        continue;
                    }
                    final GroupIdPair query = new GroupIdPair(vendor, id);
                    if (!this.groups.contains(query)) {
                        continue;
                    }
                    this.groups.remove(query);
                    this.groups.add(new GroupIdPair(vendorId, id));
                }
            }
        }
        catch (IOException exc) {
            Log.getLogger().fine("An exception was thrown while looking for manifest files. Message: " + exc.getMessage());
        }
    }
    
    @Override
    public Boolean apply(final Artifact a) {
        final GroupIdPair p = new GroupIdPair(a.getGroupId(), a.getArtifactId());
        return this.groups.contains(p);
    }
    
    private static class GroupIdPair
    {
        private final String group;
        private final String id;
        
        GroupIdPair(final String group, final String id) {
            this.group = group;
            this.id = id;
        }
        
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = 31 * result + ((this.group == null) ? 0 : this.group.hashCode());
            result = 31 * result + ((this.id == null) ? 0 : this.id.hashCode());
            return result;
        }
        
        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            final GroupIdPair other = (GroupIdPair)obj;
            if (this.group == null) {
                if (other.group != null) {
                    return false;
                }
            }
            else if (!this.group.equals(other.group)) {
                return false;
            }
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            }
            else if (!this.id.equals(other.id)) {
                return false;
            }
            return true;
        }
    }
}
