// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.runtime.directive.VelocimacroProxy;
import java.util.Hashtable;

public class VelocimacroManager
{
    private final RuntimeServices rsvc;
    private static String GLOBAL_NAMESPACE;
    private boolean registerFromLib;
    private final Hashtable namespaceHash;
    private final Hashtable libraryMap;
    private boolean namespacesOn;
    private boolean inlineLocalMode;
    
    VelocimacroManager(final RuntimeServices rsvc) {
        this.registerFromLib = false;
        this.namespaceHash = new Hashtable();
        this.libraryMap = new Hashtable();
        this.namespacesOn = true;
        this.inlineLocalMode = false;
        this.rsvc = rsvc;
        this.addNamespace(VelocimacroManager.GLOBAL_NAMESPACE);
    }
    
    public boolean addVM(final String vmName, final String macroBody, final String[] argArray, final String namespace) {
        final MacroEntry me = new MacroEntry(vmName, macroBody, argArray, namespace);
        me.setFromLibrary(this.registerFromLib);
        boolean isLib = true;
        if (this.registerFromLib) {
            this.libraryMap.put(namespace, namespace);
        }
        else {
            isLib = this.libraryMap.containsKey(namespace);
        }
        if (!isLib && this.usingNamespaces(namespace)) {
            final Hashtable local = this.getNamespace(namespace, true);
            local.put(vmName, me);
            return true;
        }
        final MacroEntry exist = this.getNamespace(VelocimacroManager.GLOBAL_NAMESPACE).get(vmName);
        if (exist != null) {
            me.setFromLibrary(exist.getFromLibrary());
        }
        this.getNamespace(VelocimacroManager.GLOBAL_NAMESPACE).put(vmName, me);
        return true;
    }
    
    public VelocimacroProxy get(final String vmName, final String namespace) {
        if (this.usingNamespaces(namespace)) {
            final Hashtable local = this.getNamespace(namespace, false);
            if (local != null) {
                final MacroEntry me = local.get(vmName);
                if (me != null) {
                    return me.createVelocimacro(namespace);
                }
            }
        }
        final MacroEntry me2 = this.getNamespace(VelocimacroManager.GLOBAL_NAMESPACE).get(vmName);
        if (me2 != null) {
            return me2.createVelocimacro(namespace);
        }
        return null;
    }
    
    public boolean dumpNamespace(final String namespace) {
        synchronized (this) {
            if (!this.usingNamespaces(namespace)) {
                return false;
            }
            final Hashtable h = this.namespaceHash.remove(namespace);
            if (h == null) {
                return false;
            }
            h.clear();
            return true;
        }
    }
    
    public void setNamespaceUsage(final boolean namespaceOn) {
        this.namespacesOn = namespaceOn;
    }
    
    public void setRegisterFromLib(final boolean registerFromLib) {
        this.registerFromLib = registerFromLib;
    }
    
    public void setTemplateLocalInlineVM(final boolean inlineLocalMode) {
        this.inlineLocalMode = inlineLocalMode;
    }
    
    private Hashtable getNamespace(final String namespace) {
        return this.getNamespace(namespace, false);
    }
    
    private Hashtable getNamespace(final String namespace, final boolean addIfNew) {
        Hashtable h = this.namespaceHash.get(namespace);
        if (h == null && addIfNew) {
            h = this.addNamespace(namespace);
        }
        return h;
    }
    
    private Hashtable addNamespace(final String namespace) {
        final Hashtable h = new Hashtable();
        final Object oh;
        if ((oh = this.namespaceHash.put(namespace, h)) != null) {
            this.namespaceHash.put(namespace, oh);
            return null;
        }
        return h;
    }
    
    private boolean usingNamespaces(final String namespace) {
        return this.namespacesOn && this.inlineLocalMode;
    }
    
    public String getLibraryName(final String vmName, final String namespace) {
        if (this.usingNamespaces(namespace)) {
            final Hashtable local = this.getNamespace(namespace, false);
            if (local != null) {
                final MacroEntry me = local.get(vmName);
                if (me != null) {
                    return null;
                }
            }
        }
        final MacroEntry me2 = this.getNamespace(VelocimacroManager.GLOBAL_NAMESPACE).get(vmName);
        if (me2 != null) {
            return me2.getSourceTemplate();
        }
        return null;
    }
    
    static {
        VelocimacroManager.GLOBAL_NAMESPACE = "";
    }
    
    private class MacroEntry
    {
        private final String vmName;
        private final String[] argArray;
        private final String macroBody;
        private final String sourceTemplate;
        private SimpleNode nodeTree;
        private boolean fromLibrary;
        
        private MacroEntry(final String vmName, final String macroBody, final String[] argArray, final String sourceTemplate) {
            this.nodeTree = null;
            this.fromLibrary = false;
            this.vmName = vmName;
            this.argArray = argArray;
            this.macroBody = macroBody;
            this.sourceTemplate = sourceTemplate;
        }
        
        public void setFromLibrary(final boolean fromLibrary) {
            this.fromLibrary = fromLibrary;
        }
        
        public boolean getFromLibrary() {
            return this.fromLibrary;
        }
        
        public SimpleNode getNodeTree() {
            return this.nodeTree;
        }
        
        public String getSourceTemplate() {
            return this.sourceTemplate;
        }
        
        VelocimacroProxy createVelocimacro(final String namespace) {
            final VelocimacroProxy vp = new VelocimacroProxy();
            vp.setName(this.vmName);
            vp.setArgArray(this.argArray);
            vp.setMacrobody(this.macroBody);
            vp.setNodeTree(this.nodeTree);
            vp.setNamespace(namespace);
            return vp;
        }
        
        void setup(final InternalContextAdapter ica) {
            if (this.nodeTree == null) {
                this.parseTree(ica);
            }
        }
        
        void parseTree(final InternalContextAdapter ica) {
            try {
                final BufferedReader br = new BufferedReader(new StringReader(this.macroBody));
                (this.nodeTree = VelocimacroManager.this.rsvc.parse(br, "VM:" + this.vmName, true)).init(ica, null);
            }
            catch (Exception e) {
                VelocimacroManager.this.rsvc.getLog().error("VelocimacroManager.parseTree() failed on VM '" + this.vmName + "'", e);
            }
        }
    }
}
