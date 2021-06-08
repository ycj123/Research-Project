// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev;

public class WorkSpace
{
    private long transactionId;
    private String name;
    
    public String getName() {
        return this.name;
    }
    
    public WorkSpace(final String name, final long transactionId) {
        this.transactionId = transactionId;
        this.name = name;
    }
    
    public long getTransactionId() {
        return this.transactionId;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
