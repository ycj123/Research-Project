// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.login;

import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.perforce.command.AbstractPerforceConsumer;

public class PerforceLoginConsumer extends AbstractPerforceConsumer implements StreamConsumer
{
    private static final Pattern LOGIN_PATTERN;
    public static final int STATE_LOGIN = 1;
    public static final int STATE_ERROR = 2;
    private int currentState;
    
    public PerforceLoginConsumer() {
        this.currentState = 1;
    }
    
    public void consumeLine(final String line) {
        if (line.startsWith("Enter password:")) {
            return;
        }
        if (this.currentState != 2 && PerforceLoginConsumer.LOGIN_PATTERN.matcher(line).matches()) {
            return;
        }
        this.error(line);
    }
    
    private void error(final String line) {
        this.currentState = 2;
        this.output.println(line);
    }
    
    public boolean isSuccess() {
        return this.currentState == 1;
    }
    
    static {
        LOGIN_PATTERN = Pattern.compile("^User [^ ]+ logged in.$");
    }
}
