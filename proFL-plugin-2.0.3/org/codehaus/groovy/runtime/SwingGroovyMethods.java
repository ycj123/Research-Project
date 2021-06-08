// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.MenuElement;
import javax.swing.JPopupMenu;
import javax.swing.JMenuBar;
import groovy.lang.GString;
import javax.swing.JMenuItem;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.util.Iterator;
import java.awt.Component;
import java.awt.Container;

public class SwingGroovyMethods
{
    public static int size(final Container self) {
        return self.getComponentCount();
    }
    
    public static Component getAt(final Container self, final int index) {
        return self.getComponent(index);
    }
    
    public static Container leftShift(final Container self, final Component c) {
        self.add(c);
        return self;
    }
    
    public static Iterator<Component> iterator(final Container self) {
        return DefaultGroovyMethods.iterator(self.getComponents());
    }
    
    public static void clear(final Container self) {
        self.removeAll();
    }
    
    public static int size(final ButtonGroup self) {
        return self.getButtonCount();
    }
    
    public static AbstractButton getAt(final ButtonGroup self, final int index) {
        final int size = self.getButtonCount();
        if (index < 0 || index >= size) {
            return null;
        }
        final Enumeration buttons = self.getElements();
        for (int i = 0; i <= index; ++i) {
            final AbstractButton b = buttons.nextElement();
            if (i == index) {
                return b;
            }
        }
        return null;
    }
    
    public static ButtonGroup leftShift(final ButtonGroup self, final AbstractButton b) {
        self.add(b);
        return self;
    }
    
    public static Iterator<AbstractButton> iterator(final ButtonGroup self) {
        return DefaultGroovyMethods.iterator(self.getElements());
    }
    
    public static int size(final ListModel self) {
        return self.getSize();
    }
    
    public static Object getAt(final ListModel self, final int index) {
        return self.getElementAt(index);
    }
    
    public static Iterator iterator(final ListModel self) {
        return new Iterator() {
            private int index = 0;
            
            public boolean hasNext() {
                return this.index < self.getSize();
            }
            
            public Object next() {
                return self.getElementAt(this.index++);
            }
            
            public void remove() {
                throw new UnsupportedOperationException("LisModel is immutable.");
            }
        };
    }
    
    public static DefaultListModel leftShift(final DefaultListModel self, final Object e) {
        self.addElement(e);
        return self;
    }
    
    public static void putAt(final DefaultListModel self, final int index, final Object e) {
        self.set(index, e);
    }
    
    public static void clear(final DefaultListModel self) {
        self.removeAllElements();
    }
    
    public static Iterator iterator(final DefaultListModel self) {
        return new Iterator() {
            private int index = 0;
            
            public boolean hasNext() {
                return this.index > -1 && this.index < self.getSize();
            }
            
            public Object next() {
                return self.getElementAt(this.index++);
            }
            
            public void remove() {
                if (this.hasNext()) {
                    self.removeElementAt(this.index--);
                }
            }
        };
    }
    
    public static int size(final JComboBox self) {
        return self.getItemCount();
    }
    
    public static Object getAt(final JComboBox self, final int index) {
        return self.getItemAt(index);
    }
    
    public static JComboBox leftShift(final JComboBox self, final Object i) {
        self.addItem(i);
        return self;
    }
    
    public static void clear(final JComboBox self) {
        self.removeAllItems();
    }
    
    public static Iterator iterator(final JComboBox self) {
        return iterator(self.getModel());
    }
    
    public static MutableComboBoxModel leftShift(final MutableComboBoxModel self, final Object i) {
        self.addElement(i);
        return self;
    }
    
    public static void putAt(final MutableComboBoxModel self, final int index, final Object i) {
        self.insertElementAt(i, index);
    }
    
    public static Iterator iterator(final MutableComboBoxModel self) {
        return new Iterator() {
            private int index = 0;
            
            public boolean hasNext() {
                return this.index > -1 && this.index < self.getSize();
            }
            
            public Object next() {
                return self.getElementAt(this.index++);
            }
            
            public void remove() {
                if (this.hasNext()) {
                    self.removeElementAt(this.index--);
                }
            }
        };
    }
    
