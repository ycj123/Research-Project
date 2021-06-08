// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.PropertyBinding;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;

class JComboBoxSelectedElementBinding extends AbstractSyntheticBinding implements PropertyChangeListener, ItemListener
{
    JComboBox boundComboBox;
    
    public JComboBoxSelectedElementBinding(final PropertyBinding source, final TargetBinding target, final String propertyName) {
        super(source, target, JComboBox.class, propertyName);
    }
    
    public synchronized void syntheticBind() {
        (this.boundComboBox = (JComboBox)((PropertyBinding)this.sourceBinding).getBean()).addPropertyChangeListener("model", this);
        this.boundComboBox.addItemListener(this);
    }
    
    public synchronized void syntheticUnbind() {
        this.boundComboBox.removePropertyChangeListener("model", this);
        this.boundComboBox.removeItemListener(this);
        this.boundComboBox = null;
    }
    
    @Override
    public void setTargetBinding(final TargetBinding target) {
        super.setTargetBinding(target);
    }
    
    public void propertyChange(final PropertyChangeEvent event) {
        this.update();
    }
    
    public void itemStateChanged(final ItemEvent e) {
        this.update();
    }
}
