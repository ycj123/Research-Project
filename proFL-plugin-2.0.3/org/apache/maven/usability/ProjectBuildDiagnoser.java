// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.usability;

import org.apache.maven.project.validation.ModelValidationResult;
import org.apache.maven.project.InvalidProjectModelException;
import org.apache.maven.project.ProjectBuildingException;
import org.apache.maven.usability.diagnostics.DiagnosisUtils;
import org.apache.maven.usability.diagnostics.ErrorDiagnoser;

public class ProjectBuildDiagnoser implements ErrorDiagnoser
{
    public boolean canDiagnose(final Throwable error) {
        return DiagnosisUtils.containsInCausality(error, ProjectBuildingException.class);
    }
    
    public String diagnose(final Throwable error) {
        final ProjectBuildingException pbe = (ProjectBuildingException)DiagnosisUtils.getFromCausality(error, ProjectBuildingException.class);
        final StringBuffer message = new StringBuffer();
        message.append("Error building POM (may not be this project's POM).").append("\n\n");
        message.append("\nProject ID: ").append(pbe.getProjectId());
        if (pbe instanceof InvalidProjectModelException) {
            final InvalidProjectModelException ipme = (InvalidProjectModelException)pbe;
            message.append("\nPOM Location: ").append(ipme.getPomLocation());
            final ModelValidationResult result = ipme.getValidationResult();
            if (result != null) {
                message.append("\nValidation Messages:\n\n").append(ipme.getValidationResult().render("    "));
            }
        }
        message.append("\n\n").append("Reason: ").append(pbe.getMessage());
        message.append("\n\n");
        return message.toString();
    }
}
