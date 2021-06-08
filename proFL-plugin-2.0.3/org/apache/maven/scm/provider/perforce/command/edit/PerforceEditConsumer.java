// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.edit;

import java.util.regex.Matcher;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.perforce.command.AbstractPerforceConsumer;

public class PerforceEditConsumer extends AbstractPerforceConsumer implements StreamConsumer
{
    private static final Pattern PATTERN;
    private static final String FILE_BEGIN_TOKEN = "//";
    private List<ScmFile> edits;
    private boolean errors;
    private StringBuilder errorMessage;
    
    public PerforceEditConsumer() {
        this.edits = new ArrayList<ScmFile>();
        this.errors = false;
        this.errorMessage = new StringBuilder();
    }
    
    public List<ScmFile> getEdits() {
        return this.edits;
    }
    
    public void consumeLine(final String line) {
        if (line.startsWith("... ")) {
            return;
        }
        if (!line.startsWith("//")) {
            this.error(line);
        }
        final Matcher matcher = PerforceEditConsumer.PATTERN.matcher(line);
        if (!matcher.matches()) {
            this.error(line);
        }
        this.edits.add(new ScmFile(matcher.group(1), ScmFileStatus.EDITED));
    }
    
    private void error(final String line) {
        this.errors = true;
        this.output.println(line);
        if (this.errorMessage.length() > 0) {
            this.errorMessage.append(System.getProperty("line.separator"));
        }
        this.errorMessage.append(line);
    }
    
    public boolean isSuccess() {
        return !this.errors;
    }
    
    public String getErrorMessage() {
        return this.errorMessage.toString();
    }
    
    static {
        PATTERN = Pattern.compile("^([^#]+)#\\d+ - (.*)");
    }
}
