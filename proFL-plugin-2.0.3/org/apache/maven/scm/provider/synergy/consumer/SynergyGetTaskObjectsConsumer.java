// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.consumer;

import java.util.StringTokenizer;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ChangeFile;
import java.util.List;
import org.apache.maven.scm.util.AbstractConsumer;

public class SynergyGetTaskObjectsConsumer extends AbstractConsumer
{
    private List<ChangeFile> entries;
    public static final String OUTPUT_FORMAT = "%name#####%version#####";
    
    public List<ChangeFile> getFiles() {
        return this.entries;
    }
    
    public SynergyGetTaskObjectsConsumer(final ScmLogger logger) {
        super(logger);
        this.entries = new ArrayList<ChangeFile>();
    }
    
    public void consumeLine(final String line) {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Consume: " + line);
        }
        final StringTokenizer tokenizer = new StringTokenizer(line.trim(), "#####");
        if (tokenizer.countTokens() == 2) {
            final ChangeFile f = new ChangeFile(tokenizer.nextToken());
            f.setRevision(tokenizer.nextToken());
            this.entries.add(f);
        }
        else if (this.getLogger().isErrorEnabled()) {
            this.getLogger().error("Invalid token count in SynergyGetTaskObjects [" + tokenizer.countTokens() + "]");
        }
    }
}
