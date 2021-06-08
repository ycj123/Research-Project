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
import org.apache.velocity.runtime.RuntimeInstance;
import org.apache.velocity.runtime.RuntimeConstants;

public class VelocityEngine implements RuntimeConstants
{
    private RuntimeInstance ri;
    
    public VelocityEngine() {
        this.ri = new RuntimeInstance();
    }
    
    public VelocityEngine(final String propsFilename) throws Exception {
        (this.ri = new RuntimeInstance()).init(propsFilename);
    }
    
    public VelocityEngine(final Properties p) throws Exception {
        (this.ri = new RuntimeInstance()).init(p);
    }
    
    public void init() throws Exception {
        this.ri.init();
    }
    
    public void init(final String propsFilename) throws Exception {
        this.ri.init(propsFilename);
    }
    
    public void init(final Properties p) throws Exception {
        this.ri.init(p);
    }
    
    public void setProperty(final String key, final Object value) {
        this.ri.setProperty(key, value);
    }
    
    public void addProperty(final String key, final Object value) {
        this.ri.addProperty(key, value);
    }
    
    public void clearProperty(final String key) {
        this.ri.clearProperty(key);
    }
    
    public void setExtendedProperties(final ExtendedProperties configuration) {
        this.ri.setConfiguration(configuration);
    }
    
    public Object getProperty(final String key) {
        return this.ri.getProperty(key);
    }
    
    public boolean evaluate(final Context context, final Writer out, final String logTag, final String instring) throws ParseErrorException, MethodInvocationException, ResourceNotFoundException, IOException {
        return this.evaluate(context, out, logTag, new BufferedReader(new StringReader(instring)));
    }
    
    public boolean evaluate(final Context context, final Writer writer, final String logTag, final InputStream instream) throws ParseErrorException, MethodInvocationException, ResourceNotFoundException, IOException {
        BufferedReader br = null;
        String encoding = null;
        try {
            encoding = this.ri.getString("input.encoding", "ISO-8859-1");
            br = new BufferedReader(new InputStreamReader(instream, encoding));
        }
        catch (UnsupportedEncodingException uce) {
            final String msg = "Unsupported input encoding : " + encoding + " for template " + logTag;
            throw new ParseErrorException(msg);
        }
        return this.evaluate(context, writer, logTag, br);
    }
    
    public boolean evaluate(final Context context, final Writer writer, final String logTag, final Reader reader) throws ParseErrorException, MethodInvocationException, ResourceNotFoundException, IOException {
        SimpleNode nodeTree = null;
        try {
            nodeTree = this.ri.parse(reader, logTag);
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
                    nodeTree.init(ica, this.ri);
                }
                catch (TemplateInitException pex3) {
                    throw new ParseErrorException(pex3);
                }
                catch (RuntimeException e) {
                    throw e;
                }
                catch (Exception e2) {
                    this.getLog().error("Velocity.evaluate() : init exception for tag = " + logTag, e2);
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
    
    public boolean invokeVelocimacro(final String vmName, final String logTag, final String[] params, final Context context, final Writer writer) throws Exception {
        if (vmName == null || params == null || context == null || writer == null || logTag == null) {
            this.getLog().error("VelocityEngine.invokeVelocimacro() : invalid parameter");
            return false;
        }
        if (!this.ri.isVelocimacro(vmName, logTag)) {
            this.getLog().error("VelocityEngine.invokeVelocimacro() : VM '" + vmName + "' not registered.");
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
            final boolean retval = this.evaluate(context, writer, logTag, construct.toString());
            return retval;
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            this.getLog().error("VelocityEngine.invokeVelocimacro() : error ", e2);
            throw e2;
        }
    }
    
    public boolean mergeTemplate(final String templateName, final Context context, final Writer writer) throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, Exception {
        return this.mergeTemplate(templateName, this.ri.getString("input.encoding", "ISO-8859-1"), context, writer);
    }
    
    public boolean mergeTemplate(final String templateName, final String encoding, final Context context, final Writer writer) throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, Exception {
        final Template template = this.ri.getTemplate(templateName, encoding);
        if (template == null) {
            this.getLog().error("Velocity.mergeTemplate() was unable to load template '" + templateName + "'");
            return false;
        }
        template.merge(context, writer);
        return true;
    }
    
    public Template getTemplate(final String name) throws ResourceNotFoundException, ParseErrorException, Exception {
        return this.ri.getTemplate(name);
    }
    
    public Template getTemplate(final String name, final String encoding) throws ResourceNotFoundException, ParseErrorException, Exception {
        return this.ri.getTemplate(name, encoding);
    }
    
    public boolean resourceExists(final String resourceName) {
        return this.ri.getLoaderNameForResource(resourceName) != null;
    }
    
    public boolean templateExists(final String resourceName) {
        return this.resourceExists(resourceName);
    }
    
    public Log getLog() {
        return this.ri.getLog();
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
    
    public void setApplicationAttribute(final Object key, final Object value) {
        this.ri.setApplicationAttribute(key, value);
    }
    
    public Object getApplicationAttribute(final Object key) {
        return this.ri.getApplicationAttribute(key);
    }
}
