// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import javax.swing.table.TableModel;
import java.beans.PropertyChangeEvent;
import javax.swing.event.TableModelEvent;
import org.codehaus.groovy.binding.TargetBinding;
import org.codehaus.groovy.binding.PropertyBinding;
import javax.swing.JTable;
import java.beans.PropertyChangeListener;
import javax.swing.event.TableModelListener;

class JTableElementsBinding extends AbstractSyntheticBinding implements TableModelListener, PropertyChangeListener
{
    JTable boundTable;
    
    public JTableElementsBinding(final PropertyBinding propertyBinding, final TargetBinding target) {
        super(propertyBinding, target, JTable.class, "elements");
    }
    
    @Override
    protected void syntheticBind() {
        (this.boundTable = (JTable)((PropertyBinding)this.sourceBinding).getBean()).addPropertyChangeListener("model", this);
        this.boundTable.getModel().addTableModelListener(this);
    }
    
    @Override
    protected void syntheticUnbind() {
        this.boundTable.removePropertyChangeListener("model", this);
        this.boundTable.getModel().removeTableModelListener(this);
    }
    
    public void tableChanged(final TableModelEvent e) {
        this.update();
    }
    
    public void propertyChange(final PropertyChangeEvent event) {
        this.update();
        ((TableModel)event.getOldValue()).removeTableModelListener(this);
        ((TableModel)event.getNewValue()).addTableModelListener(this);
    }
}
