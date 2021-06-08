// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.command.info;

import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.command.info.InfoItem;
import java.util.Collection;
import java.util.List;
import org.apache.maven.scm.command.info.InfoScmResult;

public class SvnInfoScmResult extends InfoScmResult
{
    private static final long serialVersionUID = 955993340040530451L;
    
    public SvnInfoScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
    }
    
    public SvnInfoScmResult(final String commandLine, final List<SvnInfoItem> files) {
        super(commandLine, null, null, true);
        if (files != null) {
            this.getInfoItems().addAll(files);
        }
    }
    
    public SvnInfoScmResult(final List<SvnInfoItem> files, final ScmResult result) {
        super(result);
        if (files != null) {
            this.getInfoItems().addAll(files);
        }
    }
}
