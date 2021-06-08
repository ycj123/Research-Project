// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.tag;

import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.hg.command.HgConsumer;

public class HgTagConsumer extends HgConsumer
{
    public HgTagConsumer(final ScmLogger logger) {
        super(logger);
    }
    
    @Override
    public void doConsume(final ScmFileStatus status, final String trimmedLine) {
    }
}
