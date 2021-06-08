// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io;

import javax.annotation.Nonnull;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.File;
import java.util.List;

public final class SelectorUtils
{
    private static final String PATTERN_HANDLER_PREFIX = "[";
    public static final String PATTERN_HANDLER_SUFFIX = "]";
    public static final String REGEX_HANDLER_PREFIX = "%regex[";
    public static final String ANT_HANDLER_PREFIX = "%ant[";
    
    private SelectorUtils() {
    }
    
    public static boolean matchPatternStart(final String pattern, final String str) {
        return matchPatternStart(pattern, str, true);
    }
    
    public static boolean matchPatternStart(String pattern, final String str, final boolean isCaseSensitive) {
        if (isRegexPrefixedPattern(pattern)) {
            return true;
        }
        if (isAntPrefixedPattern(pattern)) {
            pattern = pattern.substring("%ant[".length(), pattern.length() - "]".length());
        }
        final String altPattern = pattern.replace('\\', '/');
        final String altStr = str.replace('\\', '/');
        return matchAntPathPatternStart(altPattern, altStr, "/", isCaseSensitive);
    }
    
    private static boolean matchAntPathPatternStart(final String pattern, final String str, final String separator, final boolean isCaseSensitive) {
        if (separatorPatternStartSlashMismatch(pattern, str, separator)) {
            return false;
        }
        final List<String> patDirs = tokenizePath(pattern, separator);
        final List<String> strDirs = tokenizePath(str, separator);
        int patIdxStart;
        int patIdxEnd;
        int strIdxStart;
        int strIdxEnd;
        for (patIdxStart = 0, patIdxEnd = patDirs.size() - 1, strIdxStart = 0, strIdxEnd = strDirs.size() - 1; patIdxStart <= patIdxEnd && strIdxStart <= strIdxEnd; ++patIdxStart, ++strIdxStart) {
            final String patDir = patDirs.get(patIdxStart);
            if ("**".equals(patDir)) {
                break;
            }
            if (!match(patDir, strDirs.get(strIdxStart), isCaseSensitive)) {
                return false;
            }
        }
        return strIdxStart > strIdxEnd || patIdxStart <= patIdxEnd;
    }
    
    public static boolean matchPath(final String pattern, final String str) {
        return matchPath(pattern, str, true);
    }
    
    public static boolean matchPath(String pattern, final String str, final boolean isCaseSensitive) {
        if (pattern.length() > "%regex[".length() + "]".length() + 1 && pattern.startsWith("%regex[") && pattern.endsWith("]")) {
            pattern = pattern.substring("%regex[".length(), pattern.length() - "]".length());
            return str.matches(pattern);
        }
        if (pattern.length() > "%ant[".length() + "]".length() + 1 && pattern.startsWith("%ant[") && pattern.endsWith("]")) {
            pattern = pattern.substring("%ant[".length(), pattern.length() - "]".length());
        }
        return matchAntPathPattern(pattern, str, isCaseSensitive);
    }
    
