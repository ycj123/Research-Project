// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.checkin;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class PerforceCheckInConsumer implements StreamConsumer
{
    private static final Pattern CREATED_PATTERN;
    private static final Pattern SUBMITTING_PATTERN;
    private static final Pattern LOCKING_PATTERN;
    private static final Pattern OPERATION_PATTERN;
    private static final Pattern COMPLETE_PATTERN;
    public static final int STATE_CREATED = 1;
    public static final int STATE_SUBMITTING = 2;
    public static final int STATE_LOCKING = 3;
    public static final int STATE_OP = 4;
    public static final int STATE_COMPLETE = 5;
    public static final int STATE_ERROR = 6;
    private StringWriter errors;
    private PrintWriter errorOutput;
    private int currentState;
    
    public PerforceCheckInConsumer() {
        this.errors = new StringWriter();
        this.errorOutput = new PrintWriter(this.errors);
        this.currentState = 1;
    }
    
    public void consumeLine(final String line) {
        if (line.startsWith("... ")) {
            return;
        }
        switch (this.currentState) {
            case 1: {
                final boolean created = PerforceCheckInConsumer.CREATED_PATTERN.matcher(line).matches();
                if (created) {
                    ++this.currentState;
                    break;
                }
                this.error(line);
                break;
            }
            case 2: {
                final boolean submitting = PerforceCheckInConsumer.SUBMITTING_PATTERN.matcher(line).matches();
                if (submitting) {
                    ++this.currentState;
                    break;
                }
                this.error(line);
                break;
            }
            case 3: {
                final boolean locked = PerforceCheckInConsumer.LOCKING_PATTERN.matcher(line).matches();
                if (locked) {
                    ++this.currentState;
                    break;
                }
                this.error(line);
                break;
            }
            case 4: {
                final boolean operation = PerforceCheckInConsumer.OPERATION_PATTERN.matcher(line).matches();
                if (operation) {
                    break;
                }
                if (PerforceCheckInConsumer.COMPLETE_PATTERN.matcher(line).matches()) {
                    ++this.currentState;
                    break;
                }
                this.error(line);
                break;
            }
            case 6: {
                this.error(line);
                break;
            }
        }
    }
    
    private void error(final String line) {
        this.currentState = 6;
        this.errorOutput.println(line);
    }
    
    public boolean isSuccess() {
        return this.currentState == 5;
    }
    
    public String getOutput() {
        this.errorOutput.flush();
        this.errors.flush();
        return this.errors.toString();
    }
    
    static {
        CREATED_PATTERN = Pattern.compile("^Change \\d+ created .+$");
        SUBMITTING_PATTERN = Pattern.compile("^Submitting change \\d+\\.$");
        LOCKING_PATTERN = Pattern.compile("^Locking \\d+ files \\.\\.\\.$");
        OPERATION_PATTERN = Pattern.compile("^[a-z]+ //[^#]+#\\d+$");
        COMPLETE_PATTERN = Pattern.compile("^Change \\d+ .*submitted.$");
    }
}
