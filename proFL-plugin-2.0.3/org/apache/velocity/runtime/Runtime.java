// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime;

import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.resource.ContentResource;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.Template;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import java.io.Reader;
import java.util.Properties;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;

public class Runtime implements RuntimeConstants
{
    public static synchronized void init() throws Exception {
        RuntimeSingleton.init();
    }
    
    public static void setProperty(final String key, final Object value) {
        RuntimeSingleton.setProperty(key, value);
    }
    
    public static void setConfiguration(final ExtendedProperties configuration) {
        RuntimeSingleton.setConfiguration(configuration);
    }
    
    public static void addProperty(final String key, final Object value) {
        RuntimeSingleton.addProperty(key, value);
    }
    
    public static void clearProperty(final String key) {
        RuntimeSingleton.clearProperty(key);
    }
    
    public static Object getProperty(final String key) {
        return RuntimeSingleton.getProperty(key);
    }
    
    public static void init(final Properties p) throws Exception {
        RuntimeSingleton.init(p);
    }
    
    public static void init(final String configurationFile) throws Exception {
        RuntimeSingleton.init(configurationFile);
    }
    
    public static SimpleNode parse(final Reader reader, final String templateName) throws ParseException {
        return RuntimeSingleton.parse(reader, templateName);
    }
    
    public static SimpleNode parse(final Reader reader, final String templateName, final boolean dumpNamespace) throws ParseException {
        return RuntimeSingleton.parse(reader, templateName, dumpNamespace);
    }
    
    public static Template getTemplate(final String name) throws ResourceNotFoundException, ParseErrorException, Exception {
        return RuntimeSingleton.getTemplate(name);
    }
    
    public static Template getTemplate(final String name, final String encoding) throws ResourceNotFoundException, ParseErrorException, Exception {
        return RuntimeSingleton.getTemplate(name, encoding);
    }
    
    public static ContentResource getContent(final String name) throws ResourceNotFoundException, ParseErrorException, Exception {
        return RuntimeSingleton.getContent(name);
    }
    
    public static ContentResource getContent(final String name, final String encoding) throws ResourceNotFoundException, ParseErrorException, Exception {
        return RuntimeSingleton.getContent(name, encoding);
    }
    
    public static String getLoaderNameForResource(final String resourceName) {
        return RuntimeSingleton.getLoaderNameForResource(resourceName);
    }
    
    public static void warn(final Object message) {
        RuntimeSingleton.warn(message);
    }
    
    public static void info(final Object message) {
        RuntimeSingleton.info(message);
    }
    
    public static void error(final Object message) {
        RuntimeSingleton.error(message);
    }
    
    public static void debug(final Object message) {
        RuntimeSingleton.debug(message);
    }
    
    public static String getString(final String key, final String defaultValue) {
        return RuntimeSingleton.getString(key, defaultValue);
    }
    
    public static Directive getVelocimacro(final String vmName, final String templateName) {
        return RuntimeSingleton.getVelocimacro(vmName, templateName);
    }
    
    public static boolean addVelocimacro(final String name, final String macro, final String[] argArray, final String sourceTemplate) {
        return RuntimeSingleton.addVelocimacro(name, macro, argArray, sourceTemplate);
    }
    
    public static boolean isVelocimacro(final String vmName, final String templateName) {
        return RuntimeSingleton.isVelocimacro(vmName, templateName);
    }
    
    public static boolean dumpVMNamespace(final String namespace) {
        return RuntimeSingleton.dumpVMNamespace(namespace);
    }
    
    public static String getString(final String key) {
        return RuntimeSingleton.getString(key);
    }
    
    public static int getInt(final String key) {
        return RuntimeSingleton.getInt(key);
    }
    
    public static int getInt(final String key, final int defaultValue) {
        return RuntimeSingleton.getInt(key, defaultValue);
    }
    
    public static boolean getBoolean(final String key, final boolean def) {
        return RuntimeSingleton.getBoolean(key, def);
    }
    
    public static ExtendedProperties getConfiguration() {
        return RuntimeSingleton.getConfiguration();
    }
}
