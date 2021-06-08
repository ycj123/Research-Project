// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.usability.diagnostics;

import org.codehaus.plexus.context.ContextException;
import org.codehaus.plexus.context.Context;
import java.util.Iterator;
import org.codehaus.plexus.component.repository.exception.ComponentLifecycleException;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import java.util.List;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Contextualizable;
import org.codehaus.plexus.logging.AbstractLogEnabled;

public class ErrorDiagnostics extends AbstractLogEnabled implements Contextualizable
{
    public static final String ROLE;
    private PlexusContainer container;
    private List errorDiagnosers;
    
    public void setErrorDiagnosers(final List errorDiagnosers) {
        this.errorDiagnosers = errorDiagnosers;
    }
    
    public String diagnose(final Throwable error) {
        List diags = this.errorDiagnosers;
        boolean releaseDiags = false;
        boolean errorProcessed = false;
        String message = null;
        try {
            if (diags == null) {
                releaseDiags = true;
                try {
                    diags = this.container.lookupList(ErrorDiagnoser.ROLE);
                }
                catch (ComponentLookupException e) {
                    this.getLogger().error("Failed to lookup the list of error diagnosers.", e);
                }
            }
            if (diags != null) {
                for (final ErrorDiagnoser diagnoser : diags) {
                    if (diagnoser.canDiagnose(error)) {
                        errorProcessed = true;
                        message = diagnoser.diagnose(error);
                        break;
                    }
                }
            }
        }
        finally {
            if (releaseDiags && diags != null) {
                try {
                    this.container.releaseAll(diags);
                }
                catch (ComponentLifecycleException e2) {
                    this.getLogger().debug("Failed to release error diagnoser list.", e2);
                }
            }
            if (!errorProcessed) {
                message = new PuntErrorDiagnoser().diagnose(error);
            }
        }
        return message;
    }
    
    public void contextualize(final Context context) throws ContextException {
        this.container = (PlexusContainer)context.get("plexus");
    }
    
    static {
        ROLE = ErrorDiagnostics.class.getName();
    }
    
    private static class PuntErrorDiagnoser implements ErrorDiagnoser
    {
        public boolean canDiagnose(final Throwable error) {
            return true;
        }
        
        public String diagnose(final Throwable error) {
            final StringBuffer message = new StringBuffer();
            message.append(error.getMessage());
            DiagnosisUtils.appendRootCauseIfPresentAndUnique(error, message, false);
            return message.toString();
        }
    }
}
