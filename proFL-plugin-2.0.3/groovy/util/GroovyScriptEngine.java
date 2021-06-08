// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.HashSet;
import groovy.lang.GroovyCodeSource;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.tools.gse.DependencyTracker;
import org.codehaus.groovy.ast.InnerClassNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.classgen.GeneratorContext;
import org.codehaus.groovy.control.SourceUnit;
import java.security.CodeSource;
import groovy.lang.GroovyResourceLoader;
import java.util.Iterator;
import java.util.Set;
import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.Script;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import groovy.lang.DeprecationException;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedAction;
import groovy.lang.Binding;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.codehaus.groovy.control.CompilerConfiguration;
import java.util.Map;
import groovy.lang.GroovyClassLoader;
import java.net.URL;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.tools.gse.StringSetMap;
import java.lang.ref.WeakReference;

public class GroovyScriptEngine implements ResourceConnector
{
    private static final ClassLoader CL_STUB;
    private static WeakReference<ThreadLocal<StringSetMap>> dependencyCache;
    private static WeakReference<ThreadLocal<CompilationUnit>> localCu;
    private URL[] roots;
    private ResourceConnector rc;
    private final ClassLoader parentLoader;
    private final GroovyClassLoader groovyLoader;
    private final Map<String, ScriptCacheEntry> scriptCache;
    private CompilerConfiguration config;
    
    private static synchronized ThreadLocal<StringSetMap> getDepCache() {
        ThreadLocal<StringSetMap> local = GroovyScriptEngine.dependencyCache.get();
        if (local != null) {
            return local;
        }
        local = new ThreadLocal<StringSetMap>() {
            @Override
            protected StringSetMap initialValue() {
                return new StringSetMap();
            }
        };
        GroovyScriptEngine.dependencyCache = new WeakReference<ThreadLocal<StringSetMap>>(local);
        return local;
    }
    
    private static synchronized ThreadLocal<CompilationUnit> getLocalCompilationUnit() {
        ThreadLocal<CompilationUnit> local = GroovyScriptEngine.localCu.get();
        if (local != null) {
            return local;
        }
        local = new ThreadLocal<CompilationUnit>();
        GroovyScriptEngine.localCu = new WeakReference<ThreadLocal<CompilationUnit>>(local);
        return local;
    }
    
