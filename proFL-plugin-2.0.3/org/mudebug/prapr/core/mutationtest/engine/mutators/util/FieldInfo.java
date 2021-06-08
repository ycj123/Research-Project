// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators.util;

import org.pitest.classinfo.ClassName;

public class FieldInfo
{
    public final boolean isStatic;
    public final boolean isPublic;
    public final boolean isFinal;
    public final String name;
    public final ClassName owningClassName;
    
    FieldInfo(final int access, final String name, final ClassName owningClassName) {
        this.isStatic = ((access & 0x8) != 0x0);
        this.isPublic = ((access & 0x1) != 0x0);
        this.isFinal = ((access & 0x10) != 0x0);
        this.name = name;
        this.owningClassName = owningClassName;
    }
}
