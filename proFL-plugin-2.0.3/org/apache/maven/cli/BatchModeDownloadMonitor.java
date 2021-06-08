// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.cli;

import org.apache.maven.wagon.events.TransferEvent;

public class BatchModeDownloadMonitor extends AbstractConsoleDownloadMonitor
{
    public void transferInitiated(final TransferEvent transferEvent) {
        final String message = (transferEvent.getRequestType() == 6) ? "Uploading" : "Downloading";
        final String url = transferEvent.getWagon().getRepository().getUrl();
        System.out.println(message + ": " + url + "/" + transferEvent.getResource().getName());
    }
}
