// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.checkout;

import org.netbeans.lib.cvsclient.event.MessageEvent;
import java.io.IOException;
import org.netbeans.lib.cvsclient.command.update.UpdateBuilder;
import org.netbeans.lib.cvsclient.command.BuildableCommand;
import org.netbeans.lib.cvsclient.command.PipedFilesBuilder;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.event.ModuleExpansionEvent;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.request.ExpandModulesRequest;
import org.netbeans.lib.cvsclient.request.DirectoryRequest;
import java.io.EOFException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import org.netbeans.lib.cvsclient.request.ArgumentRequest;
import org.netbeans.lib.cvsclient.request.RootRequest;
import org.netbeans.lib.cvsclient.event.EventManager;
import java.util.Iterator;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import org.netbeans.lib.cvsclient.ClientServices;
import org.netbeans.lib.cvsclient.command.KeywordSubstitutionOptions;
import java.util.List;
import java.util.Set;
import org.netbeans.lib.cvsclient.command.TemporaryFileCreator;
import org.netbeans.lib.cvsclient.command.BasicCommand;

public class CheckoutCommand extends BasicCommand implements TemporaryFileCreator
{
    private static final String UPDATING = ": Updating ";
    private final Set emptyDirectories;
    private final List modules;
    private final List expandedModules;
    private boolean showModules;
    private boolean showModulesWithStatus;
    private boolean pipeToOutput;
    private boolean pruneDirectories;
    private boolean resetStickyOnes;
    private boolean useHeadIfNotFound;
    private boolean notShortenPaths;
    private boolean isNotShortenSet;
    private String checkoutByDate;
    private String checkoutByRevision;
    private String checkoutDirectory;
    private KeywordSubstitutionOptions keywordSubst;
    private boolean notRunModuleProgram;
    private ClientServices client;
    
    public CheckoutCommand(final boolean recursive, final String[] modules) {
        this.emptyDirectories = new HashSet();
        this.modules = new LinkedList();
        this.expandedModules = new LinkedList();
        this.resetCVSCommand();
        this.setRecursive(recursive);
        this.setModules(modules);
    }
    
    public CheckoutCommand(final boolean recursive, final String module) {
        this.emptyDirectories = new HashSet();
        this.modules = new LinkedList();
        this.expandedModules = new LinkedList();
        this.resetCVSCommand();
        this.setRecursive(recursive);
        this.setModule(module);
    }
    
    public CheckoutCommand() {
        this.emptyDirectories = new HashSet();
        this.modules = new LinkedList();
        this.expandedModules = new LinkedList();
        this.resetCVSCommand();
        this.setRecursive(true);
    }
    
    public void setModule(final String s) {
        this.modules.add(s);
    }
    
    public void clearModules() {
        this.modules.clear();
    }
    
    public void setModules(final String[] array) {
        this.clearModules();
        for (int i = 0; i < array.length; ++i) {
            this.modules.add(array[i]);
        }
    }
    
    public String[] getModules() {
        return this.modules.toArray(new String[this.modules.size()]);
    }
    
    private void processExistingModules(final String s) {
        if (this.expandedModules.size() == 0) {
            return;
        }
        final ArrayList list = new ArrayList<File>(this.expandedModules.size());
        for (final String child : this.expandedModules) {
            if (child.equals(".")) {
                list.add(new File(s));
                break;
            }
            final File file = new File(s, child);
            File parentFile;
            if (file.isFile()) {
                parentFile = file.getParentFile();
            }
            else {
                parentFile = file;
            }
            if (!new File(parentFile, "CVS/Repository").exists()) {
                continue;
            }
            list.add(file);
        }
        this.setFiles((File[])list.toArray(new File[list.size()]));
    }
    
