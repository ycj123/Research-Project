// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.info;

import java.util.ArrayList;
import java.util.List;
import org.apache.maven.scm.ScmResult;

public class InfoScmResult extends ScmResult
{
    private static final long serialVersionUID = 955993340040530451L;
    private List<InfoItem> infoItems;
    
    public InfoScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
        this.infoItems = new ArrayList<InfoItem>(0);
    }
    
    public InfoScmResult(final String commandLine, final List<InfoItem> files) {
        super(commandLine, null, null, true);
        this.infoItems = files;
    }
    
    public InfoScmResult(final List<InfoItem> infoItems, final ScmResult result) {
        super(result);
        this.infoItems = infoItems;
    }
    
    public InfoScmResult(final ScmResult result) {
        super(result);
    }
    
    public List<InfoItem> getInfoItems() {
        return this.infoItems;
    }
}
