// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.usability;

import org.apache.maven.artifact.InvalidArtifactRTException;
import org.apache.maven.usability.diagnostics.ErrorDiagnoser;

public class InvalidArtifactDiagnoser implements ErrorDiagnoser
{
    public boolean canDiagnose(final Throwable error) {
        return error instanceof InvalidArtifactRTException;
    }
    
    public String diagnose(final Throwable error) {
        final StringBuffer diagnosis = new StringBuffer();
        final InvalidArtifactRTException e = (InvalidArtifactRTException)error;
        diagnosis.append("An invalid artifact was detected.\n\n").append("This artifact might be in your project's POM, ").append("or it might have been included transitively during the resolution process. ").append("Here is the information we do have for this artifact:\n").append("\n    o GroupID:     ").append(this.maybeFlag(e.getGroupId())).append("\n    o ArtifactID:  ").append(this.maybeFlag(e.getArtifactId())).append("\n    o Version:     ").append(this.maybeFlag(e.getVersion())).append("\n    o Type:        ").append(this.maybeFlag(e.getType())).append("\n");
        return diagnosis.toString();
    }
    
    private String maybeFlag(final String value) {
        if (value == null || value.trim().length() < 1) {
            return "<<< MISSING >>>";
        }
        return value;
    }
}
