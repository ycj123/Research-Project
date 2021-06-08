// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

import java.util.Enumeration;
import java.io.IOException;
import groovy.lang.GroovyRuntimeException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

public class SourceExtensionHandler
{
    public static Set<String> getRegisteredExtensions(final ClassLoader loader) {
        final Set<String> extensions = new LinkedHashSet<String>();
        extensions.add("groovy");
        URL service = null;
        try {
            final Enumeration<URL> globalServices = loader.getResources("META-INF/services/org.codehaus.groovy.source.Extensions");
            while (globalServices.hasMoreElements()) {
                service = globalServices.nextElement();
                final BufferedReader svcIn = new BufferedReader(new InputStreamReader(service.openStream()));
                for (String extension = svcIn.readLine(); extension != null; extension = svcIn.readLine()) {
                    extension = extension.trim();
                    if (!extension.startsWith("#") && extension.length() > 0) {
                        extensions.add(extension);
                    }
                }
            }
        }
        catch (IOException ex) {
            throw new GroovyRuntimeException("IO Exception attempting to load source extension registerers. Exception: " + ex.toString() + ((service == null) ? "" : service.toExternalForm()));
        }
        return extensions;
    }
}
