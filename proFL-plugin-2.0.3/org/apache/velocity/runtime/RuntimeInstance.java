// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime;

import org.apache.velocity.runtime.resource.ContentResource;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.Template;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import java.io.Reader;
import org.apache.velocity.runtime.parser.Parser;
import org.apache.velocity.runtime.directive.Directive;
import java.util.Enumeration;
import org.apache.velocity.runtime.log.LogManager;
import org.apache.velocity.app.event.EventHandler;
import org.apache.velocity.app.event.InvalidReferenceEventHandler;
import org.apache.velocity.app.event.IncludeEventHandler;
import org.apache.velocity.app.event.MethodExceptionEventHandler;
import org.apache.velocity.app.event.NullSetEventHandler;
import org.apache.velocity.app.event.ReferenceInsertionEventHandler;
import java.util.Properties;
import org.apache.velocity.util.StringUtils;
import java.io.InputStream;
import java.io.IOException;
import java.io.File;
import org.apache.velocity.util.RuntimeServicesAware;
import org.apache.velocity.util.introspection.UberspectLoggable;
import org.apache.velocity.util.ClassUtils;
import java.util.HashMap;
import org.apache.velocity.util.introspection.Uberspect;
import java.util.Map;
import org.apache.velocity.util.introspection.Introspector;
import org.apache.velocity.app.event.EventCartridge;
import org.apache.velocity.runtime.resource.ResourceManager;
import java.util.Hashtable;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;
import org.apache.velocity.runtime.log.Log;

public class RuntimeInstance implements RuntimeConstants, RuntimeServices
{
    private VelocimacroFactory vmFactory;
    private Log log;
    private ParserPool parserPool;
    private boolean initializing;
    private boolean initialized;
    private ExtendedProperties overridingProperties;
    private Hashtable runtimeDirectives;
    private ExtendedProperties configuration;
    private ResourceManager resourceManager;
    private EventCartridge eventCartridge;
    private Introspector introspector;
    private Map applicationAttributes;
    private Uberspect uberSpect;
    
    public RuntimeInstance() {
        this.vmFactory = null;
        this.log = new Log();
        this.initializing = false;
        this.initialized = false;
        this.overridingProperties = null;
        this.configuration = new ExtendedProperties();
        this.resourceManager = null;
        this.eventCartridge = null;
        this.introspector = null;
        this.applicationAttributes = null;
        this.vmFactory = new VelocimacroFactory(this);
        this.introspector = new Introspector(this.getLog());
        this.applicationAttributes = new HashMap();
    }
    
    public synchronized void init() throws Exception {
        if (!this.initialized && !this.initializing) {
            this.initializing = true;
            this.log.trace("*******************************************************************");
            this.log.debug("Starting Apache Velocity v1.5 (compiled: 2007-02-22 08:52:29)");
            this.log.trace("RuntimeInstance initializing.");
            this.initializeProperties();
            this.initializeLog();
            this.initializeResourceManager();
            this.initializeDirectives();
            this.initializeEventHandlers();
            this.initializeParserPool();
            this.initializeIntrospection();
            this.vmFactory.initVelocimacro();
            this.log.trace("RuntimeInstance successfully initialized.");
            this.initialized = true;
            this.initializing = false;
        }
    }
    
    public boolean isInitialized() {
        return this.initialized;
    }
    
