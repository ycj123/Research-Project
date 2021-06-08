// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import java.io.IOException;
import java.io.Reader;

public interface ResourceManager
{
    Reader getReader(final String p0) throws IOException;
}
