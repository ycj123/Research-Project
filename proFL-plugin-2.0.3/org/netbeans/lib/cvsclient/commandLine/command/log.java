// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine.command;

import java.io.File;
import org.netbeans.lib.cvsclient.commandLine.GetOpt;
import org.netbeans.lib.cvsclient.command.log.LogCommand;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.command.GlobalOptions;

public class log extends AbstractCommandProvider
{
    public String[] getSynonyms() {
        return new String[] { "lo" };
    }
    
    public Command createCommand(final String[] array, final int n, final GlobalOptions globalOptions, String property) {
        final LogCommand logCommand = new LogCommand();
        final GetOpt getOpt = new GetOpt(array, logCommand.getOptString());
        getOpt.optIndexSet(n);
        boolean b = false;
        int getopt;
        while ((getopt = getOpt.getopt()) != -1) {
            if (!logCommand.setCVSCommand((char)getopt, getOpt.optArgGet())) {
                b = true;
            }
        }
        if (b) {
            throw new IllegalArgumentException(this.getUsage());
        }
        final int optIndexGet = getOpt.optIndexGet();
        if (optIndexGet < array.length) {
            final File[] files = new File[array.length - optIndexGet];
            if (property == null) {
                property = System.getProperty("user.dir");
            }
            final File parent = new File(property);
            for (int i = optIndexGet; i < array.length; ++i) {
                files[i - optIndexGet] = new File(parent, array[i]);
            }
            logCommand.setFiles(files);
        }
        return logCommand;
    }
}
