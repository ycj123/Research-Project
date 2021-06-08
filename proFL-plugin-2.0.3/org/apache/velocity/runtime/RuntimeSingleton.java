// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime;

import org.apache.velocity.util.introspection.Uberspect;
import org.apache.velocity.app.event.EventCartridge;
import org.apache.velocity.util.introspection.Introspector;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.log.Log;
import org.apache.velocity.runtime.resource.ContentResource;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.Template;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import java.io.Reader;
import java.util.Properties;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;

public class RuntimeSingleton implements RuntimeConstants
{
    private static RuntimeInstance ri;
    
    public static synchronized void init() throws Exception {
        RuntimeSingleton.ri.init();
    }
    
    public static boolean isInitialized() {
        return RuntimeSingleton.ri.isInitialized();
    }
    
    public static RuntimeServices getRuntimeServices() {
        return RuntimeSingleton.ri;
    }
    
    public static void setProperty(final String key, final Object value) {
        RuntimeSingleton.ri.setProperty(key, value);
    }
    
    public static void setConfiguration(final ExtendedProperties configuration) {
        RuntimeSingleton.ri.setConfiguration(configuration);
    }
    
    public static void addProperty(final String key, final Object value) {
        RuntimeSingleton.ri.addProperty(key, value);
    }
    
    public static void clearProperty(final String key) {
        RuntimeSingleton.ri.clearProperty(key);
    }
    
    public static Object getProperty(final String key) {
        return RuntimeSingleton.ri.getProperty(key);
    }
    
    public static void init(final Properties p) throws Exception {
        RuntimeSingleton.ri.init(p);
    }
    
    public static void init(final String configurationFile) throws Exception {
        RuntimeSingleton.ri.init(configurationFile);
    }
    
    public static SimpleNode parse(final Reader reader, final String templateName) throws ParseException {
        return RuntimeSingleton.ri.parse(reader, templateName);
    }
    
    public static SimpleNode parse(final Reader reader, final String templateName, final boolean dumpNamespace) throws ParseException {
        return RuntimeSingleton.ri.parse(reader, templateName, dumpNamespace);
    }
    
    public static Template getTemplate(final String name) throws ResourceNotFoundException, ParseErrorException, Exception {
        return RuntimeSingleton.ri.getTemplate(name);
    }
    
    public static Template getTemplate(final String name, final String encoding) throws ResourceNotFoundException, ParseErrorException, Exception {
        return RuntimeSingleton.ri.getTemplate(name, encoding);
    }
    
    public static ContentResource getContent(final String name) throws ResourceNotFoundException, ParseErrorException, Exception {
        return RuntimeSingleton.ri.getContent(name);
    }
    
    public static ContentResource getContent(final String name, final String encoding) throws ResourceNotFoundException, ParseErrorException, Exception {
        return RuntimeSingleton.ri.getContent(name, encoding);
    }
    
    public static String getLoaderNameForResource(final String resourceName) {
        return RuntimeSingleton.ri.getLoaderNameForResource(resourceName);
    }
    
    public static Log getLog() {
        return RuntimeSingleton.ri.getLog();
    }
    
    public static void warn(final Object message) {
        getLog().warn(message);
    }
    
    public static void info(final Object message) {
        getLog().info(message);
    }
    
    public static void error(final Object message) {
        getLog().error(message);
    }
    
    public static void debug(final Object message) {
        getLog().debug(message);
    }
    
    public static String getString(final String key, final String defaultValue) {
        return RuntimeSingleton.ri.getString(key, defaultValue);
    }
    
    public static Directive getVelocimacro(final String vmName, final String templateName) {
        return RuntimeSingleton.ri.getVelocimacro(vmName, templateName);
    }
    
    public static boolean addVelocimacro(final String name, final String macro, final String[] argArray, final String sourceTemplate) {
        return RuntimeSingleton.ri.addVelocimacro(name, macro, argArray, sourceTemplate);
    }
    
    public static boolean isVelocimacro(final String vmName, final String templateName) {
        return RuntimeSingleton.ri.isVelocimacro(vmName, templateName);
    }
    
    public static boolean dumpVMNamespace(final String namespace) {
        return RuntimeSingleton.ri.dumpVMNamespace(namespace);
    }
    
    public static String getString(final String key) {
        return RuntimeSingleton.ri.getString(key);
    }
    
    public static int getInt(final String key) {
        return RuntimeSingleton.ri.getInt(key);
    }
    
    public static int getInt(final String key, final int defaultValue) {
        return RuntimeSingleton.ri.getInt(key, defaultValue);
    }
    
    public static boolean getBoolean(final String key, final boolean def) {
        return RuntimeSingleton.ri.getBoolean(key, def);
    }
    
    public static ExtendedProperties getConfiguration() {
        return RuntimeSingleton.ri.getConfiguration();
    }
    
    public static Introspector getIntrospector() {
        return RuntimeSingleton.ri.getIntrospector();
    }
    
    public EventCartridge getEventCartridge() {
        return RuntimeSingleton.ri.getApplicationEventCartridge();
    }
    
    public static Object getApplicationAttribute(final Object key) {
        return RuntimeSingleton.ri.getApplicationAttribute(key);
    }
    
    public static Uberspect getUberspect() {
        return RuntimeSingleton.ri.getUberspect();
    }
    
    public static RuntimeInstance getRuntimeInstance() {
        return RuntimeSingleton.ri;
    }
    
    static {
        RuntimeSingleton.ri = new RuntimeInstance();
    }
}
