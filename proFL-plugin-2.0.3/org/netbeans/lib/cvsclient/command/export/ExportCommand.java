// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.export;

import java.io.IOException;
import org.netbeans.lib.cvsclient.event.MessageEvent;
import org.netbeans.lib.cvsclient.command.Builder;
import java.util.Iterator;
import java.io.File;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import org.netbeans.lib.cvsclient.request.DirectoryRequest;
import org.netbeans.lib.cvsclient.request.ArgumentRequest;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.ClientServices;
import java.util.HashSet;
import org.netbeans.lib.cvsclient.command.KeywordSubstitutionOptions;
import java.util.Set;
import org.netbeans.lib.cvsclient.command.RepositoryCommand;

public class ExportCommand extends RepositoryCommand
{
    private final Set emptyDirectories;
    private boolean pruneDirectories;
    private KeywordSubstitutionOptions keywordSubstitutionOptions;
    private String exportByDate;
    private String exportByRevision;
    private String exportDirectory;
    private boolean useHeadIfNotFound;
    private boolean notShortenPaths;
    private boolean notRunModuleProgram;
    
    public ExportCommand() {
        this.emptyDirectories = new HashSet();
        this.resetCVSCommand();
    }
    
    public KeywordSubstitutionOptions getKeywordSubstitutionOptions() {
        return this.keywordSubstitutionOptions;
    }
    
    public void setKeywordSubstitutionOptions(final KeywordSubstitutionOptions keywordSubstitutionOptions) {
        this.keywordSubstitutionOptions = keywordSubstitutionOptions;
    }
    
    public void setPruneDirectories(final boolean pruneDirectories) {
        this.pruneDirectories = pruneDirectories;
    }
    
    public boolean isPruneDirectories() {
        return this.pruneDirectories;
    }
    
