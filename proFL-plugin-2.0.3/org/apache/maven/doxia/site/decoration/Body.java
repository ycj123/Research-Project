// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.site.decoration;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Body implements Serializable
{
    private Object head;
    private List links;
    private List breadcrumbs;
    private List menus;
    
    public void addBreadcrumb(final LinkItem linkItem) {
        if (!(linkItem instanceof LinkItem)) {
            throw new ClassCastException("Body.addBreadcrumbs(linkItem) parameter must be instanceof " + LinkItem.class.getName());
        }
        this.getBreadcrumbs().add(linkItem);
    }
    
    public void addLink(final LinkItem linkItem) {
        if (!(linkItem instanceof LinkItem)) {
            throw new ClassCastException("Body.addLinks(linkItem) parameter must be instanceof " + LinkItem.class.getName());
        }
        this.getLinks().add(linkItem);
    }
    
    public void addMenu(final Menu menu) {
        if (!(menu instanceof Menu)) {
            throw new ClassCastException("Body.addMenus(menu) parameter must be instanceof " + Menu.class.getName());
        }
        this.getMenus().add(menu);
    }
    
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Body)) {
            return false;
        }
        final Body that = (Body)other;
        boolean result = true;
        result = (result && ((this.getHead() != null) ? this.getHead().equals(that.getHead()) : (that.getHead() == null)));
        result = (result && ((this.getLinks() != null) ? this.getLinks().equals(that.getLinks()) : (that.getLinks() == null)));
        result = (result && ((this.getBreadcrumbs() != null) ? this.getBreadcrumbs().equals(that.getBreadcrumbs()) : (that.getBreadcrumbs() == null)));
        result = (result && ((this.getMenus() != null) ? this.getMenus().equals(that.getMenus()) : (that.getMenus() == null)));
        return result;
    }
    
    public List getBreadcrumbs() {
        if (this.breadcrumbs == null) {
            this.breadcrumbs = new ArrayList();
        }
        return this.breadcrumbs;
    }
    
    public Object getHead() {
        return this.head;
    }
    
    public List getLinks() {
        if (this.links == null) {
            this.links = new ArrayList();
        }
        return this.links;
    }
    
    public List getMenus() {
        if (this.menus == null) {
            this.menus = new ArrayList();
        }
        return this.menus;
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((this.head != null) ? this.head.hashCode() : 0);
        result = 37 * result + ((this.links != null) ? this.links.hashCode() : 0);
        result = 37 * result + ((this.breadcrumbs != null) ? this.breadcrumbs.hashCode() : 0);
        result = 37 * result + ((this.menus != null) ? this.menus.hashCode() : 0);
        return result;
    }
    
    public void removeBreadcrumb(final LinkItem linkItem) {
        if (!(linkItem instanceof LinkItem)) {
            throw new ClassCastException("Body.removeBreadcrumbs(linkItem) parameter must be instanceof " + LinkItem.class.getName());
        }
        this.getBreadcrumbs().remove(linkItem);
    }
    
    public void removeLink(final LinkItem linkItem) {
        if (!(linkItem instanceof LinkItem)) {
            throw new ClassCastException("Body.removeLinks(linkItem) parameter must be instanceof " + LinkItem.class.getName());
        }
        this.getLinks().remove(linkItem);
    }
    
    public void removeMenu(final Menu menu) {
        if (!(menu instanceof Menu)) {
            throw new ClassCastException("Body.removeMenus(menu) parameter must be instanceof " + Menu.class.getName());
        }
        this.getMenus().remove(menu);
    }
    
    public void setBreadcrumbs(final List breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
    }
    
    public void setHead(final Object head) {
        this.head = head;
    }
    
    public void setLinks(final List links) {
        this.links = links;
    }
    
    public void setMenus(final List menus) {
        this.menus = menus;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("head = '");
        buf.append(this.getHead());
        buf.append("'");
        buf.append("\n");
        buf.append("links = '");
        buf.append(this.getLinks());
        buf.append("'");
        buf.append("\n");
        buf.append("breadcrumbs = '");
        buf.append(this.getBreadcrumbs());
        buf.append("'");
        buf.append("\n");
        buf.append("menus = '");
        buf.append(this.getMenus());
        buf.append("'");
        return buf.toString();
    }
}
