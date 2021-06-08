// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.update;

import java.util.Iterator;
import org.netbeans.lib.cvsclient.command.CommandUtils;
import org.netbeans.lib.cvsclient.event.MessageEvent;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import java.util.ListIterator;
import java.io.EOFException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.request.UnchangedRequest;
import org.netbeans.lib.cvsclient.request.EntryRequest;
import org.netbeans.lib.cvsclient.request.Request;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import org.netbeans.lib.cvsclient.request.ArgumentRequest;
import org.netbeans.lib.cvsclient.ClientServices;
import java.io.IOException;
import org.netbeans.lib.cvsclient.file.FileUtils;
import java.text.MessageFormat;
import java.io.File;
import org.netbeans.lib.cvsclient.admin.Entry;
import org.netbeans.lib.cvsclient.command.BuildableCommand;
import org.netbeans.lib.cvsclient.command.PipedFilesBuilder;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.event.EventManager;
import java.util.HashSet;
import org.netbeans.lib.cvsclient.command.KeywordSubstitutionOptions;
import java.util.Set;
import org.netbeans.lib.cvsclient.command.TemporaryFileCreator;
import org.netbeans.lib.cvsclient.command.BasicCommand;

public class UpdateCommand extends BasicCommand implements TemporaryFileCreator
{
    private static final String RENAME_FORMAT = "{0}/.#{1}.{2}";
    private static final Object[] FORMAT_PARAMETER;
    private final Set emptyDirectories;
    private boolean buildDirectories;
    private boolean cleanCopy;
    private boolean pruneDirectories;
    private boolean pipeToOutput;
    private boolean resetStickyOnes;
    private boolean useHeadIfNotFound;
    private String updateByDate;
    private String updateByRevision;
    private KeywordSubstitutionOptions keywordSubst;
    private String mergeRevision1;
    private String mergeRevision2;
    
    public UpdateCommand() {
        this.emptyDirectories = new HashSet();
        this.resetCVSCommand();
    }
    
    public Builder createBuilder(final EventManager eventManager) {
        if (this.isPipeToOutput()) {
            return new PipedFilesBuilder(eventManager, this, this);
        }
        return new UpdateBuilder(eventManager, this.getLocalDirectory());
    }
    
    protected void sendEntryAndModifiedRequests(final Entry entry, File file) {
        if (this.isCleanCopy() && file != null && entry != null) {
            if (!this.isPipeToOutput()) {
                UpdateCommand.FORMAT_PARAMETER[0] = file.getParent();
                UpdateCommand.FORMAT_PARAMETER[1] = file.getName();
                UpdateCommand.FORMAT_PARAMETER[2] = entry.getRevision();
                final String format = MessageFormat.format("{0}/.#{1}.{2}", UpdateCommand.FORMAT_PARAMETER);
                try {
                    FileUtils.copyFile(file, new File(format));
                }
                catch (IOException ex) {}
            }
            file = null;
        }
        super.sendEntryAndModifiedRequests(entry, file);
    }
    
    public void setBuildDirectories(final boolean buildDirectories) {
        this.buildDirectories = buildDirectories;
    }
    
    public boolean isBuildDirectories() {
        return this.buildDirectories;
    }
    
    public void setCleanCopy(final boolean cleanCopy) {
        this.cleanCopy = cleanCopy;
    }
    
    public boolean isCleanCopy() {
        return this.cleanCopy;
    }
    
    public void setPruneDirectories(final boolean pruneDirectories) {
        this.pruneDirectories = pruneDirectories;
    }
    
