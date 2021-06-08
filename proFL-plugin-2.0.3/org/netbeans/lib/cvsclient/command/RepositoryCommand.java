// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command;

import java.io.IOException;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.request.ExpandModulesRequest;
import org.netbeans.lib.cvsclient.request.DirectoryRequest;
import org.netbeans.lib.cvsclient.request.RootRequest;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.event.ModuleExpansionEvent;
import java.util.Iterator;
import org.netbeans.lib.cvsclient.request.Request;
import org.netbeans.lib.cvsclient.request.ArgumentRequest;
import java.util.LinkedList;
import org.netbeans.lib.cvsclient.ClientServices;
import java.util.List;

public abstract class RepositoryCommand extends BuildableCommand
{
    protected List requests;
    protected ClientServices clientServices;
    private boolean recursive;
    protected final List modules;
    protected final List expandedModules;
    
    public RepositoryCommand() {
        this.requests = new LinkedList();
        this.recursive = true;
        this.modules = new LinkedList();
        this.expandedModules = new LinkedList();
    }
    
    public boolean isRecursive() {
        return this.recursive;
    }
    
    public void setRecursive(final boolean recursive) {
        this.recursive = recursive;
    }
    
    public void addModule(final String s) {
        this.modules.add(s);
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
    
    public void clearModules() {
        this.modules.clear();
    }
    
    protected final void addArgumentRequests() {
        if (this.expandedModules.size() == 0) {
            return;
        }
        final Iterator<String> iterator = this.expandedModules.iterator();
        while (iterator.hasNext()) {
            this.addRequest(new ArgumentRequest(iterator.next()));
        }
    }
    
    public final void moduleExpanded(final ModuleExpansionEvent moduleExpansionEvent) {
        this.expandedModules.add(moduleExpansionEvent.getModule());
    }
    
    public final void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        clientServices.ensureConnection();
        this.requests.clear();
        super.execute(clientServices, eventManager);
        this.clientServices = clientServices;
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
    
    protected abstract void postExpansionExecute(final ClientServices p0, final EventManager p1) throws CommandException, AuthenticationException;
    
    protected final void addRequest(final Request request) {
        this.requests.add(request);
    }
    
    protected final void addRequestForWorkingDirectory(final ClientServices clientServices) throws IOException {
        this.addRequest(new DirectoryRequest(".", clientServices.getRepositoryForDirectory(this.getLocalDirectory())));
    }
    
    protected final void addArgumentRequest(final boolean b, final String s) {
        if (!b) {
            return;
        }
        this.addRequest(new ArgumentRequest(s));
    }
    
    protected final void appendModuleArguments(final StringBuffer sb) {
        if (this.expandedModules.size() == 0) {
            return;
        }
        final Iterator<String> iterator = (Iterator<String>)this.expandedModules.iterator();
        sb.append(iterator.next());
        while (iterator.hasNext()) {
            sb.append(' ');
            sb.append(iterator.next());
        }
    }
}
