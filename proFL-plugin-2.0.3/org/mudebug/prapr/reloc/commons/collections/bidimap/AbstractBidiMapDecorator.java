// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.bidimap;

import org.mudebug.prapr.reloc.commons.collections.MapIterator;
import java.util.Map;
import org.mudebug.prapr.reloc.commons.collections.BidiMap;
import org.mudebug.prapr.reloc.commons.collections.map.AbstractMapDecorator;

public abstract class AbstractBidiMapDecorator extends AbstractMapDecorator implements BidiMap
{
    protected AbstractBidiMapDecorator(final BidiMap map) {
        super(map);
    }
    
    protected BidiMap getBidiMap() {
        return (BidiMap)super.map;
    }
    
    public MapIterator mapIterator() {
        return this.getBidiMap().mapIterator();
    }
    
    public Object getKey(final Object value) {
        return this.getBidiMap().getKey(value);
    }
    
    public Object removeValue(final Object value) {
        return this.getBidiMap().removeValue(value);
    }
    
    public BidiMap inverseBidiMap() {
        return this.getBidiMap().inverseBidiMap();
    }
}
