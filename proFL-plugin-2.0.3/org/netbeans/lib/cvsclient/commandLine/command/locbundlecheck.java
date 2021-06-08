// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine.command;

import org.netbeans.lib.cvsclient.event.CVSListener;
import org.netbeans.lib.cvsclient.commandLine.ListenerProvider;
import org.netbeans.lib.cvsclient.command.annotate.AnnotateCommand;
import java.util.Iterator;
import org.netbeans.lib.cvsclient.command.annotate.AnnotateLine;
import java.text.MessageFormat;
import org.netbeans.lib.cvsclient.command.annotate.AnnotateInformation;
import org.netbeans.lib.cvsclient.event.TerminationEvent;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import java.util.Collection;
import java.io.File;
import java.util.ArrayList;
import org.netbeans.lib.cvsclient.commandLine.GetOpt;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.command.GlobalOptions;
import java.util.ResourceBundle;
import java.util.HashMap;
import java.io.PrintStream;
import org.netbeans.lib.cvsclient.event.CVSAdapter;

public class locbundlecheck extends CVSAdapter implements CommandProvider
{
    private PrintStream out;
    private PrintStream err;
    private int realEnd;
    private HashMap originalBundles;
    private HashMap localizedBundles;
    private String local;
    private String workDir;
    
    public locbundlecheck() {
        this.realEnd = 0;
    }
    
    public String getName() {
        return "locbundlecheck";
    }
    
    public String[] getSynonyms() {
        return new String[] { "lbch", "lbcheck" };
    }
    
    public String getUsage() {
        return ResourceBundle.getBundle(CommandProvider.class.getPackage().getName() + ".Bundle").getString("locbundlecheck.usage");
    }
    
    public void printShortDescription(final PrintStream printStream) {
        printStream.print(ResourceBundle.getBundle(CommandProvider.class.getPackage().getName() + ".Bundle").getString("locbundlecheck.shortDescription"));
    }
    
    public void printLongDescription(final PrintStream printStream) {
        printStream.println(ResourceBundle.getBundle(CommandProvider.class.getPackage().getName() + ".Bundle").getString("locbundlecheck.longDescription"));
    }
    
    public Command createCommand(final String[] array, final int n, final GlobalOptions globalOptions, String property) {
        final LocBundleAnnotateCommand locBundleAnnotateCommand = new LocBundleAnnotateCommand();
        final GetOpt getOpt = new GetOpt(array, locBundleAnnotateCommand.getOptString() + "i:");
        getOpt.optIndexSet(n);
        boolean b = false;
        String optArgGet = null;
        int getopt;
        while ((getopt = getOpt.getopt()) != -1) {
            if (getopt == 105) {
                optArgGet = getOpt.optArgGet();
                locBundleAnnotateCommand.setLocalization(optArgGet);
            }
            else {
                if (locBundleAnnotateCommand.setCVSCommand((char)getopt, getOpt.optArgGet())) {
                    continue;
                }
                b = true;
            }
        }
        if (b || optArgGet == null) {
            throw new IllegalArgumentException(this.getUsage());
        }
        final int optIndexGet = getOpt.optIndexGet();
        if (optIndexGet < array.length) {
            final ArrayList list = new ArrayList();
            if (property == null) {
                property = System.getProperty("user.dir");
            }
            locBundleAnnotateCommand.setWorkDir(property);
            final File parent = new File(property);
            for (int i = optIndexGet; i < array.length; ++i) {
                final File file = new File(parent, array[i]);
                if (file.exists() && file.isDirectory()) {
                    addFilesInDir(list, file, optArgGet);
                }
                else {
                    if (!file.exists() || !file.getName().endsWith(".properties")) {
                        throw new IllegalArgumentException();
                    }
                    addFiles(list, file, optArgGet);
                }
            }
            if (list.size() <= 0) {
                throw new IllegalArgumentException(ResourceBundle.getBundle("org/netbeans/lib/cvsclient/commandLine/command/Bundle").getString("locbundlecheck.no_file_spec"));
            }
            locBundleAnnotateCommand.setFiles((File[])list.toArray(new File[list.size()]));
        }
        return locBundleAnnotateCommand;
    }
    
    private static void addFiles(final Collection collection, final File file, final String str) {
        final String absolutePath = file.getAbsolutePath();
        final File file2 = new File(absolutePath.substring(0, absolutePath.length() - ".properties".length()) + "_" + str + ".properties");
        collection.add(file);
        if (file2.exists()) {
            collection.add(file2);
        }
    }
    
