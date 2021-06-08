// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.siterenderer;

import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.Writer;
import org.apache.maven.doxia.module.xhtml.decoration.render.RenderingContext;

public class DoxiaDocumentRenderer implements DocumentRenderer
{
    private RenderingContext renderingContext;
    
    public DoxiaDocumentRenderer(final RenderingContext renderingContext) {
        this.renderingContext = renderingContext;
    }
    
    public void renderDocument(final Writer writer, final Renderer renderer, final SiteRenderingContext siteRenderingContext) throws RendererException, FileNotFoundException, UnsupportedEncodingException {
        renderer.renderDocument(writer, this.renderingContext, siteRenderingContext);
    }
    
    public String getOutputName() {
        return this.renderingContext.getOutputName();
    }
    
    public RenderingContext getRenderingContext() {
        return this.renderingContext;
    }
    
    public boolean isOverwrite() {
        return false;
    }
}
