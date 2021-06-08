// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.remoteinfo;

import java.util.HashMap;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.util.AbstractConsumer;
import java.io.File;
import java.util.Map;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.svn.svnexe.command.SvnCommandLineUtils;
import org.mudebug.prapr.reloc.commons.lang.StringUtils;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import org.apache.maven.scm.command.remoteinfo.RemoteInfoScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.svn.command.SvnCommand;
import org.apache.maven.scm.command.remoteinfo.AbstractRemoteInfoCommand;

public class SvnRemoteInfoCommand extends AbstractRemoteInfoCommand implements SvnCommand
{
    @Override
    public RemoteInfoScmResult executeRemoteInfoCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final String url = ((SvnScmProviderRepository)repository).getUrl();
        final String baseUrl = StringUtils.endsWith(url, "/") ? StringUtils.substringAfter(StringUtils.removeEnd(url, "/"), "/") : StringUtils.substringBeforeLast(url, "/");
        Commandline cl = SvnCommandLineUtils.getBaseSvnCommandLine((fileSet == null) ? null : fileSet.getBasedir(), (SvnScmProviderRepository)repository);
        cl.createArg().setValue("ls");
        cl.createArg().setValue(baseUrl + "/tags");
        CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        LsConsumer consumer = new LsConsumer(this.getLogger(), baseUrl);
        int exitCode = 0;
        Map<String, String> tagsInfos = null;
        try {
            exitCode = SvnCommandLineUtils.execute(cl, consumer, stderr, this.getLogger());
            tagsInfos = consumer.infos;
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing svn command.", ex);
        }
        if (exitCode != 0) {
            return new RemoteInfoScmResult(cl.toString(), "The svn command failed.", stderr.getOutput(), false);
        }
        cl = SvnCommandLineUtils.getBaseSvnCommandLine((fileSet == null) ? null : fileSet.getBasedir(), (SvnScmProviderRepository)repository);
        cl.createArg().setValue("ls");
        cl.createArg().setValue(baseUrl + "/tags");
        stderr = new CommandLineUtils.StringStreamConsumer();
        consumer = new LsConsumer(this.getLogger(), baseUrl);
        Map<String, String> branchesInfos = null;
        try {
            exitCode = SvnCommandLineUtils.execute(cl, consumer, stderr, this.getLogger());
            branchesInfos = consumer.infos;
        }
        catch (CommandLineException ex2) {
            throw new ScmException("Error while executing svn command.", ex2);
        }
        if (exitCode != 0) {
            return new RemoteInfoScmResult(cl.toString(), "The svn command failed.", stderr.getOutput(), false);
        }
        return new RemoteInfoScmResult(cl.toString(), branchesInfos, tagsInfos);
    }
    
    public boolean remoteUrlExist(final ScmProviderRepository repository, final CommandParameters parameters) throws ScmException {
        final String url = ((SvnScmProviderRepository)repository).getUrl();
        final Commandline cl = SvnCommandLineUtils.getBaseSvnCommandLine(null, (SvnScmProviderRepository)repository);
        cl.createArg().setValue("ls");
        cl.createArg().setValue(url);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final LsConsumer consumer = new LsConsumer(this.getLogger(), url);
        int exitCode = 0;
        try {
            exitCode = SvnCommandLineUtils.execute(cl, consumer, stderr, this.getLogger());
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing svn command.", ex);
        }
        if (exitCode == 0) {
            return true;
        }
        final String output = stderr.getOutput();
        if (output.indexOf("W160013") >= 0 || output.indexOf("svn: URL") >= 0) {
            return false;
        }
        throw new ScmException(cl.toString() + ".The svn command failed:" + stderr.getOutput());
    }
    
    private static class LsConsumer extends AbstractConsumer
    {
        Map<String, String> infos;
        String url;
        
        LsConsumer(final ScmLogger logger, final String url) {
            super(logger);
            this.infos = new HashMap<String, String>();
            this.url = url;
        }
        
        public void consumeLine(final String s) {
            this.infos.put(StringUtils.removeEnd(s, "/"), this.url + "/" + s);
        }
        
        Map<String, String> getInfos() {
            return this.infos;
        }
    }
}
