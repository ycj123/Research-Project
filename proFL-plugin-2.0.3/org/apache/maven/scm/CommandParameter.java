// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm;

import java.io.Serializable;

public class CommandParameter implements Serializable
{
    private static final long serialVersionUID = -3391190831054016735L;
    public static final CommandParameter BINARY;
    public static final CommandParameter RECURSIVE;
    public static final CommandParameter MESSAGE;
    public static final CommandParameter BRANCH_NAME;
    public static final CommandParameter START_DATE;
    public static final CommandParameter END_DATE;
    public static final CommandParameter NUM_DAYS;
    public static final CommandParameter LIMIT;
    public static final CommandParameter BRANCH;
    public static final CommandParameter START_SCM_VERSION;
    public static final CommandParameter END_SCM_VERSION;
    public static final CommandParameter CHANGELOG_DATE_PATTERN;
    public static final CommandParameter SCM_VERSION;
    public static final CommandParameter TAG_NAME;
    public static final CommandParameter FILE;
    public static final CommandParameter FILES;
    public static final CommandParameter OUTPUT_FILE;
    public static final CommandParameter OUTPUT_DIRECTORY;
    public static final CommandParameter RUN_CHANGELOG_WITH_UPDATE;
    public static final CommandParameter SCM_TAG_PARAMETERS;
    public static final CommandParameter SCM_BRANCH_PARAMETERS;
    public static final CommandParameter SCM_MKDIR_CREATE_IN_LOCAL;
    public static final CommandParameter SCM_SHORT_REVISION_LENGTH;
    public static final CommandParameter FORCE_ADD;
    public static final CommandParameter IGNORE_WHITESPACE;
    private String name;
    
    private CommandParameter(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    static {
        BINARY = new CommandParameter("binary");
        RECURSIVE = new CommandParameter("recursive");
        MESSAGE = new CommandParameter("message");
        BRANCH_NAME = new CommandParameter("branchName");
        START_DATE = new CommandParameter("startDate");
        END_DATE = new CommandParameter("endDate");
        NUM_DAYS = new CommandParameter("numDays");
        LIMIT = new CommandParameter("limit");
        BRANCH = new CommandParameter("branch");
        START_SCM_VERSION = new CommandParameter("startScmVersion");
        END_SCM_VERSION = new CommandParameter("endScmVersion");
        CHANGELOG_DATE_PATTERN = new CommandParameter("changelogDatePattern");
        SCM_VERSION = new CommandParameter("scmVersion");
        TAG_NAME = new CommandParameter("tagName");
        FILE = new CommandParameter("file");
        FILES = new CommandParameter("files");
        OUTPUT_FILE = new CommandParameter("outputFile");
        OUTPUT_DIRECTORY = new CommandParameter("outputDirectory");
        RUN_CHANGELOG_WITH_UPDATE = new CommandParameter("run_changelog_with_update");
        SCM_TAG_PARAMETERS = new CommandParameter("ScmTagParameters");
        SCM_BRANCH_PARAMETERS = new CommandParameter("ScmBranchParameters");
        SCM_MKDIR_CREATE_IN_LOCAL = new CommandParameter("createInLocal");
        SCM_SHORT_REVISION_LENGTH = new CommandParameter("shortRevisionLength");
        FORCE_ADD = new CommandParameter("forceAdd");
        IGNORE_WHITESPACE = new CommandParameter("ignoreWhitespace");
    }
}
