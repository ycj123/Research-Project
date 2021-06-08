// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators.util;

import java.util.Iterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectedClassInfo
{
    public final Map<String, List<FieldInfo>> fieldsInfo;
    public final Map<String, List<PraPRMethodInfo>> methodsInfo;
    private boolean itf;
    
    CollectedClassInfo() {
        this.fieldsInfo = new HashMap<String, List<FieldInfo>>();
        this.methodsInfo = new HashMap<String, List<PraPRMethodInfo>>();
    }
    
    public PraPRMethodInfo findMethod(final String name, final String desc) {
        final List<PraPRMethodInfo> mil = this.methodsInfo.get(desc);
        for (final PraPRMethodInfo mi : mil) {
            if (mi.name.equals(name)) {
                return mi;
            }
        }
        return null;
    }
    
    public boolean isInterface() {
        return this.itf;
    }
    
    public void setInterface(final boolean itf) {
        this.itf = itf;
    }
}
