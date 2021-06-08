// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command;

import org.apache.maven.scm.provider.tfs.TfsScmProviderRepository;
import java.util.Iterator;
import java.util.List;
import org.apache.maven.scm.ScmFileStatus;
import java.io.File;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.tfs.command.consumer.ErrorStreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.tag.AbstractTagCommand;

public class TfsTagCommand extends AbstractTagCommand
{
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository r, final ScmFileSet f, final String tag, final String message) throws ScmException {
        return this.executeTagCommand(r, f, tag, new ScmTagParameters(message));
    }
    
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository r, final ScmFileSet f, final String tag, final ScmTagParameters scmTagParameters) throws ScmException {
        final TfsCommand command = this.createCommand(r, f, tag, scmTagParameters);
        final CommandLineUtils.StringStreamConsumer out = new CommandLineUtils.StringStreamConsumer();
        final ErrorStreamConsumer err = new ErrorStreamConsumer();
        final int status = command.execute(out, err);
        if (status != 0 || err.hasBeenFed()) {
            return new TagScmResult(command.getCommandString(), "Error code for TFS label command - " + status, err.getOutput(), false);
        }
        final List<ScmFile> files = new ArrayList<ScmFile>(f.getFileList().size());
        for (final File file : f.getFileList()) {
            files.add(new ScmFile(file.getPath(), ScmFileStatus.TAGGED));
        }
        return new TagScmResult(command.getCommandString(), files);
    }
    
    public TfsCommand createCommand(final ScmProviderRepository r, final ScmFileSet f, final String tag, final ScmTagParameters scmTagParameters) {
        final TfsScmProviderRepository tfsRepo = (TfsScmProviderRepository)r;
        final String url = tfsRepo.getServerPath();
        final TfsCommand command = new TfsCommand("label", r, f, this.getLogger());
        command.addArgument(tag);
        command.addArgument(url);
        command.addArgument("-recursive");
        command.addArgument("-child:replace");
        final String message = scmTagParameters.getMessage();
        if (message != null && !message.equals("")) {
            command.addArgument("-comment:" + message);
        }
        return command;
    }
}
