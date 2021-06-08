// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.event.implement;

import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.util.introspection.Info;
import org.apache.velocity.context.Context;
import java.util.ArrayList;
import java.util.List;
import org.apache.velocity.util.RuntimeServicesAware;
import org.apache.velocity.app.event.InvalidReferenceEventHandler;

public class ReportInvalidReferences implements InvalidReferenceEventHandler, RuntimeServicesAware
{
    public static final String EVENTHANDLER_INVALIDREFERENCE_EXCEPTION = "eventhandler.invalidreference.exception";
    List invalidReferences;
    private boolean stopOnFirstInvalidReference;
    
    public ReportInvalidReferences() {
        this.invalidReferences = new ArrayList();
        this.stopOnFirstInvalidReference = false;
    }
    
    public Object invalidGetMethod(final Context context, final String reference, final Object object, final String property, final Info info) {
        this.reportInvalidReference(reference, info);
        return null;
    }
    
    public Object invalidMethod(final Context context, final String reference, final Object object, final String method, final Info info) {
        if (reference == null) {
            this.reportInvalidReference(object.getClass().getName() + "." + method, info);
        }
        else {
            this.reportInvalidReference(reference, info);
        }
        return null;
    }
    
    public boolean invalidSetMethod(final Context context, final String leftreference, final String rightreference, final Info info) {
        this.reportInvalidReference(leftreference, info);
        return false;
    }
    
    private void reportInvalidReference(final String reference, final Info info) {
        final InvalidReferenceInfo invalidReferenceInfo = new InvalidReferenceInfo(reference, info);
        this.invalidReferences.add(invalidReferenceInfo);
        if (this.stopOnFirstInvalidReference) {
            throw new ParseErrorException("Error in page - invalid reference.  ", info, invalidReferenceInfo.getInvalidReference());
        }
    }
    
    public List getInvalidReferences() {
        return this.invalidReferences;
    }
    
    public void setRuntimeServices(final RuntimeServices rs) {
        this.stopOnFirstInvalidReference = rs.getConfiguration().getBoolean("eventhandler.invalidreference.exception", false);
    }
}
