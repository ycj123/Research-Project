// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators.util;

import org.pitest.classinfo.ClassName;
import java.util.List;

public class PraPRMethodInfo
{
    public final String name;
    public final boolean isStatic;
    public final boolean isPublic;
    public final boolean isPrivate;
    public final boolean isProtected;
    public final List<LocalVarInfo> localsInfo;
    public final ClassName owningClassName;
    public final List<Integer> nullableParamIndices;
    
    PraPRMethodInfo(final int access, final String name, final List<LocalVarInfo> localsInfo, final ClassName owningClassName, final List<Integer> nullableParamIndices) {
        this.name = name;
        this.isStatic = ((access & 0x8) != 0x0);
        this.isPublic = ((access & 0x1) != 0x0);
        this.isPrivate = ((access & 0x2) != 0x0);
        this.isProtected = ((access & 0x4) != 0x0);
        this.localsInfo = localsInfo;
        this.owningClassName = owningClassName;
        this.nullableParamIndices = nullableParamIndices;
    }
}
