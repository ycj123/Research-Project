// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.edit;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import org.netbeans.lib.cvsclient.file.FileUtils;
import org.netbeans.lib.cvsclient.event.TerminationEvent;
import java.io.IOException;
import org.netbeans.lib.cvsclient.request.NotifyRequest;
import org.netbeans.lib.cvsclient.admin.Entry;
import java.io.EOFException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.request.Request;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import org.netbeans.lib.cvsclient.event.EventManager;
import java.io.File;
import org.netbeans.lib.cvsclient.ClientServices;
import org.netbeans.lib.cvsclient.command.Watch;
import org.netbeans.lib.cvsclient.command.BasicCommand;

public class EditCommand extends BasicCommand
{
    private boolean checkThatUnedited;
    private boolean forceEvenIfEdited;
    private Watch temporaryWatch;
    private transient ClientServices clientServices;
    
    public static File getEditBackupFile(final File file) {
        return new File(file.getParent(), "CVS/Base/" + file.getName());
    }
    
    public EditCommand() {
        this.resetCVSCommand();
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException {
        this.clientServices = clientServices;
        try {
            clientServices.ensureConnection();
            super.execute(clientServices, eventManager);
            this.addArgumentRequest(this.isCheckThatUnedited(), "-c");
            this.addArgumentRequest(this.isForceEvenIfEdited(), "-f");
            this.addRequestForWorkingDirectory(clientServices);
            this.addRequest(CommandRequest.NOOP);
            clientServices.processRequests(this.requests);
        }
        catch (AuthenticationException ex4) {}
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
            this.clientServices = null;
        }
    }
    
    protected void addRequestForFile(final File file, final Entry entry) {
        this.requests.add(new NotifyRequest(file, "E", Watch.getWatchString(this.getTemporaryWatch())));
        try {
            this.editFile(this.clientServices, file);
        }
        catch (IOException ex) {}
    }
    
    public void commandTerminated(final TerminationEvent terminationEvent) {
        if (this.builder != null) {
            this.builder.outputDone();
        }
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("edit ");
        sb.append(this.getCVSArguments());
        this.appendFileArguments(sb);
        return sb.toString();
    }
    
    public boolean setCVSCommand(final char c, final String s) {
        if (c == 'R') {
            this.setRecursive(true);
        }
        else {
            if (c != 'l') {
                return false;
            }
            this.setRecursive(false);
        }
        return true;
    }
    
    public String getOptString() {
        return "Rl";
    }
    
    public void resetCVSCommand() {
        this.setRecursive(true);
        this.setCheckThatUnedited(false);
        this.setForceEvenIfEdited(true);
        this.setTemporaryWatch(null);
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer();
        if (!this.isRecursive()) {
            sb.append("-l ");
        }
        return sb.toString();
    }
    
    public boolean isCheckThatUnedited() {
        return this.checkThatUnedited;
    }
    
    public void setCheckThatUnedited(final boolean checkThatUnedited) {
        this.checkThatUnedited = checkThatUnedited;
    }
    
    public boolean isForceEvenIfEdited() {
        return this.forceEvenIfEdited;
    }
    
    public void setForceEvenIfEdited(final boolean forceEvenIfEdited) {
        this.forceEvenIfEdited = forceEvenIfEdited;
    }
    
    public Watch getTemporaryWatch() {
        return this.temporaryWatch;
    }
    
    public void setTemporaryWatch(final Watch temporaryWatch) {
        this.temporaryWatch = temporaryWatch;
    }
    
    private void editFile(final ClientServices clientServices, final File file) throws IOException {
        this.addBaserevEntry(clientServices, file);
        FileUtils.copyFile(file, getEditBackupFile(file));
        FileUtils.setFileReadOnly(file, false);
    }
    
    private void addBaserevEntry(final ClientServices clientServices, final File file) throws IOException {
        final Entry entry = clientServices.getEntry(file);
        if (entry == null || entry.getRevision() == null || entry.isNewUserFile() || entry.isUserFileToBeRemoved()) {
            throw new IllegalArgumentException("File does not have an Entry or Entry is invalid!");
        }
        final File file2 = new File(file.getParentFile(), "CVS/Baserev");
        final File file3 = new File(file2.getAbsolutePath() + '~');
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        boolean b = true;
        boolean b2 = true;
        final String string = 'B' + file.getName() + '/';
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file3));
            b2 = false;
            bufferedReader = new BufferedReader(new FileReader(file2));
            for (String str = bufferedReader.readLine(); str != null; str = bufferedReader.readLine()) {
                if (str.startsWith(string)) {
                    b = false;
                }
                b2 = true;
                bufferedWriter.write(str);
                bufferedWriter.newLine();
                b2 = false;
            }
        }
        catch (IOException ex) {
            if (b2) {
                throw ex;
            }
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException ex2) {}
            }
            if (bufferedWriter != null) {
                try {
                    if (b && !b2) {
                        bufferedWriter.write(string + entry.getRevision() + '/');
                        bufferedWriter.newLine();
                    }
                    try {
                        bufferedWriter.close();
                    }
                    catch (IOException ex3) {}
                }
                finally {
                    try {
                        bufferedWriter.close();
                    }
                    catch (IOException ex4) {}
                }
            }
        }
        file2.delete();
        file3.renameTo(file2);
    }
}
