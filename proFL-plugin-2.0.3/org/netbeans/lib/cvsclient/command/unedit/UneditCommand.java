// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.unedit;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import org.netbeans.lib.cvsclient.file.FileUtils;
import org.netbeans.lib.cvsclient.command.edit.EditCommand;
import org.netbeans.lib.cvsclient.event.TerminationEvent;
import java.io.IOException;
import org.netbeans.lib.cvsclient.request.NotifyRequest;
import org.netbeans.lib.cvsclient.admin.Entry;
import java.io.File;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import java.io.EOFException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.request.Request;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.ClientServices;
import org.netbeans.lib.cvsclient.command.Watch;
import org.netbeans.lib.cvsclient.command.BasicCommand;

public class UneditCommand extends BasicCommand
{
    private Watch temporaryWatch;
    
    public UneditCommand() {
        this.resetCVSCommand();
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        clientServices.ensureConnection();
        try {
            super.execute(clientServices, eventManager);
            this.addRequestForWorkingDirectory(clientServices);
            this.addRequest(CommandRequest.NOOP);
            clientServices.processRequests(this.requests);
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
    
    protected void addRequestForFile(final File file, final Entry entry) {
        this.requests.add(new NotifyRequest(file, "U", Watch.getWatchString(this.getTemporaryWatch())));
        try {
            this.uneditFile(file);
        }
        catch (IOException ex) {}
    }
    
    public void commandTerminated(final TerminationEvent terminationEvent) {
        if (this.builder != null) {
            this.builder.outputDone();
        }
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("unedit ");
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
    }
    
    public String getCVSArguments() {
        final StringBuffer sb = new StringBuffer();
        if (!this.isRecursive()) {
            sb.append("-l ");
        }
        return sb.toString();
    }
    
    public Watch getTemporaryWatch() {
        return this.temporaryWatch;
    }
    
    public void setTemporaryWatch(final Watch temporaryWatch) {
        this.temporaryWatch = temporaryWatch;
    }
    
    private void uneditFile(final File file) throws IOException {
        this.removeBaserevEntry(file);
        EditCommand.getEditBackupFile(file).delete();
        FileUtils.setFileReadOnly(file, true);
    }
    
    private void removeBaserevEntry(final File file) throws IOException {
        final File file2 = new File(file.getParentFile(), "CVS/Baserev");
        final File file3 = new File(file2.getAbsolutePath() + '~');
        BufferedReader bufferedReader = null;
        Writer writer = null;
        final String string = 'B' + file.getName() + '/';
        try {
            writer = new BufferedWriter(new FileWriter(file3));
            bufferedReader = new BufferedReader(new FileReader(file2));
            for (String str = bufferedReader.readLine(); str != null; str = bufferedReader.readLine()) {
                if (!str.startsWith(string)) {
                    writer.write(str);
                    ((BufferedWriter)writer).newLine();
                }
            }
        }
        catch (FileNotFoundException ex) {}
        finally {
            if (writer != null) {
                try {
                    ((BufferedWriter)writer).close();
                }
                catch (IOException ex2) {}
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException ex3) {}
            }
        }
        file2.delete();
        if (file3.length() > 0L) {
            file3.renameTo(file2);
        }
        else {
            file3.delete();
        }
    }
}
