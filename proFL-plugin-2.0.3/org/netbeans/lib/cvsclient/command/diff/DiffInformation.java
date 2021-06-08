// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.diff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;

public class DiffInformation extends FileInfoContainer
{
    private File file;
    private String repositoryFileName;
    private String rightRevision;
    private String leftRevision;
    private String parameters;
    private final List changesList;
    private Iterator iterator;
    
    public DiffInformation() {
        this.changesList = new ArrayList();
    }
    
    public File getFile() {
        return this.file;
    }
    
    public void setFile(final File file) {
        this.file = file;
    }
    
    public String getRepositoryFileName() {
        return this.repositoryFileName;
    }
    
    public void setRepositoryFileName(final String repositoryFileName) {
        this.repositoryFileName = repositoryFileName;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(30);
        sb.append("\nFile: " + ((this.file != null) ? this.file.getAbsolutePath() : "null"));
        sb.append("\nRCS file: " + this.repositoryFileName);
        sb.append("\nRevision: " + this.leftRevision);
        if (this.rightRevision != null) {
            sb.append("\nRevision: " + this.rightRevision);
        }
        sb.append("\nParameters: " + this.parameters);
        return sb.toString();
    }
    
    public String getRightRevision() {
        return this.rightRevision;
    }
    
    public void setRightRevision(final String rightRevision) {
        this.rightRevision = rightRevision;
    }
    
    public String getLeftRevision() {
        return this.leftRevision;
    }
    
    public void setLeftRevision(final String leftRevision) {
        this.leftRevision = leftRevision;
    }
    
    public String getParameters() {
        return this.parameters;
    }
    
    public void setParameters(final String parameters) {
        this.parameters = parameters;
    }
    
    public DiffChange createDiffChange() {
        return new DiffChange();
    }
    
    public void addChange(final DiffChange diffChange) {
        this.changesList.add(diffChange);
    }
    
    public DiffChange getFirstChange() {
        this.iterator = this.changesList.iterator();
        return this.getNextChange();
    }
    
    public DiffChange getNextChange() {
        if (this.iterator == null) {
            return null;
        }
        if (!this.iterator.hasNext()) {
            return null;
        }
        return this.iterator.next();
    }
    
    public class DiffChange
    {
        public static final int ADD = 0;
        public static final int DELETE = 1;
        public static final int CHANGE = 2;
        protected int type;
        private int leftBeginning;
        private int leftEnd;
        private final List leftDiff;
        private int rightBeginning;
        private int rightEnd;
        private final List rightDiff;
        
        public DiffChange() {
            this.leftBeginning = -1;
            this.leftEnd = -1;
            this.leftDiff = new ArrayList();
            this.rightBeginning = -1;
            this.rightEnd = -1;
            this.rightDiff = new ArrayList();
        }
        
        public void setType(final int type) {
            this.type = type;
        }
        
        public int getType() {
            return this.type;
        }
        
        public void setLeftRange(final int leftBeginning, final int leftEnd) {
            this.leftBeginning = leftBeginning;
            this.leftEnd = leftEnd;
        }
        
        public void setRightRange(final int rightBeginning, final int rightEnd) {
            this.rightBeginning = rightBeginning;
            this.rightEnd = rightEnd;
        }
        
        public int getMainBeginning() {
            return this.rightBeginning;
        }
        
        public int getRightMin() {
            return this.rightBeginning;
        }
        
        public int getRightMax() {
            return this.rightEnd;
        }
        
        public int getLeftMin() {
            return this.leftBeginning;
        }
        
        public int getLeftMax() {
            return this.leftEnd;
        }
        
        public boolean isInRange(final int n, final boolean b) {
            if (b) {
                return n >= this.leftBeginning && n <= this.leftEnd;
            }
            return n >= this.rightBeginning && n <= this.rightEnd;
        }
        
        public String getLine(final int n, final boolean b) {
            if (b) {
                final int n2 = n - this.leftBeginning;
                if (n2 < 0 || n2 >= this.leftDiff.size()) {
                    return null;
                }
                return (String)this.leftDiff.get(n2);
            }
            else {
                final int n3 = n - this.rightBeginning;
                if (n3 < 0 || n3 >= this.rightDiff.size()) {
                    return null;
                }
                return (String)this.rightDiff.get(n3);
            }
        }
        
        public void appendLeftLine(final String s) {
            this.leftDiff.add(s);
        }
        
        public void appendRightLine(final String s) {
            this.rightDiff.add(s);
        }
    }
}
