// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin;

import java.util.Arrays;
import org.codehaus.plexus.configuration.PlexusConfiguration;
import org.codehaus.plexus.component.configurator.ComponentConfigurationException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Map;
import java.util.regex.Matcher;
import org.apache.maven.usability.plugin.Expression;
import org.apache.maven.usability.plugin.ExpressionDocumentationException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.maven.usability.plugin.ExpressionDocumenter;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.List;
import org.apache.maven.plugin.descriptor.PluginDescriptor;

public class PluginConfigurationException extends Exception
{
    private final PluginDescriptor pluginDescriptor;
    private String originalMessage;
    private static final List UNMODIFIABLE_EXPRESSIONS;
    
    public PluginConfigurationException(final PluginDescriptor pluginDescriptor, final String message) {
        super("Error configuring: " + pluginDescriptor.getPluginLookupKey() + ". Reason: " + message);
        this.pluginDescriptor = pluginDescriptor;
        this.originalMessage = message;
    }
    
    public PluginConfigurationException(final PluginDescriptor pluginDescriptor, final Throwable cause) {
        super("Error configuring: " + pluginDescriptor.getPluginLookupKey() + ".", cause);
        this.pluginDescriptor = pluginDescriptor;
    }
    
    public PluginConfigurationException(final PluginDescriptor pluginDescriptor, final String message, final Throwable cause) {
        super("Error configuring: " + pluginDescriptor.getPluginLookupKey() + ". Reason: " + message, cause);
        this.pluginDescriptor = pluginDescriptor;
        this.originalMessage = message;
    }
    
    public PluginDescriptor getPluginDescriptor() {
        return this.pluginDescriptor;
    }
    
    public String getOriginalMessage() {
        return this.originalMessage;
    }
    
    protected static void addParameterUsageInfo(final String expression, final StringBuffer messageBuffer) {
        final StringBuffer expressionMessageBuffer = new StringBuffer();
        final Matcher exprMatcher = Pattern.compile("\\$\\{(.+)\\}").matcher(expression);
        boolean unmodifiableElementsFound = false;
        boolean activeElementsFound = false;
        int elementCount = 0;
        while (exprMatcher.find()) {
            ++elementCount;
            activeElementsFound = true;
            final String subExpression = exprMatcher.group(1);
            final StringTokenizer expressionParts = new StringTokenizer(subExpression, ".");
            final String firstPart = expressionParts.nextToken();
            Map expressions = null;
            try {
                expressions = ExpressionDocumenter.load();
            }
            catch (ExpressionDocumentationException e) {
                expressionMessageBuffer.append("\n\nERROR!! Failed to load expression documentation!");
                final StringWriter sWriter = new StringWriter();
                final PrintWriter pWriter = new PrintWriter(sWriter);
                e.printStackTrace(pWriter);
                expressionMessageBuffer.append("\n\nException:\n\n").append(sWriter.toString());
            }
            if (expressions != null) {
                final Expression expr = expressions.get(subExpression);
                if (expr != null) {
                    if (!expr.isEditable()) {
                        unmodifiableElementsFound = true;
                    }
                    else {
                        addParameterConfigDocumentation(firstPart, exprMatcher.group(0), subExpression, expressionMessageBuffer, expressions);
                    }
                }
                else if (PluginConfigurationException.UNMODIFIABLE_EXPRESSIONS.contains(subExpression)) {
                    unmodifiableElementsFound = true;
                }
                else {
                    expressionMessageBuffer.append("on the command line, specify: '-D").append(subExpression).append("=VALUE'");
                }
            }
        }
        if (activeElementsFound) {
            messageBuffer.append(expressionMessageBuffer);
        }
        else {
            messageBuffer.append("    (found static expression: '" + expression + "' which may act as a default value).\n");
        }
        if (unmodifiableElementsFound) {
            if (elementCount > 1) {
                messageBuffer.append("    ");
            }
            messageBuffer.append("NOTE: One or more purely derived expression elements were detected in '" + expression + "'.\n    If you continue to get this error after any other expression elements are specified correctly," + "\n    please report this issue to the Maven development team.\n");
        }
    }
    
    private static void addParameterConfigDocumentation(final String firstPart, final String wholeExpression, final String subExpression, final StringBuffer expressionMessageBuffer, final Map expressionDoco) {
        final Expression expr = expressionDoco.get(subExpression);
        if (expr != null) {
            expressionMessageBuffer.append("check that the following section of ");
            if ("project".equals(firstPart)) {
                expressionMessageBuffer.append("the pom.xml ");
            }
            else if ("settings".equals(firstPart)) {
                expressionMessageBuffer.append("your ~/.m2/settings.xml file ");
            }
            expressionMessageBuffer.append("is present and correct:\n\n");
            String message = expr.getConfiguration();
            if (message == null) {
                message = expr.getDescription();
            }
            expressionMessageBuffer.append(message);
            final Properties cliConfig = expr.getCliOptions();
            if (cliConfig != null && !cliConfig.isEmpty()) {
                expressionMessageBuffer.append("\n\n-OR-\n\nUse the following command-line switches:\n");
                prettyPrintCommandLineSwitches(cliConfig, '.', expressionMessageBuffer);
            }
        }
        else {
            expressionMessageBuffer.append("ensure that the expression: '" + wholeExpression + "' is satisfied");
        }
    }
    
    private static void prettyPrintCommandLineSwitches(final Properties switches, final char filler, final StringBuffer expressionMessageBuffer) {
        int maxKeyLen = 0;
        for (final Map.Entry entry : switches.entrySet()) {
            final String key = entry.getKey();
            final int keyLen = key.length();
            if (keyLen > maxKeyLen) {
                maxKeyLen = keyLen;
            }
        }
        final int minFillerCount = 4;
        for (final Map.Entry entry2 : switches.entrySet()) {
            final String key2 = entry2.getKey();
            final int keyLen2 = key2.length();
            final int fillerCount = maxKeyLen - keyLen2 + 4;
            expressionMessageBuffer.append('\n').append(key2).append(' ');
            for (int i = 0; i < fillerCount; ++i) {
                expressionMessageBuffer.append(filler);
            }
            expressionMessageBuffer.append(' ').append(entry2.getValue());
        }
        expressionMessageBuffer.append('\n');
    }
    
    public String buildConfigurationDiagnosticMessage(final ComponentConfigurationException cce) {
        final StringBuffer message = new StringBuffer();
        final PluginDescriptor descriptor = this.getPluginDescriptor();
        final PlexusConfiguration failedConfiguration = cce.getFailedConfiguration();
        message.append("Failed to configure plugin parameters for: " + descriptor.getId() + "\n\n");
        if (failedConfiguration != null) {
            final String value = failedConfiguration.getValue(null);
            if (value != null) {
                addParameterUsageInfo(value, message);
            }
        }
        message.append("\n\nCause: ").append(cce.getMessage());
        return message.toString();
    }
    
    static {
        UNMODIFIABLE_EXPRESSIONS = Arrays.asList("localRepository", "reactorProjects", "settings", "project", "session", "plugin", "basedir");
    }
}
