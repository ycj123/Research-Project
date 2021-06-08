// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsjava;

import org.apache.maven.scm.provider.cvslib.cvsjava.command.mkdir.CvsJavaMkdirCommand;
import org.apache.maven.scm.provider.cvslib.cvsjava.command.update.CvsJavaUpdateCommand;
import org.apache.maven.scm.provider.cvslib.cvsjava.command.tag.CvsJavaTagCommand;
import org.apache.maven.scm.provider.cvslib.cvsjava.command.status.CvsJavaStatusCommand;
import org.apache.maven.scm.provider.cvslib.cvsjava.command.remove.CvsJavaRemoveCommand;
import org.apache.maven.scm.provider.cvslib.cvsjava.command.login.CvsJavaLoginCommand;
import org.apache.maven.scm.provider.cvslib.cvsjava.command.list.CvsJavaListCommand;
import org.apache.maven.scm.provider.cvslib.cvsjava.command.export.CvsJavaExportCommand;
import org.apache.maven.scm.provider.cvslib.cvsjava.command.diff.CvsJavaDiffCommand;
import org.apache.maven.scm.provider.cvslib.cvsjava.command.checkout.CvsJavaCheckOutCommand;
import org.apache.maven.scm.provider.cvslib.cvsjava.command.checkin.CvsJavaCheckInCommand;
import org.apache.maven.scm.provider.cvslib.cvsjava.command.changelog.CvsJavaChangeLogCommand;
import org.apache.maven.scm.provider.cvslib.cvsjava.command.blame.CvsJavaBlameCommand;
import org.apache.maven.scm.provider.cvslib.cvsjava.command.branch.CvsJavaBranchCommand;
import org.apache.maven.scm.provider.cvslib.cvsjava.command.add.CvsJavaAddCommand;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.provider.cvslib.AbstractCvsScmProvider;

public class CvsJavaScmProvider extends AbstractCvsScmProvider
{
    @Override
    protected Command getAddCommand() {
        return new CvsJavaAddCommand();
    }
    
    @Override
    protected Command getBranchCommand() {
        return new CvsJavaBranchCommand();
    }
    
    @Override
    protected Command getBlameCommand() {
        return new CvsJavaBlameCommand();
    }
    
    @Override
    protected Command getChangeLogCommand() {
        return new CvsJavaChangeLogCommand();
    }
    
    @Override
    protected Command getCheckInCommand() {
        return new CvsJavaCheckInCommand();
    }
    
    @Override
    protected Command getCheckOutCommand() {
        return new CvsJavaCheckOutCommand();
    }
    
    @Override
    protected Command getDiffCommand() {
        return new CvsJavaDiffCommand();
    }
    
    @Override
    protected Command getExportCommand() {
        return new CvsJavaExportCommand();
    }
    
    @Override
    protected Command getListCommand() {
        return new CvsJavaListCommand();
    }
    
    @Override
    protected Command getLoginCommand() {
        return new CvsJavaLoginCommand();
    }
    
    @Override
    protected Command getRemoveCommand() {
        return new CvsJavaRemoveCommand();
    }
    
    @Override
    protected Command getStatusCommand() {
        return new CvsJavaStatusCommand();
    }
    
    @Override
    protected Command getTagCommand() {
        return new CvsJavaTagCommand();
    }
    
    @Override
    protected Command getUpdateCommand() {
        return new CvsJavaUpdateCommand();
    }
    
    @Override
    protected Command getMkdirCommand() {
        return new CvsJavaMkdirCommand();
    }
}
