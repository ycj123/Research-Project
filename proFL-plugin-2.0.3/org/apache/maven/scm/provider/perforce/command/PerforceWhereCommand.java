// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command;

import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.IOUtil;
import java.io.IOException;
import org.codehaus.plexus.util.cli.CommandLineException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.maven.scm.provider.perforce.PerforceScmProvider;
import java.io.File;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;

public class PerforceWhereCommand
{
    private ScmLogger logger;
    private PerforceScmProviderRepository repo;
    
    public PerforceWhereCommand(final ScmLogger log, final PerforceScmProviderRepository repos) {
        this.logger = null;
        this.repo = null;
        this.logger = log;
        this.repo = repos;
    }
    
    public String getDepotLocation(final File file) {
        return this.getDepotLocation(file.getAbsolutePath());
    }
    
    public String getDepotLocation(final String filepath) {
        if (!PerforceScmProvider.isLive()) {
            return null;
        }
        InputStreamReader isReader = null;
        InputStreamReader isReaderErr = null;
        try {
            final Commandline command = PerforceScmProvider.createP4Command(this.repo, null);
            command.createArg().setValue("where");
            command.createArg().setValue(filepath);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug(PerforceScmProvider.clean("Executing: " + command.toString()));
            }
            final Process proc = command.execute();
            isReader = new InputStreamReader(proc.getInputStream());
            isReaderErr = new InputStreamReader(proc.getErrorStream());
            final BufferedReader br = new BufferedReader(isReader);
            final BufferedReader brErr = new BufferedReader(isReaderErr);
            String path = null;
            String line;
            while ((line = br.readLine()) != null) {
                if (line.indexOf("not in client view") != -1) {
                    if (this.logger.isErrorEnabled()) {
                        this.logger.error(line);
                    }
                    return null;
                }
                if (line.indexOf("is not under") != -1) {
                    if (this.logger.isErrorEnabled()) {
                        this.logger.error(line);
                    }
                    return null;
                }
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug(line);
                }
                path = line.substring(0, line.lastIndexOf("//") - 1);
            }
            while ((line = brErr.readLine()) != null) {
                if (line.indexOf("not in client view") != -1) {
                    if (this.logger.isErrorEnabled()) {
                        this.logger.error(line);
                    }
                    return null;
                }
                if (line.indexOf("is not under") != -1) {
                    if (this.logger.isErrorEnabled()) {
                        this.logger.error(line);
                    }
                    return null;
                }
                if (!this.logger.isDebugEnabled()) {
                    continue;
                }
                this.logger.debug(line);
            }
            return path;
        }
        catch (CommandLineException e) {
            if (this.logger.isErrorEnabled()) {
                this.logger.error(e);
            }
            throw new RuntimeException(e.getLocalizedMessage());
        }
        catch (IOException e2) {
            if (this.logger.isErrorEnabled()) {
                this.logger.error(e2);
            }
            throw new RuntimeException(e2.getLocalizedMessage());
        }
        finally {
            IOUtil.close(isReader);
            IOUtil.close(isReaderErr);
        }
    }
}
