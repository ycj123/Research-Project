// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import java.util.ArrayList;
import java.util.List;

public class InterfaceHelperClassNode extends InnerClassNode
{
    private List callSites;
    
    public InterfaceHelperClassNode(final ClassNode outerClass, final String name, final int modifiers, final ClassNode superClass, final List callSites) {
        super(outerClass, name, modifiers, superClass, ClassHelper.EMPTY_TYPE_ARRAY, MixinNode.EMPTY_ARRAY);
        this.callSites = new ArrayList();
        this.setCallSites(callSites);
    }
    
    public void setCallSites(final List cs) {
        this.callSites = ((cs != null) ? cs : new ArrayList());
    }
    
    public List getCallSites() {
        return this.callSites;
    }
}
