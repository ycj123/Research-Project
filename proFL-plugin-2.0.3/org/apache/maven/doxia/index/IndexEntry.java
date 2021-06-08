// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.index;

import java.util.Iterator;
import org.codehaus.plexus.util.StringUtils;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class IndexEntry
{
    private IndexEntry parent;
    private String id;
    private String title;
    private List childEntries;
    private static final String EOL;
    
    public IndexEntry(final String newId) {
        this.childEntries = new ArrayList();
        this.id = newId;
    }
    
    public IndexEntry(final IndexEntry newParent, final String newId) {
        this.childEntries = new ArrayList();
        if (newParent == null) {
            throw new NullPointerException("parent cannot be null.");
        }
        if (newId == null) {
            throw new NullPointerException("id cannot be null.");
        }
        this.parent = newParent;
        this.id = newId;
        this.parent.childEntries.add(this);
    }
    
    public IndexEntry getParent() {
        return this.parent;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(final String newTitle) {
        this.title = newTitle;
    }
    
    public List getChildEntries() {
        return Collections.unmodifiableList((List<?>)this.childEntries);
    }
    
    public void setChildEntries(final List entries) {
        if (entries == null) {
            this.childEntries = new ArrayList();
        }
        this.childEntries = entries;
    }
    
    public IndexEntry getNextEntry() {
        if (this.parent == null) {
            return null;
        }
        final List entries = this.parent.getChildEntries();
        final int index = entries.indexOf(this);
        if (index + 1 >= entries.size()) {
            return null;
        }
        return entries.get(index + 1);
    }
    
    public IndexEntry getPrevEntry() {
        if (this.parent == null) {
            return null;
        }
        final List entries = this.parent.getChildEntries();
        final int index = entries.indexOf(this);
        if (index == 0) {
            return null;
        }
        return entries.get(index - 1);
    }
    
    public IndexEntry getFirstEntry() {
        final List entries = this.getChildEntries();
        if (entries.size() == 0) {
            return null;
        }
        return entries.get(0);
    }
    
    public IndexEntry getLastEntry() {
        final List entries = this.getChildEntries();
        if (entries.size() == 0) {
            return null;
        }
        return entries.get(entries.size() - 1);
    }
    
    public IndexEntry getRootEntry() {
        final List entries = this.getChildEntries();
        if (entries.size() == 0) {
            return null;
        }
        if (entries.size() > 1) {
            throw new RuntimeException("This index has more than one root entry");
        }
        return entries.get(0);
    }
    
    public String toString() {
        return this.toString(0);
    }
    
    public String toString(final int depth) {
        final StringBuffer message = new StringBuffer();
        message.append("Id: ").append(this.id);
        if (StringUtils.isNotEmpty(this.title)) {
            message.append(", title: ").append(this.title);
        }
        message.append(IndexEntry.EOL);
        String indent = "";
        for (int i = 0; i < depth; ++i) {
            indent += " ";
        }
        for (final IndexEntry entry : this.getChildEntries()) {
            message.append(indent).append(entry.toString(depth + 1));
        }
        return message.toString();
    }
    
    static {
        EOL = System.getProperty("line.separator");
    }
}
