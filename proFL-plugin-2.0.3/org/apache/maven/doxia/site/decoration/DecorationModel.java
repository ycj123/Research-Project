// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.site.decoration;

import java.util.Collections;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.io.Serializable;

public class DecorationModel implements Serializable
{
    private String name;
    private Banner bannerLeft;
    private Banner bannerRight;
    private PublishDate publishDate;
    private Version version;
    private List poweredBy;
    private Skin skin;
    private Body body;
    private Object custom;
    private String modelEncoding;
    private Map menusByRef;
    
    public DecorationModel() {
        this.modelEncoding = "UTF-8";
    }
    
    public void addPoweredBy(final Logo logo) {
        if (!(logo instanceof Logo)) {
            throw new ClassCastException("DecorationModel.addPoweredBy(logo) parameter must be instanceof " + Logo.class.getName());
        }
        this.getPoweredBy().add(logo);
    }
    
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DecorationModel)) {
            return false;
        }
        final DecorationModel that = (DecorationModel)other;
        boolean result = true;
        result = (result && ((this.getName() != null) ? this.getName().equals(that.getName()) : (that.getName() == null)));
        result = (result && ((this.getBannerLeft() != null) ? this.getBannerLeft().equals(that.getBannerLeft()) : (that.getBannerLeft() == null)));
        result = (result && ((this.getBannerRight() != null) ? this.getBannerRight().equals(that.getBannerRight()) : (that.getBannerRight() == null)));
        result = (result && ((this.getPublishDate() != null) ? this.getPublishDate().equals(that.getPublishDate()) : (that.getPublishDate() == null)));
        result = (result && ((this.getVersion() != null) ? this.getVersion().equals(that.getVersion()) : (that.getVersion() == null)));
        result = (result && ((this.getPoweredBy() != null) ? this.getPoweredBy().equals(that.getPoweredBy()) : (that.getPoweredBy() == null)));
        result = (result && ((this.getSkin() != null) ? this.getSkin().equals(that.getSkin()) : (that.getSkin() == null)));
        result = (result && ((this.getBody() != null) ? this.getBody().equals(that.getBody()) : (that.getBody() == null)));
        result = (result && ((this.getCustom() != null) ? this.getCustom().equals(that.getCustom()) : (that.getCustom() == null)));
        return result;
    }
    
    public Banner getBannerLeft() {
        return this.bannerLeft;
    }
    
    public Banner getBannerRight() {
        return this.bannerRight;
    }
    
    public Body getBody() {
        return this.body;
    }
    
    public Object getCustom() {
        return this.custom;
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
    
    public String getName() {
        return this.name;
    }
    
    public List getPoweredBy() {
        if (this.poweredBy == null) {
            this.poweredBy = new ArrayList();
        }
        return this.poweredBy;
    }
    
    public PublishDate getPublishDate() {
        return this.publishDate;
    }
    
    public Skin getSkin() {
        return this.skin;
    }
    
    public Version getVersion() {
        return this.version;
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((this.name != null) ? this.name.hashCode() : 0);
        result = 37 * result + ((this.bannerLeft != null) ? this.bannerLeft.hashCode() : 0);
        result = 37 * result + ((this.bannerRight != null) ? this.bannerRight.hashCode() : 0);
        result = 37 * result + ((this.publishDate != null) ? this.publishDate.hashCode() : 0);
        result = 37 * result + ((this.version != null) ? this.version.hashCode() : 0);
        result = 37 * result + ((this.poweredBy != null) ? this.poweredBy.hashCode() : 0);
        result = 37 * result + ((this.skin != null) ? this.skin.hashCode() : 0);
        result = 37 * result + ((this.body != null) ? this.body.hashCode() : 0);
        result = 37 * result + ((this.custom != null) ? this.custom.hashCode() : 0);
        return result;
    }
    
    public void removePoweredBy(final Logo logo) {
        if (!(logo instanceof Logo)) {
            throw new ClassCastException("DecorationModel.removePoweredBy(logo) parameter must be instanceof " + Logo.class.getName());
        }
        this.getPoweredBy().remove(logo);
    }
    
    public void setBannerLeft(final Banner bannerLeft) {
        this.bannerLeft = bannerLeft;
    }
    
    public void setBannerRight(final Banner bannerRight) {
        this.bannerRight = bannerRight;
    }
    
    public void setBody(final Body body) {
        this.body = body;
    }
    
    public void setCustom(final Object custom) {
        this.custom = custom;
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setPoweredBy(final List poweredBy) {
        this.poweredBy = poweredBy;
    }
    
    public void setPublishDate(final PublishDate publishDate) {
        this.publishDate = publishDate;
    }
    
    public void setSkin(final Skin skin) {
        this.skin = skin;
    }
    
    public void setVersion(final Version version) {
        this.version = version;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("name = '");
        buf.append(this.getName());
        buf.append("'");
        buf.append("\n");
        buf.append("bannerLeft = '");
        buf.append(this.getBannerLeft());
        buf.append("'");
        buf.append("\n");
        buf.append("bannerRight = '");
        buf.append(this.getBannerRight());
        buf.append("'");
        buf.append("\n");
        buf.append("publishDate = '");
        buf.append(this.getPublishDate());
        buf.append("'");
        buf.append("\n");
        buf.append("version = '");
        buf.append(this.getVersion());
        buf.append("'");
        buf.append("\n");
        buf.append("poweredBy = '");
        buf.append(this.getPoweredBy());
        buf.append("'");
        buf.append("\n");
        buf.append("skin = '");
        buf.append(this.getSkin());
        buf.append("'");
        buf.append("\n");
        buf.append("body = '");
        buf.append(this.getBody());
        buf.append("'");
        buf.append("\n");
        buf.append("custom = '");
        buf.append(this.getCustom());
        buf.append("'");
        return buf.toString();
    }
    
    public Menu getMenuRef(final String key) {
        if (this.menusByRef == null) {
            this.menusByRef = new HashMap();
            if (this.body != null) {
                for (final Menu menu : this.body.getMenus()) {
                    if (menu.getRef() != null) {
                        this.menusByRef.put(menu.getRef(), menu);
                    }
                }
            }
        }
        return this.menusByRef.get(key);
    }
    
    public void removeMenuRef(final String key) {
        if (this.body != null) {
            final Iterator i = this.body.getMenus().iterator();
            while (i.hasNext()) {
                final Menu menu = i.next();
                if (key.equals(menu.getRef())) {
                    i.remove();
                }
            }
        }
    }
    
    public List getMenus() {
        List menus;
        if (this.body != null && this.body.getMenus() != null) {
            menus = this.body.getMenus();
        }
        else {
            menus = Collections.EMPTY_LIST;
        }
        return menus;
    }
}
