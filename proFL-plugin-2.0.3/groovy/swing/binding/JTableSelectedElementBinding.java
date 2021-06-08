// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import java.beans.PropertyChangeEvent;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.PropertyBinding;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import java.beans.PropertyChangeListener;

class JTableSelectedElementBinding extends AbstractSyntheticBinding implements PropertyChangeListener, ListSelectionListener
{
    JTable boundTable;
    
    protected JTableSelectedElementBinding(final PropertyBinding source, final TargetBinding target, final String propertyName) {
        super(source, target, JTable.class, propertyName);
    }
    
    public synchronized void syntheticBind() {
        (this.boundTable = (JTable)((PropertyBinding)this.sourceBinding).getBean()).addPropertyChangeListener("selectionModel", this);
        this.boundTable.getSelectionModel().addListSelectionListener(this);
    }
    
    public synchronized void syntheticUnbind() {
        this.boundTable.removePropertyChangeListener("selectionModel", this);
        this.boundTable.getSelectionModel().removeListSelectionListener(this);
        this.boundTable = null;
    }
    
    public void propertyChange(final PropertyChangeEvent event) {
        this.update();
        ((ListSelectionModel)event.getOldValue()).removeListSelectionListener(this);
        ((ListSelectionModel)event.getNewValue()).addListSelectionListener(this);
    }
    
    public void valueChanged(final ListSelectionEvent e) {
        this.update();
    }
}
