// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.plexus.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public interface I18N
{
    public static final String ROLE = ((I18N$1.class$org$codehaus$plexus$i18n$I18N == null) ? (I18N$1.class$org$codehaus$plexus$i18n$I18N = I18N$1.class$("org.codehaus.plexus.i18n.I18N")) : I18N$1.class$org$codehaus$plexus$i18n$I18N).getName();
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    
    String getDefaultLanguage();
    
    String getDefaultCountry();
    
    String getDefaultBundleName();
    
    String[] getBundleNames();
    
    ResourceBundle getBundle();
    
    ResourceBundle getBundle(final String p0);
    
    ResourceBundle getBundle(final String p0, final String p1);
    
    ResourceBundle getBundle(final String p0, final Locale p1);
    
    Locale getLocale(final String p0);
    
    String getString(final String p0);
    
    String getString(final String p0, final Locale p1);
    
    String getString(final String p0, final Locale p1, final String p2);
    
    String format(final String p0, final Object p1);
    
    String format(final String p0, final Object p1, final Object p2);
    
    String format(final String p0, final Locale p1, final String p2, final Object p3);
    
    String format(final String p0, final Locale p1, final String p2, final Object p3, final Object p4);
    
    String format(final String p0, final Locale p1, final String p2, final Object[] p3);
}
