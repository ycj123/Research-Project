// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine;

import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import org.netbeans.lib.cvsclient.event.BinaryMessageEvent;
import org.netbeans.lib.cvsclient.event.EnhancedMessageEvent;
import org.netbeans.lib.cvsclient.event.MessageEvent;
import java.io.PrintStream;
import org.netbeans.lib.cvsclient.event.CVSAdapter;

public class BasicListener extends CVSAdapter
{
    private final StringBuffer taggedLine;
    private PrintStream stdout;
    private PrintStream stderr;
    
    public BasicListener() {
        this(System.out, System.err);
    }
    
    public BasicListener(final PrintStream stdout, final PrintStream stderr) {
        this.taggedLine = new StringBuffer();
        this.stdout = stdout;
        this.stderr = stderr;
    }
    
    public void messageSent(final MessageEvent messageEvent) {
        final String message = messageEvent.getMessage();
        if (messageEvent instanceof EnhancedMessageEvent) {
            return;
        }
        final PrintStream printStream = messageEvent.isError() ? this.stderr : this.stdout;
        if (messageEvent.isTagged()) {
            final String taggedMessage = MessageEvent.parseTaggedMessage(this.taggedLine, messageEvent.getMessage());
            if (taggedMessage != null) {
                printStream.println(taggedMessage);
            }
        }
        else {
            printStream.println(message);
        }
    }
    
    public void messageSent(final BinaryMessageEvent binaryMessageEvent) {
        this.stdout.write(binaryMessageEvent.getMessage(), 0, binaryMessageEvent.getMessageLength());
    }
    
    public void fileInfoGenerated(final FileInfoEvent fileInfoEvent) {
    }
}
