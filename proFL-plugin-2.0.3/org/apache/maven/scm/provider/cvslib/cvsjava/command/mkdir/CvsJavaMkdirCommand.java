// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsjava.command.mkdir;

import org.apache.maven.scm.provider.cvslib.cvsjava.command.add.CvsJavaAddCommand;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.provider.cvslib.command.mkdir.AbstractCvsMkdirCommand;

public class CvsJavaMkdirCommand extends AbstractCvsMkdirCommand
{
    @Override
    protected Command getAddCommand() {
        return new CvsJavaAddCommand();
    }
}
