// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.parser.Token;

public class NodeUtils
{
    public static String specialText(final Token t) {
        final StringBuffer specialText = new StringBuffer();
        if (t.specialToken == null || t.specialToken.image.startsWith("##")) {
            return "";
        }
        Token tmp_t;
        for (tmp_t = t.specialToken; tmp_t.specialToken != null; tmp_t = tmp_t.specialToken) {}
        while (tmp_t != null) {
            final String st = tmp_t.image;
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < st.length(); ++i) {
                final char c = st.charAt(i);
                if (c == '#' || c == '$') {
                    sb.append(c);
                }
                if (c == '\\') {
                    boolean ok = true;
                    boolean term = false;
                    int j;
                    char cc;
                    for (j = i, ok = true; ok && j < st.length(); ++j) {
                        cc = st.charAt(j);
                        if (cc != '\\') {
                            if (cc == '$') {
                                term = true;
                                ok = false;
                            }
                            else {
                                ok = false;
                            }
                        }
                    }
                    if (term) {
                        final String foo = st.substring(i, j);
                        sb.append(foo);
                        i = j;
                    }
                }
            }
            specialText.append(sb.toString());
            tmp_t = tmp_t.next;
        }
        return specialText.toString();
    }
    
    public static String tokenLiteral(final Token t) {
        return specialText(t) + t.image;
    }
    
    public static String interpolate(final String argStr, final Context vars) throws MethodInvocationException {
        final StringBuffer argBuf = new StringBuffer();
        int cIdx = 0;
        while (cIdx < argStr.length()) {
            char ch = argStr.charAt(cIdx);
            switch (ch) {
                case '$': {
                    final StringBuffer nameBuf = new StringBuffer();
                    ++cIdx;
                    while (cIdx < argStr.length()) {
                        ch = argStr.charAt(cIdx);
                        if (ch == '_' || ch == '-' || Character.isLetterOrDigit(ch)) {
                            nameBuf.append(ch);
                        }
                        else if (ch != '{' && ch != '}') {
                            break;
                        }
                        ++cIdx;
                    }
                    if (nameBuf.length() > 0) {
                        final Object value = vars.get(nameBuf.toString());
                        if (value == null) {
                            argBuf.append("$").append(nameBuf.toString());
                        }
                        else {
                            argBuf.append(value.toString());
                        }
                        continue;
                    }
                    continue;
                }
                default: {
                    argBuf.append(ch);
                    ++cIdx;
                    continue;
                }
            }
        }
        return argBuf.toString();
    }
}
