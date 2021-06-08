// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.impl;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class TableLayout extends JPanel
{
    private int rowCount;
    private int cellpadding;
    
    public TableLayout() {
        this.setLayout(new GridBagLayout());
    }
    
    @Deprecated
    public Component getComponent() {
        return this;
    }
    
    public int getCellpadding() {
        return this.cellpadding;
    }
    
    public void setCellpadding(final int cellpadding) {
        this.cellpadding = cellpadding;
    }
    
    public void addCell(final TableLayoutCell cell) {
        final GridBagConstraints constraints = cell.getConstraints();
        constraints.insets = new Insets(this.cellpadding, this.cellpadding, this.cellpadding, this.cellpadding);
        this.add(cell.getComponent(), constraints);
    }
    
    public int nextRowIndex() {
        return this.rowCount++;
    }
}
