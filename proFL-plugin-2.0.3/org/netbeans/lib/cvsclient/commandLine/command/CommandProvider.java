// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine.command;

import java.io.PrintStream;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.command.GlobalOptions;

public interface CommandProvider
{
    String getName();
    
    String[] getSynonyms();
    
    Command createCommand(final String[] p0, final int p1, final GlobalOptions p2, final String p3);
    
    String getUsage();
    
    void printShortDescription(final PrintStream p0);
    
    void printLongDescription(final PrintStream p0);
}
