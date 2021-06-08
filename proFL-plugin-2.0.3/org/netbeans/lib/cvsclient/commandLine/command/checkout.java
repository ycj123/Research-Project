// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine.command;

import org.netbeans.lib.cvsclient.commandLine.GetOpt;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.command.checkout.CheckoutCommand;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.command.GlobalOptions;

public class checkout extends AbstractCommandProvider
{
    public String[] getSynonyms() {
        return new String[] { "co", "get" };
    }
    
    public Command createCommand(final String[] array, final int n, final GlobalOptions globalOptions, final String s) {
        final CheckoutCommand checkoutCommand = new CheckoutCommand();
        checkoutCommand.setBuilder(null);
        final GetOpt getOpt = new GetOpt(array, checkoutCommand.getOptString());
        getOpt.optIndexSet(n);
        boolean b = false;
        int getopt;
        while ((getopt = getOpt.getopt()) != -1) {
            if (!checkoutCommand.setCVSCommand((char)getopt, getOpt.optArgGet())) {
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
            checkoutCommand.setModules(modules);
        }
        return checkoutCommand;
    }
}
