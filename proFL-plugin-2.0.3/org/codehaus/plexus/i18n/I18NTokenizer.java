// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.plexus.i18n;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.Collections;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Iterator;

public class I18NTokenizer implements Iterator
{
    private static final String LOCALE_SEPARATOR = ",";
    private static final char QUALITY_SEPARATOR = ';';
    private static final Float DEFAULT_QUALITY;
    private ArrayList locales;
    
    public I18NTokenizer(final String header) {
        this.locales = new ArrayList(3);
        final StringTokenizer tok = new StringTokenizer(header, ",");
        while (tok.hasMoreTokens()) {
            final AcceptLanguage acceptLang = new AcceptLanguage();
            String element = tok.nextToken().trim();
            int index;
            if ((index = element.indexOf(59)) != -1) {
                final String q = element.substring(index);
                element = element.substring(0, index);
                if ((index = q.indexOf(61)) != -1) {
                    try {
                        acceptLang.quality = Float.valueOf(q.substring(index + 1));
                    }
                    catch (NumberFormatException ex) {}
                }
            }
            element = element.trim();
            if ((index = element.indexOf(45)) == -1) {
                acceptLang.locale = new Locale(element, "");
            }
            else {
                acceptLang.locale = new Locale(element.substring(0, index), element.substring(index + 1));
            }
            this.locales.add(acceptLang);
        }
        Collections.sort((List<Object>)this.locales, Collections.reverseOrder());
    }
    
    public boolean hasNext() {
        return !this.locales.isEmpty();
    }
    
    public Object next() {
        if (this.locales.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.locales.remove(0).locale;
    }
    
    public final void remove() {
        throw new UnsupportedOperationException(this.getClass().getName() + " does not support remove()");
    }
    
    static {
        DEFAULT_QUALITY = new Float(1.0f);
    }
    
    private class AcceptLanguage implements Comparable
    {
        Locale locale;
        Float quality;
        
        private AcceptLanguage() {
            this.quality = I18NTokenizer.DEFAULT_QUALITY;
        }
        
        public final int compareTo(final Object acceptLang) {
            return this.quality.compareTo(((AcceptLanguage)acceptLang).quality);
        }
    }
}
