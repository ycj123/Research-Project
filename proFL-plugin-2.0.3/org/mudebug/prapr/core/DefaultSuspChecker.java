// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core;

import org.pitest.mutationtest.engine.MutationDetails;
import org.pitest.mutationtest.engine.Location;
import java.util.Iterator;
import org.mudebug.prapr.core.commons.TestCaseUtil;
import java.util.HashMap;
import java.util.HashSet;
import org.pitest.coverage.TestInfo;
import java.util.Set;
import org.pitest.coverage.BlockLocation;
import java.util.Map;
import java.util.Collection;

public final class DefaultSuspChecker implements SuspChecker
{
    private static final long serialVersionUID = 1L;
    private final Collection<String> failingTests;
    private final Map<String, Collection<String>> coverage;
    
    public DefaultSuspChecker(final Collection<String> failingTests, final Collection<Map.Entry<BlockLocation, Set<TestInfo>>> rawCoverageInfo) {
        this.failingTests = new HashSet<String>(failingTests);
        this.coverage = new HashMap<String, Collection<String>>();
        for (final Map.Entry<BlockLocation, Set<TestInfo>> entry : rawCoverageInfo) {
            if (TestCaseUtil.containsAll(entry.getValue(), failingTests)) {
                final Location loc = entry.getKey().getLocation();
                final String className = loc.getClassName().asJavaName();
                Collection<String> methods = this.coverage.get(className);
                if (methods == null) {
                    methods = new HashSet<String>();
                    this.coverage.put(className, methods);
                }
                methods.add(loc.getMethodName().name() + loc.getMethodDesc());
            }
        }
    }
    
    @Override
    public boolean isHit(final String className) {
        return this.coverage.containsKey(className);
    }
    
    @Override
    public boolean isHit(final String className, final String methodSig) {
        final Collection<String> methods = this.coverage.get(className);
        return methods != null && methods.contains(methodSig);
    }
    
    @Override
    public boolean isHit(final MutationDetails details) {
        return TestCaseUtil.containsAll(details.getTestsInOrder(), this.failingTests);
    }
    
    @Override
    public Collection<String> getAllFailingTests() {
        return this.failingTests;
    }
}
