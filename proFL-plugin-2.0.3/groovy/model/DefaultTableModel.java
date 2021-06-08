// 
// Decompiled by Procyon v0.5.36
// 

package groovy.model;

import javax.swing.table.DefaultTableColumnModel;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.util.Collections;
import javax.swing.table.TableColumn;
import groovy.lang.Closure;
import javax.swing.table.TableColumnModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DefaultTableModel extends AbstractTableModel
{
    private ValueModel rowModel;
    private ValueModel rowsModel;
    private MyTableColumnModel columnModel;
    
    public DefaultTableModel(final ValueModel rowsModel) {
        this(rowsModel, new ValueHolder());
    }
    
    public DefaultTableModel(final ValueModel rowsModel, final ValueModel rowModel) {
        this.columnModel = new MyTableColumnModel();
        this.rowModel = rowModel;
        this.rowsModel = rowsModel;
    }
    
    public List getColumnList() {
        return this.columnModel.getColumnList();
    }
    
    public TableColumnModel getColumnModel() {
        return this.columnModel;
    }
    
    public DefaultTableColumn addPropertyColumn(final Object headerValue, final String property, final Class type) {
        return this.addColumn(headerValue, property, new PropertyModel(this.rowModel, property, type));
    }
    
    public DefaultTableColumn addPropertyColumn(final Object headerValue, final String property, final Class type, final boolean editable) {
        return this.addColumn(headerValue, property, new PropertyModel(this.rowModel, property, type, editable));
    }
    
    public DefaultTableColumn addClosureColumn(final Object headerValue, final Closure readClosure, final Closure writeClosure, final Class type) {
        return this.addColumn(headerValue, new ClosureModel(this.rowModel, readClosure, writeClosure, type));
    }
    
    public DefaultTableColumn addColumn(final Object headerValue, final ValueModel columnValueModel) {
        return this.addColumn(headerValue, headerValue, columnValueModel);
    }
    
    public DefaultTableColumn addColumn(final Object headerValue, final Object identifier, final ValueModel columnValueModel) {
        final DefaultTableColumn answer = new DefaultTableColumn(headerValue, identifier, columnValueModel);
        this.addColumn(answer);
        return answer;
    }
    
    public void addColumn(final DefaultTableColumn column) {
        column.setModelIndex(this.columnModel.getColumnCount());
        this.columnModel.addColumn(column);
    }
    
    public void removeColumn(final DefaultTableColumn column) {
        this.columnModel.removeColumn(column);
    }
    
    public int getRowCount() {
        return this.getRows().size();
    }
    
    public int getColumnCount() {
        return this.columnModel.getColumnCount();
    }
    
    @Override
    public String getColumnName(final int columnIndex) {
        final String answer = null;
        if (columnIndex < 0 || columnIndex >= this.columnModel.getColumnCount()) {
            return answer;
        }
        final Object value = this.columnModel.getColumn(columnIndex).getHeaderValue();
        if (value != null) {
            return value.toString();
        }
        return answer;
    }
    
    @Override
    public Class getColumnClass(final int columnIndex) {
        return this.getColumnModel(columnIndex).getType();
    }
    
    @Override
    public boolean isCellEditable(final int rowIndex, final int columnIndex) {
        return this.getColumnModel(columnIndex).isEditable();
    }
    
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        final List rows = this.getRows();
        final Object answer = null;
        if (rowIndex < 0 || rowIndex >= rows.size()) {
            return answer;
        }
        if (columnIndex < 0 || columnIndex >= this.columnModel.getColumnCount()) {
            return answer;
        }
        final Object row = this.getRows().get(rowIndex);
        this.rowModel.setValue(row);
        final DefaultTableColumn column = (DefaultTableColumn)this.columnModel.getColumn(columnIndex);
        if (row == null || column == null) {
            return answer;
        }
        return column.getValue(row, rowIndex, columnIndex);
    }
    
    @Override
    public void setValueAt(final Object value, final int rowIndex, final int columnIndex) {
        final List rows = this.getRows();
        if (rowIndex < 0 || rowIndex >= rows.size()) {
            return;
        }
        if (columnIndex < 0 || columnIndex >= this.columnModel.getColumnCount()) {
            return;
        }
        final Object row = this.getRows().get(rowIndex);
        this.rowModel.setValue(row);
        final DefaultTableColumn column = (DefaultTableColumn)this.columnModel.getColumn(columnIndex);
        if (row == null || column == null) {
            return;
        }
        column.setValue(row, value, rowIndex, columnIndex);
    }
    
    protected ValueModel getColumnModel(final int columnIndex) {
        final DefaultTableColumn column = (DefaultTableColumn)this.columnModel.getColumn(columnIndex);
        return column.getValueModel();
    }
    
    protected List getRows() {
        final Object value = this.rowsModel.getValue();
        if (value == null) {
            return Collections.EMPTY_LIST;
        }
        return InvokerHelper.asList(value);
    }
    
    public ValueModel getRowModel() {
        return this.rowModel;
    }
    
    public ValueModel getRowsModel() {
        return this.rowsModel;
    }
    
    protected static class MyTableColumnModel extends DefaultTableColumnModel
    {
        public List getColumnList() {
            return this.tableColumns;
        }
        
        @Override
        public void removeColumn(final TableColumn column) {
            super.removeColumn(column);
            this.renumberTableColumns();
        }
        
        @Override
        public void moveColumn(final int columnIndex, final int newIndex) {
            super.moveColumn(columnIndex, newIndex);
            this.renumberTableColumns();
        }
        
        public void renumberTableColumns() {
            for (int i = this.tableColumns.size() - 1; i >= 0; --i) {
                this.tableColumns.get(i).setModelIndex(i);
            }
        }
    }
}
