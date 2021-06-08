// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command;

import java.util.Iterator;
import org.netbeans.lib.cvsclient.request.SetRequest;
import org.netbeans.lib.cvsclient.request.GlobalOptionRequest;
import java.util.LinkedList;
import java.util.ArrayList;
import java.io.File;
import java.util.List;

public class GlobalOptions implements Cloneable
{
    private List variables;
    private boolean doNoChanges;
    private boolean checkedOutFilesReadOnly;
    private String cvsRoot;
    private boolean useGzip;
    private int compressionLevel;
    private boolean noHistoryLogging;
    private boolean moderatelyQuiet;
    private boolean veryQuiet;
    private boolean traceExecution;
    private boolean showHelp;
    private boolean showVersion;
    private boolean ignoreCvsrc;
    private File tempDir;
    private String editor;
    private File[] exclusions;
    
    public GlobalOptions() {
        this.useGzip = true;
        this.compressionLevel = 0;
        this.variables = new ArrayList();
    }
    
    public void setExclusions(final File[] exclusions) {
        this.exclusions = exclusions;
    }
    
    public File[] getExclusions() {
        return this.exclusions;
    }
    
    public boolean isExcluded(final File file) {
        if (this.exclusions != null) {
            for (int i = 0; i < this.exclusions.length; ++i) {
                if (isParentOrEqual(this.exclusions[i], file)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean isParentOrEqual(final File obj, File parentFile) {
        while (parentFile != null) {
            if (parentFile.equals(obj)) {
                return true;
            }
            parentFile = parentFile.getParentFile();
        }
        return false;
    }
    
    public List createRequestList() {
        final LinkedList<GlobalOptionRequest> list = new LinkedList<GlobalOptionRequest>();
        if (this.variables.size() > 0) {
            final Iterator<Object> iterator = this.variables.iterator();
            while (iterator.hasNext()) {
                list.add(new SetRequest(iterator.next().toString()));
            }
        }
        if (this.isNoHistoryLogging()) {
            list.add((SetRequest)new GlobalOptionRequest("-l"));
        }
        if (this.isDoNoChanges()) {
            list.add((SetRequest)new GlobalOptionRequest("-n"));
        }
        if (this.isModeratelyQuiet()) {
            list.add((SetRequest)new GlobalOptionRequest("-q"));
        }
        if (this.isVeryQuiet()) {
            list.add((SetRequest)new GlobalOptionRequest("-Q"));
        }
        if (this.isTraceExecution()) {
            list.add((SetRequest)new GlobalOptionRequest("-t"));
        }
        return list;
    }
    
    public String getOptString() {
        return "Hvnfd:lqQtrws:z:T:e:";
    }
    
    public boolean setCVSCommand(final char c, final String editor) {
        if (c == 'n') {
            this.setDoNoChanges(true);
        }
        else if (c == 'd') {
            this.setCVSRoot(editor);
        }
        else if (c == 'l') {
            this.setNoHistoryLogging(true);
        }
        else if (c == 'q') {
            this.setModeratelyQuiet(true);
        }
        else if (c == 'Q') {
            this.setVeryQuiet(true);
        }
        else if (c == 't') {
            this.setTraceExecution(true);
        }
        else if (c == 't') {
            this.setTraceExecution(true);
        }
        else if (c == 'r') {
            this.setCheckedOutFilesReadOnly(true);
        }
        else if (c == 'w') {
            this.setCheckedOutFilesReadOnly(false);
        }
        else if (c == 's') {
            this.setCvsVariable(editor);
        }
        else if (c == 'z') {
            try {
                this.setCompressionLevel(Integer.parseInt(editor));
            }
            catch (NumberFormatException ex) {}
        }
        else if (c == 'H') {
            this.setShowHelp(true);
        }
        else if (c == 'v') {
            this.setShowVersion(true);
        }
        else if (c == 'f') {
            this.setIgnoreCvsrc(true);
        }
        else if (c == 'T') {
            this.setTempDir(new File(editor));
        }
        else {
            if (c != 'e') {
                return false;
            }
            this.setEditor(editor);
        }
        return true;
    }
    
    public void resetCVSCommand() {
        this.setCheckedOutFilesReadOnly(false);
        this.setDoNoChanges(false);
        this.setModeratelyQuiet(false);
        this.setNoHistoryLogging(false);
        this.setTraceExecution(false);
        this.setUseGzip(true);
        this.setCompressionLevel(0);
        this.setVeryQuiet(false);
        this.setShowHelp(false);
        this.setShowVersion(false);
        this.setIgnoreCvsrc(false);
        this.setTempDir(null);
        this.setEditor(null);
        this.setCVSRoot("");
        this.clearCvsVariables();
    }
    
    public String getCVSCommand() {
        final StringBuffer sb = new StringBuffer();
        if (this.isDoNoChanges()) {
            sb.append("-n ");
        }
        if (this.isNoHistoryLogging()) {
            sb.append("-l ");
        }
        if (this.isModeratelyQuiet()) {
            sb.append("-q ");
        }
        if (this.isVeryQuiet()) {
            sb.append("-Q ");
        }
        if (this.isTraceExecution()) {
            sb.append("-t ");
        }
        if (this.isCheckedOutFilesReadOnly()) {
            sb.append("-r ");
        }
        if (this.variables.size() > 0) {
            final Iterator<Object> iterator = this.variables.iterator();
            while (iterator.hasNext()) {
                sb.append("-s " + iterator.next().toString() + " ");
            }
        }
        if (this.compressionLevel != 0) {
            sb.append("-z ");
            sb.append(Integer.toString(this.compressionLevel));
            sb.append(" ");
        }
        if (this.isIgnoreCvsrc()) {
            sb.append("-f ");
        }
        if (this.tempDir != null) {
            sb.append("-T ");
            sb.append(this.tempDir.getAbsolutePath());
            sb.append(" ");
        }
        if (this.editor != null) {
            sb.append("-e ");
            sb.append(this.editor);
            sb.append(" ");
        }
        return sb.toString();
    }
    
    public void setCvsVariable(final String s) {
        this.variables.add(s);
    }
    
    public void clearCvsVariables() {
        this.variables.clear();
    }
    
    public void setCvsVariables(final String[] array) {
        this.clearCvsVariables();
        for (int i = 0; i < array.length; ++i) {
            this.variables.add(array[i]);
        }
    }
    
    public String[] getCvsVariables() {
        return this.variables.toArray(new String[this.variables.size()]);
    }
    
    public void setDoNoChanges(final boolean doNoChanges) {
        this.doNoChanges = doNoChanges;
    }
    
    public boolean isDoNoChanges() {
        return this.doNoChanges;
    }
    
    public boolean isCheckedOutFilesReadOnly() {
        return this.checkedOutFilesReadOnly;
    }
    
    public void setCheckedOutFilesReadOnly(final boolean checkedOutFilesReadOnly) {
        this.checkedOutFilesReadOnly = checkedOutFilesReadOnly;
    }
    
    public String getCVSRoot() {
        return this.cvsRoot;
    }
    
    public void setCVSRoot(final String cvsRoot) {
        this.cvsRoot = cvsRoot;
    }
    
    public void setUseGzip(final boolean useGzip) {
        this.useGzip = useGzip;
    }
    
    public boolean isUseGzip() {
        return this.useGzip;
    }
    
    public int getCompressionLevel() {
        return this.compressionLevel;
    }
    
    public void setCompressionLevel(final int compressionLevel) {
        this.compressionLevel = compressionLevel;
    }
    
    public boolean isNoHistoryLogging() {
        return this.noHistoryLogging;
    }
    
    public void setNoHistoryLogging(final boolean noHistoryLogging) {
        this.noHistoryLogging = noHistoryLogging;
    }
    
    public boolean isModeratelyQuiet() {
        return this.moderatelyQuiet;
    }
    
    public void setModeratelyQuiet(final boolean moderatelyQuiet) {
        this.moderatelyQuiet = moderatelyQuiet;
    }
    
    public boolean isVeryQuiet() {
        return this.veryQuiet;
    }
    
    public void setVeryQuiet(final boolean veryQuiet) {
        this.veryQuiet = veryQuiet;
    }
    
    public boolean isTraceExecution() {
        return this.traceExecution;
    }
    
    public void setTraceExecution(final boolean traceExecution) {
        this.traceExecution = traceExecution;
    }
    
    public boolean isShowHelp() {
        return this.showHelp;
    }
    
    public void setShowHelp(final boolean showHelp) {
        this.showHelp = showHelp;
    }
    
    public boolean isShowVersion() {
        return this.showVersion;
    }
    
    public void setShowVersion(final boolean showVersion) {
        this.showVersion = showVersion;
    }
    
    public boolean isIgnoreCvsrc() {
        return this.ignoreCvsrc;
    }
    
    public void setIgnoreCvsrc(final boolean ignoreCvsrc) {
        this.ignoreCvsrc = ignoreCvsrc;
    }
    
    public File getTempDir() {
        return this.tempDir;
    }
    
    public void setTempDir(final File tempDir) {
        this.tempDir = tempDir;
    }
    
    public String getEditor() {
        return this.editor;
    }
    
    public void setEditor(final String editor) {
        this.editor = editor;
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}
