// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.report;

public final class ConsoleOutputReceiverForCurrentThread
{
    private static final ThreadLocal<ConsoleOutputReceiver> current;
    
    private ConsoleOutputReceiverForCurrentThread() {
    }
    
    public static ConsoleOutputReceiver get() {
        return ConsoleOutputReceiverForCurrentThread.current.get();
    }
    
    public static void set(final ConsoleOutputReceiver consoleOutputReceiver) {
        ConsoleOutputReceiverForCurrentThread.current.set(consoleOutputReceiver);
    }
    
    public static void remove() {
        ConsoleOutputReceiverForCurrentThread.current.remove();
    }
    
    static {
        current = new InheritableThreadLocal<ConsoleOutputReceiver>();
    }
}
