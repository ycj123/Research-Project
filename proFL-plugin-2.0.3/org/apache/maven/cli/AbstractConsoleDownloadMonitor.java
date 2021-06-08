// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.cli;

import org.apache.maven.wagon.events.TransferEvent;
import org.apache.maven.wagon.events.TransferListener;
import org.codehaus.plexus.logging.AbstractLogEnabled;

public abstract class AbstractConsoleDownloadMonitor extends AbstractLogEnabled implements TransferListener
{
    public void transferInitiated(final TransferEvent transferEvent) {
        final String message = (transferEvent.getRequestType() == 6) ? "Uploading" : "Downloading";
        final String url = transferEvent.getWagon().getRepository().getUrl();
        System.out.println(message + ": " + url + "/" + transferEvent.getResource().getName());
    }
    
    public void transferStarted(final TransferEvent transferEvent) {
    }
    
    public void transferProgress(final TransferEvent transferEvent, final byte[] buffer, final int length) {
    }
    
    public void transferCompleted(final TransferEvent transferEvent) {
        final long contentLength = transferEvent.getResource().getContentLength();
        if (contentLength != -1L) {
            final String type = (transferEvent.getRequestType() == 6) ? "uploaded" : "downloaded";
            final String l = (contentLength >= 1024L) ? (contentLength / 1024L + "K") : (contentLength + "b");
            System.out.println(l + " " + type);
        }
    }
    
    public void transferError(final TransferEvent transferEvent) {
        transferEvent.getException().printStackTrace();
    }
    
    public void debug(final String message) {
    }
}
