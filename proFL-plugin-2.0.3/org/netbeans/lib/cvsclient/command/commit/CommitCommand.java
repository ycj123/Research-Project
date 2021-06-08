// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.commit;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import org.netbeans.lib.cvsclient.request.ArgumentxRequest;
import java.util.StringTokenizer;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.request.EntryRequest;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import org.netbeans.lib.cvsclient.request.ArgumentRequest;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.ClientServices;
import org.netbeans.lib.cvsclient.admin.Entry;
import java.util.Iterator;
import java.util.Set;
import java.util.Collection;
import java.util.Arrays;
import java.io.IOException;
import org.netbeans.lib.cvsclient.request.StickyRequest;
import org.netbeans.lib.cvsclient.request.DirectoryRequest;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import org.netbeans.lib.cvsclient.command.BasicCommand;

public class CommitCommand extends BasicCommand
{
    private final List argumentRequests;
    private String message;
    private boolean forceCommit;
    private String logMessageFromFile;
    private boolean noModuleProgram;
    private String toRevisionOrBranch;
    
    public CommitCommand() {
        this.argumentRequests = new LinkedList();
        this.resetCVSCommand();
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public boolean isForceCommit() {
        return this.forceCommit;
    }
    
    public void setForceCommit(final boolean forceCommit) {
        this.forceCommit = forceCommit;
    }
    
    protected void addRequestsForDirectory(final File file) throws IOException {
        if (!file.exists()) {
            return;
        }
        final String relativeToLocalPathInUnixStyle = this.getRelativeToLocalPathInUnixStyle(file);
        try {
            this.requests.add(new DirectoryRequest(relativeToLocalPathInUnixStyle, this.clientServices.getRepositoryForDirectory(file.getAbsolutePath())));
            final String stickyTagForDirectory = this.clientServices.getStickyTagForDirectory(file);
            if (stickyTagForDirectory != null) {
                this.requests.add(new StickyRequest(stickyTagForDirectory));
            }
        }
        catch (IOException obj) {
            System.err.println("An error occurred reading the respository for the directory " + relativeToLocalPathInUnixStyle + ": " + obj);
            obj.printStackTrace();
        }
        final Set allFiles = this.clientServices.getAllFiles(file);
        allFiles.addAll(Arrays.asList(file.listFiles()));
        List<File> list = null;
        if (this.isRecursive()) {
            list = new LinkedList<File>();
        }
        for (final File file2 : allFiles) {
            if (file2.getName().equals("CVS")) {
                continue;
            }
            try {
                final Entry entry = this.clientServices.getEntry(file2);
                if (entry == null) {
                    continue;
                }
                if (file2.isFile()) {
                    this.sendEntryAndModifiedRequests(entry, file2);
                }
                else {
                    if (!this.isRecursive() || !file2.isDirectory() || !new File(file2, "CVS").exists()) {
                        continue;
                    }
                    list.add(file2);
                }
            }
            catch (IOException obj2) {
                System.err.println("An error occurred getting the Entry for file " + file2 + ": " + obj2);
                obj2.printStackTrace();
            }
        }
        if (this.isRecursive()) {
            final Iterator<File> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                this.addRequestsForDirectory(iterator2.next());
            }
        }
    }
    
    protected void addRequestsForFile(final File obj) throws IOException {
        final File parentFile = obj.getParentFile();
        final String relativeToLocalPathInUnixStyle = this.getRelativeToLocalPathInUnixStyle(parentFile);
        try {
            this.requests.add(new DirectoryRequest(relativeToLocalPathInUnixStyle, this.clientServices.getRepositoryForDirectory(parentFile.getAbsolutePath())));
            final String stickyTagForDirectory = this.clientServices.getStickyTagForDirectory(parentFile);
            if (stickyTagForDirectory != null) {
                this.requests.add(new StickyRequest(stickyTagForDirectory));
            }
        }
        catch (IOException obj2) {
            System.err.println("An error occurred reading the respository for the directory " + relativeToLocalPathInUnixStyle + ": " + obj2);
            obj2.printStackTrace();
        }
        try {
            final Entry entry = this.clientServices.getEntry(obj);
            if (entry != null) {
                this.sendEntryAndModifiedRequests(entry, obj);
            }
        }
        catch (IOException obj3) {
            System.err.println("An error occurred getting the Entry for file " + obj + ": " + obj3);
            obj3.printStackTrace();
        }
    }
    
    protected boolean doesCheckFileTime() {
        return !this.isForceCommit();
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        clientServices.ensureConnection();
        super.execute(clientServices, eventManager);
        try {
            if (this.isForceCommit()) {
                this.requests.add(1, new ArgumentRequest("-f"));
                if (this.isRecursive()) {
                    this.requests.add(1, new ArgumentRequest("-R"));
                }
            }
            if (this.isNoModuleProgram()) {
                this.requests.add(1, new ArgumentRequest("-n"));
            }
            if (this.getToRevisionOrBranch() != null) {
                this.requests.add(1, new ArgumentRequest("-r"));
                this.requests.add(2, new ArgumentRequest(this.getToRevisionOrBranch()));
            }
            String s = this.getMessage();
            if (this.getLogMessageFromFile() != null) {
                s = this.loadLogFile(this.getLogMessageFromFile());
            }
            if (s != null) {
                s = s.trim();
            }
            if (s == null || s.length() == 0) {
                s = "no message";
            }
            this.addMessageRequest(s);
            this.addRequestForWorkingDirectory(clientServices);
            this.requests.addAll(this.argumentRequests);
            this.argumentRequests.clear();
            this.addArgumentRequests();
            this.requests.add(CommandRequest.COMMIT);
            clientServices.processRequests(this.requests);
        }
        catch (CommandException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new CommandException(ex2, ex2.getLocalizedMessage());
        }
        finally {
            this.requests.clear();
        }
    }
    
