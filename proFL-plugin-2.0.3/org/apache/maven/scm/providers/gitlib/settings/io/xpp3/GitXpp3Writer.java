// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.providers.gitlib.settings.io.xpp3;

import java.io.OutputStream;
import java.io.IOException;
import org.codehaus.plexus.util.xml.pull.XmlSerializer;
import org.codehaus.plexus.util.xml.pull.MXSerializer;
import org.apache.maven.scm.providers.gitlib.settings.Settings;
import java.io.Writer;

public class GitXpp3Writer
{
    private static final String NAMESPACE;
    
    public void write(final Writer writer, final Settings settings) throws IOException {
        final XmlSerializer serializer = new MXSerializer();
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "  ");
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", "\n");
        serializer.setOutput(writer);
        serializer.startDocument(settings.getModelEncoding(), null);
        this.writeSettings(settings, "git-settings", serializer);
        serializer.endDocument();
    }
    
    public void write(final OutputStream stream, final Settings settings) throws IOException {
        final XmlSerializer serializer = new MXSerializer();
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "  ");
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", "\n");
        serializer.setOutput(stream, settings.getModelEncoding());
        serializer.startDocument(settings.getModelEncoding(), null);
        this.writeSettings(settings, "git-settings", serializer);
        serializer.endDocument();
    }
    
    private void writeSettings(final Settings settings, final String tagName, final XmlSerializer serializer) throws IOException {
        serializer.setPrefix("", "http://maven.apache.org/SCM/GIT/1.0.0");
        serializer.setPrefix("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        serializer.startTag(GitXpp3Writer.NAMESPACE, tagName);
        serializer.attribute("", "xsi:schemaLocation", "http://maven.apache.org/SCM/GIT/1.0.0 http://maven.apache.org/xsd/scm-git-1.0.0.xsd");
        if (settings.getRevParseDateFormat() != null && !settings.getRevParseDateFormat().equals("yyyy-MM-dd HH:mm:ss")) {
            serializer.startTag(GitXpp3Writer.NAMESPACE, "revParseDateFormat").text(settings.getRevParseDateFormat()).endTag(GitXpp3Writer.NAMESPACE, "revParseDateFormat");
        }
        if (settings.getTraceGitCommand() != null && !settings.getTraceGitCommand().equals("")) {
            serializer.startTag(GitXpp3Writer.NAMESPACE, "traceGitCommand").text(settings.getTraceGitCommand()).endTag(GitXpp3Writer.NAMESPACE, "traceGitCommand");
        }
        if (settings.isCommitNoVerify()) {
            serializer.startTag(GitXpp3Writer.NAMESPACE, "commitNoVerify").text(String.valueOf(settings.isCommitNoVerify())).endTag(GitXpp3Writer.NAMESPACE, "commitNoVerify");
        }
        serializer.endTag(GitXpp3Writer.NAMESPACE, tagName);
    }
    
    static {
        NAMESPACE = null;
    }
}
