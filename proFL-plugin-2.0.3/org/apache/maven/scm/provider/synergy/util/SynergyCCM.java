// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.util;

import java.util.Properties;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmVersion;
import java.util.Calendar;
import java.util.Iterator;
import java.io.IOException;
import java.io.File;
import java.util.List;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;

public class SynergyCCM
{
    private static final String CCM = "ccm";
    private static final String BASELINE = "baseline";
    private static final String CI = "ci";
    private static final String CO = "co";
    private static final String CREATE = "create";
    private static final String DELETE = "delete";
    private static final String DELIMITER = "delimiter";
    private static final String DIR = "dir";
    private static final String QUERY = "query";
    private static final String RECONCILE = "rwa";
    private static final String RECONFIGURE = "reconfigure";
    private static final String RECONFIGURE_PROPERTIES = "reconfigure_properties";
    private static final String START = "start";
    private static final String STOP = "stop";
    private static final String SYNC = "sync";
    private static final String TASK = "task";
    private static final String WA = "wa";
    
    public static Commandline showTaskObjects(final int taskNumber, final String format, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("task");
        cl.createArg().setValue("-show");
        cl.createArg().setValue("objects");
        if (format != null && !format.equals("")) {
            cl.createArg().setValue("-f");
            cl.createArg().setValue(format);
        }
        cl.createArg().setValue(Integer.toString(taskNumber));
        return cl;
    }
    
    public static Commandline query(final String query, final String format, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("query");
        cl.createArg().setValue("-u");
        if (format != null && !format.equals("")) {
            cl.createArg().setValue("-f");
            cl.createArg().setValue(format);
        }
        cl.createArg().setValue(query);
        return cl;
    }
    
    public static Commandline createBaseline(final String projectSpec, final String name, final String release, final String purpose, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("baseline");
        cl.createArg().setValue("-create");
        cl.createArg().setValue(name);
        cl.createArg().setValue("-p");
        cl.createArg().setValue(projectSpec);
        cl.createArg().setValue("-release");
        cl.createArg().setValue(release);
        cl.createArg().setValue("-purpose");
        cl.createArg().setValue(purpose);
        return cl;
    }
    
    public static Commandline create(final List<File> files, final String message, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("create");
        if (message != null && !message.equals("")) {
            cl.createArg().setValue("-c");
            cl.createArg().setValue(message);
        }
        for (final File f : files) {
            try {
                cl.createArg().setValue(f.getCanonicalPath());
            }
            catch (IOException e) {
                throw new ScmException("Invalid file path " + f.toString(), e);
            }
        }
        return cl;
    }
    
    public static Commandline createTask(final String synopsis, final String release, final boolean defaultTask, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("task");
        cl.createArg().setValue("-create");
        cl.createArg().setValue("-synopsis");
        cl.createArg().setValue(synopsis);
        if (release != null && !release.equals("")) {
            cl.createArg().setValue("-release");
            cl.createArg().setValue(release);
        }
        if (defaultTask) {
            cl.createArg().setValue("-default");
        }
        cl.createArg().setValue("-description");
        cl.createArg().setValue("This task was created by Maven SCM Synergy provider on " + Calendar.getInstance().getTime());
        return cl;
    }
    
    public static Commandline checkinTask(final String taskSpecs, final String comment, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("task");
        cl.createArg().setValue("-checkin");
        cl.createArg().setValue(taskSpecs);
        cl.createArg().setValue("-comment");
        cl.createArg().setValue(comment);
        return cl;
    }
    
