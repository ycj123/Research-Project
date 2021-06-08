// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.list;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.provider.jazz.command.consumer.AbstractRepositoryConsumer;

public class JazzListConsumer extends AbstractRepositoryConsumer
{
    private List<ScmFile> files;
    
    public JazzListConsumer(final ScmProviderRepository repository, final ScmLogger logger) {
        super(repository, logger);
        this.files = new ArrayList<ScmFile>();
    }
    
    @Override
    public void consumeLine(final String line) {
        super.consumeLine(line);
        this.files.add(new ScmFile(line, ScmFileStatus.CHECKED_IN));
    }
    
    public List<ScmFile> getFiles() {
        return this.files;
    }
}
