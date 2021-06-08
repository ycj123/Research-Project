// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.cli;

import org.apache.maven.wagon.events.TransferEvent;

public class ConsoleDownloadMonitor extends AbstractConsoleDownloadMonitor
{
    private long complete;
    
    public void transferInitiated(final TransferEvent transferEvent) {
        final String message = (transferEvent.getRequestType() == 6) ? "Uploading" : "Downloading";
        final String url = transferEvent.getWagon().getRepository().getUrl();
        System.out.println(message + ": " + url + "/" + transferEvent.getResource().getName());
        this.complete = 0L;
    }
    
    public void transferStarted(final TransferEvent transferEvent) {
    }
    
    public void transferProgress(final TransferEvent transferEvent, final byte[] buffer, final int length) {
        final long total = transferEvent.getResource().getContentLength();
        this.complete += length;
        if (total >= 1024L) {
            System.out.print(this.complete / 1024L + "/" + ((total == -1L) ? "?" : (total / 1024L + "K")) + "\r");
        }
        else {
            System.out.print(this.complete + "/" + ((total == -1L) ? "?" : (total + "b")) + "\r");
        }
    }
}