    protected void addArgumentRequests() {
        if (this.isForceCommit()) {
            final Iterator<DirectoryRequest> iterator = this.requests.iterator();
            String localDirectory = "";
            final LinkedList<Object> list = new LinkedList<Object>();
            while (iterator.hasNext()) {
                final DirectoryRequest next = iterator.next();
                if (next instanceof DirectoryRequest) {
                    localDirectory = next.getLocalDirectory();
                }
                else {
                    if (!(next instanceof EntryRequest)) {
                        continue;
                    }
                    final EntryRequest entryRequest = (EntryRequest)next;
                    String s;
                    if (localDirectory.length() == 0) {
                        s = entryRequest.getEntry().getName();
                    }
                    else {
                        s = localDirectory + '/' + entryRequest.getEntry().getName();
                    }
                    list.add(new ArgumentRequest(s));
                }
            }
            final Iterator<Object> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                this.requests.add(iterator2.next());
            }
        }
        else {
            super.addArgumentRequests();
        }
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("commit ");
        sb.append(this.getCVSArguments());
        final File[] files = this.getFiles();
        if (files != null) {
            for (int i = 0; i < files.length; ++i) {
                sb.append(files[i].getName() + " ");
            }
        }
        return sb.toString();
    }
    
    public boolean setCVSCommand(final char c, final String toRevisionOrBranch) {
        if (c == 'm') {
            this.setMessage(toRevisionOrBranch);
        }
        else if (c == 'l') {
            this.setRecursive(false);
        }
        else if (c == 'R') {
            this.setRecursive(true);
        }
        else if (c == 'f') {
            this.setForceCommit(true);
        }
        else if (c == 'F') {
            this.setLogMessageFromFile(toRevisionOrBranch);
        }
        else if (c == 'r') {
            this.setToRevisionOrBranch(toRevisionOrBranch);
        }
        else {
            if (c != 'n') {
                return false;
            }
            this.setNoModuleProgram(true);
        }
        return true;
    }
    
    public String getOptString() {
        return "m:flRnF:r:";
    }
    
    public Builder createBuilder(final EventManager eventManager) {
        return new CommitBuilder(eventManager, this.getLocalDirectory(), this.clientServices.getRepository());
    }
    
    private void addMessageRequest(final String str) {
        this.requests.add(new ArgumentRequest("-m"));
        final StringTokenizer stringTokenizer = new StringTokenizer(str, "\n", false);
        int n = 1;
        while (stringTokenizer.hasMoreTokens()) {
            if (n != 0) {
                this.requests.add(new ArgumentRequest(stringTokenizer.nextToken()));
                n = 0;
            }
            else {
                this.requests.add(new ArgumentxRequest(stringTokenizer.nextToken()));
            }
        }
    }
    
    public String getLogMessageFromFile() {
        return this.logMessageFromFile;
    }
    
    public void setLogMessageFromFile(final String logMessageFromFile) {
        this.logMessageFromFile = logMessageFromFile;
    }
    
    public boolean isNoModuleProgram() {
        return this.noModuleProgram;
    }
    
    public void setNoModuleProgram(final boolean noModuleProgram) {
        this.noModuleProgram = noModuleProgram;
    }
    
    public String getToRevisionOrBranch() {
        return this.toRevisionOrBranch;
    }
    
    public void setToRevisionOrBranch(final String toRevisionOrBranch) {
        this.toRevisionOrBranch = toRevisionOrBranch;
    }
    
    private String loadLogFile(final String fileName) throws CommandException {
        final StringBuffer sb = new StringBuffer();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        catch (FileNotFoundException ex) {
            throw new CommandException(ex, CommandException.getLocalMessage("CommitCommand.logInfoFileNotExists", new Object[] { fileName }));
        }
        catch (IOException ex2) {
            throw new CommandException(ex2, CommandException.getLocalMessage("CommitCommand.errorReadingLogFile", new Object[] { fileName }));
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException ex3) {}
            }
        }
        return sb.toString();
    }
    
    public void resetCVSCommand() {
        this.setMessage(null);
        this.setRecursive(true);
        this.setForceCommit(false);
        this.setLogMessageFromFile(null);
        this.setNoModuleProgram(false);
        this.setToRevisionOrBranch(null);
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer();
        if (!this.isRecursive()) {
            sb.append("-l ");
        }
        if (this.isForceCommit()) {
            sb.append("-f ");
            if (this.isRecursive()) {
                sb.append("-R ");
            }
        }
        if (this.isNoModuleProgram()) {
            sb.append("-n ");
        }
        if (this.getToRevisionOrBranch() != null) {
            sb.append("-r ");
            sb.append(this.getToRevisionOrBranch() + " ");
        }
        if (this.getLogMessageFromFile() != null) {
            sb.append("-F ");
            sb.append(this.getLogMessageFromFile());
            sb.append(" ");
        }
        if (this.getMessage() != null) {
            sb.append("-m \"");
            sb.append(this.getMessage());
            sb.append("\" ");
        }
        return sb.toString();
    }
}
