// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.info;

import java.util.ArrayList;
import org.apache.maven.scm.command.info.InfoItem;
import java.util.List;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class SvnInfoConsumer implements StreamConsumer
{
    private List<InfoItem> infoItems;
    private InfoItem currentItem;
    
    public SvnInfoConsumer() {
        this.infoItems = new ArrayList<InfoItem>();
        this.currentItem = new InfoItem();
    }
    
    public void consumeLine(final String s) {
        if (s.equals("")) {
            if (this.currentItem != null) {
                this.infoItems.add(this.currentItem);
            }
            this.currentItem = new InfoItem();
        }
        else if (s.startsWith("Path: ")) {
            this.currentItem.setPath(getValue(s));
        }
        else if (s.startsWith("URL: ")) {
            this.currentItem.setURL(getValue(s));
        }
        else if (s.startsWith("Repository Root: ")) {
            this.currentItem.setRepositoryRoot(getValue(s));
        }
        else if (s.startsWith("Repository UUID: ")) {
            this.currentItem.setRepositoryUUID(getValue(s));
        }
        else if (s.startsWith("Revision: ")) {
            this.currentItem.setRevision(getValue(s));
        }
        else if (s.startsWith("Node Kind: ")) {
            this.currentItem.setNodeKind(getValue(s));
        }
        else if (s.startsWith("Schedule: ")) {
            this.currentItem.setSchedule(getValue(s));
        }
        else if (s.startsWith("Last Changed Author: ")) {
            this.currentItem.setLastChangedAuthor(getValue(s));
        }
        else if (s.startsWith("Last Changed Rev: ")) {
            this.currentItem.setLastChangedRevision(getValue(s));
        }
        else if (s.startsWith("Last Changed Date: ")) {
            this.currentItem.setLastChangedDate(getValue(s));
        }
    }
    
    private static String getValue(final String s) {
        final int idx = s.indexOf(": ");
        if (idx < 0) {
            return null;
        }
        return s.substring(idx + 2);
    }
    
    public List<InfoItem> getInfoItems() {
        return this.infoItems;
    }
}
