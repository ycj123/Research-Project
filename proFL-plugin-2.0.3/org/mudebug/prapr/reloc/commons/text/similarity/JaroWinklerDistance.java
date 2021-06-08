// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.text.similarity;

import java.util.Arrays;

public class JaroWinklerDistance implements SimilarityScore<Double>
{
    public static final int INDEX_NOT_FOUND = -1;
    
    @Override
    public Double apply(final CharSequence left, final CharSequence right) {
        final double defaultScalingFactor = 0.1;
        if (left == null || right == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        final int[] mtp = matches(left, right);
        final double m = mtp[0];
        if (m == 0.0) {
            return 0.0;
        }
        final double j = (m / left.length() + m / right.length() + (m - mtp[1]) / m) / 3.0;
        final double jw = (j < 0.7) ? j : (j + Math.min(0.1, 1.0 / mtp[3]) * mtp[2] * (1.0 - j));
        return jw;
    }
    
    protected static int[] matches(final CharSequence first, final CharSequence second) {
        CharSequence max;
        CharSequence min;
        if (first.length() > second.length()) {
            max = first;
            min = second;
        }
        else {
            max = second;
            min = first;
        }
        final int range = Math.max(max.length() / 2 - 1, 0);
        final int[] matchIndexes = new int[min.length()];
        Arrays.fill(matchIndexes, -1);
        final boolean[] matchFlags = new boolean[max.length()];
        int matches = 0;
        for (int mi = 0; mi < min.length(); ++mi) {
            final char c1 = min.charAt(mi);
            for (int xi = Math.max(mi - range, 0), xn = Math.min(mi + range + 1, max.length()); xi < xn; ++xi) {
                if (!matchFlags[xi] && c1 == max.charAt(xi)) {
                    matchFlags[matchIndexes[mi] = xi] = true;
                    ++matches;
                    break;
                }
            }
        }
        final char[] ms1 = new char[matches];
        final char[] ms2 = new char[matches];
        int i = 0;
        int si = 0;
        while (i < min.length()) {
            if (matchIndexes[i] != -1) {
                ms1[si] = min.charAt(i);
                ++si;
            }
            ++i;
        }
        i = 0;
        si = 0;
        while (i < max.length()) {
            if (matchFlags[i]) {
                ms2[si] = max.charAt(i);
                ++si;
            }
            ++i;
        }
        int transpositions = 0;
        for (int mi2 = 0; mi2 < ms1.length; ++mi2) {
            if (ms1[mi2] != ms2[mi2]) {
                ++transpositions;
            }
        }
        int prefix = 0;
        for (int mi3 = 0; mi3 < min.length() && first.charAt(mi3) == second.charAt(mi3); ++mi3) {
            ++prefix;
        }
        return new int[] { matches, transpositions / 2, prefix, max.length() };
    }
}
