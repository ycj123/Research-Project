// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.update;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.provider.jazz.command.consumer.AbstractRepositoryConsumer;

public class JazzUpdateConsumer extends AbstractRepositoryConsumer
{
    public static final String UPDATE_CMD_ADD_FLAG = "-a-";
    public static final String UPDATE_CMD_CHANGE_FLAG = "--c";
    public static final String UPDATE_CMD_DELETE_FLAG = "-d-";
    public static final String UPDATE_CMD_MOVED_FLAG = "-m-";
    private List<ScmFile> fUpdatedFiles;
    
    public JazzUpdateConsumer(final ScmProviderRepository repository, final ScmLogger logger) {
        super(repository, logger);
        this.fUpdatedFiles = new ArrayList<ScmFile>();
    }
    
    @Override
    public void consumeLine(final String line) {
        super.consumeLine(line);
        if (this.containsStatusFlag(line)) {
            this.extractUpdatedFile(line);
        }
    }
    
    private boolean containsStatusFlag(final String line) {
        boolean containsStatusFlag = false;
        if (line.trim().length() > 3) {
            final String flag = line.trim().substring(0, 3);
            if ("-a-".equals(flag) || "--c".equals(flag) || "-d-".equals(flag) || "-m-".equals(flag)) {
                containsStatusFlag = true;
            }
        }
        return containsStatusFlag;
    }
    
    private void extractUpdatedFile(final String line) {
        String filePath = "";
        final String flag = line.trim().substring(0, 3);
        ScmFileStatus status = ScmFileStatus.UNKNOWN;
        if ("-a-".equals(flag)) {
            status = ScmFileStatus.ADDED;
            filePath = line.trim().substring(4);
        }
        if ("--c".equals(flag)) {
            status = ScmFileStatus.UPDATED;
            filePath = line.trim().substring(4);
        }
        if ("-d-".equals(flag)) {
            status = ScmFileStatus.DELETED;
            filePath = line.trim().substring(4);
        }
        if ("-m-".equals(flag)) {
            status = ScmFileStatus.ADDED;
            final String pattern = "^-m-\\s(.*)\\s\\(moved\\sfrom\\s.*$";
            final Pattern r = Pattern.compile(pattern);
            final Matcher m = r.matcher(line.trim());
            if (m.find()) {
                filePath = m.group(1);
            }
        }
        this.fUpdatedFiles.add(new ScmFile(filePath, status));
    }
    
    public List<ScmFile> getUpdatedFiles() {
        return this.fUpdatedFiles;
    }
}
