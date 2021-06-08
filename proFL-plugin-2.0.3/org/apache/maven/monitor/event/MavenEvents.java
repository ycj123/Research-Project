// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.monitor.event;

public final class MavenEvents
{
    public static final String PHASE_EXECUTION = "phase-execute";
    public static final String MOJO_EXECUTION = "mojo-execute";
    public static final String PROJECT_EXECUTION = "project-execute";
    public static final String REACTOR_EXECUTION = "reactor-execute";
    public static final String[] ALL_EVENTS;
    public static final String[] NO_EVENTS;
    
    private MavenEvents() {
    }
    
    static {
        ALL_EVENTS = new String[] { "phase-execute", "mojo-execute", "project-execute", "reactor-execute" };
        NO_EVENTS = new String[0];
    }
}
