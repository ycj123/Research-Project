// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.siterenderer;

import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import org.apache.maven.doxia.module.xhtml.decoration.render.RenderingContext;
import java.net.MalformedURLException;
import java.util.Locale;
import org.apache.maven.doxia.site.decoration.DecorationModel;
import java.util.Map;
import org.apache.maven.doxia.siterenderer.sink.SiteRendererSink;
import java.io.Writer;
import java.io.IOException;
import java.io.File;
import java.util.Collection;

public interface Renderer
{
    public static final String ROLE = ((Renderer$1.class$org$apache$maven$doxia$siterenderer$Renderer == null) ? (Renderer$1.class$org$apache$maven$doxia$siterenderer$Renderer = Renderer$1.class$("org.apache.maven.doxia.siterenderer.Renderer")) : Renderer$1.class$org$apache$maven$doxia$siterenderer$Renderer).getName();
    
    void render(final Collection p0, final SiteRenderingContext p1, final File p2) throws RendererException, IOException;
    
    void generateDocument(final Writer p0, final SiteRendererSink p1, final SiteRenderingContext p2) throws RendererException;
    
    SiteRenderingContext createContextForSkin(final File p0, final Map p1, final DecorationModel p2, final String p3, final Locale p4) throws IOException;
    
    SiteRenderingContext createContextForTemplate(final File p0, final File p1, final Map p2, final DecorationModel p3, final String p4, final Locale p5) throws MalformedURLException;
    
    void copyResources(final SiteRenderingContext p0, final File p1, final File p2) throws IOException;
    
    Map locateDocumentFiles(final SiteRenderingContext p0) throws IOException, RendererException;
    
    void renderDocument(final Writer p0, final RenderingContext p1, final SiteRenderingContext p2) throws RendererException, FileNotFoundException, UnsupportedEncodingException;
}
