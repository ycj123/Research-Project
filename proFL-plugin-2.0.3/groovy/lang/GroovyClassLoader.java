// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import groovyjarjarasm.asm.ClassVisitor;
import groovyjarjarasm.asm.ClassWriter;
import org.codehaus.groovy.ast.ModuleNode;
import java.util.ArrayList;
import java.util.Collection;
import java.net.URLConnection;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.codehaus.groovy.classgen.Verifier;
import java.util.Enumeration;
import java.security.Permission;
import java.security.Permissions;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.util.List;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.io.InputStream;
import java.io.IOException;
import java.io.File;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.CompilationUnit;
import java.security.CodeSource;
import java.security.cert.Certificate;
import org.codehaus.groovy.ast.ClassNode;
import java.net.MalformedURLException;
import java.security.AccessController;
import java.util.Iterator;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.net.URL;
import org.codehaus.groovy.control.CompilerConfiguration;
import java.util.Map;
import java.net.URLClassLoader;

public class GroovyClassLoader extends URLClassLoader
{
    protected final Map<String, Class> classCache;
    protected final Map<String, Class> sourceCache;
    private final CompilerConfiguration config;
    private Boolean recompile;
    private static int scriptNameCounter;
    private GroovyResourceLoader resourceLoader;
    
    public GroovyClassLoader() {
        this(Thread.currentThread().getContextClassLoader());
    }
    
    public GroovyClassLoader(final ClassLoader loader) {
        this(loader, null);
    }
    
    public GroovyClassLoader(final GroovyClassLoader parent) {
        this(parent, parent.config, false);
    }
    
    public GroovyClassLoader(final ClassLoader parent, CompilerConfiguration config, final boolean useConfigurationClasspath) {
        super(new URL[0], parent);
        this.classCache = new HashMap<String, Class>();
        this.sourceCache = new HashMap<String, Class>();
        this.resourceLoader = new GroovyResourceLoader() {
            public URL loadGroovySource(final String filename) throws MalformedURLException {
                return AccessController.doPrivileged((PrivilegedAction<URL>)new PrivilegedAction<URL>() {
                    public URL run() {
                        for (final String extension : GroovyClassLoader.this.config.getScriptExtensions()) {
                            try {
                                final URL ret = GroovyClassLoader.this.getSourceFile(filename, extension);
                                if (ret != null) {
                                    return ret;
                                }
                                continue;
                            }
                            catch (Throwable t) {}
                        }
                        return null;
                    }
                });
            }
        };
        if (config == null) {
            config = CompilerConfiguration.DEFAULT;
        }
        this.config = config;
        if (useConfigurationClasspath) {
            for (final String path : config.getClasspath()) {
                this.addClasspath(path);
            }
        }
    }
    
    public GroovyClassLoader(final ClassLoader loader, final CompilerConfiguration config) {
        this(loader, config, true);
    }
    
    public void setResourceLoader(final GroovyResourceLoader resourceLoader) {
        if (resourceLoader == null) {
            throw new IllegalArgumentException("Resource loader must not be null!");
        }
        this.resourceLoader = resourceLoader;
    }
    
    public GroovyResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
    
    @Deprecated
    public Class defineClass(final ClassNode classNode, final String file) {
        throw new DeprecationException("the method GroovyClassLoader#defineClass(ClassNode, String) is no longer used and removed");
    }
    
