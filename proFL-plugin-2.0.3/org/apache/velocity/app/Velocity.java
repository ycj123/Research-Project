// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app;

import org.apache.velocity.runtime.log.Log;
import org.apache.velocity.Template;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.context.InternalContextAdapterImpl;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.parser.ParseException;
import java.io.UnsupportedEncodingException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.Writer;
import org.apache.velocity.context.Context;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;
import java.util.Properties;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.RuntimeConstants;

public class Velocity implements RuntimeConstants
{
    public static void init() throws Exception {
        RuntimeSingleton.init();
    }
    
    public static void init(final String propsFilename) throws Exception {
        RuntimeSingleton.init(propsFilename);
    }
    
    public static void init(final Properties p) throws Exception {
        RuntimeSingleton.init(p);
    }
    
    public static void setProperty(final String key, final Object value) {
        RuntimeSingleton.setProperty(key, value);
    }
    
    public static void addProperty(final String key, final Object value) {
        RuntimeSingleton.addProperty(key, value);
    }
    
    public static void clearProperty(final String key) {
        RuntimeSingleton.clearProperty(key);
    }
    
    public static void setExtendedProperties(final ExtendedProperties configuration) {
        RuntimeSingleton.setConfiguration(configuration);
    }
    
    public static Object getProperty(final String key) {
        return RuntimeSingleton.getProperty(key);
    }
    
    public static boolean evaluate(final Context context, final Writer out, final String logTag, final String instring) throws ParseErrorException, MethodInvocationException, ResourceNotFoundException, IOException {
        return evaluate(context, out, logTag, new BufferedReader(new StringReader(instring)));
    }
    
    public static boolean evaluate(final Context context, final Writer writer, final String logTag, final InputStream instream) throws ParseErrorException, MethodInvocationException, ResourceNotFoundException, IOException {
        BufferedReader br = null;
        String encoding = null;
        try {
            encoding = RuntimeSingleton.getString("input.encoding", "ISO-8859-1");
            br = new BufferedReader(new InputStreamReader(instream, encoding));
        }
        catch (UnsupportedEncodingException uce) {
            final String msg = "Unsupported input encoding : " + encoding + " for template " + logTag;
            throw new ParseErrorException(msg);
        }
        return evaluate(context, writer, logTag, br);
    }
    
    public static boolean evaluate(final Context context, final Writer writer, final String logTag, final Reader reader) throws ParseErrorException, MethodInvocationException, ResourceNotFoundException, IOException {
        SimpleNode nodeTree = null;
        try {
            nodeTree = RuntimeSingleton.parse(reader, logTag);
        }
        catch (ParseException pex) {
            throw new ParseErrorException(pex);
        }
        catch (TemplateInitException pex2) {
            throw new ParseErrorException(pex2);
        }
        if (nodeTree != null) {
            final InternalContextAdapterImpl ica = new InternalContextAdapterImpl(context);
            ica.pushCurrentTemplateName(logTag);
            try {
                try {
                    nodeTree.init(ica, RuntimeSingleton.getRuntimeServices());
                }
                catch (TemplateInitException pex3) {
                    throw new ParseErrorException(pex3);
                }
                catch (RuntimeException e) {
                    throw e;
                }
                catch (Exception e2) {
                    getLog().error("Velocity.evaluate() : init exception for tag = " + logTag, e2);
                }
                nodeTree.render(ica, writer);
            }
            finally {
                ica.popCurrentTemplateName();
            }
            return true;
        }
        return false;
    }
    
    public static boolean invokeVelocimacro(final String vmName, final String logTag, final String[] params, final Context context, final Writer writer) {
        if (vmName == null || params == null || context == null || writer == null || logTag == null) {
            getLog().error("Velocity.invokeVelocimacro() : invalid parameter");
            return false;
        }
        if (!RuntimeSingleton.isVelocimacro(vmName, logTag)) {
            getLog().error("Velocity.invokeVelocimacro() : VM '" + vmName + "' not registered.");
            return false;
        }
        final StringBuffer construct = new StringBuffer("#");
        construct.append(vmName);
        construct.append("(");
        for (int i = 0; i < params.length; ++i) {
            construct.append(" $");
            construct.append(params[i]);
        }
        construct.append(" )");
        try {
            return evaluate(context, writer, logTag, construct.toString());
        }
        catch (ParseErrorException pee) {
            throw pee;
        }
        catch (MethodInvocationException mie) {
            throw mie;
        }
        catch (ResourceNotFoundException rnfe) {
            throw rnfe;
        }
        catch (IOException ioe) {
            getLog().error("Velocity.invokeVelocimacro() failed", ioe);
        }
        catch (RuntimeException re) {
            throw re;
        }
        return false;
    }
    
    public static boolean mergeTemplate(final String templateName, final Context context, final Writer writer) throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, Exception {
        return mergeTemplate(templateName, RuntimeSingleton.getString("input.encoding", "ISO-8859-1"), context, writer);
    }
    
    public static boolean mergeTemplate(final String templateName, final String encoding, final Context context, final Writer writer) throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, Exception {
        final Template template = RuntimeSingleton.getTemplate(templateName, encoding);
        if (template == null) {
            getLog().error("Velocity.mergeTemplate() was unable to load template '" + templateName + "'");
            return false;
        }
        template.merge(context, writer);
        return true;
    }
    
    public static Template getTemplate(final String name) throws ResourceNotFoundException, ParseErrorException, Exception {
        return RuntimeSingleton.getTemplate(name);
    }
    
    public static Template getTemplate(final String name, final String encoding) throws ResourceNotFoundException, ParseErrorException, Exception {
        return RuntimeSingleton.getTemplate(name, encoding);
    }
    
    public static boolean resourceExists(final String resourceName) {
        return RuntimeSingleton.getLoaderNameForResource(resourceName) != null;
    }
    
    public static Log getLog() {
        return RuntimeSingleton.getLog();
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
    
    public static void setApplicationAttribute(final Object key, final Object value) {
        RuntimeSingleton.getRuntimeInstance().setApplicationAttribute(key, value);
    }
    
    public static boolean templateExists(final String resourceName) {
        return resourceExists(resourceName);
    }
}
