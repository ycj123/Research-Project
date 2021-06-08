// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.jsr223;

import java.util.Collections;
import java.util.ArrayList;
import javax.script.ScriptEngine;
import groovy.lang.GroovySystem;
import java.util.List;
import javax.script.ScriptEngineFactory;

public class GroovyScriptEngineFactory implements ScriptEngineFactory
{
    private static final String VERSION = "2.0";
    private static final String SHORT_NAME = "groovy";
    private static final String LANGUAGE_NAME = "Groovy";
    private static final List<String> NAMES;
    private static final List<String> EXTENSIONS;
    private static final List<String> MIME_TYPES;
    
    public String getEngineName() {
        return "Groovy Scripting Engine";
    }
    
    public String getEngineVersion() {
        return "2.0";
    }
    
    public String getLanguageName() {
        return "Groovy";
    }
    
    public String getLanguageVersion() {
        return GroovySystem.getVersion();
    }
    
    public List<String> getExtensions() {
        return GroovyScriptEngineFactory.EXTENSIONS;
    }
    
    public List<String> getMimeTypes() {
        return GroovyScriptEngineFactory.MIME_TYPES;
    }
    
    public List<String> getNames() {
        return GroovyScriptEngineFactory.NAMES;
    }
    
    public Object getParameter(final String key) {
        if ("javax.script.name".equals(key)) {
            return "groovy";
        }
        if ("javax.script.engine".equals(key)) {
            return this.getEngineName();
        }
        if ("javax.script.engine_version".equals(key)) {
            return "2.0";
        }
        if ("javax.script.language".equals(key)) {
            return "Groovy";
        }
        if ("javax.script.language_version".equals(key)) {
            return GroovySystem.getVersion();
        }
        if ("THREADING".equals(key)) {
            return "MULTITHREADED";
        }
        throw new IllegalArgumentException("Invalid key");
    }
    
    public ScriptEngine getScriptEngine() {
        return new GroovyScriptEngineImpl();
    }
    
    public String getMethodCallSyntax(final String obj, final String method, final String... args) {
        String ret = obj + "." + method + "(";
        final int len = args.length;
        if (len == 0) {
            ret += ")";
            return ret;
        }
        for (int i = 0; i < len; ++i) {
            ret += args[i];
            if (i != len - 1) {
                ret += ",";
            }
            else {
                ret += ")";
            }
        }
        return ret;
    }
    
    public String getOutputStatement(final String toDisplay) {
        final StringBuilder buf = new StringBuilder();
        buf.append("println(\"");
        for (int len = toDisplay.length(), i = 0; i < len; ++i) {
            final char ch = toDisplay.charAt(i);
            switch (ch) {
                case '\"': {
                    buf.append("\\\"");
                    break;
                }
                case '\\': {
                    buf.append("\\\\");
                    break;
                }
                default: {
                    buf.append(ch);
                    break;
                }
            }
        }
        buf.append("\")");
        return buf.toString();
    }
    
    public String getProgram(final String... statements) {
        final StringBuilder ret = new StringBuilder();
        for (int len = statements.length, i = 0; i < len; ++i) {
            ret.append(statements[i]);
            ret.append('\n');
        }
        return ret.toString();
    }
    
    static {
        List<String> n = new ArrayList<String>(2);
        n.add("groovy");
        n.add("Groovy");
        NAMES = Collections.unmodifiableList((List<? extends String>)n);
        n = new ArrayList<String>(1);
        n.add("groovy");
        EXTENSIONS = Collections.unmodifiableList((List<? extends String>)n);
        n = new ArrayList<String>(1);
        n.add("application/x-groovy");
        MIME_TYPES = Collections.unmodifiableList((List<? extends String>)n);
    }
}
