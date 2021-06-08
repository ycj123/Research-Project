// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.PropertyBinding;
import javax.swing.JComponent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeListener;

abstract class AbstractJComponentBinding extends AbstractSyntheticBinding implements PropertyChangeListener, ComponentListener
{
    JComponent boundComponent;
    String propertyName;
    
    public AbstractJComponentBinding(final PropertyBinding source, final TargetBinding target, final String propertyName) {
        super(source, target, JComponent.class, propertyName);
        source.setNonChangeCheck(true);
    }
    
    public synchronized void syntheticBind() {
        (this.boundComponent = (JComponent)((PropertyBinding)this.sourceBinding).getBean()).addPropertyChangeListener(this.propertyName, this);
        this.boundComponent.addComponentListener(this);
    }
    
    public synchronized void syntheticUnbind() {
        this.boundComponent.removePropertyChangeListener(this.propertyName, this);
        this.boundComponent.removeComponentListener(this);
        this.boundComponent = null;
    }
    
    public void propertyChange(final PropertyChangeEvent event) {
        this.update();
        ((JComponent)event.getOldValue()).removeComponentListener(this);
        ((JComponent)event.getNewValue()).addComponentListener(this);
    }
    
    public void componentHidden(final ComponentEvent event) {
    }
    
    public void componentShown(final ComponentEvent event) {
    }
    
    public void componentMoved(final ComponentEvent event) {
    }
    
    public void componentResized(final ComponentEvent event) {
    }
}
