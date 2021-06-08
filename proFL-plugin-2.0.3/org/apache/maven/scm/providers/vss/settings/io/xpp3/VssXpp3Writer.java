// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.providers.vss.settings.io.xpp3;

import java.io.IOException;
import org.codehaus.plexus.util.xml.pull.XmlSerializer;
import org.codehaus.plexus.util.xml.pull.MXSerializer;
import org.apache.maven.scm.providers.vss.settings.Settings;
import java.io.Writer;

public class VssXpp3Writer
{
    private String NAMESPACE;
    
    public void write(final Writer writer, final Settings settings) throws IOException {
        final XmlSerializer serializer = new MXSerializer();
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "  ");
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", "\n");
        serializer.setOutput(writer);
        serializer.startDocument(settings.getModelEncoding(), null);
        this.writeSettings(settings, "vss-settings", serializer);
        serializer.endDocument();
    }
    
    private void writeSettings(final Settings settings, final String tagName, final XmlSerializer serializer) throws IOException {
        if (settings != null) {
            serializer.startTag(this.NAMESPACE, tagName);
            if (settings.getVssDirectory() != null) {
                serializer.startTag(this.NAMESPACE, "vssDirectory").text(settings.getVssDirectory()).endTag(this.NAMESPACE, "vssDirectory");
            }
            serializer.endTag(this.NAMESPACE, tagName);
        }
    }
}
