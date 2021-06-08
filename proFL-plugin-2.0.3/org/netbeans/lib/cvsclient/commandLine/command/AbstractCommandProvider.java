// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine.command;

import java.text.MessageFormat;
import java.io.PrintStream;
import java.util.ResourceBundle;

abstract class AbstractCommandProvider implements CommandProvider
{
    public String getName() {
        final String name = this.getClass().getName();
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex > 0) {
            return name.substring(lastIndex + 1);
        }
        return name;
    }
    
    public String getUsage() {
        return ResourceBundle.getBundle(CommandProvider.class.getPackage().getName() + ".Bundle").getString(this.getName() + ".usage");
    }
    
    public void printShortDescription(final PrintStream printStream) {
        printStream.print(ResourceBundle.getBundle(CommandProvider.class.getPackage().getName() + ".Bundle").getString(this.getName() + ".shortDescription"));
    }
    
    public void printLongDescription(final PrintStream printStream) {
        printStream.println(MessageFormat.format(ResourceBundle.getBundle(CommandProvider.class.getPackage().getName() + ".Bundle").getString(this.getName() + ".longDescription"), this.getUsage()));
    }
}