    private void initializeIntrospection() throws Exception {
        final String rm = this.getString("runtime.introspector.uberspect");
        if (rm == null || rm.length() <= 0) {
            final String err = "It appears that no class was specified as the Uberspect.  Please ensure that all configuration information is correct.";
            this.log.error(err);
            throw new Exception(err);
        }
        Object o = null;
        try {
            o = ClassUtils.getNewInstance(rm);
        }
        catch (ClassNotFoundException cnfe) {
            final String err2 = "The specified class for Uberspect (" + rm + ") does not exist or is not accessible to the current classloader.";
            this.log.error(err2);
            throw new Exception(err2);
        }
        if (!(o instanceof Uberspect)) {
            final String err3 = "The specified class for Uberspect (" + rm + ") does not implement " + Uberspect.class.getName() + "; Velocity is not initialized correctly.";
            this.log.error(err3);
            throw new Exception(err3);
        }
        this.uberSpect = (Uberspect)o;
        if (this.uberSpect instanceof UberspectLoggable) {
            ((UberspectLoggable)this.uberSpect).setLog(this.getLog());
        }
        if (this.uberSpect instanceof RuntimeServicesAware) {
            ((RuntimeServicesAware)this.uberSpect).setRuntimeServices(this);
        }
        this.uberSpect.init();
    }
    
