// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester;

import org.mudebug.prapr.reloc.commons.beanutils.MethodUtils;

public class SetRootRule extends Rule
{
    protected String methodName;
    protected String paramType;
    protected boolean useExactMatch;
    
    public SetRootRule(final Digester digester, final String methodName) {
        this(methodName);
    }
    
    public SetRootRule(final Digester digester, final String methodName, final String paramType) {
        this(methodName, paramType);
    }
    
    public SetRootRule(final String methodName) {
        this(methodName, null);
    }
    
    public SetRootRule(final String methodName, final String paramType) {
        this.methodName = null;
        this.paramType = null;
        this.useExactMatch = false;
        this.methodName = methodName;
        this.paramType = paramType;
    }
    
    public boolean isExactMatch() {
        return this.useExactMatch;
    }
    
    public void setExactMatch(final boolean useExactMatch) {
        this.useExactMatch = useExactMatch;
    }
    
    public void end() throws Exception {
        final Object child = this.digester.peek(0);
        final Object parent = this.digester.root;
        if (this.digester.log.isDebugEnabled()) {
            if (parent == null) {
                this.digester.log.debug("[SetRootRule]{" + this.digester.match + "} Call [NULL ROOT]." + this.methodName + "(" + child + ")");
            }
            else {
                this.digester.log.debug("[SetRootRule]{" + this.digester.match + "} Call " + parent.getClass().getName() + "." + this.methodName + "(" + child + ")");
            }
        }
        final Class[] paramTypes = { null };
        if (this.paramType != null) {
            paramTypes[0] = this.digester.getClassLoader().loadClass(this.paramType);
        }
        else {
            paramTypes[0] = child.getClass();
        }
        if (this.useExactMatch) {
            MethodUtils.invokeExactMethod(parent, this.methodName, new Object[] { child }, paramTypes);
        }
        else {
            MethodUtils.invokeMethod(parent, this.methodName, new Object[] { child }, paramTypes);
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("SetRootRule[");
        sb.append("methodName=");
        sb.append(this.methodName);
        sb.append(", paramType=");
        sb.append(this.paramType);
        sb.append("]");
        return sb.toString();
    }
}
