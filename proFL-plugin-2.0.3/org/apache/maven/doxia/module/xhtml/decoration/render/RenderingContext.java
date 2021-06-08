// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.xhtml.decoration.render;

import java.util.HashMap;
import org.codehaus.plexus.util.PathTool;
import org.codehaus.plexus.util.StringUtils;
import java.util.Map;
import java.io.File;

public class RenderingContext
{
    private final File basedir;
    private final String inputName;
    private final String outputName;
    private final String parserId;
    private final String relativePath;
    private final String extension;
    private Map attributes;
    
    public RenderingContext(final File basedir, final String document) {
        this(basedir, document, null);
    }
    
    public RenderingContext(final File basedir, final String document, final String parserId) {
        this(basedir, document, null, null);
    }
    
    public RenderingContext(final File basedir, final String document, final String parserId, final String extension) {
        this.basedir = basedir;
        this.extension = extension;
        if (StringUtils.isNotEmpty(extension)) {
            final int startIndexOfExtension = document.indexOf("." + extension);
            final String fileNameWithoutExt = document.substring(0, startIndexOfExtension);
            this.outputName = fileNameWithoutExt + ".html";
        }
        else {
            this.outputName = document.substring(0, document.indexOf(".")).replace('\\', '/') + ".html";
        }
        this.relativePath = PathTool.getRelativePath(basedir.getPath(), new File(basedir, document).getPath());
        this.inputName = document;
        this.parserId = parserId;
        this.attributes = new HashMap();
    }
    
    public File getBasedir() {
        return this.basedir;
    }
    
    public String getInputName() {
        return this.inputName;
    }
    
    public String getOutputName() {
        return this.outputName;
    }
    
    public String getParserId() {
        return this.parserId;
    }
    
    public String getRelativePath() {
        return this.relativePath;
    }
    
    public void setAttribute(final String key, final String value) {
        this.attributes.put(key, value);
    }
    
    public String getAttribute(final String key) {
        return this.attributes.get(key);
    }
    
    public String getExtension() {
        return this.extension;
    }
}
