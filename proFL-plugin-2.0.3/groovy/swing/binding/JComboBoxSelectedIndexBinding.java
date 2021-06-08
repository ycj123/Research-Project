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

class JComboBoxSelectedIndexBinding extends AbstractSyntheticBinding implements PropertyChangeListener, ItemListener
{
    JComboBox boundComboBox;
    
    public JComboBoxSelectedIndexBinding(final PropertyBinding source, final TargetBinding target) {
        super(source, target, JComboBox.class, "selectedIndex");
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
