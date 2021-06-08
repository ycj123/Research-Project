// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.util;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.provider.synergy.consumer.SynergyWorkareaConsumer;
import org.apache.maven.scm.provider.synergy.consumer.SynergyShowDefaultTaskConsumer;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.provider.synergy.consumer.SynergyCreateTaskConsumer;
import java.util.ArrayList;
import org.apache.maven.scm.provider.synergy.consumer.SynergyGetCompletedTasksConsumer;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import org.apache.maven.scm.provider.synergy.consumer.SynergyGetTaskObjectsConsumer;
import org.apache.maven.scm.ChangeFile;
import org.apache.maven.scm.provider.synergy.consumer.SynergyGetWorkingFilesConsumer;
import java.util.List;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.synergy.consumer.SynergyGetWorkingProjectConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.log.ScmLogger;
import java.io.IOException;
import org.apache.maven.scm.ScmException;
import java.io.File;

public final class SynergyUtil
{
    public static final String SEPARATOR = "#####";
    
    private SynergyUtil() {
    }
    
    public static String removePrefix(final File prefix, final File file) throws ScmException {
        try {
            final String prefixStr = prefix.getCanonicalPath();
            final String fileStr = file.getCanonicalPath();
            if (!fileStr.startsWith(prefixStr)) {
                throw new ScmException(prefixStr + " is not a prefix of " + fileStr);
            }
            return fileStr.substring(prefixStr.length());
        }
        catch (IOException e) {
            throw new ScmException("IOException", e);
        }
    }
    