    protected void postExpansionExecute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        if (!this.isRecursive()) {
            this.requests.add(0, new ArgumentRequest("-l"));
        }
        if (this.useHeadIfNotFound) {
            this.requests.add(0, new ArgumentRequest("-f"));
        }
        if (this.exportDirectory != null && !this.exportDirectory.equals("")) {
            this.requests.add(0, new ArgumentRequest("-d"));
            this.requests.add(1, new ArgumentRequest(this.getExportDirectory()));
        }
        if (this.exportByDate != null && this.exportByDate.length() > 0) {
            this.requests.add(0, new ArgumentRequest("-D"));
            this.requests.add(1, new ArgumentRequest(this.getExportByDate()));
        }
        if (this.exportByRevision != null && this.exportByRevision.length() > 0) {
            this.requests.add(0, new ArgumentRequest("-r"));
            this.requests.add(1, new ArgumentRequest(this.getExportByRevision()));
        }
        if (this.notShortenPaths) {
            this.requests.add(0, new ArgumentRequest("-N"));
        }
        if (this.notRunModuleProgram) {
            this.requests.add(0, new ArgumentRequest("-n"));
        }
        if (this.getKeywordSubstitutionOptions() != null) {
            this.requests.add(new ArgumentRequest("-k" + this.getKeywordSubstitutionOptions()));
        }
        this.addArgumentRequests();
        this.requests.add(new DirectoryRequest(".", clientServices.getRepository()));
        this.requests.add(CommandRequest.EXPORT);
        try {
            clientServices.processRequests(this.requests);
            if (this.pruneDirectories) {
                this.pruneEmptyDirectories();
            }
            this.requests.clear();
        }
        catch (CommandException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new CommandException(ex2, ex2.getLocalizedMessage());
        }
        finally {
            this.removeAllCVSAdminFiles();
        }
    }
    
    private void removeAllCVSAdminFiles() {
        if (this.getExportDirectory() != null) {
            this.deleteCVSSubDirs(new File(this.getLocalDirectory(), this.getExportDirectory()));
        }
        else {
            final File file = new File(this.getLocalDirectory());
            final Iterator<Object> iterator = this.expandedModules.iterator();
            while (iterator.hasNext()) {
                this.deleteCVSSubDirs(new File(file.getAbsolutePath(), iterator.next().toString()));
            }
        }
    }
    
    private void deleteCVSSubDirs(final File file) {
        if (file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return;
            }
            for (int i = 0; i < listFiles.length; ++i) {
                if (listFiles[i].isDirectory()) {
                    if (listFiles[i].getName().equalsIgnoreCase("CVS")) {
                        final File[] listFiles2 = listFiles[i].listFiles();
                        for (int j = 0; j < listFiles2.length; ++j) {
                            listFiles2[j].delete();
                        }
                        listFiles[i].delete();
                    }
                    else {
                        this.deleteCVSSubDirs(listFiles[i]);
                    }
                }
            }
        }
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("export ");
        sb.append(this.getCVSArguments());
        if (this.modules != null && this.modules.size() > 0) {
            final Iterator<String> iterator = (Iterator<String>)this.modules.iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
                sb.append(' ');
            }
        }
        else {
            final String localMessage = CommandException.getLocalMessage("ExportCommand.moduleEmpty.text");
            sb.append(" ");
            sb.append(localMessage);
        }
        return sb.toString();
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer("");
        if (!this.isRecursive()) {
            sb.append("-l ");
        }
        if (this.isUseHeadIfNotFound()) {
            sb.append("-f ");
        }
        if (this.getExportByDate() != null) {
            sb.append("-D ");
            sb.append(this.getExportByDate());
            sb.append(" ");
        }
        if (this.getExportByRevision() != null) {
            sb.append("-r ");
            sb.append(this.getExportByRevision());
            sb.append(" ");
        }
        if (this.isPruneDirectories()) {
            sb.append("-P ");
        }
        if (this.isNotShortenPaths()) {
            sb.append("-N ");
        }
        if (this.isNotRunModuleProgram()) {
            sb.append("-n ");
        }
        if (this.getExportDirectory() != null) {
            sb.append("-d ");
            sb.append(this.getExportDirectory());
            sb.append(" ");
        }
        if (this.getKeywordSubstitutionOptions() != null) {
            sb.append("-k");
            sb.append(this.getKeywordSubstitutionOptions().toString());
            sb.append(" ");
        }
        return sb.toString();
    }
    
    public boolean setCVSCommand(final char c, final String exportDirectory) {
        if (c == 'k') {
            this.setKeywordSubstitutionOptions(KeywordSubstitutionOptions.findKeywordSubstOption(exportDirectory));
        }
        else if (c == 'r') {
            this.setExportByRevision(exportDirectory);
        }
        else if (c == 'f') {
            this.setUseHeadIfNotFound(true);
        }
        else if (c == 'D') {
            this.setExportByDate(exportDirectory);
        }
        else if (c == 'd') {
            this.setExportDirectory(exportDirectory);
        }
        else if (c == 'P') {
            this.setPruneDirectories(true);
        }
        else if (c == 'N') {
            this.setNotShortenPaths(true);
        }
        else if (c == 'n') {
            this.setNotRunModuleProgram(true);
        }
        else if (c == 'l') {
            this.setRecursive(false);
        }
        else {
            if (c != 'R') {
                return false;
            }
            this.setRecursive(true);
        }
        return true;
    }
    
    public void resetCVSCommand() {
        this.setModules(null);
        this.setKeywordSubstitutionOptions(null);
        this.setPruneDirectories(false);
        this.setRecursive(true);
        this.setExportByDate(null);
        this.setExportByRevision(null);
        this.setExportDirectory(null);
        this.setUseHeadIfNotFound(false);
        this.setNotShortenPaths(false);
        this.setNotRunModuleProgram(false);
    }
    
    public String getOptString() {
        return "k:r:D:NPlRnd:f";
    }
    
    public Builder createBuilder(final EventManager eventManager) {
        return new ExportBuilder(eventManager, this);
    }
    
    public void messageSent(final MessageEvent messageEvent) {
        super.messageSent(messageEvent);
        if (this.pruneDirectories && messageEvent.getMessage().indexOf(": Exporting ") > 0) {
            this.emptyDirectories.add(new File(this.getLocalDirectory(), messageEvent.getMessage().substring(21)));
        }
    }
    
    private boolean pruneEmptyDirectory(final File file) throws IOException {
        boolean pruneEmptyDirectory = true;
        final File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; ++i) {
                if (listFiles[i].isFile()) {
                    pruneEmptyDirectory = false;
                }
                else if (!listFiles[i].getName().equals("CVS")) {
                    pruneEmptyDirectory = this.pruneEmptyDirectory(listFiles[i]);
                }
                if (!pruneEmptyDirectory) {
                    break;
                }
            }
            if (pruneEmptyDirectory && new File(file, "CVS/Entries").exists()) {
                final File file2 = new File(file, "CVS");
                final File[] listFiles2 = file2.listFiles();
                for (int j = 0; j < listFiles2.length; ++j) {
                    listFiles2[j].delete();
                }
                file2.delete();
                file.delete();
            }
        }
        return pruneEmptyDirectory;
    }
    
    private void pruneEmptyDirectories() throws IOException {
        for (final File file : this.emptyDirectories) {
            if (file.exists()) {
                this.pruneEmptyDirectory(file);
            }
        }
        this.emptyDirectories.clear();
    }
    
    public String getExportByDate() {
        return this.exportByDate;
    }
    
    public void setExportByDate(final String exportByDate) {
        this.exportByDate = exportByDate;
    }
    
    public String getExportByRevision() {
        return this.exportByRevision;
    }
    
    public void setExportByRevision(final String exportByRevision) {
        this.exportByRevision = exportByRevision;
    }
    
    public String getExportDirectory() {
        return this.exportDirectory;
    }
    
    public void setExportDirectory(final String exportDirectory) {
        this.exportDirectory = exportDirectory;
    }
    
    public boolean isUseHeadIfNotFound() {
        return this.useHeadIfNotFound;
    }
    
    public void setUseHeadIfNotFound(final boolean useHeadIfNotFound) {
        this.useHeadIfNotFound = useHeadIfNotFound;
    }
    
    public boolean isNotShortenPaths() {
        return this.notShortenPaths;
    }
    
    public void setNotShortenPaths(final boolean notShortenPaths) {
        this.notShortenPaths = notShortenPaths;
    }
    
    public boolean isNotRunModuleProgram() {
        return this.notRunModuleProgram;
    }
    
    public void setNotRunModuleProgram(final boolean notRunModuleProgram) {
        this.notRunModuleProgram = notRunModuleProgram;
    }
}
