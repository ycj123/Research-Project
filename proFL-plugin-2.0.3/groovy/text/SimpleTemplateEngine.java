// 
// Decompiled by Procyon v0.5.36
// 

package groovy.text;

import java.io.BufferedReader;
import java.io.StringWriter;
import java.io.PrintWriter;
import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.Binding;
import java.io.Writer;
import java.util.Map;
import groovy.lang.Writable;
import groovy.lang.Script;
import java.io.IOException;
import org.codehaus.groovy.control.CompilationFailedException;
import groovy.lang.GroovyRuntimeException;
import java.io.Reader;
import groovy.lang.GroovyShell;

public class SimpleTemplateEngine extends TemplateEngine
{
    private boolean verbose;
    private static int counter;
    private GroovyShell groovyShell;
    
    public SimpleTemplateEngine() {
        this(GroovyShell.class.getClassLoader());
    }
    
    public SimpleTemplateEngine(final boolean verbose) {
        this(GroovyShell.class.getClassLoader());
        this.setVerbose(verbose);
    }
    
    public SimpleTemplateEngine(final ClassLoader parentLoader) {
        this(new GroovyShell(parentLoader));
    }
    
    public SimpleTemplateEngine(final GroovyShell groovyShell) {
        this.groovyShell = groovyShell;
    }
    
    @Override
    public Template createTemplate(final Reader reader) throws CompilationFailedException, IOException {
        final SimpleTemplate template = new SimpleTemplate();
        final String script = template.parse(reader);
        if (this.verbose) {
            System.out.println("\n-- script source --");
            System.out.print(script);
            System.out.println("\n-- script end --\n");
        }
        try {
            template.script = this.groovyShell.parse(script, "SimpleTemplateScript" + SimpleTemplateEngine.counter++ + ".groovy");
        }
        catch (Exception e) {
            throw new GroovyRuntimeException("Failed to parse template script (your template may contain an error or be trying to use expressions not currently supported): " + e.getMessage());
        }
        return template;
    }
    
    public void setVerbose(final boolean verbose) {
        this.verbose = verbose;
    }
    
    public boolean isVerbose() {
        return this.verbose;
    }
    
    static {
        SimpleTemplateEngine.counter = 1;
    }
    
    private static class SimpleTemplate implements Template
    {
        protected Script script;
        
        public Writable make() {
            return this.make(null);
        }
        
        public Writable make(final Map map) {
            return new Writable() {
                public Writer writeTo(final Writer writer) {
                    Binding binding;
                    if (map == null) {
                        binding = new Binding();
                    }
                    else {
                        binding = new Binding(map);
                    }
                    final Script scriptObject = InvokerHelper.createScript(SimpleTemplate.this.script.getClass(), binding);
                    final PrintWriter pw = new PrintWriter(writer);
                    scriptObject.setProperty("out", pw);
                    scriptObject.run();
                    pw.flush();
                    return writer;
                }
                
                @Override
                public String toString() {
                    final StringWriter sw = new StringWriter();
                    this.writeTo(sw);
                    return sw.toString();
                }
            };
        }
        
        protected String parse(Reader reader) throws IOException {
            if (!reader.markSupported()) {
                reader = new BufferedReader(reader);
            }
            final StringWriter sw = new StringWriter();
            this.startScript(sw);
            int c;
            while ((c = reader.read()) != -1) {
                if (c == 60) {
                    reader.mark(1);
                    c = reader.read();
                    if (c != 37) {
                        sw.write(60);
                        reader.reset();
                    }
                    else {
                        reader.mark(1);
                        c = reader.read();
                        if (c == 61) {
                            this.groovyExpression(reader, sw);
                        }
                        else {
                            reader.reset();
                            this.groovySection(reader, sw);
                        }
                    }
                }
                else if (c == 36) {
                    reader.mark(1);
                    c = reader.read();
                    if (c != 123) {
                        sw.write(36);
                        reader.reset();
                    }
                    else {
                        reader.mark(1);
                        sw.write("${");
                        this.processGSstring(reader, sw);
                    }
                }
                else {
                    if (c == 34) {
                        sw.write(92);
                    }
                    if (c == 10 || c == 13) {
                        if (c == 13) {
                            reader.mark(1);
                            c = reader.read();
                            if (c != 10) {
                                reader.reset();
                            }
                        }
                        sw.write("\n");
                    }
                    else {
                        sw.write(c);
                    }
                }
            }
            this.endScript(sw);
            return sw.toString();
        }
        
        private void startScript(final StringWriter sw) {
            sw.write("/* Generated by SimpleTemplateEngine */\n");
            sw.write("out.print(\"\"\"");
        }
        
        private void endScript(final StringWriter sw) {
            sw.write("\"\"\");\n");
        }
        
        private void processGSstring(final Reader reader, final StringWriter sw) throws IOException {
            int c;
            while ((c = reader.read()) != -1) {
                if (c != 10 && c != 13) {
                    sw.write(c);
                }
                if (c == 125) {
                    break;
                }
            }
        }
        
        private void groovyExpression(final Reader reader, final StringWriter sw) throws IOException {
            sw.write("${");
            int c;
            while ((c = reader.read()) != -1) {
                if (c == 37) {
                    c = reader.read();
                    if (c == 62) {
                        break;
                    }
                    sw.write(37);
                }
                if (c != 10 && c != 13) {
                    sw.write(c);
                }
            }
            sw.write("}");
        }
        
        private void groovySection(final Reader reader, final StringWriter sw) throws IOException {
            sw.write("\"\"\");");
            int c;
            while ((c = reader.read()) != -1) {
                if (c == 37) {
                    c = reader.read();
                    if (c == 62) {
                        break;
                    }
                    sw.write(37);
                }
                sw.write(c);
            }
            sw.write(";\nout.print(\"\"\"");
        }
    }
}
