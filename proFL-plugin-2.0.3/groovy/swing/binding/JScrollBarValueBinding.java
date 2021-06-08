// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import javax.swing.event.ChangeEvent;
import javax.swing.BoundedRangeModel;
import java.beans.PropertyChangeEvent;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.PropertyBinding;
import javax.swing.JScrollBar;
import javax.swing.event.ChangeListener;
import java.beans.PropertyChangeListener;

class JScrollBarValueBinding extends AbstractSyntheticBinding implements PropertyChangeListener, ChangeListener
{
    JScrollBar boundScrollBar;
    
    public JScrollBarValueBinding(final PropertyBinding source, final TargetBinding target) {
        super(source, target, JScrollBar.class, "value");
    }
    
    public synchronized void syntheticBind() {
        (this.boundScrollBar = (JScrollBar)((PropertyBinding)this.sourceBinding).getBean()).addPropertyChangeListener("model", this);
        this.boundScrollBar.getModel().addChangeListener(this);
    }
    
    public synchronized void syntheticUnbind() {
        this.boundScrollBar.removePropertyChangeListener("model", this);
        this.boundScrollBar.getModel().removeChangeListener(this);
        this.boundScrollBar = null;
    }
    
    @Override
    public void setTargetBinding(final TargetBinding target) {
        super.setTargetBinding(target);
    }
    
    public void propertyChange(final PropertyChangeEvent event) {
        this.update();
        ((BoundedRangeModel)event.getOldValue()).removeChangeListener(this);
        ((BoundedRangeModel)event.getNewValue()).addChangeListener(this);
    }
    
    public void stateChanged(final ChangeEvent e) {
        this.update();
    }
}
