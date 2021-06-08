// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.providers.svn.settings.io.xpp3;

import java.io.OutputStream;
import java.io.IOException;
import org.codehaus.plexus.util.xml.pull.XmlSerializer;
import org.codehaus.plexus.util.xml.pull.MXSerializer;
import org.apache.maven.scm.providers.svn.settings.Settings;
import java.io.Writer;

public class SvnXpp3Writer
{
    private static final String NAMESPACE;
    
    public void write(final Writer writer, final Settings settings) throws IOException {
        final XmlSerializer serializer = new MXSerializer();
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "  ");
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", "\n");
        serializer.setOutput(writer);
        serializer.startDocument(settings.getModelEncoding(), null);
        this.writeSettings(settings, "svn-settings", serializer);
        serializer.endDocument();
    }
    
    public void write(final OutputStream stream, final Settings settings) throws IOException {
        final XmlSerializer serializer = new MXSerializer();
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "  ");
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", "\n");
        serializer.setOutput(stream, settings.getModelEncoding());
        serializer.startDocument(settings.getModelEncoding(), null);
        this.writeSettings(settings, "svn-settings", serializer);
        serializer.endDocument();
    }
    
    private void writeSettings(final Settings settings, final String tagName, final XmlSerializer serializer) throws IOException {
        serializer.setPrefix("", "http://maven.apache.org/SCM/SVN/1.1.0");
        serializer.setPrefix("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        serializer.startTag(SvnXpp3Writer.NAMESPACE, tagName);
        serializer.attribute("", "xsi:schemaLocation", "http://maven.apache.org/SCM/SVN/1.1.0 http://maven.apache.org/xsd/svn-settings-1.1.0.xsd");
        if (settings.getConfigDirectory() != null) {
            serializer.startTag(SvnXpp3Writer.NAMESPACE, "configDirectory").text(settings.getConfigDirectory()).endTag(SvnXpp3Writer.NAMESPACE, "configDirectory");
        }
        if (settings.isUseCygwinPath()) {
            serializer.startTag(SvnXpp3Writer.NAMESPACE, "useCygwinPath").text(String.valueOf(settings.isUseCygwinPath())).endTag(SvnXpp3Writer.NAMESPACE, "useCygwinPath");
        }
        if (settings.getCygwinMountPath() != null && !settings.getCygwinMountPath().equals("/cygwin")) {
            serializer.startTag(SvnXpp3Writer.NAMESPACE, "cygwinMountPath").text(settings.getCygwinMountPath()).endTag(SvnXpp3Writer.NAMESPACE, "cygwinMountPath");
        }
        if (!settings.isUseNonInteractive()) {
            serializer.startTag(SvnXpp3Writer.NAMESPACE, "useNonInteractive").text(String.valueOf(settings.isUseNonInteractive())).endTag(SvnXpp3Writer.NAMESPACE, "useNonInteractive");
        }
        if (settings.isUseAuthCache()) {
            serializer.startTag(SvnXpp3Writer.NAMESPACE, "useAuthCache").text(String.valueOf(settings.isUseAuthCache())).endTag(SvnXpp3Writer.NAMESPACE, "useAuthCache");
        }
        if (settings.isTrustServerCert()) {
            serializer.startTag(SvnXpp3Writer.NAMESPACE, "trustServerCert").text(String.valueOf(settings.isTrustServerCert())).endTag(SvnXpp3Writer.NAMESPACE, "trustServerCert");
        }
        serializer.endTag(SvnXpp3Writer.NAMESPACE, tagName);
    }
    
    static {
        NAMESPACE = null;
    }
}
