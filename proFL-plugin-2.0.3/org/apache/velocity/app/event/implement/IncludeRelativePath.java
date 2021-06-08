// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.event.implement;

import org.apache.velocity.app.event.IncludeEventHandler;

public class IncludeRelativePath implements IncludeEventHandler
{
    public String includeEvent(final String includeResourcePath, final String currentResourcePath, final String directiveName) {
        if (includeResourcePath.startsWith("/") || includeResourcePath.startsWith("\\")) {
            return includeResourcePath;
        }
        final int lastslashpos = Math.max(currentResourcePath.lastIndexOf("/"), currentResourcePath.lastIndexOf("\\"));
        if (lastslashpos == -1) {
            return includeResourcePath;
        }
        return currentResourcePath.substring(0, lastslashpos) + "/" + includeResourcePath;
    }
}
