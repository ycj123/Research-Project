// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.providers.cvslib.settings.io.xpp3;

import java.util.Iterator;
import java.io.OutputStream;
import java.io.IOException;
import org.codehaus.plexus.util.xml.pull.XmlSerializer;
import org.codehaus.plexus.util.xml.pull.MXSerializer;
import org.apache.maven.scm.providers.cvslib.settings.Settings;
import java.io.Writer;

public class CvsXpp3Writer
{
    private static final String NAMESPACE;
    
    public void write(final Writer writer, final Settings settings) throws IOException {
        final XmlSerializer serializer = new MXSerializer();
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "  ");
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", "\n");
        serializer.setOutput(writer);
        serializer.startDocument(settings.getModelEncoding(), null);
        this.writeSettings(settings, "cvs-settings", serializer);
        serializer.endDocument();
    }
    
    public void write(final OutputStream stream, final Settings settings) throws IOException {
        final XmlSerializer serializer = new MXSerializer();
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "  ");
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", "\n");
        serializer.setOutput(stream, settings.getModelEncoding());
        serializer.startDocument(settings.getModelEncoding(), null);
        this.writeSettings(settings, "cvs-settings", serializer);
        serializer.endDocument();
    }
    
    private void writeSettings(final Settings settings, final String tagName, final XmlSerializer serializer) throws IOException {
        serializer.setPrefix("", "http://maven.apache.org/SCM/CVS/1.0.0");
        serializer.setPrefix("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        serializer.startTag(CvsXpp3Writer.NAMESPACE, tagName);
        serializer.attribute("", "xsi:schemaLocation", "http://maven.apache.org/SCM/CVS/1.0.0 http://maven.apache.org/xsd/scm-cvs-1.0.0.xsd");
        if (settings.getChangeLogCommandDateFormat() != null && !settings.getChangeLogCommandDateFormat().equals("yyyy-MM-dd HH:mm:ssZ")) {
            serializer.startTag(CvsXpp3Writer.NAMESPACE, "changeLogCommandDateFormat").text(settings.getChangeLogCommandDateFormat()).endTag(CvsXpp3Writer.NAMESPACE, "changeLogCommandDateFormat");
        }
        if (settings.isUseCvsrc()) {
            serializer.startTag(CvsXpp3Writer.NAMESPACE, "useCvsrc").text(String.valueOf(settings.isUseCvsrc())).endTag(CvsXpp3Writer.NAMESPACE, "useCvsrc");
        }
        if (settings.getCompressionLevel() != 3) {
            serializer.startTag(CvsXpp3Writer.NAMESPACE, "compressionLevel").text(String.valueOf(settings.getCompressionLevel())).endTag(CvsXpp3Writer.NAMESPACE, "compressionLevel");
        }
        if (settings.isTraceCvsCommand()) {
            serializer.startTag(CvsXpp3Writer.NAMESPACE, "traceCvsCommand").text(String.valueOf(settings.isTraceCvsCommand())).endTag(CvsXpp3Writer.NAMESPACE, "traceCvsCommand");
        }
        if (settings.getTemporaryFilesDirectory() != null) {
            serializer.startTag(CvsXpp3Writer.NAMESPACE, "temporaryFilesDirectory").text(settings.getTemporaryFilesDirectory()).endTag(CvsXpp3Writer.NAMESPACE, "temporaryFilesDirectory");
        }
        if (settings.getCvsVariables() != null && settings.getCvsVariables().size() > 0) {
            serializer.startTag(CvsXpp3Writer.NAMESPACE, "cvsVariables");
            for (final String key : settings.getCvsVariables().keySet()) {
                final String value = (String)settings.getCvsVariables().get(key);
                serializer.startTag(CvsXpp3Writer.NAMESPACE, "" + key + "").text(value).endTag(CvsXpp3Writer.NAMESPACE, "" + key + "");
            }
            serializer.endTag(CvsXpp3Writer.NAMESPACE, "cvsVariables");
        }
        if (!settings.isUseForceTag()) {
            serializer.startTag(CvsXpp3Writer.NAMESPACE, "useForceTag").text(String.valueOf(settings.isUseForceTag())).endTag(CvsXpp3Writer.NAMESPACE, "useForceTag");
        }
        serializer.endTag(CvsXpp3Writer.NAMESPACE, tagName);
    }
    
    static {
        NAMESPACE = null;
    }
}
