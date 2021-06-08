// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine.command;

import org.netbeans.lib.cvsclient.commandLine.GetOpt;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.command.tag.RtagCommand;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.command.GlobalOptions;

public class rtag extends AbstractCommandProvider
{
    public String getName() {
        return "rtag";
    }
    
    public String[] getSynonyms() {
        return new String[] { "rt" };
    }
    
    public Command createCommand(final String[] array, final int n, final GlobalOptions globalOptions, final String s) {
        final RtagCommand rtagCommand = new RtagCommand();
        rtagCommand.setBuilder(null);
        final GetOpt getOpt = new GetOpt(array, rtagCommand.getOptString());
        getOpt.optIndexSet(n);
        boolean b = false;
        int getopt;
        while ((getopt = getOpt.getopt()) != -1) {
            if (!rtagCommand.setCVSCommand((char)getopt, getOpt.optArgGet())) {
                b = true;
            }
        }
        if (b) {
            throw new IllegalArgumentException(this.getUsage());
        }
        final int optIndexGet = getOpt.optIndexGet();
        if (optIndexGet < array.length) {
            rtagCommand.setTag(array[optIndexGet]);
            final int n2 = optIndexGet + 1;
            if (n2 < array.length) {
                final String[] modules = new String[array.length - n2];
                for (int i = n2; i < array.length; ++i) {
                    modules[i - n2] = array[i];
                }
                rtagCommand.setModules(modules);
            }
            return rtagCommand;
        }
        throw new IllegalArgumentException(this.getUsage());
    }
}