    public static void clear(final DefaultComboBoxModel self) {
        self.removeAllElements();
    }
    
    public static int size(final TableModel self) {
        return self.getRowCount();
    }
    
    public static Object[] getAt(final TableModel self, final int index) {
        final int cols = self.getColumnCount();
        final Object[] rowData = new Object[cols];
        for (int col = 0; col < cols; ++col) {
            rowData[col] = self.getValueAt(index, col);
        }
        return rowData;
    }
    
    public static Iterator iterator(final TableModel self) {
        return new Iterator() {
            private int row = 0;
            
            public boolean hasNext() {
                return this.row < self.getRowCount();
            }
            
            public Object next() {
                final int cols = self.getColumnCount();
                final Object[] rowData = new Object[cols];
                for (int col = 0; col < cols; ++col) {
                    rowData[col] = self.getValueAt(this.row, col);
                }
                ++this.row;
                return rowData;
            }
            
            public void remove() {
                throw new UnsupportedOperationException("TableModel is immutable.");
            }
        };
    }
    
    public static DefaultTableModel leftShift(final DefaultTableModel self, final Object row) {
        if (row == null) {
            self.addRow((Object[])null);
            return self;
        }
        self.addRow(buildRowData(self, row));
        return self;
    }
    
    public static void putAt(final DefaultTableModel self, final int index, final Object row) {
        if (row == null) {
            self.insertRow(index, (Object[])null);
            return;
        }
        self.insertRow(index, buildRowData(self, row));
    }
    
    private static Object[] buildRowData(final DefaultTableModel delegate, final Object row) {
        final int cols = delegate.getColumnCount();
        final Object[] rowData = new Object[cols];
        int i = 0;
        for (Iterator it = DefaultGroovyMethods.iterator(row); it.hasNext() && i < cols; rowData[i++] = it.next()) {}
        return rowData;
    }
    
    public static Iterator iterator(final DefaultTableModel self) {
        return new Iterator() {
            private int row = 0;
            
            public boolean hasNext() {
                return this.row > -1 && this.row < self.getRowCount();
            }
            
            public Object next() {
                final int cols = self.getColumnCount();
                final Object[] rowData = new Object[cols];
                for (int col = 0; col < cols; ++col) {
                    rowData[col] = self.getValueAt(this.row, col);
                }
                ++this.row;
                return rowData;
            }
            
            public void remove() {
                if (this.hasNext()) {
                    self.removeRow(this.row--);
                }
            }
        };
    }
    
    public static int size(final TableColumnModel self) {
        return self.getColumnCount();
    }
    
    public static TableColumn getAt(final TableColumnModel self, final int index) {
        return self.getColumn(index);
    }
    
    public static Iterator<TableColumn> iterator(final TableColumnModel self) {
        return new Iterator<TableColumn>() {
            private int index = 0;
            
            public boolean hasNext() {
                return this.index > -1 && this.index < self.getColumnCount();
            }
            
            public TableColumn next() {
                return self.getColumn(this.index++);
            }
            
            public void remove() {
                if (this.hasNext()) {
                    self.removeColumn(self.getColumn(this.index--));
                }
            }
        };
    }
    
    public static TableColumnModel leftShift(final TableColumnModel self, final TableColumn column) {
        self.addColumn(column);
        return self;
    }
    
    public static int size(final TreePath self) {
        return self.getPathCount();
    }
    
    public static Object getAt(final TreePath self, final int index) {
        return self.getPath()[index];
    }
    
    public static TreePath leftShift(final TreePath self, final Object p) {
        return self.pathByAddingChild(p);
    }
    
    public static Iterator iterator(final TreePath self) {
        return DefaultGroovyMethods.iterator(self.getPath());
    }
    
    public static int size(final TreeNode self) {
        return self.getChildCount();
    }
    
