// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine.command;

import org.netbeans.lib.cvsclient.commandLine.GetOpt;
import org.netbeans.lib.cvsclient.command.log.RlogCommand;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.command.GlobalOptions;

public class rlog extends AbstractCommandProvider
{
    public String[] getSynonyms() {
        return new String[] { "rlo" };
    }
    
    public Command createCommand(final String[] array, final int n, final GlobalOptions globalOptions, final String s) {
        final RlogCommand rlogCommand = new RlogCommand();
        final GetOpt getOpt = new GetOpt(array, rlogCommand.getOptString());
        getOpt.optIndexSet(n);
        boolean b = false;
        int getopt;
        while ((getopt = getOpt.getopt()) != -1) {
            if (!rlogCommand.setCVSCommand((char)getopt, getOpt.optArgGet())) {
                b = true;
            }
        }
        if (b) {
            throw new IllegalArgumentException(this.getUsage());
        }
        final int optIndexGet = getOpt.optIndexGet();
        if (optIndexGet < array.length) {
            final String[] modules = new String[array.length - optIndexGet];
            for (int i = optIndexGet; i < array.length; ++i) {
                modules[i - optIndexGet] = array[i];
            }
            rlogCommand.setModules(modules);
        }
        return rlogCommand;
    }
}
