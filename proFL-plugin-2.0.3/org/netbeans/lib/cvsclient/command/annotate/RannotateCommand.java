// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.annotate;

import org.netbeans.lib.cvsclient.event.TerminationEvent;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.request.CommandRequest;
import org.netbeans.lib.cvsclient.event.ModuleExpansionEvent;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import java.util.Iterator;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.request.ExpandModulesRequest;
import org.netbeans.lib.cvsclient.request.DirectoryRequest;
import org.netbeans.lib.cvsclient.request.ArgumentRequest;
import org.netbeans.lib.cvsclient.request.RootRequest;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.ClientServices;
import java.util.LinkedList;
import java.util.List;
import org.netbeans.lib.cvsclient.command.BasicCommand;

public class RannotateCommand extends BasicCommand
{
    private final List modules;
    private final List expandedModules;
    private boolean useHeadIfNotFound;
    private String annotateByDate;
    private String annotateByRevision;
    private boolean headerAndDescOnly;
    
    public RannotateCommand() {
        this.modules = new LinkedList();
        this.expandedModules = new LinkedList();
        this.resetCVSCommand();
    }
    
    public void setModule(final String s) {
        this.modules.add(s);
    }
    
    public void clearModules() {
        this.modules.clear();
    }
    
    public void setModules(final String[] array) {
        this.clearModules();
        if (array == null) {
            return;
        }
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
        this.setModules(this.expandedModules.toArray(new String[this.expandedModules.size()]));
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        clientServices.ensureConnection();
        this.requests = new LinkedList();
        if (clientServices.isFirstCommand()) {
            this.requests.add(new RootRequest(clientServices.getRepository()));
        }
        final Iterator<String> iterator = this.modules.iterator();
        while (iterator.hasNext()) {
            this.requests.add(new ArgumentRequest(iterator.next()));
        }
        this.expandedModules.clear();
        this.requests.add(new DirectoryRequest(".", clientServices.getRepository()));
        this.requests.add(new ExpandModulesRequest());
        try {
            clientServices.processRequests(this.requests);
        }
        catch (CommandException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new CommandException(ex2, ex2.getLocalizedMessage());
        }
        this.requests.clear();
        this.postExpansionExecute(clientServices, eventManager);
    }
    
    public void moduleExpanded(final ModuleExpansionEvent moduleExpansionEvent) {
        this.expandedModules.add(moduleExpansionEvent.getModule());
    }
    
    private void postExpansionExecute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        super.execute(clientServices, eventManager);
        if (!this.isRecursive()) {
            this.requests.add(1, new ArgumentRequest("-l"));
        }
        if (this.useHeadIfNotFound) {
            this.requests.add(1, new ArgumentRequest("-f"));
        }
        if (this.annotateByDate != null && this.annotateByDate.length() > 0) {
            this.requests.add(1, new ArgumentRequest("-D"));
            this.requests.add(2, new ArgumentRequest(this.getAnnotateByDate()));
        }
        if (this.annotateByRevision != null && this.annotateByRevision.length() > 0) {
            this.requests.add(1, new ArgumentRequest("-r"));
            this.requests.add(2, new ArgumentRequest(this.getAnnotateByRevision()));
        }
        final Iterator<String> iterator = this.modules.iterator();
        while (iterator.hasNext()) {
            this.requests.add(new ArgumentRequest(iterator.next()));
        }
        this.requests.add(new DirectoryRequest(".", clientServices.getRepository()));
        this.requests.add(CommandRequest.RANNOTATE);
        try {
            clientServices.processRequests(this.requests);
            this.requests.clear();
        }
        catch (CommandException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new CommandException(ex2, ex2.getLocalizedMessage());
        }
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer("rannotate ");
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
        if (this.getAnnotateByRevision() != null) {
            sb.append("-r ");
            sb.append(this.getAnnotateByRevision());
            sb.append(" ");
        }
        if (this.getAnnotateByDate() != null) {
            sb.append("-D ");
            sb.append(this.getAnnotateByDate());
            sb.append(" ");
        }
        if (this.isUseHeadIfNotFound()) {
            sb.append("-f ");
        }
        return sb.toString();
    }
    
    public boolean setCVSCommand(final char c, final String s) {
        if (c == 'R') {
            this.setRecursive(true);
        }
        else if (c == 'l') {
            this.setRecursive(false);
        }
        else if (c == 'r') {
            this.setAnnotateByRevision(s);
        }
        else if (c == 'D') {
            this.setAnnotateByDate(s);
        }
        else {
            if (c != 'f') {
                return false;
            }
            this.setUseHeadIfNotFound(true);
        }
        return true;
    }
    
    public void resetCVSCommand() {
        this.setRecursive(true);
        this.setAnnotateByDate(null);
        this.setAnnotateByRevision(null);
        this.setUseHeadIfNotFound(false);
    }
    
    public String getOptString() {
        return "Rlr:D:f";
    }
    
    public Builder createBuilder(final EventManager eventManager) {
        return new AnnotateBuilder(eventManager, this);
    }
    
    public void commandTerminated(final TerminationEvent terminationEvent) {
        if (this.builder != null) {
            this.builder.outputDone();
        }
    }
    
    public boolean isUseHeadIfNotFound() {
        return this.useHeadIfNotFound;
    }
    
    public void setUseHeadIfNotFound(final boolean useHeadIfNotFound) {
        this.useHeadIfNotFound = useHeadIfNotFound;
    }
    
    public String getAnnotateByDate() {
        return this.annotateByDate;
    }
    
    public void setAnnotateByDate(final String annotateByDate) {
        this.annotateByDate = annotateByDate;
    }
    
    public String getAnnotateByRevision() {
        return this.annotateByRevision;
    }
    
    public void setAnnotateByRevision(final String annotateByRevision) {
        this.annotateByRevision = annotateByRevision;
    }
}
