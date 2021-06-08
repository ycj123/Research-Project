// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.util;

import java.util.SortedSet;
import java.util.Iterator;
import java.util.List;
import groovy.lang.Closure;

public class SimpleCompletor extends jline.SimpleCompletor
{
    public SimpleCompletor(final String[] candidates) {
        super(candidates);
    }
    
    public SimpleCompletor() {
        this(new String[0]);
    }
    
    public SimpleCompletor(final Closure loader) {
        this();
        assert loader != null;
        final Object obj = loader.call();
        List list = null;
        if (obj instanceof List) {
            list = (List)obj;
        }
        if (list == null) {
            throw new IllegalStateException("The loader closure did not return a list of candicates; found: " + obj);
        }
        final Iterator iter = list.iterator();
        while (iter.hasNext()) {
            this.add(String.valueOf(iter.next()));
        }
    }
    
    public void add(final String candidate) {
        this.addCandidateString(candidate);
    }
    
    public Object leftShift(final String s) {
        this.add(s);
        return null;
    }
    
    public int complete(final String buffer, final int cursor, final List clist) {
        final String start = (buffer == null) ? "" : buffer;
        final SortedSet matches = this.getCandidates().tailSet(start);
        for (String can : matches) {
            if (!can.startsWith(start)) {
                break;
            }
            final String delim = this.getDelimiter();
            if (delim != null) {
                final int index = can.indexOf(delim, cursor);
                if (index != -1) {
                    can = can.substring(0, index + 1);
                }
            }
            clist.add(can);
        }
        if (clist.size() == 1) {
            clist.set(0, clist.get(0) + " ");
        }
        return (clist.size() == 0) ? -1 : 0;
    }
}