    public static String getWorkingProject(final ScmLogger logger, final String projectSpec, final String username, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering getWorkingProject method");
        }
        final String query = "owner='" + username + "' and status='working' and type='project' and has_predecessor('" + projectSpec + "')";
        final Commandline cl = SynergyCCM.query(query, "%objectname", ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final SynergyGetWorkingProjectConsumer stdout = new SynergyGetWorkingProjectConsumer(logger);
        final int errorCode = executeSynergyCommand(logger, cl, stderr, stdout, false);
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : getWorkingProject returns " + stdout.getProjectSpec() + " with code " + errorCode);
        }
        return stdout.getProjectSpec();
    }
    
    public static List<String> getWorkingFiles(final ScmLogger logger, final String projectSpec, final String release, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering getWorkingFiles method");
        }
        final String query = "status='working' and release='" + release + "' and is_member_of('" + projectSpec + "')";
        final Commandline cl = SynergyCCM.query(query, "%name", ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final SynergyGetWorkingFilesConsumer stdout = new SynergyGetWorkingFilesConsumer(logger);
        final int errorCode = executeSynergyCommand(logger, cl, stderr, stdout, false);
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : getWorkingFiles returns " + stdout.getFiles().size() + " files with code " + errorCode);
        }
        return stdout.getFiles();
    }
    
    public static List<ChangeFile> getModifiedObjects(final ScmLogger logger, final int numTask, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering getModifiedObjects method");
        }
        final Commandline cl = SynergyCCM.showTaskObjects(numTask, "%name#####%version#####", ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final SynergyGetTaskObjectsConsumer stdout = new SynergyGetTaskObjectsConsumer(logger);
        final int errorCode = executeSynergyCommand(logger, cl, stderr, stdout, false);
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : getModifiedObjects returns " + stdout.getFiles().size() + " files with code " + errorCode);
        }
        return stdout.getFiles();
    }
    
    public static List<SynergyTask> getCompletedTasks(final ScmLogger logger, final String projectSpec, final Date startDate, final Date endDate, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering getCompletedTasks method");
        }
        final SimpleDateFormat toCcmDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", new Locale("en", "US"));
        String query = "is_task_in_folder_of(is_folder_in_rp_of('" + projectSpec + "'))";
        if (startDate != null) {
            query = query + "and completion_date>time('" + toCcmDate.format(startDate) + "')";
        }
        if (endDate != null) {
            query = query + "and completion_date<time('" + toCcmDate.format(endDate) + "')";
        }
        final Commandline cl = SynergyCCM.query(query, "%displayname#####%owner#####%completion_date#####%task_synopsis#####", ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final SynergyGetCompletedTasksConsumer stdout = new SynergyGetCompletedTasksConsumer(logger);
        executeSynergyCommand(logger, cl, stderr, stdout, false);
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : getCompletedTasks method returns " + stdout.getTasks().size() + " tasks");
        }
        return stdout.getTasks();
    }
    
    public static void createBaseline(final ScmLogger logger, final String projectSpec, final String name, final String release, final String purpose, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering createBaseline method");
        }
        final Commandline cl = SynergyCCM.createBaseline(projectSpec, name, release, purpose, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static void create(final ScmLogger logger, final File file, final String message, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering create method");
        }
        final List<File> files = new ArrayList<File>();
        files.add(file);
        final Commandline cl = SynergyCCM.create(files, message, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static int createTask(final ScmLogger logger, final String synopsis, final String release, final boolean defaultTask, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering createTask method of SynergyUtil");
        }
        if (synopsis == null || synopsis.equals("")) {
            throw new ScmException("A synopsis must be specified to create a task.");
        }
        final Commandline cl = SynergyCCM.createTask(synopsis, release, defaultTask, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final SynergyCreateTaskConsumer stdout = new SynergyCreateTaskConsumer(logger);
        executeSynergyCommand(logger, cl, stderr, stdout, true);
        if (logger.isDebugEnabled()) {
            logger.debug("createTask returns " + stdout.getTask());
        }
        return stdout.getTask();
    }
    
    public static void checkinDefaultTask(final ScmLogger logger, final String comment, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering checkinDefaultTask method");
        }
        final Commandline cl = SynergyCCM.checkinTask("default", comment, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static void checkinTask(final ScmLogger logger, final int taskNumber, final String comment, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering checkinTask method");
        }
        final Commandline cl = SynergyCCM.checkinTask("" + taskNumber, comment, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static void delete(final ScmLogger logger, final File file, final String ccmAddr, final boolean replace) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering delete method");
        }
        final List<File> list = new ArrayList<File>();
        list.add(file);
        final Commandline cl = SynergyCCM.delete(list, ccmAddr, replace);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static void reconfigure(final ScmLogger logger, final String projectSpec, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering reconfigure method");
        }
        final Commandline cl = SynergyCCM.reconfigure(projectSpec, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static void reconfigureProperties(final ScmLogger logger, final String projectSpec, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering reconfigureProperties method");
        }
        final Commandline cl = SynergyCCM.reconfigureProperties(projectSpec, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static void reconcileUwa(final ScmLogger logger, final String projectSpec, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering reconcileUwa method");
        }
        final Commandline cl = SynergyCCM.reconcileUwa(projectSpec, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static void reconcileUdb(final ScmLogger logger, final String projectSpec, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering reconcileUdb method");
        }
        final Commandline cl = SynergyCCM.reconcileUdb(projectSpec, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static void checkoutFiles(final ScmLogger logger, final List<File> files, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering checkoutFiles files method");
        }
        final Commandline cl = SynergyCCM.checkoutFiles(files, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static void checkoutProject(final ScmLogger logger, final File directory, final String projectSpec, final ScmVersion version, final String purpose, final String release, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering checkoutProject project method");
        }
        final Commandline cl = SynergyCCM.checkoutProject(directory, projectSpec, version, purpose, release, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static void checkinProject(final ScmLogger logger, final String projectSpec, final String comment, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering checkinProject project method");
        }
        final Commandline cl = SynergyCCM.checkinProject(projectSpec, comment, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static void checkinFiles(final ScmLogger logger, final List<File> files, final String comment, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering checkinFiles project method");
        }
        final Commandline cl = SynergyCCM.checkinFiles(files, comment, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static int getDefaultTask(final ScmLogger logger, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering getDefaultTask method");
        }
        final Commandline cl = SynergyCCM.showDefaultTask(ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final SynergyShowDefaultTaskConsumer stdout = new SynergyShowDefaultTaskConsumer(logger);
        final int errorCode = executeSynergyCommand(logger, cl, stderr, stdout, false);
        if (logger.isDebugEnabled()) {
            logger.debug("getDefaultTask returns " + stdout.getTask() + " with error code " + errorCode);
        }
        return stdout.getTask();
    }
    
    public static void setDefaultTask(final ScmLogger logger, final int task, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering setDefaultTask method");
        }
        final Commandline cl = SynergyCCM.setDefaultTask(task, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static void synchronize(final ScmLogger logger, final String projectSpec, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering synchronize method");
        }
        final Commandline cl = SynergyCCM.synchronize(projectSpec, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static File getWorkArea(final ScmLogger logger, final String projectSpec, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering getWorkArea method");
        }
        final Commandline cl = SynergyCCM.showWorkArea(projectSpec, ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final SynergyWorkareaConsumer stdout = new SynergyWorkareaConsumer(logger);
        executeSynergyCommand(logger, cl, stderr, stdout, true);
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : getWorkArea returns " + stdout.getWorkAreaPath());
        }
        return stdout.getWorkAreaPath();
    }
    
    public static void stop(final ScmLogger logger, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering stop method");
        }
        final Commandline cl = SynergyCCM.stop(ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
    }
    
    public static String start(final ScmLogger logger, final String username, final String password, final SynergyRole role) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering start method");
        }
        if (username == null) {
            throw new ScmException("username can't be null");
        }
        if (password == null) {
            throw new ScmException("password can't be null");
        }
        Commandline cl = SynergyCCM.start(username, password, role);
        CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        final int exitCode = executeSynergyCommand(logger, cl, stderr, stdout, false);
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : start returns with error code " + exitCode);
        }
        if (exitCode != 0) {
            cl = SynergyCCM.startRemote(username, password, role);
            stderr = new CommandLineUtils.StringStreamConsumer();
            stdout = new CommandLineUtils.StringStreamConsumer();
            executeSynergyCommand(logger, cl, stderr, stdout, true);
        }
        return stdout.getOutput();
    }
    
    public static String delimiter(final ScmLogger logger, final String ccmAddr) throws ScmException {
        if (logger.isDebugEnabled()) {
            logger.debug("Synergy : Entering delimiter method");
        }
        final Commandline cl = SynergyCCM.delimiter(ccmAddr);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        executeSynergyCommand(logger, cl, stderr, stdout, true);
        return stdout.getOutput();
    }
    
    protected static int executeSynergyCommand(final ScmLogger logger, final Commandline cl, final CommandLineUtils.StringStreamConsumer stderr, final StreamConsumer stdout, final boolean failOnError) throws ScmException {
        int exitCode;
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Executing: " + cl.toString());
            }
            exitCode = CommandLineUtils.executeCommandLine(cl, stdout, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing synergy command [" + cl.toString() + "].", ex);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Exit code :" + exitCode);
        }
        if (stdout instanceof CommandLineUtils.StringStreamConsumer) {
            if (logger.isDebugEnabled()) {
                logger.debug("STDOUT :" + ((CommandLineUtils.StringStreamConsumer)stdout).getOutput());
            }
        }
        else if (logger.isDebugEnabled()) {
            logger.debug("STDOUT : unavailable");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("STDERR :" + stderr.getOutput());
        }
        if (exitCode == 0 || !failOnError) {
            return exitCode;
        }
        if (stdout instanceof CommandLineUtils.StringStreamConsumer) {
            throw new ScmException("Commandeline = " + cl.toString() + "\nSTDOUT = " + ((CommandLineUtils.StringStreamConsumer)stdout).getOutput() + "\nSTDERR = " + stderr.getOutput() + "\n");
        }
        throw new ScmException("Commandeline = " + cl.toString() + "\nSTDOUT = unavailable" + "\nSTDERR = " + stderr.getOutput() + "\n");
    }
}
