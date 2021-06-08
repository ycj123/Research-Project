// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.site.decoration.inheritance;

import java.net.MalformedURLException;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.doxia.site.decoration.MenuItem;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.doxia.site.decoration.Body;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.apache.maven.doxia.site.decoration.Banner;
import java.util.Iterator;
import org.apache.maven.doxia.site.decoration.Menu;
import org.apache.maven.doxia.site.decoration.LinkItem;
import org.apache.maven.doxia.site.decoration.Logo;
import org.apache.maven.doxia.site.decoration.DecorationModel;

public class DefaultDecorationModelInheritanceAssembler implements DecorationModelInheritanceAssembler
{
    public void assembleModelInheritance(final String name, final DecorationModel child, final DecorationModel parent, final String childBaseUrl, final String parentBaseUrl) {
        final URLContainer urlContainer = new URLContainer(parentBaseUrl, childBaseUrl);
        if (parent != null) {
            if (child.getBannerLeft() == null) {
                child.setBannerLeft(parent.getBannerLeft());
                this.resolveBannerPaths(child.getBannerLeft(), urlContainer);
            }
            if (child.getBannerRight() == null) {
                child.setBannerRight(parent.getBannerRight());
                this.resolveBannerPaths(child.getBannerRight(), urlContainer);
            }
            if (child.getPublishDate() == null) {
                child.setPublishDate(parent.getPublishDate());
            }
            if (child.getVersion() == null) {
                child.setVersion(parent.getVersion());
            }
            if (child.getSkin() == null) {
                child.setSkin(parent.getSkin());
            }
            child.setPoweredBy(this.mergePoweredByLists(child.getPoweredBy(), parent.getPoweredBy(), urlContainer));
            this.assembleBodyInheritance(name, child, parent, urlContainer);
            this.assembleCustomInheritance(child, parent);
        }
    }
    
    public void resolvePaths(final DecorationModel decoration, final String baseUrl) {
        final URLContainer urlContainer = new URLContainer(null, baseUrl);
        if (decoration.getBannerLeft() != null) {
            this.resolveBannerPaths(decoration.getBannerLeft(), urlContainer);
        }
        if (decoration.getBannerRight() != null) {
            this.resolveBannerPaths(decoration.getBannerRight(), urlContainer);
        }
        for (final Logo logo : decoration.getPoweredBy()) {
            this.resolveLogoPaths(logo, urlContainer);
        }
        if (decoration.getBody() != null) {
            for (final LinkItem linkItem : decoration.getBody().getLinks()) {
                this.resolveLinkItemPaths(linkItem, urlContainer);
            }
            for (final LinkItem linkItem : decoration.getBody().getBreadcrumbs()) {
                this.resolveLinkItemPaths(linkItem, urlContainer);
            }
            for (final Menu menu : decoration.getBody().getMenus()) {
                this.resolveMenuPaths(menu.getItems(), urlContainer);
            }
        }
    }
    
    private void resolveBannerPaths(final Banner banner, final URLContainer urlContainer) {
        if (banner != null) {
            banner.setHref(this.convertPath(banner.getHref(), urlContainer));
            banner.setSrc(this.convertPath(banner.getSrc(), urlContainer));
        }
    }
    
    private void assembleCustomInheritance(final DecorationModel child, final DecorationModel parent) {
        if (child.getCustom() == null) {
            child.setCustom(parent.getCustom());
        }
        else {
            child.setCustom(Xpp3Dom.mergeXpp3Dom((Xpp3Dom)child.getCustom(), (Xpp3Dom)parent.getCustom()));
        }
    }
    
