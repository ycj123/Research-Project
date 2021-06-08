// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command;

import java.io.File;
import org.netbeans.lib.cvsclient.event.ModuleExpansionEvent;
import org.netbeans.lib.cvsclient.event.TerminationEvent;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import org.netbeans.lib.cvsclient.event.FileUpdatedEvent;
import org.netbeans.lib.cvsclient.event.FileRemovedEvent;
import org.netbeans.lib.cvsclient.event.FileToRemoveEvent;
import org.netbeans.lib.cvsclient.event.FileAddedEvent;
import org.netbeans.lib.cvsclient.event.BinaryMessageEvent;
import org.netbeans.lib.cvsclient.response.ErrorResponse;
import org.netbeans.lib.cvsclient.event.MessageEvent;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.ClientServices;
import org.netbeans.lib.cvsclient.event.CVSListener;

public abstract class Command implements CVSListener, Cloneable
{
    protected String localDirectory;
    private GlobalOptions globalOptions;
    private boolean failed;
    private String displayName;
    
    public Command() {
        this.failed = false;
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, CommandAbortedException, AuthenticationException {
        this.setLocalDirectory(clientServices.getLocalPath());
        this.globalOptions = clientServices.getGlobalOptions();
    }
    
    public abstract String getCVSCommand();
    
    public abstract String getCVSArguments();
    
    public abstract boolean setCVSCommand(final char p0, final String p1);
    
    public abstract void resetCVSCommand();
    
    public abstract String getOptString();
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public boolean hasFailed() {
        return this.failed;
    }
    
    public void messageSent(final MessageEvent messageEvent) {
        if ((messageEvent.isError() && messageEvent.getSource() instanceof ErrorResponse) || messageEvent.getSource() == this) {
            this.failed = true;
        }
    }
    
    public void messageSent(final BinaryMessageEvent binaryMessageEvent) {
    }
    
    public void fileAdded(final FileAddedEvent fileAddedEvent) {
    }
    
    public void fileToRemove(final FileToRemoveEvent fileToRemoveEvent) {
    }
    
    public void fileRemoved(final FileRemovedEvent fileRemovedEvent) {
    }
    
    public void fileUpdated(final FileUpdatedEvent fileUpdatedEvent) {
    }
    
    public void fileInfoGenerated(final FileInfoEvent fileInfoEvent) {
    }
    
    public void commandTerminated(final TerminationEvent terminationEvent) {
    }
    
    public void moduleExpanded(final ModuleExpansionEvent moduleExpansionEvent) {
    }
    
    public final String getLocalDirectory() {
        return this.localDirectory;
    }
    
    public final String getLocalPath() {
        return this.localDirectory;
    }
    
    public final GlobalOptions getGlobalOptions() {
        return this.globalOptions;
    }
    
    public final String getRelativeToLocalPathInUnixStyle(final File file) {
        final String absolutePath = file.getAbsolutePath();
        final int beginIndex = this.localDirectory.length() + 1;
        if (beginIndex >= absolutePath.length()) {
            return ".";
        }
        return absolutePath.substring(beginIndex).replace('\\', '/');
    }
    
    protected final void setLocalDirectory(final String localDirectory) {
        this.localDirectory = localDirectory;
    }
    
    protected static final String getTrimmedString(String trim) {
        if (trim == null) {
            return null;
        }
        trim = trim.trim();
        if (trim.length() == 0) {
            return null;
        }
        return trim;
    }
    
    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
}
