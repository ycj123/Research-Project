// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource.loader;

import org.apache.velocity.runtime.resource.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
import org.apache.velocity.io.UnicodeInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.velocity.exception.ResourceNotFoundException;
import java.io.InputStream;
import org.apache.velocity.util.StringUtils;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class FileResourceLoader extends ResourceLoader
{
    private List paths;
    private Map templatePaths;
    private boolean unicode;
    
    public FileResourceLoader() {
        this.paths = new ArrayList();
        this.templatePaths = Collections.synchronizedMap(new HashMap<Object, Object>());
        this.unicode = false;
    }
    
    public void init(final ExtendedProperties configuration) {
        if (this.log.isTraceEnabled()) {
            this.log.trace("FileResourceLoader : initialization starting.");
        }
        this.paths.addAll(configuration.getVector("path"));
        this.unicode = configuration.getBoolean("unicode", false);
        if (this.log.isDebugEnabled()) {
            this.log.debug("Do unicode file recognition:  " + this.unicode);
        }
        StringUtils.trimStrings(this.paths);
        if (this.log.isInfoEnabled()) {
            for (int sz = this.paths.size(), i = 0; i < sz; ++i) {
                this.log.info("FileResourceLoader : adding path '" + this.paths.get(i) + "'");
            }
            this.log.trace("FileResourceLoader : initialization complete.");
        }
    }
    
    public InputStream getResourceStream(final String templateName) throws ResourceNotFoundException {
        if (org.mudebug.prapr.reloc.commons.lang.StringUtils.isEmpty(templateName)) {
            throw new ResourceNotFoundException("Need to specify a file name or file path!");
        }
        final String template = StringUtils.normalizePath(templateName);
        if (template == null || template.length() == 0) {
            final String msg = "File resource error : argument " + template + " contains .. and may be trying to access " + "content outside of template root.  Rejected.";
            this.log.error("FileResourceLoader : " + msg);
            throw new ResourceNotFoundException(msg);
        }
        for (int size = this.paths.size(), i = 0; i < size; ++i) {
            final String path = this.paths.get(i);
            InputStream inputStream = null;
            try {
                inputStream = this.findTemplate(path, template);
            }
            catch (IOException ioe) {
                this.log.error("While loading Template " + template + ": ", ioe);
            }
            if (inputStream != null) {
                this.templatePaths.put(templateName, path);
                return inputStream;
            }
        }
        throw new ResourceNotFoundException("FileResourceLoader : cannot find " + template);
    }
    
    private InputStream findTemplate(final String path, final String template) throws IOException {
        try {
            final File file = this.getFile(path, template);
            if (file.canRead()) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file.getAbsolutePath());
                    if (this.unicode) {
                        UnicodeInputStream uis = null;
                        try {
                            uis = new UnicodeInputStream(fis, true);
                            if (this.log.isDebugEnabled()) {
                                this.log.debug("File Encoding for " + file + " is: " + uis.getEncodingFromStream());
                            }
                            return new BufferedInputStream(uis);
                        }
                        catch (IOException e) {
                            this.closeQuiet(uis);
                            throw e;
                        }
                    }
                    return new BufferedInputStream(fis);
                }
                catch (IOException e2) {
                    this.closeQuiet(fis);
                    throw e2;
                }
            }
            return null;
        }
        catch (FileNotFoundException fnfe) {
            return null;
        }
    }
    
    private void closeQuiet(final InputStream is) {
        if (is != null) {
            try {
                is.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public boolean isSourceModified(final Resource resource) {
        boolean modified = true;
        final String fileName = resource.getName();
        final String path = this.templatePaths.get(fileName);
        File currentFile = null;
        for (int i = 0; currentFile == null && i < this.paths.size(); ++i) {
            final String testPath = this.paths.get(i);
            final File testFile = this.getFile(testPath, fileName);
            if (testFile.canRead()) {
                currentFile = testFile;
            }
        }
        final File file = this.getFile(path, fileName);
        if (currentFile != null) {
            if (file.exists()) {
                if (currentFile.equals(file) && file.canRead()) {
                    modified = (file.lastModified() != resource.getLastModified());
                }
            }
        }
        return modified;
    }
    
    public long getLastModified(final Resource resource) {
        final String path = this.templatePaths.get(resource.getName());
        final File file = this.getFile(path, resource.getName());
        if (file.canRead()) {
            return file.lastModified();
        }
        return 0L;
    }
    
    private File getFile(final String path, String template) {
        File file = null;
        if ("".equals(path)) {
            file = new File(template);
        }
        else {
            if (template.startsWith("/")) {
                template = template.substring(1);
            }
            file = new File(path, template);
        }
        return file;
    }
}
