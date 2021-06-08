// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity;

import org.apache.velocity.exception.MethodInvocationException;
import java.io.Writer;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.context.Context;
import org.apache.velocity.context.InternalContextAdapterImpl;
import java.io.IOException;
import java.io.InputStream;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.runtime.parser.ParseException;
import java.io.UnsupportedEncodingException;
import org.apache.velocity.exception.ParseErrorException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.resource.Resource;

public class Template extends Resource
{
    private VelocityException errorCondition;
    
    public Template() {
        this.errorCondition = null;
    }
    
    public boolean process() throws ResourceNotFoundException, ParseErrorException, IOException {
        this.data = null;
        InputStream is = null;
        this.errorCondition = null;
        try {
            is = this.resourceLoader.getResourceStream(this.name);
        }
        catch (ResourceNotFoundException rnfe) {
            throw this.errorCondition = rnfe;
        }
        if (is != null) {
            try {
                final BufferedReader br = new BufferedReader(new InputStreamReader(is, this.encoding));
                this.data = this.rsvc.parse(br, this.name);
                this.initDocument();
                return true;
            }
            catch (UnsupportedEncodingException uce) {
                final String msg = "Template.process : Unsupported input encoding : " + this.encoding + " for template " + this.name;
                throw this.errorCondition = new ParseErrorException(msg);
            }
            catch (ParseException pex) {
                throw this.errorCondition = new ParseErrorException(pex);
            }
            catch (TemplateInitException pex2) {
                throw this.errorCondition = new ParseErrorException(pex2);
            }
            catch (RuntimeException e) {
                throw e;
            }
            finally {
                is.close();
            }
        }
        throw this.errorCondition = new ResourceNotFoundException("Unknown resource error for resource " + this.name);
    }
    
    public void initDocument() throws TemplateInitException {
        final InternalContextAdapterImpl ica = new InternalContextAdapterImpl(new VelocityContext());
        try {
            ica.pushCurrentTemplateName(this.name);
            ((SimpleNode)this.data).init(ica, this.rsvc);
        }
        finally {
            ica.popCurrentTemplateName();
        }
    }
    
    public void merge(final Context context, final Writer writer) throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, IOException {
        if (this.errorCondition != null) {
            throw this.errorCondition;
        }
        if (this.data != null) {
            final InternalContextAdapterImpl ica = new InternalContextAdapterImpl(context);
            try {
                ica.pushCurrentTemplateName(this.name);
                ica.setCurrentResource(this);
                ((SimpleNode)this.data).render(ica, writer);
            }
            finally {
                ica.popCurrentTemplateName();
                ica.setCurrentResource(null);
            }
            return;
        }
        final String msg = "Template.merge() failure. The document is null, most likely due to parsing error.";
        throw new RuntimeException(msg);
    }
}
