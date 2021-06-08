// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command;

import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.request.RootRequest;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.request.ArgumentRequest;
import org.netbeans.lib.cvsclient.request.StickyRequest;
import org.netbeans.lib.cvsclient.request.DirectoryRequest;
import java.util.Iterator;
import org.netbeans.lib.cvsclient.request.QuestionableRequest;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import org.netbeans.lib.cvsclient.request.ModifiedRequest;
import org.netbeans.lib.cvsclient.request.UnchangedRequest;
import org.netbeans.lib.cvsclient.admin.DateComparator;
import org.netbeans.lib.cvsclient.request.Request;
import org.netbeans.lib.cvsclient.request.EntryRequest;
import org.netbeans.lib.cvsclient.admin.Entry;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.io.File;
import org.netbeans.lib.cvsclient.ClientServices;
import java.util.List;

public abstract class BasicCommand extends BuildableCommand
{
    protected List requests;
    protected ClientServices clientServices;
    private boolean recursive;
    protected File[] files;
    
    public BasicCommand() {
        this.requests = new LinkedList();
        this.recursive = true;
    }
    
    public boolean getRecursive() {
        return this.recursive;
    }
    
    public boolean isRecursive() {
        return this.recursive;
    }
    
    public void setRecursive(final boolean recursive) {
        this.recursive = recursive;
    }
    
    public void setFiles(final File[] files) {
        if (files == null) {
            this.files = files;
            return;
        }
        this.files = new File[files.length];
        int n = 0;
        int n2 = 0;
        for (final File file : files) {
            if (file.isDirectory()) {
                final int length;
                this.files[length - (1 + n2)] = file;
                ++n2;
            }
            else {
                this.files[n] = file;
                ++n;
            }
        }
    }
    
    public File[] getFiles() {
        return this.files;
    }
    
    public File getXthFile(final int n) {
        if (n < 0 || n >= this.files.length) {
            return null;
        }
        final File file = this.files[n];
        if (!file.isFile()) {
            return null;
        }
        return file;
    }
    
    public File getFileEndingWith(final String s) {
        final String replace = s.replace('\\', '/');
        final String replace2 = this.getLocalDirectory().replace('\\', '/');
        for (int i = 0; i < this.files.length; ++i) {
            final String absolutePath = this.files[i].getAbsolutePath();
            final String replace3 = this.files[i].getParentFile().getAbsolutePath().replace('\\', '/');
            if ((absolutePath.replace('\\', '/').endsWith(replace) && replace.indexOf(47) >= 0) || (this.files[i].getName().equals(replace) && replace3.equals(replace2))) {
                return this.files[i];
            }
        }
        return null;
    }
    
    private void addRequests(final File file) throws FileNotFoundException, IOException, CommandAbortedException {
        if (file == null) {
            throw new IllegalArgumentException("Cannot add requests for a null path.");
        }
        if (!file.exists() || file.isFile()) {
            this.addRequestsForFile(file);
        }
        else {
            this.addRequestsForDirectory(file);
        }
    }
    
    protected boolean doesCheckFileTime() {
        return true;
    }
    
    protected void sendEntryAndModifiedRequests(Entry entry, final File file) {
        if (entry == null) {
            return;
        }
        if (file != null && !file.exists() && entry.isNewUserFile()) {
            return;
        }
        final Date lastModified = entry.getLastModified();
        final boolean hadConflicts = entry.hadConflicts();
        if (!hadConflicts) {
            entry.setConflict(null);
        }
        else if (this.fileRequiresConflictResolution(file, entry)) {
            final Entry entry2 = new Entry(entry.toString());
            entry2.setConflict("+=");
            entry = entry2;
        }
        this.addRequest(new EntryRequest(entry));
        if (file == null || !file.exists() || entry.isUserFileToBeRemoved()) {
            return;
        }
        if (this.doesCheckFileTime() && !hadConflicts && lastModified != null && DateComparator.getInstance().equals(file.lastModified(), lastModified.getTime())) {
            this.addRequest(new UnchangedRequest(file.getName()));
            return;
        }
        this.addRequest(new ModifiedRequest(file, entry.isBinary()));
    }
    
    private final boolean fileRequiresConflictResolution(final File file, final Entry entry) {
        if (file == null) {
            return false;
        }
        boolean b = false;
        if (entry.hadConflicts()) {
            b = (file.lastModified() / 1000L <= entry.getLastModified().getTime() / 1000L);
        }
        return b;
    }
    
