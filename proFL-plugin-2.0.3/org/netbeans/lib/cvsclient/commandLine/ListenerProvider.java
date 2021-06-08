// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine;

import org.netbeans.lib.cvsclient.event.CVSListener;
import java.io.PrintStream;

public interface ListenerProvider
{
    CVSListener createCVSListener(final PrintStream p0, final PrintStream p1);
}
