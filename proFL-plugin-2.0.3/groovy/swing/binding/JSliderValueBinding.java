// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import javax.swing.event.ChangeEvent;
import javax.swing.BoundedRangeModel;
import java.beans.PropertyChangeEvent;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.PropertyBinding;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import java.beans.PropertyChangeListener;

class JSliderValueBinding extends AbstractSyntheticBinding implements PropertyChangeListener, ChangeListener
{
    JSlider boundSlider;
    
    public JSliderValueBinding(final PropertyBinding source, final TargetBinding target) {
        super(source, target, JSlider.class, "value");
    }
    
    public synchronized void syntheticBind() {
        (this.boundSlider = (JSlider)((PropertyBinding)this.sourceBinding).getBean()).addPropertyChangeListener("model", this);
        this.boundSlider.getModel().addChangeListener(this);
    }
    
    public synchronized void syntheticUnbind() {
        this.boundSlider.removePropertyChangeListener("model", this);
        this.boundSlider.getModel().removeChangeListener(this);
        this.boundSlider = null;
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
