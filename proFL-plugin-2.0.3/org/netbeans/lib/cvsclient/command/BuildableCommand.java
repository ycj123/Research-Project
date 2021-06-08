// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command;

import org.netbeans.lib.cvsclient.event.TerminationEvent;
import java.io.UnsupportedEncodingException;
import org.netbeans.lib.cvsclient.event.EnhancedMessageEvent;
import org.netbeans.lib.cvsclient.event.MessageEvent;
import org.netbeans.lib.cvsclient.event.BinaryMessageEvent;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.ClientServices;

public abstract class BuildableCommand extends Command
{
    protected Builder builder;
    private final StringBuffer taggedLineBuffer;
    private boolean builderSet;
    
    public BuildableCommand() {
        this.taggedLineBuffer = new StringBuffer();
    }
    
    public void execute(final ClientServices clientServices, final EventManager eventManager) throws CommandException, AuthenticationException {
        super.execute(clientServices, eventManager);
        if (this.builder == null && !this.isBuilderSet()) {
            this.builder = this.createBuilder(eventManager);
        }
    }
    
    public Builder createBuilder(final EventManager eventManager) {
        return null;
    }
    
    public void messageSent(final BinaryMessageEvent binaryMessageEvent) {
        super.messageSent(binaryMessageEvent);
        if (this.builder == null) {
            return;
        }
        if (this.builder instanceof BinaryBuilder) {
            ((BinaryBuilder)this.builder).parseBytes(binaryMessageEvent.getMessage(), binaryMessageEvent.getMessageLength());
        }
    }
    
    public void messageSent(final MessageEvent messageEvent) {
        super.messageSent(messageEvent);
        if (this.builder == null) {
            return;
        }
        if (messageEvent instanceof EnhancedMessageEvent) {
            final EnhancedMessageEvent enhancedMessageEvent = (EnhancedMessageEvent)messageEvent;
            this.builder.parseEnhancedMessage(enhancedMessageEvent.getKey(), enhancedMessageEvent.getValue());
            return;
        }
        if (messageEvent.isTagged()) {
            final String taggedMessage = MessageEvent.parseTaggedMessage(this.taggedLineBuffer, messageEvent.getMessage());
            if (taggedMessage != null) {
                this.builder.parseLine(taggedMessage, false);
                this.taggedLineBuffer.setLength(0);
            }
        }
        else {
            if (this.taggedLineBuffer.length() > 0) {
                this.builder.parseLine(this.taggedLineBuffer.toString(), false);
                this.taggedLineBuffer.setLength(0);
            }
            if (this.builder instanceof PipedFilesBuilder && !messageEvent.isError()) {
                try {
                    this.builder.parseLine(new String(messageEvent.getRawData(), "ISO-8859-1"), messageEvent.isError());
                }
                catch (UnsupportedEncodingException ex) {
                    assert false;
                }
            }
            else {
                this.builder.parseLine(messageEvent.getMessage(), messageEvent.isError());
            }
        }
    }
    
    protected boolean isBuilderSet() {
        return this.builderSet;
    }
    
    public void setBuilder(final Builder builder) {
        this.builder = builder;
        this.builderSet = true;
    }
    
    public void commandTerminated(final TerminationEvent terminationEvent) {
        if (this.builder == null) {
            return;
        }
        if (this.taggedLineBuffer.length() > 0) {
            this.builder.parseLine(this.taggedLineBuffer.toString(), false);
            this.taggedLineBuffer.setLength(0);
        }
        this.builder.outputDone();
    }
}
