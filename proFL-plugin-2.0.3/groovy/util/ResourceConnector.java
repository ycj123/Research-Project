// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.net.URLConnection;

public interface ResourceConnector
{
    URLConnection getResourceConnection(final String p0) throws ResourceException;
}
