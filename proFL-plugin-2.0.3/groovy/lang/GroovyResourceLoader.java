// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.net.MalformedURLException;
import java.net.URL;

public interface GroovyResourceLoader
{
    URL loadGroovySource(final String p0) throws MalformedURLException;
}