    public Class defineClass(final ClassNode classNode, final String file, final String newCodeBase) {
        CodeSource codeSource = null;
        try {
            codeSource = new CodeSource(new URL("file", "", newCodeBase), (Certificate[])null);
        }
        catch (MalformedURLException ex) {}
        final CompilationUnit unit = this.createCompilationUnit(this.config, codeSource);
        final ClassCollector collector = this.createCollector(unit, classNode.getModule().getContext());
        try {
            unit.addClassNode(classNode);
            unit.setClassgenCallback(collector);
            unit.compile(7);
            this.definePackage(collector.generatedClass.getName());
            return collector.generatedClass;
        }
        catch (CompilationFailedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Class parseClass(final File file) throws CompilationFailedException, IOException {
        return this.parseClass(new GroovyCodeSource(file, this.config.getSourceEncoding()));
    }
    
    public Class parseClass(final String text, final String fileName) throws CompilationFailedException {
        final GroovyCodeSource gcs = AccessController.doPrivileged((PrivilegedAction<GroovyCodeSource>)new PrivilegedAction<GroovyCodeSource>() {
            public GroovyCodeSource run() {
                return new GroovyCodeSource(text, fileName, "/groovy/script");
            }
        });
        gcs.setCachable(false);
        return this.parseClass(gcs);
    }
    
    public Class parseClass(final String text) throws CompilationFailedException {
        return this.parseClass(text, "script" + System.currentTimeMillis() + Math.abs(text.hashCode()) + ".groovy");
    }
    
    @Deprecated
    public Class parseClass(final InputStream in) throws CompilationFailedException {
        return this.parseClass(in, this.generateScriptName());
    }
    
    public synchronized String generateScriptName() {
        ++GroovyClassLoader.scriptNameCounter;
        return "script" + GroovyClassLoader.scriptNameCounter + ".groovy";
    }
    
    @Deprecated
    public Class parseClass(final InputStream in, final String fileName) throws CompilationFailedException {
        final GroovyCodeSource gcs = AccessController.doPrivileged((PrivilegedAction<GroovyCodeSource>)new PrivilegedAction<GroovyCodeSource>() {
            public GroovyCodeSource run() {
                try {
                    final String scriptText = (GroovyClassLoader.this.config.getSourceEncoding() != null) ? DefaultGroovyMethods.getText(in, GroovyClassLoader.this.config.getSourceEncoding()) : DefaultGroovyMethods.getText(in);
                    return new GroovyCodeSource(scriptText, fileName, "/groovy/script");
                }
                catch (IOException e) {
                    throw new RuntimeException("Impossible to read the content of the input stream for file named: " + fileName, e);
                }
            }
        });
        return this.parseClass(gcs);
    }
    
    public Class parseClass(final GroovyCodeSource codeSource) throws CompilationFailedException {
        return this.parseClass(codeSource, codeSource.isCachable());
    }
    
    public Class parseClass(final GroovyCodeSource codeSource, final boolean shouldCacheSource) throws CompilationFailedException {
        Class answer;
        synchronized (this.sourceCache) {
            answer = this.sourceCache.get(codeSource.getName());
            if (answer != null) {
                return answer;
            }
            if (shouldCacheSource) {
                answer = this.doParseClass(codeSource);
                this.sourceCache.put(codeSource.getName(), answer);
            }
        }
        if (!shouldCacheSource) {
            answer = this.doParseClass(codeSource);
        }
        return answer;
    }
    
    private Class doParseClass(final GroovyCodeSource codeSource) {
        this.validate(codeSource);
        final CompilationUnit unit = this.createCompilationUnit(this.config, codeSource.getCodeSource());
        SourceUnit su = null;
        if (codeSource.getFile() == null) {
            su = unit.addSource(codeSource.getName(), codeSource.getScriptText());
        }
        else {
            su = unit.addSource(codeSource.getFile());
        }
        final ClassCollector collector = this.createCollector(unit, su);
        unit.setClassgenCallback(collector);
        int goalPhase = 7;
        if (this.config != null && this.config.getTargetDirectory() != null) {
            goalPhase = 8;
        }
        unit.compile(goalPhase);
        Class answer = collector.generatedClass;
        final String mainClass = su.getAST().getMainClassName();
        for (final Object o : collector.getLoadedClasses()) {
            final Class clazz = (Class)o;
            final String clazzName = clazz.getName();
            this.definePackage(clazzName);
            this.setClassCacheEntry(clazz);
            if (clazzName.equals(mainClass)) {
                answer = clazz;
            }
        }
        return answer;
    }
    
    private void validate(final GroovyCodeSource codeSource) {
        if (codeSource.getFile() == null && codeSource.getScriptText() == null) {
            throw new IllegalArgumentException("Script text to compile cannot be null!");
        }
    }
    
    private void definePackage(final String className) {
        final int i = className.lastIndexOf(46);
        if (i != -1) {
            final String pkgName = className.substring(0, i);
            final Package pkg = this.getPackage(pkgName);
            if (pkg == null) {
                this.definePackage(pkgName, null, null, null, null, null, null, null);
            }
        }
    }
    
    protected String[] getClassPath() {
        final URL[] urls = this.getURLs();
        final String[] ret = new String[urls.length];
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = urls[i].getFile();
        }
        return ret;
    }
    
    @Deprecated
    protected void expandClassPath(final List pathList, final String base, final String classpath, final boolean isManifestClasspath) {
        throw new DeprecationException("the method groovy.lang.GroovyClassLoader#expandClassPath(List,String,String,boolean) is no longer used internally and removed");
    }
    
    @Deprecated
    protected Class defineClass(final String name, final byte[] bytecode, final ProtectionDomain domain) {
        throw new DeprecationException("the method groovy.lang.GroovyClassLoader#defineClass(String,byte[],ProtectionDomain) is no longer used internally and removed");
    }
    
    @Override
    protected PermissionCollection getPermissions(final CodeSource codeSource) {
        PermissionCollection perms;
        try {
            perms = super.getPermissions(codeSource);
        }
        catch (SecurityException e) {
            perms = new Permissions();
        }
        final ProtectionDomain myDomain = AccessController.doPrivileged((PrivilegedAction<ProtectionDomain>)new PrivilegedAction<ProtectionDomain>() {
            public ProtectionDomain run() {
                return this.getClass().getProtectionDomain();
            }
        });
        final PermissionCollection myPerms = myDomain.getPermissions();
        if (myPerms != null) {
            final Enumeration<Permission> elements = myPerms.elements();
            while (elements.hasMoreElements()) {
                perms.add(elements.nextElement());
            }
        }
        perms.setReadOnly();
        return perms;
    }
    
    protected CompilationUnit createCompilationUnit(final CompilerConfiguration config, final CodeSource source) {
        return new CompilationUnit(config, source, this);
    }
    
    protected ClassCollector createCollector(final CompilationUnit unit, final SourceUnit su) {
        final InnerLoader loader = AccessController.doPrivileged((PrivilegedAction<InnerLoader>)new PrivilegedAction<InnerLoader>() {
            public InnerLoader run() {
                return new InnerLoader(GroovyClassLoader.this);
            }
        });
        return new ClassCollector(loader, unit, su);
    }
    
    public Class defineClass(final String name, final byte[] b) {
        return super.defineClass(name, b, 0, b.length);
    }
    
    public Class loadClass(final String name, final boolean lookupScriptFiles, final boolean preferClassOverScript) throws ClassNotFoundException, CompilationFailedException {
        return this.loadClass(name, lookupScriptFiles, preferClassOverScript, false);
    }
    
    protected Class getClassCacheEntry(final String name) {
        if (name == null) {
            return null;
        }
        synchronized (this.classCache) {
            return this.classCache.get(name);
        }
    }
    
    protected void setClassCacheEntry(final Class cls) {
        synchronized (this.classCache) {
            this.classCache.put(cls.getName(), cls);
        }
    }
    
    protected void removeClassCacheEntry(final String name) {
        synchronized (this.classCache) {
            this.classCache.remove(name);
        }
    }
    
    public void addURL(final URL url) {
        super.addURL(url);
    }
    
    protected boolean isRecompilable(final Class cls) {
        if (cls == null) {
            return true;
        }
        if (cls.getClassLoader() == this) {
            return false;
        }
        if (this.recompile == null && !this.config.getRecompileGroovySource()) {
            return false;
        }
        if (this.recompile != null && !this.recompile) {
            return false;
        }
        if (!GroovyObject.class.isAssignableFrom(cls)) {
            return false;
        }
        final long timestamp = this.getTimeStamp(cls);
        return timestamp != Long.MAX_VALUE;
    }
    
    public void setShouldRecompile(final Boolean mode) {
        this.recompile = mode;
    }
    
    public Boolean isShouldRecompile() {
        return this.recompile;
    }
    
    public Class loadClass(final String name, final boolean lookupScriptFiles, final boolean preferClassOverScript, final boolean resolve) throws ClassNotFoundException, CompilationFailedException {
        Class cls = this.getClassCacheEntry(name);
        final boolean recompile = this.isRecompilable(cls);
        if (!recompile) {
            return cls;
        }
        ClassNotFoundException last = null;
        try {
            final Class parentClassLoaderClass = super.loadClass(name, resolve);
            if (cls != parentClassLoaderClass) {
                return parentClassLoaderClass;
            }
        }
        catch (ClassNotFoundException cnfe) {
            last = cnfe;
        }
        catch (NoClassDefFoundError ncdfe) {
            if (ncdfe.getMessage().indexOf("wrong name") <= 0) {
                throw ncdfe;
            }
            last = new ClassNotFoundException(name);
        }
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            final String className = name.replace('/', '.');
            final int i = className.lastIndexOf(46);
            if (i != -1 && !className.startsWith("sun.reflect.")) {
                sm.checkPackageAccess(className.substring(0, i));
            }
        }
        if (cls != null && preferClassOverScript) {
            return cls;
        }
        if (lookupScriptFiles) {
            try {
                final Class classCacheEntry = this.getClassCacheEntry(name);
                if (classCacheEntry != cls) {
                    return classCacheEntry;
                }
                final URL source = this.resourceLoader.loadGroovySource(name);
                final Class oldClass = cls;
                cls = null;
                cls = this.recompile(source, name, oldClass);
            }
            catch (IOException ioe) {
                last = new ClassNotFoundException("IOException while opening groovy source: " + name, ioe);
            }
            finally {
                if (cls == null) {
                    this.removeClassCacheEntry(name);
                }
                else {
                    this.setClassCacheEntry(cls);
                }
            }
        }
        if (cls != null) {
            return cls;
        }
        if (last == null) {
            throw new AssertionError(true);
        }
        throw last;
    }
    
