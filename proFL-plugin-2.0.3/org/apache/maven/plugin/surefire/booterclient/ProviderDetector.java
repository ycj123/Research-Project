// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.booterclient;

import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashSet;
import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Collections;
import java.util.Set;

public class ProviderDetector
{
    @Nonnull
    public static Set<String> getServiceNames(final Class<?> clazz, final ClassLoader classLoader) throws IOException {
        final String resourceName = "META-INF/services/" + clazz.getName();
        if (classLoader == null) {
            return Collections.emptySet();
        }
        final Enumeration<URL> urlEnumeration = classLoader.getResources(resourceName);
        return getNames(urlEnumeration);
    }
    
    @Nonnull
    private static Set<String> getNames(final Enumeration<URL> urlEnumeration) throws IOException {
        final Set<String> names = new HashSet<String>();
    Label_0008:
        while (true) {
            while (urlEnumeration.hasMoreElements()) {
                final URL url = urlEnumeration.nextElement();
                final BufferedReader reader = getReader(url);
                try {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        final int ci = line.indexOf(35);
                        if (ci >= 0) {
                            line = line.substring(0, ci);
                        }
                        line = line.trim();
                        final int n = line.length();
                        if (n == 0) {
                            continue;
                        }
                        if (line.indexOf(32) >= 0 || line.indexOf(9) >= 0) {
                            break;
                        }
                        char cp = line.charAt(0);
                        if (!Character.isJavaIdentifierStart(cp)) {
                            break;
                        }
                        for (int i = 1; i < n; ++i) {
                            cp = line.charAt(i);
                            if (!Character.isJavaIdentifierPart(cp) && cp != '.') {
                                continue Label_0008;
                            }
                        }
                        if (names.contains(line)) {
                            continue;
                        }
                        names.add(line);
                    }
                }
                finally {
                    reader.close();
                }
            }
            break;
        }
        return names;
    }
    
    @Nonnull
    private static BufferedReader getReader(@Nonnull final URL url) throws IOException {
        final InputStream inputStream = url.openStream();
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }
}
