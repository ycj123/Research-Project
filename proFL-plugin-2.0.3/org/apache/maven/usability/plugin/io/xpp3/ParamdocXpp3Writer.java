// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.usability.plugin.io.xpp3;

import java.util.Iterator;
import org.apache.maven.usability.plugin.Expression;
import java.io.IOException;
import org.codehaus.plexus.util.xml.pull.MXSerializer;
import org.apache.maven.usability.plugin.ExpressionDocumentation;
import java.io.Writer;
import org.codehaus.plexus.util.xml.pull.XmlSerializer;

public class ParamdocXpp3Writer
{
    private XmlSerializer serializer;
    private String NAMESPACE;
    
    public void write(final Writer writer, final ExpressionDocumentation paramdoc) throws IOException {
        (this.serializer = new MXSerializer()).setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "  ");
        this.serializer.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", "\n");
        this.serializer.setOutput(writer);
        this.serializer.startDocument(paramdoc.getModelEncoding(), null);
        this.writeExpressionDocumentation(paramdoc, "paramdoc", this.serializer);
        this.serializer.endDocument();
    }
    
    private void writeExpression(final Expression expression, final String tagName, final XmlSerializer serializer) throws IOException {
        if (expression != null) {
            serializer.startTag(this.NAMESPACE, tagName);
            if (expression.getSyntax() != null) {
                serializer.startTag(this.NAMESPACE, "syntax").text(expression.getSyntax()).endTag(this.NAMESPACE, "syntax");
            }
            if (expression.getDescription() != null) {
                serializer.startTag(this.NAMESPACE, "description").text(expression.getDescription()).endTag(this.NAMESPACE, "description");
            }
            if (expression.getConfiguration() != null) {
                serializer.startTag(this.NAMESPACE, "configuration").text(expression.getConfiguration()).endTag(this.NAMESPACE, "configuration");
            }
            if (expression.getCliOptions() != null && expression.getCliOptions().size() > 0) {
                serializer.startTag(this.NAMESPACE, "cliOptions");
                for (final String key : expression.getCliOptions().keySet()) {
                    final String value = (String)expression.getCliOptions().get(key);
                    serializer.startTag(this.NAMESPACE, "cliOption");
                    serializer.startTag(this.NAMESPACE, "key").text(key).endTag(this.NAMESPACE, "key");
                    serializer.startTag(this.NAMESPACE, "value").text(value).endTag(this.NAMESPACE, "value");
                    serializer.endTag(this.NAMESPACE, "cliOption");
                }
                serializer.endTag(this.NAMESPACE, "cliOptions");
            }
            if (expression.getApiMethods() != null && expression.getApiMethods().size() > 0) {
                serializer.startTag(this.NAMESPACE, "apiMethods");
                for (final String key : expression.getApiMethods().keySet()) {
                    final String value = (String)expression.getApiMethods().get(key);
                    serializer.startTag(this.NAMESPACE, "apiMethod");
                    serializer.startTag(this.NAMESPACE, "key").text(key).endTag(this.NAMESPACE, "key");
                    serializer.startTag(this.NAMESPACE, "value").text(value).endTag(this.NAMESPACE, "value");
                    serializer.endTag(this.NAMESPACE, "apiMethod");
                }
                serializer.endTag(this.NAMESPACE, "apiMethods");
            }
            if (expression.getDeprecation() != null) {
                serializer.startTag(this.NAMESPACE, "deprecation").text(expression.getDeprecation()).endTag(this.NAMESPACE, "deprecation");
            }
            if (expression.getBan() != null) {
                serializer.startTag(this.NAMESPACE, "ban").text(expression.getBan()).endTag(this.NAMESPACE, "ban");
            }
            if (!expression.isEditable()) {
                serializer.startTag(this.NAMESPACE, "editable").text(String.valueOf(expression.isEditable())).endTag(this.NAMESPACE, "editable");
            }
            serializer.endTag(this.NAMESPACE, tagName);
        }
    }
    
    private void writeExpressionDocumentation(final ExpressionDocumentation expressionDocumentation, final String tagName, final XmlSerializer serializer) throws IOException {
        if (expressionDocumentation != null) {
            serializer.startTag(this.NAMESPACE, tagName);
            if (expressionDocumentation.getExpressions() != null && expressionDocumentation.getExpressions().size() > 0) {
                serializer.startTag(this.NAMESPACE, "expressions");
                for (final Expression o : expressionDocumentation.getExpressions()) {
                    this.writeExpression(o, "expression", serializer);
                }
                serializer.endTag(this.NAMESPACE, "expressions");
            }
            serializer.endTag(this.NAMESPACE, tagName);
        }
    }
}
