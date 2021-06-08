// 
// Decompiled by Procyon v0.5.36
// 

package groovy.text;

import java.io.InputStreamReader;
import java.net.URL;
import java.io.Closeable;
import org.codehaus.groovy.runtime.DefaultGroovyMethodsSupport;
import java.io.FileReader;
import java.io.File;
import java.io.StringReader;
import java.io.IOException;
import org.codehaus.groovy.control.CompilationFailedException;
import java.io.Reader;

public abstract class TemplateEngine
{
    public abstract Template createTemplate(final Reader p0) throws CompilationFailedException, ClassNotFoundException, IOException;
    
    public Template createTemplate(final String templateText) throws CompilationFailedException, ClassNotFoundException, IOException {
        return this.createTemplate(new StringReader(templateText));
    }
    
    public Template createTemplate(final File file) throws CompilationFailedException, ClassNotFoundException, IOException {
        final Reader reader = new FileReader(file);
        try {
            return this.createTemplate(reader);
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(reader);
        }
    }
    
    public Template createTemplate(final URL url) throws CompilationFailedException, ClassNotFoundException, IOException {
        final Reader reader = new InputStreamReader(url.openStream());
        try {
            return this.createTemplate(reader);
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(reader);
        }
    }
}
