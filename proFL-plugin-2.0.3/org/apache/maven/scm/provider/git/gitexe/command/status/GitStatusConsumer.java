// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.status;

import java.util.Iterator;
import java.util.regex.Matcher;
import org.apache.maven.scm.ScmFileStatus;
import org.mudebug.prapr.reloc.commons.lang.StringUtils;
import java.util.ArrayList;
import java.net.URI;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class GitStatusConsumer implements StreamConsumer
{
    private static final Pattern ADDED_PATTERN;
    private static final Pattern MODIFIED_PATTERN;
    private static final Pattern DELETED_PATTERN;
    private static final Pattern RENAMED_PATTERN;
    private ScmLogger logger;
    private File workingDirectory;
    private List<ScmFile> changedFiles;
    private URI relativeRepositoryPath;
    
    public GitStatusConsumer(final ScmLogger logger, final File workingDirectory) {
        this.changedFiles = new ArrayList<ScmFile>();
        this.logger = logger;
        this.workingDirectory = workingDirectory;
    }
    
    public GitStatusConsumer(final ScmLogger logger, final File workingDirectory, final URI relativeRepositoryPath) {
        this(logger, workingDirectory);
        this.relativeRepositoryPath = relativeRepositoryPath;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        if (StringUtils.isEmpty(line)) {
            return;
        }
        ScmFileStatus status = null;
        final List<String> files = new ArrayList<String>();
        Matcher matcher;
        if ((matcher = GitStatusConsumer.ADDED_PATTERN.matcher(line)).find()) {
            status = ScmFileStatus.ADDED;
            files.add(resolvePath(matcher.group(1), this.relativeRepositoryPath));
        }
        else if ((matcher = GitStatusConsumer.MODIFIED_PATTERN.matcher(line)).find()) {
            status = ScmFileStatus.MODIFIED;
            files.add(resolvePath(matcher.group(1), this.relativeRepositoryPath));
        }
        else if ((matcher = GitStatusConsumer.DELETED_PATTERN.matcher(line)).find()) {
            status = ScmFileStatus.DELETED;
            files.add(resolvePath(matcher.group(1), this.relativeRepositoryPath));
        }
        else {
            if (!(matcher = GitStatusConsumer.RENAMED_PATTERN.matcher(line)).find()) {
                this.logger.warn("Ignoring unrecognized line: " + line);
                return;
            }
            status = ScmFileStatus.RENAMED;
            files.add(resolvePath(matcher.group(1), this.relativeRepositoryPath));
            files.add(resolvePath(matcher.group(2), this.relativeRepositoryPath));
            this.logger.debug("RENAMED status for line '" + line + "' files added '" + matcher.group(1) + "' '" + matcher.group(2));
        }
        if (!files.isEmpty() && status != null) {
            if (this.workingDirectory != null) {
                if (status == ScmFileStatus.RENAMED) {
                    final String oldFilePath = files.get(0);
                    final String newFilePath = files.get(1);
                    if (this.isFile(oldFilePath)) {
                        this.logger.debug("file '" + oldFilePath + "' is a file");
                        return;
                    }
                    this.logger.debug("file '" + oldFilePath + "' not a file");
                    if (!this.isFile(newFilePath)) {
                        this.logger.debug("file '" + newFilePath + "' not a file");
                        return;
                    }
                    this.logger.debug("file '" + newFilePath + "' is a file");
                }
                else if (status == ScmFileStatus.DELETED) {
                    if (this.isFile(files.get(0))) {
                        return;
                    }
                }
                else if (!this.isFile(files.get(0))) {
                    return;
                }
            }
            for (final String file : files) {
                this.changedFiles.add(new ScmFile(file, status));
            }
        }
    }
    
    private boolean isFile(final String file) {
        File targetFile;
        if (this.relativeRepositoryPath == null) {
            targetFile = new File(this.workingDirectory, file);
        }
        else {
            targetFile = new File(this.relativeRepositoryPath.getPath(), file);
        }
        return targetFile.isFile();
    }
    
    protected static String resolvePath(final String fileEntry, final URI path) {
        if (path != null) {
            return resolveURI(fileEntry, path).getPath();
        }
        return fileEntry;
    }
    
    public static URI resolveURI(final String fileEntry, final URI path) {
        final String str = fileEntry.replace(" ", "%20");
        return path.relativize(URI.create(str));
    }
    
    public List<ScmFile> getChangedFiles() {
        return this.changedFiles;
    }
    
    static {
        ADDED_PATTERN = Pattern.compile("^A[ M]* (.*)$");
        MODIFIED_PATTERN = Pattern.compile("^ *M[ M]* (.*)$");
        DELETED_PATTERN = Pattern.compile("^ *D * (.*)$");
        RENAMED_PATTERN = Pattern.compile("^R  (.*) -> (.*)$");
    }
}