    private void setDefaultProperties() {
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getResourceAsStream("/org/apache/velocity/runtime/defaults/velocity.properties");
            this.configuration.load(inputStream);
            if (this.log.isDebugEnabled()) {
                this.log.debug("Default Properties File: " + new File("org/apache/velocity/runtime/defaults/velocity.properties").getPath());
            }
        }
        catch (IOException ioe) {
            this.log.error("Cannot get Velocity Runtime default properties!", ioe);
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (IOException ioe2) {
                this.log.error("Cannot close Velocity Runtime default properties!", ioe2);
            }
        }
    }
    
    public void setProperty(final String key, final Object value) {
        if (this.overridingProperties == null) {
            this.overridingProperties = new ExtendedProperties();
        }
        this.overridingProperties.setProperty(key, value);
    }
    
    public void setConfiguration(final ExtendedProperties configuration) {
        if (this.overridingProperties == null) {
            this.overridingProperties = configuration;
        }
        else if (this.overridingProperties != configuration) {
            this.overridingProperties.combine(configuration);
        }
    }
    
    public void addProperty(final String key, final Object value) {
        if (this.overridingProperties == null) {
            this.overridingProperties = new ExtendedProperties();
        }
        this.overridingProperties.addProperty(key, value);
    }
    
    public void clearProperty(final String key) {
        if (this.overridingProperties != null) {
            this.overridingProperties.clearProperty(key);
        }
    }
    
    public Object getProperty(final String key) {
        Object o = null;
        if (!this.initialized && !this.initializing && this.overridingProperties != null) {
            o = this.overridingProperties.get(key);
        }
        if (o == null) {
            o = this.configuration.getProperty(key);
        }
        if (o instanceof String) {
            return StringUtils.nullTrim((String)o);
        }
        return o;
    }
    
    private void initializeProperties() {
        if (!this.configuration.isInitialized()) {
            this.setDefaultProperties();
        }
        if (this.overridingProperties != null) {
            this.configuration.combine(this.overridingProperties);
        }
    }
    
    public void init(final Properties p) throws Exception {
        this.overridingProperties = ExtendedProperties.convertProperties(p);
        this.init();
    }
    
    public void init(final String configurationFile) throws Exception {
        this.overridingProperties = new ExtendedProperties(configurationFile);
        this.init();
    }
    
    private void initializeResourceManager() throws Exception {
        final String rm = this.getString("resource.manager.class");
        if (rm == null || rm.length() <= 0) {
            final String err = "It appears that no class was specified as the ResourceManager.  Please ensure that all configuration information is correct.";
            this.log.error(err);
            throw new Exception(err);
        }
        Object o = null;
        try {
            o = ClassUtils.getNewInstance(rm);
        }
        catch (ClassNotFoundException cnfe) {
            final String err2 = "The specified class for ResourceManager (" + rm + ") does not exist or is not accessible to the current classloader.";
            this.log.error(err2);
            throw new Exception(err2);
        }
        if (!(o instanceof ResourceManager)) {
            final String err3 = "The specified class for ResourceManager (" + rm + ") does not implement " + ResourceManager.class.getName() + "; Velocity is not initialized correctly.";
            this.log.error(err3);
            throw new Exception(err3);
        }
        (this.resourceManager = (ResourceManager)o).initialize(this);
    }
    
    private void initializeEventHandlers() throws Exception {
        this.eventCartridge = new EventCartridge();
        final String[] referenceinsertion = this.configuration.getStringArray("eventhandler.referenceinsertion.class");
        if (referenceinsertion != null) {
            for (int i = 0; i < referenceinsertion.length; ++i) {
                final EventHandler ev = this.initializeSpecificEventHandler(referenceinsertion[i], "eventhandler.referenceinsertion.class", ReferenceInsertionEventHandler.class);
                if (ev != null) {
                    this.eventCartridge.addReferenceInsertionEventHandler((ReferenceInsertionEventHandler)ev);
                }
            }
        }
        final String[] nullset = this.configuration.getStringArray("eventhandler.nullset.class");
        if (nullset != null) {
            for (int j = 0; j < nullset.length; ++j) {
                final EventHandler ev2 = this.initializeSpecificEventHandler(nullset[j], "eventhandler.nullset.class", NullSetEventHandler.class);
                if (ev2 != null) {
                    this.eventCartridge.addNullSetEventHandler((NullSetEventHandler)ev2);
                }
            }
        }
        final String[] methodexception = this.configuration.getStringArray("eventhandler.methodexception.class");
        if (methodexception != null) {
            for (int k = 0; k < methodexception.length; ++k) {
                final EventHandler ev3 = this.initializeSpecificEventHandler(methodexception[k], "eventhandler.methodexception.class", MethodExceptionEventHandler.class);
                if (ev3 != null) {
                    this.eventCartridge.addMethodExceptionHandler((MethodExceptionEventHandler)ev3);
                }
            }
        }
        final String[] includeHandler = this.configuration.getStringArray("eventhandler.include.class");
        if (includeHandler != null) {
            for (int l = 0; l < includeHandler.length; ++l) {
                final EventHandler ev4 = this.initializeSpecificEventHandler(includeHandler[l], "eventhandler.include.class", IncludeEventHandler.class);
                if (ev4 != null) {
                    this.eventCartridge.addIncludeEventHandler((IncludeEventHandler)ev4);
                }
            }
        }
        final String[] invalidReferenceSet = this.configuration.getStringArray("eventhandler.invalidreferences.class");
        if (invalidReferenceSet != null) {
            for (int m = 0; m < invalidReferenceSet.length; ++m) {
                final EventHandler ev5 = this.initializeSpecificEventHandler(invalidReferenceSet[m], "eventhandler.invalidreferences.class", InvalidReferenceEventHandler.class);
                if (ev5 != null) {
                    this.eventCartridge.addInvalidReferenceEventHandler((InvalidReferenceEventHandler)ev5);
                }
            }
        }
    }
    
    private EventHandler initializeSpecificEventHandler(final String classname, final String paramName, final Class EventHandlerInterface) throws Exception {
        if (classname == null || classname.length() <= 0) {
            return null;
        }
        Object o = null;
        try {
            o = ClassUtils.getNewInstance(classname);
        }
        catch (ClassNotFoundException cnfe) {
            final String err = "The specified class for " + paramName + " (" + classname + ") does not exist or is not accessible to the current classloader.";
            this.log.error(err);
            throw new Exception(err);
        }
        if (!EventHandlerInterface.isAssignableFrom(EventHandlerInterface)) {
            final String err2 = "The specified class for " + paramName + " (" + classname + ") does not implement " + EventHandlerInterface.getName() + "; Velocity is not initialized correctly.";
            this.log.error(err2);
            throw new Exception(err2);
        }
        final EventHandler ev = (EventHandler)o;
        if (ev instanceof RuntimeServicesAware) {
            ((RuntimeServicesAware)ev).setRuntimeServices(this);
        }
        return ev;
    }
    
    private void initializeLog() throws Exception {
        LogManager.updateLog(this.log, this);
    }
    
    private void initializeDirectives() throws Exception {
        this.runtimeDirectives = new Hashtable();
        final Properties directiveProperties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getResourceAsStream("/org/apache/velocity/runtime/defaults/directive.properties");
            if (inputStream == null) {
                throw new Exception("Error loading directive.properties! Something is very wrong if these properties aren't being located. Either your Velocity distribution is incomplete or your Velocity jar file is corrupted!");
            }
            directiveProperties.load(inputStream);
        }
        catch (IOException ioe) {
            this.log.error("Error while loading directive properties!", ioe);
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (IOException ioe2) {
                this.log.error("Cannot close directive properties!", ioe2);
            }
        }
        final Enumeration directiveClasses = directiveProperties.elements();
        while (directiveClasses.hasMoreElements()) {
            final String directiveClass = directiveClasses.nextElement();
            this.loadDirective(directiveClass);
            this.log.debug("Loaded System Directive: " + directiveClass);
        }
        final String[] userdirective = this.configuration.getStringArray("userdirective");
        for (int i = 0; i < userdirective.length; ++i) {
            this.loadDirective(userdirective[i]);
            if (this.log.isInfoEnabled()) {
                this.log.info("Loaded User Directive: " + userdirective[i]);
            }
        }
    }
    
    private void loadDirective(final String directiveClass) {
        try {
            final Object o = ClassUtils.getNewInstance(directiveClass);
            if (o instanceof Directive) {
                final Directive directive = (Directive)o;
                this.runtimeDirectives.put(directive.getName(), directive);
            }
            else {
                this.log.error(directiveClass + " does not implement " + Directive.class.getName() + "; it cannot be loaded.");
            }
        }
        catch (Exception e) {
            this.log.error("Failed to load Directive: " + directiveClass, e);
        }
    }
    
    private void initializeParserPool() throws Exception {
        final String pp = this.getString("parser.pool.class");
        if (pp == null || pp.length() <= 0) {
            final String err = "It appears that no class was specified as the ParserPool.  Please ensure that all configuration information is correct.";
            this.log.error(err);
            throw new Exception(err);
        }
        Object o = null;
        try {
            o = ClassUtils.getNewInstance(pp);
        }
        catch (ClassNotFoundException cnfe) {
            final String err2 = "The specified class for ParserPool (" + pp + ") does not exist (or is not accessible to the current classloader.";
            this.log.error(err2);
            throw new Exception(err2);
        }
        if (!(o instanceof ParserPool)) {
            final String err3 = "The specified class for ParserPool (" + pp + ") does not implement " + ParserPool.class + " Velocity not initialized correctly.";
            this.log.error(err3);
            throw new Exception(err3);
        }
        (this.parserPool = (ParserPool)o).initialize(this);
    }
    
    public Parser createNewParser() {
        if (!this.initialized && !this.initializing) {
            this.log.debug("Velocity was not initialized! Calling init()...");
            try {
                this.init();
            }
            catch (Exception e) {
                this.getLog().error("Could not auto-initialize Velocity", e);
                throw new IllegalStateException("Velocity could not be initialized!");
            }
        }
        final Parser parser = new Parser(this);
        parser.setDirectives(this.runtimeDirectives);
        return parser;
    }
    
    public SimpleNode parse(final Reader reader, final String templateName) throws ParseException {
        return this.parse(reader, templateName, true);
    }
    
    public SimpleNode parse(final Reader reader, final String templateName, final boolean dumpNamespace) throws ParseException {
        if (!this.initialized && !this.initializing) {
            this.log.debug("Velocity was not initialized! Calling init()...");
            try {
                this.init();
            }
            catch (Exception e) {
                this.getLog().error("Could not auto-initialize Velocity", e);
                throw new IllegalStateException("Velocity could not be initialized!");
            }
        }
        SimpleNode ast = null;
        Parser parser = this.parserPool.get();
        if (parser == null) {
            if (this.log.isInfoEnabled()) {
                this.log.info("Runtime : ran out of parsers. Creating a new one.  Please increment the parser.pool.size property. The current value is too small.");
            }
            parser = this.createNewParser();
        }
        if (parser != null) {
            try {
                if (dumpNamespace) {
                    this.dumpVMNamespace(templateName);
                }
                ast = parser.parse(reader, templateName);
                return ast;
            }
            finally {
                this.parserPool.put(parser);
            }
        }
        this.log.error("Runtime : ran out of parsers and unable to create more.");
        return ast;
    }
    
    public Template getTemplate(final String name) throws ResourceNotFoundException, ParseErrorException, Exception {
        return this.getTemplate(name, this.getString("input.encoding", "ISO-8859-1"));
    }
    
    public Template getTemplate(final String name, final String encoding) throws ResourceNotFoundException, ParseErrorException, Exception {
        if (!this.initialized && !this.initializing) {
            this.log.info("Velocity not initialized yet. Calling init()...");
            this.init();
        }
        return (Template)this.resourceManager.getResource(name, 1, encoding);
    }
    
    public ContentResource getContent(final String name) throws ResourceNotFoundException, ParseErrorException, Exception {
        return this.getContent(name, this.getString("input.encoding", "ISO-8859-1"));
    }
    
    public ContentResource getContent(final String name, final String encoding) throws ResourceNotFoundException, ParseErrorException, Exception {
        if (!this.initialized && !this.initializing) {
            this.log.info("Velocity not initialized yet. Calling init()...");
            this.init();
        }
        return (ContentResource)this.resourceManager.getResource(name, 2, encoding);
    }
    
    public String getLoaderNameForResource(final String resourceName) {
        if (!this.initialized && !this.initializing) {
            this.log.debug("Velocity was not initialized! Calling init()...");
            try {
                this.init();
            }
            catch (Exception e) {
                this.getLog().error("Could not initialize Velocity", e);
                throw new IllegalStateException("Velocity could not be initialized!");
            }
        }
        return this.resourceManager.getLoaderNameForResource(resourceName);
    }
    
    public Log getLog() {
        return this.log;
    }
    
    public void warn(final Object message) {
        this.getLog().warn(message);
    }
    
    public void info(final Object message) {
        this.getLog().info(message);
    }
    
    public void error(final Object message) {
        this.getLog().error(message);
    }
    
    public void debug(final Object message) {
        this.getLog().debug(message);
    }
    
    public String getString(final String key, final String defaultValue) {
        return this.configuration.getString(key, defaultValue);
    }
    
    public Directive getVelocimacro(final String vmName, final String templateName) {
        return this.vmFactory.getVelocimacro(vmName, templateName);
    }
    
    public boolean addVelocimacro(final String name, final String macro, final String[] argArray, final String sourceTemplate) {
        return this.vmFactory.addVelocimacro(name, macro, argArray, sourceTemplate);
    }
    
    public boolean isVelocimacro(final String vmName, final String templateName) {
        return this.vmFactory.isVelocimacro(vmName, templateName);
    }
    
    public boolean dumpVMNamespace(final String namespace) {
        return this.vmFactory.dumpVMNamespace(namespace);
    }
    
    public String getString(final String key) {
        return StringUtils.nullTrim(this.configuration.getString(key));
    }
    
    public int getInt(final String key) {
        return this.configuration.getInt(key);
    }
    
    public int getInt(final String key, final int defaultValue) {
        return this.configuration.getInt(key, defaultValue);
    }
    
    public boolean getBoolean(final String key, final boolean def) {
        return this.configuration.getBoolean(key, def);
    }
    
    public ExtendedProperties getConfiguration() {
        return this.configuration;
    }
    
    public Introspector getIntrospector() {
        return this.introspector;
    }
    
    public EventCartridge getApplicationEventCartridge() {
        return this.eventCartridge;
    }
    
    public Object getApplicationAttribute(final Object key) {
        return this.applicationAttributes.get(key);
    }
    
    public Object setApplicationAttribute(final Object key, final Object o) {
        return this.applicationAttributes.put(key, o);
    }
    
    public Uberspect getUberspect() {
        return this.uberSpect;
    }
}
