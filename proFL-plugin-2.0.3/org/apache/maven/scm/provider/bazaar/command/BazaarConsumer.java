// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import java.util.List;
import org.apache.maven.scm.ScmFileStatus;
import java.util.Map;
import org.apache.maven.scm.util.AbstractConsumer;

public class BazaarConsumer extends AbstractConsumer
{
    private static final Map<String, ScmFileStatus> IDENTIFIERS;
    private static final Map<String, String> MESSAGES;
    private static final int MAX_STDERR_SIZE = 10;
    private final List<String> stderr;
    
    public BazaarConsumer(final ScmLogger logger) {
        super(logger);
        this.stderr = new ArrayList<String>();
    }
    
    public void doConsume(final ScmFileStatus status, final String trimmedLine) {
    }
    
    public void consumeLine(final String line) {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug(line);
        }
        String trimmedLine = line.trim();
        final String statusStr = processInputForKnownIdentifiers(trimmedLine);
        if (statusStr == null) {
            final boolean isMessage = this.processInputForKnownMessages(trimmedLine);
            if (isMessage) {
                return;
            }
        }
        else {
            trimmedLine = trimmedLine.substring(statusStr.length());
            trimmedLine = trimmedLine.trim();
        }
        final ScmFileStatus status = (statusStr != null) ? BazaarConsumer.IDENTIFIERS.get(statusStr.intern()) : null;
        this.doConsume(status, trimmedLine);
    }
    
    public String getStdErr() {
        final StringBuilder str = new StringBuilder();
        for (final String line : this.stderr) {
            str.append(line);
        }
        return str.toString();
    }
    
    private static String processInputForKnownIdentifiers(final String line) {
        for (final String id : BazaarConsumer.IDENTIFIERS.keySet()) {
            if (line.startsWith(id)) {
                return id;
            }
        }
        return null;
    }
    
    private boolean processInputForKnownMessages(final String line) {
        for (final String prefix : BazaarConsumer.MESSAGES.keySet()) {
            if (line.startsWith(prefix)) {
                this.stderr.add(line);
                if (this.stderr.size() > 10) {
                    this.stderr.remove(0);
                }
                final String message = line.substring(prefix.length());
                if (BazaarConsumer.MESSAGES.get(prefix).equals("WARNING")) {
                    if (this.getLogger().isWarnEnabled()) {
                        this.getLogger().warn(message);
                    }
                }
                else if (this.getLogger().isErrorEnabled()) {
                    this.getLogger().error(message);
                }
                return true;
            }
        }
        return false;
    }
    
    static {
        IDENTIFIERS = new HashMap<String, ScmFileStatus>();
        MESSAGES = new HashMap<String, String>();
        BazaarConsumer.IDENTIFIERS.put("added", ScmFileStatus.ADDED);
        BazaarConsumer.IDENTIFIERS.put("adding", ScmFileStatus.ADDED);
        BazaarConsumer.IDENTIFIERS.put("unknown", ScmFileStatus.UNKNOWN);
        BazaarConsumer.IDENTIFIERS.put("modified", ScmFileStatus.MODIFIED);
        BazaarConsumer.IDENTIFIERS.put("removed", ScmFileStatus.DELETED);
        BazaarConsumer.IDENTIFIERS.put("renamed", ScmFileStatus.RENAMED);
        BazaarConsumer.MESSAGES.put("bzr: WARNING:", "WARNING");
        BazaarConsumer.MESSAGES.put("bzr: ERROR:", "ERROR");
        BazaarConsumer.MESSAGES.put("'bzr' ", "ERROR");
    }
}
