// 
// Decompiled by Procyon v0.5.36
// 

package groovy.text;

import java.util.Map;
import groovy.lang.Writable;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.GroovyCodeSource;
import java.security.AccessController;
import java.security.PrivilegedAction;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Closure;
import java.io.IOException;
import org.codehaus.groovy.control.CompilationFailedException;
import java.io.Reader;

public class GStringTemplateEngine extends TemplateEngine
{
    private final ClassLoader parentLoader;
    private static int counter;
    
    public GStringTemplateEngine() {
        this(GStringTemplate.class.getClassLoader());
    }
    
    public GStringTemplateEngine(final ClassLoader parentLoader) {
        this.parentLoader = parentLoader;
    }
    
    @Override
    public Template createTemplate(final Reader reader) throws CompilationFailedException, ClassNotFoundException, IOException {
        return new GStringTemplate(reader, this.parentLoader);
    }
    
    static {
        GStringTemplateEngine.counter = 1;
    }
    
    private static class GStringTemplate implements Template
    {
        final Closure template;
        
        GStringTemplate(final Reader reader, final ClassLoader parentLoader) throws CompilationFailedException, ClassNotFoundException, IOException {
            final StringBuilder templateExpressions = new StringBuilder("package groovy.tmp.templates\n def getTemplate() { return { out -> delegate = new Binding(delegate); out << \"\"\"");
            boolean writingString = true;
            while (true) {
                int c = reader.read();
                if (c == -1) {
                    break;
                }
                if (c == 60) {
                    c = reader.read();
                    if (c == 37) {
                        c = reader.read();
                        if (c == 61) {
                            parseExpression(reader, writingString, templateExpressions);
                            writingString = true;
                            continue;
                        }
                        parseSection(c, reader, writingString, templateExpressions);
                        writingString = false;
                        continue;
                    }
                    else {
                        appendCharacter('<', templateExpressions, writingString);
                        writingString = true;
                    }
                }
                else if (c == 34) {
                    appendCharacter('\\', templateExpressions, writingString);
                    writingString = true;
                }
                else if (c == 36) {
                    appendCharacter('$', templateExpressions, writingString);
                    writingString = true;
                    c = reader.read();
                    if (c == 123) {
                        appendCharacter('{', templateExpressions, writingString);
                        writingString = true;
                        this.parseGSstring(reader, writingString, templateExpressions);
                        writingString = true;
                        continue;
                    }
                }
                appendCharacter((char)c, templateExpressions, writingString);
                writingString = true;
            }
            if (writingString) {
                templateExpressions.append("\"\"\"");
            }
            templateExpressions.append("}.asWritable()}");
            final GroovyClassLoader loader = AccessController.doPrivileged((PrivilegedAction<GroovyClassLoader>)new PrivilegedAction() {
                public Object run() {
                    return new GroovyClassLoader(parentLoader);
                }
            });
            Class groovyClass;
            try {
                groovyClass = loader.parseClass(new GroovyCodeSource(templateExpressions.toString(), "GStringTemplateScript" + GStringTemplateEngine.counter++ + ".groovy", "x"));
            }
            catch (Exception e) {
                throw new GroovyRuntimeException("Failed to parse template script (your template may contain an error or be trying to use expressions not currently supported): " + e.getMessage());
            }
            try {
                final GroovyObject object = groovyClass.newInstance();
                this.template = (Closure)object.invokeMethod("getTemplate", null);
            }
            catch (InstantiationException e2) {
                throw new ClassNotFoundException(e2.getMessage());
            }
            catch (IllegalAccessException e3) {
                throw new ClassNotFoundException(e3.getMessage());
            }
        }
        
        private static void appendCharacter(final char c, final StringBuilder templateExpressions, final boolean writingString) {
            if (!writingString) {
                templateExpressions.append("out << \"\"\"");
            }
            templateExpressions.append(c);
        }
        
        private void parseGSstring(final Reader reader, final boolean writingString, final StringBuilder templateExpressions) throws IOException {
            if (!writingString) {
                templateExpressions.append("\"\"\"; ");
            }
            int c;
            do {
                c = reader.read();
                if (c == -1) {
                    break;
                }
                templateExpressions.append((char)c);
            } while (c != 125);
        }
        
        private static void parseSection(final int pendingC, final Reader reader, final boolean writingString, final StringBuilder templateExpressions) throws IOException {
            if (writingString) {
                templateExpressions.append("\"\"\"; ");
            }
            templateExpressions.append((char)pendingC);
            while (true) {
                int c = reader.read();
                if (c == -1) {
                    break;
                }
                if (c == 37) {
                    c = reader.read();
                    if (c == 62) {
                        break;
                    }
                    templateExpressions.append('%');
                }
                templateExpressions.append((char)c);
            }
            templateExpressions.append(";\n ");
        }
        
        private static void parseExpression(final Reader reader, final boolean writingString, final StringBuilder templateExpressions) throws IOException {
            if (!writingString) {
                templateExpressions.append("out << \"\"\"");
            }
            templateExpressions.append("${");
            while (true) {
                int c = reader.read();
                if (c == -1) {
                    break;
                }
                if (c == 37) {
                    c = reader.read();
                    if (c == 62) {
                        break;
                    }
                    templateExpressions.append('%');
                }
                templateExpressions.append((char)c);
            }
            templateExpressions.append('}');
        }
        
        public Writable make() {
            return this.make(null);
        }
        
        public Writable make(final Map map) {
            final Closure template = (Closure)this.template.clone();
            template.setDelegate(map);
            return (Writable)template;
        }
    }
}
