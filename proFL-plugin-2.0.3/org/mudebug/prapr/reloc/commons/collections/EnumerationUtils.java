// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections;

import java.util.Iterator;
import org.mudebug.prapr.reloc.commons.collections.iterators.EnumerationIterator;
import java.util.List;
import java.util.Enumeration;

public class EnumerationUtils
{
    public static List toList(final Enumeration enumeration) {
        return IteratorUtils.toList(new EnumerationIterator(enumeration));
    }
}
