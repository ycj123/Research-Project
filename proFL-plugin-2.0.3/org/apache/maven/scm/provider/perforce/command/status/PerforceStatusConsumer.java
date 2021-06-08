// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.status;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.perforce.command.AbstractPerforceConsumer;

public class PerforceStatusConsumer extends AbstractPerforceConsumer implements StreamConsumer
{
    static final int STATE_FILES = 1;
    static final int STATE_ERROR = 2;
    private int currentState;
    private List<String> depotfiles;
    
    public PerforceStatusConsumer() {
        this.currentState = 1;
        this.depotfiles = new ArrayList<String>();
    }
    
    public void consumeLine(final String line) {
        if (line.indexOf("not opened") != -1) {
            return;
        }
        switch (this.currentState) {
            case 1: {
                if (line.startsWith("//")) {
                    this.depotfiles.add(line.trim());
                    break;
                }
                break;
            }
            default: {
                this.error(line);
                break;
            }
        }
    }
    
    private void error(final String line) {
        this.currentState = 2;
        this.output.println(line);
    }
    
    public boolean isSuccess() {
        return this.currentState != 2;
    }
    
    public List<String> getDepotfiles() {
        return this.depotfiles;
    }
}
