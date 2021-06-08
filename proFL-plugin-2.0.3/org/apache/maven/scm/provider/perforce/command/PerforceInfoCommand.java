// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command;

import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.IOUtil;
import java.io.IOException;
import org.codehaus.plexus.util.cli.CommandLineException;
import java.util.HashMap;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import org.apache.maven.scm.provider.perforce.PerforceScmProvider;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;
import java.util.Map;
import org.apache.maven.scm.command.AbstractCommand;

public class PerforceInfoCommand extends AbstractCommand implements PerforceCommand
{
    private static PerforceInfoCommand singleton;
    private Map<String, String> entries;
    
    public PerforceInfoCommand() {
        this.entries = null;
    }
    
    public static PerforceInfoCommand getInfo(final ScmLogger logger, final PerforceScmProviderRepository repo) {
        return getSingleton(logger, repo);
    }
    
    public String getEntry(final String key) {
        return this.entries.get(key);
    }
    
    private static synchronized PerforceInfoCommand getSingleton(final ScmLogger logger, final PerforceScmProviderRepository repo) {
        if (PerforceInfoCommand.singleton == null) {
            final PerforceInfoCommand pic = new PerforceInfoCommand();
            if (logger != null) {
                pic.setLogger(logger);
            }
            try {
                pic.executeCommand(repo, null, null);
                PerforceInfoCommand.singleton = pic;
            }
            catch (ScmException e) {
                if (pic.getLogger().isErrorEnabled()) {
                    pic.getLogger().error("ScmException " + e.getMessage(), e);
                }
            }
        }
        return PerforceInfoCommand.singleton;
    }
    
    @Override
    protected ScmResult executeCommand(final ScmProviderRepository repo, final ScmFileSet scmFileSet, final CommandParameters commandParameters) throws ScmException {
        if (!PerforceScmProvider.isLive()) {
            return null;
        }
        InputStreamReader isReader = null;
        try {
            final Commandline command = PerforceScmProvider.createP4Command((PerforceScmProviderRepository)repo, null);
            command.createArg().setValue("info");
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug(PerforceScmProvider.clean("Executing: " + command.toString()));
            }
            final Process proc = command.execute();
            isReader = new InputStreamReader(proc.getInputStream());
            final BufferedReader br = new BufferedReader(isReader);
            this.entries = new HashMap<String, String>();
            String line;
            while ((line = br.readLine()) != null) {
                final int idx = line.indexOf(58);
                if (idx == -1) {
                    if (line.indexOf("Client unknown.") == -1) {
                        throw new IllegalStateException("Unexpected results from 'p4 info' command: " + line);
                    }
                    if (this.getLogger().isDebugEnabled()) {
                        this.getLogger().debug("Cannot find client.");
                    }
                    this.entries.put("Client root", "");
                }
                else {
                    final String key = line.substring(0, idx);
                    final String value = line.substring(idx + 1).trim();
                    this.entries.put(key, value);
                }
            }
        }
        catch (CommandLineException e) {
            throw new ScmException(e.getLocalizedMessage());
        }
        catch (IOException e2) {
            throw new ScmException(e2.getLocalizedMessage());
        }
        finally {
            IOUtil.close(isReader);
        }
        return null;
    }
    
    static {
        PerforceInfoCommand.singleton = null;
    }
}
