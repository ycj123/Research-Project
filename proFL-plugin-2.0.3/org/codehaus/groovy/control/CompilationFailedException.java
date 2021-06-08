// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

import groovy.lang.GroovyRuntimeException;

public class CompilationFailedException extends GroovyRuntimeException
{
    protected int phase;
    protected ProcessingUnit unit;
    
    public CompilationFailedException(final int phase, final ProcessingUnit unit, final Throwable cause) {
        super(Phases.getDescription(phase) + " failed", cause);
        this.phase = phase;
        this.unit = unit;
    }
    
    public CompilationFailedException(final int phase, final ProcessingUnit unit) {
        super(Phases.getDescription(phase) + " failed");
        this.phase = phase;
        this.unit = unit;
    }
    
    public ProcessingUnit getUnit() {
        return this.unit;
    }
}
