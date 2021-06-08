// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.lifecycle.mapping;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class DefaultLifecycleMapping implements LifecycleMapping
{
    private List lifecycles;
    private Map lifecycleMap;
    private Map phases;
    
    public List getOptionalMojos(final String lifecycle) {
        if (this.lifecycleMap == null) {
            this.lifecycleMap = new HashMap();
            if (this.lifecycles != null) {
                for (final Lifecycle l : this.lifecycles) {
                    this.lifecycleMap.put(l.getId(), l);
                }
            }
        }
        final Lifecycle j = this.lifecycleMap.get(lifecycle);
        if (j != null) {
            return j.getOptionalMojos();
        }
        return null;
    }
    
    public Map getPhases(final String lifecycle) {
        if (this.lifecycleMap == null) {
            this.lifecycleMap = new HashMap();
            if (this.lifecycles != null) {
                for (final Lifecycle l : this.lifecycles) {
                    this.lifecycleMap.put(l.getId(), l);
                }
            }
        }
        final Lifecycle j = this.lifecycleMap.get(lifecycle);
        Map mappings = null;
        if (j == null) {
            if ("default".equals(lifecycle)) {
                mappings = this.phases;
            }
        }
        else {
            mappings = j.getPhases();
        }
        return mappings;
    }
}
