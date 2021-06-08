// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators.util;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.pitest.reloc.asm.Label;
import org.pitest.reloc.asm.MethodVisitor;

class CollectorMethodVisitor extends MethodVisitor
{
    private final Indexer<Label> labelIndexer;
    private final List<RawLocalInfo> locals;
    private final List<LocalVarInfo> localsInfo;
    
    CollectorMethodVisitor(final MethodVisitor mv) {
        super(393216, mv);
        this.labelIndexer = new Indexer<Label>();
        this.locals = new ArrayList<RawLocalInfo>();
        this.localsInfo = new ArrayList<LocalVarInfo>();
    }
    
    @Override
    public void visitLabel(final Label label) {
        this.labelIndexer.index(label);
        super.visitLabel(label);
    }
    
    @Override
    public void visitLocalVariable(final String name, final String desc, final String signature, final Label start, final Label end, final int index) {
        this.locals.add(new RawLocalInfo(name, desc, start, end, index));
        super.visitLocalVariable(name, desc, signature, start, end, index);
    }
    
    @Override
    public void visitEnd() {
        for (final RawLocalInfo rli : this.locals) {
            final LocalVarInfo lvi = new LocalVarInfo(rli.name, rli.desc, rli.index, this.labelIndexer.indexOf(rli.start), this.labelIndexer.indexOf(rli.end));
            this.localsInfo.add(lvi);
        }
        super.visitEnd();
    }
    
    public List<LocalVarInfo> getLocalsInfo() {
        return this.localsInfo;
    }
    
    private static class RawLocalInfo
    {
        final String name;
        final String desc;
        final Label start;
        final Label end;
        final int index;
        
        RawLocalInfo(final String name, final String desc, final Label start, final Label end, final int index) {
            this.name = name;
            this.desc = desc;
            this.start = start;
            this.end = end;
            this.index = index;
        }
    }
}
