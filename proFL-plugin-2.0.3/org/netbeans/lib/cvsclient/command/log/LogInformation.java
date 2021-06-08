// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.log;

import org.netbeans.lib.cvsclient.util.BugLog;
import java.util.Date;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.text.SimpleDateFormat;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;

public class LogInformation extends FileInfoContainer
{
    private static final SimpleDateFormat DATE_FORMAT;
    private File file;
    private String repositoryFilename;
    private String headRevision;
    private String branch;
    private String accessList;
    private String keywordSubstitution;
    private String totalRevisions;
    private String selectedRevisions;
    private String description;
    private String locks;
    private final List revisions;
    private final List symbolicNames;
    private StringBuffer symNamesBuffer;
    
    public LogInformation() {
        this.revisions = new ArrayList();
        this.symbolicNames = new ArrayList();
    }
    
    public File getFile() {
        return this.file;
    }
    
    public void setFile(final File file) {
        this.file = file;
    }
    
    public String getRepositoryFilename() {
        return this.repositoryFilename;
    }
    
    public void setRepositoryFilename(final String repositoryFilename) {
        this.repositoryFilename = repositoryFilename;
    }
    
    public String getHeadRevision() {
        return this.headRevision;
    }
    
    public void setHeadRevision(final String headRevision) {
        this.headRevision = headRevision;
    }
    
    public String getBranch() {
        return this.branch;
    }
    
    public void setBranch(final String branch) {
        this.branch = branch;
    }
    
    public String getAccessList() {
        return this.accessList;
    }
    
    public void setAccessList(final String accessList) {
        this.accessList = accessList;
    }
    
    public String getKeywordSubstitution() {
        return this.keywordSubstitution;
    }
    
    public void setKeywordSubstitution(final String keywordSubstitution) {
        this.keywordSubstitution = keywordSubstitution;
    }
    
    public String getTotalRevisions() {
        return this.totalRevisions;
    }
    
    public void setTotalRevisions(final String totalRevisions) {
        this.totalRevisions = totalRevisions;
    }
    
    public String getSelectedRevisions() {
        return this.selectedRevisions;
    }
    
    public void setSelectedRevisions(final String selectedRevisions) {
        this.selectedRevisions = selectedRevisions;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public String getLocks() {
        return this.locks;
    }
    
    public void setLocks(final String locks) {
        this.locks = locks;
    }
    
    public void addRevision(final Revision revision) {
        this.revisions.add(revision);
    }
    
    public List getRevisionList() {
        return this.revisions;
    }
    
    public Revision getRevision(final String anObject) {
        for (final Revision revision : this.revisions) {
            if (revision.getNumber().equals(anObject)) {
                return revision;
            }
        }
        return null;
    }
    
    public void addSymbolicName(final String name, final String revision) {
        final SymName symName = new SymName();
        symName.setName(name);
        symName.setRevision(revision);
        this.symbolicNames.add(symName);
    }
    
    public List getAllSymbolicNames() {
        return this.symbolicNames;
    }
    
    public List getSymNamesForRevision(final String anObject) {
        final Iterator<SymName> iterator = this.symbolicNames.iterator();
        final LinkedList<SymName> list = new LinkedList<SymName>();
        while (iterator.hasNext()) {
            final SymName symName = iterator.next();
            if (symName.getRevision().equals(anObject)) {
                list.add(symName);
            }
        }
        return list;
    }
    
    public SymName getSymName(final String anObject) {
        for (final SymName symName : this.symbolicNames) {
            if (symName.getName().equals(anObject)) {
                return symName;
            }
        }
        return null;
    }
    
    public Revision createNewRevision(final String number) {
        final Revision revision = new Revision();
        revision.setNumber(number);
        return revision;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(30);
        sb.append("\nFile: " + ((this.file != null) ? this.file.getAbsolutePath() : "null"));
        sb.append("\nRepositoryFile: " + this.repositoryFilename);
        sb.append("\nHead revision: " + this.headRevision);
        return sb.toString();
    }
    
    static {
        DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    }
    
    public class SymName
    {
        private String name;
        private String revision;
        
        public String getName() {
            return this.name;
        }
        
        public void setName(final String name) {
            this.name = name;
        }
        
        public void setRevision(final String revision) {
            this.revision = revision;
        }
        
        public String getRevision() {
            return this.revision;
        }
        
        public final boolean isBranch() {
            boolean equals = false;
            final String[] split = this.revision.split("\\.");
            if (split.length > 2 && split.length % 2 == 0) {
                equals = "0".equals(split[split.length - 2]);
            }
            return equals;
        }
    }
    
    public class Revision
    {
        private String number;
        private Date date;
        private String dateString;
        private String author;
        private String state;
        private String lines;
        private String commitID;
        private String message;
        private String branches;
        
        public LogInformation getLogInfoHeader() {
            return LogInformation.this;
        }
        
        public String getNumber() {
            return this.number;
        }
        
        public void setNumber(final String number) {
            this.number = number;
        }
        
        public Date getDate() {
            return this.date;
        }
        
        public String getDateString() {
            return this.dateString;
        }
        
        public void setDateString(String string) {
            this.dateString = string;
            if (string == null) {
                this.date = null;
                return;
            }
            try {
                string = string.replace('/', '-') + " +0000";
                this.date = LogInformation.DATE_FORMAT.parse(string);
            }
            catch (Exception ex) {
                BugLog.getInstance().bug("Couldn't parse date " + string);
            }
        }
        
        public String getAuthor() {
            return this.author;
        }
        
        public void setAuthor(final String author) {
            this.author = author;
        }
        
        public String getState() {
            return this.state;
        }
        
        public void setState(final String state) {
            this.state = state;
        }
        
        public String getLines() {
            return this.lines;
        }
        
        public void setLines(final String lines) {
            this.lines = lines;
        }
        
        public String getCommitID() {
            return this.commitID;
        }
        
        public void setCommitID(final String commitID) {
            this.commitID = commitID;
        }
        
        public int getAddedLines() {
            if (this.lines != null) {
                final int index = this.lines.indexOf(43);
                final int index2 = this.lines.indexOf(32);
                if (index >= 0 && index2 > index) {
                    final String substring = this.lines.substring(index + 1, index2);
                    try {
                        return Integer.parseInt(substring);
                    }
                    catch (NumberFormatException ex) {}
                }
            }
            return 0;
        }
        
        public int getRemovedLines() {
            if (this.lines != null) {
                final int index = this.lines.indexOf(45);
                if (index >= 0) {
                    final String substring = this.lines.substring(index + 1);
                    try {
                        return Integer.parseInt(substring);
                    }
                    catch (NumberFormatException ex) {}
                }
            }
            return 0;
        }
        
        public String getMessage() {
            return this.message;
        }
        
        public void setMessage(final String message) {
            this.message = message;
        }
        
        public String getBranches() {
            return this.branches;
        }
        
        public void setBranches(final String branches) {
            this.branches = branches;
        }
    }
}
