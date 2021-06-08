// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.add;

import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.request.ArgumentxRequest;
import java.util.StringTokenizer;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import java.util.Collection;
import org.netbeans.lib.cvsclient.request.RootRequest;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.event.MessageEvent;
import org.netbeans.lib.cvsclient.event.EventManager;
import java.util.Iterator;
import org.netbeans.lib.cvsclient.util.SimpleStringPattern;
import org.netbeans.lib.cvsclient.admin.Entry;
import org.netbeans.lib.cvsclient.request.IsModifiedRequest;
import org.netbeans.lib.cvsclient.request.KoptRequest;
import org.netbeans.lib.cvsclient.command.WrapperUtils;
import org.netbeans.lib.cvsclient.request.EntryRequest;
import org.netbeans.lib.cvsclient.request.ArgumentRequest;
import org.netbeans.lib.cvsclient.request.StickyRequest;
import org.netbeans.lib.cvsclient.request.DirectoryRequest;
import org.netbeans.lib.cvsclient.command.CommandException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import org.netbeans.lib.cvsclient.command.KeywordSubstitutionOptions;
import java.io.File;
import org.netbeans.lib.cvsclient.ClientServices;
import java.util.List;
import org.netbeans.lib.cvsclient.command.BuildableCommand;

public class AddCommand extends BuildableCommand
{
    private static final String DIR_ADDED = " added to the repository";
    private static final String DIRECTORY = "Directory ";
    private List requests;
    private final List argumentRequests;
    private final List newDirList;
    private ClientServices clientServices;
    private File[] files;
    private String message;
    private KeywordSubstitutionOptions keywordSubst;
    private Map wrapperMap;
    private HashMap dir2WrapperMap;
    private static final Map EMPTYWRAPPER;
    
    public AddCommand() {
        this.argumentRequests = new LinkedList();
        this.newDirList = new LinkedList();
        this.dir2WrapperMap = new HashMap(16);
        this.resetCVSCommand();
    }
    
    public void setFiles(final File[] files) {
        this.files = files;
        if (files == null) {
            return;
        }
        this.files = new File[files.length];
        int n = 0;
        int n2 = 0;
        for (final File file : files) {
            if (file.isDirectory()) {
                this.files[n] = file;
                ++n;
            }
            else {
                final int length;
                this.files[length - (1 + n2)] = file;
                ++n2;
            }
        }
    }
    
