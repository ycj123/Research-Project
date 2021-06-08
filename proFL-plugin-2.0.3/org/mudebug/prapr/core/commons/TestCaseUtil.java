// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.commons;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import org.pitest.coverage.TestInfo;

public final class TestCaseUtil
{
    public static String sanitizeTestName(String name) {
        name = name.replace(':', '.');
        name = name.replace("..", ".");
        final int indexOfLP = name.indexOf(40);
        if (indexOfLP >= 0) {
            name = name.substring(0, indexOfLP);
        }
        return name;
    }
    
    public static boolean equals(final TestInfo ti, final String sanitizedName) {
        return sanitizeTestName(ti.getName()).equals(sanitizedName);
    }
    
    public static boolean contains(final Collection<TestInfo> tis, final String sanitizedName) {
        for (final TestInfo ti : tis) {
            if (equals(ti, sanitizedName)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean containsAll(final Collection<TestInfo> tis, final Collection<String> sanitizedNames) {
        for (final String name : sanitizedNames) {
            if (!contains(tis, name)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean intersect(final Collection<TestInfo> tis, final Collection<String> sanitizedNames) {
        for (final String name : sanitizedNames) {
            if (contains(tis, name)) {
                return true;
            }
        }
        return false;
    }
    
    public static String remove(final Collection<String> sanitizedNames, final TestInfo ti) {
        final Iterator<String> snit = sanitizedNames.iterator();
        while (snit.hasNext()) {
            final String sanitizedName = snit.next();
            if (equals(ti, sanitizedName)) {
                snit.remove();
                return sanitizedName;
            }
        }
        return null;
    }
    
    public static boolean contains(final Collection<String> failingTestSanitizedNames, final TestInfo ti) {
        final String tiName = sanitizeTestName(ti.getName());
        for (final String sanitizedName : failingTestSanitizedNames) {
            if (tiName.equals(sanitizedName)) {
                return true;
            }
        }
        return false;
    }
    
    public static List<TestInfo> reorder(final List<TestInfo> tis, final Collection<String> failingTestSanitizedNames) {
        final List<TestInfo> result = new ArrayList<TestInfo>(tis.size());
        final List<TestInfo> passingTests = new ArrayList<TestInfo>(tis.size());
        for (final TestInfo ti : tis) {
            if (contains(failingTestSanitizedNames, ti)) {
                result.add(ti);
            }
            else {
                passingTests.add(ti);
            }
        }
        result.addAll(passingTests);
        return result;
    }
    
    public static List<TestInfo> union(final Iterator<List<TestInfo>> lit) {
        final List<TestInfo> l = lit.next();
        if (!lit.hasNext()) {
            return l;
        }
        final Set<TestInfo> result = new HashSet<TestInfo>(l);
        while (lit.hasNext()) {
            result.addAll(lit.next());
        }
        return new ArrayList<TestInfo>(result);
    }
}
