// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.xhtml;

import java.util.Map;
import java.util.HashMap;

public class StringsMap extends HashMap
{
    private Map map;
    
    public StringsMap(final Map map) {
        this.map = map;
    }
    
    public String get(final String key) {
        return this.map.get(key);
    }
}
