// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.status;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.netbeans.lib.cvsclient.file.FileStatus;
import java.io.File;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;

public class StatusInformation extends FileInfoContainer
{
    private File file;
    private FileStatus status;
    private String workingRevision;
    private String repositoryRevision;
    private String repositoryFileName;
    private String stickyDate;
    private String stickyOptions;
    private String stickyTag;
    private List tags;
    private StringBuffer symNamesBuffer;
    
    public StatusInformation() {
        this.setAllExistingTags(null);
    }
    
    public File getFile() {
        return this.file;
    }
    
    public void setFile(final File file) {
        this.file = file;
    }
    
    public FileStatus getStatus() {
        return this.status;
    }
    
    public void setStatus(final FileStatus status) {
        this.status = status;
    }
    
    public String getStatusString() {
        if (this.status == null) {
            return null;
        }
        return this.status.toString();
    }
    
    public void setStatusString(final String s) {
        this.setStatus(FileStatus.getStatusForString(s));
    }
    
    public String getWorkingRevision() {
        return this.workingRevision;
    }
    
    public void setWorkingRevision(final String workingRevision) {
        this.workingRevision = workingRevision;
    }
    
    public String getRepositoryRevision() {
        return this.repositoryRevision;
    }
    
    public void setRepositoryRevision(final String repositoryRevision) {
        this.repositoryRevision = repositoryRevision;
    }
    
    public String getRepositoryFileName() {
        return this.repositoryFileName;
    }
    
    public void setRepositoryFileName(final String repositoryFileName) {
        this.repositoryFileName = repositoryFileName;
    }
    
    public String getStickyTag() {
        return this.stickyTag;
    }
    
    public void setStickyTag(final String stickyTag) {
        this.stickyTag = stickyTag;
    }
    
    public String getStickyDate() {
        return this.stickyDate;
    }
    
    public void setStickyDate(final String stickyDate) {
        this.stickyDate = stickyDate;
    }
    
    public String getStickyOptions() {
        return this.stickyOptions;
    }
    
    public void setStickyOptions(final String stickyOptions) {
        this.stickyOptions = stickyOptions;
    }
    
    public void addExistingTag(final String str, final String str2) {
        if (this.symNamesBuffer == null) {
            this.symNamesBuffer = new StringBuffer();
        }
        this.symNamesBuffer.append(str);
        this.symNamesBuffer.append(" ");
        this.symNamesBuffer.append(str2);
        this.symNamesBuffer.append("\n");
    }
    
    private void createSymNames() {
        this.tags = new LinkedList();
        if (this.symNamesBuffer == null) {
            return;
        }
        int i = 0;
        int start = 0;
        while (i < this.symNamesBuffer.length()) {
            while (i < this.symNamesBuffer.length() && this.symNamesBuffer.charAt(i) != '\n') {
                ++i;
            }
            if (i > start) {
                final String substring = this.symNamesBuffer.substring(start, i);
                final String substring2 = substring.substring(0, substring.indexOf(32));
                final String substring3 = substring.substring(substring.indexOf(32) + 1);
                final SymName symName = new SymName();
                symName.setTag(substring2);
                symName.setRevision(substring3);
                this.tags.add(symName);
                start = i + 1;
                ++i;
            }
        }
        this.symNamesBuffer = null;
    }
    
    public List getAllExistingTags() {
        if (this.tags == null) {
            this.createSymNames();
        }
        return this.tags;
    }
    
    public void setAllExistingTags(final List tags) {
        this.tags = tags;
    }
    
    public List getSymNamesForRevision(final String anObject) {
        if (this.tags == null) {
            this.createSymNames();
        }
        final LinkedList<SymName> list = new LinkedList<SymName>();
        for (final SymName symName : this.tags) {
            if (symName.getRevision().equals(anObject)) {
                list.add(symName);
            }
        }
        return list;
    }
    
    public SymName getSymNameForTag(final String anObject) {
        if (this.tags == null) {
            this.createSymNames();
        }
        for (final SymName symName : this.tags) {
            if (symName.getTag().equals(anObject)) {
                return symName;
            }
        }
        return null;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("\nFile: ");
        sb.append((this.file != null) ? this.file.getAbsolutePath() : "null");
        sb.append("\nStatus is: ");
        sb.append(this.getStatusString());
        sb.append("\nWorking revision: ");
        sb.append(this.workingRevision);
        sb.append("\nRepository revision: ");
        sb.append("\nSticky date: ");
        sb.append(this.stickyDate);
        sb.append("\nSticky options: ");
        sb.append(this.stickyOptions);
        sb.append("\nSticky tag: ");
        sb.append(this.stickyTag);
        if (this.tags != null && this.tags.size() > 0) {
            sb.append("\nExisting Tags:");
            final Iterator<Object> iterator = this.tags.iterator();
            while (iterator.hasNext()) {
                sb.append("\n  ");
                sb.append(iterator.next().toString());
            }
        }
        return sb.toString();
    }
    
    public static class SymName
    {
        private String tag;
        private String revision;
        
        public String getTag() {
            return this.tag;
        }
        
        public void setTag(final String tag) {
            this.tag = tag;
        }
        
        public void setRevision(final String revision) {
            this.revision = revision;
        }
        
        public String getRevision() {
            return this.revision;
        }
        
        public String toString() {
            return this.getTag() + " : " + this.getRevision();
        }
    }
}