    public File[] getFiles() {
        return this.files;
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
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public KeywordSubstitutionOptions getKeywordSubst() {
        return this.keywordSubst;
    }
    
    public void setKeywordSubst(final KeywordSubstitutionOptions keywordSubst) {
        this.keywordSubst = keywordSubst;
    }
    
    protected void addRequests(final File file) throws IOException, CommandException {
        if (file.isDirectory()) {
            this.addRequestsForDirectory(file, false);
        }
        else {
            this.addRequestsForFile(file);
        }
    }
    
    private void addRequestsForDirectory(final File file, final boolean b) throws IOException {
        final File parentFile = file.getParentFile();
        final String str = b ? this.getRelativeToLocalPathInUnixStyle(file) : this.getRelativeToLocalPathInUnixStyle(parentFile);
        String s;
        if (str.equals(".")) {
            s = file.getName();
        }
        else {
            s = str + "/" + file.getName();
            this.addRequestsForDirectory(parentFile, true);
        }
        if (b) {
            s = str;
        }
        String s2;
        String s3;
        if (b) {
            s2 = this.clientServices.getRepositoryForDirectory(file.getAbsolutePath());
            s3 = this.clientServices.getStickyTagForDirectory(file);
        }
        else {
            final String repositoryForDirectory = this.clientServices.getRepositoryForDirectory(parentFile.getAbsolutePath());
            if (repositoryForDirectory.endsWith(".")) {
                s2 = repositoryForDirectory.substring(0, repositoryForDirectory.length() - 1) + file.getName();
            }
            else {
                s2 = repositoryForDirectory + "/" + file.getName();
            }
            s3 = this.clientServices.getStickyTagForDirectory(parentFile);
        }
        this.requests.add(new DirectoryRequest(s, s2));
        if (s3 != null) {
            this.requests.add(new StickyRequest(s3));
        }
        if (!b) {
            this.argumentRequests.add(new ArgumentRequest(s));
            this.newDirList.add(new Paths(s, s2));
        }
    }
    
    protected void addRequestsForFile(final File file) throws IOException, CommandException {
        final File parentFile = file.getParentFile();
        final String relativeToLocalPathInUnixStyle = this.getRelativeToLocalPathInUnixStyle(parentFile);
        this.requests.add(new DirectoryRequest(relativeToLocalPathInUnixStyle, this.clientServices.getRepositoryForDirectory(parentFile.getAbsolutePath())));
        final String stickyTagForDirectory = this.clientServices.getStickyTagForDirectory(parentFile);
        if (stickyTagForDirectory != null) {
            this.requests.add(new StickyRequest(stickyTagForDirectory));
        }
        final Entry entry = this.clientServices.getEntry(file);
        if (entry != null) {
            this.requests.add(new EntryRequest(entry));
        }
        else {
            Map emptywrapper = this.dir2WrapperMap.get(relativeToLocalPathInUnixStyle);
            if (emptywrapper == null) {
                final File file2 = new File(parentFile, ".cvswrappers");
                if (file2.exists()) {
                    emptywrapper = new HashMap(5);
                    WrapperUtils.readWrappersFromFile(file2, emptywrapper);
                }
                else {
                    emptywrapper = AddCommand.EMPTYWRAPPER;
                }
                this.dir2WrapperMap.put(relativeToLocalPathInUnixStyle, emptywrapper);
            }
            if (this.isBinary(this.clientServices, file.getName(), emptywrapper)) {
                this.requests.add(new KoptRequest("-kb"));
            }
            this.requests.add(new IsModifiedRequest(file));
        }
        if (relativeToLocalPathInUnixStyle.equals(".")) {
            this.argumentRequests.add(new ArgumentRequest(file.getName()));
        }
        else {
            this.argumentRequests.add(new ArgumentRequest(relativeToLocalPathInUnixStyle + "/" + file.getName()));
        }
    }
    
    private boolean isBinary(final ClientServices clientServices, final String s, final Map map) throws CommandException {
        KeywordSubstitutionOptions keywordSubst = this.getKeywordSubst();
        if (keywordSubst == KeywordSubstitutionOptions.BINARY) {
            return true;
        }
        boolean b = false;
        if (this.wrapperMap == null) {
            this.wrapperMap = WrapperUtils.mergeWrapperMap(clientServices);
        }
        for (final SimpleStringPattern simpleStringPattern : this.wrapperMap.keySet()) {
            if (simpleStringPattern.doesMatch(s)) {
                keywordSubst = (KeywordSubstitutionOptions)this.wrapperMap.get(simpleStringPattern);
                b = true;
                break;
            }
        }
        if (!b && map != null && map != AddCommand.EMPTYWRAPPER) {
            for (final SimpleStringPattern simpleStringPattern2 : map.keySet()) {
                if (simpleStringPattern2.doesMatch(s)) {
                    keywordSubst = map.get(simpleStringPattern2);
                    break;
                }
            }
        }
        return keywordSubst == KeywordSubstitutionOptions.BINARY;
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        if (this.files == null || this.files.length == 0) {
            throw new CommandException("No files have been specified for adding.", CommandException.getLocalMessage("AddCommand.noFilesSpecified", null));
        }
        clientServices.ensureConnection();
        this.clientServices = clientServices;
        this.setLocalDirectory(clientServices.getLocalPath());
        if (!new File(clientServices.getLocalPath(), "CVS").isDirectory()) {
            final MessageEvent messageEvent = new MessageEvent(this, "cvs [add aborted]: there is no version here; do 'cvs checkout' first", true);
            this.messageSent(messageEvent);
            eventManager.fireCVSEvent(messageEvent);
            return;
        }
        this.newDirList.clear();
        super.execute(clientServices, eventManager);
        this.requests = new LinkedList();
        if (clientServices.isFirstCommand()) {
            this.requests.add(new RootRequest(clientServices.getRepository()));
        }
        String s = this.getMessage();
        if (s != null) {
            s = s.trim();
        }
        if (s != null && s.length() > 0) {
            this.addMessageRequest(s);
        }
        while (true) {
            if (this.getKeywordSubst() != null && !this.getKeywordSubst().equals("")) {
                this.requests.add(new ArgumentRequest("-k" + this.getKeywordSubst()));
                try {
                    for (int i = 0; i < this.files.length; ++i) {
                        this.addRequests(this.files[i]);
                    }
                    this.requests.add(new DirectoryRequest(".", clientServices.getRepositoryForDirectory(this.getLocalDirectory())));
                    this.requests.addAll(this.argumentRequests);
                    this.argumentRequests.clear();
                    this.requests.add(CommandRequest.ADD);
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
                return;
            }
            continue;
        }
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
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("add ");
        sb.append(this.getCVSArguments());
        final File[] files = this.getFiles();
        if (files != null) {
            for (int i = 0; i < files.length; ++i) {
                sb.append(files[i].getName());
                sb.append(' ');
            }
        }
        return sb.toString();
    }
    
    public Builder createBuilder(final EventManager eventManager) {
        return new AddBuilder(eventManager, this);
    }
    
    public boolean setCVSCommand(final char c, final String message) {
        if (c == 'm') {
            this.setMessage(message);
        }
        else {
            if (c != 'k') {
                return false;
            }
            this.setKeywordSubst(KeywordSubstitutionOptions.findKeywordSubstOption(message));
        }
        return true;
    }
    
    public String getOptString() {
        return "m:k:";
    }
    
    public void messageSent(final MessageEvent messageEvent) {
        final String message = messageEvent.getMessage();
        if (message.endsWith(" added to the repository")) {
            this.createCvsFiles(message.substring("Directory ".length(), message.indexOf(" added to the repository")).trim());
        }
        super.messageSent(messageEvent);
    }
    
    private void createCvsFiles(final String s) {
        String substring = s;
        if (substring.lastIndexOf(47) >= 0) {
            substring = substring.substring(substring.lastIndexOf(47) + 1, substring.length());
        }
        if (this.newDirList.size() == 0) {
            System.err.println("JavaCVS: Bug in AddCommand|createCvsFiles");
            System.err.println("         newDirInRepository = " + s);
            return;
        }
        final Paths paths = this.newDirList.remove(0);
        final String partPath = paths.getPartPath();
        final String repositoryPath = paths.getRepositoryPath();
        String s2 = paths.getRepositoryPath();
        String substring2 = repositoryPath;
        if (repositoryPath.lastIndexOf(47) >= 0) {
            substring2 = repositoryPath.substring(repositoryPath.lastIndexOf(47) + 1, repositoryPath.length());
        }
        if (!substring2.equalsIgnoreCase(substring)) {
            System.err.println("JavaCVS: Bug in AddCommand|createCvsFiles");
            System.err.println("         newDirInRepository = " + s);
            System.err.println("         tempDirName = " + substring2);
            System.err.println("         dirName = " + substring);
            return;
        }
        try {
            if (s2.startsWith(".")) {
                s2 = s2.substring(1);
            }
            this.clientServices.updateAdminData(partPath, s2, null);
            this.createCvsTagFile(partPath, s2);
        }
        catch (IOException ex) {
            System.err.println("TODO: couldn't create/update Cvs admin files");
        }
    }
    
    private void createCvsTagFile(final String child, final String s) throws IOException {
        final File parent = new File(this.getLocalDirectory(), child);
        final String stickyTagForDirectory = this.clientServices.getStickyTagForDirectory(parent.getParentFile());
        if (stickyTagForDirectory != null) {
            final File file = new File(parent, "CVS/Tag");
            file.createNewFile();
            final PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            printWriter.println(stickyTagForDirectory);
            printWriter.close();
        }
    }
    
    public void resetCVSCommand() {
        this.setMessage(null);
        this.setKeywordSubst(null);
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer();
        if (this.getMessage() != null) {
            sb.append("-m \"");
            sb.append(this.getMessage());
            sb.append("\" ");
        }
        if (this.getKeywordSubst() != null) {
            sb.append("-k");
            sb.append(this.getKeywordSubst().toString());
            sb.append(" ");
        }
        return sb.toString();
    }
    
    static {
        EMPTYWRAPPER = new HashMap(1);
    }
    
    private static class Paths
    {
        private final String partPath;
        private final String repositoryPath;
        
        public Paths(final String partPath, final String repositoryPath) {
            this.partPath = partPath;
            this.repositoryPath = repositoryPath;
        }
        
        public String getPartPath() {
            return this.partPath;
        }
        
        public String getRepositoryPath() {
            return this.repositoryPath;
        }
    }
}