    public static TreeNode getAt(final TreeNode self, final int index) {
        return self.getChildAt(index);
    }
    
    public static Iterator<TreeNode> iterator(final TreeNode self) {
        return DefaultGroovyMethods.iterator(self.children());
    }
    
    public static MutableTreeNode leftShift(final MutableTreeNode self, final MutableTreeNode node) {
        self.insert(node, self.getChildCount());
        return self;
    }
    
    public static void putAt(final MutableTreeNode self, final int index, final MutableTreeNode node) {
        self.insert(node, index);
    }
    
    public static DefaultMutableTreeNode leftShift(final DefaultMutableTreeNode self, final DefaultMutableTreeNode node) {
        self.add(node);
        return self;
    }
    
    public static void clear(final DefaultMutableTreeNode self) {
        self.removeAllChildren();
    }
    
    public static int size(final JMenu self) {
        return self.getMenuComponentCount();
    }
    
    public static Component getAt(final JMenu self, final int index) {
        return self.getMenuComponent(index);
    }
    
    public static JMenu leftShift(final JMenu self, final Action action) {
        self.add(action);
        return self;
    }
    
    public static JMenu leftShift(final JMenu self, final Component component) {
        self.add(component);
        return self;
    }
    
    public static JMenu leftShift(final JMenu self, final JMenuItem item) {
        self.add(item);
        return self;
    }
    
    public static JMenu leftShift(final JMenu self, final String str) {
        self.add(str);
        return self;
    }
    
    public static JMenu leftShift(final JMenu self, final GString gstr) {
        self.add(gstr.toString());
        return self;
    }
    
    public static Iterator iterator(final JMenu self) {
        return DefaultGroovyMethods.iterator(self.getMenuComponents());
    }
    
    public static int size(final JMenuBar self) {
        return self.getMenuCount();
    }
    
    public static JMenu getAt(final JMenuBar self, final int index) {
        return self.getMenu(index);
    }
    
    public static JMenuBar leftShift(final JMenuBar self, final JMenu menu) {
        self.add(menu);
        return self;
    }
    
    public static Iterator iterator(final JMenuBar self) {
        return DefaultGroovyMethods.iterator(self.getSubElements());
    }
    
    public static JPopupMenu leftShift(final JPopupMenu self, final Action action) {
        self.add(action);
        return self;
    }
    
    public static JPopupMenu leftShift(final JPopupMenu self, final Component component) {
        self.add(component);
        return self;
    }
    
    public static JPopupMenu leftShift(final JPopupMenu self, final JMenuItem item) {
        self.add(item);
        return self;
    }
    
    public static JPopupMenu leftShift(final JPopupMenu self, final String str) {
        self.add(str);
        return self;
    }
    
    public static JPopupMenu leftShift(final JPopupMenu self, final GString gstr) {
        self.add(gstr.toString());
        return self;
    }
    
    public static Iterator<MenuElement> iterator(final JPopupMenu self) {
        return DefaultGroovyMethods.iterator(self.getSubElements());
    }
    
    public static int size(final JTabbedPane self) {
        return self.getTabCount();
    }
    
    public static void clear(final JTabbedPane self) {
        self.removeAll();
    }
    
    public static Component getAt(final JTabbedPane self, final int index) {
        return self.getComponentAt(index);
    }
    
    public static Iterator<Component> iterator(final JTabbedPane self) {
        return new Iterator<Component>() {
            private int index = 0;
            
            public boolean hasNext() {
                return this.index > -1 && this.index < self.getTabCount();
            }
            
            public Component next() {
                return self.getComponentAt(this.index++);
            }
            
            public void remove() {
                if (this.hasNext()) {
                    self.removeTabAt(this.index--);
                }
            }
        };
    }
    
    public static JToolBar leftShift(final JToolBar self, final Action action) {
        self.add(action);
        return self;
    }
    
    public static Component getAt(final JToolBar self, final int index) {
        return self.getComponentAtIndex(index);
    }
}
