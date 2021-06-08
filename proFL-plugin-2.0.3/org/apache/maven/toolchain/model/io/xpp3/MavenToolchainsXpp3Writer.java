// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.toolchain.model.io.xpp3;

import org.codehaus.plexus.util.xml.Xpp3Dom;
import java.util.Iterator;
import org.apache.maven.toolchain.model.ToolchainModel;
import java.io.IOException;
import org.codehaus.plexus.util.xml.pull.XmlSerializer;
import org.codehaus.plexus.util.xml.pull.MXSerializer;
import org.apache.maven.toolchain.model.PersistedToolchains;
import java.io.Writer;

public class MavenToolchainsXpp3Writer
{
    private static final String NAMESPACE;
    
    public void write(final Writer writer, final PersistedToolchains persistedToolchains) throws IOException {
        final XmlSerializer serializer = new MXSerializer();
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "  ");
        serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", "\n");
        serializer.setOutput(writer);
        serializer.startDocument(persistedToolchains.getModelEncoding(), null);
        this.writePersistedToolchains(persistedToolchains, "toolchains", serializer);
        serializer.endDocument();
    }
    
    private void writePersistedToolchains(final PersistedToolchains persistedToolchains, final String tagName, final XmlSerializer serializer) throws IOException {
        if (persistedToolchains != null) {
            serializer.setPrefix("", "http://maven.apache.org/TOOLCHAINS/1.0.0");
            serializer.setPrefix("xsi", "http://www.w3.org/2001/XMLSchema-instance");
            serializer.startTag(MavenToolchainsXpp3Writer.NAMESPACE, tagName);
            serializer.attribute("", "xsi:schemaLocation", "http://maven.apache.org/TOOLCHAINS/1.0.0 http://maven.apache.org/xsd/toolchains-1.0.0.xsd");
            if (persistedToolchains.getToolchains() != null && persistedToolchains.getToolchains().size() > 0) {
                for (final ToolchainModel o : persistedToolchains.getToolchains()) {
                    this.writeToolchainModel(o, "toolchain", serializer);
                }
            }
            serializer.endTag(MavenToolchainsXpp3Writer.NAMESPACE, tagName);
        }
    }
    
    private void writeToolchainModel(final ToolchainModel toolchainModel, final String tagName, final XmlSerializer serializer) throws IOException {
        if (toolchainModel != null) {
            serializer.startTag(MavenToolchainsXpp3Writer.NAMESPACE, tagName);
            if (toolchainModel.getType() != null) {
                serializer.startTag(MavenToolchainsXpp3Writer.NAMESPACE, "type").text(toolchainModel.getType()).endTag(MavenToolchainsXpp3Writer.NAMESPACE, "type");
            }
            if (toolchainModel.getProvides() != null) {
                ((Xpp3Dom)toolchainModel.getProvides()).writeToSerializer(MavenToolchainsXpp3Writer.NAMESPACE, serializer);
            }
            if (toolchainModel.getConfiguration() != null) {
                ((Xpp3Dom)toolchainModel.getConfiguration()).writeToSerializer(MavenToolchainsXpp3Writer.NAMESPACE, serializer);
            }
            serializer.endTag(MavenToolchainsXpp3Writer.NAMESPACE, tagName);
        }
    }
    
    static {
        NAMESPACE = null;
    }
}
