// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.site.decoration.io.xpp3;

import org.apache.maven.doxia.site.decoration.Version;
import org.apache.maven.doxia.site.decoration.Skin;
import org.apache.maven.doxia.site.decoration.PublishDate;
import org.apache.maven.doxia.site.decoration.MenuItem;
import org.apache.maven.doxia.site.decoration.Logo;
import java.util.Iterator;
import org.apache.maven.doxia.site.decoration.Menu;
import org.apache.maven.doxia.site.decoration.LinkItem;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.apache.maven.doxia.site.decoration.Body;
import org.apache.maven.doxia.site.decoration.Banner;
import java.io.IOException;
import org.codehaus.plexus.util.xml.pull.XmlSerializer;
import org.codehaus.plexus.util.xml.pull.MXSerializer;
import org.apache.maven.doxia.site.decoration.DecorationModel;
import java.io.Writer;

public class DecorationXpp3Writer
{
    private static final String NAMESPACE;
    
    public void write(final Writer writer, final DecorationModel decorationModel) throws IOException {
        final XmlSerializer serializer = new MXSerializer();
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "  ");
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", "\n");
        serializer.setOutput(writer);
        serializer.startDocument(decorationModel.getModelEncoding(), null);
        this.writeDecorationModel(decorationModel, "project", serializer);
        serializer.endDocument();
    }
    
    private void writeBanner(final Banner banner, final String tagName, final XmlSerializer serializer) throws IOException {
        if (banner != null) {
            serializer.startTag(DecorationXpp3Writer.NAMESPACE, tagName);
            if (banner.getName() != null) {
                serializer.startTag(DecorationXpp3Writer.NAMESPACE, "name").text(banner.getName()).endTag(DecorationXpp3Writer.NAMESPACE, "name");
            }
            if (banner.getSrc() != null) {
                serializer.startTag(DecorationXpp3Writer.NAMESPACE, "src").text(banner.getSrc()).endTag(DecorationXpp3Writer.NAMESPACE, "src");
            }
            if (banner.getAlt() != null) {
                serializer.startTag(DecorationXpp3Writer.NAMESPACE, "alt").text(banner.getAlt()).endTag(DecorationXpp3Writer.NAMESPACE, "alt");
            }
            if (banner.getHref() != null) {
                serializer.startTag(DecorationXpp3Writer.NAMESPACE, "href").text(banner.getHref()).endTag(DecorationXpp3Writer.NAMESPACE, "href");
            }
            serializer.endTag(DecorationXpp3Writer.NAMESPACE, tagName);
        }
    }
    
    private void writeBody(final Body body, final String tagName, final XmlSerializer serializer) throws IOException {
        if (body != null) {
            serializer.startTag(DecorationXpp3Writer.NAMESPACE, tagName);
            if (body.getHead() != null) {
                ((Xpp3Dom)body.getHead()).writeToSerializer(DecorationXpp3Writer.NAMESPACE, serializer);
            }
            if (body.getLinks() != null && body.getLinks().size() > 0) {
                serializer.startTag(DecorationXpp3Writer.NAMESPACE, "links");
                for (final LinkItem o : body.getLinks()) {
                    this.writeLinkItem(o, "item", serializer);
                }
                serializer.endTag(DecorationXpp3Writer.NAMESPACE, "links");
            }
            if (body.getBreadcrumbs() != null && body.getBreadcrumbs().size() > 0) {
                serializer.startTag(DecorationXpp3Writer.NAMESPACE, "breadcrumbs");
                for (final LinkItem o : body.getBreadcrumbs()) {
                    this.writeLinkItem(o, "item", serializer);
                }
                serializer.endTag(DecorationXpp3Writer.NAMESPACE, "breadcrumbs");
            }
            if (body.getMenus() != null && body.getMenus().size() > 0) {
                for (final Menu o2 : body.getMenus()) {
                    this.writeMenu(o2, "menu", serializer);
                }
            }
            serializer.endTag(DecorationXpp3Writer.NAMESPACE, tagName);
        }
    }
    
    private void writeDecorationModel(final DecorationModel decorationModel, final String tagName, final XmlSerializer serializer) throws IOException {
        if (decorationModel != null) {
            serializer.setPrefix("", "http://maven.apache.org/DECORATION/1.0.0");
            serializer.setPrefix("xsi", "http://www.w3.org/2001/XMLSchema-instance");
            serializer.startTag(DecorationXpp3Writer.NAMESPACE, tagName);
            serializer.attribute("", "xsi:schemaLocation", "http://maven.apache.org/DECORATION/1.0.0 http://maven.apache.org/xsd/decoration-1.0.0.xsd");
            if (decorationModel.getName() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "name", decorationModel.getName());
            }
            if (decorationModel.getBannerLeft() != null) {
                this.writeBanner(decorationModel.getBannerLeft(), "bannerLeft", serializer);
            }
            if (decorationModel.getBannerRight() != null) {
                this.writeBanner(decorationModel.getBannerRight(), "bannerRight", serializer);
            }
            if (decorationModel.getPublishDate() != null) {
                this.writePublishDate(decorationModel.getPublishDate(), "publishDate", serializer);
            }
            if (decorationModel.getVersion() != null) {
                this.writeVersion(decorationModel.getVersion(), "version", serializer);
            }
            if (decorationModel.getPoweredBy() != null && decorationModel.getPoweredBy().size() > 0) {
                serializer.startTag(DecorationXpp3Writer.NAMESPACE, "poweredBy");
                for (final Logo o : decorationModel.getPoweredBy()) {
                    this.writeLogo(o, "logo", serializer);
                }
                serializer.endTag(DecorationXpp3Writer.NAMESPACE, "poweredBy");
            }
            if (decorationModel.getSkin() != null) {
                this.writeSkin(decorationModel.getSkin(), "skin", serializer);
            }
            if (decorationModel.getBody() != null) {
                this.writeBody(decorationModel.getBody(), "body", serializer);
            }
            if (decorationModel.getCustom() != null) {
                ((Xpp3Dom)decorationModel.getCustom()).writeToSerializer(DecorationXpp3Writer.NAMESPACE, serializer);
            }
            serializer.endTag(DecorationXpp3Writer.NAMESPACE, tagName);
        }
    }
    
    private void writeLinkItem(final LinkItem linkItem, final String tagName, final XmlSerializer serializer) throws IOException {
        if (linkItem != null) {
            serializer.startTag(DecorationXpp3Writer.NAMESPACE, tagName);
            if (linkItem.getName() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "name", linkItem.getName());
            }
            if (linkItem.getHref() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "href", linkItem.getHref());
            }
            serializer.endTag(DecorationXpp3Writer.NAMESPACE, tagName);
        }
    }
    
    private void writeLogo(final Logo logo, final String tagName, final XmlSerializer serializer) throws IOException {
        if (logo != null) {
            serializer.startTag(DecorationXpp3Writer.NAMESPACE, tagName);
            if (logo.getImg() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "img", logo.getImg());
            }
            if (logo.getName() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "name", logo.getName());
            }
            if (logo.getHref() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "href", logo.getHref());
            }
            serializer.endTag(DecorationXpp3Writer.NAMESPACE, tagName);
        }
    }
    
    private void writeMenu(final Menu menu, final String tagName, final XmlSerializer serializer) throws IOException {
        if (menu != null) {
            serializer.startTag(DecorationXpp3Writer.NAMESPACE, tagName);
            if (menu.getName() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "name", menu.getName());
            }
            if (menu.getInherit() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "inherit", menu.getInherit());
            }
            if (menu.isInheritAsRef()) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "inheritAsRef", String.valueOf(menu.isInheritAsRef()));
            }
            if (menu.getRef() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "ref", menu.getRef());
            }
            if (menu.getImg() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "img", menu.getImg());
            }
            if (menu.getItems() != null && menu.getItems().size() > 0) {
                for (final MenuItem o : menu.getItems()) {
                    this.writeMenuItem(o, "item", serializer);
                }
            }
            serializer.endTag(DecorationXpp3Writer.NAMESPACE, tagName);
        }
    }
    
    private void writeMenuItem(final MenuItem menuItem, final String tagName, final XmlSerializer serializer) throws IOException {
        if (menuItem != null) {
            serializer.startTag(DecorationXpp3Writer.NAMESPACE, tagName);
            if (menuItem.isCollapse()) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "collapse", String.valueOf(menuItem.isCollapse()));
            }
            if (menuItem.getRef() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "ref", menuItem.getRef());
            }
            if (menuItem.getName() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "name", menuItem.getName());
            }
            if (menuItem.getHref() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "href", menuItem.getHref());
            }
            if (menuItem.getDescription() != null) {
                serializer.startTag(DecorationXpp3Writer.NAMESPACE, "description").text(menuItem.getDescription()).endTag(DecorationXpp3Writer.NAMESPACE, "description");
            }
            if (menuItem.getItems() != null && menuItem.getItems().size() > 0) {
                for (final MenuItem o : menuItem.getItems()) {
                    this.writeMenuItem(o, "item", serializer);
                }
            }
            serializer.endTag(DecorationXpp3Writer.NAMESPACE, tagName);
        }
    }
    
    private void writePublishDate(final PublishDate publishDate, final String tagName, final XmlSerializer serializer) throws IOException {
        if (publishDate != null) {
            serializer.startTag(DecorationXpp3Writer.NAMESPACE, tagName);
            if (publishDate.getPosition() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "position", publishDate.getPosition());
            }
            if (publishDate.getFormat() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "format", publishDate.getFormat());
            }
            serializer.endTag(DecorationXpp3Writer.NAMESPACE, tagName);
        }
    }
    
    private void writeSkin(final Skin skin, final String tagName, final XmlSerializer serializer) throws IOException {
        if (skin != null) {
            serializer.startTag(DecorationXpp3Writer.NAMESPACE, tagName);
            if (skin.getGroupId() != null) {
                serializer.startTag(DecorationXpp3Writer.NAMESPACE, "groupId").text(skin.getGroupId()).endTag(DecorationXpp3Writer.NAMESPACE, "groupId");
            }
            if (skin.getArtifactId() != null) {
                serializer.startTag(DecorationXpp3Writer.NAMESPACE, "artifactId").text(skin.getArtifactId()).endTag(DecorationXpp3Writer.NAMESPACE, "artifactId");
            }
            if (skin.getVersion() != null) {
                serializer.startTag(DecorationXpp3Writer.NAMESPACE, "version").text(skin.getVersion()).endTag(DecorationXpp3Writer.NAMESPACE, "version");
            }
            serializer.endTag(DecorationXpp3Writer.NAMESPACE, tagName);
        }
    }
    
    private void writeVersion(final Version version, final String tagName, final XmlSerializer serializer) throws IOException {
        if (version != null) {
            serializer.startTag(DecorationXpp3Writer.NAMESPACE, tagName);
            if (version.getPosition() != null) {
                serializer.attribute(DecorationXpp3Writer.NAMESPACE, "position", version.getPosition());
            }
            serializer.endTag(DecorationXpp3Writer.NAMESPACE, tagName);
        }
    }
    
    static {
        NAMESPACE = null;
    }
}
