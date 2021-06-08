// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.cli;

import java.util.Date;
import java.util.Map;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.accurev.Transaction;
import java.util.List;

public class HistoryConsumer extends XppStreamConsumer
{
    private List<Transaction> transactions;
    private Transaction currentTran;
    private Long elementId;
    private String elementName;
    
    public HistoryConsumer(final ScmLogger logger, final List<Transaction> transactions) {
        super(logger);
        this.transactions = transactions;
    }
    
    @Override
    protected void startTag(final List<String> tagPath, final Map<String, String> attributes) {
        final String tagName = XppStreamConsumer.getTagName(tagPath);
        if ("transaction".equals(tagName)) {
            final Long id = Long.parseLong(attributes.get("id"));
            final Date when = new Date(Long.parseLong(attributes.get("time")) * 1000L);
            final String tranType = attributes.get("type");
            final String user = attributes.get("user");
            this.currentTran = new Transaction(id, when, tranType, user);
            this.transactions.add(this.currentTran);
        }
        else if ("version".equals(tagName)) {
            if (this.currentTran != null) {
                if (attributes.containsKey("eid")) {
                    this.elementId = Long.parseLong(attributes.get("eid"));
                    this.elementName = attributes.get("path");
                }
                final String virtualSpec = attributes.get("virtual");
                final String realSpec = attributes.get("real");
                final String ancestor = attributes.get("ancestor");
                this.currentTran.addVersion(this.elementId, this.elementName, virtualSpec, realSpec, ancestor);
            }
        }
        else if ("element".equals(tagName)) {
            this.elementId = Long.parseLong(attributes.get("eid"));
            this.elementName = attributes.get("name");
        }
    }
    
    @Override
    protected void endTag(final List<String> tagPath) {
        final String tagName = XppStreamConsumer.getTagName(tagPath);
        if ("element".equals(tagName)) {
            this.elementId = null;
            this.elementName = null;
        }
        else if ("transaction".equals(tagName)) {
            this.currentTran = null;
        }
    }
    
    @Override
    protected void text(final List<String> tagPath, final String text) {
        final String tagName = XppStreamConsumer.getTagName(tagPath);
        if (this.currentTran != null && "comment".equals(tagName)) {
            this.currentTran.setComment(text);
        }
    }
}