    protected void addRequestsForDirectory(final File file) throws IOException, CommandAbortedException {
        if (!this.clientServices.exists(file)) {
            return;
        }
        if (this.clientServices.isAborted()) {
            throw new CommandAbortedException("Command aborted during request generation", "Command aborted during request generation");
        }
        this.addDirectoryRequest(file);
        final File[] listFiles = file.listFiles();
        ArrayList<File> list;
        if (listFiles == null) {
            list = new ArrayList<File>(0);
        }
        else {
            list = new ArrayList<File>(Arrays.asList(listFiles));
            list.remove(new File(file, "CVS"));
        }
        List<File> list2 = null;
        if (this.isRecursive()) {
            list2 = new LinkedList<File>();
        }
        final Iterator entries = this.clientServices.getEntries(file);
        while (entries.hasNext()) {
            final Entry entry = entries.next();
            final File file2 = new File(file, entry.getName());
            if (entry.isDirectory()) {
                if (this.isRecursive()) {
                    list2.add(new File(file, entry.getName()));
                }
            }
            else {
                this.addRequestForFile(file2, entry);
            }
            list.remove(file2);
        }
        if (this.isRecursive() && !new File(file, "CVS").exists()) {
            final File[] listFiles2 = file.listFiles();
            if (listFiles2 != null) {
                for (int i = 0; i < listFiles2.length; ++i) {
                    if (listFiles2[i].isDirectory() && new File(listFiles2[i], "CVS").exists()) {
                        list2.add(listFiles2[i]);
                    }
                }
            }
        }
        final Iterator<File> iterator = list.iterator();
        while (iterator.hasNext()) {
            final String name = iterator.next().getName();
            if (!this.clientServices.shouldBeIgnored(file, name)) {
                this.addRequest(new QuestionableRequest(name));
            }
        }
        if (this.isRecursive()) {
            for (final File parent : list2) {
                if (this.clientServices.exists(new File(parent, "CVS"))) {
                    this.addRequestsForDirectory(parent);
                }
            }
        }
    }
    
    protected void addRequestForFile(final File file, final Entry entry) {
        this.sendEntryAndModifiedRequests(entry, file);
    }
    
    protected void addRequestsForFile(final File obj) throws IOException {
        this.addDirectoryRequest(obj.getParentFile());
        try {
            final Entry entry = this.clientServices.getEntry(obj);
            if (entry != null) {
                this.addRequestForFile(obj, entry);
            }
            else if (obj.exists()) {
                this.addRequest(new ModifiedRequest(obj, false));
            }
        }
        catch (IOException obj2) {
            System.err.println("An error occurred getting the Entry for file " + obj + ": " + obj2);
            obj2.printStackTrace();
        }
    }
    
    protected final void addDirectoryRequest(final File file) {
        final String relativeToLocalPathInUnixStyle = this.getRelativeToLocalPathInUnixStyle(file);
        try {
            this.addRequest(new DirectoryRequest(relativeToLocalPathInUnixStyle, this.clientServices.getRepositoryForDirectory(file.getAbsolutePath())));
            final String stickyTagForDirectory = this.clientServices.getStickyTagForDirectory(file);
            if (stickyTagForDirectory != null) {
                this.addRequest(new StickyRequest(stickyTagForDirectory));
            }
        }
        catch (FileNotFoundException ex) {}
        catch (IOException obj) {
            System.err.println("An error occurred reading the respository for the directory " + relativeToLocalPathInUnixStyle + ": " + obj);
            obj.printStackTrace();
        }
    }
    
    protected void addArgumentRequests() {
        if (this.files == null) {
            return;
        }
        for (int i = 0; i < this.files.length; ++i) {
            this.addRequest(new ArgumentRequest(this.getRelativeToLocalPathInUnixStyle(this.files[i])));
        }
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        this.requests.clear();
        super.execute(this.clientServices = clientServices, eventManager);
        if (clientServices.isFirstCommand()) {
            this.addRequest(new RootRequest(clientServices.getRepository()));
        }
        this.addFileRequests();
    }
    
    private void addFileRequests() throws CommandException {
        try {
            if (this.files != null && this.files.length > 0) {
                for (int i = 0; i < this.files.length; ++i) {
                    this.addRequests(this.files[i]);
                }
            }
            else if (this.assumeLocalPathWhenUnspecified()) {
                this.addRequests(new File(this.getLocalDirectory()));
            }
        }
        catch (Exception ex) {
            throw new CommandException(ex, ex.getLocalizedMessage());
        }
    }
    
    protected boolean assumeLocalPathWhenUnspecified() {
        return true;
    }
    
    protected final void addRequest(final Request request) {
        this.requests.add(request);
    }
    
    protected final void addRequestForWorkingDirectory(final ClientServices clientServices) throws IOException {
        this.addRequest(new DirectoryRequest(".", clientServices.getRepositoryForDirectory(this.getLocalDirectory())));
    }
    
    protected final void addArgumentRequest(final boolean b, final String s) {
        if (!b) {
            return;
        }
        this.addRequest(new ArgumentRequest(s));
    }
    
    protected final void appendFileArguments(final StringBuffer sb) {
        final File[] files = this.getFiles();
        if (files == null) {
            return;
        }
        for (int i = 0; i < files.length; ++i) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(files[i].getName());
        }
    }
}
