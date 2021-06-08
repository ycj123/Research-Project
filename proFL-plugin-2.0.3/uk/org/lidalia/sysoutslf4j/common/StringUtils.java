// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.common;

public final class StringUtils
{
    public static String stripEnd(final String str, final String stripChars) {
        return stripEnd(str, str.length() - 1, stripChars);
    }
    
    private static String stripEnd(final String string, final int index, final String stripChars) {
        String result;
        if (index == -1) {
            result = "";
        }
        else {
            final char candidateToBeStripped = string.charAt(index);
            final boolean candidateShouldNotBeStripped = stripChars.indexOf(candidateToBeStripped) == -1;
            if (candidateShouldNotBeStripped) {
                result = string.substring(0, index + 1);
            }
            else {
                result = stripEnd(string, index - 1, stripChars);
            }
        }
        return result;
    }
    
    public static String substringBefore(final String toBeSubstringed, final String separator) {
        final int indexOfSeparator = toBeSubstringed.indexOf(separator);
        String substring;
        if (indexOfSeparator == -1) {
            substring = toBeSubstringed;
        }
        else {
            substring = toBeSubstringed.substring(0, indexOfSeparator);
        }
        return substring;
    }
    
    private StringUtils() {
        throw new UnsupportedOperationException("Not instantiable");
    }
}
