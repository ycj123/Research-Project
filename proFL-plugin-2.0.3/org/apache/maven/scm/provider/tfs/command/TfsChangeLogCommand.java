// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command;

import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import java.util.List;
import org.apache.maven.scm.command.changelog.ChangeLogSet;
import java.util.Collection;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.ErrorStreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.TfsChangeLogConsumer;
import java.io.File;
import org.apache.maven.scm.ChangeSet;
import java.util.ArrayList;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmBranch;
import java.util.Date;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.changelog.AbstractChangeLogCommand;

public class TfsChangeLogCommand extends AbstractChangeLogCommand
{
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository r, final ScmFileSet f, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern) throws ScmException {
        final List<ChangeSet> changeLogs = new ArrayList<ChangeSet>();
        Iterator<File> iter = f.getFileList().iterator();
        if (!iter.hasNext()) {
            final List<File> dir = new ArrayList<File>();
            dir.add(f.getBasedir());
            iter = dir.iterator();
        }
        TfsCommand command = null;
        while (iter.hasNext()) {
            final TfsChangeLogConsumer out = new TfsChangeLogConsumer(this.getLogger());
            final ErrorStreamConsumer err = new ErrorStreamConsumer();
            command = this.createCommand(r, f, iter.next());
            final int status = command.execute(out, err);
            if (status != 0 || (!out.hasBeenFed() && err.hasBeenFed())) {
                return new ChangeLogScmResult(command.getCommandString(), "Error code for TFS changelog command - " + status, err.getOutput(), false);
            }
            changeLogs.addAll(out.getLogs());
        }
        return new ChangeLogScmResult(command.getCommandString(), new ChangeLogSet(changeLogs, startDate, endDate));
    }
    
    protected TfsCommand createCommand(final ScmProviderRepository r, final ScmFileSet f, final File file) {
        final TfsCommand command = new TfsCommand("history", r, f, this.getLogger());
        command.addArgument("-format:detailed");
        command.addArgument(file.getName());
        return command;
    }
}
