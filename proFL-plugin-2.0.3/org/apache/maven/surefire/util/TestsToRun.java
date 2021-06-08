// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.util;

import java.util.ArrayList;
import org.apache.maven.surefire.testset.TestSetFailedException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.List;

public class TestsToRun implements Iterable<Class>
{
    private final List<Class> locatedClasses;
    
    public TestsToRun(final List<Class> locatedClasses) {
        this.locatedClasses = (List<Class>)Collections.unmodifiableList((List<? extends Class>)locatedClasses);
        final Set<Class> testSets = new HashSet<Class>();
        for (final Class testClass : locatedClasses) {
            if (testSets.contains(testClass)) {
                throw new RuntimeException("Duplicate test set '" + testClass.getName() + "'");
            }
            testSets.add(testClass);
        }
    }
    
    public static TestsToRun fromClass(final Class clazz) throws TestSetFailedException {
        return new TestsToRun((List<Class>)Arrays.asList(clazz));
    }
    
    public Iterator<Class> iterator() {
        return (Iterator<Class>)this.locatedClasses.iterator();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TestsToRun: [");
        for (final Class clazz : this) {
            sb.append(" ").append(clazz.getName());
        }
        sb.append(']');
        return sb.toString();
    }
    
    public boolean containsAtLeast(final int atLeast) {
        return this.containsAtLeast(this.iterator(), atLeast);
    }
    
    private boolean containsAtLeast(final Iterator it, final int atLeast) {
        for (int i = 0; i < atLeast; ++i) {
            if (!it.hasNext()) {
                return false;
            }
            it.next();
        }
        return true;
    }
    
    public boolean containsExactly(final int items) {
        final Iterator it = this.iterator();
        return this.containsAtLeast(it, items) && !it.hasNext();
    }
    
    public boolean allowEagerReading() {
        return true;
    }
    
    public Class[] getLocatedClasses() {
        if (!this.allowEagerReading()) {
            throw new IllegalStateException("Cannot eagerly read");
        }
        final List<Class> result = new ArrayList<Class>();
        final Iterator<Class> it = this.iterator();
        while (it.hasNext()) {
            result.add(it.next());
        }
        return result.toArray(new Class[result.size()]);
    }
}
