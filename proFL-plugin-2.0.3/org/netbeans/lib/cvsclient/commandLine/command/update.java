// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine.command;

import java.io.File;
import org.netbeans.lib.cvsclient.commandLine.GetOpt;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.command.update.UpdateCommand;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.command.GlobalOptions;

public class update extends AbstractCommandProvider
{
    public String[] getSynonyms() {
        return new String[] { "up", "upd" };
    }
    
    public Command createCommand(final String[] array, final int n, final GlobalOptions globalOptions, String property) {
        final UpdateCommand updateCommand = new UpdateCommand();
        updateCommand.setBuilder(null);
        final GetOpt getOpt = new GetOpt(array, updateCommand.getOptString());
        getOpt.optIndexSet(n);
        boolean b = false;
        int getopt;
        while ((getopt = getOpt.getopt()) != -1) {
            if (!updateCommand.setCVSCommand((char)getopt, getOpt.optArgGet())) {
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
            updateCommand.setFiles(files);
        }
        return updateCommand;
    }
}
