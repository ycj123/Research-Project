// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.util;

import java.util.HashMap;
import java.util.Map;

public final class QuotedPropertyParser
{
    private QuotedPropertyParser() {
    }
    
    public static Map<String, String> parse(final CharSequence seq) {
        final Map<String, String> hashMap = new HashMap<String, String>();
        parse(seq, hashMap);
        return hashMap;
    }
    
    public static void parse(final CharSequence string, final Map<? super String, ? super String> propertyMap) {
        QuotedParseState state = QuotedParseState.KEY;
        char quote = '\0';
        StringBuilder buffer = new StringBuilder();
        String propertyKey = "";
        int i = 0;
        int pos = 0;
        while (i < string.length()) {
            final char current = string.charAt(i);
            Label_0408: {
                switch (state) {
                    case KEY: {
                        switch (current) {
                            case '\"':
                            case '\'': {
                                quote = current;
                                state = QuotedParseState.IN_QUOTED_KEY;
                                if (i >= pos) {
                                    buffer.append(string.subSequence(pos, i));
                                }
                                pos = i + 1;
                                break Label_0408;
                            }
                            case '=': {
                                if (i >= pos) {
                                    buffer.append(string.subSequence(pos, i));
                                }
                                propertyKey = buffer.toString();
                                buffer = new StringBuilder();
                                state = QuotedParseState.VALUE;
                                pos = i + 1;
                                break Label_0408;
                            }
                            default: {
                                break Label_0408;
                            }
                        }
                        break;
                    }
                    case VALUE: {
                        switch (current) {
                            case '\"':
                            case '\'': {
                                quote = current;
                                state = QuotedParseState.IN_QUOTED_VALUE;
                                if (i >= pos) {
                                    buffer.append(string.subSequence(pos, i));
                                }
                                pos = i + 1;
                                break Label_0408;
                            }
                            case '&': {
                                if (i >= pos) {
                                    buffer.append(string.subSequence(pos, i));
                                }
                                propertyMap.put(propertyKey, buffer.toString());
                                pos = i + 1;
                                buffer = new StringBuilder();
                                state = QuotedParseState.KEY;
                                break Label_0408;
                            }
                            default: {
                                break Label_0408;
                            }
                        }
                        break;
                    }
                    case IN_QUOTED_KEY:
                    case IN_QUOTED_VALUE: {
                        if (current == quote) {
                            state = ((state == QuotedParseState.IN_QUOTED_KEY) ? QuotedParseState.KEY : QuotedParseState.VALUE);
                            if (i >= pos) {
                                buffer.append(string.subSequence(pos, i));
                            }
                            pos = i + 1;
                            break;
                        }
                        break;
                    }
                }
            }
            ++i;
        }
        if (state == QuotedParseState.VALUE) {
            if (i >= pos) {
                buffer.append(string.subSequence(pos, i));
            }
            propertyMap.put(propertyKey, buffer.toString());
        }
    }
    
    public enum QuotedParseState
    {
        KEY, 
        IN_QUOTED_KEY, 
        IN_QUOTED_VALUE, 
        VALUE;
    }
}
