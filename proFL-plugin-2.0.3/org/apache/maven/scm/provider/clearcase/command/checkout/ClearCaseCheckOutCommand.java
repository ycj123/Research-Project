// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.checkout;

import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.FileWriter;
import org.codehaus.plexus.util.cli.Commandline;
import java.io.File;
import java.io.IOException;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.StringUtils;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.clearcase.repository.ClearCaseScmProviderRepository;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.providers.clearcase.settings.Settings;
import org.apache.maven.scm.provider.clearcase.command.ClearCaseCommand;
import org.apache.maven.scm.command.checkout.AbstractCheckOutCommand;

public class ClearCaseCheckOutCommand extends AbstractCheckOutCommand implements ClearCaseCommand
{
    private Settings settings;
    
    public ClearCaseCheckOutCommand() {
        this.settings = null;
    }
    
    @Override
    protected CheckOutScmResult executeCheckOutCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final ScmVersion version, final boolean recursive) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing checkout command...");
        }
        final ClearCaseScmProviderRepository repo = (ClearCaseScmProviderRepository)repository;
        final File workingDirectory = fileSet.getBasedir();
        if (version != null && this.getLogger().isDebugEnabled()) {
            this.getLogger().debug(version.getType() + ": " + version.getName());
        }
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Running with CLEARCASE " + this.settings.getClearcaseType());
        }
        final ClearCaseCheckOutConsumer consumer = new ClearCaseCheckOutConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        String projectDirectory = "";
        Commandline cl;
        int exitCode;
        try {
            FileUtils.deleteDirectory(workingDirectory);
            final String viewName = this.getUniqueViewName(repo, workingDirectory.getAbsolutePath());
            final String streamIdentifier = this.getStreamIdentifier(repo.getStreamName(), repo.getVobName());
            cl = this.createCreateViewCommandLine(workingDirectory, viewName, streamIdentifier);
            if (this.getLogger().isInfoEnabled()) {
                this.getLogger().info("Executing: " + cl.getWorkingDirectory().getAbsolutePath() + ">>" + cl.toString());
            }
            exitCode = CommandLineUtils.executeCommandLine(cl, new CommandLineUtils.StringStreamConsumer(), stderr);
            if (exitCode == 0) {
                File configSpecLocation;
                if (!repo.isAutoConfigSpec()) {
                    configSpecLocation = repo.getConfigSpec();
                    if (version != null && StringUtils.isNotEmpty(version.getName())) {
                        throw new UnsupportedOperationException("Building on a label not supported with user-specified config specs");
                    }
                }
                else {
                    String configSpec;
                    if (!repo.hasElements()) {
                        configSpec = this.createConfigSpec(repo.getLoadDirectory(), version);
                    }
                    else {
                        configSpec = this.createConfigSpec(repo.getLoadDirectory(), repo.getElementName(), version);
                    }
                    if (this.getLogger().isInfoEnabled()) {
                        this.getLogger().info("Created config spec for view '" + viewName + "':\n" + configSpec);
                    }
                    configSpecLocation = this.writeTemporaryConfigSpecFile(configSpec, viewName);
                    projectDirectory = repo.getLoadDirectory();
                    if (projectDirectory.startsWith("/")) {
                        projectDirectory = projectDirectory.substring(1);
                    }
                }
                cl = this.createUpdateConfigSpecCommandLine(workingDirectory, configSpecLocation, viewName);
                if (this.getLogger().isInfoEnabled()) {
                    this.getLogger().info("Executing: " + cl.getWorkingDirectory().getAbsolutePath() + ">>" + cl.toString());
                }
                exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
            }
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing clearcase command.", ex);
        }
        catch (IOException ex2) {
            throw new ScmException("Error while deleting working directory.", ex2);
        }
        if (exitCode != 0) {
            return new CheckOutScmResult(cl.toString(), "The cleartool command failed.", stderr.getOutput(), false);
        }
        return new CheckOutScmResult(cl.toString(), consumer.getCheckedOutFiles(), projectDirectory);
    }
    
    protected File writeTemporaryConfigSpecFile(final String configSpecContents, final String viewName) throws IOException {
        final File configSpecLocation = File.createTempFile("configspec-" + viewName, ".txt");
        final FileWriter fw = new FileWriter(configSpecLocation);
        try {
            fw.write(configSpecContents);
        }
        finally {
            try {
                fw.close();
            }
            catch (IOException ex) {}
        }
        configSpecLocation.deleteOnExit();
        return configSpecLocation;
    }
    
    protected String createConfigSpec(final String loadDirectory, final ScmVersion version) {
        final StringBuilder configSpec = new StringBuilder();
        configSpec.append("element * CHECKEDOUT\n");
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            configSpec.append("element * " + version.getName() + "\n");
            configSpec.append("element -directory * /main/LATEST\n");
        }
        else {
            configSpec.append("element * /main/LATEST\n");
        }
        configSpec.append("load " + loadDirectory + "\n");
        return configSpec.toString();
    }
    
    protected String createConfigSpec(final String loadDirectory, final String elementName, final ScmVersion version) {
        final StringBuilder configSpec = new StringBuilder();
        configSpec.append("element * CHECKEDOUT\n");
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            configSpec.append("element * " + version.getName() + "\n");
            configSpec.append("element * " + elementName + "\n");
        }
        else {
            configSpec.append("element * /main/LATEST\n");
        }
        configSpec.append("load " + loadDirectory + "\n");
        return configSpec.toString();
    }
    
    protected Commandline createCreateViewCommandLine(final File workingDirectory, final String viewName, final String streamIdentifier) throws IOException {
        final Commandline command = new Commandline();
        command.setWorkingDirectory(workingDirectory.getParentFile().getAbsolutePath());
        command.setExecutable("cleartool");
        command.createArg().setValue("mkview");
        command.createArg().setValue("-snapshot");
        command.createArg().setValue("-tag");
        command.createArg().setValue(viewName);
        if (this.isClearCaseUCM()) {
            command.createArg().setValue("-stream");
            command.createArg().setValue(streamIdentifier);
        }
        if (!this.isClearCaseLT() && this.useVWS()) {
            command.createArg().setValue("-vws");
            command.createArg().setValue(this.getViewStore() + viewName + ".vws");
        }
        command.createArg().setValue(workingDirectory.getCanonicalPath());
        return command;
    }
    
    protected String getStreamIdentifier(final String streamName, final String vobName) {
        if (streamName == null || vobName == null) {
            return null;
        }
        return "stream:" + streamName + "@" + vobName;
    }
    
    protected Commandline createUpdateConfigSpecCommandLine(final File workingDirectory, final File configSpecLocation, final String viewName) {
        final Commandline command = new Commandline();
        command.setWorkingDirectory(workingDirectory.getAbsolutePath());
        command.setExecutable("cleartool");
        command.createArg().setValue("setcs");
        command.createArg().setValue("-tag");
        command.createArg().setValue(viewName);
        command.createArg().setValue(configSpecLocation.getAbsolutePath());
        return command;
    }
    
    private String getUniqueViewName(final ClearCaseScmProviderRepository repository, final String absolutePath) {
        final int lastIndexBack = absolutePath.lastIndexOf(92);
        final int lastIndexForward = absolutePath.lastIndexOf(47);
        String uniqueId;
        if (lastIndexBack != -1) {
            uniqueId = absolutePath.substring(lastIndexBack + 1);
        }
        else {
            uniqueId = absolutePath.substring(lastIndexForward + 1);
        }
        return repository.getViewName(uniqueId);
    }
    
    protected String getViewStore() {
        String result = null;
        if (this.settings.getViewstore() != null) {
            result = this.settings.getViewstore();
        }
        if (result == null) {
            result = "\\\\" + this.getHostName() + "\\viewstore\\";
        }
        else if (this.isClearCaseLT()) {
            result = result + this.getUserName() + "\\";
        }
        return result;
    }
    
    protected boolean isClearCaseLT() {
        return "LT".equals(this.settings.getClearcaseType());
    }
    
    protected boolean isClearCaseUCM() {
        return "UCM".equals(this.settings.getClearcaseType());
    }
    
    protected boolean useVWS() {
        return this.settings.isUseVWSParameter();
    }
    
    private String getHostName() {
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return hostname;
    }
    
    private String getUserName() {
        final String username = System.getProperty("user.name");
        return username;
    }
    
    public void setSettings(final Settings settings) {
        this.settings = settings;
    }
}
