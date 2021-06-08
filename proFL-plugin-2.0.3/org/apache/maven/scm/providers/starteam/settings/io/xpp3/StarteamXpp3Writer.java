// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.providers.starteam.settings.io.xpp3;

import java.io.OutputStream;
import java.io.IOException;
import org.codehaus.plexus.util.xml.pull.XmlSerializer;
import org.codehaus.plexus.util.xml.pull.MXSerializer;
import org.apache.maven.scm.providers.starteam.settings.Settings;
import java.io.Writer;

public class StarteamXpp3Writer
{
    private static final String NAMESPACE;
    
    public void write(final Writer writer, final Settings settings) throws IOException {
        final XmlSerializer serializer = new MXSerializer();
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "  ");
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", "\n");
        serializer.setOutput(writer);
        serializer.startDocument(settings.getModelEncoding(), null);
        this.writeSettings(settings, "starteam-settings", serializer);
        serializer.endDocument();
    }
    
    public void write(final OutputStream stream, final Settings settings) throws IOException {
        final XmlSerializer serializer = new MXSerializer();
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "  ");
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", "\n");
        serializer.setOutput(stream, settings.getModelEncoding());
        serializer.startDocument(settings.getModelEncoding(), null);
        this.writeSettings(settings, "starteam-settings", serializer);
        serializer.endDocument();
    }
    
    private void writeSettings(final Settings settings, final String tagName, final XmlSerializer serializer) throws IOException {
        serializer.setPrefix("", "http://maven.apache.org/SCM/STARTEAM/1.0.0");
        serializer.setPrefix("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        serializer.startTag(StarteamXpp3Writer.NAMESPACE, tagName);
        serializer.attribute("", "xsi:schemaLocation", "http://maven.apache.org/SCM/STARTEAM/1.0.0 http://maven.apache.org/xsd/scm-starteam-1.0.0.xsd");
        if (settings.isCompressionEnable()) {
            serializer.startTag(StarteamXpp3Writer.NAMESPACE, "compressionEnable").text(String.valueOf(settings.isCompressionEnable())).endTag(StarteamXpp3Writer.NAMESPACE, "compressionEnable");
        }
        if (settings.getEol() != null && !settings.getEol().equals("on")) {
            serializer.startTag(StarteamXpp3Writer.NAMESPACE, "eol").text(settings.getEol()).endTag(StarteamXpp3Writer.NAMESPACE, "eol");
        }
        serializer.endTag(StarteamXpp3Writer.NAMESPACE, tagName);
    }
    
    static {
        NAMESPACE = null;
    }
}