    public void execute(final ClientServices client, final EventManager eventManager) throws CommandException, AuthenticationException {
        client.ensureConnection();
        this.client = client;
        try {
            this.requests = new LinkedList();
            if (client.isFirstCommand()) {
                this.requests.add(new RootRequest(client.getRepository()));
            }
            if (this.showModules || this.showModulesWithStatus) {
                if (this.builder == null && !this.isBuilderSet()) {
                    this.builder = this.createBuilder(eventManager);
                }
                if (this.showModules) {
                    this.requests.add(new ArgumentRequest("-c"));
                }
                if (this.showModulesWithStatus) {
                    this.requests.add(new ArgumentRequest("-s"));
                }
                this.requests.add(CommandRequest.CHECKOUT);
                try {
                    client.processRequests(this.requests);
                    this.requests.clear();
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
                return;
            }
            final Iterator<String> iterator = this.modules.iterator();
            while (iterator.hasNext()) {
                this.requests.add(new ArgumentRequest(iterator.next()));
            }
            this.expandedModules.clear();
            this.requests.add(new DirectoryRequest(".", client.getRepository()));
            this.requests.add(new RootRequest(client.getRepository()));
            this.requests.add(new ExpandModulesRequest());
            try {
                client.processRequests(this.requests);
            }
            catch (CommandException ex4) {
                throw ex4;
            }
            catch (Exception ex5) {
                throw new CommandException(ex5, ex5.getLocalizedMessage());
            }
            this.requests.clear();
            this.postExpansionExecute(client, eventManager);
        }
        finally {
            this.client = null;
        }
    }
    
    protected boolean assumeLocalPathWhenUnspecified() {
        return false;
    }
    
    public void moduleExpanded(final ModuleExpansionEvent moduleExpansionEvent) {
        this.expandedModules.add(moduleExpansionEvent.getModule());
    }
    
    private void postExpansionExecute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        this.processExistingModules(clientServices.getLocalPath());
        super.execute(clientServices, eventManager);
        final int size = this.requests.size();
        if (!this.isRecursive()) {
            this.requests.add(0, new ArgumentRequest("-l"));
        }
        if (this.pipeToOutput) {
            this.requests.add(0, new ArgumentRequest("-p"));
        }
        if (this.resetStickyOnes) {
            this.requests.add(0, new ArgumentRequest("-A"));
        }
        if (this.useHeadIfNotFound) {
            this.requests.add(0, new ArgumentRequest("-f"));
        }
        if (this.isNotShortenPaths()) {
            this.requests.add(0, new ArgumentRequest("-N"));
        }
        if (this.notRunModuleProgram) {
            this.requests.add(0, new ArgumentRequest("-n"));
        }
        if (this.checkoutByDate != null && this.checkoutByDate.length() > 0) {
            this.requests.add(0, new ArgumentRequest("-D"));
            this.requests.add(1, new ArgumentRequest(this.getCheckoutByDate()));
        }
        if (this.checkoutByRevision != null && this.checkoutByRevision.length() > 0) {
            this.requests.add(0, new ArgumentRequest("-r"));
            this.requests.add(1, new ArgumentRequest(this.getCheckoutByRevision()));
        }
        if (this.checkoutDirectory != null && !this.checkoutDirectory.equals("")) {
            this.requests.add(0, new ArgumentRequest("-d"));
            this.requests.add(1, new ArgumentRequest(this.getCheckoutDirectory()));
        }
        if (this.getKeywordSubst() != null) {
            this.requests.add(0, new ArgumentRequest("-k" + this.getKeywordSubst()));
        }
        int n = this.requests.size() - size;
        this.requests.add(n++, new ArgumentRequest("--"));
        final Iterator<String> iterator = this.modules.iterator();
        while (iterator.hasNext()) {
            this.requests.add(n++, new ArgumentRequest(iterator.next()));
        }
        this.requests.add(new DirectoryRequest(".", clientServices.getRepository()));
        this.requests.add(CommandRequest.CHECKOUT);
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
    }
    
    public boolean isShowModules() {
        return this.showModules;
    }
    
    public void setShowModules(final boolean showModules) {
        this.showModules = showModules;
    }
    
    public boolean isShowModulesWithStatus() {
        return this.showModulesWithStatus;
    }
    
    public void setShowModulesWithStatus(final boolean showModulesWithStatus) {
        this.showModulesWithStatus = showModulesWithStatus;
    }
    
    public void setPruneDirectories(final boolean pruneDirectories) {
        this.pruneDirectories = pruneDirectories;
    }
    
    public boolean getPruneDirectories() {
        return this.pruneDirectories;
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
    
    public boolean isNotShortenPaths() {
        return this.notShortenPaths || (!this.isNotShortenSet && this.checkoutDirectory == null);
    }
    
    public void setNotShortenPaths(final boolean notShortenPaths) {
        this.notShortenPaths = notShortenPaths;
        this.isNotShortenSet = true;
    }
    
    public boolean isNotRunModuleProgram() {
        return this.notRunModuleProgram;
    }
    
    public void setNotRunModuleProgram(final boolean notRunModuleProgram) {
        this.notRunModuleProgram = notRunModuleProgram;
    }
    
    public String getCheckoutByDate() {
        return this.checkoutByDate;
    }
    
    public void setCheckoutByDate(final String checkoutByDate) {
        this.checkoutByDate = checkoutByDate;
    }
    
    public String getCheckoutByRevision() {
        return this.checkoutByRevision;
    }
    
    public void setCheckoutByRevision(final String checkoutByRevision) {
        this.checkoutByRevision = checkoutByRevision;
    }
    
    public String getCheckoutDirectory() {
        return this.checkoutDirectory;
    }
    
    public void setCheckoutDirectory(final String checkoutDirectory) {
        this.checkoutDirectory = checkoutDirectory;
    }
    
    public KeywordSubstitutionOptions getKeywordSubst() {
        return this.keywordSubst;
    }
    
    public void setKeywordSubst(final KeywordSubstitutionOptions keywordSubst) {
        this.keywordSubst = keywordSubst;
    }
    
    public Builder createBuilder(final EventManager eventManager) {
        if (this.isShowModules() || this.isShowModulesWithStatus()) {
            return new ModuleListBuilder(eventManager, this);
        }
        if (this.isPipeToOutput()) {
            return new PipedFilesBuilder(eventManager, this, this);
        }
        return new UpdateBuilder(eventManager, this.getLocalDirectory());
    }
    
    public File createTempFile(final String s) throws IOException {
        return File.createTempFile("cvs", ".dff", this.getGlobalOptions().getTempDir());
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("checkout ");
        sb.append(this.getCVSArguments());
        if (!this.isShowModules() && !this.isShowModulesWithStatus()) {
            final Iterator<String> iterator = this.modules.iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
                sb.append(' ');
            }
        }
        return sb.toString();
    }
    
