// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine.command;

import org.netbeans.lib.cvsclient.commandLine.GetOpt;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.command.importcmd.ImportCommand;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.command.GlobalOptions;

public class Import extends AbstractCommandProvider
{
    public String getName() {
        return "import";
    }
    
    public String[] getSynonyms() {
        return new String[] { "im", "imp" };
    }
    
    public Command createCommand(final String[] array, final int n, final GlobalOptions globalOptions, final String s) {
        final ImportCommand importCommand = new ImportCommand();
        importCommand.setBuilder(null);
        final GetOpt getOpt = new GetOpt(array, importCommand.getOptString());
        getOpt.optIndexSet(n);
        boolean b = false;
        int getopt;
        while ((getopt = getOpt.getopt()) != -1) {
            if (!importCommand.setCVSCommand((char)getopt, getOpt.optArgGet())) {
                b = true;
            }
        }
        if (b) {
            this.throwUsage();
        }
        int optIndexGet = getOpt.optIndexGet();
        if (optIndexGet < array.length - 2) {
            importCommand.setModule(array[optIndexGet]);
            importCommand.setVendorTag(array[++optIndexGet]);
            importCommand.setReleaseTag(array[++optIndexGet]);
        }
        else {
            this.throwUsage();
        }
        return importCommand;
    }
    
    private void throwUsage() {
        throw new IllegalArgumentException(this.getUsage());
    }
}
