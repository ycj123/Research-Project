// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire;

import java.io.IOException;
import org.apache.maven.plugin.surefire.booterclient.ProviderDetector;
import org.apache.maven.surefire.providerapi.SurefireProvider;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import org.apache.maven.plugin.logging.Log;

public class ProviderList
{
    private final ProviderInfo[] wellKnownProviders;
    private final ConfigurableProviderInfo dynamicProvider;
    
    public ProviderList(final ConfigurableProviderInfo dynamicProviderInfo, final ProviderInfo... wellKnownProviders) {
        this.wellKnownProviders = wellKnownProviders;
        this.dynamicProvider = dynamicProviderInfo;
    }
    
    @Nonnull
    public List<ProviderInfo> resolve(@Nonnull final Log log) {
        final List<ProviderInfo> providersToRun = new ArrayList<ProviderInfo>();
        final Set<String> manuallyConfiguredProviders = this.getManuallyConfiguredProviders();
        if (manuallyConfiguredProviders.size() > 0) {
            for (final String name : manuallyConfiguredProviders) {
                final ProviderInfo wellKnown = this.findByName(name);
                final ProviderInfo providerToAdd = (wellKnown != null) ? wellKnown : this.dynamicProvider.instantiate(name);
                log.info("Using configured provider " + providerToAdd.getProviderName());
                providersToRun.add(providerToAdd);
            }
            return providersToRun;
        }
        return this.autoDetectOneProvider();
    }
    
    @Nonnull
    private List<ProviderInfo> autoDetectOneProvider() {
        final List<ProviderInfo> providersToRun = new ArrayList<ProviderInfo>();
        for (final ProviderInfo wellKnownProvider : this.wellKnownProviders) {
            if (wellKnownProvider.isApplicable()) {
                providersToRun.add(wellKnownProvider);
                return providersToRun;
            }
        }
        return providersToRun;
    }
    
    private Set<String> getManuallyConfiguredProviders() {
        try {
            return ProviderDetector.getServiceNames(SurefireProvider.class, Thread.currentThread().getContextClassLoader());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private ProviderInfo findByName(final String providerClassName) {
        for (final ProviderInfo wellKnownProvider : this.wellKnownProviders) {
            if (wellKnownProvider.getProviderName().equals(providerClassName)) {
                return wellKnownProvider;
            }
        }
        return null;
    }
}
