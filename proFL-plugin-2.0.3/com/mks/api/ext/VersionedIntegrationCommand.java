// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.ext;

import com.mks.api.VersionNumber;

public interface VersionedIntegrationCommand extends IntegrationCommand
{
    VersionNumber getAPIExecutionVersion();
}
