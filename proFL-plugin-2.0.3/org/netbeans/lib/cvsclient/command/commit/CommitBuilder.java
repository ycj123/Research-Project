// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.commit;

import java.io.IOException;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import org.netbeans.lib.cvsclient.event.EventManager;
import java.io.File;
import org.netbeans.lib.cvsclient.command.Builder;

public class CommitBuilder implements Builder
{
    public static final String UNKNOWN = "commit: nothing known about `";
    public static final String EXAM_DIR = ": Examining";
    public static final String REMOVING = "Removing ";
    public static final String NEW_REVISION = "new revision:";
    public static final String INITIAL_REVISION = "initial revision:";
    public static final String DELETED_REVISION = "delete";
    public static final String DONE = "done";
    public static final String RCS_FILE = "RCS file: ";
    public static final String ADD = "commit: use `cvs add' to create an entry for ";
    public static final String COMMITTED = " <-- ";
    private CommitInformation commitInformation;
    private File fileDirectory;
    private final EventManager eventManager;
    private final String localPath;
    private final String repositoryRoot;
    private boolean isAdding;
    
    public CommitBuilder(final EventManager eventManager, final String localPath, final String repositoryRoot) {
        this.eventManager = eventManager;
        this.localPath = localPath;
        this.repositoryRoot = repositoryRoot;
    }
    
    public void outputDone() {
        if (this.commitInformation != null) {
            this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.commitInformation));
            this.commitInformation = null;
        }
    }
    
    public void parseLine(final String s, final boolean b) {
        if (s.indexOf("commit: nothing known about `") >= 0) {
            this.outputDone();
            this.processUnknownFile(s.substring(s.indexOf("commit: nothing known about `") + "commit: nothing known about `".length()).trim());
        }
        else if (s.indexOf("commit: use `cvs add' to create an entry for ") > 0) {
            this.processToAddFile(s.substring(s.indexOf("commit: use `cvs add' to create an entry for ") + "commit: use `cvs add' to create an entry for ".length()).trim());
        }
        else {
            final int index;
            if ((index = s.indexOf(" <-- ")) > 0) {
                this.outputDone();
                final String trim = s.substring(index + " <-- ".length()).trim();
                File file;
                if (this.fileDirectory == null) {
                    String s2 = s.substring(0, index).trim();
                    if (s2.startsWith(this.repositoryRoot)) {
                        s2 = s2.substring(this.repositoryRoot.length());
                        if (s2.startsWith("/")) {
                            s2 = s2.substring(1);
                        }
                    }
                    final int lastIndex = s2.lastIndexOf(47);
                    if (lastIndex > 0) {
                        s2 = s2.substring(0, lastIndex);
                    }
                    file = this.findFile(trim, s2);
                }
                else {
                    file = new File(this.fileDirectory, trim);
                }
                this.processFile(file);
                if (this.isAdding) {
                    this.commitInformation.setType("Added");
                    this.isAdding = false;
                }
                else {
                    this.commitInformation.setType("Changed");
                }
            }
            else if (s.startsWith("Removing ")) {
                this.outputDone();
                this.processFile(s.substring("Removing ".length(), s.length() - 1));
                this.commitInformation.setType("Removed");
            }
            else if (s.indexOf(": Examining") >= 0) {
                this.fileDirectory = new File(this.localPath, s.substring(s.indexOf(": Examining") + ": Examining".length()).trim());
            }
            else if (s.startsWith("RCS file: ")) {
                this.isAdding = true;
            }
            else if (s.startsWith("done")) {
                this.outputDone();
            }
            else if (s.startsWith("initial revision:")) {
                this.processRevision(s.substring("initial revision:".length()));
                this.commitInformation.setType("Added");
            }
            else if (s.startsWith("new revision:")) {
                this.processRevision(s.substring("new revision:".length()));
            }
        }
    }
    
    private File createFile(final String child) {
        return new File(this.localPath, child);
    }
    
    private void processUnknownFile(final String s) {
        (this.commitInformation = new CommitInformation()).setType("Unknown");
        this.commitInformation.setFile(this.createFile(s.substring(0, s.indexOf(39)).trim()));
        this.outputDone();
    }
    
    private void processToAddFile(final String s) {
        (this.commitInformation = new CommitInformation()).setType("To-be-added");
        String s2 = s.trim();
        if (s2.endsWith(";")) {
            s2 = s2.substring(0, s2.length() - 2);
        }
        this.commitInformation.setFile(this.createFile(s2));
        this.outputDone();
    }
    
    private void processFile(String substring) {
        if (this.commitInformation == null) {
            this.commitInformation = new CommitInformation();
        }
        if (substring.startsWith("no file")) {
            substring = substring.substring(8);
        }
        this.commitInformation.setFile(this.createFile(substring));
    }
    
    private void processFile(final File file) {
        if (this.commitInformation == null) {
            this.commitInformation = new CommitInformation();
        }
        this.commitInformation.setFile(file);
    }
    
    private void processRevision(String s) {
        final int index = s.indexOf(59);
        if (index >= 0) {
            s = s.substring(0, index);
        }
        s = s.trim();
        if ("delete".equals(s)) {
            this.commitInformation.setType("Removed");
        }
        this.commitInformation.setRevision(s);
    }
    
    public void parseEnhancedMessage(final String s, final Object o) {
    }
    
    private File findFile(final String s, String substring) {
        final File file = new File(this.localPath);
        if (substring.endsWith("/Attic")) {
            substring = substring.substring(0, substring.length() - 6);
        }
        final File quickFindFile = this.quickFindFile(file, s, substring);
        if (quickFindFile != null) {
            return quickFindFile;
        }
        return this.findFile(file, s, substring);
    }
    
    private File findFile(final File parent, final String child, final String s) {
        if (this.isWorkForRepository(parent, s)) {
            return new File(parent, child);
        }
        File file = null;
        final File[] listFiles = parent.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; ++i) {
                if (listFiles[i].isDirectory()) {
                    file = this.findFile(listFiles[i], child, s);
                    if (file != null) {
                        break;
                    }
                }
            }
        }
        return file;
    }
    
    private File quickFindFile(File parentFile, final String child, final String child2) {
        do {
            final File parent = new File(parentFile, child2);
            if (this.isWorkForRepository(parent, child2)) {
                return new File(parent, child);
            }
            parentFile = parentFile.getParentFile();
        } while (parentFile != null);
        return null;
    }
    
    private boolean isWorkForRepository(final File file, final String s) {
        try {
            String anObject = this.eventManager.getClientServices().getRepositoryForDirectory(file);
            final String repository = this.eventManager.getClientServices().getRepository();
            if (anObject.startsWith(repository)) {
                anObject = anObject.substring(repository.length() + 1);
            }
            return s.equals(anObject);
        }
        catch (IOException ex) {
            return false;
        }
    }
}
