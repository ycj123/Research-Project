// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.reporting.sink;

import org.apache.maven.doxia.module.xhtml.decoration.render.RenderingContext;
import org.apache.maven.doxia.siterenderer.sink.SiteRendererSink;
import java.io.IOException;
import org.apache.maven.doxia.siterenderer.RendererException;
import java.io.File;
import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.doxia.siterenderer.Renderer;

public class SinkFactory
{
    private String siteDirectory;
    private Renderer siteRenderer;
    
    public void setSiteRenderer(final Renderer siteRenderer) {
        this.siteRenderer = siteRenderer;
    }
    
    public void setSiteDirectory(final String siteDirectory) {
        this.siteDirectory = siteDirectory;
    }
    
    public Sink getSink(final String outputFileName) throws RendererException, IOException {
        return createSink(new File(this.siteDirectory), outputFileName);
    }
    
    public static SiteRendererSink createSink(final File basedir, final String document) {
        return new SiteRendererSink(new RenderingContext(basedir, document));
    }
}
