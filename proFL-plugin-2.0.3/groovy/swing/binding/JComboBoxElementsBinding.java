// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import javax.swing.event.ListDataEvent;
import javax.swing.ComboBoxModel;
import java.beans.PropertyChangeEvent;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.PropertyBinding;
import javax.swing.JComboBox;
import java.beans.PropertyChangeListener;
import javax.swing.event.ListDataListener;

class JComboBoxElementsBinding extends AbstractSyntheticBinding implements ListDataListener, PropertyChangeListener
{
    JComboBox boundComboBox;
    
    public JComboBoxElementsBinding(final PropertyBinding propertyBinding, final TargetBinding target) {
        super(propertyBinding, target, JComboBox.class, "elements");
    }
    
    @Override
    protected void syntheticBind() {
        (this.boundComboBox = (JComboBox)((PropertyBinding)this.sourceBinding).getBean()).addPropertyChangeListener("model", this);
        this.boundComboBox.getModel().addListDataListener(this);
    }
    
    @Override
    protected void syntheticUnbind() {
        this.boundComboBox.removePropertyChangeListener("model", this);
        this.boundComboBox.getModel().removeListDataListener(this);
    }
    
    public void propertyChange(final PropertyChangeEvent event) {
        this.update();
        ((ComboBoxModel)event.getOldValue()).removeListDataListener(this);
        ((ComboBoxModel)event.getNewValue()).addListDataListener(this);
    }
    
    public void intervalAdded(final ListDataEvent e) {
        this.update();
    }
    
    public void intervalRemoved(final ListDataEvent e) {
        this.update();
    }
    
    public void contentsChanged(final ListDataEvent e) {
        this.update();
    }
}
