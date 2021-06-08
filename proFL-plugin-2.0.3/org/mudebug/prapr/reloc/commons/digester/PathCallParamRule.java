// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester;

import org.xml.sax.Attributes;

public class PathCallParamRule extends Rule
{
    protected int paramIndex;
    
    public PathCallParamRule(final int paramIndex) {
        this.paramIndex = 0;
        this.paramIndex = paramIndex;
    }
    
    public void begin(final String namespace, final String name, final Attributes attributes) throws Exception {
        final String param = this.getDigester().getMatch();
        if (param != null) {
            final Object[] parameters = (Object[])this.digester.peekParams();
            parameters[this.paramIndex] = param;
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("PathCallParamRule[");
        sb.append("paramIndex=");
        sb.append(this.paramIndex);
        sb.append("]");
        return sb.toString();
    }
}
