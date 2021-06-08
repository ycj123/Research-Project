// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.impl;

import java.util.logging.Level;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.util.logging.Logger;

public class TableLayoutCell
{
    protected static final Logger LOG;
    private TableLayoutRow parent;
    private Component component;
    private GridBagConstraints constraints;
    private String align;
    private String valign;
    private int colspan;
    private int rowspan;
    private boolean colfill;
    private boolean rowfill;
    
    public int getColspan() {
        return this.colspan;
    }
    
    public int getRowspan() {
        return this.rowspan;
    }
    
    public TableLayoutCell(final TableLayoutRow parent) {
        this.colspan = 1;
        this.rowspan = 1;
        this.parent = parent;
    }
    
    public void addComponent(final Component component) {
        if (this.component != null) {
            TableLayoutCell.LOG.log(Level.WARNING, "This td cell already has a component: " + component);
        }
        this.component = component;
        this.parent.addCell(this);
    }
    
    public Component getComponent() {
        return this.component;
    }
    
    public void setAlign(final String align) {
        this.align = align;
    }
    
    public void setValign(final String valign) {
        this.valign = valign;
    }
    
    public void setColspan(final int colspan) {
        this.colspan = colspan;
    }
    
    public void setRowspan(final int rowspan) {
        this.rowspan = rowspan;
    }
    
    public boolean isColfill() {
        return this.colfill;
    }
    
    public boolean isRowfill() {
        return this.rowfill;
    }
    
    public void setColfill(final boolean colfill) {
        this.colfill = colfill;
    }
    
    public void setRowfill(final boolean rowfill) {
        this.rowfill = rowfill;
    }
    
    public GridBagConstraints getConstraints() {
        if (this.constraints == null) {
            this.constraints = this.createConstraints();
        }
        return this.constraints;
    }
    
    protected GridBagConstraints createConstraints() {
        final GridBagConstraints answer = new GridBagConstraints();
        answer.anchor = this.getAnchor();
        if (this.colspan < 1) {
            this.colspan = 1;
        }
        if (this.rowspan < 1) {
            this.rowspan = 1;
        }
        if (this.isColfill()) {
            answer.fill = (this.isRowfill() ? 1 : 2);
        }
        else {
            answer.fill = (this.isRowfill() ? 3 : 0);
        }
        answer.weightx = 0.2;
        answer.weighty = 0.0;
        answer.gridwidth = this.colspan;
        answer.gridheight = this.rowspan;
        return answer;
    }
    
    protected int getAnchor() {
        final boolean isTop = "top".equalsIgnoreCase(this.valign);
        final boolean isBottom = "bottom".equalsIgnoreCase(this.valign);
        if ("center".equalsIgnoreCase(this.align)) {
            if (isTop) {
                return 11;
            }
            if (isBottom) {
                return 15;
            }
            return 10;
        }
        else if ("right".equalsIgnoreCase(this.align)) {
            if (isTop) {
                return 12;
            }
            if (isBottom) {
                return 14;
            }
            return 13;
        }
        else {
            if (isTop) {
                return 18;
            }
            if (isBottom) {
                return 16;
            }
            return 17;
        }
    }
    
    static {
        LOG = Logger.getLogger(TableLayoutCell.class.getName());
    }
}
