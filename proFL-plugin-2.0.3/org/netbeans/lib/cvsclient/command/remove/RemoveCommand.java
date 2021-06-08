// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.remove;

import java.io.IOException;
import org.netbeans.lib.cvsclient.util.BugLog;
import java.io.File;
import org.netbeans.lib.cvsclient.admin.Entry;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.request.Request;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.ClientServices;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.command.BasicCommand;

public class RemoveCommand extends BasicCommand
{
    private boolean deleteBeforeRemove;
    private boolean ignoreLocallyExistingFiles;
    
    public boolean isDeleteBeforeRemove() {
        return this.deleteBeforeRemove;
    }
    
    public void setDeleteBeforeRemove(final boolean deleteBeforeRemove) {
        this.deleteBeforeRemove = deleteBeforeRemove;
    }
    
    public boolean doesIgnoreLocallyExistingFiles() {
        return this.ignoreLocallyExistingFiles;
    }
    
    public boolean isIgnoreLocallyExistingFiles() {
        return this.ignoreLocallyExistingFiles;
    }
    
    public void setIgnoreLocallyExistingFiles(final boolean ignoreLocallyExistingFiles) {
        this.ignoreLocallyExistingFiles = ignoreLocallyExistingFiles;
    }
    
    public Builder createBuilder(final EventManager eventManager) {
        return new RemoveBuilder(eventManager, this);
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        if (this.files == null || this.files.length == 0) {
            throw new CommandException("No files have been specified for removal.", CommandException.getLocalMessage("RemoveCommand.noFilesSpecified", null));
        }
        clientServices.ensureConnection();
        if (this.isDeleteBeforeRemove()) {
            this.removeAll(this.files);
        }
        super.execute(clientServices, eventManager);
        try {
            this.addRequestForWorkingDirectory(clientServices);
            this.addArgumentRequests();
            this.addRequest(CommandRequest.REMOVE);
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
    
    protected void sendEntryAndModifiedRequests(final Entry entry, final File file) {
        super.sendEntryAndModifiedRequests(entry, this.isIgnoreLocallyExistingFiles() ? null : file);
        if (entry.getRevision().equals("0")) {
            try {
                this.clientServices.removeEntry(file);
            }
            catch (IOException ex) {
                BugLog.getInstance().showException(ex);
            }
        }
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("remove ");
        sb.append(this.getCVSArguments());
        final File[] files = this.getFiles();
        if (files != null) {
            for (int i = 0; i < files.length; ++i) {
                sb.append(files[i].getName() + " ");
            }
        }
        return sb.toString();
    }
    
    public boolean setCVSCommand(final char c, final String s) {
        if (c == 'l') {
            this.setRecursive(false);
        }
        else if (c == 'R') {
            this.setRecursive(true);
        }
        else {
            if (c != 'f') {
                return false;
            }
            this.setDeleteBeforeRemove(true);
        }
        return true;
    }
    
    private void removeAll(final File[] array) throws CommandException {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            final File file = array[i];
            if (file.exists() && file.isFile()) {
                if (!file.delete()) {
                    throw new CommandException("Cannot delete file " + file.getAbsolutePath(), CommandException.getLocalMessage("RemoveCommand.cannotDelete", new Object[] { file.getAbsolutePath() }));
                }
            }
            else if (this.isRecursive() && !file.getName().equalsIgnoreCase("CVS")) {
                this.removeAll(file.listFiles());
            }
        }
    }
    
    public String getOptString() {
        return "flR";
    }
    
    public void resetCVSCommand() {
        this.setRecursive(true);
        this.setDeleteBeforeRemove(false);
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer("");
        if (!this.isRecursive()) {
            sb.append("-l ");
        }
        if (this.isDeleteBeforeRemove()) {
            sb.append("-f ");
        }
        return sb.toString();
    }
}
