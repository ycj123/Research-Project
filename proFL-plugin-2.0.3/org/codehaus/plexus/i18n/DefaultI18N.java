// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.plexus.i18n;

import java.util.MissingResourceException;
import java.util.Map;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;
import java.text.MessageFormat;
import org.codehaus.plexus.util.StringUtils;
import java.lang.reflect.Field;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.HashMap;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.codehaus.plexus.logging.AbstractLogEnabled;

public class DefaultI18N extends AbstractLogEnabled implements I18N, Initializable
{
    private static final Object[] NO_ARGS;
    private HashMap bundles;
    private String[] bundleNames;
    private String defaultBundleName;
    private Locale defaultLocale;
    private String defaultLanguage;
    private String defaultCountry;
    private boolean devMode;
    
    public DefaultI18N() {
        this.defaultLocale = Locale.getDefault();
        this.defaultLanguage = Locale.getDefault().getLanguage();
        this.defaultCountry = Locale.getDefault().getCountry();
    }
    
    public String getDefaultLanguage() {
        return this.defaultLanguage;
    }
    
    public String getDefaultCountry() {
        return this.defaultCountry;
    }
    
    public String getDefaultBundleName() {
        return this.defaultBundleName;
    }
    
    public String[] getBundleNames() {
        return this.bundleNames.clone();
    }
    
    public ResourceBundle getBundle() {
        return this.getBundle(this.getDefaultBundleName(), (Locale)null);
    }
    
    public ResourceBundle getBundle(final String bundleName) {
        return this.getBundle(bundleName, (Locale)null);
    }
    
    public ResourceBundle getBundle(final String bundleName, final String languageHeader) {
        return this.getBundle(bundleName, this.getLocale(languageHeader));
    }
    
    public ResourceBundle getBundle(String bundleName, Locale locale) {
        bundleName = ((bundleName == null) ? this.getDefaultBundleName() : bundleName.trim());
        if (this.devMode) {
            try {
                final Class klass = ResourceBundle.getBundle(bundleName).getClass().getSuperclass();
                final Field field = klass.getDeclaredField("cacheList");
                field.setAccessible(true);
                final Object cache = field.get(null);
                cache.getClass().getDeclaredMethod("clear", (Class<?>[])null).invoke(cache, (Object[])null);
                field.setAccessible(false);
            }
            catch (Exception ex) {}
        }
        if (locale == null) {
            locale = this.getLocale(null);
        }
        final HashMap bundlesByLocale = this.bundles.get(bundleName);
        ResourceBundle rb;
        if (bundlesByLocale != null) {
            rb = bundlesByLocale.get(locale);
            if (rb == null) {
                rb = this.cacheBundle(bundleName, locale);
            }
        }
        else {
            rb = this.cacheBundle(bundleName, locale);
        }
        return rb;
    }
    
    public Locale getLocale(final String header) {
        if (!StringUtils.isEmpty(header)) {
            final I18NTokenizer tok = new I18NTokenizer(header);
            if (tok.hasNext()) {
                return (Locale)tok.next();
            }
        }
        return this.defaultLocale;
    }
    
    public String getString(final String key) {
        return this.getString(key, null);
    }
    
    public String getString(final String key, final Locale locale) {
        return this.getString(this.getDefaultBundleName(), locale, key);
    }
    
    public String getString(final String bundleName, Locale locale, final String key) {
        if (locale == null) {
            locale = this.getLocale(null);
        }
        ResourceBundle rb = this.getBundle(bundleName, locale);
        String value = this.getStringOrNull(rb, key);
        if (value == null && this.bundleNames.length > 0) {
            for (int i = 0; i < this.bundleNames.length; ++i) {
                final String name = this.bundleNames[i];
                if (!name.equals(bundleName)) {
                    rb = this.getBundle(name, locale);
                    value = this.getStringOrNull(rb, key);
                    if (value != null) {
                        locale = rb.getLocale();
                        break;
                    }
                }
            }
        }
        if (value == null) {
            final String loc = locale.toString();
            final String mesg = "Noticed missing resource: bundleName=" + bundleName + ", locale=" + loc + ", key=" + key;
            this.getLogger().debug(mesg);
            value = key;
        }
        return value;
    }
    
    public String format(final String key, final Object arg1) {
        return this.format(this.defaultBundleName, this.defaultLocale, key, new Object[] { arg1 });
    }
    
    public String format(final String key, final Object arg1, final Object arg2) {
        return this.format(this.defaultBundleName, this.defaultLocale, key, new Object[] { arg1, arg2 });
    }
    
