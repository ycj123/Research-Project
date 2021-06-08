// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import groovy.lang.GroovyObjectSupport;

public class MessageSource extends GroovyObjectSupport
{
    private final String[] bundleNames;
    private ResourceBundle[] cachedBundles;
    
    public MessageSource(final String[] names) {
        assert names != null;
        assert names.length != 0;
        this.bundleNames = names;
    }
    
    public MessageSource(final String name) {
        this(new String[] { name });
    }
    
    private static String[] classNames(final Class[] types) {
        assert types != null;
        assert types.length != 0;
        final String[] names = new String[types.length];
        for (int i = 0; i < types.length; ++i) {
            assert types[i] != null;
            names[i] = types[i].getName();
        }
        return names;
    }
    
    public MessageSource(final Class[] types) {
        this(classNames(types));
    }
    
    public MessageSource(final Class type) {
        this(new String[] { type.getName() });
    }
    
    private ResourceBundle[] createBundles() {
        final ResourceBundle[] bundles = new ResourceBundle[this.bundleNames.length];
        for (int i = 0; i < this.bundleNames.length; ++i) {
            assert this.bundleNames[i] != null;
            bundles[i] = ResourceBundle.getBundle(this.bundleNames[i]);
        }
        return bundles;
    }
    
    private ResourceBundle[] getBundles() {
        if (this.cachedBundles == null) {
            this.cachedBundles = this.createBundles();
        }
        return this.cachedBundles;
    }
    
    public String getMessage(final String code) {
        assert code != null;
        MissingResourceException error = null;
        final ResourceBundle[] bundles = this.getBundles();
        int i = 0;
        while (i < bundles.length) {
            try {
                return bundles[i].getString(code);
            }
            catch (MissingResourceException e) {
                if (error != null) {
                    error = e;
                }
                ++i;
                continue;
            }
            break;
        }
        assert error != null;
        throw error;
    }
    
    public String format(final String code, final Object[] args) {
        assert args != null;
        final String pattern = this.getMessage(code);
        return MessageFormat.format(pattern, args);
    }
    
    @Override
    public Object getProperty(final String name) {
        return this.getMessage(name);
    }
}
