// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.net.MalformedURLException;
import java.security.Permission;
import groovy.security.GroovyCodeSourcePermission;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.io.ByteArrayInputStream;
import groovy.util.CharsetToolkit;
import java.security.PrivilegedActionException;
import java.security.AccessController;
import java.net.URL;
import java.security.PrivilegedExceptionAction;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.io.Reader;
import java.io.File;
import java.security.cert.Certificate;
import java.security.CodeSource;

public class GroovyCodeSource
{
    private CodeSource codeSource;
    private String name;
    private String scriptText;
    Certificate[] certs;
    private boolean cachable;
    private File file;
    
    public GroovyCodeSource(final String script, final String name, final String codeBase) {
        this.name = name;
        this.scriptText = script;
        this.codeSource = createCodeSource(codeBase);
        this.cachable = true;
    }
    
    public GroovyCodeSource(final Reader reader, final String name, final String codeBase) {
        this.name = name;
        this.codeSource = createCodeSource(codeBase);
        try {
            this.scriptText = DefaultGroovyMethods.getText(reader);
        }
        catch (IOException e) {
            throw new RuntimeException("Impossible to read the text content from that reader, for script: " + name + " with codeBase: " + codeBase, e);
        }
    }
    
    @Deprecated
    public GroovyCodeSource(final InputStream inputStream, final String name, final String codeBase) {
        this.name = name;
        this.codeSource = createCodeSource(codeBase);
        try {
            this.scriptText = DefaultGroovyMethods.getText(inputStream);
        }
        catch (IOException e) {
            throw new RuntimeException("Impossible to read the text content from that input stream, for script: " + name + " with codeBase: " + codeBase, e);
        }
    }
    
    public GroovyCodeSource(final File infile, final String encoding) throws IOException {
        final File file = new File(infile.getCanonicalPath());
        if (!file.exists()) {
            throw new FileNotFoundException(file.toString() + " (" + file.getAbsolutePath() + ")");
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException(file.toString() + " (" + file.getAbsolutePath() + ") is a directory not a Groovy source file.");
        }
        try {
            if (!file.canRead()) {
                throw new RuntimeException(file.toString() + " can not be read. Check the read permission of the file \"" + file.toString() + "\" (" + file.getAbsolutePath() + ").");
            }
        }
        catch (SecurityException e) {
            throw e;
        }
        this.file = file;
        this.cachable = true;
        try {
            final Object[] info = AccessController.doPrivileged((PrivilegedExceptionAction<Object[]>)new PrivilegedExceptionAction<Object[]>() {
                public Object[] run() throws IOException {
                    if (encoding != null) {
                        GroovyCodeSource.this.scriptText = DefaultGroovyMethods.getText(infile, encoding);
                    }
                    else {
                        GroovyCodeSource.this.scriptText = DefaultGroovyMethods.getText(infile);
                    }
                    final Object[] info = new Object[2];
                    final URL url = file.toURI().toURL();
                    info[0] = url.toExternalForm();
                    info[1] = new CodeSource(url, (Certificate[])null);
                    return info;
                }
            });
            this.name = (String)info[0];
            this.codeSource = (CodeSource)info[1];
        }
        catch (PrivilegedActionException pae) {
            final Throwable cause = pae.getCause();
            if (cause != null && cause instanceof IOException) {
                throw (IOException)cause;
            }
            throw new RuntimeException("Could not construct CodeSource for file: " + file, cause);
        }
    }
    
    public GroovyCodeSource(final File infile) throws IOException {
        this(infile, CharsetToolkit.getDefaultSystemCharset().name());
    }
    
    public GroovyCodeSource(final URL url) throws IOException {
        if (url == null) {
            throw new RuntimeException("Could not construct a GroovyCodeSource from a null URL");
        }
        this.name = url.toExternalForm();
        this.codeSource = new CodeSource(url, (Certificate[])null);
        try {
            final String contentEncoding = url.openConnection().getContentEncoding();
            if (contentEncoding != null) {
                this.scriptText = DefaultGroovyMethods.getText(url, contentEncoding);
            }
            else {
                this.scriptText = DefaultGroovyMethods.getText(url);
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Impossible to read the text content from " + this.name, e);
        }
    }
    
    CodeSource getCodeSource() {
        return this.codeSource;
    }
    
    @Deprecated
    public InputStream getInputStream() {
        IOException ioe = null;
        Label_0045: {
            if (this.file == null) {
                try {
                    return new ByteArrayInputStream(this.scriptText.getBytes("UTF-8"));
                }
                catch (UnsupportedEncodingException e) {
                    ioe = e;
                    break Label_0045;
                }
            }
            try {
                return new FileInputStream(this.file);
            }
            catch (FileNotFoundException e2) {
                ioe = e2;
            }
        }
        final String errorMsg = "Impossible to read the bytes from the associated script: " + this.scriptText + " with name: " + this.name;
        if (ioe != null) {
            throw new RuntimeException(errorMsg, ioe);
        }
        throw new RuntimeException(errorMsg);
    }
    
    public String getScriptText() {
        return this.scriptText;
    }
    
    public String getName() {
        return this.name;
    }
    
    public File getFile() {
        return this.file;
    }
    
    public void setCachable(final boolean b) {
        this.cachable = b;
    }
    
    public boolean isCachable() {
        return this.cachable;
    }
    
    private static CodeSource createCodeSource(final String codeBase) {
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new GroovyCodeSourcePermission(codeBase));
        }
        try {
            return new CodeSource(new URL("file", "", codeBase), (Certificate[])null);
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("A CodeSource file URL cannot be constructed from the supplied codeBase: " + codeBase);
        }
    }
}
