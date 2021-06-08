// 
// Decompiled by Procyon v0.5.36
// 

package org.objectweb.asm.util;

import org.objectweb.asm.ModuleVisitor;

public final class TraceModuleVisitor extends ModuleVisitor
{
    public final Printer p;
    
    public TraceModuleVisitor(final Printer p) {
        this(null, p);
    }
    
    public TraceModuleVisitor(final ModuleVisitor mv, final Printer p) {
        super(393216, mv);
        this.p = p;
    }
    
    @Override
    public void visitMainClass(final String mainClass) {
        this.p.visitMainClass(mainClass);
        super.visitMainClass(mainClass);
    }
    
    @Override
    public void visitPackage(final String packaze) {
        this.p.visitPackage(packaze);
        super.visitPackage(packaze);
    }
    
    @Override
    public void visitRequire(final String module, final int access, final String version) {
        this.p.visitRequire(module, access, version);
        super.visitRequire(module, access, version);
    }
    
    @Override
    public void visitExport(final String packaze, final int access, final String... modules) {
        this.p.visitExport(packaze, access, modules);
        super.visitExport(packaze, access, modules);
    }
    
    @Override
    public void visitOpen(final String packaze, final int access, final String... modules) {
        this.p.visitOpen(packaze, access, modules);
        super.visitOpen(packaze, access, modules);
    }
    
    @Override
    public void visitUse(final String use) {
        this.p.visitUse(use);
        super.visitUse(use);
    }
    
    @Override
    public void visitProvide(final String service, final String... providers) {
        this.p.visitProvide(service, providers);
        super.visitProvide(service, providers);
    }
    
    @Override
    public void visitEnd() {
        this.p.visitModuleEnd();
        super.visitEnd();
    }
}
