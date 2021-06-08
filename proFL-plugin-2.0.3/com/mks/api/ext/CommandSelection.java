// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.ext;

public abstract class CommandSelection
{
    public abstract int getSize();
    
    public abstract String nextItem();
    
    public abstract boolean hasNext();
    
    public abstract void startSelection();
    
    public abstract void endSelection();
    
    public String[] toArgs() {
        final int size = this.getSize();
        final String[] args = new String[size];
        this.startSelection();
        for (int i = 0; i < size; ++i) {
            args[i] = this.nextItem();
        }
        return args;
    }
}
