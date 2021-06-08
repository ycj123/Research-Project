// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.util;

import java.io.File;

public interface IgnoreFileFilter
{
    boolean shouldBeIgnored(final File p0, final String p1);
}