    public boolean setCVSCommand(final char c, final String checkoutDirectory) {
        if (c == 'c') {
            this.setShowModules(true);
        }
        else if (c == 's') {
            this.setShowModulesWithStatus(true);
        }
        else if (c == 'p') {
            this.setPipeToOutput(true);
        }
        else if (c == 'R') {
            this.setRecursive(true);
        }
        else if (c == 'l') {
            this.setRecursive(false);
        }
        else if (c == 'A') {
            this.setResetStickyOnes(true);
        }
        else if (c == 'f') {
            this.setUseHeadIfNotFound(true);
        }
        else if (c == 'P') {
            this.setPruneDirectories(true);
        }
        else if (c == 'D') {
            this.setCheckoutByDate(checkoutDirectory.trim());
        }
        else if (c == 'r') {
            this.setCheckoutByRevision(checkoutDirectory.trim());
        }
        else if (c == 'd') {
            this.setCheckoutDirectory(checkoutDirectory);
        }
        else if (c == 'N') {
            this.setNotShortenPaths(true);
        }
        else if (c == 'n') {
            this.setNotRunModuleProgram(true);
        }
        else {
            if (c != 'k') {
                return false;
            }
            this.setKeywordSubst(KeywordSubstitutionOptions.findKeywordSubstOption(checkoutDirectory));
        }
        return true;
    }
    
    public String getOptString() {
        return "cnpslNPRAD:r:fk:d:";
    }
    
    public void resetCVSCommand() {
        this.setShowModules(false);
        this.setShowModulesWithStatus(false);
        this.setPipeToOutput(false);
        this.setRecursive(true);
        this.setResetStickyOnes(false);
        this.setUseHeadIfNotFound(false);
        this.setCheckoutByDate(null);
        this.setCheckoutByRevision(null);
        this.setKeywordSubst(null);
        this.setPruneDirectories(false);
        this.setNotShortenPaths(false);
        this.setNotRunModuleProgram(this.isNotShortenSet = false);
        this.setCheckoutDirectory(null);
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer("");
        if (this.isShowModules()) {
            sb.append("-c ");
        }
        if (this.isShowModulesWithStatus()) {
            sb.append("-s ");
        }
        if (this.isPipeToOutput()) {
            sb.append("-p ");
        }
        if (!this.isRecursive()) {
            sb.append("-l ");
        }
        if (this.isResetStickyOnes()) {
            sb.append("-A ");
        }
        if (this.isUseHeadIfNotFound()) {
            sb.append("-f ");
        }
        if (this.getPruneDirectories()) {
            sb.append("-P ");
        }
        if (this.isNotShortenPaths()) {
            sb.append("-N ");
        }
        if (this.isNotRunModuleProgram()) {
            sb.append("-n ");
        }
        if (this.getKeywordSubst() != null) {
            sb.append("-k");
            sb.append(this.getKeywordSubst());
            sb.append(' ');
        }
        if (this.getCheckoutByRevision() != null && this.getCheckoutByRevision().length() > 0) {
            sb.append("-r ");
            sb.append(this.getCheckoutByRevision());
            sb.append(' ');
        }
        if (this.getCheckoutByDate() != null && this.getCheckoutByDate().length() > 0) {
            sb.append("-D ");
            sb.append(this.getCheckoutByDate());
            sb.append(' ');
        }
        if (this.getCheckoutDirectory() != null) {
            sb.append("-d ");
            sb.append(this.getCheckoutDirectory());
            sb.append(" ");
        }
        return sb.toString();
    }
    
    public void messageSent(final MessageEvent messageEvent) {
        super.messageSent(messageEvent);
        if (this.pruneDirectories && messageEvent.getMessage().indexOf(": Updating ") > 0) {
            this.emptyDirectories.add(new File(this.getLocalDirectory(), messageEvent.getMessage().substring(messageEvent.getMessage().indexOf(": Updating ") + ": Updating ".length())));
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
                this.client.removeEntry(file);
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
}
