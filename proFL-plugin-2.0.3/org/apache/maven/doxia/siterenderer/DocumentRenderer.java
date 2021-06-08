// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.siterenderer;

import org.apache.maven.doxia.module.xhtml.decoration.render.RenderingContext;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.Writer;

public interface DocumentRenderer
{
    void renderDocument(final Writer p0, final Renderer p1, final SiteRenderingContext p2) throws RendererException, FileNotFoundException, UnsupportedEncodingException;
    
    String getOutputName();
    
    RenderingContext getRenderingContext();
    
    boolean isOverwrite();
}
