// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire;

interface ConfigurableProviderInfo extends ProviderInfo
{
    ProviderInfo instantiate(final String p0);
}
