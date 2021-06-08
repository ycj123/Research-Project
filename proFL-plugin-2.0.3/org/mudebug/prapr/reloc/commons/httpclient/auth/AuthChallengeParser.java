// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.auth;

import java.util.HashMap;
import java.util.Map;

public final class AuthChallengeParser
{
    public static String extractScheme(final String challengeStr) throws MalformedChallengeException {
        if (challengeStr == null) {
            throw new IllegalArgumentException("Challenge may not be null");
        }
        final int i = challengeStr.indexOf(32);
        String s = null;
        if (i == -1) {
            s = challengeStr;
        }
        else {
            s = challengeStr.substring(0, i);
        }
        if (s.equals("")) {
            throw new MalformedChallengeException("Invalid challenge: " + challengeStr);
        }
        return s.toLowerCase();
    }
    
    public static Map extractParams(final String challengeStr) throws MalformedChallengeException {
        if (challengeStr == null) {
            throw new IllegalArgumentException("Challenge may not be null");
        }
        int i = challengeStr.indexOf(32);
        if (i == -1) {
            throw new MalformedChallengeException("Invalid challenge: " + challengeStr);
        }
        final Map elements = new HashMap();
        ++i;
        final int len = challengeStr.length();
        String name = null;
        String value = null;
        final StringBuffer buffer = new StringBuffer();
        boolean parsingName = true;
        boolean inQuote = false;
        boolean gotIt = false;
        while (i < len) {
            final char ch = challengeStr.charAt(i);
            ++i;
            if (parsingName) {
                if (ch == '=') {
                    name = buffer.toString().trim();
                    parsingName = false;
                    buffer.setLength(0);
                }
                else if (ch == ',') {
                    name = buffer.toString().trim();
                    value = null;
                    gotIt = true;
                    buffer.setLength(0);
                }
                else {
                    buffer.append(ch);
                }
                if (i == len) {
                    name = buffer.toString().trim();
                    value = null;
                    gotIt = true;
                }
            }
            else {
                if (!inQuote) {
                    if (ch == ',') {
                        value = buffer.toString().trim();
                        gotIt = true;
                        buffer.setLength(0);
                    }
                    else if (buffer.length() == 0) {
                        if (ch != ' ') {
                            if (ch != '\t') {
                                if (ch != '\n') {
                                    if (ch != '\r') {
                                        buffer.append(ch);
                                        if (ch == '\"') {
                                            inQuote = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else {
                        buffer.append(ch);
                    }
                }
                else {
                    buffer.append(ch);
                    if (ch == '\"') {
                        inQuote = false;
                    }
                }
                if (i == len) {
                    value = buffer.toString().trim();
                    gotIt = true;
                }
            }
            if (gotIt) {
                if (name == null || name.equals("")) {
                    throw new MalformedChallengeException("Invalid challenge: " + challengeStr);
                }
                if (value != null && value.length() > 1 && value.charAt(0) == '\"' && value.charAt(value.length() - 1) == '\"') {
                    value = value.substring(1, value.length() - 1);
                }
                elements.put(name.toLowerCase(), value);
                parsingName = true;
                gotIt = false;
            }
        }
        return elements;
    }
}
