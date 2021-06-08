// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime;

import org.apache.velocity.runtime.directive.VelocimacroProxy;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.directive.Macro;
import org.apache.velocity.Template;
import org.mudebug.prapr.reloc.commons.lang.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.apache.velocity.runtime.log.LogDisplayWrapper;

public class VelocimacroFactory
{
    private final RuntimeServices rsvc;
    private final LogDisplayWrapper log;
    private VelocimacroManager vmManager;
    private boolean replaceAllowed;
    private boolean addNewAllowed;
    private boolean templateLocal;
    private boolean autoReloadLibrary;
    private Vector macroLibVec;
    private Map libModMap;
    
    public VelocimacroFactory(final RuntimeServices rsvc) {
        this.vmManager = null;
        this.replaceAllowed = false;
        this.addNewAllowed = true;
        this.templateLocal = false;
        this.autoReloadLibrary = false;
        this.macroLibVec = null;
        this.rsvc = rsvc;
        this.log = new LogDisplayWrapper(rsvc.getLog(), "Velocimacro : ", rsvc.getBoolean("velocimacro.messages.on", true));
        this.libModMap = new HashMap();
        this.vmManager = new VelocimacroManager(rsvc);
    }
    
    public void initVelocimacro() {
        synchronized (this) {
            this.log.trace("initialization starting.");
            this.setReplacementPermission(true);
            this.vmManager.setNamespaceUsage(false);
            Object libfiles = this.rsvc.getProperty("velocimacro.library");
            if (libfiles == null) {
                this.log.debug("\"velocimacro.library\" is not set.  Trying default library: VM_global_library.vm");
                if (this.rsvc.getLoaderNameForResource("VM_global_library.vm") != null) {
                    libfiles = "VM_global_library.vm";
                }
                else {
                    this.log.debug("Default library not found.");
                }
            }
            if (libfiles != null) {
                if (libfiles instanceof Vector) {
                    this.macroLibVec = (Vector)libfiles;
                }
                else if (libfiles instanceof String) {
                    (this.macroLibVec = new Vector()).addElement(libfiles);
                }
                for (int i = 0; i < this.macroLibVec.size(); ++i) {
                    final String lib = this.macroLibVec.elementAt(i);
                    if (StringUtils.isNotEmpty(lib)) {
                        this.vmManager.setRegisterFromLib(true);
                        this.log.debug("adding VMs from VM library : " + lib);
                        try {
                            final Template template = this.rsvc.getTemplate(lib);
                            final Twonk twonk = new Twonk();
                            twonk.template = template;
                            twonk.modificationTime = template.getLastModified();
                            this.libModMap.put(lib, twonk);
                        }
                        catch (Exception e) {
                            this.log.error(true, "Velocimacro : Error using VM library : " + lib, e);
                        }
                        this.log.trace("VM library registration complete.");
                        this.vmManager.setRegisterFromLib(false);
                    }
                }
            }
            this.setAddMacroPermission(true);
            if (!this.rsvc.getBoolean("velocimacro.permissions.allow.inline", true)) {
                this.setAddMacroPermission(false);
                this.log.info("allowInline = false : VMs can NOT be defined inline in templates");
            }
            else {
                this.log.debug("allowInline = true : VMs can be defined inline in templates");
            }
            this.setReplacementPermission(false);
            if (this.rsvc.getBoolean("velocimacro.permissions.allow.inline.to.replace.global", false)) {
                this.setReplacementPermission(true);
                this.log.info("allowInlineToOverride = true : VMs defined inline may replace previous VM definitions");
            }
            else {
                this.log.debug("allowInlineToOverride = false : VMs defined inline may NOT replace previous VM definitions");
            }
            this.vmManager.setNamespaceUsage(true);
            this.setTemplateLocalInline(this.rsvc.getBoolean("velocimacro.permissions.allow.inline.local.scope", false));
            if (this.getTemplateLocalInline()) {
                this.log.info("allowInlineLocal = true : VMs defined inline will be local to their defining template only.");
            }
            else {
                this.log.debug("allowInlineLocal = false : VMs defined inline will be global in scope if allowed.");
            }
            this.vmManager.setTemplateLocalInlineVM(this.getTemplateLocalInline());
            this.setAutoload(this.rsvc.getBoolean("velocimacro.library.autoreload", false));
            if (this.getAutoload()) {
                this.log.info("autoload on : VM system will automatically reload global library macros");
            }
            else {
                this.log.debug("autoload off : VM system will not automatically reload global library macros");
            }
            this.log.trace("Velocimacro : initialization complete.");
        }
    }
    
