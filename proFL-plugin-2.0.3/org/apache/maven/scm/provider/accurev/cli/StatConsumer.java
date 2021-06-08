// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.cli;

import java.util.Map;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;

public class StatConsumer extends XppStreamConsumer
{
    private static final String ELEMENT_TAG = "element";
    private String status;
    
    public StatConsumer(final ScmLogger logger) {
        super(logger);
        this.status = null;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    @Override
    protected void startTag(final List<String> tagPath, final Map<String, String> attributes) {
        final int lastIndex = tagPath.size() - 1;
        if ("element".equalsIgnoreCase(tagPath.get(lastIndex))) {
            this.status = attributes.get("status");
        }
    }
}
