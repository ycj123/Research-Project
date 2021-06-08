// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.site.decoration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.Serializable;

public class MenuItem extends LinkItem implements Serializable
{
    private String description;
    private boolean collapse;
    private String ref;
    private List items;
    
    public MenuItem() {
        this.collapse = false;
    }
    
    public void addItem(final MenuItem menuItem) {
        if (!(menuItem instanceof MenuItem)) {
            throw new ClassCastException("MenuItem.addItems(menuItem) parameter must be instanceof " + MenuItem.class.getName());
        }
        this.getItems().add(menuItem);
        menuItem.createMenuItemAssociation(this);
    }
    
    public void breakMenuItemAssociation(final MenuItem menuItem) {
        if (!this.getItems().contains(menuItem)) {
            throw new IllegalStateException("menuItem isn't associated.");
        }
        this.getItems().remove(menuItem);
    }
    
    public void createMenuItemAssociation(final MenuItem menuItem) {
        final Collection items = this.getItems();
        if (items.contains(menuItem)) {
            throw new IllegalStateException("menuItem is already assigned.");
        }
        items.add(menuItem);
    }
    
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MenuItem)) {
            return false;
        }
        final MenuItem that = (MenuItem)other;
        boolean result = true;
        result = (result && ((this.getDescription() != null) ? this.getDescription().equals(that.getDescription()) : (that.getDescription() == null)));
        result = (result && this.collapse == that.collapse);
        result = (result && ((this.getRef() != null) ? this.getRef().equals(that.getRef()) : (that.getRef() == null)));
        result = (result && ((this.getItems() != null) ? this.getItems().equals(that.getItems()) : (that.getItems() == null)));
        result = (result && super.equals(other));
        return result;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public List getItems() {
        if (this.items == null) {
            this.items = new ArrayList();
        }
        return this.items;
    }
    
    public String getRef() {
        return this.ref;
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((this.description != null) ? this.description.hashCode() : 0);
        result = 37 * result + (this.collapse ? 0 : 1);
        result = 37 * result + ((this.ref != null) ? this.ref.hashCode() : 0);
        result = 37 * result + ((this.items != null) ? this.items.hashCode() : 0);
        result = 37 * result + super.hashCode();
        return result;
    }
    
    public boolean isCollapse() {
        return this.collapse;
    }
    
    public void removeItem(final MenuItem menuItem) {
        if (!(menuItem instanceof MenuItem)) {
            throw new ClassCastException("MenuItem.removeItems(menuItem) parameter must be instanceof " + MenuItem.class.getName());
        }
        menuItem.breakMenuItemAssociation(this);
        this.getItems().remove(menuItem);
    }
    
    public void setCollapse(final boolean collapse) {
        this.collapse = collapse;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void setItems(final List items) {
        this.items = items;
    }
    
    public void setRef(final String ref) {
        this.ref = ref;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("description = '");
        buf.append(this.getDescription());
        buf.append("'");
        buf.append("\n");
        buf.append("collapse = '");
        buf.append(this.isCollapse());
        buf.append("'");
        buf.append("\n");
        buf.append("ref = '");
        buf.append(this.getRef());
        buf.append("'");
        buf.append("\n");
        buf.append("items = '");
        buf.append(this.getItems());
        buf.append("'");
        buf.append("\n");
        buf.append(super.toString());
        return buf.toString();
    }
}
