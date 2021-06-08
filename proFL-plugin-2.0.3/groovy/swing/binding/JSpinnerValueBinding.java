// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerModel;
import java.beans.PropertyChangeEvent;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.PropertyBinding;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import java.beans.PropertyChangeListener;

class JSpinnerValueBinding extends AbstractSyntheticBinding implements PropertyChangeListener, ChangeListener
{
    JSpinner boundSlider;
    
    public JSpinnerValueBinding(final PropertyBinding source, final TargetBinding target) {
        super(source, target, JSpinner.class, "value");
    }
    
    public synchronized void syntheticBind() {
        (this.boundSlider = (JSpinner)((PropertyBinding)this.sourceBinding).getBean()).addPropertyChangeListener("model", this);
        this.boundSlider.getModel().addChangeListener(this);
    }
    
    public synchronized void syntheticUnbind() {
        this.boundSlider.removePropertyChangeListener("model", this);
        this.boundSlider.getModel().removeChangeListener(this);
        this.boundSlider = null;
    }
    
    public void propertyChange(final PropertyChangeEvent event) {
        this.update();
        ((SpinnerModel)event.getOldValue()).removeChangeListener(this);
        ((SpinnerModel)event.getNewValue()).addChangeListener(this);
    }
    
    public void stateChanged(final ChangeEvent e) {
        this.update();
    }
}
