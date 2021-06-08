// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.jsr223;

import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.lang.reflect.Method;
import java.io.Writer;
import groovy.lang.MissingMethodException;
import groovy.lang.Tuple;
import org.codehaus.groovy.runtime.MetaClassHelper;
import groovy.lang.MetaClass;
import groovy.lang.DelegatingMetaClass;
import org.codehaus.groovy.runtime.MethodClosure;
import groovy.lang.Script;
import groovy.lang.MissingPropertyException;
import groovy.lang.Binding;
import java.io.PrintWriter;
import org.codehaus.groovy.control.CompilationFailedException;
import java.io.IOException;
import javax.script.CompiledScript;
import javax.script.ScriptEngineFactory;
import javax.script.SimpleBindings;
import javax.script.Bindings;
import org.codehaus.groovy.syntax.SyntaxException;
import javax.script.ScriptException;
import javax.script.ScriptContext;
import java.io.Reader;
import org.codehaus.groovy.control.CompilerConfiguration;
import java.util.Collections;
import java.util.HashMap;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Closure;
import java.util.Map;
import javax.script.Invocable;
import javax.script.Compilable;
import javax.script.AbstractScriptEngine;

public class GroovyScriptEngineImpl extends AbstractScriptEngine implements Compilable, Invocable
{
    private static boolean debug;
    private Map<String, Class> classMap;
    private Map<String, Closure> globalClosures;
    private GroovyClassLoader loader;
    private volatile GroovyScriptEngineFactory factory;
    private static int counter;
    
    public GroovyScriptEngineImpl() {
        this.classMap = (Map<String, Class>)Collections.synchronizedMap(new HashMap<String, Class>());
        this.globalClosures = Collections.synchronizedMap(new HashMap<String, Closure>());
        this.loader = new GroovyClassLoader(this.getParentLoader(), new CompilerConfiguration());
    }
    
    public Object eval(final Reader reader, final ScriptContext ctx) throws ScriptException {
        return this.eval(this.readFully(reader), ctx);
    }
    
    public Object eval(final String script, final ScriptContext ctx) throws ScriptException {
        try {
            final Class clazz = this.getScriptClass(script);
            if (clazz == null) {
                throw new ScriptException("Script class is null");
            }
            return this.eval(clazz, ctx);
        }
        catch (SyntaxException e) {
            throw new ScriptException(e.getMessage(), e.getSourceLocator(), e.getLine());
        }
        catch (Exception e2) {
            if (GroovyScriptEngineImpl.debug) {
                e2.printStackTrace();
            }
            throw new ScriptException(e2);
        }
    }
    
    public Bindings createBindings() {
        return new SimpleBindings();
    }
    
    public ScriptEngineFactory getFactory() {
        if (this.factory == null) {
            synchronized (this) {
                if (this.factory == null) {
                    this.factory = new GroovyScriptEngineFactory();
                }
            }
        }
        return this.factory;
    }
    
    public CompiledScript compile(final String scriptSource) throws ScriptException {
        try {
            return new GroovyCompiledScript(this, this.getScriptClass(scriptSource));
        }
        catch (SyntaxException e) {
            throw new ScriptException(e.getMessage(), e.getSourceLocator(), e.getLine());
        }
        catch (IOException e2) {
            throw new ScriptException(e2);
        }
        catch (CompilationFailedException ee) {
            throw new ScriptException(ee);
        }
    }
    
    public CompiledScript compile(final Reader reader) throws ScriptException {
        return this.compile(this.readFully(reader));
    }
    
    public Object invokeFunction(final String name, final Object... args) throws ScriptException, NoSuchMethodException {
        return this.invokeImpl(null, name, args);
    }
    
    public Object invokeMethod(final Object thiz, final String name, final Object... args) throws ScriptException, NoSuchMethodException {
        if (thiz == null) {
            throw new IllegalArgumentException("script object is null");
        }
        return this.invokeImpl(thiz, name, args);
    }
    
    public <T> T getInterface(final Class<T> clasz) {
        return this.makeInterface(null, clasz);
    }
    
    public <T> T getInterface(final Object thiz, final Class<T> clasz) {
        if (thiz == null) {
            throw new IllegalArgumentException("script object is null");
        }
        return (T)this.makeInterface(thiz, (Class<Object>)clasz);
    }
    
