// 
// Decompiled by Procyon v0.5.36
// 

package org.objectweb.asm.util;

import java.util.HashSet;
import org.objectweb.asm.ModuleVisitor;

public final class CheckModuleAdapter extends ModuleVisitor
{
    private boolean end;
    private final boolean isOpen;
    private final HashSet<String> requireNames;
    private final HashSet<String> exportNames;
    private final HashSet<String> openNames;
    private final HashSet<String> useNames;
    private final HashSet<String> provideNames;
    
    public CheckModuleAdapter(final ModuleVisitor mv, final boolean isOpen) {
        super(393216, mv);
        this.requireNames = new HashSet<String>();
        this.exportNames = new HashSet<String>();
        this.openNames = new HashSet<String>();
        this.useNames = new HashSet<String>();
        this.provideNames = new HashSet<String>();
        this.isOpen = isOpen;
    }
    
    @Override
    public void visitRequire(final String module, final int access, final String version) {
        this.checkEnd();
        if (module == null) {
            throw new IllegalArgumentException("require cannot be null");
        }
        checkDeclared("requires", this.requireNames, module);
        CheckClassAdapter.checkAccess(access, 36960);
        super.visitRequire(module, access, version);
    }
    
    @Override
    public void visitExport(final String packaze, final int access, final String... modules) {
        this.checkEnd();
        if (packaze == null) {
            throw new IllegalArgumentException("packaze cannot be null");
        }
        CheckMethodAdapter.checkInternalName(packaze, "package name");
        checkDeclared("exports", this.exportNames, packaze);
        CheckClassAdapter.checkAccess(access, 36864);
        if (modules != null) {
            for (int i = 0; i < modules.length; ++i) {
                if (modules[i] == null) {
                    throw new IllegalArgumentException("module at index " + i + " cannot be null");
                }
            }
        }
        super.visitExport(packaze, access, modules);
    }
    
    @Override
    public void visitOpen(final String packaze, final int access, final String... modules) {
        this.checkEnd();
        if (this.isOpen) {
            throw new IllegalArgumentException("an open module can not use open directive");
        }
        if (packaze == null) {
            throw new IllegalArgumentException("packaze cannot be null");
        }
        CheckMethodAdapter.checkInternalName(packaze, "package name");
        checkDeclared("opens", this.openNames, packaze);
        CheckClassAdapter.checkAccess(access, 36864);
        if (modules != null) {
            for (int i = 0; i < modules.length; ++i) {
                if (modules[i] == null) {
                    throw new IllegalArgumentException("module at index " + i + " cannot be null");
                }
            }
        }
        super.visitOpen(packaze, access, modules);
    }
    
    @Override
    public void visitUse(final String service) {
        this.checkEnd();
        CheckMethodAdapter.checkInternalName(service, "service");
        checkDeclared("uses", this.useNames, service);
        super.visitUse(service);
    }
    
    @Override
    public void visitProvide(final String service, final String... providers) {
        this.checkEnd();
        CheckMethodAdapter.checkInternalName(service, "service");
        checkDeclared("provides", this.provideNames, service);
        if (providers == null || providers.length == 0) {
            throw new IllegalArgumentException("providers cannot be null or empty");
        }
        for (int i = 0; i < providers.length; ++i) {
            CheckMethodAdapter.checkInternalName(providers[i], "provider");
        }
        super.visitProvide(service, providers);
    }
    
    @Override
    public void visitEnd() {
        this.checkEnd();
        this.end = true;
        super.visitEnd();
    }
    
    private void checkEnd() {
        if (this.end) {
            throw new IllegalStateException("Cannot call a visit method after visitEnd has been called");
        }
    }
    
    private static void checkDeclared(final String directive, final HashSet<String> names, final String name) {
        if (!names.add(name)) {
            throw new IllegalArgumentException(directive + " " + name + " already declared");
        }
    }
}
