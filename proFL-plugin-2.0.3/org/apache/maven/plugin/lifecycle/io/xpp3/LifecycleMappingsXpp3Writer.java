// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.lifecycle.io.xpp3;

import org.apache.maven.plugin.lifecycle.Phase;
import org.apache.maven.plugin.lifecycle.Lifecycle;
import java.util.Iterator;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.apache.maven.plugin.lifecycle.Execution;
import java.io.IOException;
import org.codehaus.plexus.util.xml.pull.MXSerializer;
import org.apache.maven.plugin.lifecycle.LifecycleConfiguration;
import java.io.Writer;
import org.codehaus.plexus.util.xml.pull.XmlSerializer;

public class LifecycleMappingsXpp3Writer
{
    private XmlSerializer serializer;
    private String NAMESPACE;
    
    public void write(final Writer writer, final LifecycleConfiguration lifecycles) throws IOException {
        (this.serializer = new MXSerializer()).setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "  ");
        this.serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", "\n");
        this.serializer.setOutput(writer);
        this.serializer.startDocument(lifecycles.getModelEncoding(), null);
        this.writeLifecycleConfiguration(lifecycles, "lifecycles", this.serializer);
        this.serializer.endDocument();
    }
    
    private void writeExecution(final Execution execution, final String tagName, final XmlSerializer serializer) throws IOException {
        if (execution != null) {
            serializer.startTag(this.NAMESPACE, tagName);
            if (execution.getConfiguration() != null) {
                ((Xpp3Dom)execution.getConfiguration()).writeToSerializer(this.NAMESPACE, serializer);
            }
            if (execution.getGoals() != null && execution.getGoals().size() > 0) {
                serializer.startTag(this.NAMESPACE, "goals");
                for (final String goal : execution.getGoals()) {
                    serializer.startTag(this.NAMESPACE, "goal").text(goal).endTag(this.NAMESPACE, "goal");
                }
                serializer.endTag(this.NAMESPACE, "goals");
            }
            serializer.endTag(this.NAMESPACE, tagName);
        }
    }
    
    private void writeLifecycle(final Lifecycle lifecycle, final String tagName, final XmlSerializer serializer) throws IOException {
        if (lifecycle != null) {
            serializer.startTag(this.NAMESPACE, tagName);
            if (lifecycle.getId() != null) {
                serializer.startTag(this.NAMESPACE, "id").text(lifecycle.getId()).endTag(this.NAMESPACE, "id");
            }
            if (lifecycle.getPhases() != null && lifecycle.getPhases().size() > 0) {
                serializer.startTag(this.NAMESPACE, "phases");
                for (final Phase o : lifecycle.getPhases()) {
                    this.writePhase(o, "phase", serializer);
                }
                serializer.endTag(this.NAMESPACE, "phases");
            }
            serializer.endTag(this.NAMESPACE, tagName);
        }
    }
    
    private void writeLifecycleConfiguration(final LifecycleConfiguration lifecycleConfiguration, final String tagName, final XmlSerializer serializer) throws IOException {
        if (lifecycleConfiguration != null) {
            serializer.startTag(this.NAMESPACE, tagName);
            if (lifecycleConfiguration.getLifecycles() != null && lifecycleConfiguration.getLifecycles().size() > 0) {
                for (final Lifecycle o : lifecycleConfiguration.getLifecycles()) {
                    this.writeLifecycle(o, "lifecycle", serializer);
                }
            }
            serializer.endTag(this.NAMESPACE, tagName);
        }
    }
    
    private void writePhase(final Phase phase, final String tagName, final XmlSerializer serializer) throws IOException {
        if (phase != null) {
            serializer.startTag(this.NAMESPACE, tagName);
            if (phase.getId() != null) {
                serializer.startTag(this.NAMESPACE, "id").text(phase.getId()).endTag(this.NAMESPACE, "id");
            }
            if (phase.getExecutions() != null && phase.getExecutions().size() > 0) {
                serializer.startTag(this.NAMESPACE, "executions");
                for (final Execution o : phase.getExecutions()) {
                    this.writeExecution(o, "execution", serializer);
                }
                serializer.endTag(this.NAMESPACE, "executions");
            }
            if (phase.getConfiguration() != null) {
                ((Xpp3Dom)phase.getConfiguration()).writeToSerializer(this.NAMESPACE, serializer);
            }
            serializer.endTag(this.NAMESPACE, tagName);
        }
    }
}
