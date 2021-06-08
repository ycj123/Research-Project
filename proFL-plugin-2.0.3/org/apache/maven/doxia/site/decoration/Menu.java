// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.site.decoration;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Menu implements Serializable
{
    private String name;
    private String inherit;
    private boolean inheritAsRef;
    private String ref;
    private String img;
    private List items;
    
    public Menu() {
        this.inheritAsRef = false;
    }
    
    public void addItem(final MenuItem menuItem) {
        if (!(menuItem instanceof MenuItem)) {
            throw new ClassCastException("Menu.addItems(menuItem) parameter must be instanceof " + MenuItem.class.getName());
        }
        this.getItems().add(menuItem);
    }
    
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Menu)) {
            return false;
        }
        final Menu that = (Menu)other;
        boolean result = true;
        result = (result && ((this.getName() != null) ? this.getName().equals(that.getName()) : (that.getName() == null)));
        result = (result && ((this.getInherit() != null) ? this.getInherit().equals(that.getInherit()) : (that.getInherit() == null)));
        result = (result && ((this.getRef() != null) ? this.getRef().equals(that.getRef()) : (that.getRef() == null)));
        result = (result && ((this.getImg() != null) ? this.getImg().equals(that.getImg()) : (that.getImg() == null)));
        result = (result && ((this.getItems() != null) ? this.getItems().equals(that.getItems()) : (that.getItems() == null)));
        return result;
    }
    
    public String getImg() {
        return this.img;
    }
    
    public String getInherit() {
        return this.inherit;
    }
    
    public List getItems() {
        if (this.items == null) {
            this.items = new ArrayList();
        }
        return this.items;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getRef() {
        return this.ref;
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((this.name != null) ? this.name.hashCode() : 0);
        result = 37 * result + ((this.inherit != null) ? this.inherit.hashCode() : 0);
        result = 37 * result + ((this.ref != null) ? this.ref.hashCode() : 0);
        result = 37 * result + ((this.img != null) ? this.img.hashCode() : 0);
        result = 37 * result + ((this.items != null) ? this.items.hashCode() : 0);
        return result;
    }
    
    public boolean isInheritAsRef() {
        return this.inheritAsRef;
    }
    
    public void removeItem(final MenuItem menuItem) {
        if (!(menuItem instanceof MenuItem)) {
            throw new ClassCastException("Menu.removeItems(menuItem) parameter must be instanceof " + MenuItem.class.getName());
        }
        this.getItems().remove(menuItem);
    }
    
    public void setImg(final String img) {
        this.img = img;
    }
    
    public void setInherit(final String inherit) {
        this.inherit = inherit;
    }
    
    public void setInheritAsRef(final boolean inheritAsRef) {
        this.inheritAsRef = inheritAsRef;
    }
    
    public void setItems(final List items) {
        this.items = items;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setRef(final String ref) {
        this.ref = ref;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("name = '");
        buf.append(this.getName());
        buf.append("'");
        buf.append("\n");
        buf.append("inherit = '");
        buf.append(this.getInherit());
        buf.append("'");
        buf.append("\n");
        buf.append("ref = '");
        buf.append(this.getRef());
        buf.append("'");
        buf.append("\n");
        buf.append("img = '");
        buf.append(this.getImg());
        buf.append("'");
        buf.append("\n");
        buf.append("items = '");
        buf.append(this.getItems());
        buf.append("'");
        return buf.toString();
    }
}