    public String format(final String bundleName, final Locale locale, final String key, final Object arg1) {
        return this.format(bundleName, locale, key, new Object[] { arg1 });
    }
    
    public String format(final String bundleName, final Locale locale, final String key, final Object arg1, final Object arg2) {
        return this.format(bundleName, locale, key, new Object[] { arg1, arg2 });
    }
    
    public String format(final String bundleName, Locale locale, final String key, Object[] args) {
        if (locale == null) {
            locale = this.getLocale(null);
        }
        final String value = this.getString(bundleName, locale, key);
        if (args == null) {
            args = DefaultI18N.NO_ARGS;
        }
        final MessageFormat messageFormat = new MessageFormat("");
        messageFormat.setLocale(locale);
        messageFormat.applyPattern(value);
        return messageFormat.format(args);
    }
    
    public void initialize() throws InitializationException {
        this.bundles = new HashMap();
        this.defaultLocale = new Locale(this.defaultLanguage, this.defaultCountry);
        this.initializeBundleNames();
        if ("true".equals(System.getProperty("PLEXUS_DEV_MODE"))) {
            this.devMode = true;
        }
    }
    
    protected void initializeBundleNames() {
        if (this.defaultBundleName != null && this.defaultBundleName.length() > 0) {
            if (this.bundleNames == null || this.bundleNames.length <= 0) {
                this.bundleNames = new String[] { this.defaultBundleName };
            }
            else {
                final String[] array = new String[this.bundleNames.length + 1];
                array[0] = this.defaultBundleName;
                System.arraycopy(this.bundleNames, 0, array, 1, this.bundleNames.length);
                this.bundleNames = array;
            }
        }
        if (this.bundleNames == null) {
            this.bundleNames = new String[0];
        }
    }
    
    private synchronized ResourceBundle cacheBundle(final String bundleName, final Locale locale) throws MissingResourceException {
        HashMap bundlesByLocale = this.bundles.get(bundleName);
        ResourceBundle rb = (bundlesByLocale == null) ? null : bundlesByLocale.get(locale);
        if (rb == null) {
            bundlesByLocale = ((bundlesByLocale == null) ? new HashMap(3) : new HashMap(bundlesByLocale));
            try {
                rb = ResourceBundle.getBundle(bundleName, locale);
            }
            catch (MissingResourceException e) {
                rb = this.findBundleByLocale(bundleName, locale, bundlesByLocale);
                if (rb == null) {
                    throw (MissingResourceException)e.fillInStackTrace();
                }
            }
            if (rb != null) {
                bundlesByLocale.put(rb.getLocale(), rb);
                final HashMap bundlesByName = new HashMap(this.bundles);
                bundlesByName.put(bundleName, bundlesByLocale);
                this.bundles = bundlesByName;
            }
        }
        return rb;
    }
    
    private ResourceBundle findBundleByLocale(final String bundleName, final Locale locale, final Map bundlesByLocale) {
        ResourceBundle rb = null;
        if (!StringUtils.isNotEmpty(locale.getCountry()) && this.defaultLanguage.equals(locale.getLanguage())) {
            final Locale withDefaultCountry = new Locale(locale.getLanguage(), this.defaultCountry);
            rb = bundlesByLocale.get(withDefaultCountry);
            if (rb == null) {
                rb = this.getBundleIgnoreException(bundleName, withDefaultCountry);
            }
        }
        else if (!StringUtils.isNotEmpty(locale.getLanguage()) && this.defaultCountry.equals(locale.getCountry())) {
            final Locale withDefaultLanguage = new Locale(this.defaultLanguage, locale.getCountry());
            rb = bundlesByLocale.get(withDefaultLanguage);
            if (rb == null) {
                rb = this.getBundleIgnoreException(bundleName, withDefaultLanguage);
            }
        }
        if (rb == null && !this.defaultLocale.equals(locale)) {
            rb = this.getBundleIgnoreException(bundleName, this.defaultLocale);
        }
        return rb;
    }
    
    private ResourceBundle getBundleIgnoreException(final String bundleName, final Locale locale) {
        try {
            return ResourceBundle.getBundle(bundleName, locale);
        }
        catch (MissingResourceException ignored) {
            return null;
        }
    }
    
    protected final String getStringOrNull(final ResourceBundle rb, final String key) {
        if (rb != null) {
            try {
                return rb.getString(key);
            }
            catch (MissingResourceException ex) {}
        }
        return null;
    }
    
    static {
        NO_ARGS = new Object[0];
    }
}
