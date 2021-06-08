// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.bsf;

import org.apache.bsf.BSFDeclaredBean;
import org.apache.bsf.BSFEngine;
import org.apache.bsf.util.BSFFunctions;
import java.util.HashMap;
import java.security.AccessController;
import org.codehaus.groovy.control.CompilerConfiguration;
import java.security.PrivilegedAction;
import groovy.lang.GroovyShell;
import java.util.Vector;
import org.apache.bsf.BSFManager;
import java.util.logging.Level;
import groovy.lang.Script;
import org.apache.bsf.BSFException;
import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Binding;
import java.util.Map;
import java.util.logging.Logger;

public class CachingGroovyEngine extends GroovyEngine
{
    private static final Logger LOG;
    private static final Object[] EMPTY_ARGS;
    private Map evalScripts;
    private Map execScripts;
    private Binding context;
    private GroovyClassLoader loader;
    
    @Override
    public Object eval(final String source, final int lineNo, final int columnNo, final Object script) throws BSFException {
        try {
            Class scriptClass = this.evalScripts.get(script);
            if (scriptClass == null) {
                scriptClass = this.loader.parseClass(script.toString(), source);
                this.evalScripts.put(script, scriptClass);
            }
            else {
                CachingGroovyEngine.LOG.fine("eval() - Using cached script...");
            }
            final Script s = InvokerHelper.createScript(scriptClass, this.context);
            return s.run();
        }
        catch (Exception e) {
            throw new BSFException(100, "exception from Groovy: " + e, (Throwable)e);
        }
    }
    
    @Override
    public void exec(final String source, final int lineNo, final int columnNo, final Object script) throws BSFException {
        try {
            Class scriptClass = this.execScripts.get(script);
            if (scriptClass == null) {
                scriptClass = this.loader.parseClass(script.toString(), source);
                this.execScripts.put(script, scriptClass);
            }
            else {
                CachingGroovyEngine.LOG.fine("exec() - Using cached version of class...");
            }
            InvokerHelper.invokeMethod(scriptClass, "main", CachingGroovyEngine.EMPTY_ARGS);
        }
        catch (Exception e) {
            CachingGroovyEngine.LOG.log(Level.WARNING, "BSF trace", e);
            throw new BSFException(100, "exception from Groovy: " + e, (Throwable)e);
        }
    }
    
    @Override
    public void initialize(final BSFManager mgr, final String lang, final Vector declaredBeans) throws BSFException {
        super.initialize(mgr, lang, declaredBeans);
        ClassLoader parent = mgr.getClassLoader();
        if (parent == null) {
            parent = GroovyShell.class.getClassLoader();
        }
        final ClassLoader finalParent = parent;
        this.loader = AccessController.doPrivileged((PrivilegedAction<GroovyClassLoader>)new PrivilegedAction() {
            public Object run() {
                final CompilerConfiguration configuration = new CompilerConfiguration();
                configuration.setClasspath(mgr.getClassPath());
                return new GroovyClassLoader(finalParent, configuration);
            }
        });
        this.execScripts = new HashMap();
        this.evalScripts = new HashMap();
        (this.context = this.shell.getContext()).setVariable("bsf", new BSFFunctions(mgr, (BSFEngine)this));
        for (int size = declaredBeans.size(), i = 0; i < size; ++i) {
            this.declareBean(declaredBeans.elementAt(i));
        }
    }
    
    static {
        LOG = Logger.getLogger(CachingGroovyEngine.class.getName());
        EMPTY_ARGS = new Object[] { new String[0] };
    }
}