    private static void addFilesInDir(final Collection collection, final File file, final String s) {
        final File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (int i = 0; i < listFiles.length; ++i) {
                if (listFiles[i].exists() && listFiles[i].isDirectory()) {
                    addFilesInDir(collection, listFiles[i], s);
                }
                else if (listFiles[i].exists() && "Bundle.properties".equals(listFiles[i].getName())) {
                    addFiles(collection, listFiles[i], s);
                }
            }
        }
    }
    
    locbundlecheck(final PrintStream out, final PrintStream err, final String local, final String workDir) {
        this.realEnd = 0;
        this.out = out;
        this.err = err;
        this.originalBundles = new HashMap();
        this.localizedBundles = new HashMap();
        this.local = local;
        this.workDir = workDir;
    }
    
    public void fileInfoGenerated(final FileInfoEvent fileInfoEvent) {
        final FileInfoContainer infoContainer = fileInfoEvent.getInfoContainer();
        if (infoContainer.getFile().getName().indexOf("_" + this.local) >= 0) {
            this.localizedBundles.put(infoContainer.getFile().getAbsolutePath(), infoContainer);
        }
        else {
            this.originalBundles.put(infoContainer.getFile().getAbsolutePath(), infoContainer);
        }
        if (this.realEnd == 2) {
            this.generateOutput();
        }
    }
    
    public void commandTerminated(final TerminationEvent terminationEvent) {
        if (this.realEnd == 0) {
            this.realEnd = 1;
            return;
        }
        this.realEnd = 2;
    }
    
    private void generateOutput() {
        for (final String key : this.originalBundles.keySet()) {
            final int lastIndex = key.lastIndexOf(".");
            if (lastIndex < 0) {
                throw new IllegalStateException(ResourceBundle.getBundle("org/netbeans/lib/cvsclient/commandLine/command/Bundle").getString("locbundlecheck.illegal_state"));
            }
            final String string = key.substring(0, lastIndex) + "_" + this.local + key.substring(lastIndex);
            final AnnotateInformation annotateInformation = this.originalBundles.get(key);
            final AnnotateInformation annotateInformation2 = this.localizedBundles.get(string);
            if (annotateInformation2 == null) {
                this.out.println(MessageFormat.format(ResourceBundle.getBundle("org/netbeans/lib/cvsclient/commandLine/command/Bundle").getString("locbundlecheck.noLocalizedFile"), key));
            }
            else {
                this.localizedBundles.remove(string);
                final HashMap propMap = this.createPropMap(annotateInformation);
                final HashMap propMap2 = this.createPropMap(annotateInformation2);
                String s = key;
                if (key.startsWith(this.workDir)) {
                    s = key.substring(this.workDir.length());
                    if (s.startsWith("/") || s.startsWith("\\")) {
                        s = s.substring(1);
                    }
                }
                this.out.println(MessageFormat.format(ResourceBundle.getBundle("org/netbeans/lib/cvsclient/commandLine/command/Bundle").getString("locbundlecheck.File"), s));
                for (final String s2 : propMap.keySet()) {
                    final AnnotateLine annotateLine = propMap.get(s2);
                    final AnnotateLine annotateLine2 = propMap2.get(s2);
                    if (annotateLine2 == null) {
                        this.out.println(MessageFormat.format(ResourceBundle.getBundle("org/netbeans/lib/cvsclient/commandLine/command/Bundle").getString("locbundlecheck.propMissing"), s2));
                    }
                    else {
                        if (annotateLine.getDate().compareTo(annotateLine2.getDate()) <= 0) {
                            continue;
                        }
                        this.out.println(MessageFormat.format(ResourceBundle.getBundle("org/netbeans/lib/cvsclient/commandLine/command/Bundle").getString("locbundlecheck.prop_updated"), s2));
                    }
                }
            }
        }
        if (this.localizedBundles.size() > 0) {
            final Iterator<String> iterator3 = this.localizedBundles.keySet().iterator();
            while (iterator3.hasNext()) {
                this.out.println(MessageFormat.format(ResourceBundle.getBundle("org/netbeans/lib/cvsclient/commandLine/command/Bundle").getString("locbundlecheck.prop_removed"), iterator3.next()));
            }
        }
    }
    
    private HashMap createPropMap(final AnnotateInformation annotateInformation) {
        final HashMap<String, AnnotateLine> hashMap = new HashMap<String, AnnotateLine>();
        for (AnnotateLine value = annotateInformation.getFirstLine(); value != null; value = annotateInformation.getNextLine()) {
            final String content = value.getContent();
            if (!content.startsWith("#")) {
                final int index = content.indexOf(61);
                if (index > 0) {
                    hashMap.put(content.substring(0, index), value);
                }
            }
        }
        return hashMap;
    }
    
    private static class LocBundleAnnotateCommand extends AnnotateCommand implements ListenerProvider
    {
        private String loc;
        private String workDir;
        
        public CVSListener createCVSListener(final PrintStream printStream, final PrintStream printStream2) {
            return new locbundlecheck(printStream, printStream2, this.loc, this.workDir);
        }
        
        public void setLocalization(final String loc) {
            this.loc = loc;
        }
        
        public void setWorkDir(final String workDir) {
            this.workDir = workDir;
        }
    }
}
