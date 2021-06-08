// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.blame;

import java.util.List;
import org.apache.maven.scm.ScmResult;

public class BlameScmResult extends ScmResult
{
    private static final long serialVersionUID = -3877526036464636595L;
    private List<BlameLine> lines;
    
    public BlameScmResult(final String commandLine, final List<BlameLine> lines) {
        this(commandLine, null, null, true);
        this.lines = lines;
    }
    
    public BlameScmResult(final String commandLine, final String providerMessage, final String commandOutput, final boolean success) {
        super(commandLine, providerMessage, commandOutput, success);
    }
    
    public BlameScmResult(final List<BlameLine> lines, final ScmResult scmResult) {
        super(scmResult);
        this.lines = lines;
    }
    
    public List<BlameLine> getLines() {
        return this.lines;
    }
}