    public static void main(final String[] urls) throws Exception {
        final GroovyScriptEngine gse = new GroovyScriptEngine(urls);
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("groovy> ");
            final String line;
            if ((line = br.readLine()) == null || line.equals("quit")) {
                break;
            }
            try {
                System.out.println(gse.run(line, new Binding()));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private GroovyClassLoader initGroovyLoader() {
        return AccessController.doPrivileged((PrivilegedAction<GroovyClassLoader>)new PrivilegedAction() {
            public Object run() {
                if (GroovyScriptEngine.this.parentLoader instanceof GroovyClassLoader) {
                    return new ScriptClassLoader((GroovyClassLoader)GroovyScriptEngine.this.parentLoader);
                }
                return new ScriptClassLoader(GroovyScriptEngine.this.parentLoader);
            }
        });
    }
    
    public URLConnection getResourceConnection(final String resourceName) throws ResourceException {
        URLConnection groovyScriptConn = null;
        ResourceException se = null;
        for (final URL root : this.roots) {
            URL scriptURL = null;
            try {
                scriptURL = new URL(root, resourceName);
                groovyScriptConn = scriptURL.openConnection();
                groovyScriptConn.getInputStream();
                break;
            }
            catch (MalformedURLException e) {
                final String message = "Malformed URL: " + root + ", " + resourceName;
                if (se == null) {
                    se = new ResourceException(message);
                }
                else {
                    se = new ResourceException(message, se);
                }
            }
            catch (IOException e2) {
                groovyScriptConn = null;
                final String message = "Cannot open URL: " + scriptURL;
                groovyScriptConn = null;
                if (se == null) {
                    se = new ResourceException(message);
                }
                else {
                    se = new ResourceException(message, se);
                }
            }
        }
        if (se == null) {
            se = new ResourceException("No resource for " + resourceName + " was found");
        }
        if (groovyScriptConn == null) {
            throw se;
        }
        return groovyScriptConn;
    }
    
    private void forceClose(final URLConnection urlConnection) {
        if (urlConnection != null) {
            InputStream in = null;
            try {
                in = urlConnection.getInputStream();
            }
            catch (Exception e) {}
            finally {
                if (in != null) {
                    try {
                        in.close();
                    }
                    catch (IOException ex) {}
                }
            }
        }
    }
    
    private GroovyScriptEngine(URL[] roots, ClassLoader parent, ResourceConnector rc) {
        this.scriptCache = new ConcurrentHashMap<String, ScriptCacheEntry>();
        this.config = new CompilerConfiguration(CompilerConfiguration.DEFAULT);
        if (roots == null) {
            roots = new URL[0];
        }
        this.roots = roots;
        if (rc == null) {
            rc = this;
        }
        this.rc = rc;
        if (parent == GroovyScriptEngine.CL_STUB) {
            parent = this.getClass().getClassLoader();
        }
        this.parentLoader = parent;
        this.groovyLoader = this.initGroovyLoader();
        for (final URL root : roots) {
            this.groovyLoader.addURL(root);
        }
    }
    
    public GroovyScriptEngine(final URL[] roots) {
        this(roots, GroovyScriptEngine.CL_STUB, null);
    }
    
    public GroovyScriptEngine(final URL[] roots, final ClassLoader parentClassLoader) {
        this(roots, parentClassLoader, null);
    }
    
    public GroovyScriptEngine(final String[] urls) throws IOException {
        this(createRoots(urls), GroovyScriptEngine.CL_STUB, null);
    }
    
    private static URL[] createRoots(final String[] urls) throws MalformedURLException {
        if (urls == null) {
            return null;
        }
        final URL[] roots = new URL[urls.length];
        for (int i = 0; i < roots.length; ++i) {
            if (urls[i].indexOf("://") != -1) {
                roots[i] = new URL(urls[i]);
            }
            else {
                roots[i] = new File(urls[i]).toURI().toURL();
            }
        }
        return roots;
    }
    
    public GroovyScriptEngine(final String[] urls, final ClassLoader parentClassLoader) throws IOException {
        this(createRoots(urls), parentClassLoader, null);
    }
    
    public GroovyScriptEngine(final String url) throws IOException {
        this(new String[] { url });
    }
    
    public GroovyScriptEngine(final String url, final ClassLoader parentClassLoader) throws IOException {
        this(new String[] { url }, parentClassLoader);
    }
    
    public GroovyScriptEngine(final ResourceConnector rc) {
        this(null, GroovyScriptEngine.CL_STUB, rc);
    }
    
    public GroovyScriptEngine(final ResourceConnector rc, final ClassLoader parentClassLoader) {
        this(null, parentClassLoader, rc);
    }
    
    public ClassLoader getParentClassLoader() {
        return this.parentLoader;
    }
    
    @Deprecated
    public void setParentClassLoader(final ClassLoader parentClassLoader) {
        throw new DeprecationException("The method GroovyScriptEngine#setParentClassLoader(ClassLoader) is no longer supported. Specify a parentLoader in the constructor instead.");
    }
    
    public Class loadScriptByName(final String scriptName) throws ResourceException, ScriptException {
        final URLConnection conn = this.rc.getResourceConnection(scriptName);
        final String path = conn.getURL().getPath();
        final ScriptCacheEntry entry = this.scriptCache.get(path);
        Class clazz = null;
        if (entry != null) {
            clazz = entry.scriptClass;
        }
        try {
            if (this.isSourceNewer(entry)) {
                try {
                    final String encoding = (conn.getContentEncoding() != null) ? conn.getContentEncoding() : "UTF-8";
                    clazz = this.groovyLoader.parseClass(DefaultGroovyMethods.getText(conn.getInputStream(), encoding), path);
                }
                catch (IOException e) {
                    throw new ResourceException(e);
                }
            }
        }
        finally {
            this.forceClose(conn);
        }
        return clazz;
    }
    
    @Deprecated
    public Class loadScriptByName(final String scriptName, final ClassLoader parentClassLoader) throws ResourceException, ScriptException {
        throw new DeprecationException("The method GroovyScriptEngine#loadScriptByName(String,ClassLoader) is no longer supported. Use GroovyScriptEngine#loadScriptByName(String) instead.");
    }
    
    public String run(final String scriptName, final String argument) throws ResourceException, ScriptException {
        final Binding binding = new Binding();
        binding.setVariable("arg", argument);
        final Object result = this.run(scriptName, binding);
        return (result == null) ? "" : result.toString();
    }
    
    public Object run(final String scriptName, final Binding binding) throws ResourceException, ScriptException {
        return this.createScript(scriptName, binding).run();
    }
    
    public Script createScript(final String scriptName, final Binding binding) throws ResourceException, ScriptException {
        return InvokerHelper.createScript(this.loadScriptByName(scriptName), binding);
    }
    
    protected boolean isSourceNewer(final ScriptCacheEntry entry) throws ResourceException {
        if (entry == null) {
            return true;
        }
        final long now = System.currentTimeMillis();
        for (final String scriptName : entry.dependencies) {
            final ScriptCacheEntry depEntry = this.scriptCache.get(scriptName);
            final long nextPossibleRecompilationTime = depEntry.lastModified + this.config.getMinimumRecompilationInterval();
            if (nextPossibleRecompilationTime > now) {
                continue;
            }
            final URLConnection conn = this.rc.getResourceConnection(scriptName);
            final long lastMod = (conn.getLastModified() / 1000L + 1L) * 1000L - 1L;
            this.forceClose(conn);
            if (depEntry.lastModified < lastMod) {
                final ScriptCacheEntry newEntry = new ScriptCacheEntry(depEntry.scriptClass, lastMod, depEntry.dependencies);
                this.scriptCache.put(scriptName, newEntry);
                return true;
            }
        }
        return false;
    }
    
    public GroovyClassLoader getGroovyClassLoader() {
        return this.groovyLoader;
    }
    
    public CompilerConfiguration getConfig() {
        return this.config;
    }
    
    public void setConfig(final CompilerConfiguration config) {
        if (config == null) {
            throw new NullPointerException("configuration cannot be null");
        }
        this.config = config;
    }
    
    static {
        CL_STUB = new ClassLoader() {};
        GroovyScriptEngine.dependencyCache = new WeakReference<ThreadLocal<StringSetMap>>(null);
        GroovyScriptEngine.localCu = new WeakReference<ThreadLocal<CompilationUnit>>(null);
    }
    
    private static class ScriptCacheEntry
    {
        private final Class scriptClass;
        private final long lastModified;
        private final Set<String> dependencies;
        
        public ScriptCacheEntry(final Class clazz, final long modified, final Set<String> depend) {
            this.scriptClass = clazz;
            this.lastModified = modified;
            this.dependencies = depend;
        }
    }
    
    private class ScriptClassLoader extends GroovyClassLoader
    {
        public ScriptClassLoader(final GroovyClassLoader loader) {
            super(loader);
            this.setResLoader();
        }
        
        public ScriptClassLoader(final ClassLoader loader) {
            super(loader);
            this.setResLoader();
        }
        
        private void setResLoader() {
            final GroovyResourceLoader rl = this.getResourceLoader();
            this.setResourceLoader(new GroovyResourceLoader() {
                public URL loadGroovySource(final String className) throws MalformedURLException {
                    for (final String extension : GroovyScriptEngine.this.getConfig().getScriptExtensions()) {
                        final String filename = className.replace('.', File.separatorChar) + "." + extension;
                        try {
                            final URLConnection dependentScriptConn = GroovyScriptEngine.this.rc.getResourceConnection(filename);
                            return dependentScriptConn.getURL();
                        }
                        catch (ResourceException e) {
                            continue;
                        }
                        break;
                    }
                    return rl.loadGroovySource(className);
                }
            });
        }
        
        @Override
        protected CompilationUnit createCompilationUnit(final CompilerConfiguration config, final CodeSource source) {
            final CompilationUnit cu = super.createCompilationUnit(config, source);
            getLocalCompilationUnit().set(cu);
            final StringSetMap cache = getDepCache().get();
            for (final String depSourcePath : cache.get(".")) {
                try {
                    cu.addSource(GroovyScriptEngine.this.getResourceConnection(depSourcePath).getURL());
                }
                catch (ResourceException ex) {}
            }
            cache.clear();
            cu.addPhaseOperation(new CompilationUnit.PrimaryClassNodeOperation() {
                @Override
                public void call(final SourceUnit source, final GeneratorContext context, final ClassNode classNode) throws CompilationFailedException {
                    if (classNode instanceof InnerClassNode) {
                        return;
                    }
                    final DependencyTracker dt = new DependencyTracker(source, cache);
                    dt.visitClass(classNode);
                }
            }, 7);
            return cu;
        }
        
        @Override
        public Class parseClass(final GroovyCodeSource codeSource, final boolean shouldCacheSource) throws CompilationFailedException {
            final ThreadLocal<CompilationUnit> localCu = getLocalCompilationUnit();
            final ThreadLocal<StringSetMap> localCache = getDepCache();
            final ScriptCacheEntry origEntry = GroovyScriptEngine.this.scriptCache.get(codeSource.getName());
            Set<String> origDep = null;
            if (origEntry != null) {
                origDep = origEntry.dependencies;
            }
            if (origDep != null) {
                localCache.get().put(".", origDep);
            }
            final Class answer = super.parseClass(codeSource, false);
            final StringSetMap cache = localCache.get();
            cache.makeTransitiveHull();
            final long now = System.currentTimeMillis();
            final Set<String> entryNames = new HashSet<String>();
            for (final Map.Entry<String, Set<String>> entry : cache.entrySet()) {
                final String className = entry.getKey();
                final Class clazz = this.getClassCacheEntry(className);
                if (clazz == null) {
                    continue;
                }
                final String entryName = this.getPath(clazz);
                if (entryNames.contains(entryName)) {
                    continue;
                }
                entryNames.add(entryName);
                final Set<String> value = this.convertToPaths(entry.getValue());
                final ScriptCacheEntry cacheEntry = new ScriptCacheEntry(clazz, now, value);
                GroovyScriptEngine.this.scriptCache.put(entryName, cacheEntry);
            }
            cache.clear();
            localCu.set(null);
            return answer;
        }
        
        private String getPath(final Class clazz) {
            final ThreadLocal<CompilationUnit> localCu = getLocalCompilationUnit();
            final ClassNode classNode = localCu.get().getClassNode(clazz.getCanonicalName());
            return classNode.getModule().getContext().getName();
        }
        
        private Set<String> convertToPaths(final Set<String> orig) {
            final Set<String> ret = new HashSet<String>();
            for (final String className : orig) {
                final Class clazz = this.getClassCacheEntry(className);
                if (clazz == null) {
                    continue;
                }
                ret.add(this.getPath(clazz));
            }
            return ret;
        }
    }
}
