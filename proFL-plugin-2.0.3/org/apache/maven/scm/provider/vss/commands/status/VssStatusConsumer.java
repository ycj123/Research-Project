// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.vss.commands.status;

import java.io.File;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.vss.repository.VssScmProviderRepository;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.util.AbstractConsumer;

public class VssStatusConsumer extends AbstractConsumer implements StreamConsumer
{
    private static final int DIFF_UNKNOWN = 0;
    private static final int DIFF_LOCAL_FILES_NOT_IN_PROJECT = 1;
    private static final int DIFF_VSS_FILES_DIFFERENT_FROM_LOCAL_FILES = 2;
    private static final int DIFF_VSS_FILES_NOT_IN_CURRENT_FOLDER = 3;
    private static final int DIFF_START_DIFFING_REMOTE = 4;
    private static final int DIFF_START_DIFFING_LOCAL = 5;
    private static final String START_DIFFING_REMOTE = "Diffing:";
    private static final String START_DIFFING_LOCAL = "Against:";
    private static final String LOCAL_FILES_NOT_IN_PROJECT = "Local files not in the current project:";
    private static final String VSS_FILES_DIFFERENT_FROM_LOCAL_FILES = "SourceSafe files different from local files:";
    private static final String VSS_FILES_NOT_IN_CURRENT_FOLDER = "SourceSafe files not in the current folder:";
    private String remoteProjectFolder;
    private String localFolder;
    private int lastState;
    private List<ScmFile> updatedFiles;
    private VssScmProviderRepository repo;
    private ScmFileSet fileSet;
    
    public VssStatusConsumer(final VssScmProviderRepository repo, final ScmLogger logger, final ScmFileSet fileSet) {
        super(logger);
        this.remoteProjectFolder = "";
        this.localFolder = "";
        this.lastState = 0;
        this.updatedFiles = new ArrayList<ScmFile>();
        this.repo = repo;
        this.fileSet = fileSet;
    }
    
    public void consumeLine(final String line) {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug(line);
        }
        switch (this.getLineStatus(line)) {
            case 1: {
                this.lastState = 1;
                break;
            }
            case 2: {
                this.lastState = 2;
                break;
            }
            case 3: {
                this.lastState = 3;
                break;
            }
            case 5: {
                this.lastState = 5;
                this.processLocalFolder(line);
                break;
            }
            case 4: {
                this.lastState = 4;
                this.processRemoteProjectFolder(line);
                break;
            }
            default: {
                this.processLastStateFiles(line);
                break;
            }
        }
    }
    
    private void processLastStateFiles(final String line) {
        if (line != null && line.trim().length() > 0) {
            if (this.lastState == 5) {
                this.setLocalFolder(this.localFolder + line);
                this.getLogger().debug("Local folder: " + this.localFolder);
            }
            else if (this.lastState == 4) {
                this.setRemoteProjectFolder(this.remoteProjectFolder + line);
                this.getLogger().debug("Remote folder: " + this.localFolder);
            }
            final String[] fileLine = line.split(" ");
            for (int i = 0; i < fileLine.length; ++i) {
                if (fileLine[i].trim().length() > 0) {
                    if (this.lastState == 1) {
                        this.updatedFiles.add(new ScmFile(this.localFolder + fileLine[i], ScmFileStatus.ADDED));
                    }
                    else if (this.lastState == 3) {
                        this.updatedFiles.add(new ScmFile(this.localFolder + fileLine[i], ScmFileStatus.UPDATED));
                    }
                    else if (this.lastState == 2) {
                        this.updatedFiles.add(new ScmFile(this.localFolder + fileLine[i], ScmFileStatus.MODIFIED));
                    }
                    if (this.getLogger().isDebugEnabled()) {
                        this.getLogger().debug(this.localFolder + fileLine[i]);
                    }
                }
            }
        }
        else if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("processLastStateFiles:  empty line");
        }
    }
    
    private void processLocalFolder(final String line) {
        this.setLocalFolder(line.substring("Against:".length()).trim());
    }
    
    private void processRemoteProjectFolder(final String line) {
        this.setRemoteProjectFolder(line.substring("Diffing:".length()).trim());
    }
    
    private int getLineStatus(final String line) {
        int argument = 0;
        if (line.startsWith("Local files not in the current project:")) {
            argument = 1;
        }
        else if (line.startsWith("SourceSafe files different from local files:")) {
            argument = 2;
        }
        else if (line.startsWith("SourceSafe files not in the current folder:")) {
            argument = 3;
        }
        else if (line.startsWith("Against:")) {
            argument = 5;
        }
        else if (line.startsWith("Diffing:")) {
            argument = 4;
        }
        return argument;
    }
    
    public List<ScmFile> getUpdatedFiles() {
        return this.updatedFiles;
    }
    
    private void setLocalFolder(final String localFolder) {
        if (localFolder != null && localFolder.trim().length() > 0) {
            this.localFolder = localFolder.replace(File.separatorChar, '/') + "/";
        }
        else {
            this.localFolder = "";
        }
    }
    
    private void setRemoteProjectFolder(final String remoteProjectFolder) {
        this.remoteProjectFolder = remoteProjectFolder;
    }
}
