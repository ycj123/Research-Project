// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.impl;

import javax.swing.DefaultListCellRenderer;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.JTable;
import javax.swing.JList;
import java.util.List;
import groovy.lang.Closure;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.ListCellRenderer;

public class ClosureRenderer implements ListCellRenderer, TableCellRenderer, TreeCellRenderer
{
    Closure update;
    List children;
    JList list;
    JTable table;
    JTree tree;
    Object value;
    boolean selected;
    boolean focused;
    boolean leaf;
    boolean expanded;
    int row;
    int column;
    boolean tableHeader;
    private boolean defaultRenderer;
    
    public ClosureRenderer() {
        this(null);
    }
    
    public ClosureRenderer(final Closure c) {
        this.children = new ArrayList();
        this.setUpdate(c);
    }
    
    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
        this.list = list;
        this.table = null;
        this.tree = null;
        this.value = value;
        this.row = index;
        this.column = -1;
        this.selected = isSelected;
        this.focused = cellHasFocus;
        this.leaf = false;
        this.expanded = false;
        return this.render();
    }
    
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        this.list = null;
        this.table = table;
        this.tree = null;
        this.value = value;
        this.row = row;
        this.column = column;
        this.selected = isSelected;
        this.focused = hasFocus;
        this.leaf = false;
        this.expanded = false;
        return this.render();
    }
    
    public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean selected, final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
        this.list = null;
        this.table = null;
        this.tree = tree;
        this.value = value;
        this.row = row;
        this.column = -1;
        this.selected = selected;
        this.focused = hasFocus;
        this.leaf = leaf;
        this.expanded = expanded;
        return this.render();
    }
    
    private Component render() {
        if (this.children.isEmpty() || this.defaultRenderer) {
            this.defaultRenderer = true;
            this.children.clear();
            if (this.table != null) {
                TableCellRenderer tcr;
                if (this.tableHeader) {
                    tcr = this.table.getTableHeader().getDefaultRenderer();
                }
                else {
                    tcr = this.table.getDefaultRenderer(this.table.getColumnClass(this.column));
                }
                this.children.add(tcr.getTableCellRendererComponent(this.table, this.value, this.selected, this.focused, this.row, this.column));
            }
            else if (this.tree != null) {
                final TreeCellRenderer tcr2 = new DefaultTreeCellRenderer();
                this.children.add(tcr2.getTreeCellRendererComponent(this.tree, this.value, this.selected, this.expanded, this.leaf, this.row, this.focused));
            }
            else if (this.list != null) {
                ListCellRenderer lcr = (ListCellRenderer)UIManager.get("List.cellRenderer");
                if (lcr == null) {
                    lcr = new DefaultListCellRenderer();
                }
                this.children.add(lcr.getListCellRendererComponent(this.list, this.value, this.row, this.selected, this.focused));
            }
        }
        final Object o = this.update.call();
        if (o instanceof Component) {
            return (Component)o;
        }
        return this.children.get(0);
    }
    
    public Closure getUpdate() {
        return this.update;
    }
    
    public void setUpdate(final Closure update) {
        if (update != null) {
            update.setDelegate(this);
            update.setResolveStrategy(1);
        }
        this.update = update;
    }
    
    public void setTableHeader(final boolean tableHeader) {
        this.tableHeader = tableHeader;
    }
    
    public boolean isTableHeader() {
        return this.tableHeader;
    }
    
    public List getChildren() {
        return this.children;
    }
    
    public JList getList() {
        return this.list;
    }
    
    public JTable getTable() {
        return this.table;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public boolean isSelected() {
        return this.selected;
    }
    
    public boolean isFocused() {
        return this.focused;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public int getColumn() {
        return this.column;
    }
    
    public JTree getTree() {
        return this.tree;
    }
    
    public boolean isLeaf() {
        return this.leaf;
    }
    
    public boolean isExpanded() {
        return this.expanded;
    }
    
    public boolean isDefaultRenderer() {
        return this.defaultRenderer;
    }
}
