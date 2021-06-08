// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.impl;

import java.awt.GridBagConstraints;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class TableLayoutRow
{
    private final TableLayout parent;
    private final List<TableLayoutCell> cells;
    private int rowIndex;
    
    public TableLayoutRow(final TableLayout tableLayoutTag) {
        this.cells = new ArrayList<TableLayoutCell>();
        this.parent = tableLayoutTag;
    }
    
    public void addCell(final TableLayoutCell tag) {
        int gridx = 0;
        for (final TableLayoutCell cell : this.cells) {
            gridx += cell.getColspan();
        }
        tag.getConstraints().gridx = gridx;
        this.cells.add(tag);
    }
    
    public void addComponentsForRow() {
        this.rowIndex = this.parent.nextRowIndex();
        for (final TableLayoutCell cell : this.cells) {
            final GridBagConstraints c = cell.getConstraints();
            c.gridy = this.rowIndex;
            this.parent.addCell(cell);
        }
    }
    
    public int getRowIndex() {
        return this.rowIndex;
    }
}
