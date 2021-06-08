// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine.command;

import java.io.File;
import org.netbeans.lib.cvsclient.commandLine.GetOpt;
import org.netbeans.lib.cvsclient.command.annotate.AnnotateCommand;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.command.GlobalOptions;

public class annotate extends AbstractCommandProvider
{
    public String[] getSynonyms() {
        return new String[] { "ann" };
    }
    
    public Command createCommand(final String[] array, final int n, final GlobalOptions globalOptions, String property) {
        final AnnotateCommand annotateCommand = new AnnotateCommand();
        final GetOpt getOpt = new GetOpt(array, annotateCommand.getOptString());
        getOpt.optIndexSet(n);
        boolean b = false;
        int getopt;
        while ((getopt = getOpt.getopt()) != -1) {
            if (!annotateCommand.setCVSCommand((char)getopt, getOpt.optArgGet())) {
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
            annotateCommand.setFiles(files);
        }
        return annotateCommand;
    }
}
