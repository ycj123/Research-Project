// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.checkout;

import java.util.regex.Matcher;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.maven.scm.provider.jazz.command.consumer.AbstractRepositoryConsumer;

public class JazzCheckOutConsumer extends AbstractRepositoryConsumer
{
    private static final Pattern DOWNLOAD_PATTERN;
    protected String fCurrentDir;
    private List<ScmFile> fCheckedOutFiles;
    
    public JazzCheckOutConsumer(final ScmProviderRepository repository, final ScmLogger logger) {
        super(repository, logger);
        this.fCurrentDir = "";
        this.fCheckedOutFiles = new ArrayList<ScmFile>();
    }
    
    @Override
    public void consumeLine(final String line) {
        final Matcher matcher = JazzCheckOutConsumer.DOWNLOAD_PATTERN.matcher(line);
        if (matcher.matches()) {
            this.fCheckedOutFiles.add(new ScmFile(matcher.group(1), ScmFileStatus.CHECKED_OUT));
        }
    }
    
    public List<ScmFile> getCheckedOutFiles() {
        return this.fCheckedOutFiles;
    }
    
    static {
        DOWNLOAD_PATTERN = Pattern.compile("^Downloading\\s(.*)\\s\\s\\(\\d.*B\\)$");
    }
}
