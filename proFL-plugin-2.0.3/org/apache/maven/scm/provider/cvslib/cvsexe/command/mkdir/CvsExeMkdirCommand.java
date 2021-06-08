// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsexe.command.mkdir;

import org.apache.maven.scm.provider.cvslib.cvsexe.command.add.CvsExeAddCommand;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.provider.cvslib.command.mkdir.AbstractCvsMkdirCommand;

public class CvsExeMkdirCommand extends AbstractCvsMkdirCommand
{
    @Override
    protected Command getAddCommand() {
        return new CvsExeAddCommand();
    }
}
