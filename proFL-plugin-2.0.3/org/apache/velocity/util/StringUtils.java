// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.util.Map;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.File;
import java.util.List;

public class StringUtils
{
    private static final String EOL;
    
    public String concat(final List list) {
        final StringBuffer sb = new StringBuffer();
        for (int size = list.size(), i = 0; i < size; ++i) {
            sb.append(list.get(i).toString());
        }
        return sb.toString();
    }
    
    public static String getPackageAsPath(final String pckge) {
        return pckge.replace('.', File.separator.charAt(0)) + File.separator;
    }
    
    public static String removeUnderScores(final String data) {
        String temp = null;
        final StringBuffer out = new StringBuffer();
        temp = data;
        final StringTokenizer st = new StringTokenizer(temp, "_");
        while (st.hasMoreTokens()) {
            final String element = (String)st.nextElement();
            out.append(firstLetterCaps(element));
        }
        return out.toString();
    }
    
    public static String removeAndHump(final String data) {
        return removeAndHump(data, "_");
    }
    
    public static String removeAndHump(final String data, final String replaceThis) {
        String temp = null;
        final StringBuffer out = new StringBuffer();
        temp = data;
        final StringTokenizer st = new StringTokenizer(temp, replaceThis);
        while (st.hasMoreTokens()) {
            final String element = (String)st.nextElement();
            out.append(capitalizeFirstLetter(element));
        }
        return out.toString();
    }
    
    public static String firstLetterCaps(final String data) {
        final String firstLetter = data.substring(0, 1).toUpperCase();
        final String restLetters = data.substring(1).toLowerCase();
        return firstLetter + restLetters;
    }
    
    public static String capitalizeFirstLetter(final String data) {
        final String firstLetter = data.substring(0, 1).toUpperCase();
        final String restLetters = data.substring(1);
        return firstLetter + restLetters;
    }
    
    public static String[] split(final String line, final String delim) {
        final List list = new ArrayList();
        final StringTokenizer t = new StringTokenizer(line, delim);
        while (t.hasMoreTokens()) {
            list.add(t.nextToken());
        }
        return list.toArray(new String[list.size()]);
    }
    
    public static String chop(final String s, final int i) {
        return chop(s, i, StringUtils.EOL);
    }
    
    public static String chop(final String s, int i, final String eol) {
        if (i == 0 || s == null || eol == null) {
            return s;
        }
        int length = s.length();
        if (eol.length() == 2 && s.endsWith(eol)) {
            length -= 2;
            --i;
        }
        if (i > 0) {
            length -= i;
        }
        if (length < 0) {
            length = 0;
        }
        return s.substring(0, length);
    }
    
    public static StringBuffer stringSubstitution(final String argStr, final Hashtable vars) {
        return stringSubstitution(argStr, (Map)vars);
    }
    
    public static StringBuffer stringSubstitution(final String argStr, final Map vars) {
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
                        if (ch != '_' && !Character.isLetterOrDigit(ch)) {
                            break;
                        }
                        nameBuf.append(ch);
                        ++cIdx;
                    }
                    if (nameBuf.length() > 0) {
                        final String value = vars.get(nameBuf.toString());
                        if (value == null) {
                            continue;
                        }
                        argBuf.append(value);
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
        return argBuf;
    }
    
    public static String fileContentsToString(final String file) {
        String contents = "";
        File f = null;
        try {
            f = new File(file);
            if (f.exists()) {
                FileReader fr = null;
                try {
                    fr = new FileReader(f);
                    final char[] template = new char[(int)f.length()];
                    fr.read(template);
                    contents = new String(template);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if (fr != null) {
                        fr.close();
                    }
                }
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        return contents;
    }
    
    public static String collapseNewlines(final String argStr) {
        char last = argStr.charAt(0);
        final StringBuffer argBuf = new StringBuffer();
        for (int cIdx = 0; cIdx < argStr.length(); ++cIdx) {
            final char ch = argStr.charAt(cIdx);
            if (ch != '\n' || last != '\n') {
                argBuf.append(ch);
                last = ch;
            }
        }
        return argBuf.toString();
    }
    
    public static String collapseSpaces(final String argStr) {
        char last = argStr.charAt(0);
        final StringBuffer argBuf = new StringBuffer();
        for (int cIdx = 0; cIdx < argStr.length(); ++cIdx) {
            final char ch = argStr.charAt(cIdx);
            if (ch != ' ' || last != ' ') {
                argBuf.append(ch);
                last = ch;
            }
        }
        return argBuf.toString();
    }
    
    public static final String sub(final String line, final String oldString, final String newString) {
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0) {
            final char[] line2 = line.toCharArray();
            final char[] newString2 = newString.toCharArray();
            final int oLength = oldString.length();
            final StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            int j;
            for (i = (j = i + oLength); (i = line.indexOf(oldString, i)) > 0; i = (j = i + oLength)) {
                buf.append(line2, j, i - j).append(newString2);
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }
    
    public static final String stackTrace(final Throwable e) {
        String foo = null;
        try {
            final ByteArrayOutputStream ostr = new ByteArrayOutputStream();
            e.printStackTrace(new PrintWriter(ostr, true));
            foo = ostr.toString();
        }
        catch (Exception ex) {}
        return foo;
    }
    
    public static final String normalizePath(final String path) {
        String normalized = path;
        if (normalized.indexOf(92) >= 0) {
            normalized = normalized.replace('\\', '/');
        }
        if (!normalized.startsWith("/")) {
            normalized = "/" + normalized;
        }
        while (true) {
            final int index = normalized.indexOf("//");
            if (index < 0) {
                break;
            }
            normalized = normalized.substring(0, index) + normalized.substring(index + 1);
        }
        while (true) {
            final int index = normalized.indexOf("%20");
            if (index < 0) {
                break;
            }
            normalized = normalized.substring(0, index) + " " + normalized.substring(index + 3);
        }
        while (true) {
            final int index = normalized.indexOf("/./");
            if (index < 0) {
                break;
            }
            normalized = normalized.substring(0, index) + normalized.substring(index + 2);
        }
        while (true) {
            final int index = normalized.indexOf("/../");
            if (index < 0) {
                return normalized;
            }
            if (index == 0) {
                return null;
            }
            final int index2 = normalized.lastIndexOf(47, index - 1);
            normalized = normalized.substring(0, index2) + normalized.substring(index + 3);
        }
    }
    
    public String select(final boolean state, final String trueString, final String falseString) {
        if (state) {
            return trueString;
        }
        return falseString;
    }
    
    public boolean allEmpty(final List list) {
        for (int size = list.size(), i = 0; i < size; ++i) {
            if (list.get(i) != null && list.get(i).toString().length() > 0) {
                return false;
            }
        }
        return true;
    }
    
    public static List trimStrings(final List list) {
        if (list == null) {
            return null;
        }
        for (int sz = list.size(), i = 0; i < sz; ++i) {
            list.set(i, nullTrim(list.get(i)));
        }
        return list;
    }
    
    public static String nullTrim(final String s) {
        if (s == null) {
            return null;
        }
        return s.trim();
    }
    
    static {
        EOL = System.getProperty("line.separator");
    }
}
