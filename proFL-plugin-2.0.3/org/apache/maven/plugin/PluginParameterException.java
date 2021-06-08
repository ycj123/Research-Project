// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin;

import java.util.Iterator;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.plugin.descriptor.Parameter;
import org.apache.maven.plugin.descriptor.MojoDescriptor;
import java.util.List;

public class PluginParameterException extends PluginConfigurationException
{
    private final List parameters;
    private final MojoDescriptor mojo;
    
    public PluginParameterException(final MojoDescriptor mojo, final List parameters) {
        super(mojo.getPluginDescriptor(), "Invalid or missing parameters: " + parameters + " for mojo: " + mojo.getRoleHint());
        this.mojo = mojo;
        this.parameters = parameters;
    }
    
    public PluginParameterException(final MojoDescriptor mojo, final List parameters, final Throwable cause) {
        super(mojo.getPluginDescriptor(), "Invalid or missing parameters: " + parameters + " for mojo: " + mojo.getRoleHint(), cause);
        this.mojo = mojo;
        this.parameters = parameters;
    }
    
    public MojoDescriptor getMojoDescriptor() {
        return this.mojo;
    }
    
    public List getParameters() {
        return this.parameters;
    }
    
    private static void decomposeParameterIntoUserInstructions(final MojoDescriptor mojo, final Parameter param, final StringBuffer messageBuffer) {
        final String expression = param.getExpression();
        if (param.isEditable()) {
            messageBuffer.append("Inside the definition for plugin '" + mojo.getPluginDescriptor().getArtifactId() + "' specify the following:\n\n<configuration>\n  ...\n  <" + param.getName() + ">VALUE</" + param.getName() + ">\n</configuration>");
            final String alias = param.getAlias();
            if (StringUtils.isNotEmpty(alias) && !alias.equals(param.getName())) {
                messageBuffer.append("\n\n-OR-\n\n<configuration>\n  ...\n  <" + alias + ">VALUE</" + alias + ">\n</configuration>\n");
            }
        }
        if (StringUtils.isEmpty(expression)) {
            messageBuffer.append(".");
        }
        else {
            if (param.isEditable()) {
                messageBuffer.append("\n\n-OR-\n\n");
            }
            PluginConfigurationException.addParameterUsageInfo(expression, messageBuffer);
        }
    }
    
    public String buildDiagnosticMessage() {
        final StringBuffer messageBuffer = new StringBuffer();
        final List params = this.getParameters();
        final MojoDescriptor mojo = this.getMojoDescriptor();
        messageBuffer.append("One or more required plugin parameters are invalid/missing for '").append(mojo.getPluginDescriptor().getGoalPrefix()).append(":").append(mojo.getGoal()).append("'\n");
        int idx = 0;
        for (final Parameter param : params) {
            messageBuffer.append("\n[").append(idx).append("] ");
            decomposeParameterIntoUserInstructions(mojo, param, messageBuffer);
            messageBuffer.append("\n");
            ++idx;
        }
        return messageBuffer.toString();
    }
}
