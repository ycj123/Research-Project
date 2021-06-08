// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.providers.clearcase.settings.io.xpp3;

import java.io.OutputStream;
import java.io.IOException;
import org.codehaus.plexus.util.xml.pull.XmlSerializer;
import org.codehaus.plexus.util.xml.pull.MXSerializer;
import org.apache.maven.scm.providers.clearcase.settings.Settings;
import java.io.Writer;

public class ClearcaseXpp3Writer
{
    private static final String NAMESPACE;
    
    public void write(final Writer writer, final Settings settings) throws IOException {
        final XmlSerializer serializer = new MXSerializer();
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "  ");
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", "\n");
        serializer.setOutput(writer);
        serializer.startDocument(settings.getModelEncoding(), null);
        this.writeSettings(settings, "clearcase-settings", serializer);
        serializer.endDocument();
    }
    
    public void write(final OutputStream stream, final Settings settings) throws IOException {
        final XmlSerializer serializer = new MXSerializer();
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "  ");
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", "\n");
        serializer.setOutput(stream, settings.getModelEncoding());
        serializer.startDocument(settings.getModelEncoding(), null);
        this.writeSettings(settings, "clearcase-settings", serializer);
        serializer.endDocument();
    }
    
    private void writeSettings(final Settings settings, final String tagName, final XmlSerializer serializer) throws IOException {
        serializer.setPrefix("", "http://maven.apache.org/SCM/CLEARCASE/1.1.0");
        serializer.setPrefix("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        serializer.startTag(ClearcaseXpp3Writer.NAMESPACE, tagName);
        serializer.attribute("", "xsi:schemaLocation", "http://maven.apache.org/SCM/CLEARCASE/1.1.0 http://maven.apache.org/xsd/scm-clearcase-1.1.0.xsd");
        if (settings.getViewstore() != null) {
            serializer.startTag(ClearcaseXpp3Writer.NAMESPACE, "viewstore").text(settings.getViewstore()).endTag(ClearcaseXpp3Writer.NAMESPACE, "viewstore");
        }
        if (!settings.isUseVWSParameter()) {
            serializer.startTag(ClearcaseXpp3Writer.NAMESPACE, "useVWSParameter").text(String.valueOf(settings.isUseVWSParameter())).endTag(ClearcaseXpp3Writer.NAMESPACE, "useVWSParameter");
        }
        if (settings.getClearcaseType() != null) {
            serializer.startTag(ClearcaseXpp3Writer.NAMESPACE, "clearcaseType").text(settings.getClearcaseType()).endTag(ClearcaseXpp3Writer.NAMESPACE, "clearcaseType");
        }
        if (settings.getChangelogUserFormat() != null) {
            serializer.startTag(ClearcaseXpp3Writer.NAMESPACE, "changelogUserFormat").text(settings.getChangelogUserFormat()).endTag(ClearcaseXpp3Writer.NAMESPACE, "changelogUserFormat");
        }
        serializer.endTag(ClearcaseXpp3Writer.NAMESPACE, tagName);
    }
    
    static {
        NAMESPACE = null;
    }
}
