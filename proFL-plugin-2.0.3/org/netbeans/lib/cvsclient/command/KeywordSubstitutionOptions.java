// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command;

public final class KeywordSubstitutionOptions
{
    public static final KeywordSubstitutionOptions DEFAULT;
    public static final KeywordSubstitutionOptions DEFAULT_LOCKER;
    public static final KeywordSubstitutionOptions ONLY_KEYWORDS;
    public static final KeywordSubstitutionOptions ONLY_VALUES;
    public static final KeywordSubstitutionOptions OLD_VALUES;
    public static final KeywordSubstitutionOptions BINARY;
    private String value;
    
    public static KeywordSubstitutionOptions findKeywordSubstOption(final String s) {
        if (KeywordSubstitutionOptions.BINARY.toString().equals(s)) {
            return KeywordSubstitutionOptions.BINARY;
        }
        if (KeywordSubstitutionOptions.DEFAULT.toString().equals(s)) {
            return KeywordSubstitutionOptions.DEFAULT;
        }
        if (KeywordSubstitutionOptions.DEFAULT_LOCKER.toString().equals(s)) {
            return KeywordSubstitutionOptions.DEFAULT_LOCKER;
        }
        if (KeywordSubstitutionOptions.OLD_VALUES.toString().equals(s)) {
            return KeywordSubstitutionOptions.OLD_VALUES;
        }
        if (KeywordSubstitutionOptions.ONLY_KEYWORDS.toString().equals(s)) {
            return KeywordSubstitutionOptions.ONLY_KEYWORDS;
        }
        if (KeywordSubstitutionOptions.ONLY_VALUES.toString().equals(s)) {
            return KeywordSubstitutionOptions.ONLY_VALUES;
        }
        return null;
    }
    
    private KeywordSubstitutionOptions(final String value) {
        this.value = value;
    }
    
    public String toString() {
        return this.value;
    }
    
    static {
        DEFAULT = new KeywordSubstitutionOptions("kv");
        DEFAULT_LOCKER = new KeywordSubstitutionOptions("kvl");
        ONLY_KEYWORDS = new KeywordSubstitutionOptions("k");
        ONLY_VALUES = new KeywordSubstitutionOptions("v");
        OLD_VALUES = new KeywordSubstitutionOptions("o");
        BINARY = new KeywordSubstitutionOptions("b");
    }
}
