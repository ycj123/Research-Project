// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine;

import java.util.Collection;
import java.util.HashSet;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.command.GlobalOptions;
import org.netbeans.lib.cvsclient.commandLine.command.CommandProvider;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory
{
    private static final String[] COMMAND_CLASSES;
    private static CommandFactory instance;
    private Map commandProvidersByNames;
    
    private CommandFactory() {
        this.createCommandProviders();
    }
    
    private void createCommandProviders() {
        this.commandProvidersByNames = new HashMap();
        final String string = CommandFactory.class.getPackage().getName() + ".command.";
        for (int i = 0; i < CommandFactory.COMMAND_CLASSES.length; ++i) {
            try {
                final CommandProvider commandProvider = (CommandProvider)Class.forName(string + CommandFactory.COMMAND_CLASSES[i]).newInstance();
                this.commandProvidersByNames.put(commandProvider.getName(), commandProvider);
                final String[] synonyms = commandProvider.getSynonyms();
                for (int j = 0; j < synonyms.length; ++j) {
                    this.commandProvidersByNames.put(synonyms[j], commandProvider);
                }
            }
            catch (Exception ex) {
                System.err.println("Creation of command '" + CommandFactory.COMMAND_CLASSES[i] + "' failed:");
                ex.printStackTrace(System.err);
            }
        }
    }
    
    public static synchronized CommandFactory getDefault() {
        if (CommandFactory.instance == null) {
            CommandFactory.instance = new CommandFactory();
        }
        return CommandFactory.instance;
    }
    
    public Command createCommand(final String str, final String[] array, final int n, final GlobalOptions globalOptions, final String s) throws IllegalArgumentException {
        final CommandProvider commandProvider = this.commandProvidersByNames.get(str);
        if (commandProvider == null) {
            throw new IllegalArgumentException("Unknown command: '" + str + "'");
        }
        return commandProvider.createCommand(array, n, globalOptions, s);
    }
    
    public CommandProvider getCommandProvider(final String s) {
        return this.commandProvidersByNames.get(s);
    }
    
    public CommandProvider[] getCommandProviders() {
        return (CommandProvider[])new HashSet(this.commandProvidersByNames.values()).toArray((Object[])new CommandProvider[0]);
    }
    
    static {
        COMMAND_CLASSES = new String[] { "Import", "add", "annotate", "checkout", "commit", "diff", "export", "locbundlecheck", "log", "rannotate", "remove", "rlog", "rtag", "status", "tag", "update" };
    }
}
