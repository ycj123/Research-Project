// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.usability;

import org.codehaus.plexus.component.configurator.ComponentConfigurationException;
import org.apache.maven.plugin.PluginParameterException;
import org.apache.maven.plugin.PluginConfigurationException;
import org.apache.maven.usability.diagnostics.DiagnosisUtils;
import org.apache.maven.usability.diagnostics.ErrorDiagnoser;

public class PluginConfigurationDiagnoser implements ErrorDiagnoser
{
    public boolean canDiagnose(final Throwable error) {
        return DiagnosisUtils.containsInCausality(error, PluginConfigurationException.class);
    }
    
    public String diagnose(final Throwable error) {
        final PluginConfigurationException pce = (PluginConfigurationException)DiagnosisUtils.getFromCausality(error, PluginConfigurationException.class);
        if (pce instanceof PluginParameterException) {
            final PluginParameterException exception = (PluginParameterException)pce;
            return exception.buildDiagnosticMessage();
        }
        if (DiagnosisUtils.containsInCausality(pce, ComponentConfigurationException.class)) {
            final ComponentConfigurationException cce = (ComponentConfigurationException)DiagnosisUtils.getFromCausality(pce, ComponentConfigurationException.class);
            return pce.buildConfigurationDiagnosticMessage(cce);
        }
        return pce.getMessage();
    }
}
