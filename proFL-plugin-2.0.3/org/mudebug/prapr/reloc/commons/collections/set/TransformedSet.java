// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.set;

import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.Transformer;
import java.util.Set;
import org.mudebug.prapr.reloc.commons.collections.collection.TransformedCollection;

public class TransformedSet extends TransformedCollection implements Set
{
    private static final long serialVersionUID = 306127383500410386L;
    
    public static Set decorate(final Set set, final Transformer transformer) {
        return new TransformedSet(set, transformer);
    }
    
    protected TransformedSet(final Set set, final Transformer transformer) {
        super(set, transformer);
    }
}
