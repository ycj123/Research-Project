// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import java.awt.event.ItemEvent;
import javax.swing.ButtonModel;
import java.beans.PropertyChangeEvent;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.PropertyBinding;
import javax.swing.AbstractButton;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;

class AbstractButtonSelectedBinding extends AbstractSyntheticBinding implements PropertyChangeListener, ItemListener
{
    AbstractButton boundButton;
    
    public AbstractButtonSelectedBinding(final PropertyBinding source, final TargetBinding target) {
        super(source, target, AbstractButton.class, "selected");
    }
    
    public synchronized void syntheticBind() {
        (this.boundButton = (AbstractButton)((PropertyBinding)this.sourceBinding).getBean()).addPropertyChangeListener("model", this);
        this.boundButton.getModel().addItemListener(this);
    }
    
    public synchronized void syntheticUnbind() {
        this.boundButton.removePropertyChangeListener("model", this);
        this.boundButton.getModel().removeItemListener(this);
        this.boundButton = null;
    }
    
    public void propertyChange(final PropertyChangeEvent event) {
        this.update();
        ((ButtonModel)event.getOldValue()).removeItemListener(this);
        ((ButtonModel)event.getNewValue()).addItemListener(this);
    }
    
    public void itemStateChanged(final ItemEvent e) {
        this.update();
    }
}