    private void assembleBodyInheritance(final String name, final DecorationModel child, final DecorationModel parent, final URLContainer urlContainer) {
        Body cBody = child.getBody();
        Body pBody = parent.getBody();
        if (cBody != null || pBody != null) {
            if (cBody == null) {
                cBody = new Body();
                child.setBody(cBody);
            }
            if (pBody == null) {
                pBody = new Body();
            }
            if (cBody.getHead() == null) {
                cBody.setHead(pBody.getHead());
            }
            else {
                cBody.setHead(Xpp3Dom.mergeXpp3Dom((Xpp3Dom)cBody.getHead(), (Xpp3Dom)pBody.getHead()));
            }
            cBody.setLinks(this.mergeLinkItemLists(cBody.getLinks(), pBody.getLinks(), urlContainer));
            if (cBody.getBreadcrumbs().isEmpty() && !pBody.getBreadcrumbs().isEmpty()) {
                final LinkItem breadcrumb = new LinkItem();
                breadcrumb.setName(name);
                breadcrumb.setHref(urlContainer.getNewPath());
                cBody.getBreadcrumbs().add(breadcrumb);
            }
            cBody.setBreadcrumbs(this.mergeLinkItemLists(cBody.getBreadcrumbs(), pBody.getBreadcrumbs(), urlContainer));
            cBody.setMenus(this.mergeMenus(cBody.getMenus(), pBody.getMenus(), urlContainer));
        }
    }
    
    private List mergeMenus(final List childMenus, final List parentMenus, final URLContainer urlContainer) {
        final List menus = new ArrayList();
        for (final Menu menu : childMenus) {
            menus.add(menu);
        }
        int topCounter = 0;
        for (final Menu menu2 : parentMenus) {
            if ("top".equals(menu2.getInherit())) {
                menus.add(topCounter, menu2);
                ++topCounter;
                this.resolveMenuPaths(menu2.getItems(), urlContainer);
            }
            else {
                if (!"bottom".equals(menu2.getInherit())) {
                    continue;
                }
                menus.add(menu2);
                this.resolveMenuPaths(menu2.getItems(), urlContainer);
            }
        }
        return menus;
    }
    
    private void resolveMenuPaths(final List items, final URLContainer urlContainer) {
        for (final MenuItem item : items) {
            this.resolveLinkItemPaths(item, urlContainer);
            this.resolveMenuPaths(item.getItems(), urlContainer);
        }
    }
    
    private void resolveLinkItemPaths(final LinkItem item, final URLContainer urlContainer) {
        if (StringUtils.isNotEmpty(item.getHref())) {
            final String href = this.convertPath(item.getHref(), urlContainer);
            if (StringUtils.isNotEmpty(href)) {
                item.setHref(href);
            }
        }
        else {
            item.setHref(this.convertPath("", urlContainer));
        }
    }
    
    private void resolveLogoPaths(final Logo logo, final URLContainer urlContainer) {
        logo.setImg(this.convertPath(logo.getImg(), urlContainer));
        this.resolveLinkItemPaths(logo, urlContainer);
    }
    
    private List mergeLinkItemLists(final List childList, final List parentList, final URLContainer urlContainer) {
        final List items = new ArrayList();
        for (final LinkItem item : parentList) {
            this.resolveLinkItemPaths(item, urlContainer);
            if (!items.contains(item)) {
                items.add(item);
            }
        }
        for (final LinkItem item : childList) {
            if (!items.contains(item)) {
                items.add(item);
            }
        }
        return items;
    }
    
    private List mergePoweredByLists(final List childList, final List parentList, final URLContainer urlContainer) {
        final List logos = new ArrayList();
        for (final Logo logo : parentList) {
            if (!logos.contains(logo)) {
                logos.add(logo);
            }
            this.resolveLogoPaths(logo, urlContainer);
        }
        for (final Logo logo : childList) {
            if (!logos.contains(logo)) {
                logos.add(logo);
            }
        }
        return logos;
    }
    
    private String convertPath(final String relativePath, final URLContainer urlContainer) {
        try {
            final PathDescriptor oldPathDescriptor = new PathDescriptor(urlContainer.getOldPath(), relativePath);
            final PathDescriptor newPathDescriptor = new PathDescriptor(urlContainer.getNewPath(), "");
            final PathDescriptor relativePathDescriptor = PathUtils.convertPath(oldPathDescriptor, newPathDescriptor);
            return relativePathDescriptor.getLocation();
        }
        catch (MalformedURLException mue) {
            throw new RuntimeException("While converting Pathes:", mue);
        }
    }
    
    public final class URLContainer
    {
        private final String oldPath;
        private final String newPath;
        
        public URLContainer(final String oldPath, final String newPath) {
            this.oldPath = oldPath;
            this.newPath = newPath;
        }
        
        public String getNewPath() {
            return this.newPath;
        }
        
        public String getOldPath() {
            return this.oldPath;
        }
    }
}
