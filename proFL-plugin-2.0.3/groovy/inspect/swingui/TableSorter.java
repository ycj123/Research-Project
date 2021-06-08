// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import javax.swing.table.JTableHeader;
import java.awt.event.MouseListener;
import javax.swing.table.TableColumnModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import java.util.Date;
import javax.swing.table.TableModel;
import java.util.Vector;

public class TableSorter extends TableMap
{
    int[] indexes;
    Vector sortingColumns;
    boolean ascending;
    int lastSortedColumn;
    
    public TableSorter() {
        this.sortingColumns = new Vector();
        this.ascending = true;
        this.lastSortedColumn = -1;
        this.indexes = new int[0];
    }
    
    public TableSorter(final TableModel model) {
        this.sortingColumns = new Vector();
        this.ascending = true;
        this.lastSortedColumn = -1;
        this.setModel(model);
    }
    
    @Override
    public void setModel(final TableModel model) {
        super.setModel(model);
        this.reallocateIndexes();
    }
    
    public int compareRowsByColumn(final int row1, final int row2, final int column) {
        final Class type = this.model.getColumnClass(column);
        final TableModel data = this.model;
        final Object o1 = data.getValueAt(row1, column);
        final Object o2 = data.getValueAt(row2, column);
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }
        if (type.getSuperclass() == Number.class) {
            return this.compareNumbers(data, row1, column, row2);
        }
        if (type == Date.class) {
            return this.compareDates(data, row1, column, row2);
        }
        if (type == String.class) {
            return this.compareStrings(data, row1, column, row2);
        }
        if (type == Boolean.class) {
            return this.compareBooleans(data, row1, column, row2);
        }
        return this.compareObjects(data, row1, column, row2);
    }
    
    private int compareObjects(final TableModel data, final int row1, final int column, final int row2) {
        final Object v1 = data.getValueAt(row1, column);
        final String s1 = v1.toString();
        final Object v2 = data.getValueAt(row2, column);
        final String s2 = v2.toString();
        final int result = s1.compareTo(s2);
        if (result < 0) {
            return -1;
        }
        if (result > 0) {
            return 1;
        }
        return 0;
    }
    
    private int compareBooleans(final TableModel data, final int row1, final int column, final int row2) {
        final Boolean bool1 = (Boolean)data.getValueAt(row1, column);
        final boolean b1 = bool1;
        final Boolean bool2 = (Boolean)data.getValueAt(row2, column);
        final boolean b2 = bool2;
        if (b1 == b2) {
            return 0;
        }
        if (b1) {
            return 1;
        }
        return -1;
    }
    
    private int compareStrings(final TableModel data, final int row1, final int column, final int row2) {
        final String s1 = (String)data.getValueAt(row1, column);
        final String s2 = (String)data.getValueAt(row2, column);
        final int result = s1.compareTo(s2);
        if (result < 0) {
            return -1;
        }
        if (result > 0) {
            return 1;
        }
        return 0;
    }
    
    private int compareDates(final TableModel data, final int row1, final int column, final int row2) {
        final Date d1 = (Date)data.getValueAt(row1, column);
        final long n1 = d1.getTime();
        final Date d2 = (Date)data.getValueAt(row2, column);
        final long n2 = d2.getTime();
        if (n1 < n2) {
            return -1;
        }
        if (n1 > n2) {
            return 1;
        }
        return 0;
    }
    
    private int compareNumbers(final TableModel data, final int row1, final int column, final int row2) {
        final Number n1 = (Number)data.getValueAt(row1, column);
        final double d1 = n1.doubleValue();
        final Number n2 = (Number)data.getValueAt(row2, column);
        final double d2 = n2.doubleValue();
        if (d1 < d2) {
            return -1;
        }
        if (d1 > d2) {
            return 1;
        }
        return 0;
    }
    
    public int compare(final int row1, final int row2) {
        for (int level = 0; level < this.sortingColumns.size(); ++level) {
            final Integer column = this.sortingColumns.elementAt(level);
            final int result = this.compareRowsByColumn(row1, row2, column);
            if (result != 0) {
                return this.ascending ? result : (-result);
            }
        }
        return 0;
    }
    
    public void reallocateIndexes() {
        final int rowCount = this.model.getRowCount();
        this.indexes = new int[rowCount];
        for (int row = 0; row < rowCount; ++row) {
            this.indexes[row] = row;
        }
    }
    
    @Override
    public void tableChanged(final TableModelEvent e) {
        System.out.println("Sorter: tableChanged");
        this.reallocateIndexes();
        super.tableChanged(e);
    }
    
    public void checkModel() {
        if (this.indexes.length != this.model.getRowCount()) {
            System.err.println("Sorter not informed of a change in model.");
        }
    }
    
    public void sort(final Object sender) {
        this.checkModel();
        this.shuttlesort(this.indexes.clone(), this.indexes, 0, this.indexes.length);
    }
    
    public void n2sort() {
        for (int i = 0; i < this.getRowCount(); ++i) {
            for (int j = i + 1; j < this.getRowCount(); ++j) {
                if (this.compare(this.indexes[i], this.indexes[j]) == -1) {
                    this.swap(i, j);
                }
            }
        }
    }
    
    public void shuttlesort(final int[] from, final int[] to, final int low, final int high) {
        if (high - low < 2) {
            return;
        }
        final int middle = (low + high) / 2;
        this.shuttlesort(to, from, low, middle);
        this.shuttlesort(to, from, middle, high);
        int p = low;
        int q = middle;
        if (high - low >= 4 && this.compare(from[middle - 1], from[middle]) <= 0) {
            System.arraycopy(from, low, to, low, high - low);
            return;
        }
        for (int i = low; i < high; ++i) {
            if (q >= high || (p < middle && this.compare(from[p], from[q]) <= 0)) {
                to[i] = from[p++];
            }
            else {
                to[i] = from[q++];
            }
        }
    }
    
    public void swap(final int i, final int j) {
        final int tmp = this.indexes[i];
        this.indexes[i] = this.indexes[j];
        this.indexes[j] = tmp;
    }
    
    @Override
    public Object getValueAt(final int aRow, final int aColumn) {
        this.checkModel();
        return this.model.getValueAt(this.indexes[aRow], aColumn);
    }
    
    @Override
    public void setValueAt(final Object aValue, final int aRow, final int aColumn) {
        this.checkModel();
        this.model.setValueAt(aValue, this.indexes[aRow], aColumn);
    }
    
    public void sortByColumn(final int column) {
        this.sortByColumn(column, true);
    }
    
    public void sortByColumn(final int column, final boolean ascending) {
        this.ascending = ascending;
        this.sortingColumns.removeAllElements();
        this.sortingColumns.addElement(column);
        this.sort(this);
        super.tableChanged(new TableModelEvent(this));
    }
    
    public void addMouseListenerToHeaderInTable(final JTable table) {
        final TableSorter sorter = this;
        final JTable tableView = table;
        tableView.setColumnSelectionAllowed(false);
        final MouseAdapter listMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final TableColumnModel columnModel = tableView.getColumnModel();
                final int viewColumn = columnModel.getColumnIndexAtX(e.getX());
                final int column = tableView.convertColumnIndexToModel(viewColumn);
                if (e.getClickCount() == 1 && column != -1) {
                    if (TableSorter.this.lastSortedColumn == column) {
                        TableSorter.this.ascending = !TableSorter.this.ascending;
                    }
                    sorter.sortByColumn(column, TableSorter.this.ascending);
                    TableSorter.this.lastSortedColumn = column;
                }
            }
        };
        final JTableHeader th = tableView.getTableHeader();
        th.addMouseListener(listMouseListener);
    }
}