    public boolean addVelocimacro(final String name, final String macroBody, final String[] argArray, final String sourceTemplate) {
        if (name == null || macroBody == null || argArray == null || sourceTemplate == null) {
            this.log.warn("VM addition rejected : programmer error : arg null");
            return false;
        }
        if (!this.canAddVelocimacro(name, sourceTemplate)) {
            return false;
        }
        synchronized (this) {
            this.vmManager.addVM(name, macroBody, argArray, sourceTemplate);
        }
        final StringBuffer msg = new StringBuffer("added ");
        Macro.macroToString(msg, argArray);
        msg.append(" : source = ").append(sourceTemplate);
        this.log.info(msg.toString());
        return true;
    }
    
    private synchronized boolean canAddVelocimacro(final String name, final String sourceTemplate) {
        if (this.getAutoload() && this.macroLibVec != null) {
            for (int i = 0; i < this.macroLibVec.size(); ++i) {
                final String lib = this.macroLibVec.elementAt(i);
                if (lib.equals(sourceTemplate)) {
                    return true;
                }
            }
        }
        if (!this.addNewAllowed) {
            this.log.warn("VM addition rejected : " + name + " : inline VMs not allowed.");
            return false;
        }
        if (!this.templateLocal && this.isVelocimacro(name, sourceTemplate) && !this.replaceAllowed) {
            this.log.warn("VM addition rejected : " + name + " : inline not allowed to replace existing VM");
            return false;
        }
        return true;
    }
    
    public boolean isVelocimacro(final String vm, final String sourceTemplate) {
        synchronized (this) {
            if (this.vmManager.get(vm, sourceTemplate) != null) {
                return true;
            }
        }
        return false;
    }
    
    public Directive getVelocimacro(final String vmName, final String sourceTemplate) {
        VelocimacroProxy vp = null;
        synchronized (this) {
            vp = this.vmManager.get(vmName, sourceTemplate);
            if (vp != null && this.getAutoload()) {
                final String lib = this.vmManager.getLibraryName(vmName, sourceTemplate);
                if (lib != null) {
                    try {
                        final Twonk tw = this.libModMap.get(lib);
                        if (tw != null) {
                            Template template = tw.template;
                            final long tt = tw.modificationTime;
                            final long ft = template.getResourceLoader().getLastModified(template);
                            if (ft > tt) {
                                this.log.debug("auto-reloading VMs from VM library : " + lib);
                                tw.modificationTime = ft;
                                template = this.rsvc.getTemplate(lib);
                                tw.template = template;
                                tw.modificationTime = template.getLastModified();
                            }
                        }
                    }
                    catch (Exception e) {
                        this.log.error(true, "Velocimacro : Error using VM library : " + lib, e);
                    }
                    vp = this.vmManager.get(vmName, sourceTemplate);
                }
            }
        }
        return vp;
    }
    
    public boolean dumpVMNamespace(final String namespace) {
        return this.vmManager.dumpNamespace(namespace);
    }
    
    private void setTemplateLocalInline(final boolean b) {
        this.templateLocal = b;
    }
    
    private boolean getTemplateLocalInline() {
        return this.templateLocal;
    }
    
    private boolean setAddMacroPermission(final boolean addNewAllowed) {
        final boolean b = this.addNewAllowed;
        this.addNewAllowed = addNewAllowed;
        return b;
    }
    
    private boolean setReplacementPermission(final boolean arg) {
        final boolean b = this.replaceAllowed;
        this.replaceAllowed = arg;
        return b;
    }
    
    private void setAutoload(final boolean b) {
        this.autoReloadLibrary = b;
    }
    
    private boolean getAutoload() {
        return this.autoReloadLibrary;
    }
    
    private static class Twonk
    {
        public Template template;
        public long modificationTime;
    }
}
