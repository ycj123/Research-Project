// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.manager;

import org.apache.maven.scm.log.DefaultLog;
import org.apache.maven.scm.log.ScmLogger;

public class BasicScmManager extends AbstractScmManager
{
    @Override
    protected ScmLogger getScmLogger() {
        return new DefaultLog();
    }
}
