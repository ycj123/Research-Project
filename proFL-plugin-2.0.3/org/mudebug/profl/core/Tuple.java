// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.profl.core;

class Tuple
{
    String methodId;
    private double suspiciousnessValue;
    private PatchCategory category;
    
    Tuple(final String methodId, final double suspiciousnessValue, final PatchCategory category) {
        this.methodId = methodId;
        this.setSuspiciousnessValue(suspiciousnessValue);
        this.setCategory(category);
    }
    
    public double getSuspiciousnessValue() {
        return this.suspiciousnessValue;
    }
    
    public PatchCategory getCategory() {
        return this.category;
    }
    
    @Override
    public String toString() {
        return this.methodId + ": " + this.getCategory() + "-" + this.getSuspiciousnessValue();
    }
    
    void setSuspiciousnessValue(final double suspiciousnessValue) {
        this.suspiciousnessValue = suspiciousnessValue;
    }
    
    void setCategory(final PatchCategory category) {
        this.category = category;
    }
}
