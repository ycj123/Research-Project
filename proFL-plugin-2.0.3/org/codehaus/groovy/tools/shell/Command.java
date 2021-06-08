// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import java.util.List;
import jline.Completor;

public interface Command
{
    String getName();
    
    String getShortcut();
    
    Completor getCompletor();
    
    String getDescription();
    
    String getUsage();
    
    String getHelp();
    
    List getAliases();
    
    Object execute(final List p0);
    
    boolean getHidden();
}
