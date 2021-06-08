// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.ext;

import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;
import com.mks.api.IntegrationPoint;

public interface IntegrationCommand
{
    int execute(final IntegrationPoint p0, final CmdRunnerCreator p1, final ResponseWriter p2, final CommandOptions p3, final CommandSelection p4) throws APIException;
}
