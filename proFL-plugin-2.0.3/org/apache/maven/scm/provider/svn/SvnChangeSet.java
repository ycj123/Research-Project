// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn;

import java.util.Iterator;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import org.apache.maven.scm.provider.ScmProviderRepository;
import java.util.Date;
import org.apache.maven.scm.ChangeFile;
import java.util.List;
import org.apache.maven.scm.ChangeSet;

public class SvnChangeSet extends ChangeSet
{
    private static final long serialVersionUID = -4454710577968060741L;
    
    public SvnChangeSet() {
    }
    
    public SvnChangeSet(final String strDate, final String userDatePattern, final String comment, final String author, final List<ChangeFile> files) {
        super(strDate, userDatePattern, comment, author, files);
    }
    
    public SvnChangeSet(final Date date, final String comment, final String author, final List<ChangeFile> files) {
        super(date, comment, author, files);
    }
    
    @Override
    public boolean containsFilename(final String filename, final ScmProviderRepository repository) {
        final SvnScmProviderRepository repo = (SvnScmProviderRepository)repository;
        String url = repo.getUrl();
        if (!url.endsWith("/")) {
            url += "/";
        }
        final String currentFile = url + StringUtils.replace(filename, "\\", "/");
        if (this.getFiles() != null) {
            for (final ChangeFile file : this.getFiles()) {
                if (currentFile.endsWith(StringUtils.replace(file.getName(), "\\", "/"))) {
                    return true;
                }
            }
        }
        return false;
    }
}
