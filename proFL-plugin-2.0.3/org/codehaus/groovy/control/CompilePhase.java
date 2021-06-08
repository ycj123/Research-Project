// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

public enum CompilePhase
{
    INITIALIZATION(1), 
    PARSING(2), 
    CONVERSION(3), 
    SEMANTIC_ANALYSIS(4), 
    CANONICALIZATION(5), 
    INSTRUCTION_SELECTION(6), 
    CLASS_GENERATION(7), 
    OUTPUT(8), 
    FINALIZATION(9);
    
    public static CompilePhase[] phases;
    int phaseNumber;
    
    private CompilePhase(final int phaseNumber) {
        this.phaseNumber = phaseNumber;
    }
    
    public int getPhaseNumber() {
        return this.phaseNumber;
    }
    
    static {
        CompilePhase.phases = new CompilePhase[] { null, CompilePhase.INITIALIZATION, CompilePhase.PARSING, CompilePhase.CONVERSION, CompilePhase.SEMANTIC_ANALYSIS, CompilePhase.CANONICALIZATION, CompilePhase.INSTRUCTION_SELECTION, CompilePhase.CLASS_GENERATION, CompilePhase.OUTPUT, CompilePhase.FINALIZATION };
    }
}
