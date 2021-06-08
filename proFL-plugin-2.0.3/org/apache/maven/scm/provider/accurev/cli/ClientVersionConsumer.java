// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.cli;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class ClientVersionConsumer implements StreamConsumer
{
    private static final Pattern CLIENT_VERSION_PATTERN;
    private String clientVersion;
    
    public void consumeLine(final String line) {
        if (this.clientVersion == null) {
            final Matcher matcher = ClientVersionConsumer.CLIENT_VERSION_PATTERN.matcher(line);
            if (matcher.matches()) {
                this.clientVersion = matcher.group(1);
            }
        }
    }
    
    public String getClientVersion() {
        return this.clientVersion;
    }
    
    static {
        CLIENT_VERSION_PATTERN = Pattern.compile("^AccuRev ([0-9a-z.]+) .*");
    }
}
