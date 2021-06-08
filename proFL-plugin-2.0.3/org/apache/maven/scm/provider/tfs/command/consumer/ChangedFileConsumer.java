// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command.consumer;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.util.Map;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class ChangedFileConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private static final String KEY_CHANGE = "Change";
    private static final String KEY_LOCAL_ITEM = "Local item";
    private static final String CHANGE_EDIT = "edit";
    private static final String CHANGE_ADD = "add";
    private Map<String, String> values;
    private List<ScmFile> changedFiles;
    
    public ChangedFileConsumer(final ScmLogger logger) {
        this.values = new HashMap<String, String>();
        this.changedFiles = new ArrayList<ScmFile>();
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (line.indexOf(58) >= 0) {
            final String[] s = line.split(":", 2);
            if (s.length > 1) {
                this.values.put(s[0].trim(), s[1].trim());
            }
        }
        if (line.trim().equals("")) {
            this.extractChangedFile();
        }
        this.logger.debug("line -" + line);
    }
    
    private void extractChangedFile() {
        final String change = this.getChange();
        if (change != null) {
            ScmFileStatus stat = ScmFileStatus.UNKNOWN;
            if (change.equals("edit")) {
                stat = ScmFileStatus.MODIFIED;
            }
            if (change.equals("add")) {
                stat = ScmFileStatus.ADDED;
            }
            this.changedFiles.add(new ScmFile(this.getLocalPath(), stat));
            this.values.clear();
        }
    }
    
    public List<ScmFile> getChangedFiles() {
        if (this.values.size() > 0) {
            this.extractChangedFile();
        }
        return this.changedFiles;
    }
    
    private String getChange() {
        return this.values.get("Change");
    }
    
    private String getLocalPath() {
        String local = this.values.get("Local item");
        if (local != null) {
            local = local.split("]", 2)[1].trim();
        }
        return local;
    }
}
