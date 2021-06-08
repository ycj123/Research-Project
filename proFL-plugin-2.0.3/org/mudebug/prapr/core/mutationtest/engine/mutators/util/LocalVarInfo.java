// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators.util;

import org.pitest.reloc.asm.Type;

public class LocalVarInfo
{
    public final String name;
    public final String typeDescriptor;
    public final int index;
    public final int startsAt;
    public final int endsAt;
    
    LocalVarInfo(final String name, final String typeDescriptor, final int index, final int startsAt, final int endsAt) {
        if (startsAt < 0 || endsAt < 0) {
            throw new IllegalArgumentException("undefined scope");
        }
        this.name = name;
        this.typeDescriptor = typeDescriptor;
        this.index = index;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }
    
    public Type getType() {
        return Type.getType(this.typeDescriptor);
    }
    
    @Override
    public String toString() {
        return "LocalVarInfo [name=" + this.name + ", typeDescriptor=" + this.typeDescriptor + ", index=" + this.index + ", startsAt=" + this.startsAt + ", endsAt=" + this.endsAt + "]";
    }
}
