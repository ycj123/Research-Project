// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine.command;

import java.util.ResourceBundle;
import org.netbeans.lib.cvsclient.commandLine.GetOpt;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.command.export.ExportCommand;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.command.GlobalOptions;

public class export extends AbstractCommandProvider
{
    public String getName() {
        return "export";
    }
    
    public String[] getSynonyms() {
        return new String[] { "ex", "exp" };
    }
    
    public Command createCommand(final String[] array, final int n, final GlobalOptions globalOptions, final String s) {
        final ExportCommand exportCommand = new ExportCommand();
        exportCommand.setBuilder(null);
        final GetOpt getOpt = new GetOpt(array, exportCommand.getOptString());
        getOpt.optIndexSet(n);
        boolean b = false;
        int getopt;
        while ((getopt = getOpt.getopt()) != -1) {
            if (!exportCommand.setCVSCommand((char)getopt, getOpt.optArgGet())) {
                b = true;
            }
        }
        if (b) {
            throw new IllegalArgumentException(this.getUsage());
        }
        if (exportCommand.getExportByDate() == null && exportCommand.getExportByRevision() == null) {
            throw new IllegalArgumentException("cvs [export]: " + ResourceBundle.getBundle(export.class.getPackage().getName() + ".Bundle").getString("export.Msg_NeedTagOrDate"));
        }
        final int optIndexGet = getOpt.optIndexGet();
        if (optIndexGet < array.length) {
            final String[] modules = new String[array.length - optIndexGet];
            for (int i = optIndexGet; i < array.length; ++i) {
                modules[i - optIndexGet] = array[i];
            }
            exportCommand.setModules(modules);
        }
        return exportCommand;
    }
}