    protected Class recompile(final URL source, final String className, final Class oldClass) throws CompilationFailedException, IOException {
        if (source != null && ((oldClass != null && this.isSourceNewer(source, oldClass)) || oldClass == null)) {
            synchronized (this.sourceCache) {
                this.sourceCache.remove(className);
                return this.parseClass(source.openStream(), className);
            }
        }
        return oldClass;
    }
    
    @Override
    protected Class loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
        return this.loadClass(name, true, true, resolve);
    }
    
    protected long getTimeStamp(final Class cls) {
        return Verifier.getTimestamp(cls);
    }
    
    private String decodeFileName(final String fileName) {
        String decodedFile = fileName;
        try {
            decodedFile = URLDecoder.decode(fileName, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            System.err.println("Encountered an invalid encoding scheme when trying to use URLDecoder.decode() inside of the GroovyClassLoader.decodeFileName() method.  Returning the unencoded URL.");
            System.err.println("Please note that if you encounter this error and you have spaces in your directory you will run into issues.  Refer to GROOVY-1787 for description of this bug.");
        }
        return decodedFile;
    }
    
    private boolean isFile(final URL ret) {
        return ret != null && ret.getProtocol().equals("file");
    }
    
    private File getFileForUrl(final URL ret, final String filename) {
        String fileWithoutPackage = filename;
        if (fileWithoutPackage.indexOf(47) != -1) {
            final int index = fileWithoutPackage.lastIndexOf(47);
            fileWithoutPackage = fileWithoutPackage.substring(index + 1);
        }
        return this.fileReallyExists(ret, fileWithoutPackage);
    }
    
    private File fileReallyExists(final URL ret, final String fileWithoutPackage) {
        final File path = new File(this.decodeFileName(ret.getFile())).getParentFile();
        if (path.exists() && path.isDirectory()) {
            final File file = new File(path, fileWithoutPackage);
            if (file.exists()) {
                final File parent = file.getParentFile();
                for (final String child : parent.list()) {
                    if (child.equals(fileWithoutPackage)) {
                        return file;
                    }
                }
            }
        }
        return null;
    }
    
    private URL getSourceFile(final String name, final String extension) {
        final String filename = name.replace('.', '/') + "." + extension;
        final URL ret = this.getResource(filename);
        if (this.isFile(ret) && this.getFileForUrl(ret, filename) == null) {
            return null;
        }
        return ret;
    }
    
    private URL getSourceFile(final String name) {
        return this.getSourceFile(name, this.config.getDefaultScriptExtension());
    }
    
    protected boolean isSourceNewer(final URL source, final Class cls) throws IOException {
        long lastMod;
        if (this.isFile(source)) {
            final String path = source.getPath().replace('/', File.separatorChar).replace('|', ':');
            final File file = new File(path);
            lastMod = file.lastModified();
        }
        else {
            final URLConnection conn = source.openConnection();
            lastMod = conn.getLastModified();
            conn.getInputStream().close();
        }
        final long classTime = this.getTimeStamp(cls);
        return classTime + this.config.getMinimumRecompilationInterval() < lastMod;
    }
    
    public void addClasspath(final String path) {
        AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction<Void>() {
            public Void run() {
                try {
                    final File f = new File(path);
                    final URL newURL = f.toURI().toURL();
                    final URL[] arr$;
                    final URL[] urls = arr$ = GroovyClassLoader.this.getURLs();
                    for (final URL url : arr$) {
                        if (url.equals(newURL)) {
                            return null;
                        }
                    }
                    GroovyClassLoader.this.addURL(newURL);
                }
                catch (MalformedURLException ex) {}
                return null;
            }
        });
    }
    
    public Class[] getLoadedClasses() {
        synchronized (this.classCache) {
            final Collection<Class> values = (Collection<Class>)this.classCache.values();
            return values.toArray(new Class[values.size()]);
        }
    }
    
    public void clearCache() {
        synchronized (this.classCache) {
            this.classCache.clear();
        }
        synchronized (this.sourceCache) {
            this.sourceCache.clear();
        }
    }
    
    static {
        GroovyClassLoader.scriptNameCounter = 1000000;
    }
    
    public static class InnerLoader extends GroovyClassLoader
    {
        private final GroovyClassLoader delegate;
        private final long timeStamp;
        
        public InnerLoader(final GroovyClassLoader delegate) {
            super(delegate);
            this.delegate = delegate;
            this.timeStamp = System.currentTimeMillis();
        }
        
        @Override
        public void addClasspath(final String path) {
            this.delegate.addClasspath(path);
        }
        
        @Override
        public void clearCache() {
            this.delegate.clearCache();
        }
        
        @Override
        public URL findResource(final String name) {
            return this.delegate.findResource(name);
        }
        
        @Override
        public Enumeration findResources(final String name) throws IOException {
            return this.delegate.findResources(name);
        }
        
        @Override
        public Class[] getLoadedClasses() {
            return this.delegate.getLoadedClasses();
        }
        
        @Override
        public URL getResource(final String name) {
            return this.delegate.getResource(name);
        }
        
        @Override
        public InputStream getResourceAsStream(final String name) {
            return this.delegate.getResourceAsStream(name);
        }
        
        @Override
        public GroovyResourceLoader getResourceLoader() {
            return this.delegate.getResourceLoader();
        }
        
        @Override
        public URL[] getURLs() {
            return this.delegate.getURLs();
        }
        
        @Override
        public Class loadClass(final String name, final boolean lookupScriptFiles, final boolean preferClassOverScript, final boolean resolve) throws ClassNotFoundException, CompilationFailedException {
            final Class c = this.findLoadedClass(name);
            if (c != null) {
                return c;
            }
            return this.delegate.loadClass(name, lookupScriptFiles, preferClassOverScript, resolve);
        }
        
        @Override
        public Class parseClass(final GroovyCodeSource codeSource, final boolean shouldCache) throws CompilationFailedException {
            return this.delegate.parseClass(codeSource, shouldCache);
        }
        
        @Override
        public void setResourceLoader(final GroovyResourceLoader resourceLoader) {
            this.delegate.setResourceLoader(resourceLoader);
        }
        
        @Override
        public void addURL(final URL url) {
            this.delegate.addURL(url);
        }
        
        public long getTimeStamp() {
            return this.timeStamp;
        }
    }
    
    public static class ClassCollector extends CompilationUnit.ClassgenCallback
    {
        private Class generatedClass;
        private final GroovyClassLoader cl;
        private final SourceUnit su;
        private final CompilationUnit unit;
        private final Collection<Class> loadedClasses;
        
        protected ClassCollector(final InnerLoader cl, final CompilationUnit unit, final SourceUnit su) {
            this.cl = cl;
            this.unit = unit;
            this.loadedClasses = new ArrayList<Class>();
            this.su = su;
        }
        
        public GroovyClassLoader getDefiningClassLoader() {
            return this.cl;
        }
        
        protected Class createClass(final byte[] code, final ClassNode classNode) {
            final GroovyClassLoader cl = this.getDefiningClassLoader();
            final Class theClass = cl.defineClass(classNode.getName(), code, 0, code.length, this.unit.getAST().getCodeSource());
            this.loadedClasses.add(theClass);
            if (this.generatedClass == null) {
                final ModuleNode mn = classNode.getModule();
                SourceUnit msu = null;
                if (mn != null) {
                    msu = mn.getContext();
                }
                ClassNode main = null;
                if (mn != null) {
                    main = mn.getClasses().get(0);
                }
                if (msu == this.su && main == classNode) {
                    this.generatedClass = theClass;
                }
            }
            return theClass;
        }
        
        protected Class onClassNode(final ClassWriter classWriter, final ClassNode classNode) {
            final byte[] code = classWriter.toByteArray();
            return this.createClass(code, classNode);
        }
        
        @Override
        public void call(final ClassVisitor classWriter, final ClassNode classNode) {
            this.onClassNode((ClassWriter)classWriter, classNode);
        }
        
        public Collection getLoadedClasses() {
            return this.loadedClasses;
        }
    }
}
