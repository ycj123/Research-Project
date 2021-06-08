// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class TableMap extends AbstractTableModel implements TableModelListener
{
    protected TableModel model;
    
    public TableModel getModel() {
        return this.model;
    }
    
    public void setModel(final TableModel model) {
        (this.model = model).addTableModelListener(this);
    }
    
    public Object getValueAt(final int aRow, final int aColumn) {
        return this.model.getValueAt(aRow, aColumn);
    }
    
    @Override
    public void setValueAt(final Object aValue, final int aRow, final int aColumn) {
        this.model.setValueAt(aValue, aRow, aColumn);
    }
    
    public int getRowCount() {
        return (this.model == null) ? 0 : this.model.getRowCount();
    }
    
    public int getColumnCount() {
        return (this.model == null) ? 0 : this.model.getColumnCount();
    }
    
    @Override
    public String getColumnName(final int aColumn) {
        return this.model.getColumnName(aColumn);
    }
    
    @Override
    public Class getColumnClass(final int aColumn) {
        return this.model.getColumnClass(aColumn);
    }
    
    @Override
    public boolean isCellEditable(final int row, final int column) {
        return this.model.isCellEditable(row, column);
    }
    
    public void tableChanged(final TableModelEvent e) {
        this.fireTableChanged(e);
    }
}
