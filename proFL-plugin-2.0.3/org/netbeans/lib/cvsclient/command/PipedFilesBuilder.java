// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command;

import java.io.File;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import java.io.IOException;
import org.netbeans.lib.cvsclient.event.EventManager;

public class PipedFilesBuilder implements Builder, BinaryBuilder
{
    private static final String ERR_START = "=======";
    private static final String ERR_CHECK = "Checking out ";
    private static final String ERR_RCS = "RCS:  ";
    private static final String ERR_VERS = "VERS: ";
    private static final String EXAM_DIR = ": Updating";
    private static final byte[] lineSeparator;
    private PipedFileInformation fileInformation;
    private EventManager eventManager;
    private String fileDirectory;
    private BuildableCommand command;
    private TemporaryFileCreator tempFileCreator;
    
    public PipedFilesBuilder(final EventManager eventManager, final BuildableCommand command, final TemporaryFileCreator tempFileCreator) {
        this.eventManager = eventManager;
        this.command = command;
        this.tempFileCreator = tempFileCreator;
    }
    
    public void outputDone() {
        if (this.fileInformation == null) {
            return;
        }
        try {
            this.fileInformation.closeTempFile();
        }
        catch (IOException ex) {}
        this.eventManager.fireCVSEvent(new FileInfoEvent(this, this.fileInformation));
        this.fileInformation = null;
    }
    
    public void parseBytes(final byte[] array, final int n) {
        if (this.fileInformation == null) {
            try {
                this.fileInformation = new PipedFileInformation(File.createTempFile("checkout", null));
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            this.fileInformation.addToTempFile(array, n);
        }
        catch (IOException ex2) {
            this.outputDone();
        }
    }
    
    public void parseLine(final String s, final boolean b) {
        if (b) {
            if (s.indexOf(": Updating") >= 0) {
                this.fileDirectory = s.substring(s.indexOf(": Updating") + ": Updating".length()).trim();
            }
            else if (s.startsWith("Checking out ")) {
                this.processFile(s);
            }
            else if (s.startsWith("RCS:  ")) {
                if (this.fileInformation != null) {
                    this.fileInformation.setRepositoryFileName(s.substring("RCS:  ".length()).trim());
                }
            }
            else if (s.startsWith("VERS: ") && this.fileInformation != null) {
                this.fileInformation.setRepositoryRevision(s.substring("RCS:  ".length()).trim());
            }
        }
        else {
            if (this.fileInformation == null) {
                try {
                    this.fileInformation = new PipedFileInformation(File.createTempFile("checkout", null));
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (this.fileInformation != null) {
                try {
                    this.fileInformation.addToTempFile(s.getBytes("ISO-8859-1"));
                    this.fileInformation.addToTempFile(PipedFilesBuilder.lineSeparator);
                }
                catch (IOException ex2) {
                    this.outputDone();
                }
            }
        }
    }
    
    private void processFile(final String s) {
        this.outputDone();
        final String substring = s.substring("Checking out ".length());
        try {
            this.fileInformation = new PipedFileInformation(this.tempFileCreator.createTempFile(substring));
        }
        catch (IOException ex) {
            this.fileInformation = null;
            return;
        }
        this.fileInformation.setFile(this.createFile(substring));
    }
    
    private File createFile(final String child) {
        return new File(this.command.getLocalDirectory(), child);
    }
    
    public void parseEnhancedMessage(final String s, final Object o) {
    }
    
    static {
        lineSeparator = System.getProperty("line.separator").getBytes();
    }
}
