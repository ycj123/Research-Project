// 
// Decompiled by Procyon v0.5.36
// 

package org.xmlpull.mxp1;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MXParserCachingStrings extends MXParser
{
    private static final boolean CACHE_STATISTICS = false;
    private static final boolean TRACE_SIZING = false;
    private static final int INITIAL_CAPACITY = 13;
    private int cacheStatCalls;
    private int cacheStatWalks;
    private int cacheStatResets;
    private int cacheStatRehash;
    private static final int CACHE_LOAD = 77;
    private int cacheEntriesCount;
    private int cacheEntriesThreshold;
    private char[][] keys;
    private String[] values;
    private boolean global;
    private static int globalCacheEntriesCount;
    private static int globalCacheEntriesThreshold;
    private static char[][] globalKeys;
    private static String[] globalValues;
    
    public MXParserCachingStrings() {
        this.allStringsInterned = true;
    }
    
    public void setFeature(final String name, final boolean state) throws XmlPullParserException {
        if ("http://xmlpull.org/v1/doc/features.html#names-interned".equals(name)) {
            if (this.eventType != 0) {
                throw new XmlPullParserException("interning names feature can only be changed before parsing", this, null);
            }
            this.allStringsInterned = state;
            if (!state && this.keys != null) {
                this.resetStringCache();
            }
        }
        else {
            super.setFeature(name, state);
        }
    }
    
    public boolean getFeature(final String name) {
        if ("http://xmlpull.org/v1/doc/features.html#names-interned".equals(name)) {
            return this.allStringsInterned;
        }
        return super.getFeature(name);
    }
    
    public void finalize() {
    }
    
    protected String newString(final char[] cbuf, final int off, final int len) {
        if (this.allStringsInterned) {
            return this.newStringIntern(cbuf, off, len);
        }
        return super.newString(cbuf, off, len);
    }
    
    protected String newStringIntern(final char[] cbuf, final int off, final int len) {
        if (this.cacheEntriesCount >= this.cacheEntriesThreshold) {
            this.rehash();
        }
        int offset;
        char[] k;
        for (offset = MXParser.fastHash(cbuf, off, len) % this.keys.length, k = null; (k = this.keys[offset]) != null && !keysAreEqual(k, 0, k.length, cbuf, off, len); offset = (offset + 1) % this.keys.length) {}
        if (k != null) {
            return this.values[offset];
        }
        k = new char[len];
        System.arraycopy(cbuf, off, k, 0, len);
        final String v = new String(k).intern();
        this.keys[offset] = k;
        this.values[offset] = v;
        ++this.cacheEntriesCount;
        return v;
    }
    
    protected void initStringCache() {
        if (this.keys == null) {
            this.cacheEntriesThreshold = 10;
            if (this.cacheEntriesThreshold >= 13) {
                throw new RuntimeException("internal error: threshold must be less than capacity: 13");
            }
            this.keys = new char[13][];
            this.values = new String[13];
            this.cacheEntriesCount = 0;
        }
    }
    
    protected void resetStringCache() {
        this.initStringCache();
    }
    
    private void rehash() {
        final int newSize = 2 * this.keys.length + 1;
        this.cacheEntriesThreshold = newSize * 77 / 100;
        if (this.cacheEntriesThreshold >= newSize) {
            throw new RuntimeException("internal error: threshold must be less than capacity: " + newSize);
        }
        final char[][] newKeys = new char[newSize][];
        final String[] newValues = new String[newSize];
        for (int i = 0; i < this.keys.length; ++i) {
            final char[] k = this.keys[i];
            this.keys[i] = null;
            final String v = this.values[i];
            this.values[i] = null;
            if (k != null) {
                int newOffset = MXParser.fastHash(k, 0, k.length) % newSize;
                char[] newk = null;
                while ((newk = newKeys[newOffset]) != null) {
                    if (keysAreEqual(newk, 0, newk.length, k, 0, k.length)) {
                        throw new RuntimeException("internal cache error: duplicated keys: " + new String(newk) + " and " + new String(k));
                    }
                    newOffset = (newOffset + 1) % newSize;
                }
                newKeys[newOffset] = k;
                newValues[newOffset] = v;
            }
        }
        this.keys = newKeys;
        this.values = newValues;
    }
    
    private static final boolean keysAreEqual(final char[] a, final int astart, final int alength, final char[] b, final int bstart, final int blength) {
        if (alength != blength) {
            return false;
        }
        for (int i = 0; i < alength; ++i) {
            if (a[astart + i] != b[bstart + i]) {
                return false;
            }
        }
        return true;
    }
}
