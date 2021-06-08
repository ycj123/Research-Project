// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git;

import org.apache.maven.scm.providers.gitlib.settings.Settings;
import org.apache.maven.scm.provider.git.util.GitUtil;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.git.repository.GitScmProviderRepository;

public class GitCommandUtils
{
    private GitCommandUtils() {
    }
    
    public static Commandline getBaseCommand(final String commandName, final GitScmProviderRepository repo, final ScmFileSet fileSet) {
        return getBaseCommand(commandName, repo, fileSet, null);
    }
    
    public static Commandline getBaseCommand(final String commandName, final GitScmProviderRepository repo, final ScmFileSet fileSet, final String options) {
        final Settings settings = GitUtil.getSettings();
        final Commandline cl = new Commandline();
        cl.setExecutable("git");
        cl.setWorkingDirectory(fileSet.getBasedir().getAbsolutePath());
        if (settings.getTraceGitCommand() != null) {
            cl.addEnvironment("GIT_TRACE", settings.getTraceGitCommand());
        }
        cl.createArg().setLine(options);
        cl.createArg().setValue(commandName);
        return cl;
    }
    
    public static String getRevParseDateFormat() {
        return GitUtil.getSettings().getRevParseDateFormat();
    }
}
