// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.impl;

import java.awt.event.ActionEvent;
import groovy.lang.Closure;
import javax.swing.AbstractAction;

public class DefaultAction extends AbstractAction
{
    private Closure closure;
    
    public void actionPerformed(final ActionEvent event) {
        if (this.closure == null) {
            throw new NullPointerException("No closure has been configured for this Action");
        }
        this.closure.call(event);
    }
    
    public Closure getClosure() {
        return this.closure;
    }
    
    public void setClosure(final Closure closure) {
        this.closure = closure;
    }
}
