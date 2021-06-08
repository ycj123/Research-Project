// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core;

import org.pitest.mutationtest.engine.MutationDetails;
import java.util.Collection;

public class DummySuspChecker implements SuspChecker
{
    private static final long serialVersionUID = 1L;
    private final Collection<String> failingTests;
    
    public DummySuspChecker(final Collection<String> failingTests) {
        this.failingTests = failingTests;
    }
    
    @Override
    public boolean isHit(final String className) {
        return true;
    }
    
    @Override
    public boolean isHit(final String className, final String methodSig) {
        return true;
    }
    
    @Override
    public boolean isHit(final MutationDetails details) {
        return true;
    }
    
    @Override
    public Collection<String> getAllFailingTests() {
        return this.failingTests;
    }
}
