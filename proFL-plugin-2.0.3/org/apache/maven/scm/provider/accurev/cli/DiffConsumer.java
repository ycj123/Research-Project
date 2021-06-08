// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.cli;

import java.util.Map;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.accurev.FileDifference;
import java.util.List;

public class DiffConsumer extends XppStreamConsumer
{
    private List<FileDifference> results;
    private FileDifference currentDifference;
    
    public DiffConsumer(final ScmLogger logger, final List<FileDifference> results) {
        super(logger);
        this.results = results;
    }
    
    @Override
    protected void startTag(final List<String> tagPath, final Map<String, String> attributes) {
        final String tagName = XppStreamConsumer.getTagName(tagPath);
        if ("Element".equals(tagName)) {
            this.currentDifference = new FileDifference();
        }
        else if ("Stream2".equals(tagName) && attributes.get("Name") != null) {
            this.currentDifference.setElementId(Long.parseLong(attributes.get("eid")));
            this.currentDifference.setNewVersion(attributes.get("Name"), attributes.get("Version"));
        }
        else if ("Stream1".equals(tagName) && attributes.get("Name") != null) {
            this.currentDifference.setElementId(Long.parseLong(attributes.get("eid")));
            this.currentDifference.setOldVersion(attributes.get("Name"), attributes.get("Version"));
        }
    }
    
    @Override
    protected void endTag(final List<String> tagPath) {
        final String tagName = XppStreamConsumer.getTagName(tagPath);
        if ("Element".equals(tagName) && (this.currentDifference.getNewFile() != null || this.currentDifference.getOldFile() != null)) {
            this.results.add(this.currentDifference);
        }
    }
}
