// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.add;

import java.io.File;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.Builder;

public class AddBuilder implements Builder
{
    private static final String UNKNOWN = ": nothing known about";
    private static final String ADDED = " added to the repository";
    private static final String WARNING = ": warning: ";
    private static final String ALREADY_ENTERED = " has already been entered";
    private static final String SCHEDULING = ": scheduling file `";
    private static final String USE_COMMIT = ": use 'cvs commit' ";
    private static final String DIRECTORY = "Directory ";
    private static final String READDING = ": re-adding file ";
    private static final String RESURRECTED = ", resurrected";
    private static final String RESUR_VERSION = ", version ";
    private static final boolean DEBUG = false;
    private AddInformation addInformation;
    private EventManager eventManager;
    private String fileDirectory;
    private AddCommand addCommand;
    private boolean readingTags;
    
    public AddBuilder(final EventManager eventManager, final AddCommand addCommand) {
        this.eventManager = eventManager;
        this.addCommand = addCommand;
    }
    
    public void outputDone() {
        if (this.addInformation != null) {
            this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.addInformation));
            this.addInformation = null;
        }
    }
    
    public void parseLine(final String s, final boolean b) {
        if (s.endsWith(" added to the repository")) {
            this.addDirectory(s.substring("Directory ".length(), s.indexOf(" added to the repository")));
        }
        else if (s.indexOf(": scheduling file `") >= 0) {
            this.addFile(s.substring(s.indexOf(": scheduling file `") + ": scheduling file `".length(), s.indexOf(39)).trim());
        }
        else if (s.indexOf(": re-adding file ") >= 0) {
            this.addFile(s.substring(s.indexOf(": re-adding file ") + ": re-adding file ".length(), s.indexOf(40)).trim());
        }
        else if (s.endsWith(", resurrected")) {
            this.resurrectFile(s.substring(0, s.length() - ", resurrected".length()));
        }
    }
    
    private File createFile(final String s) {
        final File fileEndingWith = this.addCommand.getFileEndingWith(s);
        if (fileEndingWith == null) {
            final String replace = s.replace('\\', '/');
            final File[] files = this.addCommand.getFiles();
            replace.length();
            File file = null;
            final String[] array = new String[files.length];
            for (int i = 0; i < files.length; ++i) {
                array[i] = files[i].getAbsolutePath().replace('\\', '/');
            }
            int n = replace.lastIndexOf(47);
            String suffix;
            if (n < 0) {
                suffix = replace;
            }
            else {
                suffix = replace.substring(n + 1);
            }
            while (n >= 0 || suffix != null) {
                boolean b = false;
                for (int j = 0; j < array.length; ++j) {
                    if (array[j].endsWith(suffix)) {
                        file = files[j];
                        b = true;
                    }
                }
                n = replace.lastIndexOf(47, n - 1);
                if (n < 0) {
                    break;
                }
                if (!b) {
                    break;
                }
                suffix = replace.substring(n + 1);
            }
            return file;
        }
        return fileEndingWith;
    }
    
    private void addDirectory(final String s) {
        (this.addInformation = new AddInformation()).setType("A");
        this.addInformation.setFile(this.createFile(s.replace('\\', '/')));
        this.outputDone();
    }
    
    private void addFile(final String s) {
        (this.addInformation = new AddInformation()).setFile(this.createFile(s));
        this.addInformation.setType("A");
        this.outputDone();
    }
    
    private void resurrectFile(final String s) {
        final int lastIndex = s.lastIndexOf(", version ");
        s.substring(lastIndex + ", version ".length()).trim();
        final String trim = s.substring(0, lastIndex).trim();
        final String trim2 = trim.substring(trim.lastIndexOf(32)).trim();
        (this.addInformation = new AddInformation()).setType("U");
        this.addInformation.setFile(this.createFile(trim2));
        this.outputDone();
    }
    
    public void parseEnhancedMessage(final String s, final Object o) {
    }
}