    Object eval(final Class scriptClass, final ScriptContext ctx) throws ScriptException {
        if (null == ctx.getAttribute("context", 100)) {
            ctx.setAttribute("context", ctx, 100);
            final Writer writer = ctx.getWriter();
            ctx.setAttribute("out", (writer instanceof PrintWriter) ? writer : new PrintWriter(writer, true), 100);
        }
        if (ctx.getWriter() != null) {
            ctx.setAttribute("out", new PrintWriter(ctx.getWriter(), true), 100);
        }
        final Binding binding = new Binding(ctx.getBindings(100)) {
            @Override
            public Object getVariable(final String name) {
                synchronized (ctx) {
                    final int scope = ctx.getAttributesScope(name);
                    if (scope != -1) {
                        return ctx.getAttribute(name, scope);
                    }
                }
                throw new MissingPropertyException(name, this.getClass());
            }
            
            @Override
            public void setVariable(final String name, final Object value) {
                synchronized (ctx) {
                    int scope = ctx.getAttributesScope(name);
                    if (scope == -1) {
                        scope = 100;
                    }
                    ctx.setAttribute(name, value, scope);
                }
            }
        };
        try {
            if (!Script.class.isAssignableFrom(scriptClass)) {
                return scriptClass;
            }
            final Script scriptObject = scriptClass.newInstance();
            scriptObject.setBinding(binding);
            final Method[] methods = scriptClass.getMethods();
            final Map<String, Closure> closures = new HashMap<String, Closure>();
            for (final Method m : methods) {
                final String name = m.getName();
                closures.put(name, new MethodClosure(scriptObject, name));
            }
            this.globalClosures.putAll(closures);
            final MetaClass oldMetaClass = scriptObject.getMetaClass();
            scriptObject.setMetaClass(new DelegatingMetaClass(oldMetaClass) {
                @Override
                public Object invokeMethod(final Object object, final String name, final Object args) {
                    if (args == null) {
                        return this.invokeMethod(object, name, MetaClassHelper.EMPTY_ARRAY);
                    }
                    if (args instanceof Tuple) {
                        return this.invokeMethod(object, name, ((Tuple)args).toArray());
                    }
                    if (args instanceof Object[]) {
                        return this.invokeMethod(object, name, (Object[])args);
                    }
                    return this.invokeMethod(object, name, new Object[] { args });
                }
                
                @Override
                public Object invokeMethod(final Object object, final String name, final Object[] args) {
                    try {
                        return super.invokeMethod(object, name, args);
                    }
                    catch (MissingMethodException mme) {
                        return GroovyScriptEngineImpl.this.callGlobal(name, args, ctx);
                    }
                }
                
                @Override
                public Object invokeStaticMethod(final Object object, final String name, final Object[] args) {
                    try {
                        return super.invokeStaticMethod(object, name, args);
                    }
                    catch (MissingMethodException mme) {
                        return GroovyScriptEngineImpl.this.callGlobal(name, args, ctx);
                    }
                }
            });
            return scriptObject.run();
        }
        catch (Exception e) {
            throw new ScriptException(e);
        }
        finally {
            ctx.removeAttribute("context", 100);
            ctx.removeAttribute("out", 100);
        }
    }
    
    Class getScriptClass(final String script) throws SyntaxException, CompilationFailedException, IOException {
        Class clazz = this.classMap.get(script);
        if (clazz != null) {
            return clazz;
        }
        clazz = this.loader.parseClass(script, this.generateScriptName());
        this.classMap.put(script, clazz);
        return clazz;
    }
    
    public void setClassLoader(final GroovyClassLoader classLoader) {
        this.loader = classLoader;
    }
    
    public GroovyClassLoader getClassLoader() {
        return this.loader;
    }
    
    private Object invokeImpl(final Object thiz, final String name, final Object... args) throws ScriptException, NoSuchMethodException {
        if (name == null) {
            throw new NullPointerException("method name is null");
        }
        try {
            if (thiz != null) {
                return InvokerHelper.invokeMethod(thiz, name, args);
            }
            return this.callGlobal(name, args);
        }
        catch (MissingMethodException mme) {
            throw new NoSuchMethodException(mme.getMessage());
        }
        catch (Exception e) {
            throw new ScriptException(e);
        }
    }
    
    private Object callGlobal(final String name, final Object[] args) {
        return this.callGlobal(name, args, this.context);
    }
    
    private Object callGlobal(final String name, final Object[] args, final ScriptContext ctx) {
        final Closure closure = this.globalClosures.get(name);
        if (closure != null) {
            return closure.call(args);
        }
        final Object value = ctx.getAttribute(name);
        if (value instanceof Closure) {
            return ((Closure)value).call(args);
        }
        throw new MissingMethodException(name, this.getClass(), args);
    }
    
    private synchronized String generateScriptName() {
        return "Script" + ++GroovyScriptEngineImpl.counter + ".groovy";
    }
    
    private <T> T makeInterface(final Object obj, final Class<T> clazz) {
        final Object thiz = obj;
        if (clazz == null || !clazz.isInterface()) {
            throw new IllegalArgumentException("interface Class expected");
        }
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, new InvocationHandler() {
            public Object invoke(final Object proxy, final Method m, final Object[] args) throws Throwable {
                return GroovyScriptEngineImpl.this.invokeImpl(thiz, m.getName(), args);
            }
        });
    }
    
    private ClassLoader getParentLoader() {
        final ClassLoader ctxtLoader = Thread.currentThread().getContextClassLoader();
        try {
            final Class c = ctxtLoader.loadClass(Script.class.getName());
            if (c == Script.class) {
                return ctxtLoader;
            }
        }
        catch (ClassNotFoundException ex) {}
        return Script.class.getClassLoader();
    }
    
    private String readFully(final Reader reader) throws ScriptException {
        final char[] arr = new char[8192];
        final StringBuilder buf = new StringBuilder();
        try {
            int numChars;
            while ((numChars = reader.read(arr, 0, arr.length)) > 0) {
                buf.append(arr, 0, numChars);
            }
        }
        catch (IOException exp) {
            throw new ScriptException(exp);
        }
        return buf.toString();
    }
    
    static {
        GroovyScriptEngineImpl.debug = false;
        GroovyScriptEngineImpl.counter = 0;
    }
}
