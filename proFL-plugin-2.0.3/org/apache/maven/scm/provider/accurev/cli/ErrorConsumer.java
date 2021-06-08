// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.cli;

import org.apache.maven.scm.log.ScmLogger;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;

final class ErrorConsumer implements StreamConsumer
{
    private static final Pattern[] SKIPPED_WARNINGS;
    private final ScmLogger logger;
    private final StringBuilder errors;
    
    public ErrorConsumer(final ScmLogger logger, final StringBuilder errors) {
        this.logger = logger;
        this.errors = errors;
    }
    
    public void consumeLine(final String line) {
        this.errors.append(line);
        this.errors.append('\n');
        boolean matched = false;
        for (int i = this.logger.isDebugEnabled() ? ErrorConsumer.SKIPPED_WARNINGS.length : 0; !matched && i < ErrorConsumer.SKIPPED_WARNINGS.length; matched = ErrorConsumer.SKIPPED_WARNINGS[i++].matcher(line).matches()) {}
        if (!matched) {
            this.logger.warn(line);
        }
    }
    
    static {
        SKIPPED_WARNINGS = new Pattern[] { Pattern.compile(".*replica sync on the master server.*"), Pattern.compile("No elements selected.*"), Pattern.compile("You are not in a directory.*"), Pattern.compile("Note.*"), Pattern.compile("\\s+(members,|conjunction).*") };
    }
}
