// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.helper;

import com.mks.api.response.Response;
import com.mks.api.response.modifiable.ResponseFactory;
import com.mks.api.Session;

public interface HelperCommand
{
    Response execute(final Session p0, final ResponseFactory p1, final String[] p2);
}