    private static boolean matchAntPathPattern(final String pattern, final String str, final boolean isCaseSensitive) {
        if (str.startsWith(File.separator) != pattern.startsWith(File.separator)) {
            return false;
        }
        final List<String> patDirs = tokenizePath(pattern, File.separator);
        final List<String> strDirs = tokenizePath(str, File.separator);
        int patIdxStart;
        int patIdxEnd;
        int strIdxStart;
        int strIdxEnd;
        for (patIdxStart = 0, patIdxEnd = patDirs.size() - 1, strIdxStart = 0, strIdxEnd = strDirs.size() - 1; patIdxStart <= patIdxEnd && strIdxStart <= strIdxEnd; ++patIdxStart, ++strIdxStart) {
            final String patDir = patDirs.get(patIdxStart);
            if ("**".equals(patDir)) {
                break;
            }
            if (!match(patDir, strDirs.get(strIdxStart), isCaseSensitive)) {
                return false;
            }
        }
        if (strIdxStart > strIdxEnd) {
            for (int i = patIdxStart; i <= patIdxEnd; ++i) {
                if (!"**".equals(patDirs.get(i))) {
                    return false;
                }
            }
            return true;
        }
        if (patIdxStart > patIdxEnd) {
            return false;
        }
        while (patIdxStart <= patIdxEnd && strIdxStart <= strIdxEnd) {
            final String patDir = patDirs.get(patIdxEnd);
            if ("**".equals(patDir)) {
                break;
            }
            if (!match(patDir, strDirs.get(strIdxEnd), isCaseSensitive)) {
                return false;
            }
            --patIdxEnd;
            --strIdxEnd;
        }
        if (strIdxStart > strIdxEnd) {
            for (int i = patIdxStart; i <= patIdxEnd; ++i) {
                if (!"**".equals(patDirs.get(i))) {
                    return false;
                }
            }
            return true;
        }
        while (patIdxStart != patIdxEnd && strIdxStart <= strIdxEnd) {
            int patIdxTmp = -1;
            for (int j = patIdxStart + 1; j <= patIdxEnd; ++j) {
                if ("**".equals(patDirs.get(j))) {
                    patIdxTmp = j;
                    break;
                }
            }
            if (patIdxTmp == patIdxStart + 1) {
                ++patIdxStart;
            }
            else {
                final int patLength = patIdxTmp - patIdxStart - 1;
                final int strLength = strIdxEnd - strIdxStart + 1;
                int foundIdx = -1;
                int k = 0;
            Label_0402:
                while (k <= strLength - patLength) {
                    for (int l = 0; l < patLength; ++l) {
                        final String subPat = patDirs.get(patIdxStart + l + 1);
                        final String subStr = strDirs.get(strIdxStart + k + l);
                        if (!match(subPat, subStr, isCaseSensitive)) {
                            ++k;
                            continue Label_0402;
                        }
                    }
                    foundIdx = strIdxStart + k;
                    break;
                }
                if (foundIdx == -1) {
                    return false;
                }
                patIdxStart = patIdxTmp;
                strIdxStart = foundIdx + patLength;
            }
        }
        for (int i = patIdxStart; i <= patIdxEnd; ++i) {
            if (!"**".equals(patDirs.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean match(final String pattern, final String str) {
        return match(pattern, str, true);
    }
    
    public static boolean match(final String pattern, final String str, final boolean isCaseSensitive) {
        final char[] patArr = pattern.toCharArray();
        final char[] strArr = str.toCharArray();
        int patIdxStart = 0;
        int patIdxEnd = patArr.length - 1;
        int strIdxStart = 0;
        int strIdxEnd = strArr.length - 1;
        boolean containsStar = false;
        for (final char aPatArr : patArr) {
            if (aPatArr == '*') {
                containsStar = true;
                break;
            }
        }
        if (!containsStar) {
            if (patIdxEnd != strIdxEnd) {
                return false;
            }
            for (int i = 0; i <= patIdxEnd; ++i) {
                final char ch = patArr[i];
                if (ch != '?' && !equals(ch, strArr[i], isCaseSensitive)) {
                    return false;
                }
            }
            return true;
        }
        else {
            if (patIdxEnd == 0) {
                return true;
            }
            char ch;
            while ((ch = patArr[patIdxStart]) != '*' && strIdxStart <= strIdxEnd) {
                if (ch != '?' && !equals(ch, strArr[strIdxStart], isCaseSensitive)) {
                    return false;
                }
                ++patIdxStart;
                ++strIdxStart;
            }
            if (strIdxStart > strIdxEnd) {
                for (int i = patIdxStart; i <= patIdxEnd; ++i) {
                    if (patArr[i] != '*') {
                        return false;
                    }
                }
                return true;
            }
            while ((ch = patArr[patIdxEnd]) != '*' && strIdxStart <= strIdxEnd) {
                if (ch != '?' && !equals(ch, strArr[strIdxEnd], isCaseSensitive)) {
                    return false;
                }
                --patIdxEnd;
                --strIdxEnd;
            }
            if (strIdxStart > strIdxEnd) {
                for (int i = patIdxStart; i <= patIdxEnd; ++i) {
                    if (patArr[i] != '*') {
                        return false;
                    }
                }
                return true;
            }
            while (patIdxStart != patIdxEnd && strIdxStart <= strIdxEnd) {
                int patIdxTmp = -1;
                for (int j = patIdxStart + 1; j <= patIdxEnd; ++j) {
                    if (patArr[j] == '*') {
                        patIdxTmp = j;
                        break;
                    }
                }
                if (patIdxTmp == patIdxStart + 1) {
                    ++patIdxStart;
                }
                else {
                    final int patLength = patIdxTmp - patIdxStart - 1;
                    final int strLength = strIdxEnd - strIdxStart + 1;
                    int foundIdx = -1;
                    int k = 0;
                Label_0412:
                    while (k <= strLength - patLength) {
                        for (int l = 0; l < patLength; ++l) {
                            ch = patArr[patIdxStart + l + 1];
                            if (ch != '?' && !equals(ch, strArr[strIdxStart + k + l], isCaseSensitive)) {
                                ++k;
                                continue Label_0412;
                            }
                        }
                        foundIdx = strIdxStart + k;
                        break;
                    }
                    if (foundIdx == -1) {
                        return false;
                    }
                    patIdxStart = patIdxTmp;
                    strIdxStart = foundIdx + patLength;
                }
            }
            for (int i = patIdxStart; i <= patIdxEnd; ++i) {
                if (patArr[i] != '*') {
                    return false;
                }
            }
            return true;
        }
    }
    
    private static boolean equals(final char c1, final char c2, final boolean isCaseSensitive) {
        return c1 == c2 || (!isCaseSensitive && (Character.toUpperCase(c1) == Character.toUpperCase(c2) || Character.toLowerCase(c1) == Character.toLowerCase(c2)));
    }
    
    private static List<String> tokenizePath(final String path, final String separator) {
        final List<String> ret = new ArrayList<String>();
        final StringTokenizer st = new StringTokenizer(path, separator);
        while (st.hasMoreTokens()) {
            ret.add(st.nextToken());
        }
        return ret;
    }
    
    static boolean matchAntPathPatternStart(@Nonnull final MatchPattern pattern, @Nonnull final String str, @Nonnull final String separator, final boolean isCaseSensitive) {
        return !separatorPatternStartSlashMismatch(pattern, str, separator) && matchAntPathPatternStart(pattern.getTokenizedPathString(), str, separator, isCaseSensitive);
    }
    
    private static String[] tokenizePathToString(@Nonnull final String path, @Nonnull final String separator) {
        final List<String> ret = new ArrayList<String>();
        final StringTokenizer st = new StringTokenizer(path, separator);
        while (st.hasMoreTokens()) {
            ret.add(st.nextToken());
        }
        return ret.toArray(new String[ret.size()]);
    }
    
    private static boolean matchAntPathPatternStart(@Nonnull final String[] patDirs, @Nonnull final String str, @Nonnull final String separator, final boolean isCaseSensitive) {
        final String[] strDirs = tokenizePathToString(str, separator);
        int patIdxStart;
        int patIdxEnd;
        int strIdxStart;
        int strIdxEnd;
        for (patIdxStart = 0, patIdxEnd = patDirs.length - 1, strIdxStart = 0, strIdxEnd = strDirs.length - 1; patIdxStart <= patIdxEnd && strIdxStart <= strIdxEnd; ++patIdxStart, ++strIdxStart) {
            final String patDir = patDirs[patIdxStart];
            if (patDir.equals("**")) {
                break;
            }
            if (!match(patDir, strDirs[strIdxStart], isCaseSensitive)) {
                return false;
            }
        }
        return strIdxStart > strIdxEnd || patIdxStart <= patIdxEnd;
    }
    
    private static boolean separatorPatternStartSlashMismatch(@Nonnull final MatchPattern matchPattern, @Nonnull final String str, @Nonnull final String separator) {
        return str.startsWith(separator) != matchPattern.startsWith(separator);
    }
    
    private static boolean separatorPatternStartSlashMismatch(final String pattern, final String str, final String separator) {
        return str.startsWith(separator) != pattern.startsWith(separator);
    }
    
    static boolean matchAntPathPattern(final String[] patDirs, final String[] strDirs, final boolean isCaseSensitive) {
        int patIdxStart;
        int patIdxEnd;
        int strIdxStart;
        int strIdxEnd;
        for (patIdxStart = 0, patIdxEnd = patDirs.length - 1, strIdxStart = 0, strIdxEnd = strDirs.length - 1; patIdxStart <= patIdxEnd && strIdxStart <= strIdxEnd; ++patIdxStart, ++strIdxStart) {
            final String patDir = patDirs[patIdxStart];
            if (patDir.equals("**")) {
                break;
            }
            if (!match(patDir, strDirs[strIdxStart], isCaseSensitive)) {
                return false;
            }
        }
        if (strIdxStart > strIdxEnd) {
            for (int i = patIdxStart; i <= patIdxEnd; ++i) {
                if (!patDirs[i].equals("**")) {
                    return false;
                }
            }
            return true;
        }
        if (patIdxStart > patIdxEnd) {
            return false;
        }
        while (patIdxStart <= patIdxEnd && strIdxStart <= strIdxEnd) {
            final String patDir = patDirs[patIdxEnd];
            if (patDir.equals("**")) {
                break;
            }
            if (!match(patDir, strDirs[strIdxEnd], isCaseSensitive)) {
                return false;
            }
            --patIdxEnd;
            --strIdxEnd;
        }
        if (strIdxStart > strIdxEnd) {
            for (int i = patIdxStart; i <= patIdxEnd; ++i) {
                if (!patDirs[i].equals("**")) {
                    return false;
                }
            }
            return true;
        }
        while (patIdxStart != patIdxEnd && strIdxStart <= strIdxEnd) {
            int patIdxTmp = -1;
            for (int j = patIdxStart + 1; j <= patIdxEnd; ++j) {
                if (patDirs[j].equals("**")) {
                    patIdxTmp = j;
                    break;
                }
            }
            if (patIdxTmp == patIdxStart + 1) {
                ++patIdxStart;
            }
            else {
                final int patLength = patIdxTmp - patIdxStart - 1;
                final int strLength = strIdxEnd - strIdxStart + 1;
                int foundIdx = -1;
                int k = 0;
            Label_0304:
                while (k <= strLength - patLength) {
                    for (int l = 0; l < patLength; ++l) {
                        final String subPat = patDirs[patIdxStart + l + 1];
                        final String subStr = strDirs[strIdxStart + k + l];
                        if (!match(subPat, subStr, isCaseSensitive)) {
                            ++k;
                            continue Label_0304;
                        }
                    }
                    foundIdx = strIdxStart + k;
                    break;
                }
                if (foundIdx == -1) {
                    return false;
                }
                patIdxStart = patIdxTmp;
                strIdxStart = foundIdx + patLength;
            }
        }
        for (int i = patIdxStart; i <= patIdxEnd; ++i) {
            if (!patDirs[i].equals("**")) {
                return false;
            }
        }
        return true;
    }
    
    static boolean isRegexPrefixedPattern(final String pattern) {
        return pattern.length() > "%regex[".length() + "]".length() + 1 && pattern.startsWith("%regex[") && pattern.endsWith("]");
    }
    
    static boolean isAntPrefixedPattern(final String pattern) {
        return pattern.length() > "%ant[".length() + "]".length() + 1 && pattern.startsWith("%ant[") && pattern.endsWith("]");
    }
    
    static boolean matchAntPathPattern(@Nonnull final MatchPattern matchPattern, @Nonnull final String str, @Nonnull final String separator, final boolean isCaseSensitive) {
        if (separatorPatternStartSlashMismatch(matchPattern, str, separator)) {
            return false;
        }
        final String[] patDirs = matchPattern.getTokenizedPathString();
        final String[] strDirs = tokenizePathToString(str, separator);
        return matchAntPathPattern(patDirs, strDirs, isCaseSensitive);
    }
}