    public boolean isPruneDirectories() {
        return this.pruneDirectories;
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        clientServices.ensureConnection();
        super.execute(clientServices, eventManager);
        this.emptyDirectories.clear();
        try {
            if (!this.isRecursive()) {
                this.requests.add(1, new ArgumentRequest("-l"));
            }
            if (this.isBuildDirectories()) {
                this.requests.add(1, new ArgumentRequest("-d"));
            }
            if (this.isCleanCopy() && !this.isPipeToOutput()) {
                this.requests.add(1, new ArgumentRequest("-C"));
            }
            if (this.isPipeToOutput()) {
                this.requests.add(1, new ArgumentRequest("-p"));
            }
            if (this.isResetStickyOnes()) {
                this.requests.add(1, new ArgumentRequest("-A"));
            }
            if (this.isUseHeadIfNotFound()) {
                this.requests.add(1, new ArgumentRequest("-f"));
            }
            if (this.getUpdateByDate() != null) {
                this.requests.add(1, new ArgumentRequest("-D"));
                this.requests.add(2, new ArgumentRequest(this.getUpdateByDate()));
            }
            else if (this.getUpdateByRevision() != null) {
                this.requests.add(1, new ArgumentRequest("-r"));
                this.requests.add(2, new ArgumentRequest(this.getUpdateByRevision()));
            }
            if (this.getMergeRevision1() != null) {
                this.requests.add(1, new ArgumentRequest("-j"));
                this.requests.add(2, new ArgumentRequest(this.getMergeRevision1()));
                if (this.getMergeRevision2() != null) {
                    this.requests.add(3, new ArgumentRequest("-j"));
                    this.requests.add(4, new ArgumentRequest(this.getMergeRevision2()));
                }
            }
            if (this.getKeywordSubst() != null) {
                this.requests.add(1, new ArgumentRequest("-k"));
                this.requests.add(2, new ArgumentRequest(this.getKeywordSubst().toString()));
            }
            this.requests.add(1, new ArgumentRequest("-u"));
            this.addRequestForWorkingDirectory(clientServices);
            this.addArgumentRequests();
            this.addRequest(CommandRequest.UPDATE);
            if (this.isPipeToOutput() && (this.getUpdateByRevision() != null || this.getUpdateByDate() != null)) {
                final ListIterator<UnchangedRequest> listIterator = this.requests.listIterator();
                while (listIterator.hasNext()) {
                    final UnchangedRequest next = listIterator.next();
                    if (next instanceof EntryRequest) {
                        final Entry entry = ((EntryRequest)next).getEntry();
                        if (entry.getRevision().startsWith("-")) {
                            entry.setRevision(entry.getRevision().substring(1));
                        }
                        listIterator.set((UnchangedRequest)new EntryRequest(entry));
                        listIterator.add(new UnchangedRequest(entry.getName()));
                    }
                }
            }
            clientServices.processRequests(this.requests);
            if (this.pruneDirectories && (this.getGlobalOptions() == null || !this.getGlobalOptions().isDoNoChanges())) {
                this.pruneEmptyDirectories(clientServices);
            }
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
        finally {
            this.requests.clear();
        }
    }
    
    public boolean isPipeToOutput() {
        return this.pipeToOutput;
    }
    
    public void setPipeToOutput(final boolean pipeToOutput) {
        this.pipeToOutput = pipeToOutput;
    }
    
    public boolean isResetStickyOnes() {
        return this.resetStickyOnes;
    }
    
    public void setResetStickyOnes(final boolean resetStickyOnes) {
        this.resetStickyOnes = resetStickyOnes;
    }
    
    public boolean isUseHeadIfNotFound() {
        return this.useHeadIfNotFound;
    }
    
    public void setUseHeadIfNotFound(final boolean useHeadIfNotFound) {
        this.useHeadIfNotFound = useHeadIfNotFound;
    }
    
    public String getUpdateByDate() {
        return this.updateByDate;
    }
    
    public void setUpdateByDate(final String s) {
        this.updateByDate = Command.getTrimmedString(s);
    }
    
    public String getUpdateByRevision() {
        return this.updateByRevision;
    }
    
    public void setUpdateByRevision(final String s) {
        this.updateByRevision = Command.getTrimmedString(s);
    }
    
    public KeywordSubstitutionOptions getKeywordSubst() {
        return this.keywordSubst;
    }
    
    public void setKeywordSubst(final KeywordSubstitutionOptions keywordSubst) {
        this.keywordSubst = keywordSubst;
    }
    
    public File createTempFile(final String s) throws IOException {
        return File.createTempFile("cvs", ".dff", this.getGlobalOptions().getTempDir());
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("update ");
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
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer("");
        if (this.isPipeToOutput()) {
            sb.append("-p ");
        }
        if (this.isCleanCopy()) {
            sb.append("-C ");
        }
        if (!this.isRecursive()) {
            sb.append("-l ");
        }
        if (this.isBuildDirectories()) {
            sb.append("-d ");
        }
        if (this.isPruneDirectories()) {
            sb.append("-P ");
        }
        if (this.isResetStickyOnes()) {
            sb.append("-A ");
        }
        if (this.isUseHeadIfNotFound()) {
            sb.append("-f ");
        }
        if (this.getKeywordSubst() != null) {
            sb.append("-k");
            sb.append(this.getKeywordSubst().toString());
            sb.append(' ');
        }
        if (this.getUpdateByRevision() != null) {
            sb.append("-r ");
            sb.append(this.getUpdateByRevision());
            sb.append(' ');
        }
        if (this.getUpdateByDate() != null) {
            sb.append("-D ");
            sb.append(this.getUpdateByDate());
            sb.append(' ');
        }
        if (this.getMergeRevision1() != null) {
            sb.append("-j ");
            sb.append(this.getMergeRevision1());
            sb.append(' ');
            if (this.getMergeRevision2() != null) {
                sb.append("-j ");
                sb.append(this.getMergeRevision2());
                sb.append(' ');
            }
        }
        return sb.toString();
    }
    
    public boolean setCVSCommand(final char c, final String s) {
        if (c == 'R') {
            this.setRecursive(true);
        }
        else if (c == 'C') {
            this.setCleanCopy(true);
        }
        else if (c == 'l') {
            this.setRecursive(false);
        }
        else if (c == 'd') {
            this.setBuildDirectories(true);
        }
        else if (c == 'P') {
            this.setPruneDirectories(true);
        }
        else if (c == 'A') {
            this.setResetStickyOnes(true);
        }
        else if (c == 'f') {
            this.setUseHeadIfNotFound(true);
        }
        else if (c == 'D') {
            this.setUpdateByDate(s.trim());
        }
        else if (c == 'r') {
            this.setUpdateByRevision(s.trim());
        }
        else if (c == 'k') {
            this.setKeywordSubst(KeywordSubstitutionOptions.findKeywordSubstOption(s));
        }
        else if (c == 'p') {
            this.setPipeToOutput(true);
        }
        else {
            if (c != 'j') {
                return false;
            }
            if (this.getMergeRevision1() == null) {
                this.setMergeRevision1(s);
            }
            else {
                this.setMergeRevision2(s);
            }
        }
        return true;
    }
    
    public void resetCVSCommand() {
        this.setRecursive(true);
        this.setCleanCopy(false);
        this.setBuildDirectories(false);
        this.setPruneDirectories(false);
        this.setResetStickyOnes(false);
        this.setUseHeadIfNotFound(false);
        this.setUpdateByDate(null);
        this.setUpdateByRevision(null);
        this.setKeywordSubst(null);
        this.setPipeToOutput(false);
        this.setMergeRevision1(null);
        this.setMergeRevision2(null);
    }
    
    public void messageSent(final MessageEvent messageEvent) {
        super.messageSent(messageEvent);
        if (!this.pruneDirectories) {
            return;
        }
        final String examinedDirectory = CommandUtils.getExaminedDirectory(messageEvent.getMessage(), ": Updating");
        if (examinedDirectory == null) {
            return;
        }
        if (examinedDirectory.equals(".")) {
            return;
        }
        this.emptyDirectories.add(new File(this.getLocalDirectory(), examinedDirectory));
    }
    
    private boolean pruneEmptyDirectory(final File parent, final ClientServices clientServices) throws IOException {
        final File[] listFiles = parent.listFiles();
        if (listFiles == null) {
            return true;
        }
        for (int i = 0; i < listFiles.length; ++i) {
            if (listFiles[i].isFile()) {
                return false;
            }
            if (!listFiles[i].getName().equals("CVS")) {
                if (!this.pruneEmptyDirectory(listFiles[i], clientServices)) {
                    return false;
                }
            }
        }
        if (new File(parent, "CVS/Entries").isFile() && new File(parent, "CVS/Repository").isFile()) {
            final File file = new File(parent, "CVS");
            final Iterator entries = this.clientServices.getEntries(parent);
            while (entries.hasNext()) {
                final Entry entry = entries.next();
                if (entry.getName() != null && entry.isUserFileToBeRemoved()) {
                    return false;
                }
            }
            this.deleteRecursively(file);
            parent.delete();
            if (!clientServices.exists(parent)) {
                clientServices.removeEntry(parent);
            }
            return true;
        }
        return false;
    }
    
    private void deleteRecursively(final File file) {
        final File[] listFiles = file.listFiles();
        for (int i = 0; i < listFiles.length; ++i) {
            final File file2 = listFiles[i];
            if (file2.isDirectory()) {
                this.deleteRecursively(file2);
            }
            else {
                file2.delete();
            }
        }
        file.delete();
    }
    
    private void pruneEmptyDirectories(final ClientServices clientServices) throws IOException {
        for (final File file : this.emptyDirectories) {
            if (file.exists()) {
                this.pruneEmptyDirectory(file, clientServices);
            }
        }
        this.emptyDirectories.clear();
    }
    
    public String getOptString() {
        return "RCnldPAfD:r:pj:k:";
    }
    
    public String getMergeRevision1() {
        return this.mergeRevision1;
    }
    
    public void setMergeRevision1(final String s) {
        this.mergeRevision1 = Command.getTrimmedString(s);
    }
    
    public String getMergeRevision2() {
        return this.mergeRevision2;
    }
    
    public void setMergeRevision2(final String s) {
        this.mergeRevision2 = Command.getTrimmedString(s);
    }
    
    static {
        FORMAT_PARAMETER = new Object[3];
    }
}
