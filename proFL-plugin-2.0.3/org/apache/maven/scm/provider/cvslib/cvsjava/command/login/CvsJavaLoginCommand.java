// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsjava.command.login;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.cvslib.command.login.CvsLoginCommand;

public class CvsJavaLoginCommand extends CvsLoginCommand
{
    @Override
    public boolean isCvsNT() throws ScmException {
        return false;
    }
}
