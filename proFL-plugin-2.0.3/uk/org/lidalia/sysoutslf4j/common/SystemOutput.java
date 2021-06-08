// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.common;

import java.io.PrintStream;

public enum SystemOutput
{
    OUT(0, "System.out") {
        @Override
        public PrintStream get() {
            return System.out;
        }
        
        @Override
        public void set(final PrintStream newPrintStream) {
            System.setOut(newPrintStream);
        }
    }, 
    ERR(1, "System.err") {
        @Override
        public PrintStream get() {
            return System.err;
        }
        
        @Override
        public void set(final PrintStream newPrintStream) {
            System.setErr(newPrintStream);
        }
    };
    
    private final String friendlyName;
    
    public abstract PrintStream get();
    
    public abstract void set(final PrintStream p0);
    
    private SystemOutput(final String name2, final int ordinal, final String name) {
        this.friendlyName = name;
    }
    
    @Override
    public String toString() {
        return this.friendlyName;
    }
}
