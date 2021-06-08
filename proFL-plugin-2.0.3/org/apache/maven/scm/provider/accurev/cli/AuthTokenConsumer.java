// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.cli;

import java.util.regex.Matcher;
import org.codehaus.plexus.util.StringUtils;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class AuthTokenConsumer implements StreamConsumer
{
    private static final Pattern TOKEN_PATTERN;
    private String authToken;
    
    public AuthTokenConsumer() {
        this.authToken = null;
    }
    
    public String getAuthToken() {
        return this.authToken;
    }
    
    public void consumeLine(final String line) {
        if (StringUtils.isBlank(this.authToken)) {
            final Matcher matcher = AuthTokenConsumer.TOKEN_PATTERN.matcher(line);
            if (matcher.matches()) {
                this.authToken = matcher.group(1);
            }
        }
    }
    
    static {
        TOKEN_PATTERN = Pattern.compile("(?:Password:|)\\s*([0-9a-f]+).*");
    }
}