    public static Commandline delete(final List<File> files, final String ccmAddr, final boolean replace) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("delete");
        if (replace) {
            cl.createArg().setValue("-replace");
        }
        for (final File f : files) {
            try {
                cl.createArg().setValue(f.getCanonicalPath());
            }
            catch (IOException e) {
                throw new ScmException("Invalid file path " + f.toString(), e);
            }
        }
        return cl;
    }
    
    public static Commandline reconfigure(final String projectSpec, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("reconfigure");
        cl.createArg().setValue("-recurse");
        if (projectSpec != null) {
            cl.createArg().setValue("-p");
            cl.createArg().setValue(projectSpec);
        }
        return cl;
    }
    
    public static Commandline reconfigureProperties(final String projectSpec, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("reconfigure_properties");
        cl.createArg().setValue("-refresh");
        cl.createArg().setValue(projectSpec);
        return cl;
    }
    
    public static Commandline reconcileUwa(final String projectSpec, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("rwa");
        cl.createArg().setValue("-r");
        cl.createArg().setValue("-uwa");
        if (projectSpec != null) {
            cl.createArg().setValue("-p");
            cl.createArg().setValue(projectSpec);
        }
        return cl;
    }
    
    public static Commandline reconcileUdb(final String projectSpec, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("rwa");
        cl.createArg().setValue("-r");
        cl.createArg().setValue("-udb");
        if (projectSpec != null) {
            cl.createArg().setValue("-p");
            cl.createArg().setValue(projectSpec);
        }
        return cl;
    }
    
    public static Commandline dir(final File directory, final String format, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        try {
            cl.setWorkingDirectory(directory.getCanonicalPath());
        }
        catch (IOException e) {
            throw new ScmException("Invalid directory", e);
        }
        cl.setExecutable("ccm");
        cl.createArg().setValue("dir");
        cl.createArg().setValue("-m");
        if (format != null && !format.equals("")) {
            cl.createArg().setValue("-f");
            cl.createArg().setValue(format);
        }
        return cl;
    }
    
    public static Commandline checkoutFiles(final List<File> files, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("co");
        for (final File f : files) {
            try {
                cl.createArg().setValue(f.getCanonicalPath());
            }
            catch (IOException e) {
                throw new ScmException("Invalid file path " + f.toString(), e);
            }
        }
        return cl;
    }
    
    public static Commandline checkoutProject(final File directory, final String projectSpec, final ScmVersion version, final String purpose, final String release, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("co");
        cl.createArg().setValue("-subprojects");
        cl.createArg().setValue("-rel");
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            cl.createArg().setValue("-t");
            cl.createArg().setValue(version.getName());
        }
        if (purpose != null && !purpose.equals("")) {
            cl.createArg().setValue("-purpose");
            cl.createArg().setValue(purpose);
        }
        if (release != null && !release.equals("")) {
            cl.createArg().setValue("-release");
            cl.createArg().setValue(release);
        }
        if (directory != null) {
            cl.createArg().setValue("-path");
            try {
                cl.createArg().setValue(directory.getCanonicalPath());
            }
            catch (IOException e) {
                throw new ScmException("Invalid directory", e);
            }
        }
        cl.createArg().setValue("-p");
        cl.createArg().setValue(projectSpec);
        return cl;
    }
    
    public static Commandline checkinProject(final String projectSpec, final String comment, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("ci");
        if (comment != null && !comment.equals("")) {
            cl.createArg().setValue("-c");
            cl.createArg().setValue(comment);
        }
        cl.createArg().setValue("-p");
        cl.createArg().setValue(projectSpec);
        return cl;
    }
    
    public static Commandline checkinFiles(final List<File> files, final String comment, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("ci");
        if (comment != null && !comment.equals("")) {
            cl.createArg().setValue("-c");
            cl.createArg().setValue(comment);
        }
        if (files.size() > 0) {
            for (final File f : files) {
                try {
                    cl.createArg().setValue(f.getCanonicalPath());
                }
                catch (IOException e) {
                    throw new ScmException("Invalid file path " + f.toString(), e);
                }
            }
        }
        return cl;
    }
    
    public static Commandline synchronize(final String projectSpec, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("sync");
        cl.createArg().setValue("-r");
        cl.createArg().setValue("-p");
        cl.createArg().setValue(projectSpec);
        return cl;
    }
    
    public static Commandline showWorkArea(final String projectSpec, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("wa");
        cl.createArg().setValue("-show");
        cl.createArg().setValue(projectSpec);
        return cl;
    }
    
    public static Commandline stop(final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("stop");
        return cl;
    }
    
    private static void configureEnvironment(final Commandline cl, final String ccmAddr) throws ScmException {
        try {
            final Properties envVars = CommandLineUtils.getSystemEnvVars();
            for (final String key : envVars.keySet()) {
                if (!key.equalsIgnoreCase("CCM_ADDR")) {
                    cl.addEnvironment(key, envVars.getProperty(key));
                }
            }
        }
        catch (Exception e1) {
            throw new ScmException("Fail to add PATH environment variable.", e1);
        }
        cl.addEnvironment("CCM_ADDR", ccmAddr);
    }
    
    public static Commandline start(final String username, final String password, final SynergyRole role) throws ScmException {
        final Commandline cl = new Commandline();
        cl.setExecutable("ccm");
        cl.createArg().setValue("start");
        cl.createArg().setValue("-nogui");
        cl.createArg().setValue("-m");
        cl.createArg().setValue("-q");
        cl.createArg().setValue("-n");
        cl.createArg().setValue(username);
        cl.createArg().setValue("-pw");
        cl.createArg().setValue(password);
        if (role != null) {
            cl.createArg().setValue("-r");
            cl.createArg().setValue(role.toString());
        }
        return cl;
    }
    
    public static Commandline startRemote(final String username, final String password, final SynergyRole role) throws ScmException {
        final Commandline cl = new Commandline();
        cl.setExecutable("ccm");
        cl.createArg().setValue("start");
        cl.createArg().setValue("-nogui");
        cl.createArg().setValue("-m");
        cl.createArg().setValue("-q");
        cl.createArg().setValue("-rc");
        cl.createArg().setValue("-n");
        cl.createArg().setValue(username);
        cl.createArg().setValue("-pw");
        cl.createArg().setValue(password);
        if (role != null) {
            cl.createArg().setValue("-r");
            cl.createArg().setValue(role.toString());
        }
        return cl;
    }
    
    public static Commandline delimiter(final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("delimiter");
        return cl;
    }
    
    public static Commandline showDefaultTask(final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("task");
        cl.createArg().setValue("-default");
        return cl;
    }
    
    public static Commandline setDefaultTask(final int task, final String ccmAddr) throws ScmException {
        final Commandline cl = new Commandline();
        configureEnvironment(cl, ccmAddr);
        cl.setExecutable("ccm");
        cl.createArg().setValue("task");
        cl.createArg().setValue("-default");
        cl.createArg().setValue(String.valueOf(task));
        return cl;
    }
}
