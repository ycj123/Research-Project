// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.usability;

import java.io.IOException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.usability.diagnostics.DiagnosisUtils;
import org.apache.maven.artifact.manager.WagonManager;
import org.apache.maven.usability.diagnostics.ErrorDiagnoser;

public class ArtifactResolverDiagnoser implements ErrorDiagnoser
{
    private WagonManager wagonManager;
    
    public boolean canDiagnose(final Throwable error) {
        return DiagnosisUtils.containsInCausality(error, ArtifactResolutionException.class);
    }
    
    public String diagnose(final Throwable error) {
        final ArtifactResolutionException exception = (ArtifactResolutionException)DiagnosisUtils.getFromCausality(error, ArtifactResolutionException.class);
        final StringBuffer message = new StringBuffer();
        message.append("Failed to resolve artifact.");
        message.append("\n\n");
        message.append(exception.getMessage());
        final IOException ioe = (IOException)DiagnosisUtils.getFromCausality(exception, IOException.class);
        if (ioe != null && exception.getMessage().indexOf(ioe.getMessage()) < 0) {
            message.append("\n\nCaused by I/O exception: ").append(ioe.getMessage());
        }
        if (!this.wagonManager.isOnline()) {
            message.append("\n").append(SystemWarnings.getOfflineWarning());
        }
        message.append("\n");
        return message.toString();
    }
}
