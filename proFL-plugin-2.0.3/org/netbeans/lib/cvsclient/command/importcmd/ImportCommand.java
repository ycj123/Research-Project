// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.importcmd;

import org.netbeans.lib.cvsclient.command.Builder;
import java.io.IOException;
import org.netbeans.lib.cvsclient.request.ModifiedRequest;
import org.netbeans.lib.cvsclient.request.ArgumentxRequest;
import java.util.StringTokenizer;
import org.netbeans.lib.cvsclient.response.WrapperSendResponse;
import java.util.Iterator;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import java.io.EOFException;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import org.netbeans.lib.cvsclient.request.DirectoryRequest;
import java.io.File;
import org.netbeans.lib.cvsclient.request.ArgumentRequest;
import java.util.ArrayList;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.ClientServices;
import java.util.Collections;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.util.StringPattern;
import org.netbeans.lib.cvsclient.util.SimpleStringPattern;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import org.netbeans.lib.cvsclient.command.KeywordSubstitutionOptions;
import java.util.Map;
import org.netbeans.lib.cvsclient.command.BuildableCommand;

public class ImportCommand extends BuildableCommand
{
    private Map wrapperMap;
    private String logMessage;
    private String module;
    private String releaseTag;
    private String vendorBranch;
    private String vendorTag;
    private String importDirectory;
    private KeywordSubstitutionOptions keywordSubstitutionOptions;
    private boolean useFileModifTime;
    private List ignoreList;
    
    public ImportCommand() {
        this.wrapperMap = new HashMap();
        this.ignoreList = new LinkedList();
        this.resetCVSCommand();
    }
    
    public void addWrapper(final String s, final KeywordSubstitutionOptions keywordSubstitutionOptions) {
        if (keywordSubstitutionOptions == null) {
            throw new IllegalArgumentException("keywordSubstitutionOptions must not be null");
        }
        this.wrapperMap.put(new SimpleStringPattern(s), keywordSubstitutionOptions);
    }
    
    public void addWrapper(final StringPattern stringPattern, final KeywordSubstitutionOptions keywordSubstitutionOptions) {
        if (keywordSubstitutionOptions == null) {
            throw new IllegalArgumentException("keywordSubstitutionOptions must not be null");
        }
        this.wrapperMap.put(stringPattern, keywordSubstitutionOptions);
    }
    
    public void setWrappers(final Map wrapperMap) {
        this.wrapperMap = wrapperMap;
    }
    
    public Map getWrappers() {
        return this.wrapperMap;
    }
    
    public KeywordSubstitutionOptions getKeywordSubstitutionOptions() {
        return this.keywordSubstitutionOptions;
    }
    
    public void setKeywordSubstitutionOptions(final KeywordSubstitutionOptions keywordSubstitutionOptions) {
        this.keywordSubstitutionOptions = keywordSubstitutionOptions;
    }
    
    public String getReleaseTag() {
        return this.releaseTag;
    }
    
    public void setReleaseTag(final String s) {
        this.releaseTag = Command.getTrimmedString(s);
    }
    
    public String getLogMessage() {
        return this.logMessage;
    }
    
    public void setLogMessage(final String s) {
        this.logMessage = Command.getTrimmedString(s);
    }
    
    public String getModule() {
        return this.module;
    }
    
    public void setModule(final String s) {
        this.module = Command.getTrimmedString(s);
    }
    
    public void setImportDirectory(final String importDirectory) {
        this.importDirectory = importDirectory;
    }
    
    public String getImportDirectory() {
        return this.importDirectory;
    }
    
    public String getVendorBranch() {
        return this.vendorBranch;
    }
    
    private String getVendorBranchNotNull() {
        if (this.vendorBranch == null) {
            return "1.1.1";
        }
        return this.vendorBranch;
    }
    
    public void setVendorBranch(final String s) {
        this.vendorBranch = Command.getTrimmedString(s);
    }
    
    public String getVendorTag() {
        return this.vendorTag;
    }
    
    public void setVendorTag(final String s) {
        this.vendorTag = Command.getTrimmedString(s);
    }
    
    public boolean isUseFileModifTime() {
        return this.useFileModifTime;
    }
    
    public void setUseFileModifTime(final boolean useFileModifTime) {
        this.useFileModifTime = useFileModifTime;
    }
    
    public List getIgnoreFiles() {
        return Collections.unmodifiableList((List<?>)this.ignoreList);
    }
    
