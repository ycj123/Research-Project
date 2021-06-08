// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr;

import java.awt.Dimension;
import javax.swing.JTextPane;

class HScrollableTextPane extends JTextPane
{
    @Override
    public boolean getScrollableTracksViewportWidth() {
        return this.getSize().width < this.getParent().getSize().width;
    }
    
    @Override
    public void setSize(final Dimension d) {
        if (d.width < this.getParent().getSize().width) {
            d.width = this.getParent().getSize().width;
        }
        super.setSize(d);
    }
}
