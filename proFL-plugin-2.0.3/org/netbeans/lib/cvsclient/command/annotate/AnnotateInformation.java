// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.annotate;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;

public class AnnotateInformation extends FileInfoContainer
{
    private File file;
    private List linesList;
    private Iterator iterator;
    private File tempFile;
    private File tempDir;
    private BufferedOutputStream tempOutStream;
    
    public AnnotateInformation() {
        this.tempDir = null;
    }
    
    public AnnotateInformation(final File tempDir) {
        this.tempDir = tempDir;
    }
    
    public File getFile() {
        return this.file;
    }
    
    public void setFile(final File file) {
        this.file = file;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(30);
        sb.append("\nFile: " + ((this.file != null) ? this.file.getAbsolutePath() : "null"));
        return sb.toString();
    }
    
    public AnnotateLine createAnnotateLine() {
        return new AnnotateLine();
    }
    
    public void addLine(final AnnotateLine annotateLine) {
        this.linesList.add(annotateLine);
    }
    
    public AnnotateLine getFirstLine() {
        if (this.linesList == null) {
            this.linesList = this.createLinesList();
        }
        this.iterator = this.linesList.iterator();
        return this.getNextLine();
    }
    
    public AnnotateLine getNextLine() {
        if (this.iterator == null) {
            return null;
        }
        if (!this.iterator.hasNext()) {
            return null;
        }
        return this.iterator.next();
    }
    
    protected void addToTempFile(final String s) throws IOException {
        if (this.tempOutStream == null) {
            try {
                (this.tempFile = File.createTempFile("ann", ".cvs", this.tempDir)).deleteOnExit();
                this.tempOutStream = new BufferedOutputStream(new FileOutputStream(this.tempFile));
            }
            catch (IOException ex) {}
        }
        this.tempOutStream.write(s.getBytes());
        this.tempOutStream.write(10);
    }
    
    protected void closeTempFile() throws IOException {
        if (this.tempOutStream == null) {
            return;
        }
        try {
            this.tempOutStream.flush();
        }
        finally {
            this.tempOutStream.close();
        }
    }
    
    public File getTempFile() {
        return this.tempFile;
    }
    
    private List createLinesList() {
        final LinkedList<AnnotateLine> list = new LinkedList<AnnotateLine>();
        BufferedReader bufferedReader = null;
        if (this.tempFile == null) {
            return list;
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(this.tempFile));
            String s = bufferedReader.readLine();
            int lineNum = 1;
            while (s != null) {
                final AnnotateLine processLine = AnnotateBuilder.processLine(s);
                if (processLine != null) {
                    processLine.setLineNum(lineNum);
                    list.add(processLine);
                    ++lineNum;
                }
                s = bufferedReader.readLine();
            }
        }
        catch (IOException ex) {}
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            catch (IOException ex2) {}
        }
        return list;
    }
}
