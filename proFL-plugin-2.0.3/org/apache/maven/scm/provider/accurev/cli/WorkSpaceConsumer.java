// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.cli;

import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.accurev.WorkSpace;
import java.util.Map;

public class WorkSpaceConsumer extends XppStreamConsumer
{
    private Map<String, WorkSpace> workSpaces;
    
    public WorkSpaceConsumer(final ScmLogger logger, final Map<String, WorkSpace> workSpaces) {
        super(logger);
        this.workSpaces = workSpaces;
    }
    
    @Override
    protected void startTag(final List<String> tagPath, final Map<String, String> attributes) {
        if ("Element".equals(XppStreamConsumer.getTagName(tagPath))) {
            final String name = attributes.get("Name");
            final long transactionId = Long.valueOf(attributes.get("Trans"));
            final WorkSpace ws = new WorkSpace(name, transactionId);
            this.workSpaces.put(name, ws);
        }
    }
}