    public void addIgnoredFile(final String s) {
        this.ignoreList.add(s);
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        if (this.getLogMessage() == null) {
            throw new CommandException("message may not be null nor empty", CommandException.getLocalMessage("ImportCommand.messageEmpty"));
        }
        if (this.getModule() == null) {
            throw new CommandException("module may not be null nor empty", CommandException.getLocalMessage("ImportCommand.moduleEmpty"));
        }
        if (this.getReleaseTag() == null) {
            throw new CommandException("release tag may not be null nor empty", CommandException.getLocalMessage("ImportCommand.releaseTagEmpty"));
        }
        if (this.getVendorTag() == null) {
            throw new CommandException("vendor tag may not be null nor empty", CommandException.getLocalMessage("ImportCommand.vendorTagEmpty"));
        }
        clientServices.ensureConnection();
        final HashMap wrappers = new HashMap(clientServices.getWrappersMap());
        wrappers.putAll(this.getWrappers());
        this.setWrappers(wrappers);
        super.execute(clientServices, eventManager);
        assert this.getLocalDirectory() != null : "local directory may not be null";
        final ArrayList<ArgumentRequest> list = new ArrayList<ArgumentRequest>();
        try {
            list.add(new ArgumentRequest("-b"));
            list.add(new ArgumentRequest(this.getVendorBranchNotNull()));
            if (this.getKeywordSubstitutionOptions() != null) {
                list.add(new ArgumentRequest("-k"));
                list.add(new ArgumentRequest(this.getKeywordSubstitutionOptions().toString()));
            }
            this.addMessageRequests(list, this.getLogMessage());
            this.addWrapperRequests(list, this.wrapperMap);
            if (this.isUseFileModifTime()) {
                list.add(new ArgumentRequest("-d"));
            }
            for (int i = 0; i < this.ignoreList.size(); ++i) {
                list.add(new ArgumentRequest("-I"));
                list.add(new ArgumentRequest((String)this.ignoreList.get(i)));
            }
            list.add(new ArgumentRequest(this.getModule()));
            list.add(new ArgumentRequest(this.getVendorTag()));
            list.add(new ArgumentRequest(this.getReleaseTag()));
            this.addFileRequests(new File(this.getLocalDirectory()), list, clientServices);
            list.add((ArgumentRequest)new DirectoryRequest(".", this.getRepositoryRoot(clientServices)));
            list.add((ArgumentRequest)CommandRequest.IMPORT);
            clientServices.processRequests(list);
        }
        catch (CommandException ex) {
            throw ex;
        }
        catch (EOFException ex2) {
            throw new CommandException(ex2, CommandException.getLocalMessage("CommandException.EndOfFile", null));
        }
        catch (Exception ex3) {
            throw new CommandException(ex3, ex3.getLocalizedMessage());
        }
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("import ");
        sb.append(this.getCVSArguments());
        if (this.getModule() != null) {
            sb.append(" ");
            sb.append(this.getModule());
        }
        else {
            final String localMessage = CommandException.getLocalMessage("ImportCommand.moduleEmpty.text");
            sb.append(" ");
            sb.append(localMessage);
        }
        if (this.getVendorTag() != null) {
            sb.append(" ");
            sb.append(this.getVendorTag());
        }
        else {
            final String localMessage2 = CommandException.getLocalMessage("ImportCommand.vendorTagEmpty.text");
            sb.append(" ");
            sb.append(localMessage2);
        }
        if (this.getReleaseTag() != null) {
            sb.append(" ");
            sb.append(this.getReleaseTag());
        }
        else {
            final String localMessage3 = CommandException.getLocalMessage("ImportCommand.releaseTagEmpty.text");
            sb.append(" ");
            sb.append(localMessage3);
        }
        return sb.toString();
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer("");
        if (this.getLogMessage() != null) {
            sb.append("-m \"");
            sb.append(this.getLogMessage());
            sb.append("\" ");
        }
        if (this.getKeywordSubstitutionOptions() != null) {
            sb.append("-k");
            sb.append(this.getKeywordSubstitutionOptions().toString());
            sb.append(" ");
        }
        if (this.getVendorBranch() != null) {
            sb.append("-b ");
            sb.append(this.getVendorBranch());
            sb.append(" ");
        }
        if (this.isUseFileModifTime()) {
            sb.append("-d ");
        }
        if (this.wrapperMap.size() > 0) {
            for (final StringPattern stringPattern : this.wrapperMap.keySet()) {
                final KeywordSubstitutionOptions keywordSubstitutionOptions = this.wrapperMap.get(stringPattern);
                sb.append("-W ");
                sb.append(stringPattern.toString());
                sb.append(" -k '");
                sb.append(keywordSubstitutionOptions.toString());
                sb.append("' ");
            }
        }
        final Iterator<String> iterator2 = this.ignoreList.iterator();
        while (iterator2.hasNext()) {
            sb.append("-I ");
            sb.append(iterator2.next());
            sb.append(" ");
        }
        return sb.toString();
    }
    
