// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.util.Date;
import org.netbeans.lib.cvsclient.event.EventManager;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.event.EnhancedMessageEvent;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

class MergedResponse extends UpdatedResponse
{
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        super.process(loggedDataInputStream, responseServices);
        final EventManager eventManager = responseServices.getEventManager();
        if (eventManager.isFireEnhancedEventSet()) {
            eventManager.fireCVSEvent(new EnhancedMessageEvent(this, "Merged_Response_File_Path", this.localFile));
        }
    }
    
    protected String getEntryConflict(final Date date, final boolean b) {
        if (!b) {
            return "Result of merge";
        }
        return "Result of merge+" + this.getDateFormatter().format(date);
    }
}
