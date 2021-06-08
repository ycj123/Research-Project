// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.usability;

import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.usability.diagnostics.DiagnosisUtils;
import org.apache.maven.usability.diagnostics.ErrorDiagnoser;

public class MojoFailureExceptionDiagnoser implements ErrorDiagnoser
{
    public boolean canDiagnose(final Throwable error) {
        return DiagnosisUtils.containsInCausality(error, MojoFailureException.class);
    }
    
    public String diagnose(final Throwable error) {
        final MojoFailureException mfe = (MojoFailureException)DiagnosisUtils.getFromCausality(error, MojoFailureException.class);
        final StringBuffer message = new StringBuffer();
        final Object source = mfe.getSource();
        if (source != null) {
            message.append(": ").append(mfe.getSource()).append("\n");
        }
        message.append(mfe.getMessage());
        final String longMessage = mfe.getLongMessage();
        if (longMessage != null) {
            message.append("\n\n").append(longMessage);
        }
        return message.toString();
    }
}