    public boolean setCVSCommand(final char c, final String s) {
        if (c == 'b') {
            this.setVendorBranch(s);
        }
        else if (c == 'm') {
            this.setLogMessage(s);
        }
        else if (c == 'k') {
            this.setKeywordSubstitutionOptions(KeywordSubstitutionOptions.findKeywordSubstOption(s));
        }
        else if (c == 'W') {
            final Map wrappers = WrapperSendResponse.parseWrappers(s);
            for (final StringPattern stringPattern : wrappers.keySet()) {
                this.addWrapper(stringPattern, wrappers.get(stringPattern));
            }
        }
        else if (c == 'd') {
            this.setUseFileModifTime(true);
        }
        else {
            if (c != 'I') {
                return false;
            }
            this.addIgnoredFile(s);
        }
        return true;
    }
    
    public void resetCVSCommand() {
        this.setLogMessage(null);
        this.setModule(null);
        this.setReleaseTag(null);
        this.setVendorTag(null);
        this.setVendorBranch(null);
        this.setUseFileModifTime(false);
        this.ignoreList.clear();
        this.wrapperMap.clear();
    }
    
    public String getOptString() {
        return "m:W:b:k:dI:";
    }
    
    private void addMessageRequests(final List list, final String str) {
        list.add(new ArgumentRequest("-m"));
        final StringTokenizer stringTokenizer = new StringTokenizer(str, "\n", false);
        int n = 1;
        while (stringTokenizer.hasMoreTokens()) {
            if (n != 0) {
                list.add(new ArgumentRequest(stringTokenizer.nextToken()));
                n = 0;
            }
            else {
                list.add(new ArgumentxRequest(stringTokenizer.nextToken()));
            }
        }
    }
    
    private void addWrapperRequests(final List list, final Map map) {
        for (final StringPattern stringPattern : map.keySet()) {
            final KeywordSubstitutionOptions keywordSubstitutionOptions = map.get(stringPattern);
            final StringBuffer sb = new StringBuffer();
            sb.append(stringPattern.toString());
            sb.append(" -k '");
            sb.append(keywordSubstitutionOptions.toString());
            sb.append("'");
            list.add(new ArgumentRequest("-W"));
            list.add(new ArgumentRequest(sb.toString()));
        }
    }
    
    private void addFileRequests(final File file, final List list, final ClientServices clientServices) throws IOException {
        final String relativeToLocalPathInUnixStyle = this.getRelativeToLocalPathInUnixStyle(file);
        String str = this.getRepositoryRoot(clientServices);
        if (!relativeToLocalPathInUnixStyle.equals(".")) {
            str = str + '/' + relativeToLocalPathInUnixStyle;
        }
        list.add(new DirectoryRequest(relativeToLocalPathInUnixStyle, str));
        final File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return;
        }
        List<File> list2 = null;
        for (int i = 0; i < listFiles.length; ++i) {
            final File file2 = listFiles[i];
            final String name = file2.getName();
            if (!clientServices.shouldBeIgnored(file, name)) {
                if (file2.isDirectory()) {
                    if (list2 == null) {
                        list2 = new LinkedList<File>();
                    }
                    list2.add(file2);
                }
                else {
                    list.add(new ModifiedRequest(file2, this.isBinary(name)));
                }
            }
        }
        if (list2 != null) {
            final Iterator<File> iterator = list2.iterator();
            while (iterator.hasNext()) {
                this.addFileRequests(iterator.next(), list, clientServices);
            }
        }
    }
    
    private String getRepositoryRoot(final ClientServices clientServices) {
        return clientServices.getRepository() + '/' + this.getModule();
    }
    
    private boolean isBinary(final String s) {
        KeywordSubstitutionOptions keywordSubstitutionOptions = this.getKeywordSubstitutionOptions();
        for (final StringPattern stringPattern : this.wrapperMap.keySet()) {
            if (stringPattern.doesMatch(s)) {
                keywordSubstitutionOptions = (KeywordSubstitutionOptions)this.wrapperMap.get(stringPattern);
                break;
            }
        }
        return keywordSubstitutionOptions == KeywordSubstitutionOptions.BINARY;
    }
    
    public Builder createBuilder(final EventManager eventManager) {
        return new ImportBuilder(eventManager, this);
    }
}
