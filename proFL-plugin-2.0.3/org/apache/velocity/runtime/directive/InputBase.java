// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.directive;

import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.context.InternalContextAdapter;

public abstract class InputBase extends Directive
{
    protected String getInputEncoding(final InternalContextAdapter context) {
        final Resource current = context.getCurrentResource();
        if (current != null) {
            return current.getEncoding();
        }
        return (String)this.rsvc.getProperty("input.encoding");
    }
}
