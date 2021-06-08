// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.usability;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.usability.diagnostics.DiagnosisUtils;
import org.apache.maven.usability.diagnostics.ErrorDiagnoser;

public class MojoExecutionExceptionDiagnoser implements ErrorDiagnoser
{
    public boolean canDiagnose(final Throwable error) {
        return DiagnosisUtils.containsInCausality(error, MojoExecutionException.class);
    }
    
    public String diagnose(final Throwable error) {
        final MojoExecutionException mee = (MojoExecutionException)DiagnosisUtils.getFromCausality(error, MojoExecutionException.class);
        final StringBuffer message = new StringBuffer();
        final Object source = mee.getSource();
        if (source != null) {
            message.append(": ").append(mee.getSource()).append("\n");
        }
        message.append(mee.getMessage());
        final String longMessage = mee.getLongMessage();
        if (longMessage != null) {
            message.append("\n\n").append(longMessage);
        }
        final Throwable directCause = mee.getCause();
        if (directCause != null) {
            message.append("\n");
            final String directCauseMessage = directCause.getMessage();
            final String meeMessage = mee.getMessage();
            if (directCauseMessage != null && meeMessage != null && meeMessage.indexOf(directCauseMessage) < 0) {
                message.append("\nEmbedded error: ").append(directCauseMessage);
            }
            DiagnosisUtils.appendRootCauseIfPresentAndUnique(directCause, message, false);
        }
        return message.toString();
    }
}
