// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.usability;

import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.apache.maven.profiles.activation.ProfileActivationException;
import org.apache.maven.usability.diagnostics.DiagnosisUtils;
import org.apache.maven.usability.diagnostics.ErrorDiagnoser;

public class ProfileActivationDiagnoser implements ErrorDiagnoser
{
    public boolean canDiagnose(final Throwable error) {
        return DiagnosisUtils.containsInCausality(error, ProfileActivationException.class);
    }
    
    public String diagnose(final Throwable error) {
        final ProfileActivationException activationException = (ProfileActivationException)DiagnosisUtils.getFromCausality(error, ProfileActivationException.class);
        final StringBuffer messageBuffer = new StringBuffer();
        messageBuffer.append("Error activating profiles.");
        messageBuffer.append("\n\nReason: ").append(activationException.getMessage());
        if (DiagnosisUtils.containsInCausality(activationException, ComponentLookupException.class)) {
            final ComponentLookupException cle = (ComponentLookupException)DiagnosisUtils.getFromCausality(activationException, ComponentLookupException.class);
            messageBuffer.append("\n\nThere was a problem retrieving one or more profile activators.");
            messageBuffer.append("\n").append(cle.getMessage());
        }
        messageBuffer.append("\n");
        return messageBuffer.toString();
    }
}
