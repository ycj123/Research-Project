// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine.command;

import java.io.File;
import org.netbeans.lib.cvsclient.commandLine.GetOpt;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.command.tag.TagCommand;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.command.GlobalOptions;

public class tag extends AbstractCommandProvider
{
    public String getName() {
        return "tag";
    }
    
    public String[] getSynonyms() {
        return new String[] { "ta" };
    }
    
    public Command createCommand(final String[] array, final int n, final GlobalOptions globalOptions, String property) {
        final TagCommand tagCommand = new TagCommand();
        tagCommand.setBuilder(null);
        final GetOpt getOpt = new GetOpt(array, tagCommand.getOptString());
        getOpt.optIndexSet(n);
        boolean b = false;
        int getopt;
        while ((getopt = getOpt.getopt()) != -1) {
            if (!tagCommand.setCVSCommand((char)getopt, getOpt.optArgGet())) {
                b = true;
            }
        }
        if (b) {
            throw new IllegalArgumentException(this.getUsage());
        }
        final int optIndexGet = getOpt.optIndexGet();
        if (optIndexGet < array.length) {
            tagCommand.setTag(array[optIndexGet]);
            final int n2 = optIndexGet + 1;
            if (n2 < array.length) {
                final File[] files = new File[array.length - n2];
                if (property == null) {
                    property = System.getProperty("user.dir");
                }
                final File parent = new File(property);
                for (int i = n2; i < array.length; ++i) {
                    files[i - n2] = new File(parent, array[i]);
                }
                tagCommand.setFiles(files);
            }
            return tagCommand;
        }
        throw new IllegalArgumentException(this.getUsage());
    }
}
